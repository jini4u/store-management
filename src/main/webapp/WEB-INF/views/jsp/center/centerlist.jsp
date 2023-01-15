<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/resources/css/center/centerList.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/blood-bank.png" />
	<h2>센터관리</h2>
</div>
<div class="center-search-button">
	<div class="search-box center">
		<input type="text" name="centerCode" id="findCenterName"
			class="search-txt search-button"> <a class="search-btn"
			id="findCenterList" href="#"> <i class="fas fa-search"></i>
		</a>
	</div>
</div>
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
			<!-- 	왼쪽박스 리스트 -->
			<c:forEach var="centerList" items="${centerList}" varStatus="status">
				<tr class="centerListTr" onclick="CallcenterList()">
					<td>${centerList.centerCode}</td>
					<td>${centerList.centerName}</td>
					<td>${centerList.centerTel}</td>
					<td>${centerList.centerAddress}</td>
					<td>${fn:substring(centerList.centerOpeningDate,0,10)}</td>
					<td>${centerList.centerCondition}</td>

					<td style="display: none">${centerList.centerGuide}</td>
					<td style="display: none">${fn:substring(centerList.centerClosingDate,0,10)}</td>
					<td><button id="centerDetails" class="centerSize"
							data-toggle="modal" data-target="#myModal"
							onclick="CallcenterList()">상세보기</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table> 
	<div class="center-pagging">
		<ul class="pagination pageModal">
			<li><a class="innerPager first" href="centerList?pageNo=1">처음</a></li>
			<li><c:if test="${pager.groupNo>1}">
					<a class="innerPager arrow left"
						href="centerList?pageNo=${pager.startPageNo-1}">이전</a>
				</c:if></li>
			<c:forEach var="i" begin="${pager.startPageNo}"
				end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager active num" href="centerList?pageNo=${i}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a class="innerPager num" href="centerList?pageNo=${i}">${i}</a>
					</c:if></li>
			</c:forEach>
			<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
					<a class="innerPager arrow right"
						href="centerList?pageNo=${pager.endPageNo+1}">다음</a>
				</c:if></li>
			<li><a class="innerPager last"
				href="centerList?pageNo=${pager.totalPageNo}">맨끝</a></li>
		</ul>
	</div>
</div>
<div class="center-button-group"></div>


<button id="centerInsertBtn" class="centerSize" data-toggle="modal"
	data-target="#myModal">등록</button>
<!--insert Modal -->

<div class="modal fade" data-backdrop="static" id="myModal"
	role="dialog">
	<!-- 사용자 지정 부분① : id명 -->
	<div class="modal-dialog modal-lg modal-dialog-centered">
		<!-- Modal content-->
		<div class="modal-content p-6">
			<div class="modal-header">
				<h4 class="modal-title">센터 등록</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- 사용자 지정 부분② : 타이틀 -->
			</div>
			<div class="modal-body" id="centerphoto">
				<!--   disabled="disabled" class="form-control removeDisabled"  <table class="rowTable" id="center-right">  -->

				<form id="centerForm">
					<div class="row">
						<div class="col-md-6 mb-3">
							<input type="hidden" name="${centerCode}" id="centerCode">
							<label for="name">센터명</label> <input type="text"
								name="centerName" id="centerName" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm" readonly="readonly">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">운영여부</label> <input type="text"
								id="centerCondition" name="centerCondition" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
					<div class="mb-3">
						<label for="email">전화번호</label> <input type="text"
							name="centerTel" id="centerTel" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm">
					</div>
					<div class="mb-3">
						<label for="email">주소</label> <input type="text"
							name="centerAddress" id="centerAddress" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm">
					</div>
					<div class="mb-3">
						<label for="email">오시는 길</label> <input type="text"
							name="centerGuide" id="centerGuide" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm">
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">오픈일</label> <input type="date"
								name="centerOpeningDate" id="centerOpeningDate"
								class="form-control centerDate"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">폐점일</label> <input type="date"
								name="centerClosingDate" id="centerClosingDate"
								class="form-control centerDate"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
				</form>
				<div>
					<div class="col-md-6 mb-3">
						<label for="name">사진</label> 
						<div id="centerPhotoList"></div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<!-- 				<button type="button" class="centerSize" data-dismiss="modal" >Close</button> -->
				<input type="button" class="centerSize  btn-lg btn-block"
					id="centerSavedBtn" value="저장">
			</div>
		</div>
	</div>
</div>
</div>




<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/center/centerList.js"></script>
