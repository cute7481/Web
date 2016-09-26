<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" 
import="java.util.*, yoon.board.model.BoardDTO, yoon.reply.model.ReplyDTO"%>

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
	BoardDTO dto = (BoardDTO)request.getAttribute("contDto");
	ArrayList<ReplyDTO> relist = (ArrayList<ReplyDTO>)request.getAttribute("replylist");
	String cont = dto.getContent().replace("\r\n","<br>");
%>
  <p id="board_title">일상 게시판</p>
	  <hr width="90%" color="White" noshade>
	  <table class="board1">
	  <tr class="board-normal">
		  <td align="center">소소한 일상 공유</td>
	  </tr>
	  </table>
	  <table class="board1">  
		<tr class="board-normal">
		  <td align="left" width="15%" height="40">
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>번호</b>
		  &nbsp;&nbsp;&nbsp;<%= dto.getNum()%></td>
		  <td align="center" width="20%" height="40"><b>작성자</b>
		  &nbsp;&nbsp;&nbsp;<%=dto.getNick()%></td>
		  <td align="center" width="20%"><b>날짜</b>
		  &nbsp;&nbsp;&nbsp;<%=dto.getRdate()%></td>
		  <td align="right" width="15%"><b>조회수</b>&nbsp;&nbsp;&nbsp;
		  <%=dto.getReadnum()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr class="board-normal">
		  <td align="left" height="40">
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>제목 </b>
		  &nbsp;&nbsp;&nbsp;<%=dto.getSubject() %></td>
		</tr>
		<tr class="board-normal">
		  <td colspan="4" style="padding-left:10%;padding-right:10%;height:500px"><%= cont %></td>
		</tr>
<%
if(mbdto != null){
	if(mbdto.getId().equals(dto.getId())){
%>
		<tr class="board-normal">
		  <td colspan="4" align="center" height="40">
			<a href="board.do?method=list">목록</a><b>   |   </b> 
			<a href="board.do?method=edit&num=<%=dto.getNum()%>">편집</a><b>   |   </b> 
			<form id="del_cont" action="board.do?method=del" method="post" style="display:inline;">
				<input type="hidden" name="num" value="<%=dto.getNum()%>"><a id="del_cont_btn">삭제</a>
			</form>
		  </td>
		</tr>
		<%
		
			}
		%>
		</table>
		<table class="board1" width='600' align='center' cellpadding='2'>
			<tr class="board-normal">
				<td align="center" height='32'>댓글 작성</td>
			</tr>	
			<tr class="board-normal">
				<td align="center">
				<br>
				<form id="reply_form" method="post" action="board.do?method=reply" >
				<input type="hidden" value="<%=mbdto.getId()%>" name="replyid">
				<input type="hidden" name="num" value="<%=dto.getNum() %>">
				<textarea name="reply" id="reply_area"></textarea>&nbsp;&nbsp;
				<input type="button" id="btn_reply"" value="작성">
				</form>
				</td>
			</tr>		
		</table>
	<%
		}		
	%>
	<table class="board1" id="reply_board"> 
		 <tr class= "board-normal">
		<%
			if(mbdto != null){//로그인 했을 경우
		%>
			     <td align="center" colspan="3" height="40">댓   글</td>
		<%
			}else{
		%>
			     <td align="center" colspan="3" height="40">댓   글 (회원)</td>
		<%
			}
		%>          
		 </tr>
	<%
		if(relist != null){
			for(ReplyDTO rdto : relist){
				String rcont = rdto.getReply().replace("\r\n","<br>");
				if(rdto.getPic()==null)rdto.setPic("img/default_profile.png");	
	%>		
			<form action="board.do?method=redel" method="post" id="reply<%=rdto.getNum()%>">
			<tr class="board-normal">
				<input type="hidden" name="bnum" value="<%=dto.getNum()%>">
				<td align="center" width="22%"><img align="center" src="<%=rdto.getPic()%>" 
				width="50px" height="50px">&nbsp;&nbsp;<%=rdto.getNick()%></td>
					<%
						if(mbdto != null){//로그인 했을 경우
							if(rdto.getId().equals(mbdto.getId())){//세션아이디랑 리플아이디 같을 경우
					%>
							<td align="left" width="52%" style="table-layout:fixed;
							word-break:break-all;"><br><%=rcont%><br>&nbsp;</td>
							<td align="center" width="26%"><%=rdto.getCtime()%>&nbsp;&nbsp;
							<input type="hidden" name="num" value="<%=rdto.getNum()%>">
							<a name="del_reply_btn" value="<%=rdto.getNum()%>">삭제</a></td>
					<%
							}
						}else{
					%>		
							<td align="left" width="56%" style="table-layout:fixed;
									word-break:break-all;"><br><%=rcont%><br>&nbsp;</td>
							<td align="center" width="22%"><%=rdto.getCtime()%></td>
					<%				
						}
					%>
			</tr>
			</form>
<%				
			}
		}
%>
	  </table>
</div>
</div>
</body>

<script type="text/javascript">
$(document).ready(function(){
	$(document).on("mouseenter","a",function(){
		$(this).css('color', '#777777');	
	});
	$(document).on("mousedown","a",function(){
		$(this).css('color','#505050');
	});
	$(document).on("mouseleave","a",function(){
		$(this).css('color','white');
	});
	$(document).on("mouseup","a",function(){
		$(this).css('color','#777777');
	});

	$("a[name=del_reply_btn]").click(function(event){
		var result = confirm("정말로 삭제하시겠습니까?");
		var str = "#reply" + $(this).attr("value");
		
		if(result){
			$(str).submit();
		}
	});
	$("#del_cont_btn").click(function(event){
		var result =confirm("정말로 삭제하시겠습니까?");
		if(result){
			$("#del_cont").submit();
		}
		
	});
	$('#btn_reply').click(function(event){
		if($("#reply_area").val()==""){
			alert("댓글을 입력하셔야 합니다.");
			return;
		}
		$("#reply_form").submit();	
	});					
});
</script>