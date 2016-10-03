<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/resource/include/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="仓库管理">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/login.css">
  </head>
  <body>
    <div class="container-fluid">
        <div class="log-panel col-md-3 col-md-offset-5">
            <div class="log-title">Sign In</div>
            <div class="log-body">
                <form role="form" id="form">
                    <fieldset>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="icon-group"></i></span>
                                <input class="form-control" type="text" name="userid" id="userid" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class=input-group-addon><i class="icon-key"></i></span>
                                <input class="form-control" type="password" name="password" id="password" placeholder="请输入密码">
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="log-footer">
            	<input type="button" class="btn" id="submitbtn" value="登录">
                <!-- <button class="btn" id="submitbtn"></button> -->
                <div class="footertext2">
                    <a href="javascript:void(0)">注册新用户</a>
                </div>
                <div class="footertext">
                    <a href="javascript:void(0)">忘记密码?</a>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	function Mypopover(id,content,placement,isAnimation){
    		$(id).popover({
						animation:false,
						content:content,
						placement:placement,
						template:'<div class="popover" role="tooltip" style="background-color:#F5C3BF"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
					});
			$(id).popover('show');
    	}
		$("#submitbtn").click(function(){
			var $btn = $(this).button('loading');
			$.post(__CONTEXT_PATH+"/profile/auth.json",$("#form").serialize(),function(data){
				if(data.statusCode == 200){
					window.location.replace("<c:url value='/profile/admin.html'/>");
				}
				if(data.statusCode == 101){
					Mypopover("#userid",data.msg,'top',false);
					$("#userid").focus(function(){
						$("#userid").popover('destroy');
					});
					
				}
				if(data.statusCode == 102){
					Mypopover("#password",data.msg,'top',false);
					$("#password").focus(function(){
						$("#password").popover('destroy');
					});
				}
			});
			$btn.button('reset');
		});
	</script>
  </body>
</html>
