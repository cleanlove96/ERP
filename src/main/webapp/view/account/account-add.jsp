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
<title>添加员工</title>
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
		<form class="layui-form">
			<div class="layui-form-item">
				<label for="L_id" class="layui-form-label"> <span
					class="x-red">*</span>登录名/工号
				</label>
				<div class="layui-input-inline">
					<!-- autocomplete="off" 是自动补全功能，是否需要看以前输入过的历史记录 -->
					<input type="text" id="accountLoginId" name="accountLoginId"
						required="required" lay-verify="requireds" autocomplete="ture"
						class="layui-input" data-validate="required:所属工号(登录名)"
						onchange="selectAccount()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="youAccountLoginId">所属工号(登录名)</div>
			</div>



			<div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>密码
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountLoginPwd" name="accountLoginPwd"
						autocomplete="off" class="layui-input" value="000000"
						lay-verify="requireds">
				</div>
				<div class="layui-form-mid layui-word-aux">6到16个字符</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>姓名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountName" autocomplete="off"
						class="layui-input" name="accountName" lay-verify="requireds">
				</div>
				<div class="layui-form-mid layui-word-aux">输入真实姓名</div>
			</div>
			<div class="layui-form-item">
				<label for="L_sex" class="layui-form-label"> <span
					class="x-red">*</span>性别
				</label>
				<div class="layui-input-inline">
					<select id="accountSex" name="accountSex">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_sex" class="layui-form-label"> <span
					class="x-red">*</span>状态
				</label>
				<div class="layui-input-inline">
					<select id="accountStatus" name="accountStatus">
						<option value="ON">ON</option>
						<option value="OFF">OFF</option>
					</select>
				</div>
				<div class="layui-form-mid layui-word-aux">选择员工是否允许登录</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>电话号码
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountPhone" autocomplete="off"
						class="layui-input" name="accountPhone" lay-verify="requireds"
						onchange="phoneChange()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="myaccountPhone">输入电话号码</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>身份证号码
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountIdcard" autocomplete="off"
						class="layui-input" name="accountIdcard" lay-verify="requireds"
						onchange="idChange()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="myaccountIdcard">输入身份证号码</div>
			</div>

			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>入职时间
				</label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="入职时间" id="accountEntryDate"
						type="text" name="accountEntryDate" lay-verify="requireds">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>学历
				</label>
				<div class="layui-input-inline">
					<select id="accountEduLevel" name="accountEduLevel">
						<option value="专科">专科</option>
						<option value="学士">学士</option>
						<option value="硕士">硕士</option>
						<option value="博士">博士</option>
						<option value="其他">其他</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>居住地
				</label>
				<div class="layui-input-inline">
					<input type="text" id="accountLocation" autocomplete="off"
						name="accountLocation" class="layui-input" lay-verify="requireds">
				</div>
				<div class="layui-form-mid layui-word-aux">输入员工居住地址</div>
			</div>
			<div class="layui-form-item">
				<label for="L_name" class="layui-form-label"> <span
					class="x-red">*</span>职位
				</label>
				<div class="layui-input-inline">
					<select id="roleId" name="roleId">

					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="button" lay-filter="edit"
					lay-submit="">增加</button>
			</div>
		</form>
	</div>
	<script>
		
		//框架原有的
		//加载时间的方法
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#accountEntryDate' //指定元素
			});
		});
		//查看所有职位
		$(function() {
			$.ajax({
				url : 'accountController/selectAllRoleWithAccount.ajax;charset=UTF-8',
				type : 'POST',
				/*async:false, ajax同步*/
				async:false, 
				data : {
			
				},
				dataType : 'text',
				success : function(res) {
					var ress = eval("(" + res + ")");
					/*var sss="";*/
					console.log(ress);
					$("#roleId").html("");
					$.each(ress,function(n, val) {	
						console.log(val.roleName);
						$("#roleId").append("<option value="+val.roleId+">"+val.roleName+"</option>");
						/*sss+="<option value="+val.roleId+">"+val.roleName+"</option>";*/
						
					});
				/*	$("#roleId").html(sss);*/
				},
				error : function() {

				}
				
			});
		})
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
		//查看该工号是否存在
		function selectAccount(){
			var accountLoginId=$("#accountLoginId").val();
			$.ajax({
				url : 'accountController/selectAccountByAccountId.ajax',
				type : 'POST',
				data : {
					accountLoginId:accountLoginId
				},
				success : function(res) {
					
					if(res=="yousuccess"){
						$("#youAccountLoginId").text("该工号可用");		
					}else{
						$("#youAccountLoginId").text("该工号已存在");
						alert("该工号已存在");
					}
				
				},
				error : function() {
					
				},
			});
		}
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
		     var accounts = $(".layui-form").serialize();
		     console.log(".....accounts....."+accounts);
			if($("#youAccountLoginId").html()=="该工号可用" && $("#myaccountIdcard").html()=="格式正确" && $("#myaccountPhone").html()=="格式正确"){
			var accountLoginId=$("#accountLoginId").val();
			var accountLoginPwd=$("#accountLoginPwd").val();
			var accountName=$("#accountName").val();
			var accountSex=$("#accountSex").val();
			var accountStatus=$("#accountStatus").val();
			var accountPhone=$("#accountPhone").val();
			var accountIdcard=$("#accountIdcard").val();
			var accountEntryDate=$("#accountEntryDate").val();
			var accountEduLevel=$("#accountEduLevel").val();
			var accountLocation=$("#accountLocation").val();
			var roleId=$("#roleId").val();
			$.ajax({
				url : 'accountController/accountAdd.ajax',
				type : 'POST',
				data : {
					accountLoginId:accountLoginId,
					accountLoginPwd:accountLoginPwd,
					accountName:accountName,
					accountSex:accountSex,
					accountStatus:accountStatus,
					accountPhone:accountPhone,
					accountIdcard:accountIdcard,
					accountEntryDate:accountEntryDate,
					accountEduLevel:accountEduLevel,
					accountLocation:accountLocation,
					roleId:roleId
				},
				dataType : 'text',
				success : function(res) {
					if(res=="success"){
						layer.alert("增加成功", {
							
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
					layer.alert("添加失败", {
						icon : 6
					});
				},
				
			
			});	
			}else{
				
				if( $("#youAccountLoginId").html()!="该工号可用"){
					alert("工号已存在，请重新输入");
					}else if( $("#myaccountIdcard").html()!="格式正确"){
						alert("身份证格式不正确");
					}else if($("#myaccountPhone").html()!="格式正确"){
						alert("电话号码格式不正确");
					}
		          
				
			}
			return false;
		});

		})
	</script>
</body>

</html>