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
<title>编辑商品总库存</title>
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
			<div class="layui-form-item">
				<label for="L_id" class="layui-form-label"> <span
					class="x-red">*</span>仓库名称
				</label>
				<div class="layui-input-inline">
					<div class="layui-input-inline content-box">
						<select id="select">
                        <c:forEach items="${listw}" var="wareHouse">
                        <option value="${wareHouse.warehouseId}" 
							id="${wareHouse.warehouseId}">${wareHouse.warehouseName}</option>
                        </c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_education" class="layui-form-label">存储商品名称 </label>
				<div class="layui-input-inline">
					<input type="text" id="L_name" name="customerName" required
						lay-verify="name" autocomplete="off"
						value="${gp.commodityName }" class="layui-input" readonly>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_education" class="layui-form-label">总数/瓶 </label>
				<div class="layui-input-inline">
					<input type="text" id="gcountAmount" name="customerName" required
						lay-verify="name" autocomplete="off"
						value="${gp.gcountAmount }" class="layui-input" onchange="money()">
				</div>
			</div>
						<div class="layui-form-item">
				<label for="L_education" class="layui-form-label">金额/元 </label>
				<div class="layui-input-inline">
					<input type="text" id="gcountMoney" name="customerName" required
						lay-verify="name" autocomplete="off"
						value="" class="layui-input" readonly>
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
			var gcountAmount = $("#gcountAmount").val();
			if (gcountAmount == null || gcountAmount == "") {
				layer.msg("不能为空", {
					icon : 5,
					anim : 6
				});
				$("#gcountAmount").attr("style", "border:1px solid red");
				return false;
			}
			
			layer.alert("修改成功", {
				icon : 6
			}, function() {
				var gcountId = "${gp.gcountId}";
				var commodityId = "${gp.commodityId}";
				var warehouseId = $("#select").val();
				var gcountMoney = $("#gcountMoney").val();
				//提交信息……
				$.ajax({
					url : 'gcountController/update.ajax',
					type : 'POST',
					data : {
						gcountId : gcountId,
						commodityId : commodityId,
						warehouseId : warehouseId,
						gcountAmount : gcountAmount,
						gcountMoney : gcountMoney
					},
					dataType : 'text',
					success : function(res) {

						},
					error : function() {

					}
				});
				parent.location.reload();
				// 获得frame索引
				var index = parent.layer.getFrameIndex(window.name);
				//关闭当前frame
				parent.layer.close(index);
			});


		}
	</script>
	<script type="text/javascript">
		$("#${gp.warehouseId}").attr("selected", "selected");
		
			function money(){
			$("#gcountMoney").val($("#gcountAmount").val()* ${Price});
			}
			
			$(function(){
				$("#gcountMoney").val($("#gcountAmount").val()*${Price});
			});
	</script>
</body>

</html>