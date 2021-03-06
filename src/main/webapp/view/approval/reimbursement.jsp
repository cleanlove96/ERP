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
								<td onclick="doSearchState('0')" ><input hidden="" id="quzhi" value="${state}">未审核报销单</td>
							</tr>
							<tr>
								<td onclick="doSearchState('3')" >审核中报销单</td>
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
									<th>费用项目</th>
									<th>费用说明</th>
									<th>费用类别</th>
									<th>费用金额</th>
									<th>提交时间</th>
									<th>审核状态</th>
									<th id="thhhh" width="100px">操作</th>
								</tr>
								</thead>
								<tbody id="seeLeave">
								</tbody>
							</table>
							
						</form>
						<div id="kkpager" align="center"></div>
						<hr />
						<button class="layui-btn" onclick="x_admin_show('填写报销单','view/approval/write_reimbursement.jsp',600,500)">填单</button>
						<!-- end 右侧内容 -->
					</div>
					
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
	window.location.href = 'approvalController/doSearchState.do?state=' + n;
}
	
	//分页
	$(function() {
		var state =  $("#quzhi").val();
		console.log(state);
		$.ajax({
			url : 'approvalController/getPage.do',
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
			url : 'approvalController/seeReimbursement.ajax',
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
					var str;
					if(state=="1"){
						str="<td><a title='详情' onclick=\"x_admin_show('编辑','approvalController/seeReiXiangXi.do?reimbursementId="+val.reimbursementId+
						"',600,500)\" href='javascript:;'>详情</a></td></tr>";
						
					}else{
						str="<td class='td-manage'><a title='编辑' onclick=\"x_admin_show('编辑','approvalController/updateReiUI.do?reimbursementId="+val.reimbursementId+
						"',600,500)\" href='javascript:;'> <i class='layui-icon'>&#xe642;</i></a> <a title='删除' onclick=\"member_del(this,'"+val.reimbursementId+"')\" href='javascript:;'> <i class='layui-icon'>&#xe640;</i></a></td></tr>"
					}
					var times = new Date(val.costCreateTime);
					$("#seeLeave").append(
					"<tr><td width='100px' hidden='' value='"+val.reimbursementId+"'>"+val.reimbursementId+"</td>"+
					"<td>"+val.costItem+"</td>"+
					"<td>"+val.reimbursementRespon+"</td>"+
					"<td>"+val.costType+"</td>"+
					"<td>"+val.costTotal+"</td>"+
					"<td>"+times.Format("yyyy-MM-dd HH-mm")+"</td>"+
					"<td>"+val.reimbursementState+"</td>"+
					str
					);
				});
			},
			error : function() {
				alert("失败");
			},
		});	
	}
	function member_del(obj, reimbursementId) {
		layer.confirm('确认要删除吗？', function(reimbursementId1) {
			//发异步删除数据
			$.post('approvalController/delRei.ajax',{reimbursementId:reimbursementId},function(res){
				if(res=="SUCCESS"){
					$(obj).parents("tr").remove();
					layer.msg('已删除!', {
						icon : 1,
						time : 500
					});
					/*location.replace(location.href);*/
				}else{			
					layer.msg('未删除!', {
					icon : 1,
					time : 500
				});
					
				}
			})
		});
	}


	$(function() {
		$.post('authController/getAuthGroup.ajax',function(result) {
			var res = JSON.parse(result);
			$("#auth_parent").html("");
			$.each(res, function(n, val) {
				$("#auth_parent").append(
						"<option  value='"+val.authGroupId+"'>"+ val.authGroupName +"</option>");
			});
		});

		/*
		$.ajax({
			url : 'authController/getAuth.ajax',
			data : {

			},
			type : 'post',
			dataType : 'text',
			success : function(result) {
				var jtable = eval("(" + result + ")");
				$("#auth_list").html("");
				$.each(jtable, function(n, val) {
					$("#auth_list").append(
							"<tr><td onclick=\"seeInfo('" + val.authId
									+ "')\">" + val.authName + "</td></tr>");
				});
			},
			error : function() {

			},
		});
		*/
		var id = $("#auth_id").val();
		if (id == "") {
			$("#change").hide();
			$("#delete").hide();
			$("#create").show();
		}
	});
	
	
	
	function seeInfo(id) {
		$.post('authController/getAuthGroup.ajax', function(result) {
			var res = JSON.parse(result);
			$("#auth_parent").html("");
			$.each(res, function(n, val) {
				$("#auth_parent").append(
						"<option value='"+val.authGroupId+"' >"+ val.authGroupName + "</option>");
			});
			$.post('authController/getAuthInfo.ajax', {
				authId : id
			}, function(result) {
				var res = JSON.parse(result);
				console.log(id);
				console.log(res);
				$("#auth_id").attr("value", res.authId);
				$("#auth_name").attr("value", res.authName);
				$(".message-title").text(res.authName);
				$("#auth_href").attr("value", res.authHref);
				$("option[value='" + res.authGroupId + "']").attr("selected",true);
			});
			$("#change").show();
			$("#delete").show();
			$("#create").hide();
		});
	};
	$("#change").click(function() {
		var form = $("#fm").serialize();
		$.post('authController/authChange.ajax', form, function(res) {
			console.log(res);
			if (res == "SUCCESS") {
				layer.msg('已修改!', {
					icon : 1,
					time : 500
				});
			} else {
				layer.msg('未修改!', {
					icon : 1,
					time : 500
				});
			}
		});
	});
	
	
	
	$("#delete").click(function() {
		var id = $("#auth_id").val();
		$.post('authController/authDelete.ajax', {
			id : id
		}, function(res) {
			if (res == "SUCCESS") {
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
			} else {
				layer.msg('未删除!', {
					icon : 1,
					time : 500
				});
			}

		});
	});
	 $("#create").click(function() {
	 var form = $("#fm").serialize();
	 var name = $("#auth_name").val();
	 var href = $("#auth_href").val();
	 if(name==null||name==""){
	 		layer.msg('名字不能为空!', {
	 			icon : 0,
	 			time : 500
	 	});
	 		return false;
	 }
	 if(href==null||href==""){
	 		layer.msg('连接地址不能为空!', {
	 			icon : 0,
	 			time : 500
	 	});
	 		return false;
	 }
	 $.post('authController/authAdd.ajax', form, function(res) {
	 	if (res == "SUCCESS") {
	 		layer.msg('已添加!', {
	 			icon : 1,
	 			time : 500
	 	});
	 } else {
	 	layer.msg('未添加!', {
		 icon : 1,
	 	time : 500
		 });
	 }
	});
}); 
</script>
</html>			