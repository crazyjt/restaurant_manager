<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath }/css/home.css" type="text/css" rel="stylesheet">
  </head>
  
<body class="homeSession">
	<iframe class="homeTop" src="${pageContext.request.contextPath}/login/top.jsp" frameborder="no" scrolling="NO" marginheight="0"></iframe>
	<iframeset class="homeMiddle">
		<iframe class="homeLeft" src="${pageContext.request.contextPath}/login/left.jsp" frameborder="no" scrolling="YES" marginheight="0" ></iframe>
		<iframe class="homeRight" src="${pageContext.request.contextPath}/login/welcome.jsp" frameborder="no" scrolling="YES" marginheight="0"  name="mainFrame"></iframe>
	</iframeset>
	<iframe src="${pageContext.request.contextPath}/login/bottom.jsp" class="homeBottom" name="bottomFrame" frameborder="no" scrolling="NO" marginheight="0"></iframe>
</body>
</html>
