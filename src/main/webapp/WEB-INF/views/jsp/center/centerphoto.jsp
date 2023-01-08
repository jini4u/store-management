<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href="resources/css/center/centerphoto.css" rel="stylesheet">
<div id="center-photo-total">
	<div id="photo-left-frame">
		<div id="center-search">
			<input type="text" placeholder="센터명" />
			<button>검색</button>
		</div>
		<div id="center-photo-first">
			<table id="centertable" class="table click verticalTable">
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
							<td>${center.centerCondition}</td>
							<td class="centercode">${center.centerCode}</td>
						</tr>
					</c:forEach>
					<tr>
				<td id="pager" colspan="4">
					<div>
						<a class="innerPager" href="centerPhoto?pageNo=1">처음</a>
						<c:if test="${pager.groupNo>1}">
							<a class="innerPager" href="centerPhoto?pageNo=${pager.startPageNo-1}">이전</a>
						</c:if>
						
						<c:forEach var="i" begin="${pager.startPageNo}" end="${pager.endPageNo}">
							<c:if test="${pager.pageNo != i}">
								<a class="innerPager" href="centerPhoto?pageNo=${i}">${i}</a>
							</c:if>
							<c:if test="${pager.pageNo == i}">
								<a class="innerPager" href="centerPhoto?pageNo=${i}">${i}</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${pager.groupNo<pager.totalGroupNo}">
							<a class="innerPager" href="centerPhoto?pageNo=${pager.endPageNo+1}">다음</a>
						</c:if>
						<a class="innerPager" href="centerPhoto?pageNo=${pager.totalPageNo}">맨끝</a>
					</div>
				</td>
			</tr>
				</tbody>
			</table>
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
				<div id="centerImagesDiv">
				<!-- 센터별 사진들 들어가야함. value에서 /image/사진이름. /image는 resources.xml에서 맵핑해둠 -->
				<img src="<spring:url value='/image/centerCode_6+originalName_IMG_0352.jpeg'/>" class="photo-img">
				</div>
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