function removeUser(o,dbid){
	var url = __CONTEXT_PATH+"/resource/remove.json?dbid="+dbid;
	$(o).confirmation({animation:true,title:'确定删除吗?',href:'javascript:void(0)',template:$("#confirm_hzh").html(),singleton: false
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
					$('#table-javascript_res').bootstrapTable('refresh',{url:__CONTEXT_PATH+"/resource/list.json"});
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

function updateResource(dbid){
	var url = __CONTEXT_PATH+"/resource/init.html?dbid="+dbid;
	openWinWith(url,0,500,120,750);
};


function openWinWith(url,id,width,top,left){
		var addResourcemodal = $.scojs_modal({
			title:(0 == id ? "新增" : "编辑") + "资源",
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
					addResourcemodal.close();
					$('#table-javascript_res').bootstrapTable('refresh',{url:__CONTEXT_PATH+"/resource/list.json"});
				}
			}
		});
		addResourcemodal.show();
}; 
$(function(){
	$('#table-javascript_res').bootstrapTable({
		method: 'post',
		contentType:"application/x-www-form-urlencoded",
		url:__CONTEXT_PATH+"/resource/list.json",
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
		toolbar:"#custom-toolbar_res",
		columns: [{
            field: 'dbid',
            align: 'right',
            valign: 'middle',
            visible:true
        },{
            field: 'resid',
            title: '资源编号',
            align: 'right',
            valign: 'middle',
        },{
            field: 'resname',
            title: '姓名',
            align: 'right',
            valign: 'middle',
        },{
            field: 'parentid',
            title: '上级编号',
            align: 'right',
            valign: 'middle',
        }/*, {
            field: 'home',
            title: '籍贯',
            align: 'center',
            valign: 'middle',
        }*/,{
        	field: 'url',
            title: '资源地址',
            align: 'center',
            valign: 'middle',
        }/*,{
        	field: 'icon',
            title: '手机号',
            align: 'center',
            valign: 'middle',
        }*/,{
        	field: 'ordernum',
            title: '排序码',
            align: 'center',
            valign: 'middle',
        },{
        	field:'operate',
        	title:'操作',
        	align: 'center',
        	valign: 'middle',
        	formatter:function(value,row,index){
        			return "<a class='t_operate' onclick='updateResource("+row.dbid+")'><i class='icon-edit'></i>编辑</a>"
            	       +"<a class='t_operate' onclick='removeUser(this,"+row.dbid+")'><i class='icon-remove-sign' ></i>删除</a>" 
            },
        }]
	});
	
	
	$("#addResourceBtn").click(function(){
		var url = __CONTEXT_PATH+"/resource/init.html?dbid=0";
		openWinWith(url,0,500,120,750);
	});
	
	
	$("#search").click(function(){
	 	params = encodeURI($("#custom-toolbar_res .form-inline").serialize());
		$('#table-javascript_res').bootstrapTable('refresh',{url:__CONTEXT_PATH+"/resource/list.json?"+params});
	});
});