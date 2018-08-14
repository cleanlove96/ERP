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
<title>用户管理</title>
<meta name="renderer" content="webkit">
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
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>财务管理</a> <a> <cite>商品成本利润统计</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so">
				<span>按商品查询：</span><input type="text" placeholder="请输入商品名"
					autocomplete="off" class="layui-input" id="searchGoodsName"
					value="${searchGoodsName}"> <span>起始时间：</span><input type="text"
					placeholder="请输入商品名" autocomplete="off" class="layui-input"
					id="searchTimeStart" value="${search}">
					<span>结束时间：</span><input type="text"
					placeholder="请输入商品名" autocomplete="off" class="layui-input"
					id="searchTimeEnd" value="${search}">
				<button class="layui-btn" lay-submit="" lay-filter="search"
					onclick="doSearch()" type="button">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>

		<xblock> <!-- <button class="layui-btn"
			onclick="x_admin_show('添加用户','view/member/member-add.jsp',500,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button> --> <!-- <span class="x-right" style="line-height: 40px">共有数据：??? 条</span> </xblock> -->
		<table class="layui-table">
			<thead>
				<tr>
					<th>商品名称</th>
					<!-- <th>数量</th>
					<th>单价</th>
					<th>销售金额</th> -->
					<th>成本</th>
					<th>利润</th>
					<!-- <th>单据时间</th> -->
				</tr>
			</thead>
			<tbody>
				<!-- 数据示例 -->
				<tr>
					<td><a id="total" href="view/costProfittable/cost-profit-list1.jsp">${CostProfitTable.saleSumTotal}</a></td>
					<td><a id="cost" onclick="costShow()" href="">${CostProfitTable.cost}</a></td>
					<td><a id="profit" onclick="profitShow()" href="">${CostProfitTable.profit}</a></td>
				</tr>
				<!-- end 数据示例 -->
			</tbody>
		</table>
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->
	</div>
</body>
</html>