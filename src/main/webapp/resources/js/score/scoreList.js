
/**
 * @code.js
 * @Description : 점수관리 js
 * @Modification 
 *     수정일      	수정자      	  	수정내용
 *  =========== =========  =====================
 * 2023.01.03	정윤선
 * 2023.01.13	정윤선
 * @author 정윤선
 * **/
$(function(){
	 // 모달 버튼에 이벤트를 건다.
	  $('#openModalBtn').on('click', function(){
	    $('#modalBox').modal('show');
	  });
	  // 모달 안의 취소 버튼에 이벤트를 건다.
	  $('#closeModalBtn').on('click', function(){
	    $('#modalBox').modal('hide');
	  });

});


//점수리스트 마우스 오버시 색 변화
function changeColor(){
	$('.scoreListTableTr').mouseover(function(){
		$(this).addClass('changeColor');
	}).mouseout(function() {
		$(this).removeClass('changeColor');
	});
}

changeColor();

//년도 select박스
//$(document).ready()는 문서가 준비되면 매개변수로 넣은 콜백 함수를 실행하라는 의미
$(document).ready(function(){			
	setYearBox();
	
});    

var urlParams = new URLSearchParams(window.location.search);

function setYearBox(){
	var dt = new Date();
	var year = "";
	var com_year = dt.getFullYear();
	$("#yearbox").append("<option value=''>년도</option>");

	for(var y = (com_year); y >= (com_year-10); y--){ 	// 올해 기준으로 -10 까지
		if(y == urlParams.get('checkYear')){
			$("#yearbox").append("<option selected='selected' value='"+ y +"'>"+ y + " 년" +"</option>");
		} else{
			$("#yearbox").append("<option value='"+ y +"'>"+ y + " 년" +"</option>");
		}
	}
};

//값 입력 범위 지정 및 문자 입력 제한
/*	$('#inputNumber').on('keyup', function() {
	    if (/\D/.test(this.value)) {
	        this.value = this.value.replace(/\D/g, '')
	        alert('숫자만 입력가능합니다.');
	    }
	  if (this.value > 100 ) {
	      this.value = 100;
	      return false
	    }else{
	        alert("점수는 0-100까지만 가능합니다.")
	        return false
	    }

	});*/



	
//scoreUpdate
	
var updateBtn = document.getElementById("score-update-button");
if(urlParams.get("checkYear") > 0 || urlParams.get("checkSeason") > 0){
	updateBtn.style.display = 'block';
	$(".placeholderstlye").removeAttr('disabled');
} else {
	updateBtn.style.display = 'none';
}

//엑셀로 다운로드
var excelBtn = document.getElementById('excel-download-button');
excelBtn.addEventListener('click', function(){
	var urlParams = new URLSearchParams(location.search);
	var centerCode = urlParams.get('centerCode');
	var checkYear = urlParams.get('checkYear');
	var checkSeason = urlParams.get('checkSeason');
	
	checkYear = (checkYear==null)?0:checkYear;
	checkSeason = (checkSeason==null)?0:checkSeason;
	
	makeRequest(function(){
		var response = httpRequest.responseText;
		var excel = document.getElementById('excel');
		excel.setAttribute('href','/file/'+response);			
		excel.click();
	}, 'GET', '/score/scorelistdownload?centerCode='+centerCode+'&checkYear='+checkYear+'&checkSeason='+checkSeason);
});
	


function modalScore(){
	let insertBtn = true;
	$("input[name=arrayScore]").each(function(){
		if($(this).val()==0){
			insertBtn = false;
		}
	})
	if(!insertBtn){ /*alert("값을 입력해주세요!");*/
		$("#invalid-insertscore").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>모든값을 입력해주세요.</p>");
		$("#invalid-insertscore").show();
	} else {
		loadingMask();
	} 
	
	return insertBtn;
}

function checkUpdateScores(){
	let update = true;
	$("#scoreListTable input[name=arrayScore]").each(function(){
		if($(this).val()==null || $(this).val()==''){
            console.log($(this));
            update = false;
        }
	})
	
	if(update == false){
		$("#invalid-updatescore").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>모든값을 입력해주세요.</p>");
		$("#invalid-updatescore").show();
	}
	return update;
}
