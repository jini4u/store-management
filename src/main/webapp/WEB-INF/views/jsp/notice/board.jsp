<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/notice/notice.css" />

<div class="menuRoute">
		<img
		src="${pageContext.request.contextPath}/resources/images/home.png">
		<a href="/">&nbsp; Home &nbsp; ></a>
		<a href="/notice/list">&nbsp; 공지사항</a>	
</div>

<div class="titleBox">
	<img src="/resources/images/notification-bell.png">
	<h2>공지사항</h2>
</div>

<div class="noticeInsertDiv">
	<a href="create" id="createnotice">
		<button class="pinkButton btn btn btn-primary btn-lg">글쓰기</button>
	</a>
</div>

<div class="noticediv">
	<table id="fullnoticetable" class="verticalTable">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>게시일</th>
		</tr>
		<c:forEach var="post" items="${allPosts}">
			<tr class="noticeTr">
				<td>${post.postno}</td>
				<td class="noticeTitle"><a
					href="${pageContext.request.contextPath}/notice/update?postno=${post.postno}">${post.title}</a></td>
				<td>${post.author}</td>
				<td>${post.posttime}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="center-pagging">
		<ul class="pagination">
			<li><a class="innerPager first" href="list?pageNo=1">처음</a></li>
			<li><c:if test="${pager.groupNo>1}">
					<a class="innerPager arrow left"
						href="list?pageNo=${pager.startPageNo-1}">이전</a>
				</c:if></li>

			<c:forEach var="i" begin="${pager.startPageNo}"
				end="${pager.endPageNo}">
				<li><c:if test="${pager.pageNo != i}">
						<a class="innerPager num" href="list?pageNo=${i}">${i}</a>
					</c:if></li>
				<li><c:if test="${pager.pageNo == i}">
						<a class="innerPager active num" id="now-page"
							href="list?pageNo=${i}">${i}</a>
					</c:if></li>
			</c:forEach>

			<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
					<a class="innerPager arrow right"
						href="list?pageNo=${pager.endPageNo+1}">다음</a>
				</c:if></li>
			<li><a class="innerPager last"
				href="list?pageNo=${pager.totalPageNo}">맨끝</a></li>
		</ul>
	</div>

</div>


<script
	src="${pageContext.request.contextPath}/resources/js/notice/notice.js"></script>