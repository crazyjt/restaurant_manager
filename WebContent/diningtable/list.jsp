<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging.js"></script>
		<script type="text/javascript">
		function addTable() {
			window.location.href = "${pageContext.request.contextPath}/diningtable/add.jsp";
		}
		</script>
	</HEAD>
	<body id="test" onload="goPage(1,10)">
		<s:form action="listTableByCondition" >
			<div class="listTop">
				<div class="listTopTitle">筛选查询	</div>
				<div class="listTopCondition">
					<span class="listTopState">餐桌编号</span>
					<s:textfield name="d_no"></s:textfield>
					<span class="listTopState">餐桌状态</span>
					<s:select name="d_status" list="{'有客','空闲'}" headerKey="" headerValue="---选择状态---"></s:select>
					<div class="listTopInput">
						<input class="topSubmit" type="submit" id="search" name="search" value="查询" class="button_view"/>
						<input class="topReset" type="reset" name="reset" value="重置" class="button_view"/>
					</div>
				</div>
			</div>
			<div class="listBottom">
				<div class="listTopTitle" style="margin-top: 0;">餐桌列表</div>
				<div class="listBottomAdd" onclick="addTable()" title="添加餐桌"></div>
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
								<td align="center" width="19%">餐桌id</td>
								<td align="center" width="20%">餐桌编号</td>
								<td align="center" width="20%">餐桌状态</td>
								<td align="center" width="20%">餐桌人数</td>
								<td align="center" width="7%" >编辑</td>
								<td align="center" width="7%" >查看</td>
								<td align="center" width="7%">删除</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid2"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<s:iterator value="diningtables">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="19%"><s:property value="d_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="20%"><s:property value="d_no" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="20%"><s:property value="d_status" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="20%"><s:property value="d_num" /></td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="editTableUI">
											<s:param name="d_id" value="d_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/i_edit.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="viewTable">
											<s:param name="d_id" value="d_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/button_view.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="deleteTable">
											<s:param name="d_id" value="d_id"></s:param>
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

