const mainTable = document.querySelector(".click");
mainTable.onclick = disappearTable;
function disappearTable() {
	const searchTable = document.querySelector(".search");
	const searchImg = document.querySelector("#photo-right-frame");
	searchTable.style.display = "block";
	searchImg.style.display = "block";	
};


//세 버튼을 선택할수있도록 -> 리스트로 반환됨 -> foreach?로 반복하면서 click Event 달아주기
//-> click 했을때 12~14라인 작동하도록 (그럼 아마도 12~14라인을 함수로 묶어놔야겠찌?)

//querySelectorAll을 이용해 등록,수정,삭제 모든 버튼의 요소들을 가지고 온다
const buttonClick = document.querySelectorAll(".centerButton");
const closeBtn = document.querySelector("#centermodal-close-btn");
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
		closeBtn.addEventListener("click", function() {
			modalOpen.style.display ="none";
		});
	});
});


