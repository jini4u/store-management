<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://kit.fontawesome.com/809e779c97.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="/resources/css/manager/managerlookup.css">
<!--  Font Awesome 라이브러리 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />


<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/manager.png">
	<h2>담당자 조회</h2>
</div>

<div class="search_insert">
	<!-- 검색 -->

	<form class="search-form" action="/manager/managerSearch" method="get">
			<select class="search-select" name="keywordType">
				<option value="UC">담당자 코드</option>
				<option value="UN">담당자명</option>
				<option value="UTC">팀코드</option>
			</select> 
		<div class="search-box">
			<input type="text" class="search-txt" id="searchTxt" name="keyword"
				placeholder="search">
			<button type="submit" value="search" class="search-btn">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</form>
	<button class="pinkButton" data-toggle="modal"
		data-target="#insertModal">등록</button>
</div>

<div class="managerbox">
	<!-- 검색한 키워드가 존재할 때 -->

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
				<th class="updateCell">상세보기</th>
			</tr>
		</thead>
		<c:if test="${managerListCheck != 'empty' }">
			<tbody id="mgrList">
				<c:forEach var="managerVO" items="${managerList}">
					<tr>
						<td>${managerVO.userCode}</td>
						<td>${managerVO.userName}</td>
						<td>${managerVO.userBirth}</td>
						<td>${managerVO.userTel}</td>
						<td>${managerVO.userEmail}</td>
						<td>${managerVO.userTeamCode}</td>
						<td>${managerVO.userHireDate}</td>
						<td>${managerVO.userResignDate}</td>
						<td><button class="updateBtn" data-toggle='modal'
								data-target='#updateModal'>
									<img
		src="${pageContext.request.contextPath}/resources/images/edit-tools.png">
							</button></td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
	<div class="center-pagging">
		<ul class="pagination">
			<li><a class="innerPager first"
				href="${mgrURL}?pageNo=1&keywordType=${keywordType}&keyword=${keyword}">처음</a></li>
			<li><c:if test="${pager.groupNo>1}">
					<a class="innerPager arrow left"
						href="${mgrURL}?pageNo=${pager.startPageNo-1}&keywordType=${keywordType}&keyword=${keyword}">이전</a>
				</c:if></li>
			<c:forEach var="i" begin="${pager.startPageNo}"
				end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager active num"
							href="${mgrURL}?pageNo=${i}&keywordType=${keywordType}&keyword=${keyword}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a id="now-page" class="innerPager num"
							href="${mgrURL}?pageNo=${i}&keywordType=${keywordType}&keyword=${keyword}">${i}</a>
					</c:if></li>
			</c:forEach>
			<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
					<a class="innerPager arrow right"
						href="${mgrURL}?pageNo=${pager.endPageNo+1}&keywordType=${keywordType}&keyword=${keyword}">다음</a>
				</c:if></li>
			<li><a class="innerPager last"
				href="${mgrURL}?pageNo=${pager.totalPageNo}&keywordType=${keywordType}&keyword=${keyword}">맨끝</a></li>
		</ul>
	</div>
	</c:if>

	<!-- 검색한 키워드가 존재하지 않을 때 -->
	<c:if test="${managerListCheck == 'empty'}">
		<tbody id="mgrList">
			<tr>
				<td colspan="9">등록된 담당자가 없습니다.</td>
			</tr>
		</tbody>
		</table>
	</c:if>
</div>





<!--등록 Modal -->
<div class="modal fade" data-backdrop="static" id="insertModal"
	role="dialog">
	<!-- 사용자 지정 부분① : id명 -->
	<div class="modal-dialog modal-dialog-centered">
		<!-- Modal content-->
		<div class="modal-content p-6">
			<div class="modal-header">
				<h4 class="modal-title">담당자 등록</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- 사용자 지정 부분② : 타이틀 -->
			</div>
			<div class="modal-body">
				<!--   disabled="disabled" class="form-control removeDisabled"  <table class="rowTable" id="center-right">  -->
				<form id="insertform">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">담당자명</label> <input type="hidden"
								name="userCode" id="userCode"> <input type="hidden"
								name="userPassword" id="userPassword"> <input
								type="text" name="userName" id="userName" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">생년월일</label> <input type="date"
								name="userBirth" id="userBirth" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>


					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">Email</label> <input type="text"
								name="userEmail" id="userEmail" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">휴대전화번호</label> <input type="text"
								name="userTel" id="userTel" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">입사일자</label> <input type="date"
								name="userHireDate" id="userHireDate" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">팀코드</label> <input type="text"
								name="userTeamCode" id="userTeamCode" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="greyButton" id="resetBtn">초기화</button>
				<button type="button" class="pinkButton" id="savemgr"
					data-dismiss="modal">저장</button>

				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button> -->
			</div>
		</div>
	</div>
</div>


<!-- 수정 모달창 -->
<div class="modal fade" data-backdrop="static" id="updateModal"
	role="dialog">
	<!-- 사용자 지정 부분① : id명 -->
	<div class="modal-dialog modal-dialog-centered">
		<!-- Modal content-->
		<div class="modal-content p-6">
			<div class="modal-header">
				<h4 class="modal-title">담당자 정보 수정</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- 사용자 지정 부분② : 타이틀 -->
			</div>
			<div class="modal-body">
				<!--   disabled="disabled" class="form-control removeDisabled"  <table class="rowTable" id="center-right">  -->
				<form id="insertform">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">담당자명</label> <input type="hidden"
								name="userCode" id="userCodeInfo"> <input type="hidden"
								name="userPassword" id="userPasswordInfo"> <input
								type="text" name="userName" id="userNameInfo"
								class="form-control" aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm" disabled="disabled">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">생년월일</label> <input type="date"
								name="userBirth" id="userBirthInfo" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm" disabled="disabled">
						</div>
					</div>


					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">Email</label> <input type="text"
								name="userEmail" id="userEmailInfo" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">휴대전화번호</label> <input type="text"
								name="userTel" id="userTelInfo" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">입사일자</label> <input type="date"
								name="userHireDate" id="userHireDateInfo" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm" disabled="disabled">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">팀코드</label> <input type="text"
								name="userTeamCode" id="userTeamCodeInfo" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">퇴사일자</label> <input type="date"
								name="userResignDate" id="userResignDateInfo"
								class="form-control" aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">담당 센터명</label>
							<div id="centerName" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="pinkButton" id="updatemgr"
					data-dismiss="modal">수정</button>

				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/manager/manager.js"></script>