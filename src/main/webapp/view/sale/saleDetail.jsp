<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_orange.css" />
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/kkpager.min.js"></script>
</head>
<style>
.order-table {
    top: 20px;
    bottom: 30px;
    /*border: 1px solid #ccc;*/
    height: auto;
}
	input[readonly]{
	    cursor:not-allowed;/*鼠标为禁用样式*/
	}

</style>

<body>
    <div class="x-body layui-anim layui-anim-up">
         <form class="layui-form">     
          <input type="hidden" id="Receipts" value="<%=request.getParameter("Receipts") %>">
          <input type="hidden" id="customerName" value="<%=request.getParameter("customerName") %>">
          <input type="hidden" id="accountName" value="<%=request.getParameter("accountName") %>">
          
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;">
              <label for="L_customerName" class="layui-form-label">
                  <span class="x-red">*</span>顾客名称
              </label>
              <div class="layui-input-inline">
              	  <!-- <select id="L_customerName" name="customerName" required="" autocomplete="off" class="layui-input" lay-verify="customerName" lay-filter="customerSelect"></select> -->
                  <input type="text" id="L_customerName" name="customerName" required="" autocomplete="off" class="layui-input" lay-verify="customerName" value="<%=request.getParameter("customerName") %>" readonly="readonly">
              </div>
            </div>  
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;">
              <label for="L_accountName" class="layui-form-label">
                  <span class="x-red">*</span>负责人
              </label>
              <div class="layui-input-inline">
              	  <!-- <select id="L_accountName" name="accountName" required="" autocomplete="off" class="layui-input" lay-verify="accountName" lay-filter="accountSelect"></select> -->
                  <input type="text" id="L_accountName" name="accountName" required="" autocomplete="off" class="layui-input" lay-verify="accountName" value="<%=request.getParameter("accountName") %>" readOnly="readonly">                 
              </div>
            </div>   
          </div>
          
          <!-- 销售单明细条目 -->
          <div class="order-table">
            <table lay-filter="order" class="layui-table" id="saleTable">
                 <thead>
                    <tr>
                        <th lay-data="{type:'commodityname'}">商品名</th>
                        <th lay-data="{field:'amount', width:100,edit:'text'}">数量</th>
                        <th lay-data="{field:'price', width:150,templet: '#Age'}">单价</th>
                        <th lay-data="{field:'total',width:'100',edit:'text'}">金额</th>
                        <!-- <th lay-data="{field:'sign',edit:'text'}">备注</th> -->
                    </tr>
                </thead>
                <tbody id="saleTableBody">
                    
                </tbody>
            </table>
        </div>
        <div class="layui-form-item">
            <div style="width: 64px;height: 38px;margin: 0 auto;">
              <label for="L_close" class="layui-form-label">
                  
              </label>
              <button  class="layui-btn" lay-filter="close" type="button" lay-submit="" style="margin: 0 auto;">
                  	关闭
              </button>
            </div>  
          </div>
      </form>    
    </div>
</body>
<script>
	$(function(){
		/* 获取商品信息 */
		var Receipts = $("#Receipts").val();
		$.ajax({
			type:"POST",
			url:"<%=basePath%>saleController/getDetail.do",
			data:{	
				Receipts:Receipts
			},
			async : false,
			dataType : 'text',
			success:function(result){
				var jselect = eval("("+result+")");
				$("#saleTableBody").html("");
				$.each(jselect,function(n,element){
					$("#saleTableBody").append(
							"<tr><td>"
							+ element.commodityName + "</td><td>"
							+ element.amount + "</td><td>"
							+ element.price +  "</td><td>"
							+ element.total + "</td></tr>"
					);
				});
			},
			error: function(){
				alert("ajax请求失败");
			}
		});
	});
	
	layui.use(['form','layer'], function(){
        $ = layui.jquery;
      var form = layui.form 
      ,layer = layui.layer;               
      
      //监听提交
      form.on('submit(close)', function(data){		
	        // 获得frame索引
	        var index = parent.layer.getFrameIndex(window.name);
	        //关闭当前frame
	        parent.layer.close(index);
	        //parent.location.reload(); //刷新父页面
        	return false;
      });
    }); 
</script>
</html>
