<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>title</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    	<form action="" method="post">
    		�û�����<input type="text" name="username"/><br/>
    		���룺<input type="password" name="password"/><br/>
    		��֤�룺<input type="text" name="validateCode"/>
    			<img src="${pageContext.request.contextPath}/captchaAction.action" />
		<%--<c:set var="ctx" value="${pageContext.request.contextPath}/captchaAction.action" />--%>
			<br/>
    		<input type="submit" value="��¼" />
    	</form>
  </body>
</html>
