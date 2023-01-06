//centerCondition 바꿔주는 자스

let getToday = function() {
	let date = new Date();
	let year = date.getFullYear();
	//month 의 경우 시작 월수가 0~11이기 때문에 +1을 해줘야 한다
	// 5일이면 05가 아닌 5로 출력이 되기 때문에 0을 앞에 붙여준 뒤 뒤에서 부터 2자리를 잘라 사용
	let month = ("0" + (1+date.getMonth())).slice(-2);
	let day = ("0" + date.getDate()).slice(-2);
	
	return new Date(year+"-"+month+"-"+day);
}


$("#centerOpeningDate").change(function() {
	let centerOpeningDate = new Date(document.querySelector("#centerOpeningDate").value);
	let centerClosingDate = document.querySelector("#centerClosingDate");
	centerClosingDate.value = '';
	
if (centerOpeningDate != '') {
	centerClosingDate.readOnly = false;
}
	else{
		centerClosingDate.readOnly = true;
	}
	
	if (centerOpeningDate <= getToday()) {
		centerCondition.value = "운영중";
	}if (centerOpeningDate > getToday())  {
		centerCondition.value = "오픈 예정";
	}

});
$("#centerClosingDate").change(function() {
	let centerCondition = document.querySelector("#centerCondition");
	let centerOpeningDate = document.querySelector("#centerOpeningDate");
	let centerClosingDate = document.querySelector("#centerClosingDate");
	if ((centerOpeningDate.value !='') && (centerClosingDate.value != '')) {
		centerCondition.value = "폐점";
	}
	if (centerOpeningDate.value < centerClosingDate.value) {
		centerClosingDate.value = '';
		centerCondition.value='';
	}
});

//centerList 불러오는 자스
const leftTable = document.querySelector("#center-left");
var leftTableTr = leftTable.rows;
for(var i=1; i<leftTableTr.length; i++) {
var row = leftTableTr[i];

row.onclick = function () {
let centerCode = document.querySelector("#centerCode");
let centerName = document.querySelector("#centerName");
let centerTel = document.querySelector("#centerTel");
let centerAddress = document.querySelector("#centerAddress");
let centerOpeningDate = document.querySelector("#centerOpeningDate");
let centerClosingDate = document.querySelector("#centerClosingDate");
let centerGuide = document.querySelector("#centerGuide");
centerCode.value = this.cells[0].innerHTML;
centerName.value = this.cells[1].innerHTML;
centerTel.value = this.cells[2].innerHTML;
centerAddress.value = this.cells[3].innerHTML;
centerOpeningDate.value = this.cells[4].innerText;
centerGuide.value = this.cells[6].textContent;
centerClosingDate.value = this.cells[7].textContent;
}
}

//ceterInsert 해주는 자스
$("#centerInsertBtn").click(function (){
	let url = "/centerInsert";
	let centername = $("#centerName").val();
	let centertel = $("#centerTel").val();
	let centeraddress = $("#centerAddress").val();
	let centerguide = $("#centerGuide").val();
	let centeropeingDate = $("#centerOpeningDate").val();
	let centerclosingDate = $("#centerClosingDate").val();
	let centercondition = $("#centerCondition").val();	
	
	
	$.ajax({
		type:"POST",
		url :url,	
		data: {
			centerName : centername,
			centerTel : centertel,
			centerAddress : centeraddress,
			centerGuide : centerguide,
			centerOpeningDate : centeropeingDate,
			centerClosingDate : centerclosingDate,
		},
		  success: function(result) {
			  
			  let results = result;	
			  let str = "<tr>";
//			  getToday함수 선언
			  $.each(results, function(i) {
				  
				  str += "<td>" + results[i].centerCode + "</td><td>" + 
				  results[i].centerName + "</td><td>" + results[i].centerTel + "</td><td>" +
				  results[i].centerAddress + "</td><td>" + results[i].centerOpeningDate +"</td><td>"
				  + centerCondition + "</td>";
				  
				  str += "</tr>";
			  });
			  $("#center-left").append(str);
			  alert("성공")
		  },
	      error: function() {
	          alert("에러 발생");
	      }
	});
});