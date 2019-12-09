<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 화면</title>
<script>
	function file_delete(){
		document.f.file2.value=""; //기본에 있는 내용을 없애버림
		file_desc.style.display="none"; //div를 아예 보이지 않게(실제 데이터는 지우지 않음)
	}
</script>
<style type="text/css">
	th{text-align:center;}
</style>
</head>
<body>
<div class="grid">
<div class="grid-body" style="align-content: center;">
<div class="form-group input-rounded">
<form action="update.do?boardnum=${b.boardnum }&num=${b.num}" method="post" enctype="multipart/form-data" name="f" style="width:80%">
	<input type="hidden" name="file2" value="${b.file1}"><%--수정전의 이미지를 가져오기 위해 --%>
	<table border="1" style="align-content: center; border-color:#BDBDBD">
		<tr>
			<th>글쓴이</th>
			<td><input class="form-control" type="text" name="name" value="${b.name }" readonly></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input class="form-control" type="text" name="subject" value="${b.subject }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea class="form-control" rows="15" name="content" id="content1">${b.content }</textarea>
			</td>
			<script>CKEDITOR.replace("content1", {
			filebrowserImageUploadUrl : "imgupload.do"
			});
			</script>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td style="text-align:left">
				<c:if test="${!empty b.file1 }">
					<div class="form-control" id="file_desc">${b.file1}
					<a href="javascript:file_delete()">[첨부파일 삭제]</a></div>
				</c:if>
					<input class="form-control" type="file" name="file1">
			</td>
		</tr>
		<tr><th colspan="2">
			<a href="javascript:document.f.submit()" style="color:black;">
				<input class="btn btn-outline-success btn-rounded" type="button" value="게시물수정"></a>
			<a href="javascript:history.go(-1)" style="color:black;">
				<input class="btn btn-outline-success btn-rounded" type="button" value="취소"></a></th>
		</tr>
	</table>
</form>
</div></div></div>
</body>
</html>