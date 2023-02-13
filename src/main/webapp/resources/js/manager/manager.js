window.onload = function(){

var mgrListTd;
$(".updateBtn").click(function(){ 
   $(".invalid-userEmail").empty();
   
   var str = ""
   var mgrTdArr = new Array();   // 배열 선언
   var updateModal = $(this);
   
   // updateModal.parent() : updateModal의 부모는 <td>이다.
   // updateModal.parent().parent() : <td>의 부모이므로 <tr>이다.
   var mgrListTr = updateModal.parent().parent();
   mgrListTd = mgrListTr.children();
   
   console.log("클릭한 Row의 모든 데이터 : "+mgrListTr.text());
   
   var userCode = mgrListTd.eq(0).text();
   var userName = mgrListTd.eq(1).text();
   var userBirth = mgrListTd.eq(2).text();
   var userTel = mgrListTd.eq(3).text();
   var userEmail = mgrListTd.eq(4).text();
   var userTeamCode = mgrListTd.eq(5).text();
   var userHireDate = mgrListTd.eq(6).text();
   var userResignDate = '';
    if(mgrListTd.eq(7)) {
       userResignDate =  mgrListTd.eq(7).text();
    }
   
   $("#userCodeInfo").val(userCode);
   $("#userNameInfo").val(userName);
   $("#userBirthInfo").val(userBirth);
   $("#userTelInfo").val(userTel);
   $("#userEmailInfo").val(userEmail);
   $("#userTeamCodeInfo").val(userTeamCode);            
   $("#userHireDateInfo").val(userHireDate);
   $("#userResignDateInfo").val(userResignDate);
   
   console.log("배열에 담긴 값 : "+mgrTdArr);
   
   //담당 센터명 조회
    $.ajax({
        type: 'POST',
        url:'/manager/getcenters/'+userCode,
        success: function(result) {
           console.log(result);
           var view = '';
           //.each : 배열과 length 속성을 갖는 배열과 유사 배열 객체들을 index를 기준으로 반복
           $.each(result,function(index, value){   
              view += value.centerName;
              
              if(index != result.length-1){
                 view += ', ';
              }   
           });
           $("#centerName").html(view);
        }
     });
});

//담당자 수정
$("#updatemgr").click(function (){
   let userCode=$("#userCodeInfo").val();
   let userPassWord=$("#userPasswordInfo").val();
   let userName=$("#userNameInfo").val();
   let userBirth=$("#userBirthInfo").val();
   let userTel=$("#userTelInfo").val();
   let userEmail=$("#userEmailInfo").val();
   let userTeamCode=$("#userTeamCodeInfo").val();
   let userHireDate=$("#userHireDateInfo").val();
   let userResignDate = $("#userResignDateInfo").val();
   let pageNo = $("#now-page").text();  
   
   var data = {
 		   	pageNo : pageNo,
            userCode : userCode,
            userName : userName,
            userBirth : userBirth,
            userTel : userTel,
            userEmail : userEmail,
            userTeamCode : userTeamCode,
            userHireDate : userHireDate,
            userResignDate : userResignDate,
      };

      console.log(data);

      $.ajax({
         type:"POST",
         url: "/manager/managerupdate",
         data: data,
         success: function(results) {
        	 mgrListTd.eq(0).text(results.userCode);
             mgrListTd.eq(1).text(results.userName);
             mgrListTd.eq(2).text(results.userBirth);
             mgrListTd.eq(3).text(results.userTel);
             mgrListTd.eq(4).text(results.userEmail);
             mgrListTd.eq(5).text(results.userTeamCode);
             mgrListTd.eq(6).text(results.userHireDate); 
             if(results.userResignDate) {
                userResignDate =  mgrListTd.eq(7).text(results.userResignDate);
             }
             
            alert("수정 성공");
            changeColor();
         },

         error: function( request, status, error ){
            alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);

         }

      });

});

   
   
   //담당자 리스트 마우스 오버시 색 변화
   function changeColor(){
      $('#mgrList tr').mouseover(function(){
         $(this).addClass('changeColor');
      }).mouseout(function() {
         $(this).removeClass('changeColor');
      });
   }

   changeColor();

   //날짜 YYYY-MM-DD 형태로 변환
   function dateFormat(date) {
      let month = date.getMonth() + 1;
      let day = date.getDate();

      month = month >= 10 ? month : '0' + month;
      day = day >= 10 ? day : '0' + day;

      return date.getFullYear() + '-' + month + '-' + day;
   }

   // 초기화 버튼
   function inputValueReset() {
	   let userCode = $("#userCode").val();
	      $("#userCode").val(userCode);
	      $("#userName").val('');
	      $("#userBirth").val('');
	      $("#userTel").val('');
	      $("#userEmail").val('');
	      $("#userTeamCode").val('');            
	      $("#userHireDate").val('');
	      $("#userResignDate").val('');  
	      $("#invalid-userName").empty();
	      $(".invalid-userEmail").empty();	
	      $(".invalid-userTel").empty();
		  $(".invalid-userTeamCode").empty();	 
   };
   
   $("#resetBtn").click(function (){
	   inputValueReset();
	});
   
   $(".mgrInsertBtn").click(function (){
	   inputValueReset();
	});
   
   //담당자 등록
   $("#savemgr").click(function (){
      let userCode=$("#userCode").val();
      let userPassWord=$("#userPassword").val();
      let userName=$("#userName").val();
      let userBirth=$("#userBirth").val();
      let userTel=$("#userTel").val();
      let userEmail=$("#userEmail").val();
      let userTeamCode=$("#userTeamCode").val();
      let userHireDate=$("#userHireDate").val();
      //휴대전화번호 뒤에 4자리 자르기
      let pwCut= userTel.substr(9, 12);
       
      let data = {
         userPassword : pwCut,
         userName : userName,
         userBirth : userBirth,
         userTel : userTel,
         userEmail : userEmail,
         userTeamCode : userTeamCode,
         userHireDate : userHireDate
      };
      
      $.ajax({
         type:"POST",
         url: "/manager/managerinsert",
         data: data,
         success: function(result) {
        	 location.href = "/manager/managerlist";  
         },
         error: function() {
            alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);
         }

      });
   });
   
  
	
/*  유효성 검사*/
   //담당자명: 한글만 가능, 띄어쓰기 불가능
   let checkName = /^[가-힣]+$/;
   //이메일 형식만 가능
   let checkEmail = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
   //전화번호 
   let checkTel = /^\d{3}-\d{3,4}-\d{4}$/;
   //팀코드: 3으로 시작하는 세자리만 가능
   let checkTeamCode = /3\d{2}/;

  //userName 유효성 검사
   $("#userName").on("keyup",function(event){
	   //빈 칸 일때
	   if($("#userName").val().length == 0){
			  $("#invalid-userName").empty();
	   }else{
		      //담당자명이 한글이 아닐때
			  if(!checkName.test($("#userName").val())){
				  $("#invalid-userName").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>한글만 입력해 주세요.</p>");
				  $("#invalid-userName").show();
				  $("#savemgr").attr("disabled", true); //disabled 속성 적용
			  }else{//담당자명이 한글일때
				  $("#invalid-userName").empty();
				  //담당자명 길이 맞지 않을때
				  if($("#userName").val().length >=10 || $("#userName").val().length <= 1){
					  $("#invalid-userName").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>담당자명 2~9자 사이로 입력해 주세요.</p>");
					  $("#invalid-userName").show();
					  $("#savemgr").attr("disabled", true); //disabled 속성 적용
				  }else{ //담당자명 길이 맞을때
					  $("#savemgr").attr("disabled", false); //disabled 속성 해제
				  }
			  }
		   
	   }   
   });
   
   //userEmail 유효성 검사
   $(".userEmailCheck").on("keyup",function(event){
	   //현재 보이는 모달 선택
	   var nowModal = $('.show')[0];
	   var num;
	   //모달 타이틀이 담당자 등록이면
	   if(nowModal.innerText.split('\n')[0] == '담당자 등록'){
		   num = 0;
	   } else { //아니면 (수정이면)
		   num = 1;
	   }
	   
	   //보이는 모달에 해당하는 객체 선택 (0번이 등록모달, 1번이 수정모달)
	   var emailValue = $(".userEmailCheck")[num].value;
	   var invalidEmailDiv = $(".invalid-userEmail")[num];
	   //빈 칸 일때
	   if(emailValue.length == 0){
		   invalidEmailDiv.style.display = 'none';
	   }else{
		      //이메일 형식이 아닐때
			  if(!checkEmail.test(emailValue)){
				  invalidEmailDiv.innerHTML = "<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>이메일 형식이 올바르지 않습니다.</p>";
				  invalidEmailDiv.style.display = 'flex';
//				  $(".invalid-userEmail").show();
				  $("#savemgr").attr("disabled", true); //disabled 속성 적용
				  $("#updatemgr").attr("disabled", true); 
			  }else{//이메일 형식일때
				  invalidEmailDiv.style.display = 'none';  
				  $("#savemgr").attr("disabled", false); //disabled 속성 해제
				  $("#updatemgr").attr("disabled", false);
			  }
	   }   
   });
   


   //userTel 유효성 검사
   $(".userTelCheck").on("keyup",function(event){
	   //현재 보이는 모달 선택
	   var nowModal = $('.show')[0];
	   var num;
	   //모달 타이틀이 담당자 등록이면
	   if(nowModal.innerText.split('\n')[0] == '담당자 등록'){
		   num = 0;
	   } else { //아니면 (수정이면)
		   num = 1;
	   }
	   
	   //보이는 모달에 해당하는 객체 선택 (0번이 등록모달, 1번이 수정모달)
	   var telValue = $(".userTelCheck")[num].value;
	   var invalidTelDiv = $(".invalid-userTel")[num];
	   
	   //빈 칸 일때
	   if(telValue.length == 0){
		   invalidTelDiv.style.display = 'none';  	
	   }else{
		      //전화번호 형식이 아닐때
			  if(!checkTel.test(telValue)){
				  invalidTelDiv.innerHTML = "<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>전화번호 형식이 올바르지 않습니다.</p>";
				  invalidTelDiv.style.display = 'flex';
				  $("#savemgr").attr("disabled", true); //disabled 속성 적용
				  $("#updatemgr").attr("disabled", true); 
			  }else{//전화번호 형식일때
				  invalidTelDiv.style.display = 'none';  				  
				  $("#savemgr").attr("disabled", false); //disabled 속성 해제
				  $("#updatemgr").attr("disabled", false);
			  }
		   
	   }   
   });
   
   //userTeamCode 유효성 검사
   $(".userTeamCodeCheck").on("keyup",function(event){
	   //현재 보이는 모달 선택
	   var nowModal = $('.show')[0];
	   var num;
	   //모달 타이틀이 담당자 등록이면
	   if(nowModal.innerText.split('\n')[0] == '담당자 등록'){
		   num = 0;
	   } else { //아니면 (수정이면)
		   num = 1;
	   }
	   
	   //보이는 모달에 해당하는 객체 선택 (0번이 등록모달, 1번이 수정모달)
	   var teamCodeValue = $(".userTeamCodeCheck")[num].value;
	   var invalidTeamCodeDiv = $(".invalid-userTeamCode")[num];
	   
	   //빈 칸 일때
	   if(teamCodeValue.length == 0){
			  invalidTeamCodeDiv.style.display = 'none';  	
	   }else{
		      //팀코드 형식이 아닐때
			  if(!checkTeamCode.test(teamCodeValue)){
				  invalidTeamCodeDiv.innerHTML = "<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>300번대로 입력해 주세요.</p>";
				  invalidTeamCodeDiv.style.display = 'flex';
				  $("#savemgr").attr("disabled", true); //disabled 속성 적용
				  $("#updatemgr").attr("disabled", true); 
			  }else{//팀코드 형식일때
				  invalidTeamCodeDiv.style.display = 'none';  	
				  $("#savemgr").attr("disabled", false); //disabled 속성 해제
				  $("#updatemgr").attr("disabled", false);
			  }
		   
	   }   
   });
   
   //퇴사일자 유효성 검사
   $("#userResignDateInfo").change(function() {
		let userHitrDate = new Date(document.querySelector("#userHireDateInfo").value);
		let userResignDate = new Date(document.querySelector("#userResignDateInfo").value);
		if(userHitrDate > userResignDate){
			$("#invalid-userResignDate").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>입사일자 이후로 선택해주세요.</p>");
			$("#invalid-userResignDate").show();
		} else{
			$("#invalid-userResignDate").empty();
			$("#updatemgr").attr("disabled", false);
		}
	   
   });

}

var excelBtn = document.getElementById('excel-download-button');

excelBtn.addEventListener('click', function(){
	var urlParams = new URLSearchParams(location.search);
	var keywordType = urlParams.get('keywordType');
	var keyword = urlParams.get('keyword');
	
	makeRequest(function(){
		var response = httpRequest.responseText;
		var excel = document.getElementById('excel');
		if(keyword=='' || keyword==null){
			excel.setAttribute('href', '/file/'+response);
		} else {
			response = response.split('_');
			excel.setAttribute('href','/file/'+response[0]+'_'+keyword+'_'+response[2]);			
		}
		excel.click();
	}, 'GET', '/manager/managerlistdownload?keywordType='+keywordType+'&keyword='+keyword);
});

/*생년월일 등록 제한*/
var now_utc = Date.now() // 지금 날짜를 밀리초로
//getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
//new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("userBirth").setAttribute("max", today);