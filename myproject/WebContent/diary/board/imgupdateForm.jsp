<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	th{text-align:center;}
	table{font-size: medium;}
</style>
<meta charset="UTF-8">
<title>게시판 수정 화면</title>
<script>
	function file_delete(){
		document.f.file2.value=""; //기본에 있는 내용을 없애버림
		file_desc.style.display="none"; //div를 아예 보이지 않게(실제 데이터는 지우지 않음)
	}
	
	function win_upload(){
		var op = "width=500, height=150, left=50, top=150";
		open("../board/pictureForm.jsp","",op)
	}
</script>
</head>
<body>
<form action="update.do?boardnum=${b.boardnum }&num=${b.num}" method="post" enctype="multipart/form-data" name="f" style="width:80%">
	<input type="hidden" name="file2" value="${b.file1}">
	<input type="hidden" name="img" value="" />
	<table border="2" width="100%">
		<tr><th width="30%">글쓴이</th>
			<td width="70%"><input type="text" name="name" value="${b.name }"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" value="${b.subject }"></td>
		</tr>
		<tr class="form-group input-rounded" align="center">
	  		<td colspan="2" valign="middle">
				<img src="../board/picture/${b.img}" width="150" height="150" id="pic"><br>
				<font size="1">
					<a href="javascript:win_upload()">사진등록</a>
				</font>
				<br>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="15" name="content" id="content1">${b.content }</textarea>
			</td>
		</tr>
		<tr><th colspan="2">
			<a href="javascript:document.f.submit()" style="color:black;">
				<input type="button" value="게시물수정"></a>
			<a href="javascript:history.go(-1)" style="color:black;">
				<input type="button" value="취소"></a></th>
		</tr>
	</table>
</form>
</body>
</html>