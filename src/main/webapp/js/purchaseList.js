var searchCard;
	var pn=1;
	var myStartTime1;
	var myStartTime2;
	var myEndTime1;
	var myEndTime2;
	//转换时间
	 Date.prototype.Format = function (fmt) {
		    var o = {
		        "M+": this.getMonth() + 1, //月份 
		        "d+": this.getDate(), //日 
		        "h+": this.getHours(), //小时 
		        "m+": this.getMinutes(), //分 
		        "s+": this.getSeconds(), //秒 
		        "S": this.getMilliseconds() //毫秒 
		    };
		    if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o){
		    if (new RegExp("(" + k + ")").test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
		    }
		    return fmt;
		} 
	//设计时间格式
	 layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#startTime' //指定元素
				
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#endTime' //指定元素
			});
		});
	 layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#myStartTime1' //指定元素
				
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#myEndTime1' //指定元素
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#myStartTime2' //指定元素
				
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#myEndTime2' //指定元素
			});
		});
	function doSearchCard(){
		var s = $("#searchCard").val();
		myStartTime1 = $("#myStartTime1").val();
		myEndTime1 = $("#myEndTime1").val();
		myStartTime2 = $("#myStartTime2").val();
		myEndTime2 = $("#myEndTime2").val();
		window.location.href = 'purchaseController/doSearchCard.do?searchCard='+ s+'&myStartTime1='+myStartTime1+'&myEndTime1='+myEndTime1+'&myStartTime2='+myStartTime2+'&myEndTime2='+myEndTime2;	
	}
	function resetAll(){
		$("#myStartTime1").val("");
		$("#myEndTime1").val("");
		$("#myStartTime2").val("");
		$("#myEndTime2").val("");
		doSearchCard();
	}
	$(function(){
		searchCard= $("#searchCard").val();
		myStartTime1 = $("#myStartTime1").val();
		myEndTime1 = $("#myEndTime1").val();
		myStartTime2 = $("#myStartTime2").val();
		myEndTime2 = $("#myEndTime2").val();
		selectPurchase();
		getpage();
		showPurchasepage(pn);
		//查询出计划采购计划表的所有信息
	})
	
	/*所需物料信息*/
	function selectPurchase(){
		$.ajax({
			url : 'purchaseController/selectAllNeed.ajax',
			type : 'POST',
			async:false, 
			data : {
				searchCard : searchCard			
			},
			dataType : 'text',
			success : function(res) {
				var myPurchaseTable = eval("(" + res + ")");
				$.each(myPurchaseTable,function(n, val) {
					
				$("#myPurchase").append("<tr><td>"+val.materialName+"</td><td>"+val.materialNum+"</td><td>"+val.materialUnit+"</td><td>"+val.materialPrice+"</td><td>"+val.materialTotalPrices+"</td><td>"+val.materialNotPlan+"</td></tr>");
				$("#materialId").append("<option value='"+val.materialId+"'>"+val.materialName+"</option>");
				});
				
			},
			error : function() {

			}

		});
	}
	//直接加载原料对应的单位
	
	
	layui.use([ 'form', 'layer' ], function() {
	$ = layui.jquery;
	var form = layui.form,
	layer = layui.layer;
	form.on('select(select)',function(data){
		var myUnitIs=$("#materialId").val();
		$.ajax({
			url : 'purchaseController/selectUnit.ajax',
			type : 'POST',
			async:false, 
			data : {
				myUnitIs : myUnitIs
			},
			dataType : 'text',
			success : function(res) {
				console.log(res);
				var j = eval("(" + res + ")");
				$("#purchaseUnit").val(j.materialUnit);
				$("#price").val(j.price);
				
			},
			error : function() {

			}
		});
	});
	});

//增加操作
layui.use(['form','layer'], function(){
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
		        	  //
		    var materialId=$("#materialId").val();
		    var purchaseCount=$("#purchaseCount").val();
		    var purchaseUnit=$("#purchaseUnit").val();
		    var purchaseTime=$("#purchaseTime").val();
		    var startTime=$("#startTime").val();
		    var endTime=$("#endTime").val();
		    var price=$("#price").val();
		  
			$.ajax({
				url :'purchaseController/myPurchaseAdd.ajax',
				type:'POST',
				data:{
					materialId:materialId,
					purchaseCount:purchaseCount,
					purchaseUnit:purchaseUnit,
					purchaseTime:purchaseTime,
					startTime:startTime,
					endTime:endTime,
					price:price
				},
				dataType : 'text',
				success : function(res) {
					if(res=="success"){
						layer.alert("增加成功", {
							
							icon : 6 //icon : 6定义弹框样式
						},doSearchCard()
						
						/*调用下面分采购计划表的方法*/
								
						);
					}	
				},
				error : function() {
					layer.alert("添加失败", {
						icon : 6
					});
				},
						
			});	
			return false;
		});

		})
	//查询所有分计划表和分页
		
		/*计划表-删除*/
		function member_del(obj, PurchaseId) {
			layer.confirm('确认要删除吗？', function(id) {
				$.ajax({
					url : 'purchaseController/deletePurchaseById.ajax',
					type : 'POST',
					
					data : {
						id:PurchaseId
					},
					dataType : 'text',
					success : function(res) {
						if(res=="success"){
							$(obj).parents("tr").remove();
							
							if($("#yearplanTable tr").length<1){
								pn = pn-1;
							}
							getpage();
							showPurchasepage(pn);
							doSearchCard();
							layer.msg('已删除!', {
								icon : 1,
								time : 500
								
							});
							
						}else{
							layer.msg('未删除!', {
								icon : 1,
								time : 500
							});
						}
					},
					error : function() {
						layer.msg('未删除!', {
							icon : 1,
							time : 500
					});
					}		
				});
				
			});
		}
	
	function getpage() {
		
		$.ajax({
			url : 'purchaseController/getPage.ajax',
			type : 'POST',
			data : {
				searchCard:searchCard,
				myStartTime1:myStartTime1,
				myEndTime1:myEndTime1,
				myStartTime2:myStartTime2,
				myEndTime2:myEndTime2
			},
			dataType : 'text',
			success : function(res) {
				var page = eval("(" + res + ")");
				$("#count_num").text("共有数据:" + page.totalRecords + "条");
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
						showPurchasepage(n);
						//处理完后可以手动条用selectPage进行页码选中切换
						pn=n;
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

	function showPurchasepage(n) {
		$.ajax({
		 	url : 'purchaseController/getPurchaseTable.ajax',
			type : 'POST',
			data : {
			n:n,
			searchCard:searchCard,
			myStartTime1:myStartTime1,
			myEndTime1:myEndTime1,
			myStartTime2:myStartTime2,
			myEndTime2:myEndTime2
			},
			dataType : 'text',
			success : function(res) {
				var purchasess = eval("(" + res + ")");
				
				$("#puechaseTable").html("");
				$.each(purchasess,function(n, val) {
					var naem=new Date(val.startTime);
					var naemm=new Date(val.endTime);
				$("#puechaseTable").append("<tr><td>"+val.materialId+"</td><td>"+val.purchaseCount+"</td><td>"+val.purchaseUnit+"</td><td>"+val.price+"</td><td>"+val.purchaseTotalPrices+"</td><td>"+naem.Format("yyyy-MM-dd")+"</td><td>"+naemm.Format("yyyy-MM-dd")+"</td><td class='td-manage'> <a onclick=\"x_admin_show('修改计划表','purchaseController/updatePurchaseTable.do?purchaseId="+val.purchaseId+"',600,400)\" title='修改计划表' href='javascript:;'> <i class='layui-icon'>&#xe631;</i></a> <a title='删除' onclick=\"member_del(this,'"+val.purchaseId+"')\"  href='javascript:;'> <i class='layui-icon'>&#xe640;</i> </a></td></tr>");
						});	
					},
			error : function() {

					}
		});

	}
	//查询出所有计划好的原材料
	function selectPurchaseAll() {
		$.ajax({
			url : 'purchaseController/selectPurchaseAll.ajax',
			type : 'POST',
			async:false, 
			data : {
				searchCard : searchCard
			},
			dataType : 'text',
			success : function(res) {
				var myPurchaseTable = eval("(" + res + ")");
				$.each(myPurchaseTable,function(n, val) {
					
				$("#myPurchaseAll").append("<tr><td>"+val.materialName+"</td><td>"+val.materialNum+"</td><td>"+val.materialUnit+"</td><td>"+val.materialPrice+"</td><td>"+val.materialTotalPrices+"</td><td>未完成</td></tr>");
				
				});
				
			},
			error : function() {

			}

		});
	}
	
