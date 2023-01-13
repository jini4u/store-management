let centerlist = function centerList(results){
	$("#centerList").empty();
	let str = "<tr>";
	$.each(results, function(i){
		if(results[i].centerOpeningDate == null) {
			results[i].centerOpeningDate = '-';
		}else {
			results[i].centerOpeningDate = results[i].centerOpeningDate.substring(0,10);
		}
		console.log(results[i].centerCondition);
		str += "<td>" + results[i].centerCode + "</td><td>" + results[i].centerName 
		+"</td><td>" + results[i].centerTel + "</td><td>" + results[i].centerAddress 
		+ "</td><td>" + results[i].centerOpeningDate + "</td><td>" + results[i].centerCondition 
		+ "</td><td style='display:none'>" + results[i].centerGuide 
		+ "</td><td style='display:none'>" + results[i].centerClosingDate +"</td>";

		str += "</tr>";
	});
	alert("성공");
	$("#center-left").append(str);
	$("#centerForm input").val('');
	$("#findCenterName").val('');
	CallcenterList();
}
let error = function( request, status, error ){
	alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);
};
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

//담당자 리스트 마우스 오버시 색 변화
function changeColor(){
	$('#center-left tr').mouseover(function(){
		$(this).addClass('changeColor');
	}).mouseout(function() {
		$(this).removeClass('changeColor');
	});
}
changeColor();

$("#centerOpeningDate").change(function() {
	let centerOpeningDate = new Date(document.querySelector("#centerOpeningDate").value);
	let centerClosingDate = document.querySelector("#centerClosingDate");
	let centerCondition = document.querySelector("#centerCondition");
	centerClosingDate.value = '';
	if (centerOpeningDate !== '') {
		centerClosingDate.readOnly = false;
	} else {
		centerClosingDate.readOnly = true;
	}
	if (centerOpeningDate > getToday()) {
		centerCondition.value = "notyet";
	}
	if (centerOpeningDate < getToday()) {
		centerCondition.value = "o"+"1";
	}
	$("#centerClosingDate").change(function() {
		let centerClosingDate = new Date(document.querySelector("#centerClosingDate").value);
		let closingDate = document.querySelector("#centerClosingDate");
		if (centerOpeningDate > getToday() && centerClosingDate > getToday())  {
			centerCondition.value = "o"+"1";
		}
		if ((centerOpeningDate !=='') && (centerClosingDate !== '')) {
			if (centerOpeningDate <= centerClosingDate && centerClosingDate <= getToday() ) {
				centerCondition.value = "closed";
			}else if (centerOpeningDate < getToday()  && centerOpeningDate < centerClosingDate && centerClosingDate > getToday()) {
				centerCondition.value = "o"+"-1";
			}else if (centerOpeningDate > centerClosingDate) {
				closingDate.value = '';
				centerCondition.value='';
			}
		}
	});
});




//centerList 불러오는 자스
const leftTable = document.querySelector("#center-left");
var leftTableTr = leftTable.rows;

function CallcenterList() {
	for(var i=1; i<leftTableTr.length; i++) {
		var row = leftTableTr[i];

		row.onclick = function () {
			$("#centerName").attr("readonly", true);
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
			if (this.cells[5].innerText == '폐점') {
				centercondition.value = 'closed';
			}else if(this.cells[5].innerText == '오픈예정') {
				centercondition.value = 'notyet';
			}else if(this.cells[5].innerText == '영업중') {
				centercondition.value = 'o'+'-1';
			}
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
	let centerclosingDate = $("#centerClosingDate").val();
	let centercondition = $("#centerCondition").val();	

	let centeropeningDate = $("#centerOpeningDate").val();
	if (centeropeningDate  == '') {
		centercondition = "notyet";
		console.log(centercondition);
	}

	if (!$("#centerName").attr("readonly")) {

		let insertURL = "/center/centerInsert";

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
			success: centerlist,

			error: error

		});
	}else{
		let updateURL = "/center/centerUpdate";
		let centercondition = document.querySelector("#centerCondition").value;
		console.log("centerCondition"+centercondition);

		$.ajax({
			type : "POST",
			url : updateURL,
			data : {
				centerCode : centercode,
				centerName : centername,
				centerTel : centertel,
				centerAddress : centeraddress,
				centerOpeningDate  : centeropeningDate,
				centerCondition  : centercondition,
				centerGuide : centerguide,
				centerClosingDate : centerclosingDate
			},
			success : centerlist,
			error: error
		});
	}

});

//등록버튼
$("#centerInsertBtn").click(function () {

	$("#centerName").attr("readonly", false);

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


$("#findCenterList").click(function (){
	let centerCode = $("#centerCode").val();
	let centername = $("#findCenterName").val();
	let centerGuide = $("#centerGuide").val();
	let centerOpeningDate = $("#centerOpeningDate").val();
	let centerCondition = $("#ceneterCondition").val();
	let centerClosingDate = $("centerClosingDate").val();

//	ajaxCmm("POST", "/findCenter", 
//	{
//	centername
//	}, callback) {
//	}

//	}

	$.ajax({ 
		url : "/center/findCenter",
		type : "POST",
		data : {
			centerName : centername
		},
		success : centerlist,
		error: error
	});
});


