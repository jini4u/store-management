window.onload = function(){
/*	handleClickTr();
*/
	//담당자 테이블 행 클릭 시 상세조회
/*	function handleClickTr() {
		$(".updateBtn").click(function(event){      
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

			$("#userName").attr("disabled",true);
			$("#userBirth").attr("disabled",true);
			$("#userEmail").attr("disabled",false);
			$("#userTel").attr("disabled",false);
			$("#userTeamCode").attr("disabled",false);
			$("#userHireDate").attr("disabled",true);
			$("#userResignDate").attr("disabled",false);
			
			//담당 센터명 조회
	        $.ajax({
	            type: 'POST',
	            url:'/manager/getCenters/'+userCode,
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

		})
	}*/

	
$(".updateModal").click(function(){ 
	
	var str = ""
	var tdArr = new Array();	// 배열 선언
	var updateModal = $(this);
	
	// updateModal.parent() : updateModal의 부모는 <td>이다.
	// updateModal.parent().parent() : <td>의 부모이므로 <tr>이다.
	var tr = updateModal.parent().parent();
	var td = tr.children();
	
	console.log("클릭한 Row의 모든 데이터 : "+tr.text());
	
	var userCode = td.eq(0).text();
	var userName = td.eq(1).text();
	var userBirth = td.eq(2).text();
	var userTel = td.eq(3).text();
	var userEmail = td.eq(4).text();
	var userTeamCode = td.eq(5).text();
	var userHireDate = td.eq(6).text();
	var userResignDate = '';
	
	$("#userCodeInfo").val(userCode);
	$("#userNameInfo").val(userName);
	$("#userBirthInfo").val(userBirth);
	$("#userTelInfo").val(userTel);
	$("#userEmailInfo").val(userEmail);
	$("#userTeamCodeInfo").val(userTeamCode);            
	$("#userHireDateInfo").val(userHireDate);
	$("#userResignDateInfo").val(userResignDate);
	
	console.log("배열에 담긴 값 : "+tdArr);
	
	//담당 센터명 조회
    $.ajax({
        type: 'POST',
        url:'/manager/getCenters/'+userCode,
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

	// 초기화 버튼
	$("#resetBtn").click(function (){
		let userCode = $("#userCode").val();
		$("#userCode").val(userCode);
		$("#userName").val('');
		$("#userBirth").val('');
		$("#userTel").val('');
		$("#userEmail").val('');
		$("#userTeamCode").val('');            
		$("#userHireDate").val('');
		$("#userResignDate").val('');   
	});


	//검색 버튼
	/*$("#searchBtn").click(function (){
		let userCode=$("#userCode").val();
		let userPassWord=$("#userPassword").val();
		let userName=$("#searchTxt").val();
		let userBirth=$("#userBirth").val();
		let userTel=$("#userTel").val();
		let userEmail=$("#userEmail").val();
		let userTeamCode=$("#userTeamCode").val();
		let userHireDate=$("#userHireDate").val();
		let userResignDate = $("#userResignDate").val();
		$.ajax({
			type:"POST",
			url: "/managerSearch",
			data: {
				userName : userName
			},
			success: function(result) {

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
				$("#mgrList").html(str);
				
				handleClickTr();
				changeColor();
			},
			error: function( request, status, error ){
				alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);

			}

		});
	});*/
	
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
		
		$.ajax({
			type:"POST",
			url: "/manager/managerInsert",
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

				$("#mgrList").html(str);

				$("#userCode").val('');
				$("#userName").val('');
				$("#userBirth").val('');
				$("#userTel").val('');
				$("#userEmail").val('');
				$("#userTeamCode").val('');            
				$("#userHireDate").val('');
				$("#userResignDate").val('');   

				alert("담당자 등록에 성공하셨습니다.")

				changeColor();
			},
			error: function() {
				alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);
				/* alert("담당자를 등록하세요.");*/
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
		//휴대전화번호 뒤에 4자리 자르기
		let pwCut= userTel.substr(9, 12);
		let updateModal = $(".updateModal").val();
			
		// 수정
		var data = {
					userCode : userCode,
					userPassword : pwCut,
					userName : userName,
					userBirth : userBirth,
					userTel : userTel,
					userEmail : userEmail,
					userTeamCode : userTeamCode,
					userHireDate : userHireDate,
					userResignDate : userResignDate,
					updateModal : updateModal
			};

			console.log(data);

			$.ajax({
				type:"POST",
				url: "/manager/managerUpdate",
				data: data,
				success: function(result) {


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
						results[i].userTeamCode + "</td><td>"+ dateFormat(hiredate) + "</td><td>" +
						dateFormat(resigndate)+ "</td>"+
						 "<td><button class='updateModal' data-toggle='modal' " +
						 "data-target='#updateModal'>수정</button></td>";
						str += "</tr>"; 
					});
					$("#mgrList").html(str);
					alert("수정 성공");
					changeColor();
				},

				error: function( request, status, error ){
					alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);

				}

			});

	});
	
}


