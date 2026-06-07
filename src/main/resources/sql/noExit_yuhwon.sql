SELECT USER
FROM DUAL;




--==============================================================================
-- FUNCTION(함수) 생성
--==============================================================================
-- 1. 파티인원수 구하는 함수
CREATE OR REPLACE FUNCTION FN_MEMBER_COUNT
( P_PARTY_ID IN PARTY.PARTY_ID%TYPE
)
RETURN NUMBER
IS
    V_TOTAL_COUNT   NUMBER := 0;
BEGIN

    SELECT COUNT(*) + 1 INTO V_TOTAL_COUNT
    FROM PARTY_MEMBER PM 
        JOIN PARTY_APPLY PA 
        ON PM.APPLY_ID = PA.APPLY_ID
        JOIN PARTY P 
        ON PA.PARTY_ID = P.PARTY_ID
    WHERE P.PARTY_ID = P_PARTY_ID 
        AND NOT EXISTS(
                    SELECT PK.MEMBER_ID
                    FROM PARTY_KICK PK
                    WHERE PK.MEMBER_ID = PM.MEMBER_ID
                    
                    );
                    
    RETURN V_TOTAL_COUNT;
    
    EXCEPTION 
        WHEN OTHERS THEN
            RETURN 0;

END;
/

DESC PARTY_KICK;


DESC PARTY_MEMBER;



-- 2. 예약오픈아이디로 유저아이디를 보고 사장인지, 매니저인지, 일반사용자인지 판별하는 함수
-- 취소일자도 넘겨줘야함 
CREATE OR REPLACE FUNCTION FN_GET_USER_ROLE
(
     P_RES_OPEN_ID  RES_OPEN.RES_OPEN_ID%TYPE
    , P_USER_ID     RESERVATION_CANCEL.USER_ID%TYPE
    , P_CANCELD_AT  RESERVATION_CANCEL.CANCEL_AT%TYPE DEFAULT SYSDATE
)
RETURN VARCHAR2
IS
    V_CAFE_ID       CAFE.CAFE_ID%TYPE;
    V_OWNER_ID      CAFE.USER_ID%TYPE;
    V_ROLE_NAME     VARCHAR2(20) := 'USER';
    V_EVENT_ID      NUMBER;

BEGIN
    
    -- 입력한 예약오픈아이디가 어느 카페건지 확인 
    SELECT R.CAFE_ID INTO V_CAFE_ID
    FROM RES_OPEN RO  
        JOIN ROOM R
        ON RO.ROOM_ID = R.ROOM_ID
    WHERE RES_OPEN_ID = P_RES_OPEN_ID;
    
    -- 카페아이디로 카페 사장아이디 얻어내기 
    SELECT USER_ID INTO V_OWNER_ID
    FROM CAFE
    WHERE CAFE_ID = V_CAFE_ID;
    
    IF (V_OWNER_ID = P_USER_ID) THEN
        V_ROLE_NAME := 'OWNER';
        RETURN V_ROLE_NAME;
    END IF;
    
    -- 카페아이디로 매니저아이디 얻어내기
    BEGIN
        SELECT REG_EVENT_ID INTO V_EVENT_ID
        FROM 
        (
            SELECT REG_EVENT_ID 
            FROM MANAGER_HISTORY
            WHERE CAFE_ID = V_CAFE_ID 
                AND USER_ID = P_USER_ID
                AND CREATED_AT <= P_CANCELD_AT
            ORDER BY CREATED_AT DESC
        )
        WHERE ROWNUM = 1;
        
        IF (V_EVENT_ID = 1) THEN
            V_ROLE_NAME := 'MANAGER';
        END IF;

        
        EXCEPTION 
            WHEN NO_DATA_FOUND THEN
                V_ROLE_NAME := 'USER';
    END;
    
    RETURN V_ROLE_NAME;

    EXCEPTION
        WHEN OTHERS THEN
            RETURN 'USER';
END;
/



DESC MANAGER_HISTORY;


-- 3. 출석상태 판별함수(사용자예약아이디, 사용자아이디)
CREATE OR REPLACE FUNCTION FN_GET_ATTEND_STATUS
(
    P_RESERVATION_ID ATTENDANCE.RESERVATION_ID%TYPE
    , P_USER_ID        ATTENDANCE_DETAIL.USER_ID%TYPE
)
RETURN VARCHAR2
IS
    V_VALID_RESERVATION NUMBER;
    V_ATTEND_STATUS NUMBER;

BEGIN

    -- 출석체크테이블에 사용자예약아이디가 존재하는지 체크
        SELECT COUNT(*) INTO V_VALID_RESERVATION
        FROM ATTENDANCE
        WHERE RESERVATION_ID = P_RESERVATION_ID;
        
        IF (V_VALID_RESERVATION < 1) THEN
            RETURN '출석 미등록';
        END IF;
    
    BEGIN
        -- 파라미터로 넘겨받은 사용자예약아이디와 사용자아이디로 출석상태 반환 
        SELECT AD.ATTEND_STATUS_ID INTO V_ATTEND_STATUS
        FROM ATTENDANCE A
            JOIN ATTENDANCE_DETAIL AD
            ON A.ATTENDANCE_ID = AD.ATTENDANCE_ID
        WHERE AD.USER_ID = P_USER_ID
            AND A.RESERVATION_ID = P_RESERVATION_ID;
            
            IF (V_ATTEND_STATUS = 1)  THEN
                RETURN '출석 완료';
            ELSIF (V_ATTEND_STATUS = 2) THEN 
                RETURN '노쇼';
            ELSE
                RETURN '출석 미등록';
            END IF;
            
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                RETURN '출석 미등록';
    END;
    
    EXCEPTION
        WHEN OTHERS THEN
            RETURN '상태를 찾을 수 없습니다.';
    
END;
/

DESC ATTENDANCE;
DESC ATTENDANCE_DETAIL;

SELECT *
FROM ATTEND_STATUS;






--==============================================================================
-- VIEW 생성
--==============================================================================


-- 1. 활성 파티(비활성파티 제외) 뷰
-- 파티아이디, 파티명, 파티장아이디, 성별조건, 방장한마디, 파티생성일자, 예약오픈예정번호, 예약오픈변경일 
CREATE OR REPLACE VIEW VW_ACTIVE_PARTY
AS
SELECT P.PARTY_ID, P.PARTY_NAME, P.USER_ID AS LEADER_ID
,PR.RES_OPEN_ID
, TO_CHAR(PR.CREATED_AT, 'YYYY-MM-DD HH24:MI:SS') AS UPDATED_AT  
,P.GENDER_ID, P.MESSAGE
,TO_CHAR(P.CREATED_AT, 'YYYY-MM-DD') AS CREATED_AT
FROM PARTY P JOIN PARTY_ROOM PR
    ON P.PARTY_ID = PR.PARTY_ID
WHERE NOT EXISTS(
                SELECT PD.PARTY_ID
                FROM PARTY_DROP PD
                WHERE P.PARTY_ID = PD.PARTY_ID
                );
                
                
-- 2. 활성 예약 오픈(비활성 예약 오픈 제외) 뷰
-- 예약오픈아이디, 테마(방)아이디, 테마명, 카페아이디, 카페명, 예약오픈일시, 등록일시,
CREATE OR REPLACE VIEW VW_ACTIVE_RES_OPEN
AS
SELECT RO.RES_OPEN_ID, R.CAFE_ID, C.CAFE_NAME
, RO.ROOM_ID, R.ROOM_NAME
, TO_CHAR(RO.OPEN_AT, 'YYYY-MM-DD HH24:MI') AS OPEN_AT
, TO_CHAR(RO.CREATED_AT, 'YYYY-MM-DD') AS CREATED_AT
FROM RES_OPEN RO JOIN ROOM R
    ON RO.ROOM_ID = R.ROOM_ID
    JOIN CAFE C
    ON R.CAFE_ID = C.CAFE_ID
WHERE NOT EXISTS (
    SELECT RD.RES_OPEN_ID 
    FROM RES_DROP RD 
    WHERE RO.RES_OPEN_ID = RD.RES_OPEN_ID
);

desc res_open;



-- 3. 모든 예약 이력 뷰
-- 취소된거 포함 모든 예약 이력 조회
-- 사용자예약아이디, 파티아이디, 파티명, 파티장 아이디, 파티장이름, 파티장연락처, 파티원인원수(파티장포함)
-- ,예약오픈아이디, 카페아이디, 카페명, 테마아이디, 테마명, 예약오픈일시, 예약한일자 
CREATE OR REPLACE VIEW VW_RESERVATION_ALL
AS
SELECT 
    RV.RESERVATION_ID, RV.PARTY_ID, P.PARTY_NAME
    , P.USER_ID AS LEADER_ID, UI.NAME AS LEADER_NAME, UI.PHONE AS LEADER_PHONE
    , FN_MEMBER_COUNT(RV.PARTY_ID) AS TOTAL_MEMBER
    , PR.RES_OPEN_ID, VARO.CAFE_ID, VARO.CAFE_NAME, VARO.ROOM_ID, VARO.ROOM_NAME
    , TO_CHAR(VARO.OPEN_AT, 'YYYY-MM-DD HH24:MI') AS OPEN_AT
    , VARO.OPEN_AT AS OPEN_AT_DT
    , TO_CHAR(RV.CREATED_AT, 'YYYY-MM-DD') AS BOOKED_AT
FROM RESERVATION RV
    JOIN PARTY_ROOM PR        
        ON RV.PARTY_ID = PR.PARTY_ID
    JOIN VW_ACTIVE_RES_OPEN VARO 
        ON VARO.RES_OPEN_ID = PR.RES_OPEN_ID
    JOIN PARTY P     
        ON RV.PARTY_ID = P.PARTY_ID
    JOIN USER_INFO UI            
    ON P.USER_ID = UI.USER_ID;
/

DESC RESERVATION;



-- 4. 예약 된 오픈(슬롯) 뷰
-- 어떤 예약오픈아이디가 어떤 파티에 예약이 되었는가
-- 예약오픈아이디, 카페아이디, 카페명, 테마아이디, 테마명, 예약오픈일시
-- 사용자예약아이디, 파티아이디, 파티명, 파티장, 파티장이름, 파티장연락처, 인원수(파티원+파티장), 예약된 일자 
-- 활성 사용자 예약 뷰에서 매칭 개설 번호를 찾아서
-- 매칭 예약(PARTY_ROOM)테이블과 조인으로 예약 오픈 예정 번호 찾기 
CREATE OR REPLACE VIEW VW_RES_OPEN_BOOKED
AS
SELECT *
FROM VW_RESERVATION_ALL VRA
WHERE NOT EXISTS (
         SELECT 1
        FROM RESERVATION_CANCEL RC
        WHERE RC.RESERVATION_ID = VRA.RESERVATION_ID
);


-- 5. 예약 가능 목록 뷰
-- 예약오픈아이디, 예약오픈일시, 카페아이디, 카페명, 테마아이디, 테마명, 등록일
CREATE OR REPLACE VIEW VW_RES_OPEN_UNBOOKED
AS
SELECT VRO.RES_OPEN_ID, VRO.CAFE_ID, VRO.CAFE_NAME
    , VRO.ROOM_ID, VRO.ROOM_NAME
    , TO_CHAR(VRO.OPEN_AT, 'YYYY-MM-DD HH24:MI') AS OPEN_AT
    , TO_CHAR(VRO.CREATED_AT, 'YYYY-MM-DD') AS CREATED_AT
FROM VW_ACTIVE_RES_OPEN VRO
WHERE NOT EXISTS(
                    SELECT VRB.RES_OPEN_ID
                    FROM VW_RES_OPEN_BOOKED VRB
                    WHERE VRO.RES_OPEN_ID = VRB.RES_OPEN_ID
                );




-- 6. 예약 취소 목록 뷰
-- 예약을 했었는데 취소했던 목록
-- 어떤 예약아이디가 어떤 카페, 테마, 일시였는데
-- 어떤 파티에서 언제 예약했다가 언제 예약취소를 했다
-- 혹은 카페관계자가 강제로 예약 취소를 언제 했다
-- 사용자예약아이디, 파티아이디, 파티명, 예약오픈아이디, 카페아이디, 카페명, 테마아이디, 테마명, 예약오픈일시, 예약한 날짜, 취소한 날짜, 취소타입, 취소한 사용자의 역할, 취소한 사용자의 아이디 
CREATE OR REPLACE VIEW VW_RES_OPEN_CANCELED
AS
SELECT RC.RESERVATION_ID, VRA.PARTY_ID, VRA.PARTY_NAME
, VRA.RES_OPEN_ID, VRA.CAFE_ID, VRA.CAFE_NAME, VRA.ROOM_ID, VRA.ROOM_NAME
, VRA.OPEN_AT, VRA.BOOKED_AT
, TO_CHAR(RC.CANCEL_AT, 'YYYY-MM-DD') AS CANCELD_AT
, CASE
    WHEN RC.USER_ID = VRA.LEADER_ID THEN 'USER_CANCEL' 
    WHEN FN_GET_USER_ROLE(VRA.RES_OPEN_ID, RC.USER_ID, RC.CANCEL_AT) IN ('OWNER', 'MANAGER') THEN 'CAFE_CANCEL'
    ELSE 'SYSTEM_CANCEL' END AS CANCEL_TYPE
, FN_GET_USER_ROLE(VRA.RES_OPEN_ID, RC.USER_ID, RC.CANCEL_AT) AS ROLE
, RC.USER_ID
FROM VW_RESERVATION_ALL VRA
    JOIN RESERVATION_CANCEL RC
    ON VRA.RESERVATION_ID = RC.RESERVATION_ID;




SELECT *
FROM RES_OPEN;




DESC RESERVATION;

