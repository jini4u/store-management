<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="resources/css/login.css"/>
<div id="logindiv">
<h1>로그인</h1>
<form action="#" method="post">

	<table>
	<tr id="bordertr">
		<td>
		<img src="resources/images/person-icon.png">
		</td>
		<td>
		<input class="rightinput" id="userId" placeholder="아이디">
		</td>
	</tr>
	<tr id="blanktr">
	</tr>
	<tr id="bordertr">
		<td>
		<img src="resources/images/key-icon.png">
		</td>
		<td>
		<input class="rightinput" id="userPassword" placeholder="비밀번호">
		</td>
	</tr>
	<tr>
	<td class="righttd" colspan="2"><a href="#">로그인 정보 찾기</a></td>
	</tr>
	<tr>
	<td colspan="2"><input type="submit" id="loginbtn" value="로그인"></td>
	</tr>
	</table>
</form>
</div>