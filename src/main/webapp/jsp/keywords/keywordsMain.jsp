<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关键词管理</title>
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
					<div style="float:center;width:80%">
						<div class="widget">
						<div class="widget-header ">
								<h5 class="widget-caption">关键词管理</h5>
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
										<a onclick="addTClassify();" class="btn btn-primary"> 
											<i class="fa fa-plus"></i> 添加关键词
										</a>
									</div>
								</div>
								<div class="table-toolbar">
									<span>关键词名称：</span>
									<input maxlength="20" type="text" id="keyWordsName" class="form-control" style="width:250px;">
									<a class="btn btn-default shiny" onclick="query();">查询</a>
								</div>
								<table class="table table-bordered table-striped table-condensed table-hover flip-content" >
									<thead class="flip-content bordered-darkorange">
										<tr role="row">
											<th width="5%" style="text-align: center;">序号</th>
											<th width="50%">关键词</th>
											<th width="15%" style="text-align: center;">文章数</th>
											<th width="15%" style="text-align: center;">视频数</th>
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
					<div class="modal-dialog" style="margin: 60px auto;width:700px;">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeEditDiv();">×</button>
								<h4 class="modal-title">编辑分类信息</h4>
							</div>
							<div class="modal-body">
								<div class="bootbox-body">
									<div class="row" style="padding: 10px;">
										<div class="col-lg-12 col-sm-12 col-xs-12">
											<div class="col-md-12">
												<div class="col-lg-6">分类名称：</div>
												<div class="col-lg-6">
													<span class="input-icon icon-right"> <input
														type="text"
														id="name" name="name" class="form-control" style="width:100%;">
													</span>
												</div>
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												<div class="col-lg-6">分类上级关键词：</div>
												<div class="col-lg-6">
													<select class="form-control" id="Pid"></select> 
												</div>
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												<div class="col-lg-6" >是否显示：</div>
												<div class="col-lg-6">
													<div id="isDisplay">
														<label> <input  type="radio"
															id="isDisplay1" name="isDisplay" value="1" checked="checked"> <span
															class="text">显示</span>
														</label> <label> <input  type="radio"
															id="isDisplay0" name="isDisplay" value="0"> <span
															class="text">不显示</span>
														</label>
													</div>
												</div>
											</div>
											<input type="hidden" id="classId" value="">
											<input type="hidden" id="pid" value="">
										</div>
									</div>
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
				<!-- add -->
				<div class="modal modal-darkorange" id="addDiv">
					<div class="modal-dialog" style="margin: 100px auto;width:500px;">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeAddDiv();">×</button>
								<h4 class="modal-title">添加关键词</h4>
							</div>
							<div class="modal-body">
								<div class="bootbox-body">
									<div class="row" style="padding: 10px;">
										<div class="col-lg-12 col-sm-12 col-xs-12">
											<div class="col-md-12">
												<div class="col-lg-4">关 键 词 名 称 ：</div>
												<div class="col-lg-8">
													<span class="input-icon icon-right"> 
														<input type="text" id="Addname" name="Addname" class="form-control" value="" style="width:100%;">
													</span>
												</div>
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												<div class="col-lg-4">关 键 词 描 述 ：</div>
												<div class="col-lg-8">
													<span class="input-icon icon-right"> 
														<textarea  id="Addexcerpt" name="Addexcerpt" class="form-control" 
															rows="3" value="" style="width:100%;"></textarea>
													</span>
												</div>
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												<div class="col-lg-4" >状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</div>
												<div class="col-lg-8">  
													<div id="Addstate">
														<label> <input  type="radio"
															id="Addstate1" name="Addstate" value="1" checked="checked"> <span
															class="text">启用</span>
														</label> <label> <input  type="radio"
															id="Addstate0" name="Addstate" value="0"> <span
															class="text">禁用</span>
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-default" type="button"
									onclick="saveAdd();">保存</button>
								<button data-dismiss="modal" class="btn btn-default"
									onclick="closeAddDiv();" type="button">取消</button>
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
	var keyWordsName = $("#keyWordsName").val();
	$.ajax({
		url : rootPath+"/keyWords/initKeyWordsMain.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize,
			"keyWordsName" : keyWordsName
		},
		success : function(result) {
			$("tr[class='keyWords_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					var str = "<tr id='keyWords_tr"+res.id+"' class='keyWords_tr'>"
							+ "<th style='text-align: center;'>"+((pageCount-1)*pageSize+(i+1))+"</th>"
							+ "<th>"+res.name+"</th>"
							+ "<th style='text-align: center;'>"+res.articleCount+"</th>"
							+ "<th style='text-align: center;'>"+res.videoCount+"</th>"
							+ "<th style='text-align: center;'><a href='javascript:editKeyWords("+res.id+")'>编辑</a> "
							+ "| <a href='javascript:deleteKeyWords("+res.id+")'>删除</a><br>";
					str += "</tr>";
					$('.table').find('tbody').append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
}
function editKeyWords(id){
	$("#keyWordsId").val(id);
	$.ajax({
		url : rootPath+"/tclassify/selectClassifyByParam.do",
		type : "post",
		dataType : "json",
		data : {
			"id" : classid 
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				var data = result.data;
				for(var i=0; i<data.length; i++){
					var res = data[i];
					$("#name").val(res.name);
					$("#pid").val(res.pid);
					var isDisplayStr = "";
					if(res.isdisplay == "1"){
						isDisplayStr = "<label><input type='radio' id='isDisplay1' name='isDisplay' value='1' checked='checked'><span class='text'>显示</span></label>"
									 + "&nbsp;&nbsp;<label><input  type='radio' id='isDisplay0' name='isDisplay' value='0'><span class='text'>不显示</span></label>";
					} else {
						isDisplayStr = "<label><input type='radio' id='isDisplay1' name='isDisplay' value='1'><span class='text'>显示</span></label>"
							 + "&nbsp;&nbsp;<label><input checked='checked' type='radio' id='isDisplay0' name='isDisplay' value='0'><span class='text'>不显示</span></label>";
					}
					$("#isDisplay").html(isDisplayStr);
				}
				var data1 = result.pdata;
				var pid = $("#pid").val();
				if(pid == "0"){
					$("#Pid").html("<option selected='selected' value='0'>一级分类</option>");
				} else {
					$("#Pid").html("<option value='0'>一级分类</option>");
				}
				var option = "";
				for(var i=0; i<data1.length; i++){
					var res = data1[i];
					if(classid != res.id){
						if(pid == res.id){
							option = "<option selected='selected' value='"+res.id+"'>"+res.name+"</option>";
						} else {
							option = "<option value='"+res.id+"'>"+res.name+"</option>";
						}
						$("#Pid").append(option);
					}
				}
			}
		}
	});
	$("#editDiv").show();
}
function saveEdit(){
	var isdisplay = $("#isDisplay input[checked='checked']").val();
	$.ajax({
		url : rootPath+"/tclassify/updateSelectById.do",
		type : "post",
		dataType : "json",
		data : {
			"id" : $("#classId").val(),
			"name" : $("#name").val(),
			"pid" : $("#Pid").val(),
			"isdisplay" : isdisplay
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				alert("修改成功！");
				init();
			} else {
				alert("修改失败！");
			}
			$("#editDiv").hide();
		}
	});
}
function closeEditDiv(){
	$("#editDiv").hide();
}
function deleteClassify(classid){
	if(confirm("你确信要删除此评论吗？")){
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
	$("#Addname").val("");
	$("#Addexcerpt").html("");
	$("#Addstate input[type='radio']:eq(0)").attr("checked","checked");
	$("#addDiv").show();
}
function saveAdd(){
	if($("#Addname").val() == ""){
		alert("关键词名字不能为空！");
		return false;
	}
	var state = $("#Addstate input[type='radio']:checked").val();
	$.ajax({
		url : rootPath+"/keyWords/addKeyWords.do",
		type : "post",
		dataType : "json",
		data : {
			"name" : $("#Addname").val(),
			"excerpt" : $("#Addexcerpt").val(),
			"state" : state
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				alert("添加成功！");
				init();
			} else {
				alert("添加失败！");
			}
			$("#addDiv").hide();
		}
	});
}
function closeAddDiv(){
	$("#addDiv").hide();
}

function query(){
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

$(document).ready(function(){
	init();
	$("#pageSelect").change(function(){
		pageCount = 1;
		init();
	});
});
</script>
</html>