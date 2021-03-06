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
<title>质检管理</title>
<meta name="renderer" content="webkit">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_orange.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/kkpager.min.js"></script>
<script type="text/javascript" src="js/date_util.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>用户管理</a> <a> <cite>质检管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so" action="qualityTestController/sreach.do">
				<span>快速查询：</span>  <input id="keyword" name="keyword" type="text" value="${keyword}"
					placeholder="请输入关键字" autocomplete="off" class="layui-input">
				<button class="layui-btn"  lay-filter="sreach">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>
		<xblock>
			<div style="height: 40px">
			<span class="x-right" style="line-height: 40px">共有数据：<a id="num">...</a> 条</span>
			</div>
			
		</xblock>
		
		<table class="layui-table">
			<thead>
				<tr>
					<th>生产批次</th>
					<th>生产线</th>
					<th>商品</th>
					<th>产量</th>
					<th>损耗</th>
					<th>实际入库量</th>
					<th>生产时间</th>
					<th>入库时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="table">
				<!-- 数据示例 -->
				
				<!-- end 数据示例 -->
			</tbody>
		</table>
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->

	</div>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<input type="hidden" id="pno" value="1">
	<script type="text/javascript" src="js/test.js"></script>
	<script>
	
		$(function(){
			getPage(1);
			showView(1);
		})
	</script>
</body>
</html>