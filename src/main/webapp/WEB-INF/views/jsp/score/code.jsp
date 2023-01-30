<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/score/code.css"/>

<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/selection.png">
	<h2>코드관리</h2>
</div>

	<div id="twocodedivs">
		<div class="codediv">
			<form id="groupform">
				<div id="groupinputs" class="codedetailtopdiv">
					<div class="codenodiv">
						그룹코드 <input type="text" name="groupCode" id="groupcode" value="" readonly>
					</div>
					<div class="codenamediv">
						그룹코드명 <input type="text" name="groupContent" id="groupcontent" value="">
					</div>
				</div>
					<div id="groupselectdiv" class="occupieddiv">사용여부 
						<select name="groupOccupied">
							<option value="all">전체
							<option value="y">사용중
							<option value="n">사용안함
						</select>
					</div>
			<hr>
					<div class="buttonsdiv">
						<button type="button" id="insertgroup" class="pinkButton">추가</button>
						<button type="button" id="savegroup" class="pinkButton">저장</button>
					</div>
			</form>
			<table id="grouptable" class="codetable verticalTable">
			<thead>
			<tr>
				<th>번호</th>
				<th>그룹코드</th>
				<th>그룹코드명</th>
				<th>사용여부</th>
			</tr>	
			</thead>
			<tbody>
			</tbody>	
			</table>
		</div>
		
		<hr class="verticalhr">
		
		<div class="codediv">
			<form id="detailform">
				<div class="codedetailtopdiv">
					<div class="codenodiv">
						상세코드 <input type="text" name="detailCode" id="detailcode" value="" readonly>
					</div>
					<div class="codenamediv">
						상세코드명 <input type="text" name="detailContent" id="detailcontent" value="">
					</div>
				</div>
					<div class="occupieddiv">사용여부 
						<select name="detailOccupied">
							<option value="all">전체
							<option value="y">사용중
							<option value="n">사용안함
						</select>
					</div>
			<hr>
					<div class="buttonsdiv">
						<button type="button" id="insertdetail" class="pinkButton">추가</button>
						<button type="button" id="savedetail" class="pinkButton">저장</button>
					</div>
			</form>
			<table id="detailtable" class="codetable verticalTable">
			<thead>
			<tr>
				<th>번호</th>
				<th>상세코드</th>
				<th id="detailcontentth">상세코드명</th>
				<th>사용여부</th>
			</tr>
			</thead>
			<tbody>
			</tbody>
			</table>
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/score/code.js"></script>