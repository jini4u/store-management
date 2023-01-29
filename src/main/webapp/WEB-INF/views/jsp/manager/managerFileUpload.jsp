<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/manager/managerFileUpload.css">
<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/upload.png">
	<h2>담당자 일괄 등록</h2>
</div>

<div id="fileuplod_collection">
	<a class="greyButton" href='/file/managerUplaod_form.xlsx' download="" class="btn btn btn-primary btn-lg">양식파일 다운로드</a>
	<form action="/manager/managerFileUpload" method="post" enctype="multipart/form-data">
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
			<td><a href='/file/manager_${historyMap.originalName}' download="">${historyMap.originalName}</a>${historyMap.originalName}</td>
			<td>${historyMap.result}</td>
		</tr>
	</c:forEach>
</table>

<script src="${pageContext.request.contextPath}/resources/js/manager/managerFileUpload.js"></script>