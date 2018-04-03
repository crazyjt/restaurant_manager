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
				餐厅信息
			</div>
			<div class="viewMiddle" style="height: 95px;">
				<div class="viewLeft" style="width: 100%;height: 100%;">
					<table cellSpacing="1" cellPadding="5" width="100%" align="center" style="border: 1px solid #8ba7e3" border="0">
						<tr>
							<td width="25%" align="center" bgColor="#ADD8E6">餐厅id：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="info_id" /></td>
							<td width="25%" align="center" bgColor="#ADD8E6">餐厅名称：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="info_name" /></td>
						</tr>
						<tr>
							<td width="25%" align="center" bgColor="#ADD8E6">餐厅经营者：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="worker.w_name" /></td>
							<td width="25%" align="center" bgColor="#ADD8E6">餐厅地址：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="info_address" /></td>
						</tr>
						<tr>
							<td width="25%" align="center" bgColor="#ADD8E6">开始营业日期：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="info_startDate.toString()" /></td>
							<td width="25%" align="center" bgColor="#ADD8E6">餐厅营业时间：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="info_businessHours" /></td>
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