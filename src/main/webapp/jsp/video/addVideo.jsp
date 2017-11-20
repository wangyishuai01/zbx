<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频管理</title>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
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
<link href="${ctx }/css/publicUpload.css" rel="stylesheet"/>
</head>
<body>
<form action="${ctx }/upload/uploadVideo.do" class="upload" method="post" enctype="multipart/form-data">
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">
			<!-- Page Body -->
			<div class="page-body" id="pageBodyRight">
				<div class="row">
					<div style="float:center;width:80%">
						<div class="widget">
						<div class="widget-header ">
								<h5 class="widget-caption">新增视频</h5>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize"></a> <a href="#"
										data-toggle="collapse" onclick="tab('pro');"> <i
										class="fa fa-minus" id="pro-i"></i>
									</a> <a href="#" data-toggle="dispose"></a>
								</div>
							</div>
							<div class="widget-body" id="pro">
								<div style="margin-left: 11%;">
									<div class="col-md-12">
										<div class="col-md-12">
											<span class="input-icon icon-right"> 
												视频名称：<input type="text" id="videoName" name="videoName" class="form-control" style="width:76%;">
											</span>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-12">
											<span class="input-icon icon-right"> 
												选择文件：
												<input type="text" id="videoPath" disabled="disabled" name="videoPath" class="form-control" 
													style="width:68%;">
												<input type="file" id="videoFile" accept="video/*" onchange="selectFileAfterInitInfo(this);" 
													name="videoFile" style="width:10%;display: none">
												<button class="btn btn-default shiny" type="button" onclick="javascript:$('#videoFile').click();" style="margin-top:-5px;">
													浏&nbsp;&nbsp;&nbsp;览</button>
											</span>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-6">
											大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;小：
											<input type="text" id="videoSize" disabled="disabled" class="form-control" style="width:50%;">M
										</div>
										<div class="col-md-6">
											格&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;式：
											<input type="text" id="videoSuffix" disabled="disabled" class="form-control" style="width:50%;">
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-1">
											<button id="button" class="btn btn-default shiny" type="button">上传视频</button>
										</div>
										<div class="col-md-11">
											<div class="progress">
											    <div class="bar"></div>
											    <div class="percent">0%</div>
											</div>
											<div class="status" id="status"></div>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-6">
											<span class="input-icon icon-right"> 
												是否免费：
												<input type="radio" name="isFree" checked="checked" value="1">是&nbsp;&nbsp;
												<input type="radio" name="isFree" value="0">否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label id="videoPriceLable" style="display: none">
													单价：<input type="text" id="videoPrice" class="form-control" style="width: 35%;"
																onkeyup="value=value.replace(/[^0-9\.]/g,'');" onpaste="return false;"
										           				placeholder="输入数字">元
													<input type="hidden" id="videoPriceId" value="">
												</label>
											</span>
										</div>
										<div class="col-md-6">
											评论权限：
											<select id="isCanComment" class="form-control" style="width:50%;">
												<option selected="selected" value="0">禁止评论</option>
												<option value="1">允许评论</option>
											</select>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-12">
											<span class="input-icon icon-right"> 
												关联文章：
												<input type="text" id="relationArticleName" readonly="readonly" class="form-control" 
													style="width:76%;" placeholder="双击选择关联的文章">
												<input type="hidden" id="relationArticleId" value="">
											</span>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-6">
											视频类别：
											<select id="videoFirClass1" disabled="disabled" class="form-control" style="width:50%;"></select>
										</div>
										<div class="col-md-6">
											视频类型：
											<select id="videoSecClass1" disabled="disabled" class="form-control" style="width:50%;"></select>
										</div>
									</div>
									<br>&nbsp;<br>
									<input type="hidden" id="videoId" value="">
									<div class="col-md-12" id="addButton">
										<div style="float: right;margin-right: 18%;">
											<button class="btn btn-default shiny" type="button"
												onclick="saveAddVideoInfo();">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button data-dismiss="modal" class="btn btn-default shiny"
												onclick='returnVideoMain();' type="button">取消</button>
										</div>
									</div>
									<br>&nbsp;<br>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- edit -->
				<div class="modal modal-darkorange" id="editRelationArticleDiv">
					<div class="modal-dialog" style="margin: 60px auto;width:850px;">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeEditRelationArticleDiv();">×</button>
								<h4 class="modal-title">更新关联文章</h4>
							</div>
							<div class="modal-body">
								<div class="bootbox-body">
									<div class="row" style="padding: 10px;">
										<div class="table-toolbar">
											<span>文章类别：</span>
											<select id="videoFirClass" class="form-control" style="width:200px;"></select>
											<span>文章类型：</span>
											<select id="videoSecClass" class="form-control" style="width:200px;"><option value=''>--请选择--</option></select>
											<span>文章状态：</span>
											<select id="articleState" class="form-control" style="width:200px;">
												<option value=''>--请选择--</option>
												<option value='1'>启用</option>
												<option value='0'>禁用</option>
											</select>
											<br>&nbsp;<br>
											<span>文章标题：</span>
											<input maxlength="20" type="text" id="articleTitle" class="form-control" style="width:200px;">
											<a class="btn btn-default shiny" onclick="query();">查询</a>
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
										<div class="page">
											<input value="首页" type="button" onclick="first()"/>
											<input value="上一页" type="button" onclick="previous()"/>
											<input value="下一页" type="button" onclick="next()"/>
											<input value="尾页" type="button" onclick="last()"/>
										</div>
									</div>
									<input type="hidden" id="videoId" value="">
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-default" type="button"
									onclick="returnRelationArticleInfo();">确定</button>
								<button data-dismiss="modal" class="btn btn-default"
									onclick="closeEditRelationArticleDiv();" type="button">取消</button>
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
</form>
</body>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}";

function init(videoId){
	
}

function checkVideoFile(suffix) {
    if (!suffix.match(/.mp4|.mp/i)) {
        return false;
    }
    return true;
}
function selectFileAfterInitInfo(file){
	var filePath = file.value;
    var fileSuffix = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
	if (!checkVideoFile(fileSuffix)) {
        alert("您上传的文件不是视频,请重新上传！");
        file.value = "";
        return;
    }
	var sFiles = file.files;  
    //上次修改时间  
//     alert(sFiles[0].lastModifiedDate);  
     //名称  
//     alert(sFiles[0].name);  
     //大小 字节  
//     alert(sFiles[0].size);  
     //类型  
//     alert(sFiles[0].type);
	$('#videoPath').val(filePath);
	$('#videoSize').val((sFiles[0].size / 1024 / 1024).toFixed(0));
	$('#videoSuffix').val(fileSuffix);
}

var pageCount = 1, pageSize = 5, pageMax = 100;
function queryArticleInfo(){
	var articleTitle = $("#articleTitle").val();
	var classifyId = $("#videoSecClass").val();
	var articleState = $("#articleState").val();
	$.ajax({
		url : rootPath+"/Article/selectArticleMainByParam.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize,
			"title" : articleTitle,
			"classid" : classifyId,
			"state" : articleState
		},
		success : function(result) {
			$("tr[class='article_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					var str = "<tr id='article_tr"+res.id+"' class='article_tr'>"
							+ "<th style='text-align: center;'><input type='radio' value='"+res.id+"' name='article_tr' classId='"+res.classid+"'></th>"
							+ "<th style='text-align: center;'>"+((pageCount-1)*pageSize+(i+1))+"</th>"
							+ "<th style='max-width: 300px' class='line-limit-length'>"+res.title+"</th>"
							+ "<th style='max-width: 342px' class='line-limit-length'>"+((res.excerpt==undefined||res.excerpt=='')?"暂无":res.excerpt)+"</th>";
					if(res.state == "1"){
						str += "<th style='text-align: center;'>启用</th>";
					} else {
						str += "<th style='text-align: center;color: red;'>禁用</th>";
					}
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
		alert("请选择文章类型！");
		return false;
	}
	pageCount = 1;
	queryArticleInfo();
}

function queryRelationArticleInfo(articleId){
	$.ajax({
		url : rootPath+"/Article/selectClassifyByArticleId.do",
		type : "post",
		dataType : "json",
		data : {
			"articleId" : articleId 
		},
		success : function(result) {
			result = JSON.parse(result);
			var firOption = "", secOption = "";
			if(result.success){
				var res = result.data;
				firOption += "<option value='"+res.cid+"'>"+res.cname+"</option>";
				secOption += "<option value='"+res.pcid+"'>"+res.pcname+"</option>";
				$("#videoFirClass1").html(firOption);
				$("#videoSecClass1").html(secOption);
				$("#relationArticleId").val(res.id);
				$("#relationArticleName").val(res.title+"（双击修改）");
				closeEditRelationArticleDiv();
			} else {
				alert("查询失败！");
			}
		}
	});
}

function returnRelationArticleInfo(){
	var articleId = $("input[name='article_tr']:checked").val();
	if(articleId != ""){
		queryRelationArticleInfo(articleId);
	} else {
		alert("请选择关联文章！");
	}
}

function saveEditCheck(){
	if($("#videoName").val() == ""){
		alert("视频名字不能为空！");
		return false;
	}
	if($("#videoName").val().length > 50){
		alert("视频名字长度不能大于50！");
		return false;
	}
	if($("input[name='isFree']:checked").val() == "0"){
		if($("#videoPrice").val() <= 0){
			alert("价格必须是正数！");
			return false;
		}
	}
	if($('.percent').text() != "100%"){
		alert("请先上传视频！");
		return false;
	}
	if($("#videoSecClass1").val() == "" || $("#videoSecClass1").val() == null){
		alert("请选择视频关联的文章！");
		return false;
	}
	return true;
}

function saveAddVideoInfo(){
	if(saveEditCheck()){
		var videoName = $("#videoName").val();
		var videoSize = $("#videoSize").val();
		var videoSuffix = $("#videoSuffix").val();
		var isCanComment = $("#isCanComment").val();
		var isFree = $("input[name='isFree']:checked").val();
		var videoPath = $("#videoPath").val();
		var videoPrice =  $("#videoPrice").val();
		var relationArticleId = $("#relationArticleId").val();
		$.ajax({
			url : rootPath+"/video/addVideoInfo.do",
			type : "post",
			dataType : "json",
			data : {
				"title" : videoName,
				"articleid" : relationArticleId,
				"suffix" : videoSuffix,
				"size" : videoSize,
				"nocomment" : isCanComment,
				"isfree" : isFree,
				"videopath" : videoPath,
				"videoPrice" : videoPrice
			},
			success : function(result) {
				result = JSON.parse(result);
				if(result.success){
					alert("保存成功！");
					returnVideoMain();
				} else {
					alert("保存失败！");
				}
			}
		});
	}
}

function openEditRelationArticleDiv(){
	queryArticleInfo();
	initFirstClasstify();
	$("#videoFirClass").change(initSecondClasstify);
	$("#editRelationArticleDiv").show();
}

function closeEditRelationArticleDiv(){
	$("#editRelationArticleDiv").hide();
}

function returnVideoMain(){
	openUrl("/jsp/video/videoMain.jsp");
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

//ajaxSubmit 上传视频
function uploadVideo(){
	if($("#videoPath").val() == null || $("#videoPath").val() == ""){
		alert("请选择视频文件！");
		return false;
	}
	var bar = $('.bar');
    var percent = $('.percent');
    var status = $('#status');
	$("form").ajaxSubmit({
        type: 'post', // 提交方式 get/post
        url: rootPath+"/upload/uploadVideo.do", // 需要提交的 url
        data: {
            'title': "",
            'content': ""
        },
        beforeSerialize:function(){//表单数据序列化前执行的操作
            //$("#txt2").val("java");//如：改变元素的值
        },
        beforeSubmit:function(){//表单提交前的操作
            var filesize = $("#videoFile")[0].files[0].size/1024/1024;
            /* if(filesize > 50){
                alert("文件大小超过限制，最多50M");
                return false;
            } */
            //if($("#txt1").val()==""){return false;}//如：验证表单数据是否为空
        },
        beforeSend: function() {
            status.empty();
            var percentVal = '0%';
            bar.width(percentVal);
            percent.html(percentVal);
        },
        uploadProgress: function(event, position, total, percentComplete) {//上传的过程
            //position 已上传了多少
            //total 总大小
            //已上传的百分数
            var percentVal = percentComplete + '%';
            bar.width(percentVal);
            percent.html(percentVal);
        },
        success: function(data) {//成功
        	var result = JSON.parse(data);
			if(result.success){
				var percentVal = '100%';
                bar.width(percentVal);
                percent.html(percentVal);
                alert("上传成功！");
                $("#videoPath").val(result.path);
        	} else {
        		var percentVal = '0%';
                bar.width("100%");
                percent.html(percentVal);
        		alert(result.errorMsg);
        	}
        },
        error:function(err){//失败
            alert("表单提交异常！"+err.msg);
        },
        complete: function(xhr) {//完成
        	var result = JSON.parse(JSON.parse(xhr.responseText));
        	if(result.success){
        		status.html("<span>"+result.msg+"</span>");
        	} else {
        		status.html("<span style='color: red;'>"+result.errorMsg+"</span>");
        	}
        }
	});
}

$(document).ready(function(){
//	init(videoId);
	$("#relationArticleName").dblclick(openEditRelationArticleDiv);
	$("input[name='isFree']").click(function(){
		if($(this).val()=="1"){
			$("#videoPriceLable").hide();
		} else {
			$("#videoPriceLable").show();
		}
	});
	$('#button').on('click', uploadVideo);
});
</script>
</html>