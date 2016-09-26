<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

  <body style="margin: 0px;" bgcolor="transparent" ">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>

<%
     String userid = (String)request.getAttribute("userid");
     if(userid != ""){
%>
		  <p id="board_title">아이디 찾기 성공!</p>
	  <hr width="90%" color="White"  noshade>
		<div align="center">
	<br><br>
	  	<b style="font-size:35px;font-family:Dokdo;">아이디는&nbsp;&nbsp;&nbsp;&nbsp;<font color="5C72FF"><%=userid%></font>&nbsp;&nbsp;&nbsp;&nbsp;입니다.</b>
	  	<br><br><br>
		<a href="member.do?method=loginform" style="font-size:35px;font-family:Dokdo;">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="search.do?method=spw_form" style="font-size:35px;font-family:Dokdo;">비밀번호 찾기</a><br><br>
		</div>
		
<%
     }else{
%>
		  <p id="board_title">해당하는 아이디가 없습니다.</p>
	  <hr width="90%" color="White"  noshade>
		<div align="center">
		<br><br><br>
		<a href="search.do?method=sid_form"  style="font-size:35px;font-family:Dokdo;">아이디 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="member.do?method=join"  style="font-size:35px;font-family:Dokdo;">회원가입</a><br><br>
		</div>
<%
     }
%>
 </body>
</html>