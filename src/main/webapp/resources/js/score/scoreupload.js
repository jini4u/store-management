/*
점수 엑셀파일 일괄 업로드 화면을 위한 js 파일
 */
//파일 선택하는 input태그 선택자
var fileInput = document.getElementById("file-select-button");
//선택한 파일 이름을 넣어줄 p태그 선택자
var fileNamePlace = document.getElementById("file-name");

//파일선택 input의 상태가 변할경우 이벤트 실행
fileInput.addEventListener("change", function(){
	let fileList = this.files;
	let fileType = fileList[0].type;
	if(fileType == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'){
		fileNamePlace.innerText = fileList[0].name;
	} else {
		alert('엑셀 파일을 선택해 주세요');
	}
});

//fileNamePlace, fileInput을 포함하는 form 선택자
var fileUploadForm = document.getElementById("file-upload-form");
//업로드 버튼 선택자
var fileUpload = document.getElementById("file-upload-button");

fileUpload.addEventListener("click", uploadFile);
//업로드 버튼 클릭시 실행될 함수
function uploadFile(){
	let formData = new FormData(fileUploadForm);
	makeRequest(afterUploadFile, 'POST', '/score/scorefileupload', formData);
}

function afterUploadFile(){
	location.replace(location.href);
}