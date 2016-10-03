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
	<script type="text/javascript" src="<%=commonContextPath%>/js/system/resource_index.js"></script>
  </head>
  
  <body>
  	<div id="custom-toolbar_res">
    	<form class="form-inline" role="form">
	        <div class="form-group">
	            <label for="resid_s">资源编号</label>
	            <input type="text" class="form-control" name="resid" id="resid_s" placeholder="请输入资源编号">
	        </div>
	        <div class="form-group">
	            <label for="resname_s">资源名称</label>
	            <input type="text" class="form-control" name="resname" id="resname_s" placeholder="请输入名称">
	        </div>
        	<button id="search" type="button" class="btn btn-info">搜索</button>
        	<button id="addResourceBtn" type="button" class="btn btn-default">添加资源</button>
	    </form>
	</div>
  	<table id="table-javascript_res">
  	
  	</table>
  </body>
</html>
