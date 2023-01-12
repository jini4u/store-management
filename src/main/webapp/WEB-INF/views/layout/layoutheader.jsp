<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css"
	href="/resources/css/layout/layoutheader.css">

	<a href="/"> <img class="logoImage" src="/resources/images/logo.png">
	</a>

		<ul class="sidebox">
			<li>
				<a class="mypage"> <img src="/resources/images/user.png">
				</a>
			</li>
			<c:set var="titleUserName" value="고은별"/>
			<li><c:out value="${titleUserName}"/> 담당자<!-- 로그인하는 사람마다 달라짐 --></li>
			<li class="bar">|</li>
			<li>
				<a>로그아웃</a>
			</li>
		</ul>

