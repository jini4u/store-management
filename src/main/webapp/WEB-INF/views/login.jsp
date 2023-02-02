<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>헌혈의 집 관리 시스템 로그인</title>
</head>
<link rel="stylesheet" href="resources/css/login/login.css" />
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>


	<div class="login__content">
		<div class="login__img">
			<img
				src="${pageContext.request.contextPath}/resources/images/헌혈의집 캐릭터.png" alt="user login">
		</div>
		<div class="login__forms">
			<!--         login form -->

        
        <form action="/login" class="login__register" id="login-in" method="post">
          <h1 class="login__title">Sign In</h1>
          <div class="login__box">
            <i class='bx bx-user login__icon'></i>
            <input type="text" name="userCode" placeholder="Username" class="login__input">
          </div>
          <div class="login__box">
            <i class='bx bx-lock login__icon'></i>
            <input type="password" name="password" placeholder="Password" class="login__input">
          </div>
       <input type="submit" class="pinkButton" value="Sign Up">
        </form>

		</div>
	</div>
