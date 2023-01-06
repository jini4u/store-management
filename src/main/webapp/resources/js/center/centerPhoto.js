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
	centerNameArr = document.querySelector(".selectedtr").innerText.split("\t");
	insertModalCenterName.innerText = centerNameArr[0];
};


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
insertImgBtn.addEventListener("click",function(){
	var form = new FormData();
	form.append("centerCode", centerNameArr[4]);
	for(var i=0;i<fileList.length;i++){
		form.append(fileList[i].name, fileList[i]);		
	}
	
	
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
