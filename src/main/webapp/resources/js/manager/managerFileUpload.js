/*엑셀 등록 유효성 검사*/
let checkFileForm = /(.*?)\.(xlsx|xls|XLSX|XLS)$/;

function mgrExcelUploadCheck() {
	//파일 선택 안했을때
	if($("#mgrFileInput").val() == ""){
		$("#invalid-mgrFile").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>파일을 선택해 주세요.</p>");
		$("#invalid-mgrFile").show();
		$("#mgrExcelInsertBtn").attr("disabled", true);
		
		return false;
	}else{
	//파일 선택 했을때
		//파일 형식이 일치하지 않을때
		if(!$("#mgrFileInput").val().match(checkFileForm)){
			$("#invalid-mgrFile").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>XLSX, XLS 파일만 업로드 가능합니다</p>");
			$("#invalid-mgrFile").show();
			$("#mgrExcelInsertBtn").attr("disabled", true);
			return false;
		}else{
		//파일 형식이 일치할때
			$("#mgrExcelInsertBtn").attr("disabled", false);
			return true;
		}
	}
};

$("#mgrFileInput").change(function(){
	$("#invalid-mgrFile").empty();
	mgrExcelUploadCheck();
});

function checkFile(){
	if(mgrExcelUploadCheck()){
		loadingMask();
		setTimeout(function(){
			return true;
		}, 50);
	} else {
		return false;
	}
}

