/**
 * @code.js
 * @Description : 코드관리 js
 * @Modification 
 *     수정일      	수정자      	  	수정내용
 *  =========== =========  =====================
 * 	
 * @author 임유진
 * **/
//그룹코드 테이블 선택자
var groupTable = document.getElementById("grouptable");
//상세코드 테이블 선택자 
var detailTable = document.getElementById("detailtable");
//그룹코드 입력창 선택자  > 두개를 분리해서 각각 id selector 를 사용하는것이 좋음.
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

//각 요소에 이벤트 달아주는 함수
function addEvent(){	
	//그룹코드 테이블 클릭 이벤트 등록
	groupTable.addEventListener("click", function(event){
		//event.target: 클릭된 td, .parentElement: 선택된 td의 부모 객체 (=tr), .innerText: 텍스트 내용, .split("\t"): \t 기준으로 split 
		var groupTextArr = event.target.parentElement.innerText.split("\t");
		
		//그룹코드, 그룹코드명에 내용 넣어주기
		groupCodeInput.value = groupTextArr[1];
		groupContentInput.value = groupTextArr[2];
		
		//사용중 여부에 맞춰 selected 되도록
		for(var i=1;i<groupSelect.length;i++){
			if(groupSelect[i].value == groupTextArr[3].toLowerCase()){
				groupSelect[i].selected = true;
			}
		}
		
		//상세 테이블에 있던 내용 지우기
		detailTable.tBodies[0].innerHTML = '';
		//ajax 요청해서 상세코드 내용 채우기
		makeRequest(getDetailCodes, 'GET', '/getDetailCodes/'+groupTextArr[1]);
	});
	
	//상세코드 테이블 클릭 이벤트 등록
	detailTable.addEventListener("click", function(event){
		//event.target: 클릭된 td, .parentElement: 선택된 td의 부모 객체 (=tr), .innerText: 텍스트 내용, .split("\t"): \t 기준으로 split 
		var detailTextArr = event.target.parentElement.innerText.split("\t");
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
		var groupFormData = new FormData(groupForm);
		//POST 방식, /updateGroupCode로 groupFormData를 전송하는 요청
		makeRequest(afterSendForm, 'POST', '/updateGroupCode', groupFormData);
	});
	
	//상세코드 저장버튼 클릭 이벤트 등록
	saveDetailBtn.addEventListener("click", function(){
		//상세코드 form 데이터 가져오기
		var detailFormData = new FormData(detailForm);
		//해당 그룹코드 from 데이터에 추가
		detailFormData.append('groupCode', groupCodeInput.value);
		//POST 방식, /updateDetailCode로 detailFormData를 전송하는 요청
		makeRequest(afterSendForm, 'POST', '/updateDetailCode', detailFormData);
	});
	
} //addEvent() 끝


/**
 * ajax 통신함수
 * @param {Function} callback 함수
 * @param {String} method
 * @param {String} 요청 url
 * @param {Object} 서버로 전송할 object
 *  */
function makeRequest(getFunc, method, url, sendItem){
	//HTTP request 기능 제공하는 Object 생성
	httpRequest = new XMLHttpRequest();
	if(!httpRequest){
		alert('XMLHTTP 인스턴스 만들기 실패');
		return false;
	}
	//요청에 대한 상태가 변화할 때(응답 받은 후에) 지정 함수를 부른다
	httpRequest.onreadystatechange = function(){
		//DONE: 이상 없음, 응답 받았음
		if(httpRequest.readyState === XMLHttpRequest.DONE){
			//200: OK, 이상없음
			if(httpRequest.status === 200){
				getFunc();
			} else {
				//처리하는 과정에서 문제 발생. 404, 500 등
				alert('request에 문제 발생'+httpRequest.status);
			}
		}
	}
	//.open, .send로 요청하기
	//.open(request method, URL, [true | false]) : 세번째 파라미터는 비동기성. 기본값은 true
	httpRequest.open(method, url, false);
	httpRequest.send(sendItem); 
}

/**
 * makeRequest 콜백으로 들어갈 함수
 * 그룹 코드 정보들을 요청
 */
function getGroupCodes(){
	groupTable.tBodies[0].innerHTML = '';
	//httpRequest.responseText: 서버의 응답을 텍스트 문자열로 반환
	var groupJSON = JSON.parse(httpRequest.responseText);
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
	console.log("hihi");
	//상세 테이블에 있던 내용 지우기
	detailTable.tBodies[0].innerHTML = '';
	//httpRequest.responseText: 서버의 응답을 텍스트 문자열로 반환
	var detailJSON = JSON.parse(httpRequest.responseText);
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
		var sendFormResponse = JSON.parse(httpRequest.responseText);
		//수정인 경우
		if(sendFormResponse[0]=='updateDetail' || sendFormResponse[0]=='updateGroup'){
			//수정된 칼럼의 수가 1인 경우 (정상 수정)
			if(sendFormResponse[1] == '1'){
				//그룹코드 수정이면
				if(sendFormResponse[0] == 'updateGroup'){
					makeRequest(getGroupCodes, 'GET', '/getGroupCodes');
				}
			}
		}
	}).then( //앞부분 완료 후 동작
		makeRequest(getDetailCodes, 'GET', '/getDetailCodes/'+groupCodeInput.value)
	);
}

function init(){
	new Promise(function(){
		//ajax 요청해서 상세코드 내용 채우기
		makeRequest(getGroupCodes, 'GET', '/getGroupCodes');		
	}).then(addEvent());
}

window.onload = init;

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
			return console.warn('url은 필수값입니다');
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