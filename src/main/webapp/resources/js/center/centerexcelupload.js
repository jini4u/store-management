//-----------------파일 등록 유효성 검사----------------------------
var fileForm = /(.*?)\.(xlsx|xls)$/;

function excelCenterSubmit() {
	if ($("#centerInputExcelFile").val() == "") {
		$("#centerExcelFile").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>파일을 선택해 주세요</p>");
		$("#centerExcelFile").show();
		$("#centerExcelInsertBtn").attr("disabled", true);
		return false;
	}else{
		if (!$("#centerInputExcelFile").val().match(fileForm)) {
			$("#centerExcelFile").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>XLSX, XLS 파일만 업로드 가능합니다</p>");
			$("#centerExcelFile").show();
			$("#centerExcelInsertBtn").attr("disabled", true);
			return false;
		}else{
		$("#invalid-centerPhoto").hide();
		$("#centerExcelInsertBtn").attr("disabled", false);
		return true;
		}
	}
};

$("#centerInputExcelFile").change(function (){
	$("#centerExcelFile").empty();
	excelCenterSubmit();
});

function checkFile(){
	let result = excelCenterSubmit();
	if(result == true){
		loadingMask();
		setTimeout(function(){
			$("#centerExcelFile").empty();
			return result;
		}, 50);		
	} else {
		return result;
	}
}
