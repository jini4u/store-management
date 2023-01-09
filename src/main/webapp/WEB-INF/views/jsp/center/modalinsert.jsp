<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal insertModal">
	<div class="modal-content">
		<div id="center-modal-total">
			<div class="center-modal-mainbar">
				<p>사진 등록</p>
				<button class="centermodal-close-btn">✖</button>
			</div>
			<div id="centermodal-insertphoto-info">
				<form id="photoinsertform">
				<table id="centermodal-insert">
					<tr>
					<th>센터명</th>
					<td id="targetCenterName"></td>
					<th>외관/내부</th>
					<td>
						<select name="fileDetail">
							<option value="outside">외관</option>
							<option value="inside">내부</option>
						</select>
					</td>
					</tr>
				</table>
				<input name='centerCode' class='centercode' value='' readonly/>
			</div>
			<div id="center-search-bar">
				<input type="file" name="centerImage" multiple>
			</div>
			<div id="centermodal-photo-list">
				<table id="centermodal-originalphoto-info">
				<thead>
					<tr>
						<th>사진 번호</th>
						<th>원본 사진명</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
				</table>
				<!-- 일단 고정해둠.. 로그인한 사용자걸로 바꾸기 -->
				<c:set var="titleUserCode" value="10001"/>
				<input type="text" name='uploadUserCode' id="uploadUserCode" value="<c:out value='${titleUserCode}'/>" />
				</form>
				<button type="button" id="centermodal-photo-insert" class="savebtn">등록</button>
			</div>
		</div>
	</div>
	<div class="modal-layer"></div>
</div>