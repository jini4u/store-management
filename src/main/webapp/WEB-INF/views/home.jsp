<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card m-2">
	<div class="card-header">
		Home
	</div>
	<div class="card-body">
		<a href="<c:url value='/centerList'/>">센터 조회</a>
		<a href="<c:url value='/centerPhoto'/>">센터 사진 관리</a>
	</div>
</div>