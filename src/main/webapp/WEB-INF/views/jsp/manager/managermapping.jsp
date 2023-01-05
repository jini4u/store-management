<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/manager/managermapping.css">
<div class="titleBox">
	<img src="resources/images/manager.png">
	<h2>담당자 맵핑</h2>
</div>
<div class="buttonBox">
	<div>
		<button type="button" class="mappingButton">맵핑</button> <button type="button" class="releaseButton">해제</button>
	</div>
</div>

<!-- 검색 -->
<div class="search-box">
	<input type="text" class="search-txt" name="" placeholder="담당자 검색">
	<a class="search-btn" href="#"> <i class="fas fa-search"></i>
	</a>
</div>

<div class="graphbox">
	<div class="managerbox">
		<p>담당자
		<table class="verticalTable" id="managertable">
			<thead>
				<tr>
					<th>담당자 코드</th>
					<th>담당자명</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${managerList}" var="manager">
					<tr>
						<td>${manager.userCode}</td>
						<td>${manager.userName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="centerbox">
		<p>센터
		<table class="verticalTable" id="centertable">
			<thead>
				<tr>
					<th></th>
					<th>센터코드</th>
					<th>센터명</th>
					<th id="address">주소</th>
				</tr>			
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>

<div class="hiddenmodal">
	<div class="modal_overlay"></div>
	<div class="modal_content">

		<!-- 모달창 안 테이블 -->
			<table class="scoretable" border="1">

			</table>
	
	
			<button type="submit" class="close-btn">입력</button>
			<button class="close-btn">취소</button>
		</form>
	</div>
</div>
<script src="resources/js/manager/mapping.js"></script>