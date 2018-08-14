<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_orange.css" />
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
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
  		<span class="layui-breadcrumb"> 
  			<a style="vertical-align: middle;">基本信息管理</a> 
  			<a style="vertical-align: middle;"> <cite>销售信息</cite></a>
  		</span>
		<a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
  <div class="x-body">
    <div class="layui-row">
      <form class="layui-form layui-col-md12 x-so">
        <span>快速查询：</span>  <input type="text" id="customer" name="customer" value="${customer }"  placeholder="请输入顾客名" autocomplete="off" class="layui-input">
        <button class="layui-btn"  lay-submit="" lay-filter="search" type="button" onclick="search()"><i class="layui-icon">&#xe615;</i></button>
      </form>
    </div>
    <xblock>
      <!-- <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button> -->
      <button class="layui-btn" onclick="x_admin_show('添加销售信息','view/sale/addSale.jsp',800,400)"><i class="layui-icon"></i>添加</button>
      <span class="x-right" id="x-right" style="line-height:40px"></span>
    </xblock>
    <table class="layui-table">
      <thead>
        <tr>         
          <th>单据号</th>
          <th>顾客名称</th>
          <th>销售时间</th>
          <th>负责人</th>
          <th>金额</th>
          <th>详细信息</th></tr>
      </thead>
      <tbody id="saletable">      
      </tbody>    
    </table>
	<div id="kkpager"></div>
  </div>
  <script>var _hmt = _hmt || []; (function() {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
      var s = document.getElementsByTagName("script")[0];
      s.parentNode.insertBefore(hm, s);
    })();</script>

    <script type="text/javascript">
    	var customer;
    	$(function(){
    		customer = $("#customer").val();
    		$.ajax({
    			url:"<%=basePath%>saleController/getPages.do",
    			type:"POST",
    			data:{
    				customer:customer
    			},
    			dataType:"text",
    			success:function(result){    				
    				var page = eval("("+result+")");
    				var totalRecords = page.totalRecords;
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
    						showpage(n);
    						//处理完后可以手动条用selectPage进行页码选中切换
    						this.selectPage(n);
    					},
    					//getHref是在click模式下链接算法，一般不需要配置，默认代码如下
    					getHref : function(n) {
    						return '#';
    					}
    				});
    			},
    			error:function(){
    				
    			}
    		});
    		showpage(1);   		    		
    	});
    	
    	 function showpage(n){
    		    $.ajax({
    				type:"POST",
    				url:"<%=basePath%>saleController/querySale.do",
    				data:{	
    					n:n,
    					customer:customer
    				},
    				dataType : 'text',
    				success:function(result){
    					var jtable = eval("("+result+")");
    					var key, count = 0;
    					for(key in jtable) { //获取json的长度
    					  if(jtable.hasOwnProperty(key)) {
    					    count++;
    					  }
    					}
    					$("#x-right").text("共有数据："+count+" 条");
    					$("#saletable").html("");
    					$.each(jtable,function(n,element){   
    						console.log(element);
    						$("#saletable").append(
    								"<tr>" 
    								+ "<td class='td-id'>"
    								+ element.receiptId + "</td>"
    								+ "<td>" 
    								+ element.customerName + "</td><td>" 
    								+ element.saleTime + "</td><td>" 
    								+ element.accountName + "</td><td>" 
    								+ element.saleTotal + "</td><td>"
    								+ "<a title='详细信息' onclick=\"x_admin_show('详细信息','<%=basePath %>view/sale/saleDetail.jsp?Receipts="+element.receiptId+"&customerName="+element.customerName+"&accountName="+element.accountName+"',800,400)\" href='javascript:;'><i class='layui-icon'>&#xe60b;</i></a>" 
    								+ "</td></tr>");
    					});   					
    				},
    				error: function(){
    					alert("ajax请求失败");
    				}
    			});
    	    }
    	 
	   	 function search(){
	   		var s = $("#customer").val();
	   		//alert(s);
	   		window.location.href='saleController/search.do?customer='+s; 
	   	 }
    </script>
</body>
</html>