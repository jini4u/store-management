<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 엑셀 파일 다운로드 하기 위해 사용 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/업로드.png">

	<h2>센터 정보 일괄 등록</h2>
</div>

<div class='uploadbutton'>
<form id="file-upload-form" enctype="multipart/form-data" action="centerexcelupload" method="post">
<input type="file"  name="centerExcelFile" accept=".xlsx, .xls">
<input type="submit"  class="pinkButton">
</form>
<!-- <button id="file-upload-button" class="pinkButton">업로드</button> -->
</div>
<a href='/file/centerUpload_form.xlsx' download="" class="btn btn btn-primary btn-lg">양식파일 다운로드</a>

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
			<td><a href='/file/centerExcel_${historyMap.originalName}' download="">${historyMap.originalName}</a></td>
			<td>${historyMap.result}</td>
			
		</tr>
	</c:forEach>

</table>