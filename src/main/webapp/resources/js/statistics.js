var menu = document.getElementsByClassName("menubtn");

function handleClick(event){
	for(var i=0;i<menu.length;i++){
		menu[i].classList.remove("clicked");
	}
	event.target.classList.add("clicked");
}

function init(){
	console.log(menu.length);
	for(var i=0;i<menu.length;i++){
		menu[i].addEventListener("click", handleClick);
		console.log(i);
	}
}

init();