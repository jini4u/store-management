<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css"
	href="/resources/css/manager/managerlookup.css">
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/resources/css/bootstrap.css" />
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/bootstrap.js"></script>
	<script src="/resources/js/manager/manager.js"></script>
	
<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/manager.png"> 
	<h2>담당자 조회</h2>
</div>

<!-- 검색 -->
<div class="search-box">
		<input type="text" class="search-txt" id="searchTxt" name="userName" placeholder="담당자 검색">
		<a class="search-btn" href="#" id="searchBtn"> <i class="fas fa-search"></i></a>
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
			<tbody id="mgrList">
				<c:forEach var="managerVO" items="${managerList}">
					<tr>
						<td>${managerVO.userCode}</td>
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
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 처리  -->
		<div class="center-pagging">
			<ul class="pagination pageModal">
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
					
					<th scope="row">담당자명</th>
					<td>
						<input type="hidden" name="userCode" id="userCode">
						<input type="hidden" name="userPassword" id="userPassword">
						<input type="text" name="userName" id="userName" disabled>
					</td>
					<th scope="row">생년월일</th>
					<td><input type="date" name="userBirth" id="userBirth" disabled></td>
				</tr>
				<tr>
					<th scope="row">Email</th>
					<td><input type="text" name="userEmail" id="userEmail" disabled> </td>
					<th scope="row">휴대전화번호</th>
					<td><input type="text" name="userTel" id="userTel" disabled></td>
				</tr>
				<tr>
					<th scope="row">입사일자</th>
					<td><input type="date" name="userHireDate" id="userHireDate" disabled></td>
					<th scope="row">팀코드</th>
					<td><input type="text" name="userTeamCode" id="userTeamCode" disabled></td>
				</tr>			

				
				<tr>
					<th scope="row">퇴사일자</th>
					<td><input type="date" name="userResignDate"
						id="userResignDate" disabled></td>
					<th>담당 센터명</th>
					<td id="centerName">
					</td> 
				</tr>
			</table>
		</form>
	</div>
</div>
<!-- 모달창 -->
 <button id="testBtn" class="btn">모달 테스트</button>
	<div class="modal fade" id="testModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">담당자 등록</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
				
				
				</div>
				<div class="modal-footer">
					<a class="btn" id="modalY" href="#">예</a>
					<button class="btn" type="button" data-dismiss="modal">아니요</button>
				</div>
			</div>
		</div>
	</div>
