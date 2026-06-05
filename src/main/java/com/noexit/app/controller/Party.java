package com.noexit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/party/*")
public class Party
{

	/*
	 * 파티 개설 폼으로 이동
	 */
	@GetMapping("write")
	public String partyWrite(@RequestParam(name="slotId", defaultValue = "0") long slotId, Model model)
	{
		/*
		 * 유효성 검사 목록
		 *
		 * 로그인한 사용자인가?
		 * 
		 * 존재하는 slotId 인가?
		 * 
		 * 예약되지 않은 slodId 인가?
		 * 
		 * mode = "write" 전달
		 * 
		 */
		
		/*
		 * 가져와야 하는 데이터
		 * 
		 * 카페명
		 * 테마명
		 * 날짜
		 * 시간
		 * 최소 인원
		 * 최대 인원
		 * 가격
		 */
		
		return "party/partywrite";
	}
 
 	/*
 	 * 파티 insert 액션 처리
 	 * PartyDTO 를 파라미터로 받음 
 	 */
	@PostMapping("write")
	public String partyInsert()
	{
		// slotId, partyName, genderCondition, partyComment
		
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인한 사용자인가?
		 * 
		 * 존재하는 slotId 인가?
		 * 
		 * 예약되지 않은 slodId 인가?
		 * 
		 * 파티명 길이 
		 * 
		 * 파티코멘트 길이
		 */
		
		/*
		 * 가져올 데이터 없음
		 */
		
		return "redirect:/party/board/1";
	}
	
	/*
	 * 파티 리스트로 이동
	 */
	@GetMapping("list")
	public String partyListPage(@RequestParam(name="schType", defaultValue = "themeName") String schType
						  , @RequestParam(name="kwd", defaultValue = "") String kwd
						  , Model model)
	{
		if(!kwd.isBlank())
		{
			model.addAttribute("schType",schType);
			model.addAttribute("kwd", kwd);
		}
		
		/*
		 * 여유 되면 필터도 넣어야 하는데 머가 되게 많네?
		 */
		
		return "party/partylist";
	}
	
	/*
	 * 파티 리스트 받아오기
	 * 
	 * AJAX 처리
	 */
	@PostMapping("list")
	public String partyListData(@RequestParam(name="schType", defaultValue = "themeName") String schType
							  , @RequestParam(name="kwd", defaultValue = "") String kwd
							  , @RequestParam(name="lastId") long lastId
							  , Model model)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 없음
		 */
		
		/*
		 * 가져와야 하는 데이터
		 * 
		 * 테마 번호
		 * 테마 이미지 경로
		 * 테마명
		 * 날짜
		 * 시간
		 * 파티명
		 * 평균 매너 온도
		 * 평균 나이
		 * 현재 인원 수 
		 */
		
		if(!kwd.isBlank())
		{
			
		}
		
		return "";
	}
	
	/*
	 * 파티 상세 창으로 이동
	 */
	@GetMapping("info/{partyid}")
	public String partyDetail(@PathVariable(name="partyid") long partyId, Model model)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 존재하는 partId 인가?
		 * 
		 * active 상태인 파티인가?
		 * 
		 * 
		 * 상태 : anonymous / crew / matching / idle
		 * 
		 * 로그인 / 비로그인	비로그인이면 신청 버튼 비활성화
		 * 파티원 / 비파티원    파티원이면   신청 버튼 비활성화
		 * 
		 * 신청자 / 비신청자	신청자면     취소 버튼 활성화 
		 *                      비신청자면   신청 버튼 활성화
		 * 
		 * 구분해서 바인딩
		 */
		
		/*
		 * 가져와야 하는 데이터 
		 * 
		 * 파티번호
		 * 파티상태
		 * 파티명
		 * 카페명
		 * 테마명
		 * 날짜
		 * 시간
		 * 최소 인원
		 * 최대 인원
		 * 성별 조건
		 * 파티 코멘트
		 * 
		 * 파티원 정보
		 * 닉네임
		 * 나이
		 * 성별
		 * 매너온도
		 * 포지션(파티장/파티원)
		 */
		
		return "party/partyinfo";
	}
	
	/*
	 * 파티 보드 창으로 이동
	 */
	@GetMapping("board/{partyid}")
	public String partyBoard(@PathVariable(name="partyid") long partyId, Model model)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는지?   		비로그인이면 로그인창으로
		 * 
		 * 존재하는 파티인가?
		 * 
		 * hidden 상태가 아닌 파티인가?
		 * 
		 * 파티장/파티원 인지?  	아니라면 partyList 로
		 * 
		 * 파티장/파티원 구분해서 바인딩
		 * 
		 * 파티 보드 활성화/비활성화 구분
		 */
		
		/*
		 * 가져와야 하는 데이터 
		 * 없네? 
		 * 죄다 AJAX 라서
		 */
		
		return "party/partyboard";
	}
	
	/*
	 * 파티 신청 insert 액션 처리
	 */
	@PostMapping("apply/{partyid}")
	public String partyApplyInsert(@PathVariable(name="partyid") long partyId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는지? 			비로그인이라면 로그인창으로
		 * 
		 * 존재하는 파티인가?
		 * 
		 * active 상태인 파티인가? 
		 * 
		 * 파티장/파티원인지?   	맞다면 파티보드창으로
		 * 
		 * 이미 신청내역이 있는지?  있다면 파티정보창으로
		 * 
		 * 코멘트 길이 제한
		 */
		
		/*
		 * 파티 신청 insert 이후 파티 정보창으로 redirect
		 */
		
		return "redirect:/party/info/" + partyId;
	}
	
	/*
	 * 파티 정보 수정 폼 이동
	 */
	@GetMapping("update/{partyid}")
	public String partyUpdate(@PathVariable(name="partyid") long partyId, Model model)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 파티인가?
		 * 
		 * active/close 상태인 파티인가?
		 * 
		 * 파티장인가?
		 * 
		 * mode="update" 전달
		 * 
		 * 파티 정보 바인딩해서 전달
		 */
		
		/*
		 * 받아와야 하는 데이터
		 * 
		 * 카페명
		 * 테마명
		 * 날짜
		 * 시간
		 * 최소 인원 수
		 * 최대 인원 수
		 * 가격
		 * 파티명
		 * 성별 조건
		 * 파티코멘트
		 */
		
		return "party/partywrite";
	}
	
	/*
	 * 파티 해산 메소드
	 * 해산 이후 파티 리스트로 이동
	 */
	@GetMapping("delete/{partyid}")
	public String partyDelete(@PathVariable(name="partyid") long partyId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 파티 인가?
		 * 
		 * active/close 상태인 파티 인가?
		 * 
		 * 파티장인가?
		 * 
		 * 파티 해산 이후 리스트로 리다이렉트
		 */
		
		/*
		 * 받아올 데이터 
		 * 없음
		 */
		
		return "redirect:/party/list";
	}
	
	/*
	 * 댓글 insert 액션 처리
	 * AJAX 처리
	 */
	@PostMapping("comment/insert")
	public String commentInsert(@RequestParam(name="partyId") long partyId
							  , @RequestParam(name="partyComment") String partyComment)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 파티 인가?
		 * 
		 * active/close/fix 상태인 파티인가?
		 * 
		 * 파티장/파티원 인가?
		 */
		
		/*
		 * 받아올 데이터
		 * 없음
		 */
		
		return "";
	}
	
	/*
	 * 댓글 삭제 메소드
	 * AJAX 처리
	 */
	@PostMapping("comment/delete/{commentid}")
	public String commentDelete(@PathVariable(name="commentid") long commentId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 댓글이 존재하는가?
		 * 
		 * 댓글이 삭제되지 않았는가?
		 * 
		 * 댓글 작성자인가?
		 */
		
		/*
		 * 받아올 데이터
		 * 없음
		 */
		
		return "";
	}
	
	/*
	 * 레디 액션 처리
	 * AJAX 처리
	 */
	@PostMapping("ready/{partyid}")
	public String setReady()
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 파티 인가?
		 * 
		 * 레디를 하지 않은 상태인가?
		 * 
		 * active/close 상태인 파티 인가?
		 * 
		 * 파티원 인가?
		 */
		
		/*
		 * 받아올 데이터
		 * 없음
		 */
		
		return "";
	}
	
	/*
	 * 파티 강퇴 액션 처리
	 * AJAX 처리
	 */
	@PostMapping("kick/{applyid}")
	public String crewKick(@PathVariable(name="applyid") long applyId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 신청 id 인가?
		 * 
		 * active/close 상태인 파티인가?
		 * 
		 * 현재 파티원인가?
		 * 
		 * 파티장인가?
		 */
		
		/*
		 * 받아올 데이터
		 * 없음
		 */
		
		return "";
	}
	
	/*
	 * 파티 탈퇴 액션 처리
	 * 
	 * 파티원 delete
	 * 파티신청 delete
	 * 
	 * AJAX 처리
	 */
	@PostMapping("out/{applyid}")
	public String crewOut(@PathVariable(name="applyid") long applyId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 신청 id 인가?
		 * 
		 * active/close 상태인 파티인가?
		 * 
		 * 현재 파티원인가?
		 * 
		 * 자기자신인가?
		 */
		
		/*
		 * 받아올 데이터
		 * 없음
		 */
		
		return "";
	}
	
	/*
	 * 파티 신청 승인 액션 처리
	 * 
	 * 파티원 insert
	 * 
	 * AJAX 처리
	 */
	@PostMapping("aprvapply/{applyid}")
	public String aprvApply(@PathVariable(name="applyid") long applyId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 신청 id 인가?
		 * 
		 * active 상태인 파티인가?
		 * 
		 * 현재 파티원이 아닌가?
		 * 
		 * 파티장인가?
		 */
		
		/*
		 * 받아올 데이터 없음
		 */
		
		return "";
	}
	
	/*
	 * 파티 신청 거절 액션 처리
	 * 
	 *  apply delete
	 *  
	 *  AJAX 처리
	 */
	@PostMapping("rejectapply/{applyid}")
	public String rejectApply(@PathVariable(name="applyid") long applyId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 신청 id 인가?
		 * 
		 * active/close/fix 상태인 파티인가?
		 * 
		 * 현재 파티원이 아닌가?
		 * 
		 * 파티장인가?
		 */
		
		/*
		 * 받아올 데이터
		 * 없음
		 */
		
		return "";
	}
	
	/*
	 * 파티 정보를 바인딩해서 전달하는 메소드
	 * ㅈㄴ 바쁜 메소드
	 * 
	 * AJAX 처리
	 */
	@PostMapping("data/{partyid}")
	public String partyData(@PathVariable(name="partyid") long partyId)
	{
		/*
		 * 유효성 검사 목록
		 * 
		 * 로그인 했는가?
		 * 
		 * 존재하는 파티인가?
		 * 
		 * 파티 상태가 hidden 이 아닌가?
		 * 
		 * 파티장/파티원 인가?
		 */
		
		/*
		 * 받아올 데이터 
		 * 
		 * 
		 * 파티 정보
		 * 
		 * 슬롯 상태 : 가능 / 불가능
		 * 
		 * 파티 상태
		 * 파티명
		 * 카페명
		 * 테마명
		 * 날짜
		 * 시간
		 * 최소인원
		 * 최대인원
		 * 성별조건
		 * 파티코멘트
		 * 
		 * 
		 * 파티원 정보
		 * 
		 * 닉네임
		 * 나이
		 * 성별
		 * 매너온도
		 * 레디상태
		 * 포지션
		 * 상태
		 * 
		 * 
		 * 파티 댓글 정보
		 * 
		 * 댓글 번호
		 * 작성자
		 * 작성 내용
		 * 삭제 유무
		 * 
		 * 
		 * 파티 신청
		 * 
		 * 닉네임
		 * 나이
		 * 성별
		 * 매너온도
		 * 신청 코멘트
		 * 상태
		 * 
		 * 
		 * 
		 */
		
		return "";
	}
}















