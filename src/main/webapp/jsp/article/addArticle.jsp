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
								<h5 class="widget-caption">新增文章</h5>
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
												文章标题：<input type="text" id="title" name="title" class="form-control" style="width:76%;">
											</span>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-6">
											文章类别：
											<select id="articleFirClass" class="form-control" style="width:50%;"></select>
										</div>
										<div class="col-md-6">
											文章类型：
											<select id="articleSecClass" class="form-control" style="width:50%;"><option value=''>--请选择--</option></select>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-6">
											状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
											<select id="state" class="form-control" style="width:50%;">
												<option value="1" selected="selected">启用</option>
												<option value="0">禁用</option>
											</select>
										</div>
										<div class="col-md-6">
											评论权限：
											<select id="nocomment" class="form-control" style="width:50%;">
												<option value="0" selected="selected">禁止评论</option>
												<option value="1">允许评论</option>
											</select>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-12">
											<label style="line-height:90px;margin-top:-70px;">文章摘要：</label>
											<textarea id="excerpt" name="excerpt" class="form-control" style="width:76%;height:90px;resize:none;" 
													maxlength="200" onchange="this.value=this.value.substring(0, 200)" 
													onkeydown="this.value=this.value.substring(0, 200)" 
													onkeyup="this.value=this.value.substring(0, 200)"></textarea>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-12">
											<span class="input-icon icon-right"> 
												文章标签：
												<input type="text" id="keyWords" placeholder="（添加Tag，你的内容能被更多人看到）" name="keyWords" 
														class="form-control" style="width:50%;">
												（最多添加5个标签，多个标签之间用“，”隔开）
											</span>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12">
										<div class="col-md-6">
											<span class="input-icon icon-right"> 
												是否免费：
												<input type="radio" name="isFree" checked="checked" value="1">是&nbsp;&nbsp;
												<input type="radio" name="isFree" value="0">否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label id="articlePriceLable" style="display: none">
													单价：<input type="text" id="articlePrice" class="form-control" style="width: 50%;"
																onkeyup="value=value.replace(/[^0-9\.]/g,'');" onpaste="return false;"
										           				placeholder="输入数字">元
												</label>
											</span>
										</div>
										<div class="col-md-6">
											<span class="input-icon icon-right" onmouseover="this.style.cursor='pointer'"> 
												<a onclick="openEditArticleDiv()">编辑文章内容</a>
												<input type="hidden" id="content" value="">
											</span>
										</div>
									</div>
									<br>&nbsp;<br>
									<div class="col-md-12" id="editButton">
										<div style="float: right;margin-right: 18%;">
											<button class="btn btn-default shiny" type="button"
												onclick="saveAdd();">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button data-dismiss="modal" class="btn btn-default shiny"
												onclick='returnArticleMain();' type="button">取消</button>
										</div>
									</div>
									<br>&nbsp;<br>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- editArticle -->
				<div class="modal modal-darkorange" id="editArticleDiv">
					<div class="modal-dialog" style="margin:0px auto;width:100%;height:100%;">
						<div class="modal-content" style="height:100%;">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeEditArticleDiv();">×</button>
								<h4 class="modal-title">编辑文章</h4>
							</div>
							<div class="modal-body" style="height:84%;">
								<div class="bootbox-body" style="height:100%;">
									<div class="row" style="padding: 10px;height:100%;" >
										<div style="width: 100%;height: 100%;overflow: auto">
											<script id="editor" type="text/plain" style="width:100%;margin-left:0px;height:75%;"></script>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-default" type="button"
									onclick="returnArticleInfo();">确定</button>
								<button data-dismiss="modal" class="btn btn-default"
									onclick="closeEditArticleDiv();" type="button">返回</button>
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
var ue;
var rootPath = "${pageContext.request.contextPath}";

function returnArticleMain(){
	openUrl("/jsp/article/articleMain.jsp");
}
  
function openEditArticleDiv(){
	$("#editArticleDiv").show();
}  
function closeEditArticleDiv(){
	$("#editArticleDiv").hide();
}
function returnArticleInfo(){
	var content = UE.getEditor('editor').getContent();
	$("#content").val(content);
	$("#editArticleDiv").hide();
	ue.setContent('');
}

function saveAddCheck(){
	if($("#title").val() == ""){
		alert("请填写文章标题！");
		return false;
	}
	if($("#title").val().length > 50){
		alert("文章标题长度不能大于50！");
		return false;
	}
	if($("#articleSecClass").val() == "" || $("#articleSecClass").val() == null){
		alert("请选择文章类型！");
		return false;
	}
	var keyWordsStr = $("#keyWords").val();
	var index = keyWordsStr.indexOf("，");
	if(index < 0){
		var keyWords = keyWordsStr.split(",");
		if(keyWords.length > 5){
			alert("最多添加5个标签！");
			return false;
		}
	} else {
		alert("标签中的逗号请用英文逗号！");
		return false;
	}
	if($("input[name='isFree']:checked").val() == "0"){
		if($("#articlePrice").val() <= 0){
			alert("价格必须是正数！");
			return false;
		}
	}
	return true;
}

function saveAdd(){
	if(saveAddCheck()){
		var title = $("#title").val();
		var isCanComment = $("#nocomment").val();
		var isFree = $("input[name='isFree']:checked").val();
		var state = $("#state").val();
		var articlePrice =  $("#articlePrice").val();
		var classId = $("#articleSecClass").val();
		var excerpt = $("#excerpt").val();
		var content = $("#content").val();
		var keyWords = $("#keyWords").val();
		$.ajax({
			url : rootPath+"/Article/addArticleInfo.do",
			type : "post",
			dataType : "json",
			data : {
				"title" : title,
				"classid" : classId,
				"nocomment" : isCanComment,
				"isfree" : isFree,
				"state" : state,
				"excerpt" : excerpt,
				"content" : content, 
				"articlePrice" : articlePrice,
				"keyWords" : keyWords
			},
			success : function(result) {
				result = JSON.parse(result);
				if(result.success){
					alert("添加成功！");
					returnArticleMain();
				} else {
					alert("添加失败！");
				}
			}
		});
	}
}

function initFirstClasstify(){
	var articleFirClassOld = $("#articleFirClassOld").val();
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
					if(articleFirClassOld == res.id){
						option += "<option value='"+res.id+"' selected='selected'>"+res.name+"</option>";
					} else {
						option += "<option value='"+res.id+"'>"+res.name+"</option>";
					}
				}
				$("#articleFirClass").html(option);
			}
		}
	});
}
function initSecondClasstify(){
	var articleSecClassOld = $("#articleSecClassOld").val();
	var firstClassifyId = $("#articleFirClass").val()==null ? $("#articleFirClassOld").val() : $("#articleFirClass").val();
	if(firstClassifyId != '' && firstClassifyId != null){
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
						if(articleSecClassOld == res.id){
							option += "<option value='"+res.id+"' selected='selected'>"+res.name+"</option>";
						} else {
							option += "<option value='"+res.id+"'>"+res.name+"</option>";
						}
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

$(document).ready(function(){
	initFirstClasstify();
	$("#articleFirClass").change(initSecondClasstify);
	$("input[name='isFree']").click(function(){
		if($(this).val()=="1"){
			$("#articlePriceLable").hide();
		} else {
			$("#articlePriceLable").show();
		}
	});
	//创建编辑器
	ue = UE.getEditor('editor');
});
</script>
</html>