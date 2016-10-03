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
	<script type="text/javascript" src="<%=commonContextPath%>/js/system/res_add.js"></script>
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
        <input type="hidden" id="dbid_hid" name="dbid" value="${data.dbid}">
        <input type="hidden" id="icon_hid" name="icon" value="${data.icon}">
            <fieldset>
                <div class="form-group">
                    <label class="col-md-3 control-label">资源编号:</label>
                    <div class="col-md-9 control-group">
                        <input type="text" class="form-control" id="resid_edit" name="resid"  placeholder="请输入资源编号" value="${data.resid }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">资源名称:</label>
                    <div class="col-md-9 control-group">
                        <input type="text" class="form-control" id="resname_edit" name="resname"  placeholder="请输入资源编号" value="${data.resname }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">上级编号:</label>
                    <div class="col-md-9 control-group">
                    	<input type="hidden" id="parentid_hid" value="${data.parentid }">
                        <select class="form-control" name="parentid" id="parentid" >
     					</select>
                    </div>
                </div>
                <div class="form-group">
                	<label class="col-md-3 control-label">资源地址:</label>
                    <div class="col-md-9 control-group">
                        <input type="text" class="form-control" name="url" id="idcard_edit" placeholder="请输入资源地址" value="${data.url }">
                    </div>
                </div>
                <div class="form-group">
                	<label class="col-md-3 control-label">排序码:</label>
                    <div class="col-md-9 control-group">
                        <input type="text" class="form-control" name="ordernum" id="idcard_edit" placeholder="请输入排序码" value="${data.ordernum }">
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
  </body>
</html>
