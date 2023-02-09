
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
function menuClick(event){
	
}

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
	$('#scoreListTable tr').mouseover(function(){
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
	$('#inputNumber').on('keyup', function() {
	    if (/\D/.test(this.value)) {
	        this.value = this.value.replace(/\D/g, '')
	        alert('숫자만 입력가능합니다.');
	    }
	  if (this.value > 100) {
	      this.value = 100;
	      alert('점수는 0-100까지만 가능합니다.');
	  }
	});

	
//scoreUpdate
	
var updateBtn = document.getElementById("score-update-button");

if(urlParams.get("checkYear")!=null && urlParams.get("checkSeason")!=null){
	updateBtn.style.display = 'block';
} else {
	updateBtn.style.display = 'none';
}

	
	$('input[name=arrayScore]').prop('type', "placeholder");
	
