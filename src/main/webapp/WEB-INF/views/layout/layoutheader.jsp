<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="resources/css/layoutheader.css">
<meta charset="UTF-8">
<title>sitemesh test</title>
</head>
<body>
	<div class="header">
		<a> <img class="logoImage" src="resources/images/logo.png">
		</a>

		<nav>
		<ul class="menu">
			<li class="menuMain">
				<a><span>센터</span></a>
				<ul class="menuMiddle">
					<li><a>센터 조회</a></li>
					<li><a>센터 사진 관리</a></li>
					<li><a>센터 정보 일괄 등록</a></li>
				</ul>
			</li>
			<li class="menuMain"><a><span>담당자</span></a>
				<ul class="menuMiddle">
					<li><a>센터 점수 조회</a></li>
					<li><a>항목 코드 관리</a></li>
					<li><a>센터 점수 일괄 등록</a></li>
				</ul>
			</li>
			<li class="menuMain"><a><span>점포점수</span></a>
				<ul class="menuMiddle">
					<li><a>담당자 조회</a></li>
					<li><a>담당자 맵핑</a></li>
					<li><a>담당자 정보 일괄 등록</a></li>
				</ul>
			</li>
			<li class="menuMain">
				<a><span>통계</span></a>
			</li>
		</ul>
		</nav>
		<a class="myapage"> <img src="resources/images/user.png">
		</a>
	</div>
</body>
</html>