<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="border:1px solid #222;">
	<form id="frm" method="post" enctype="multipart/form-data">
		<div style="padding:10px;">
			<div>
				<input type="file" id="centerInputExcelFile" name="centerInputExcelFile"/>
				<button type="button" id="uploadBtn">센터정보 업로드</button>
			</div>
		</div>
	</form>
</div>

<script src="resource/js/center/excelUploadCenter"></script>