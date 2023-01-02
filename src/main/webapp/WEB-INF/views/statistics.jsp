<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/statistics.css"/>
<div class="titleBox">
<img src="resources/images/trend.png">
<h2>통계보기</h2>
</div>
<div id="buttonsdiv">
	<button class="menubtn">센터별</button><button class="menubtn">담당자별</button><button class="menubtn">점수항목별</button><button class="menubtn">TOP3</button>
</div>
<div id="statisticcontentdiv">
	<div id="searchlistdiv">
		<div id="searchdiv">
			<img src="resources/images/search-icon.png"><input type="text" id="search">
		</div>
		<table id="listtable" border="1">
			<tr>
				<td class="listitem">중앙센터</td>
			</tr>
			<tr>
				<td class="listitem">서울역센터</td>
			</tr>
			<tr>
				<td class="listitem">대화역센터</td>
			</tr>
			<tr>
				<td class="listitem">신촌센터</td>
			</tr>
			<tr>
				<td class="listitem">연신내센터</td>
			</tr>
			<tr>
				<td class="listitem">홍대센터</td>
			</tr>
			<tr>
				<td class="listitem">구로디지털단지역센터</td>
			</tr>
		</table>
	</div>
	<div id="graphdiv">
		<h2><p id="centername">신촌센터</p> <p id="statisticcontent">평균 점수 추이</p></h2>
		<img src="resources/images/graph.jpg">
	</div>
</div>
<script src="resources/js/statistics.js"></script>