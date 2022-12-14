//브라우저 로드 완료 상태를 나타냅니다 
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
			let centerName=$("#centerName").val();

			$("#userCode").val(userCode);
			$("#userName").val(userName);
			$("#userBirth").val(userBirth);
			$("#userTel").val(userTel);
			$("#userEmail").val(userEmail);
			$("#userTeamCode").val(userTeamCode);            
			$("#userHireDate").val(userHireDate);
			$("#userResignDate").val(userResignDate);
			$("#centerName").val(centerName);

			$("#userName").attr("disabled");
			$("#userBirth").attr("disabled");
			$("#userEmail").removeAttr("disabled");
			$("#userTel").removeAttr("disabled");
			$("#userTeamCode").removeAttr("disabled");
			$("#userResignDate").removeAttr("disabled");


			$.ajax({
				type: 'POST',
				url:'/manager/getCenters/'+userCode,
				success: function(result) {
					console.log(result);
					$.each(result, function(index, value){
						console.log(index + " :: " + value.centerName);
						$("#centerName").html(value.centerName);	
					});
				}
			});

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
		let userCode = $("#userCode").val();
		$("#userCode").val(userCode);
		$("#userName").val('');
		$("#userBirth").val('');
		$("#userTel").val('');
		$("#userEmail").val('');
		$("#userTeamCode").val('');            
		$("#userHireDate").val('');
		$("#userResignDate").val('');   

		$("#userName").removeAttr("disabled");
		$("#userBirth").removeAttr("disabled");
		$("#userEmail").removeAttr("disabled");
		$("#userTel").removeAttr("disabled");
		$("#userHireDate").removeAttr("disabled");
		$("#userTeamCode").removeAttr("disabled");
		$("#userResignDate").attr("disabled");
	});

	//저장 버튼 클릭 
	$("#savemgr").click(function (){
		let updateUrl = "/manager/managerUpdate";
		let insertUrl ="/manager/managerInsert";
		let userCode=$("#userCode").val();
		let userPassWord=$("#userPassword").val();
		let userName=$("#userName").val();
		let userBirth=$("#userBirth").val();
		let userTel=$("#userTel").val();
		let userEmail=$("#userEmail").val();
		let userTeamCode=$("#userTeamCode").val();
		let userHireDate=$("#userHireDate").val();
		let userResignDate = $("#userResignDate").val();
		//휴대전화번호 뒤에 4자리 자르기
		let pwCut= userTel.substr(9, 12);

		//등록
		if(!$("#userName").attr("disabled")){
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

					//등록 성공 시 담당자 전체 목록 리스트 마지막 열에 추가 됨
					let results = result;

					let str = "";
					//결과룰 반복한다
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

					handleClickTr();
					changeColor();
				},
				error: function() {
					alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);
					/* alert("담당자를 등록하세요.");*/
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
					$("#mgrList").html(str);
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

	//검색 버튼
	$("#searchBtn").click(function (){
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
			url: "manager/managerSearch",
			data: {
				userName : userName
			},
			success: function(result) {

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
				//테이블 비우고 불러온다
				$("#mgrList").html(str);

				handleClickTr();
				changeColor();
			},
			error: function( request, status, error ){
				alert("status : " + request.status + ", message : " + request.responseText + ", error : " + error);

			}

		})
	});

}


