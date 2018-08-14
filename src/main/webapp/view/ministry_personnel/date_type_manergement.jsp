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
		<span class="layui-breadcrumb"> <a>系统管理</a> <a> <cite>时间管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<xblock>
		<form class="layui-form" id="fm">
			<div class="layui-form-item">
				<label for="time" class="layui-form-label">日期</label>
				<div class="layui-input-inline">
					<input type="text" id="time" name="dateTypeDate"
						class="layui-input">
				</div>
				<label for="L_name" class="layui-form-label"> 名称 </label>
				<div class="layui-input-inline">
					<input type="text" id="L_name" name="dateTypeName" required
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="dateTypeType" value="休" checked
							title="休"> <input type="radio" name="dateTypeType"
							value="班" title="班">
					</div>
					<button class="layui-btn" lay-filter="save" lay-submit>
						</i>添加
					</button>
				</div>
			</div>
		</form>
		<hr>
		<span class="x-right" style="line-height: 40px" id="number_customer"></span>
		<div class="layui-form">
			<div class="layui-input-inline">
				<button class="layui-btn" onclick="show('休')">休</button>
				<button class="layui-btn" onclick="show('班')">班</button>
			</div>
		</div>
		</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>时间</th>
					<th>名称</th>
					<th>类型</th>
					<th>修改时间</th>
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
<script type="text/javascript">
var type="";
var pn = 1;
	layui.use([ 'form', 'layer', 'laydate' ], function() {
		$ = layui.jquery;
		var form = layui.form, laydate = layui.laydate, layer = layui.layer;
		laydate.render({
			elem : '#time' //指定元素
		});
		form.on('submit(save)', function(data) {
			console.log(data.field);
			$.post('ministryController/addDateType.ajax', data.field, function(
					res) {
				if (res == "SUCCESS") {
					layer.msg('添加成功!', {
						icon : 1,
						time : 500
					});
				} else {
					layer.msg("添加失败", {
						icon : 7,
						time : 500
					});
				}
			});
			return false;
		});
	});
	
	/*日子-删除*/
	function day_del(obj, dateTypeId) {
		layer.confirm('确认要删除吗？', function(id) {
			//发异步删除数据
			$.post('ministryController/deleteDateType.ajax', {
				id : dateTypeId
			}, function(res) {
				if (res == "SUCCESS") {
					$(obj).parents("tr").remove();
					if ($("#customer_table tr").length < 1) {
						pn = pn - 1;
					}
					if(pn==0){
						pn=1;
					}
					getpage();
					showpage(pn);
					layer.msg('已删除!', {
						icon : 1,
						time : 500
					});
					/*location.replace(location.href);*/
				} else {
					layer.msg('未删除!', {
						icon : 1,
						time : 500
					});

				}
			})
		});
	}
	//ajax 分页查询总数据，以及页数
	$(function() {
		getpage();
		showpage(pn);
	})

	
	function show(str) {
			type = str;
			getpage();
			pn=1;
			showpage(pn);
	}
	function getpage() {
		$.ajax({
			url : 'ministryController/getDayPage.ajax',
			type : 'POST',
			data : {
				status : type
			},
			dataType : 'text',
			success : function(res) {
				var page = eval("(" + res + ")");
				$("#number_customer").text("共有数据" + page.totalRecords + "条");
				//初始化分页插件
				kkpager.generPageHtml({
					pno : pn,
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
						pn = n;
						//处理完后可以手动条用selectPage进行页码选中切换
						this.selectPage(n);
					},
					//getHref是在click模式下链接算法，一般不需要配置，默认代码如下
					getHref : function(n) {
						return '#';
					}

				}, true);
			},
			error : function() {

			}
		});
	}
	function showpage(n) {
		getpage();
		//显示页面内容
		$
				.ajax({
					url : 'ministryController/getDayTable.ajax',
					type : 'POST',
					data : {
						n : n,
						status : type
					},
					dataType : 'text',
					success : function(res) {
						var jtable = eval("(" + res + ")");
						$("#customer_table").html("");
						$
								.each(
										jtable,
										function(n, val) {
											var time = formatDate(val.changeTime);
											$("#customer_table")
													.append(
															"<tr><td>"
																	+ val.dateTypeDate
																	+ "</td><td>"
																	+ val.dateTypeName
																	+ "</td><td>"
																	+ val.dateTypeType
																	+ "</td><td>"
																	+ time
																	+ "</td><td class='td-manage'><a title='编辑' onclick=\"x_admin_show('编辑','ministryController/dayEdit.do?dateTypeId="
																	+ val.dateTypeId
																	+ "',600,500)\" href='javascript:;' class='layui-btn'>编辑</a> <a title='删除' onclick=\"day_del(this,'"
																	+ val.dateTypeId
																	+ "')\" href='javascript:;' class='layui-btn'>删除</a></td></tr>")
										});
					},
					error : function() {

					}
				});
	}
	//时间格式转换
	function formatDate(time) {
		var date = new Date(time);

		var year = date.getFullYear(), month = date.getMonth() + 1, //月份是从0开始的
		day = date.getDate(), hour = date.getHours(), min = date.getMinutes(), sec = date
				.getSeconds();
		var newTime = year + '-' + month + '-' + day + ' ';
		hour + ':' + min + ':' + sec;
		return newTime;
	}
</script>
</html>