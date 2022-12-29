<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/managermapping.css">
<div class="titleBox">
	<img src="resources/images/manager.png">
	<h2>담당자 맵핑</h2>
</div>
<div class="buttonBox">
	<div>
		<a href="#" class="mappingButton">맵핑</a> <a href="#"
			class="releaseButton">해제</a>
	</div>
</div>

<!-- 검색 -->
 <div class="search-box">
      <input type="text" class="search-txt" name="" placeholder="담당자 검색">
      <a class="search-btn" href="#">
        <i class="fas fa-search"></i>
      </a>
  </div> 
  
  <div class="graphbox">
  	<div class="managerbox">
  		<h3>담당자</h3>
  	</div>
  	<div class="centerbox">
  		<h3>센터</h3>
  	 </div>
  </div>

