<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8" src="${ctx }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/ueditor/ueditor.all.js"> </script>

<script type="text/javascript" charset="utf-8" src="${ctx }/ueditor/lang/zh-cn/zh-cn.js"></script>
<title>文章管理</title>
<script src="${ctx }/js/jquery-1.9.1.js"></script>
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
								<!-- <div class="table-toolbar">
									<div class="clearfix">
										<a onclick="addTClassify();" class="btn btn-primary"> 
											<i class="fa fa-plus"></i> 添加分类
										</a>
									</div>
								</div> -->
								<div id="hidediv1">
								<div class="table-toolbar" >
									<span>类别：</span> <input maxlength="20" type="text" id="articleName" />
									<span>类型：</span> <input maxlength="20" type="text" id="articleType" />
									<span>文章标题：</span> <input maxlength="20" type="text" id="articleTitle" />
									<span>关键字：</span> <input maxlength="20" type="text" id="articleKey" />
									<a class="btn btn-default shiny" onclick="init();">搜索</a>
								</div>
								<table class="table table-bordered table-striped table-condensed table-hover flip-content" >
									<thead class="flip-content bordered-darkorange">
										<tr role="row">
											<th width="5%" style="text-align: center;">序号</th>
											<th width="45%">标题</th>
											<th width="8%" style="text-align: center;">状态</th>
											<th width="8%" style="text-align: center;">阅读</th>
											<th width="8%" style="text-align: center;">评论</th>
											<th width="10%" style="text-align: center;">评论权限</th>
											<th width="16%" style="text-align: center;">操作</th>
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
						<!-- 编辑器 -->
								<div id="hideeditor">
								<div class="table-toolbar" >
								 <input type="hidden" id="articleid" />
									<span>文章标题：</span> <input maxlength="20" type="text" id="title" />
									<span>状态：</span> <select id="state">
													  <option value="1">启用</option>
													  <option value="0">禁用</option>
													</select>
									<span>评论权限：</span> <select id="nocomment">
													  <option value="1">允许</option>
													  <option value="0">禁止</option>
													</select>
													<!-- <input maxlength="20" type="text" id="nocomment" /> -->
									<button onclick="closeUeditor()">取消编辑</button>
								</div>
									<script id="editor" type="text/plain"
										style="width:1024px;height:500px;"></script>
									<div id="">
										<button onclick="submit()">提交</button>
									</div>
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
	var className = $("#className").val();
	$.ajax({
		url : rootPath+"/Article/ArticleList.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize,
			"className" : className
		},
		success : function(result) {
			$("tr[class='comment_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					if(res.state==1){
						res.state="启用";
					}else{
						res.state="禁用";
					}
					if(res.nocomment==1){
						res.nocomment="允许";
					}else{
						res.nocomment="禁止";
					}
					var str = "<tr id='comment_tr"+res.id+"' class='comment_tr'>"
					+ "<th style='text-align: center;'>"+((pageCount-1)*pageSize+(i+1))+"</th>"
					+ "<th>"+res.title+"</th><th style='text-align: center;'>"+res.state+"</th>"
					+ "<th style='text-align: center;'>"+res.commentCount+"</th>"
					+ "<th style='text-align: center;'>"+res.commentCount+"</th>"
					+ "<th style='text-align: center;'>"+res.nocomment+"</th>"
					+ "<th style='text-align: center;'><a href='javascript:createEditor("+res.id+")'>编辑</a>"
					+ " | <a href='javascript:showOneComment("+res.id+")'>视频</a>"
					+ " | <a href='javascript:deleteArticle("+res.id+")'>删除</a>"
					+ " | <a href='javascript:showOneComment("+res.id+")'>分类</a></th>";
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
	if(confirm("你确信要删除此评论吗？")){
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
					alert("删除失败！");
				}
			}
		});
	}
}


function closeEditDiv(){
	$("#showArticleDiv").hide();
}

function closeUeditor(){
	$("#hidediv1").show();
	$("#hideeditor").hide();
	ue.setContent('');
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

//编辑器
function createEditor(articleId) {
	$("#articleid").val(articleId);
	$.ajax({
		url : rootPath+"/Article/selectById.do",
		type : "post",
		dataType : "json",
		data : {
			"articleId" : articleId 
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				var data = result.data;
				$("#title").val(data.title);
				$("#state").val(data.state);
				$("#nocomment").val(data.nocomment);
				$("#content").val(data.content);
				$("#hidediv1").hide();
			    $("#hideeditor").show();
			    if(data.contentStr!=null){
			    	var str = data.contentStr;
				    ue.setContent(str);
			    }
			    
			}
		}
	}); 
    }
    

function submit() {
	var content = UE.getEditor('editor').getContent();
	$.ajax({
		url : rootPath+"/Article/articleUpdate.do",
		type : "post",
		dataType : "json",
		data : {
			"id" : $("#articleid").val(), 
			"title" : $("#title").val(), 
			"state" : $("#state").val(), 
			"nocomment" : $("#nocomment").val(), 
			"content" : content 
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				init();
				alert("编辑成功");
			}
		}
	}); 
	$("#hidediv1").show();
	$("#hideeditor").hide();
	ue.setContent('');
	
} 


$(document).ready(function(){
	init();
	$("#pageSelect").change(function(){
		pageCount = 1;
		init();
	});
	//创建编辑器
	 ue = UE.getEditor('editor');
	 $("#hideeditor").hide();
});
</script>
</html>