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
        <input type="hidden" name="status" value="${data.status}">
            <fieldset>
                <div class="form-group">
                    <label class="col-md-3 control-label">账号:</label>
                    <div class="col-md-8 control-group">
                    	<input type="text" class="form-control" name="userid" id="userid_edit" placeholder="请输入账号" value="${data.userid}">
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
  </body>
</html>
