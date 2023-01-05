<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="resources/css/center/centerList.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<h1 class="title">센터 관리</h1>
<table class="verticalTable" id="center-left">
	<thead>
		<tr>
			<th>센터 코드</th>
			<th>센터명</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>오픈 일</th>
			<th>운영 여부</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="centerList" items="${centerList}">
			<tr class="centerListTr">
				<td>${centerList.centerCode}</td>
				<td>${centerList.centerName}</td>
				<td>${centerList.centerTel}</td>
				<td>${centerList.centerAddress}</td>
				<td>${fn:substring(centerList.centerOpeningDate,0,10)}</td>
									
				<c:if test="${centerList.centerOpeningDate!=null}">
					<td>Y</td>
				</c:if>
				<c:if test="${centerList.centerClosingDate!=null}"> 
					<td>N</td>
				</c:if>
				<td style="display:none">${centerList.centerGuide}</td>
				<td style="display:none">${fn:substring(centerList.centerClosingDate,0,10)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<form action="/centerInsert" method="post">
	<table class="rowTable" id="center-right">
			<tr>
				<th>센터 코드</th>
				<td><input type="text" name="centerCode" value="${centerCode}" id="centerCode" readonly="readonly"></td>
				<th>센터명</th>
				<td><input type="text" name="centerName" id="centerName"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="centerTel" id="centerTel"></td>
				<th>운영여부</th>
				<td><select>
						<option>Y</option>
						<option>N</option>
				</select></td>
			</tr> 
			<tr>
				<th>주소</th>
				<td><input type="text" name="centerAddress" id="centerAddress"></td>
				<th>오시는 길</th>
				<td><input type="text" name="centerGuide" id="centerGuide"></td>
			</tr>
			<tr>
				<th>오픈 일</th>
				<td><input type="date" name="centerOpeningDate" id="centerOpeningDate"></td>
				<th>폐점 일</th>
				<td><input type="date" name="centerClosingDate" id="centerClosingDate"></td>
			</tr>
	</table>
</form>
	<input type="button" id="centerInsertBtn" value="등록">
	<input type="button" id="centerSavedBtn" value="저장">

<script src="resources/js/center/centerList.js"></script>
