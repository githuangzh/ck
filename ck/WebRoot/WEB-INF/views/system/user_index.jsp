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
	<script type="text/javascript" src="<%=commonContextPath%>/js/system/user_index.js"></script>
  </head>
  
  <body>
  	<div id="custom-toolbar_user">
    	<form class="form-inline" role="form">
	        <div class="form-group">
	            <label for="userid_s">账号</label>
	            <input type="text" class="form-control" name="userid" id="userid_s" placeholder="请输入账号">
	        </div>
	        <div class="form-group">
	            <label for="username_s">姓名</label>
	            <input type="text" class="form-control" name="user.username" id="username_s" placeholder="请输入姓名">
	        </div>
        	<button id="searchUser" type="button" class="btn btn-info">搜索</button>
        	<button id="addUserBtn" type="button" class="btn btn-default">添加用户</button>
	    </form>
	</div>
  	<table id="table-javascript_user">
  	
  	</table>
  </body>
</html>
