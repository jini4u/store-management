<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"    href="${pageContext.request.contextPath}/resources/css/score/score.css" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
   src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
   src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>



<!-- 점검년도 리스트 -->
<div class="titleBox">
   <img src="${pageContext.request.contextPath}/resources/images/checklist.png">
   <h2>점포 점수 조회</h2>
</div>

<div id="score_page">

	<div id="btn_group">

	<c:forEach items="${centerName}" var="center">
	<input type="button"  class="pinkButton firstCenter" value="${center.centerName}"/>
	<input type="hidden" class="hiddenCenterCode" value="${center.centerCode}"/> 
	<input type="hidden" class="hiddenCenterCode" value="${userCode}"/> 
	 
	</c:forEach>

	
</div>
	

   <div class="year_and_quarter">
      <form action="${pageContext.request.contextPath}/score/indexListAjax" name="score" method="post">
         <input type="hidden" name="centerCode" value="1"> 
         <select name="checkYear">
            <option value="0">점검년도</option>
            <option value="2023">2023</option>
            <option value="2022">2022</option>
            <option value="2021">2021</option>
            <option value="2020">2020</option>
         </select> <select name="checkSeason">
            <option value="0">분기</option>
            <option value="4">4 분기</option>
            <option value="3">3 분기</option>
            <option value="2">2 분기</option>
            <option value="1">1 분기</option>
         </select>
         <button type="submit" class="pinkButton">찾기</button>
      </form>
   </div>

</div>

<!-- 점수리스트 테이블 -->
<div id="tabl1">

</div>

<!-- 입력 모달창 -->

<!--maxyear가 year와 maxSeason이 season과 같지 않으면 모달창 실행 그렇지 않으면 모달창 실행되지 않음 -->
<c:if test="${(maxYear eq year and maxSeason eq season) == false}"> 

   <div class="modal fade" id="testModal" tabindex="-1" role="dialog"
      aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title" id="exampleModalLabel">점수입력</h5>
               <button class="close" type="button" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">X</span>
               </button>
            </div>
            <div class="modal-body">

               <!-- 모달창 안 테이블 -->
               <form method="post" action="/insertScore">
                  <div>년도: ${year}, 분기: ${season}</div>
                  <input type="hidden" name="centerCode" value="${centerCode}" /> 
                  <input type="hidden" name="userCode" value="${userCode}" /> 
                  <input type="hidden" name="checkYear" value="${year}" /> 
                  <input type="hidden" name="checkSeason" value="${season}" />
                  <table class="scoretable" border="1">
                     <tr>
                        <th class="score_th">항목</th>
                        <th class="score_th">상세항목</th>
                        <th class="score_th">점수</th>
                     </tr>
                     <c:forEach items="${usingCodeList}" var="usingCodeList">
                        <tr>
                           <td>${usingCodeList.checkGroupContent}</td>
                           <td>${usingCodeList.checkDetailContent}</td>
                           <td><input type="hidden" name="arrayCheckGroupCode" value="${usingCodeList.checkGroupCode}"> 
                           <input type="hidden" name="arrayCheckDetailCode" value="${usingCodeList.checkDetailCode}"> 
                           <input type="text" size="13" name="arrayScore" value="0"></td>
                        </tr>
                     </c:forEach>
                  </table>
            </div>
            <div class="modal-footer">
               
               <button type="submit" class="close-btn pinkButton">입력</button>
               <!-- <a class="pinkButton" id="modalY" href="#">입력</a>-->
               </form>
               <button class="greyButton"  data-dismiss="modal">취소</button>
            </div>
         </div>
      </div>
   </div>
  </c:if>
  </div>  


<!-- 모달 자바 스크립트 -->

<script src="${pageContext.request.contextPath}/resources/js/score/scoreList.js"></script>