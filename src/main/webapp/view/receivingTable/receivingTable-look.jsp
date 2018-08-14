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
<title>往来账务管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_blue.css" />
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

<body class="layui-anim layui-anim-up">
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>财务管理</a> <a> <cite>往来账务详情</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so">
				<div class="layui-input-inline">
					<span>查询客户：</span><input type="text" placeholder="客户名称"
						autocomplete="off" class="layui-input" id="searchCustomer"
						value="<%=request.getParameter("customerName") %>"> 
					<button class="layui-btn" lay-submit="" lay-filter="search"
						onclick="doSearch()" type="button" style="display: none">
						<i class="layui-icon">&#xe615;</i>
					</button>
				</div>

			</form>
		</div>
		<xblock> <%-- <button class="layui-btn"
			onclick="x_admin_show('添加原料信息','<%=basePath%>materialInfoController/AddUI.do',500,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button> --%> <span class="x-right" style="line-height: 40px">共有数据：<a
			id="num"></a> 条
		</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<!-- <th>原料ID</th> -->
					<th>单据编号</th>
					<th>往来单位名称</th>
					<th>经手人</th>
					<th>商品名称</th>
					<th>数量</th>
					<th>单价</th>
					<th>总金额</th>
					<th>已付金额</th>
					<th>未付金额</th>
					<th>时间</th>
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
	<script type="text/javascript">
		/*用户查询*/
		var searchCustome;
		$(function() {
			searchCustomer = $("#searchCustomer").val();		
			$.ajax({
				url : 'moneyTableController/getMoneyTableLooksPage.ajax',
				type : 'POST',
				data : {
					searchCustomer : searchCustomer
					
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
			//未完待续，页码和查询的总数量已写好，待写分页
			showpage(1);
		});
		function showpage(n) {
			//显示页面内容
			$.ajax({
				url : 'moneyTableController/getMoneyTableLooksBilss.ajax',
				type : 'POST',
				data : {
					n : n,
					searchCustomer : searchCustomer					
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
										+ val.sum + "</td><td>"
										+ val.price + "</td><td>"
										+ val.total + "</td><td>"
										+ val.paidAmount + "</td><td>"
										+ val.unpaidAmount + "</td><td>"
										+ val.completeTime + "</td></tr>");
					});
				},
				error : function() {
				}
			});
		};
		Date.prototype.Format = function(format) {
			var o = {
				"M+" : this.getMonth() + 1, //月份 
				"d+" : this.getDate(), //日 
				"h+" : this.getHours(), //小时 
				"m+" : this.getMinutes(), //分 
				"s+" : this.getSeconds(), //秒 
				"S" : this.getMilliseconds()
			//毫秒 
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var k in o) {
				if (new RegExp("(" + k + ")").test(fmt)) {
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
				}
			}
			return fmt;
		}

		/* function doSearch() {
			var searchCustomer = $("#searchCustomer").val();				
			window.location.href = 'moneyTableController/doSearchCustomer.do?searchCustomer='
					+ searchCustomer;
		} */
	</script>
</body>
</html>