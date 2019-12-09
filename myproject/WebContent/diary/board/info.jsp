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
	td{text-align:center;}
</style>
</head>
<body>
<div class="grid">
<div class="grid-body" style="align-content: center;">
<div class="form-group input-rounded">
<form style="width:80%">
<table border="1" width="100%" style="border-color: #BDBDBD;align-content: center;">
	<tr><th width="15%">�۾���</th>
		<td width="85%">${b.name}</td>
	</tr>
	<tr><th>����</th>
		<td>${b.subject }</td>
	</tr>
	<tr><th>����</th>
		<td>
			<table style="width:100%; height:250px;">
				<tr>
					<td style="border-width:0px; vertical-align:top;">
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
		<%-- <a href="replyForm.do?num=${b.num}">[���]</a> --%>
		<c:if test="${login eq 'admin' || login eq b.id}">
			<a href="updateForm.do?num=${b.num}&boardnum=${b.boardnum}">
			<input type="button" class="btn btn-outline-info btn-rounded" value="����"></a>
			<a href="delete.do?num=${b.num}&boardnum=${b.boardnum}">
			<input type="button" class="btn btn-outline-info btn-rounded" value="����"></a>
		</c:if>
		<a href="board1.do?boardnum=3"><input type="button" class="btn btn-outline-info btn-rounded" value="���"></a>
		</td>
	</tr>
</table>
</form>
</div></div></div>
</body>
</html>