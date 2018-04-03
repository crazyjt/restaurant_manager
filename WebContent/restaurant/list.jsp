<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging.js"></script>
		<script type="text/javascript">
		function addRestaurant() {
			window.location.href = "${pageContext.request.contextPath}/restaurant/add.jsp";
		}
		</script>
	</HEAD>
	<body id="test" onload="goPage(1,10)">
		<s:form action="listRestaurantByCondition" >
			<div class="listTop">
				<div class="listTopTitle">筛选查询	</div>
				<div class="listTopCondition">
					<span class="listTopState">经营者</span>
					<s:textfield name="worker.w_name"></s:textfield>
					<%-- <s:select name="w_type" list="{'经理','店长','组长','服务员','厨师'}" headerKey="" headerValue="---选择岗位---"></s:select> --%>
					<span class="listTopState">餐厅地址</span>
					<s:textfield name="info_address"></s:textfield>
					<%-- <s:select name="w_sex" list="{'男','女'}" headerKey="" headerValue="---选择性别---"></s:select> --%>
					<div class="listTopInput">
						<input class="topSubmit" type="submit" id="search" name="search" value="查询" class="button_view"/>
						<input class="topReset" type="reset" name="reset" value="重置" class="button_view"/>
					</div>
				</div>
			</div>
			<div class="listBottom">
				<div class="listTopTitle" style="margin-top: 0;">餐厅列表</div>
				<div class="listBottomAdd" onclick="addRestaurant()" title="添加餐厅"></div>
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
								<td align="center" width="10%">餐厅id</td>
								<td align="center" width="10%">餐厅名</td>
								<td align="center" width="8%">经营者</td>
								<td align="center" width="23%">地址</td>
								<td align="center" width="11%" >营业开始日期</td>
								<td align="center" width="11%" >营业时间</td>
								<td align="center" width="7%" >编辑</td>
								<td align="center" width="7%" >查看</td>
								<td align="center" width="7%">删除</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid2"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<s:iterator value="restaurants">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="info_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="info_name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="worker.w_name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23%"><s:property value="info_address" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="11%"><s:property value="info_startDate.toString()" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="11%"><s:property value="info_businessHours" /></td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="editRestaurantUI">
											<s:param name="info_id" value="info_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/i_edit.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="viewRestaurant">
											<s:param name="info_id" value="info_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/button_view.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="deleteRestaurant">
											<s:param name="info_id" value="info_id"></s:param>
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

