<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

	<style type="text/css">
	   table{text-align: center} 
	</style>
 <body style="margin: 0px;" bgcolor="transparent" onload="id_search_form.name.focus();">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>
				  <p id="board_title">아이디 찾기</p>
	  <hr width="90%" color="White"  noshade>
     <form id="id_search_form" action="search.do?method=s_id" method="post">
	    <table align="center" cellpadding="3" cellspacing="1">
		   <tr>
		     <td align="right"><b name="basic">이름 : </b></td>
			 <td align="left"><input type="text" name="name" id="name_form"></td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td align="left"><label id="name_form-error" class="error" for="name_form"></label></td>
		   </tr>
		   <tr>
		     <td align="right"><b name="basic">닉네임: </b></td>
			 <td align="left"><input type="text" name="nick" id="nick_form"></td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td align="left"><label id="nick_form-error" class="error" for="nick_form"></label></td>
		   </tr>
		   <tr>
		     <td align="right"><b name="basic">이메일 : </b></td>
			 <td align="left"><input type="text" name="email" id="email_form"></td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td align="left"><label id="email_form-error" class="error" for="email_form"></label></td>
		   </tr>
		   <tr>
		     <td colspan="3" align="center"> 
				<a id="btn_sid">찾기</a> 
			 </td>
		   </tr>
		</table>
	 </form>
</div>
</div>
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

	 	$("#id_search_form").validate({
	 		rules:{
	 			name:{
	 				required: true,
	 				minlength:3,
	 			},
	 			nick:{
	 				required: true,
	 				minlength:3,
	 			},
	 			email:{
	 				required: true,
	 				minlength:3,
	 				email:true
	 			}
	 		}
	 	});
 		$("#btn_sid").click(function(event){
	 		if(!$("#id_search_form").valid()){
	 			alert("정보가 올바르지 않습니다.");
	 		}else{
	 			$("#id_search_form").submit();
	 		}
 		});
 	});
	</script>