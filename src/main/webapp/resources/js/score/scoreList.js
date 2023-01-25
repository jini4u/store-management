
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

window.onload = function(){
	//모달창
	$('#testBtn').click(function(e){
		e.preventDefault();
		$('#testModal').modal("show");
	});


}


//점수리스트 마우스 오버시 색 변화
function changeColor(){
	$('#scoreListTable tr').mouseover(function(){
		$(this).addClass('changeColor');
	}).mouseout(function() {
		$(this).removeClass('changeColor');
	});
}

changeColor();

//지점버튼 누르면 점수 리스트로 이동
//$(".firstCenter").click(function list() {
/*$(document).ready(function () {
$(document).on('click', '.firstCenter', function(){

	let firstCenter=$(this).val(); //이벤트 핸들러가 등록된 dom $("firstCenter") 이것이 html에서 포함되어 있는 태그
	let hiddenCenterCode =$(this).next().val();//.next : 해당 태그의 다음 태그를 찾는다
	let checkYear =$(".checkYear").next().val();
	let checkSeason  =$(".checkSeason").next().val();
	let checkGroupContent  =$(".checkGroupContent").next().val();
	let checkDetailContent  =$(".checkDetailContent").next().val();
	let checkScore  =$(".checkScore").next().val();

//	console.log(firstCenter);
//	console.log(hiddenCenterCode);

	let data = {
			centerName : firstCenter, 
			centerCode : hiddenCenterCode,
			checkYear   : checkYear,
			checkSeason   : checkSeason,
			checkGroupContent : checkGroupContent,
			checkDetailContent : checkDetailContent,
			checkScore   : checkScore
	};


	$.ajax({
		url : "/score/ListAjax", //호출 URL
		data: data,
		type : "post",	

	}).done(function(result) {
//		console.log("결과확인");
		var html = $('<div>').html(result); 
		var contents = html.find("div#indexListAjax").html();div태그 중에서 id를 indexListAjax를 찾아서
																			contents라는 변수에 할당
																			jQuery('<div>') 이부분은 result를 넣은 html로 선언한 것 
		
		$("#tabl1").html(contents);

	}).fail(function (jqXHR, textStatus, errorThrown) {
		console.log("에러");
//		console.log(jqXHR);
//		console.log(textStatus);
//		console.log(errorThrown);
	});

});*/

	

//년도 select박스
//$(document).ready()는 문서가 준비되면 매개변수로 넣은 콜백 함수를 실행하라는 의미
$(document).ready(function(){			
	setYearBox();
});    
function setYearBox(){
	var dt = new Date();
	var year = "";
	var com_year = dt.getFullYear();
	$("#yearbox").append("<option value=''>년도</option>");

	for(var y = (com_year); y >= (com_year-10); y--){ 								// 올해 기준으로 -10 까지
		$("#yearbox").append("<option value='"+ y +"'>"+ y + " 년" +"</option>");
	}
};






