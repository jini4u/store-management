$("#userPassword").on("keyup",function(event){
	  let checkPassword=/^(?=.*[A-Za-z])(?=.*[~!@#$%^&*])(?=.*[0-9]).{6,16}$/;
	
	   //빈 칸 일때
	   if($("#userPassword").val().length == 0){
			  $("#invalid-password").empty();
	   }else{
		      //비밀번호가 형식에 맞지 않을때
		   	  if(!checkPassword.test($("#userPassword").val())){
				  $("#invalid-password").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;" +
				  		"<p class='danger_p'>영문(대소문자)/숫자/특수문자를 조합하여 8자 이상 16자 이하 이어야 합니다.</p>");
				  $("#invalid-password").show();
				  $("#savemgr").attr("disabled", true); //disabled 속성 적용
			  }else{
				  $("#invalid-password").empty();
				  $("#savemgr").attr("disabled", false);
			  }
		   
	   }   
});

$("#pwCloseBtn").click(function (){
		$("#invalid-password").empty();
	    $("#userPassword").val('');
	});