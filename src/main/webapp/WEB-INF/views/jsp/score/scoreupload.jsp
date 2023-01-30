<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/upload.png">
	<h2>점수 일괄 등록</h2>
</div>
<div class='uploadbutton'>
<form id="file-upload-form">
<p id="file-name">선택된 파일 없음</p>
<label for="file-select-button" class="greyButton">파일선택</label>
<input type="file" id="file-select-button" name="scoreExcelFile">
</form>
<button id="file-upload-button" class="pinkButton">업로드</button>
</div>
<a href='${pageContext.request.contextPath}/resources/templates/template_점수.xlsx' download="" class="btn btn btn-primary btn-lg">양식파일 다운로드</a>

<table class="scoretable">
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
		<td><a href='/file/score_${historyMap.originalName}' download="">${historyMap.originalName}</a></td>
		<td>${historyMap.result}</td>
	</tr>
	</c:forEach>
</table>

<script src="${pageContext.request.contextPath}/resources/js/score/scoreupload.js"></script>