/**
 * @author 임유진
 * 담당자 맵핑 동적 페이지를 위한 js
 */
//담당자 테이블 선택자
var managerTable = document.getElementById("managertable");
//센터 테이블 선택자
var centerTable = document.getElementById("centertable");
//담당자 행 선택자
var allTr = document.querySelectorAll("#managertable tr");

managerTable.addEventListener("click",function(e){
	let targetTr = e.target.parentElement;
	let clickedManagerCode = targetTr.innerText.split("\t")[0];
	makeRequest(getCenterList, 'GET', '/getCenters/'+clickedManagerCode);
	for(var i=0;i<allTr.length;i++){
		if(allTr[i].classList.contains("selectedtr")){
			allTr[i].classList.remove("selectedtr");
		}
	}
	targetTr.classList.add("selectedtr");
});

//해제 버튼 선택자
var releaseBtn = document.querySelector(".releaseButton");

releaseBtn.addEventListener("click", function(){
	let userCode = document.querySelector(".selectedtr").children[0].innerText;
	let centerCode = document.querySelector("input[name='center']:checked").value;
	let reqList = {"userCode":userCode, "centerCode":centerCode};
	makeRequest(function(){	
		makeRequest(getCenterList, 'GET', '/getCenters/'+userCode);
	}, 'POST', '/cancelMapping', JSON.stringify(reqList));
})

//맵핑 버튼, 모달 선택자
var mappingBtn = document.querySelector(".mappingButton");
var modal = document.querySelector(".hiddenmodal");
var availTable = document.getElementById("availtable");

mappingBtn.addEventListener("click", function(){
	modal.classList.remove("hide");
	modal.classList.remove("fadeout")
	modal.classList.add("fadein");
	
	makeRequest(getAvailCenters,'GET','/availCenter');
});

availTable.addEventListener("click", function(e){
	let availTrList = e.target.parentElement.parentElement.children;
	for(var i=0;i<availTrList.length;i++){
		if(availTrList[i].classList.contains("selectedtr")){
			availTrList[i].classList.remove("selectedtr");
		}
		e.target.parentElement.classList.add("selectedtr");
	}
})

//닫기버튼 선택자
var closeBtn = document.querySelectorAll(".close-btn");

closeBtn[0].addEventListener("click", function(){
	modal.classList.remove("fadein");
	modal.classList.add("fadeout");
});

closeBtn[1].addEventListener("click", function(){
	modal.classList.remove("fadein");
	modal.classList.add("fadeout");
});

var mappingBtn = document.getElementById("mappingbutton");

mappingBtn.addEventListener("click", function(){
	let selectedTrList = document.querySelectorAll(".selectedtr");
	if(selectedTrList.length == 2){
		let userCode = selectedTrList[0].innerText.split("\t")[0];
		let centerCode = selectedTrList[1].innerText.split("\t")[0];
		let reqList = {"userCode":userCode, "centerCode":centerCode};
		makeRequest(function(){},'POST','/mapping',JSON.stringify(reqList));
	}
	
	selectedTrList[0].classList.remove("selectedtr");
	selectedTrList[1].classList.remove("selectedtr");
	let centerTbody = centerTable.tBodies[0];
	centerTbody.innerHTML = '';
	modal.classList.add("hide");
});

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

function getCenterList(){
	let centerTbody = centerTable.tBodies[0];
	centerTbody.innerHTML = '';
	let res = JSON.parse(httpRequest.responseText);
	for(var i=0;i<res.length;i++){
		centerTbody.innerHTML += "<tr><td><input type='checkbox' name='center' value='"+res[i].centerCode+"'></td><td>"+res[i].centerCode+"</td><td>"+res[i].centerName+"</td><td>"+res[i].centerAddress+"</td></tr>";
	}
}

function getAvailCenters(){
	let availBody = availTable.tBodies[0];
	availBody.innerHTML = '';
	let res = JSON.parse(httpRequest.responseText);
	for(var i=0;i<res.length;i++){
		availBody.innerHTML += "<tr><td>"+res[i].centerCode+"</td><td>"+res[i].centerName+"</td><td>"+res[i].centerAddress+"</td></tr>";
	}
}