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
		<s:form action="editMenu" enctype="multipart/form-data" validate="true">
			<div class="editTitle">
				修改菜品信息
			</div>
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						菜品id(不可修改)：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_id" readonly="true" cssStyle="border:none"></s:textfield>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						 菜品名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_name" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
				</tr>
				<tr> 
					<td align="center" bgColor="#f5fafe" class="ta_01">
						菜品价格(元)：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_price" requiredLabel="true" requiredPosition="left"></s:textfield>
					</td>
					
					<td align="center" bgColor="#f5fafe" class="ta_01">
						菜品库存(份)：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:textfield name="m_inventory"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						菜品类型：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:select name="m_type" list="{'饭','粉','汤','甜点'}"></s:select>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						菜品相片：
					</td>
					<td class="ta_01" bgColor="#ffffff" colSpan="10">
						<div id="fileDiv" style="display: inline">
							<s:if test="m_filename!=null">
								<s:url action="menuPicDownload" var="url">
									<s:param name="m_id" value="m_id"></s:param>
								</s:url>
								<a href="#" onclick="openWindow('<s:property value="#url"/>','700','400')" class="cl_01">
							  	 	<s:property value="m_filename.substring(m_filename.indexOf('_') + 1)"/>
								</a>
							</s:if>
							<s:else>
								暂未上传照片
							</s:else>
						</div>
						<div id="uploadDiv" style="display:none" onclick="showFile()">
							<s:file name="m_pic" size="30"></s:file>
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