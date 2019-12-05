<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>나만의 다이어리</title>
<script type="text/javascript">
  var selcolor;
</script>
<link rel="stylesheet" href="../../css/materialdesignicons.css">
<link rel="stylesheet" href="../../css/shared.css">
<link rel="stylesheet" href="../../css/demo1.css">
<link rel="shortcut icon" href="../../src/assets/images/favicon.ico" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
<link rel="stylesheet" href="../../css/azzara.min.css">
<link rel="stylesheet" href="../../css/demo.css"> 
<style type="text/css">
	@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	html,body,h1,h2,h3,h4,h5,h6,p,a {font-family: 'Jeju Gothic', serif;}
	.table.info-table tr td:first-child, .table.info-table tr th:first-child,
	table.info-table tr td:first-child, table.info-table tr th:first-child
	{
	text-align: center;
	}
</style>
  </head>
  <body class="header-fixed">
    <!-- partial:partials/_header.html -->
    <!-- partial -->
    <div class="page-body">
      <!-- partial:partials/_sidebar.html -->
      
      
      <div class="sidebar" data-background-color="${mem.color }">
        <div class="user-profile" style="background:rgba(255,255,255,0.6)">
          <div class="display-avatar animated-avatar">
	           <c:if test="${empty mem.picture}">
	          	 <img class="profile-img img-lg rounded-circle" src="../member/picture/사람.png" alt="profile image">
	          </c:if>
	          <c:if test="${!empty mem.picture}">
	            <img class="profile-img img-lg rounded-circle" src="../member/picture/${mem.picture }" alt="profile image">
	          </c:if>
          </div>
          <div class="info-wrapper">
            <p class="user-name" style="font-size:15px;"><a href="javascript:minihome()">${mem.name }</a></p>
          </div>
        </div>
      </div>
      <!-- partial -->
      
      <c:if test="${login eq mem.id}">
      <div class="custom-template">
			<div class="title">Settings</div>
			<div class="custom-content">
				<div class="switcher">
					<div class="switch-block">
						<h4>Topbar</h4>
						<div class="btnSwitch">
							<button type="button" class="changeMainHeaderColor" data-color="white" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="red" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="orange" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="green" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="light-blue"></button>
							<button type="button" class="changeMainHeaderColor" data-color="purple"></button>
						</div>
					</div>

					<input type="button" 
					   onclick="location.href='color.do?id=${mem.id}&color='+selcolor" value="저장">
				</div>
			</div>
			<div class="custom-toggle">
				<i class="flaticon-settings"></i>
			</div>
		</div>
      	</c:if>
      <!-- 내가만든 리스트 -->
      <div class="page-content-wrapper" style="margin-top:5px">
      	 <div class="page-content-wrapper-inner">
          <div class="content-viewport">
                <div class="grid-title">
                  <div class="item-wrapper">
                    <div class="table-responsive">
                      <table style="font-size: medium;" class="table info-table table-striped">
                      	<tr><td><p style="font-size:15px; margin-bottom: 0">${mem.diaryname}</p></td></tr>
                      </table>
                    </div>
                  </div>
                </div>
                <div class=></div>
              </div>
            </div>
          </div>
      </div>
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