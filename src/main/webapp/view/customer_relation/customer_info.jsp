<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>客户管理</title>
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
		<span class="layui-breadcrumb"> <a>客户关系管理</a> <a> <cite>客户资料</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span>  <input type="text" name="username" id="search"
					placeholder="请输入姓名" autocomplete="off" class="layui-input" value="${sreach }">
				<button class="layui-btn" onclick="doSearch()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加客户','view/customer_relation/customer_add.jsp',600,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height: 40px" id="number_customer"></span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>公司</th>
					<th>电话</th>
					<th>QQ</th>
					<th>邮箱</th>
					<th>地址</th>
					<th>备注</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="customer_table">
				<!-- 数据示例 -->
				<!-- end 数据示例 -->
			</tbody>
		</table>
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->

	</div>
</body>
<script type="text/javascript" src="js/customer.js"></script>
</html>