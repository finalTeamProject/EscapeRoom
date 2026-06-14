<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoExit - 매니저 등록</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/common.css">
<link rel="stylesheet" href='${pageContext.request.contextPath}/dist/css/manager.css' />
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<main class="ne-main-content ne-body-offset">
	<div class="ne-container d-flex">
		<aside class="col-md-2">
			<%@ include file="/WEB-INF/views/common/ownerSide.jsp"%>
		</aside>
		<div class="col-md-10">
			<div class="container my-4" style="max-width: 600px;">
				<div class="ne-sc">
					<div class="ne-sc-title fs-5">매니저 등록</div>

					<form action="${pageContext.request.contextPath}/owner/manager/enroll" method="post">

						<div class="mb-3">
							<label for="cafeId" class="form-label">담당 카페</label>
							<select id="cafeId" name="cafeId" class="form-select" required>
								<option value="">-- 카페 선택 --</option>
								<c:forEach var="cafe" items="${cafeList}">
									<option value="${cafe.cafeId}">${cafe.cafeName}</option>
								</c:forEach>
							</select>
						</div>

						<div class="mb-3">
							<label for="loginId" class="form-label">매니저 아이디</label>
							<input type="text" id="loginId" name="loginId" class="form-control">
							<c:if test="${not empty errorMessage}">
								<div class="text-danger mt-2">${errorMessage}</div>
							</c:if>
						</div>

						<div class="text-end mt-4">
							<a href="${pageContext.request.contextPath}/owner/manager" class="btn btn-secondary">취소</a>
							<button type="submit" class="btn btn-primary">등록</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
