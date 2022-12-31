//그룹코드 테이블 tr 선택자 
var groupcodetr = document.querySelectorAll("#grouptable tr");
//상세코드 테이블 선택자 
var detailtable = document.getElementById("detailtable");
//그룹코드 입력창 선택자 
var groupinputs = document.querySelectorAll("#groupinputs input");
//그룹코드 select 선택자 
var groupselect = document.querySelector("#groupselectdiv select")

//th 부분 제외하고 클릭이벤트 달아주는 함수. 근데 tr로 선택해서 이벤트를 달았는데 td로도 들어감... 뭐지
function addEvent(){
	for(var i=1;i<groupcodetr.length;i++){
		groupcodetr[i].addEventListener("click", appearance);
	}
}

//클릭했을때 상세코드 테이블 나오도록, 그룹코드 내용 알맞게 들어가도록
function appearance(event){
	//클릭된 객체의 groupcode-** 클래스 읽어오기
	var groupCode = event.target.classList[0];
	//해당 클래스로 객체들 선택
	var selectByGroupCode = document.querySelectorAll("."+groupCode);

	//테이블 보이도록
	detailtable.style.display = "revert";
	
	//그룹코드, 그룹코드명에 내용 넣어주기
	groupinputs[0].value = selectByGroupCode[1].innerText;
	groupinputs[1].value = selectByGroupCode[2].innerText;
	
	//사용중 여부에 맞춰 selected 되도록
	for(var i=1;i<groupselect.length;i++){
		if(groupselect[i].value == selectByGroupCode[3].innerText.toLowerCase()){
			groupselect[i].selected = true;
		}
	}
}

//처음에 상세테이블 안보이게 하기
detailtable.style.display = "none";
//클릭이벤트 달아주기
addEvent();