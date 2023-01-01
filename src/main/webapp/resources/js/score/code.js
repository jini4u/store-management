//그룹코드 테이블 tr 선택자 
var groupcodetr = document.querySelectorAll("#grouptable tr");
//상세코드 테이블 선택자 
var detailtable = document.getElementById("detailtable");
//그룹코드 입력창 선택자  > 두개를 분리해서 각각 id selector 를 사용하는것이 좋음.
var groupcodeinput = document.getElementById("groupCode");
var groupcontentinput = document.getElementById("groupContent");
//그룹코드 select 선택자 
var groupselect = document.querySelector('select[name="occupied"]');

//th 부분 제외하고 tr에 클릭이벤트 달아주는 함수
function addEvent(){
	//table에 이벤트를 주고, tr에 data-**로 custom attribute 줘서 
	//선택한 td(event.target)의 부모(.parentElement)에서 attribute 얻어와 컨트롤 해보는게 더 이벤트가 간단
	for(var i=1;i<groupcodetr.length;i++){
		groupcodetr[i].addEventListener("click", function(event){
			//혹시나 나중을 위해 캐싱
			var self = this;
			var tdTextArr = self.innerText.split("\t");

			//테이블 보이도록
			detailtable.style.display = "revert";
			
			//그룹코드, 그룹코드명에 내용 넣어주기
			groupcodeinput.value = tdTextArr[1];
			groupcontentinput.value = tdTextArr[2];
			
			//사용중 여부에 맞춰 selected 되도록
			for(var i=1;i<groupselect.length;i++){
				if(groupselect[i].value == tdTextArr[3].toLowerCase()){
					groupselect[i].selected = true;
				}
			}
		});
	}
}

//처음에 상세테이블 안보이게 하기
detailtable.style.display = "none";
//클릭이벤트 달아주기
addEvent();

/*****************비동기통신 참고***************************
 * Promise 써보기
 */
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