<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>


<!-- 점검년도 리스트 -->

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/checklist.png">
	<h2>점포 점수 조회</h2>
</div>

<c:forEach items="${centerName}" var="center">
	<a href="${pageContext.request.contextPath}/score/scorelist?centerCode=${center.centerCode}" class="pinkButton firstCenter">${center.centerName}</a>
</c:forEach>



<div class="year_and_quarter">

<!-- 년도,분기 찾기 -->

	<form action="${pageContext.request.contextPath}/score/scorelist" name="score" method="get">
	 <input type="hidden" name="centerCode" value="${param.centerCode}">
		<select name="checkYear" id="yearbox" title="년도">년도</select> 
		<select name="checkSeason">
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
<form action="${pageContext.request.contextPath}/score/updatescore" method="post">
<input type="hidden" name="centerCode" value="${param.centerCode}">
	<table class="scoretable" id="scoreListTable" border="1">
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
					<td class="score_td">
					<input type="text" name="checkScore" class="placeholderstlye" size="5" placeholder="${scoreCode.checkScore}"></td>
					
				</tr>

			</c:forEach>

			<!-- 수정 리스트 -->
			<c:forEach items="${scoreList}" var="score">
				<input type="hidden" name="centerCode" value="${score.centerCode}" />
				<input type="hidden" name="checkYear" value="${score.checkYear}" />
				<input type="hidden" name="checkSeason" value="${score.checkSeason}" />
				<input type="hidden" name="arrayCheckGroupCode" value="${score.checkGroupCode}"/>
				<input type="hidden" name="arrayCheckDetailCode" value="${score.checkDetailCode}"/>
			</c:forEach>
		</c:if>
	</table>

	<!-- 페이징 처리  -->
	<div class="center-pagging">
		<ul class="pagination pageModal">
			<li><a class="innerPager first" href="scorelist?pageNo=1&centerCode=${param.centerCode}">처음</a></li>

			<c:forEach var="i" begin="${pager.startPageNo}"	end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager active num" href="scorelist?pageNo=${i}&centerCode=${param.centerCode}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a class="innerPager num" href="scorelist?pageNo=${i}&centerCode=${param.centerCode}">${i}</a>
					</c:if></li>
			</c:forEach>

			<li><a class="innerPager last"
				href="scorelist?pageNo=${pager.totalPageNo}&centerCode=${param.centerCode}">맨끝</a></li>
		</ul>
	</div> 

	<!--  수정 점수등록 버튼 -->
	<div id="btnclick">
		<div id="btn_group">
			<button type="submit" class="pinkButton">수정</button>
</form>
<button type="button" id="testBtnn" class="pinkButton" data-toggle="modal" data-target="#myModal">점수입력</button>


</div>
</div>
</div>

<!--입력 모달 영역 -->
<c:if test="${(maxYear eq year and maxSeason eq season) == false}"> 
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
			<h5 class="modal-title" id="exampleModalLabel">점수입력</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<form action="${pageContext.request.contextPath}/score/insertscore" method="post" >
			<div class="modal-body">

				<!-- 모달창 안 테이블 -->
					<div>년도: ${year}, 분기: ${season}</div>
					<input type="hidden" name="centerCode" value="${param.centerCode}" /> 
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
								<td>
								<input type="hidden" name="arrayGroupCode" value="${usingCodeList.checkGroupCode}"> 
								<input type="hidden" name="arrayDetailCode" value="${usingCodeList.checkDetailCode}"> 
								<input type="text" size="13" name="arrayScore" value="0"></td>
							</tr>
						</c:forEach>
						</table>
			</div>
			<div class="modal-footer">
				<button type="submit" class=" pinkButton">확인</button>
				
				<button class="greyButton" data-dismiss="modal">취소</button>
			</div>
			</form>
		</div>
	</div>
</div>
 </c:if> 
                      
<!-- 모달 자바 스크립트 -->
<script src="${pageContext.request.contextPath}/resources/js/score/scoreList.js"></script>