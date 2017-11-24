<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章管理</title>
<style type="text/css">
*{
	margin: auto;
	padding: auto;
}
.page {
	margin: 20px;
	text-align: center;
}
.hidden_font{
	opacity: 0.6;
}
</style>
</head>
<body>
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">
			<!-- Page Body -->
			<div class="page-body" id="pageBodyRight">
				<div class="row">
					<div class="col-xs-12 col-md-12">
						<div class="widget">
						<div class="widget-header ">
								<h5 class="widget-caption">文章管理</h5>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize"></a> <a href="#"
										data-toggle="collapse" onclick="tab('pro');"> <i
										class="fa fa-minus" id="pro-i"></i>
									</a> <a href="#" data-toggle="dispose"></a>
								</div>
							</div>
							<div class="widget-body" id="pro">
								<div class="table-toolbar">
									<div class="clearfix">
										<a onclick="openUrl('/jsp/article/addArticle.jsp')" class="btn btn-primary"> 
											<i class="fa fa-plus"></i> 添加文章
										</a>
									</div>
								</div>
								<div class="table-toolbar" >
									<span>文章类别：</span>
									<select id="articleFirClass" class="form-control" style="width:200px;"></select>
									<span>文章类型：</span>
									<select id="articleSecClass" class="form-control" style="width:200px;"><option value=''>--请选择--</option></select>
									<span>文章标题：</span> 
									<input  class="form-control" style="width:200px;" id="articleTitle" />
									<span>关键字：</span> 
									<input  class="form-control" style="width:200px;" id="articleKey" />
									<a class="btn btn-default shiny" onclick="query();">查询</a>
								</div>
								<table class="table table-bordered table-striped table-condensed table-hover flip-content" >
									<thead class="flip-content bordered-darkorange">
										<tr role="row">
											<th width="5%" style="text-align: center;">序号</th>
											<th width="48%">标题</th>
											<th width="8%" style="text-align: center;">状态</th>
											<th width="8%" style="text-align: center;">阅读</th>
											<th width="8%" style="text-align: center;">评论</th>
											<th width="8%" style="text-align: center;">评论权限</th>
											<th width="15%" style="text-align: center;">操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<div class="pull-right" style="margin-top: 5px;">
									<form id="supplierInfo_form" action="">
										<div class="col-lg-12">
											<select id="pageSelect" name="pageSize">
												<option>5</option>
												<option selected="selected">10</option>
												<option>15</option>
												<option>20</option>
											</select>
										</div>
									</form>
								</div>
								<div class="page">
									<input value="首页" type="button" onclick="first()"/>
									<input value="上一页" type="button" onclick="previous()"/>
									<input value="下一页" type="button" onclick="next()"/>
									<input value="尾页" type="button" onclick="last()"/>
								</div>

							</div>
						</div>
					</div>
				</div>
				
			</div>
			<!-- /Page Body -->
		</div>
		<!-- /Page Content -->
	</div>
</body>
<script type="text/javascript">
var ue;
var rootPath = "${pageContext.request.contextPath}";
var pageCount = 1, pageSize = 10, pageMax = 100;
function init(){
	pageSize = $("#pageSelect").val();
	var articleTitle = $("#articleTitle").val();
	var classifyId = $("#articleSecClass").val();
	$.ajax({
		url : rootPath+"/Article/ArticleList.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize,
			"title" : articleTitle,
			"classid" : classifyId
		},
		success : function(result) {
			$("tr[class='comment_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					var str = "<tr id='comment_tr"+res.id+"' class='comment_tr'>"
							+ "<th style='text-align: center;'>"+((pageCount-1)*pageSize+(i+1))+"</th>"
							+ "<th>"+res.title+"</th>";
					if(res.state==1){
						str += "<th style='text-align: center;'>启用</th>";
					}else{
						str += "<th style='text-align: center;color: red;'>禁用</th>";
					}
					str += "<th style='text-align: center;'>"+res.commentCount+"</th>"
						+ "<th style='text-align: center;'>"+res.commentCount+"</th>";
					if(res.nocomment==1){
						str += "<th style='text-align: center;'>允许</th>";
					}else{
						str += "<th style='text-align: center;color: red;'>禁止</th>";
					}
					str += "<th style='text-align: center;'><a href='javascript:showArticle("+res.id+")'>查看</a>"
						+ " | <a href='javascript:editArticle("+res.id+")'>编辑</a>"
						+ "<br><a href='javascript:deleteArticle("+res.id+")'>删除</a>"
						+ " | <a href=''>统计</a></th>";
					str += "</tr>";
					$('.table').find('tbody').append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
}
//删除
function deleteArticle(articleId){
	if(confirm("你确信要删除此文章吗？")){
		$.ajax({
			url : rootPath+"/Article/deleteById.do",
			type : "post",
			dataType : "json",
			data : {
				"articleId" : articleId 
			},
			success : function(result) {
				result = JSON.parse(result);
				if(result.success){
					alert("删除成功！");
					init();
				} else {
					alert("删除失败，原因："+result.errorMsg);
				}
			}
		});
	}
}

function initFirstClasstify(){
	$.ajax({
		url : rootPath+"/tclassify/selectClassifyByParamV2.do",
		type : "post",
		dataType : "json",
		data : {
			"pid" : "0" 
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				var data1 = result.data;
				var option = "<option value=''>--请选择--</option>";
				for(var i=0; i<data1.length; i++){
					var res = data1[i];
					option += "<option value='"+res.id+"'>"+res.name+"</option>";
				}
				$("#articleFirClass").html(option);
			}
		}
	});
}
function initSecondClasstify(){
	var firstClassifyId = $("#articleFirClass").val();
	if(firstClassifyId != ''){
		$.ajax({
			url : rootPath+"/tclassify/selectClassifyByParamV2.do",
			type : "post",
			dataType : "json",
			data : {
				"pid" : firstClassifyId 
			},
			success : function(result) {
				result = JSON.parse(result);
				var option = "<option value=''>--请选择--</option>";
				if(result.success){
					var data1 = result.data;
					for(var i=0; i<data1.length; i++){
						var res = data1[i];
						option += "<option value='"+res.id+"'>"+res.name+"</option>";
					}
				}
				$("#articleSecClass").html(option);
			}
		});
	} else {
		var option = "<option value=''>--请选择--</option>";
		$("#articleSecClass").html(option);
	}
}

function query(){
	var firstClassifyId = $("#articleFirClass").val();
	var secondClassifyId = $("#articleSecClass").val();
	if(firstClassifyId != '' && secondClassifyId == ''){
		alert("请选择文章类型！");
		return false;
	}
	pageCount = 1;
	init();
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
function tab(data) {
	if (data == 'pro') {//基本
		if ($("#pro-i").attr("class") == "fa fa-minus") {
			$("#pro-i").attr("class", "fa fa-plus");
			$("#pro").css({
				"display" : "none"
			});
		} else {
			$("#pro-i").attr("class", "fa fa-minus");
			$("#pro").css({
				"display" : "block"
			});
		}
	}
}

function editArticle(articleId){
	openUrl("/jsp/article/showAndEditArticle.jsp?articleId="+articleId+"&action=edit");
}
function showArticle(articleId){
	openUrl("/jsp/article/showAndEditArticle.jsp?articleId="+articleId+"&action=show");
}

$(document).ready(function(){
	init();
	$("#pageSelect").change(function(){
		pageCount = 1;
		init();
	});
	initFirstClasstify();
	$("#articleFirClass").change(initSecondClasstify);
});
</script>
</html>