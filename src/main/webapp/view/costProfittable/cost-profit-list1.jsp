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
<title>商品销售统计</title>
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
		<span class="layui-breadcrumb"> <a>销售统计</a> <a> <cite>商品销售统计</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<%-- <form class="layui-form layui-col-md12 x-so">
				<span>商品查询：</span><input type="text" placeholder="请输入品名"
					autocomplete="off" class="layui-input" id="searchGoodsName"
					value="${searchGoodsName}">
				<!-- <button class="layui-btn" lay-submit="" lay-filter="search"
					onclick="doSearch()" type="button">
					<i class="layui-icon">&#xe615;</i>
				</button> -->
				<span>客户查询：</span><input type="text" placeholder="请输入客户名称"
					autocomplete="off" class="layui-input" id="searchCustomer"
					value="${searchCustomer}">
				<!-- <button class="layui-btn" lay-submit="" lay-filter="search"
					onclick="doSearch()" type="button">
					<i class="layui-icon">&#xe615;</i>
				</button> -->
				<div class="layui-input-inline">
					<span>开始时间：</span><input type="date" placeholder="请选择时间"
						autocomplete="off" class="layui-input" id="searchTimeStart"
						value="${searchTimeStart}">
				</div>
				<div class="layui-input-inline">
					<span>结束时间：</span><input type="date" placeholder="请选择时间"
						autocomplete="off" class="layui-input" id="searchTimeEnd"
						value="${searchTimeEnd}">
					<button class="layui-btn" lay-submit="" lay-filter="search"
						onclick="doSearch()" type="button" style="width: 60px">
						<i class="layui-icon">&#xe615;</i>
					</button>
				</div>
			</form> --%>
		</div>
		<xblock> <span class="x-right" style="line-height: 40px">共有数据：<a
			id="num"></a> 条
		</span> </xblock>

		<table class="layui-table">
			<thead>
				<tr>
					<!-- <th>原料ID</th> -->
					<th>商品名称</th>
					<th>数量</th>
					<th>单价</th>
					<th>金额</th>
					<th>成本</th>
					<th>利润</th>
					<th>往来客户</th>
					<th>单据时间</th>
					<th>单据ID</th>
					<th>经手人</th>
					<!-- <th>商品ID</th> -->

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
		/* var searchGoodsName = "";
		var searchTimeStart="";
		var searchTimeEnd=""; */
		$(function() {
			/* searchGoodsName = $("#searchGoodsName").val();		
			searchTimeStart = $("#searchTimeStart").val();
			searchTimeEnd = $("#searchTimeEnd").val(); */

			/* var str = date.Format("yyyy-MM-dd"); */
			$.ajax({
				url : 'financialManagementController/docblrtjPages.ajax',
				type : 'POST',
				data : {
				/* searchGoodsName : searchGoodsName,			
				searchTimeStart : searchTimeStart,
				searchTimeEnd:searchTimeEnd */
				},
				dataType : 'text',
				success : function(res) {
					var page = eval("(" + res + ")");
					$("#num").html(page.total)
					//初始化分页插件
					kkpager.generPageHtml({
						pno : '1',
						mode : 'click', //设置为click模式
						//总页码  
						total : page.totalRecords,
						//总数据条数  
						totalRecords : page.total,
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
				url : 'financialManagementController/getCblrtjBills.ajax',
				type : 'POST',
				data : {
					n : n
				/* searchGoodsName : searchGoodsName,
				searchTimeEnd : searchTimeEnd,
				searchTimeStart:searchTimeStart */
				},
				dataType : 'text',
				success : function(res) {
					var jtable = eval("(" + res + ")");
					$("#infoTable").html("");
					$.each(jtable, function(n, val) {
						$("#infoTable").append(
								"<tr><td>" + val.commodityName + "</td><td>"
										+ val.sum + "</td><td>" + val.price
										+ "</td><td>" + val.total + "</td><td>"
										+ val.capital + "</td><td>"
										+ val.profit + "</td><td>"
										+ val.customerName + "</td><td>"
										+ val.saleTime + "</td><td>"
										+ val.saleBillId + "</td><td>"
										+ val.accountName + "</td></tr>");
					});
				},
				error : function() {

				}
			});
		}

		/* function doSearch() {
			var searchGoodsName = $("#searchGoodsName").val();
			var searchCustomer = $("#searchCustomer").val();
			var searchTimeStart = $("#searchTimeStart").val();			
			window.location.href = 'financialManagementController/doGoodsTjSreach.do?searchGoodsName='
					+ searchGoodsName+ '&searchTimeStart=' + searchTimeStart+'&searchTimeEnd='+searchTimeEnd;

		} */
	</script>
</body>
</html>