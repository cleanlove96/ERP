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
</style>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>人事管理</a> <a> <cite>薪资结算</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-form">
		<div class="layui-form-item">
				<!-- <label for="L_education" class="layui-form-label"></label> -->
				<div class="layui-input-inline">
					<select id="section" lay-filter="select">
						<option>53°D酒业有限公司</option>
					<c:forEach items="${sf}" var="section">
						<option value="${section.sectionId}">${section.sectionName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="layui-input-inline" id="js">
				</div>
			</div>
			</div>
		<table class="layui-table">
			<thead id="thead">
				<tr>
					<th>编号</th>
					<th>名字</th>
					<th>基本工资</th>
					<th>养老</th>
					<th>医疗</th>
					<th>失业</th>
					<th>公积金</th>
					<th>个税</th>
					<th>加班</th>
					<th>累计扣款</th>
					<th>实发工资</th>
				</tr>
			</thead>
			<tbody id="salary_table">
				<!-- 数据示例 -->
				<!-- end 数据示例 -->
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
var paytable1;
var state;
//结算全部员工工资
function jsgz() {
	$.post('ministryController/liquidation.ajax',{pay:paytable1},function(res){
		if(res!=0){
			layer.alert("本次结算了"+res+"名员工工资", {
				icon : 1,
				time : 500
			});
			$("#js").html("");
			state="no";
		}
	});
}
//加载全部员工工资
$(function(){
	var id =null;
	//判断本月是否结算了工资
	$.post('ministryController/isLiquidation.ajax',function(res){
		if(res=="yes"){
			state="yes";
			$("#js").html("<h2 class='layui-input' style='border: none;'>上月工资已结算</h2>");
			
		}else{
			state="no";
			$("#js").html("<button type='button' onclick='jsgz()' class='layui-btn'>结算</button>");
		}
	});
	//显示所有工资
	$.post('ministryController/showAllSalary.ajax',{id:id},function(res){
		paytable1=res;
		if(res=="ERROR"){
			$("#salary_table").html("");
			$("#thead").show();
			return false;
		}
		var jtable = eval("(" + res + ")");
		$("#salary_table").html("");
		$("#thead").hide();
	 	 $.each(jtable,function(n, val) {
			$("#salary_table").append("<tr><th>编号</th><th>名字</th><th>基本工资</th><th>养老</th><th>医疗</th><th>失业</th><th>公积金</th><th>个税</th><th>加班</th><th>累计扣款</th><th>实发工资</th></tr><tr><td>"+val.accountNum+"</td><td>"+val.accountName+"</td><td>"+val.salary+"</td><td>-"+val.yanglaoSelf+"</td><td>-"+val.yiliaoSelf+"</td><td>-"+val.shiyeSelf+"</td><td>-"+val.houseFundSelf+"</td><td>-"+val.payMoney+"</td><td>+"+val.overtimePay+"</td><td>-"+val.vacateDeduct+"</td><td>"+val.finallyPay+"</td></tr>")
		});
	});
});
layui.use([ 'form', 'layer' ], function() {
	$ = layui.jquery;
	var form = layui.form, layer = layui.layer,table = layui.table;
	form.on('select(select)',function(data){
		console.log(state);
		if(data.value!="53°D酒业有限公司"){
			$("#js").html("");
		}else{
			if(state=="yes"){
				$("#js").html("<h2 class='layui-input' style='border: none;'>上月工资已结算</h2>");
			}else{
			$("#js").html("<button type='button' onclick='jsgz()' class='layui-btn'>结算</button>");
			}
		}
		$.post('ministryController/showSalary.ajax',{id:data.value},function(res){
			if(res=="ERROR"){
				$("#salary_table").html("");
				$("#thead").show();
				return false;
			}
			var jtable = eval("(" + res + ")");
			paytable=eval("(" + res + ")");
			paytable1=res;
			$("#salary_table").html("");
			$("#thead").hide();
		 	 $.each(jtable,function(n, val) {
				$("#salary_table").append("<tr><th>编号</th><th>名字</th><th>基本工资</th><th>养老</th><th>医疗</th><th>失业</th><th>公积金</th><th>个税</th><th>加班</th><th>累计扣款</th><th>实发工资</th></tr><tr><td>"+val.accountNum+"</td><td>"+val.accountName+"</td><td>"+val.salary+"</td><td>-"+val.yanglaoSelf+"</td><td>-"+val.yiliaoSelf+"</td><td>-"+val.shiyeSelf+"</td><td>-"+val.houseFundSelf+"</td><td>-"+val.payMoney+"</td><td>+"+val.overtimePay+"</td><td>-"+val.vacateDeduct+"</td><td>"+val.finallyPay+"</td></tr>")
			});
		});
	});
	});
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
	 </script>
</html>