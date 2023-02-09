<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/statistics.css" />
	
<div class="menuRoute">
		<img
		src="${pageContext.request.contextPath}/resources/images/home.png">
		<a href="/">&nbsp; Home &nbsp; ></a>
		<a href="/statistics">&nbsp; 통계</a>
</div>
	
<div class="titleBox">
	<img
		src="${pageContext.request.contextPath}/resources/images/trend.png">
	<h2>통계보기</h2>
</div>
<div id="buttonsdiv">
	<button class="menubtn center clicked">센터별</button>
	<button class="menubtn manager">담당자별</button>
	<button class="menubtn code">점수항목별</button>
</div>
<div id="statisticcontentdiv">
	<div id="infodiv">
		<div>
			<p class="title-p"></p>
			<p class="colored-p">-</p>
		</div>
		<div>
			<p class="title-p"></p>
			<p class="colored-p">-</p>
		</div>
		<div>
			<p class="title-p"></p>
			<p class="colored-p">-</p>
		</div>
	</div>
	
	<div class="line"></div>
	
	<div id="searchlistdiv">
		<div>
			<div class="search-box">
				<input type="text" class="search-txt" id="searchinput"
					placeholder="검색"> <a class="search-btn"> <i
					class="fas fa-search" aria-hidden="true"></i>
				</a>
			</div>
			<div class="tablediv">
				<table id="centerlisttable" border="1" class="center table">
					<c:forEach items="${centerList}" var="center">
						<tr>
							<td class="listitem" id="center${center.centerCode}">${center.centerName}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="tablediv">
				<table id="managerlisttable" border="1" class="manager table">
					<c:forEach items="${managerList}" var="manager">
						<tr>
							<td class="listitem" id="manager${manager.userCode}">${manager.userName}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="tablediv">
				<table id="codelisttable" border="1" class="code table">
					<c:forEach items="${codeList}" var="codeMap">
						<c:forEach items="${codeMap.detailCodes}" var="detailMap">
							<tr>
								<td class="listitem"
									id="code${codeMap.CHECK_GROUP_CODE}.${detailMap.CHECK_DETAIL_CODE}">${detailMap.CHECK_DETAIL_CONTENT}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</div>
		<div id="rightdiv">

			<div id="graphdiv">
				<h2 id="graphtitle">
					<p id="itemname"></p>
					<p id="statisticcontent">평균 점수 추이</p>
				</h2>
				<div id="container"></div>
			</div>


		</div>
	</div>
</div>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/statistics.js"></script>