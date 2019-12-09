<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내정보</title>
<script type="text/javascript">
	function win_upload(){
		var op = "width=400,height=150,left=50,top=150";
		open("pictureForm.jsp","",op)
	}
	
	function repass(){
		var op = "width=500, height=250, left=50, top=150"
		open("passwordForm.me","",op)
	}
	
	function inputcheck(f){
		<c:if test="${sessionScope.login != 'admin'}">
			if(f.pass.value==""){
				alert("비밀번호를 입력하세요")
				f.pass.focus()
				return false
			}
		</c:if>
	}
</script>
<style type="text/css">
	input{size: 10px;}
</style>
</head>
<body>
	
<!--  <div class="row">  -->
        <div class="col-lg-5 col-md-7 col-sm-9 col-11 mx-auto">
          <div class="grid" style="margin-bottom: 50px; align:center">
            <div class="grid-body">
              <div class="row">
                <div class="col-lg-7 col-md-8 col-sm-9 col-12 mx-auto form-wrapper">
                  <form action="update.me" name="f" method="post" onsubmit="return inputcheck(this)">
                  	<input type="hidden" name="picture" value="${info.picture}" />
                  	<table>
                  	<tr class="form-group input-rounded" align="center">
                  	   	<td rowspan="4"  colspan="3" valign="bottom">
							<img src="../member/picture/${info.picture}" width="150" height="150" id="pic"><br>
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
                    	<td>아이디</td>
                    	<td>
                      		<input type="text" class="form-control" value="${info.id}" name="id" readonly />
                      	</td>
                   	</tr>
                   	<tr class="form-group input-rounded">
                   		<td>비밀번호</td>
                    	<td colspan="3">
                    		<br>
	                    	<input type="password" class="form-control" placeholder="비밀번호를 입력하세요" name="pass"/>
	                    	<br>
	                    </td>
	                </tr>
                    <tr class="form-group input-rounded">
                    	<td>닉네임</td>
                    	<td colspan="3">
	                    	<input type="text" class="form-control" value="${info.name }" name="name"/>
	                    	<br>
	                    </td>
	                </tr>
                    <tr class="form-group input-rounded">
                    	<td>이메일</td>
                    	<td colspan="3">
	                    	<input type="email" class="form-control" value="${info.email }" name="email"/>
	                    	<br>
	                    </td>
	                </tr>
                    <tr class="form-group input-rounded">
                    	<td>다이어리이름</td>
                    	<td colspan="3">
                      		<input type="text" class="form-control" value="${info.diaryname}" name="diaryname"/>
                      		<br>
                    	</td>
                    </tr>
					<tr class="form-group input-rounded">
						<td colspan="3" align="center">
							<input type="submit" class="btn btn-outline-info btn-rounded" value="회원수정" onclick="javascript:inputcheck()" >
							<c:if test="${my.id != 'admin' && sessionScope.login!='admin' }">
								<a href="deleteForm.me?id=${info.id}">
									<input type="button" class="btn btn-outline-info btn-rounded" value="탈퇴하기">
								</a>
							</c:if>
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
<!--  </div>  -->
</body>
</html>