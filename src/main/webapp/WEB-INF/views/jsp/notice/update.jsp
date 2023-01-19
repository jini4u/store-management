<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<div class="titleBox">
	<img src="/resources/images/notification-bell.png">
	<h2>공지사항</h2>
</div>
	<article>
		<div class="container" role="main">
			<h3>공지사항 수정</h3>
			<form name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}/board/update" >
			<div class="mb-3">
				<label for="title">글번호</label> 
				<input type="text" class="form-control" name="postno" id="title" value="${post.postno}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="title">제목</label> 
				<input type="text" class="form-control" name="title" id="title" value="${post.title}" placeholder="제목을 입력해 주세요">
			</div>
			<div class="mb-3">
				<label for="reg_id">작성자</label> 
				<input type="text" class="form-control" name="author" id="author" value="${post.author}" placeholder="이름을 입력해 주세요">
			</div>
			<div class="mb-3">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" name="content" id="content" value="${post.content}" placeholder="내용을 입력해 주세요"></textarea>
			</div>
			</form>
	<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
	<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
		</div>
	</article>


<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/notice/notice.js"></script>


<a href="${pageContext.request.contextPath}/board/delete?postno=${post.postno}">삭제</a>