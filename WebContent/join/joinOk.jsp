<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<body style="margin: 0px;" bgcolor="transparent">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>

		  <p id="board_title"><br><br>
		  회원가입에 성공하셨습니다!!!
		  <br>
		  <br></p>
	  <hr width="90%" color="White"  noshade>
<br><br><br>
	<div align="center">
	<a href="member.do?method=loginform" id="go_login" style="font-family:Dokdo;font-size:30px;">로그인하기</a>
  </div>
  <script>
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
	
	</script>