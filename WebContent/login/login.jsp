<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String publicKeyModulus = (String)request.getAttribute("publicKeyModulus");
	String publicKeyExponent = (String)request.getAttribute("publicKeyExponent");
%>
<body style="margin: 0px;" bgcolor="transparent" onload="login_form_fake.id.focus();">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>
		
	<p id="board_title">로 그 인</p>
	<hr width="90%" color="White"  noshade>
		<br>
		<br>
		<br>
		<br>
<form id="login_form_fake">
	    <table align="center" cellpadding="3" cellspacing="1">

		   <tr height="80">
		     <td align="right"><b name="basic">아이디 : </b></td>
			 <td align="center"><input type="text" name="id"  id="id_form"></td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="id_form-error" class="error" for="id_form"></label></td>
		   </tr>
		   <tr>
		   <tr height="80">
		     <td align="right"><b name="basic">비밀번호 : </b></td>
			 <td align="center"><input type="password" name="pwd" id="pw_form"></td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="pw_form-error" class="error" for="pw_form"></label></td>
		   </tr>
		   <tr>
		   <td></td>
		   <td>

		   <input type="hidden" id="rsaPublicKeyModulus" value="<%=publicKeyModulus %>"/>
		   <input type="hidden" id="rsaPublicKeyexponent" value="<%=publicKeyExponent %>"/>

		   </td>
		   </tr>
		   <tr>
		     <td colspan="2" align="center"> 
			    <a id="btn_login"> [ 로 그 인 ] </a>
			 </td>
		   </tr>
		</table>
</form>
	  <form id="login_form" name="securedLoginForm" action="member.do?method=login" method="post" style="display: none;">
	  		<input type="hidden" name="rpage" value="<%=(String)request.getAttribute("rpage")%>"/>
            <input type="hidden" name="securedId" id="securedId" value="" />
            <input type="hidden" name="securedPwd" id="securedPwd" value="" />
      </form>
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

	 	
	 	$("#login_form_fake").validate({
	 		rules:{
	 			id:{
	 				required: true,
	 				minlength:6,
	 			},
	 			pwd:{
	 				required: true,
	 				minlength:6,
	 			}
	 		}
	 	});		
 		$("#btn_login").click(function(event){
	 		if(!$("#login_form_fake").valid()){
	 			alert("정보가 올바르지 않습니다.");
	 		}else{
	 			var id = $("#id_form").val();
	 			var pwd = $("#pw_form").val();
	 			try{
	 				var rsaPublicKeyModulus = $("#rsaPublicKeyModulus").val();
	 				var rsaPublicKeyExponent = $("#rsaPublicKeyexponent").val();
	 				submitEncryptedform(id,pwd,rsaPublicKeyModulus,rsaPublicKeyExponent);
	 			}catch(err){
	 				alert(err);
	 			}
	 		}
 		});
 		function submitEncryptedform(id, pwd, rsaPublicKeyModulus,rsaPublicKeyExponent){
 			var rsa = new RSAKey();
 			rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);
 			
 			var securedPwd = rsa.encrypt(pwd);
 			$("#securedId").val(id);
 			$("#securedPwd").val(securedPwd);
 			$("#login_form").submit();
 		}

 		$("#id_form").keydown(function(event){

 	        if(event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39
 	    	        || event.keyCode == 46 ) return;
 	    	        //obj.value = obj.value.replace(/[\a-zㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
 	   	 	this.value = this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
 		})
 	});
	</script>