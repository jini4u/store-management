<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="resources/css/home.css">
<div class="bigGroupBox">
	<div class="smallGroupBox">
		<div class="slidebox">
			<div id="carouselExampleInterval" class="carousel slide"
				data-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active" data-interval="5000">
						<img src="resources/images/center/도남센터-1.jpg"class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item" data-interval="5000">
						<img src="resources/images/center/송촌센터-1.jpg" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="resources/images/center/송촌센터-2.jpg" class="d-block w-100" alt="...">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-target="#carouselExampleInterval" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-target="#carouselExampleInterval" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</button>
			</div>
		</div>

		<div class="announcement">
			<div class="announcementTitle">
				<h2>공지사항</h2>
				<a href="/board/list">>></a>
			</div>
			<div class="line"></div>
			<div>
				<ul class="announcementList">
					<c:forEach var="i" begin="0" end="4">
						<li>${allPosts[i].title}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="introduce">
		<div class="linkBox">
			<div class="introduceImg">
				<img src="resources/images/소개사진1.jpg">
			</div>
			<div class="introduceTitle">
				<p>대한적십자 기관소개</p>
				<img src="resources/images/right.png">
			</div>
		</div>
		<div class="linkBox">
			<div class="introduceImg">
				<img src="resources/images/소개사진2.jpg">
			</div>
			<div class="introduceTitle">
				<p>적십자운동 기본원칙</p>
				<img src="resources/images/right.png">
			</div>
		</div>
		<div class="linkBox">
			<div class="introduceImg">
				<img src="resources/images/소개사진3.jpg">
			</div>
			<div class="introduceTitle">
				<p>대학적십자사 사업소개</p>
				<img src="resources/images/right.png">
			</div>
		</div>
	</div>


</div>