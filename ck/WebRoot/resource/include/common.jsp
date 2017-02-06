<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ include file="/resource/include/taglib.jsp"%>
<%
String contextPath = request.getContextPath();
String jspContextPath = contextPath+"/WEB-INF/views";
String commonContextPath = contextPath+"/resource";
%>
<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/font-awesome-ie7.min.css" />
<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/scojs.css" />
<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/sco.message.css" />
<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/bootstrap-table.min.css" />
<link rel="stylesheet" type="text/css" href="<%=commonContextPath%>/css/zTreeStyle/zTreeStyle.css" />

<style type="text/css">
	html{overflow-x:hidden; overflow-y:auto;}
</style>
<script>
<%-- if('${__SESSION_OBJECT.userid}'==''){
	top.location.replace('<%=contextPath%>/profile/login.html');
} --%>
var __CONTEXT_PATH="<%=contextPath%>";
var __COMMON_CONTEXT_PATH="<%=commonContextPath%>";
var __ctxPath="<%=request.getContextPath() %>";
var __fullPath="<%=request.getScheme() + "://" + request.getHeader("host") +  request.getContextPath()%>";

</script>
		<!-- 基础js组件-->
		<script type="text/javascript" src="<%=commonContextPath%>/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/jquery.form.min.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/bootstrap/responsive-nav.min.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/bootstrap/bootstrap-table.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/bootstrap/bootstrap-confirmation.js"></script>
		
		<!-- sco bootstrap增强组件 -->
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.ajax.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.collapse.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.confirm.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.countdown.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.message.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.modal.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.panes.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.tab.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.tab.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.tooltip.js"></script>
		<script type="text/javascript" src="<%=commonContextPath%>/js/sco/sco.valid.js"></script>
		
		<!-- ztree组件 -->
		<script type="text/javascript" src="<%=commonContextPath%>/js/jquery.ztree.all.min.js"></script>
<!-- 模态框模板  -->
<div class="modal fade" id="modal_hzh" style="display:none">
        <div class="modal-header">
            <button class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title"></h4>
        </div>
        <div class="inner">
            <div class="container">
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
</div>


<!-- 提示框模板 -->
<div id="confirm_hzh">
	<div class="popover">
		<div class="arrow"></div>
		<h3 class="popover-title"></h3>
		<div class="popover-content text-center">
			<div class="btn-group">
			<a class="btn btn-sm btn-primary" href="" target="confirm_y">YES</a><a class="btn btn-sm btn-danger" data-dismiss="confirmation">NO</a>
			</div>
		</div>
	</div>
</div>

