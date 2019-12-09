<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

	html, body, h1, h2, h3, h4, h5, h6, p, a {
		font-family: 'Jeju Gothic', serif;
	}

	.table.info-table tr td:first-child, .table.info-table tr th:first-child,
	table.info-table tr td:first-child, table.info-table tr th:first-child
	{
		text-align: center;
	}

	@media ( min-width :600px) {
		.sidebar {
			width: 250px;
		}
	body {
		border-color: #BDBDBD;
	}
	
	
	
/* div {
    position:relative;
    z-index:1;
}
.background:after {
	background-image:url('../board/picture/${mem.picture}');
    top:0;
    left:0;
    position:absolute;
    background-size:100%;
    opacity:0.5!important;
    filter:alpha(opacity=50);
    z-index:-1;
    content:"";
    width:100%;
    height:100%;
} */


</style>
<script type="text/javascript">
	function listdo(page){ //list.do를 위에서 실행하게 함
		document.sf.pageNum.value=page;
		document.sf.submit();
	}
</script>
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
						<h4>color</h4>
						<div class="btnSwitch">
							<button type="button" class="changeMainHeaderColor" data-color="white" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="red" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="orange" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="green" ></button>
							<button type="button" class="changeMainHeaderColor" data-color="light-blue"></button>
							<button type="button" class="changeMainHeaderColor" data-color="purple"></button>
						</div>
					</div>
					<input class="btn btn-primary btn-sm" type="button" 
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
        <div class="t-header-content">
				<table class="table info-table table-striped">
                     <tr><td><p style="font-size:15px; margin-bottom: 0">${mem.diaryname}</p></td></tr>
                </table>        
        </div>
      	 <div class="page-content-wrapper-inner">
          <div class="content-viewport">
                <div class="grid"> 
			<div class="grid-body"> 
			<div class="item-wrapper">
			<div class="table-responsive">
			<form name="sf">
			<input type="hidden" value="${mem.id}" name="id">
					<c:if test="${login eq mem.id}">
						<tr>
							<td colspan="5" style="text-align: right;">
								<a href="minihomewriteForm.do?id=${mem.id}">[글쓰기]</a>

							</td>
						</tr>
					</c:if>
				<input type="hidden" name="pageNum" value="1">
				<input type="hidden" name="id" value="${param.id}">
				<table class="background" class="table info-table table-striped" 
					style="text-align:center; border-color:#BDBDBD; width:100%;">
					<c:if test="${boardcnt == 0}">
						<tr>
							<td colspan="5">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${boardcnt > 0 }">
						<% int cnt=0; %>
						<c:forEach items="${list}" var="b">
						<c:if test="${login eq 'admin' || login eq b.id}">
						<style>
							div {
							    position:relative;
							    z-index:1;
							}
							.background:after {
								background-image:url('../board/picture/${b.backimg}');
							    top:0;
							    left:0;
							    position:absolute;
							    background-size:100%;
							    opacity:0.4!important;
							    filter:alpha(opacity=50);
							    z-index:-1;
							    content:"";
							    width:100%;
							    height:100%;
							}
						</style>
						<tr style="background-color: white" align="left"><td>
							<a href="minihomeupdateForm.do?num=${b.num}&boardnum=4&id=${b.id}">[수정]</a>
							<a href="delete.do?num=${b.num}&boardnum=4">[삭제]</a>
						</td></tr>
						</c:if>
						<c:set var="board" value="${board-1 }" />
						 <c:choose>
							<c:when test="${login eq 'admin' || login eq mem.id}">
								<tr style="background-color: white">
									<td style="font-size:15px">${b.subject}</td>
								</tr>
								<tr style="background-color: white; text-align: right;">
									<td><c:set var="today" value="<%=new java.util.Date()%>" />
										<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${b.regdate }" />
									</td>
								</tr>
								<tr style=" height:400px;">
									<td style=" padding: 10px;">
									<div>
										${b.content}
									</div>
									</td>
								</tr>
							</c:when>
							<c:when test="${login eq 'admin' || login ne mem.id}">
								<c:if test="${b.setpublic==1 }">
									<tr style="background-color: white;">
										<td>${b.subject}</td>
									</tr>
									<tr style="background-color: white; text-align: right;">
										<td><c:set var="today" value="<%=new java.util.Date()%>" />
											<fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${b.regdate }" />
										</td>
									</tr>
									<tr>
										<td style=" padding: 10px;">
											${b.content}
										</td>
									</tr>
								</c:if>
								<c:if test="${b.setpublic==2 }">
								</c:if>
							</c:when>
						  </c:choose>
						</c:forEach><!-- for구문 끝 -->
						
						<tr style="background-color: white;">
							<td colspan="5">
							<c:if test="${pageNum <=1}">[이전]</c:if> 
							<c:if test="${pageNum >1 }">
								<a href="javascript:listdo(${pageNum-1 })">[이전]</a>
								<%--검색시 다음페이지로 넘어가도 깨지지 않게 --%>
							</c:if> 
							<c:forEach var="a" begin="${startpage }" end="${endpage}">
								<c:if test="${a==pageNum }"> 
									[${a}] 
								</c:if>
								<c:if test="${a!=pageNum }">
									<a href="javascript:listdo(${a})">[${a}]</a>
								</c:if>
							</c:forEach> 
							<c:if test="${pageNum >= maxpage}">
								[다음]
							</c:if>
							<c:if test="${pageNum < maxpage}">
								<a href="javascript:listdo(${pageNum+1 })">[다음]</a>
							</c:if>
							</td>
							</tr>
					</c:if>
					<!-- else 구문 끝 -->
				</table>
				</form>
			</div>
			</div>
			</div>
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