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
			<input type="hidden" id="yearpanlTableId" name="yearpanlTableId" value="${yearTable.yearpanlTableId }">
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>商品
				</label>
				<div class="layui-input-inline">
					<select id="commodityId" name="commodityId">

					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>年份
				</label>
				<div class="layui-input-inline">
					<input type="text" id="yearNum" autocomplete="off"
						class="layui-input" name="yearNum" lay-verify="requireds" value="${yearTable.yearNum }">
				</div>
				<div class="layui-form-mid layui-word-aux">输入年份（xxxx）</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>数量（瓶）
				</label>
				<div class="layui-input-inline">
					<input type="text" id="goodsSum" autocomplete="off"
						class="layui-input" name="goodsSum" lay-verify="requireds" value="${yearTable.goodsSum }">
				</div>
				<div class="layui-form-mid layui-word-aux">输入数量</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>开始时间
				</label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="开始时间" id="startTime"
						type="text" name="startTime" lay-verify="requireds" value="${yearTable.startTime }">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>结束时间
				</label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="结束时间" id="endTime"
						type="text" name="endTime" lay-verify="requireds" value="${yearTable.endTime }">
				</div>
			</div>
			
			
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red"></span>备注
				</label>
				<div class="layui-input-inline">
					<input type="text" id="yearpanlVariety" autocomplete="off"
						class="layui-input" name="yearpanlVariety" value="${yearTable.yearpanlVariety }">
				</div>
				<div class="layui-form-mid layui-word-aux">输入备注</div>
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
	
	$(function(){
		//查看所有商品
		$.ajax({
			url : 'yearplanController/selectAllCommodity.ajax;charset=UTF-8',
			type : 'POST',
			/*async:false, ajax同步*/
			async:false, 
			data : {
		
			},
			dataType : 'text',
			success : function(res) {
				var ress = eval("(" + res + ")");
				console.log(ress);
				$("#commodityId").html("");
				$.each(ress,function(n, val) {	
					console.log(val.commodityName);
					$("#commodityId").append("<option value="+val.commodityId+">"+val.commodityName+"</option>");
					
				});
			},
			error : function() {

			}	
		});
		if("${yearTable.commodityId }"!=null){
			$("option[value='${yearTable.commodityId  }']").attr("selected",true);
		}
		
	}) 
//执行增加操作
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
		        	  //
		     var yearplans = $(".layui-form").serialize();
			$.ajax({
				url :'yearplanController/yearplanUpdate.ajax',
				type:'POST',
				data:yearplans,
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