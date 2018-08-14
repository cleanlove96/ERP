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
<title>客户管理</title>
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
		<span class="layui-breadcrumb"> <a>客户关系管理</a> <a> <cite>客户恢复中心</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span>  <input type="text" name="username" id="search"
					placeholder="请输入姓名" autocomplete="off" class="layui-input" value="${sreach }">
				<button class="layui-btn" onclick="doSearch()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
		<table class="layui-table">
			<thead>
				<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>公司</th>
					<th>电话</th>
					<th>QQ</th>
					<th>邮箱</th>
					<th>地址</th>
					<th>备注</th>
					<th>创建时间</th>
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
/*客户-恢复*/
function member_back(obj, customerId) {
	layer.confirm('确认要恢复吗？', function(id) {
		//发异步删除数据
		$.post('customerController/backCustomer.ajax',{id:customerId},function(res){
			if(res=="SUCCESS"){
				$(obj).parents("tr").remove();
				layer.msg('已恢复!', {
					icon : 1,
					time : 500
				});
			}else{			
				layer.msg('未恢复!', {
				icon : 1,
				time : 500
			});
				
			}
		})
	});
}
//ajax 分页查询总数据，以及页数
var sreach;
$(function() {
	sreach=$("#search").val();
	console.log(sreach);
	$.ajax({
		url : 'customerController/getPage.ajax',
		type : 'POST',
		data : {
			sreach:sreach,
			status:"1"
		},
		dataType : 'text',
		success : function(res) {
			var page = eval("(" + res + ")");
			$("#number_customer").text("共有数据"+page.totalRecords+"条");
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

function showpage(n){
	//显示页面内容
	$.ajax({
		url : 'customerController/getCustomerTable.ajax',
		type : 'POST',
		data : {
			n:n,
			sreach:sreach,
			status:"1"
		},
		dataType : 'text',
		success : function(res) {
			var jtable = eval("(" + res + ")");
			$("#customer_table").html("");
			$.each(jtable, function(n, val) {
				var time = formatDate(val.createTime);
				$("#customer_table").append(
				"<tr><td>" +val.customerName+"</td><td>" +val.customerSex+"</td><td>"+val.customerCompany+ "</td><td>"+val.customerTel+"</td><td>"+val.customerQq+"</td><td>"+val.customerEmail+"</td><td>"+val.customerAddr+"</td><td>"+val.customerDemand+"</td><td>"+time+"</td><td class='td-manage'><a class='layui-btn layui-btn-xs' title='恢复' onclick=\"member_back(this,'"+val.customerId+"')\" href='javascript:;'>恢复</a></td></tr>")
			});
		},
		error : function() {

		}
	});
}
//搜索
function doSearch(){
	var s=$("#search").val();
	window.location.href='customerController/doSreach2.do?sreach='+s;
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