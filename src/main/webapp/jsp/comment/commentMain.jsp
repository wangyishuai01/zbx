<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论管理</title>
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
	max-width: 500px;
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
								<h5 class="widget-caption">评论管理</h5>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize"></a> <a href="#"
										data-toggle="collapse" onclick="tab('pro');"> <i
										class="fa fa-minus" id="pro-i"></i>
									</a> <a href="#" data-toggle="dispose"></a>
								</div>
							</div>
							<div class="widget-body" id="pro">
								<table class="table table-bordered table-striped table-condensed table-hover flip-content" >
									<thead class="flip-content bordered-darkorange">
										<tr role="row">
											<th width="20%" style="text-align: center;">标题</th>
											<th width="30%" style="text-align: center;">内容</th>
											<th width="15%" style="text-align: center;">发布人</th>
											<th width="20%" style="text-align: center;">时间</th>
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
				<!-- show -->
				<div class="modal modal-darkorange" id="showCommentDiv">
					<div class="modal-dialog" style="margin: 60px auto;width:700px;">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeCommentDivDiv();">×</button>
								<h4 class="modal-title">评论详细信息</h4>
							</div>
							<div class="modal-body">
								<div class="bootbox-body">
									<div class="row" style="padding: 10px;">
										<div class="col-lg-12 col-sm-12 col-xs-12">
											<form id="div2Form" method="post" class="form-horizontal"
												enctype="multipart/form-data">
												<div class="col-md-12">
													<div class="col-lg-3">标题：</div>
													<div class="col-lg-9">
														<span class="input-icon icon-right"> <input
															type="text" placeholder="标题" readonly="readonly"
															id="title" name="title" class="form-control" style="width:100%;">
														</span>
													</div>
												</div>
												<br>&nbsp;<br>
												<div class="col-md-12">
													<div class="col-lg-3" >发布人：</div>
													<div class="col-lg-9">
														<span class="input-icon icon-right"> <input
															type="text" placeholder="发布人" readonly="readonly"
															id="customerName" name="customerName" class="form-control" style="width:100%;">
														</span>
													</div>
												</div>
												<br>&nbsp;<br>
												<div class="col-md-12">
													<div class="col-lg-3" >时间：</div>
													<div class="col-lg-9">
														<span class="input-icon icon-right"> <input
															type="text" placeholder="时间" readonly="readonly"
															id="makedate" name="makedate" class="form-control" style="width:100%;">
														</span>
													</div>
												</div>
												<br>&nbsp;<br>
												<div class="col-md-12">
													<div class="col-lg-3">内容：</div>
													<div class="col-lg-9">
														<span class="input-icon icon-right"> <textarea readonly="readonly" 
															id="comment"  style="width:100%;resize: none;" rows="20"></textarea> 
														</span>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button data-dismiss="modal" class="btn btn-default"
									onclick="closeCommentDivDiv();" type="button">取消</button>
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
var pageCount = 1, pageSize = 10, pageMax = 100;
function init(){
	pageSize = $("#pageSelect").val();
	$.ajax({
		url : rootPath+"/comment/initCommentMain.do",
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
							+ "<th>"+res.title+"</th><th class='line-limit-length'>"+res.comment+"</th>"
							+ "<th style='text-align: center;'>"+res.customerName+"</th>"
							+ "<th style='text-align: center;'>"+(makeDate.format('yyyy-MM-dd h:m:s'))+"</th>"
							+ "<th style='text-align: center;'><a href='javascript:deleteComment("+res.id+")'>删除</a>"
							+ " | <a href='javascript:showOneComment("+res.id+")'>查看</a></th>";
					str += "</tr>";
					$('.table').find('tbody').append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
}

function deleteComment(commentid){
	if(confirm("你确信要删除此评论吗？")){
		$.ajax({
			url : rootPath+"/comment/deleteById.do",
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
}

function addComment(){
	$.ajax({
		url : rootPath+"/comment/addComment.do",
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
function showOneComment(commentid){
	var ths = $("#comment_tr"+commentid).find("th");
	$("#title").val($(ths[0]).text());
	$("#comment").val($(ths[1]).text());
	$("#customerName").val($(ths[2]).text());
	$("#makedate").val($(ths[3]).text());
	$("#showCommentDiv").show();
}

function closeCommentDivDiv(){
	$("#showCommentDiv").hide();
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
	$("#pageSelect").change(init);
});
</script>
</html>