<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="resources/css/center/centerphoto.css" rel="stylesheet">
<div id="center-photo-total">
	<div id="photo-left-frame">
		<div id="center-search">
			<input type="text" placeholder="센터명" />
			<button>검색</button>
		</div>
		<div id="center-photo-first">
			<table class="table click verticalTable">
				<thead>
					<tr>
						<th>센터명</th>
						<th>센터 담당자</th>
						<th>전화번호</th>
						<th>운영 여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${centerList}" var="center">
						<tr>
							<td>${center.centerName}</td>
							<td>${center.userName}</td>
							<td>${center.centerTel}</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<img src="resources/images/center/paging.jpg" class="pagging">
		</div>
		<div id="center-photo-second">
			<table class="table search verticalTable" name="search-centerList">
				<thead>
					<tr>
						<th>사진 이름</th>
						<th>센터명</th>
						<th>사진 등록자</th>
						<th>등록일</th>
						<th>수정일</th>
						<th>경로</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>(서울남부)강남센터1.jpg</td>
						<td>강남센터</td>
						<td>심수혜</td>
						<td>2000.10.15</td>
						<td>2000.10.18</td>
						<td>C:\KCC정보통신</td>
					</tr>
					<tr>
						<td>/</td>
						<td>/</td>
						<td>/</td>
						<td>/</td>
						<td>/</td>
						<td>/</td>
					</tr>
					<tr>
						<td>/</td>
						<td>/</td>
						<td>/</td>
						<td>/</td>
						<td>/</td>
						<td>/</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="photo-right-frame">
		<div id="button-frame">
			<button id="insert-center-modal" class="insert centerButton">등록</button>
			<button id="update-center-modal" class="update centerButton">수정</button>
			<button id="delete-center-modal" class="delete centerButton">삭제</button>
		</div>
		<div id="photo-frame">
			<div id="photo-main-size">
				<img src="resources/images/center/left-arrow.png" class="arrow">
				<img src="resources/images/center/(대구경남)경상대센터1.jpg" id="photo-img">
				<img src="resources/images/center/right-arrow.png" class="arrow">
			</div>
			<div id="photo-mini-size">
				<input type="radio" name="slid"> <input type="radio"
					name="slid"> <input type="radio" name="slid">
			</div>
		</div>
	</div>
</div>
<%@ include file="../center/modalinsert.jsp"%>
<%@ include file="../center/modalupdate.jsp"%>
<%@ include file="../center/modaldelete.jsp"%>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="resources/js/center/centerPhoto.js"></script>