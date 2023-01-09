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

//$("#centerOpeningDate").change(function() {
//let centerOpeningDate = new Date(document.querySelector("#centerOpeningDate").value);
//let centerClosingDate = document.querySelector("#centerClosingDate");
//centerClosingDate.value = '';
//if (centerOpeningDate !== '') {
//centerClosingDate.readOnly = false;
//} else {
//centerClosingDate.readOnly = true;
//}
//if (centerOpeningDate < getToday()) {
//centerCondition.value = "o";
//}
//if (centerOpeningDate > getToday())  {
//centerCondition.value = "notyet";
//}
//$("#centerClosingDate").change(function() {
//let centerCondition = document.querySelector("#centerCondition");
//let centerClosingDate = new Date(document.querySelector("#centerClosingDate").value);
//if ((centerOpeningDate !=='') && (centerClosingDate !== '')) {
//if (centerOpeningDate <= centerClosingDate && centerClosingDate <= getToday() ) {
//centerCondition.value = "c";
//}else if (centerOpeningDate < centerClosingDate && (centerClosingDate > getToday())) {
//centerCondition.value = "notyet";
//}
//}
//if (centerOpeningDate.value > centerClosingDate.value) {
//centerClosingDate.value = '';
//centerCondition.value='';
//}
//});

//});

$("#centerOpeningDate").change(function() {
	centerClosingDate.readOnly = false;
	$("#centerClosingDate").change(function() {
		let centercode = $("centerCode").val();

		$.ajax({
			url : "/centerCondition",
			type : "post",
			data : {
				centerCode : centercode,
			},
			//계산결과 받아옴
			success : function(results){
				let centerCondition = $("#centerCondition").val(results);

				alert(centerCondition);
				alert("성공");
			},
			error : function(error) {
				alert(error)
			}
		});

	});
});



//centerList 불러오는 자스
const leftTable = document.querySelector("#center-left");
var leftTableTr = leftTable.rows;

function CallcenterList() {
	for(var i=1; i<leftTableTr.length; i++) {
		var row = leftTableTr[i];

		row.onclick = function () {
			let centercode = document.querySelector("#centerCode");
			let centername = document.querySelector("#centerName");
			let centertel = document.querySelector("#centerTel");
			let centeraddress = document.querySelector("#centerAddress");
			let centeropeningdate = document.querySelector("#centerOpeningDate");
			let centercondition = document.querySelector("#centerCondition");
			let centerclosingdate = document.querySelector("#centerClosingDate");
			let centerguide = document.querySelector("#centerGuide");


			centercode.value = this.cells[0].innerHTML;
			centername.value = this.cells[1].innerHTML;
			centertel.value = this.cells[2].innerHTML;
			centeraddress.value = this.cells[3].innerHTML;
			centeropeningdate.value = this.cells[4].innerText;
			centercondition.value = this.cells[5].innerText;
			centerguide.value = this.cells[6].textContent;
			centerclosingdate.value =this.cells[7].textContext;
			if (this.cells[7]) {
				centerclosingdate.value = this.cells[7].textContent;
			}
			console.log(centerclosingdate.value);

		}
	}
}

//ceterInsert 해주는 자스
$("#centerSavedBtn").click(function (){


	let centercode = $("#centerCode").val();
	let centername = $("#centerName").val();
	let centertel = $("#centerTel").val();
	let centeraddress = $("#centerAddress").val();
	let centerguide = $("#centerGuide").val();
	let centeropeningDate = $("#centerOpeningDate").val();
	let centerclosingDate = $("#centerClosingDate").val();


	if (centercode == $("#center-left tr").length) {
		let centercondition = $("#centerCondition").val();	
		let insertURL = "/centerInsert";

//		if ($("#centerTel").css("disabled")=="") {

		$.ajax({
			type:"POST",
			url :insertURL,	
			data: {
				centerName : centername,
				centerTel : centertel,
				centerAddress : centeraddress,
				centerGuide : centerguide,
				centerOpeningDate : centeropeningDate,
				centerClosingDate : centerclosingDate,
				centerCondition : centercondition
			},
			success: function(results) {

				$("#centerList").empty();


				let str = "<tr>";
				$.each(results, function(i) {

					if (results[i].centerOpeningDate == null) {
						results[i].centerOpeningDate ='-';
					} else {
						results[i].centerOpeningDate = results[i].centerOpeningDate.substring(0, 10);
					}

					str += "<td>" + results[i].centerCode + "</td><td>" + 
					results[i].centerName + "</td><td>" + results[i].centerTel + "</td><td>" +
					results[i].centerAddress + "</td><td>" + results[i].centerOpeningDate +"</td><td>"
					+ results[i].centerCondition + "</td><td style='display:none'>" + results[i].centerGuide + "</td><td style='display:none'>"
					+ results[i].centerClosingDate + "</td>";

					str += "</tr>";
				});
				$("#center-left").append(str);
				alert("성공")
				$("#centerForm input").val('');
				CallcenterList();
				$(".removeDisabled").attr("disabled", true);
			},

			error: function( request, status, error ){
				alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);
			}

		});


	}else{
		let updateURL = "/centerUpdate";


		let centercondition = document.querySelector("#centerCondition").value;

//		if (centercondition.value == "영업중") {
//		centercondition = "0";
//		console.log(centercondition);
//		}else if (centercondition.value == "오픈예정") {
//		centercondition = "notyet";
//		}else if (centercondition.value == "폐점") {
//		centercondition = "closed";
//		}
		$.ajax({
			type : "post",
			url : updateURL,
			data : {
				centerCode : centercode,
				centerName : centername,
				centerTel : centertel,
				centerAddress : centeraddress,
				centerOpeningDate  : centeropeningDate,
				centerCondition  : centercondition,
				cemterGuide : centerguide,
				centerClosingDate : centerclosingDate
			},
			success : function(results) {
				alert(results);
				$("#centerList").empty();


				let str = "<tr>";
				$.each(results, function(i) {

					if (results[i].centerOpeningDate == null) {
						results[i].centerOpeningDate ='-';
					} else {
						results[i].centerOpeningDate = results[i].centerOpeningDate.substring(0, 10);
					}

					str += "<td>" + results[i].centerCode + "</td><td>" + 
					results[i].centerName + "</td><td>" + results[i].centerTel + "</td><td>" +
					results[i].centerAddress + "</td><td>" + results[i].centerOpeningDate +"</td><td>"
					+ results[i].centerCondition + "</td><td style='display:none'>" + results[i].centerGuide + "</td><td style='display:none'>"
					+ results[i].centerClosingDate + "</td>";

					str += "</tr>";
				});
				$("#center-left").append(str);
				alert("성공")
				$("#centerForm input").val('');
				CallcenterList();
				$(".removeDisabled").attr("disabled", true);
			},
			error: function( request, status, error ){
				alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);
			}
		});
	}

});

//등록버튼
$("#centerInsertBtn").click(function () {

	$(".removeDisabled").attr('disabled', false);

	//테이블 행 개수 구하는 법
	let centerTrCount = $("#center-left tr").length;
	$("#centerCode").val(centerTrCount);

	//폐점일 비활성화
	if ($("#centerOpeningDate").val('')) {
		$("#centerClosingDate").attr("readonly",true);
	}


	$("#centerName").val('');
	$("#centerTel").val('');
	$("#centerCondition").val('');
	$("#centerAddress").val('');
	$("#centerGuide").val('');
	$("#centerOpeningDate").val('');
	$("#centerClosingDate").val('');

});


