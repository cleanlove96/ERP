<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>固定资产管理</title>
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
		<span class="layui-breadcrumb"> <a>固定资产管理</a> <a> <cite>固定资产信息</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span>  <input type="text" name="fixedAssetsName" id="search"
					placeholder="请输入名称" autocomplete="off" class="layui-input" value="${sreach }">
				<button class="layui-btn" onclick="doSearch()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加固定资产','view/fixed_assets/fixed_assets_add.jsp',600,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height: 40px" id="number_assets"></span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>编号</th>
					<th>名称</th>
					<th>价值金额（元）</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="assets_table">
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
/*固定资产-删除*/
function member_del(obj, fixedAssetsId) {
	layer.confirm('确认要删除吗？', function(id) {
		//发异步删除数据
		$.post('fixedAssetsController/deleteFixedAssets.ajax',{id:fixedAssetsId},function(res){
			if(res=="SUCCESS"){
				$(obj).parents("tr").remove();
				if($("#assets_table tr").length<1){
					pn = pn-1;
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
			}else{			
				layer.msg('未删除!', {
				icon : 1,
				time : 500
			});
				
			}
		})
	});
}
//ajax 分页查询总数据，以及页数
var sreach;
var pn=1;
$(function() {
	sreach=$("#search").val();
	getpage();
	showpage(pn);
})

function getpage(){
	$.ajax({
		url : 'fixedAssetsController/getPage.ajax',
		type : 'POST',
		data : {
			sreach:sreach,
		},
		dataType : 'text',
		success : function(res) {
			var page = eval("(" + res + ")");
			$("#number_assets").text("共有数据"+page.totalRecords+"条");
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

			},true);
		},
		error : function() {

		}
	});
}
function showpage(n){
	//显示页面内容
	$.ajax({
		url : 'fixedAssetsController/getFixedAssetsTable.ajax',
		type : 'POST',
		data : {
			n:n,
			sreach:sreach
		},
		dataType : 'text',
		success : function(res) {
			var jtable = eval("(" + res + ")");
			$("#assets_table").html("");
			$.each(jtable, function(n, val) {
				var time = formatDate(val.fixedAssetsTime);
				$("#assets_table").append(
				"<tr><td>" +val.fixedAssetsNum+"</td><td>" +val.fixedAssetsName+"</td><td>"+val.fixedAssetsPrice+ "</td><td>"+time+"</td><td class='td-manage'><a title='编辑' onclick=\"x_admin_show('编辑','fixedAssetsController/fixedAssetsEdit.do?fixedAssetsId="+val.fixedAssetsId+"',600,500)\" href='javascript:;'> <i class='layui-icon'>&#xe642;</i></a> <a title='删除' onclick=\"member_del(this,'"+val.fixedAssetsId+"')\" href='javascript:;'> <i class='layui-icon'>&#xe640;</i></a></td></tr>")
			});
		},
		error : function() {

		}
	});
}
//搜索
function doSearch(){
	var s=$("#search").val();
	window.location.href='fixedAssetsController/doSreach.do?sreach='+s;
}
//时间格式转换
function formatDate(time){
    var date = new Date(time);

    var year = date.getFullYear(),
        month = date.getMonth() + 1,//月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var newTime = year + '-' +
                month + '-' +
                day + ' ' ;
              /*   hour + ':' +
                min + ':' +
                sec; */
    return newTime;         
}

</script>
</html>