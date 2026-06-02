<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<h1>회원가입</h1>

<form action="${pageContext.request.contextPath}/user/enroll" method="post">

	<div>아이디 <input type="text" name="loginId"></div>
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

	<button type="submit">확인</button>
	<button type="button">취소</button>

</form>

</body>
</html>
