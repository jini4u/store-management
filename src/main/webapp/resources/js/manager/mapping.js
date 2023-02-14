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

var urlparams = new URLSearchParams(location.search);
keyword.value = urlparams.get('keyword');

//검색버튼 클릭 이벤트 지정 
searchBtn.addEventListener("click", function(){
	let keywordValue = keyword.value;
	location.replace("/manager/managermapping?pageNo=1&keyword="+keywordValue);
});

//담당자 칸 클릭 이벤트 지정 
managerTable.tBodies[0].addEventListener("click",function(e){
	let targetTr = e.target.parentElement;
	let clickedManagerCode = targetTr.innerText.split("\t")[0];
	makeRequest(getCenterList, 'GET', '/manager/getcenters/'+clickedManagerCode);
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
	let centerCode = document.querySelectorAll("input[name='center']:checked");
	let reqList = [];
	for(var i=0;i<centerCode.length;i++){
		reqList.push({"userCode":userCode, "centerCode":centerCode[i].value});		
	}
	makeRequest(function(){	
		makeRequest(getCenterList, 'GET', '/manager/getcenters/'+userCode);
	}, 'POST', '/manager/cancelmapping', JSON.stringify(reqList));
})

//맵핑 버튼 선택자
var mappingBtn = document.querySelector(".mappingButton");
//모달 안 맵핑가능센터 테이블 선택자 
var availTable = document.getElementById("availtable");

//맵핑 버튼 클릭 이벤트 지정 
mappingBtn.addEventListener("click", function(){
	let mngSelected = false;
	for(var i=0;i<allTr.length;i++){
		if(allTr[i].classList.contains("selectedtr")){
			mngSelected = true;
		}
	}
	if(mngSelected == true){
		document.getElementById("mappingModal").classList.add("mappingModal");
		makeRequest(getAvailCenters,'GET','/center/availcenter');		
	} else {
		alert('담당자를 먼저 선택해 주세요');
		document.getElementById("mappingModal").classList.remove("mappingModal");
	}
});

//맵핑 버튼 클릭시 응답 받아 맵핑가능센터 조회 이벤트 
function getAvailCenters(){
	let availBody = availTable.tBodies[0];
	availBody.innerHTML = '';
	let res = JSON.parse(httpRequest.responseText);
	
	let pager = res[res.length-1];
	let modalPager = document.getElementById("modal-pager");
	
	modalPager.innerHTML = '<li><a class="innerPager first">처음</a></li>';
	
	for(var i=pager.startRowIndex;i<=pager.endRowIndex;i++){
		if(i == res.length-1){
			break;
		}
		availBody.innerHTML += "<tr><td>"+res[i].centerCode+"</td><td>"+res[i].centerName+"</td><td>"+res[i].centerAddress+"</td></tr>";
	}
	
	modalPager.innerHTML += '<li><a class="innerPager arrow left">이전</a></li>';

	if(pager.groupNo == 1){
		document.querySelector(".modal-pagging .left").style.display = 'none';
	}
	
	for(var i=pager.startPageNo;i<=pager.endPageNo;i++){
		if(i == pager.pageNo){
			modalPager.innerHTML += '<li><a id="now-page" class="innerPager active num">'+i+'</a></li>';
		} else {
			modalPager.innerHTML += '<li><a class="innerPager num">'+i+'</a></li>';
		}
	}
	
	if(pager.groupNo < pager.totalGroupNo){
		modalPager.innerHTML += '<li><a class="innerPager arrow right">다음</a></li>';
	}
	modalPager.innerHTML += '<li><a class="innerPager last">맨끝<input type="hidden" value="'+pager.totalPageNo+'"></a></li>'
	
	modalPageNums = document.querySelectorAll(".modal-pagging .num");

	//모달 안 페이징 숫자 선택 이벤트 
	[].forEach.call(modalPageNums, function(modalPageNum){
		modalPageNum.addEventListener("click", function(e){
			let clickedNum = e.target.innerText;
			makeRequest(getAvailCenters,'GET','/center/availcenter?pageNo='+clickedNum);
		});
	});

	modalPageFirst = document.querySelector(".modal-pagging .first");
	modalPageLast = document.querySelector(".modal-pagging .last");
	
	//모달 안 페이징 처음 선택 이벤트 
	modalPageFirst.addEventListener("click", function(){
		makeRequest(getAvailCenters,'GET','/center/availcenter?pageNo=1');
	});
	
	//모달 안 페이징 끝 선택 이벤트
	modalPageLast.addEventListener("click", function(){
		makeRequest(getAvailCenters,'GET','/center/availcenter?pageNo='+pager.totalPageNo);
	});
	
	modalPageLeft = document.querySelector('.modal-pagging .left');
	modalPageRight = document.querySelector('.modal-pagging .right');
	
	modalPageLeft.addEventListener('click', function(){
		pageNo = Number(pager.startPageNo)-1;
		makeRequest(getAvailCenters, 'GET', '/center/availcenter?pageNo='+pageNo);
	});
	
	modalPageRight.addEventListener('click', function(){
		pageNo = Number(pager.endPageNo)+1;
		makeRequest(getAvailCenters, 'GET', '/center/availcenter?pageNo='+pageNo);
	});
}

//모달 안 페이징 숫자 선택자 
var modalPageNums;


//모달 안 페이징 처음,끝 선택자 
var modalPageFirst;
var modalPageLast;


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
	makeRequest(getCenterList, 'GET', '/manager/getcenters/'+userCode);
	modal.classList.add("hide");
});

//저장버튼 클릭시 담당 센터 재조회 
function getCenterList(){
	let centerTbody = centerTable.tBodies[0];
	centerTbody.innerHTML = '';
	let res = JSON.parse(httpRequest.responseText); //응답으로 받은 문자열 형식을 json 형식으로 변환 
	if(res.length == 0){
		centerTbody.innerHTML += "<tr><td colspan='4'>담당 센터가 없습니다.</td></tr>"
	}
	for(var i=0;i<res.length;i++){
		centerTbody.innerHTML += "<tr><td><input type='checkbox' name='center' value='"+res[i].centerCode+"'></td><td>"+res[i].centerCode+"</td><td>"+res[i].centerName+"</td><td>"+res[i].centerAddress+"</td></tr>";
	}
}
