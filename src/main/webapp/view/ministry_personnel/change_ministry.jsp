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
<title>薪资管理</title>
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
#append div {
	cursor: pointer;
    width: 190px;
    margin-left: 62px;
	font-size: 16px;
    padding-top: 11px;
    background-color: #fff;
    text-align: left;
    border-radius: 4px
}
#append div:hover {
	background-color: #ccc;
} 
</style>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>人事管理</a> <a> <cite>薪资管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
			<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so" style="position: absolute; z-index: 100" >
				<span>快速查询：</span>  <input type="text" name="username" id="search"
					placeholder="请输入姓名" autocomplete="off" class="layui-input">
			<div id="append" align="center"></div>
			</div>
		</div>
		<span class="x-right" style="line-height: 40px" id="number_ministry"></span>
		<table class="layui-table" id="table" style="margin-top: 80px;">
			<thead>
				<tr>
					<th>部门</th>
					<th>职务名称</th>
					<th>薪资</th>
					<th>修改时间</th>
					<th>薪资调整</th>
				</tr>
			</thead>
			<tbody id="ministry_table">
				<!-- 数据示例 -->
				<!-- end 数据示例 -->
			</tbody>
		</table>
		<table class="layui-table" id="table_search" style="display: none;">
			<thead>
				<tr>
					<th>编号</th>
					<th>名字</th>
					<th>部门</th>
					<th>基本薪资</th>
					<th>特别薪资</th>
					<th>调整时间</th>
					<th>薪资调整</th>
				</tr>
			</thead>
			<tbody id="salary_table">
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
	
	//ajax 分页查询总数据，以及页数
	var pn = 1;
	$(function() {
		getpage();
		showpage(pn);
	})

	function getpage() {
		$.ajax({
			url : 'ministryController/getPage.ajax',
			type : 'POST',
			data : {
				pageRecords:'5'
			},
			dataType : 'text',
			success : function(res) {
				var page = eval("(" + res + ")");
				$("#number_ministry").text("共有数据" + page.totalRecords + "条");
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
						pn= n ;
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
		//显示页面内容
		$.ajax({
					url : 'ministryController/getMinistryTable.ajax',
					type : 'POST',
					data : {
						n : n,
						pageRecords:'5'
					},
					dataType : 'text',
					success : function(res) {
						var jtable = eval("(" + res + ")");
						$("#ministry_table").html("");
						$
								.each(
										jtable,
										function(n, val) {
											var time = formatDate(val.changeTime);
											$("#ministry_table")
													.append(
															"<tr><td>"
																	+ val.sectionName
																	+ "</td><td>"
																	+ val.roleName
																	+ "</td><td><input id='"+val.salaryScaleId+"'type='text' class='layui-input' value='" 
																	+ val.salary
																	+ "'></td><td>"
																	+ time
																	+ "</td><td class='td-manage'><a class='layui-btn layui-btn-xs' title='修改薪资' onclick=\"changeMinistry(this,'"+val.salaryScaleId+"')\" href='javascript:;'>修改薪资</a></td></tr>")
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
		/*   hour + ':' +
		  min + ':' +
		  sec; */
		return newTime;
	}
	function changeMinistry(obj, salaryScaleId) {
		var salary = $("#"+salaryScaleId).val();
		layer.confirm('确认要修改吗？', function(id) {
			$.post('ministryController/changeMinistry.ajax',{id:salaryScaleId,salary:salary},function(res){
				if(res=="SUCCESS"){
					layer.msg('已修改!', {
						icon : 1,
						time : 500
					});
					getpage();
					showpage(pn);
				}else{			
					layer.msg('未修改!', {
					icon : 1,
					time : 500
				});
					
				}
			})
		});
	}
	$("#search").keyup(function(){
		var s = $("#search").val();
		if(s==null||s==""){
			$("#append").html("");
			return false;
		}
		$.post('accountController/getAccountTable.ajax',{searchCard:s,n:"1"},function(res){
			var atable = eval("(" + res + ")");
				$("#append").html("");
			 $.each(atable,function(n, val){
				$("#append").append("<div onclick=\"getSearch(this,'"+val.accountId+"')\">"+val.accountName+"-------"+val.accountNum+"</div>")
			} ); 
				
		});
	});
	function getSearch(obj,id) {
		var text = $(obj).text();
		$("#search").val(text);
		$.post('ministryController/getMinistryTableByAccountId.ajax',{id:id},function(res){
			if(res!="ERROR"){
			$("#append").html("");
			$("#table").html("");
			$("#table_search").show();
			$("#salary_table").html("");
			var val = eval("(" + res + ")");
			var time = formatDate(val.adjustTime);
			$("#salary_table").append("<tr><td>"+ val.accountNum+ "</td><td>"+ val.accountName+ "</td><td>"+ val.sectionName+ "</td><td>"+ val.salary+ "</td><td><input id='"+val.individualSalaryId+"'type='text' class='layui-input' value='" + val.adjustMoney+ "'></td><td>"+ time+ "</td><td class='td-manage'><a class='layui-btn layui-btn-xs' title='修改薪资' onclick=\"changeIndividual(this,'"+val.individualSalaryId+"')\" href='javascript:;'>修改特别薪资</a></td></tr>");
			}else{
				$("#append").html("");
				$("#search").val("");
				layer.msg('没有该员工数据!', {
					icon : 1,
					time : 500
				});
				return false;
			}
		});
	}
//修改特别薪资
	function changeIndividual(obj, individualSalaryId) {
		var salary = $("#"+individualSalaryId).val();
		console.log(salary);
		layer.confirm('确认要修改吗？', function(id) {
			//发异步删除数据
			$.post('ministryController/changeIndividual.ajax',{id:individualSalaryId,salary:salary},function(res){
				if(res=="SUCCESS"){
					layer.msg('已修改!', {
						icon : 1,
						time : 500
					});
					getpage();
					showpage(pn);
				}else{			
					layer.msg('未修改!', {
					icon : 1,
					time : 500
				});
					
				}
			})
		});
	}
	 </script>
</html>