<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="ne-header d-flex justify-content-around align-items-center bg-white shadow-sm">
	<div class="nav-left">
			<ul class="d-flex m-0 gap-3">
				<li><strong>관리자 페이지</strong></li>
			</ul>
		</div>
		
		<div class="logo">
			<h1 class="m-0">
				<a href="${pageContext.request.contextPath }/admin/main" class="no-hover">
					<img src="${pageContext.request.contextPath }/dist/images/logo.png" alt="로고이미지" style="height: 40px;" />
				</a>
			</h1>
		</div>
		<div class="nav-right">
			<ul class="d-flex m-0 gap-3">
				<li><a href="${pageContext.request.contextPath }/admin/login">LOGOUT</a></li>
			</ul>
		</div>
	</header>
		
</body>
</html>