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
<title>添加用户</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="panel-head" id="add" style="margin-top: 10px;margin-left: 50px">
		<strong><span class="icon-pencil-square-o"></span>
			<h3>分配角色：${se.roleName }</h3></strong>
	</div>
	<div class="x-body" style="margin-left: 40px">
		<form class="layui-form" lay-filter="example" method="post" action="roleController/roleAuth.do">
			<input type="hidden" name="roleId" value="${se.roleId }">
			<div class="label">
				<label><h3>菜单列表：</h3></label>
				<c:forEach items="${sa}" var="auth">
					<div class="layui-form-item">
						<div class="layui-input-inline " class=>
							<input type="checkbox" title="${auth.authName }"
								lay-skin="primary" name="authIds" id=${auth.authId }
								value="${auth.authId }" style="width: auto;">
						</div>
					</div>
				</c:forEach>
				<div >
					<label for="L_repass" class="layui-form-label"> </label>
					<button class="layui-btn" type="submit" onclick="addmember()"
						>确定</button>
				</div>
			</div>
		</form>
	</div>
		<script>
		$(function() {
			var array = new Array();
			<c:forEach items="${authIds}" var="item" >
			array.push("${item}"); //对象，加引号       
			</c:forEach>
			for (var i = 0; i < array.length; i++) {
				$("#" + array[i]).attr("checked", "checked");
			}
		})
	</script>
<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#L_entry' //指定元素
			});
		});
		
		function addmember() {
			layer.alert("修改成功", {
				icon : 6
			}, function() {
				
			});
		}
	</script>

</body>

</html>