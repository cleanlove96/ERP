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
<script type="text/javascript" src="js/address.js"></script>
<style type="text/css">
	.m_zlxg {  border: none; height: 38px; line-height: 25px; cursor: pointer; float: left; display: inline; position: relative ;}
	.m_zlxg p { width: 100%; padding-right:5px; overflow: hidden; line-height: 38px; color: #000000; font-size: 12px; text-overflow: ellipsis; white-space: nowrap; vertical-align: middle;}
	.m_zlxg2 { position: absolute; z-index: 1; top: 24px; border: 1px solid #cccccc; background: #fff; width: 98px; display: none; max-height: 224px; -height: 224px; overflow-x: hidden; overflow-y: auto; white-space: nowrap; border-radius: 5px; }
	.m_zlxg2 ul { width: 100% }
	.m_zlxg2 li { line-height: 28px; padding-left: 10px; color: #333333; font-size: 12px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden }
	.m_zlxg2 li:hover { color: #7a5a21; }
</style>
</head>
<body>
    <div class="x-body layui-anim layui-anim-up">
        <form class="layui-form">      
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityName" class="layui-form-label">
                  <span class="x-red">*</span>商品名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityName" name="commodityName" required="" autocomplete="off" class="layui-input" lay-verify="commodityName">
              </div>
            </div>  
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commoditySpecification" class="layui-form-label">
                  <span class="x-red">*</span>商品规格
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commoditySpecification" name="commoditySpecification" required="" autocomplete="off" class="layui-input" lay-verify="commoditySpecification">
              </div>
            </div>    
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityPlace" class="layui-form-label">
                  <span class="x-red">*</span>产地
              </label>
              <div class="layui-input-inline" style="width: 300px;">
                  <input type="hidden" id="L_commodityPlace" name="commodityPlace" required="" autocomplete="off" class="layui-input" lay-verify="commodityPlace">           
                  <!-- <div id="area_adr" class="layui-input area_adr" style="width: 190px;height:38px;overflow-y: hidden;overflow-x:auto;white-space:nowrap;"> -->
            			<div class="m_zlxg" id="shengfen">
			                <p title="">选择省份</p>
			                <div class="m_zlxg2">
			                    <ul></ul>
			                </div>
			            </div>
			            <div class="m_zlxg" id="chengshi">
			                <p title="">选择城市</p>
			                <div class="m_zlxg2">
			                    <ul></ul>
			                </div>
			            </div>
			            <div class="m_zlxg" id="quyu">
			                <p title="">选择区域</p>
			                <div class="m_zlxg2">
			                    <ul></ul>
			                </div>
			            </div>
			            <input id="sfdq_num" type="hidden" value="" />
			            <input id="csdq_num" type="hidden" value="" />
			            <input id="sfdq_tj" type="hidden" value="" />
			            <input id="csdq_tj" type="hidden" value="" />
			            <input id="qydq_tj" type="hidden" value="" />
			        </div>       			
              <!-- </div> -->
            </div> 
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityType" class="layui-form-label">
                  <span class="x-red">*</span>商品类型
              </label>
              <div class="layui-input-inline">
                  <!-- <input type="text" id="L_commodityType" name="commodityType" required="" autocomplete="off" class="layui-input" lay-verify="commodityType"> -->
              	  <select id="L_commodityType" name="commodityType" required="" autocomplete="off" class="layui-input" lay-verify="commodityType">
              	  	<option>白酒</option>
              	  	<option>啤酒</option>
              	  	<option>葡萄酒</option>
              	  	<option>黄酒</option>
              	  	<option>米酒</option>
              	  	<option>药酒</option>
              	  </select>
              </div>
            </div> 
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityBrand" class="layui-form-label">
                  <span class="x-red">*</span>品牌
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityBrand" name="commodityBrand" required="" autocomplete="off" class="layui-input" lay-verify="commodityBrand">
              </div>
            </div>   
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityDegrees" class="layui-form-label">
                  <span class="x-red">*</span>度数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityDegrees" name="commodityDegrees" required="" autocomplete="off" class="layui-input" lay-verify="commodityDegrees">
              </div>
            </div>  
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityRecipe" class="layui-form-label">
                  <span class="x-red">*</span>配料
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityRecipe" name="commodityRecipe" required="" autocomplete="off" class="layui-input" lay-verify="commodityRecipe"> 
              </div>
            </div>  
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityExpiration" class="layui-form-label">
                  <span class="x-red">*</span>保质期
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityExpiration" name="commodityExpiration" required="" autocomplete="off" class="layui-input" lay-verify="commodityExpiration">
              </div>
            </div>  
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityOdor" class="layui-form-label">
                  <span class="x-red">*</span>香型
              </label>
              <div class="layui-input-inline">
                  <!-- <input type="text" id="L_commodityOdor" name="commodityOdor" required="" autocomplete="off" class="layui-input" lay-verify="commodityOdor"> -->
                  <select id="L_commodityOdor" name="commodityOdor" required="" autocomplete="off" class="layui-input" lay-verify="commodityOdor">
              	  	<option>无香型</option>
              	  	<option>酱香型(茅香型)</option>
              	  	<option>浓香型(泸香型)</option>
              	  	<option>清香型(汾香型)</option>
              	  	<option>米香型</option>
              	  	<option>兼香型(复香型)</option>
              	  </select>
              </div>
            </div>   
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityPrice" class="layui-form-label">
                  <span class="x-red">*</span>价格
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_commodityPrice" name="commodityPrice" required="" autocomplete="off" class="layui-input" lay-verify="commodityPrice">
              </div>
            </div>   
          </div>
          <div class="layui-form-item">
            <div style="width: 500px;height: 38px;margin: 0 auto;">
              <label for="L_commodityStatus" class="layui-form-label">
                  <span class="x-red">*</span>状态
              </label>
              <div class="layui-input-inline">
                  <!-- <input type="text" id="L_commodityStatus" name="commodityStatus" required="" autocomplete="off" class="layui-input" lay-verify="commodityStatus"> -->
              	  <select id="L_commodityStatus" name="commodityStatus" required="" autocomplete="off" class="layui-input" lay-verify="commodityStatus">
              	  	<option>启用</option>
              	  	<option>停用</option>
              	  </select>
              </div>
            </div>  
          </div>
          <div class="layui-form-item">
            <div style="width: 160px;height: 38px;margin: 0 auto;">
              <label class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                  	增加
              </button>
            </div>  
          </div>
      </form>
    </div>
    <script>
	    $(function(){
	    	$("#area_adr").sjld("#shengfen","#chengshi","#quyu");
	    	
	    	$(".layui-btn").click(function(){
	    		$("#L_commodityPlace").val($(".m_zlxg p").text());
	    	});
	    	
	    });
    
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form 
          ,layer = layui.layer;               

          //自定义验证规则
          form.verify({
        	commodityName: function(value){
              if(value==null||value==""){
                return '请输入商品名称';
              }
            },
            commoditySpecification: function(value){
               if(value==null||value==""){
                 return '请输入商品规格';
               }
            },
            commodityPlace: function(value){
                if(value==null||value==""){
                  return '请输入产地';
                }
             },
             commodityType: function(value){
                 if(value==null||value==""){
                   return '请输入商品类型';
                 }
              },
              commodityBrand: function(value){
                  if(value==null||value==""){
                    return '请输入商品品牌';
                  }
               },
               commodityDegrees: function(value){
                   if(value==null||value==""){
                     return '请输入度数';
                   }
                },
                commodityRecipe: function(value){
                    if(value==null||value==""){
                      return '请输入商品配料';
                    }
                 },
                 commodityExpiration: function(value){
                     if(value==null||value==""){
                       return '请输入保质期';
                     }
                 },
                 commodityOdor: function(value){
                      if(value==null||value==""){
                        return '请输入香型';
                      }
                  },
                  commodityPrice: function(value){
                      if(value==null||value==""){
                        return '请输入商品价格';
                      }
                      var zz= /^[1-9]\d*(.\d{1,2})?$/;
                      if(zz.test(value)==false){
                    	  return '请输入正确的价格格式';
                      }
                  },
          });
          
          //监听提交
          form.on('submit(add)', function(data){
        	var elements = $(".layui-form").serialize();
            console.log(elements);
            //发异步，把数据提交给php
            $.ajax({
  	            type:"POST",
   				url:"<%=basePath %>commodityController/addCommodity.do",
   				data:elements,
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

    </script>
</body>
</html>