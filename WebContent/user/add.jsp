<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/add.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
	</HEAD>
	<script type="text/javascript">
	</script>
	<body>
		<s:fielderror cssStyle="color: red"/>
		<s:form action="addWorker" enctype="multipart/form-data" validate="true">
			<div class="addTitle">
				添加员工
			</div>
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						登录id：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="w_id"></s:textfield>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						 密码：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:password name="w_password" requiredLabel="true" requiredPosition="left"></s:password>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						员工姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="w_name" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
					
					<td align="center" bgColor="#f5fafe" class="ta_01">
						工龄：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="w_workTime"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						性别：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:radio name="w_sex" list="{'男','女'}" value="'男'"></s:radio>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						岗位：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:select name="w_type" list="{'经理','店长','组长','服务员','厨师'}" headerKey="" headerValue="---选择岗位---"></s:select>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						员工相片：
					</td>
					<td class="ta_01" bgColor="#ffffff" colSpan="3">
						<s:file name="w_pic" size="30"></s:file>
					</td>
				</tr>
				<tr>
					<s:div cssStyle="color:red"><s:actionerror /> </s:div>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%;" align="center"
						bgColor="#f5fafe" colSpan="4">
						<div class="addBottom">
							<button class="addButton" type="submit">添加</button>
							<button class="addButton" type="reset">重置</button>
							<input class="addButton" type="button" onclick="history.go(-1)" value="返回"/>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</HTML>