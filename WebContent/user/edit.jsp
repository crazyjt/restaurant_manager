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
		<s:form action="editWorker" enctype="multipart/form-data" validate="true">
			<div class="editTitle">
				修改员工信息
			</div>
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						登录id(不可修改)：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="w_id" readonly="true" cssStyle="border:none"></s:textfield>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						 密码：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:password name="w_password" requiredLabel="true" requiredPosition="left" showPassword="true"></s:password>
					</td>
				</tr>
				<tr> 
					<td align="center" bgColor="#f5fafe" class="ta_01">
						员工姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="w_name" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
					
					<td align="center" bgColor="#f5fafe" class="ta_01">
						工龄：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="w_workTime"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						性别：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:radio name="w_sex" list="{'男','女'}" value="'男'"></s:radio>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						岗位：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:select name="w_type" list="{'经理','店长','组长','服务员','厨师'}" headerKey="" headerValue="---选择岗位---"></s:select>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						员工相片：
					</td>
					<td class="ta_01" bgColor="#ffffff" colSpan="10">
						<div id="fileDiv" style="display: inline">
							<s:if test="w_filename!=null">
								<s:url action="workerPicDownload" var="url">
									<s:param name="w_id" value="w_id"></s:param>
								</s:url>
								<a href="#" onclick="openWindow('<s:property value="#url"/>','700','400')" class="cl_01">
							  	 	<s:property value="w_filename.substring(w_filename.indexOf('_') + 1)"/>
								</a>
							</s:if>
							<s:else>
								暂未上传照片
							</s:else>
						</div>
						<div id="uploadDiv" style="display:none" onclick="showFile()">
							<s:file name="w_pic" size="30"></s:file>
						</div>
						<s:if test="w_filename!=null">
							<input type="button" id="uploadBtn" onclick="showFile()" value="重新上传"/>
						</s:if>
						<s:else>
							<input type="button" id="uploadBtn" onclick="showFile()" value="上传照片"/>
						</s:else>
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