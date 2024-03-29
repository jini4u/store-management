<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 엑셀 파일 다운로드 하기 위해 사용 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/manager/managerFileUpload.css">

<div class="menuRoute">
	<img src="${pageContext.request.contextPath}/resources/images/home.png">
	<a href="/">&nbsp; Home &nbsp; ></a> <span>&nbsp; 센터 &nbsp; ></span> <a
		href="">&nbsp; 센터 일괄 등록</a>
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/upload.png">
	<h2>센터 일괄 등록</h2>
</div>

<div class='uploadbutton' id="fileuplod_collection">
	<a
		href='${pageContext.request.contextPath}/resources/templates/template_센터.xlsx'
		download="" class="greyButton">양식파일 다운로드</a>
	<div class="fileupload_input">	
		<div id="centerExcelFile"></div>
		<form id="file-upload-form" enctype="multipart/form-data"
			action="centerexcelupload" method="post" onsubmit="return checkFile();">
			<input type="hidden" name="userCode" value="${ssesionScope.userCode}">
			<input type="file" id="centerInputExcelFile" name="centerExcelFile" accept=".xlsx, .xls">
			<input type="submit" class="pinkButton" id="centerExcelInsertBtn" value="UPLOAD">
		</form>
	</div>
	<!-- <button id="file-upload-button" class="pinkButton">업로드</button> -->
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
			<td><a href='/file/${historyMap.fileSavedName}'
				download="${historyMap.originalName}">${historyMap.originalName}</a></td>
			<td>${historyMap.result}</td>

		</tr>
	</c:forEach>
</table>
	<div class="center-pagging">
		<ul class="pagination">
			<li><a class="innerPager first"
				href="centerexcelupload?pageNo=1">처음</a></li>
			<li><c:if test="${pager.groupNo>1}"> 
					<a class="innerPager arrow left"
						href="centerexcelupload?pageNo=${pager.startPageNo-1}">이전</a>
				</c:if></li>

			<c:forEach var="i" begin="${pager.startPageNo}"
				end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager num"
							href="centerexcelupload?pageNo=${i}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a class="innerPager active num" id="now-page"
							href="centerexcelupload?pageNo=${i}">${i}</a>
					</c:if></li>
			</c:forEach>

			<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
					<a class="innerPager arrow right"
						href="centerexcelupload?pageNo=${pager.endPageNo+1}">다음</a>
				</c:if></li>
			<li><a class="innerPager last"
				href="centerexcelupload?pageNo=${pager.totalPageNo}">맨끝</a></li>
		</ul>
	</div>
<script src="${pageContext.request.contextPath}/resources/js/center/centerexcelupload.js"></script>