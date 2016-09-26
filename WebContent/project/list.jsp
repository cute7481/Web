<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" 
import="java.util.*, yoon.board.model.BoardDTO"%>

<body style="margin: 0px;" bgcolor="transparent">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>

    <%
   		ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("list");
   		String t = (String)request.getAttribute("tok");
   		String kw = (String)request.getAttribute("keyword");
    %>
	
		<p id="board_title">일상 게시판</p>
	  	<hr width="90%" color="White"  noshade>
		<table class="board1" style="table-layout:fixed;word-break:break-all;">
		  	<thead class="board1-head">	
			<tr align="center">
			  <th width="10%" class="num"><b>번호</b></th>
			  <th width="50%" class="b-title"><b>제목</b></th>
			  <th width="15%" class="auth"><b>글쓴이</b></th>
			  <th width="15%" class="b-date"><b>날짜</b></th>
			  <th width="10%" class="b-cl"><b>조회수</b></th>
			</tr>
			</thead>
	<%
		if(list != null){
			for(BoardDTO dto : list){
	%>
			<tr class="board-normal">
			  <td width="10%" class="num" align="center"><%=dto.getNum() %></td>
			  <td width="60%"class="b-title">
			  <a href="board.do?method=content&num=<%=dto.getNum()%>"><%=dto.getSubject()%>
     <%
     				if(dto.getReplycnt() != 0 ){
     %>
     					[<font style="color:#F6CEF5"><%=dto.getReplycnt() %></font>]				
     <%
     				}
     %>
              </a>    
			  </td>
			  <td width="10%" class="auth" align="center"><%= dto.getNick()%></td>
			  <td width="15%" class="b-date" align="center"><%= dto.getRdate()%></td>
			  <td width="5%" class="b-cl" align="center"><%= dto.getReadnum()%></td>
			</tr>
	<%
			}
		}
	%>
	  </table>
	  <table id="search_write">
	  <tr>
	  	<td align="left" width="70%" style="padding-top:15px;">
	    <form action="board.do?method=list" method="get">
		  	<select name="stoken">
				<option value="sub">제목</option>
				<option value="con">내용</option>
				<option value="consub">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			<input type="text" name="search" id="input_search" size="15">
			<input type="submit" value="검색">
		</form>
		</td>
	<%
		if(mbdto != null){
	%>		
			<td align="right" width="30%"><a id="board_write"
			href="board.do?method=write&id=<%=mbdto.getId()%>">글쓰기</a></td>
		<%
		}
		%>
	  </tr>
	  </table>

<div align="center">
 <%
	int total = (int)request.getAttribute("total");	
 	int currentPg = (int)request.getAttribute("pg");
 	if(currentPg>1){
 %>
		<a class="paging" href="board.do?method=list&pg=1&stoken=<%=t%>&search=<%=kw%>">
        	< < </a>&nbsp;&nbsp;
        <a class="paging" href="board.do?method=list&pg=<%=currentPg-1%>&stoken=<%=t%>&search=<%=kw%>">
			<&nbsp; 이전</a>
 <%
 	}
 	int tempNum=1;
 	if(currentPg>4){
 		tempNum=currentPg-4;
 	}
 	for(int i=tempNum;i<=currentPg+4;i++){
 		if(i>total)break;
 		if(i == currentPg){
%>
 			<b class="paging" style="color: #CEE3F6"><%= i %></b>
<%
 		}else{
%>
 			<a class="paging" href="board.do?method=list&pg=<%=i %>&stoken=<%=t%>&search=<%=kw%>"><%=i %></a>
<%	
 		}
 	}
	if(currentPg < total) {
%>
		<a class="paging" href="board.do?method=list&pg=<%=currentPg+1%>&stoken=<%=t%>&search=<%=kw%>">
 			다음&nbsp;></a>&nbsp;&nbsp;
 		<a class="paging" href="board.do?method=list&pg=<%=total%>&stoken=<%=t%>&search=<%=kw%>">> ></a>
<%		
	} 
%>
</div>
</div>
</div>
</body>	

<script type="text/javascript">
$(document).ready(function(){
	$('a').mouseenter(function(){
		$(this).css('color','#707070');
	})
	$('a').mouseleave(function(){
		$(this).css('color','white');
	})
	$('a').mousedown(function(){
		$(this).css('color','#505050');
	})
	$('a').mouseup(function(){
		$(this).css('color','#777777');
	})
});
</script>