<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>青阳ERP管理系统</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
<script type="text/javascript">
	//如果该页面在iframe中出现，那么会自动调用最外层窗口刷新本链接
	if (window != top) {
		top.location.href = location.href;
	}
</script>

</head>
<body class="login-bg">

	<div class="login">
		<div class="message">青阳ERP管理系统-用户登录</div>
		<div id="darkbannerwrap"></div>
		<form method="post" class="layui-form">
			<input id="username" placeholder="用户名" type="text"
				class="layui-input">
			<hr class="hr15">
			<input id="password" placeholder="密码" type="password"
				class="layui-input">
			<hr class="hr15">
			<input lay-submit lay-filter="login" style="width: 100%;"
				type="button" value="登录" onclick="dologin()" />
			<hr class="hr20">
		</form>
		<input type="checkbox" id="memoryuser" onchange="removeMe()">&nbsp;30天内自动登录
	</div>

	<script>
		$(function() {
			//获取记住账户状态
			$.ajax({
				url : 'accountController/getRember.ajax',
				data : {
				},
				type : 'POST',
				dataType : 'text',
				success : function(result) {
					if(result=="NULL"){
						
					}else{
						$("#memoryuser").attr("checked","checked");
						var j=eval("("+result+")");
						$("#username").val(j.username);
						$("#password").val(j.password);
					}
				},
				error : function() {
					
				}
			});
		});

		//进行登录处理
		function dologin() {
			var username = $("#username").val();
			var password = $("#password").val();
			var rember = "f";
			if ($("#memoryuser").prop("checked")) {
				rember = "t";
			}
			$.ajax({
				url : 'accountController/doLogin.ajax',
				data : {
					username : username,
					password : password,
					rember : rember
				},
				type : 'POST',
				dataType : 'text',
				success : function(result) {
					switch (result) {
					case "SUCCESS":
						window.location.href = "indexController/showIndexPage.do";
						break;
					case "ACCOUNT_NOT_FOUND":
						alert("错误：用户不存在。");
						break;
					case "PWD_ERROR":
						alert("错误：密码不正确，请检查。");
						break;
					case "STATUS_ERROR":
						alert("错误：账户状态异常，不允许登录。");
						break;
					}
				},
				error : function() {
					alert("错误：请求失败，请稍后再试。");
				}
			});
		}
		
		//忘记账户信息的
		function removeMe(){
			if($("#memoryuser").prop("checked")){
				
			}else{
				$.ajax({
					url : 'accountController/removeRember.ajax',
					data : {
					},
					type : 'POST',
					dataType : 'text',
					success : function(result) {
						
					},
					error : function() {
						$("#memoryuser").attr("checked","checked");
					}
				});
			}
		}
	</script>
</body>
</html>