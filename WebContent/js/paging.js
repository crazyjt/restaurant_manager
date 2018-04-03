/**实现分页功能
 * pno:当前页数  psize:每页显示行数 
 */
	
function goPage(pno, psize) {
	var table = document.getElementById("DataGrid2");
	console.log("table" + table);
	console.log("table.rows: " + table.rows);
	//表格行数(去掉标题行)
	var rowsNum = table.rows.length;
	console.log("表格行数：" + rowsNum);
	//总页数
	var totalPage = 0;
	//当前页数
	var currentPage = pno;
	//每页显示行数
	var eachPageRow = psize;
	//确定总页数
	if( (rowsNum / eachPageRow) > parseInt(rowsNum / eachPageRow) ) {
		totalPage = parseInt(rowsNum / eachPageRow) + 1;
	} else {
		totalPage = parseInt(rowsNum / eachPageRow);
	}
	console.log("总页数：" + totalPage);
	//当前页第一行
	var startRow = (currentPage - 1) * eachPageRow + 1;
	console.log("当前页第一行: " + startRow);
	//当前页最后一行
	var endRow = currentPage * eachPageRow;
	endRow = (endRow > rowsNum) ? rowsNum : endRow;
	console.log("当前页最后一行: " + endRow);
	//遍历数据，只显示当前页对应的行数
	for(var i = 1; i < (rowsNum+1); i++){
		var irow = table.rows[i-1];
		if(i < startRow || i > endRow){
			console.log("irow: " + irow.style);
			irow.style.display = "none";
		} else {
			irow.style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word";
		}
	}
	//分页选择
	var tempStr = "共"+"<span style=\"color:blue;\">"+ rowsNum+"</span>"+"条记录  分"+"<span style=\"color:blue;\">"+totalPage+"</span>"+"页  当前第"+"<span style=\"color:blue;\">"+currentPage+"</span>"+"页  ";
    if(currentPage>1){
        tempStr += "<a href=\"#\" onClick=\"goPage("+(1)+","+eachPageRow+")\">首页</a>";
        tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage-1)+","+eachPageRow+")\"><上一页</a>";
    }else{
        tempStr += "首页";
        tempStr += "<上一页";    
    }

    if(currentPage<totalPage){
        tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage+1)+","+eachPageRow+")\">下一页></a>";
        tempStr += "<a href=\"#\" onClick=\"goPage("+(totalPage)+","+eachPageRow+")\">尾页</a>";
    }else{
        tempStr += "下一页>";
        tempStr += "尾页";    
    }
    //将字符串输出于页面
	document.getElementById("listPageChange").innerHTML = tempStr;
	/*var listPageFirst = document.getElementById("listPageFirst");
	var listPagePre = document.getElementById("listPagePre");
	var listPageNext = document.getElementById("listPageNext");
	var listPageEnd = document.getElementById("listPageEnd");
	//第一页取消显示“首页”、“上一页”标签
	if(currentPage == 1){
		listPageFirst.style = "display: none;"
		listPagePre.style = "display: none;"
	} 
	//最后一页取消显示“尾页”、“下一页”标签
	if(currentPage == totalPage && currentPage != 1){
		console.log("haha");
		listPageEnd.style = "display: none;"
		listPageNext.style = "display: none;"
	}
	console.log(listPageNext.onclick);
	listPageNext.onclick = "goPage(currentPage+1,eachPageRow,'DataGrid2');";
	console.log(listPageNext.onclick);*/
}

