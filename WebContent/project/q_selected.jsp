<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" 
import="yoon.questions.model.QuestionsDTO, yoon.qreply.model.QreplyDTO, java.util.*"%>

<body style="margin: 0px;" bgcolor="transparent">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px;  margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>
		
	<p id="board_title">질문 리스트</p>
	<hr width="90%" color="White"  noshade>
<%
	QuestionsDTO dto = (QuestionsDTO)request.getAttribute("qdto");
%>
	<table class="board1" style="table-layout:fixed;word-break:break-all;">
	<tr class="board-normal">
		<td align="left" width="6%">&nbsp;<b id="qt">No. </b></td>
		<td align="left"><font id="qdetail"><%=dto.getNum()%></font></td>
	</tr>
	<tr class="board-normal">
		<td align="left" width="6%">&nbsp;<b id="qt">Title. </b></td>
		<td align="left" width="80%"><font id="qdetail"><%=dto.getSubject()%></font></td>
	</tr>
	<tr class="board-normal">	
		<td colspan="2" align="center"><p><br>
		<img width="700" height="500" src=<%=dto.getPic()%>></img>
		<br>&nbsp;</p></td>
	</tr>
	</table>
	<table class="board1" style="table-layout:fixed;word-break:break-all;">
	<tr class="board-normal">
		<td width="50%" align="center" height="40">
			<b id="qt">Photo by.</b>&nbsp;&nbsp;<b id="qdetail"><%=dto.getAuthor()%></b>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<b id="qt">Date.</b>&nbsp;&nbsp;<b id="qdetail"><%=dto.getRdate()%></b>
		</td>
	</tr>
	</table>
	<br>
<%
	if(mbdto == null){//비회원일때
%>
	<table class="board1" width='600' align='center' cellpadding='2'>
	<form action="qreply.do?method=reply" method="post" id="n_qreply">
		<tr class="board-normal">
			<td align="center" height="40">댓글 작성</td>
		</tr>	
		<tr class="board-normal">
			<td align="center">
			<br><input type="hidden" name="mcheck" value="none">
			<input type="hidden" name="num" value="<%=dto.getNum() %>">
			<textarea name="reply" id="reply_area" wrap="physical"></textarea>&nbsp;&nbsp;
			<input type="button" id="btn_reply_n" value="작성"><br><br>
			이름&nbsp;<input type="text" name="name" size="10%" id="reply_name">&nbsp;&nbsp;
			비밀번호&nbsp;<input type="password" name="pwd" size="10%" id="reply_pwd"><br>&nbsp;</td>
		</tr>
	</form>
	</table>
<%
	}else{//회원일때
%>
	<table class="board1">
	<form action="qreply.do?method=reply" method="post" id="m_qreply">
		<tr class="board-normal">
			<td align="center" height="40">댓글 작성</td>
		</tr>
		<tr class="board-normal">
			<td align="center">
			<input type="hidden" name="mcheck" value="member"><input type="hidden" name="num" value="<%=dto.getNum() %>">
			<input type="hidden" name="nick" value="<%= mbdto.getNick() %>">
			<input type="hidden" name="replyid" value="<%=mbdto.getId() %>"><br>
			<textarea name="reply" id="reply_area"></textarea>&nbsp;&nbsp;
			<input type="hidden" name="id" id="reply_name" value="<%=mbdto.getId()%>">
			<input type="button" id="btn_reply_m" value="작성"><br><br>
			</td>
		</tr>	
	</form>	
	</table>
<%		
	}
	ArrayList<QreplyDTO> qrlist = (ArrayList<QreplyDTO>)request.getAttribute("qrlist");
%> 
	<table class="board1">
	<tr class= "board-normal">
		<td align="center" height="40">댓    글</td>
	</tr>
	</table>
	<table class="board1" id="reply_board" style="table-layout:fixed;word-break:break-all;"> 
<%
	if(qrlist != null){
		for(QreplyDTO rdto : qrlist){
			String rcont = rdto.getReply().replace("\r\n", "<br>");
			if(rdto.getPic()==null)rdto.setPic("img/default_profile.png");
%>
			<form action="qreply.do?method=delete" method="post" id="qr_form<%=rdto.getNum()%>">
			<tr class="board-normal" id="qreply<%=rdto.getNum() %>">
				<input type="hidden" name="num" value="<%=rdto.getNum()%>">
				<input type="hidden" name="pnum" value="<%=dto.getNum()%>">
				<td width="20%" align="center"><img align="center" 
				width="50px" height="50px" src="<%=rdto.getPic()%>">&nbsp;
			<%
				if(rdto.getUserid() == null){//비회원 댓글일경우
					String n = rdto.getName()+"(-)";
			%>
					<%=n%></td>
			<%
				}else{
			%>
					<%=rdto.getName()%></td>
			<%
				}
			%>
				<td width="75%"><br><font size="5px"><%= rdto.getRdate()%></font>&nbsp;&nbsp;
			<%
				if(mbdto == null){//비회원일경우
					if(rdto.getUserid() == null){//댓글도 비회원일 경우
			%>
					<input type="hidden" name="memchk" value="none">
					<a name="btn_pw_chk" value="<%=rdto.getNum()%>">삭제</a>
					<input type="password" id="pw<%=rdto.getNum()%>" name="pwd" size="5%" 
					style="border-radius:5px;font-size:20px;visibility:hidden;">
					<a name="del_qreply_btn" id="del<%=rdto.getNum()%>" value="<%=rdto.getNum()%>" 
					style="visibility:hidden;">삭제</a>
			<%
					}
				}else{//로그인했을 경우
					if(rdto.getUserid() != null){//회원 댓글일 경우
						if(rdto.getUserid().equals(mbdto.getId())){//댓글의 아이디와 로그인한 자신의 아이디가 같을 경우				
			%>
					<input type="hidden" name="memchk" value="member">
					<a name="del_qreply_btn" value="<%=rdto.getNum()%>">삭제</a>
			<%
						}
					}
				}
			%>
			<br><%= rcont %><br>&nbsp;</td>
			<td width="5%"></td>
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
	$("a[name=btn_pw_chk]").click(function(event){
		var n = $(this).attr("value");
		var strpw = "#pw"+n;
		var strdel = "#del"+n;
		$(this).css("visibility","hidden");
		$(strpw).css("visibility","visible");
		$(strdel).css("visibility","visible");
	});
	$("a[name=del_qreply_btn]").click(function(event){
		var result = confirm("정말로 삭제하시겠습니까?");
		var this_val = $(this).attr("value");
		if(result){
			var str = "#qr_form"+this_val;
			$(str).submit();
		}
	});
	$('#btn_reply_m').click(function(event){
		if($("#reply_area").val()==""){
			alert("댓글을 입력하셔야 합니다.");
			return;
		}
		$("#m_qreply").submit();
	});
	$("#btn_reply_n").click(function(evetn){
		if($("#reply_name").val()==""){
			alert("이름을 입력하셔야 합니다.");
			return;
		}
		if($("#reply_pwd").val()==""){
			alert("비밀번호를 입력하셔야 합니다.");
			return;
		}
		if($("#reply_area").val()==""){
			alert("댓글을 입력하셔야 합니다.");
			return;
		}
		$("#n_qreply").submit();
	});
});
</script>
