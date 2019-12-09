<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 상세 보기</title>
<style type="text/css">
	th{text-align:center;}
	table{font-size: medium;}
</style>
</head>
<body>
<div class="grid">
<div class="grid-body" style="align-content: center;">
<div class="form-group input-rounded">
<form style="width:80%">
<table border="1" style="border-color: #BDBDBD;align-content: center;" width="100%">
	<tr><th width="15%">글쓴이</th>
		<td width="85%" style="text-align:center;">${b.name}</td>
	</tr>
	<tr><th>제목</th>
		<td style="text-align:center">${b.subject }</td>
	</tr>
	<tr class="form-group input-rounded" align="center">
	  	<c:if test="${param.boardnum==1 }">
				<th>스티커</th>
				<td colspan="2" valign="middle">
				<img src="../board/picture/${b.img}" width="200" height="200" id="pic"><br>
				<br>
			</td>
			</c:if>
			<c:if test="${param.boardnum==2 }">
				<th>배경</th>
		  		<td colspan="2" valign="middle">
					<img src="../board/picture/${b.img}" width="300" height="200" id="pic"><br>
					<br>
				</td>
			</c:if>
	</tr>
	<tr><th>내용</th>
		<td style="text-align:center;">
			<table style="width:100%; height:150px;">
				<tr>
					<td style="border-width:0px; vertical-align:top; text-align:center">
						${b.content }
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="2" align="center">
<%-- 		<a href="replyForm.do?num=${b.num}">[댓글]</a> --%>
		<c:if test="${login eq 'admin' || login eq b.id}">
			<a href="imgupdateForm.do?num=${b.num}&boardnum=${b.boardnum}">
				<input type="button" class="btn btn-outline-info btn-rounded" value="수정">
			</a>
			<a href="delete.do?num=${b.num}&boardnum=${b.boardnum}">
				<input type="button" class="btn btn-outline-info btn-rounded" value="삭제">
			</a>
		</c:if>
		<a href="<c:if test='${b.boardnum==1}'>stickerboard.do?boardnum=1</c:if>
				<c:if test='${b.boardnum==2}'>backgroundboard.do?boardnum=2</c:if>
				<c:if test='${b.boardnum==3}'>board1.do?boardnum=3</c:if>">
				<input type="button" class="btn btn-outline-info btn-rounded" value="목록"></a>
		</td>
	</tr>
</table>
</form>
</div></div></div>
</body>
</html>