<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>

	<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>

<%@include file="navbar.jsp" %>

<div class='center-header'>
<b id="r_title" style="font-size:65px;">쉬어가기. . .</b><br>
<b id="r_title" style="font-size:40px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      생각이 고이는 시간</b>
				<br><br>
				<table width="1000" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<table id="menu-table" width='100%' >
							<td><a href="main.do" id="menu1" class="a_text">메인화면</a></td>
							<td><a href="board.do?method=list&pg=1" id="menu2" class="a_text">게시판</a></td>
							<td><a href="main.do?method=list" id="menu3" class="a_text">질문리스트</a></td>
							<td><a href="qget.do?method=list&pg=1" id="menu4" class="a_text">질문제보</a></td>
						</table>
						</td>
					</tr>
				</table>
				<hr width="90%" color="white" noshade>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#menu1').mouseenter(function(){
		$(this).css('color','#777777');
	})
	$('#menu1').mousedown(function(){
		$(this).css('color','#505050');
	})
	$('#menu1').mouseleave(function(){
		$(this).css('color','white');
	})
	$('#menu1').mouseup(function(){
		$(this).css('color','#777777');
	})
	$('#menu2').mouseenter(function(){
		$(this).css('color','#777777');
	})
	$('#menu2').mousedown(function(){
		$(this).css('color','#505050');
	})
	$('#menu2').mouseleave(function(){
		$(this).css('color','white');
	})	
	$('#menu2').mouseup(function(){
		$(this).css('color','#777777');
	})
	$('#menu3').mouseenter(function(){
		$(this).css('color','#777777');
	})
	$('#menu3').mousedown(function(){
		$(this).css('color','#505050');
	})
	$('#menu3').mouseleave(function(){
		$(this).css('color','white');
	})
	$('#menu3').mouseup(function(){
		$(this).css('color','#777777');
	})
	$('#menu4').mouseenter(function(){
		$(this).css('color','#777777');
	})
	$('#menu4').mousedown(function(){
		$(this).css('color','#505050');
	})
	$('#menu4').mouseleave(function(){
		$(this).css('color','white');
	})
	$('#menu4').mouseup(function(){
		$(this).css('color','#777777');
	})
		
});
</script>