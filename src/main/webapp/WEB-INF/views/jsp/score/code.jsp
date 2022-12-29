<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/code.css"/>

<div class="titleBox">
	<img src="resources/images/selection.png">
	<h2>코드관리</h2>
</div>
	<div id="twocodedivs">
		<div class="codediv">
			<form>
				<div class="codedetailtopdiv">
					<div class="codenodiv">
						그룹코드 <input type="text" id="groupCode">
					</div>
					<div class="codenamediv">
						그룹코드명 <input type="text" id="groupContent">
					</div>
				</div>
				<div class="codedetailbottomdiv">
					<div class="occupieddiv">사용여부 
						<select name="occupied">
							<option value="all">전체
							<option value="y">사용중
							<option value="n">사용안함
						</select>
					</div>
					<div class="buttonsdiv">
						<button>추가</button>
						<button>삭제</button>
						<button>저장</button>
					</div>
				</div>
			</form>
			<hr>
			<table class="codetable">
			<tr>
				<th>번호</th>
				<th>그룹코드</th>
				<th>그룹코드명</th>
				<th>사용여부</th>
			</tr>
			<tr>
				<td>1</td>
				<td>HI</td>
				<td>위생관리</td>
				<td>Y</td>
			</tr>
			<tr>
				<td>2</td>
				<td>DT</td>
				<td>업무</td>
				<td>Y</td>
			</tr>
			<tr>
				<td>3</td>
				<td>BM</td>
				<td>혈액관리</td>
				<td>Y</td>
			</tr>
			<tr>
				<td>4</td>
				<td>FM</td>
				<td>재무관리</td>
				<td>Y</td>
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
				<div class="codedetailbottomdiv">
					<div class="occupieddiv">사용여부 
						<select name="occupied">
							<option value="all">전체
							<option value="y">사용중
							<option value="n">사용안함
						</select>
					</div>
					<div class="buttonsdiv">
						<button>추가</button>
						<button>삭제</button>
						<button>저장</button>
					</div>
				</div>
			</form>
			<hr>
			<table class="codetable">
			<tr>
				<th>번호</th>
				<th>그룹코드</th>
				<th>그룹코드명</th>
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