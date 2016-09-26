<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String publicKeyModulus = (String)request.getAttribute("publicKeyModulus");
	String publicKeyExponent = (String)request.getAttribute("publicKeyExponent");

%>
<body style="margin: 0px;" bgcolor="transparent" onload="join_form.name.focus();">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="../menu.jsp" %>
		  <p id="board_title">회원 정보 변경</p>
	  <hr width="90%" color="White"  noshade>

     <form id="my_info_form_fake">
	    <table align="center">
  
		   <tr>
		     <td>
		     	<b name="basic">이름  : </b>
		     </td>
			 <td>
			 	<input type="text" name="name" value="<%=mbdto.getName() %>" id="name_form" readonly></b>
			 </td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td></td>
		   </tr>
		   <tr>
		     <td>
		     	<b name="basic">아이디 : </b>
		     </td>
			 <td>
			 	<input type="text" name="id" value=" <%=mbdto.getId() %>" id="id_form" readonly></b>
			 </td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td></td>
		   </tr>
		   <tr>
		     <td>
		     	<b name="basic">비밀번호 : </b>
		     </td>
			 <td>
			 	<input type="password" name="pwd" id="pw_form">
			 </td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="pw_form-error" class="error" for="pw_form"></label></td>
		   </tr>
		   <tr>
		     <td>
		     	<b name="basic">비밀번호(확인) : </b>
		     </td>
			 <td>
			 	<input type="password" name="rpwd" id="rpw_form">
			 </td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="rpw_form-error" class="error" for="rpw_form"></label></td>
		   </tr>
		   	<tr>
		     <td>
		     	<b name="basic">닉네임 : </b>
		     </td>
			 <td>
			 	<input type="text" name="nick" id="nick_form" placeholder="<%=mbdto.getNick()%>">
			 </td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="nick_form-error" class="error" for="nick_form"></label></td>
		   </tr>
		   <tr>
		     <td>
		     	<b name="basic">이메일 : </b>
		     </td>
			 <td>
			 	<input type="text" name="email" id="email_form" placeholder="<%=mbdto.getEmail()%>">
			 </td>
		   </tr>
		   <tr>
		   <td><b>&nbsp;</b></td>
		   <td><label id="email_form-error" class="error" for="email_form"></label></td>
		   </tr>
		   <tr>
		     <td colspan="2" align="center"> 
				<a id="btn_myinfo"> 정보 수정 </a>
			 </td>
		   </tr>
		</table>
	 </form>
		<input type="hidden" id="rsaPublicKeyModulus" value="<%=publicKeyModulus %>"/>
		 <input type="hidden" id="rsaPublicKeyexponent" value="<%=publicKeyExponent %>"/>
		<form id="my_info_form" action="member.do?method=myinfo" method="post" style="display: none;">

			<input type="hidden" name="securedName" id="securedName">
			<input type="hidden" name="securedId" id="securedId">
			<input type="hidden" name="securedPwd" id="securedPwd">
			<input type="hidden" name="securedNick" id="securedNick">
			<input type="hidden" name="securedEmail" id="securedEmail">
		</form>
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
	 	

 		$("#btn_myinfo").click(function(event){
	 		if(!$("#my_info_form_fake").valid()){
	 			alert("정보가 올바르지 않습니다.");
	 		}else{
	 			var name = $("#name_form").val();
	 			var id = $("#id_form").val();
	 			var pwd = $("#pw_form").val();
	 			var nick = $("#nick_form").val();
	 			var email = $("#email_form").val();
	 			try{
	 				var rsaPublicKeyModulus = $("#rsaPublicKeyModulus").val();
	 				var rsaPublicKeyExponent = $("#rsaPublicKeyexponent").val();
	 				submitEncryptedform(name,id,pwd,nick,email,rsaPublicKeyModulus,rsaPublicKeyExponent);
	 			}catch(err){
	 				alert(err);
	 			}
	 		}
 		});

	 	$("#btn_myinfo").click(function(event){

	 		if(!$("#my_info_form").valid()){
	 			alert("정보가 올바르지 않습니다.");
	 		}else{
	 			var chk = confirm("정보를 변경 하시겠습니까?");
	 			if(chk){
	 				$("#my_info_form").submit();
	 			}else{
	 				return ;
	 			}
	 		}

	 	});
 		function submitEncryptedform(name,id,pwd,nick,email,rsaPublicKeyModulus,rsaPublicKeyExponent){
 			var rsa = new RSAKey();
 			rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);
 			
 			var securedPwd = rsa.encrypt(pwd);
 			$("#securedName").val(name);
 			$("#securedId").val(id);
 			$("#securedPwd").val(securedPwd);
 			$("#securedNick").val(nick);
 			$("#securedEmail").val(email);
 			$("#my_info_form").submit();
 		}
	 	$("#my_info_form_fake").validate({
	 		rules:{
	 			pwd:{
	 				required: true,
	 				minlength:6,
	 			},
	 			rpwd:{
	 				required: true,
	 				minlength:6,
	 				equalTo:pw_form
	 			},
	 			nick:{
	 				required: true,
	 				minlength:3,
	 				remote:{
	 			        url: "search.do?",
	 			        type: "post",
	 			        data: {
	 			          method:"duple3",
	 			          id: function() {
	 			            return $("#id_form" ).val();
	 			          },
	 			          nick:function(){
	 			        	  return $("#nick_form").val();
	 			          }
	 			        }
	 				}
	 			},
	 			email:{
	 				required: true,
	 				minlength:3,
	 				email:true
	 			}
	 		},
	 		messages:{
	 			pwd:{

	 			},
	 			rpwd:{
	 				equalTo: "비밀번호가 일치하지 않습니다."
	 			},
	 			nick:{
	 				remote: "중복된 닉네임 입니다."
	 			},
	 			email:{

	 			}
	 		}
	 	});
	});
	</script> 