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

<body class="layui-anim layui-anim-up">
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>人员管理</a> <a> <cite>部门管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span>  <input type="text" name="username" id="search"
					placeholder="请输入部门名称" autocomplete="off" class="layui-input" value="${sreach }">
				<button class="layui-btn" onclick="doSearch()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加部门','view/department/department-add.jsp',500,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height: 40px" id="num">加载中</span> </xblock>
		<span class="x-right" style="line-height: 40px" >${Retrun}</span>
		<table class="layui-table">
			<thead>
				<tr>
					<th>部门名称</th>
					<th>部门图标</th>
					<th>状态</th>
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
				url : 'sectionInfoController/getPage.ajax',
				type : 'POST',
				data : {
					sreach : sreach,
					pageRecords:'5'
				},
				dataType : 'text',
				success : function(res) {
					var page = eval("(" + res + ")");
					$("#num").html("共有部门数:" + page.totalRecords + "条")
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
			$
					.ajax({
						url : 'sectionInfoController/getinfoTable.ajax',
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
								var str="";
								var img="";
								if(val.sectionStatus=='0'){
									str+="<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini' >已启用</span></td>";
									str+="<td class='td-manage'>";
									str+="<a onclick=\"member_stop(this,'"+val.sectionId+"')\" href='javascript:;' title='已启用'> <i class='layui-icon'>";
									img+="&#xe601;";
								}else{
									str+="<td class='td-status'><span class='layui-btn layui-btn-normal  layui-btn-mini layui-btn-disabled' >已停用</span></td>";
									str+="<td class='td-manage'>";
									str+="<a onclick=\"member_stop(this,'"+val.sectionId+"')\" href='javascript:;' title='已停用'> <i class='layui-icon'>";
										img+="&#xe62f;";
								}
												$("#infotable").append(
																"<tr id='"+val.sectionId+"'><td>"+ val.sectionName+ "</td><td>"+ "<img src='"+val.sectionImg+"' class='left-menu-icon'>" 
																 +"</td>" 
																 +str
																 +img
																 +"</i></a>"
																 +"<a title='编辑'onclick=x_admin_show('编辑','sectionInfoController/updateSection.do?sectionId="+ val.sectionId+ "',600,400) href='javascript:;'>"
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
			window.location.href='<%=basePath%>sectionInfoController/sreach.do?sreach='+s;
		}

	</script>
	<script>
	function member_stop(obj, id) {
		layer.confirm('确认要更改该用户状态吗？', function(index) {
			if ($(obj).attr('title') == '已启用') {
				//发异步把用户状态进行更改
					$.ajax({
					url : 'sectionInfoController/block.ajax',
					type : 'POST',
					data : {
						sectionId : id
					},
					dataType : 'text',
					success : function(res) {
						switch (res) {
						case "SUCCESS":
							$(obj).attr('title', '已停用')
							$(obj).find('i').html('&#xe62f;');
							$(obj).parents("tr").find(".td-status").find('span')
									.addClass('layui-btn-disabled').html('已停用');
							layer.msg('停用成功!', {
								icon : 1,
								time : 500
							});
							break;
						case "ERROR":
							layer.msg("错误：部门中人员不为0 ，请检查!", {
								icon : 5,
								time : 1500
							});
							break;
						}

					},
					error : function() {

					}
				});
				//.....

			} else {
				$.ajax({
					url : 'sectionInfoController/start.ajax',
					type : 'POST',
					data : {
						sectionId : id
					},
					dataType : 'text',
					success : function(res) {
						$(obj).attr('title', '已启用')
						$(obj).find('i').html('&#xe601;');
						$(obj).parents("tr").find(".td-status").find('span')
								.removeClass('layui-btn-disabled').html('已启用');
						layer.msg('启用成功!', {
							icon : 1,
							time : 500
						});

					},
					error : function() {

					}
				});

			}

		});
	}
	
	</script>
</body>
</html>