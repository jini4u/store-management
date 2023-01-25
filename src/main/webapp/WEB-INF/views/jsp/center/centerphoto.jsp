<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script
   src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<!-- Popper JS -->
<script
   src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/css/center/centerphoto.css" rel="stylesheet">

<div id="center-photo-total">
	<div id="photo-left-frame">
		<div class="search-box">
			<input type="text" id="searchCenter" class="search-txt" name="" placeholder="검색">
			<button class="search-btn"> <i class="fas fa-search"
				aria-hidden="true"></i>
			</button>
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
			<table class="table search verticalTable" id="imageHistory">
				<thead>
					<tr>
						<th>사진 이름</th>
						<th>사진 등록자</th>
						<th>등록일</th>
						<th>수정일</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<div id="photo-right-frame">
		<div id="button-frame">
			<button id="insert-center-modal" class="insert centerButton greyButton">등록</button>
			<button id="update-center-modal" class="update centerButton greyButton">수정</button>
			<button id="delete-center-modal" class="delete centerButton greyButton">삭제</button>
		</div>
		<div id="photo-frame">
			<div id="photo-main-size">
				<img src="${pageContext.request.contextPath}/resources/images/center/left-arrow.png" class="arrow">
				<div id="centerImagesDiv">
				<!-- 센터별 사진들 들어가야함. src에서 /image/사진이름. /image는 resources.xml에서 맵핑해둠 -->
				<img src="" class="photo-img">
				</div>
				<img src="${pageContext.request.contextPath}/resources/images/center/right-arrow.png" class="arrow">
			</div>
			<div id="photo-mini-size">
				<input type="radio" name="slid"> <input type="radio"
					name="slid"> <input type="radio" name="slid">
			</div>
		</div>
	</div>
</div>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
  Launch static backdrop modal
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Understood</button>
      </div>
    </div>
  </div>
</div>
<%-- <%@ include file="../center/modalinsert.jsp"%>
<%@ include file="../center/modalupdate.jsp"%>
<%@ include file="../center/modaldelete.jsp"%> --%>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>