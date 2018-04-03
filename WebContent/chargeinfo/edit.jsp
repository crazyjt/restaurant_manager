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
		<s:form action="editCharge" validate="true">
			<div class="editTitle">
				修改流水账信息
			</div>
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						流水账id(不可修改)：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="c_id" readonly="true" cssStyle="border:none"></s:textfield>
					</td>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						 订单id：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="orderInfo.o_id" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
				</tr>
				<tr> 
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						员工id：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="worker.w_id" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
					
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						餐桌id：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="diningtable.d_id"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						应收金额(元)：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="c_requestReceive"></s:textfield>
					</td>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						实收金额(元)：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff">
						<s:textfield name="c_factReceive"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						是否开发票：
					</td>
					<td width="20%" class="ta_01" bgColor="#ffffff"  colSpan="4">
						<s:select name="c_giveBillflg" list="{'是','否'}" headerKey="" headerValue="---选择---"></s:select>
					</td>
				</tr>
				<tr>
					<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
						备注：
					</td>
					<td bgColor="#ffffff" colSpan="4">
						<s:textarea name="c_remark" cssStyle="height: 125px; width: 80%;"></s:textarea>
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