window.onload = function(){
	handleClickTr();
	
	//담당자 테이블 행 클릭 시 상세조회
	function handleClickTr() {
		$("#managerTable tr").click(function(event){		
			console.log(event.target.nodeName);
			
			if(event.target.nodeName == "A"){
				return;
			} 
			
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
			

			$("#userCode").val(userCode);
			$("#userName").val(userName);
			$("#userBirth").val(userBirth);
			$("#userTel").val(userTel);
			$("#userEmail").val(userEmail);
			$("#userTeamCode").val(userTeamCode);				
			$("#userHireDate").val(userHireDate);
			$("#userResignDate").val(userResignDate);

			$("#userName").attr("readonly","true");
			$("#userBirth").attr("readonly","true");
			$("#userHireDate").attr("readonly","true");
			$("#userResignDate").removeAttr("readonly");
			
		})
	}
	
	//담당자 리스트 마우스 오버시 색 변화
	function changeColor(){
		$('#managerTable tr').mouseover(function(){
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
		let hour = date.getHours();

		month = month >= 10 ? month : '0' + month;
		day = day >= 10 ? day : '0' + day;
		hour = hour >= 10 ? hour : '0' + hour;

		return date.getFullYear() + '-' + month + '-' + day;
	}

	//등록 버튼 클릭 시 리셋
	$("#insertmgr").click(function (){
		const newUserCode = $("#newUserCode").val();

		if($("#userName").attr("readonly")){
			$("#userCode").val(newUserCode);
			$("#userName").val('');
			$("#userBirth").val('');
			$("#userTel").val('');
			$("#userEmail").val('');
			$("#userTeamCode").val('');				
			$("#userHireDate").val('');
			$("#userResignDate").val('');	
			
			$("#userName").removeAttr("readonly");
			$("#userBirth").removeAttr("readonly");
			$("#userHireDate").removeAttr("readonly");
			$("#userResignDate").attr("readonly",true);
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
		
		console.log("userHireDate: " + userHireDate);
		console.log("userResignDate: " + userResignDate);
		
		//등록
		if(!$("#userName").attr("readonly")){
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
	            	  
	            /*	   // 전체 테이블 지우기
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
*/
	            	  
	            	  
	        
	                  // 전체 테이블 지우기
	                  $("#mgrListTr").html("");

	                //등록 성공 시 담당자 전체 목록 리스트 마지막 열에 추가 됨
	                  let results = result;
	                  
	                  let str = "";
	                  $.each(results, function(i) {
	                     let birth = new Date(results[i].userBirth);
	                     let hiredate = new Date(results[i].userHireDate);
	                     
	                     str += "<tr>";
	                     str += "<td>" + results[i].userCode + "</td><td>" + 
	                     results[i].userName + "</td><td>" + dateFormat(birth) + "</td><td>" +
	                     results[i].userTel + "</td><td>" + results[i].userEmail +"</td><td>" +
	                     results[i].userTeamCode + "</td><td>"+ dateFormat(hiredate) + "</td>";
	                     str += "</tr>";
	                  });
	                  
	                  $("#mgrListTr").html(str);
	                  
	                  $("#userCode").val(mgrTr + 10002);
	                  $("#userName").val('');
	                  $("#userBirth").val('');
	                  $("#userTel").val('');
	                  $("#userEmail").val('');
	                  $("#userTeamCode").val('');				
	                  $("#userHireDate").val('');
	                  $("#userResignDate").val('');	
	                  
	                  alert("담당자 등록에 성공하셨습니다.")

	                  handleClickTr();
	                },
	                error: function() {
	                    alert("담당자를 등록하세요.");
	                }

	          });
			
		} else { // 수정
				var data = {
	               userCode : userCode,
	               userPassword : pwCut,
	               userName : userName,
	               userBirth : userBirth,
	               userTel : userTel,
	               userEmail : userEmail,
	               userTeamCode : userTeamCode,
	               userHireDate : userHireDate,
	               userResignDate : userResignDate
			    };
				
				console.log(data);
			
			   $.ajax({
		            type:"POST",
		            url: updateUrl,
		            data: data,
		             success: function(result) {
		            	  // 전체 테이블 지우기
		                  $("#mgrListTr").empty();


		                //등록 성공 시 담당자 전체 목록 리스트 마지막 열에 추가 됨
		                  let results = result;
		                  let str = " ";
		                  $.each(results, function(i) {
		                     let birth = new Date(results[i].userBirth);
		                     let hiredate = new Date(results[i].userHireDate);
		                     let resigndate = new Date(results[i].userResignDate);
		                     
		                     str +="<tr>"
		                     str += "<td>" + results[i].userCode + "</td><td>" + 
		                     results[i].userName + "</td><td>" + dateFormat(birth) + "</td><td>" +
		                     results[i].userTel + "</td><td>" + results[i].userEmail +"</td><td>" +
		                     results[i].userTeamCode + "</td><td>"+ dateFormat(hiredate) + "</td><td>" + dateFormat(resigndate)+ "</td>";
		                     str += "</tr>";
		                  });
		                  $("#managerTable").html(str);
		                     alert("수정 성공");
		                     handleClickTr();
		                     changeColor();
		                  },

		            error: function( request, status, error ){
		               alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);

		            }

		         });

	       }
		
	});




}



