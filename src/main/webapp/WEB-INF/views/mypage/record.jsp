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
<!-- FULL CALENDAR CDN -->
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js"></script>
<script type="text/javascript">
	
	// DOM이 Loaded 되었을 때 실행
	document.addEventListener('DOMContentLoaded', function(){
		
		// 달력 div 영역
		let calendarEl = document.getElementById('calendar');
		
		// 달력 생성자 속성 구성
		let calendar = new FullCalendar.Calendar(calendarEl, {
			
			// 월별 달력
			initialView: 'dayGridMonth', 
	        
	        // 상단 툴바 배치
	        headerToolbar: {
	        	center: 'title',
	            left: 'prev',
	            right: 'next'
	        },
	        
	        // 날짜 칸의 높이를 부모인 ne-sc 크기에 맞춰 적절히 자동 조절
	        height: 'auto', 
	        
	        events: [
	            {
	                title: ' 홍대 NoExit 예약',
	            },
	            {
	                title: ' 강남 방탈출 예약',
	            }
	        ]
	    });
	    
	    // 3
	    calendar.render();
	});
	</script>
<script type="text/javascript">

</script>

</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 헤더 높이만큼 본문 밀기( padding-top ) -->
<div class="main-body ne-body-offset">
	
	<!-- 왼쪽 사이드 바 영역 -->
	<%@ include file="/WEB-INF/views/common/myside.jsp" %>

	<!-- 메인 구성 영역 (개인기록, 매칭기록, 예약내역이 이 구역 안에서만 바뀜) -->
	<div class="main-content">
	
		<!-- 섹션 카드 -->
		<div class="ne-sc">
			<div class="ne-sc-title" style="font-size: 24px;">개인 기록</div>
			
			<div class="ne-card ne-card-accent p-4 mb-3">
				<div class="record-item-body">
					<div class="ne-room-img" style="width: 120px; height: 120px; flex-shrink: 0; font-size: 1.2rem;">
					</div>
					<div>
						<h4 class="m-0 mb-2" style="font-size: 20px;">테마 제목</h4>
						<p class="m-0 text-secondary small">플레이 일시: 2026-06-01 14:00</p>
					</div>
				</div>
			</div>
			
		</div><!-- 섹션 -->
		
	</div><!-- 메인 구성 영역 -->



	<!-- 우측 사이드 바 구성 -->
	<div class="right-sidebar">
			
			
		<!-- 달력 바인딩 영역 -->
		<div class="ne-sc m-0">
		    <div class="ne-sc-title">예약 캘린더</div>
		    
		    <div id="calendar" style="font-size: 14px;"></div>
		</div>
			
		
		<!-- 매너온도 섹션 -->
		<div class="ne-sc m-0">
		
			<!-- 매너온도 title -->
			<div class="ne-sc-title">매너 온도</div>
			
			<!-- 매너온도 뱃지 영역 -->
			<div class="d-flex align-items-center gap-2 mt-2">
				
				<!-- 매너온도 표기 영역 -->
				<span class="ne-mannero" style="font-size: 18px; padding: 0.4em 0.8em;">
					
					<!-- 부트스트랩 아이콘 영역 -->					
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-fire" viewBox="0 0 16 16">
  						<path d="M8 16c3.314 0 6-2 6-5.5 0-1.5-.5-4-2.5-6 .25 1.5-1.25 2-1.25 2C11 4 9 .5 6 0c.357 2 .5 4-2 6-1.25 1-2 2.729-2 4.5C2 14 4.686 16 8 16m0-1c-1.657 0-3-1-3-2.75 0-.75.25-2 1.25-3C6.125 10 7 10.5 7 10.5c-.375-1.25.5-3.25 2-3.5-.179 1-.25 2 1 3 .625.5 1 1.364 1 2.25C11 14 9.657 15 8 15"/>
					</svg> 매너온도 바인딩°C
					
				</span>
				
			</div>
			
			<p class="ne-hint mt-3">매너온도는 매칭 시 참고 지표로 활용됩니다.<br>매너있는 플레이로 매너 온도를 높여보세요!</p>
		</div>
		
		<div class="ne-sc m-0">
			<div class="ne-sc-title">현재 미진행 상호평가</div>
			<div class="d-flex flex-column gap-3 mt-2">
				<div class="d-flex justify-content-between align-items-center">
					<div>
						<span class="fw-bold d-block" style="font-size: 14px;">파티원 닉네임 바인딩</span>
						<span class="text-muted text-xs">매장이름 바인딩</span>
						<div>
							<span class="text-muted text-xxs">테마 바인딩</span>
						</div>
						
					</div>
					<button class="btn btn-sm btn-outline-primary" style="font-size: 12px; padding: 4px 10px;">평가하기</button>
				</div>
			</div>
		</div>
		
	</div>

</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>