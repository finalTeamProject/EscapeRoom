<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoExit - 예약현황</title>
<link rel="stylesheet"
	href='${pageContext.request.contextPath }/dist/css/resList.css' />
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		 // 카페 셀렉트 변경 시 테마 목록 갱신
	    $("#schCafe").on("change", function(){
	        loadTheme($(this).val());
	    });
		
	    // 페이지 진입 시 첫 번째 카페 자동 선택 + 테마/목록 로드
	    const firstCafe = $("#schCafe option:first").val();
	    if(firstCafe){
	        $("#schCafe").val(firstCafe);
	        loadTheme(firstCafe);
	    }
		
	});

	
	// 테마 목록 AJAX
	function loadTheme(cafeId){

	    $("#schTheme").empty().append('<option value="">전체 테마</option>');

	    if(!cafeId) return;

	    $.ajax({
	          url: '/owner/openRes/theme'
	        , type: 'GET'
	        , data: {cafeId: cafeId}
	        , success: function(res){
	            res.forEach(function(room){
	                $("#schTheme").append(
	                    '<option value="' + room.roomId + '">' + room.roomName + '</option>'
	                );
	            });
	        }
	    });
	}

	// 예약 현황 목록 AJAX
	/* function loadList(schDate, cafeId, roomId){
		
		$.ajax({
			  url: '/owner/resList/list'
			, type: 'GET'
			, data: {schDate: schDate, cafeId: cafeId, roomId:roomId}
			, success: function(res){
				const tbody = $('tbody');
				tbody.empty();
				
				res.forEach(function(item){
					tbody.append(
						'<tr>' +
		                '<td>' + item.cafeName + '</td>' +
		                '<td>' + item.roomName + '</td>' +
		                '<td>' + item.openAt + '</td>' +
		                '<td><button type="button" class="btn btn-outline-primary" >예약 상세</button></td>' +
		                '<td><button type="button" class="btn ne-btn-deact" onclick="deleteOk('+item.reservationId+')">예약 취소</button></td>' +
		                '</tr>'	
					);
				});
			}
		}); */
function loadList(offset){

    const schDate = $("input[type=date]").val();
    const cafeId  = $("#schCafe").val();
    const roomId  = $("#schTheme").val();

    if(!schDate || !cafeId) return;

    $.ajax({
          url: '/owner/resList/list'
        , type: 'GET'
        , data: {schDate: schDate, cafeId: cafeId, roomId: roomId, offset: offset}
        , success: function(res){

            const tbody = $('tbody');

            if(offset === 0) tbody.empty();  // 초기화 or 필터 변경

            if(res.length === 0 && offset === 0){
                tbody.append('<tr><td colspan="5" class="text-center">예약 내역이 없습니다.</td></tr>');
                $('#moreBtn').hide();
                return;
            }

            res.forEach(function(item){
                tbody.append(
                    '<tr>' +
                    '<td>' + item.cafeName + '</td>' +
                    '<td>' + item.roomName + '</td>' +
                    '<td>' + item.openAt + '</td>' +
                    '<td><button type="button" class="btn btn-outline-primary">예약 상세</button></td>' +
                    '<td><button type="button" class="btn ne-btn-deact" ' +
                        'onclick="deleteOk(' + item.reservationId + ')">예약 취소</button></td>' +
                    '</tr>'
                );
            });

            // 10개 미만이면 더보기 숨김
            if(res.length < 10){
                $('#moreBtn').hide();
            } else {
                $('#moreBtn').show();
            }
        }
    });
}

		
		
	}

</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<main class="ne-main-content ne-body-offset">
		<div class="ne-container d-flex">
			<aside class="col-md-2">
					<%@ include file="/WEB-INF/views/common/ownerSide.jsp"%>
			</aside>
			<div class="col-md-10 resWrap">
				<div class="title">예약 현황</div>
				<div class="d-flex justify-content-between">
					<div class="resList">
						<div class="inputBox d-flex">
							<input type="date" class="ne-box" value="${schDate }" />
							<select name="schCafe" id="schCafe" class="ne-box">
								<c:forEach var="list" items="${cafeList }">
									<option value="${list.cafeId }"  <c:if test="${list.cafeId == schCafe}">selected</c:if>>${list.cafeName }</option>
								</c:forEach>
							</select>
							<select name="schTheme" id="schTheme" class="ne-box">
								
							</select>
						</div>
						<table class="ne-table">
							<thead>
								<tr>
									<th>번호</th>
									<th>카페</th>
									<th>테마</th>
									<th>예약일</th>
									<th colspan="2"></th>
								</tr>
							</thead>
							<tbody>
								 <c:choose>
							        <c:when test="${empty resList}">
							            <tr>
							                <td colspan="5" class="text-center">예약 내역이 없습니다.</td>
							            </tr>
							        </c:when>
							        <c:otherwise>
							            <c:forEach var="res" items="${resList}" varStatus="vs">
							                <tr>
							                    <td>${vs.count}</td>
							                    <td>${res.cafeName}</td>
							                    <td>${res.roomName}</td>
							                    <td>${res.openAt}</td>
							                    <td>
							                        <button type="button" class="btn btn-outline-primary">예약 상세</button>
							                        <button type="button" class="btn ne-btn-deact"
							                                onclick="deleteOk(${res.reservationId})">예약 취소</button>
							                    </td>
							                </tr>
							            </c:forEach>
							        </c:otherwise>
							    </c:choose>
							
								<!-- <tr>
									<td>1</td>
									<td>지구별</td>
									<td>어둠의 저택</td>
									<td style="font-size: 16px;">2026-06-01 14:00</td>
									<td>
										<button type="button" class="btn btn-outline-primary">예약 상세</button>
										 <button type="button" class="btn ne-btn-deact" onclick="deleteOk()">예약취소</button>
									</td>
								</tr> -->
							</tbody>
						</table>
						<div id="noRes" class="text-center" style="display: none;">등록된 예약이 없습니다.</div>
						<button type="button" id="moreBtn" class="btn ne-btn" >더보기</button>
						
					</div>
				</div>
			</div>
		</div>

	</main>
	

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>