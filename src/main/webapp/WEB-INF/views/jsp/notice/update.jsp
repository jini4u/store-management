<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="titleBox">
	<img src="resources/images/notification-bell.png">
	<h2>공지사항</h2>
</div>
<form action="update" method="post">
	글번호 ${post.postno} <input name="postno" type="number" value="${post.postno}" hidden="true">
	제목 <input name="title" type="text" value="${post.title}">
	내용 <input name="content" type="text" value="${post.content}">
	작성자 <input name="author" type="text" value="${post.author}">
	<input type="submit" value="수정">
</form>
<a href="delete?postno=${post.postno}">삭제</a>