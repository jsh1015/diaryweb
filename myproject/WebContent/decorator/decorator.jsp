<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><decorator:title/></title>
    <!-- plugins:css -->
  <link rel="stylesheet" href="../../src/assets/vendors/iconfonts/mdi/css/materialdesignicons.css">
     <link rel="stylesheet" href="../../css/demo1.css">
	<link rel="stylesheet" href="../../css/shared.css">
    <link rel="shortcut icon" href="../../images/favicon.ico" />
    <script type="text/javascript" src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
  <style type="text/css">
  	/* @import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);

	html, body, h1, h2, h3, h4, h5, h6, p, a {
		font-family: 'Jeju Gothic', serif;
	} */
	.t-header .t-header-content-wrapper .t-header-search-box {
    width: 25%;
  </style>
  </head>
  <decorator:head />
  <body class="header-fixed">
    <nav class="t-header">
      <div class="t-header-brand-wrapper">
        <a href="../member/main.me" style="color:black;">
          <h3>Diary</h3>
        </a>
      </div>
      <div class="t-header-content-wrapper">
        <div class="t-header-content">
          <button class="t-header-toggler t-header-mobile-toggler d-block d-lg-none">
            <i class="mdi mdi-menu"></i>
          </button>
          <!-- 검색했을때 이동하는 action -->
          <form action="../member/memsearch.me?id=${login}" name="f" class="t-header-search-box" method="post">
          <%-- <%=URLEncoder.encode("'몽'", "UTF-8")%>  --%>
            <div class="input-group">
            <input type="text" class="form-control" name="searchid"  size="20" id="inlineFormInputGroup" placeholder="사람찾기 : 아이디를 입력하세요" >
              <button class="btn btn-primary" type="submit"><i class="mdi mdi-arrow-right-thick"></i></button>
            </div>
          </form>
          <ul class="nav ml-auto">
	            <li class="nav-item dropdown">
	              <a class="nav-link" href="../member/logout.me">
	                Logout
	              </a>
	            </li>
	            <li class="nav-item dropdown">
	              <a class="nav-link" href="../board/deco.do?cate=1">
	                DECO
	              </a>
	            </li>
	            <li class="nav-item dropdown">
	              <a class="nav-link" href="../member/mypage.me?id=${login}">
	                Mypage
	              </a>
	            </li>
        	 </ul>
        </div>
       </div>
    </nav>
    
    <!-- partial -->
    <div class="page-body">
      <!-- partial:partials/_sidebar.html -->
      <div class="sidebar">
        <div class="user-profile">
          <div class="display-avatar animated-avatar">
          <c:if test="${empty picture}">
          	 <img class="profile-img img-lg rounded-circle" src="../member/picture/사람.png" alt="profile image">
          </c:if>
          <c:if test="${!empty picture}">
            <img class="profile-img img-lg rounded-circle" src="../member/picture/${picture}" alt="profile image">
          </c:if>
          </div>
          <div class="info-wrapper">
            <p class="user-name"><a href="javascript:loginminihome()">${nickname}</a></p><br>
            <c:if test="${login eq 'admin'}">
				<a href="../member/memlist.me">회원정보보기</a>
         	</c:if>
            <script type="text/javascript">
            function loginminihome(){
    			var op = "width=1000, height=600, left=50, top=150, scrollbars=no, resizable=no,toolbars=no, menubar=no"
    			var url ="../board/minihome.do?id=${login}&boardnum=4"
    			open(url,"",op);
    		}
            </script>
          </div>
        </div>
        <ul class="navigation-menu">
          <li class="nav-category-divider">MAIN</li>
          <li>
            <a href="../member/main.me">
              <span class="link-title">HOME</span>
              <i class="mdi mdi-face link-icon"></i>
            </a>
          </li>
          
          
          <li>
            <a href="../board/stickerboard.do?boardnum=1">
              <span class="link-title">스티커공유게시판</span>
              <i class="mdi mdi-arrow-right-bold link-icon"></i>
            </a>
          </li>
          <li>
            <a href="../board/backgroundboard.do?boardnum=2">
              <span class="link-title">배경공유게시판</span>
              <i class="mdi mdi-arrow-right-bold link-icon"></i>
            </a>
          </li>
          <li>
            <a href="../board/board1.do?boardnum=3">
              <span class="link-title">자유게시판</span>
              <i class="mdi mdi-arrow-right-bold link-icon"></i>
            </a>
          </li>
        </ul>
      </div>
      
      	<div class="page-content-wrapper">
       	 <div class="page-content-wrapper-inner">
          <div class="content-viewport">
          	<decorator:body/>
          </div>
         </div>
        </div>
      </div>
       -->
      <!-- page content ends -->
    </div>
    <!--page body ends -->
    <!-- SCRIPT LOADING START FORM HERE /////////////-->
    <!-- plugins:js -->
    <script src="../../src/assets/vendors/js/core.js"></script>
    <!-- endinject -->
    <!-- Vendor Js For This Page Ends-->
    <script src="../../src/assets/vendors/apexcharts/apexcharts.min.js"></script>
    <script src="../../src/assets/vendors/chartjs/Chart.min.js"></script>
    <script src="../../src/assets/js/charts/chartjs.addon.js"></script>
    <!-- Vendor Js For This Page Ends-->
    <!-- build:js -->
    <script src="../../src/assets/js/template.js"></script>
    <script src="../../src/assets/js/dashboard.js"></script>
    <!-- endbuild --> 
    
      
<!-- <script src="../assets/js/ready.min.js"></script> -->
	<script src="../../src/assets/js/setting-demo.js"></script>
<!--	<script src="../assets/js/demo.js"></script> -->
  </body>
</html>