<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>로그인 화면</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../../css/materialdesignicons.css" />
    <link rel="stylesheet" href="../../src/assets/vendors/css/vendor.addons.css" />
    <!-- endinject -->
    <!-- vendor css for this page -->
    <!-- End vendor css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="../../css/shared.css" />
    <!-- endinject -->
    <!-- Layout style -->
    <link rel="stylesheet" href="../../css/demo1.css">
    <!-- Layout style -->
    <link rel="shortcut icon" href="../../src/assets/images/favicon.ico" />
  </head>
  <body>
    <div class="authentication-theme auth-style_1">
      <div class="row">
        <div class="col-12 logo-section" style="width=100%">
        <table align="center">
 	       <caption><h1>Diary</h1></caption>
        </table>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-5 col-md-7 col-sm-9 col-11 mx-auto">
          <div class="grid">
            <div class="grid-body">
              <div class="row">
                <div class="col-lg-7 col-md-8 col-sm-9 col-12 mx-auto form-wrapper">
                  <form action="loginForm.me">
                    <div class="form-group input-rounded">
                      <input type="text" class="form-control" placeholder="id" name="id" />
                    </div>
                    <div class="form-group input-rounded">
                      <input type="password" class="form-control" placeholder="Password" name="pass"/>
                    </div>
                    <div class="form-inline">
                    </div>
                    <input type="submit" value="login" class="btn btn-primary btn-block"/>
                  </form>
                  <div class="signup-link">
                    <p>Don't have an account yet?</p>
                    <a href="joinForm.jsp">Sign Up</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
<!-- 
      <div class="auth_footer">
        <p class="text-muted text-center">© Label Inc 2019</p>
      </div>
 -->
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