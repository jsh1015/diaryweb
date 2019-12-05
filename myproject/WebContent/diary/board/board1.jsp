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
</head>
<body>
	<form action="board1.do?boardnum=3" method="post" name="sf">
		<input type="hidden" name="pageNum" value="1">
		<input type="hidden" name="boardnum" value="3">
		<table class="table info-table table-bordered">
		<tr><td><p class="grid-header">자유게시판</p></td></tr>
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
				<table style="text-align: center; width:100%">
					<c:if test="${boardcnt == 0}">
						<tr>
							<td colspan="5">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${boardcnt > 0 }">
						<tr>
							<td colspan="5" style="text-align: left">글개수 : ${boardcnt }</td>
						</tr>
						<tr>
							<th width="8%">번호</th>
							<th width="50%">제목</th>
							<th width="14%">작성자</th>
							<th width="17%">등록일</th>
							<th width="11%">조회수</th>
						</tr>
						<c:forEach items="${list}" var="b">
						<tr>
							<td>${board}</td>
							<c:set var="board" value="${board-1 }" />
							<td style="text-align: left">
								<c:if test="${!empty b.file1}">
								<%--null이거나 비어있는 경우 empty --%>
									<a href="file/${b.file1 }" style="text-decoration: none;">@</a>
								</c:if> 
								<c:if test="${empty b.file1}">
									&nbsp;&nbsp;&nbsp; 
								</c:if> <%--답글 표시하기 --%> 
								<a href="info.do?num=${b.num}&boardnum=3"> ${b.subject}</a>
							</td>
							<td>${b.name}</td>
							<td><c:set var="today" value="<%=new java.util.Date()%>" />
								<fmt:formatDate pattern="yy-MM-dd" value="${today }"
										var="now" /> <fmt:formatDate pattern="yy-MM-dd"
										value="${b.regdate }" var="reg" /> 
								<c:if test="${reg==now}">
									<fmt:formatDate value="${b.regdate}" pattern="HH:mm:ss" />
								</c:if>
								<c:if test="${reg!=now}">
									<fmt:formatDate pattern="yy-MM-dd HH:mm" value="${b.regdate }" />
								</c:if>
							</td>
							<td>${b.cnt}</td>
						</tr>
						</c:forEach><!-- for구문 끝 -->
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
							<a href="writeForm.do?boardnum=3">[글쓰기]</a>
						</td>
					</tr>
				</table>
			</div>
			</div>
			</div>
		</div>
</body>
</html>