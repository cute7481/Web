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

		  <p id="board_title">로그인 실패! 사용자 정보가 올바르지 않습니다.</p>
	  <hr width="90%" color="White"  noshade>

			  <br><br>
			  
			  <table align="center">
				<tr>
					<td align="center"  style="font-size:30px;font-family:Dokdo;">
					  <a href="member.do?method=loginform" >로그인 재시도</a>
			  		</td>
			  		<td align="center"  style="font-size:30px;font-family:Dokdo;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  			<a href="member.do?method=joinform">회원가입</a>
			  	</td>
			  </tr>

			  <tr>
			  <td align="center"  style="font-size:30px;font-family:Dokdo;">			  <br>
			  <br>
			  			  <a href="search.do?method=sid_form">ID를 잊으셨나요?</a><br>
			  </td>
			  <td align="center" style="font-size:30px;font-family:Dokdo;">			  <br>
			  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <a href="search.do?method=spw_form">비밀번호를 잊으셨나요?</a>
			</tr>
			</table>
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