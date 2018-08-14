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
						<span class="message-title">所需物料信息</span>
		
						<hr />
						<table  id="myPurchase">
							<tr>
								<th width="150px">原料</th>
								<th width="150px">数量</th>
								<th width="150px">单位</th>
								<th width="150px">单价</th>
								<th width="150px">总金额（元）</th>
								<th width="150px">未计划量</th>
							</tr>
							
						</table>
						<br /> <span class="message-title">增加计划</span>
						<hr />
		<form class="layui-form">
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label">原料
				</label>
				<div class="layui-input-inline">
					<select id=materialId name="materialId"  lay-filter="select">
						<option value="">请选择原料</option>
						
					</select>
				</div>
			
				<label for="L_name" class="layui-form-label">数量
				</label>
				<div class="layui-input-inline">
					<input type="text" id="purchaseCount" autocomplete="off"
						class="layui-input" name="purchaseCount" lay-verify="requireds">
				</div>
		
				<label for="L_name" class="layui-form-label">单位
				</label>
				<div class="layui-input-inline">
					<input type="text" id="purchaseUnit" autocomplete="off"  placeholder="和上面单位一致" readonly="readonly"
						class="layui-input" name=purchaseUnit lay-verify="requireds">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label">开始时间
				</label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="开始时间" id="startTime"
						type="text" name="startTime" lay-verify="requireds" autocomplete="off">
				</div>
			
				<label for="L_name" class="layui-form-label">结束时间
				</label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="结束时间" id="endTime"
						type="text" name="endTime" lay-verify="requireds" autocomplete="off">
				</div>
			
				<label for="L_name" class="layui-form-label">单价
				</label>
				<div class="layui-input-inline">
					<input type="text" id="price" autocomplete="off"
						class="layui-input" name="price"  lay-verify="requireds" readonly="readonly">
				</div>
			
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="button" lay-filter="edit"
					lay-submit="">增加</button>
			</div>
			<input type="hidden" id="purchaseTime" name="purchaseTime" value="${searchCard }">
		</form>
		<br /> <span class="message-title">分采购计划表</span>
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
					<th>操作</th>
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
<script type="text/javascript" src="js/purchaseList.js">
function doSearchCard(){
	var s = $("#searchCard").val();

	window.location.href = 'purchaseController/doSearchCard.do?searchCard='+s;	
}


</script>

</html>