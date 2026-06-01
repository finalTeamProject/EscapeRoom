 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <script src="https://code.jquery.com/jquery.min.js"></script>
  <script type="text/javascript">

  $(function(){
        let isIdChecked = false;

        // 중복확인 버튼
        $("#idChkBtn").click(function(){
                $("#error").css("display", "none");   

                let loginId = $("#loginId").val().trim();

                if (loginId === "") {
                        $("#error").html("아이디를 입력해주세요.")
                                   .css({color: "red", display: "inline"});
                        $("#loginId").focus();
                        return;
                }

                $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/user/id-check",
                        data: { loginId: loginId },
                        success: function(result){
                                if (result === "AVAILABLE") {
                                        $("#error").html("사용 가능한 아이디입니다.")
                                                   .css({color: "blue", display: "inline"});
                                        $("#loginId").prop("readonly", true);
                                        isIdChecked = true;
                                } else {
                                        $("#error").html("이미 사용 중인 아이디입니다.")
                                                   .css({color: "red", display: "inline"});
                                        $("#loginId").val("").focus();
                                }
                        },
                        error: function(){
                                $("#error").html("오류가 발생했습니다.")
                                           .css({color: "red", display: "inline"});
                        }
                });
        });

        // 회원가입 버튼
        $("#signUpBtn").click(function(){
                $("#error").css("display", "none");                 

                if (!isIdChecked) {
                        $("#error").html("아이디 중복 확인은 필수입니다.")
                                   .css({color: "red", display: "inline"});
                        $("#idChkBtn").focus();
                        return;
                }
                $("#signUpForm").submit();
        });
  });
  </script>
  </head>
  <body>

  <h1>회원가입</h1>

  <form action="${pageContext.request.contextPath}/user/enroll" method="post" id="signUpForm">

        <div>아이디 <input type="text" name="loginId" id="loginId">
                      <button type="button" id="idChkBtn">중복확인</button>
               	  <span id="error" style="font-size: small; display: none;"></span>       
        </div>
        <div>비밀번호 <input type="password" name="password"></div>
        <div>닉네임 <input type="text" name="nickName"></div>
        <div>이름 <input type="text" name="name"></div>
        <div>이메일 <input type="text" name="email"></div>
        <div>연락처 <input type="text" name="phone"></div>
        <div>
                성별
                <input type="radio" name="gender" value="M">남
                <input type="radio" name="gender" value="F">여
        </div>
        <div>생년월일 <input type="date" name="birthDate"></div>

        <button type="button" id="signUpBtn">확인</button>
        <button type="button">취소</button>

        <br><br>
        

  </form>

  </body>
  </html>