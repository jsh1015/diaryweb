<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록 보기</title>
<script>
	function listdo(page){ //list.do를 위에서 실행하게 함
		document.sf.pageNum.value=page;
		document.sf.submit();
	}
</script>
<style type="text/css">
	th, td {
		padding: 10px;
	}
</style>
</head>
<body>
 		<div class="grid"> 
			<div class="grid-body"> 
			<div class="item-wrapper">
			<div class="table-responsive">
				<table class="table info-table table-bordered">
					<tr><td colspan="5"><p class="grid-header">내 다꾸함</p></td></tr>
				</table>
				<table style="text-align:center; width:100%;">
					<c:if test="${decocnt == 0}">
						<tr>
							<td colspan="5">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${decocnt > 0 }">
						<tr height="5">
							<td border="1" colspan="5" style="text-align: left; padding: 10px; ">
							다꾸개수 : ${decocnt }<br><br>
								분류 (<a href="deco.do?cate=1">스티커</a>&nbsp;&nbsp;
									<a href="deco.do?cate=2">배경</a>)<br>
								<hr>
							</td>
						</tr>
						<ul>
						<% int cnt=0; %>
							<c:forEach items="${list}" var="b">
							<% cnt++; %>
							<c:set var="board" value="${board-1 }" />
							<c:if test="<%=cnt%5==1 %>">
								<tr class="form-group input-rounded" align="center">
							</c:if>
								<td valign="bottom"><img src="../board/picture/${b.img }"
									width="150" height="150" id="pic"><br>
									<a href="decodelete.do?num=${b.num}">[삭제]</a>
								</td>
							<c:if test="<%=cnt % 5 == 0%>">
								</tr>
							</c:if>
							</c:forEach>
						</ul>
					</c:if>
				</table>
			</div>
			</div>
			</div>
		</div>
</body>
</html>