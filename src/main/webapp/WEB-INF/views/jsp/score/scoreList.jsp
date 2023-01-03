<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="resources/css/score/score.css" />
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<!-- 점검년도 리스트 -->
<div class="titleBox">
	<img src="resources/images/checklist.png">
	<h2>점포 점수 조회</h2>
</div>


<div id="score_page">
	<div id="btn_group">
		<button>지점1</button>
		<button>지점2</button>
	</div>

	<div class="year_and_quarter">
		<form>
			<select name="year">
				<option value="none">점검년도</option>
				<option value="2022">2022</option>
				<option value="2021">2021</option>
				<option value="2020">2020</option>
				<option value="2019">2019</option>
			</select>
		</form>

		<form>
			<select name="quarter_of_ a_year">
				<option value="none">분기</option>
				<option value="4">4 분기</option>
				<option value="3">3 분기</option>
				<option value="2">2 분기</option>
				<option value="1">1 분기</option>
			</select>
		</form>
	</div>
</div>









<!-- 점수리스트 테이블 -->
<table class="scoretable" border="1">
	<tr>
		<th>점검년도</th>
		<th>분기</th>
		<th>항목</th>
		<th>상세항목</th>
		<th>점수</th>
	</tr>

	<c:forEach items="${scoreList}" var="scoreCode">
		<tr>
			<td class="score_td">${scoreCode.checkYear}</td>
			<td class="score_td">${scoreCode.checkSeason}</td>
			<td class="score_td">${scoreCode.checkGroupCode}</td>
			<td class="score_td">${scoreCode.checkDetailCode}</td>
			<td class="score_td"><input type="text" class="placeholderstlye"
				size="5" placeholder="${scoreCode.checkScore}"></td>


		</tr>
	</c:forEach>
</table>





<!--  수정 점수등록 버튼 -->
<div id="btnclick">
	<div id="btn_group">
		<form action="/saveScore" name="saveScore" method="post">
			<button id="btn1">수정</button>
		</form>
		<button class="open">점수등록</button>
	</div>
</div>




<!-- 입력 모달창 -->
<div class="modal">
	<div class="modal_overlay"></div>
	<div class="modal_content">
		
			<h1 class="modalh1">점수 입력</h1>

			<!-- 모달창 안 테이블 -->
			<table class="scoretable" border="1">
				<tr>
					<th class="score_th">점검년도</th>
					<th class="score_th">분기</th>
					<th class="score_th">항목</th>
					<th class="score_th">상세항목</th>
					<th class="score_th">점수</th>
				</tr>
				<tr>
					<td>2023</td>
					<td>1</td>
					<td>HI</td>
					<td>센터 내부 위생관리</td>
					<td><input type="text" size="13" placeholder="점수를 입력하세요"></td>
				</tr>
			</table>


			<button type="submit" class="close-btn">입력</button>
			<button>취소</button>

		</div>
	</div>


<!-- 모달 자바 스크립트 -->

<script type="text/javascript">
$(function() {

	$(".open").click(function() {
		$(".modal").fadeIn();
	});
	
	window.onclick = function(e){
		if(e.target == modal){
			modal.style.visibility = "hidden";
			modal.style.opacity = 0;
		}
	}

	const openButton = document.querySelector(".open");
	const modal = document.querySelector(".modal");
	const overlay = modal.querySelector(".modal_overlay");
	const openModal = function() {
		console.log("hi");
	}
	const closeModal = function() {
		modal.classList.add();
	}
	
	openButton.addEventListener("click", openModal);
	//모달창 닫기//
    const closeBtn = modal.querySelector(".close-btn");
    $(closeBtn).click(function(){
    	$(".modal").fadeOut();

   })
});


</script>