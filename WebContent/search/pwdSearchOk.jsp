<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

 <body style="margin: 0px;" bgcolor="transparent" >
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>

<%
 	String id = (String)request.getAttribute("id");
 	if(id == null){
 		session.removeAttribute("__rsaPrivateKey__");
%>
				  <p id="board_title">올바른 정보가 아닙니다.</p>
	  <hr width="90%" color="White"  noshade>
		<div align="center">
	
		<br><br><br>
		<a href="search.do?method=spw_form" style="font-size:35px;font-family:Dokdo;">비밀번호 찾기</a><br><br>
		<a href="search.do?method=sid_form" style="font-size:35px;font-family:Dokdo;">아이디 찾기</a><br><br>
		<a href="member.do?method=join" style="font-size:35px;font-family:Dokdo;">회원가입</a><br><br><br>
		</div>
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
<%
 	}else{

 		String publicKeyModulus = (String)request.getAttribute("publicKeyModulus");
 		String publicKeyExponent = (String)request.getAttribute("publicKeyExponent");


%>
  <body onload="update_pw_form.pwd.focus();"><div align="center">
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
	 	
	 	$("#update_pw_form_fake").validate({
	 		rules:{
	 			pwd:{
	 				required: true,
	 				minlength:6,
	 			},
	 			rpwd:{
	 				required: true,
	 				minlength:6,
	 				equalTo:pw_form
	 			}
	 		},
	 		messages:{
	 			rpwd:{
	 				equalTo: "비밀번호가 일치하지 않습니다."
	 			}
	 		}
	 	});

 		$("#btn_uppw").click(function(event){
	 		if(!$("#update_pw_form_fake").valid()){
	 			alert("정보가 올바르지 않습니다.");
	 		}else{
	 			var chk = confirm("비밀번호를 변경 하시겠습니까?");
	 			if(chk){
		 			var id = $("input[name=s_id]").val();
		 			var pwd = $("#pw_form").val();
		 			try{
		 				var rsaPublicKeyModulus = $("#rsaPublicKeyModulus").val();
		 				var rsaPublicKeyExponent = $("#rsaPublicKeyexponent").val();
		 				submitEncryptedform(id,pwd,rsaPublicKeyModulus,rsaPublicKeyExponent);
		 			}catch(err){
		 				alert(err);
		 			}
	 			}else{
	 				return ;
	 			}
	 		}
 		});
 		function submitEncryptedform(id, pwd, rsaPublicKeyModulus,rsaPublicKeyExponent){
 			var rsa = new RSAKey();
 			rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);
 			
 			var securedPwd = rsa.encrypt(pwd);
 			$("#securedId").val(id);
 			$("#securedPwd").val(securedPwd);
 			$("#update_pw_form").submit();
 		}
 	});
</script>
				  <p id="board_title">비밀번호 변경</p>
	  <hr width="90%" color="White"  noshade>
  	<b style="font-size:35px;font-family:Dokdo;"><font color="5C72FF"><%=id%></font>&nbsp;님,
  	변경하실 비밀번호를 입력해주세요</b><br><br>
     <form id="update_pw_form_fake">
	    <input type="hidden" name="s_id" value="<%=id%>">
	    <table align="center" cellpadding="3" cellspacing="1">

		   </tr>
		   <tr>
		     <td><b name="basic">새 비밀번호 : </b></td>
			 <td><input type="password" name="pwd" id="pw_form"></td>
		   </tr>
		    <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="pw_form-error" class="error" for="pw_form"></label></td>
		   </tr>
		   <tr>
		     <td><b name="basic">새 비밀번호(확인) : </b></td>
			 <td><input type="password" name="rpwd" id="rpw_form"></td>
		   </tr>
		    <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="rpw_form-error" class="error" for="rpw_form"></label></td>
		   </tr>
		   <tr>
		     <td colspan="2" align="center"> 
			    <a id="btn_uppw"> 비밀번호 변경 </a>
			 </td>
		   </tr>
		</table>
	 </form>
	 			<input type="hidden" id="rsaPublicKeyModulus" value="<%=publicKeyModulus %>"/>
		   <input type="hidden" id="rsaPublicKeyexponent" value="<%=publicKeyExponent %>"/>
	 <form id="update_pw_form"  action="search.do?method=pwdUp" method="post" style="display: none;">
            <input type="hidden" name="securedId" id="securedId" value="" />
            <input type="hidden" name="securedPwd" id="securedPwd" value="" />
      </form>
  </div>
<%
	}
%>
