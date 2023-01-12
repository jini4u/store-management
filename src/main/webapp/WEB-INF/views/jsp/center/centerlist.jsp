<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="resources/css/center/centerList.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<div class="titleBox">
	<img src="resources/images/blood-bank.png">
	<h2>센터관리</h2>
</div>
<div class="center-search-button">
	<div class="search-box center">
		<input type="text" name="centerCode" id="findCenterName" class="search-txt search-button"> 
			<a class="search-btn" id="findCenterList" href="#"> 
				<i class="fas fa-search"></i>
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
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="center-pagging">
		<ul class="pagination modal"> 
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
	<div class="center-button-group">
		<input type="button" class="pinkButton" id="centerInsertBtn" value="등록"> 
		<input type="button" class="greyButton" id="centerSavedBtn" value="저장">
	</div>
<form id="centerForm">
	<table class="rowTable" id="center-right">
			<input type="hidden" name="centerCode" value="${newCenterCode}" id="centerCode" class="removeDisabled"> 
		<tr>
			<th>센터명</th>
			<td><input type="text" name="centerName" id="centerName"
				class="removeDisabled" disabled="disabled" readonly="readonly"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="centerTel" id="centerTel"
				class="removeDisabled" disabled="disabled"></td>
		</tr>
		<tr>
			<th>운영여부</th>
			<td><input type="text" id="centerCondition"
				class="removeDisabled" disabled="disabled" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="centerAddress" id="centerAddress"
				class="removeDisabled" disabled="disabled"></td>
		</tr>
		<tr>
			<th>오시는 길</th>
			<td><input type="text" name="centerGuide" id="centerGuide"
				class="removeDisabled" disabled="disabled"></td>
		</tr>
		<tr>
			<th>오픈 일</th>
			<td><input type="date" name="centerOpeningDate"
				id="centerOpeningDate" class="removeDisabled" disabled="disabled"></td>
		</tr>
		<tr>
			<th>폐점 일</th>
			<td><input type="date" name="centerClosingDate"
				id="centerClosingDate" class="removeDisabled" disabled="disabled"
				readonly="readonly"></td>
		</tr>
	</table>
</form>
<div id="center-photo-frame">
	<div id="centerphoto-main-size">
		<img src="resources/images/center/left-arrow.png" class="center-arrow">
		<div id="centerImagesDiv">
			<!-- 센터별 사진들 들어가야함. src에서 /image/사진이름. /image는 resources.xml에서 맵핑해둠 -->
			<img src="" class="centerphoto-img">
		</div>
		<img src="resources/images/center/right-arrow.png"
			class="center-arrow">
	</div>
	<div id="centerphoto-mini-size">
		<input type="radio" name="slid"> <input type="radio"
			name="slid"> <input type="radio" name="slid">
	</div>
</div>

<script src="resources/js/center/centerList.js"></script>
