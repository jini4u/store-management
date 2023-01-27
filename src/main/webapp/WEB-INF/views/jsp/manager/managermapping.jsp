<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/resources/css/manager/managermapping.css">

<div class="titleBox">
   <img
      src="${pageContext.request.contextPath}/resources/images/manager.png">
   <h2>담당자 맵핑</h2>
</div>
<div class="buttonBox">
   <div>
      <button type="button" class="mappingButton pinkButton">맵핑</button>
      <button type="button" class="releaseButton greyButton">해제</button>
   </div>
</div>

<!-- 검색 -->
<div class="search-box">
   <input type="text" id="search-txt" class="search-txt" name=""
      placeholder="담당자 검색"> <a id="search-btn" class="search-btn"><i
      class="fas fa-search"></i></a>
</div>

<div class="graphbox">
   <div class="managerbox">
      <div id="managerTitle">
         <p>담당자</p>
         <p>${totalManagers}명</p>
      </div>
      <table class="verticalTable" id="managertable">
         <thead>
            <tr>
               <th>담당자 코드</th>
               <th>담당자명</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${managerList}" var="manager">
               <tr>
                  <td>${manager.userCode}</td>
                  <td>${manager.userName}</td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
      <div class="center-pagging">
         <ul class="pagination">
            <li><a class="innerPager first"
               href="managerMapping?pageNo=1&keyword=${keyword}">처음</a></li>
            <li><c:if test="${pager.groupNo>1}">
                  <a class="innerPager arrow left"
                     href="managerMapping?pageNo=${pager.startPageNo-1}&keyword=${keyword}">이전</a>
               </c:if></li>
            <c:forEach var="i" begin="${pager.startPageNo}"
               end="${pager.endPageNo}">
               <li><c:if test="${pager.pageNo != i}">
                     <a class="innerPager active num"
                        href="managerMapping?pageNo=${i}&keyword=${keyword}">${i}</a>
                  </c:if></li>
               <li><c:if test="${pager.pageNo == i}">
                     <a id="now-page" class="innerPager num"
                        href="managerMapping?pageNo=${i}&keyword=${keyword}">${i}</a>
                  </c:if></li>
            </c:forEach>
            <li><c:if test="${pager.groupNo<pager.totalGroupNo}">
                  <a class="innerPager arrow right"
                     href="managerMapping?pageNo=${pager.endPageNo+1}&keyword=${keyword}">다음</a>
               </c:if></li>
            <li><a class="innerPager last"
               href="managerMapping?pageNo=${pager.totalPageNo}&keyword=${keyword}">맨끝</a></li>
         </ul>
      </div>
   </div>
   <div class="centerbox">
      <p>센터
      <table class="verticalTable" id="centertable">
         <thead>
            <tr>
               <th></th>
               <th>센터코드</th>
               <th>센터명</th>
               <th id="address">주소</th>
            </tr>
         </thead>
         <tbody>
         </tbody>
      </table>
   </div>
</div>

<div class="hiddenmodal hide">
   <div class="modal_overlay"></div>
   <div class="modal_content">
      <h3>맵핑 가능 센터 목록</h3>
      <!-- 모달창 안 테이블 -->
      <table class="verticalTable" id="availtable">
         <thead>
            <th>센터코드</th>
            <th>센터명</th>
            <th>주소</th>
         </thead>
         <tbody>
         </tbody>
      </table>

      <div class="center-pagging modal-pagging">
         <ul class="pagination">
            <li><a class="innerPager first">처음</a></li>
            <li>
               <c:if test="${pager.groupNo>1}">
                  <a class="innerPager arrow left">이전</a>
               </c:if>
            </li>
            <c:forEach var="i" begin="${pager.startPageNo}" end="${pager.endPageNo}">
               <li>
                  <c:if test="${pager.pageNo != i}">
                     <a class="innerPager active num">${i}</a>
                  </c:if>
               </li>
               <li>
                  <c:if test="${pager.pageNo == i}">
                     <a id="now-page" class="innerPager num">${i}</a>
                  </c:if>
               </li>
            </c:forEach>
            <li>
               <c:if test="${pager.groupNo<pager.totalGroupNo}">
                  <a class="innerPager arrow right">다음</a>
               </c:if>
            </li>
            <li><a class="innerPager last">맨끝<input type="hidden" value="${pager.totalPageNo}"></a></li>
         </ul>
      </div>
      <button type="button" class="close-btn pinkButton" id="mappingbutton">저장</button>
      <button class="close-btn greyButton">취소</button>
   </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/manager/mapping.js"></script>