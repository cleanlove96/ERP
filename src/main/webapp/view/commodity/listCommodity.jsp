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
<title>商品管理</title>
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
  			<a style="vertical-align: middle;"> <cite>商品信息</cite></a>
  		</span>
		<a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
  <div class="x-body">
    <div class="layui-row">
      <form class="layui-form layui-col-md12 x-so">
        <span>快速查询：</span>  <input type="text" id="commodity" name="commodity" value="${commodity }"  placeholder="请输入商品名" autocomplete="off" class="layui-input">
        <button class="layui-btn"  lay-submit="" lay-filter="search" type="button" onclick="search()"><i class="layui-icon">&#xe615;</i></button>
      </form>
    </div>
    <xblock>
      <!-- <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button> -->
      <button class="layui-btn" onclick="x_admin_show('添加商品','view/commodity/addCommodity.jsp',600,400)"><i class="layui-icon"></i>添加</button>
      <span class="x-right" id="x-right" style="line-height:40px"></span>
    </xblock>
    <table class="layui-table">
      <thead>
        <tr>         
          <!-- <th>ID</th> -->
          <th>商品名称</th>
          <th>商品规格</th>
          <th>产地</th>
          <th>商品类型</th>
          <th>品牌</th>
          <th>度数</th>
          <!-- <th>配料</th> -->
          <th>保质期</th>
          <th>香型</th>
          <th>价格</th>
          <th>状态</th>
          <th>操作</th></tr>
      </thead>
      <tbody id="commoditytable">      
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
    	var commodity;
    	$(function(){
    		commodity = $("#commodity").val();
    		$.ajax({
    			url:"<%=basePath%>commodityController/getPages.do",
    			type:"POST",
    			data:{
    				commodity:commodity
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
    				$("#x-right").text("共有数据："+totalRecords+" 条");
    			},
    			error:function(){
    				
    			}
    		});
    		showpage(1);   		    		
    	});
    	
    	 function showpage(n){
    		    $.ajax({
    				type:"POST",
    				url:"<%=basePath%>commodityController/queryCommodity.do",
    				data:{	
    					n:n,
    					commodity:commodity
    				},
    				dataType : 'text',
    				success:function(result){
    					var jtable = eval("("+result+")");
    					$("#commoditytable").html("");
    					$.each(jtable,function(n,element){
    						var str="";
    						var img="";
    						if(element.commodtyStatus=='启用'){
    							str+="<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini' id='"+element.commodityId+"'>";
    							str+=element.commodtyStatus + "</span></td>";
    							img+="&#xe601;";
    						}else{
    							str+="<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini layui-btn-disabled' id='"+element.commodityId+"'>";
    							str+=element.commodtyStatus + "</span></td>";
    							img+="&#xe62f;";
    						}   						
    						$("#commoditytable").append(
    								"<tr>" 
    								/* + "<td class='td-id'>"
    								+ element.commodityId + "</td>" */
    								+ "<td>" 
    								+ element.commodityName + "</td><td>" 
    								+ element.commoditySpecification + "</td><td>" 
    								+ element.commodityPlace + "</td><td>" 
    								+ element.commodtylType + "</td><td>" 
    								+ element.commodtyBrand + "</td><td>" 
    								+ element.commodtyDegrees + "</td>" 
    								/* + "<td>"
    								+ element.commodtyRecipe + "</td>"  */
    								+ "<td>"
    								+ element.commodtyExpirationDate + "</td><td>" 
    								+ element.commodtyOdorType + "</td><td>" 
    								+ element.commodtyPrice + "</td>"
    								+ str
    								+ "<td class='td-manage'>"
    								+ "<a onclick=\"status_change(this,'"+element.commodityId+"')\" href='javascript:;'  title='更改状态'> <i class='layui-icon' id='change"+element.commodityId+"'>"
    								+ img
    								+ "</i> </a>"
    								+ "<a title='编辑' onclick=\"x_admin_show('编辑','<%=basePath %>commodityController/updateCommodityUI.do?commodityId="+element.commodityId+"',600,400)\" href='javascript:;'><i class='layui-icon'>&#xe642;</i></a>" 
    								+ "</td></tr>");
    					});   					
    				},
    				error: function(){
    					alert("ajax请求失败");
    				}
    			});
    	    }
    	 
    	 /*商品-删除*/
   	    function status_change(obj,id){
    		 var status = $("#"+id).text();
   			 layer.confirm('确认要启用/停用该商品吗？',function(){ 
   	            $.ajax({
   	            	type:"POST",
    				url:"<%=basePath%>commodityController/changeStatus.do",
    				data:{	
    					commodityId:id,
    					commodtyStatus:status
    				},
   	            	success:function(){
   	            		if(status=='启用'){
   	                     //发异步把用户状态进行更改
   	                     $("#"+id).text('停用')
   	                     $("#change"+id).html('&#xe62f;');
   	                  	 $("#"+id).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('停用');
   	                     layer.msg('已停用!',{icon: 5,time:1000});
   	                   }else if(status=='停用'){
   	                	 $("#"+id).text('启用')
   	                     $("#change"+id).html('&#xe601;');
   	                	 $("#"+id).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('启用');
   	                     layer.msg('已启用!',{icon: 6,time:1000});
   	                   }
   	            	},
   	            	error:function(){
   	            		
   	            	}
   	            });
   	        });    		    	        
   	    }
    	 
	   	 function search(){
	   		var s = $("#commodity").val();
	   		//alert(s);
	   		window.location.href='commodityController/search.do?commodity='+s; 
	   	 }
    </script>
</body>
</html>