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
				员工信息
			</div>
			<div class="viewMiddle">
				<div class="viewLeft" style="width: 70%;height: 100%;">
					<table cellSpacing="1" cellPadding="5" width="100%" align="center" style="border: 1px solid #8ba7e3" border="0">
						<tr>
							<td width="50%" align="center" bgColor="#f5fafe">登录id：</td>
							<td align="center" bgColor="#f5fafe"><s:property value="w_id" /></td>
						</tr>
						<tr>
							<td width="50%" align="center" bgColor="#ADD8E6">员工姓名：</td>
							<td align="center" bgColor="#ADD8E6"><s:property value="w_name" /></td>
						</tr>	
						<tr>
							<td width="50%" align="center" bgColor="#f5fafe">性别：</td>
							<td align="center" bgColor="#f5fafe"><s:property value="w_sex" /></td>
						</tr>
						<tr>	
							<td width="50%" align="center" bgColor="#ADD8E6">岗位：</td>
							<td align="center" bgColor="#ADD8E6"><s:property value="w_type" /></td>
						</tr>	
						<tr>
							<td width="50%" align="center" bgColor="#f5fafe">工龄(年)：</td>
							<td align="center" bgColor="#f5fafe"><s:property value="w_workTime" /></td>
						</tr>
					</table>
				</div>
				<div class="viewRight" style="width: 30%;height: 100%;">
					<table cellSpacing="1" cellPadding="5" width="100%" height="100%;" align="center" style="border: 1px solid #8ba7e3" border="0">
						<tr>
							<td align="center" bgColor="#f5fafe" width="30%" height="100%" style="flex-direction: column;">员工照片</td>
							<td align="center" bgColor="#ffffff" width="70%" height="145px" >
								<div class="viewRightPic">
									<s:if test="w_filename!=null">
										<s:url action="workerPicShow" var="picurl">
											<s:param name="w_id" value="w_id"></s:param>
										</s:url>
										<img width="50%" height="70%" src="<s:property value="#picurl"/>" />
	
										<s:url action="workerPicDownload" var="url">
											<s:param name="w_id" value="w_id"></s:param>
										</s:url>
										<input width="70%" type="button" onclick="openWindow('<s:property value="#url"/>','700','400')" value="下载图片" style="margin-top: 1%;"/>
									</s:if> 
									<s:else>
										暂未上传照片
									</s:else>
								</div>
							</td>
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