<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.will.bussiness.system.entity.*"%>
<%@page import="org.springframework.util.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/resource/include/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String, ArrayList<SysResource>> rlist = (Map<String, ArrayList<SysResource>>)request.getSession().getAttribute("rlist");
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
	<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/style.css">
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="javascript:void(0)">AdminCk</a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="javascript:void(0)"><i class="icon-envelope"></i><span class="badge"></span></a></li>
                <li><a href="javascript:void(0)"><i class="icon-bell"></i></a></li>
                <li><a href="<%=contextPath%>/profile/exit.html"><i class="icon-signin"></i></a></li>
                <li><a href="javascript:void(0)">Login</a></li>
            </ul>
        </div>
    </nav>
    <div class="Main">
        <div class="NavCol">
            <div class="sidebar-menu">
            	<%
            		ArrayList<SysResource> list = rlist.get("root");
            		for (SysResource sysResource : list){
            			String resid = sysResource.getResid();
            			String icon = sysResource.getIcon();
            			String resname = sysResource.getResname();
            	%>		            			
           			<div href="#res_<%=resid %>" class="nav nav-header menu-first collapsed" data-toggle="collapse" >
                   		<i class="<%=icon %>"></i> <%=resname %>
               		</div>
               		<ul id="res_<%=resid %>" class="nav nav-list collapse menu-second">
            	<%	
            			ArrayList<SysResource> sub = rlist.get(resid);
            			for (SysResource subRes : sub){
            				String subid = subRes.getResid();
            				String subicon = subRes.getIcon();
            				String subname = subRes.getResname();
            				String suburl = subRes.getUrl();
            				if(!StringUtils.hasText(suburl))
		    				{
		    					suburl = "";
		    				}
		    				else
		    				{
		    					suburl = path +"/"+suburl;
		    				}
            	%>
            	
            		<li><a href="javascript:void(0)" url="<%=suburl %>" id="res_list<%=subid %>" content="<%=subname %>"><i class="<%=subicon %>"></i> <%=subname %></a></li>	
     			<%
            			}
            	%>
            		</ul>
            	<%		
            		}
            	 %>
            </div>
        </div>
		
        <div class="NavContent">
			  <!-- Tab panes -->
			  <ul class="nav nav-pills" id="navigations">
				  <li><a href="#">首页</a></li>
			 </ul>
			 <div class="pane-wrapper" id="nav-pane">
			   <div title="欢迎使用" class="tab-content">
	                <h1>欢迎使用仓库管理系统!</h1>
	           </div>
			 </div>
			 <!--  <ul class="nav nav-pills" role="tablist" id="navigations">
			    <li role="presentation" class="active"><a role="tab" >Home</a></li>
			    <li role="presentation"><a role="tab" >Profile</a></li>
			    <li role="presentation"><a role="tab" >Messages</a></li>
			    <li role="presentation"><a role="tab" >Settings</a></li>
		 	  </ul>
			  <div class="tab-content" id="">
			  </div> -->
       	</div>
    </div>
    
    <script type="text/javascript" src="<%=commonContextPath%>/js/admin_main.js"></script>
  </body>
</html>
