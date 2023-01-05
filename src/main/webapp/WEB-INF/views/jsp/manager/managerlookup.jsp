<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/manager/managerlookup.css">

<div class="titleBox">
	<img src="resources/images/manager.png">
	<h2>담당자 조회</h2>
</div>

<div class="graphbox">
	<div class="managerbox">
		<table class="verticalTable" id="managerTable">
			<thead>
				<tr>
					<th>담당자 코드</th>
					<th>담당자명</th>
					<th>생년월일</th>
					<th>휴대전화번호</th>
					<th>Email</th>
					<th>팀코드</th>
					<th>입사일자</th>
					<th>퇴사일자</th>
					<th>센터명</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="managerVO" items="${managerList}">
				<!-- 	<tr onclick="
						fetch('managerdetail.jsp').then(function(response){
							ressponse.text().then(function(text)){
								document.querySelector('table').innerHTML = text;
							}
						});
					"> -->
					<tr>
						<td>${managerVO.userCode}</td>
						<td>${managerVO.userName}</td>
						<td>${managerVO.userBirth}</td>
						<td>${managerVO.userTel}</td>
						<td>${managerVO.userEmail}</td>
						<td>${managerVO.userTeamCode}</td>
						<td>${managerVO.userHireDate}</td>
						<td>${managerVO.userResignDate}</td>
						<td>${managerVO.centerName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

<div style="height: 30px;"></div>

<form action='/managerlist' method="post">
<button type="button" id="insertmgr">등록</button>
<button type="button" id="savemgr">저장</button>
<table class="rowTable" id="mgrdetailtable">
			<tr>
				<th scope="row">담당자코드</th>
				<td>
					<input type="text" name="userCode" id="userCode" value="${userCode}" readonly>
				</td>
				<th scope="row">담당자명</th>
				<td><input type="text" name="userName" id="userName" value="${userName}" readonly></td>
			</tr>
			<tr>
				<th scope="row">생년월일</th>
				<td><input type="text" name="userBirth" id="userBirth" value="${useruserBirth}" readonly></td>
				<th scope="row">휴대전화번호</th>
				<td><input type="text" name="userTel" id="userTel"></td>
			</tr>
			<tr>
				<th scope="row">Email</th>
				<td><input type="text" name="userEmail" id="userEmail"></td>
				<th scope="row">팀코드</th>
				<td><input type="text" name="userTeamCode" id="userTeamCode"></td>
			</tr>
			<tr>
				<th scope="row">입사일자</th>
				<td><input type="date" name="userHireDate" id="userHireDate" readonly></td>
				<th scope="row">퇴사일자</th>
				<td><input type="date" name="userResignDate" id="userResignDate"></td>
			</tr>
		</table> 
		</form>
	</div>
</div>
<script src="resources/js/manager/manager.js"></script>