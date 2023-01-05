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
			  alert("성공")
	          for(var i=0; i<result.length; i++){
	        	  $("#centerListTr").append("" +
	        	  		"<td>centercode</td>" +
	        	  		"<td>centerName</td>"+
	        	  		"<td>centertel</td>" +
	        	  		"<td>centeraddress</td>" +
	        	  		"<td>centeropeingDate</td>")
	          }
	      },
	      error: function() {
	          alert("에러 발생");
	      }
	});
});