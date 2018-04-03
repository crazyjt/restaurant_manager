<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/view.css" type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath}/css/edit.css" type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath}/css/add.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	</HEAD>
	<body style="overflow-y: visible;">
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/user/userAction_save.do" method="post" enctype="multipart/form-data">
			<div class="viewTitle">
				订单信息
			</div>
			<div class="viewMiddle" style="height: 95px;">
				<div class="viewLeft" style="width: 100%;height: 100%;">
					<table cellSpacing="1" cellPadding="5" width="100%" align="center" style="border: 1px solid #8ba7e3" border="0">
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">订单id：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="o_id" /></td>
							<td width="30%" align="center" bgColor="#ADD8E6">订单消费：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="o_price" /></td>
						</tr>
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">下单时间(yyyy-mm-dd hh:mm:ss)：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="o_serverTime.toString()" /></td>
							<td width="30%" align="center" bgColor="#ADD8E6">是否买单：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="o_pay" /></td>
						</tr>
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">员工id：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="worker.w_id" /></td>
							<td width="30%" align="center" bgColor="#ADD8E6">餐桌id：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="diningtable.d_id" /></td>
						</tr>
						<tr>
							<td class="addSecondTitle" colspan="5">下单菜品</td>
						</tr>
						<tr width="100%">
							<td width="25%" style="text-align: center;" colspan="2">菜名</td>
							<td width="25%" style="text-align: center;" colspan="2">已下单份数</td>
						</tr>
						<s:iterator value="menuList" status="status">
							<tr>
								<td width="25%" style="text-align: center;border: 1px solid #8ba7e3;" colspan="2"><s:property value="m_name"></s:property></td>
								<td width="25%" style="text-align: center;border: 1px solid #8ba7e3;" colspan="2"><s:property value="menuSetStr.split(',')[#status.index]"></s:property></td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="5">
								<div class="viewBottom">
									<input type="button" onclick="history.go(-1)" value="返回" style="border-radius: 5px; width: 8%; height: 70%;"/>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</HTML>