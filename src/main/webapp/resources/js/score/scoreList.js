
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
   
   $("#firstCenter").click(function(){
	   
	 alert("점포1입니다.");
   });
}