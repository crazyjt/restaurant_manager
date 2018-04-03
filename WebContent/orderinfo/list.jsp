<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging.js"></script>
		<script type="text/javascript">
		</script>
	</HEAD>
	<body id="test" onload="goPage(1,10)">
		<s:form action="listOrderByCondition" >
			<div class="listTop">
				<div class="listTopTitle">筛选查询	</div>
				<div class="listTopCondition">
					<span class="listTopState">订单id</span>
					<s:textfield name="o_id"></s:textfield>
					<span class="listTopState">餐桌id</span>
					<s:textfield name="diningtable.d_id"></s:textfield>
					<span class="listTopState">是否买单</span>
					<s:select name="o_pay" list="{'是','否'}" headerKey="" headerValue="---选择---"></s:select>
					<div class="listTopInput">
						<input class="topSubmit" type="submit" id="search" name="search" value="查询" class="button_view"/>
						<input class="topReset" type="reset" name="reset" value="重置" class="button_view"/>
					</div>
				</div>
			</div>
			<div class="listBottom" style="text-align: center;">
				<div class="listTopTitle" style="margin-top: 0;">订单列表</div>
				<s:a action="addOrderUI">
					<div class="listBottomAdd" title="添加订单"></div>
				</s:a>
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
								<td align="center" width="10%">订单id</td>
								<td align="center" width="10%">订单消费(元)</td>
								<td align="center" width="23%">下单时间</td>
								<td align="center" width="8%">服务员id</td>
								<td align="center" width="11%" >餐桌id</td>
								<td align="center" width="11%" >是否买单</td>
								<td align="center" width="7%" >编辑</td>
								<td align="center" width="7%" >查看</td>
								<td align="center" width="7%">删除</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid2"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<s:iterator value="orderInfos">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="o_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="o_price" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23%"><s:property value="o_serverTime.toString()" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="worker.w_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="11%"><s:property value="diningtable.d_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="11%"><s:property value="o_pay" /></td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="editOrderUI">
											<s:param name="o_id" value="o_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/i_edit.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="viewOrder">
											<s:param name="o_id" value="o_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/button_view.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="deleteOrder">
											<s:param name="o_id" value="o_id"></s:param>
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

