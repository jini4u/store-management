<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/score/code.css"/>

<div class="titleBox">
	<img src="resources/images/selection.png">
	<h2>코드관리</h2>
</div>

	<div id="twocodedivs">
		<div class="codediv">
			<form>
				<div id="groupinputs" class="codedetailtopdiv">
					<div class="codenodiv">
						그룹코드 <input type="text" id="groupCode" value="">
					</div>
					<div class="codenamediv">
						그룹코드명 <input type="text" id="groupContent" value="">
					</div>
				</div>
					<div id="groupselectdiv" class="occupieddiv">사용여부 
						<select name="groupoccupied">
							<option value="all">전체
							<option value="y">사용중
							<option value="n">사용안함
						</select>
					</div>
			<hr>
					<div class="buttonsdiv">
						<button>추가</button>
						<button>삭제</button>
						<button>저장</button>
					</div>
			</form>
			<table id="grouptable" class="codetable">
			<tr>
				<th>번호</th>
				<th>그룹코드</th>
				<th>그룹코드명</th>
				<th>사용여부</th>
			</tr>
			<c:forEach items="${allGroupCodes}" var="groupCodeMap" varStatus="status">
				<tr>
					<td>${status.count}</td>
				<c:forEach items="${groupCodeMap}" var="groupCode">
					<td>${groupCode.value}</td>
				</c:forEach>
				</tr>
			</c:forEach>
			
			</table>
		</div>
		
		<hr class="verticalhr">
		
		<div class="codediv">
			<form>
				<div class="codedetailtopdiv">
					<div class="codenodiv">
						상세코드 <input type="text" id="detailCode" value="">
					</div>
					<div class="codenamediv">
						상세코드명 <input type="text" id="detailContent" value="">
					</div>
				</div>
					<div class="occupieddiv">사용여부 
						<select name="detailoccupied">
							<option value="all">전체
							<option value="y">사용중
							<option value="n">사용안함
						</select>
					</div>
			<hr>
					<div class="buttonsdiv">
						<button>추가</button>
						<button>삭제</button>
						<button>저장</button>
					</div>
			</form>
			<table id="detailtable" class="codetable">
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

<script src="resources/js/score/code.js"></script>