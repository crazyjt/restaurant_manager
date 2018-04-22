<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link href="${pageContext.request.contextPath }/css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
/* 跳出iframe框架 */
if(top.location!=self.location)top.location=self.location;
</script>
</head>
<body>
	<s:form action="login">
		<s:div cssClass="loginForm">
			<s:div cssClass="loginTitle">餐厅管理系统</s:div>
			<s:div cssClass="loginCenterPart">
				<span class="loginSpan">用户id：</span>
				<s:textfield cssClass="loginIdInput" name="w_id"></s:textfield>
				<span class="loginSpan">密码：</span>
				<s:password cssClass="loginPwdInput" name="w_password"></s:password>
				<s:div cssClass="loginError"> <s:actionerror /> </s:div>
				<s:submit value="登录" cssClass="loginSubmit"></s:submit>
				<s:reset value="重置" cssClass="loginReset"></s:reset>
			</s:div>
		</s:div>
	</s:form>
</body>
</html>