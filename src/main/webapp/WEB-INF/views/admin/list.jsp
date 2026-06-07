<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카페 조회 및 숨김</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href='${pageContext.request.contextPath}/dist/css/common.css'>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
	.left-sidebar-wrapper {
		display: flex;
		margin-top: 10px;
	}
	
	.ne-sc {
		width: 100%;
		padding: 10px 20px;
	}

	.ne-card {
		margin: 10px;
	}

	.ne-sc-title {
		border-bottom: 1px solid #dee2e6;
		margin-bottom: 20px;
	}

	.search-box {
		max-width: 500px;
	}
	
	.table th {
		background-color: #f8f9fa;
		text-align: center;
	}
	
	.table td {
		vertical-align: middle;
	}
</style>

<script type="text/javascript">
	// 숨김 버튼 클릭 시 모달 열고 데이터 바인딩
	function openHideModal(cafeId, cafeName) {
		document.getElementById('modal-cafe-id').value = cafeId;
		document.getElementById('modal-cafe-name').innerText = cafeName;
		
		let myModal = new bootstrap.Modal(document.getElementById('cafeHideModal'));
		myModal.show();
	}
</script>
</head>
<body>

<%@ include file="/WEB-INF/views/common/adminHeader.jsp" %>

<div class="left-sidebar-wrapper">
	<nav class="ne-side-nav">
		<div class="ne-side-nav-section">관리자 메뉴</div>
		<a href="${pageContext.request.contextPath}/admin/cafelist" id="record" class="active">카페 조회 및 숨김</a>
		<a href="/mypage/party" id="party" class="">테마 · 리뷰 조회 및 숨김</a>
		<a href="/mypage/reservations" id="reservations" class="">전체 회원 목록 조회</a>
	</nav>
	
	<div class="ne-sc">
		
		<div class="ne-sc-title">
			<h3 class="m-0 mb-1 fw-bold" style="padding:15px 0;">카페 조회 및 숨김</h3>
		</div>
		
		<div class="ne-card ne-card-accent p-3 mb-4" style="border-color: #dee2e6;">
			<form action="${pageContext.request.contextPath}/admin/cafelist" method="get" class="search-box d-flex gap-2">
				<div class="input-group">
					<select name="searchType" class="form-select bg-light fw-semibold text-secondary" style="max-width: 120px; font-size: 14px;">
						<option value="name" ${param.searchType == 'name' ? 'selected' : ''}>카페명</option>
						<option value="id" ${param.searchType == 'id' ? 'selected' : ''}>카페 ID</option>
					</select>
					
					<input type="text" name="keyword" class="form-control" value="${param.keyword}" placeholder="검색어를 입력하세요.">
					<button class="btn btn-outline-secondary fw-semibold" type="submit">검색</button>
				</div>
			</form>
		</div>
		
		<div class="ne-card ne-card-accent p-3" style="border-color: #dee2e6;">
			<table class="table table-hover table-bordered m-0 text-center" style="font-size: 14px;">
				<thead>
					<tr>
						<th style="width: 10%;">카페 ID</th>
						<th style="width: 35%;">카페명</th>
						<th style="width: 20%;">사업자등록번호</th>
						<th style="width: 15%;">등록일</th>
						<th style="width: 10%;">현재 상태</th>
						<th style="width: 10%;">노출 상태 제어</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty cafeList}">
							<c:forEach var="cafe" items="${cafeList}">
								<tr>
									<td>${cafe.cafeId}</td>
									<td class="text-start fw-semibold px-3">${cafe.cafeName}</td>
									<td>${cafe.brNo}</td>
									<td>
										<fmt:formatDate value="${cafe.createdAt}" pattern="yyyy-MM-dd" />
									</td>
									<td>
										<c:choose>
											<c:when test="${cafe.status == 'ACTIVE'}">
												<span class="badge bg-success-subtle text-success border border-success-subtle px-2 py-1">ACTIVE</span>
											</c:when>
											<c:otherwise>
												<span class="badge bg-danger-subtle text-danger border border-danger-subtle px-2 py-1">HIDDEN</span>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${cafe.status == 'ACTIVE'}">
												<button type="button" class="btn btn-sm btn-outline-danger fw-semibold px-3" 
														onclick="openHideModal('${cafe.cafeId}', '${cafe.cafeName}')">
													숨김
												</button>
											</c:when>
											<c:otherwise>
												<span class="text-muted small">제어 불가</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" class="text-center py-5 text-secondary">
									조회된 카페 내역이 존재하지 않습니다.
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			
			<c:if test="${not empty pageInfo && pageInfo.totalCount > 0}">
				<nav class="d-flex justify-content-center mt-4">
					<ul class="pagination pagination-sm m-0">
						<li class="page-item ${pageInfo.prev ? '' : 'disabled'}">
							<a class="page-link" href="?page=${pageInfo.startPage - 1}&searchType=${param.searchType}&keyword=${param.keyword}">&laquo;</a>
						</li>
						
						<c:forEach var="p" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
							<li class="page-item ${pageInfo.currentPage == p ? 'active' : ''}">
								<a class="page-link" href="?page=${p}&searchType=${param.searchType}&keyword=${param.keyword}">${p}</a>
							</li>
						</c:forEach>
						
						<li class="page-item ${pageInfo.next ? '' : 'disabled'}">
							<a class="page-link" href="?page=${pageInfo.endPage + 1}&searchType=${param.searchType}&keyword=${param.keyword}">&raquo;</a>
						</li>
					</ul>
				</nav>
			</c:if>
		</div>
		
	</div>
</div>

<div class="modal fade" id="cafeHideModal" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title fw-bold text-danger">카페 숨김(비활성화) 경고</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<form action="${pageContext.request.contextPath}/admin/cafe/hide" method="post">
				<input type="hidden" id="modal-cafe-id" name="cafeId">
				
				<div class="modal-body p-4" style="font-size: 14px; line-height: 1.6;">
					<p class="m-0 mb-3 text-dark">
						선택하신 카페: <strong id="modal-cafe-name" class="text-primary"></strong>
					</p>
					<div class="alert alert-warning m-0 d-flex flex-column gap-1 fw-medium">
						<span> 카페 숨김 처리 시 발생할 수 있는 현상:</span>
						<span class="small text-secondary">- 해당 카페의 노출 및 조회가 즉시 비활성화됩니다.</span>
						<span class="small text-secondary">- 소속된 모든 테마의 <strong>신규 예약이 자동으로 불가 처리</strong>됩니다.</span>
					</div>
					<p class="mt-3 mb-0 fw-bold text-center text-dark">정말 숨김 처리 하시겠습니까?</p>
				</div>
				<div class="modal-footer py-2">
					<button type="button" class="btn btn-sm btn-secondary fw-semibold" data-bs-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-sm btn-danger fw-semibold px-3">숨김 처리</button>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>