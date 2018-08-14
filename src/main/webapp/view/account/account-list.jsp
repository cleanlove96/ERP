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
<title>用户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_blue.css" />
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/kkpager.min.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="layui-anim layui-anim-up">
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>人员管理</a> <a> <cite>员工基本信息管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>快速查询：</span> <input class="layui-input" placeholder="请输入关键字"
					id="searchCard" value="${searchCard }">
				<button class="layui-btn" onclick="doSearchCard()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加用户','view/account/account-add.jsp',500,500)">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height: 40px" id="count_num"></span>
		</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>登录名/工号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>电话号码</th>
					<th>居住地</th>
					<th>学历</th>
					<th>身份证号码</th>
					<th>入职时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="accountTable">
				<!-- 数据示例 -->

				<!-- end 数据示例 -->
			</tbody>
		</table>
		<!-- 分页部分 -->
		<div id="kkpager"></div>
		<!-- end 分页 -->

	</div>

</body>

<script type="text/javascript">
	var searchCard;
	/*用户-控制*/
	function member_stop(obj, id) {
		var status = $("#"+id+"").text();
		layer.confirm('确认要停用/启用该用户吗？', function(index) {
      		console.log("myStatus"+status)
			if (status=="ON") {
				$(obj).attr('title', '启用')
				$(obj).find('i').html('&#xe62f;');
				//发异步把用户状态进行更改
				$.ajax({
					url : 'accountController/updateAccountStatus.ajax',
					type : 'POST',
					data : {
						id : id,
						status : status
					},
					dataType : 'text',
					success : function(res) {
						var astatus = eval("(" + res + ")");
						
						$(obj).parents("tr").find(".td-status").find('span').addClass(
								'layui-btn-disabled').html(astatus);
						layer.msg('停用成功!', {
							icon : 1,
							time : 500
						});
					},
					error : function() {

					}

				});
				//.....
				
			} else {
				
				$(obj).attr('title', '启用')
				$(obj).find('i').html('&#xe601;');
				console.log();
				$.ajax({
					url : 'accountController/updateAccountStatus.ajax',
					type : 'POST',
					data : {
						id : id,
						status : status
					},
					dataType : 'text',
					success : function(res) {
						var bstatus = eval("(" + res + ")");
						
						$(obj).parents("tr").find(".td-status").find('span')
								.removeClass('layui-btn-disabled').html(bstatus);
						layer.msg('启用成功!', {
							icon : 1,
							time : 500
						});
					},
					error : function() {

					}

				});
				
			}

		});
	}

	/*用户-删除*/
	function member_del() {
		layer.confirm('不能删除员工信息，可把状态置为OFF', function(index) {
			//发异步删除数据
			layer.msg('未进行任何操作!', {
				icon : 1,
				time :2000
			});
		});
	}
	/*重置默认密码*/
	function updatePwd(obj,id){
		layer.confirm('确认要重置改密码吗？密码为：000000', function(index) {
			//发异步删除数据
			$.ajax({
				url : 'accountController/resetPwd.ajax',
				type : 'POST',
				data : {
					id : id,
				},
				dataType : 'text',
				success : function(res) {
					if(res=="success"){
					layer.msg('已重置!', {
						icon : 1,
						time : 500
					});	
					}else{
						layer.msg('已是默认密码!', {
							icon : 1,
							time : 2000
						});	
					}
				},
				error : function() {

				}
				
			});
			
		});
		
		
	}
	/*用户查询*/
	function doSearchCard() {
		var s = $("#searchCard").val();
		window.location.href = 'accountController/doSearchCard.do?searchCard='+ s;
	}

	$(function() {
		searchCard = $("#searchCard").val();
		
		$.ajax({
			url : 'accountController/getPage.do',
			type : 'POST',
			data : {
				searchCard : searchCard
			},
			dataType : 'text',
			success : function(res) {
				var page = eval("(" + res + ")");
				$("#count_num").text("共有数据:" + page.totalRecords + "条");
				//初始化分页插件
				kkpager.generPageHtml({
					pno : '1',
					mode : 'click', //设置为click模式
					//总页码  
					total : page.total,
					//总数据条数  
					totalRecords : page.totalRecords,
					//点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
					//适用于不刷新页面，比如ajax
					click : function(n) {

						//这里可以做自已的处理
						showAccountpage(n);
						//处理完后可以手动条用selectPage进行页码选中切换

						this.selectPage(n);
					},
					//getHref是在click模式下链接算法，一般不需要配置，默认代码如下
					getHref : function(n) {
						return '#';
					}

				});
			},
			error : function() {

			}
		});
		showAccountpage(1);
	})

	function showAccountpage(n) {
		$.ajax({
		 	url : 'accountController/getAccountTable.ajax',
			type : 'POST',
			data : {
			n : n,
			searchCard : searchCard
			},
			dataType : 'text',
			success : function(res) {
				var atable = eval("(" + res + ")");
				$("#accountTable").html("");
				$.each(atable,function(n, val) {
					var strr="";
					if(val.accountStatus=="ON"){
						strr+="<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini' id='"+val.accountId+"'>" + val.accountStatus+ "</span></td><td class='td-manage'> <a onclick=\"member_stop(this,'"+ val.accountId+"')\" href='javascript:;' title='停用'><i class='layui-icon'>&#xe601;</i> </a>"
						
					}else{
						strr+="<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini layui-btn-disabled' id='"+val.accountId+"'>" + val.accountStatus+ "</span></td><td class='td-manage'> <a onclick=\"member_stop(this,'"+ val.accountId+"')\" href='javascript:;' title='启用'><i class='layui-icon'>&#xe62f;</i> </a>"
						
					}
				$("#accountTable").append("<tr><td>"+ val.accountLoginId+ "</td><td>"+ val.accountName+ "</td><td>"+ val.accountSex+ "</td><td>"
				+ val.accountPhone+ "</td><td>"+ val.accountLocation+ "</td><td>"+ val.accountEduLevel+ "</td><td>"+ val.accountIdcard+ "</td><td>"
				+ val.accountEntryDate+ "</td>"+strr+"<a title='修改' onclick=\"x_admin_show('修改','accountController/selsectAccountByAccountId.do?accountId="+val.accountId+"',600,400)\" href='javascript:;'> <i class='layui-icon'>&#xe642;</i> </a> <a onclick=\"updatePwd(this,'"+val.accountId+"')\" title='置为默认密码' href='javascript:;'> <i class='layui-icon'>&#xe631;</i> </a> <a title='删除' onclick='member_del()' href='javascript:;'> <i class='layui-icon'>&#xe640;</i> </a></td> <tr>");
						});
						
					},
			error : function() {

					}

		});

	}
	//不能单引号里面包单引号，得用\"来代替'\"x_admin_show('修改','view/account/account-update.jsp',600,400)里面的数字表示表格的长宽
</script>
</html>