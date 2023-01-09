<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/manager/managerlookup.css">

<div class="titleBox">
	<img src="resources/images/manager.png">
	<h2>담당자 조회</h2>
</div>

<!-- 검색 -->
<div class="search-box">
	<input type="text" class="search-txt" name="" placeholder="담당자 검색">
	<a class="search-btn" href="#"> <i class="fas fa-search"></i>
	</a>
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
						<td id=existenceUserCode>${managerVO.userCode}</td>
						<td>${managerVO.userName}</td>
						<td>${managerVO.userBirth}</td>
						<td>${managerVO.userTel}</td>
						<td>${managerVO.userEmail}</td>
						<td>${managerVO.userTeamCode}</td>
						<td>${managerVO.userHireDate}</td>
						<td>${managerVO.userResignDate}</td>
						<td><select>
								<c:forEach var="center" items="${managerVO.centerList}">
									<option>${center.centerName}</option>
								</c:forEach>
						</select></td>
					</tr>
				</c:forEach>

				<tr>
					<td id="pager" colspan="9">
						<div>
							<a class="innerPager" href="managerList?pageNo=1">처음</a>
							<c:if test="${pager.groupNo>1}">
								<a class="innerPager"
									href="managerList?pageNo=${pager.startPageNo-1}">이전</a>
							</c:if>

							<c:forEach var="i" begin="${pager.startPageNo}"
								end="${pager.endPageNo}">
								<c:if test="${pager.pageNo != i}">
									<a class="innerPager" href="managerList?pageNo=${i}">${i}</a>
								</c:if>
								<c:if test="${pager.pageNo == i}">
									<a class="innerPager" href="managerList?pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>

							<c:if test="${pager.groupNo<pager.totalGroupNo}">
								<a class="innerPager"
									href="managerList?pageNo=${pager.endPageNo+1}">다음</a>
							</c:if>
							<a class="innerPager"
								href="managerList?pageNo=${pager.totalPageNo}">맨끝</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<div style="height: 30px;"></div>

		<form id="insertform">
			<button type="button" id="insertmgr">등록</button>
			<button type="button" id="savemgr">저장</button>
			<table class="rowTable" id="mgrdetailtable">
				<tr>
					<th scope="row">담당자코드</th>
					<td><input type="text" name="userCode" id="userCode"
						value="${userCode}" readonly> <input type="hidden"
						name="userPassword" id="userPassword"></td>
					<th scope="row">담당자명</th>
					<td><input type="text" name="userName" id="userName"></td>
				</tr>
				<tr>
					<th scope="row">생년월일</th>
					<td><input type="date" name="userBirth" id="userBirth"></td>
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
					<td><input type="date" name="userHireDate" id="userHireDate"></td>
					<th scope="row">퇴사일자</th>
					<td><input type="date" name="userResignDate"
						id="userResignDate" readonly></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script src="resources/js/manager/manager.js"></script>