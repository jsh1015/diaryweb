<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<meta charset="EUC-KR">
<head>
<%--
	1. 파일업로드 => java
	2. opener 화면에 경과 전달 =>javascript
	3. 현재 화면 close() =>javascript
 --%>

 <script type="text/javascript">
	img = opener.document.getElementById("pic"); //opener이미지에게 알려줌
	img.src = "picture/${fname}";//request객체에 fname이 필요 
	opener.document.f.img.value="${fname}"; //opener에 파라미터값으로 전달
	self.close();
 </script>
