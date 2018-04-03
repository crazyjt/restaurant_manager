<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging.js"></script>
		<script type="text/javascript">
		function addUser() {
			window.location.href = "${pageContext.request.contextPath}/user/add.jsp";
		}
		</script>
	</HEAD>
	<body id="test" onload="goPage(1,10)">
		<s:form action="listWorkerByCondition" >
			<div class="listTop">
				<div class="listTopTitle">筛选查询	</div>
				<div class="listTopCondition">
					<span class="listTopState">员工岗位</span>
					<s:select name="w_type" list="{'经理','店长','组长','服务员','厨师'}" headerKey="" headerValue="---选择岗位---"></s:select>
					<span class="listTopState">性别</span>
					<s:select name="w_sex" list="{'男','女'}" headerKey="" headerValue="---选择性别---"></s:select>
					<span class="listTopState">是否上传相片</span>
					<s:select name="hasPic" list="#{'true':'有','false':'无'}" headerKey="" headerValue="---请选择---"></s:select>
					<div class="listTopInput">
						<input class="topSubmit" type="submit" id="search" name="search" value="查询" class="button_view"/>
						<input class="topReset" type="reset" name="reset" value="重置" class="button_view"/>
					</div>
				</div>
			</div>
			<div class="listBottom">
				<div class="listTopTitle" style="margin-top: 0;">员工列表</div>
				<div class="listBottomAdd" onclick="addUser()" title="添加员工"></div>
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
								<td align="center" width="18%">登录id</td>
								<td align="center" width="17%">员工姓名</td>
								<td align="center" width="8%">性别</td>
								<td align="center" width="23%">岗位</td>
								<td width="11%" align="center">工龄(年)</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">查看</td>
								<td width="7%" align="center">删除</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid2"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<s:iterator value="workers">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%"><s:property value="w_id" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="w_name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="w_sex" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23%"><s:property value="w_type" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" width="11%"
										align="center"><s:property value="w_workTime" /></td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="editWorkerUI">
											<s:param name="w_id" value="w_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/i_edit.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="viewWorker">
											<s:param name="w_id" value="w_id"></s:param>
											<img
												src="${pageContext.request.contextPath}/images/button_view.gif"
												border="0" style="CURSOR: hand">
										</s:a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<s:a
											action="deleteWorker">
											<s:param name="w_id" value="w_id"></s:param>
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
<%-- 				<a id="listPageFirst" href="" onclick="goPage(1,5,'DataGrid2')">首页</a>
				<a id="listPagePre" href="">上一页</a>
				&nbsp;第<span id="listPageState"style="color: blue;" ></span>页&nbsp;
				<a id="listPageNext" href="" onclick="goPage(2,5,'DataGrid2')">下一页</a>
				<a id="listPageEnd" href="" onclick="goPage(1,5,'DataGrid2')">尾页</a>
 --%>		</div>
		</s:form>
	</body>
</HTML>

