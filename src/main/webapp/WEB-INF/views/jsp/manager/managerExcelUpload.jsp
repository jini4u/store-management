<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/manager/managerFileUpload.css">

<div class="menuRoute">
		<img
		src="${pageContext.request.contextPath}/resources/images/home.png">
		<a href="/">&nbsp; Home &nbsp; ></a>
		<span>&nbsp; 담당자  &nbsp; ></span>
		<a href="${pageContext.request.contextPath}/manager/managerfileuploadhistory">&nbsp; 담당자 일괄 등록</a>	
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/upload.png">
	<h2>담당자 일괄 등록</h2>
</div>

<div id="fileuplod_collection">
	<a class="greyButton" href='${pageContext.request.contextPath}/resources/templates/template_담당자.xlsx' download="" class="btn btn btn-primary btn-lg">양식파일 다운로드</a>
	<form action="/manager/managerfileupload" method="post" enctype="multipart/form-data">
	<input type="file" name="mgrExcelFile">
	<input class="pinkButton" type="submit" value="UPLOAD">
	</form>
</div>

<table class="verticalTable">
	<tr>
		<th>업로드 날짜</th>
		<th>업로드 한 담당자</th>
		<th>파일명</th>
		<th>구분</th>
	</tr>
	<c:forEach items="${mgrHistoryMapList}" var="historyMap">
		<tr>
			<td>${historyMap.postDate}</td>
			<td>${historyMap.userName}</td>
			<td><a href='/file/manager_${historyMap.originalName}' download="">${historyMap.originalName}</a></td>
			<td>${historyMap.result}</td>
		</tr>
	</c:forEach>
</table>
	<div class="center-pagging">
		<ul class="pagination">
			<li><a class="innerPager first"
				href="${mgrURL}?pageNo=1">처음</a></li>
			<li><c:if test="${pager.groupNo>1}">
					<a class="innerPager arrow left"
						href="${mgrURL}?pageNo=${pager.startPageNo-1}">이전</a>
				</c:if></li>
			<c:forEach var="i" begin="${pager.startPageNo}"
				end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager num"
							href="${mgrURL}?pageNo=${i}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a id="now-page" class="innerPager num active"
							href="${mgrURL}?pageNo=${i}">${i}</a>
					</c:if></li>
			</c:forEach>
			<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
					<a class="innerPager arrow right"
						href="${mgrURL}?pageNo=${pager.endPageNo+1}">다음</a>
				</c:if></li>
			<li><a class="innerPager last"
				href="${mgrURL}?pageNo=${pager.totalPageNo}">맨끝</a></li>
		</ul>
	</div>

<script src="${pageContext.request.contextPath}/resources/js/manager/managerFileUpload.js"></script>