<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="resources/css/center/centerList.css">
<h1 class="title">센터 관리</h1>

<form action="/centerInsert" method="post">
	<input type="hidden" name="centerCode" value="${centerCode}">
	센터명:<input type="text" name="centerName"> 
	전화번호:<input type="text" name="centerTel"> 
	주소:<input type="text" name="centerAddress"> 
	운영여부:<select>
		<option>Y</option>
		<option>N</option>
	</select> 
	오시는 길: <input type="text" name="centerGuide"> 
	오픈 일: <input type="date" name="centerOpeningDate"> 
	폐점 일: <input type="date" name="centerClosingDate" disabled="disabled"> 
	<input type="submit" value="등록">
</form>

<table>							
	<tr>
		<th>센터 코드</th>
		<th>센터명</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>오픈 일</th>
		<th>운영 여부</th>
		<th>폐점 일</th>
	</tr>
	<c:forEach var="centerList" items="${centerList}">
	<tr>
		<td>${centerList.centerCode}</td>
		<td>${centerList.centerName}</td>
		<td>${centerList.centerTel}</td>
		<td>${centerList.centerGuide}</td>
		<td>${centerList.centerOpeningDate}</td>
		<c:if test="${centerList.centerOpeningDate != null}">
			<td>Y</td>
		</c:if>
		<c:if test="${centerList.centerOpeningDate == null}">
			<td>N</td>
		</c:if>
	</tr>
	</c:forEach>
</table>