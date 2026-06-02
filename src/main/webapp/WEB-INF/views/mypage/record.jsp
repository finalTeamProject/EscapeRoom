<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>record.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/common.css">

<style type="text/css">

	.main-body {
		display: flex;                		
		width: 100%;
		box-sizing: border-box;
		padding-left: 2rem;
		padding-right: 2rem;
		gap: 1.5rem;
	}

	.main-content {
		flex-grow: 1;
		min-width: 0;
	}
	
	.right-sidebar {
		width: 340px;                    
		flex-shrink: 0;                  
		display: flex;
		flex-direction: column;
		gap: 1rem;                       
	}
	
	.record-item-body {
		display: flex;
		gap: 1.5rem;
		align-items: center;
	}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 헤더 높이만큼 본문 밀기( padding-top ) -->
<div class="main-body ne-body-offset">
	
	<!-- 왼쪽 사이드 바 영역 -->
	<%@ include file="/WEB-INF/views/common/leftSideBar.jsp" %>

	<!-- 메인 구성 영역 (개인기록, 매칭기록, 예약내역이 이 구역 안에서만 바뀜) -->
	<div class="main-content">
	
		<!-- 섹션 카드 -->
		<div class="ne-sc">
			<div class="ne-sc-title" style="font-size: 24px;">개인 기록</div>
			
			<div class="ne-card ne-card-accent p-4 mb-3">
				<div class="record-item-body">
					<div class="ne-room-img" style="width: 100px; height: 100px; flex-shrink: 0; font-size: 1.2rem;">
					</div>
					<div>
						<h4 class="m-0 mb-2" style="font-size: 20px;">테마 제목</h4>
						<p class="m-0 text-secondary small">플레이 일시: 2026-06-01 14:00</p>
					</div>
				</div>
			</div>
			
			<div class="ne-card ne-card-accent p-4 mb-3">
				<div class="record-item-body">
					<div class="ne-room-img" style="width: 100px; height: 100px; flex-shrink: 0; font-size: 1.2rem;">
					</div>
					<div>
						<h4 class="m-0 mb-2" style="font-size: 20px;">테마 제목</h4>
						<p class="m-0 text-secondary small">플레이 일시: 2026-06-01 14:00</p>
					</div>
				</div>
			</div>

			
		</div><!-- 섹션 -->
		
	</div><!-- 메인 구성 영역 -->

<!-- 우측 사이드 바 import -->
<%@ include file="/WEB-INF/views/common/rightSideBar.jsp" %>

</div>

<!-- 푸터 import -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>