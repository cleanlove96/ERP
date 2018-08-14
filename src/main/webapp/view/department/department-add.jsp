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
<title>添加用户</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="stylesheet" href="css/wSelect.css">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>

<script type="text/javascript" src="js/wSelect.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body">
		<form>
			<div class="layui-form-item">
				<label for="L_id" class="layui-form-label"> <span
					class="x-red">*</span>部门名称
				</label>
				<div class="layui-input-inline ">
					<input type="text" autocomplete="off" class="layui-input" id="sn"
						lay-verify="required">
				</div>
				<div class="layui-form-mid layui-word-aux">6到20个字符</div>
			</div>
			<img alt="" src="">
			<div class="layui-form-item">
				<label for="L_education" class="layui-form-label">部门图标 </label>
				<div class="layui-input-inline content-box">
					<select id="L_education" tabindex="1">
						<option value="images/company.png" data-icon="images/company.png">&nbsp;</option>
						<option value="images/department.png"
							data-icon="images/department.png">&nbsp;</option>
						<option value="images/staff.png" data-icon="images/staff.png">&nbsp;</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="button" onclick="addmember()">
					增加</button>
			</div>
		</form>
	</div>
	<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#L_entry' //指定元素
			});
		});
		function addmember() {
			//提交信息……
			var sectionName = $("#sn").val();
			if (sectionName == null || sectionName == "") {
				layer.msg("不能为空", {
					icon : 5,
					anim : 6
				});
				$("#sn").attr("style", "border:1px solid red");
				return false;
			}
			var sectionImg = $("#L_education").val();
			//提交信息……
			$.ajax({
				url : 'sectionInfoController/insetinfo.ajax',
				type : 'POST',
				data : {
					sectionName : sectionName,
					sectionImg : sectionImg
				},
				dataType : 'text',
				success : function(res) {
					switch (res) {
					case "SUCCESS":
						layer.msg("增加成功", {
							icon : 6
						});
						break;
					case "ERROR":
						layer.msg("错误：部门添加重复，请检查!", {
							icon : 5,
							anim : 6
						});
						break;
					}
					parent.location.reload();
					// 获得frame索引
					var index = parent.layer.getFrameIndex(window.name);
					//关闭当前frame
					parent.layer.close(index);
				},
				error : function() {

				}
			});
			// 获得frame索引
			var index = parent.layer.getFrameIndex(window.name);
			//关闭当前frame
			parent.layer.close(index);
		};
	</script>



	<script type="text/javascript">
		$('select').wSelect();
	</script>
</body>

</html>