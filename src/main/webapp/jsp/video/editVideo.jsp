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
								<h5 class="widget-caption">编辑视屏</h5>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize"></a> <a href="#"
										data-toggle="collapse" onclick="tab('pro');"> <i
										class="fa fa-minus" id="pro-i"></i>
									</a> <a href="#" data-toggle="dispose"></a>
								</div>
							</div>
							<div class="widget-body" id="pro">
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
											<th width="30%">文章标题</th>
											<th width="5%" style="text-align: center;">购买次数</th>
											<th width="5%" style="text-align: center;">评论次数</th>
											<th width="10%" style="text-align: center;">评论权限</th>
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
				<!-- edit -->
				<div class="modal modal-darkorange" id="editDiv">
					<div class="modal-dialog" style="margin: 60px auto;width:850px;">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeEditDiv();">×</button>
								<h4 class="modal-title">更新关联文章</h4>
							</div>
							<div class="modal-body">
								<div class="bootbox-body">
									<div class="row" style="padding: 10px;">
										<div class="table-toolbar">
											<span>文章类别：</span>
											<select id="videoFirClass1" class="form-control" style="width:200px;"></select>
											<span>文章类型：</span>
											<select id="videoSecClass1" class="form-control" style="width:200px;"><option value=''>--请选择--</option></select>
											<span>文章状态：</span>
											<select id="articleState" class="form-control" style="width:200px;">
												<option value=''>--请选择--</option>
												<option value='1'>启用</option>
												<option value='0'>禁用</option>
											</select>
											<br>&nbsp;<br>
											<span>文章标题：</span>
											<input maxlength="20" type="text" id="articleTitle" class="form-control" style="width:200px;">
											<a class="btn btn-default shiny" onclick="query1();">查询</a>
										</div>
										<table class="table table-bordered table-striped table-condensed table-hover flip-content" >
											<thead class="flip-content bordered-darkorange">
												<tr role="row">
													<th width="5%" style="text-align: center;">选择</th>
													<th width="5%" style="text-align: center;">序号</th>
													<th width="35%" style="text-align: center;">文章标题</th>
													<th width="45%" style="text-align: center;">文章摘要</th>
													<th width="10%" style="text-align: center;">文章状态</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
										<!-- <div class="pull-right" style="margin-top: 5px;">
											<form id="supplierInfo_form" action="">
												<div class="col-lg-12">
													<select id="pageSelect1" name="pageSize1">
														<option selected="selected">4</option>
														<option>8</option>
													</select>
												</div>
											</form>
										</div> -->
										<div class="page">
											<input value="首页" type="button" onclick="first1()"/>
											<input value="上一页" type="button" onclick="previous1()"/>
											<input value="下一页" type="button" onclick="next1()"/>
											<input value="尾页" type="button" onclick="last1()"/>
										</div>
									</div>
									<input type="hidden" id="videoId" value="">
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-default" type="button"
									onclick="saveEdit();">保存</button>
								<button data-dismiss="modal" class="btn btn-default"
									onclick="closeEditDiv();" type="button">取消</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
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
						str += "<th style='text-align: center;'><a href='javascript:editCommentPower("+res.id+",1)'>允许评论</a> ";
					} else {
						str += "<th style='text-align: center;'>允许</th>";
						str += "<th style='text-align: center;'><a href='javascript:editCommentPower("+res.id+",0)'>禁止评论</a> ";
					}
					str += "| <a href='javascript:openEditDiv("+res.id+")'>更新关联文章</a><br> "
						+ "<a href=''>查看视屏</a> "
						+ "| <a href=''>查看关联文章</a></th>";
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

function editCommentPower(videoId, power){
	$.ajax({
		url : rootPath+"/video/updateSelectById.do",
		type : "post",
		dataType : "json",
		data : {
			"id" : videoId, 
			"nocomment" : power
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

var pageCount1 = 1, pageSize1 = 5, pageMax1 = 100;
function init1(){
//	pageSize1 = $("#pageSelect1").val();
	var articleTitle = $("#articleTitle").val();
	var classifyId1 = $("#videoSecClass1").val();
	var articleState = $("#articleState").val();
	$.ajax({
		url : rootPath+"/Article/selectArticleMainByParam.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount1,
			"pageSize" : pageSize1,
			"title" : articleTitle,
			"classid" : classifyId1,
			"state" : articleState
		},
		success : function(result) {
			$("tr[class='article_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax1 = Math.floor(result.number%pageSize1 == 0 ? result.number/pageSize1 : result.number/pageSize1+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					var str = "<tr id='article_tr"+res.id+"' class='article_tr'>"
							+ "<th style='text-align: center;'><input type='radio' value='"+res.id+"' name='article_tr'></th>"
							+ "<th style='text-align: center;'>"+((pageCount1-1)*pageSize1+(i+1))+"</th>"
							+ "<th style='max-width: 300px' class='line-limit-length'>"+res.title+"</th>"
							+ "<th style='max-width: 342px' class='line-limit-length'>"+((res.excerpt==undefined||res.excerpt=='')?"暂无":res.excerpt)+"</th>";
					if(res.state == "1"){
						str += "<th style='text-align: center;'>启用</th>";
					} else {
						str += "<th style='text-align: center;color: red;'>禁用</th>";
					}
					str += "</tr>";
					$('.table:eq(1)').find('tbody').append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
}

function initFirstClasstify1(){
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
				$("#videoFirClass1").html(option);
			}
		}
	});
}
function initSecondClasstify1(){
	var firstClassifyId = $("#videoFirClass1").val();
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
				$("#videoSecClass1").html(option);
			}
		});
	} else {
		var option = "<option value=''>--请选择--</option>";
		$("#videoSecClass1").html(option);
	}
}

function query1(){
	var firstClassifyId = $("#videoFirClass1").val();
	var secondClassifyId = $("#videoSecClass1").val();
	if(firstClassifyId != '' && secondClassifyId == ''){
		alert("请选择文章类型！");
		return false;
	}
	init1();
}

function saveEdit(){
	var articleId = $("input[name='article_tr']:checked").val();
	if(articleId != "" && articleId != null){
		$.ajax({
			url : rootPath+"/video/updateSelectById.do",
			type : "post",
			dataType : "json",
			data : {
				"id" : $("#videoId").val(), 
				"articleid" : articleId
			},
			success : function(result) {
				result = JSON.parse(result);
				if(result.success){
					alert("修改成功！");
					closeEditDiv();
				} else {
					alert("修改失败！");
				}
			}
		});
	} else {
		alert("请选择要关联的文章！");
	}
}

function first1(){
	if(pageCount1 == 1){
		alert("已是首页！");
		return;
	}
	pageCount1 = 1;
	init1();
}
function previous1(){
	if(pageCount1 == 1){
		alert("已是首页！");
		return;
	}
	pageCount1--;
	init1();
}
function next1(){
	if(pageCount1 == pageMax1){
		alert("已是尾页！");
		return;
	}
	pageCount1++;
	init1();
}
function last1(){
	if(pageCount1 == pageMax1){
		alert("已是尾页！");
		return;
	}
	pageCount1 = pageMax1;
	init1();
}

function openEditDiv(videoId){
	$("#videoId").val(videoId);
	init1();
//	$("#pageSelect1").change(function(){
//		pageCount1 = 1;
//		init1();
//	});
	initFirstClasstify1();
	$("#videoFirClass1").change(initSecondClasstify1);
	$("#editDiv").show();
}

function closeEditDiv(){
	$("#videoId").val("");
	$("#editDiv").hide();
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