//-----------------파일 등록 유효성 검사----------------------------
var fileForm = /(.*?)\.(xlsx|xls)$/;

function excelCenterSubmit() {
	if ($("#centerInputExcelFile").val() == "") {
		$("#centerExcelFile").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>파일을 선택해 주세요</p>");
		$("#centerExcelFile").show();
		$("#centerExcelInsertBtn").attr("disabled", true);
	}else{
		if (!$("#centerInputExcelFile").val().match(fileForm)) {
			$("#centerExcelFile").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>xlsx, xls 파일만 업로드 가능합니다</p>");
			$("#centerExcelFile").show();
			$("#centerExcelInsertBtn").attr("disabled", true);
		}else{
		$("#invalid-centerPhoto").hide();
		$("#centerExcelInsertBtn").attr("disabled", false);
		}
	}
};

$("#centerInputExcelFile").change(function (){
	$("#centerExcelFile").empty();
	excelCenterSubmit();
});
$("#centerExcelInsertBtn").click(function(){
	loadingMask();
	setTimeout(function(){
		$("#centerExcelFile").empty();
		excelCenterSubmit();		
	}, 50);
});

