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
<title>原料基本信息</title>
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
		<span class="layui-breadcrumb"> <a>基本信息管理</a> <a> <cite>原料信息管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span><input type="text" placeholder="请输入品名"
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
			onclick="x_admin_show('添加原料信息','<%=basePath%>materialInfoController/AddUI.do',500,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height: 40px" >共有数据：<a id="num"></a> 条</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<!-- <th>原料ID</th> -->
					<th>原料名称</th>
					<th>供货商</th>
					<th>单位</th>
					<th>价格</th>
					<th>创建时间</th>
					<th>上次修改时间</th>					
					<th>状态</th>
					<th>操作</th>
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
		/*用户-控制*/
		function member_stop(obj, id) {
			var	Status;
			
			
			layer.confirm('确认更改该用户状态吗？', function(index) {
				if ($(obj).attr('title') == '启用') {
					//发异步把用户状态进行更改
					//.....
				Status="1";					
					$(obj).attr('title', '停用')
					$(obj).find('i').html('&#xe62f;');
					$(obj).parents("tr").find(".td-status").find('span')
							.addClass('layui-btn-disabled').html('已停用');
					layer.msg('停用成功!', {
						icon : 1,
						time : 500
					});		$.ajax({
						url:'materialInfoController/updateBySysMaterialStatus.ajax',
						data:{
							materialId:id,
							Status:Status
						},
						type:'post',
						dataType:'text',
						success : function(res) {
							if(res="succee"){								
								layer.alert("修改成功",{icon : 1,
									time : 500
								});
							}else{
								layer.alert("失败成功",{icon : 6,
									time : 500
								},function() {
									var index = parent.layer.getFrameIndex(window.name);
									parent.layer.close(index);									
									parent.location.reload();
								});
							}
						}
						
					});

		
				} else {
					Status="0";
					$(obj).attr('title', '启用')
					$(obj).find('i').html('&#xe601;');
					$(obj).parents("tr").find(".td-status").find('span')
							.removeClass('layui-btn-disabled').html('已启用');
					layer.msg('启用成功!', {
						icon : 1,
						time : 500
					});
					$.ajax({
						url:'materialInfoController/updateBySysMaterialStatus.ajax',
						data:{
							materialId:id,
							Status:Status
						},
						type:'post',
						dataType:'text',
						success : function(res) {
							if(res="succee"){								
								layer.alert("修改成功",{icon : 1,
									time : 500
								});
							}else{
								layer.alert("失败成功",{icon : 6,
									time : 500
								},function() {
									var index = parent.layer.getFrameIndex(window.name);
									parent.layer.close(index);									
									parent.location.reload();
								});
							}
						}
						
					});
				}

			});
	
		}

		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				layer.msg('已删除!', {
					//icon : 1,
					time : 500
				});
			});
		}

		/*用户查询*/
		var search;
		$(function() {
			search = $("#search").val();
			$.ajax({
				url : 'materialInfoController/getPage.ajax',
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
			$
					.ajax({
						url : 'materialInfoController/materialInfo.ajax',
						type : 'POST',
						data : {
							n : n,
							search : search
						},
						dataType : 'text',
						success : function(res) {
							var jtable = eval("(" + res + ")");
							$("#infoTable").html("");
							$
									.each(
											jtable,
											function(n, val) {
												var type;
												if(val.materialStatus==0){
													type="已启用";	
													type1="";
												}else{
													type="已停用";
													type1="layui-btn-disabled";
												}
												$("#infoTable")
														.append(
																"<tr><td style='display:none'>"
																		+ val.materialId
																		+ "</td><td>"
																		+ val.materialName
																		+ "</td><td>"
																		+ val.customerId
																		+ "</td><td>"
																		+ val.materialUnit
																		+ "</td><td>"
																		+ val.price
																		+ "</td><td>"
																		+ val.materialCreateTime
																		+ "</td><td>"
																		+ val.materialUpdateTime //字符串拼接的时候遇到双引号要用单引号   \"member_stop加斜杠进行转义
																		+ "</td><td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini "+type1+"'>"+type+"</span></td><td class='td-manage'><a onclick=\"member_stop(this,'"+val.materialId+"')\"href='javascript:;' title='停用'> <i class='layui-icon'>&#xe601;</i></a> <a title='编辑'onclick=\"x_admin_show('编辑','materialInfoController/materialInfoById.do?SysMaterialId="+val.materialId+"',600,400)\" href='javascript:;'> <i class='layui-icon'>&#xe642;</i></a></td></tr>");
												
											});
						},
						error : function() {

						}
					});
		}

		function doSearch() {
			var s = $("#search").val();
		
			window.location.href = 'materialInfoController/doSreach.do?search='+ s;
			
		}
	</script>
</body>
</html>