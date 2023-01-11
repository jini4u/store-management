function fn_xlsXlsxChk(fileName) {
	
	//문자열.split()메소드는 문자열을 파라미터에 적은 문자로 구분하여 배열로 저장
	let tmp =fileName.split(".");
	let extensionType =tmp[1];
	
	//extensionType에 담은 확장자가 'xls' 또는 'xlsx'이면
	//true를 리턴, 아닐 경우 false를 리턴
	if (extensionType == "xls") {
		return true;
	}else if (extensionType == "xlsx") {
		return true;
	}else {
		return false;
	}
}

function regularExpression(fileName) {
	//-,_,.,숫자, 알파벳 소문자가 아니면 체크
	let checkRegExp = /^[-_.0-9-z]*$/;
	
	//test()메소드는 주어진 문자열이 정규표현식을 만족하는 지 판별,
	//test앞에 .은 판별하고 싶은 정규표현식을 적어준다
	//허용된 범위의 문자만 사용했다면 true를 리턴, 그 외의 문자를 사용했다면 false
	if (!checkRegEXP.test(fileName)) {
		return false;
	}else {
		return true;
	}
}

function fn_chkNSubmit(){
	
	//input type="file의 value는 선택한 파일의 경로
	let filePath = $("#centerInputExcleFile").val();
	
	//.files를 사용하면 선택한 파일의 목록을 볼 수 있다, 즉 선택한 파일 0번째 이름
	//input type="file"의 파일명은 .files[0].name 메소드를 이용
	let fileName = $("#centerInputExcelFile").files[0].name;
	
	//파일명 체크 스크립트
	if (!regularExpression(fileName)) {
		alert("파일명을 확인!");
		return false;
	}
	//확장자 체크 스크립트
	if (!fn_xlsXlsxChk(fileName)) {
		alert("엑셀파일만 업로드 가능");
		return false;
	}
	//파일명을 "."으로 나눠서 확장자를 제외한 파일명만 저장
	let tmp = fileName.split(".");
	let justFileName = tmp[0];
}