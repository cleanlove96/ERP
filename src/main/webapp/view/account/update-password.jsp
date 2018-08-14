<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
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
</head>
 <body>
    <div class="x-body">
        <form class="layui-form">
        <input type="hidden" id="accountLoginPwd" name="accountLoginPwd" value="${myAccount.accountLoginPwd }">
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                	登录名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_username" name="username" disabled="" value="${myAccount.accountLoginId }" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>旧密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="oldpass" name="oldpass"  lay-verify="requireds" onchange="selectPwd()" 
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>新密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="newpass" name="newpass"  lay-verify="requireds1"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  6到16个字符
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>确认密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="repass" name="repass"  lay-verify="requireds2"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="save" lay-submit="">
                 	 修改
              </button>
          </div>
      </form>
    </div>
    <script>
    
    
    //验证旧密码是否正确
		    function selectPwd(){
		    	if($("#oldpass").val()!=$("#accountLoginPwd").val()){
		    		alert("旧密码输入错误");
		    		
		    	}
		  	
		    }
    
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
              form.verify({
            	  requireds:function(value){
		  				if(value==null||value==""){
		  					return "有值未输入，请全部输入再提交";
		  				}
		  			},
		  			requireds1:function(value){
	  				if(value==null||value==""){
	  					return "有值未输入，请全部输入再提交";
	  				}
	  			},
	  		
		  			requireds2:function(value){
				  	if(value==null||value==""){
				  			return "有值未输入，请全部输入再提交";
				  		}
				  	},
				  	requireds2: function(value){
	                if($('#repass').val()!=$('#newpass').val()){
	                    return '两次密码不一致';
	                }
	            },
	            requireds1: [/(.+){6,12}$/, '密码必须6到12位']
	  			
                });
				
              //监听提交
          form.on('submit(save)', function(data){
            var newPassword = $(".layui-form").serialize();
              console.log(data);
                if($("#oldpass").val()!=$("#accountLoginPwd").val()){
		    		alert("旧密码输入错误");
		    	}else{

		    			var newpass=$("#newpass").val();
		    			$.ajax({
		    				url : 'accountController/updateNewPassword.ajax',
		    				type : 'POST',
		    				data : {
		    					newpass:newpass
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
		    					}
		    					
		    			
		    				},
		    				error : function() {
		    					layer.alert("修改失败", {
		    						icon : 6
		    					});
		    				},
		    				
		    			
		    			});	
		    			  			
		}
                return false;
        });
              
            })
        </script>
    <script>
    var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>
</html>