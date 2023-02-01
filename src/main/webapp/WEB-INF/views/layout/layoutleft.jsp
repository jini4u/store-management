<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout/layoutleft.css">

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://kit.fontawesome.com/0b3bdf6c61.js" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<a href="/" class="logoAtag"> <img class="logoImage"
	src="${pageContext.request.contextPath}/resources/images/logo.png">
</a>

<ul class="accordion-menu">
	<li>
		<div class="dropdownlink">
			센터 <i class="fa fa-chevron-down" aria-hidden="true"></i>
		</div>
		<ul class="submenuItems">
			<li><a href="<c:url value='/center/centerlist'/>">센터 조회</a></li>
			<c:if test="${authority eq 'sysadmin'}">
			<li><a href="<c:url value='/center/centerphoto'/>">센터 사진 관리</a></li>
			<li><a href="${pageContext.request.contextPath}/center/centerexcelupload">센터 정보 일괄 등록</a></li>
			</c:if>
		</ul>
	</li>
	<c:if test="${authority ne 'manager'}">
	<li>
		<div class="dropdownlink">
			담당자 <i class="fa fa-chevron-down" aria-hidden="true"></i>
		</div>
		<ul class="submenuItems">
			<li><a href="${pageContext.request.contextPath}/manager/managerlist">담당자 조회</a></li>
			<li><a href="${pageContext.request.contextPath}/manager/managermapping">담당자 맵핑</a></li>
			<li><a href="${pageContext.request.contextPath}/manager/managerfileuploadhistory">담당자 정보 일괄 등록</a></li>
		</ul>
	</li>
	</c:if>
	<li>
		<div class="dropdownlink">
			점수 <i class="fa fa-chevron-down" aria-hidden="true"></i>
		</div>
		<ul class="submenuItems">
			<li><a href="<c:url value='/score/scorelist'/>">센터 점수 조회</a></li>
			<!-- centercode 로그인 기능 만든 후 변경해줘야함 -->
			<li><a href="<c:url value='/score/code'/>">항목 코드 관리</a></li>
			<li><a href="<c:url value='/score/scoreupload'/>">센터 점수 일괄 등록</a></li>
		</ul>
	</li>
	<li>
		<div class="dropdownlink">
			<a href="<c:url value='/statistics'/>">통계 </a>
		</div>
	</li>
</ul>

<div class="userInfo btn-group dropup">
		 <button class="userName btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    		<img src="${pageContext.request.contextPath}/resources/images/avatar.png">
	
			<p>&nbsp;${userName}&nbsp;</p>
			<c:choose>
				<c:when test="${authority eq 'manager'}">담당자</c:when>
				<c:when test="${authority eq 'admin'}">담당자 관리자</c:when>
				<c:when test="${authority eq 'sysadmin'}">시스템 관리자</c:when>
			</c:choose>
  		</button>

  <ul class="dropdown-menu">
	    <li>
	    	<a class="aTag dropdown-item" data-toggle="modal"
         data-backdrop="static" href="#pwUpdate">비밀번호 변경</a>
	    </li>
	    <li><a class="dropdown-item" href="/logout">로그아웃</a></li>
  </ul>
</div>
<script src="${pageContext.request.contextPath}/resources/js/layoutleft.js"></script>
