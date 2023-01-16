var menu = document.getElementsByClassName("menubtn");
var list = document.getElementsByClassName("listitem");
var centerTb = document.getElementById("centerlisttable");
var managerTb = document.getElementById("managerlisttable");
var codeTb = document.getElementById("codelisttable");
var graphTitle = document.getElementById("graphtitle");
var graphContainer = document.getElementById("container");

//메뉴 버튼 클릭시 
function menuClick(event){
	for(var i=0;i<menu.length;i++){
		menu[i].classList.remove("clicked");
		graphTitle.style.display = 'none';
		graphContainer.style.display = 'none';
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
	}
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
	    }
	  },
	  plotOptions: {
	    line: {
	      dataLabels: {
	        enabled: false
	      },
	      enableMouseTracking: true
	    }
	  },
	  series: [{
	    name: '전체 센터 평균',
	    data: entireAvgArr
	  }, {
	    name: '선택 센터 평균',
	    data: centerAvgArr
	  }]
	});

}

function makeCenterGraph(){
	let response = JSON.parse(httpRequest.responseText);
	
	var entireVOArr = response.entireAvg;
	var centerVOArr = response.centerAvg;
	categories = [];
	entireAvgArr = [];
	centerAvgArr = [];
	
	for(var i=0;i<entireVOArr.length;i++){
		categories.push(entireVOArr[i].checkYear+'/'+entireVOArr[i].checkSeason);
		entireAvgArr.push(entireVOArr[i].checkScore);
		for(var j=0;j<centerVOArr.length;j++){
			var matched = false;
			if(entireVOArr[i].checkYear==centerVOArr[j].checkYear && entireVOArr[i].checkSeason==centerVOArr[j].checkSeason){
				centerAvgArr.push(centerVOArr[j].checkScore);
				matched = true;
				break;
			}
		}
		if(matched == false){
			centerAvgArr.push(0);
		}
	}
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