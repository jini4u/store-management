var formInputs = document.querySelectorAll('.noticediv form .form-control');
var invalidDivs = document.getElementsByClassName('invalid');

$("#btnSave").click(function (){
	let filled = true;
	for(var i=0;i<invalidDivs.length;i++){
		invalidDivs[i].innerHTML = '';
	}
	
	for(var i=0;i<formInputs.length;i++){
		var input = formInputs[i];
		var invalid = invalidDivs[i];
		if(input.value.trim() == ''){
			filled = false;
			invalid.innerHTML = "<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>내용을 입력해주세요</p>";
			input.focus();
		}
	}
	if(filled == true){
		$("#create-form").submit();
	}
});

$("#btnUpdate").click(function (){
	let filled = true;
	for(var i=0;i<invalidDivs.length;i++){
		invalidDivs[i].innerHTML = '';
	}
	
	for(var i=0;i<formInputs.length;i++){
		var input = formInputs[i];
		var invalid = invalidDivs[i];
		if(input.value.trim() == ''){
			filled = false;
			invalid.innerHTML = "<img src='/resources/images/center/icons_care.png' class='danger_img'></img><p class='danger_p'>내용을 입력해주세요</p>";
			input.focus();
		}
	}
	if(filled == true){
		$("#update-form").submit();
	}
	
});

$("#btnList").click(function (){
	location.href="/board/list";
});

window.onload = function(){
	var createBtn = document.getElementById("createnotice");
	var readUpdateHead = document.getElementById("read-update-heading");
	var updateBtn = document.getElementById("btnUpdate");
	var deleteBtn = document.getElementById("deletebutton");
	
	if(createBtn != null){
		if(loginUserAuthority != 'sysadmin'){
			createBtn.style.display = 'none';
		} else {
			readUpdateHead.innerText = '공지사항 조회';
		}		
	} else {
		if(loginUserAuthority != 'sysadmin'){
			updateBtn.style.display = 'none';
			deleteBtn.style.display = 'none';
			readUpdateHead.innerText = '공지사항 조회';
			$("input").css('border', 'none');
				 $('textarea').css('height', 'auto');
				 $('textarea').height(this.scrollHeight);
		} else {
			readUpdateHead.innerText = '공지사항 수정';
			$(".editable").attr('readonly', false);
		}		
	}
	
	
}

