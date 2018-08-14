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
<title>组织结构</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<script type="text/javascript"
	src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.ztree.all.js"></script>
<!-- <script type="text/javascript" src="view/organization/organization-list.js"></script> -->
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>人员管理</a> <a> <cite>组织管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="row">
			<!-- 页面左侧（树形结构）部分 -->
			<div class="col-md-2">
				<div class="panel panel-default">
					<div class="panel-heading">组织结构</div>
					<div class="panel-body fullhight">
						<!-- 这里放置左侧内容主体 -->
						<div>
							<ul id="companytree" class="ztree"></ul>
						</div>
						<!-- end 左侧内容 -->
					</div>
				</div>
			</div>
			<!-- end 左侧 -->
			<!-- 页面右侧（详细信息）部分 -->
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">详细信息</h3>
					</div>
					<div class="panel-body fullhight">
						<!-- 这里放置右侧内容主体 -->
						<span class="message-title">基本信息</span>
						<hr />					
						<br /> <span class="message-title">联系方式</span>
						<hr />
						<table>
							<tr>

								<th width="100px">姓名</th>
								<th width="100px">学历</th>
								<th width="100px">工号</th>
								<th width="100px">状态</th>
								<th width="100px">性别</th>

								<th width="100px">电话</th>

							</tr>

							<tr>
								
							</tr>

						</table>
						<!-- end 右侧内容 -->
					</div>
				</div>
			</div>
			<!-- end 右侧 -->
		</div>
	</div>
</body>
<script type="text/javascript">
	//树形结构的设置
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : "0"
			}
		},
		// 拖动相关的设置
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false,
			drag : {
				isCopy : false,
				isMove : true
			}
		},
		view : {
			showIcon : true
		},
		/* async : {
			enable : true,
			url : "sectionRoleController/initZtree.do",
			autoParam : ["id"],
			type: "POST",
			otherParam: []
		}  */
		// 回调：拖动触发的剧情
		callback : {
			onDrop : zTreeOnDrop,
			onClick : oClick
		}
	/* check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "p", "N": "s" }
	} */
	};
	// 树形结构的节点信息
	 var nodes = [ {
	 'id' : '1',
	 'pId' : '0',
	 'name' : '公司',
	 'open' : 'true',
	 'icon' : 'images/company.png',
	 'type' : 'company'
	 }, {
	 'id' : '2',
	 'pId' : '1',
	 'name' : '部门1',
	 'icon' : 'images/department.png',
	 'type' : 'department'
	 }, {
	 'id' : '3',
	 'pId' : '1',
	 'name' : '部门2',
	 'icon' : 'images/department.png',
	 'type' : 'department'
	 }, {
	 'id' : '4',
	 'pId' : '1',
	 'name' : '部门3',
	 'icon' : 'images/department.png',
	 'type' : 'department'
	 }, {
	 'id' : '5',
	 'pId' : '2',
	 'name' : '子部门1',
	 'icon' : 'images/department.png',
	 'type' : 'department'
	 }, {
	 'id' : '6',
	 'pId' : '3',
	 'name' : '员工1',
	 'icon' : 'images/staff.png',
	 'type' : 'staff'
	 } ];

	 // 使用ajax生成树
	$(function() {
		$.ajax({
			url : "sectionRoleController/initZtree.do",
			type : "POST",
			data : {},
			dataType : "text",
			success : function(res) {
				//将对象解析成json格式
				var zNodes = eval("(" + res + ")");
				console.log(zNodes);
				$.fn.zTree.init($("#companytree"), setting, zNodes);
			}
		})
	});

	function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
		$.ajax({
			url : "sectionRoleController/drop.do",
			type : "POST",
			data : {
				pId : targetNode.id,
				id : treeNodes[0].id
			},
			dataType : "text",
			success : function(res) {
				if (res == "success") {

				} else {
					window.location.reload();
				}
			},
			error : function() {

			}
		});
	};

	function oClick(event, treeId, treeNode, treeNodes, targetNode) {
		// 获取树对象
		var companytree = $.fn.zTree.getZTreeObj("companytree");
		/* if (treeNode.style == 'staff') {
			// 如果点击的是员工节点，进行之后的操作

		} else {
			// 否则，展开/关闭该节点
			companytree.expandNode(treeNode);
		} */
		$.ajax({
			url : "sectionRoleController/selectAllAccount.do",
			type : "POST",
			data : {
				id : treeNode.id
			},
			dataType : "text",
			success : function(res) {
				alert(res);
				if (res == "error") {
					layer.alert("请选择一个员工", {
						icon : 6,
						time : 3500
					})
				} else {
					var account = eval("(" + res + ")");
					$("#infoTable").append(
							"<tr><td>" + account.accountLoginId + "</td><td>"
									+ account.accountEntryDate + "</td><td>"
									+ account.accountName + "</td><td>"
									+ account.accountEduLevel + "</td><td>"
									+ account.accountNum + "</td><td>"
									+ account.accountStatus + "</td><td>"
									+ account.accountSex + "</td><td>"
									+ account.accountPhone + "</td></tr>");
				}
			},
			error : function() {

			}
		});
	};
</script>
</html>