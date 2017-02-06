function updateStatus(o,dbid,status){
	var url = __CONTEXT_PATH+"/user/status.json?dbid="+dbid;
	var str;
	if(status){
		str='启用';
	}else{
		str='禁用';
	}
	$(o).confirmation({animation:true,title:'确定'+str+'吗?',href:'javascript:void(0)',template:$("#confirm_hzh").html(),singleton: false
		, popout: false
		, onConfirm: function(){}
		, onCancel: function(){}});
	$(o).confirmation('toggle');
	$('[data-dismiss="confirmation"]').click(function(){
		$(this).parent().parent().parent().hide();
	});
	
	$('[target="confirm_y"]').click(function(){
		$.ajax({
			type:'post',
			url:url,
			success:function(data){
				if(data.succ){
					$.scojs_message(data.msg, $.scojs_message.TYPE_OK,2000);
					$('#table-javascript_user').bootstrapTable('refresh',{url:__CONTEXT_PATH+"/user/list.json"});
				}else{
					$.scojs_message(data.msg, $.scojs_message.TYPE_ERROR,2000);
				}
			},
			error:function(){
				$.scojs_message('请检查网络问题', $.scojs_message.TYPE_ERROR,2000);
			}
		});
	})
}

function updateUser(dbid){
	var url = __CONTEXT_PATH+"/user/init.html?dbid="+dbid;
	openWinWith(url,1,460,220,700);
};
function updatepwd(dbid){
	var url = __CONTEXT_PATH+"/user/updatepwd.html?dbid="+dbid; 
	openWinWith(url,0,460,220,700);
}

function openWinWith(url,id,width,top,left){
		var addUsermodal = $.scojs_modal({
			title:(0 == id ? "新增" : "编辑") + "用户",
			target:"#modal_hzh",
			remote:url,
			top:top,
			width:width,
			left:left,
			onSubmit:function(){
				var $btn = $(this).button('loading');
				var iframe = $("#modal_hzh .inner").html();
				if(iframe == ''){
					$.scojs_message('正在加载，请稍候...', $.scojs_message.TYPE_ERROR,2000);
					return false;
				}
				if(submitForm($btn)){
					addUsermodal.close();
					$('#table-javascript_user').bootstrapTable('refresh',{url:__CONTEXT_PATH+"/user/list.json"});
				}
			}
		});
		addUsermodal.show();
}; 
$(function(){
	$('#table-javascript_user').bootstrapTable({
		method: 'post',
		contentType:"application/x-www-form-urlencoded",
		url:__CONTEXT_PATH+"/user/list.json",
		cache:false,
		striped:true,
		pagination:true,
		pageSize:5,
		height:500,
		pageList:[1,2,5,10,20],
		sidePagination:"server",
		showSearch:true,
		showColumns:true,
		showRefresh:true,
		showToggle:true,
		minimumCountColumns:1,
		idField:"dbid",
		toolbar:"#custom-toolbar_user",
		columns: [{
            field: 'dbid',
            align: 'right',
            valign: 'middle',
            visible:true
        },{
            field: 'userid',
            title: '账号',
            align: 'right',
            valign: 'middle',
        },{
            field: 'staffid',
            title: '关联工号',
            align: 'right',
            valign: 'middle',
        },/*{
            field: 'gender',
            title: '性别',
            align: 'right',
            valign: 'middle',
            sortable: true,
            formatter:function(value,row,index){
            	if(value == 1){
            		return '男';
            	}else{
            		return '女';
            	}
            },
            sorter:function(a,b){
            	if(b > a){
            		return b;
            	}else{
            		return a;
            	}
            }
        }*/{
        	field: 'status',
            title: '状态',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter:function(value,row,index){
            	if(value == 1){
            		return '<font color="red">禁用</font>';
            	}else{
            		return '<font color="green">启用</font>';
            	}
            },
            sorter:function(a,b){
            	if(b > a){
            		return b;
            	}else{
            		return a;
            	}
            }
        },{
        	field:'operate',
        	title:'操作',
        	align: 'center',
        	valign: 'middle',
        	formatter:function(value,row,index){
        		if(row.userid == 'huangzh'){
        			return "";
        		}else{
        			if(row.status == 1){
        				return "<a class='t_operate' onclick='updateUser("+row.dbid+")'><i class='icon-edit'></i>编辑</a>"
        				+"<a class='t_operate' onclick='updatepwd("+row.dbid+")'><i class='icon-credit-card'></i>修改密码</a>"
             	       +"<a class='t_operate' onclick='updateStatus(this,"+row.dbid+","+row.status+")'><i class='icon-remove-sign' ></i>启用</a>" ;
        			}else{
        				return "<a class='t_operate' onclick='updateUser("+row.dbid+")'><i class='icon-edit'></i>编辑</a>"
        				+"<a class='t_operate' onclick='updatepwd("+row.dbid+")'><i class='icon-credit-card'></i>修改密码</a>"
              	       +"<a class='t_operate' onclick='updateStatus(this,"+row.dbid+","+row.status+")'><i class='icon-remove-sign' ></i>禁用</a>" ;
        			}
        			
        		}
            	
            },
        }]
	});
	
	
	$("#addUserBtn").click(function(){
		var url = __CONTEXT_PATH+"/user/init.html?dbid=0";
		openWinWith(url,0,460,220,700);
	});
	
	
	$("#searchUser").click(function(){
	 	params = encodeURI($("#custom-toolbar_user .form-inline").serialize());
		$('#table-javascript_user').bootstrapTable('refresh',{url:__CONTEXT_PATH+"/user/list.json?"+params});
	});
});