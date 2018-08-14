<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>组织结构</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body">
		<div class="row">
			<div style="text-align: center;" id="clock">
			<img alt="" src="images/loading.gif">
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
//打卡
	$(function(){
		
		$.ajax({
			url:'ministryController/accountClock.ajax',
			type:'post',
			data:{
				
			},
			dataType:'text',
			success:function(res){
				console.log(res);
				$("#clock").html("");
				switch(res){
				case "WARING":
					$("#clock").html("<h2 style='font-family: cursive;'>失败</h2><p>早上8点到9点之间才能打卡哦</p>");
					break;
				case "ERROR":
					$("#clock").html("发生未知错误，请与上级联系");
					break;
				case res:
					$("#clock").html("<h2 style='font-family: cursive;'>打卡成功</h2><p>本月累计打卡"+res+"天</p>");
					parent.$("#layerDemo").html("<a >已打卡</a>");
					break;
				default:
					break;
				}
			},
			error:function(){
				
			}
		});
	});
</script>
</html>