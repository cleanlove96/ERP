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
<link rel="stylesheet" href="css/wSelect.css">
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
			<c:forEach items="${listPojo }" var="list">
				<div class="layui-form-item">
					<label for="L_id" class="layui-form-label"> <span
						class="x-red">*</span>原料名称
					</label>
					<div class="layui-input-inline">
						<input type="text" name="materialName"
							value="${list.materialName}" autocomplete="off"
							class="layui-input"  readonly>
					</div>
					<div class="layui-form-mid layui-word-aux">6到20个字符</div>
				</div>

				<div class="layui-form-item">
					<label for="L_id" class="layui-form-label"> <span
						class="x-red">*</span>原料计划出库数量
					</label>
					<div class="layui-input-inline">
						<input type="text" name="num0" value="" autocomplete="off"
							class="layui-input" id="${list.materialId}" readonly>
					</div>
					<div class="layui-form-mid layui-word-aux">6到20个字符</div>
				</div>

				<div class="layui-form-item">
					<label for="L_id" class="layui-form-label"> <span
						class="x-red">*</span>计划原料总金额
					</label>
					<div class="layui-input-inline">
						<input type="text" name="money" value="" autocomplete="off"
							class="layui-input" id="${list.formulaCount}" readonly>
					</div>
					<div class="layui-form-mid layui-word-aux">6到20个字符</div>
				</div>

				<div class="layui-form-item">
					<label for="L_id" class="layui-form-label"> <span
						class="x-red">*</span>原料实际出库数量
					</label>
					<div class="layui-input-inline">
						<input type="text" name="num"  class="layui-input" onchange="mon()" id="${list.materialId}s" >
					</div>
					<div class="layui-form-mid layui-word-aux">6到20个字符</div>
				</div>

				<div class="layui-form-item">
					<label for="L_id" class="layui-form-label"> <span
						class="x-red">*</span>实际原料总金额
					</label>
					<div class="layui-input-inline">
						<input type="text" name="money" value="" autocomplete="off"
							class="layui-input" id="${list.formulaCount}s">
					</div>
					<div class="layui-form-mid layui-word-aux">6到20个字符</div>
				</div>
			</c:forEach>
			<div class="layui-form-item">
				<label for="L_education" class="layui-form-label">出库仓库 </label>
				<div class="layui-input-inline content-box">
					<select id="L_education" tabindex="1">
						<c:forEach items="${wh}" var="wh">
							<option value="${wh.warehouseId}" name="warehouseName">${wh.warehouseName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="button" onclick="addmember()">
					修改</button>
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
			<c:forEach items="${listPojo }" var="list">
			var mater = '${list.materialId}s';
			 var mun =   $("#"+'${list.materialId}s').val();
			 var m   = $("#"+'${list.materialId}').val();
			
			var sectionName = $("#sn").val();
			if (sectionName == null || sectionName == "" ||mun>m) {
				layer.msg("不能为空或实际数量不能小于计划数量", {
					icon : 5,
					anim : 6
				});
				$("#"+mater).attr("style", "border:1px solid red");
				return false;
			}
			</c:forEach>
			var sectionId = "${sf.sectionId}";
			var sectionName = $("#sn").val();
			var sectionImg = $("#L_education").val();
			//提交信息……
			$.ajax({
				url : 'sectionInfoController/update.ajax',
				type : 'POST',
				data : {
					sectionId : sectionId,
					sectionName : sectionName,
					sectionImg : sectionImg
				},
				dataType : 'text',
				success : function(res) {
					location.reload();
					switch (res) {
					case "SUCCESS":
						layer.msg("修改成功", {
							icon : 6
						});
						break;
					case "ERROR":
						layer.msg("修改名称重复请检查!", {
							icon : 5
						});
						break;
					}
				},
				error : function() {

				}
			});
			parent.location.reload();
			// 获得frame索引
			var index = parent.layer.getFrameIndex(window.name);
			//关闭当前frame
			parent.layer.close(index);

		}
	</script>
	<script type="text/javascript">
	
	$(function() {
		<c:forEach items="${listPojo }" var="list">
		var mate = "${list.materialId}";
		var formula = "${list.formulaCount}";
		var unit = ${list.ropUnit};
		var price = ${list.price};
		  $("#"+mate).val(formula*unit);
		  
		  $("#"+formula).val(formula*unit*price);
		</c:forEach>
	});

	function mon(){
		<c:forEach items="${listPojo }" var="list">
		$("#"+'${list.formulaCount}s').val($("#"+'${list.materialId}s').val()*${list.price});
		</c:forEach>
	};
	</script>
</body>

</html>