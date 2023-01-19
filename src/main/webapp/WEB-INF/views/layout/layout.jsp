<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/layout/layout.css">
	<meta charset="UTF-8">
	<title>헌혈의 집 관리 시스템</title>
	<!-- 공통함수 부분 -->
	<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
<body class="totalbox"> 
	<div>
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
</html>