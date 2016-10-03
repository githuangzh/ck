<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
String jspContextPath = contextPath+"/WEB-INF/views";
String commonContextPath = contextPath+"/resource";
%>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="仓库管理">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<script type="text/javascript" src="<%=commonContextPath%>/js/system/user_add.js"></script>
	<style type="text/css">
		.form-group{
			padding-top: 15px;
		}
		.form-group .control-group input{
		 	width: 300px;
		}
	</style>
  </head>
  <body >
    <div class="container">
        <form class="form-horizontal" id="query_form">
        <input type="hidden" name="dbid" value="${data.dbid}">
            <fieldset>
                <div class="form-group">
                    <label class="col-md-1 control-label">工资:</label>
                    <div class="col-md-5 control-group">
                    	<input type="text" class="form-control" name="pay" placeholder="请输入工资" value="${data.pay}.00">
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
  </body>
  <script type="text/javascript">
  	function submitForm($btn){
		var flag = true;
		var valid = $.scojs_valid('#query_form',{wrapper:'div.control-group',
									rules:{pay:['alpha_numeric']},model:'tooltip',messages:{idcard:{alpha_numeric:'请输入正整数'}}});
		flag =  valid.validate();
		if(flag){
			$.ajax({
				type:'post',
				async:false,
				data:$('#query_form').serializeArray(),
				dataType:'json',
				url:__CONTEXT_PATH+"/user/updatepay.json",
				success:function(data){
					var succ = data.succ;
					var msg = data.msg;
					if(succ){
						$.scojs_message(msg, $.scojs_message.TYPE_OK,2000);
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
  </script>
</html>
