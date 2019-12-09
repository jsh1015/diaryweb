<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ����Ϻ���</title>
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
	<table style="width:100%; height:100%">
		<tr align="center">
			<th colspan="4" style="font-size:20px; ">ȸ�� ���</th>
		</tr>
		<tr align="center">
			<th>���̵�</th>
			<th>�̸�</th>
			<th>�̸���</th>
			<th>����/Ż��</th>
		</tr>
		<c:forEach var="m" items="${memlist}">
			<tr align="center">
				<td>${m.id }</td>
				<td><a href="javascript:minihome('../board/minihome.do?id=${m.id}')">${m.name }</a></td>
				<td>${m.email}</td>
				<td><a href="../member/mypage.me?id=${m.id}">[����]</a> 
					<c:if test="${m.id != 'admin'}">
						<a href="delete.me?id=${m.id }">[����Ż��]</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<!-- 	<tr><td colspan="6"><a href="main.me">[������]</a></td></tr> -->
	</table>
</body>
</html>