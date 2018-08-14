var basePath=$("#basePath").val();
var pn=$("#pno").val();

//获取总页数，总条数
function getPage(pno){
	
	var keyword=$("#keyword").val();
	$.ajax({
		url:'capacityController/page.ajax',
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
		url:'capacityController/getList.do',
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
				var dateStr=new Date(val.capacityCreationTime);
				
				var str1="<tr id='"+val.capacityId+"'><td>" + val.capacityProductionLineName + "</td><td>" + val.commodityName
				+ "</td><td>" + val.odorType +val.commodityType 
				+ "</td><td>" + val.capacityYield+ "</td><td>" + val.capacityUnit+ "</td><td>" + dateStr.Format("yyyy-MM-dd ") + "</td>"
				+"<td class='td-manage'><a title='编辑'onclick=\"x_admin_show('编辑','"+basePath+"capacityController/updateUI.do?capacityId="+val.capacityId+"',600,400)\" href='javascript:;'>"
				+" <i class='layui-icon'>&#xe642;</i></a>"
				+"<a title='删除' onclick=\"member_del('"+val.capacityId+"')\" href='javascript:;'>"
				+" <i class='layui-icon'>&#xe640;</i></a></td></tr>";
				var str2="<tr id='"+val.capacityId+"'><td><input type='checkbox' name='ids' value='"+val.capacityId+"'></input></td><td>" +basePath+ val.capacityName + "</td><td>" + val.capacityDesc
				+ "</td><td>" + val.createDate+ "</td>"
				+"<td class='td-manage'><a title='编辑'onclick=\"x_admin_show('编辑','"+basePath+"capacityController/updateUI.do?capacityId="+val.capacityId+"',600,400)\" href='javascript:;'>"
				+" <i class='layui-icon'>&#xe642;</i></a>"
				+"<a title='删除' onclick=member_del('"+val.capacityId+"') href='javascript:;'>"
				+" <i class='layui-icon'>&#xe640;</i></a></td></tr>";
				$("#table").append(str1);
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
/*删除*/
function member_del(capacityId) {
	layer.confirm('确认要删除吗？', function(index) {
		//发异步删除数据
		$.ajax({
		url:'capacityController/del.do',
		type:'post',
		data:{
			'capacityId':capacityId
			},
		success:function(data){
			if(data=="SUCCESS"){
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
				//location.replace(location.href);
				$("#"+capacityId).remove();
				if($("#table tr").length<1){
					pn=pn-1;
					if(pn<1){
						pn=0;
					}
				}
				getPage(pn);
				showView(pn);
				
			}else{
				if(data=="DataException_ERROR"){
					layer.msg('此项含关联项，无法删除!', {
						icon : 1,
						time : 500
					});
				}else{
					layer.msg('删除失败!', {
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
	    	  capacityProductionLineName: function(value,dom){
		        	if(value.length==0){
			        		return '名称不能为空！';
		        		}
		        	return false;
		        },
		        capacityYield: function(value,dom){
		        	if(value.length==0){
			        		return '产量不能为空！';
		        		}
		        	if(value<0){
		        		return '请不要输入负数';
		        	}
		        	if(value>2500){
		        		return '产量过大产量，请符合逻辑';
		        	}
		        	return false;
		        },
		        capacityUnit: function(value,dom){
		        	if(value.length==0){
		        		return '单位不能为空！';
	        		}
		        	var regex=/[\u4e00-\u9fa5]/;
		        	if(!regex.test(value)){
		        		return '请输入中文';
		        	}
		        	return false;
		        }
	      });
		  });
}