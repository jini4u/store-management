var centerBody = document.getElementById("centertable").tBodies[0];
var centerNameArr;

function setTrEvent(){
	for(var i=0;i<centerBody.children.length-1;i++){
		centerBody.children[i].addEventListener("click", appearTable);	
	}
}

setTrEvent();

function appearTable(e) {
	for(var i=0;i<centerBody.children.length-1;i++){
		if(centerBody.children[i].classList.contains("selectedtr")){
			centerBody.children[i].classList.remove("selectedtr");
		}
	}
	e.target.parentElement.classList.add("selectedtr");
	const searchTable = document.querySelector(".search");
	const searchImg = document.querySelector("#photo-right-frame");
	searchTable.style.display = "block";
	searchImg.style.display = "block";	
	let insertModalCenterName = document.getElementById("targetCenterName");
	centerNameArr = document.querySelector(".selectedtr").children;
	let centerCode = document.querySelector("input[name='centerCode']");
	centerCode.setAttribute("value", centerNameArr[4].innerText); 
	insertModalCenterName.innerText = centerNameArr[0].innerText;
	
	makeRequest(getCenterImages,'GET','/getCenterImages/'+centerNameArr[4].innerText);
};

var imgDiv = document.getElementById("centerImagesDiv");
var imgHistoryTable = document.getElementById("imageHistory");

function getCenterImages(){
	//리턴받은 정보로 이미지 만들어주기
	let result = JSON.parse(httpRequest.responseText);
	
	imgDiv.innerHTML = ''
	for(var i=0;i<result.length;i++){
		// element 를 만들어서 넣기
		var img = document.createElement('img');
		img.setAttribute('src', '/image/'+result[i].fileSavedName);
		img.setAttribute('class','photo-img');
		imgDiv.appendChild(img);
	}
	
	imgHistoryTable.tBodies[0].innerHTML = '';
	for(var i=0;i<result.length;i++){
		imgHistoryTable.tBodies[0].innerHTML += '<tr><td>'+result[i].originalName+'</td><td>'+result[i].uploadUserName+"</td><td>"+result[i].filePostDate+"</td><td>"+result[i].fileModifyDate+"</td></tr>";
	}
}

//세 버튼을 선택할수있도록 -> 리스트로 반환됨 -> foreach?로 반복하면서 click Event 달아주기
//-> click 했을때 12~14라인 작동하도록 (그럼 아마도 12~14라인을 함수로 묶어놔야겠찌?)

//querySelectorAll을 이용해 등록,수정,삭제 모든 버튼의 요소들을 가지고 온다
const buttonClick = document.querySelectorAll(".centerButton");
const closeBtn = document.querySelector("#centermodal-close-btn");
//등록, 수정, 삭제 각각의 모달창
let modalOpen;
//forEach문을 이용해 각 요소를 하나씩 뽑아온 뒤 element에 넣어준다
buttonClick.forEach(function (element) {
	//classList[0] -> class의(첫번째로 있는) 이름을 가지고 올 수 있다
	let action = element.classList[0];
	//element에는 각 버튼의 요소가 들어있다, 즉 그냥 버튼이라고 생각해도 ok
	//click하면 modalOpen해주는 함수 실행->이 때 class이름과 Modal을
	//합쳐서 각각의 모달창을 열어줄 수 있게 된다, 
	//이제 마지막으로 각 modal창에 display를 block으로 해주면 끝
	element.addEventListener("click", function() {
		modalOpen = document.querySelector("."+action+"Modal");
		modalOpen.style.display = "block";
		closeBtn.addEventListener("click", function() {
			modalOpen.style.display ="none";
		});
	});
});


var fileSelect = document.querySelector("input[name='centerImage']");

var insertImgTable = document.getElementById("centermodal-originalphoto-info");
var insertImgTbody = insertImgTable.tBodies[0];

fileSelect.addEventListener("change", handleFiles);

function handleFiles(){
	fileList = this.files;
	insertImgTbody.innerHTML = '';
	for(var i=0;i<fileList.length;i++){
		insertImgTbody.innerHTML += "<tr><td>"+(i+1)+"</td><td>"+fileList[i].name+"</td></tr>";
	}
}

var insertImgBtn = document.getElementById("centermodal-photo-insert");
var insertForm = document.getElementById("photoinsertform");
insertImgBtn.addEventListener("click",function(){
	let insertFormData = new FormData(insertForm);
	makeRequest(addCenterImage, 'POST', '/addCenterImage', insertFormData);
});

function addCenterImage(){
	let response = JSON.parse(httpRequest.responseText);
	console.log(response+"개 저장됨");
	modalOpen.style.display ="none";
	makeRequest(getCenterImages,'GET','/getCenterImages/'+centerNameArr[4].innerText);
}