<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� �� ����</title>
<style type="text/css">
	th{text-align:center;}
	table{font-size: medium;}
</style>
</head>
<body>
<table border="2" width="100%">
	<tr><th width="20%">�۾���</th>
		<td width="80%" style="text-align:left">${b.name}</td>
	</tr>
	<tr><th>����</th>
		<td style="text-align:left">${b.subject }</td>
	</tr>
	<tr><th>����</th>
		<td>
			<table style="width:100%; height:250px;">
				<tr>
					<td style="border-width:0px; vertical-align:top; text-align:left">
						${b.content }
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><th>÷������</th>
		<td><c:if test="${empty b.file1}">
				&nbsp;		
			</c:if>
			<c:if test="${!empty b.file1}">
				<a href="file/${b.file1 }">${b.file1 }</a>
			</c:if>
			</td></tr>
	<tr><td colspan="2" align="center">
		<a href="replyForm.do?num=${b.num}">[���]</a>
		<c:if test="${login eq 'admin' || login eq b.id}">
			<a href="updateForm.do?num=${b.num}&boardnum=${b.boardnum}">[����]</a>
			<a href="delete.do?num=${b.num}&boardnum=${b.boardnum}">[����]</a>
		</c:if>
		<a href="board1.do?boardnum=3">[���]</a>
		</td>
	</tr>
</table>
</body>
</html>