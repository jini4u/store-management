var menu = document.getElementsByClassName("menubtn");
var list = document.getElementsByClassName("listitem");
var centerTb = document.getElementById("centerlisttable");
var managerTb = document.getElementById("managerlisttable");
var codeTb = document.getElementById("codelisttable");
var img1 = document.getElementById("graphIMG");
var img2 = document.getElementById("blueIMG");
var img3 = document.getElementById("codeIMG");

function menuClick(event){
	for(var i=0;i<menu.length;i++){
		menu[i].classList.remove("clicked");
	}
	event.target.classList.add("clicked");
	if(event.target.classList[1] == 'center'){
		centerTb.style.display = 'block';
		managerTb.style.display = 'none';
		codeTb.style.display = 'none';
		
		img1.style.display = 'block';
		img2.style.display = 'none';
		img3.style.display = 'none';
	} else if(event.target.classList[1] == 'manager'){
		centerTb.style.display = 'none';
		managerTb.style.display = 'block';
		codeTb.style.display = 'none';
		
		img1.style.display = 'none';
		img2.style.display = 'block';
		img3.style.display = 'none';
	} else if(event.target.classList[1] == 'code'){
		centerTb.style.display = 'none';
		managerTb.style.display = 'none';
		codeTb.style.display = 'block';
		
		img1.style.display = 'none';
		img2.style.display = 'none';
		img3.style.display = 'block';
	}

}

function listClick(event){
	for(var i=0;i<list.length;i++){
		list[i].classList.remove("selected");
	}
	event.target.classList.add("selected");
	var selected = event.target.innerText;
	document.getElementById("centername").innerText = selected;
}

function init(){
	for(var i=0;i<menu.length;i++){
		menu[i].addEventListener("click", menuClick);
	}
	for(var i=0;i<list.length;i++){
		list[i].addEventListener("click", listClick);
	}
	managerTb.style.display = 'none';
	codeTb.style.display = 'none';
	
	img2.style.display = 'none';
	img3.style.display = 'none';
}

init();