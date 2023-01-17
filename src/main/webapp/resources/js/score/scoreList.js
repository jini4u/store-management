
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



$('#firstCenter').click('submit', function(e) {
	let firstCenter=$(this).val();
	e.preventDefault();

	//$("#firstCenter").html("");
	if(confirm('지점 1이 맞습니까?')) //확인 누르면 true, 취소 누르면 false

	{   
		$.ajax({
			type: "GET",
			url: "/score/scoreList",
			dataType:"JSON",
			data: {
				centerCode : firstCenter 
			}, 
			//contentType: false,

			success: function(data)
			{
				alert("성공");
				// 전체 테이블 지우기
				$("#scoreListTable").html("<h1>centerlist</h1>");
			},
			fail: function(data)
			{
				alert('failed');
			},
			error: function(data)
			{
				if (data.status == 401) {
					alert('1 지점 입니다.');
					return;
				}
			}
		});
	}});



















