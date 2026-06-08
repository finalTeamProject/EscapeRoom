<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href='${pageContext.request.contextPath }/dist/css/common.css'>
<style type="text/css">
	header{
		width: 100%;
		height: 100px;
	}



	.left-sidebar-wrapper{
		display: flex;
		margin-top: 10px;
	
	}
	
	.ne-card{
		margin: 10px;
	}

	.ne-sc{
		width:100%;
	}
	.ne-card-accent{
		margin:10px;
	}
	
	.ne-card ne-card-accent{
		display:flex;
	}

	.card-flex{
		display: flex;
	}
	
	.cafe-name{
		margin: 10px;
	}
	
	
	.ne-sc-title{
		display: flex;
	}




</style>

</head>
<body>

<%@ include file="/WEB-INF/views/common/adminHeader.jsp" %>


<div class="left-sidebar-wrapper">
	<nav class="ne-side-nav" >
		
		
		<div class="ne-side-nav-section">관리자 메뉴</div>
		<a href="/admin/cafelist" id="record" class="">카페 조회 및 숨김</a>
		<a href="/mypage/party" id="party"  class="">테마 · 리뷰 조회 및 숨김</a>
		<a href="/mypage/reservations" id="reservations"  class="">전체 회원 목록 조회</a>
	</nav>
	
	<div class ="ne-sc"><!-- 메인 섹션 -->

		
		<div class="card-flex justify-content-center gap-3 w-100" style="padding: 10px;">
			
			<div class="ne-card ne-card-accent ne-count" style="border-color: #afdcf1; text-align: center; width: 33.33% !important;">
				<div class="ne-sc-title justify-content-center" style="border-bottom: 1px solid #afdcf1;">
					<div class="m-0 fw-bold fs-4" style="padding:20px;">전체 테마 수</div>
				</div>
				<div class="ne-cafe-count fw-bolder fs-1" style="min-height: 100px; padding: 25px 10px;">
					${status.totalThemeCount }개
				</div>
			</div>
			
			<div class="ne-card ne-card-accent ne-count" style="border-color: #ffcedc; text-align: center; width: 33.33% !important;">
				<div class="ne-sc-title justify-content-center" style="border-bottom: 1px solid #ffcedc;">
					<div class="m-0 fw-bold fs-4" style="padding:20px;">전체 카페 수</div>
				</div>
				<div class="ne-cafe-count fw-bolder fs-1" style="min-height: 100px; padding: 25px 10px;">
					${status.totalCafeCount }개
				</div>
			</div>
			
			<div class="ne-card ne-card-accent ne-count" style="border-color: #cee7c7; text-align: center; width: 33.33% !important;">
				<div class="ne-sc-title justify-content-center" style="border-bottom: 1px solid #cee7c7;">
					<div class="m-0 fw-bold fs-4" style="padding:20px;">전체 회원 수</div>
				</div>
				<div class="ne-cafe-count fw-bolder fs-1" style="min-height: 100px; padding: 25px 10px;">
					${status.totalMemberCount }명
				</div>
			</div>
			
		</div>
	
	</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>


</body>
</html>