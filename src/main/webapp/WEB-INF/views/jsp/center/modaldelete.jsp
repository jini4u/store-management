<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal deleteModal">
	<div class="modal-content">
		<div class="center-modal-mainbar">
				<p>사진 정보 삭제</p>
				<button class="centermodal-close-btn">✖</button>
		</div>
		<div id="modal-image-delete">
			<table id="image-delete-table" class="verticalTable">
				<thead>
					<tr>
						<th id="deletecheck">선택</th>
						<th>사진 이름</th>
					</tr>
				</thead>
					<tbody>
					</tbody>
			</table>
		</div>
		<button type="button" class="button" id="deletebutton">삭제</button>
	</div>
</div>