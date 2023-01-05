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

$("#centerInsertBtn").click(function (){
	let url = "/centerInsert";
	let centername = $("#centerName").val();
	let centertel = $("#centerTel").val();
	let centeraddress = $("#centerAddress").val();
	let centerguide = $("#centerGuide").val();
	let centeropeingDate = $("#centerOpeningDate").val();
	let centerclosingDate = $("#centerClosingDate").val();
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
			  $.each(results, function(i) {
				  
				  str += "<td>" + results[i].centerCode + "</td><td>" + 
				  results[i].centerName + "</td><td>" + results[i].centerTel + "</td><td>" +
				  results[i].centerAddress + "</td><td>" + results[i].centerGuide +"</td><td>";
//				  results[i].centerOpeningDate + "</td><td>" + results[i].centerClosingDate + "<td></td>";
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