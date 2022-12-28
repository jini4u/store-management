<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/centerList.css">
<h1 class="title">센터 관리</h1>
<div id="total">
	<div id="center-contentframe-left">
		<div id="search-center">
			<input type="text" placeholder="점포명">
			<button>검색</button>
		</div>
		<div id="center-content">
			<table id="table">
				<thead>
				<tr>
					<th>센터 코드</th>
					<th>센터명</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>오픈 일</th>
					<th>운영 여부</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>0000001</td>
					<td>강남역점</td>
					<td>02-xxx-xxxx</td>
					<td>서울시 강남구</td>
					<td>2022.03.15</td>
					<td>Y</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
					<td>/</td>
				</tr>
				</tbody>
			</table>
			<img src="resources/images/paging.jpg" class="paging-png">
		</div>
	</div>
	<div id="center-contentframe-right">
		<div class="button-frame">
			<button class="button">등록</button>
			<button class="button">수정</button>
			<button class="button">삭제</button>
		</div>
		<div id="center-content-detail">
			<table id="table">
				<tr>
					<th>센터 코드</th>
					<td>0000001</td>
					<th>센터명</th>
					<td>강남역점</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>02-xxx-xxxx</td>
					<th>운영여부</th>
					<td><select>
							<option>Y</option>
							<option>N</option>
					</select></td>
				</tr>
				<tr>
					<th>주소</th>
					<td>서울시 강남구</td>
					<th>오시는 길</th>
					<td>9호선 신논현 6번출구 대원빌라(라인프렌즈 건물)7층</td>
				</tr>
				<tr>
					<th>오픈 일</th>
					<td>2022.03.15</td>
					<th>폐점 일</th>
					<td><input type="date"></td>
				</tr>
			</table>
		</div>
		<div id="total-photo-frame">
			<div id="main-photo-frame">
				<p>외관</p>
				<div id="main-photo-position">
				<img src="resources/images/left-arrow.png" class="arrow"> 
				<img src="resources/images/(대구경남)경상대센터1.jpg" id="photo-outside">
				<img src="resources/images/right-arrow.png" class="arrow">
			</div>
		</div>
		<div id="mini-photo-frame">
			<input type="radio" name="slid" id="slide" >
			<input type="radio" name="slid" id="slide" >
			<input type="radio" name="slid" id="slide" >
		</div>
	</div>
</div>
</div>