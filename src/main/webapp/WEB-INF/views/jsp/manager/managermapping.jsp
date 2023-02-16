<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/manager/managermapping.css">
<!--  Font Awesome 라이브러리 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<div class="menuRoute">
	<img src="${pageContext.request.contextPath}/resources/images/home.png">
	<a href="/">&nbsp; Home &nbsp; ></a> <span>&nbsp; 담당자 &nbsp; ></span> <a
		href="${pageContext.request.contextPath}/manager/managermapping">&nbsp;
		담당자 맵핑</a>
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/manager.png">
	<h2>담당자 맵핑</h2>
</div>

<!-- 검색 -->
<div class="search_btn_collection">
	<div class="search-box">
		<input type="text" id="search-txt" class="search-txt" name=""
			placeholder="담당자 검색">
		<button type="submit" value="search" id="search-btn"
			class="search-btn">
			<i class="fa fa-search"></i>
		</button>
	</div>
	<div>
		<button type="button" class="mappingButton pinkButton"
			data-toggle="modal" data-target=".mappingModal">맵핑</button>
		<button type="button" class="releaseButton greyButton">해제</button>
	</div>
</div>

<div class="graphbox">
	<div class="managerbox">
		<div id="managerTitle">
			<p class="subheading">담당자</p>
			<p class="mgrCount">총&nbsp;${totalManagers}명</p>
		</div>
		<div class="userTitleLine"></div>
		<table class="verticalTable" id="managertable">
			<thead>
				<tr>
					<th>담당자 코드</th>
					<th>담당자명</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${managerList}" var="manager">
					<tr>
						<td>${manager.userCode}</td>
						<td>${manager.userName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="center-pagging">
			<ul class="pagination">
				<li><a class="innerPager first"
					href="managermapping?pageNo=1&keyword=${keyword}">처음</a></li>
				<li><c:if test="${pager.groupNo>1}">
						<a class="innerPager arrow left"
							href="managermapping?pageNo=${pager.startPageNo-1}&keyword=${keyword}">이전</a>
					</c:if></li>
				<c:forEach var="i" begin="${pager.startPageNo}"
					end="${pager.endPageNo}">
					<li><c:if test="${pager.pageNo != i}">
							<a class="innerPager num"
								href="managermapping?pageNo=${i}&keyword=${keyword}">${i}</a>
						</c:if></li>
					<li><c:if test="${pager.pageNo == i}">

							<a id="now-page" class="innerPager num active"
								href="managermapping?pageNo=${i}&keyword=${keyword}">${i}</a>
						</c:if></li>
				</c:forEach>
				<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
						<a class="innerPager arrow right"
							href="managermapping?pageNo=${pager.endPageNo+1}&keyword=${keyword}">다음</a>
					</c:if></li>
				<li><a class="innerPager last"
					href="managermapping?pageNo=${pager.totalPageNo}&keyword=${keyword}">맨끝</a></li>
			</ul>
		</div>
	</div>
	<div class="centerbox">
		<p class="subheading">센터
		<div class="centerTitleLine"></div>
		<div id="center-table-div">
			<table class="verticalTable" id="centertable">
				<thead>
					<tr>
						<th></th>
						<th>센터코드</th>
						<th>센터명</th>
						<th id="address">주소</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>

<div class="modal fade mappingModal" data-backdrop="static"
	role="dialog" id="mappingModal">
	<div class="modal-dialog modal-dialog-centered modal-lg">
		<div class="modal-content p-6">
			<div class="modal-header">
				<h4 class="modal-title">맵핑 가능 센터 목록</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- 사용자 지정 부분② : 타이틀 -->
			</div>
			<div class="modal-body">
				<!-- 모달창 안 테이블 -->
				<table class="verticalTable" id="availtable">
					<thead>
						<th>센터코드</th>
						<th>센터명</th>
						<th>주소</th>
					</thead>
					<tbody>
					</tbody>
				</table>

				<div class="center-pagging modal-pagging">
					<ul id="modal-pager" class="pagination">

					</ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="pinkButton modal-pinkBtn"
					id="mappingbutton" data-dismiss="modal">저장</button>
				<button type="button" class="greyButton modal-greyBtn"
					data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/resources/js/manager/mapping.js"></script>