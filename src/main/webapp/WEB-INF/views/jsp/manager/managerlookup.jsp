<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css"
	href="/resources/css/manager/managerlookup.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
<div class="titleBox">
	 <img src="${pageContext.request.contextPath}/resources/images/manager.png">
   <h2>담당자 조회</h2>

</div>

<!-- 검색 -->
<div class="search-box">
   <form action="/manager/managerList" method="get">
      <input type="text" class="search-txt" id="searchTxt" name="keyword" placeholder="담당자 검색"> 
     <a class="search-btn" href="/manager/managersearch" id="searchBtn"> <i class="fas fa-search"></i></a> 

   </form>
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
						<td><button  class='updateModal' 
								   data-toggle='modal' data-target='#updateModal'>수정</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>

		<div class="center-pagging">
			<ul class="pagination">
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


<button class="pinkButton" data-toggle="modal" data-target="#insertModal">등록</button>
<!-- <button class="greyButton" data-toggle="modal" data-target="#myModal">수정</button> -->
<!-- Modal -->

<div class="modal fade" data-backdrop="static" id="insertModal"
	role="dialog">
	<!-- 사용자 지정 부분① : id명 -->
	<div class="modal-dialog modal-dialog-centered" >
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
							<label for="name">담당자명</label> 
							<input type="hidden" name="userCode" id="userCode">
							<input type="hidden" name="userPassword" id="userPassword">
							<input type="text" name="userName" id="userName" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">생년월일</label> 
							<input type="date" name="userBirth" id="userBirth" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
				</div>


				<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">Email</label> 
							<input type="text" name="userEmail" id="userEmail" class="form-control" 
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">휴대전화번호</label> 
							<input type="text" name="userTel" id="userTel" class="form-control" 
									aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-sm">
						</div>
				</div>
				<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">입사일자</label>
							<input type="date" name="userHireDate" id="userHireDate" class="form-control" 
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">팀코드</label> 
							<input type="text" name="userTeamCode" id="userTeamCode" class="form-control" 
									aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-sm">
						</div>
				</div>
			</form>
			</div>
		<div class="modal-footer">
			<button type="button" class="pinkButton" id="resetBtn">초기화</button>
			<button type="button" class="greyButton" id="savemgr">저장</button>
	<!-- 		<button type="button" class="greyButton" id="updatemgr">수정</button> -->
				
			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		</div>
		</div>
	</div>
</div>


<!-- 수정 모달창 -->

<div class="modal fade" data-backdrop="static" id="updateModal"
	role="dialog">
	<!-- 사용자 지정 부분① : id명 -->
	<div class="modal-dialog modal-dialog-centered" >
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
							<label for="name">담당자명</label> 
							<input type="hidden" name="userCode" id="userCodeInfo">
							<input type="hidden" name="userPassword" id="userPasswordInfo">
							<input type="text" name="userName" id="userNameInfo" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm" disabled="disabled">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">생년월일</label> 
							<input type="date" name="userBirth" id="userBirthInfo" class="form-control"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm"  disabled="disabled">
						</div>
				</div>


				<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">Email</label> 
							<input type="text" name="userEmail" id="userEmailInfo" class="form-control" 
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">휴대전화번호</label> 
							<input type="text" name="userTel" id="userTelInfo" class="form-control" 
									aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-sm">
						</div>
				</div>
				<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">입사일자</label>
							<input type="date" name="userHireDate" id="userHireDateInfo" class="form-control" 
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm"  disabled="disabled">
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">팀코드</label> 
							<input type="text" name="userTeamCode" id="userTeamCodeInfo" class="form-control" 
									aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-sm" >
						</div>
				</div>
				<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">퇴사일자</label>
							<input  type="date" name="userResignDate" id="userResignDateInfo" class="form-control" 
								aria-label="Sizing example input"
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
			<button type="button" class="greyButton" id="updatemgr">수정</button>
				
			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		</div>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="/resources/js/manager/manager.js"></script>