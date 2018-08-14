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
<title>组织结构</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="view/organization/organization-list.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="layui-anim layui-anim-up">
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>个人中心</a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="row">
			<!-- 页面右侧（详细信息）部分 -->
			<div class="col-md-1">
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">详细信息</h3>
						
					</div>
					<div class="panel-body fullhight">
						<!-- 这里放置右侧内容主体 -->
						<span class="message-title">基本信息</span>
		
						<hr />
						<table>
							<tr>
								<th width="100px">登录名</th>
								<td width="270px;">${myAccount.accountLoginId }</td>
								<th width="100px">入职时间</th>
								<td width="270px;">${myAccount.accountEntryDate }</td>
							</tr>
							<tr>
								<th>姓名</th>
								<td>${myAccount.accountName }</td>
								<th>学历</th>
								<td>${myAccount.accountEduLevel }</td>
							</tr>
							<tr>
								<th>工号</th>
								<td>${myAccount.accountNum }</td>
								<th>状态</th>
								<td>${myAccount.accountStatus }</td>
							</tr>
							<tr>
								<th>性别</th>
								<td>${myAccount.accountSex }</td>
								<th>身份证号码</th>
								<td>${myAccount.accountIdcard }</td>
							</tr>
							<tr>
								<th>所在部门</th>
								<td>${roleName }</td>
							</tr>
							<tr>
								<td>职务</td>
								<td>${sectionName }</td>
							</tr>
						</table>
						<br /> <span class="message-title">联系方式</span>
						<hr />
						<table>
						<tr>
								<th width="100px">电话</th>
								<td width="300px;">${myAccount.accountPhone }</td>
							</tr>
							<tr>
								<th>地址</th>
								<td>${myAccount.accountLocation }</td>
							</tr>
						</table>
						<!-- end 右侧内容 -->
						<hr/>
						 <button  class="layui-btn" onclick="x_admin_show('修改密码','accountController/selectUpdateAccount.do',500,500)"> 修改密码 </button>
						 <button  class="layui-btn" onclick="x_admin_show('修改个人信息','accountController/selectCenterAccount.do',500,500)"> 修改个人信息</button>
					</div>
				</div>
			</div>
			<!-- end 右侧 -->
			
			<div class="col-md-1">
			</div>
		</div>
	</div>
</body>
</html>