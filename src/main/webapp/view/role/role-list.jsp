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
		<span class="layui-breadcrumb"> <a>权限管理</a> <a> <cite>角色管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span>  <input type="text" name="username" id="search"
					placeholder="请输入角色名称" autocomplete="off" class="layui-input" value="${sreach }">
				<button class="layui-btn" onclick="doSearch()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加角色','roleController/insertRole.do',500,500)" id="add">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height: 40px" id="num">加载中</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>角色名称</th>
					<th>角色描述</th>
					<th>所属部门</th>
					<th>角色状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tfoot id="roletable">
				
			</tfoot>
		</table>
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->

	</div>
	<script>
		/*用户-删除*/
		function member_del(obj, roleId) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$.ajax({
					url : 'roleController/deletInfo.ajax',
					type : 'POST',
					data : {
						roleId : roleId
					},
					dataType : 'text',
					success : function(res) {
                         
					},
					error : function() {

					}
				});
				//....
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
				window.location.reload();
			});
		}
	</script>
	<script>
		$(function() {
			var sreach = $("#search").val();
			$.ajax({
				url : 'roleController/getPage.ajax',
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
			var sreach = $("#search").val();
			//显示页面内容
			$.ajax({
				url : 'roleController/getRoleTable.ajax',
				type : 'POST',
				data : {
					n : n,
					sreach : sreach,
					pageRecords:'5'
				},
				dataType : 'text',
				success : function(res) {
					var jtable = eval("(" + res + ")");
					$("#roletable").html("");
					$.each(jtable, function(n, val) {
						var str="";
						var img="";
						if(val.roleStatus=='0'){
							str+="<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini'>已启用</span></td>";
							str+="<td class='td-manage'>";
							str+="<a onclick=\"member_stop(this,'"+val.roleId+"')\" href='javascript:;' title='已启用'> <i class='layui-icon'>";
							img+="&#xe601;";
						}else{
							str+="<td class='td-status'><span class='layui-btn layui-btn-normal  layui-btn-mini layui-btn-disabled'>已停用</span></td>";
							str+="<td class='td-manage'>";
							str+="<a onclick=\"member_stop(this,'"+val.roleId+"')\" href='javascript:;' title='已停用'> <i class='layui-icon'>";
								img+="&#xe62f;";
						}
						$("#roletable").prepend(
								"<tr id='"+val.roleId+"'><td>"+ val.roleName+ "</td><td>"+val.roleDesc+"</td><td>"+val.sectionName
								        +"</td>"
								        +str
										+img
										+"</i></a>"
								        +"<a title='编辑' onclick=x_admin_show('编辑','roleController/updateRole.do?roleId="+ val.roleId+ "',600,400) href='javascript:;'>"
										+ " <i class='layui-icon'>&#xe642;</i></a>"
										+ "<a title='分配权限' onclick=x_admin_show('分配权限','roleController/aollt.do?roleId="+ val.roleId+ "',400,400) href='javascript:;'>"
										+ " <i class='layui-icon'>&#xe614;</i></a>"
										+"</td></tr>");

					});
				},
				error : function() {

				}
			});
		}

		function doSearch() {
			var s = $("#search").val();
			window.location.href = '<%=basePath%>roleController/sreach.do?sreach=' + s;
		}
	</script>
	<script>
	function member_stop(obj, id) {
		layer.confirm('确认更改该用户状态吗？', function(index) {
			if ($(obj).attr('title') == '已启用') {
				//发异步把用户状态进行更改
					$.ajax({
					url : 'roleController/block.ajax',
					type : 'POST',
					data : {
						roleId : id
					},
					dataType : 'text',
					success : function(res) {
						$(obj).attr('title', '已停用')
						$(obj).find('i').html('&#xe62f;');
						$(obj).parents("tr").find(".td-status").find('span')
								.addClass('layui-btn-disabled').html('已停用');
						layer.msg('停用成功!', {
							icon : 1,
							time : 500
						});

					},
					error : function() {

					}
				});
				//.....

			} else {
				$.ajax({
					url : 'roleController/start.ajax',
					type : 'POST',
					data : {
						roleId : id
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