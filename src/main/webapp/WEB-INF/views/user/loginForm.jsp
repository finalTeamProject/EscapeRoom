<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<h1>로그인</h1>

<form action="${pageContext.request.contextPath}/user/login" method="post">

	<div>아이디 <input type="text" name="loginId"></div>
	<div>비밀번호 <input type="password" name="password"></div>

	<button type="submit">로그인</button>
	<button type="button">취소</button>

</form>

</body>
</html>
