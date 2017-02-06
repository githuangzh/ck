<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
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
	<script type="text/javascript" src="<%=commonContextPath%>/js/system/role_auth.js"></script>
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
        
        </form>
    </div>
  </body>
</html>
