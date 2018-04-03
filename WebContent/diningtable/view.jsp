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
				餐桌信息
			</div>
			<div class="viewMiddle" style="height: 65px;">
				<div class="viewLeft" style="width: 100%;height: 100%;">
					<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
						<tr>
							<td width="25%" align="center" bgColor="#ADD8E6">餐桌id：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="d_id" /></td>
							<td width="25%" align="center" bgColor="#ADD8E6">餐桌编号：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="d_no" /></td>
						</tr>
						<tr>
							<td width="25%" align="center" bgColor="#ADD8E6">用餐人数：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="d_num" /></td>
							<td width="25%" align="center" bgColor="#ADD8E6">状态：</td>
							<td width="25%" align="center" bgColor="#f5fafe"><s:property value="d_status" /></td>
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