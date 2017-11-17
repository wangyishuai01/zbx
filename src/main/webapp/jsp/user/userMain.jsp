<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
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
								<h5 class="widget-caption">用户管理</h5>
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
											<th width="50%">用户名</th>
											<th width="15%" style="text-align: center;">状态（启用/禁用）</th>
											<th width="15%" style="text-align: center;">用户最近登录</th>
											<th width="20%" style="text-align: center;">操作</th>
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
												<div class="col-lg-6">分类上级类别：</div>
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
		url : rootPath+"/customer/initCustomerMain.do",
		type : "post",
		dataType : "json",
		data : {
			"pageCount" : pageCount,
			"pageSize" : pageSize
		},
		success : function(result) {
			$("tr[class='customer_tr']").remove();
			result = JSON.parse(result);
			if(result.success){
				pageMax = Math.floor(result.number%pageSize == 0 ? result.number/pageSize : result.number/pageSize+1);
				result = result.data;
				for(var i=0; i<result.length; i++){
					var res = result[i];
					
					var str = "<tr id='customer_tr"+res.id+"' class='customer_tr'>"
							+ "<th>"+res.name+"</th>";
					if(res.isdisable == "1"){
						str += "<th style='text-align: center;'>启用</th>";
					} else {
						str += "<th style='text-align: center;color:red;'>禁用</th>";
					}
					var loginDate = new Date();
					loginDate.setTime(res.logindate);
					if(res.logindate == null || res.logindate == "undefined"){
						str += "<th style='text-align: center;'></th>";
					} else {
						str += "<th style='text-align: center;'>"+(loginDate.format('yyyy-MM-dd h:m:s'))+"</th>";
					}
					if(res.isdisable == "1"){
						str += "<th style='text-align: center;'><a href='javascript:editIsdisable("+res.id+",0)'>禁用</a>";
					} else {
						str += "<th style='text-align: center;'><a href='javascript:editIsdisable("+res.id+",1)'>启用</a>";
					}
					str += " | <a href='javascript:return false;'>查看统计</a></th></tr>";
					$('.table').find('tbody').append(str);
				}
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
}
function editIsdisable(cusId, isdisable){
	$.ajax({
		url : rootPath+"/customer/customerEditState.do",
		type : "post",
		dataType : "json",
		data : {
			"customerId" : cusId,
			"isdisable" : isdisable
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

function closeEditDiv(){
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
});
</script>
</html>