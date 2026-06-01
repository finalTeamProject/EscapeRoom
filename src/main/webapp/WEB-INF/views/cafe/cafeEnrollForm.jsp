<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카페 등록</title>
</head>
<body>

<h1>카페 등록 신청</h1>

<form action="${pageContext.request.contextPath}/cafe/enroll" method="post">

	<div>카페명 <input type="text" name="cafeName"></div>
	<div>대표자명 <input type="text" name="ownerName"></div>
	<div>사업자번호 <input type="text" name="businessNumber"></div>
	<div>주소 <input type="text" name="address" size="40"></div>
	<div>전화번호 <input type="text" name="phone"></div>
	<div>영업시간
		<input type="time" name="openTime"> ~ <input type="time" name="closeTime">
	</div>
	<div>카페소개 <textarea name="description" rows="" cols=""></textarea></div>

	<button type="submit">등록 신청</button>
	<button type="button">취소</button>

</form>

</body>
</html>
