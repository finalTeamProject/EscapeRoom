<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

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
	        
	        // 캘린더 내부 커스터마이징 훅 
	        eventContent: func(args){
	        	
	        	let imgUrl = arg.event.exetendedProps.imageUrl;
	        	let theme = arg.event.title;
	        	
	        	
	        }
	        
	        
	        
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

</head>
<body>

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
					
					<!-- 부트스트랩 아이콘 영역	 -->				
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




</body>
</html>