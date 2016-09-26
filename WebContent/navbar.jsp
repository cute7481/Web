<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*, yoon.member.model.MemberDTO"%>
 
	<script type='text/javascript' src='js/jquery-3.1.0.min.js'></script>
	<script type='text/javascript' src='js/additional-methods.min.js'></script>
	<script type='text/javascript' src='js/jquery.validate.min.js'></script>
	<script type='text/javascript' src='js/messages_ko.min.js'></script>
	<script type="text/javascript" src="js/rsa/jsbn.js"></script>
	<script type="text/javascript" src="js/rsa/rsa.js"></script>
    <script type="text/javascript" src="js/rsa/prng4.js"></script>
    <script type="text/javascript" src="js/rsa/rng.js"></script>
    <script type="text/javascript" src="js/jquery-filestyle.js"></script>
	<link href="css/base.css" rel="stylesheet" type="text/css">
	<link href="css/jquery-filestyle.css" rel="stylesheet" type="text/css">

<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #3A3B3D;
    border:0px;
}
li {
    float: right;
}
li a {
    display: block;
    color: white;
    text-align: center;
    padding: 5px 16px;
    font-family:Misaeng;
    text-decoration: none;
    font-size:20px;
    font-family:Misaeng;
}
li a:hover {
    background-color: #111;
}
</style>

<div class='navbar'>
<%
	MemberDTO mbdto = (MemberDTO)session.getAttribute("loginOkUser");
 	String pic = "img/default_profile.png";
 	
	if(mbdto == null){
%>
	<ul>
	  <li><a href="member.do?method=joinform">회원가입</a></li>
	  <li><a href="member.do?method=loginform">로그인</a></li>
	</ul>
<%
	}else{
		if(mbdto.getPic() == null){
			mbdto.setPic(pic);
		}
%>
<ul>
  <li><a id="btn_logout">로그아웃</a></li>
  <li><a href="member.do?method=mypage"><%= mbdto.getName() %>님</a></li>
  <li><a href="member.do?method=mypic"><img src ="<%=mbdto.getPic()%>" width="25px" height="25px"></a></li>
</ul>
<%
	}
%>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#btn_logout").click(function(){
		var result = confirm("로그아웃 하시겠습니까?");
		if(result){
			location.replace("member.do?method=logout");
		}else{
			return ;
		}
	});
});
</script>
