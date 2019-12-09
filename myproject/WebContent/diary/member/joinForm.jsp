<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>회원가입 화면</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../../css/materialdesignicons.css" />
    <link rel="stylesheet" href="../../src/assets/vendors/css/vendor.addons.css" />
    <link rel="stylesheet" href="../../css/shared.css" />
    <!-- endinject -->
    <!-- Layout style -->
    <link rel="stylesheet" href="../../css/demo1.css">
    <script type="text/javascript">
	function win_upload(){
		var op = "width=500, height=150, left=50, top=150";
		open("pictureForm.jsp","",op)
	}
	function inputcheck(){
		f = document.f;
		if(f.id.value==""){
			alert("아이디를 입력하세요")
			f.id.focus()
			return false;
		}
		if(f.pass.value==""){
			alert("비밀번호를 입력하세요")
			f.pass.focus()
			return false;
		}
		if(f.name.value==""){
			alert("닉네임을 입력하세요")
			f.name.focus()
			return false;
		}
		if(f.email.value==""){
			alert("이메일을 입력하세요")
			f.email.focus()
			return false;
		}
		f.submit()
	}
</script>
  </head>
  <body>
    <div class="authentication-theme auth-style_1" style="padding: 2% 0 2px 0;">
        <div class="col-12 logo-section" style="width=100%">
        <table align="center">
 	       <caption><h1>Diary</h1></caption>
        </table>
      </div>
      <div class="row">
        <div class="col-lg-5 col-md-7 col-sm-9 col-11 mx-auto">
          <div class="grid" style="margin-bottom: 50px;">
            <div class="grid-body">
              <div class="row">
                <div class="col-lg-7 col-md-8 col-sm-9 col-12 mx-auto form-wrapper">
                  <form action="join.me" name="f" method="post" style="text-align: center;">
                  	<input type="hidden" name="picture" value="" />
                  	<table  width="100%">
                  	<tr class="form-group input-rounded" align="center">
                  	   	<td rowspan="4"  colspan="3" valign="bottom">
							<img src="" width="100" height="120" id="pic"><br>
							<font size="1">
								<a href="javascript:win_upload()">사진등록</a>
							</font>
						</td>
					</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
                    <tr class="form-group input-rounded">
                    	<td colspan="3">
                      		<input type="text" class="form-control" placeholder="id" name="id"/>
                      	</td>
                      	<!-- <td>
                      		&nbsp;<input type="button" class="btn btn-primary btn-xs" value="아이디 중복확인"
                      			onclick="openIdChk()">
                      			<input type="hidden" name="inDuplication" value="idUncheck">
                      	</td> -->
                   	</tr>
                    <tr class="form-group input-rounded">
                    	<td colspan="3">
                    		<br>
                    		<input type="password" class="form-control" placeholder="Password" name="pass" />
                    		<br>
                      	</td>
                    </tr>
                    <tr class="form-group input-rounded">
                    	<td colspan="3">
	                    	<input type="text" class="form-control" placeholder="name" name="name"/>
	                    	<br>
	                    </td>
	                </tr>
                    <tr class="form-group input-rounded">
                    	<td colspan="3">
	                    	<input type="email" class="form-control" placeholder="email" name="email"/>
	                    	<br>
	                    </td>
	                </tr>
                    <tr class="form-group input-rounded">
                    	<td colspan="3">
                      		<input type="text" class="form-control" placeholder="diaryname" name="diaryname"/>
                      		<br>
                    	</td>
                    </tr>
                    <tr class="form-group input-rounded">
                    <tr>
    	                <td colspan="3">
	        	            <button type="button" onclick="inputcheck()" class="btn btn-primary btn-block">회원가입</button>
	        	            <br>
        	            </td>
        	        </tr>
                    <tr class="form-group input-rounded">
        	            <td colspan="3" align="center">
	        	            <a href="loginForm.jsp">로그인</a>
        	            </td>
        	        </tr>
                    </table>
                  </form>
                  <div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--page body ends -->
    <!-- SCRIPT LOADING START FORM HERE /////////////-->
    <!-- plugins:js -->
    <script src="../../src/assets/vendors/js/core.js"></script>
    <script src="../../src/assets/vendors/js/vendor.addons.js"></script>
    <!-- endinject -->
    <!-- Vendor Js For This Page Ends-->
    <!-- Vendor Js For This Page Ends-->
    <!-- build:js -->
    <script src="../../src/assets/js/template.js"></script>
    <!-- endbuild -->
  </body>
</html>