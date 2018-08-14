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
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
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
		<form class="layui-form" >
		
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>电话号码
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountPhone" autocomplete="off" value="${myAccount.accountPhone }"
						class="layui-input" name="accountPhone" lay-verify="requireds" onchange="phoneChange()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="myaccountPhone">输入电话号码</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>身份证号码
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountIdcard" autocomplete="off" value="${myAccount.accountIdcard }"
						class="layui-input" name="accountIdcard" lay-verify="requireds" onchange="idChange()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="myaccountIdcard">输入身份证号码</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>居住地
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountLocation" autocomplete="off" value="${myAccount.accountLocation }"
						name="accountLocation" class="layui-input" lay-verify="requireds">
				</div>
				<div class="layui-form-mid layui-word-aux">输入员工居住地址</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="button"  lay-filter="edit" lay-submit="">
					修改</button>
			</div>
		</form>
	</div>
	<script>			
		//判断身份证格式是否正确
		function idChange(){
			var idCard=/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
			if(idCard.test($("#accountIdcard").val()))
			{
				$("#myaccountIdcard").text("格式正确");
			}
			else{
				$("#myaccountIdcard").text("格式不正确");
				alert("身份证格式不正确");
				}
		}
		//手机号码，正则表达
		function phoneChange(){
			var phone=/(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
			if(phone.test($("#accountPhone").val()))
			{
				$("#myaccountPhone").text("格式正确");
			}
			else{
				$("#myaccountPhone").text("格式不正确");
				alert("电话号码格式不正确");
				}
		}
		
		
		//框架原有的
		//加载时间的方法
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#accountEntryDate' //指定元素
			});
		});
		
		 layui.use(['form','layer'], function(){
		          $ = layui.jquery;
		          var form = layui.form ,layer = layui.layer; 
		          form.verify({
		  			requireds:function(value){
		  				if(value==null||value==""){
		  					return "有值未输入，请全部输入再提交";
		  				}
		  			}
		  					
		  		})
		  		
		        	//监听提交
		          form.on('submit(edit)', function(data){
		        	
		        	var accountsome = $(".layui-form").serialize();
		          
		            var idCards=/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
					if(idCards.test($("#accountIdcard").val()))
					{
						$("#myaccountIdcard").text("格式正确");
					}
					else{
						$("#myaccountIdcard").text("格式不正确");
						
						}
					
					var phones=/(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
					if(phones.test($("#accountPhone").val()))
					{
						$("#myaccountPhone").text("格式正确");
					}
					else{
						$("#myaccountPhone").text("格式不正确");
					 	
						}
					
		            if($("#myaccountIdcard").html()=="格式正确" && $("#myaccountPhone").html()=="格式正确"){
		            //发异步，把数据提交给controller层
		         	var accountPhone=$("#accountPhone").val();
					var accountIdcard=$("#accountIdcard").val();
					var accountLocation=$("#accountLocation").val();
		            $.ajax({
		  	            type:"POST",
		   				url:"accountController/updateMyAccount.ajax",
		   				data:{
		   					accountPhone:accountPhone,
		   					accountIdcard:accountIdcard,
		   					accountLocation:accountLocation
		   				},	
		   				dataType :"text",
		            	success:function(res){
		            		if(res=="success"){
		            			layer.alert("修改成功", {
									icon : 6
								}, function() {							
									// 获得frame索引
									var index = parent.layer.getFrameIndex(window.name);
									//关闭当前frame
									parent.layer.close(index);
									//刷新父页面
									parent.location.reload(); 
								});
		            		}	
		            		
		            	},
		            	error:function(){
		            		layer.alert("修改失败", {
								icon : 6
							});
		            	}         
		            }); 
		            }else{
		            	if( $("#myaccountIdcard").html()=="格式不正确"){
								alert("身份证格式不正确");
							}else if($("#myaccountPhone").html()=="格式不正确"){
								alert("电话号码格式不正确");
							}
		            }
		            return false;
		          
		            });
			     });                
		
	</script>
</body>

</html>