window.onload = function(){

   //담당자 조회 리스트
   var mgrTable = document.querySelector("#managerTable");
   //담당자 등록 버튼 선택자
   var insertBtn = document.getElementById("insertmgr");
   //담당자 등록 선택자   
   var usercodeinput = document.querySelector("#userCode");
   var usernameinput = document.querySelector("#userName");
   var userbirthinput = document.querySelector("#userBirth");
   var usertelinput = document.querySelector("#userTel");
   var useremailinput = document.querySelector("#userEmail");
   var userteamcodeinput = document.querySelector("#userTeamCode");
   var userhiredateinput = document.querySelector("#userHireDate");
   var userresigndateinput = document.querySelector("#userResignDate");
   //담당자 등록 form 선택자
   var insertForm = document.querySelector("#insertform");
/*   
   var innerText = document.getElementById("#userCode").innerText;
   */
   
   handleClickTr();

   function handleClickTr() {
      $("#managerTable tr").click(function(){
            //테이블 행들 rowList에 담기
              var rowList = mgrTable.rows;
              for (i=1; i<rowList.length; i++) {//thead부분 제외.
                    var row = rowList[i];
                    var usercode = this.cells[0].innerHTML;
                  var username = this.cells[1].innerHTML;
                  var userbirth = this.cells[2].innerHTML;
                  var usertel = this.cells[3].innerHTML;
                  var useremail = this.cells[4].innerHTML;
                  var userteamcode = this.cells[5].innerHTML;
                  var userhiredate = this.cells[6].innerHTML;
                  var userresigndate = '';
                  if(this.cells[7]) {
                     userresigndate = this.cells[7].innerHTML;
                  }
                  
                  usercodeinput.value = usercode;
                  usernameinput.value = username;
                  userbirthinput.value = userbirth;
                  usertelinput.value = usertel;
                  useremailinput.value = useremail;
                  userteamcodeinput.value = userteamcode;
                  userhiredateinput.value = userhiredate;
                  userresigndateinput.value = userresigndate;
                  
                  usernameinput.setAttribute("readonly",true);
                  userbirthinput.setAttribute("readonly",true);
                  userhiredateinput.setAttribute("readonly",true);
                  userresigndateinput.removeAttribute("readonly",true);
              }//for
      })
    }
   
   
   //날짜 YYYY-MM-DD 형태로 변환
   function dateFormat(date) {
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();

        month = month >= 10 ? month : '0' + month;
        day = day >= 10 ? day : '0' + day;
        hour = hour >= 10 ? hour : '0' + hour;

        return date.getFullYear() + '-' + month + '-' + day + ' ';
}
   
   //등록 버튼 클릭 시 
   $("#insertmgr").click(function (){
      let url ="/managerInsert";
      let usercode=$("#userCode").val();
      let userpassword=$("#userPassword").val();
      let username=$("#userName").val();
      let userbirth=$("#userBirth").val();
      let usertel=$("#userTel").val();
      let useremail=$("#userEmail").val();
      let userteamCode=$("#userTeamCode").val();
      let userhireDate=$("#userHireDate").val();
      //담당자 전체 리스트 행의 수
      let mgrTr =$('#managerTable >tbody tr').length;
      //휴대전화번호 뒤에 4자리 자르기
      let pwCut= usertel.substr(9, 12);
      var userCodePlus = $('input[name=userCode]').val();
       
      //등록 버튼 클릭 시 리셋
      if(usernameinput.hasAttribute("readonly")){
         usercodeinput.value = mgrTr + 10001;
         usernameinput.value = '';
         userbirthinput.value = '';
         usertelinput.value = '';
         useremailinput.value = '';
         userteamcodeinput.value = '';
         userhiredateinput.value = '';
         userresigndateinput.value = '';
               
         usernameinput.removeAttribute("readonly",true);
         userbirthinput.removeAttribute("readonly",true);
         userhiredateinput.removeAttribute("readonly",true);
         userresigndateinput.setAttribute("readonly",true);
      } /*else { // 등록 버튼 클릭 시 등록
         $.ajax({
            type:"POST",
            url: url,
            data: {
               userCode : usercode,
               userPassword : pwCut,
               userName : username,
               userBirth : userbirth,
               userTel : usertel,
               userEmail : useremail,
               userTeamCode : userteamCode,
               userHireDate : userhireDate
            },
             success: function(result) {
                 // 전체 테이블 지우기
                 mgrTable.tBodies[0].innerHTML = '';
                 
               //등록 성공 시 담당자 전체 목록 리스트 마지막 열에 추가 됨
                 let results = result;
                 let str = "<tr>";
                 $.each(results, function(i) {
                    let birth = new Date(results[i].userBirth);
                    let hiredate = new Date(results[i].userHireDate);

                    str += "<td>" + results[i].userCode + "</td><td>" + 
                    results[i].userName + "</td><td>" + dateFormat(birth) + "</td><td>" +
                    results[i].userTel + "</td><td>" + results[i].userEmail +"</td><td>" +
                    results[i].userTeamCode + "</td><td>"+ dateFormat(hiredate) + "</td>";
                    str += "</tr>";
                 });
                 $("#managerTable").append(str);
                    usercodeinput.value = mgrTr + 10002;
                  usernameinput.value = '';
                  userbirthinput.value = '';
                  usertelinput.value = '';
                  useremailinput.value = '';
                  userteamcodeinput.value = '';
                  userhiredateinput.value = '';
                  userresigndateinput.value = '';
                 alert("담당자 등록에 성공하셨습니다.")
                 
                 handleClickTr();
               },
               error: function() {
                   alert("담당자를 등록하세요.");
               }
               
         });
         
      }*/
      
   });
   
   //저장 버튼 클릭 시 수정 완료
   $("#savemgr").click(function (){
      let url = "/managerUpdate";
      let usercode=$("#userCode").val();
      let userpassword=$("#userPassword").val();
      let username=$("#userName").val();
      let userbirth=$("#userBirth").val();
      let usertel=$("#userTel").val();
      let useremail=$("#userEmail").val();
      let userteamCode=$("#userTeamCode").val();
      let userhireDate=$("#userHireDate").val();
      let userresigndate = $("#userResignDate").val();
      let pwCut= usertel.substr(9, 12);
      
      $.ajax({
         type:"POST",
         url: url,
         data: {
            userCode : usercode,
            userPassword : pwCut,
            userName : username,
            userBirth : userbirth,
            userTel : usertel,
            userEmail : useremail,
            userTeamCode : userteamCode,
            userHireDate : userhireDate,
            userResignDate : userresigndate
         },
         dataType:'json',
          success: function(result) {
             // 전체 테이블 지우기
              mgrTable.tBodies[0].innerHTML = '';
              
            //등록 성공 시 담당자 전체 목록 리스트 마지막 열에 추가 됨
              let results = result;
              let str = "<tr>";
              $.each(results, function(i) {
                 let birth = new Date(results[i].userBirth);
                 let hiredate = new Date(results[i].userHireDate);

                 str += "<td>" + results[i].userCode + "</td><td>" + 
                 results[i].userName + "</td><td>" + dateFormat(birth) + "</td><td>" +
                 results[i].userTel + "</td><td>" + results[i].userEmail +"</td><td>" +
                 results[i].userTeamCode + "</td><td>"+ dateFormat(hiredate) + "</td>";
                 str += "</tr>";
              });
              $("#managerTable").append(str);
              alert("성공")
            },
            error: function( request, status, error ){

                 alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);

                }
            
         });
      });
   
   
   
   
   }
   
   
   
   