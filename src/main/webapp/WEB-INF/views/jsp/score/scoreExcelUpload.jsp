<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<div class="menuRoute">
		<img
		src="${pageContext.request.contextPath}/resources/images/home.png">
		<a href="/">&nbsp; Home &nbsp; ></a>
		<span>&nbsp; 점수  &nbsp; ></span>
		<a href="">&nbsp; 점수 일괄 등록</a>	
</div>

<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/upload.png">
	<h2>점수 일괄 등록</h2>
</div>
<div class='uploadbutton'>
<a href='${pageContext.request.contextPath}/resources/templates/template_점수.xlsx' download="" class="greyButton btn btn btn-primary btn-lg">양식파일 다운로드</a>
<form id="file-upload-form">
<input type="file" id="file-select-button" name="scoreExcelFile">
<button id="file-upload-button" class="pinkButton">UPLOAD</button>
</form>
</div>

<table class="verticalTable">
	<tr>
		<th>업로드 날짜</th>
		<th>업로드 한 담당자</th>
		<th>파일명</th>
		<th>구분</th>
	</tr>
	<c:forEach items="${historyMapList}" var="historyMap">
	<tr>
		<td>${historyMap.postDate}</td>
		<td>${historyMap.userName}</td>
		<td><a href='/file/score_${historyMap.originalName}' download="${historyMap.originalName}">${historyMap.originalName}</a></td>
		<td>${historyMap.result}</td>
	</tr>
	</c:forEach>
</table>

<div class="center-pagging">
		<ul class="pagination">
			<li><a class="innerPager first"
				href="scoreupload?pageNo=1">처음</a></li>
			<li><c:if test="${pager.groupNo>1}"> 
					<a class="innerPager arrow left"
						href="scoreupload?pageNo=${pager.startPageNo-1}">이전</a>
				</c:if></li>

			<c:forEach var="i" begin="${pager.startPageNo}"
				end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager num"
							href="scoreupload?pageNo=${i}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a class="innerPager active num" id="now-page"
							href="scoreupload?pageNo=${i}">${i}</a>
					</c:if></li>
			</c:forEach>

			<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
					<a class="innerPager arrow right"
						href="scoreupload?pageNo=${pager.endPageNo+1}">다음</a>
				</c:if></li>
			<li><a class="innerPager last"
				href="scoreupload?pageNo=${pager.totalPageNo}">맨끝</a></li>
		</ul>
	</div>

<script src="${pageContext.request.contextPath}/resources/js/score/scoreupload.js"></script>