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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=basePath %>css/font.css">
<link rel="stylesheet" href="<%=basePath %>css/xadmin.css">
<link rel="stylesheet" href="<%=basePath %>css/kkpager_orange.css" />
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath %>js/xadmin.js"></script>
<script type="text/javascript" src="<%=basePath %>js/kkpager.min.js"></script>
</head>
<body>
	<div class="x-body layui-anim layui-anim-up">
        <form class="layui-form">      
          <input type="hidden" name="commodityId" value="${sci.commodityId}">
          <div class="layui-form-item">
              <label for="L_commodityName" class="layui-form-label">
                  <span class="x-red">*</span>商品名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityName" name="commodityName" required="" autocomplete="off" class="layui-input" value="${sci.commodityName}">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commoditySpecification" class="layui-form-label">
                  <span class="x-red">*</span>商品规格
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commoditySpecification" name="commoditySpecification" required="" autocomplete="off" class="layui-input" value="${sci.commoditySpecification}">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityPlace" class="layui-form-label">
                  <span class="x-red">*</span>产地
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityPlace" name="commodityPlace" required="" autocomplete="off" class="layui-input" value="${sci.commodityPlace }"> 
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityType" class="layui-form-label">
                  <span class="x-red">*</span>商品类型
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityType" name="commodityType" required="" autocomplete="off" class="layui-input" value="${sci.commodtylType }">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityBrand" class="layui-form-label">
                  <span class="x-red">*</span>品牌
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityBrand" name="commodityBrand" required="" autocomplete="off" class="layui-input" value="${sci.commodtyBrand }">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityDegrees" class="layui-form-label">
                  <span class="x-red">*</span>度数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityDegrees" name="commodityDegrees" required="" autocomplete="off" class="layui-input" value="${sci.commodtyDegrees }">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityRecipe" class="layui-form-label">
                  <span class="x-red">*</span>配料
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityRecipe" name="commodityRecipe" required="" autocomplete="off" class="layui-input" value="${sci.commodtyRecipe }">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityExpiration" class="layui-form-label">
                  <span class="x-red">*</span>保质期
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityExpiration" name="commodityExpiration" required="" autocomplete="off" class="layui-input" value="${sci.commodtyExpirationDate }">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityOdor" class="layui-form-label">
                  <span class="x-red">*</span>香型
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityOdor" name="commodityOdor" required="" autocomplete="off" class="layui-input" value="${sci.commodtyOdorType }">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_commodityPrice" class="layui-form-label">
                  <span class="x-red">*</span>价格
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityPrice" name="commodityPrice" required="" autocomplete="off" class="layui-input" value="${sci.commodtyPrice }">
              </div>
          </div>          
          <div class="layui-form-item">
              <label class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="edit" lay-submit="">
                  	修改
              </button>
          </div>
      </form>
    </div>
    <script>
        layui.use(['form','layer'], function(){
          $ = layui.jquery;
          var form = layui.form ,layer = layui.layer;   
        	//监听提交
          form.on('submit(edit)', function(data){
        	var elements = $(".layui-form").serialize();
            console.log(elements);
            
            //发异步，把数据提交给controller层
            $.ajax({
  	            type:"POST",
   				url:"<%=basePath %>commodityController/updateCommodity.do",
   				data:elements,
   				dataType :"text",
            	success:function(res){
            		if(res=="YES"){
            			layer.alert("修改成功", {
							icon : 6
						}, function() {							
							// 获得frame索引
							var index = parent.layer.getFrameIndex(window.name);
							//关闭当前frame
							parent.layer.close(index);
							//刷新页面
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
    </script>
</body>
</html>