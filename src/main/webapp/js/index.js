/**
 * 
 */

$(function(){
	$.ajax({
		url:'indexController/getAccountLoginId.ajax',
		type:'POST',
		data:{
			
		},
		dataType:'text',
		success:function(result){
			if(result=="ERROR"){
				$("#accountLoginId").text("无法获取");
			}else{
				$("#accountLoginId").text("["+result+"]");
			}
		},
		error:function(){
			$("#accountLoginId").text("无法获取");
		}
	});
});

