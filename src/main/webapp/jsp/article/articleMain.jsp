<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章管理</title>
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
</style>
</head>
<body>
	<div class="body">
		<table>
			<tr class="title">
				<td width="500px">类别</td>
				<td width="200px">文章数</td>
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
var rootPath = "${pageContext.request.contextPath}";
var pageCount = 1, pageSize = 4, pageMax = 100;
function init(){
	$.ajax({
		url : rootPath+"/Article/ArticleList.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize
		},
		success : function(result) {
			$("tr[class='tclassify_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					var str = "<tr id='tclassify_tr"+res.classid+"' class='tclassify_tr'>"
							+ "<td align='center'>"+res.name+"</td><td align='center'>"+res.count+"</td>"
							+ "<td align='center'><a href=''>编辑</a> "
							+ "| <a href='javascript:deleteClassify("+res.classid+")'>删除</a><br>";
					if(res.spareField1 == "1"){
						str += "<a disabled = 'disabled' href='javascript:updateIsDisplay("+res.classid+",1)'>显示</a> "
							+ "| <a href='javascript:updateIsDisplay("+res.classid+",0)'>隐藏</a></td>";
					} else {
						str += "<a href='javascript:updateIsDisplay("+res.classid+",1)'>显示</a> "
							+ "| <a disabled = 'disabled' href='javascript:updateIsDisplay("+res.classid+",0)'>隐藏</a></td>";
					}
					str += "</tr>";
					$(".body table").append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
}

function deleteClassify(classid){
	$.ajax({
		url : rootPath+"/tclassify/deleteById.do",
		type : "post",
		dataType : "json",
		data : {
			"tclassifyId" : classid 
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

function updateIsDisplay(classid, isDisplay){
	$.ajax({
		url : rootPath+"/tclassify/updateIsDisplayById.do",
		type : "post",
		dataType : "json",
		data : {
			"tclassifyId" : classid,
			"isDisplay" : isDisplay
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				alert("修改成功！");
				init();
			} else {
				alert("修改失败！");
			}
		}
	});
}

function addTClassify(){
	$.ajax({
		url : rootPath+"/tclassify/addtClassify.do",
		type : "post",
		dataType : "json",
		data : {
			"tclassifyName" : "",
			"tclassifyLevel" : "",
			"tclassifyPid" : ""
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