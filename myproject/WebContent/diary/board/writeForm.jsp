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
		f.submit()
	}
</script>
<style type="text/css">
	table{text-align:center;}
	textarea{width:50% !important;}
	.custom-file .custom-file-label:after {
    padding:0px;
    line-height: 0px;
}
</style>
</head>
<body>
<form action="write.do" style="width:100%; height:100%" method="post" enctype="multipart/form-data" name="f">
<input type="hidden" value="${login}" name="id">
<input type="hidden" value="${param.boardnum}" name="boardnum">
	<div class="grid">
      <table border="1" style="text-ailgn:center; border-collapse:collapse;" class="form-group input-rounded">
		<tr><th colspan="2">게시판 글쓰기</th></tr> 
		<tr><td>글쓴이</td><td><input size="150" class="form-control" type="text" value="${nickname}" name="name" readonly></td></tr>
		<tr><td>제목</td><td><input size="150" class="form-control" type="text" name="subject"></td></tr>
		<tr><td>내용</td><td><textarea class="form-control" rows="15" cols="20" name="content" id="content1"></textarea>
		<script>CKEDITOR.replace("content1", {
			filebrowserImageUploadUrl : "imgupload.do"
		})
		</script>
		</td></tr>
		<tr>
		<td>첨부파일</td>
<!-- 		<td class="custom-file">
                <input type="file" class="custom-file-input" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
        </td>
 -->		<td><input type="file" name="file1"></td>
		</tr>
		<tr><td colspan="2">
			<input type="button" class="btn btn-outline-success btn-rounded" value="게시물 등록" onclick="inputcheck()"></td>
		</tr>
	</table>
	</div>
</form>
</body>
</html>