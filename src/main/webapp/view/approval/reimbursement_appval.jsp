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
<title>权限组织结构</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<link rel="stylesheet" href="css/kkpager_blue.css" />
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style>
td input {
	width: 100%;
	border: 0;
}
</style>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>流程审批</a> <a> <cite>请假申请</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="row">
			<!-- 页面左侧（树形结构）部分 -->
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">菜单栏</div>
					<div class="panel-body fullhight">
						<!-- 这里放置左侧内容主体 -->
						<table class="layui-table">
							<tbody id="auth_list">
								<!-- 数据示例 -->
								
								<!-- end 数据示例 -->
							</tbody>
							
							<tr>
								<td onclick="doSearchState('0')" ><input hidden="" id="quzhi" value="${state }">待审核报销单</td>
							</tr>
							<tr>
								<td onclick="doSearchState('1')">已审核报销单</td>
							</tr>
							<tr>
								<td onclick="doSearchState('2')">
									<span class="badge pull-right">${notRead }</span>未读消息
								</td>
							</tr>
						</table>
						<!-- end 左侧内容 -->
					</div>
				</div>
			</div>
			<!-- end 左侧 -->
			<!-- 页面右侧（详细信息）部分 -->
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">详细信息</h3>
					</div>
					<div class="panel-body fullhight">
						<!-- 这里放置右侧内容主体 -->
						<span class="message-title"></span>
						<hr />
						<form id="fm">
							<table class="layui-table" >
								<thead>
								<tr>
									<th hidden="">ID</th>
									<th width="100px">申请人</th>
									<th width="100px">费用项目</th>
									<th width="100px">费用说明</th>
									<th width="100px">费用类别</th>
									<th width="100px">费用金额</th>
									<th width="100px">提交时间</th>
									<th width="100px">审核状态</th>
									<th id="thhhh" width="100px">操作</th>
								</tr>
								</thead>
								<tbody id="seeLeave">
								</tbody>
							</table>
							
						</form>
						<div id="kkpager" align="center"></div>
						<hr />
						
						<!-- end 右侧内容 -->
					</div>
					<!-- 作为点击函数使用（用于查看用户详细信息表） -->
					<a hidden="" onclick="x_admin_show('资讯','http://www.baidu.com')" id="seeAccountInfo"></a>
				</div>
				
			</div>
			<!-- end 右侧 -->
		</div>
	</div>
	
	
	
</body>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/kkpager.min.js"></script>
<script type="text/javascript">
//时间格式
Date.prototype.Format = function (fmt) {
	   var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "H+": this.getHours(), //小时 
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
	
	
	//根具状态查询
	
function doSearchState(n) {
	var s = n;
	window.location.href = 'approvalController/doSearchStateApproval.do?state=' + n;
}
	
	//分页
	$(function() {
		var state =  $("#quzhi").val();
		console.log(state);
		$.ajax({
			url : 'approvalController/ARgetPage.do',
			type : 'POST',
			data : {
				state:state
			},
			dataType : 'text',
			success : function(res) {
				var page = eval("(" + res + ")");
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
						seeLeave(n);
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
		seeLeave(1);
	})
	
	function seeLeave(n){
		var state = $("#quzhi").val();
		$.ajax({
			url : 'approvalController/seeAllReimbursement.ajax',
			data : {
				n : n,
				state : state
			},
			type : 'post',
			dateType : 'text',
			success : function(result){
				var res = JSON.parse(result);
				$("#seeLeave").html("");
				$.each(res,function(n, val){
					var str ="";
					if(state=="1"){
						//已审批，审批状态显示审批结果
						str="<td>"+val.reimbursementState+"</td>"+
							"<td><a title='详情' onclick=\"x_admin_show('编辑','approvalController/seeReiXiangXi.do?reimbursementId="+val.reimbursementId+
						"',600,500)\" href='javascript:;'>详情</a></td></tr>";
					}else {
						//未审批，审核状态显示对应信息
						str = "<td><select onchange=\"approvalReimbursement(this.value,'"+val.reimbursementId+"','"+state+"')\">"+
						"<option value=''>请选择审核结果</option><option value='1' onchange=\"approval(1,'"+val.reimbursementId+"',"+state+")\">同意</option><option value='0' onchange=\"approval(0,'"+val.reimbursementId+"',"+state+")\">拒绝</option></select></td>"
						document.getElementById("thhhh").style.display = "none";
					};
					var times = new Date(val.costCreateTime);
					
					$("#seeLeave").append(
					"<tr><td width='100px' hidden='' value='"+val.reimbursementId+"'>"+val.reimbursementId+"</td>"+
					"<td>"+"<a onclick=\"x_admin_show('报销人详细信息','approvalController/accountId.do?bxPersonId="+val.bxPersonId+"')\">"+val.accountName+"</a></td>"+
					"<td>"+val.costItem+"</td>"+
					"<td>"+val.reimbursementRespon+"</td>"+
					"<td>"+val.costType+"</td>"+
					"<td>"+val.costTotal+"</td>"+
					"<td>"+times.Format("yyyy-MM-dd HH-mm")+"</td>"+
					str+
					"</tr>"
					
					);
				});
			},
			error : function() {
				alert("失败");
			},
		});	
	}

	//提交审核结果
	function approvalReimbursement(appro,reimbursementId,state){
		
		
		$.ajax({
			url : 'approvalController/approvalRei.ajax',
			data : {
				appro : appro,
				reimbursementId : reimbursementId
			},
			type : 'post',
			dateType : 'text',
			success : function(result){
				
			},
			error : function() {
				alert("失败");
			},
			
		});
		doSearchState(state);
		
	}

	function doAlert(bxPersonId) {
		 document.getElementById("seeAccountInfo").click();
		$.ajax({
			url : 'approvalController/seeLeaveAccountInfo.ajax',
			data : {
				accountId : bxPersonId
			},
			type : 'post',
			dateType : 'text',
			success : function(result){
				var res = JSON.parse(result);
				$.each(res,function(n,val){
					var str;
					str="<h2>员工详细信息</h2>"+
					"姓名："+val.accountName+"</br>"+
					"工号："+val.accountNum+"</br>"+
					"所属部门："+val.accountSex+"</br>"+
					"性别："+val.accountSex+"</br>"+
					"居住地："+val.accountLocation+"</br>"+
					"电话号码："+val.accountPhone+"</br>"+
					"入职时间："+val.accountEntryDate+"</br>"
					
				});
				
			},
			error : function() {
				alert("失败");
			},
		});
	}
	
	 
</script>
</html>			