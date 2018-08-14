/*用户-删除*/
		function member_del(obj, customerId) {
			layer.confirm('确认要删除吗？', function(id) {
				//发异步删除数据
				$.post('customerController/deleteCustomer.ajax',{id:customerId},function(res){
					if(res=="SUCCESS"){
						$(obj).parents("tr").remove();
						if($("#customer_table tr").length<1){
							pn = pn-1;
						}
						getpage();
						showpage(pn);
						layer.msg('已删除!', {
							icon : 1,
							time : 500
						});
						/*location.replace(location.href);*/
					}else{			
						layer.msg('未删除!', {
						icon : 1,
						time : 500
					});
						
					}
				})
			});
		}
		//ajax 分页查询总数据，以及页数
		var sreach;
		var pn=1;
		$(function() {
			sreach=$("#search").val();
			getpage();
			showpage(pn);
		})
		
		function getpage(){
			$.ajax({
				url : 'customerController/getPage.ajax',
				type : 'POST',
				data : {
					sreach:sreach,
					status:"0"
				},
				dataType : 'text',
				success : function(res) {
					var page = eval("(" + res + ")");
					$("#number_customer").text("共有数据"+page.totalRecords+"条");
					//初始化分页插件
					kkpager.generPageHtml({
						pno : pn,
						mode : 'click', //设置为click模式
						//总页码  
						total : page.total,
						//总数据条数  
						totalRecords : page.totalRecords,
						//点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
						//适用于不刷新页面，比如ajax
						click : function(n) {
							//这里可以做自已的处理
							showpage(n);
							pn = n;
							//处理完后可以手动条用selectPage进行页码选中切换
							this.selectPage(n);
						},
						//getHref是在click模式下链接算法，一般不需要配置，默认代码如下
						getHref : function(n) {
							return '#';
						}

					},true);
				},
				error : function() {

				}
			});
		}
		function showpage(n){
			//显示页面内容
			$.ajax({
				url : 'customerController/getCustomerTable.ajax',
				type : 'POST',
				data : {
					n:n,
					sreach:sreach,
					status:"0"
				},
				dataType : 'text',
				success : function(res) {
					var jtable = eval("(" + res + ")");
					$("#customer_table").html("");
					$.each(jtable, function(n, val) {
						var time = formatDate(val.createTime);
						$("#customer_table").append(
						"<tr><td>" +val.customerName+"</td><td>" +val.customerSex+"</td><td>"+val.customerCompany+ "</td><td>"+val.customerTel+"</td><td>"+val.customerQq+"</td><td>"+val.customerEmail+"</td><td>"+val.customerAddr+"</td><td>"+val.customerDemand+"</td><td>"+time+"</td><td class='td-manage'><a title='编辑' onclick=\"x_admin_show('编辑','customerController/customerEdit.do?customerId="+val.customerId+"',600,500)\" href='javascript:;'> <i class='layui-icon'>&#xe642;</i></a> <a title='删除' onclick=\"member_del(this,'"+val.customerId+"')\" href='javascript:;'> <i class='layui-icon'>&#xe640;</i></a></td></tr>")
					});
				},
				error : function() {

				}
			});
		}
		//搜索
		function doSearch(){
			var s=$("#search").val();
			window.location.href='customerController/doSreach.do?sreach='+s;
		}
		//时间格式转换
		function formatDate(time){
		    var date = new Date(time);

		    var year = date.getFullYear(),
		        month = date.getMonth() + 1,//月份是从0开始的
		        day = date.getDate(),
		        hour = date.getHours(),
		        min = date.getMinutes(),
		        sec = date.getSeconds();
		    var newTime = year + '-' +
		                month + '-' +
		                day + ' ' ;
		              /*   hour + ':' +
		                min + ':' +
		                sec; */
		    return newTime;         
		}
