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
<title>编辑生产单</title>
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
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/date_util.js"></script>
<script type="text/javascript" src="js/test.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body">
		<form id="updateForm" class="layui-form"  onsubmit="return update()">
			<input type="hidden" id="ropId" name="ropId" value="${ropTable.ropId}">
				<div class="layui-form-item">
					<label for="" class="layui-form-label"> <span
						class="x-red"></span>批次号
					</label>
					<div class="layui-input-inline">
						<input  type="text" id="batchNumber" autocomplete="off" name="batchNumber"  value="${ropTable.batchNumber }"  lay-verify="batchNumber"
							class="layui-input" readonly="readonly">
					</div>
					<label for="" class="layui-form-label">
					</label>
				</div>
				<div class="layui-form-item">
				<label for="" class="layui-form-label"> <span
					class="x-red">*</span>生产线
				</label>
				<div class="layui-input-inline" >
					<select id="capacityId" name="capacityId" class="valid" disabled="disabled">
	                   <c:forEach items="${capacityList}" var="capacity">
	                   		<c:choose>
		                   		<c:when test="${capacity.capacityId==ropTable.capacityId}">
		                   			<option selected="selected" value="${capacity.capacityId}">${capacity.capacityProductionLineName}</option>
		                   		</c:when>
		                   		<c:otherwise>
		                   			<option value="${capacity.capacityId}">${capacity.capacityProductionLineName}</option>
		                   		</c:otherwise>
	                   		</c:choose>
	                   </c:forEach>
                  	</select>
				</div>
				
			</div>
			<div class="layui-form-item">
				<label for="1" class="layui-form-label"> <span
					class="x-red">*</span>生产商品
				</label>
				<div class="layui-input-inline">
					<select id="commodityId" name="commodityName" class="valid" disabled="disabled">
	                   
	                   <c:forEach items="${commodityList}" var="commodity">
                  		<c:choose>
                   		<c:when test="${commodity.commodityId==ropTable.commodityId}">
                   			<option selected="selected"  value="${commodity.commodityId}">${commodity.commodityName}</option>
                   		</c:when>
                   		<c:otherwise>
                   			<option value="${commodity.commodityId}">${commodity.commodityName}</option>
                   		</c:otherwise>
                   		</c:choose>
                   </c:forEach>
                  	</select>
					
				</div>
				
			</div>
			<div class="layui-form-item">
				<label for="2" class="layui-form-label"> <span
					class="x-red"></span>产量
				</label>
				<div class="layui-input-inline">
					<input type="text" id="ropUnit" autocomplete="off" name="ropUnit" value="${ropTable.ropUnit }"  lay-verify="ropUnit"
						class="layui-input" readonly="readonly">
				</div>
				<div class="layui-form-mid layui-word-aux">瓶</div>
			</div>
			
			<div class="layui-form-item">
				<label for="3" class="layui-form-label"> <span
					class="x-red"></span>损耗量
				</label>
				<div class="layui-input-inline">
					<input type="text" id="ropLoss" autocomplete="off" name="ropLoss" value="${ropTable.ropLoss }"   lay-verify="ropLoss"
						class="layui-input">
				</div>
				
				<div class="layui-form-mid layui-word-aux">瓶</div>
			</div>
			<div class="layui-form-item">
				<label for="4" class="layui-form-label"> <span
					class="x-red"></span>入库量
				</label>
				<div class="layui-input-inline">
					<input  type="text" id="ropIntoWarehouse" autocomplete="off" name="ropIntoWarehouse" value="${ropTable.ropIntoWarehouse }"   lay-verify="ropIntoWarehouse"
						class="layui-input" readonly="readonly">
				</div>
				
				<div class="layui-form-mid layui-word-aux">瓶</div>
			</div>
			
			<div class="layui-form-item">
				<label for="5" class="layui-form-label"> <span
					class="x-red"></span>生产时间
				</label>
				<div class="layui-input-inline">
					<input type="date" id="ropProductionTime" autocomplete="off" name="ropProductionTime" value="${ropTable.ropProductionTime }" 
						class="layui-input" readonly="readonly">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="6" class="layui-form-label"> <span
					class="x-red"></span>入库时间
				</label>
				<div class="layui-input-inline">
					<input type="date" id="ropWarehouseEntryTime" autocomplete="off" name="ropWarehouseEntryTime"  value="${ropTable.ropWarehouseEntryTime }" 
						class="layui-input"> 
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button  class="layui-btn" lay-filter="*" lay-submit="">修改</button> 
			</div>
		</form>
	</div>
	<script>
		$(function(){
		verify();
		var dateStr=new Date('${ropTable.ropProductionTime }');
		$("#ropProductionTime").val(dateStr.Format("yyyy-MM-dd"));
		var timeStr=new Date('${ropTable.ropWarehouseEntryTime }');
		$("#ropWarehouseEntryTime").val(timeStr.Format("yyyy-MM-dd"));
		});
		$("#ropLoss").blur(function(){
			var ropUnit=$("#ropUnit").val();
			var ropLoss=$("#ropLoss").val();
			if(ropUnit.length>0&&ropLoss.length>0){
				$("#ropIntoWarehouse").val(ropUnit-ropLoss);
			}
		});
		function update() {

			/* var data =$("#updateForm").serialize(); */
			var batchNumber=$("#batchNumber").val();
			var capacityId=$("#capacityId").val();
			var commodityId=$("#commodityId").val();
			var ropUnit=$("#ropUnit").val();
			var ropId=$("#ropId").val();
			var ropLoss=$("#ropLoss").val();
			var ropIntoWarehouse=$("#ropIntoWarehouse").val();
			var productionProductionTime=$("#ropProductionTime").val();
			var dateStr=JSON.stringify(productionProductionTime);
			var ropWarehouseEntryTime=$("#ropWarehouseEntryTime").val();
			var timeStr=JSON.stringify(ropWarehouseEntryTime);
			$.ajax({
				url : 'qualityTestController/update.do',
				data :{
					batchNumber:batchNumber,
					capacityId:capacityId,
					commodityId:commodityId,
					ropUnit:ropUnit,
					ropLoss:ropLoss,
					ropIntoWarehouse:ropIntoWarehouse,
					dateStr:dateStr,
					timeStr:timeStr,
					ropId:ropId
				},
				type : 'POST',
				dataType : 'text',
				success : function(result) {
					if(result=="SUCCESS"){
						 layer.alert("修改成功", {
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
					layer.alert("修改失败", {
						icon : 6
					}); 
				}
			});
			return false;
		}
	</script>
</body>

</html>