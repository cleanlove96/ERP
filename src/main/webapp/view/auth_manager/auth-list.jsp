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
<title>权限组织结构</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style>
td input {
	width: 100%;
	border: 0;
}
</style>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>权限管理</a> <a> <cite>权限信息管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="row">
			<!-- 页面左侧（树形结构）部分 -->
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">权限列表</div>
					<div class="panel-body fullhight">
						<!-- 这里放置左侧内容主体 -->
						<table class="layui-table">
							<tbody id="auth_list">
								<!-- 数据示例 -->
								<!-- end 数据示例 -->
							</tbody>
						</table>
						<!-- end 左侧内容 -->
					</div>
				</div>
			</div>
			<!-- end 左侧 -->
			<!-- 页面右侧（详细信息）部分 -->
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">详细信息</h3>
					</div>
					<div class="panel-body fullhight">
						<!-- 这里放置右侧内容主体 -->
						<span class="message-title"></span>
						<hr />
						<form id="fm">
							<table class="layui-table">
								<tr>
									<th width="100px">权限ID</th>
									<td width="270px;"><input class="layui-input"
										name="authId" id="auth_id" readonly="readonly" type="text"></td>
									<th width="100px">权限名称</th>
									<td width="270px;"><input class="layui-input"
										name="authName" id="auth_name" type="text"></td>
								</tr>
								<tr>
									<th>权限地址</th>
									<td><input class="layui-input" name="authHref" type="text" id="auth_href"></td>
									<th>权限的分组</th>
									<td><select name="authGroupId" id="auth_parent"></select></td>
								</tr>
							</table>
						</form>
						<hr />
						<button class="layui-btn" id="change">修改</button>
						<button class="layui-btn" id="delete">删除</button>
						<button class="layui-btn" id="create" >添加权限</button>
						<!-- end 右侧内容 -->
					</div>
				</div>
			</div>
			<!-- end 右侧 -->
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
	$(function() {
		$.post('authController/getAuthGroup.ajax',function(result) {
			var res = JSON.parse(result);
			$("#auth_parent").html("");
			$.each(res, function(n, val) {
				$("#auth_parent").append(
						"<option  value='"+val.authGroupId+"'>"+ val.authGroupName +"</option>");
			});
		});
		$.ajax({
			url : 'authController/getAuth.ajax',
			data : {

			},
			type : 'post',
			dataType : 'text',
			success : function(result) {
				var jtable = eval("(" + result + ")");
				$("#auth_list").html("");
				$.each(jtable, function(n, val) {
					$("#auth_list").append(
							"<tr><td onclick=\"seeInfo('" + val.authId
									+ "')\">" + val.authName + "</td></tr>");
				});
			},
			error : function() {

			},
		});
		var id = $("#auth_id").val();
		if (id == "") {
			$("#change").hide();
			$("#delete").hide();
			$("#create").show();
		}
	});
	function seeInfo(id) {
		$.post('authController/getAuthGroup.ajax', function(result) {
			var res = JSON.parse(result);
			$("#auth_parent").html("");
			$.each(res, function(n, val) {
				$("#auth_parent").append(
						"<option value='"+val.authGroupId+"' >"+ val.authGroupName + "</option>");
			});
			$.post('authController/getAuthInfo.ajax', {
				authId : id
			}, function(result) {
				var res = JSON.parse(result);
				console.log(id);
				console.log(res);
				$("#auth_id").attr("value", res.authId);
				$("#auth_name").attr("value", res.authName);
				$(".message-title").text(res.authName);
				$("#auth_href").attr("value", res.authHref);
				$("option[value='" + res.authGroupId + "']").attr("selected",true);
			});
			$("#change").show();
			$("#delete").show();
			$("#create").hide();
		});
	};
	$("#change").click(function() {
		var form = $("#fm").serialize();
		$.post('authController/authChange.ajax', form, function(res) {
			if (res == "SUCCESS") {
				layer.msg('已修改!', {
					icon : 1,
					time : 500
				});
			} else {
				layer.msg('未修改!', {
					icon : 1,
					time : 500
				});
			}
		});
	});
	$("#delete").click(function() {
		var id = $("#auth_id").val();
		$.post('authController/authDelete.ajax', {
			id : id
		}, function(res) {
			if (res == "SUCCESS") {
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
			} else {
				layer.msg('未删除!', {
					icon : 1,
					time : 500
				});
			}

		});
	});
	 $("#create").click(function() {
	 var form = $("#fm").serialize();
	 var name = $("#auth_name").val();
	 var href = $("#auth_href").val();
	 if(name==null||name==""){
	 		layer.msg('名字不能为空!', {
	 			icon : 0,
	 			time : 500
	 	});
	 		return false;
	 }
	 if(href==null||href==""){
	 		layer.msg('连接地址不能为空!', {
	 			icon : 0,
	 			time : 500
	 	});
	 		return false;
	 }
	 $.post('authController/authAdd.ajax', form, function(res) {
	 	if (res == "SUCCESS") {
	 		layer.msg('已添加!', {
	 			icon : 1,
	 			time : 500
	 	});
	 } else {
	 	layer.msg('未添加!', {
		 icon : 1,
	 	time : 500
		 });
	 }
	});
}); 
</script>
</html>