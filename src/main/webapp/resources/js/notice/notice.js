$("#btnSave").click(function (){
	$("#form").submit();
});

$("#btnList").click(function (){
	location.href="/board/list";
});

$("#btnUpdate").click(function (){
	$("#btnUpdate").hide();
	$("#btnSave").show();
	$(".form-control").attr("readonly", false);
});
