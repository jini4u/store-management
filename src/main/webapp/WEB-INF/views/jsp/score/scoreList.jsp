<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
   src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
   src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>



<!-- 점검년도 리스트 -->
<div class="titleBox">
   <img src="${pageContext.request.contextPath}/resources/images/checklist.png">
   <h2>점포 점수 조회</h2>
</div>

<div id="score_page">
	<div id="btn_group">
	<c:forEach items="${scoreCenterName}" var="centerName">
		<input type="button" name="centerName" class="pinkButton" id="firstCenter" value="${scoreCenterName.centerName}" />
		
	</c:forEach>
	<button class="pinkButton" id="firstCenter" name="centerName">${scoreCenterName.centerName}</button>
	</div>



	<div class="year_and_quarter">

      <form action="${pageContext.request.contextPath}/score/scorelist" name="score" method="get">
         <input type="hidden" name="centerCode" value="1"> <select name="checkYear">
            <option value="0">점검년도</option>
            <option value="2023">2023</option>
            <option value="2022">2022</option>
            <option value="2021">2021</option>
            <option value="2020">2020</option>
         </select> <select name="checkSeason">
            <option value="0">분기</option>
            <option value="4">4 분기</option>
            <option value="3">3 분기</option>
            <option value="2">2 분기</option>
            <option value="1">1 분기</option>
         </select>
         <button type="submit" class="pinkButton">찾기</button>
      </form>
   </div>


<!-- 점수리스트 테이블 -->

<form action="updateScore" name="updatescore" method="post">
	<table class="scoretable" id ="scoreListTable" border="1">
		<tr>
			<th>점검년도</th>
			<th>분기</th>
			<th>항목</th>
			<th>상세항목</th>
			<th>점수</th>
		</tr>
		<c:if test="${empty scoreList}">
			데이터가 없습니다.
		</c:if>
		<c:if test="${not empty scoreList}">
			<c:forEach items="${scoreList}" var="scoreCode">
				<tr>
					<td class="score_td">${scoreCode.checkYear}</td>
					<td class="score_td">${scoreCode.checkSeason}</td>
					<td class="score_td">${scoreCode.checkGroupContent}</td>
					<td class="score_td">${scoreCode.checkDetailContent}</td>
					<td class="score_td"><input type="text" name="arrayScore"
						class="placeholderstlye" size="5"
						placeholder="${scoreCode.checkScore}"></td>
				</tr>
			</c:forEach>


			<c:forEach items="${scoreList}" var="score">
				<input type="hidden" name="centerCode" value="${score.centerCode}" />
				<input type="hidden" name="checkYear" value="${score.checkYear}" />
				<input type="hidden" name="checkSeason" value="${score.checkSeason}" />
				<input type="hidden" name="arrayCheckGroupCode"
					value="${score.checkGroupCode}">
				<input type="hidden" name="arrayCheckDetailCode"
					value="${score.checkDetailCode}">

			</c:forEach>
		</c:if>
	</table>
	<!-- 페이징 처리  -->
	<div class="center-pagging">
		<ul class="pagination pageModal">
			<li><a class="innerPager first" href="scorelist?pageNo=1">처음</a></li>
			<li><c:if test="${pager.groupNo>1}">
					<a class="innerPager arrow left"
						href="scorelist?pageNo=${pager.startPageNo-1}">이전</a>
				</c:if></li>
			<c:forEach var="i" begin="${pager.startPageNo}"
				end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager active num" href="scorelist?pageNo=${i}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a class="innerPager num" href="scorelist?pageNo=${i}">${i}</a>
					</c:if></li>
			</c:forEach>
			<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
					<a class="innerPager arrow right"
						href="scorelist?pageNo=${pager.endPageNo+1}">다음</a>
				</c:if></li>
			<li><a class="innerPager last"
				href="scorelist?pageNo=${pager.totalPageNo}">맨끝</a></li>
		</ul>
	</div>

	<!--  수정 점수등록 버튼 -->
	<div id="btnclick">
		<div id="btn_group">
			<button type="submit" class="pinkButton">수정</button>
			
</form>
<button id="testBtn" class="pinkButton">점수입력</button>


</div>

<!-- 입력 모달창 -->

<c:if test="${(maxYear eq year and maxSeason eq season) == false}"> 

	<div class="modal fade" id="testModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">점수입력</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">

					<!-- 모달창 안 테이블 -->
					<form method="post" action="insertScore">
						<div>년도: ${year}, 분기: ${season}</div>

						<input type="hidden" name="centerCode" value="${centerCode}" /> 
						<input type="hidden" name="userCode" value="${userCode}" /> 
						<input type="hidden" name="checkYear" value="${year}" /> 
						<input type="hidden" name="checkSeason" value="${season}" />
						<table class="scoretable" border="1">
							<tr>
								<th class="score_th">항목</th>
								<th class="score_th">상세항목</th>
								<th class="score_th">점수</th>
							</tr>
							<c:forEach items="${usingCodeList}" var="usingCodeList">
								<tr>
									<td>${usingCodeList.checkGroupContent}</td>
									<td>${usingCodeList.checkDetailContent}</td>
									<td><input type="hidden" name="arrayCheckGroupCode" value="${usingCodeList.checkGroupCode}"> 
									<input type="hidden" name="arrayCheckDetailCode" value="${usingCodeList.checkDetailCode}"> 
									<input type="text" size="13" name="arrayScore" value="0"></td>
								</tr>
							</c:forEach>
						</table>
				</div>
				<div class="modal-footer">
					
					<button type="submit" class="close-btn pinkButton">입력</button>
					<!-- <a class="pinkButton" id="modalY" href="#">입력</a>-->
					</form>
					<button class="greyButton"  data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
  </c:if>  


<!-- 모달 자바 스크립트 -->

<script src="${pageContext.request.contextPath}/resources/js/score/scoreList.js"></script>
