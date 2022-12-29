<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/layout.css">
<meta charset="UTF-8">
<title>sitemesh main</title>
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