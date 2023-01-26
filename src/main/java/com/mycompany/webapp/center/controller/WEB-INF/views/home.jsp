<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="resources/css/home.css">
<div class="bigGroupBox">
	<div class="smallGroupBox">
		<div class="slidebox">
			<!-- input 한개가 한개의 슬라이드 스크린 -->
			<!-- checked: 슬라이드 첫 화면 설정 -->
			<input type="radio" name="slide" id="slide1" checked> <input
				type="radio" name="slide" id="slide2"> <input type="radio"
				name="slide" id="slide3"> <input type="radio" name="slide"
				id="slide4">
			<ul class="slidelist">
				<!--li는 장면의 갯수이므로 input 갯수랑 같게 만들어줌 -->
				<li class="slideitem">
					<div>
						<!-- 왼쪽 화살표-->
						<label for="slide4" class="left"></label>
						<!-- 오른쪽 화살표-->
						<label for="slide2" class="right"></label> <a><img
							src="resources/images/center/양산센터-1.jpg"></a>
					</div>
				</li>
				<li class="slideitem">
					<div>
						<label for="slide1" class="left"></label> <label for="slide3"
							class="right"></label> <a><img
							src="resources/images/center/송촌센터-1.jpg"></a>
					</div>
				</li>
				<li class="slideitem">
					<div>
						<label for="slide2" class="left"></label> <label for="slide4"
							class="right"></label> <a><img
							src="resources/images/center/함월센터-1.jpg"></a>
					</div>
				</li>
				<li class="slideitem">
					<div>
						<label for="slide3" class="left"></label> <label for="slide1"
							class="right"></label> <a><img
							src="resources/images/center/송촌센터-2.jpg"></a>
					</div>
				</li>
			</ul>
		</div>

		<div class="announcement">
			<h2>공지사항</h2>
			<a href="/board/list">>></a>
			<div class="line"></div>
			<div>
				<ul class="announcementList">
					<li>[전국]2023년 대한적십자사 회장 신년사</li>
					<li>[전국]혈액관리본부 홈페이지 서비스 신규 오픈 안내</li>
					<li>[전국]다회헌혈자 블러드도너 컬렉션 수여 안내</li>
					<li>[전국]대한적십자사 헌혈유공패(장) 수여기준 변경 안내</li>
					<li>[전국]코로나19 관련 헌혈금지기간 안내</li>
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