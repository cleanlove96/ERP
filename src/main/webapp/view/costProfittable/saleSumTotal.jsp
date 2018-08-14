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
		<span class="layui-breadcrumb"> <a>人员管理</a> <a> <cite>用户管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span><input type="text" placeholder="请输入客户名"
					autocomplete="off" class="layui-input" id="search"
					value="${search}">
				<button class="layui-btn" lay-submit="" lay-filter="search"
					onclick="doSearch()" type="button">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>

		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加用户','view/member/member-add.jsp',500,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height: 40px">共有数据：??? 条</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>单据编号</th>
					<th>客户名称</th>
					<th>经手人</th>
					<th>商品名称</th>
					<th>数量</th>
					<th>单价</th>
					<th>总金额</th>
					<th>开单时间</th>
				</tr>
			</thead>
			<tbody id="infoTable">
				<!-- 数据示例 -->

				<!-- end 数据示例 -->
			</tbody>
		</table>
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->

	</div>
	<script>
		/*用户查询*/
		var search="";
		$(function() {
			search = $("#search").val();
			$.ajax({
						url : 'financialManagementController/getPages.ajax',
						type : 'POST',
						data : {
							search : search
						},
						dataType : 'text',					
						success : function(res) {													
							var page = eval("(" + res + ")");
							$("#num").html(page.totalRecords)
							//初始化分页插件
							kkpager.generPageHtml({
								pno : '1',
								mode : 'click', //设置为click模式
								//总页码  
								total : page.total,
								//总数据条数  
								totalRecords : page.totalRecords,
								//点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
								//适用于不刷新页面，比如ajax
								click : function(n) {
									//这里可以做自已的处理
										
									showpage(n);
									//处理完后可以手动条用selectPage进行页码选中切换
									this.selectPage(n);
								},
								//getHref是在click模式下链接算法，一般不需要配置，默认代码如下
								getHref : function(n) {
									return '#';
								}
							});
						},
						error : function() {
						}
						
					});
			showpage(1); 		
		});
		function showpage(n) {
		
			//显示页面内容			
			$.ajax({
				url : 'financialManagementController/getAllSaleBills.do',
				type : 'POST',
				data : {
					n : n,
					search : search
				},			
				dataType : 'text',
				success : function(res) {					
					var jtable = eval("(" + res + ")");
					$("#infoTable").html("");
					$.each(jtable, function(n, val) {
						$("#infoTable").append(
								"<tr><td>" + val.saleBillId + "</td><td>"
										+ val.customerName + "</td><td>"
										+ val.accountName + "</td><td>"
										+ val.commodityName + "</td><td>"
										+ val.sum + "</td><td>" + val.price
										+ "</td><td>" + val.total //字符串拼接的时候遇到双引号要用单引号   \"member_stop加斜杠进行转义
										+ "</td><td>" + val.saleTime
										+ "</td></tr>");

					});
				},
				error : function() {
				}
			});			
		}
		function doSearch() {
			var s = $("#search").val();
			window.location.href = 'FinancialManagementController/doSreach.do?search='
					+ s;
		}
	</script>
</body>
</html>