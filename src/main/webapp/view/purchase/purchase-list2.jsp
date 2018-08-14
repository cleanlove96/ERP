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
<script type="text/javascript" src="view/organization/organization-list.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>采购总计划</a>
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
			<span class="message-title">计划采购量</span>
			<hr />
			<table id="myPurchaseAll">
							<tr>
								<th width="150px">原料</th>
								<th width="150px">数量</th>
								<th width="150px">单位</th>
								<th width="150px">单价</th>
								<th width="150px">总金额（元）</th>
								<th width="150px">未采购量</th>
							</tr>
						
			</table>
	
	
		<br /> <span class="message-title">采购计划表</span>
		<hr />
		<span class="x-right" style="line-height: 40px" id="count_num"></span> 
			<table class="layui-table">
			<thead>
				<div class="layui-row" style="height: 40px">
							<div class="layui-form layui-col-md12 x-so">
								<span>开始时间段:</span> <input class="layui-input" placeholder="开始"
									id="myStartTime1" value="${myStartTime1 }">
									<input class="layui-input" placeholder="结束"
									id="myStartTime2" value="${myStartTime2 }">
								<span>结束时间段:</span> <input class="layui-input" placeholder="开始"
									id="myEndTime1" value="${myEndTime1 }"><input class="layui-input" placeholder="结束"
									id="myEndTime2" value="${myEndTime2 }">
								<button class="layui-btn" onclick="doSearchCard()">
									<i class="layui-icon">&#xe615;</i>
								</button>
								<button class="layui-btn" onclick="resetAll()">
									<i class="layui-icon">&#x1006;</i>
								</button>
				</div>
				</div>
				<tr>
				
					<th>原料名</th>
					<th>数量</th>
					<th>单位</th>
					<th>单价</th>
					<th>总金额（元）</th>
					<th>开始时间</th>
					<th>结束时间</th>
				</tr>
			</thead>
			<tbody id="puechaseTable">
				
						
			</tbody>
		</table>			
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->			
						
	</div>
</div>
</div>		
</div>
</div>
</body>
<script type="text/javascript">
var searchCard;
var pn=1;
var myStartTime1;
var myStartTime2;
var myEndTime1;
var myEndTime2;
//设计时间格式
layui.use('laydate', function() {
	var laydate = layui.laydate;
	//执行一个laydate实例
	laydate.render({
		elem : '#myStartTime1' //指定元素
		
	});
});
layui.use('laydate', function() {
	var laydate = layui.laydate;
	//执行一个laydate实例
	laydate.render({
		elem : '#myEndTime1' //指定元素
	});
});
layui.use('laydate', function() {
	var laydate = layui.laydate;
	//执行一个laydate实例
	laydate.render({
		elem : '#myStartTime2' //指定元素
		
	});
});
layui.use('laydate', function() {
	var laydate = layui.laydate;
	//执行一个laydate实例
	laydate.render({
		elem : '#myEndTime2' //指定元素
	});
});
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


$(function(){
	searchCard= $("#searchCard").val();
	myStartTime1 = $("#myStartTime1").val();
	myEndTime1 = $("#myEndTime1").val();
	myStartTime2 = $("#myStartTime2").val();
	myEndTime2 = $("#myEndTime2").val();
	getpage();
	showPurchasepage(pn);
	//查询出计划采购计划表的所有信息
	selectPurchaseAll();	
	
})
function doSearchCard(){
		var s = $("#searchCard").val();
		myStartTime1 = $("#myStartTime1").val();
		myEndTime1 = $("#myEndTime1").val();
		myStartTime2 = $("#myStartTime2").val();
		myEndTime2 = $("#myEndTime2").val();
		window.location.href = 'purchaseController/doSearchCardss.do?searchCard='+s+'&myStartTime1='+myStartTime1+'&myEndTime1='+myEndTime1+'&myStartTime2='+myStartTime2+'&myEndTime2='+myEndTime2;					
	}

function resetAll(){
	$("#myStartTime1").val("");
	$("#myEndTime1").val("");
	$("#myStartTime2").val("");
	$("#myEndTime2").val("");
	doSearchCard();
}
function selectPurchaseAll() {
		$.ajax({
			url : 'purchaseController/selectPurchaseAll.ajax',
			type : 'POST',
			async:false, 
			data : {
				searchCard : searchCard
			},
			dataType : 'text',
			success : function(res) {
				var myPurchaseTable = eval("(" + res + ")");
				$.each(myPurchaseTable,function(n, val) {
					
				$("#myPurchaseAll").append("<tr><td>"+val.materialName+"</td><td>"+val.materialNum+"</td><td>"+val.materialUnit+"</td><td>"+val.materialPrice+"</td><td>"+val.materialTotalPrices+"</td><td>"+val.materialNotBuy+"</td></tr>");
				
				});
				
			},
			error : function() {

			}

		});
	}
function getpage() {
	
	$.ajax({
		url : 'purchaseController/getPage.ajax',
		type : 'POST',
		data : {
			searchCard:searchCard,
			myStartTime1:myStartTime1,
			myEndTime1:myEndTime1,
			myStartTime2:myStartTime2,
			myEndTime2:myEndTime2
		},
		dataType : 'text',
		success : function(res) {
			var page = eval("(" + res + ")");
			$("#count_num").text("共有数据:" + page.totalRecords + "条");
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
					showPurchasepage(n);
					//处理完后可以手动条用selectPage进行页码选中切换
					pn=n;
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

function showPurchasepage(n) {
	$.ajax({
	 	url : 'purchaseController/getPurchaseTable.ajax',
		type : 'POST',
		data : {
		n:n,
		searchCard:searchCard,
		myStartTime1:myStartTime1,
		myEndTime1:myEndTime1,
		myStartTime2:myStartTime2,
		myEndTime2:myEndTime2
		},
		dataType : 'text',
		success : function(res) {
			var purchasess = eval("(" + res + ")");
			
			$("#puechaseTable").html("");
			$.each(purchasess,function(n, val) {
				var naem=new Date(val.startTime);
				var naemm=new Date(val.endTime);
			$("#puechaseTable").append("<tr><td>"+val.materialId+"</td><td>"+val.purchaseCount+"</td><td>"+val.purchaseUnit+"</td><td>"+val.price+"</td><td>"+val.purchaseTotalPrices+"</td><td>"+naem.Format("yyyy-MM-dd")+"</td><td>"+naemm.Format("yyyy-MM-dd")+"</td></tr>");
					});	
				},
		error : function() {

				}
	});

}
	
</script>
</html>