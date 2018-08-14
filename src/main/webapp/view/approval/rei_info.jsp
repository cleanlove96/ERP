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
<title>添加员工</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body">
		<table class="layui-table" id="account" >
		
		<!-- 用于取值 -->
		<input hidden="" id="quzhi" value="${reimbursement.reimbursementId }">
		</table>
	</div>
	<script>
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
	$(function() {
		 var reimbursementId =$("#quzhi").val();
		 
		$.ajax({
			url : 'approvalController/chakan.ajax',
			data : {
				reimbursementId : reimbursementId
			},
			type : 'post',
			dateType : 'text',
			success : function(result){
				var res = JSON.parse(result);
				$("#account").html("");
				$.each(res,function(n,val){
					var times = new Date(val.costCreateTime);
					$("#account").append(
							"<tr class='col-md-8'><td>报销人</td><td>"+val.accountName+"</td></tr>"+
							"<tr class='col-md-8'><td>费用项目</td><td>"+val.costItem+"</td></tr>"+
							"<tr class='col-md-8'><td>费用说明</td><td>"+val.reimbursementRespon+"</td></tr>"+
							"<tr class='col-md-8'><td>费用类别</td><td>"+val.costType+"</td></tr>"+
							"<tr class='col-md-8'><td>费用金额</td><td>"+val.costTotal+"</td></tr>"+
							"<tr class='col-md-8'><td>创建时间</td><td>"+times.Format("yyyy-MM-dd HH-mm")+"</td></tr>"+
							"<tr class='col-md-8'><td>审核结果</td><td>"+val.reimbursementState+"</td></tr>"	+
							"<tr class='col-md-8'><td>审核人</td><td>"+val.accountName1+"</td></tr>"
					)
				});
				
			},
			error : function() {
				alert("失败");
			},
		});
	});
	</script>
</body>

</html>