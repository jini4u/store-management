<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<!-- 점검년도 리스트 -->
<div class="menuRoute">
	<img src="${pageContext.request.contextPath}/resources/images/home.png">
	<a href="/">&nbsp; Home &nbsp; ></a> <span>&nbsp; 점수 &nbsp; ></span> <a
		href="${pageContext.request.contextPath}/score/scorelist?centerCode=${center.centerCode}">&nbsp;
		센터 점수 조회</a>
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/checklist.png">
	<h2>센터 점수 조회</h2>
</div>
<c:forEach items="${centerName}" var="center" > 


	<c:if test="${center.centerCode eq param.centerCode}">
		<a class="clikedBtn clicked" href="${pageContext.request.contextPath}/score/scorelist?centerCode=${center.centerCode}">${center.centerName}</a>
	</c:if>
	<c:if test="${center.centerCode ne param.centerCode}">
		<a class="clikedBtn" href="${pageContext.request.contextPath}/score/scorelist?centerCode=${center.centerCode}">${center.centerName}</a>
	</c:if>

</c:forEach>



<div class="year_and_quarter">

	<!-- 년도,분기 찾기 -->

	<form action="${pageContext.request.contextPath}/score/scorelist"
		name="score" method="get">
		<input type="hidden" name="centerCode" value="${param.centerCode}">
		<select name="checkYear" id="yearbox" title="년도">년도
		</select> <select name="checkSeason">
			<option value="0">분기</option>
			<c:if test='${param.checkSeason eq 4}'>
				<option selected='selected' value="4">
			</c:if>
			<c:if test='${param.checkSeason ne 4}'>
				<option value="4">
			</c:if> 4 분기
			</option>

			<c:if test='${param.checkSeason eq 3}'>
				<option selected='selected' value="3">
			</c:if>
			<c:if test='${param.checkSeason ne 3}'>
				<option value="3">
			</c:if>3 분기
			</option>

			<c:if test='${param.checkSeason eq 2}'>
				<option selected='selected' value="2">
			</c:if>
			<c:if test='${param.checkSeason ne 2}'>
				<option value="2">
			</c:if>2 분기
			</option>


			<c:if test='${param.checkSeason eq 1}'>
				<option selected='selected' value="1">
			</c:if>
			<c:if test='${param.checkSeason ne 1}'>
				<option value="1">
			</c:if> 1 분기
			</option>
		</select>
		<button type="submit" class="pinkButton">찾기</button>
	</form>
</div>



<!-- 점수리스트 테이블 -->
<form action="${pageContext.request.contextPath}/score/updatescore"
	method="post" name="InsertModal">
	<input type="hidden" name="centerCode" value="${param.centerCode}">


	<c:if test="${empty scoreList}">
		<table class="scoretable" id="scoreListTable" border="1">
			<tr>
				<th>점검년도</th>
				<th>분기</th>
				<th>항목</th>
				<th>상세항목</th>
				<th>점수</th>
			</tr>
			<tr>
				<td colspan="5">데이터가 없습니다.</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${not empty scoreList}">
		<table class="scoretable" id="scoreListTable" border="1">
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
					<td class="score_td">${scoreCode.checkGroupContent}</td>
					<td class="score_td">${scoreCode.checkDetailContent}</td>
					<td class="score_td">
					<input id="inputNumber " type="number"
						name="arrayScore" class="placeholderstlye" size="5"
						value="${scoreCode.checkScore}" value="placeholder" min="0" max="100"></td>


				</tr>

			</c:forEach>

			<!-- 수정 리스트 -->
			<c:forEach items="${scoreList}" var="score">
				<input type="hidden" name="centerCode" value="${score.centerCode}" />
				<input type="hidden" name="checkYear" value="${score.checkYear}" />
				<input type="hidden" name="checkSeason" value="${score.checkSeason}" />
				<input type="hidden" name="arrayGroupCode"
					value="${score.checkGroupCode}" />
				<input type="hidden" name="arrayDetailCode"
					value="${score.checkDetailCode}" />
			</c:forEach>
			<!-- 페이징 처리  -->
		</table>
		<div class="center-pagging">
			<ul class="pagination pageModal">
				<li><a class="innerPager first"
					href="scorelist?pageNo=1&centerCode=${param.centerCode}&checkYear=${param.checkYear}&checkSeason=${param.checkSeason}">처음</a></li>

				<c:forEach var="i" begin="${pager.startPageNo}"
					end="${pager.endPageNo}">
					<li><c:if test="${pager.pageNo != i}">
							<a class="innerPager num"
								href="scorelist?pageNo=${i}&centerCode=${param.centerCode}&checkYear=${param.checkYear}&checkSeason=${param.checkSeason}">${i}</a>
						</c:if></li>
					<li><c:if test="${pager.pageNo == i}">
							<a class="innerPager num active"
								href="scorelist?pageNo=${i}&centerCode=${param.centerCode}&checkYear=${param.checkYear}&checkSeason=${param.checkSeason}">${i}</a>
						</c:if></li>
				</c:forEach>

				<li><a class="innerPager last"
					href="scorelist?pageNo=${pager.totalPageNo}&centerCode=${param.centerCode}&checkYear=${param.checkYear}&checkSeason=${param.checkSeason}">맨끝</a></li>
			</ul>
		</div>
	</c:if>

	<!--  수정 점수등록 버튼 -->
	<div id="btnclick">
		<button id="excel-download-button" class="greyButton">파일 다운</button>
		<a id="excel" href="" download=""></a>
		<div id="btn_group">
<!-- 			<button id="no-box" class="greyButton">수정</button> -->
			<button type="submit" class="pinkButton" id="score-update-button">수정</button>
			
</form>
<c:if test="${(maxYear eq year and maxSeason eq season) == false}">
<button type="button" id="testBtnn modalOpen" class="pinkButton"
	data-toggle="modal" data-target="#myModal" >점수입력</button>
		</div>
	</div>

<!--입력 모달 영역 -->


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">점수입력</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" >
						<span aria-hidden="true">×</span>
					</button>
					
				</div>
				<form action="${pageContext.request.contextPath}/score/insertscore"
					method="post"  >
					<div class="modal-body">
						<!-- 모달창 안 테이블 -->
						<div>년도: ${year}, 분기: ${season}</div>
						<input type="hidden" name="centerCode" value="${param.centerCode}" />
						<input type="hidden" name="userCode" value="${userCode}" /> <input
							type="hidden" name="checkYear" value="${year}" /> <input
							type="hidden" name="checkSeason" value="${season}" />
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
									<td><input type="hidden" name="arrayGroupCode"
										value="${usingCodeList.checkGroupCode}"> <input
										type="hidden" name="arrayDetailCode"
										value="${usingCodeList.checkDetailCode}"> <input
										id="inputNumber" placeholder="점수를 입력하세요" size="13" name="arrayScore"
										  min="0" max="100">
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
<script
	src="${pageContext.request.contextPath}/resources/js/score/scoreList.js"></script>