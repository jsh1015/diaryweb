<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원탈퇴 비밀번호 입력 화면</title>
<style type="text/css">
	.btn{height:30px;}
</style>
</head>
<body>
	<div class="col-lg-5 col-md-7 col-sm-9 col-11 mx-auto">
		<div class="grid" style="margin-top: 50px; align: center">
			<div class="grid-body">
				<div class="row">
				<div class="col-lg-7 col-md-8 col-sm-9 col-12 mx-auto form-wrapper">
				<form action="delete.me" method="post">
					<input type="hidden" name="id" value='${param.id}'>
					<table>
						<tr align="center">
							<th colspan="2">비밀번호 입력</th>
						</tr>
						<tr class="form-group input-rounded">
							<td>pw&nbsp;&nbsp;</td>
							<td><input size="20" class="form-control" placeholder="비밀번호를 입력하세요" type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan="2"><input class="btn btn-primary btn-block mt-0" type="submit" value="탈퇴하기"></td>
						</tr>
					</table>
				</form>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>