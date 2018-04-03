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
		<s:form action="addMenu" enctype="multipart/form-data" validate="true">
			<div class="addTitle">
				添加菜品
			</div>
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">菜品id：</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_id"></s:textfield>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">菜品名称：</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_name" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">菜品价格：</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_price" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
					
					<td align="center" bgColor="#f5fafe" class="ta_01">菜品库存：</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_inventory"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">菜品类型：</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:select name="m_type" list="{'饭','粉','汤','甜点'}" headerKey="" headerValue="---选择类型---"></s:select>
					</td>
					
					<td align="center" bgColor="#f5fafe" class="ta_01">菜品相片：</td>
					<td class="ta_01" bgColor="#ffffff" colSpan="3">
						<s:file name="m_pic" size="30"></s:file>
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