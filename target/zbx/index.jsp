<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>aaa</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/ajaxfileupload.js"></script>
    <script type="text/javascript">
		$(function(){
			//获取项目名称
			function getContextPath(){ 
				var pathName = document.location.pathname; 
				var index = pathName.substr(1).indexOf("/"); 
				var result = pathName.substr(0,index+1); 
				return result; 
			} 
			//获取参数
			function GetQueryString(name){
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  unescape(r[2]); return null;
			}
			
			$("input[type='file']").change(function() {
		        $.ajaxFileUpload({
		            url: getContextPath() + "/upload/upload",	//服务器路径
		            dataType: 'text/html', 						//返回值类型，一般设置为json、application/json
		            element:$(this),
		            success: function(data, status){
		                $(".fileImg").attr("src",data);
		            },error: function(data, status, e){
		            	alert("网络错误!!!");
		            }
		        });
		    });
			$(".testheaderbtn").click(function() {
		        $.ajax({
		        	type:"post",
		            url: getContextPath() + "/user/getUserByToken",	//服务器路径
		            dataType: 'json', 						//返回值类型，一般设置为json、application/json
		            element:$(this),
		            headers:{
		            	"token":"qweqweqwe"
		            },success: function(data, status){
		                alert(data);
		                alert(data.success);
		                alert(data.data);
		            },error: function(data, status, e){
		            	alert("网络错误!!!");
		            }
		        });
		    });
		});
	</script>
  </head>
  <body>
  	<input type="file" name="file" /><br/>
  	<img src="" style="border: 1px solid red; height:150px;" class="fileImg"/><br/>
	<img class="img_code" src="/zbx/user/getImgCode?w=200&h=50" onclick="this.src=getContextPath()+'/user/getImgCode?w=200&h=50&time='+new Date().getTime()"/>
  	<input type="button" value="testheaderbtn" class="testheaderbtn"/><br/>
  </body>
</html>