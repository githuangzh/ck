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
  </head>
  <style>
  	.container{
  		width: 100%;
  		padding-top: 12px;
  	}
  	.popover.top>.arrow:after{
		border-top-color:#F5C3BF;
	}
	.popover{
		font-size:12px;
		line-height:10px;
		text-align:center;
		height: 30px;
		width: 200px;
		box-shadow:none;
		-webkit-box-shadow:none;
		border-radius:0px;
	}
  </style>
  <body >
    <div class="container">
        <form class="form-horizontal" id="query_form">
        <input type="hidden" name="dbid" value="${data.dbid}">
            <fieldset>
                <div class="form-group">
                    <label class="col-md-1 control-label">姓名:</label>
                    <div class="col-md-5 control-group">
                        <input type="text" class="form-control" name="user.username"  placeholder="请输入姓名" value="${data.user.username}">
                    </div>
                    <label class="col-md-1 control-label">账号:</label>
                    <div class="col-md-5 control-group">
                    	<input type="text" class="form-control" name="userid" id="userid_edit" placeholder="请输入账号" value="${data.userid}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-1 control-label">性别:</label>
                    <div class="col-md-5 control-group">
                        <span class="col-md-2"><input type="radio" name="gender" id="gender" value="1" checked="checked">&nbsp;男</span>
                        <span class="col-md-2"><input type="radio" name="gender" id="gender" value="0">&nbsp;女</span>
                    </div>
                    <label class="col-md-1 control-label">籍贯:</label>
                    <div class="col-md-5 control-group">
                        <input type="text" class="form-control" name="home" placeholder="请输入籍贯" value="${data.home}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-1 control-label">手机号:</label>
                    <div class="col-md-5 control-group">
                        <input type="text" class="form-control" name="phone" placeholder="请输入手机号" value="${data.phone}">
                    </div>
                    <label class="col-md-1 control-label">QQ:</label>
                    <div class="col-md-5 control-group">
                        <input type="text" class="form-control" name="qq" placeholder="请输入QQ" value="${data.qq}">
                    </div>
                </div>
                <div class="form-group">
                	<label class="col-md-1 control-label">身份证:</label>
                    <div class="col-md-5 control-group">
                        <input type="text" class="form-control" name="idcard" id="idcard_edit" placeholder="请输入身份证" value="${data.idcard}">
                    </div>
                    <label class="col-md-1 control-label">E-mail:</label>
                    <div class="col-md-5 control-group">
                        <input type="text" class="form-control" name="email" placeholder="请输入邮箱" value="${data.email}">
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
  </body>
</html>
