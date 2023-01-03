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
		<table class="verticalTable">
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
					<tr onClick="location.href='${pageContext.request.contextPath}/managerList/${managerVO.userCode}'">
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

		<table class="rowTable">
			<tr>
				<th scope="row">담당자코드</th>
				<td>${managerVO.userCode}</td>
				<th scope="row">담당자명</th>
				<td>${managerVO.userName}</td>
			</tr>
			<tr>
				<th scope="row">생년월일</th>
				<td>${managerVO.userBirth}</td>
				<th scope="row">휴대전화번호</th>
				<td>${managerVO.userTel}</td>
			</tr>
			<tr>
				<th scope="row">Email</th>
				<td>${managerVO.userEmail}</td>
				<th scope="row">팀코드</th>
				<td>${managerVO.userTeamCode}</td>
			</tr>
			<tr>
				<th scope="row">입사일자</th>
				<td>${managerVO.userHireDate}</td>
				<th scope="row">퇴사일자</th>
				<td>${managerVO.userResignDate}</td>
			</tr>
		</table>
	</div>
</div>