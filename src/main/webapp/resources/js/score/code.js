
/**
 * @code.js
 * @Description : 코드관리 js
 * @author 임유진
 * **/
//그룹코드 테이블 선택자
var groupTable = document.getElementById("grouptable");
//상세코드 테이블 선택자 
var detailTable = document.getElementById("detailtable");
//그룹코드 입력창 선택자
var groupCodeInput = document.getElementById("groupcode");
var groupContentInput = document.getElementById("groupcontent");
//그룹코드 select 선택자 
var groupSelect = document.querySelector('select[name="groupOccupied"]');
//상세코드 입력창 선택자
var detailCodeInput = document.getElementById("detailcode");
var detailContentInput = document.getElementById("detailcontent");
//상세코드 select 선택자
var detailSelect = document.querySelector('select[name="detailOccupied"]');
//그룹코드 form 선택자
var groupForm = document.getElementById("groupform");
//상세코드 form 선택자
var detailForm = document.getElementById("detailform");
//그룹코드 저장 버튼 선택자
var saveGroupBtn = document.getElementById("savegroup");
//상세코드 저장 버튼 선택자
var saveDetailBtn = document.getElementById("savedetail");
//그룹코드 추가 버튼 선택자
var insertGroupBtn = document.getElementById("insertgroup");
//상세코드 추가 버튼 선택자
var insertDetailBtn = document.getElementById("insertdetail");
//그룹코드 테이블 tbody 선택자
var grouptableBody = document.getElementById("grouptable").tBodies[0];
//상세코드 테이블 tbody 선택자
var detailtableBody = document.getElementById("detailtable").tBodies[0];
//그룹코드 수정 버튼 선택자
var updateGroupBtn = document.getElementById("updategroup");
//상세코드 수정 버튼 선택자
var updateDetailBtn = document.getElementById("updatedetail");

function appearTable(e) {
	if(e.target.closest('table').id == 'detailtable'){
		//테이블의 행 수만큼 반복
		for(var i=0;i<detailtableBody.children.length;i++){
			//모든 행에 selectedtr 클래스 제거
			if(detailtableBody.children[i].classList.contains("selectedtr")){
				detailtableBody.children[i].classList.remove("selectedtr");
			}
		}

		//상세코드명 수정불가
		detailContentInput.setAttribute("readonly", true);
		//disabled
		detailSelect[1].setAttribute('disabled', 'disabled');
		detailSelect[0].setAttribute('disabled', 'disabled');	
	} else {
		//테이블의 행 수-1만큼 반복
		for(var i=0;i<grouptableBody.children.length;i++){
			//모든 행에 selectedtr 클래스 제거
			if(grouptableBody.children[i].classList.contains("selectedtr")){
				grouptableBody.children[i].classList.remove("selectedtr");
			}
		}

		groupCodeInput.setAttribute('readonly', true);
		//그룹코드명 수정불가
		groupContentInput.setAttribute("readonly", true);
		//disabled
		groupSelect[1].setAttribute('disabled', 'disabled');
		groupSelect[0].setAttribute('disabled', 'disabled');	

		detailCodeInput.value = '';
		detailContentInput.value = '';
		detailSelect.value = '';
	}

	//선택된 행에 selectedtr 클래스 추가
	e.target.parentElement.classList.add("selectedtr");
}	
//그룹코드 테이블의 tr들에 클릭 이벤트 추가
function setTrEvent(){
	grouptableBody.addEventListener("click", appearTable);
	detailtableBody.addEventListener("click", appearTable);
}

setTrEvent();

//각 요소에 이벤트 달아주는 함수
function addEvent(){	
	//그룹코드 테이블 클릭 이벤트 등록
	grouptableBody.addEventListener("click", function(event){
		//event.target: 클릭된 td, .parentElement: 선택된 td의 부모 객체 (=tr), .innerText: 텍스트 내용, .split("\t"): \t 기준으로 split 
		let groupTextArr = event.target.parentElement.innerText.split("\t");

		//그룹코드, 그룹코드명에 내용 넣어주기
		groupCodeInput.value = groupTextArr[1];
		groupContentInput.value = groupTextArr[2];

		//사용중 여부에 맞춰 selected 되도록
		for(var i=0;i<groupSelect.length;i++){
			if(groupSelect[i].value == groupTextArr[3].toLowerCase()){
				groupSelect[i].selected = true;
			}
		}

		//ajax 요청해서 상세코드 내용 채우기
		makeRequest(getDetailCodes, 'GET', '/score/getdetailcodes/'+groupTextArr[1]);
	});

	//상세코드 테이블 클릭 이벤트 등록
	detailtableBody.addEventListener("click", function(event){
		//event.target: 클릭된 td, .parentElement: 선택된 td의 부모 객체 (=tr), .innerText: 텍스트 내용, .split("\t"): \t 기준으로 split 
		let detailTextArr = event.target.parentElement.innerText.split("\t");
		//상세코드, 상세코드명에 내용 넣어주기
		detailCodeInput.value = detailTextArr[1];
		detailContentInput.value = detailTextArr[2];

		//사용중 여부에 맞춰 selected 되도록
		for(var i=0;i<detailSelect.length;i++){
			if(detailSelect[i].value == detailTextArr[3].toLowerCase()){
				detailSelect[i].selected = true;
			}
		}
	});

	//그룹코드 저장버튼 클릭 이벤트 등록
	saveGroupBtn.addEventListener("click", function(){

		//그룹코드 form 데이터 가져오기
		if(Checkform()!=false){
			
			let groupFormData = new FormData(groupForm);
			if(!groupCodeInput.hasAttribute("readonly")){	//추가
				makeRequest(afterSendForm, 'POST', '/score/insertgroupcode', groupFormData);
				groupCodeInput.setAttribute("readonly", true);
				//상세코드 칸들 비우기
				detailCodeInput.value = '';
				detailContentInput.value = '';
				detailSelect[1].selected = false;
				detailSelect[0].selected = false;
				
				grouptableBody.lastChild.classList.add('selectedtr');
			} else if(groupContentInput.hasAttribute('readonly')){
				//잘못된 접근..
			} else{	//수정
				//POST 방식, /updateGroupCode로 groupFormData를 전송하는 요청
				makeRequest(afterSendForm, 'POST', '/score/updategroupcode', groupFormData);			
			}
			
			groupCodeInput.setAttribute('readonly', true);
			//그룹코드명 수정불가
			groupContentInput.setAttribute("readonly", true);
			//disabled
			groupSelect[1].setAttribute('disabled', 'disabled');
			groupSelect[0].setAttribute('disabled', 'disabled');
		}

	});

	//상세코드 저장버튼 클릭 이벤트 등록
	saveDetailBtn.addEventListener("click", function(){
		//상세코드 form 데이터 가져오기
		let detailFormData = new FormData(detailForm);
		//해당 그룹코드 from 데이터에 추가
		detailFormData.append('groupCode', groupCodeInput.value);
		if(!detailCodeInput.hasAttribute("readonly")){
			makeRequest(afterSendForm, 'POST', '/score/insertdetailcode', detailFormData);
			detailCodeInput.setAttribute("readonly", true);
		} else if(detailContentInput.hasAttribute('readonly')){

		} else {
			//POST 방식, /updateDetailCode로 detailFormData를 전송하는 요청
			makeRequest(afterSendForm, 'POST', '/score/updatedetailcode', detailFormData);			
		}

		//상세코드명 수정불가
		detailContentInput.setAttribute("readonly", true);
		//disabled
		detailSelect[1].setAttribute('disabled', 'disabled');
		detailSelect[0].setAttribute('disabled', 'disabled');	
	});

	//상세코드 추가버튼 클릭 이벤트 등록
	insertDetailBtn.addEventListener("click", function(){
		for(var i=0;i<detailtableBody.children.length;i++){
			//모든 행에 selectedtr 클래스 제거
			detailtableBody.children[i].classList.remove("selectedtr");
		}

		//정보 입력될 칸들 비우기
		detailCodeInput.value = '';
		detailContentInput.value = '';
		detailSelect[1].selected = false;
		detailSelect[0].selected = false;
		//상세코드부분 입력할 수 있도록
		detailCodeInput.removeAttribute("readonly");
		//상세코드명 수정가능
		detailContentInput.removeAttribute("readonly");
		//disabled 풀어주기
		detailSelect[1].removeAttribute('disabled');
		detailSelect[0].removeAttribute('disabled');
	});

	//그룹코드 추가버튼 클릭 이벤트 등록
	insertGroupBtn.addEventListener("click", function(){
		for(var i=0;i<grouptableBody.children.length;i++){
			//모든 행에 selectedtr 클래스 제거
			grouptableBody.children[i].classList.remove("selectedtr");
		}

		groupCodeInput.value = '';
		groupContentInput.value = '';
		groupSelect[1].selected = false;
		groupSelect[0].selected = false;

		groupCodeInput.removeAttribute("readonly");
		//그룹코드명 수정가능
		groupContentInput.removeAttribute("readonly");
		//disabled 풀어주기
		groupSelect[1].removeAttribute('disabled');
		groupSelect[0].removeAttribute('disabled');	
	});

	//상세코드 수정 버튼 클릭 이벤트 등록
	updateDetailBtn.addEventListener("click", function(){
		if(detailCodeInput.value!='' && detailCodeInput.value!=null){
			//상세코드명 수정가능
			detailContentInput.removeAttribute("readonly");

			if(groupSelect.value == 'y'){
				//disabled 풀어주기
				detailSelect[1].removeAttribute('disabled');
				detailSelect[0].removeAttribute('disabled');							
			}
		}
	});

	//그룹코드 수정 버튼 클릭 이벤트 등록
	updateGroupBtn.addEventListener("click", function(){
		if(groupCodeInput.value!='' && groupCodeInput.value!=null){
			//그룹코드명 수정가능
			groupContentInput.removeAttribute("readonly");
			//disabled 풀어주기
			groupSelect[1].removeAttribute('disabled');
			groupSelect[0].removeAttribute('disabled');			
		}
	});

} //addEvent() 끝

/**
 * makeRequest 콜백으로 들어갈 함수
 * 그룹 코드 정보들을 요청
 */
function getGroupCodes(){
	groupTable.tBodies[0].innerHTML = '';
	//httpRequest.responseText: 서버의 응답을 텍스트 문자열로 반환
	let groupJSON = JSON.parse(httpRequest.responseText);
	//반환된 그룹코드 수만큼 반복
	for(var i=0;i<groupJSON.length;i++){
		//그룹코드 tbody안에 번호, 상세코드, 상세코드명, 사용여부 추가하기
		groupTable.tBodies[0].innerHTML += "<tr><td>"+(i+1)+"</td><td>"+groupJSON[i].CHECK_GROUP_CODE+"</td><td>"+groupJSON[i].CHECK_GROUP_CONTENT+"</td><td>"+groupJSON[i].CHECK_GROUP_OCCUPIED+"</td></tr>";			
	}
}

/**
 * makeRequest 콜백으로 들어갈 함수
 * 그룹에 해당하는 상세코드를 요청
 */
function getDetailCodes(){
	//상세 테이블에 있던 내용 지우기
	detailTable.tBodies[0].innerHTML = '';
	//httpRequest.responseText: 서버의 응답을 텍스트 문자열로 반환
	let detailJSON = JSON.parse(httpRequest.responseText);
	//반환된 상세코드 수만큼 반복
	for(var i=0;i<detailJSON.length;i++){
		//상세코드 tbody안에 번호, 상세코드, 상세코드명, 사용여부 추가하기
		detailTable.tBodies[0].innerHTML += "<tr><td>"+(i+1)+"</td><td>"+detailJSON[i].CHECK_DETAIL_CODE+"</td><td>"+detailJSON[i].CHECK_DETAIL_CONTENT+"</td><td>"+detailJSON[i].CHECK_DETAIL_OCCUPIED+"</td></tr>";			
	}
}

/**
 * makeRequest 콜백으로 들어갈 함수
 * 코드 수정
 */
function afterSendForm(){
	//순서대로 실행위해  Promise
	new Promise(function(){
		//응답을 문자열로 변환
		let sendFormResponse = JSON.parse(httpRequest.responseText);
		//수정된 칼럼의 수가 1인 경우 (정상 수정)
		/*if(sendFormResponse[0]=='group' && sendFormResponse[1]=='1'){
			makeRequest(getGroupCodes, 'GET', '/score/getgroupcodes');
		}
	}).then( //앞부분 완료 후 동작
			makeRequest(getDetailCodes, 'GET', '/score/getdetailcodes/'+groupCodeInput.value)
	);*/
		if(sendFormResponse[0]=='group' && sendFormResponse[1]=='1'){
			makeRequest(getGroupCodes, 'POST', '/score/getgroupcodes');
		}
	}).then( //앞부분 완료 후 동작
			makeRequest(getDetailCodes, 'POST', '/score/getdetailcodes/'+groupCodeInput.value)
			//show 반대
	);

}

makeRequest(getGroupCodes, 'GET', '/score/getgroupcodes');		
addEvent();




//값 입력 범위 지정 및 문자 입력 제한
$('.inputEnglish').keyup(function(event) {
	regexp = /[^A-Z]{3}/g;
	v = $(this).val();
	if (regexp.test(v)) {

        $("#invalid-groupName").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>대문자 2자리만 입력가능합니다</p>");
        $("#invalid-groupName").show();
        $(this).val(v.replace(regexp, ''));

    }

});

$('.contentLimit').keyup(function(event) {
	regexp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
	groupcontent = $(this).val();
	if (regexp.test(groupcontent)) {
		 $("#invalid-groupDetailName").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>특수 문자는 입력할 수 없습니다.</p>");
	     $("#invalid-groupDetailName").show();

        $(this).val(groupcontent.replace(regexp, ''));

    }
	
});
$('.detailContentLimit').keyup(function(event) {
	regexp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
	groupcontent = $(this).val();
	if (regexp.test(groupcontent)) {
		 $("#invalid-DetailName").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>특수 문자는 입력할 수 없습니다.</p>");
	     $("#invalid-DetailName").show();
        $(this).val(groupcontent.replace(regexp, ''));
        

    }
	
});
	

$('.inputNumber').keyup(function(event) {
	regexp = /[^0-9.]/g;
	groupcontent = $(this).val();
	if (regexp.test(groupcontent)) {
		 $("#invalid-detailCodeName").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>숫자만 입력할 수 있습니다.</p>");
	     $("#invalid-detailCodeName").show();
		
        $(this).val(groupcontent.replace(regexp, ''));

    }

});


function Checkform() {
	let checked = $("select[name=groupOccupied]").val();
    if(checked == null) {
    	
    	//selectBoxCheck.essential.focus();
		 $("#invalid-groupOccupied").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>운영여부를 선택해 주세요.</p>");
	     $("#invalid-groupOccupied").show();
        return false;
        
    }
    return true;
}

function Checkform() {
	let checked = $("select[name=groupOccupied]").val();
    if(checked == null) {
    	
    	//selectBoxCheck.essential.focus();
		 $("#invalid-groupOccupied").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>운영여부를 선택해 주세요.</p>");
	     $("#invalid-groupOccupied").show();
        return false;
        
    }
    return true;
}