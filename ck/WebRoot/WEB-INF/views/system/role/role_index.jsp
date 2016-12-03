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
	<meta http-equiv="description" content="仓库管理--黄志浩毕业设计 13131404">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<script type="text/javascript" src="<%=commonContextPath%>/js/system/role_index.js"></script>
  </head>
  
  <body>
  	<div id="custom-toolbar_role">
    	<form class="form-inline" role="form">
	        <div class="form-group">
	            <label for="resid_s">角色编号</label>
	            <input type="text" class="form-control" name="roleid" id="roleid_s" placeholder="请输入资源编号">
	        </div>
	        <div class="form-group">
	            <label for="resid_s">角色名称</label>
	            <input type="text" class="form-control" name="rolename" id="rolename_s" placeholder="请输入名称">
	        </div>
        	<button id="searchRole" type="button" class="btn btn-info">搜索</button>
        	<button id="addRoleBtn" type="button" class="btn btn-default">添加角色</button>
	    </form>
	</div>
  	<table id="table-javascript_role">
  	
  	</table>
  </body>
</html>
