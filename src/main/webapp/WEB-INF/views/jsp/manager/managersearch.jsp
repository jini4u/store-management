<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/manager.png"> 
	<h2>담당자 조회</h2>
</div>

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