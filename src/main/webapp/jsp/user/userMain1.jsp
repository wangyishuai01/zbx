<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<script src="${ctx }/js/jquery-1.9.1.js"></script>
<style type="text/css">
*{
	margin: auto;
	padding: auto;
}
.body {
	padding: auto;
	width: 100%;
	height: auto;
}
.body table .title{
	font-size: 20px;
	text-align: center;
}
.body .page {
	text-align: center;
}
.line-limit-length {
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap; 
max-width: 500px;
}
</style>
</head>
<body>
	<div class="body">
		<table>
			<tr class="title">
				<td width="500px">用户名</td>
				<td width="200px">用户最近登录</td>
				<td width="300px">操作</td>
			</tr>
		</table>
		<div class="page">
			<input value="首页" type="button" onclick="first()"/>
			<input value="上一页" type="button" onclick="previous()"/>
			<input value="下一页" type="button" onclick="next()"/>
			<input value="尾页" type="button" onclick="last()"/>
		</div>
	</div>
</body>
<script type="text/javascript">
Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}
var rootPath = "${pageContext.request.contextPath}";
var pageCount = 1, pageSize = 4, pageMax = 100;
function init(){
	$.ajax({
		url : rootPath+"/customer/initCommentMain.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize
		},
		success : function(result) {
			$("tr[class='comment_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					var makeDate = new Date();
					makeDate.setTime(res.makedate);
					var str = "<tr id='comment_tr"+res.id+"' class='comment_tr'>"
							+ "<td align='center'>"+res.title+"</td><td class='line-limit-length'>"+res.comment+"</td>"
							+ "<td align='center'>"+res.customerName+"</td>"
							+ "<td align='center'>"+(makeDate.format('yyyy-MM-dd h:m:s'))+"</td>"
							+ "<td align='center'><a href='javascript:deleteClassify("+res.id+")'>删除</a>"
							+ " | <a href='javascript:'>查看</a>";
					str += "</tr>";
					$(".body table").append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
}

function deleteClassify(commentid){
	$.ajax({
		url : rootPath+"/customer/deleteById.do",
		type : "post",
		dataType : "json",
		data : {
			"commentId" : commentid 
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				alert("删除成功！");
				init();
			} else {
				alert("删除失败！");
			}
		}
	});
}

function addTClassify(){
	$.ajax({
		url : rootPath+"/customer/addComment.do",
		type : "post",
		dataType : "json",
		data : {
			"commentTitle" : "",
			"comment" : "",
			"commentType" : "",
			"customerId" : ""
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				alert("添加成功！");
				init();
			} else {
				alert("添加失败！");
			}
		}
	});
}

function first(){
	if(pageCount == 1){
		alert("已是首页！");
		return;
	}
	pageCount = 1;
	init();
}
function previous(){
	if(pageCount == 1){
		alert("已是首页！");
		return;
	}
	pageCount--;
	init();
}
function next(){
	if(pageCount == pageMax){
		alert("已是尾页！");
		return;
	}
	pageCount++;
	init();
}
function last(){
	if(pageCount == pageMax){
		alert("已是尾页！");
		return;
	}
	pageCount = pageMax;
	init();
}

$(document).ready(function(){
	
	init();
	
});
</script>
</html>