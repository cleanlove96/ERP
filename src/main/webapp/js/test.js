var basePath=$("#basePath").val();
var pn=$("#pno").val();

//获取总页数，总条数
function getPage(pno){
	
	var keyword=$("#keyword").val();
	$.ajax({
		url:'qualityTestController/page.ajax',
		type:'post',
		data:{
			keyword:keyword,
			pageRecords:'5'
			},
		success:function(data){
			var page=eval("("+data+")");
			$("#num").html(page.totalRecords)
			$("#pno").val(pno);
			pn=$("#pno").val();
			/*分页控件设置*/
			kkpager.generPageHtml({
				//当前页数
				pno : pno,
				mode : 'click', //可选，默认就是link
				//总页码  
				total : page.total,
				//总数据条数  
				totalRecords : page.totalRecords,
				
				click : function(n) {
					//这里可以做自已的处理
					showView(n);
					$("#pno").val(n);
					pn=$("#pno").val();
					//处理完后可以手动条用selectPage进行页码选中切换
					this.selectPage(n);
				},
				//getHref是在click模式下链接算法，一般不需要配置，默认代码如下
				getHref : function(n) {
					return '#';
				}

			},true);
		},
		error:function(data){
			
		}
	});
}
//显示表单
function showView(pno){
	
	var keyword=$("#keyword").val();
	$.ajax({
		url:'qualityTestController/getList.do',
		type:'post',
		typepost:"text",
		data:{
			pno:pno,
			pageRecords:'5',
			keyword:keyword
			},
		success:function(data){
			var jtable = eval("(" + data + ")");
			$("#table").html("");
			$.each(jtable, function(n, val) {
				var dateStr=new Date(val.ropProductionTime);
				var timeStr=new Date(val.ropWarehouseEntryTime).Format("yyyy-MM-dd");
				var ropLoss=val.ropLoss+"瓶";
				var ropIntoWarehouse=val.ropIntoWarehouse+"瓶";
				if(val.ropLoss<0){
					ropLoss="待填写";
					ropIntoWarehouse="待填写";
					timeStr="待填写";
				}
				var str="<tr id='"+val.ropId+"'><td>" + val.batchNumber+ "</td><td>" + val.capacityName + "</td><td>" + val.commodityName
				+ "</td><td>" + val.ropUnit+ "瓶</td><td>" + ropLoss+ "</td><td>" + ropIntoWarehouse+ "</td><td>" + dateStr.Format("yyyy-MM-dd")+ "</td>"
				+"<td>" + timeStr+ "</td>"
				+"<td class='td-manage'><a title='编辑'onclick=\"x_admin_show('编辑','"+basePath+"qualityTestController/updateUI.do?ropId="+val.ropId+"',600,400)\" href='javascript:;'>"
				+" <i class='layui-icon'>&#xe642;</i></a>"
				+"<a title='确认质检' onclick=\"member_del('"+val.ropId+"')\" href='javascript:;'>"
				+" <i class='layui-icon'>&#xe601;</i></a></td></tr>";
				
				$("#table").append(str);
			}); 
			
		},
		error:function(data){
		}
	});
}

function suaxin(){
	var pno=$("#pno").val();
	getPage(pno);
	showView(pno);
}
/*仓库-删除*/
function member_del(ropId) {
	layer.confirm('确认完成质检吗？', function(index) {
		//发异步删除数据
		$.ajax({
		url:'qualityTestController/del.do',
		type:'post',
		data:{
			'ropId':ropId
			},
		success:function(data){
			if(data=="SUCCESS"){
				layer.msg('已质检!', {
					icon : 1,
					time : 500
				});
				//location.replace(location.href);
				$("#"+ropId).remove();
				if($("#table tr").length<1){
					pn=pn-1;
					if(pn<1){
						pn=0;
					}
				}
				getPage(pn);
				showView(pn);
				
			}else{
				layer.msg('删除失败!', {
					icon : 1,
					time : 500
				});
			}
			
		},
		error:function(data){
			layer.msg('访问出错!', {
				icon : 1,
				time : 500
			});
		}
	});
		
	});
}
//表单验证
function verify(){
	layui.use(['form','layer'], function(){
	      $ = layui.jquery;
	      var form = layui.form,layer = layui.layer;

	      //自定义验证规则
	      form.verify({
	    	  ropLoss: function(value,dom){
		        	if(value.length<1){
		        		return '损耗量不能为空！';
		        	}
		        	if(value<0){
		        		return '损耗量不能负数';
		        	}
		        	return false;
		        },
		      ropIntoWarehouse: function(value,dom){
	        	if(value.length<1){
	        		return '损耗量不能为空！';
	        	}
	        	if(value<0){
	        		return '损耗量不能大于产量';
	        	}
	        	return false;
	        }
		        
	      });
		  });
}