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
          <input type="hidden" id="customerId" name="customerId">     
          <input type="hidden" id="accountId" name="accountId">        
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;">
              <label for="L_customerName" class="layui-form-label">
                  <span class="x-red">*</span>顾客名称
              </label>
              <div class="layui-input-inline">
              	  <select id="L_customerName" lay-ignore name="customerName" style="display:block;"></select>
              </div>
            </div>  
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;">
              <label for="L_accountName" class="layui-form-label">
                  <span class="x-red">*</span>负责人
              </label>
              <div class="layui-input-inline">
              	  <select id="L_accountName" lay-ignore style="display:block;" name="accountName"></select>
              </div>
            </div>   
          </div>
          <!-- 按钮区域 -->
          <div style="width: 120px;height: 30px;">
              <button  class="layui-btn" id="s_add" lay-filter="s_add" type="button" style="width: 90px;height: 35px;font-size: 10px;">
                  	增加商品
              </button>
          </div>  
          <!-- 订单明细条目 -->
          <div class="order-table">
            <table lay-filter="order" class="layui-table" id="orderTable">
                 <thead>
                    <tr>
                    	<th lay-data="{field:'commodityId',style:'display:none;'}">id</th>
                        <th lay-data="{type:'commodityname'}">商品名</th>
                        <th lay-data="{field:'amount', width:100,edit:'text'}">数量</th>
                        <th lay-data="{field:'price', width:150}">单价</th>
                        <th lay-data="{field:'total',width:'100',edit:'text'}">金额</th>
                        <th lay-data="{field:'operate'}">操作</th>
                    </tr>
                </thead>
                <tbody id="orderTableBody" lay-filter="test">
                    <!-- <tr><td><div><select><option></option></select></div></td></tr> -->
                </tbody>
            </table>
        </div>
        <div class="layui-form-item">
            <div style="width: 160px;height: 38px;margin: 0 auto;">
              <label class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                  	提交订单
              </button>
            </div>  
          </div>
      </form>    
    </div>
</body>
<script>
	$(function(){
		/* 获取所有保存的顾客名  */
		var customer = "";
		$.ajax({
			type:"POST",
			url:"<%=basePath%>saleController/queryCustomerName.do",
			data:{	
				
			},
			async : false,
			dataType : 'text',
			success:function(result){
				var jselect = eval("("+result+")");
				$("#L_customerName").html("");				
				$.each(jselect,function(n,element){
					if(element.customerDemand=='买家'){
						$("#L_customerName").append(
								"<option title='"+element.customerId+"'>"+element.customerName+"</option>"		
							);
					}
					
					if(n==0){
						customer = element.customerId;
					}
				});
			},
			error: function(){
				alert("ajax请求失败");
			}
		});
		$("#customerId").val(customer);
		
		/* 获取所有销售人员的名字  */
		var account= "";
		$.ajax({
			type:"POST",
			url:"<%=basePath%>saleController/queryAccountName.do",
			data:{	
				
			},
			async : false,
			dataType : 'text',
			success:function(result){
				var jselect = eval("("+result+")");
				$("#L_accountName").html("");
				$.each(jselect,function(n,element){    
					$("#L_accountName").append(
						"<option title='"+element.accountId+"'>"+element.accountName+"</option>"		
					);
					if(n==0){
						account = element.accountId;
					}
				});
			},
			error: function(){
				alert("ajax请求失败");
			}
		});
		$("#accountId").val(account);	
		
		
		$("#L_customerName").change(function(){
			//alert("进入顾客ID改变方法");
			$("#customerId").val($("#L_customerName").find("option:selected").attr("title"));
		});
		
		$("#L_accountName").change(function(){
			//alert("进入员工ID改变方法");
			$("#accountId").val($("#L_accountName").find("option:selected").attr("title"));
		});
	});
	
	$('table.layui-table thead tr th:nth-child(1)').addClass('layui-hide');

	layui.use(['table', 'layer', 'form'], function() {
	    var table = layui.table,
	    form = layui.form
	    ,layer = layui.layer;
	    
	    form.verify({
	    	amount:function(value){
	    		 if(value==null||value==""){
	                 return '请输入商品数量';
	               }
	    		 var p= /^[\d]+$/;
	             if(!p.test(value)){
	            	 return '请输入正整数';
	             }
	    	}
	    });
	    	
	    $("#s_add").click(function() {
	    	/* 获取所有商品的名字 */
    		$.ajax({
    			type:"POST",
				url:"<%=basePath%>saleController/queryCommodityName.do",
				data:{	
					
				},
				async : false,
				dataType : 'text',
				success:function(result){
					var jselect = eval("("+result+")");
					var v = "";
					var i = "";
					$.each(jselect,function(n,element){
						if(n==0){
							i = element.commodityId;
						}
					});
					var allCommodity = "<tr><td style='display:none'><input type='text' value='"+i+"' id='commodityId' name='commodityId'></input></td><td><select id='L_commodity' onchange=\"cha(this)\" name='commodity' style='display:block;'>";
					$.each(jselect,function(n,element){
						allCommodity += "<option id='"+element.commodtyPrice+"' title='"+element.commodityId+"' value=''>"+element.commodityName+"</option>";
						if(n==0){
							v = element.commodtyPrice;
						}
					});	
					allCommodity += "</select></td><td id='amountParent' style='width:120px;'>"
									+ "<input type='text' oninput=\"changePrice(this)\" onporpertychange=\"changePrice(this)\" id='L_amount' name='amount' required='' autocomplete='off' class='layui-input' lay-filter='amount' lay-verify='amount' style='border:0;width:120px;'>"
									+ "</td>"
									+ "<td id='comSinglePrice' style='width:100px;'>"
									+ "<input type='text' id='L_comSinglePriceInp' value='"+v+"' oninput=\"singlePrice(this)\" onporpertychange=\"singlePrice(this)\" name='comSinglePriceInp' required='' autocomplete='off' class='layui-input' lay-verify='comSinglePriceInp' style='border:0;width:100px;' readOnly='readOnly'></td>"
									+ "<td id='comSumPrice' style='width:100px;'>"
									+ "<input type='text' id='L_comSumPriceInp' name='comSumPriceInp' required='' autocomplete='off' class='layui-input' lay-verify='comSumPriceInp' style='border:0;width:100px;' readOnly='readOnly' placeholder='0'></td>"
									+ "</td>"
									+ "<td>"
									+ "<button class='layui-btn' onclick='del(this)' id='remove' lay-filter='remove' type='button' style='height: 28px;line-height:28px;font-size: 8px;'>删除此行</button>"
									+ "</td>"
									+ "</tr>";
					$("#orderTable").append(allCommodity);
					//$(this).parent().parent().find("td").eq(2).find("input").val($("#L_commodity option").attr("id"));
				},
				error: function(){
					alert("ajax请求失败");
				}
    		});
	    });
      
	  //监听提交
        form.on('submit(add)', function(data){
      	var elements = $(".layui-form").serialize();
          var arr= new Array();
          $("#orderTable tr").each(function(i){
        	  var str ="";
        	  var commodityId = $(this).find("td").eq(1).find("select").find("option:selected").attr("title");
        	  var amount = $(this).find("td").eq(2).find("input").val();
        	  var UnitPrice = $(this).find("td").eq(3).find("input").val();
        	  var totalPrice = $(this).find("td").eq(4).find("input").val();
        	  str = "{'commodityId':'"+commodityId+"','amount':"+amount+",'UnitPrice':'"+UnitPrice+"','totalPrice':'"+totalPrice+"'}";
			  arr[i] = str;
          });
          var order=JSON.stringify(arr);
          var customerId = $("#customerId").val();
    	  var accountId = $("#accountId").val();
          //发异步，把数据提交给php
          $.ajax({
	            type:"POST",
 				url:"<%=basePath %>orderController/addOrder.do",
 				data:{
 					order:order,
 					customerId:customerId,
 					accountId:accountId
 				},
 				dataType :"text",
          	success:function(res){
          		if(res=="YES"){
          			layer.alert("增加成功", {icon: 6},function(){
   	                  // 获得frame索引
   	                  var index = parent.layer.getFrameIndex(window.name);
   	                  //关闭当前frame
   	                  parent.layer.close(index);
   	                  parent.location.reload();
   	                  });
          		}	            	 
          	},
          	error:function(){
          		
          	}         
          });
          return false;
        }); 
      }); 
 	
	//改变商品获取单价
	function cha(obj){
		var comSinglePrice = $(obj).find("option:selected").attr("id");
		$(obj).parent().parent().find("td").eq(3).find("input").val(comSinglePrice);
		//$("#commodityId").val( $(obj).find("option:selected").attr("title"));
		$(obj).parent().parent().find("td").eq(0).find("input").val($(obj).find("option:selected").attr("title"))
		var comCount = $(obj).parent().parent().find("td").eq(2).find("input").val();
		$(obj).parent().parent().find("td").eq(4).find("input").val(comSinglePrice*comCount);
	}
	
	//获取商品的总金额
	function changePrice(obj){
		var comSinglePrice= $(obj).parent().parent().find("td").eq(1).find("select").find("option:selected").attr("id");
		var comCount = $(obj).parent().parent().find("td").eq(2).find("input").val();
		$(obj).parent().parent().find("td").eq(4).find("input").val(comSinglePrice*comCount);
		//alert(comSinglePrice+"+"+comCount);
	}
	
	//获取商品的单价
	function singlePrice(obj){
		var comCount = $(obj).parent().parent().find("td").eq(2).find("input").val();
		var comSinglePrice= $(obj).parent().parent().find("td").eq(1).find("select").find("option:selected").attr("id");
		$(obj).parent().parent().find("td").eq(4).find("input").val(comSinglePrice*comCount);
		//alert(comSinglePrice+"+"+comCount);
	}
	
	//删除行
	function del(obj){
		var row = $(obj).parent().parent().remove();
	}
</script>
</html>
