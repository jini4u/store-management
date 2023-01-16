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
		str +=  "<td>" + results[i].centerCode + "</td>" +
		"<td>" + results[i].centerName +"</td>+" +
		"<td>" + results[i].centerTel + "</td>" +
		"<td>" + results[i].centerAddress + "</td>" +
		"<td>" + results[i].centerOpeningDate + "</td>" +
		"<td>" + results[i].centerCondition + "</td>" +
		"<td style='display:none'>" + results[i].centerGuide + "</td>" +
		"<td style='display:none'>" + results[i].centerClosingDate +"</td>" +
		"<td><button id='centerDetails' class='centerSize' data-toggle='modal' data-target='#myModal'>상세보기</button></td>";

		str += "</tr>";
	});
	$("#center-left").append(str);
	$("#centerForm input").val('');
	$("#findCenterName").val('');
	CallcenterList();
	$('#myModal').hide();
	$('.modal-backdrop').hide();
}
let error = function( request, status, error ){
	alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);
};

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

//date를 떠났을 때 이런식으로 고쳐야한다
$(".centerDate").change(function() {
	let centerOpeningDate = new Date(document.querySelector("#centerOpeningDate").value);
	let centerClosingDateRd = document.querySelector("#centerClosingDate");
	let centerCondition = document.querySelector("#centerCondition");
//	centerClosingDate.value = '';
	if (centerOpeningDate !== '') {
		centerClosingDateRd.readOnly = false;
	} else {
		centerClosingDateRd.readOnly = true;
	}
	if (centerOpeningDate > getToday()) {
		centerCondition.value = "notyet";
	}
	if (centerOpeningDate < getToday()) {
		centerCondition.value = "o"+"1";
	}
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




//centerList 불러오는 자스
const leftTable = document.querySelector("#center-left");
var leftTableTr = leftTable.rows;

var imgCenterCode;

function CallcenterList() {
	$("#centerName").attr("readonly", true);
	let centercode = document.querySelector("#centerCode");
	let centername = document.querySelector("#centerName");
	let centertel = document.querySelector("#centerTel");
	let centeraddress = document.querySelector("#centerAddress");
	let centeropeningdate = document.querySelector("#centerOpeningDate");
	let centercondition = document.querySelector("#centerCondition");

	let centerclosingdate = document.querySelector("#centerClosingDate");
	let centerguide = document.querySelector("#centerGuide");

	for(var i=1; i<leftTableTr.length; i++) {
		var row = leftTableTr[i];
		row.onclick = function () {
			centercode.value = this.cells[0].textContent;
			imgCenterCode = this.cells[0].textContent;
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
			console.log(this.cells[6].textContent);
			centerclosingdate.value = this.cells[7].textContent;
			alert(imgCenterCode);
			if (imgCenterCode != null) {
				alert("centerCode 받아오나")
				//디스플레이 논 풀어주고
				$("#showPhoto").show();
				//id="centerphoto" 추가
				$(".modal-body").attr('id', 'centerphoto' );
				getDetailAjax();
			}
			
		}
	}
	$("#centerPhotoList").empty();
}
	function getDetailAjax() {
		$.ajax({
			url : "getCenterImages/" + imgCenterCode,
			data : {
				centerCode : imgCenterCode
			},
			success : function(results) {
				let centerPhotoList = $("#centerPhotoList");
				let strDOM = "";
				if (results.length == 0) {
					strDOM +="<div>"
					strDOM += "<img src='/resources/images/center/no_image.png'>"
					strDOM += "</div>";
				}
				for(i=0; i<results.length; i++) {
					let image = results[i];
					strDOM += "<div>";
		               strDOM += "<img id='centerPhotoSize' src=/image/"+image.fileSavedName + "/> </c:if>"
					strDOM += "</div>";
				}
				centerPhotoList.append(strDOM);
			},
			error : error
		});
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

	if(!$("#centerName").attr("readonly")) {
		alert("등록");

		let insertURL = "centerInsert";

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
		let updateURL = "centerUpdate";
		let centercondition = document.querySelector("#centerCondition").value;
		console.log("centerCondition"+centercondition);
		alert("수정");
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
	$("#centerPhotoList").empty();
	
	$("#showPhoto").hide();
	$(".modal-body").removeAttr('id', 'centerphoto' );

	
	
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



