<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테마 등록</title>
</head>
<body>

<h1>테마 등록</h1>

<form action="${pageContext.request.contextPath}/theme/enroll" method="post">

	<div>테마명 <input type="text" name="themeName"></div>
	<div>소속카페 <input type="text" name="cafeName"></div>
	<div>난이도
		<select name="difficulty">
			<option value="1">★</option>
			<option value="2">★★</option>
			<option value="3">★★★</option>
			<option value="4">★★★★</option>
			<option value="5">★★★★★</option>
		</select>
	</div>
	<div>소요시간(분) <input type="number" name="runtime"></div>
	<div>최대인원 <input type="number" name="maxPeople"></div>
	<div>가격(원) <input type="number" name="price"></div>
	<div>설명 <textarea name="description" rows="" cols=""></textarea></div>

	<%-- 이미지 업로드
	<div>이미지 <input type="file" name="roomImg" accept="image/*"></div>
	--%>

	<button type="submit">등록</button>
	<button type="button">취소</button>

</form>

</body>
</html>
