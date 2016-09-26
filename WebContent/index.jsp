<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="java.util.*, yoon.questions.model.QuestionsDTO, yoon.questions.control.QuestionsControl" %>

<%
	QuestionsDTO dto = (QuestionsDTO)request.getAttribute("pic");
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>

<title>쉬어가기... 생각이 고이는 시간</title>
</head>

<body style="margin: 0px;" bgcolor="transparent">
	<div id="wrapper"> 
	<!-- top line-->
		<div class='roof' style="width:100%; height:0px; margin:0; padding:0;"></div>
		<!-- top line-->
		<!-- wrapper -->
		<div class='center-wrapper' style="width:1000px; margin:0 auto;">
		<!-- header-->
		<%@include file="menu.jsp" %>
		<br><br><br>
		<div align="center">
			<table>
			<tr >
			<a id="piclink" href="main.do?method=qinfo&num=<%=dto.getNum()%>"><img id="qimg" width="700px" height="500px" src ="<%=dto.getPic()%>"></a><br>
			</tr>
			<tr>
				<b id="photoby" name="<%=dto.getAuthor() %>">photo by <%=dto.getAuthor() %>
			</tr>
			</table>
			<input id="picnum" type="hidden" value="<%= dto.getNum() %>">
			<b id="next_pic" >또 다른 질문</a>
		</div>
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
	$('#next_pic').mouseenter(function(){
		$(this).css('color','#777777');
	})
	$('#next_pic').mousedown(function(){
		$(this).css('color','#505050');
	})
	$('#next_pic').mouseup(function(){
		$(this).css('color','#777777');
	})
	$('#next_pic').mouseleave(function(){
		$(this).css('color','white');
	})

	
	$('#next_pic').click(function(event){
		$.ajax({
			type:"GET",
			url:"main.do",
			data:{
				"method" : "asyncpic",
				"num" : $("#picnum").attr("value"),
				"auth" : $("#photoby").attr("name")
			},
			datatype: "JSON",
			success:function(obj){
				var n = JSON.parse(obj);
				var num = n.num;
				var psrc = n.pic;
				var aut = n.auth;
				$("#qimg").attr("src", psrc);
				$("#picnum").attr("value", num);
				$("#photoby").attr("name", aut);
				$("#photoby").html("photo by "+aut);
				$("#piclink").attr("href","main.do?method=qinfo&num="+num);
			}
		});
	});
});
</script>


</body>
</html>