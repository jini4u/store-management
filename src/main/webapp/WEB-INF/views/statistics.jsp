<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/statistics.css"/>
<div class="titleBox">
<img src="resources/images/trend.png">
<h2>통계보기</h2>
</div>
<div id="buttonsdiv">
	<button class="menubtn center">센터별</button><button class="menubtn manager">담당자별</button><button class="menubtn code">점수항목별</button><button class="menubtn">TOP3</button>
</div>
<div id="statisticcontentdiv">
	<div id="searchlistdiv">
		<div class="search-box">
			<input type="text" class="search-txt" name="" placeholder="검색">
			<a class="search-btn" href="#"> <i class="fas fa-search"
				aria-hidden="true"></i>
			</a>
		</div>
		<table id="centerlisttable" border="1" class="center table">
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
			<tr>
				<td class="listitem">진접센터</td>
			</tr>
			<tr>
				<td class="listitem">당고개센터</td>
			</tr>
			<tr>
				<td class="listitem">마석센터</td>
			</tr>
			<tr>
				<td class="listitem">동래센터</td>
			</tr>
		</table>
		
		<table id="managerlisttable" border="1" class="manager table">
			<tr>
				<td class="listitem">이소정</td>
			</tr>
			<tr>
				<td class="listitem">고은별</td>
			</tr>
			<tr>
				<td class="listitem">정윤선</td>
			</tr>
			<tr>
				<td class="listitem">임유진</td>
			</tr>
			<tr>
				<td class="listitem">이정림</td>
			</tr>
			<tr>
				<td class="listitem">정가영</td>
			</tr>
			<tr>
				<td class="listitem">김영서</td>
			</tr>
			<tr>
				<td class="listitem">최혜민</td>
			</tr>
			<tr>
				<td class="listitem">한혜원</td>
			</tr>
			<tr>
				<td class="listitem">김나영</td>
			</tr>
			<tr>
				<td class="listitem">이영진</td>
			</tr>
		</table>
		
		<table id="codelisttable" border="1" class="code table">
			<tr>
				<td class="listitem">센터 내부 위생관리</td>
			</tr>
			<tr>
				<td class="listitem">의료폐기용품 관리</td>
			</tr>
			<tr>
				<td class="listitem">의료기구 관리</td>
			</tr>
			<tr>
				<td class="listitem">개인위생 관리</td>
			</tr>
			<tr>
				<td class="listitem">문서보안 관리</td>
			</tr>
			<tr>
				<td class="listitem">채혈 및 간호 업무</td>
			</tr>
			<tr>
				<td class="listitem">프로모션 이행</td>
			</tr>
			<tr>
				<td class="listitem">헌혈유보군 관리</td>
			</tr>
			<tr>
				<td class="listitem">헌혈 혈액 상태 관리</td>
			</tr>
			<tr>
				<td class="listitem">채혈 기구 예산안 적정성</td>
			</tr>
			<tr>
				<td class="listitem">채혈자 기념품 예산안 적정성</td>
			</tr>
		</table>
	</div>
	<div id="rightdiv">

		<div id="graphdiv">
			<h2><p id="centername">신촌센터</p> <p id="statisticcontent">평균 점수 추이</p></h2>
			<img src="resources/images/graph.jpg" id="graphIMG">
			<img src="resources/images/blueGraph.jpg" id="blueIMG">
			<img src="resources/images/codeGraph.jpg" id="codeIMG">
		</div>
		<div id="infodiv">
			<div>
			<p class="title-p">전체 센터 수</p>
		 	<p class="colored-p">127</p>
		 	</div>
		 	<div>
		 	<p class="title-p">전체 센터 평균 점수</p>
		 	<p class="colored-p">68.9</p>
		 	</div>
		 	<div>
		 	<p class="title-p">선택 센터 1년 평균 점수</p>
		 	<p class="colored-p">73.5</p>
		 	</div>
		</div>
		
	</div>
</div>
<script src="resources/js/statistics.js"></script>