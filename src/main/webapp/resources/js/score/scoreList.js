
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
$(".firstCenter").click(function list() {
	let firstCenter=$(this).val();
	let hiddenCenterCode =$(this).next().val();
	let checkYear =$(".checkYear").next().val();
	let checkSeason  =$(".checkSeason").next().val();
	let checkGroupContent  =$(".checkGroupContent").next().val();
	let checkDetailContent  =$(".checkDetailContent").next().val();
	let checkScore  =$(".checkScore").next().val();
//	let userCode = $(this).next().next().val();


//	 console.log(userCode);
//	 console.log(hiddenCenterCode);

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
				url : "/score/indexListAjax?hiddenCenterCode="+hiddenCenterCode, //호출 URL
				type : "post",		//http타입
				dataType : "text"	//http 통신 후 응답 데이터 타입
		    }).done(function(result) {
				  console.log("결과확인");
		     		var html = jQuery('<div>').html(result);
					var contents = html.find("div#indexListAjax").html();/*div태그 중에서 id를 indexListAjax를 찾아서
																			contents라는 변수에 할당
																			jQuery('<div>') 이부분은 result를 넣은 html로 선언한 것
																			*/
						$("#tabl1").html(contents);
				
			}).fail(function (jqXHR, textStatus, errorThrown) {
				console.log("에러");
//				console.log(jqXHR);
//				console.log(textStatus);
//				console.log(errorThrown);
			});
		
});















