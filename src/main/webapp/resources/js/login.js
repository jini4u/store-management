/*const signup = document.getElementById("sign-up");
signin = document.getElementById("sign-in");
loginin = document.getElementById("login-in");
loginup = document.getElementById("login-up");

signup.addEventListener("click", () => {
    loginin.classList.remove("block");
    loginup.classList.remove("none");

    loginin.classList.add("none");
    loginup.classList.add("block");
})

signin.addEventListener("click", () => {
    loginin.classList.remove("none");
    loginup.classList.remove("block");

    loginin.classList.add("block");
    loginup.classList.add("none");
})*/

 function loginBtnClick(){
	 $("#invalid-login").empty();
		if($("#userCode").val().length == 0){
			console.log(message);
			$("#invalid-login").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>아이디를 입력해 주세요.</p>");
			$("#invalid-login").show;
			return false;
		}else if($("#password").val().length == 0){
			$("#invalid-login").html("<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>비밀번호를 입력해 주세요.</p>");
			$("#invalid-login").show;
			return false;
		}else{
			loadingMask();
			setTimeout(function(){
				return true;
			}, 50);
		} 
		return false;
 };
