<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath }/css/left.css" type="text/css" rel="stylesheet">
		<script type="text/javascript">
			function onMouseMove(x) {
				x.style.height="19%";
			}
			function onMouseOut(x) {
				x.style.height= "16%";
			}
			var isDown = false;
			var secondList = document.getElementsByClassName("leftLiSecond");
			function onMouseDown(x) {
				isDown = !isDown;
				var img = document.getElementById("leftFirstImg");
				if(isDown) {
					var img = document.getElementById("leftFirstImg");
					img.src = "../images/leftlistdown.png";
					for(var i = 0; i < secondList.length; i++) {
						secondList[i].style.display = "block";
					}
				} else {
					img.src = "../images/leftlist.png";
					onMouseMove(x);
					for(var i = 0; i < secondList.length; i++) {
						secondList[i].style.display = "none";
					}
				}
			}
		</script>
	</head>
	<body>
			<s:url action="listAllWorker" var="workerUrl"></s:url>
			<s:url action="listAllRestaurant" var="restaurantUrl"></s:url>
			<s:url action="listAllMenu" var="menuUrl"></s:url>
			<s:url action="listAllTable" var="tableUrl"></s:url>
			<s:url action="listAllOrder" var="orderUrl"></s:url>
			<s:url action="listAllCharge" var="chargeUrl"></s:url>
			<div class="leftDiv">
				<ul class="leftUl">
					<li class="leftLiFirst" onmousemove="onMouseMove(this)" onmouseout="onMouseOut(this)" onmousedown="onMouseDown(this)">
						<img  id="leftFirstImg" width="15%" height="60%" src="../images/leftlist.png" ></img>
						&nbsp; <span>餐厅管理系统</span>
					</li>
					<li class="leftLiSecond" style="display: none">
						<img width="15%" height="50%" src="../images/person.png"></img>
						<a href="<s:property value='#workerUrl'/>" target="mainFrame" style="text-decoration: none">员工管理</a>
					</li>
					<li class="leftLiSecond" style="display: none">
						<img width="15%" height="50%" src="../images/restaurant.png"></img>
						<a href="<s:property value='#restaurantUrl'/>" target="mainFrame" style="text-decoration: none">餐厅信息管理</a>
					</li>
					<li class="leftLiSecond" style="display: none">
						<img width="15%" height="50%" src="../images/menu.png"></img>
						<a href="<s:property value='#menuUrl'/>" target="mainFrame" style="text-decoration: none">菜单管理</a>
					</li>
					<li class="leftLiSecond" style="display: none">
						<img width="15%" height="50%" src="../images/table.png"></img>
						<a href="<s:property value='#tableUrl'/>" target="mainFrame" style="text-decoration: none">餐桌管理</a>
					</li>
					<li class="leftLiSecond" style="display: none">
						<img width="15%" height="50%" src="../images/order.png"></img>
						<a href="<s:property value='#orderUrl'/>" target="mainFrame" style="text-decoration: none">订单管理</a>
					</li>
					<li class="leftLiSecond" style="display: none">
						<img width="15%" height="50%" src="../images/chargeinfo.png"></img>
						<a href="<s:property value='#chargeUrl'/>" target="mainFrame" style="text-decoration: none">流水账管理</a>
					</li>
				</ul> 
			</div>
		
		
	
	
<%-- <table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<s:url action="listAllWorker" var="workerUrl" />
	<s:url action="listAllRestaurant" var="restaurantUrl"/>
	<ul>
		<li><a href="<s:property value="#workerUrl"/>" target="mainFrame">啦啦啦</a></li>
	</ul> 
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'管理导航栏');
		d.add(2,0,'员工管理','<s:property value="#workerUrl"/>','','mainFrame');
		d.add(3,0,'餐厅信息管理','<s:property value="#restaurantUrl"/>','','mainFrame');
		d.add(4,0,'菜单管理','<s:property value="#url"/>','','mainFrame');
		d.add(5,0,'餐桌管理','<s:property value="#url"/>','','mainFrame');
		d.add(6,0,'订单管理','<s:property value="#url"/>','','mainFrame');
		d.add(7,0,'流水账管理','<s:property value="#url"/>','','mainFrame');
		
		
		/* //子目录添加
		d.add(3,2,'用户管理','<s:property value="#url"/>','','mainFrame'); */

		
		document.write(d);
		//-->
	</script>
</div>	</td>
  </tr>
</table> --%>
	</body>
</html>
