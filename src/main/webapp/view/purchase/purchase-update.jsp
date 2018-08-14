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
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
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
			<input type="hidden" id="purchaseId" name="purchaseId" value="${purchaseOnly.purchaseId }">
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>原料
				</label>
				<div class="layui-input-inline">
					<select id="materialId" name="materialId">

					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>数量
				</label>
				<div class="layui-input-inline">
					<input type="text" id="purchaseCount" autocomplete="off"
						class="layui-input" name="purchaseCount" lay-verify="requireds" value="${purchaseOnly.purchaseCount }">
				</div>
				<div class="layui-form-mid layui-word-aux">请输入数量</div>
			</div>			
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>开始时间
				</label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="开始时间" id="startTime"
						type="text" name="startTime" lay-verify="requireds" value="${purchaseOnly.startTime }">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>结束时间
				</label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="结束时间" id="endTime"
						type="text" name="endTime" lay-verify="requireds" value="${purchaseOnly.endTime }">
				</div>
			</div>
					
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="button" lay-filter="edit"
					lay-submit="">修改</button>
			</div>
		</form>
	</div>
	
</body>
<script type="text/javascript">
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt))
fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o){
    if (new RegExp("(" + k + ")").test(fmt)) {
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
}
    }
    return fmt;
} 
	var naemm=new Date($("#startTime").val());
	var naemme=new Date($("#endTime").val());
	$("#startTime").val(naemm.Format("yyyy-MM-dd"));
	$("#endTime").val(naemme.Format("yyyy-MM-dd"));
//加载时间的方法
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//执行一个laydate实例
		laydate.render({
			elem : '#startTime' //指定元素
			
		});
	});
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//执行一个laydate实例
		laydate.render({
			elem : '#endTime' //指定元素
		});
	});
	$(function() {
		selectPurchase();
	})
	function selectPurchase(){
		var searchCard=${purchaseOnly.purchaseTime};
		$.ajax({
			url : 'purchaseController/selectAllNeed.ajax',
			type : 'POST',
			async:false, 
			data : {
				searchCard : searchCard
			},
			dataType : 'text',
			success : function(res) {
				var myPurchaseTable = eval("(" + res + ")");
				$.each(myPurchaseTable,function(n, val) {
				$("#materialId").append("<option value='"+val.materialId+"'>"+val.materialName+"</option>");
				});
				
			},
			error : function() {

			}

		});
		if("${purchaseOnly.materialId }"!=null){
			$("option[value='${purchaseOnly.materialId }']").attr("selected",true);
		}
	}
	layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form ,layer = layui.layer; 
        form.verify({
			requireds:function(value){
				if(value==null||value==""){
					return "有值未输入，请全部输入再提交";
				}
			}
        });
							
      	//监听提交
   form.on('submit(edit)', function(data){
	   var purchaseId=$("#purchaseId").val();
	   var materialId=$("#materialId").val();
	   var purchaseCount=$("#purchaseCount").val();
	   var startTime=$("#startTime").val();
	   var endTime=$("#endTime").val();
  
	$.ajax({
		url :'purchaseController/purchaseUpdate.ajax',
		type:'POST',
		data:{
			purchaseId:purchaseId,
			materialId:materialId,
			purchaseCount:purchaseCount,
			startTime:startTime,
			endTime:endTime
		},
		dataType : 'text',
		success : function(res) {
			if(res=="success"){
				layer.alert("修改成功", {
					
					icon : 6 //icon : 6定义弹框样式
				}, function() {
					//提交信息……
					// 获得frame索引
					var index = parent.layer.getFrameIndex(window.name);
					//关闭当前frame
					parent.layer.close(index);
					parent.location.reload(); 
				});
			}else{
				layer.alert("修改失败", {
					
					icon : 6 //icon : 6定义弹框样式
				}, function() {
					//提交信息……
					// 获得frame索引
					var index = parent.layer.getFrameIndex(window.name);
					//关闭当前frame
					parent.layer.close(index);
					parent.location.reload(); 
				});
			}
			

		},
		error : function() {
			layer.alert("修改失败", {
				icon : 6
			});
		},
		
	
	});	
	return false;
});

})
	
	
	
	</script>
</html>