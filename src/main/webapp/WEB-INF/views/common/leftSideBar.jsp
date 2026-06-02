<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
/* 왼쪽 사이드바 고정틀 유지 */
.left-sidebar-wrapper {
	width: 280px;
	flex-shrink: 0;
}
.ne-side-profile {
	text-align: center;
	padding: 1.5rem 1rem;
}
.ne-side-profile img {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	object-fit: cover;
	margin-bottom: 1rem;
}
</style>
</head>
<body>

	<div class="left-sidebar-wrapper">
		<nav class="ne-side-nav">
			<div class="ne-side-profile">
				<img src="${pageContext.request.contextPath}/dist/images/zazaz.jpg" alt="프로필">
				<div id="nickName" class="fw-bold mb-2" style="font-size: 20px;">닉네임 바인딩</div>
				<div class="text-muted small">
					<h5 class="m-0" style="font-size: 16px;">어서와!</h5>
					<h6 class="m-0 text-secondary" style="font-size: 14px;">오늘도 좋은 추억 만들자.</h6>
				</div>
			</div>
			
			<div class="ne-side-nav-section">마이 메뉴</div>
			<a href="/mypage/record" id="record" class="${fn:contains(pageContext.request.requestURI, '/record') ? 'active' : ''}">개인 기록</a>
			<a href="/mypage/party" id="party" class="${fn:contains(pageContext.request.requestURI, '/party') ? 'active' : ''}">매칭 목록</a>
			<a href="/mypage/reservations" id="reservations" class="${fn:contains(pageContext.request.requestURI, '/reservations') ? 'active' : ''}">예약 내역</a>
			
			<div class="ne-side-nav-section">기타</div>
			<a href="/" id="home">홈으로</a>
		</nav>
	</div>

</body>
</html>