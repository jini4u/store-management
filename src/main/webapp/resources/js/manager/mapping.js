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
//검색 버튼 선택자
var searchBtn = document.getElementById("search-btn");
//검색 input 선택자 
var keyword = document.getElementById("search-txt");

//검색버튼 클릭 이벤트 지정 
searchBtn.addEventListener("click", function(){
	let keywordValue = keyword.value;
	location.replace("/manager/managerMapping?pageNo=1&keyword="+keywordValue);
});

//담당자 칸 클릭 이벤트 지정 
managerTable.addEventListener("click",function(e){
	let targetTr = e.target.parentElement;
	let clickedManagerCode = targetTr.innerText.split("\t")[0];
	makeRequest(getCenterList, 'GET', '/manager/getCenters/'+clickedManagerCode);
	for(var i=0;i<allTr.length;i++){
		if(allTr[i].classList.contains("selectedtr")){
			allTr[i].classList.remove("selectedtr");
		}
	}
	targetTr.classList.add("selectedtr");
});

//해제 버튼 선택자
var releaseBtn = document.querySelector(".releaseButton");

//해제 버튼 클릭 이벤트 지정 
releaseBtn.addEventListener("click", function(){
	let userCode = document.querySelector(".selectedtr").children[0].innerText;
	let centerCode = document.querySelector("input[name='center']:checked").value;
	let reqList = {"userCode":userCode, "centerCode":centerCode};
	makeRequest(function(){	
		makeRequest(getCenterList, 'GET', '/manager/getCenters/'+userCode);
	}, 'POST', '/manager/cancelMapping', JSON.stringify(reqList));
})

//맵핑 버튼, 모달 선택자
var mappingBtn = document.querySelector(".mappingButton");
var modal = document.querySelector(".hiddenmodal");
//모달 안 맵핑가능센터 테이블 선택자 
var availTable = document.getElementById("availtable");

//맵핑 버튼 클릭 이벤트 지정 
mappingBtn.addEventListener("click", function(){
	modal.classList.remove("hide");
	modal.classList.remove("fadeout")
	modal.classList.add("fadein");
	
	makeRequest(getAvailCenters,'GET','/center/availCenter');
});

//맵핑 버튼 클릭시 응답 받아 맵핑가능센터 조회 이벤트 
function getAvailCenters(){
	let availBody = availTable.tBodies[0];
	availBody.innerHTML = '';
	let res = JSON.parse(httpRequest.responseText);
	for(var i=0;i<res.length;i++){
		availBody.innerHTML += "<tr><td>"+res[i].centerCode+"</td><td>"+res[i].centerName+"</td><td>"+res[i].centerAddress+"</td></tr>";
	}
}

//모달 안 페이징 숫자 선택자 
var modalPageNums = document.querySelectorAll(".modal-pagging .num");

//모달 안 페이징 숫자 선택 이벤트 
[].forEach.call(modalPageNums, function(modalPageNum){
	modalPageNum.addEventListener("click", function(e){
		let clickedNum = e.target.innerText;
		makeRequest(getAvailCenters,'GET','/center/availCenter?pageNo='+clickedNum);
	});
});

//모달 안 페이징 처음,끝 선택자 
var modalPageFirst = document.querySelector(".modal-pagging .first");
var modalPageLast = document.querySelector(".modal-pagging .last");

//모달 안 페이징 처음 선택 이벤트 
modalPageFirst.addEventListener("click", function(){
	makeRequest(getAvailCenters,'GET','/center/availCenter?pageNo=1');
});
//모달 안 페이징 끝 선택 이벤트
modalPageLast.addEventListener("click", function(){
	makeRequest(getAvailCenters,'GET','/center/availCenter?pageNo='+modalPageLast.children[0].value);
});

//맵핑가능센터 칸 클릭 이벤트 지정 
availTable.addEventListener("click", function(e){
	let availTrList = e.target.parentElement.parentElement.children;
	for(var i=0;i<availTrList.length;i++){
		if(availTrList[i].classList.contains("selectedtr")){
			availTrList[i].classList.remove("selectedtr");
		}
		e.target.parentElement.classList.add("selectedtr");
	}
})

//닫기버튼 선택자 (모달 안 저장, 취소 버튼)
var closeBtn = document.querySelectorAll(".close-btn");

//모달 안 저장버튼, 취소버튼 클릭시 모달 닫히는 이벤트 
closeBtn[0].addEventListener("click", function(){
	modal.classList.remove("fadein");
	modal.classList.add("fadeout");
});

closeBtn[1].addEventListener("click", function(){
	modal.classList.remove("fadein");
	modal.classList.add("fadeout");
});

//모달 안 저장버튼만 선택 
var mappingBtn = document.getElementById("mappingbutton");

//모달 안 저장버튼 클릭 이벤트 
mappingBtn.addEventListener("click", function(){
	let selectedTrList = document.querySelectorAll(".selectedtr");
	if(selectedTrList.length == 2){
		var userCode = selectedTrList[0].innerText.split("\t")[0];
		let centerCode = selectedTrList[1].innerText.split("\t")[0];
		let reqList = {"userCode":userCode, "centerCode":centerCode};
		makeRequest(function(){},'POST','/manager/mapping',JSON.stringify(reqList));
	}
	let centerTbody = centerTable.tBodies[0];
	centerTbody.innerHTML = '';
	makeRequest(getCenterList, 'GET', '/manager/getCenters/'+userCode);
	modal.classList.add("hide");
});

//저장버튼 클릭시 담당 센터 재조회 
function getCenterList(){
	let centerTbody = centerTable.tBodies[0];
	centerTbody.innerHTML = '';
	let res = JSON.parse(httpRequest.responseText); //응답으로 받은 문자열 형식을 json 형식으로 변환 
	for(var i=0;i<res.length;i++){
		centerTbody.innerHTML += "<tr><td><input type='checkbox' name='center' value='"+res[i].centerCode+"'></td><td>"+res[i].centerCode+"</td><td>"+res[i].centerName+"</td><td>"+res[i].centerAddress+"</td></tr>";
	}
}
