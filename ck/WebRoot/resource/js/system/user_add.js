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
								rules:{userid:['not_empty'],
								'user.username':['not_empty'],
								phone:['not_empty','digit'],
								idcard:['not_empty',{regex:/^(\d{18,18}|\d{15,15}|\d{17,17}x)$/}],
								email:['email']
								},model:'tooltip',messages:{idcard:{regex:'身份证格式不正确'}}});
	flag =  valid.validate();
	if(flag){
		$.ajax({
			type:'post',
			async:false,
			data:$('#query_form').serializeArray(),
			dataType:'json',
			url:__CONTEXT_PATH+"/user/add.json",
			success:function(data){
				var code = data.statusCode;
				var msg = data.msg;
				if(code == 200){
					$.scojs_message(msg, $.scojs_message.TYPE_OK,2000);
				}else if(code == 300){
					return flag;
				}else if(code == 40101){
					var text = data.data.idcard;
					Mypopover("#idcard_edit",text,'top',false);
					$("#idcard_edit").focus(function(){
						$("#idcard_edit").popover('destroy');
					});
					flag = false;
				}else if(code == 40102){
					var text = data.data.userid;
					Mypopover("#userid_edit",text,'top',false);
					$("#userid_edit").focus(function(){
						$("#userid_edit").popover('destroy');
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