<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<link rel="stylesheet" href="resources/css/login/login.css" />
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'/>


	<div class="login__content">
		<div class="login__img">
			<img
				src="${pageContext.request.contextPath}/resources/images/헌혈의집 캐릭터.png" alt="user login">
		</div>
		<div class="login__forms">
			<!--         login form -->

        
        <form action="/login" class="login__register" id="login-in" method="post" onsubmit="return loginBtnClick();">
		    <h1 class="login__title">Sign In</h1>
		    <div class="login__box">
			    <i class='bx bx-user login__icon'></i>
			    <input type="text" name="userCode" id="userCode" placeholder="UserCode" class="login__input">
		    </div>
		    <div class="login__box password">
		        <i class='bx bx-lock login__icon'></i>
			    <input type="password" name="password" id="password" placeholder="Password" class="login__input">
		    </div>
		    
			<c:if test="${message != null}">
				<div id="invalid-login-message">
					<img src='/resources/images/center/icons_care.png' class='danger_img'></img>&nbsp;<p class='danger_p'>${message}</p>
				</div>
			</c:if>
		    <div id="invalid-login">
		    </div>
		    
		    <input type="submit" id="loginBtn" class="pinkButton" value="Sign In">
        </form>

		</div>
	</div>
	
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="/resources/js/login.js"></script>