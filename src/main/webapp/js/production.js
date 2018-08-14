var basePath=$("#basePath").val();
var pn=$("#pno").val();

//获取总页数，总条数
function getPage(pno){
	
	var keyword=$("#keyword").val();
	$.ajax({
		url:'productionController/page.ajax',
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
		url:'productionController/getList.do',
		type:'post',
		typepost:"text",
		async:false,
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
				
				var str="<tr id='"+val.ropId+"'><td>" + val.batchNumber+ "</td><td>" + val.capacityName + "</td><td>" + val.commodityName
				+ "</td><td>" + val.ropUnit+ "瓶</td><td>" + dateStr.Format("yyyy-MM-dd")+ "</td>"
				+ "</td>"
				+"<td class='td-manage'><a title='编辑'onclick=\"x_admin_show('编辑','"+basePath+"productionController/updateUI.do?ropId="+val.ropId+"',600,400)\" href='javascript:;'>"
				+" <i class='layui-icon'>&#xe642;</i></a>"
				+"<a title='删除' onclick=\"member_del('"+val.ropId+"')\" href='javascript:;'>"
				+" <i class='layui-icon'>&#xe640;</i></a>"
				+"<a title='确认生产记录' onclick=\"notarize('"+val.ropId+"')\" href='javascript:;'>"
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
/*生产单-删除*/
function member_del(ropId) {
	layer.confirm('确认要删除吗？', function(index) {
		//发异步删除数据
		$.ajax({
		url:'productionController/del.do',
		type:'post',
		data:{
			'ropId':ropId
			},
		success:function(data){
			if(data=="SUCCESS"){
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
				//location.replace(location.href);
				$("#"+ropId).remove();
				if($("#table tr").length<1){
					pn=pn-1;
					if(pn<1){
						pn=1;
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

function notarize(ropId) {
	layer.confirm('确认生产单吗？', function(index) {
		//发异步删除数据
		$.ajax({
		url:'productionController/notarize.do',
		type:'post',
		data:{
			'ropId':ropId
			},
		success:function(data){
			if(data=="SUCCESS"){
				layer.msg('已记录!', {
					icon : 1,
					time : 500
				});
				//location.replace(location.href);
				$("#"+ropId).remove();
				if($("#table tr").length<1){
					pn=pn-1;
					if(pn<1){
						pn=1;
					}
				}
				getPage(pn);
				showView(pn);
				
			}else{
				if(data=="Material_ERROR"){
					layer.msg('配料有误，请查询!', {
						icon : 1,
						time : 500
					});
				}else{
					layer.msg('记录失败!', {
						icon : 1,
						time : 500
					});
				}
				
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
	    	  batchNumber: function(value,dom){
	        	if(value.length<1){
	        		return '批次号不能为空';
	        	}

	        	return false;
	        },
	        ropUnit: function(value,dom){
	        	var max_num=$("#capacityId option:selected").attr("title");
	        	if(parseInt(value)>parseInt(max_num)){
	        		return '产量不能大于生产线产量';
	        	}
	        	if(value.length<1){
	        		return '产量不能为空！';
	        	}
	        	if(value<0){
	        		return '产量不能负数';
	        	}
	        	
	        	return false;
	        }
	      });
		  });
}