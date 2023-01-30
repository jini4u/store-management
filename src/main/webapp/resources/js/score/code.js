
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
//전체 센터 목록 표시될 테이블 tbody 선택자
var grouptableBody = document.getElementById("grouptable").tBodies[0];

function appearTable(e) {
	//테이블의 행 수-1만큼 반복
	for(var i=0;i<grouptableBody.children.length-1;i++){
		//모든 행에 selectedtr 클래스 제거
		if(grouptableBody.children[i].classList.contains("selectedtr")){
			grouptableBody.children[i].classList.remove("selectedtr");
		}
	}
	//선택된 행에 selectedtr 클래스 추가
	e.target.parentElement.classList.add("selectedtr");
}	
//그룹코드 테이블의 tr들에 클릭 이벤트 추가
function setTrEvent(){
	//마지막 줄은 페이징 처리이므로 제외(-1)
	for(var i=0;i<grouptableBody.children.length-1;i++){
		grouptableBody.children[i].addEventListener("click", appearTable);	
	}
}
setTrEvent();

//각 요소에 이벤트 달아주는 함수
function addEvent(){	
	//그룹코드 테이블 클릭 이벤트 등록
	groupTable.addEventListener("click", function(event){
		//event.target: 클릭된 td, .parentElement: 선택된 td의 부모 객체 (=tr), .innerText: 텍스트 내용, .split("\t"): \t 기준으로 split 
		let groupTextArr = event.target.parentElement.innerText.split("\t");
		
		//그룹코드, 그룹코드명에 내용 넣어주기
		groupCodeInput.value = groupTextArr[1];
		groupContentInput.value = groupTextArr[2];
		
		//사용중 여부에 맞춰 selected 되도록
		for(var i=1;i<groupSelect.length;i++){
			if(groupSelect[i].value == groupTextArr[3].toLowerCase()){
				groupSelect[i].selected = true;
			}
		}
		
		//ajax 요청해서 상세코드 내용 채우기
		makeRequest(getDetailCodes, 'GET', '/score/getDetailCodes/'+groupTextArr[1]);
	});
	
	//상세코드 테이블 클릭 이벤트 등록
	detailTable.addEventListener("click", function(event){
		//event.target: 클릭된 td, .parentElement: 선택된 td의 부모 객체 (=tr), .innerText: 텍스트 내용, .split("\t"): \t 기준으로 split 
		let detailTextArr = event.target.parentElement.innerText.split("\t");
		//상세코드, 상세코드명에 내용 넣어주기
		detailCodeInput.value = detailTextArr[1];
		detailContentInput.value = detailTextArr[2];
		
		//사용중 여부에 맞춰 selected 되도록
		for(var i=1;i<detailSelect.length;i++){
			if(detailSelect[i].value == detailTextArr[3].toLowerCase()){
				detailSelect[i].selected = true;
			}
		}
	});
	
	//그룹코드 저장버튼 클릭 이벤트 등록
	saveGroupBtn.addEventListener("click", function(){
		//그룹코드 form 데이터 가져오기
		let groupFormData = new FormData(groupForm);
		if(!groupCodeInput.hasAttribute("readonly")){
			makeRequest(afterSendForm, 'POST', '/score/insertGroupCode', groupFormData);
			groupCodeInput.setAttribute("readonly", true);
			//상세코드 칸들 비우기
			detailCodeInput.value = '';
			detailContentInput.value = '';
			detailSelect[1].selected = false;
			detailSelect[2].selected = false;
		} else {
			//POST 방식, /updateGroupCode로 groupFormData를 전송하는 요청
			makeRequest(afterSendForm, 'POST', '/score/updateGroupCode', groupFormData);			
		}
	});
	
	//상세코드 저장버튼 클릭 이벤트 등록
	saveDetailBtn.addEventListener("click", function(){
		//상세코드 form 데이터 가져오기
		let detailFormData = new FormData(detailForm);
		//해당 그룹코드 from 데이터에 추가
		detailFormData.append('groupCode', groupCodeInput.value);
		if(!detailCodeInput.hasAttribute("readonly")){
			makeRequest(afterSendForm, 'POST', '/score/insertDetailCode', detailFormData);
			detailCodeInput.setAttribute("readonly", true);
		} else {
			//POST 방식, /updateDetailCode로 detailFormData를 전송하는 요청
			makeRequest(afterSendForm, 'POST', '/score/updateDetailCode', detailFormData);			
		}
	});
	
	//상세코드 추가버튼 클릭 이벤트 등록
	insertDetailBtn.addEventListener("click", function(){
		//정보 입력될 칸들 비우기
		detailCodeInput.value = '';
		detailContentInput.value = '';
		detailSelect[1].selected = false;
		detailSelect[2].selected = false;
		//상세코드부분 입력할 수 있도록
		detailCodeInput.removeAttribute("readonly");
	});
	
	insertGroupBtn.addEventListener("click", function(){
		groupCodeInput.value = '';
		groupContentInput.value = '';
		groupSelect[1].selected = false;
		groupSelect[2].selected = false;
		
		groupCodeInput.removeAttribute("readonly");
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
		if(sendFormResponse[0]=='group' && sendFormResponse[1]=='1'){
			makeRequest(getGroupCodes, 'GET', '/score/getGroupCodes');
		}
	}).then( //앞부분 완료 후 동작
		makeRequest(getDetailCodes, 'GET', '/score/getDetailCodes/'+groupCodeInput.value)
	);
}

makeRequest(getGroupCodes, 'GET', '/score/getGroupCodes');		
addEvent();

/*****************비동기통신 참고***************************
 * Promise 써보기

function getFromServerData(){
	$.axios.post({ // ajax
		url : '',
		success : function(result){
			if(result.code == 200){
				
			}
		}
	})
	
	// 1번째 할일
	// custom ajax helper 만들기
	$ajax = function(options){
		if(options.url == null){
			return .warn('url은 필수값입니다');
		}
		// success 도 필수 체
		_options = {
		url : options.ulr,
		server :  options.server || "localhost:8080",
		success : resolve(success)
		}
		
		$.ajax(_options);
			
	}
}
 */
