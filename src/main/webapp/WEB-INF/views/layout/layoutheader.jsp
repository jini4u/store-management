<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/layout/layoutheader.css">



<a href="/"> <img class="logoImage"
	src="${pageContext.request.contextPath}/resources/images/logo.png">
</a>

<ul class="sidebox">
	<li><a class="mypage"> <img
			src="${pageContext.request.contextPath}/resources/images/user.png">
	</a></li>
	<li id="userName"><p>${sessionScope.userName}&nbsp;</p>
		<c:choose>
			<c:when test="${userCode<20000}">시스템 관리자</c:when>
			<c:when test="${userCode<30000}">담당자 관리자</c:when>
			<c:when test="${userCode<40000}">담당자</c:when>
		</c:choose>
	</li>
	<li class="bar">|</li>
	<li><a class="aTag" style="color: black;" data-toggle="modal" data-backdrop="static" href="#pwUpdate">비밀번호 변경</a></li>
	<li class="bar">|</li>
	<li><a id="aTag" href="/logout">로그아웃</a></li>
</ul>




