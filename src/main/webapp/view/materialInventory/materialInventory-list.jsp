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
<title>原料库存表</title>
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
		<span class="layui-breadcrumb"> <a>仓库管理</a> <a> <cite>原料库存表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span>  <input type="text"  id="search"
					placeholder="请输入原料名称" autocomplete="off" class="layui-input" value="${sreach }">
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
					<th>存储原料名称</th>
					<th>总数/斤</th>
					<th>金额/元</th>
					<th>操作</th>
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
			$.ajax({
				url : 'materialInventoryController/getPage.ajax',
				type : 'POST',
				data : {
					sreach : sreach,
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
			//显示页面内容
			
					$.ajax({
						url : 'materialInventoryController/getinfoTable.ajax',
						type : 'POST',
						data : {
							n : n,
							sreach : sreach,
							pageRecords:'5'
						},
						dataType : 'text',
						success : function(res) {
							var jtable = eval("(" + res + ")");
							$("#infotable").html("");
							$.each(jtable,function(n, val) {
												$("#infotable").append(
																"<tr id='"+val.materialInventoryId+"'><td>"+ val.warehouseName+"</td><td>"+val.materialName +"</td><td>"+val.materialInventoryAmount
																+"</td><td>"+val.materialInventoryValue+"</td><td>"
																+"<a title='编辑'onclick=x_admin_show('编辑','materialInventoryController/updateGcountUI.do?materialInventoryId="+val.materialInventoryId+ "',600,400) href='javascript:;'>"
																+ " <i class='layui-icon'>&#xe642;</i></a>"
																 + " </td></tr>");
											});
						},
						error : function() {

						}
					});
		}
		function doSearch(){
			var s=$("#search").val();
			window.location.href='<%=basePath%>materialInventoryController/sreach.do?sreach='+s;
		}

	</script>
</body>
</html>