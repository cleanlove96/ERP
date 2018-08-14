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

<body>
	<div class="x-nav">
	
		<span class="layui-breadcrumb"> <a>人员管理</a> <a> <cite>用户管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<div class="layui-form layui-col-md12 x-so">
				<span>年份：</span> <input class="layui-input" placeholder="请输入年份"
					id="searchCard" value="${searchCard }">
				<button class="layui-btn" onclick="doSearchCard()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</div>
	
	<xblock>
		<span>商品：</span>
		<select id="selectSomeInfo" name="selectSomeInfo" style="width: 200px; height: 40px;" onchange="doSearchCard()" >
			<option value="">请选择商品</option>
			
		</select>
		<span class="x-right" style="line-height: 40px" id="count_num"></span> 
	</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>商品名</th>
					<th>年份</th>
					<th>数量（瓶）</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>制表人</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody id="yearplanTable">
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
	var mycommodity;
	var pn=1;
		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
			});
		}

		/*用户查询*/
		function doSearchCard(){
			var s = $("#searchCard").val();
			mycommodity=$("#selectSomeInfo").val()
			window.location.href = 'yearplanController/doSearchCards.do?searchCard='+ s+'&mycommodity='+ mycommodity;
		}
		/*改变商品名,查看相应的计划表信息*/
		
		$(function() {
			
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
					$("#roleId").html("");
					$.each(ress,function(n, val) {	
						console.log(val.commodityName);
						$("#selectSomeInfo").append("<option value="+val.commodityId+">"+val.commodityName+"</option>");
						
					});
				},
				error : function() {

				}	
			
			});
			
			if("${youcommodity }"!=null){
				$("option[value='${youcommodity }']").attr("selected",true);
			}
			//查看所有信息的内容
			searchCard = $("#searchCard").val();
			mycommodity=$("#selectSomeInfo").val()
			$.ajax({
				url : 'yearplanController/getPage.ajax',
				type : 'POST',
				data : {
					searchCard:searchCard,
					mycommodity:mycommodity
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
							showYeartablepage(n);
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
			showYeartablepage(1);
		})

		function showYeartablepage(n) {
			console.log(".....showYeartablepage....."+searchCard)
			$.ajax({
			 	url : 'yearplanController/getYearplanTable.ajax',
				type : 'POST',
				data : {
				n:n,
				searchCard:searchCard,
				mycommodity:mycommodity
				},
				dataType : 'text',
				success : function(res) {
					var yearplan = eval("(" + res + ")");
					console.log(".............."+yearplan)
					$("#yearplanTable").html("");
					$.each(yearplan,function(n, val) {	
					$("#yearplanTable").append("<tr><td>"+val.commodityId+"</td><td>"+val.yearNum+"</td><td>"+val.goodsSum+"</td><td>"+val.startTime+"</td><td>"+val.endTime+"</td><td>"+val.accountId+"</td><td>"+val.yearpanlVariety+"</td></tr>");
							});	
						},
				error : function() {

						}
			});

		}
	</script>
</html>