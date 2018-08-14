<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>53°D-ERP管理系统</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>

</head>
<body>
<!-- 老师做好的动态显示权限页面，没做权限之前，先用index -->
	<!-- 顶部开始 -->
	<div class="container">
		<div class="logo">
			<a href="index.jsp">53°D-ERP管理系统</a>
		</div>
		<div class="left_open">
			<i title="展开左侧栏" class="iconfont">&#xe699;</i>
		</div>
		<!-- <ul class="layui-nav left fast-add" lay-filter="">
			<li class="layui-nav-item"><a href="javascript:;">+下拉菜单</a>
				<dl class="layui-nav-child">
					二级菜单
					<dd>
						<a onclick="x_admin_show('资讯','http://www.baidu.com')"><i
							class="iconfont">&#xe6a2;</i>模拟窗口A</a>
					</dd>
					<dd>
						<a onclick="x_admin_show('图片','http://www.baidu.com')"><i
							class="iconfont">&#xe6a8;</i>模拟窗口B</a>
					</dd>
					<dd>
						<a onclick="x_admin_show('用户','http://www.baidu.com')"><i
							class="iconfont">&#xe6b8;</i>模拟窗口C</a>
					</dd>
					<dd>
						<a onclick="x_admin_show('用户','http://www.baidu.com')"><i
							class="iconfont">&#xe6b8;</i>模拟窗口D</a>
					</dd>

				</dl></li>
		</ul> -->
		<ul class="layui-nav right">
			<li class="layui-nav-item"><a href="javascript:;"><span
					id="accountLoginId">获取中...</span></a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a onclick="x_admin_show('个人信息','accountController/selsectCenterByAccountId.do')">个人中心</a>
					</dd>
					<dd id="layerDemo">
					</dd>
					<dd>
						<a href="accountController/doLogout.do">退出登录</a>
					</dd>
				</dl></li>
			<li class="layui-nav-item to-index"><a href="indexController/showIndexPage.do">系统首页</a></li>
		</ul>

	</div>
	<!-- 顶部结束 -->
	<!-- 中部开始 -->
	<!-- 左侧菜单开始 -->
	<div class="left-nav">
		<div id="side-nav">
			<ul id="nav">
				
				<!-- 根据用户权限的数据动态生成左侧菜单栏 -->
				<c:forEach items="${leftButtons }" var="authGroupPojo">
					<li><a href="javascript:;"> <img
							src="${authGroupPojo.authGroup.authGroupIcon }"
							class="left-menu-icon"> <cite>${authGroupPojo.authGroup.authGroupName }</cite>
							<i class="iconfont nav_right">&#xe697;</i>
					</a>
						<ul class="sub-menu">
							<c:forEach items="${authGroupPojo.auths }" var="auth">
								<li><a _href="${auth.authHref }"> <i class="iconfont">&#xe6a7;</i>
										<cite>${auth.authName }</cite>
								</a></li>
							</c:forEach>
						</ul></li>
				</c:forEach>
				<!-- end 左侧菜单 -->
			</ul>
		</div>
	</div>

	<!-- 左侧菜单结束 -->
	<!-- 右侧主体开始 -->
	<div class="page-content">
		<div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
			<!-- 打开的标签页，无需手动添加 -->
			<ul class="layui-tab-title">
				<li>欢迎进入53°D-ERP管理系统</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<iframe src='indexController/showWelcome.do' frameborder="0"
						scrolling="yes" class="x-iframe"></iframe>
				</div>
			</div>
			<!-- 标签页结束 -->
		</div>
	</div>
	<div class="page-content-bg"></div>
	<!-- 右侧主体结束 -->
	<!-- 中部结束 -->
	<!-- 底部开始 -->
	<div class="footer">
		<div class="copyright">53°D有限责任公司 ©2017 前端：x-admin v2.3
			后端：华清远见成都中心A组-java EE项目</div>
	</div>
	<!-- 底部结束 -->
</body>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
	$(function(){
		$.post('ministryController/isClock.ajax',function(res){
			if(res=="1"){
				$("#layerDemo").html("<a >已打卡</a>");
			}else{
				$("#layerDemo").html("<a onclick=\"x_admin_show('考勤打卡','view/account/account-clock.jsp',300,200)\">打卡</a>");
			}
		});
	});
</script>
</html>