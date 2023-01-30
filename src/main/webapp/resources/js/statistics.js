//메뉴 버튼 선택자 (센터별, 담당자별, 점수항목별)
var menu = document.getElementsByClassName("menubtn");
//테이블 안 항목 선택자
var list = document.getElementsByClassName("listitem");
//센터별, 담당자별, 점수항목별 메뉴 테이블 가진 div 선택자
var centerTb = document.querySelectorAll(".tablediv")[0];
var managerTb = document.querySelectorAll(".tablediv")[1];
var codeTb = document.querySelectorAll(".tablediv")[2];
//그래프 위 타이틀 선택자
var graphTitle = document.getElementById("graphtitle");
//그래프 속한 div 선택자
var graphContainer = document.getElementById("container");
//검색 input 선택자
var keyword = document.getElementById("searchinput");
//검색 버튼 선택자
var searchBtn = document.querySelector("a[class='search-btn']");
//서브 통계 제목들 선택자
var titleP = document.querySelectorAll(".title-p");
//서브 통계 내용들 선택자
var colorP = document.querySelectorAll(".colored-p");

//메뉴 버튼 클릭시 등록될 함수
function menuClick(event){

	//검색창 비우기
	keyword.value='';
	//선택되어있엇던 메뉴 해제, 그래프 숨기기
	for(var i=0;i<menu.length;i++){
		menu[i].classList.remove("clicked");
		graphTitle.style.display = 'none';
		graphContainer.style.display = 'none';
	}
	//리스트 항목들 다 보이게, 선택상태는 해제
	for(var j=0;j<list.length;j++){
		list[j].parentElement.style.display = 'block';
		list[j].classList.remove("selected");
	}
	//서브 통계 내용 비우기
	for(var i=0;i<colorP.length;i++){
		colorP[i].innerText = '';
	}
	//클릭한 메뉴에 clicked 클래스 추가
	event.target.classList.add("clicked");
	//현재 선택된 버튼 클래스에서 어떤 메뉴인지 얻어오기 (center, manager, code)
	let nowMenu = event.target.classList[1];
	//선택된 메뉴에 따라 다른 동작
	if(nowMenu == 'center'){	//센터별
		//센터 테이블만 보이게
		centerTb.style.display = 'block';
		managerTb.style.display = 'none';
		codeTb.style.display = 'none';
		//서브통계 항목 변경
		titleP[0].innerText = '최고 점수 항목';
		titleP[1].innerText = '최저 점수 항목';
		titleP[2].innerText = '해당 분기 담당자';
	} else if(nowMenu == 'manager'){	//담당자별
		//담당자 테이블만 보이게
		centerTb.style.display = 'none';
		managerTb.style.display = 'block';
		codeTb.style.display = 'none';
		//서브 통계 항목 변경
		titleP[0].innerText = '최고 점수 항목';
		titleP[1].innerText = '우수 담당자';
		titleP[2].innerText = '해당 분기 신입 사원 수';
	} else if(nowMenu == 'code'){	//점수항목별
		//점수항목 테이블만 보이게
		centerTb.style.display = 'none';
		managerTb.style.display = 'none';
		codeTb.style.display = 'block';
		//서브 통계 항목 변경
		titleP[0].innerText = '최고 점수 센터';
		titleP[1].innerText = '최고 평균 점수 항목';
		titleP[2].innerText = '최저 평균 점수 항목';
	}
}	//메뉴버튼 클릭 시행 함수 끝

//검색 버튼에 클릭 이벤트 등록
searchBtn.addEventListener("click", function(){
	//검색창 내용
	let keywordValue = keyword.value;
	//현재 선택된 메뉴 (center, manager, code)
	let nowMenu = document.querySelector(".clicked").classList[1];
	//현재 보여지고있는 테이블 선택자
	let nowTable = document.getElementById(nowMenu+"listtable");
	//리스트 항목들 중 검색 내용을 포함한것만 보이도록
	for(var i=0;i<list.length;i++){
		//전부 보이도록 함
		list[i].parentElement.style.display = 'block';
		//현재 보여지는 테이블 안에있는 항목들이면
		if(list[i].closest("table") == nowTable){
			//항목 내용 얻어오기
			var str = list[i].innerText;
			//항목의 내용이 검색 키워드를 포함하지 않으면
			if(str.indexOf(keywordValue) == -1){
				//보이지 않게 하기
				list[i].parentElement.style.display = 'none';
			}
		}
	}
});	//검색창 클릭 이벤트 등록 함수 끝

//리스트 항목 클릭시 등록될 함수
function listClick(event){
	//테이블 안 항목들 선택된 상태 해제
	for(var i=0;i<list.length;i++){
		list[i].classList.remove("selected");
	}
	//현재 선택된 항목에만 selected 클래스 추가 (선택된 상태로 만들기)
	event.target.classList.add("selected");
	//선택한 항목 내용 얻어오기
	var selected = event.target.innerText;
	//그래프 제목 안 이름 바뀌는 부분에 대입시켜줌
	document.getElementById("itemname").innerText = selected;
	//그래프 제목 보이도록
	graphTitle.style.display = 'block';
	//그래프 보이도록
	graphContainer.style.display = 'block';
	//현재 선택된 버튼 클래스에서 어떤 메뉴인지 얻어오기 (center, manager, code)
	let clickedMenu = document.querySelector(".clicked").classList[1];
	//선택된 메뉴에 따라 다른 동작
	if(clickedMenu == 'center'){	//센터별
		//선택한 항목 id에서 센터코드 얻어오기 (center0 형태)
		centerCode = event.target.id.substr(6);
		//센터에 대한 통계 그래프 그리기 위한 정보 요청
		makeRequest(makeCenterGraph, 'GET', '/centerAvgScore/'+centerCode);
		//그래프에서 분기별 포인트 클릭시 사용될 함수, 서브통계 내용 채우기
		var makeSubStat = function(response){
			//최고점수항목
			colorP[0].innerText = response.bestCodeName;
			if(response.countBestCode > 1){
				colorP[0].innerText += ' 외 '+(response.countBestCode-1)+'개';
			}
			//최저점수항목
			colorP[1].innerText = response.worstCodeName;
			if(response.countWorstCode > 1){
				colorP[1].innerText += ' 외 '+(response.countWorstCode-1)+'개';
			}
			//해당분기담당자
			colorP[2].innerText = response.managerName;
		}
	} else if(clickedMenu == 'manager'){	//담당자별
		//선택한 항목 id에서 담당자코드 얻어오기 (manager00000 형태)
		userCode = event.target.id.substr(7);
		//담당자에 대한 통계 그래프 그리기 위한 정보 요청
		makeRequest(makeManagerGraph, 'GET', '/managerAvgScore/'+userCode);
		//그래프에서 분기별 포인트 클릭시 사용될 함수, 서브통계 내용 채우기
		var makeSubStat = function(response){
			colorP[0].innerText = response.bestCodeName;
			if(response.countBestCode > 1){
				colorP[0].innerText += ' 외 '+(response.countBestCode-1)+'개';
			}
			
			colorP[1].innerText = response.bestManager;
			if(response.countBestManager > 1){
				colorP[1].innerText += ' 외 '+(response.countBestManager-1)+'명';
			}
			
			colorP[2].innerText = response.countNewManager+'명';
		}
	} else if(clickedMenu == 'code'){	//점수항목별
		userCode = loginUserCode; //로그인 기능 되면 로그인한 담당자 번호로 바꾸기 
		//선택한 항목 id에서 점수 그룹코드, 상세코드 얻어와 분리 (codeXX.00 형태)
		code = event.target.id.substr(4).split('.');
		//그룹코드
		groupCode = code[0];
		//상세코드
		detailCode = code[1];
		//점수 코드에 대한 통계 그래프 그리기 위한 정보 요청
		makeRequest(makeCodeGraph, 'GET', '/codeAvgScore/'+userCode+'?group='+groupCode+'&detail='+detailCode);
		var makeSubStat = function(response){
			colorP[0].innerText = response.bestCenter;
			colorP[1].innerText = response.bestCode;
			colorP[2].innerText = response.worstCode;
		}
	}
	//차트 그리는 부분
	Highcharts.chart('container', {
	  chart: {
	    type: 'line'
	  },
	  //그래프 내 타이틀 내용 없게(사용X)
	  title: {
	    text: ''
	  },
	  subtitle: {
	    text: '마커 클릭시 그 분기에 해당하는 통계 제공'
	  },
	  //가로축에 들어갈 내용
	  xAxis: {
		//value: makeXXGraph 함수에서 지정됨
	    categories: categories
	  },
	  //세로축에 들어갈 내용
	  yAxis: {
	    title: {
	      text: '평균 점수'
	    },
		max: 100,
		min: 0
	  },
	  //그래프 내용 속성
	  plotOptions: {
	    line: {
	    	//윗부분 라벨 표시X
	      dataLabels: {
	        enabled: false
	      },
	      //마우스 위치에 맞게 데이터 표시
	      enableMouseTracking: true
	    },
	    series: {
	    	marker: {
	    		//크기
	    		radius:3
	    	},
	    	point: {
	    		events: {
	    			//클릭 이벤트 지정
	    			click: function(){
	    				//선택한 0000/00 얻어서 분리
	    				let yearSeason = this.category.split("/");
	    				//년도
	    				let checkYear = yearSeason[0];
	    				//분기
	    				let checkSeason = yearSeason[1];
	    				//서브 통계 채우기 위한 정보 요청
	    				makeRequest(function(){
	    					//응담 JSON형태로 가공
	    					let response = JSON.parse(httpRequest.responseText);
	    					//각 테이블의 항목 클릭시 지정된 makeSubStat에 전달
	    					makeSubStat(response);
	    					//subUrl: makeXXGraph 함수에서 지정됨
	    				}, 'GET', subUrl+'?year='+checkYear+'&season='+checkSeason);
	    			}
	    		}
	    	}
	    }
	  },
	  //value: makeXXGraph 함수에서 지정됨
	  series: seriesArr
	});

}

//센터 통계 그래프 그리기 위한 정보 처리 부분
function makeCenterGraph(){
	let response = JSON.parse(httpRequest.responseText);
	
	var entireVOArr = response.entireAvg;
	var centerVOArr = response.centerAvg;
	categories = [];
	entireAvgArr = [];
	itemAvgArr = [];
	
	for(var i=0;i<entireVOArr.length;i++){
		categories.push(entireVOArr[i].checkYear+'/'+entireVOArr[i].checkSeason);
		entireAvgArr.push(entireVOArr[i].checkScore);
		for(var j=0;j<centerVOArr.length;j++){
			var matched = false;
			if(entireVOArr[i].checkYear==centerVOArr[j].checkYear && entireVOArr[i].checkSeason==centerVOArr[j].checkSeason){
				itemAvgArr.push(centerVOArr[j].checkScore);
				matched = true;
				break;
			}
		}
		if(matched == false){
			itemAvgArr.push(0);
		}
	}
	
	entireStr = '전체 센터 점수 평균';
	itemStr = '선택 센터 점수 평균';
	
	seriesArr = [{
		name: itemStr,
		data: itemAvgArr
	  }, {
	    name: entireStr,
	    data: entireAvgArr
	  }];
	
	subUrl = '/centerSubStat/'+centerCode;
}

//담당자 통계 그래프 그리기 위한 정보 처리 부분
function makeManagerGraph(){
	let response = JSON.parse(httpRequest.responseText);
	
	var entireVOArr = response.entireAvg;
	var managerVOArr = response.managerAvg;
	categories = [];
	entireAvgArr = [];
	itemAvgArr = [];
	
	for(var i=0;i<entireVOArr.length;i++){
		categories.push(entireVOArr[i].checkYear+'/'+entireVOArr[i].checkSeason);
		entireAvgArr.push(entireVOArr[i].checkScore);
		for(var j=0;j<managerVOArr.length;j++){
			var matched = false;
			if(entireVOArr[i].checkYear==managerVOArr[j].checkYear && entireVOArr[i].checkSeason==managerVOArr[j].checkSeason){
				itemAvgArr.push(managerVOArr[j].checkScore);
				matched = true;
				break;
			}
		}
		if(matched == false){
			itemAvgArr.push(0);
		}
	}
	entireStr = '전체 담당자 점수 평균';
	itemStr = '선택 담당자 점수 평균';
	
	seriesArr = [{
		name: itemStr,
		data: itemAvgArr
	  }, {
		name: entireStr,
		data: entireAvgArr
	  }];
	
	subUrl = '/managerSubStat/'+userCode;
}

function makeCodeGraph(){
	let response = JSON.parse(httpRequest.responseText);
	
	categories = [];
	var entireAvgArr = [];
	seriesArr = [];
	//key값들 얻어오기 
	let keyArr = Object.keys(response);
	
	let values = Object.values(response);
	let maxLength = 0;
	for(var i=0;i<values.length;i++){
		maxLength = Math.max(maxLength,values[i].length);
	}
	
	entireAvgArr = Array(maxLength).fill(0);
	
	let categoryCheck = false;
	
	//key 갯수만큼 반복 
	for(var i=0;i<keyArr.length;i++){
		var key = keyArr[i];
		var itemAvgArr = [];
		//key에 해당하는 value 얻어오기 
		var value = response[key];
		
		if(value.length==maxLength && categoryCheck==false){
			categoryCheck = true;
			for(var j=0;j<value.length;j++){
				categories.push(value[j].checkYear+'/'+value[j].checkSeason);
			}
		}

		for(var j=0;j<maxLength;j++){
			let matched = false;
			for(var k=0;k<value.length;k++){
				if(categories[j] == value[k].checkYear+'/'+value[k].checkSeason){						
					matched = true;
					itemAvgArr.push(value[k].checkScore);
				}
			}
			if(matched == false){
				itemAvgArr.push(0);

			}
			entireAvgArr[j] += itemAvgArr[j];
		}

		seriesArr.push({name:key.substr(9), data:itemAvgArr});
	}
	for(var j=0;j<entireAvgArr.length;j++){
		entireAvgArr[j] /= keyArr.length;
	}
	seriesArr.push({type:'line', dashStyle:'LongDash', name:"담당 센터 평균", data:entireAvgArr});
	
	subUrl = '/codeSubStat/'+userCode;
}


//메뉴 버튼, 리스트 항목에 이벤트 달아줌 
function init(){	
	for(var i=0;i<menu.length;i++){
		menu[i].addEventListener("click", menuClick);
	}
	for(var i=0;i<list.length;i++){
		list[i].addEventListener("click", listClick);
	}
	managerTb.style.display = 'none';
	codeTb.style.display = 'none';
	
	menu[0].click();
}

init();

window.onload = function(){
	if(loginUserCode < 20000){	//담당자면 담당자별 통계X
		menu[1].style.display = 'none';
	} else {	//시스템이나 관리자면 점수항목별 통계X
		menu[2].style.display = 'none';
	}	
}