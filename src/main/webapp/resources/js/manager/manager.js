window.onload = function(){
	/*
//	각 요소에 이벤트 달아주는 함수
	function addEvent(){	
		mgrTable.addEventListener("click", function(event){	
				var xhr = new XMLHttpRequest(); //xhr을 통해서 XMLHtppRequest 인스턴스를 제어할 수 있다
				let userCode = document.querySelector("#userCode");
				console.log(userCode);
				const mgrTable2 = document.querySelector("#managerTable");
				var mgrTableTr = mgrTable2.rows;
				for(var i=1; i<mgrTableTr.length; i++) {
					var row = mgrTableTr[i];
//					row.onclick = function () {
//						let userCode = document.querySelector("#userCode");
//						console.log(userCode);
//					}
				xhr.open('GET', 'http://localhost:8080/managerList/1'); //(서버랑 통신할때 get 방식으로 통신, 서버 url)
				//서버와 통신하면 여러단계들이 존재하는데 단계마다 onreadystatechange 이벤트 헨들러가 호출된다
				xhr.onreadystatechange = function(){
					//readyState 프로퍼티 : 현재 통신이 어떤 상태에 있다
					//xhr.readyState === 4 -> 통신이 끝났을때 호출된 이벤트 헨들러
					//xhr.status : 커뮤니케이션 결과(200 = 성공)
					if(xhr.readyState === 4 && xhr.status === 200){
						//id 값이 'mgrdetailtable'인 요소를 innerHTML로 서버에서 가져온 정보를 넣는다
						// xhr.responseText: 서버에서 리턴해준 정보를 담고있는 프로퍼티
						console.log(xhr.responseText);
						document.querySelector('#mgrdetailtable').innerHTML = xhr.responseText;
								}
					} 
				}
				
				xhr.send(); //send 메서드가 호출될 때 xhr 객체가 33라인에 방식과 주소로 통신을 시작함
			
			});
		}

		addEvent();
		*/
	
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
	
	

	
	
	//상세 조회
	$("#managerTable tr").click(function(){
			var str = ""
			var tdArr = new Array();	// 배열 선언
			
			// 현재 클릭된 Row(<tr>)
			var tr = $(this);
			var td = tr.children();
			
			// tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
			console.log("클릭한 Row의 모든 데이터 : "+tr.text());
			
			// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
			td.each(function(i){
				tdArr.push(td.eq(i).text());
			});
			
			console.log("배열에 담긴 값 : "+tdArr);
			
			// td.eq(index)를 통해 값을 가져올 수도 있다.
			var usercode = td.eq(0).text();
			var username = td.eq(1).text();
			var userbirth = td.eq(2).text();
			var usertel = td.eq(3).text();
			var useremail = td.eq(4).text();
			var userteamcode = td.eq(5).text();
			var userhiredate = td.eq(6).text();
			var userresigndate = td.eq(7).text();

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
			
	
	})
	
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
		let mgrTr =$('#managerTable >tbody tr').length;
		
	

		let pwCut= usertel.substr(9, 12);
		
		//등록 버튼 클릭 시 리셋
		if(usernameinput.hasAttribute("readonly")){
			usercodeinput.value = mgrTr+1;
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
		} else { // 등록 버튼 클릭 시 등록
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
					  alert("성공")
			        
			      },
			      error: function() {
			          alert("에러 발생");
			      }
			      
			});
			
		}
		
	});
	
	
	
	
	}
