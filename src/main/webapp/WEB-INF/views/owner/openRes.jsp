<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoExit - 예약등록</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	 $({
		 
	 });

</script>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<style>
	.resOpenWrap{
		background-color: #fff;
		width: 100%;
		height: 500px;
		box-shadow: 2px 2px 10px rgba(0,0,0,0.1);
		border-radius: 5px;
		border: 1px solid #ccc;
		margin: 20px 0 ;
		padding: 20px;
		box-sizing: border-box;

	}
	
	.res-title{
		font-size: 18px;
		font-weight: bold;
		border-bottom: 2px solid #fdb400;
		margin-bottom: 5px;
	}
	
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<main class="ne-main-content ne-body-offset">
		<div class="ne-container">
			<div class="resOpenWrap">
				<div class="res-title">예약오픈 등록</div>
				
				<form  method="post" name="resOpenForm">
					<div class="form-label">테마 선택</div>
					<select name="" id="">
						<option value="">테마1</option>
						<option value="">테마2</option>
						<option value="">테마3</option>
						<option value="">테마4</option>
						<option value="">테마5</option>
						<option value="">테마6</option>
					</select>
					<div class="form-label">날짜 선택</div>
					<input type="date" id="openDate"/>
					<div class="form-label">시간 선택</div>
					<div class="timeWrap">
						<select name="" id="">
							<option value="0">00</option>
							<option value="1">01</option>
							<option value="2">02</option>
							<option value="3">03</option>
							<option value="4">04</option>
							<option value="5">05</option>
							<option value="6">06</option>
							<option value="7">07</option>
							<option value="8">08</option>
							<option value="9">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
						</select>
						시 
						<select name="" id="">
							<option value="0">00</option>
							<option value="5">05</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="20">20</option>
							<option value="25">25</option>
							<option value="30">30</option>
							<option value="35">35</option>
							<option value="40">40</option>
							<option value="45">45</option>
							<option value="50">50</option>
							<option value="55">55</option>
						</select>
						분
					</div>
					<button type="button" class="btn btn-primary">예약시간 등록</button>
					<button type="button" class="btn btn-outline-primary">취소</button>
				</form>
				
			</div>
			
		</div>
	</main>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>