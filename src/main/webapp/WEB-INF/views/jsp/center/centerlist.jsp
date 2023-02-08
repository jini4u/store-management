<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/resources/css/center/centerList.css">

<div class="menuRoute">
		<img
		src="${pageContext.request.contextPath}/resources/images/home.png">
		<a href="/">&nbsp; Home &nbsp; ></a>
		<span>&nbsp; 센터  &nbsp; ></span>
		<a href="">&nbsp; 센터관리</a>	
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/blood-bank.png" />
	<h2>센터관리</h2>
</div>
<div class="search_insert">
	<form action="centerlist" class="search-form">
		<select name="keywordType" class="search-select">
			<option value="CN" <c:if test="${keywordType eq 'CN'}">selected</c:if>>센터명</option>
			<option value="CC" <c:if test="${keywordType eq 'CC'}">selected</c:if>>센터코드</option>
			<option value="CL" <c:if test="${keywordType eq 'CL'}">selected</c:if>>지역</option>
		</select>
		<div class="search-box">
			<input type="text" name="keyword" id="findCenterName"
				class="search-txt" placeholder="search" value="${keyword}">
			<button type="submit" class="search-btn" id="findCenterList">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</form>
	<div>
		<button class="greyButton">엑셀로 다운로드</button>
		<button id="centerInsertBtn" class="pinkButton" data-toggle="modal"
		data-target="#myModal">등록</button>
	</div>
</div>
<c:if test="${centerListN != 'empty'}">
	<div id="center-form-pagging">
		<table class="verticalTable" id="center-left">
			<thead>
				<tr>
					<th>센터 코드</th>
					<th>센터명</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>오픈 일</th>
					<th>운영 여부</th>
					<th>상세보기</th>
				</tr>
			</thead>
			<tbody id="centerList">
				<!--varStatus는 forEach문에서의 인덱스 -->
				<!--    왼쪽박스 리스트 -->
				<c:forEach var="centerList" items="${centerList}" varStatus="status">
					<tr class="centerListTr">
						<td>${centerList.centerCode}</td>
						<td>${centerList.centerName}</td>
						<td>${centerList.centerTel}</td>
						<td>${centerList.centerAddress}</td>
						<td>${fn:substring(centerList.centerOpeningDate,0,10)}</td>
						<td>${centerList.centerCondition}</td>
						<td style="display: none">${centerList.centerGuide}</td>
						<td style="display: none">${fn:substring(centerList.centerClosingDate,0,10)}</td>
						<td><button id="centerDetails" class="updateBtn"
								data-toggle="modal" data-target="#myModal"><img
		src="${pageContext.request.contextPath}/resources/images/edit-tools.png"></button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	<div class="center-pagging">
			<ul class="pagination pageModal">
				<li><a class="innerPager first"
					href="centerlist?pageNo=1&keywordType=${keywordType}&keyword=${keyword}">처음</a></li>
				<li><c:if test="${pager.groupNo>1}">
						<a class="innerPager arrow left"
							href="centerlist?pageNo=${pager.startPageNo-1}&keywordType=${keywordType}&keyword=${keyword}">이전</a>
					</c:if></li>
				<c:forEach var="i" begin="${pager.startPageNo}"
					end="${pager.endPageNo}">
					<li ><c:if test="${pager.pageNo != i}">
							<a class="innerPager num"
								href="centerlist?pageNo=${i}&keywordType=${keywordType}&keyword=${keyword}"><span class="please">${i}</span></a>
						</c:if></li>
					<li><c:if test="${pager.pageNo == i}">
							<a id="now-page" class="innerPager active num"
								href="centerlist?pageNo=${i}&keywordType=${keywordType}&keyword=${keyword}"><span class="please">${i}</span></a>
						</c:if></li>
				</c:forEach>
				<li ><c:if test="${pager.groupNo<pager.totalGroupNo}">
						<a class="innerPager arrow right"
							href="centerlist?pageNo=${pager.endPageNo+1}&keywordType=${keywordType}&keyword=${keyword}">다음</a>
					</c:if></li>
				<li><a class="innerPager last"
					href="centerlist?pageNo=${pager.totalPageNo}&keywordType=${keywordType}&keyword=${keyword}">맨끝</a></li>
			</ul>
		</div>
	</div>
</c:if>

<c:if test="${centerListN == 'empty'}">
	<div id="center-form-pagging">
		<table class="verticalTable" id="center-left">
			<thead>
				<tr>
					<th>센터 코드</th>
					<th>센터명</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>오픈 일</th>
					<th>운영 여부</th>
					<th>상세보기</th>
				</tr>
			</thead>
			<tbody id="centerList">
				<tr>
					<td colspan="7">등록된 센터가 없습니다.</td>
				</tr>
			</tbody>
		</table>
	</div>
</c:if>

<!--insert Modal -->

<div class="modal fade" data-backdrop="static" id="myModal"
	role="dialog">
	<!-- 사용자 지정 부분① : id명 -->
	<div class="modal-dialog modal-xl modal-dialog-centered"
		id="centerInsertModal">
		<!-- Modal content-->
		<div class="modal-content p-6">
			<div class="modal-header">
				<h4 class="modal-title centerTitle">센터 정보 수정</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- 사용자 지정 부분② : 타이틀 -->
			</div>
			<div class="modal-body">
				<!--   disabled="disabled" class="form-control removeDisabled"  <table class="rowTable" id="center-right">  -->

				<form id="centerForm">
					<div class="row">
						<div class="col-md-6 mb-3">
							<input type="hidden" name="${centerCode}" id="centerCode">
							<label for="centerName">센터명</label> 
							<input type="text" onchange="checkCenterName()" name="centerName" id="centerName" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" readonly="readonly">
							<div id="invalid-centerName"></div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">운영여부</label> <input type="text"
								id="centerCondition" name="centerCondition" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
					<div class="mb-3">
						<label for="centerTel">전화번호</label> 
						<input type="text" name="centerTel" onchange="checkCentertel()" id="centerTel" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
						<div id="invalid-tel"></div>
					</div>
					<div class="mb-3">
						<label for="centerAddress">주소</label> <input type="text"
							name="centerAddress" id="centerAddress" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm">
							<div id="invalid-Address"></div>
					</div>
					<div class="mb-3">
						<label for="centerGuide">오시는 길</label> <input type="text"
							name="centerGuide" id="centerGuide" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm">
					</div>
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="centerOpeningDate">오픈일</label> <input type="date"
								name="centerOpeningDate" id="centerOpeningDate"
								class="form-control centerDate"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="centerClosingDate">폐점일</label> <input type="date"
								name="centerClosingDate" id="centerClosingDate"
								class="form-control centerDate"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
				</form>
				<div id="showPhoto">
					<div class="col-md-6 mb-3">
						<label for="name" class="center-label">사진</label>
						<div id="centerPhotoList" class="slider">
							<div class="bullets"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<!--             <button type="button" class="centerSize" data-dismiss="modal" >Close</button> -->
				<input type="button" class="pinkButton  btn-lg btn-block"
					id="centerSavedBtn"  value="저장">
			</div>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/center/centerList.js"></script>