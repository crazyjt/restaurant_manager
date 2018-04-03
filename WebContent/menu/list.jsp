<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging.js"></script>
		<script type="text/javascript">
		function addMenu() {
			window.location.href = "${pageContext.request.contextPath}/menu/add.jsp";
		}
		</script>
	</HEAD>
	<body id="test" onload="goPage(1,10)">
		<s:form action="listMenuByCondition" >
			<div class="listTop">
				<div class="listTopTitle">筛选查询	</div>
				<div class="listTopCondition">
					<span class="listTopState">菜品名称</span>
					<s:textfield name="m_name"></s:textfield>
					<span class="listTopState">菜品类型</span>
					<s:select name="m_type" list="{'饭','粉','汤','甜点'}" headerKey="" headerValue="---选择类型---"></s:select>
					<span class="listTopState">是否上传相片</span>
					<s:select name="hasPic" list="#{'true':'有','false':'无'}" headerKey="" headerValue="---请选择---"></s:select>
					<div class="listTopInput">
						<input class="topSubmit" type="submit" id="search" name="search" value="查询" class="button_view"/>
						<input class="topReset" type="reset" name="reset" value="重置" class="button_view"/>
					</div>
				</div>
			</div>
			<div class="listBottom">
				<div class="listTopTitle" style="margin-top: 0;">菜品列表</div>
				<div class="listBottomAdd" onclick="addMenu()" title="添加菜品"></div>
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
								<td align="center" width="15%">菜品id</td>
								<td align="center" width="19%">菜品名称</td>
								<td align="center" width="15%">菜品价格(元)</td>
								<td align="center" width="15%">菜品库存(份)</td>
								<td align="center" width="15%">菜品类型</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">查看</td>
								<td width="7%" align="center">删除</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid2"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<s:iterator value="menus">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="15%"><s:property value="m_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="19%"><s:property value="m_name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="15%"><s:property value="m_price" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="15%"><s:property value="m_inventory" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
										width="15%"><s:property value="m_type" /></td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="editMenuUI">
											<s:param name="m_id" value="m_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/i_edit.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="viewMenu">
											<s:param name="m_id" value="m_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/button_view.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="deleteMenu">
											<s:param name="m_id" value="m_id"></s:param>
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

