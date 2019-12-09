<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
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

<!-- <script type="text/javascript" src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>  -->
<script type="text/javascript" src="../../ckeditor/ckeditor.js"></script>
<script type="text/javascript">
		function inputcheck(){
			f = document.f;
			if(f.name.value==""){
				alert("글쓴이를 입력하세요")
				f.name.focus()
				return
			}
			if(f.subject.value==""){
				alert("제목을 입력하세요")
				f.subject.focus()
				return
			}
			f.submit()
		}
</script>
<style type="text/css">
	@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	html,body,h1,h2,h3,h4,h5,h6,p,a {font-family: 'Jeju Gothic', serif;}
	.table.info-table tr td:first-child, .table.info-table tr th:first-child,
	table.info-table tr td:first-child, table.info-table tr th:first-child
	{
	text-align: center;
	}

    .custom-template .title {
    padding: 10px;
    text-align: left;
    font-size: 16px;
    font-weight: 600;
    color: #ffffff;
    border-top-left-radius: 5px;
    border-bottom: 1px solid #ebedf2;
    background: #5867dd;
}
};
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
      
      <div class="custom-template">
			<div class="custom-content">
				<div class="switcher" style="overflow:auto; overflow-x:hidden;  height:250px;">
					<div class="switch-block" style="width:300px;">
						<p style="text-align: center; font-size:15px;">스티커</p>
						<table>
							<% int cnt=0; %>
							<c:forEach items="${s}" var="s">
							<% cnt++; %>
							<c:if test="<%=cnt%4==1 %>">
								<tr>
							</c:if>
							<td>
								<a href="javascript:sticker('${s.img}')">
								<img class="profile-img img-lg rounded-circle" src="../board/picture/${s.img}" 
									alt="sticker" style="width:60px; height:60px"> 
								</a>
							</td>
							<c:if test="<%=cnt % 4 == 0%>">
								</tr>
							</c:if>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="custom-content">
				<div class="switcher" style="overflow:auto; overflow-x:hidden; height:250px;">
					<div class="switch-block" style="width:300px;">
						<p style="text-align: center; font-size:15px;">배경</p>
						<table>
							<% cnt=0; %>
							<c:forEach items="${b}" var="b">
							<% cnt++; %>
							<c:if test="<%=cnt%4==1 %>">
								<tr>
							</c:if>
							<td>
								<a href="javascript:background('${b.img}')">
								<img class="profile-img img-lg rounded-circle" src="../board/picture/${b.img}" 
									alt="sticker" style="width:60px; height:60px"> 
								</a>
							</td>
							<c:if test="<%=cnt % 4 == 0%>">
								</tr>
							</c:if>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="custom-toggle">
				다꾸함
				<!-- <i class="flaticon-settings"></i> -->
			</div>
		</div>
      
      <!-- 내가만든 리스트 -->
			<div class="grid">
		<form action="minihomeupdate.do?boardnum=4&num=${board.num}" style="width: 95%; height: 100%; margin-left:10px; margin-top: 10px; align-content: center; "
			method="post" enctype="multipart/form-data" name="f">
					<input type="hidden" value="${board.id}" name="id"> 
				<table style="text-ailgn: center; border-collapse: collapse;" class="form-group input-rounded">
					<tr>
						<td>글쓴이</td>
						<td><input size="150" class="form-control" type="text"
							value="${board.name}" name="name" readonly></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input size="150" class="form-control" type="text"
							name="subject" value="${board.subject}"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
						<textarea class="form-control" rows="24" cols="20"
								name="content" id="content1">${board.content}</textarea> 
						<script type="text/javascript">
								CKEDITOR.replace("content1",{
							 		language: "ko",
									filebrowserImageUploadUrl : "imgupload.do"
								})
							 	function sticker(i) {
							 	    var html = "<img src='${path}/diary/board/picture/"+i+"' width='100' height='100'>";
							 	    CKEDITOR.instances.content1.insertHtml(html);
						 	  	}
								function background(i) {
									document.f.backimg.value = i;
						 	  	}
						</script>
						</td>
					</tr>
					<tr>
						<td>배경</td>
						<td><input class="form-control" type="text" name="backimg" value="${board.backimg }" readonly></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="radio" name="setpublic" value="1" checked >공개
							<input type="radio" name="setpublic" value="2" >비공개
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<a href="javascript:document.f.submit()" style="color: black;"> 
								<input class="btn btn-outline-success btn-rounded" type="button" value="게시물수정"></a> 
							<a href="javascript:history.go(-1)" style="color: black;"> 
								<input class="btn btn-outline-success btn-rounded" type="button" value="취소"></a>
						</td>
					</tr>
				</table>
		</form>
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