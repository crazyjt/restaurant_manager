<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging.js"></script>
		<script type="text/javascript">
		function addCharge() {
			window.location.href = "${pageContext.request.contextPath}/chargeinfo/add.jsp";
		}
		</script>
	</HEAD>
	<body id="test" onload="goPage(1,10)">
		<s:form action="listChargeByCondition" >
			<div class="listTop">
				<div class="listTopTitle">筛选查询	</div>
				<div class="listTopCondition">
					<span class="listTopState">订单id</span>
					<s:textfield name="orderInfo.o_id"></s:textfield>
					<span class="listTopState">员工id</span>
					<s:textfield name="worker.w_id"></s:textfield>
					<span class="listTopState">餐桌id</span>
					<s:textfield name="diningtable.d_id"></s:textfield>
					<span class="listTopState" style="width: 125px;">是否开发票</span>
					<s:select name="c_giveBillflg" list="{'是','否'}" headerKey="" headerValue="---选择---"></s:select>
					<div class="listTopInput" style="margin-right: 2%;">
						<input class="topSubmit" type="submit" id="search" name="search" value="查询" class="button_view"/>
						<input class="topReset" type="reset" name="reset" value="重置" class="button_view"/>
					</div>
				</div>
			</div>
			<div class="listBottom">
				<div class="listTopTitle" style="margin-top: 0;">流水账列表</div>
				<div class="listBottomAdd" onclick="addCharge()" title="添加流水账"></div>
			</div>

		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="10%">流水账id</td>
								<td align="center" width="10%">订单id</td>
								<td align="center" width="10%">员工id</td>
								<td align="center" width="10%">餐桌id</td>
								<td align="center" width="10%" >应收金额(元)</td>
								<td align="center" width="10%" >实收金额(元)</td>
								<td align="center" width="10%" >找零金额(元)</td>
								<td align="center" width="9%" >是否开发票</td>
								<td align="center" width="7%" >编辑</td>
								<td align="center" width="7%" >查看</td>
								<td align="center" width="7%">删除</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid2"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<s:iterator value="chargeinfos">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="c_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="orderInfo.o_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="worker.w_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="diningtable.d_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="10%"><s:property value="c_requestReceive" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="10%"><s:property value="c_factReceive" /></td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="10%"><s:property value="c_returnMoney" /></td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="9%"><s:property value="c_giveBillflg" /></td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="editChargeUI">
											<s:param name="c_id" value="c_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/i_edit.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="viewCharge">
											<s:param name="c_id" value="c_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/button_view.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="deleteCharge">
											<s:param name="c_id" value="c_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/i_del.gif"
												width="16" height="16" border="0" style="CURSOR: hand">
										</s:a>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
		<div id="listPageChange" style="text-align: center;">
		</div>
		</s:form>
	</body>
</HTML>

