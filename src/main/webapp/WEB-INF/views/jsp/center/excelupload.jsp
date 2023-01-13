<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<div class="titleBox">
	<img src="${pageContext.request.contextPath}/resources/images/업로드.png">
	<h2>센터 정보 일괄 등록</h2>
</div>
<div class='uploadbutton'>
<input type="text" size="20" placeholder="파일을 선택하세요">


<button>파일선택</button>
<button>업로드</button>
</div>


<table class="scoretable">
	<th>업로드 날짜</th>
	<th>업로드 한 담당자</th>
	<th>파일명</th>
	<th>구분</th>


</table>