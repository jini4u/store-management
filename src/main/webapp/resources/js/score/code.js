//그룹코드 테이블 tr 선택자 
var groupcodetr = document.querySelectorAll("#grouptable tr");
//상세코드 테이블 선택자 
var detailtable = document.getElementById("detailtable");
//그룹코드 입력창 선택자  > 두개를 분리해서 각각 id selector 를 사용하는것이 좋음.
var groupcodeinput = document.getElementById("groupCode");
var groupcontentinput = document.getElementById("groupContent");
//그룹코드 select 선택자 
var groupselect = document.querySelector('select[name="groupoccupied"]');
//상세코드 입력창 선택자
var detailcodeinput = document.getElementById("detailCode");
var detailcontentinput = document.getElementById("detailContent");
//상세코드 select 선택자
var detailselect = document.querySelector('select[name="detailoccupied"]');

//th 부분 제외하고 tr에 클릭이벤트 달아주는 함수
function addEvent(){
	//선택한 td(event.target)의 부모(.parentElement)에서 attribute 얻어와 컨트롤 해보는게 더 이벤트가 간단
	for(var i=1;i<groupcodetr.length;i++){
		groupcodetr[i].addEventListener("click", function(event){
			//혹시나 나중을 위해 캐싱
			var self = this;
			var groupTextArr = self.innerText.split("\t");
			
			//그룹코드, 그룹코드명에 내용 넣어주기
			groupcodeinput.value = groupTextArr[1];
			groupcontentinput.value = groupTextArr[2];
			
			//사용중 여부에 맞춰 selected 되도록
			for(var i=1;i<groupselect.length;i++){
				if(groupselect[i].value == groupTextArr[3].toLowerCase()){
					groupselect[i].selected = true;
				}
			}
			
			//상세 테이블에 있던 내용 지우기
			detailtable.tBodies[0].innerHTML = '';
			//ajax 요청해서 상세코드 내용 채우기
			makeRequest(groupTextArr[1]);
		});
	}
	
	//상세코드 테이블 클릭 이벤트 등록
	detailtable.addEventListener("click", function(event){
		//event.target: 클릭된 td, .parentElement: 선택된 td의 부모 객체 (=tr), .innerText: 텍스트 내용, .split("\t"): \t 기준으로 split 
		var detailTextArr = event.target.parentElement.innerText.split("\t");
		//상세코드, 상세코드명에 내용 넣어주기
		detailcodeinput.value = detailTextArr[1];
		detailcontentinput.value = detailTextArr[2];
		
		//사용중 여부에 맞춰 selected 되도록
		for(var i=1;i<detailselect.length;i++){
			if(detailselect[i].value == detailTextArr[3].toLowerCase()){
				detailselect[i].selected = true;
			}
		}
	});
}

//ajax 통신하는 함수
function makeRequest(groupCode){
	//HTTP request 기능 제공하는 Object 생성
	httpRequest = new XMLHttpRequest();
	if(!httpRequest){
		alert('XMLHTTP 인스턴스 만들기 실패');
		return false;
	}
	//요청에 대한 상태가 변화할 때(응답 받은 후에) getDetailCodes 함수를 부른다
	httpRequest.onreadystatechange = getDetailCodes;
	//.open, .send로 요청하기
	//.open(request method, URL, [true | false]) : 세번째 파라미터는 비동기성. 기본값은 true
	httpRequest.open('GET', '/getDetailCodes/'+groupCode);
	httpRequest.send();
}

function getDetailCodes(){
	//DONE: 이상 없음, 응답 받았음
	if(httpRequest.readyState === XMLHttpRequest.DONE){
		//200: OK, 이상없음
		if(httpRequest.status === 200){
			//httpRequest.responseText: 서버의 응답을 텍스트 문자열로 반환
			var detailJSON = JSON.parse(httpRequest.responseText);
			//반환된 상세코드 수만큼 반복
			for(var i=0;i<detailJSON.length;i++){
				//상세코드 tbody안에 번호, 상세코드, 상세코드명, 사용여부 추가하기
				detailtable.tBodies[0].innerHTML += "<tr><td>"+(i+1)+"</td><td>"+detailJSON[i].CHECK_DETAIL_CODE+"</td><td>"+detailJSON[i].CHECK_DETAIL_CONTENT+"</td><td>"+detailJSON[i].CHECK_DETAIL_OCCUPIED+"</td></tr>";
			}
		} else {
			//처리하는 과정에서 문제 발생. 404, 500 등
			alert('request에 문제 발생');
		}
	}
}
//클릭이벤트 달아주기
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