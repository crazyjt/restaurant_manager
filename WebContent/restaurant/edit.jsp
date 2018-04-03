<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/edit.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
	</HEAD>
	<script type="text/javascript">
		function showFile() {
			var uploadDiv = document.getElementById("uploadDiv");
			var fileDiv = document.getElementById("fileDiv");
			var uploadBtn = document.getElementById("uploadBtn");
			uploadDiv.style.display = "inline";
			fileDiv.style.display = "none";
			uploadBtn.style.display = "none";
		}
	</script>
	<body>
		<s:fielderror cssStyle="color: red"/>
		<s:form action="editRestaurant" validate="true">
			<div class="editTitle">
				修改餐厅信息
			</div>
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td width="25%" align="center" bgColor="#f5fafe" class="ta_01">
						餐厅id(不可修改)：
					</td>
					<td width="25%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="info_id" readonly="true" cssStyle="border:none"></s:textfield>
					</td>
					<td width="25%" align="center" bgColor="#f5fafe" class="ta_01">
						 餐厅名称：
					</td>
					<td width="25%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="info_name" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
				</tr>
				<tr> 
					<td width="25%" align="center" bgColor="#f5fafe" class="ta_01">
						经营者姓名：
					</td>
					<td width="25%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="worker.w_name" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
					
					<td width="25%" align="center" bgColor="#f5fafe" class="ta_01">
						地址：
					</td>
					<td width="25%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="info_address"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%" align="center" bgColor="#f5fafe" class="ta_01">
						开始营业日期(yyyy-mm-dd)：
					</td>
					<td width="25%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="info_startDate"></s:textfield>
					</td>
					<td width="25%" align="center" bgColor="#f5fafe" class="ta_01">
						营业时间(hh:mm-hh:mm)：
					</td>
					<td width="25%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="info_businessHours"></s:textfield>
					</td>
				</tr>
				<tr>
					<s:div cssStyle="color:red"><s:actionerror /> </s:div>
				</tr>
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