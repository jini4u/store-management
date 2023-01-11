<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<tr id="mgrListTr">
						<td id=existenceUserCode>${managerVO.userCode}</td>
						<td>${managerVO.userName}</td>
						<td><fmt:formatDate value="${managerVO.userBirth}"
								pattern="yyyy-MM-dd" /></td>
						<td>${managerVO.userTel}</td>
						<td>${managerVO.userEmail}</td>
						<td>${managerVO.userTeamCode}</td>
						<td><fmt:formatDate value="${managerVO.userHireDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${managerVO.userResignDate}"
								pattern="yyyy-MM-dd" /></td>
						<%-- 						<td><select>
								<c:forEach var="center" items="${managerVO.centerList}">
									<option>${center.centerName}</option>
								</c:forEach>
						</select></td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="center-pagging">
			<ul class="pagination modal">
				<li><a class="innerPager first" href="managerList?pageNo=1">처음</a></li>
				<li><c:if test="${pager.groupNo>1}">
						<a class="innerPager arrow left"
							href="managerList?pageNo=${pager.startPageNo-1}">이전</a>
					</c:if></li>
				<c:forEach var="i" begin="${pager.startPageNo}"
					end="${pager.endPageNo}">
					<li><c:if test="${pager.pageNo != i}">
							<a class="innerPager active num" href="managerList?pageNo=${i}">${i}</a>
						</c:if></li>
					<li><c:if test="${pager.pageNo == i}">
							<a class="innerPager num" href="managerList?pageNo=${i}">${i}</a>
						</c:if></li>
				</c:forEach>
				<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
						<a class="innerPager arrow right"
							href="managerList?pageNo=${pager.endPageNo+1}">다음</a>
					</c:if></li>
				<li><a class="innerPager last"
					href="managerList?pageNo=${pager.totalPageNo}">맨끝</a></li>
			</ul>
		</div>

		<div style="height: 30px;"></div>

		<form id="insertform">
			<div>
				<button type="button" class="pinkButton" id="insertmgr">등록</button>
				<button type="button" class="greyButton" id="savemgr">저장</button>
			</div>
			<table class="rowTable" id="mgrdetailtable">
				<tr>
					<th scope="row">담당자코드</th>
					<td><input type="text" name="userCode" id="userCode"
						value="${newUserCode}" readonly> <input type="hidden"
						value="${newUserCode}" id="newUserCode"> <input
						type="hidden" name="userPassword" id="userPassword"></td>
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
				<tr>
					<th>담당 센터명</th>
					<td colspan="3"><c:forEach var="center"
							items="${managerVO.centerList}">
							${center.centerName}
						</c:forEach></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script src="resources/js/manager/manager.js"></script>