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
	<form action="backgroundboard.do?boardnum=2" method="post" name="sf">
		<input type="hidden" name="pageNum" value="1">
		<input type="hidden" name="boardnum" value="2">
		<table class="table info-table table-bordered">
		<tr><td><p class="grid-header">배경지 공유 게시판</p></td></tr>
		<tr>
			<td style="text-align:center; border-width:0px;">
			<select name="column">
				<option value="">선택하세요</option>
				<option value="subject">제목</option>
				<option value="name">작성자</option>
				<option value="subject,name">제목+작성자</option>
			</select>
			<script>
				document.sf.column.value="${param.column}"; //선택한 column의 값을 column.value에 넣어줌
			</script>
			<input type="text" name="find" value="${param.find}" style="width:50%">
			<input type="submit" value="검색">
			</td>
		</tr>
		</table>
	</form>
 		<div class="grid"> 
			<div class="grid-body"> 
			<div class="item-wrapper">
			<div class="table-responsive">
				<table style="text-align:center; width:100%;">
					<c:if test="${boardcnt == 0}">
						<tr>
							<td colspan="5">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${boardcnt > 0 }">
						<tr height="5">
							<td border="1" colspan="5" style="text-align: left; padding: 10px; ">글개수 : ${boardcnt }</td>
						</tr>
						<ul>
						<% int cnt=0; %>
						<c:forEach items="${list}" var="b">
							<% cnt++; %>
						<c:set var="board" value="${board-1 }" />
						<c:if test="<%=cnt%5==1 %>">
							<tr class="form-group input-rounded" align="center">
						</c:if>
		                  	   	<td valign="bottom">
									<img src="../board/picture/${b.img }" width="150" height="150" id="pic"><br>
									<a style="font-size:medium;" href="imginfo.do?num=${b.num}&boardnum=2"> ${b.subject}</a>
								<br>
								작성자 : ${b.name}<br>
								좋아요 : ${b.likenum}<br>
											<a href="like.do?id=${login}&num=${b.num}&boardnum=${b.boardnum}">
											<c:if test="${b.likechk==0}">
												<button type="button" class="btn action-btn btn-like btn-outline-danger btn-rounded">
													<i class="mdi mdi-heart-outline"></i>
												</button>
											</c:if>
											<c:if test="${b.likechk==1}"><!-- 좋아요 눌러져있을때 -->
												<button type="button" class="btn action-btn btn-like btn-outline-danger btn-rounded clicked">
													<i class="mdi clicked mdi-heart"></i>
												</button>
											</c:if>
											</a>
											<a href="decoinsert.do?img=${b.img}&boardnum=2">
												<button class="btn action-btn btn-like btn-outline-danger btn-rounded">
												저장
												</button>
											</a>
								</td>

									<c:if test="<%=cnt%5==0 %>">
							</tr>
						</c:if>
							<!-- </tr> -->
						</c:forEach><!-- for구문 끝 -->
						</ul>
						<tr>
							<td colspan="5">
							<c:if test="${pageNum <=1}">[이전]</c:if> 
							<c:if test="${pageNum >1 }">
								<a href="javascript:listdo(${pageNum-1 })">[이전]</a>
								<%--검색시 다음페이지로 넘어가도 깨지지 않게 --%>
							</c:if> 
							<c:forEach var="a" begin="${startpage }" end="${endpage}">
								<c:if test="${a==pageNum }"> 
									[${a}] 
								</c:if>
								<c:if test="${a!=pageNum }">
									<a href="javascript:listdo(${a})">[${a}]</a>
								</c:if>
							</c:forEach> 
							<c:if test="${pageNum >= maxpage}">
								[다음]
							</c:if>
							<c:if test="${pageNum < maxpage}">
								<a href="javascript:listdo(${pageNum+1 })">[다음]</a>
							</c:if>
							</td>
							</tr>
					</c:if>
					<!-- else 구문 끝 -->
					<tr>
						<td colspan="5" style="text-align: right">
							<a href="imgwriteForm.do?boardnum=2">[글쓰기]</a>
						</td>
					</tr>
				</table>
			</div>
			</div>
			</div>
		</div>
</body>
</html>