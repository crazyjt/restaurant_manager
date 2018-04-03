<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/top.css" rel="stylesheet" type="text/css">
</head>
	<body>
		<div class="topDiv"></div>
		<div class="topBottomDiv">
			<div class="topBottomLeft" >
				 <script language="JavaScript">
				 <!--
						tmpDate = new Date();
						date = tmpDate.getDate();
						month = tmpDate.getMonth() + 1;
						year = tmpDate.getYear() + 1900;
						document.write(year);
						document.write("年");
						document.write(month);
						document.write("月");
						document.write(date);
						document.write("日 ");

						myArray = new Array(6);
						myArray[0] = "星期日"
						myArray[1] = "星期一"
						myArray[2] = "星期二"
						myArray[3] = "星期三"
						myArray[4] = "星期四"
						myArray[5] = "星期五"
						myArray[6] = "星期六"
						weekday = tmpDate.getDay();
						document.write(myArray[weekday]);
					// -->
					</script> 
			</div>
			<div class="topBottomRight" >
				用户名：
				<font color="#A7D9FA" >${sessionScope.worker.w_name }</font>
			</div>
		</div>

	</body>
</HTML>
