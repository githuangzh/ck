function Mypopover(id,content,placement,isAnimation){
		$(id).popover({
					animation:false,
					content:content,
					placement:placement,
					template:'<div class="popover" role="tooltip" style="background-color:#F5C3BF"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
			});
	$(id).popover('show');
}
function submitForm($btn){
	var flag = true;
	var valid = $.scojs_valid('#query_form',{wrapper:'div.control-group',
								rules:{resid:['not_empty'],
								resname:['not_empty'],
								parentid:['not_empty']
								/*,url:['url']*/
								},model:'tooltip',messages:{parentid:{'not_empty':'请选择上级编号'}}});
	flag =  valid.validate();
	if(flag){
		$.ajax({
			type:'post',
			async:false,
			data:$('#query_form').serializeArray(),
			dataType:'json',
			url:__CONTEXT_PATH+"/resource/add.json",
			success:function(data){
				var code = data.statusCode;
				var msg = data.msg;
				if(code == 200){
					$.scojs_message(msg, $.scojs_message.TYPE_OK,2000);
				}else if(code == 300){
					return flag;
				}else if(code == 40101){
					var text = data.data.resid;
					Mypopover("#resid_edit",text,'top',false);
					$("#resid_edit").focus(function(){
						$("#resid_edit").popover('destroy');
					});
					flag = false;
				}else if(code == 40102){
					var text = data.data.resname;
					Mypopover("#resname_edit",text,'top',false);
					$("#resname_edit").focus(function(){
						$("#resname_edit").popover('destroy');
					});
					flag = false;
				}else{
					$.scojs_message(msg, $.scojs_message.TYPE_ERROR,2000);
				}
			},
			error:function(){
				$.scojs_message('请检查网络问题', $.scojs_message.TYPE_ERROR,2000);
			}
		});
	}
	setTimeout(function(){
		$btn.button('reset');
	},20);
	return flag;
}
$(function(){
	$.ajax({
		type:'post',
		async:false,
		dataType:'json',
		url:__CONTEXT_PATH+'/resource/topid.json',
		success:function(data){
			var code = data.statusCode;
			var obj = data.data.toString();
			if(code == 200){
				$("#parentid").html("");
				$("#parentid").append("<option value=''>请选择上级编号</option> ");
				var list = obj.split(',');
				for(var i = 0;i<list.length;i++){
					$("#parentid").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
			}
		},
		error:function(){
			$.scojs_message('请检查网络问题', $.scojs_message.TYPE_ERROR,2000);
		}
	});
	var pid_hid = $("#parentid_hid").val();
	var dbid_hid = $("#dbid_hid").val();
	if(pid_hid == "" && dbid_hid != 0){
		$("#parentid").attr("disabled","disabled");
	}
	if(pid_hid != ""){
		$("#parentid").val(pid_hid);
	}
})
