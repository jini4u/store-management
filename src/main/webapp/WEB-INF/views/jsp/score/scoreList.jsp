<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/score/score.css" />
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>









<script type="text/javascript">
$(function(){ 

	  $(".open").click(function(){
	    $(".modal").fadeIn();
	  });
	  
	  $(".modal_content").click(function(){
	    $(".modal").fadeOut();
	  });
	  
	});
</script>









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
	<th class="score_th">점검년도</th>
	<th class="score_th">분기</th>
	<th class="score_th">항목</th>
	<th class="score_th">상세항목</th>
	<th class="score_th">점수</th>
	<tr>
		<td>2022</td>
		<td>분기</td>
		<td>위생</td>
		<td>매장위생관리</td>
		<td><input type="text" size="5" placeholder="점수 수정">
	</tr>

</table>





<!--  수정 점수등록 버튼 -->
<div id ="btnclick">
<div id="btn_group">
<button class="open" id="btn1">수정</button>
<button class="open" id="btn2">점수등록</button>
</div>
</div>




<!-- 수정 모달창 -->

<div class="modal hidden">
	<div class="modal_overlay"></div>
	<div class="modal_content">
		<h1 class="modalh1">여기는 모달창 입니다.</h1>
<div id="btn_group">
		<button >등록</button>
		<button>취소</button>
		</div>
		</div>
	</div>









<!-- 모달 자바 스크립트 -->
<script>
  const openButton=document.getElementById(".open");
  const modal = document.querySelector(".modal");
  const overlay = modal.querySelector(".modal_overlay");
  const openModal = () => {
	  modal.classList.remove("hidden"); 
  }
  const closeModal = () => {
	  modal.classList.add("hidden");
  }
  
  overlay.addEventListener("click", closeModal);
 
  </script>



