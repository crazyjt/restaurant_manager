<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/edit.css" type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath}/css/add.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
	</HEAD>
	<script type="text/javascript">
	</script>
	<body style="overflow-y: visible;">
		<s:fielderror cssStyle="color: red"/>
		<s:form action="editOrder" validate="true">
			<div class="editTitle">
				修改订单信息
			</div>
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						订单id(不可修改)：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="o_id" readonly="true" cssStyle="border:none"></s:textfield>
					</td>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						 订单消费：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="o_price" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
				</tr>
				<tr> 
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						下单时间(yyyy-mm-dd hh:mm:ss)：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="o_serverTime" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
					
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						是否买单：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:select name="o_pay" list="{'是','否'}" headerKey="" headerValue="---选择---"></s:select>
					</td>
				</tr>
				<tr>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						员工id：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="worker.w_id"></s:textfield>
					</td>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						餐桌id：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="diningtable.d_id"></s:textfield>
					</td>
				</tr>
				<tr>
					<s:div cssStyle="color:red"><s:actionerror /> </s:div>
				</tr>
				<tr>
					<td class="addSecondTitle" colspan="5">下单菜品</td>
				</tr>
					<tr width="100%">
						<td width="25%" style="text-align: center;">菜名</td>
						<td width="25%" style="text-align: center;">剩余份数</td>
						<td width="25%" style="text-align: center;">已下单份数</td>
						<td width="25%" style="text-align: center;">修改份数</td>
					</tr>
					<s:iterator value="allMenus" status="status">
						<tr>
							<td width="25%" style="text-align: center;border: 1px solid #8ba7e3;"><s:property value="m_name"></s:property></td>
							<td width="25%" style="text-align: center;border: 1px solid #8ba7e3;"><s:property value="m_inventory"></s:property></td>
							<td width="25%" style="text-align: center;border: 1px solid #8ba7e3;"><s:property value="m_order.split(',')[#status.index]"></s:property></td>
							<td width="25%" style="text-align: center;border: 1px solid #8ba7e3;"><s:textfield name="m_order" value=""></s:textfield></td>
						</tr>
					</s:iterator>
				<tr>
					<td class="ta_01" style="WIDTH: 100%;" align="center"
						bgColor="#f5fafe" colSpan="4">
						<div class="editBottom">
							<button class="editButton" type="submit">修改</button>
							<button class="editButton" type="reset">重置</button>
							<input class="editButton" type="button" onclick="history.go(-1)" value="返回"/>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</HTML>