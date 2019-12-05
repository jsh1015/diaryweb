<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<script type="text/javascript" src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<script>
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
		if(f.img.value==""){
			alert("이미지를 등록하세요")
			f.name.focus()
			return
		}
		f.submit()
	}
	function win_upload(){
		var op = "width=500, height=150, left=50, top=150";
		open("../board/pictureForm.jsp","",op)
	}
</script>
<style type="text/css">
	table{text-align:center;}
	textarea{width:50% !important;}
</style>
</head>
<body>
<form action="write.do" style="width:100%; height:100%" method="post" enctype="multipart/form-data" name="f">
<input type="hidden" value="${login}" name="id">
<input type="hidden" value="${param.boardnum}" name="boardnum">
<input type="hidden" name="img" value="" />
	<div class="grid">
      <table border="1" style="text-ailgn:center; border-collapse:collapse;" class="form-group input-rounded">
		<tr><th colspan="5">게시판 글쓰기</th></tr>
		<tr class="form-group input-rounded" align="center">
           	   	<td rowspan="4"  colspan="3" valign="bottom">
					<img src="" width="200" height="200" id="pic"><br>
					<font size="2">
						<a href="javascript:win_upload()">사진등록</a>
					</font>
				</td>
		</tr> 
		<tr><td>글쓴이</td><td><input size="150" class="form-control" type="text" value="${nickname}" name="name" readonly></td></tr>
		<tr><td>제목</td><td><input size="150" class="form-control" type="text" name="subject"></td></tr>
		<tr><td>내용</td><td><textarea class="form-control" rows="15" cols="20" name="content" id="content1"></textarea>
		</td></tr>
		<tr><td colspan="5">
			<input type="button" value="게시물 등록" onclick="inputcheck()"></td>
		</tr>
	</table>
	</div>
</form>
</body>
</html>