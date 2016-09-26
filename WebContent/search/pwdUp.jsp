<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

 <%
 	boolean state = (boolean)request.getAttribute("state");
 	if(state){
 %>
 <body style="margin: 0px;" bgcolor="transparent" onload="f.name.focus();">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>
				  <p id="board_title">비밀번호 변경</p>
	  <hr width="90%" color="White"  noshade>
	 <div align="center">
	 <br><br><br><br>
		<b name="basic">비밀번호 변경 성공!</b><br><br><br>
		<a href="member.do?method=loginform" style="font-size:35px;font-family:Dokdo;">로그인</a>
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
 	})
 	</script>
	 </div>


<%
 	}else{
%>
	<body>
	<div align="center">
		<b name="basic">비밀번호 변경 오류! 다시 시도해주세요.</b><br><br><br>
		<a href="search.do?method=spw_form" style="font-size:35px;font-family:Dokdo;">비밀번호 찾기</a>
	</div>

<%
 	}
%>
