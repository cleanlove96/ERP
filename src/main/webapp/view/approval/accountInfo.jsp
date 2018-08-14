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
		<input hidden="" id="quzhi" value="${accountId }">
		</table>
	</div>
	<script>
	$(function() {
		 var accountId =$("#quzhi").val();
		 console.log(accountId);
		$.ajax({
			url : 'approvalController/seeLeaveAccountInfo.ajax',
			data : {
				accountId : accountId
			},
			type : 'post',
			dateType : 'text',
			success : function(result){
				var res = JSON.parse(result);
				$("#account").html("");
				$.each(res,function(n,val){
					$("#account").append(
							"<tr class='col-md-8'><td>姓名</td><td>"+val.accountName+"</td></tr>"+
							"<tr class='col-md-8'><td>工号</td><td>"+val.accountNum+"</td></tr>"+
							"<tr class='col-md-8'><td>所属部门</td><td>"+val.sectionName+"</td></tr>"+
							"<tr class='col-md-8'><td>性别</td><td>"+val.accountSex+"</td></tr>"+
							"<tr class='col-md-8'><td>居住地</td><td>"+val.accountLocation+"</td></tr>"+
							"<tr class='col-md-8'><td>电话号码</td><td>"+val.accountPhone+"</td></tr>"+
							"<tr class='col-md-8'><td>入职时间</td><td>"+val.accountEntryDate+"</td></tr>"						
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