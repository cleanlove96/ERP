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
<title>添加配方</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- <meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" /> -->
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<script type="text/javascript"	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

	
<script type="text/javascript"
	src="js/jquery.js"></script>

<script type="text/javascript" src="lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript" src="lib/layui/lay/modules/form.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/formula.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body">
		<form class="layui-form" id="form" onsubmit="return add()">
			<div class="layui-form-item">
				<label for="" class="layui-form-label"> <span
					class="x-red">*</span>生产商品
				</label>
				<div class="layui-input-inline">
					<select id="commodityId" name="commodityId" class="valid">
	                   <c:forEach items="${commodityList}" var="commodity">
	                   		<option value="${commodity.commodityId}">${commodity.commodityName}</option>
	                   </c:forEach>
                  	</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="" class="layui-form-label"> <span
					class="x-red">*</span>所需配料
				</label>
				<div class="layui-input-inline">
					<select  id="materialId" name="materialId" class="valid">
	                   <c:forEach items="${materialList}" var="material">
	                   		<option value="${material.materialId}">${material.materialName}</option>
	                   </c:forEach>
                  	</select>
				</div>
				
			</div>
			<div class="layui-form-item">
				<label for="" class="layui-form-label"> <span
					class="x-red"></span>所需配料数量
				</label>
				<div class="layui-input-inline">
					<input type="number" id="formulaCount" autocomplete="off" name="formulaCount"  lay-verify="required"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">描述数字</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				 <button  class="layui-btn" lay-filter="*" lay-submit="" >增加</button> 
				<!-- <button class="layui-btn"  type="button" lay-filter="add" onclick="addmember()" lay-submit="">
					增加</button> -->
			</div>
		</form>
	</div>
	<script>
		$(function(){
			verify();
		});
		
		function add() {
			var data=$("#form").serialize();
			alert(data);
			$.ajax({
				url : 'formulaController/add.do',
				data : data,
				type : 'POST',
				dataType : 'text',
				async:false,
				success : function(result) {
					if(result=="SUCCESS"){
						 layer.alert("增加成功", {
							icon : 6
						}, function() {
							//提交信息……
							// 获得frame索引
							var index = parent.layer.getFrameIndex(window.name);
							//关闭当前frame
							parent.layer.close(index);
							//parent.location.reload(); 
							parent.suaxin();
						}); 
					}else {
						if(result=="NAME_ERROR"){
							//$("#whn").html("名称重复，请修改");
							layer.alert('名称重复，请修改', {
								icon: 5,
								title: "提示"
								});
						}
						
					} 
				},
				error : function(data) {
					layer.alert("添加失败", {
						icon : 6
					});
				}
			});
			return false;
		}
	</script>
</body>

</html>