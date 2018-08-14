<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>添加用户</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body">
		<form class="layui-form">
		<input type="hidden" name="customerId" value="${customer.customerId }">
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> 姓名</label>
				<div class="layui-input-inline">
					<input type="text" id="L_name" name="customerName" required
						lay-verify="name" autocomplete="off"
						value="${customer.customerName }" class="layui-input">
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="customerSex" value="男" title="男">
						<input type="radio" name="customerSex" value="女" title="女">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_company" class="layui-form-label"> 公司 </label>
				<div class="layui-input-inline">
					<input type="text" id="L_company" name="customerCompany" required
						lay-verify="required" autocomplete="off"
						value="${customer.customerCompany }" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_tel" class="layui-form-label"> 电话 </label>
				<div class="layui-input-inline">
					<input type="text" id="L_tel" name="customerTel" autocomplete="off"
						value="${customer.customerTel }" class="layui-input" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_qq" class="layui-form-label"> QQ </label>
				<div class="layui-input-inline">
					<input type="text" id="L_qq" name="customerQq" autocomplete="off"
						value="${customer.customerQq }" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> 邮箱 </label>
				<div class="layui-input-inline">
					<input type="text" id="L_email" name="customerEmail"
						autocomplete="off" value="${customer.customerEmail }"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_addr" class="layui-form-label"> 地址 </label>
				<div class="layui-input-inline">
					<input type="text" id="L_addr" name="customerAddr"
						autocomplete="off" value="${customer.customerAddr }" lay-verify="required"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_demand" class="layui-form-label"> 备注 </label>
					<div class="layui-input-inline">
						<input type="radio" name="customerDemand" value="买家"  title="买家">
						<input type="radio" name="customerDemand" value="卖家" title="卖家">
					</div>
			</div>
			<div class="layui-form-item">
				<label for="L_sign" class="layui-form-label"> </label>
				<button class="layui-btn"  lay-filter="save"
					lay-submit>保存</button>
			</div>
		</form>
	</div>
	<script>
		layui.use([ 'form', 'layer' ], function() {
            $ = layui.jquery;
            var form = layui.form
            ,layer = layui.layer;
			//监听提交
			form.on('submit(save)', function(data) {
				$.post('customerController/updateCustomer.ajax',data.field,function(res){
					if(res=="SUCCESS"){
					//res就是返回的结果
					layer.alert("修改成功", {icon : 6}, function() {
						// 获得frame索引
						var index = parent.layer.getFrameIndex(window.name);
						//关闭当前frame
						parent.layer.close(index);
						parent.location.reload();
					});
					}else{
						layer.alert("修改失败", {icon : 7}, function() {
							// 获得frame索引
							var index = parent.layer.getFrameIndex(window.name);
							//关闭当前frame
							parent.layer.close(index);
							parent.location.reload();
						});
					}
					});
						return false;
		});
		});
	</script>
	<script>
		$(function() {
			$("input[value='${customer.customerSex}']").attr("checked",
					"checked");
			$("input[value='${customer.customerDemand}']").attr("checked",
					"checked");
		});
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
</body>
</html>