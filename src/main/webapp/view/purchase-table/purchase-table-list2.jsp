<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>采购总计划管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_blue.css" />
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/kkpager.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript"
	src="view/organization/organization-list.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>采购单</a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="row">
			<!-- 页面右侧（详细信息）部分 -->

			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="layui-row" style="height: 40px">
							<div class="layui-form layui-col-md12 x-so">
								<span>年份：</span> <input class="layui-input" placeholder="请输入精确的年份(xxxx)"
									id="searchCard" value="${searchCard }">
								<button class="layui-btn" onclick="doSearchCard()">
									<i class="layui-icon">&#xe615;</i>
								</button>							
							</div>
						</div>

					</div>

					<div class="panel-body fullhight">
						<!-- 这里放置右侧内容主体 -->
						<form class="layui-form">

							<div class="layui-form-item">
							<label for="L_name" class="layui-form-label">采购编号 </label>
								<div class="layui-input-inline">
									<select id="extendId" name="extendId" lay-filter="extendTable">
										<option value="">请选择编号</option>

									</select>
								</div>
								
								<label for="L_name" class="layui-form-label">客户 </label>
								<div class="layui-input-inline">
									<input type="text" id="customerId" autocomplete="off"
										placeholder="xxxx" class="layui-input" name="customerId"
										lay-verify="requireds">
								</div>
								
								<!-- <label for="L_name" class="layui-form-label">年份 </label>
								<div class="layui-input-inline">
									<input type="text" id="purchase" autocomplete="off"
										placeholder="xxxx" class="layui-input" name=purchase
										lay-verify="requireds">
								</div> -->
								
								<label for="L_name" class="layui-form-label">经办人 </label>
								<div class="layui-input-inline">
									<input type="text" id="extendName" autocomplete="off" readonly="readonly"
										class="layui-input" name="extendName" placeholder="经办人姓名"
										lay-verify="requireds">
								</div>
								</div>
								<div class="layui-form-item">				
								<label for="L_name" class="layui-form-label">联系方式 </label>
								<div class="layui-input-inline">
									<input type="text" id="extendPhone" autocomplete="off" readonly="readonly"
										class="layui-input" name="extendPhone" placeholder="经办人联系方式"
										lay-verify="requireds">
								</div>
								
							</div>
							<table class="layui-table">
								<thead>
									<tr>
										<th>采购编号</th>
										<th>原料名</th>
										<th>数量</th>
										<th>单价</th>
										<th>总金额（元）</th>
										<th>采购时间</th>
										<th>备注</th>
										<th>操作</th>									
									</tr>
								</thead>
								<tbody id="puechaseTable">
									
								</tbody>
							</table>

							</br>
							</br>
							<div class="layui-form-item">
								<label for="L_name" class="layui-form-label">总金额</label>
								<div class="layui-input-inline">
									<input type="text" id="extendPrices" autocomplete="off" readonly="readonly"
										class="layui-input" name="extendPrices" placeholder="采购表实付总金额"
										lay-verify="requireds">
								</div>
								<label for="L_name" class="layui-form-label">制表人</label>
								<div class="layui-input-inline">
									<input type="text" id="accountName" autocomplete="off" readonly="readonly"
										class="layui-input" name="accountName" placeholder="制表人"
										lay-verify="requireds">
								</div>
								<label for="L_name" class="layui-form-label">制表时间 </label>
								<div class="layui-input-inline">
									<input type="text" id="extendTime" autocomplete="off" readonly="readonly"
										class="layui-input" name="extendTime" placeholder="制表时间"
										lay-verify="requireds">
								</div>								
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var searchCard;
var myextendId;
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt))
fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o){
    if (new RegExp("(" + k + ")").test(fmt)) {
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
}
    }
    return fmt;
} 
function doSearchCard(){
	var s = $("#searchCard").val();	
	window.location.href ='PurchaseTableController/doSearchCard.do?searchCard=' +s;	
}

$(function(){
	searchCard= $("#searchCard").val();
	selectAllPurchaseId();
	
})

//查询所有采购编号和客户信息
function selectAllPurchaseId(){

	$.ajax({
		url : 'PurchaseTableController/selectAllPurchaseId.ajax',
		type : 'POST',
		async:false,		
		data : {
			searchCard:searchCard
		},
		dataType : 'text',
		success : function(res) {
			var purchaseEx = eval("(" + res + ")");	
			$.each(purchaseEx,function(n, val) {		
				$("#extendId").append("<option value='"+val.extendId+"'>"+val.extendId+"</option>");
				
			});	
		},
		error : function() {

		}
	});
}
/*function selectCustomerAll(){
	$.ajax({
		url : 'PurchaseTableController/selectCustomerAll.ajax',
		type : 'POST',
		async:false, 
		data : {
			
		},
		dataType : 'text',
		success : function(res) {
			var myPurchaseTable = eval("(" + res + ")");
			$.each(myPurchaseTable,function(n, val) {		
			$("#customerId").append("<option value='"+val.customerId+"'>"+val.customerCompany+"</option>");
			});	
		},
		error : function() {

		}
	});
}*/

layui.use([ 'form', 'layer' ], function() {
	$ = layui.jquery;
	var form = layui.form,
	layer = layui.layer;
	form.on('select(extendTable)',function(data){
		
		myextendId=$("#extendId").val();
		$.ajax({
			url : 'PurchaseTableController/selectExtendAllById.ajax',
			type : 'POST',
			async:false, 
			data : {
				myextendId : myextendId
			},
			dataType : 'text',
			success : function(res) {
				var ssss = eval("(" + res + ")");
				var naem=new Date(ssss.extendTime);
				$("#customerId").val(ssss.customerId);
				$("#extendName").val(ssss.extendName);
				$("#extendPrices").val(ssss.extendPrices);
				$("#extendTime").val(naem.Format("yyyy-MM-dd"));
				$("#extendPhone").val(ssss.extendPhone);
								
			},
			
			error : function() {
				alert("未获得数据");
			}

		});
		selectOther();
		member_del();
	});
	});

		function selectOther(){
			myextendId=$("#extendId").val();
		$.ajax({
			url : 'PurchaseTableController/selectTableAllById.ajax',
			type : 'POST',
			async:false, 
			data : {
				myextendId : myextendId
			},
			dataType : 'text',
			success : function(res) {
				var ssss = eval("(" + res + ")");
				$.each(ssss,function(n, val) {
				$("#puechaseTable").append("<tr><td>"+myextendId+"</td><td>"+val.materialId+"</td><td>"+val.purchaseTableInt+"</td><td>"+val.purchaseTablePrice+"</td><td>"+val.purchaseTableMoney+"</td><td>"+val.purchaseTableTime+"</td><td>"+val.remarks+"</td><td><button class='layui-btn' onclick='del(this)' id='remove' lay-filter='remove' type='button'><i class='layui-icon'>&#x1006;</i></button></td></tr>");
						});	
			},
			error : function() {
				alert("未获得数据");
			}

		});
		}
		//删除行
	    function del(obj){
			var row = $(obj).parent().parent().remove();
		}
		
//查看填表人
			function member_del() {
				myextendId=$("#extendId").val();
				$.ajax({
					url : 'PurchaseTableController/selectAccountName.ajax',
					type : 'POST',
					
					data : {
						id:myextendId
					},
					dataType : 'text',
					success : function(res) {
						
						$("#accountName").val(res);
					},
					error : function() {
						
					}										
			});
	
		}

</script>

</html>