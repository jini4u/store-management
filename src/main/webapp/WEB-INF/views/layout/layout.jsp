<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<!DOCTYPE html>
<html>
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
	
<!-- 공통함수 부분 -->
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>

<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/layout/layout.css">
<meta charset="UTF-8">
<title>헌혈의 집 관리 시스템</title>
</head>
<body class="totalbox">

	<div>
		<!-- Modal -->
		<div class="modal fade " id="pwUpdate" role="dialog">
			<!-- 사용자 지정 부분① : id명 -->
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content p-6">
					<div class="modal-header">
						<h4 class="modal-title">비밀번호 변경</h4>
						<button type="button" class="close" data-dismiss="modal">×</button>
						<!-- 사용자 지정 부분② : 타이틀 -->
					</div>
					<div class="modal-body">
						<form action="passwordUpdate" method="post">
							<div class="col-md-6 mb-3" style="max-width: 100%">
								<label for="name">아이디</label> 
								<input type="text"
									 name="userCode" class="form-control"
									aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-sm" value="${sessionScope.userCode}" readonly="readonly">
							</div>
							<div class="col-md-6 mb-3" style="max-width: 100%">
								<label for="name">비밀번호</label> 
								<input type="password"
									 name="userPassword" class="form-control"
									aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-sm">
							</div>
						
					</div>
					<div class="modal-footer">
						<input type="submit" class="pinkButton modal-pinkBtn" value="저장">
						</form>
						<button type="button" class="greyButton modal-greyBtn" data-dismiss="modal">닫기</button>	
					</div>
				</div>
			</div>
		</div>

		<div class="header">
			<page:applyDecorator name="layoutheader" />
		</div>

		<div class="middlebox">
			<div class="leftbox">
				<page:applyDecorator name="layoutleft" />
			</div>
			<div class="contentbox">
				<decorator:body />
			</div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</html>
