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
<body>
	<div>
		<div class="header">
			<page:applyDecorator name="layoutheader" />
		</div>
		<div>
			<div>
				<page:applyDecorator name="layoutleft" />
			</div>
			<div>
				<decorator:body />
			</div>
		</div>
		<div>foot</div>
	</div>
</body>
</html>