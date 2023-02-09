<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/score/code.css" />

<div class="menuRoute">
	<img src="${pageContext.request.contextPath}/resources/images/home.png">
	<a href="/">&nbsp; Home &nbsp; ></a> <span>&nbsp; 점수 &nbsp; ></span> <a
		href="${pageContext.request.contextPath}/score/code">&nbsp; 코드관리</a>
</div>

<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/selection.png">
	<h2>코드관리</h2>
</div>




<div id="twocodedivs">
	<div class="codeTotalDiv">
		<p class="textStyle">그룹코드
		<div class="titleLine"></div>
		<div class="codediv">
			<form name="selectBoxCheck" id="groupform">
				<div class="inputDiv">
					<div id="groupinputs" class="codedetailtopdiv">
						<div class="alretdiv">
							<div class="codenodiv">
								그룹코드 <input type="text" class="inputEnglish" name="groupCode"
									id="groupcode" value="" 
									readonly />
							</div>
							<div id="invalid-groupcode"></div>
						</div>
						<div class="alretDiv">
							<div class="codenamediv">
								그룹코드명 <input type="text" class="contentLimit" required="required"
									name="groupContent" id="groupcontent" value="" readonly>
							</div>
							<div id="invalid-groupcontent"></div>
						</div>
					</div>
					<div id="groupselectdiv" class="occupieddiv">
						사용여부 <select name="groupOccupied" class="groupOccupied">
							<option disabled="disabled" value="y">사용중
							<option disabled="disabled" value="n">사용안함
						</select>
					</div>
					<div id="invalid-groupOccupied"></div>
				</div>
				<div class="widthLine"></div>
				<div id="code-notice">코드 등록 또는 수정 시, 정보 입력 후 저장 버튼을 눌러주세요</div>
				<div class="buttonsdiv">
					<button type="button" id="insertgroup" class="pinkButton">추가</button>
					<button type="button" id="updategroup" class="pinkButton">수정</button>
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
	</div>
	<div class="codeLine"></div>

	<div class="codeTotalDiv">
		<p>상세코드
		<div class="titleLine"></div>
		<div class="codediv">
			<form id="detailform">
				<div class="inputDiv">
					<div class="codedetailtopdiv">
					<div class="alretdiv">
						<div class="codenodiv">
							상세코드 <input type="text" class="inputNumber" name="detailCode" id="detailcode" value="" readonly>
						</div>
								<div id="invalid-detailcode"></div>
						</div>
						<div class="alretDiv">
						<div class="codenamediv">
							상세코드명 <input type="text" class="detailContentLimit" name="detailContent" id="detailcontent" value="" readonly>
						</div>
								<div id="invalid-detailcontent"></div>
					</div>
					</div>
					<div class="occupieddiv" class="occupieddiv">
						사용여부 <select name="detailOccupied" class="detailOccupied">
							<option disabled="disabled" value="y">사용중
							<option disabled="disabled" value="n">사용안함
						</select>
					</div>
						<div id="invalid-detailOccupied"></div>
				</div>
				<div class="widthLine"></div>
				<div id="code-notice">코드 등록 또는 수정 시, 정보 입력 후 저장 버튼을 눌러주세요</div>
				<div class="buttonsdiv">
					<button type="button" id="insertdetail" class="pinkButton">추가</button>
					<button type="button" id="updatedetail" class="pinkButton">수정</button>
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
</div>

<script
	src="${pageContext.request.contextPath}/resources/js/score/code.js"></script>