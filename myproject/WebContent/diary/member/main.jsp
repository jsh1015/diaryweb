<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%

int action = 0; //up(1) down(0)
int currYear = 0;
int currMonth = 0;
String boxSize = "90";

Calendar c = Calendar.getInstance();
Calendar cal = Calendar.getInstance();

if(request.getParameter("action") == null) {
    
     currMonth = c.get(Calendar.MONTH);
     currYear = c.get(Calendar.YEAR);
     cal.set(currYear,currMonth,1);
    
} else {
    
     if(request.getParameter("action") != null){
         
          currMonth = Integer.parseInt(request.getParameter("month"));
          currYear = Integer.parseInt(request.getParameter("year"));
         
          if(Integer.parseInt(request.getParameter("action"))==1) {

               cal.set(currYear, currMonth, 1);
               cal.add(Calendar.MONTH, 1); //다음달
               currMonth = cal.get(Calendar.MONTH);
               currYear = cal.get(Calendar.YEAR);
              
          } else {              

               cal.set(currYear, currMonth, 1);
               cal.add(Calendar.MONTH, -1); //이전달
               currMonth = cal.get(Calendar.MONTH);
               currYear = cal.get(Calendar.YEAR);         
          }
         
     }
}
%>
<%!
     public boolean isDate(int y, int m, int d) {
          m -= 1;
          Calendar c = Calendar.getInstance();
          c.setLenient(false);         
          try {              
               c.set(y, m, d);
               Date dt = c.getTime();              
          } catch(IllegalArgumentException e) {
               return false;
          }
          return true;
     }
%>
<%!
     public String getTitle(Calendar cal){
         
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
          return sdf.format(cal.getTime());    
     }
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
<script type="text/javascript">
	function minihome(id){
		var op = "width=1000, height=500, left=50, top=150, scrollbars=no, resizable=no "
		open(id,"",op);
	}
</script>
<style>
		#calendarTable, #calendarTable th, #calendarTable td {
			border-collapse: collapse;
		}
		
		#calendarTable {
			border: 1px solid #A6A6A6;
			width: 519px;
		}
		
		#calendarTable th, #calendarTable td {
			width: <%=boxSize%>px;
			border: 1px solid #A6A6A6;
			border-collapse: collapse;
			padding: 5px;
		}
		
		#calendarTable th {
			background-color: #696ffb;
			color: #fff;
		}
		
		#calendarTable td {
			height: 60px;
			text-align: left;
		}
		
		#calendarTable td.empty {
			background-color: #E8D9FF;
		}
		
		#calendarTable td.toDayColor {
			background-color: #D1B2FF;
		}
		.grid-header{
			padding: 0px 0px;
			margin-bottom: 0px;
		}
</style>
</head>
<body bgcolor='white'>
	<table width="100%">
		<tr height="100%">
			<td rowspan="2" text-align="center">
				<div class="table-responsive"  align="center">
					<table border='0' width="100%" celpadding='0' cellspacing='0'>
						<tr>
							<td align='right' valign='middle'>
								<a href="main.me?month=<%=currMonth%>&year=<%=currYear%>&action=0">
									<font size="2">이전달</font>
								</a>
							</td>
							<td  align='center' valign='middle'><b><%= getTitle(cal)%></b></td>
							<td  align='left' valign='middle'>
								<a href="main.me?month=<%=currMonth%>&year=<%=currYear%>&action=1">
									<font size="2">다음달</font>
								</a>
							</td>
						</tr>
					</table>
					<table width="100%">
						<tr>
							<td width="100%" align="center">
								<table id="calendarTable">
									<tr>
										<th>일</th>
										<th>월</th>
										<th>화</th>
										<th>수</th>
										<th>목</th>
										<th>금</th>
										<th>토</th>
									</tr>
									<%
     int currDay;
     String todayColor;
     int count = 1;
     int dispDay = 1;
     for (int w = 1; w < 7; w++)
     {
%>
									<tr>
										<%
          for (int d = 1; d < 8; d++)
          {
               if (!(count >= cal.get(Calendar.DAY_OF_WEEK))) //  
               {%>
										<td class="empty">&nbsp;</td>
										<%				count += 1;
               }else{
                    if (isDate (currYear, currMonth + 1,dispDay) ){
                         //오늘
                         if (dispDay == c.get(Calendar.DAY_OF_MONTH) &&
                            c.get(Calendar.MONTH) == cal.get(Calendar.MONTH) 
                                && c.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ){
                             todayColor = "class='toDayColor'";
                         }
                         else{
                             todayColor = "";
                         }
%>
										<td <%=todayColor%>><%=dispDay%><br></td>
										<%
                         count += 1;
                         dispDay += 1;
                    }
                    else
                    {
%>
										<td class="empty">&nbsp;</td>
										<%
                    }
               }
       }
%>
									</tr>
									<%
}
%>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
			
			<td>
				<div class="table-responsive" style="padding-left: 25px;">
					<table border="1" style="text-align: center;">
					<tr><td colspan="5"><p class="grid-header">인기스티커</p></td></tr>
						<tr>
							<th width="8%">번호</th>
							<th width="50%">제목</th>
							<th width="14%">작성자</th>
							<th width="17%">등록일</th>
							<th width="11%">좋아요</th>
						</tr>
					<% int cnt = 0; %>
						<c:forEach items="${stickerlist}" begin="0" end="9" var="b">
						<tr>
						<%cnt++; %>
							<td><%=cnt %></td>
							<td style="text-align: left">
								<c:if test="${!empty b.file1}">
								<%--null이거나 비어있는 경우 empty --%>
									<a href="file/${b.file1 }" style="text-decoration: none;">@</a>
								</c:if> 
								<c:if test="${empty b.file1}">
									&nbsp;&nbsp;&nbsp; 
								</c:if> <%--답글 표시하기 --%> 
								<a href="../board/imginfo.do?num=${b.num}&boardnum=1"> ${b.subject}</a>
							</td>
							<td><a href="javascript:minihome('../board/minihome.do?id=${b.id}')">
									${b.name}
								</a>
							</td>
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
							<td>${b.likenum}</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="table-responsive" style="padding-left: 25px;">
					<table border="1" style="text-align: center;">
					<tr><td colspan="5"><p class="grid-header">인기배경</p></td></tr>
						<tr>
							<th width="8%">번호</th>
							<th width="50%">제목</th>
							<th width="14%">작성자</th>
							<th width="17%">등록일</th>
							<th width="11%">좋아요</th>
						</tr>
					<% cnt = 0; %>
						<c:forEach items="${backgroundlist}" begin="0" end="9" var="b">
						<tr>
						<%cnt++; %>
							<td><%=cnt %></td>
							<td style="text-align: left">
								<c:if test="${!empty b.file1}">
								<%--null이거나 비어있는 경우 empty --%>
									<a href="file/${b.file1 }" style="text-decoration: none;">@</a>
								</c:if> 
								<c:if test="${empty b.file1}">
									&nbsp;&nbsp;&nbsp; 
								</c:if> <%--답글 표시하기 --%> 
								<a href="../board/imginfo.do?num=${b.num}&boardnum=2"> ${b.subject}</a>
							</td>
							<td><a href="javascript:minihome('../board/minihome.do?id=${b.id}')">
									${b.name}
								</a>
							</td>
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
							<td>${b.likenum}</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>