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
	$("#findCenterName").val('');

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
	$('#centerList tr').mouseover(function(){
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
		centerCondition.value = "o"+"-1";
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



var clickTd;
var imgCenterCode;
//테이블 클릭시 td값
$("#center-left tr").click(function (){

	$("#centerInsertModal").removeAttr('class', 'modal-lg');
	$("#centerInsertModal").attr('class', 'modal-dialog modal-xl modal-dialog-centered');

	$("#centerPhotoList").empty();
	str =""
		tdArr = new Array(); //배열 선언

	//현재 클릭된 Row(<tr>)
	tr = $(this);
	clickTd = tr.children();

	var centerCode = clickTd.eq(0).text();	
	imgCenterCode = clickTd.eq(0).text();
	console.log(imgCenterCode);
	var centerName = clickTd.eq(1).text();
	var centerTel = clickTd.eq(2).text();
	var centAddress = clickTd.eq(3).text();

	var centOpeningDate = '';
	if (clickTd.eq(4).text()) {
		centOpeningDate = clickTd.eq(4).text();
	};
	var centCondition =clickTd.eq(5).text();

	var centGuide = clickTd.eq(6).text();
	var centClosingDate = '';

	if (clickTd.eq(7).text()) {
		centClosingDate = clickTd.eq(7).text();
	}

	$("#centerCode").val(centerCode);
	$("#centerName").val(centerName);
	$("#centerTel").val(centerTel);
	$("#centerAddress").val(centAddress);
	$("#centerOpeningDate").val(centOpeningDate);
	$("#centerCondition").val(centCondition);
	$("#centerClosingDate").val(centClosingDate);
	$("#centerGuide").val(centGuide);

	console.log("클릭한 td데이터 :" + clickTd.text());

	if (imgCenterCode !=null) {
		$("#showPhoto").show();
		$(".modal-body").attr('id', 'centerphoto');
		getDetailAjax()
	}

});

function getDetailAjax() {
	$.ajax({
		url : "getcenterimages/" + imgCenterCode,
		data : {
			centerCode : imgCenterCode
		},
		success : function(results) {
			let centerPhotoList = $("#centerPhotoList");
			let imgholder = $("#imgholder");
			let bullets = $(".bullets");
			let strDOMinput = "";
			let strDOMul ="";
			let strDOMdiv ="";
			if (results.length == 0) {
				strDOMdiv +="<div>"
				strDOMdiv += "<img src='/resources/images/center/no_image.png' class='center-noimage'>"
				strDOMdiv += "</div>";
			}
			for(i=0; i<results.length; i++) {
				strDOMinput += "<input type='radio' name='slide' id='slide"+i+"' checked>";
			}
			centerPhotoList.append(strDOMinput);
			
			strDOMul += "<ul id='imgholder' class='imgs'>";
			for(i=0; i<results.length; i++) {
				let image = results[i];
				strDOMul += "<li><img id='centerPhotoSize' src=/file/"+image.fileSavedName + "/></li>";
			} 
			strDOMul += "</ul>";
			centerPhotoList.append(strDOMul);
			
			strDOMdiv += "<div class='bullets'>";
			for(i=0; i<results.length; i++) {
				strDOMdiv += "<label for=slide" +i+ ">&nbsp;&nbsp;</label>";
			} 
			strDOMdiv +="</div>";
			centerPhotoList.append(strDOMdiv);
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
		let insertURL = "centerinsert";
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
			success: function(results){
				location.href="centerlist"
			},
			error: error
		});
	}else{
		let updateURL = "centerupdate";
		let centercondition = document.querySelector("#centerCondition").value;
		let pageNo = $("#now-page").text();
		alert(pageNo);
		console.log("centerCondition"+centercondition);
		$.ajax({
			type : "POST",
			url : updateURL,
			data : {
				pageNo : pageNo,
				centerCode : centercode,
				centerName : centername,
				centerTel : centertel,
				centerAddress : centeraddress,
				centerOpeningDate  : centeropeningDate,
				centerCondition  : centercondition,
				centerGuide : centerguide,
				centerClosingDate : centerclosingDate
			},
			success : function(results){
				if(results.centerOpeningDate == null) {
					results.centerOpeningDate = '-';
				}else {
					results.centerOpeningDate = results.centerOpeningDate.substring(0,10);
				}
				clickTd.eq(0).text(results.centerCode);
				clickTd.eq(1).text(results.centerName);
				clickTd.eq(2).text(results.centerTel);
				clickTd.eq(3).text(results.centerAddress);
				clickTd.eq(4).text(results.centerOpeningDate);
				clickTd.eq(5).text(results.centerCondition);
				clickTd.eq(6).text(results.centerguide);
				clickTd.eq(7).text(results.centerclosingDate);
				$("#findCenterName").val('');
			},
			error: error
		});
	}
});


//등록버튼
$("#centerInsertBtn").click(function () {

	$("#centerPhotoList").empty();


	$("#showPhoto").hide();
	$(".modal-body").removeAttr('id', 'centerphoto' );
	$("#centerInsertModal").removeAttr('class', 'modal-xl');


	$("#centerName").attr("readonly", false);
	$("#centerInsertModal").attr('class', 'modal-dialog modal-lg modal-dialog-centered');
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



