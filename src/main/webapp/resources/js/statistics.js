var menu = document.getElementsByClassName("menubtn");
var list = document.getElementsByClassName("listitem");

function menuClick(event){
	for(var i=0;i<menu.length;i++){
		menu[i].classList.remove("clicked");
	}
	event.target.classList.add("clicked");
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
}

init();