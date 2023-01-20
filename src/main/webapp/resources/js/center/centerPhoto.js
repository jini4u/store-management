//전체 센터 목록 표시될 테이블 tbody 선택자
var centerBody = document.getElementById("centertable").tBodies[0];
//tr 선택시 해당 tr 내 정보 담을 객체 선언
var centerNameArr;

//센터 목록 테이블의 tr들에 클릭 이벤트 추가
function setTrEvent(){
	//마지막 줄은 페이징 처리이므로 제외(-1)
	for(var i=0;i<centerBody.children.length-1;i++){
		centerBody.children[i].addEventListener("click", appearTable);	
	}
}

setTrEvent();

//수정 모달 선택자
var updateModal = document.querySelector(".updateModal");
//삭제 모달 선택자
var deleteModal = document.querySelector(".deleteModal");
//삭제 모달 테이블 바디 선택자
var deleteModalBody = document.getElementById("image-delete-table").tBodies[0];

//센터 목록 테이블 클릭시 실행되는 함수
function appearTable(e) {
	//테이블의 행 수-1만큼 반복
	for(var i=0;i<centerBody.children.length-1;i++){
		//모든 행에 selectedtr 클래스 제거
		if(centerBody.children[i].classList.contains("selectedtr")){
			centerBody.children[i].classList.remove("selectedtr");
		}
	}
	
	//선택된 행에 selectedtr 클래스 추가
	e.target.parentElement.classList.add("selectedtr");
	//센터 사진 기록 테이블 선택자
	const searchTable = document.querySelector(".search");
	//센터 목록 오른쪽에 사진들 들어갈 div 선택자
	const searchImg = document.querySelector("#photo-right-frame");
	//사진 기록 테이블 화면에 표시
	searchTable.style.display = "block";
	//사진 div 화면에 표시
	searchImg.style.display = "block";	
	//등록 모달의 센터명 옆 td 선택자
	let insertModalCenterName = document.getElementById("targetCenterName");
	//센터 목록 테이블에서 선택된 행의 td들 목록 선택자
	centerNameArr = document.querySelector(".selectedtr").children;
	//등록 모달 안 input태그 선택자(숨겨둠)
	let centerCode = document.querySelector("input[name='centerCode']");
	//input의 value를 선택된 센터코드로 지정 (행의 마지막에 있음. 숨겨둠)
	centerCode.setAttribute("value", centerNameArr[4].innerText); 
	//센터명 옆 td에 센터명 지정
	insertModalCenterName.innerText = centerNameArr[0].innerText;
	
	//선택한 센터 사진들 요청하는 ajax요청
	makeRequest(getCenterImages,'GET','/center/getCenterImages/'+centerNameArr[4].innerText);
}; 

//센터 선택시 사진들이 들어갈 div 선택자
var imgDiv = document.getElementById("centerImagesDiv");
//센터 선택시 사진 등록, 수정 기록이 들어갈 테이블 선택자
var imgHistoryTable = document.getElementById("imageHistory");
//수정 모달 내부 선택한 사진 정보가 들어갈 테이블 선택자
var imgDetailTable = document.getElementById("image-detail-table");

//센터 선택시 센터의 사진들 정보 얻어오기
function getCenterImages(){
	//리턴받은 정보
	let result = JSON.parse(httpRequest.responseText);
	//사진 들어갈 div 초기화
	imgDiv.innerHTML = ''
	//응답으로 받은 사진 수 만큼 반복
	for(var i=0;i<result.length;i++){
		//<img> 생성 
		var img = document.createElement('img');
		//생성한 img에 속성 추가. src='/file/저장된사진이름'
		img.setAttribute('src', '/file/'+result[i].fileSavedName);
		//class='photo-img'
		img.setAttribute('class','photo-img');
		//data-img-name='사진이름'
		img.setAttribute('data-img-name',result[i].originalName);
		//imageDetail 값 설정위해 선언
		let imageDetail;
		//imageDetail 값 지정 위해 분기처리
		if(result[i].fileDetail == 'inside'){
			imageDetail = '내부';
		} else {
			imageDetail = '외부';
		}
		//data-img-detail='내부'또는'외부'
		img.setAttribute('data-img-detail', imageDetail);
		//data-img-fileNo='파일번호'
		img.setAttribute('data-img-fileNo',result[i].fileNo);
		
		//img에 클릭 이벤트 등록
		img.onclick = function(e) {
			//수정 모달 내부 테이블 초기화
			imgDetailTable.tBodies[0].innerHTML = '';
			//사진 정보를 담을 <tr></tr> 생성
			var imgDetailTr = document.createElement('tr');
			//사진 이름을 담을 <td></td> 생성
			var nameTd = document.createElement('td');
			//<input> 생성
			var nameInput = document.createElement('input');
			//input에 속성 추가. type='text'
			nameInput.setAttribute('type', 'text');
			//name='originalName'
			nameInput.setAttribute('name', 'newOriginalName');
			//value='클릭한 이미지의 data-img-name 속성 값'
			nameInput.setAttribute('value', e.target.getAttribute('data-img-name'));
			//nameTd에 nameInput 넣기
			nameTd.append(nameInput);
			
			//사진 상세 정보 담을 <td></td> 생성
			var detailTd = document.createElement('td');
			//내부,외부 선택가능하게 할 <select></select> 생성
			var detailSelect = document.createElement('select');
			//select에 속성 추가. name='fileDetail'
			detailSelect.setAttribute('name', 'fileDetail');
			//select에 들어갈 <option></option> 생성
			var detailInside = document.createElement('option');
			//option에 속성 추가. value='inside'
			detailInside.setAttribute('value', 'inside');
			//태그 안에 '내부' 문자열 추가
			detailInside.innerHTML = '내부';
			//select에 들어갈 <option></option> 생성
			var detailOutside = document.createElement('option');
			//option에 속성 추가. value='outside'
			detailOutside.setAttribute('value', 'outside');
			//태그 안에 '외부' 문자열 추가
			detailOutside.innerHTML = '외부';
			//select에 내부option 추가
			detailSelect.append(detailInside);
			//select에 외부option 추가
			detailSelect.append(detailOutside);
			//사진 상세 정보 td에 select 추가
			detailTd.append(detailSelect);
			
			//사진 정보  tr에 이름td 추가
			imgDetailTr.append(nameTd);
			//사진 정보 tr에 상세 정보td 추가
			imgDetailTr.append(detailTd);
			//수정 모달 사진 정보 table에 사진정보tr 추가
			imgDetailTable.tBodies[0].append(imgDetailTr);
			
			//수정 전 사진 이름을 담을 <td></td> 생성
			var oldNameTd = document.createElement('td');
			//<input> 생성
			var oldNameInput = document.createElement('input');
			//input에 속성 추가. type='text'
			oldNameInput.setAttribute('type', 'text');
			//name='originalName'
			oldNameInput.setAttribute('name', 'oldOriginalName');
			//value='클릭한 이미지의 data-img-name 속성 값'
			oldNameInput.setAttribute('value', e.target.getAttribute('data-img-name'));
			oldNameInput.setAttribute('class','hidden-file-no');
			//테이블에 nameInput 넣기
			imgDetailTable.tBodies[0].append(oldNameInput);
			
			//파일번호를 담을 <input> 생성
			var fileNoInput = document.createElement('input');
			fileNoInput.setAttribute('name', 'fileNo');
			fileNoInput.setAttribute('value', e.target.getAttribute('data-img-fileNo'));
			fileNoInput.setAttribute('class','hidden-file-no');
			
			imgDetailTable.tBodies[0].append(fileNoInput);
			
			//클릭시 화면에 보이도록
			updateModal.style.display = "block";
			//닫기 버튼들에 모달 보이지않게하는 이벤트 등록
			for(var i=0;i<closeBtns.length;i++){
				closeBtns[i].addEventListener("click", function() {
					updateModal.style.display ="none";
				});
			}
		};
		//사진 들어갈 div에 사진 넣기
		imgDiv.appendChild(img);
	}
	
	//사진 정보 기록 테이블 초기화
	imgHistoryTable.tBodies[0].innerHTML = '';
	//삭제 모달 테이블 초기화
	deleteModalBody.innerHTML = '';
	//응답 받은 사진 수만큼 반복
	for(var i=0;i<result.length;i++){
		//파일 이름, 등록자, 등록일, 수정일 넣어주기
		imgHistoryTable.tBodies[0].innerHTML += '<tr><td>'+result[i].originalName+'</td><td>'+result[i].uploadUserName+"</td><td>"+result[i].filePostDate+"</td><td>"+result[i].fileModifyDate+"</td></tr>";
		deleteModalBody.innerHTML += '<tr><td><input type="checkbox" name="deleteCheck" value="'+result[i].fileNo+'"></td><td>'+result[i].originalName+'</td></tr>';
	}
	
	
}//사진 선택 요청 시 실행되는 함수 끝

//세 버튼을 선택할수있도록 -> 리스트로 반환됨 -> foreach?로 반복하면서 click Event 달아주기
//-> click 했을때 12~14라인 작동하도록 (그럼 아마도 12~14라인을 함수로 묶어놔야겠찌?)

//querySelectorAll을 이용해 등록,수정,삭제 모든 버튼의 요소들을 가지고 온다
const buttonClick = document.querySelectorAll(".centerButton");
const closeBtns = document.querySelectorAll(".centermodal-close-btn");
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
		for(var i=0;i<closeBtns.length;i++){
			closeBtns[i].addEventListener("click", function() {
				modalOpen.style.display ="none";
			});
		}
	});
});

//등록 모달 안 사진 선택 input 선택자
var fileSelect = document.querySelector("input[name='centerImage']");
//등록 모달 안 선택한 사진 정보 테이블 선택자
var insertImgTable = document.getElementById("centermodal-originalphoto-info");
//사진 정보 테이블의 tbody 선택자
var insertImgTbody = insertImgTable.tBodies[0];
//등록 모달 파일선택 상태 변화시 이벤트 등록
fileSelect.addEventListener("change", handleFiles);
//등록 모달 파일 선택 상태 변화시 실행되는 함수
function handleFiles(){
	//선택한 파일들을 얻어오기
	fileList = this.files;
	//테이블 초기화
	insertImgTbody.innerHTML = '';
	//테이블에 얻어온 파일들 이름 넣어주기
	for(var i=0;i<fileList.length;i++){
		insertImgTbody.innerHTML += "<tr><td>"+(i+1)+"</td><td>"+fileList[i].name+"</td></tr>";
	}
}

//사진 등록 모달 안 등록 버튼 선택자
var insertImgBtn = document.getElementById("centermodal-photo-insert");
//사진 등록 모달 내부 form 선택자
var insertForm = document.getElementById("photoinsertform");
//사진 등록 모달에서 등록버튼에 클릭 이벤트 등록
insertImgBtn.addEventListener("click",function(){
	let insertFormData = new FormData(insertForm);
	makeRequest(addCenterImage, 'POST', '/center/addCenterImage', insertFormData);
});

//센터 사진 등록 ajax 요청시 실행될 함수
function addCenterImage(){
	let response = JSON.parse(httpRequest.responseText);
	console.log(response+"개 저장됨");
	modalOpen.style.display ="none";
	makeRequest(getCenterImages,'GET','/center/getCenterImages/'+centerNameArr[4].innerText);
}

//수정 모달 내부 수정 버튼
var updateBtn = document.getElementById("updatebutton");

//수정 버튼 클릭 이벤트
updateBtn.addEventListener("click", function(){
	var imgUpdateForm = document.getElementById("imageUpdateForm");
	var imgUpdateFormData = new FormData(imgUpdateForm);
	imgUpdateFormData.append("centerCode", centerNameArr[4].innerText);
	makeRequest(afterUpdateImg, 'POST', '/center/updateImage', imgUpdateFormData);
});

//수정 요청 후 실행되는 함수
function afterUpdateImg(){
	updateModal.style.display ="none";
	makeRequest(getCenterImages,'GET','/center/getCenterImages/'+centerNameArr[4].innerText);
}

//삭제 모달 내부 삭제 버튼 선택자
var deleteBtn = document.getElementById("deletebutton");

deleteBtn.addEventListener("click", function(){
	let checkbox = document.getElementsByName("deleteCheck");
	let checked = [];
	for(var i=0;i<checkbox.length;i++){
		if(checkbox[i].checked){
			checked.push(checkbox[i].value);
		}
	}
	makeRequest(afterDeleteImg, 'POST', '/center/deleteImage/'+centerNameArr[4].innerText, checked);
});

function afterDeleteImg(){
	deleteModal.style.display = "none";
	makeRequest(getCenterImages,'GET','/center/getCenterImages/'+centerNameArr[4].innerText);
}