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
		<form class="layui-form" onsubmit="return deletemember()">
			<div class="layui-form-item">

				<div class="layui-input-inline">
					<input type="hidden" id="materialId" autocomplete="off"
						class="layui-input" value="${sysMaterial.materialId}"
						name="materialId">
				</div>

			</div>
			<div class="layui-form-item">
				<label for="L_id" class="layui-form-label"> <span
					class="x-red">*</span>原料品名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="materialName" autocomplete="off"
						class="layui-input" value="${sysMaterial.materialName}" name="materialName"  lay-verify="required"> <span id="failmaterialName"></span>
				</div>
				<div class="layui-form-mid layui-word-aux">不能为空</div>
			</div>
			<div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>供应商
              </label>
              <div class="layui-input-inline">
                  <select id="customerId" name="customerId" class="valid">
                   <c:forEach items="${customerList}" var="customer">
                  		<c:choose>
                   		<c:when test="${customer.customerId==sysMaterial.customerId}">
                   			<option selected="selected"  value="${customer.customerId}">${customer.customerCompany}+${customer.customerName}</option>
                   		</c:when>
                   		<c:otherwise>
                   			<option value="${customer.customerId}">${customer.customerCompany}+${customer.customerName}</option>
                   		</c:otherwise>
                   			
                   		</c:choose>
                   </c:forEach>
                  </select>
              </div>
          </div>
			<div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>单位
				</label>
				<div class="layui-input-inline">
					<input type="text" id="materialUnit" autocomplete="off"
						class="layui-input" value="${sysMaterial.materialUnit}" name="materialUnit"  lay-verify="required"> <span
						id="failmaterialUnit" ></span>
				</div>
				<div class="layui-form-mid layui-word-aux">不能为空</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> <span
					class="x-red">*</span>单价
				</label>
				<div class="layui-input-inline">
					<input type="text" id="price" autocomplete="off"
						class="layui-input" value="${sysMaterial.price}" name="price"  lay-verify="required"> <span id="failmaterialPrice"></span>
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" lay-filter="*" lay-submit="" >
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
		$(function(){
			   
			   verify();
		   })
		   function verify(){
				layui.use(['form','layer'], function(){
				      $ = layui.jquery;
				      
					   var regPos = /^\d+(\.\d+)?$/; //非负浮点数
					   var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;
				       var form = layui.form,layer = layui.layer;

				      //自定义验证规则
				      form.verify({
				    	  required: function(value,dom){
				    		  
				        	var name=dom.name;
				        	if(value.length<1){
				        		switch(name){
				        		case 'materialName':return '名称不能为空！';break;
				        		case 'materialUnit':return '单位不能为空！';break;
				        		case 'price':return '单价不能为空！';break;					        								        		
				        	}
				        		}else if(name=='price'){
				        		if(regPos.test(value) || regNeg.test(value)){
				        			
				        		}else{
				        			return '请输入数字！';	
				        		}
				        	}else{
				        		return false;
				        	}
				        	
				        	
				        }
				      });
					  });
			}
		/*  $("#materialUnit").blur(function(){
			   var pass = $(this).val();
			   if(pass==""){
				   $("#ibuttonsss").attr("disabled","true");
				   $("#failmaterialUnit").html("<font color=red>商品单位不能为空</font>");
			   }else{
				   $("#ibuttonsss").attr("disabled","false");
				   $("#failmaterialUnit").html("<font color=red>请注意单位填写是否正确</font>"); 
			   }							   
			   });
		   $("#materialName").blur(function(){
			   var pass = $(this).val();
			   if(pass==""){
				   $("#ibuttonsss").attr("disabled","true");
				   $("#failmaterialName").html("<font color=red>商品名称不能为空</font>");
			   }else{
				   $("#ibuttonsss").attr("disabled","false");
				   $("#failmaterialName").html("<font color=red>请商品名称注意填写是否正确</font>"); 
			   }							   
			   });
		   $("#price").blur(function(){
			   var pass = $(this).val();
			   var regPos = /^\d+(\.\d+)?$/; //非负浮点数
			    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;
			   if(pass==""){
				   $("#ibuttonsss").attr("disabled","true");
				   $("#failmaterialPrice").html("<font color=red>商品单价不能为空</font>");
			   }else if(regPos.test(pass) || regNeg.test(pass)){
				   $("#ibuttonsss").attr("disabled",false);
				   $("#failmaterialPrice").html("<font color=red>请注意单价必须为数字</font>"); 
			   }else{
				   $("#ibuttonsss").attr("disabled","true");
				   $("#failmaterialPrice").html("<font color=red>请注意单价必须为数字</font>"); 
			   }						   
			   }); */
		function deletemember() {
			var materialName = $("#materialName").val();
			var materialUnit = $("#materialUnit").val();
			var customerId=$("#customerId").val();
			var price = $("#price").val();
			var materialId = $("#materialId").val();
			$.ajax({
				url : 'materialInfoController/updateBySysMaterialId.ajax',
				data : {
					materialName : materialName,
					materialUnit : materialUnit,
					customerId:customerId,
					price : price,
					materialId : materialId
				},
				type : 'post',
				dataType : 'text',
				success : function(result) {					
					if (result == "succee") {
						
						layer.alert("修改成功", {
							icon : 6,
							time : 1500
						},
								function() {
									var index = parent.layer
											.getFrameIndex(window.name);
									parent.layer.close(index);
									parent.location.reload();
								});
					}else if(result=="repetition"){
						layer.alert("添加商品名称重复", {	
							icon : 6,
							time : 1500
						},function() {
						 var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index); 
						window.location.href = "materialInfoController/materialInfo.do";
						parent.location.reload();
					})		
					} else {
						alert(ssssssss);
					}
				},
			});
			return false;
		}
	</script>
</body>

</html>