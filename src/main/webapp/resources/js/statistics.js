var menu = document.getElementsByClassName("menubtn");
var list = document.getElementsByClassName("listitem");

var centerTb = document.querySelectorAll(".tablediv")[0];
var managerTb = document.querySelectorAll(".tablediv")[1];
var codeTb = document.querySelectorAll(".tablediv")[2];
var graphTitle = document.getElementById("graphtitle");
var graphContainer = document.getElementById("container");
var keyword = document.getElementById("searchinput");
var searchBtn = document.querySelector("a[class='search-btn']");

//메뉴 버튼 클릭시 
function menuClick(event){
	keyword.value='';
	
	for(var i=0;i<menu.length;i++){
		menu[i].classList.remove("clicked");
		graphTitle.style.display = 'none';
		graphContainer.style.display = 'none';
	}
	for(var j=0;j<list.length;j++){
		list[j].parentElement.style.display = 'block';
	}
	
	event.target.classList.add("clicked");
	if(event.target.classList[1] == 'center'){
		centerTb.style.display = 'block';
		managerTb.style.display = 'none';
		codeTb.style.display = 'none';
	} else if(event.target.classList[1] == 'manager'){
		centerTb.style.display = 'none';
		managerTb.style.display = 'block';
		codeTb.style.display = 'none';
	} else if(event.target.classList[1] == 'code'){
		centerTb.style.display = 'none';
		managerTb.style.display = 'none';
		codeTb.style.display = 'block';
	}

}

searchBtn.addEventListener("click", function(){
	let keywordValue = keyword.value;
	let nowMenu = document.querySelector(".clicked").classList[1];
	let nowTable = document.getElementById(nowMenu+"listtable");
	for(var i=0;i<list.length;i++){
		list[i].parentElement.style.display = 'block';
		if(list[i].closest("table") == nowTable){
			var str = list[i].innerText;
			if(str.indexOf(keywordValue) == -1){
				list[i].parentElement.style.display = 'none';
			}
		}
	}
});


//리스트 항목 클릭시 
function listClick(event){
	for(var i=0;i<list.length;i++){
		list[i].classList.remove("selected");
	}
	event.target.classList.add("selected");
	var selected = event.target.innerText;
	document.getElementById("centername").innerText = selected;
	graphTitle.style.display = 'block';
	graphContainer.style.display = 'block';
	
	let clickedMenu = document.querySelector(".clicked").classList[1];
	if(clickedMenu == 'center'){
		centerCode = event.target.id.substr(6);
		makeRequest(makeCenterGraph, 'GET', '/centerAvgScore/'+centerCode);
	} else if(clickedMenu == 'manager'){
		userCode = event.target.id.substr(7);
		makeRequest(makeManagerGraph, 'GET', '/managerAvgScore/'+userCode);
	} else if(clickedMenu == 'code'){
		userCode = 10016 //로그인 기능 되면 로그인한 담당자 번호로 바꾸기 
		code = event.target.id.substr(4).split('.');
		groupCode = code[0];
		detailCode = code[1];
		makeRequest(makeCodeGraph, 'GET', '/codeAvgScore/'+userCode+'?group='+groupCode+'&detail='+detailCode);
	}
	//차트 그리는 부분
	Highcharts.chart('container', {
	  chart: {
	    type: 'line'
	  },
	  title: {
	    text: ''
	  },
	  subtitle: {
	    text: ''
	  },
	  xAxis: {
	    categories: categories
	  },
	  yAxis: {
	    title: {
	      text: '평균 점수'
	    },
		max: 100,
		min: 0
	  },
	  plotOptions: {
	    line: {
	      dataLabels: {
	        enabled: false
	      },
	      enableMouseTracking: true
	    }
	  },
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
	    name: entireStr,
	    data: entireAvgArr
	  }, {
	    name: itemStr,
	    data: itemAvgArr
	  }];
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
	    name: entireStr,
	    data: entireAvgArr
	  }, {
	    name: itemStr,
	    data: itemAvgArr
	  }];
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
			if(value[j] != undefined){
				itemAvgArr.push(value[j].checkScore);
			} else {
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
}

init();