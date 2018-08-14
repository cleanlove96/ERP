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
		<form class="layui-form ">
			<div class="layui-form-item">
				<label for="L_id" class="layui-form-label"> <span
					class="x-red">*</span>角色名称
				</label>
				<div class="layui-input-inline">
					<input type="text" autocomplete="off" class="layui-input" id="rn"
						lay-verify="required">
				</div>
				<div class="layui-form-mid layui-word-aux">6到20个字符</div>
			</div>
			<div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>角色描述
				</label>
				<div class="layui-input-inline">
					<input type="text" autocomplete="off" class="layui-input" id="rd"
						lay-verify="required">
				</div>
				<div class="layui-form-mid layui-word-aux">6到16个字符</div>
			</div>
			<div class="layui-form-item">
				<label for="L_education" class="layui-form-label">所属部门</label>
				<div class="layui-input-inline">
					<select id="L_education">
					<c:forEach items="${sf}" var="section">
						<option value="${section.sectionId}">${section.sectionName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="button" lay-filter="add"
					lay-submit="">增加</button>
			</div>
		</form>
	</div>
	<script>
		layui.use([ 'form', 'layer' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;
			form.on('submit(add)', function(data) {
				var roleName = $("#rn").val();
				var roleDesc = $("#rd").val();
				var sectionId = $("#L_education").val();
				//提交信息……
				$.ajax({
					url : 'roleController/insetinfo.ajax',
					type : 'POST',
					data : {
						roleName : roleName,
						roleDesc : roleDesc,
						sectionId : sectionId
					},
					dataType : 'text',
					success : function(res) {
						switch (res) {
						case "SUCCESS":
							layer.msg("增加成功", {icon : 6});
							break;
						case "ERROR":
							layer.msg("错误：角色添加重复，请检查!", {icon : 5,anim: 6});
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

			});
		});
	</script>
</body>

</html>