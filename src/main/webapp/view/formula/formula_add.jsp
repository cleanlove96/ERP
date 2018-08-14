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
<title>生产管理</title>
<meta name="renderer" content="webkit">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/kkpager_orange.css">
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
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so">
				<div class="layui-form-item">
				<label for="" class="layui-form-label"> <span
					class="x-red">*</span>生产商品
				</label>
				<div class="layui-input-inline">
					<select id="commodityId" name="commodityId"  class="valid">
	                   <c:forEach items="${commodityList}" var="commodity">
	                   		<option value="${commodity.commodityId}">${commodity.commodityName}</option>
	                   </c:forEach>
                  	</select>
				</div>
				
			</div>
			</form>
		</div>
		<xblock>
			<button class="layui-btn" onclick="addMaterial()">
				<i class="layui-icon">&#xe608;</i>添加配料
			</button>
			<span class="x-right" style="line-height: 40px">共有数据：<a id="num">0</a> 条</span>
		</xblock>
		
		<table class="layui-table">
			<thead>
				<tr>
					<th>所需配料</th>
					<th>所需配料数量</th>
					<th>单位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="table">
				<!-- 数据示例 -->
				
				
				<!-- end 数据示例 -->
			</tbody>
		</table>
		<div id="add_btn"></div>
	</div>
	<script>
	
	function addMaterial(){
		
		var str="<tr id=\"${material.materialId}\"><td><select onchange=\"sel_change(this)\"  name=\"materialId\" >";
		var materialList=new Array();
		<c:forEach items="${materialList}" varStatus="status" var="material">
		var obj={};
			obj.materialId='${material.materialId}';
			obj.materialUnit='${material.materialUnit}';
			materialList[${status.count}-1]=obj;
			str+="<option title='${material.materialUnit}' value='${material.materialId}'>${material.materialName}</option>";
		</c:forEach>
		str+="</select></td><td><input type=\"number\"  value='0' autocomplete=\"off\"   lay-verify=\"required\""
		+"class=\"layui-input\"></td><td>"+materialList[0].materialUnit+"</td><td><a title='删除' onclick=\"del(this)\" href='javascript:;'>"
		+"<i class='layui-icon'>&#xe640;</i></a></td></tr>";
		$("#table").append(str);
		$("#num").html($("#table tr").length);
		if($("#table tr").length>0){
			$("#add_btn").html("<button class=\"layui-btn\"  onclick=\"add()\">增加</button>");
		}
	}
	function sel_change(obj){
		$(obj).parent().parent().find("td").eq(2).html($(obj).find("option:selected").attr("title"));
	}
	function add(){
		var arr=new Array();
		$("#table tr").each(function(i){
			var str="";
			var materialId=$(this).find("select").val();
			var formulaCount=$(this).find("input").val();
			str="{\"materialId\":\""+materialId+"\",\"formulaCount\":\""+formulaCount+"\"}";
			arr[i]=str;
		});
		var commodityId=$("#commodityId").val();
		var material=JSON.stringify(arr);
		$.ajax({
			url : 'formulaController/add.do',
			data : {
				material:material,
				commodityId:commodityId
			},
			type : 'POST',
			dataType : 'text',
			async:false,
			success : function(result) {
				if(result=="SUCCESS"){
					 layer.alert("增加成功", {
						icon : 6
					}, function() {
						//提交信息……
						// 获得frame索引
						var index = parent.layer.getFrameIndex(window.name);
						//关闭当前frame
						parent.layer.close(index);
						//parent.location.reload(); 
						parent.suaxin();
					}); 
				}else {
					if(result=="NAME_ERROR"){
						//$("#whn").html("名称重复，请修改");
						layer.alert('名称重复，请修改', {
							icon: 5,
							title: "提示"
							});
					}
					
				} 
			},
			error : function(data) {
				layer.alert("添加失败", {
					icon : 6
				});
			}
		});
	}
	 function del(obj){
         layer.confirm('确认要删除吗？',function(index){
             //发异步删除数据
             $(obj).parents("tr").remove();
             $("#num").html($("#table tr").length);
             layer.msg('已删除!',{icon:1,time:1000});
             if($("#table tr").length==0){
            	 $("#add_btn").html("");
             }
         });
     }
	</script>
</body>
</html>