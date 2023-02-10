<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/notice/notice.css" />

<div class="menuRoute">
	<img src="${pageContext.request.contextPath}/resources/images/home.png">
	<a href="/">&nbsp; Home &nbsp; ></a> <a href="/board/list">&nbsp;
		공지사항 &nbsp; > &nbsp;</a> <a
		href="${pageContext.request.contextPath}/board/update?postno=${post.postno}"
		id="menuRoute_change">공지사항 수정 </a>

</div>

<div class="titleBox">
	<img src="/resources/images/notification-bell.png">
	<h2 id="read-update-heading">공지사항 수정</h2>
</div>
<article>
	<div class="container noticediv" role="main">
		<form name="form" id="update-form" role="form" method="post"
			action="${pageContext.request.contextPath}/notice/update">
			<div class="mb-3">
				<label for="title">글번호</label> <input type="text"
					class="form-control" name="postno" id="title"
					value="${post.postno}" readonly="readonly">
				<div class="invalid"></div>
			</div>
			<div class="mb-3">
				<label for="title">제목</label> <input type="text"
					class="form-control editable" name="title" id="title"
					value="${post.title}" readonly="readonly"
					style="background-color: white;">
				<div class="invalid"></div>
			</div>
			<div class="mb-3">
				<label for="reg_id">작성자</label> <input type="text"
					class="form-control editable" name="author" id="author"
					value="${post.author}" readonly="readonly"
					style="background-color: white;">
				<div class="invalid"></div>
			</div>
			<div class="mb-3">
				<label for="content">내용</label>
				<textarea class="form-control editable" rows="${rows}"
					name="content" id="content" readonly="readonly"
					style="background-color: white;">${post.content}</textarea>
				<div class="invalid"></div>
			</div>
		</form>
		<div id="buttonsdiv">
			<div>
				<button type="button" class="pinkButton" id="btnUpdate">수정</button>
				<a
					href="${pageContext.request.contextPath}/board/delete?postNo=${post.postno}"
					class="btn btn btn-danger btn-lg greyButton">삭제</a>
				<button type="button" class="greyButton btn btn btn-primary btn-lg"
					id="btnList">목록</button>
			</div>
		</div>
</article>
<script
	src="${pageContext.request.contextPath}/resources/js/notice/notice.js"></script>