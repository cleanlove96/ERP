var basePath=$("#basePath").val();
var pn=$("#pno").val();

//获取总页数，总条数
function getPage(pno){
	
	var keyword=$("#keyword").val();
	$.ajax({
		url:'formulaController/page.ajax',
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
		url:'formulaController/getList.do',
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
			
			$.each(jtable, function(n, val1) {
				
				
				var strTd="<td rowspan='"+val1.list.length+"'>" + val1.commodityName + "</td>";
				$.each(val1.list, function(n, val) {
					var dateStr=new Date(val.formulaCreateTime);
					if(n==0){
						var str="<tr id='"+val.formulaId+"'>" +strTd+"<td>" + val.materialName
						+ "</td><td>" + val.formulaCount+ "</td><td>" + dateStr.Format("yyyy-MM-dd ") + "</td>"
						+"<td class='td-manage'><a title='编辑'onclick=\"x_admin_show('编辑','"+basePath+"formulaController/updateUI.do?formulaId="+val.formulaId+"',600,400)\" href='javascript:;'>"
						+" <i class='layui-icon'>&#xe642;</i></a>"
						+"<a title='删除' onclick=\"member_del('"+val.formulaId+"')\" href='javascript:;'>"
						+" <i class='layui-icon'>&#xe640;</i></a></td>" ;
						str+="</tr>";
						$("#table").append(str);
					}else{
						var str="<tr id='"+val.formulaId+"'>" +"<td>" + val.materialName
						+ "</td><td>" + val.formulaCount+ "</td><td>" + dateStr.Format("yyyy-MM-dd ") + "</td>"
						+"<td class='td-manage'><a title='编辑'onclick=\"x_admin_show('编辑','"+basePath+"formulaController/updateUI.do?formulaId="+val.formulaId+"',600,400)\" href='javascript:;'>"
						+" <i class='layui-icon'>&#xe642;</i></a>"
						+"<a title='删除' onclick=\"member_del('"+val.formulaId+"')\" href='javascript:;'>"
						+" <i class='layui-icon'>&#xe640;</i></a></td>" ;
						str+="</tr>";
						$("#table").append(str);
					}
				});
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
function member_del(formulaId) {
	layer.confirm('确认要删除吗？', function(index) {
		//发异步删除数据
		$.ajax({
		url:'formulaController/del.do',
		type:'post',
		data:{
			'formulaId':formulaId
			},
		success:function(data){
			if(data=="SUCCESS"){
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
				//location.replace(location.href);
				$("#"+formulaId).remove();
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
	    	  required1: function(value,dom){
	        	var name=dom.name;
	        	
	        	if(value.length<1){
	        		switch(name){
	        		case 'formulaProductionLineName':return '名称不能为空！';break;
	        		case 'commodityName':return '商品不能为空！';break;
	        		case 'formulaYield':return '数量不能为空！';break;
	        		case 'formulaUnit':return '单位不能为空！';break;
	        		}
	        	}
	        	var regex="^[0-9]+$";
	        	if(dom.name=='formulaYield'&&!value.test(regex)){
	        		return '请输入数字';
	        	}
	        	return false;
	        },
	      formulaYield: function(value,dom){
	        	var name=dom.name;
	        	if(value.length<1){
	        		return '数量不能为空！';
	        	}
	        	var regex="^[0-9]+$";
	        	if(!value.test(regex)){
	        		return '请输入数字';
	        	}
	        	return false;
	        }
	      });
		  });
}