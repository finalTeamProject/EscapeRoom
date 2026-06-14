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
<style type="text/css">

.detail-modal-overlay {
	display: none;
	position: fixed;
	top: 0; left: 0;
	width: 100%; height: 100%;
	background: rgba(0, 0, 0, .45);
	z-index: 1050;
}
.detail-modal {
	position: fixed;
	top: 25%; left: 50%;
	transform: translate(-50%, -50%);
	width: 420px;
	max-height: 70vh;
	overflow-y: auto;
	background: #fff;
	border-radius: 8px;
	box-shadow: 0 10px 30px rgba(0,0,0,.2);
	padding: 1.25rem 1.5rem;
	 z-index: 1051; 
}
.detail-modal .modal-title {
	font-weight: 700;
	font-size: 1.05rem;
	margin-bottom: .35rem;
}
.detail-modal .modal-sub {
	font-size: .82rem;
	color: #888;
	margin-bottom: .9rem;
}
.detail-modal .crew-row {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: .5rem 0;
	border-bottom: 1px solid #eee;
	font-size: .9rem;
}
.detail-modal .crew-row:last-child { border-bottom: none; }
.detail-modal .modal-close-wrap {
	text-align: right;
	margin-top: 1rem;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	// 모달 열기
	function openDetail(reservationId, title, sub)
	{
		$.ajax({
			"type":"GET"
			, "url":"${pageContext.request.contextPath}/owner/attendance/history/detail"
			, "data": { "reservationId": reservationId }
			, "dataType":"json"
			, "success":function(data)
			{
				if(data == null || data.length == 0)
				{
					alert("상세 기록이 없습니다.");
					return;
				}

				$("#detailTitle").text(title);
				$("#detailSub").text(sub);

				let html = "";
				data.forEach(function(crew)
				{
					let status = crew.statusName;
					let cls = (status == "노쇼") ? "status-noshow" : "status-done";

					html += "<div class='crew-row'>"
						 + "<span>" + crew.nickname + "</span>"
						 + "<span class='" + cls + "'>" + status + "</span>"
						 + "</div>";
				});

				$("#detailBody").html(html);
				$("#detailOverlay, #detailModal").show();
			}
			, "error":function(e)
			{
				alert("상세 조회 중 에러 발생");
				console.log(e.responseText);
			}
		});
	}

	// 모달 닫기
	function closeDetail()
	{
		$("#detailOverlay, #detailModal").hide();
	}

	$(function(){
		
		$("#detailOverlay").click(closeDetail);
	});

</script>
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
									<div class="col-2 d-flex justify-content-between align-items-center">
										<span class="status-noshow">${r.noshowCount}명</span>
										<button type="button" class="btn btn-sm btn-outline-primary"
										        onclick="openDetail(${r.reservationId}, '${r.roomName}', '<fmt:formatDate value="${r.openAt}" pattern="MM-dd HH:mm"/>  ${r.cafeName}')">상세</button>
									</div>								
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
	<!-- 출석 상세 모달 -->
	<div id="detailOverlay" class="detail-modal-overlay"></div>
	<div id="detailModal" class="detail-modal" style="display:none;">
		<div class="modal-title" id="detailTitle"></div>
		<div class="modal-sub" id="detailSub"></div>
		<div id="detailBody"></div>
		<div class="modal-close-wrap">
			<button type="button" class="btn btn-sm btn-secondary" onclick="closeDetail()">닫기</button>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
