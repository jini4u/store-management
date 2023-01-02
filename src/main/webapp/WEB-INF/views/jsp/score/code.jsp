<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<select name="occupied">
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
			<tr>
				<td class="groupcode-HI">1</td>
				<td class="groupcode-HI">HI</td>
				<td class="groupcode-HI">위생관리</td>
				<td class="groupcode-HI">Y</td>
			</tr>
			<tr>
				<td class="groupcode-DT">2</td>
				<td class="groupcode-DT">DT</td>
				<td class="groupcode-DT">업무</td>
				<td class="groupcode-DT">Y</td>
			</tr>
			<tr>
				<td class="groupcode-BM">3</td>
				<td class="groupcode-BM">BM</td>
				<td class="groupcode-BM">혈액관리</td>
				<td class="groupcode-BM">Y</td>
			</tr>
			<tr>
				<td class="groupcode-FM">4</td>
				<td class="groupcode-FM">FM</td>
				<td class="groupcode-FM">재무관리</td>
				<td class="groupcode-FM">Y</td>
			</tr>
			</table>
		</div>
		
		<hr class="verticalhr">
		
		<div class="codediv">
			<form>
				<div class="codedetailtopdiv">
					<div class="codenodiv">
						상세코드 <input type="text" id="detailCode">
					</div>
					<div class="codenamediv">
						상세코드명 <input type="text" id="detailContent">
					</div>
				</div>
					<div class="occupieddiv">사용여부 
						<select name="occupied">
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
			<tr>
				<th>번호</th>
				<th>상세코드</th>
				<th>상세코드명</th>
				<th>사용여부</th>
			</tr>
			<tr>
				<td>1</td>
				<td>01</td>
				<td>센터 내부 위생관리</td>
				<td>Y</td>
			</tr>
			<tr>
				<td>2</td>
				<td>02</td>
				<td>의료폐기용품 관리</td>
				<td>Y</td>
			</tr>
			<tr>
				<td>3</td>
				<td>03</td>
				<td>의료기구 관리</td>
				<td>Y</td>
			</tr>
			<tr>
				<td>4</td>
				<td>04</td>
				<td>개인위생 관리</td>
				<td>Y</td>
			</tr>
			</table>
	</div>
</div>

<script src="resources/js/score/code.js"></script>