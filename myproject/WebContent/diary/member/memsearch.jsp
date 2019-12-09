<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록보기</title>
<!-- <link rel="stylesheet" href="../../css/main.css"> -->
<style type="text/css">
	tr,td{padding:10px; font-size: 20px;}
	table,td,th{border: 1px solid #BDBDBD}
</style>
<script type="text/javascript">
	function minihome(id){
		var op = "width=1000, height=600, left=50, top=150, scrollbars=no, resizable=no "
		open(id,"",op);
	}
</script>
</head>
<body>
	<table style="width: 100%; height: 100%">
	<% int cnt=0; %>
		<c:forEach items="${mem}" var="m">
			<%cnt++ ; %>
			<c:if test="<%=cnt%4==1 %>">
				<tr class="form-group input-rounded" align="center">
			</c:if>
			<td width="25%" valign="bottom">
			<c:if test="${empty m.picture}">
	          	<img class="profile-img img-lg rounded-circle" src="../member/picture/사람.png"
					width="200" height="200" id="pic"><br> 
	        </c:if>
	        <c:if test="${!empty m.picture}">
				<img class="profile-img img-lg rounded-circle" src="../member/picture/${m.picture}"
					width="200" height="200" id="pic"><br> 
	        </c:if>
	        	ID : ${m.id}<br>
				<a href="javascript:minihome('../board/minihome.do?id=${m.id}')">${m.name}</a><br>
				${m.email }
			</td>
			<c:if test="<%=cnt%4==0 %>">
				</tr>
			</c:if>
		</c:forEach>
		<!-- 	<tr><td colspan="6"><a href="main.me">[나가기]</a></td></tr> -->
	</table>
</body>
</html>