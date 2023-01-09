window.onload = function(){

	//담당자 조회 리스트
	var mgrTable = document.querySelector("#managerTable");
	//담당자 등록 버튼 선택자
	var insertBtn = document.getElementById("insertmgr");
	//담당자 등록 선택자   
	var userCodeInput = document.querySelector("#userCode");
	var userNameInput = document.querySelector("#userName");
	var userBirthInput = document.querySelector("#userBirth");
	var userTelInput = document.querySelector("#userTel");
	var userEmailInput = document.querySelector("#userEmail");
	var userTeamCodeInput = document.querySelector("#userTeamCode");
	var userHireDateInput = document.querySelector("#userHireDate");
	var userResignDateInput = document.querySelector("#userResignDate");
	//담당자 등록 form 선택자
	var insertForm = document.querySelector("#insertform");
	/*   
   var innerText = document.getElementById("#userCode").innerText;
	 */

	handleClickTr();
	//담당자 테이블 행 클릭 시 상세조회
	function handleClickTr() {
		$("#managerTable tr").click(function(){
			//테이블 행들 rowList에 담기
			var rowList = mgrTable.rows;
			for (i=1; i<rowList.length; i++) {//thead부분 제외.
				var row = rowList[i];
				var userCode = this.cells[0].innerHTML;
				var userName = this.cells[1].innerHTML;
				var userBirth = this.cells[2].innerHTML;
				var userTel = this.cells[3].innerHTML;
				var userEmail = this.cells[4].innerHTML;
				var userTeamCode = this.cells[5].innerHTML;
				var userHireDate = this.cells[6].innerHTML;
				var userResignDate = '';
				if(this.cells[7]) {
					userResignDate = this.cells[7].innerHTML;
				}

				userCodeInput.value = userCode;
				userNameInput.value = userName;
				userBirthInput.value = userBirth;
				userTelInput.value = userTel;
				userEmailInput.value = userEmail;
				userTeamCodeInput.value = userTeamCode;
				userHireDateInput.value = userHireDate;
				userResignDateInput.value = userResignDate;

				userNameInput.setAttribute("readonly",true);
				userBirthInput.setAttribute("readonly",true);
				userHireDateInput.setAttribute("readonly",true);
				userResignDateInput.removeAttribute("readonly",true);
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

	//등록 버튼 클릭 시 리셋
	$("#insertmgr").click(function (){
		//담당자 전체 리스트 행의 수
		let mgrTr =$('#managerTable >tbody tr').length;

		if(userNameInput.hasAttribute("readonly")){
			userCodeInput.value = mgrTr + 10001;
			userNameInput.value = '';
			userBirthInput.value = '';
			userTelInput.value = '';
			userEmailInput.value = '';
			userTeamCodeInput.value = '';
			userHireDateInput.value = '';
			userResignDateInput.value = '';

			userNameInput.removeAttribute("readonly",true);
			userBirthInput.removeAttribute("readonly",true);
			userHireDateInput.removeAttribute("readonly",true);
			userResignDateInput.setAttribute("readonly",true);
		} 
     
	});

	//저장 버튼 클릭 
	$("#savemgr").click(function (){
		let updateUrl = "/managerUpdate";
		let insertUrl ="/managerInsert";
		let userCode=$("#userCode").val();
		let userPassWord=$("#userPassword").val();
		let userName=$("#userName").val();
		let userBirth=$("#userBirth").val();
		let userTel=$("#userTel").val();
		let userEmail=$("#userEmail").val();
		let userTeamCode=$("#userTeamCode").val();
		let userHireDate=$("#userHireDate").val();
		let userResignDate = $("#userResignDate").val();
		let existenceUserCode = $("#existenceUserCode").val();
		//담당자 전체 리스트 행의 수
		let mgrTr =$('#managerTable >tbody tr').length;
		//휴대전화번호 뒤에 4자리 자르기
		let pwCut= userTel.substr(9, 12);
		
		//등록
		if(!userNameInput.hasAttribute("readonly")){
	         $.ajax({
	             type:"POST",
	             url: insertUrl,
	             data: {
	                userCode : userCode,
	                userPassword : pwCut,
	                userName : userName,
	                userBirth : userBirth,
	                userTel : userTel,
	                userEmail : userEmail,
	                userTeamCode : userTeamCode,
	                userHireDate : userHireDate
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
	                  
	                   userCodeInput.value = mgrTr + 10002;
	                   userNameInput.value = '';
	                   userBirthInput.value = '';
	                   userTelInput.value = '';
	                   userEmailInput.value = '';
	                   userTeamCodeInput.value = '';
	                   userHireDateInput.value = '';
	                   userResignDateInput.value = '';
	                  alert("담당자 등록에 성공하셨습니다.")

	                  handleClickTr();
	                },
	                error: function() {
	                    alert("담당자를 등록하세요.");
	                }

	          });
			
		} else { // 수정
			   $.ajax({
		            type:"POST",
		            url: updateUrl,
		            data: {
		               userCode : userCode,
		               userPassword : pwCut,
		               userName : userName,
		               userBirth : userBirth,
		               userTel : userTel,
		               userEmail : userEmail,
		               userTeamCode : userTeamCode,
		               userHireDate : userHireDate,
		               userResignDate : userResignDate
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
		                     let resigndate = new Date(results[i].userResignDate);

		                     str += "<td>" + results[i].userCode + "</td><td>" + 
		                     results[i].userName + "</td><td>" + dateFormat(birth) + "</td><td>" +
		                     results[i].userTel + "</td><td>" + results[i].userEmail +"</td><td>" +
		                     results[i].userTeamCode + "</td><td>"+ dateFormat(hiredate) + "</td><td>" + dateFormat(resigndate)+ "</td>";
		                     str += "</tr>";
		                  });
		                  $("#managerTable").append(str);
		                     alert("수정 성공");
		                     handleClickTr();
		                  },

		            error: function( request, status, error ){
		               alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);

		            }

		         });
	

	       }
		
	});




}



