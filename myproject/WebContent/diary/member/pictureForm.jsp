<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ���������</title>
<link rel="stylesheet" href="../../css/materialdesignicons.css">
<link rel="stylesheet" href="../../css/shared.css">
<link rel="stylesheet" href="../../css/demo1.css">
<style type="text/css">
	.btn{height:30px;}
</style>
</head>
<body>
	<div class="col-lg-5 col-md-7 col-sm-9 col-11 mx-auto">
		<div class="grid" style="margin-bottom: 50px; align: center">
			<div class="grid-body">
				<div class="row">
				<div class="col-lg-7 col-md-8 col-sm-9 col-12 mx-auto form-wrapper">
				<form action="picture.me" method="post" enctype="Multipart/form-data">
				<table>
					<tr><th style="text-align:center">�������ε�</th></tr>
					<tr><td><input class="btn btn-outline-primary btn-rounded" type="file" value="���ϼ���" name="picture"></td></tr>
					<tr><td><input type="submit" 
							class="btn btn-primary btn-block mt-0" value="�������"></td></tr>
				</table>
				</form>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>