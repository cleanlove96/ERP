<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>时间管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_orange.css">
<link rel="stylesheet" href="css/x-admin.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/kkpager.min.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style>
table {
	table-layout: fixed;
}

td {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>系统管理</a> <a> <cite>日期修改</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<form class="layui-form" id="fm">
		<input type="hidden" value="${day.dateTypeId }" name="dateTypeId">
			<div class="layui-form-item">
				<label for="time" class="layui-form-label">日期</label>
				<div class="layui-input-inline">
					<input type="text" id="ztime" name="dateTypeDate"
						class="layui-input" value="${day.dateTypeDate }">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> 名称 </label>
				<div class="layui-input-inline">
					<input type="text" id="L_name" name="dateTypeName" required
						lay-verify="required" autocomplete="off" class="layui-input"
						value="${day.dateTypeName }">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label for="" class="layui-form-label"> 类型 </label>
					<div class="layui-input-inline">
						<input type="radio" name="dateTypeType" value="休" 
							title="休"> <input type="radio" name="dateTypeType"
							value="班" title="班">
					</div>
				</div>
			</div>
			<label for="" class="layui-form-label"></label>
			<button class="layui-btn" lay-filter="save" lay-submit>保存</button>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function () {
	$("input[value='${day.dateTypeType}']").attr("checked","checked");
});
	layui.use([ 'form', 'layer', 'laydate' ], function() {
		$ = layui.jquery;
		var form = layui.form, laydate = layui.laydate, layer = layui.layer;
		laydate.render({
			elem : '#ztime' //指定元素
		});
		form.on('submit(save)', function(data) {
			console.log(data.field);
			$.post('ministryController/updateDateType.ajax', data.field, function(
					res) {
				if(res=="SUCCESS"){
					//res就是返回的结果
					layer.alert("修改成功", {icon : 6}, function() {
						// 获得frame索引
						var index = parent.layer.getFrameIndex(window.name);
						//关闭当前frame
						parent.layer.close(index);
						parent.location.reload();
					});
					}else{
						layer.alert("修改失败", {icon : 7}, function() {
							// 获得frame索引
							var index = parent.layer.getFrameIndex(window.name);
							//关闭当前frame
							parent.layer.close(index);
							parent.location.reload();
						});
					}
					});
						return false;

		});
	});
</script>
</html>