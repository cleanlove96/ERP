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
	<div align="center">
		<div class="x-body">
			<form class="layui-form">
				<div class="layui-form-item">
					<label for="L_name" class="layui-form-label"> <span
						class="x-red">*</span>费用类型
					</label>
					<div class="layui-input-inline">
						<select id="costType" name="costType">
							<option value="交通">交通</option>
							<option value="餐饮">餐饮</option>
							<option value="会议">会议</option>
							<option value="出差">出差</option>
							<option value="话费">话费</option>
							<option value="住宿">住宿</option>
							<option value="办公">办公</option>
							<option value="其他">其他</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_name" class="layui-form-label"> <span
						class="x-red">*</span>费用项目
					</label>
					<div class="layui-input-inline">
						<input type="text" id="costItem" autocomplete="off"
							class="layui-input" name="costItem" lay-verify="requireds">
					</div>
					<div class="layui-form-mid layui-word-aux"></div>
				</div>
				<div class="layui-form-item">
					<label for="L_name" class="layui-form-label"> <span
						class="x-red">*</span> 费用说明
					</label>
					<div class="layui-input-inline">
						<textarea lay-verify="requireds" id="reimbursementRespon"
							autocomplete="off" name="reimbursementRespon" class="layui-textarea"
							rows="7" cols="60" value=""></textarea>
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span name="spanz" hidden=""></span>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_name" class="layui-form-label"> <span
						class="x-red">*</span>费用金额
					</label>
					<div class="layui-input-inline">
						<input type="text" id="costTotal" autocomplete="off"
							class="layui-input" name="costTotal" lay-verify="requireds">
					</div>
					<div class="layui-form-mid layui-word-aux"></div>
				</div>
				<div class="layui-form-item">
					<label for="L_repass" class="layui-form-label"> </label>
					<button class="layui-btn" type="button" lay-filter="edit"
						lay-submit="">提交</button>
				</div>
			</form>
		</div>
		
		
		<script>
			//框架原有的
			//加载时间的方法
			layui.use('laydate', function() {
				var laydate = layui.laydate;
				//执行一个laydate实例
				laydate.render({
					elem : '#accountEntryDate' //指定元素
				});
			});
			
			//执行增加操作
			layui.use([ 'form', 'layer' ], function() {
				$ = layui.jquery;
				var form = layui.form, layer = layui.layer;
				form.verify({
					requireds : function(value) {
						if (value == null || value == "") {
							return "有值未输入，请全部输入再提交";
						}
					}

				});

				//监听提交
				form.on('submit(edit)', function(data) {
					//
					var accounts = $(".layui-form").serialize();
					console.log(".....accounts....." + accounts);
					var costItem = $("#costItem").val();
					var costType = $("#costType").val();
					var costTotal = $("#costTotal").val();
					var reimbursementRespon = $("#reimbursementRespon").val();
					$.ajax({
						url : 'approvalController/writeReimbursement.ajax',
						type : 'post',
						data : {
							costItem : costItem,
							costType : costType,
							costTotal : costTotal,
							reimbursementRespon : reimbursementRespon
						},
						dataType : 'text',
						success : function(res) {
							if (res == "SUCCESS") {
								layer.alert("提交成功", {

									icon : 6
								//icon : 6定义弹框样式
								});
							}

						},
						error : function() {
							layer.alert("添加失败", {
								icon : 6
							});
						},

					});

					return false;
				});

			})
		</script>
	</div>
</body>

</html>