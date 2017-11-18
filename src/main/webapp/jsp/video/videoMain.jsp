<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视屏管理</title>
<style type="text/css">
*{
	margin: auto;
	padding: auto;
}
.page {
	margin: 20px;
	text-align: center;
}
.line-limit-length {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap; 
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
								<h5 class="widget-caption">视屏管理</h5>
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
										<a onclick="openUrl('/jsp/video/addVideo.jsp')" class="btn btn-primary"> 
											<i class="fa fa-plus"></i> 添加视屏
										</a>
									</div>
								</div>
								<div class="table-toolbar">
									<span>视屏类别：</span>
									<select id="videoFirClass" class="form-control" style="width:250px;"></select>
									<span>视屏类型：</span>
									<select id="videoSecClass" class="form-control" style="width:250px;"><option value=''>--请选择--</option></select>
									<span>视屏标题：</span>
									<input maxlength="20" type="text" id="videoTitle" class="form-control" style="width:250px;">
									<a class="btn btn-default shiny" onclick="query();">查询</a>
								</div>
								<table class="table table-bordered table-striped table-condensed table-hover flip-content" >
									<thead class="flip-content bordered-darkorange">
										<tr role="row">
											<th width="5%" style="text-align: center;">序号</th>
											<th width="30%">视屏标题</th>
											<th width="35%">文章标题</th>
											<th width="5%" style="text-align: center;">购买次数</th>
											<th width="5%" style="text-align: center;">评论次数</th>
											<th width="10%" style="text-align: center;">评论权限</th>
											<th width="10%" style="text-align: center;">操作</th>
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
var rootPath = "${pageContext.request.contextPath}";
var pageCount = 1, pageSize = 10, pageMax = 100;
function init(){
	pageSize = $("#pageSelect").val();
	var videoTitle = $("#videoTitle").val();
	var classifyId = $("#videoSecClass").val();
	$.ajax({
		url : rootPath+"/video/initVideoMain.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize,
			"videoTitle" : videoTitle,
			"classifyId" : classifyId
		},
		success : function(result) {
			$("tr[class='video_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					var str = "<tr id='video_tr"+res.id+"' class='video_tr'>"
							+ "<th style='text-align: center;'>"+((pageCount-1)*pageSize+(i+1))+"</th>"
							+ "<th>"+res.title+"</th>"
							+ "<th>"+res.articleTitle+"</th>"
							+ "<th style='text-align: center;'>"+res.buyCount+"</th><th style='text-align: center;'>"+res.commentCount+"</th>";
					if(res.nocomment == "0"){
						str += "<th style='text-align: center;color:red;'>禁止</th>";
					} else {
						str += "<th style='text-align: center;'>允许</th>";
					}
					str += "<th style='text-align: center;'>"
						+ "<a href='javascript:openUrl(\"/jsp/video/showAndEditVideo.jsp?videoId="+res.id+"&action=show\")'>查看</a> " 
						+ "| <a href='javascript:openUrl(\"/jsp/video/showAndEditVideo.jsp?videoId="+res.id+"&action=edit\")'>编辑</a><br> "
						+ "<a href='javascript:deleteVideo("+res.id+")'>删除</a> "
						+ "| <a href=''>统计</a></th>";
					str += "</tr>";
					$('.table:eq(0)').find('tbody').append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
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
				$("#videoFirClass").html(option);
			}
		}
	});
}
function initSecondClasstify(){
	var firstClassifyId = $("#videoFirClass").val();
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
				$("#videoSecClass").html(option);
			}
		});
	} else {
		var option = "<option value=''>--请选择--</option>";
		$("#videoSecClass").html(option);
	}
}

function query(){
	var firstClassifyId = $("#videoFirClass").val();
	var secondClassifyId = $("#videoSecClass").val();
	if(firstClassifyId != '' && secondClassifyId == ''){
		alert("请选择视屏类型！");
		return false;
	}
	init();
}

function deleteVideo(videoId){
	if(confirm("确定删除该视屏吗？")){
		$.ajax({
			url : rootPath+"/video/deleteVideoById.do",
			type : "post",
			dataType : "json",
			data : {
				"videoId" : videoId
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

$(document).ready(function(){
	init();
	$("#pageSelect").change(function(){
		pageCount = 1;
		init();
	});
	initFirstClasstify();
	$("#videoFirClass").change(initSecondClasstify);
});
</script>
</html>