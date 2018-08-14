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
<title>商品出入库统计表</title>
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

<body class="layui-anim layui-anim-up">
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>仓库管理</a> <a> <cite>商品出入库统计表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span>  <input type="text"  id="search"
					placeholder="请输入商品名称" autocomplete="off" class="layui-input" value="${sreach }">
						<input class="layui-input" placeholder="请输入时间" id="search2" value="${sreach2 }">
				<button class="layui-btn" onclick="doSearch()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
		<xblock>
		<span class="x-right" style="line-height: 40px" id="num">加载中</span> </xblock>
		<span class="x-right" style="line-height: 40px" >${Retrun}</span>
		<table class="layui-table">
			<thead>
				<tr>
					<th>仓库名称</th>
					<th>存储商品名称</th>
					<th>总数/瓶</th>
					<th>金额/元</th>
					<th>操作类型</th>
					<th>批号</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody id="infotable">

			</tbody>
		</table>
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->

	</div>

	<script>
		$(function() {
			var	sreach=$("#search").val();
			var	sreach2=$("#search2").val();
			$.ajax({
				url : 'goodsController/getPage.ajax',
				type : 'POST',
				data : {
					sreach : sreach,
					sreach2 : sreach2,
					pageRecords:'5'
				},
				dataType : 'text',
				success : function(res) {
					var page = eval("(" + res + ")");
					$("#num").html("共有数据数:" + page.totalRecords + "条")
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
		})

		function showpage(n) {
			var	sreach=$("#search").val();
			var	sreach2=$("#search2").val();
			//显示页面内容
			
					$.ajax({
						url : 'goodsController/getinfoTable.ajax',
						type : 'POST',
						data : {
							n : n,
							sreach : sreach,
							sreach2 : sreach2,
							pageRecords:'5'
						},
						dataType : 'text',
						success : function(res) {
							var jtable = eval("(" + res + ")");
							$("#infotable").html("");
							$.each(jtable,function(n, val) {
												$("#infotable").append(
																"<tr id='"+val.goodsOutInId+"'><td>"+ val.warehouseName+"</td><td>"+val.commodityName +"</td><td>"+val.materialInventoryAmount
																+"</td><td>"+val.materialInventoryValue+"</td><td>"+val.goodsType+"</td><td>"+val.batchNumber
																+"</td><td>"+val.time
																 + " </td></tr>");
											});
						},
						error : function() {

						}
					});
		}
		function doSearch(){
			var s=$("#search").val();
			var s2=$("#search2").val();
			window.location.href='<%=basePath%>goodsController/sreach.do?sreach='+s+'&sreach2='+s2;
		}

	</script>
</body>
</html>