<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/score/score.css" />
<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/업로드.png">

	<h2>센터 정보 일괄 등록</h2>
</div>

<div class='uploadbutton'>
<form id="file-upload-form" enctype="multipart/form-data" action="centerExcelUpload" method="post">
<label for="file-select-button" class="greyButton">파일선택</label>
<input type="file" id="file-select-button" name="centerExcelFile">
<input type="submit" id="file-upload-button" class="pinkButton">
</form>
<!-- <button id="file-upload-button" class="pinkButton">업로드</button> -->
</div>


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
			<td>${historyMap.originalName}</td>
			<td>${historyMap.result}</td>
		</tr>
	</c:forEach>

</table>