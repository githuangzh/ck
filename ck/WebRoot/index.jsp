<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript">
			var w = screen.availWidth;
			var h = screen.availHeight;
			w=1366;h=738;
			var openWin = window.open("profile/login.html",'_blank',"fullscreen=0,toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=yes");
			openWin.moveTo(0,0);
			openWin.resizeTo(w+9,h+9);
			window.opener = null;
			window.close();
		</script>
	</head>
	<body>
	</body>
</html>