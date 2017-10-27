/*
 * Created by zywayh on 2016/8/13
 */

$(function(){
	//初始化赋值	
	$(".head_img").attr("src",window.localStorage.userHeaderImg);
	$(".nick").val(window.localStorage.userNick);
	$(".name").val( window.localStorage.userName);
	$(".email").val(window.localStorage.userEmail);
	
	//文件上传
  	$(function(){
  		
		$("input[type='file']").change(function() {
			var _this = $(this);
			var url_arr = $(this).val().split('.');
            var suffix = url_arr[url_arr.length-1];
            suffix = suffix.toLocaleLowerCase();
            alert(suffix)
            if(suffix == "png" || suffix == "jpg" || suffix == "gif"){
            	_this.parent(".img").parent(".l_main").siblings(".l_prompt").html("支持png，jpg，gif后缀的格式").css({"color":"#ccc"});
            	$.ajaxFileUpload({
    	            url: getContextPath() + "/upload/upload",	//服务器路径
    	            dataType: 'text/html', 						//返回值类型，一般设置为json、application/json
    	            element:$(this),
    	            success: function(data, status){
    	            	$(".head_img").attr("src",data);
    	            },error: function(data, status, e){
    	            	alert("网络错误!!!");
    	            }
    	        });
            }else{
            	_this.parent(".img").parent(".l_main").siblings(".l_prompt").html("文件格式错误，仅支持png，jpg，gif后缀的格式").css({"color":"red"});
            }
	    });
		
		var save_user_btn_type = 0;
		$(".save_user").click(function(){
			if(save_user_btn_type == 0){
				save_user_btn_type = 1;
				var head_img = $(".head_img").attr("src");
				var nick = $(".nick").val();
				var name = $(".name").val();
				var email = $(".email").val();
				if(nick == "" || nick == null || nick.lenght <= 0){
					save_user_btn_type = 0;
					$(".nick").parent().parent().siblings(".l_prompt").html("昵称不能为空").css({"color":"red"});
				}else{
					$(".nick").parent().parent().siblings(".l_prompt").html("昵称不能为空").css({"color":"#ccc"});
					$.ajax({
			        	type:"post",
			            url: getContextPath() + "/user/update",	//服务器路径
			            data:{"userHeaderImg":head_img,"userNick":nick,"userName":name,"userEmail":email},
			            dataType: 'json', 						//返回值类型，一般设置为json、application/json
			            headers:{
			            	"token":window.localStorage.token
			            },success: function(data){
			                if(data.success){
			            		window.localStorage.userHeaderImg = head_img;
			            		window.localStorage.userNick = nick;
			            		window.localStorage.userName = name;
			            		window.localStorage.userEmail = email;
			            		window.location.href="index.html";
			                }else{
			                	save_user_btn_type = 0;
			                	alert("修改失败：" + data.msg);
			                }
			            },error: function(data, status, e){
			            	save_user_btn_type = 0;
			            	alert("网络错误!!!");
			            }
			        });
				}
			}
		});
		
		
	});
})
