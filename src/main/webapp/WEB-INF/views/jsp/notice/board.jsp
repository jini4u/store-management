<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/notice/notice.css"/>
<div class="titleBox">
	<img src="/resources/images/notification-bell.png">
	<h2>공지사항</h2>
</div>
<div id="noticediv">
<table id="fullnoticetable">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>내용</th>
		<th>작성자</th>
		<th>게시일</th>
	</tr>
<c:forEach var="post" items="${allPosts}">
	<tr>
		<td><a href="board/update?postno=${post.postno}">${post.postno}</a></td>
		<td>${post.title}</td>
		<td>${post.content}</td>
		<td>${post.author}</td>
		<td>${post.posttime}</td>
	</tr>
</c:forEach>
</table>
</div>
<a href="board/create"><button>글쓰기</button></a>