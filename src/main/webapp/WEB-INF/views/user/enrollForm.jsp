<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/dist/css/common.css">
<style>

	.auth-wrap {
		max-width: 560px;
		margin: 40px auto;
		padding: 0 20px;
	}

	.auth-logo {
		display: flex;
		align-items: baseline;
		justify-content: center;
		gap: 0;
		text-decoration: none;
		margin-bottom: 28px;
	}
	.auth-logo .logo-main {
		font-weight: 900;
		font-size: 2.4rem;
		letter-spacing: -2px;
		color: var(--ne-text);
		line-height: 1;
	}

	.page-title {
		font-size: 1.3rem;
		font-weight: 800;
		color: var(--ne-text);
		padding-bottom: 0.6rem;
		margin-bottom: 1.5rem;
		border-bottom: 2px solid var(--ne-primary);
	}
		
	.gender-row {
		display: flex;
		gap: 24px;
		padding: 6px 0;
	}

	.btn-row {
		display: flex;
		justify-content: flex-end;
		gap: 8px;
		margin-top: 1.5rem;
	}
	.btn-row .btn {
		min-width: 110px;
	}
</style>
</head>
<body>

<div class="auth-wrap">

	<a href="${pageContext.request.contextPath}/" class="auth-logo">
		<span class="logo-main">Noexit</span>
	</a>

	<h2 class="page-title">회원가입</h2>

	<form action="${pageContext.request.contextPath}/user/enroll" method="post">

		<div class="mb-3">
			<label for="loginId" class="form-label">아이디<span class="form-required">*</span></label>
			<input type="text" id="loginId" name="loginId" class="form-control" placeholder="6~20자 영문/숫자">
		</div>

		<div class="mb-3">
			<label for="password" class="form-label">비밀번호<span class="form-required">*</span></label>
			<input type="password" id="password" name="password" class="form-control" placeholder="8자 이상">
		</div>

		<div class="mb-3">
			<label for="nickName" class="form-label">닉네임<span class="form-required">*</span></label>
			<input type="text" id="nickName" name="nickName" class="form-control">
		</div>

		<div class="mb-3">
			<label for="name" class="form-label">이름<span class="form-required">*</span></label>
			<input type="text" id="name" name="name" class="form-control">
		</div>

		<div class="mb-3">
			<label for="email" class="form-label">이메일<span class="form-required">*</span></label>
			<input type="email" id="email" name="email" class="form-control" >
		</div>

		<div class="mb-3">
			<label for="phone" class="form-label">연락처<span class="form-required">*</span></label>
			<input type="text" id="phone" name="phone" class="form-control" >
		</div>

		<div class="mb-3">
			<label class="form-label">성별</label>
			<div class="gender-row">
				<div class="form-check">
					<input class="form-check-input" type="radio" name="gender" value="M" id="genderM">
					<label class="form-check-label" for="genderM">남</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="gender" value="F" id="genderF">
					<label class="form-check-label" for="genderF">여</label>
				</div>
			</div>
		</div>

		<div class="mb-3">
			<label for="birthDate" class="form-label">생년월일</label>
			<input type="date" id="birthDate" name="birthDate" class="form-control">
		</div>

		<div class="btn-row">
			<button type="button" class="btn btn-outline-primary" onclick="history.back()">취소</button>
			<button type="submit" class="btn btn-primary">가입하기</button>
		</div>

	</form>

</div>
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