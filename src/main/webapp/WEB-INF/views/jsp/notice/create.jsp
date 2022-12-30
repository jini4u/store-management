<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="titleBox">
	<img src="/resources/images/notification-bell.png">
	<h2>공지사항</h2>
</div>
<form action="create" method="post" >
제목 <input name="title" type="text">
내용 <input name="content" type="text">
작성자 <input name="author" type="text">
<input type="submit" value="저장">
</form>