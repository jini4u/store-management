$("#btnSave").click(function (){
	$("#create-form").submit();
});

$("#btnUpdate").click(function (){
	$("#update-form").submit();
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
		if(loginUserCode < 30000){
			createBtn.style.display = 'none';
		} else {
			readUpdateHead.innerText = '공지사항 조회';
		}		
	} else {
		if(loginUserCode < 30000){
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

