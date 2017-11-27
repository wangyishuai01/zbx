<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统管理</title>
<style type="text/css">
*{
	margin: auto;
	padding: auto;
}
.space{
	margin: 20px 30px;
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
								<h5 class="widget-caption">系统管理</h5>
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
										<a onclick="openEditLoginPwdDiv();" class="btn btn-primary space"> 
											修改登录密码
										</a>
										<!-- <a onclick="openAddBUserDiv();" class="btn btn-primary space"> 
											添加后台管理用户
										</a>
										<a onclick="" class="btn btn-primary space"> 
											后台管理用户权限管理
										</a> -->
									</div>
								</div>
							</div>
							
						</div>
					</div>
				</div>
				<!-- 修改登录密码 -->
				<div class="modal modal-darkorange" id="editLoginPwdDiv">
					<div class="modal-dialog" style="margin: 100px auto;width:400px;">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeEditLoginPwdDiv();">×</button>
								<h4 class="modal-title">修改登录密码</h4>
							</div>
							<div class="modal-body">
								<div class="bootbox-body">
									<div class="row" style="padding: 10px;">
										<div class="col-lg-12 col-sm-12 col-xs-12">
											<div class="col-md-12">
												用&nbsp;户&nbsp;名：&nbsp;&nbsp;<input type="text" readonly="readonly" id="code" class="form-control" style="width:75%;">
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												新&nbsp;密&nbsp;码：&nbsp;&nbsp;<input type="password" id="password" class="form-control" style="width:75%;">
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												确认密码：&nbsp;<input type="password" id="repassword" class="form-control" style="width:75%;">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-default" type="button"
									onclick="saveLoginPwd();">保存</button>
								<button data-dismiss="modal" class="btn btn-default"
									onclick="closeEditLoginPwdDiv();" type="button">取消</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- 添加后台用户 -->
				<div class="modal modal-darkorange" id="addBUserDiv">
					<div class="modal-dialog" style="margin: 100px auto;width:400px;">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button" onclick="closeAddBUserDiv();">×</button>
								<h4 class="modal-title">添加后台管理用户</h4>
							</div>
							<div class="modal-body">
								<div class="bootbox-body">
									<div class="row" style="padding: 10px;">
										<div class="col-lg-12 col-sm-12 col-xs-12">
											<div class="col-md-12">
												用&nbsp;户&nbsp;名：&nbsp;&nbsp;<input type="text" id="Addcode" class="form-control" style="width:75%;">
													<input type="hidden" id="customerId" value="">
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												真实姓名：&nbsp;<input type="text" id="Addname" class="form-control" style="width:75%;">
													<input type="hidden" id="customerId" value="">
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：&nbsp;&nbsp;<input type="password" id="Addpassword" class="form-control" style="width:75%;">
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												确认密码：&nbsp;<input type="password" id="repassword" class="form-control" style="width:75%;">
											</div>
											<br>&nbsp;<br>
											<div class="col-md-12">
												权限等级：&nbsp;<select type="password" id="Addpower" class="form-control" style="width:75%;">
															 	<option value="A" selected="selected">A</option>
															 	<option value="B">B</option>
															 	<option value="C">C</option>
															 	<option value="D">D</option>
															 </select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-default" type="button"
									onclick="saveAddBUserDiv();">保存</button>
								<button data-dismiss="modal" class="btn btn-default"
									onclick="closeAddBUserDiv();" type="button">取消</button>
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
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

function openEditLoginPwdDiv(){
	var code = getCookie("username");
	if(code == null){
		alert("获取登录用户失败！请重新登录！");
		return;
	}
	$("#code").val(code);
	$("#editLoginPwdDiv").show();
}
function closeEditLoginPwdDiv(){
	$("#editLoginPwdDiv").hide();
}
function saveLoginPwd(){
	var code = $("#code").val();
	var password = $("#password").val();
	var rePassword = $("#repassword").val();
	if(password != rePassword){
		alert("两次密码不一致！");
		return;
	}
	if(!(password.length>=6&&password.length<=11)){
		alert("密码长度为6~11位！");
		return;
	}
	if(code != ""){
		$.ajax({
			url : rootPath+"/bUser/editLoginPwd.do",
			type : "post",
			dataType : "json",
			data : {
				"userCode" : code,
				"password" : password
			},
			success : function(result) {
				result = JSON.parse(result);
				if(result.success){
					alert("修改成功！");
					closeEditLoginPwdDiv();
				} else {
					alert("修改失败，原因："+result.errorMsg);
				}
			}
		});
	} else {
		alert("用户为空！");
	}
	$("#editLoginPwdDiv").hide();
}


function openAddBUserDiv(){
	$("#addBUserDiv").show();
}
function closeAddBUserDiv(){
	$("#addBUserDiv").hide();
}
function saveAddBUserDiv(){
	$("#addBUserDiv").hide();
}
</script>
</html>