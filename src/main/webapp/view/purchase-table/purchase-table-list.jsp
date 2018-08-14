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
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>采购总计划管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_blue.css" />
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script type="text/javascript" src="js/kkpager.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript"
	src="view/organization/organization-list.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>采购单</a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="row">
			<!-- 页面右侧（详细信息）部分 -->

			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="layui-row" style="height: 40px">
							<div class="layui-form layui-col-md12 x-so">

								<button class="layui-btn"
									onclick="x_admin_show('添加客户','view/customer_relation/customer_add.jsp',600,500)">
									<i class="layui-icon">&#xe654;</i>添加客户信息
								</button>
								<button class="layui-btn" id="s_add" lay-filter="s_add">
									<i class="layui-icon">&#xe642;</i>添加一行数据
								</button>
							</div>
						</div>

					</div>

					<div class="panel-body fullhight">
						<!-- 这里放置右侧内容主体 -->
						<form class="layui-form">

							<div class="layui-form-item">
								<label for="L_name" class="layui-form-label">编号</label>
								<div class="layui-input-inline">
									<input type="text" id="extendId" autocomplete="off" onchange="selectExtendById()"
										class="layui-input" name="extendId" placeholder="采购单编号"
										lay-verify="requireds" lay-filter="extendNumber"><span id="extendIdName" style="color: green;"></span>
								</div>
								<label for="L_name" class="layui-form-label">客户 </label>
								<div class="layui-input-inline">
									<select id="customerId" name="customerId" lay-filter="selectCustomer">
										<option value="">请选择客户</option>

									</select>
								</div>
								<label for="L_name" class="layui-form-label">经办人 </label>
								<div class="layui-input-inline">
									<input type="text" id="extendName" autocomplete="off"
										class="layui-input" name="extendName" placeholder="经办人姓名"
										lay-verify="requireds">
								</div>
							</div>
							<div class="layui-form-item">
								<label for="L_name" class="layui-form-label">联系方式 </label>
								<div class="layui-input-inline">
									<input type="text" id="extendPhone" autocomplete="off"
										class="layui-input" name="extendPhone" placeholder="经办人联系方式"
										lay-verify="requireds">
								</div>

								<label for="L_name" class="layui-form-label">年份 </label>
								<div class="layui-input-inline">
									<input type="text" id="purchase" autocomplete="off"
										placeholder="xxxx" class="layui-input" name=purchase
										lay-verify="requireds">
								</div>
							</div>
							<table class="layui-table">
								<thead>
									<tr>
										<th>原料名</th>
										<th>数量</th>
										<th>单价</th>
										<th>总金额（元）</th>
										<th>采购时间</th>
										<th>备注</th>
										<th>操作</th>
										
									</tr>
								</thead>
								<tbody id="puechaseTable">
								
								
								
								</tbody>
							</table>
							</br>
							</br>
							</br>
							</br>
							<div class="layui-form-item">
								<label for="L_name" class="layui-form-label">总金额</label>
								<div class="layui-input-inline">
									<input type="text" id="extendPrices" autocomplete="off"
										class="layui-input" name="extendPrices" placeholder="采购表实付总金额"
										lay-verify="requireds">
								</div>
								<label for="L_name" class="layui-form-label">制表人</label>
								<div class="layui-input-inline">
									<input type="text" id="accountName" autocomplete="off" readonly="readonly"
										class="layui-input" name="accountName" placeholder="制表人"
										lay-verify="requireds">
								</div>
								<label for="L_name" class="layui-form-label">制表时间 </label>
								<div class="layui-input-inline">
									<input type="text" id="extendTime" autocomplete="off" readonly="readonly"
										class="layui-input" name="extendTime" placeholder="制表时间"
										lay-verify="requireds">
								</div>
							
							<button class="layui-btn" type="button" lay-filter="edit"
									lay-submit="">提交采购单</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
layui.use('laydate', function() {
	var laydate = layui.laydate;
	//执行一个laydate实例
	laydate.render({
		elem :'#timeIshehe' //指定元素		
	});
});
$(function(){
	selectCustomerAll();
	selectAccountSome();
	selectMaterialAll();
	})
	//查询所有客户信息
function selectCustomerAll(){
	$.ajax({
		url : 'PurchaseTableController/selectCustomerAll.ajax',
		type : 'POST',
		async:false, 
		data : {
				
		},
		dataType : 'text',
		success : function(res) {
			var myPurchaseTable = eval("(" + res + ")");
			$.each(myPurchaseTable,function(n, val) {		
			$("#customerId").append("<option value='"+val.customerId+"'>"+val.customerCompany+"</option>");
			});			
		},
		error : function() {
			alert("未获得数据");
		}
	});
}
//根据选择的客户公司，写出负责人
layui.use([ 'form', 'layer' ], function() {
	$ = layui.jquery;
	var form = layui.form,
	layer = layui.layer;
	form.on('select(selectCustomer)',function(data){
		var myCustomerId=$("#customerId").val();
		$.ajax({
			url : 'PurchaseTableController/selectCustomerAllById.ajax',
			type : 'POST',
			async:false, 
			data : {
				myCustomerId : myCustomerId
			},
			dataType : 'text',
			success : function(res) {
		
				var j = eval("(" + res + ")");
				$("#extendName").val(j.customerName);
				$("#extendPhone").val(j.customerTel);				
			},
			error : function() {
				alert("未获得数据");
			}

		});
	});
	});
	
//根据传入的采购单编号，查看是否已有该采购单
	function selectExtendById(){
		var extendId=$("#extendId").val();
		console.log("........"+extendId);
		$.ajax({
			url : 'PurchaseTableController/selectextendIdById.ajax',
			type : 'POST',
			async:false, 
			data : {
				extendId : extendId
			},
			dataType : 'text',
			success : function(res) {		
				if(res=="success"){
					alert("该编号不可用已存在");
					
				}else{
					$("#extendIdName").text("该编号可用");
				}			
			},
			error : function() {
				alert("未获得数据");
			}

		});
	}
//页面加载，初始化数据	
	function selectAccountSome(){
		$.ajax({
			url : 'PurchaseTableController/selectAccountSome.ajax',
			type : 'POST',
			async:false, 
			data : {
				
			},
			dataType : 'text',
			success : function(res) {		
				var mysomeTable = eval("(" + res + ")");							
				$("#extendTime").val(mysomeTable.newTime);
				$("#purchase").val(mysomeTable.year);
				$("#accountName").val(mysomeTable.accounName);			
			},
			error: function() {
				alert("未获得数据");
			}

		});
	
	
	}
//查看出所有原材料	

function selectMaterialAll(){
	$.ajax({
		url : 'PurchaseTableController/selectMaterialAll.ajax',
		type : 'POST',
		async:false, 
		data : {
				
		},
		dataType : 'text',
		success : function(res) {
			var myMaterialTable = eval("(" + res + ")");
			$.each(myMaterialTable,function(n, val) {		
			$("#materialId").append("<option value='"+val.materialId+"'>"+val.materialName+"</option>");
			});			
		},
		error : function() {
			alert("未获得原料数据");
		}
	});
}	
layui.use(['table', 'layer', 'form'], function() {
    var table = layui.table,
    form = layui.form
    ,layer = layui.layer;   
    /* var tables = $("#orderTable").next().find(".layui-table"); 
    tables.find("[data-field='commodityId']").css("display","none"); */

    $("#s_add").click(function() {
    	/* 获取所有原料的名字 */
		$.ajax({
			type:"POST",
			url:"PurchaseTableController/selectMaterialAll.ajax",
			data:{	
				
			},
			async : false,
			dataType : 'text',
			success:function(res){
				var jselect = eval("("+res+")");
				var v = "";
				var i = "";
				/*$.each(jselect,function(n,element){
					if(n==0){
						i = element.materialId;
					}
				});*/
				var allMterial="<tr><td><div class='layui-title'><select id='materialId' name='materialId' class='layui-input layui-unselect' style='display:block;width:150px'> <option value=''>请选择原料</option>";
				$.each(jselect,function(n,element){
					
					allMterial +="<option id='"+element.materialId+"' title='"+element.materialId+"' value='"+element.materialId+"'>"+element.materialName+"</option>";
					
				});	
				
				allMterial +="</select></div></td><td>"
							+"<input type='text' id='purchaseTableInt' autocomplete='off' onchange='changePrice(this)' class='layui-input' name='purchaseTableInt' placeholder='采购数量' lay-verify='requireds' style='border: 0'></td>"
								+"<td><input type='text' id='purchaseTablePrice' onchange='changePrice(this)' autocomplete='off' class='layui-input' name='purchaseTablePrice' placeholder='单价' lay-verify='requireds' style='border: 0'></td>"
								+"<td><input type='text' id='purchaseTableMoney'  autocomplete='off' class='layui-input' name='purchaseTableMoney' placeholder='总金额' lay-verify='requireds' style='border: 0'></td>"
								+"<td><input class='layui-input' placeholder='采购时间' id='purchaseTableTime' type='date' name='purchaseTableTime' lay-verify='timeIshehe' autocomplete='off'></td>"
								+"<td><input type='text' id='remarks' autocomplete='off' class='layui-input' name='remarks' placeholder='备注'  style='border: 0'></td>"	
								+"<td><button class='layui-btn' onclick='del(this)' id='remove' lay-filter='remove' type='button'><i class='layui-icon'>&#xe640;</i>删除此行</button></td>"										
								+"</tr>";
				$("#puechaseTable").append(allMterial);
				//$(this).parent().parent().find("td").eq(2).find("input").val($("#L_commodity option").attr("id"));
			},
			error: function(){
				alert("ajax请求失败");
			}
		});
    });
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
    	
  	var elements = $(".layui-form").serialize();
      var arr= new Array();
      $("#puechaseTable tr").each(function(i){
    	  var str ="";
    	  var materialId = $(this).find("td").eq(0).find("select").find("option:selected").attr("title");
    	  var purchaseTableInt = $(this).find("td").eq(1).find("input").val();
    	  var purchaseTablePrice = $(this).find("td").eq(2).find("input").val();
    	  var purchaseTableMoney = $(this).find("td").eq(3).find("input").val();
    	  var purchaseTableTime = $(this).find("td").eq(4).find("input").val();
    	  var remarks = $(this).find("td").eq(5).find("input").val();
    	  str = "{'materialId':'"+materialId+"','purchaseTableInt':"+purchaseTableInt+",'purchaseTablePrice':'"+purchaseTablePrice+"','purchaseTableMoney':'"+purchaseTableMoney+"','purchaseTableTime':'"+purchaseTableTime+"','remarks':'"+remarks+"'}";
		  arr[i] = str;
      });
      var material=JSON.stringify(arr);
      //采购单编号
      var extendId = $("#extendId").val();
      //客户id
      var customerId = $("#customerId").val();
      //经办人姓名
      var extendName = $("#extendName").val();
      //联系方式
      var extendPhone = $("#extendPhone").val();
      //年份
      var purchase = $("#purchase").val();
      //总金额
	  var extendPrices = $("#extendPrices").val();
	  var extendIdName=$("#extendIdName").html();
	  console.log("......."+extendIdName);
	  if(extendIdName=="该编号可用"){
	 
      //发异步，把数据提交给后台
      $.ajax({
            	type:"POST",
				url:"PurchaseTableController/addAllTable.ajax",
				data:{
					material:material,
					extendId:extendId,
					customerId:customerId,
					extendName:extendName,
					extendPhone:extendPhone,
					purchase:purchase,
					extendPrices:extendPrices
				},
				dataType :"text",
      	success:function(res){
      		if(res=="success"){
      			layer.alert("增加成功", {
					
					icon : 6 //icon : 6定义弹框样式
					//刷新页面
					
				},function() {
					//提交信息……
					// 获得frame索引
					
					//关闭当前frame
					
					location.reload(); 
				});
				
      		}   	 
      	},
      	error:function(){
      		alert("增加有误");
      	}         
      });
	  }else{
		  alert("编号有误，请重新输入");
	  }
      return false;
	  
    }); 

});
function updateAome(){
	
}
//获取原料单行的总金额
function changePrice(obj){
	var comSinglePrice= $(obj).parent().parent().find("td").eq(1).find("input").val();
	var comCount = $(obj).parent().parent().find("td").eq(2).find("input").val();
	$(obj).parent().parent().find("td").eq(3).find("input").val(comSinglePrice*comCount);
	//alert(comSinglePrice+"+"+comCount);
}


	//删除行
    function del(obj){
		var row = $(obj).parent().parent().remove();
	}
	
</script>

</html>