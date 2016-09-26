<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<body style="margin: 0px;" bgcolor="transparent" onload="join_form.name.focus();">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
	
		 	$('a').mouseenter(function(){
		 		$(this).css('color','#707070');
		 	});
		 	$('a').mouseleave(function(){
		 		$(this).css('color','white');
		 	});
		 	$('a').mousedown(function(){
		 		$(this).css('color','#505050');
		 	});
		 	$('a').mouseup(function(){
		 		$(this).css('color','#777777');
		 	});
	
	</script> 
	
	<%
		boolean upchk = (boolean)request.getAttribute("update");
		
		if(upchk){
	%>
		  <p id="board_title">회원 정보 변경 성공!</p>
	  <hr width="90%" color="White"  noshade>
	  
	  <br><br><br><br>
	  <div align="center">
	  <a href="main.do" style="font-size:35px;font-family:Dokdo;" >메인화면으로</a>
		</div>
  <%
		}else{
			
  %>
	<p id="board_title">회원 정보 변경 실패!</p>
	  <hr width="90%" color="White"  noshade>
	  
	  <br><br><br><br>
	  <div align="center">
	  <a href="main.do" style="font-size:35px;font-family:Dokdo;" >메인화면으로</a>
	  <br><br>
	  <%
	  if(mbdto!=null){
	  %>
	  <a href="member.do?method=mypage" style="font-size:35px;font-family:Dokdo;" >회원정보수정</a>
	  <%
	  }
	  %>
		</div>


<%
		}
%>
	