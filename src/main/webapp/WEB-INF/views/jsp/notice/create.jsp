<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notice/notice.css"/>


<div class="menuRoute">
      <img
      src="${pageContext.request.contextPath}/resources/images/home.png">
      <a href="/">&nbsp; Home &nbsp; ></a>
      <a href="/notice/list">&nbsp; 공지사항 &nbsp; > &nbsp;</a>   
      <a href="${pageContext.request.contextPath}/notice/create" id="menuRoute_change">공지사항 등록 </a>
</div>

<div class="titleBox">
   <img src="${pageContext.request.contextPath}/resources/images/notification-bell.png">
   <h2>공지사항 등록</h2>
</div>
   <article>
      <div class="container noticediv" role="main">
         <form class="insertForm" name="form" id="create-form" role="form" method="post" action="${pageContext.request.contextPath}/notice/create">
         <div class="mb-3">
            <label for="title">제목</label> 
            <input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
            <div class="invalid"></div>
         </div>
         <div class="mb-3">
            <label for="reg_id">작성자</label> 
            <input type="text" class="form-control" name="author" id="reg_id" placeholder="이름을 입력해 주세요">
            <div class="invalid"></div>
         </div>
         <div class="mb-3 insertContent">
            <label for="content">내용</label>
            <textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요"></textarea>
            <div class="invalid"></div>
         </div>
         </form>
         <div class="insertBtn">
			<button type="button" class="pinkButton" id="btnSave">저장</button>
			<button type="button" class="greyButton btn btn btn-primary btn-lg" id="btnList">목록</button>
      	</div>
      </div>
   </article>


<script src="${pageContext.request.contextPath}/resources/js/notice/notice.js"></script>