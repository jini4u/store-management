<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Latest compiled JavaScript -->
<link
	href="${pageContext.request.contextPath}/resources/css/center/centerphoto.css"
	rel="stylesheet">

<div class="menuRoute">
	<img src="${pageContext.request.contextPath}/resources/images/home.png">
	<a href="/">&nbsp; Home &nbsp; ></a> <span>&nbsp; 센터 &nbsp; ></span> <a
		href="${pageContext.request.contextPath}/center/centerphoto">&nbsp;
		센터 사진 관리</a>
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/photo.png" />
	<h2>센터 사진 관리</h2>
</div>

<div id="center-photo-total">
	<div id="photo-left-frame">

		<div class="search_insert removePadding">
			<form action="centerphoto" class="search-form">
				<div class="search-box">
					<input type="text" name="keyword" id="findCenterName"
						class="search-txt" placeholder="search" value="${keyword}">
					<button type="submit" class="search-btn" id="findCenterList">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</form>
		</div>

		<c:if test="${centerListN != 'empty' }">
			<div id="center-photo-first">
				<table id="centertable" class="table click verticalTable">
					<thead>
						<tr>
							<th class="centerPhoto-centerName">센터명</th>
							<th class="centerPhoto-thead">센터 담당자</th>
							<th class="centerPhoto-thead">전화번호</th>
							<th class="centerPhoto-thead">운영 여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${centerList}" var="center">
							<tr>
								<td class="centerPhoto-centerName">${center.centerName}</td>
								<td class="centerPhoto-thead">${center.userName}</td>
								<td class="centerPhoto-thead">${center.centerTel}</td>
								<td class="centerPhoto-thead">${center.centerCondition}</td>
								<td class="centercode">${center.centerCode}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<tr>
				<td id="pager" colspan="4">
					<div class="center-pagging" id="pagingWidth">
						<ul class="pagination">
							<li><a class="innerPager first"
								href="centerphoto?pageNo=1&keyword=${keyword}">처음</a></li>
							<li><c:if test="${pager.groupNo>1}">
									<a class="innerPager arrow left"
										href="centerphoto?pageNo=${pager.startPageNo-1}&keyword=${keyword}">이전</a>
								</c:if></li>

							<c:forEach var="i" begin="${pager.startPageNo}"
								end="${pager.endPageNo}">
								<li><c:if test="${pager.pageNo != i}">
										<a class="innerPager num"
											href="centerphoto?pageNo=${i}&keyword=${keyword}">${i}</a>
									</c:if></li>
								<li><c:if test="${pager.pageNo == i}">
										<a class="innerPager active num" id="now-page"
											href="centerphoto?pageNo=${i}&keyword=${keyword}">${i}</a>
									</c:if></li>
							</c:forEach>

							<li><c:if test="${pager.groupNo<pager.totalGroupNo}">
									<a class="innerPager arrow right"
										href="centerphoto?pageNo=${pager.endPageNo+1}&keyword=${keyword}">다음</a>
								</c:if></li>
							<li><a class="innerPager last"
								href="centerphoto?pageNo=${pager.totalPageNo}&keyword=${keyword}">맨끝</a></li>
						</ul>
					</div>
				</td>
			</tr>
		</c:if>
		<c:if test="${centerListN == 'empty' }">
		<div id="center-photo-first">
			<table id="centertable" class="table click verticalTable">
				<thead>
					<tr>
						<th class="centerPhoto-centerName">센터명</th>
						<th class="centerPhoto-thead">센터 담당자</th>
						<th class="centerPhoto-thead">전화번호</th>
						<th class="centerPhoto-thead">운영 여부</th>
					</tr>
				</thead>
				<tbody id="centerList">
					<tr>
						<td colspan="4" id="tableClickEventNo">등록된 센터가 없습니다.</td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:if>
	</div>

	<div id="photo-right-frame">
		<div id="button-frame">
			<button type="button" id="insert-center-modal" class="pinkButton"
				data-toggle="modal" data-target="#insertModal">등록</button>
			<button type="button" id="update-center-modal" class="greyButton"
				data-toggle="modal" data-target="#updateModal">수정</button>
			<button type="button" id="delete-center-modal" class="greyButton"
				data-toggle="modal" data-target="#deleteModal">삭제</button>
		</div>
		<div id="photo-frame">
			<div id="photo-main-size">
				<div id="centerImagesDiv">
					<!-- 센터별 사진들 들어가야함. src에서 /image/사진이름. /image는 resources.xml에서 맵핑해둠 -->

				</div>
			</div>
		</div>
		<div id="center-photo-second">
			<table class="search verticalTable" id="imageHistory">
				<thead>
					<tr>
						<th class="thead-centerPhoto-file">사진 이름</th>
						<th class="thead-allcenterPhoto-file" id="centerDetail">구분</th>
						<th class="thead-allcenterPhoto-file">등록자</th>
						<th class="thead-allcenterPhoto-file">등록일</th>
						<th class="thead-allcenterPhoto-file">수정일</th>
					</tr>
				</thead>
				<tbody class="noImage">
				</tbody>
			</table>
		</div>
	</div>

</div>
<!-- Modal -->
<div class="modal fade" data-backdrop="static" id="insertModal"
	role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content" id="checkForm">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">사진 등록</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close" id="centerPhoto-close">x</button>
			</div>
			<div class="modal-body">
				<form id="photoinsertform">
					<div id="centermodal-insertphoto-info">
						<table id="centermodal-insert">
							<tr>
								<th>센터명</th>
								<td id="targetCenterName"></td>
								<th id="selectSize">외관/내부</th>
								<td><select name="fileDetail">
										<option value="outside">외관</option>
										<option value="inside">내부</option>
								</select></td>
							</tr>
						</table>
						<input name='centerCode' class='centercode' value='' readonly />
					</div>
					<div id="center-search-bar">
						<input type="file" id="centerPhoto_file" name="centerImage"
							multiple>
						<div id="invalid-centerPhoto"></div>
					</div>
					<div id="centermodal-photo-list">
						<table id="centermodal-originalphoto-info" class="verticalTable">
							<thead>
								<tr>
									<th>사진 번호</th>
									<th>원본 사진명</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- 일단 고정해둠.. 로그인한 사용자걸로 바꾸기 -->
						<c:set var="titleUserCode" value='${sessionScope.userCode}' />
						<input type="text" name='uploadUserCode' id="uploadUserCode"
							value="<c:out value='${titleUserCode}'/>" />
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="centermodal-photo-insert"
					class="savebtn pinkButton">등록</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<!-- <div class="modal fade modal-show-none" id="updateModal"
	data-backdrop="static" tabindex="-1" role="dialog"
	aria-labelledby="staticBackdropLabel" aria-hidden="true"> -->
<div class="modal" id="updateModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">사진 정보 수정</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal updateModal">
					<p>사진 정보 수정</p>
					<button class="centermodal-close-btn">✖</button>
				</div>
				<div id="modal-image-update">
					<form id="imageUpdateForm">
						<table id="image-detail-table" class="verticalTable">
							<thead>
								<tr>
									<th>사진 이름</th>
									<th>사진 정보</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="button pinkButton" id="updatebutton">수정</button>
			</div>
		</div>
	</div>
</div>


<!-- Modal -->
<div class="modal fade modal-show-none" id="deleteModal"
	data-backdrop="static" tabindex="-1" role="dialog"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">사진 삭제</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- 				<div class="modal deleteModal"> -->
				<!-- 						<div class="center-modal-mainbar"> -->
				<div id="modal-image-delete">
					<table id="image-delete-table" class="verticalTable">
						<thead>
							<tr>
								<th id="deletecheck">선택</th>
								<th>사진 이름</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="button pinkButton" id="deletebutton">삭제</button>
			</div>
		</div>
	</div>
</div>

<!-- 	</div> -->
<!-- 	</div> -->
<%-- <%@ include file="../center/modalinsert.jsp"%>
<%@ include file="../center/modalupdate.jsp"%>
<%@ include file="../center/modaldelete.jsp"%> --%>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/center/centerPhoto.js"></script>