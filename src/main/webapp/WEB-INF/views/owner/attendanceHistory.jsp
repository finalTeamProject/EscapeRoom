<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석기록</title>
<link rel="stylesheet"
	href='${pageContext.request.contextPath }/dist/css/attendance.css' />
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<div class="ne-main-content ne-body-offset">
		<div class="ne-container d-flex">
			<aside class="col-md-2">
					<%@ include file="/WEB-INF/views/common/ownerSide.jsp"%>
			</aside>
			<div class="col-md-10 resWrap">
				<div class="title">출석 기록</div>
				<div class="d-flex justify-content-between">
					<div class="resList" style="width:100%">
						<div class="attend-list">

							<div class="row fw-bold border-bottom py-2 m-0">
								<div class="col-1">시간</div>
								<div class="col-2">카페</div>
								<div class="col-2">테마</div>
								<div class="col-2">예약자</div>
								<div class="col-1">인원</div>
								<div class="col-2">출석</div>
								<div class="col-2">노쇼</div>
							</div>

							<c:forEach var="r" items="${historyList}">
								<div class="row align-items-center border-bottom py-2 m-0 text-muted">
									<div class="col-1 fw-bold">
									<fmt:formatDate value="${r.openAt}" pattern="MM-dd"/><br>
									<fmt:formatDate value="${r.openAt}" pattern="HH:mm"/>
								</div>
									<div class="col-2">${r.cafeName}</div>
									<div class="col-2">${r.roomName}</div>
									<div class="col-2">${r.leaderNickname}</div>
									<div class="col-1">${r.totalMember}명</div>
									<div class="col-2"><span class="status-done">${r.doneCount}명</span></div>
									<div class="col-2"><span class="status-noshow">${r.noshowCount}명</span></div>
								</div>
							</c:forEach>
							<c:if test="${empty historyList}">
								<div class="text-center py-3">처리된 출석 기록이 없습니다.</div>
							</c:if>
						</div>

						${dataCount == 0 ? "" : paging}
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
