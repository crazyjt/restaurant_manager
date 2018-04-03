<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/view.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	</HEAD>
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/user/userAction_save.do" method="post" enctype="multipart/form-data">
			<div class="viewTitle">
				流水账信息
			</div>
			<div class="viewMiddle" style="height: 158px;">
				<div class="viewLeft" style="width: 100%;height: 100%;">
					<table cellSpacing="1" cellPadding="5" width="100%" align="center" style="border: 1px solid #8ba7e3" border="0">
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">流水账id：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="c_id" /></td>
							<td width="30%" align="center" bgColor="#ADD8E6">订单id：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="orderInfo.o_id" /></td>
						</tr>
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">员工id：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="worker.w_id" /></td>
							<td width="30%" align="center" bgColor="#ADD8E6">餐桌id：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="diningtable.d_id" /></td>
						</tr>
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">应收金额(元)：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="c_requestReceive" /></td>
							<td width="30%" align="center" bgColor="#ADD8E6">实收金额(元)：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="c_factReceive" /></td>
						</tr>
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">返还金额(元)：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="c_returnMoney" /></td>
							<td width="30%" align="center" bgColor="#ADD8E6">是否开发票：</td>
							<td width="20%" align="center" bgColor="#f5fafe"><s:property value="c_giveBillflg" /></td>
						</tr>
						<tr>
							<td width="30%" align="center" bgColor="#ADD8E6">备注：</td>
							<td colSpan="4" align="center" bgColor="#f5fafe"><s:property value="c_remark" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="viewBottom">
				<input type="button" onclick="history.go(-1)" value="返回" style="border-radius: 5px; width: 8%; height: 70%;"/>
			</div>
		</form>
	</body>
</HTML>