<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/notice/notice.css"/>
<div class="titleBox">
	<img src="/resources/images/notification-bell.png">
	<h2>공지사항</h2>
</div>
<div class="noticediv">
<table id="fullnoticetable" class="verticalTable">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th class="board-hidden">내용</th>
		<th class="board-hidden">작성자</th>
		<th>게시일</th>
	</tr>
<c:forEach var="post" items="${allPosts}">
	<tr class="noticeTr">
		<td>${post.postno}</td>
		<td class="noticeTitle"><a href="${pageContext.request.contextPath}/board/update?postno=${post.postno}">${post.title}</a></td>
		<td class="board-hidden">${post.content}</td>
		<td class="board-hidden">${post.author}</td>
		<td>${post.posttime}</td>
	</tr>
</c:forEach>
</table>
</div>
<a href="create" id="createnotice"><button class="greyButton btn btn btn-primary btn-lg">글쓰기</button></a>

<script src="${pageContext.request.contextPath}/resources/js/notice/notice.js"></script>