<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Page</title>
</head>
<body>
${allPosts} 
<div>
<c:forEach var="post" items="${allPosts}">
	${post.author}
</c:forEach>
</div>
</body>
</html>