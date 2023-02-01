<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 엑셀 파일 다운로드 하기 위해 사용 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<div class="menuRoute">
		<img
		src="${pageContext.request.contextPath}/resources/images/home.png">
		<a href="/">&nbsp; Home &nbsp; ></a>
		<span>&nbsp; 센터  &nbsp; ></span>
		<a href="">&nbsp; 센터 일괄 등록</a>	
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/upload.png">
	<h2>센터 일괄 등록</h2>
</div>

<div class='uploadbutton' id="fileuplod_collection">
<a href='${pageContext.request.contextPath}/resources/templates/template_센터.xlsx' download="" class="greyButton">양식파일 다운로드</a>
<form id="file-upload-form" enctype="multipart/form-data" action="centerexcelupload" method="post">
<input type="hidden" name="userCode" value="${ssesionScope.userCode}">
<input type="file"  name="centerExcelFile" accept=".xlsx, .xls">
<input type="submit"  class="pinkButton" value="UPLOAD"> 
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
			<td><a href='/file/centerExcel_${historyMap.originalName}' download="">${historyMap.originalName}</a></td>
			<td>${historyMap.result}</td>
			
		</tr>
	</c:forEach>

</table>