<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/layout/layoutleft.css">
<body>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://kit.fontawesome.com/0b3bdf6c61.js" crossorigin="anonymous"></script>
		<ul class="accordion-menu">
			<li>
				<div class="dropdownlink">
					센터 <i class="fa fa-chevron-down" aria-hidden="true"></i>
				</div>
				<ul class="submenuItems">
					<li><a href="<c:url value='/centerList'/>">센터 조회</a></li>
					<li><a href="<c:url value='/centerPhoto'/>">센터 사진 관리</a></li>
					<li><a href="#">센터 정보 일괄 등록</a></li>
				</ul>
			</li>
			<li>
				<div class="dropdownlink">
					담당자 <i class="fa fa-chevron-down" aria-hidden="true"></i>
				</div>
				<ul class="submenuItems">
					<li><a href="<c:url value='/managerlookup'/>">담당자 조회</a></li>
					<li><a href="<c:url value='/managermapping'/>">담당자 맵핑</a></li>
					<li><a href="#">담당자 정보 일괄 등록</a></li>
				</ul>
			</li>
			<li>
				<div class="dropdownlink">
					점포점수 <i class="fa fa-chevron-down" aria-hidden="true"></i>
				</div>
				<ul class="submenuItems">
					<li><a href="<c:url value='/score'/>">센터 점수 조회</a></li>
					<li><a href="<c:url value='/code'/>">항목 코드 관리</a></li>
					<li><a href="<c:url value='/scoreupload'/>">센터 점수 일괄 등록</a></li>
				</ul>
			</li>
			<li>
				<div class="dropdownlink">
					<a href="<c:url value='/statistics'/>">통계 </a>
				</div>
			</li>
		</ul>
<script src="resources/js/layoutleft.js"></script>
