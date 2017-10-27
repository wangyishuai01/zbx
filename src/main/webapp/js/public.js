/**
 * Created by zywayh on 2016/8/1.
 */
//点击首页资源时的事件
var resClick = function(event){
	var resId = $(this).attr("resid");
	$.ajax({
    	type:"post",
        url: getContextPath() + "/resource/checkUserIsCanLookRes",
        dataType: 'JSON', 
        element:$(this),
        data:{
        	"resId" : resId
        },
        headers:{
        	"token" : window.localStorage.token
        },
        success: function(data, status){
        	if(data.success){
        		var url = getContextPath() + "/detail.html?resId=" + resId;
        		window.open(url);
        	} else {
        		alert(data.msg);
        	}
        },
        error: function(data, status, e){
        	alert("网络错误!!!");
        }
	});
};
$(function(){
    /* 顶部浮动导航栏 */
    window.onload=function(){
        //获取token
        var token = window.localStorage.token;
        //讲头部浮动栏注入
        $("body").append("" +
            "<div class='nav'>" +
                "<div class='n_body'>" +
                    "<div class='b_logo cursor'>智博炫</div>" +
                    "<div class='b_main'>" +
                        "<ul>" +
                            "<li class='cursor index'>首页</li>" +
                            "<li class='cursor course_all'>全部课程</li>" +
                            "<li class='cursor help'>帮助 </li>" +
                        "</ul>" +
                    "</div>" +
                    "<div class='b_soso'>" +
                        "<div class='s_body'>" +
                            "<div class='b_input'>" +
                                "<input type='text' class='soso_text' placeholder='请输入要搜索的内容'>" +
                            "</div>" +
                            " <div class='b_btn'>" +
                                "<input type='button' class='cursor soso_btn' value='搜索'>" +
                            "</div>" +
                        "</div>" +
                    "</div>" +
                    "<div class='b_login'>" +
                        "<!--<div class='l_reg cursor'>注册</div>-->" +
                        "<div class='l_login cursor'>登录</div>" +
                        "<div class='login_success cursor' style='display: none;'>" +
                        	"<img class='head_img'/>" +
                        	"<span></span>" +
	                        "<div class='s_detail cursor'>" +
	                        	"<div class='update_user cursor'>修改信息</div>" +
	                            "<div class='login_out cursor'>退出登录</div>" +
	                        "</div>" +
	                    "</div>" +
                    "</div>" +
                "</div>" +
            "</div>"
        );
        $(".login_success").on("mouseover",function(){
        	$(this).children(".s_detail").show();
        }).on("mouseout",function(){
        	$(this).children(".s_detail").hide();
        });
        
        //注入登录框
        $("body").append(
            "<div class='login_win' style='background: rgba(0,0,0,0.5);position: fixed;top:0;left: 0;z-index: 1000;display:none;min-width: 400px;min-height: 300px'>" +
                "<div class='login_input' style='border-radius: 5px;width: 400px;height: 300px;background: #fff;margin: 0 auto;position:relative;'>" +
                    "<div class='l_close' style='cursor: pointer;position: absolute;top: 10px;right: 20px;width: 20px;height: 20px;text-align:center;line-height: 20px;font-size: 30px'>x</div>" +
                        "<div class='input_text' style='position: absolute;top:50%;left: 50%;margin:-90px 0 0 -125px;'>" +
                            "<div style='width: 250px;height: 35px;margin: 0 0 20px;'>" +
                                "<input class='login_phone' style='width: 100%;height: 100%;' value='15811019845' type='text' placeholder='请输入手机号'/>" +
                            "</div>" +
                        "<div style='width: 254px;height: 35px;margin: 0 0 20px;'>" +
                            "<div style='float: left;width: 150px;height: 100%;'>" +
                                "<input class='login_code' style='width: 100%;height: 100%;' type='text' placeholder='请输入验证码'/>" +
                            "</div>" +
                            "<div class='code_btn' style='float: left;width: 84px;height: 39px;margin: 0 0 0 20px;'>" +
                                "<input style='width: 100%;height: 100%;border: 0;' type='button' value='获取验证码'/>" +
                            "</div>" +
                        "</div>" +
                        "<div class='login_btn' style='width: 254px;height: 39px;'>" +
                            "<input style='width: 100%;height:100%;border: 0;' type='button' value='登录'/>" +
                        "</div>" +
                    "</div>" +
                "</div>" +
            "</div>"
         );
        
        //验证是否存在token，如果存在则表示已登录，再次跟后台验证token
        if(token){
        	$.ajax({
	        	type:"post",
	            url: getContextPath() + "/user/getUserByToken",	//服务器路径
	            dataType: 'JSON', 						//返回值类型，一般设置为json、application/json
	            element:$(this),
	            data:{},
	            headers:{
	            	"token":token
	            },success: function(data, status){
	            	if(data.success){
	            		window.localStorage.userHeaderImg = data.data.userHeaderImg;
		                window.localStorage.userNick = data.data.userNick;
		                window.localStorage.userName = data.data.userName;
		                window.localStorage.userEmail = data.data.userEmail;
	            		$(".b_login .l_login").hide();
	                    $(".login_success").show().children("span").html(window.localStorage.userNick);
	                    $(".login_success").children(".head_img").attr("src",window.localStorage.userHeaderImg);
	            	}
	            },error: function(data, status, e){
	            	alert("网络错误!!!");
	            }
        	});
        }
        //退出登录
        $(".login_out").click(function(){
        	window.localStorage.removeItem("token");
        	$(".b_login .l_login").show();
            $(".login_success").hide().children("span").html("");
        });

        //控制转跳
        $(".b_logo,.index").on("click",function(){
            window.location.href="index.html";
        });
        $(".course_all").on("click",function(){
            window.location.href="list.html";
        });
        $(".update_user").on("click",function(){
            window.location.href="userUpdate.html";
        });


        //TODO 获取验证码
        var interval ;
        var code_time_out = 90;
        var code_btn_click_type = 0;
        $(".code_btn").click(function(){
            if(code_btn_click_type == 0){
                var _this = $(this).children("input");
                var phone = $(".login_phone").val();
                if(phone && /^1[3|4|5|8]\d{9}$/.test(phone)){
                	$.ajax({
    		        	type:"post",
    		            url: getContextPath() + "/user/getCode",	//服务器路径
    		            dataType: 'json', 							//返回值类型，一般设置为json、application/json
    		            element:$(this),
    		            data:{"phone":phone},
    		            headers:{
    		            },success: function(data, status){
    		                if(data.success){
    		                	_this.val(code_time_out + "秒");
    		                    code_btn_click_type = 1;
    		                    interval = setInterval(function(){
    		                        if(code_time_out <= 0){
    		                            code_time_out = 5;
    		                            code_btn_click_type = 0;
    		                            clearInterval(interval);
    		                            _this.val("重新获取");
    		                        }else{
    		                            code_time_out --;
    		                            _this.val(code_time_out + "秒");
    		                        }
    		                    },1000);
    		                }else{
    		                	alert("短信发送失败，稍后重试")
    		                }
    		            },error: function(data, status, e){
    		            	alert("网络错误!!!");
    		            }
    		        });
                } else{
                    //alert("请输入正确的手机号")
                    alert("phone is error")
                }
            }
        });

        //TODO 登录
        var login_btn_click_type = 0;
        $(".login_btn").click(function(){
            if(login_btn_click_type == 0){
            	var phone = $(".login_phone").val();
                var code = $(".login_code").val();
                $.ajax({
		        	type:"post",
		            url: getContextPath() + "/user/loginByCode",	//服务器路径
		            dataType: 'JSON', 						//返回值类型，一般设置为json、application/json
		            element:$(this),
		            data:{"phone":phone, "code":code},
		            headers:{
		            },success: function(data, status){
		            	if(data.success){
		            		//清除倒计时，初始化按钮状态
	                        code_time_out = 90;
	                        code_btn_click_type = 0;
	                        clearInterval(interval);
	                        $(".code_btn").children("input").val("获取验证码");
	                        window.localStorage.token = data.data.token;
	                        //登录成功改变login_btn_click_type = 1
	                        login_btn_click_type = 1;
	                        //登录成功，关闭浮窗
	                        $(".login_win").css({"display":"none"});
	                        //显示设置的用户信息 .login_success
	                        $(".b_login .l_login").hide();
	                        window.localStorage.userId = data.data.user.userId;
	                        window.localStorage.userHeaderImg = data.data.user.userHeaderImg;
		                    window.localStorage.userNick = data.data.user.userNick;
		                    window.localStorage.userName = data.data.user.userName;
		                    window.localStorage.userEmail = data.data.user.userEmail;
	                        $(".login_success").show().children("span").html(window.localStorage.userNick);
	                        $(".login_success").children(".head_img").attr("src",window.localStorage.userHeaderImg);
		                }else{
		                	alert("验证码错误");
		                }
		            },error: function(data, status, e){
		            	alert("网络错误!!!");
		            }
		        });
            }
        });

        //TODO 打开登录窗口
        $(".l_login").on("click",function(){
            //初始化登录按钮状态
            code_btn_click_type = 0;
            login_btn_click_type = 0;
            $(".login_win").css({"display":"block"});
            login_win();
        });

        //TODO 关闭登录窗口
        $(".l_close").on("click",function(){
            $(".login_win").css({"display":"none"});
        });

        
        
        
        

        
        $(window).resize(function(){
            login_win();
        });
        function login_win(){
            var win_height = $(window).height();
            var win_width = $(window).width();
            var login_win = $(".login_win");
            var login_input = login_win.children(".login_input");
            login_win.css({"width":win_width,"height":win_height});
            login_input.css({"margin-top":(win_height-login_input.height())/3});
        }

    }
})
