/**
 * Created by zywayh on 2016/8/1.
 */
$(function(){
    var ctx = getContextPath();
	$(".get-all").click(function(){
		window.location.href="list.html";
	});
	
	//加载分类信息
    $.ajax({
    	type:"post",
        url: ctx + "/category/getAll",	//服务器路径
        dataType: 'JSON', 						//返回值类型，一般设置为json、application/json
        data:{"catePid":0,"pageCount":1,"pageSize":13},
        headers:{
        }, success: function(data, status){
        	if(data.success){
        		for(var i=0;i<data.data.length;i++){
        			var sub_str = "";
            		for(var j=0;j<data.data[i].sub.length;j++){
            			var sub = data.data[i].sub[j];
            			sub_str +="<li class='cursor' cateId="+sub.cateId+">"+sub.cateName+"</li>"
            		}
        			$(".con .c_header .h_nav .n_nav .n_navs > ul").append(
    					"<li class='cursor' cateId="+data.data[i].par.cateId+">"+data.data[i].par.cateName+"" +
							"<div class='n_sub'>" +
    							"<ul>" + sub_str + "</ul>" +
							"</div>" +
    					"</li>");
        		}
        		var n_navs = $(".con .c_header .h_nav .n_nav .n_navs ul li");
        		n_navs.mouseover(function(){
        	        $(this).children(".n_sub").show();
        	    }).mouseout(function(){
        	        $(this).children(".n_sub").hide();
        	    });
        		n_navs.children(".n_sub").children("ul").children("li").click(function(){
        			var __this = $(this);
        			var sub_cateId = __this.attr("cateId");
        			var cateId = __this.parent().parent().parent().attr("cateId");
        			window.location.href="list.html?parId="+cateId+"&subId="+sub_cateId;
        		});
        	}else{
        		alert(data.msg)
        	}
        }, error: function(data, status, e){
        	alert("网络错误!!!");
        }
	});
    
    //加载首页楼层资源
    var loadres = function(resList, leftTitle, rightTitle, flag){
    	var mainDiv = $(".c_body .b_main");
    	var option = "<div class='m_group'>" +
		 				"<div class='g_title'>" +
		 					"<div class='t_left cursor'>" + (leftTitle || "") + "</div>" +
		 					"<div class='t_right cursor'>" + (rightTitle || "") + "</div>" +
		 				"</div>" +
		 				"<div class='g_content'>";
		for(var i=0; i<resList.length; i++){
		var ele = resList[i];
		option += (flag == 1) ? "<div class='c_detail cursor hover-transform-rotate-scale' resid='" + ele.resId + "'>" 
							: "<div class='c_detail cursor hover-transform-rotate-rotate-scale' resid='" + ele.resId + "'>";
		option += 
			"<div class='d_img'><img src='" + ele.resCoverImg + "'></div>" +
				"<div class='d_text'>" +
					"<div class='t_name'>" + ele.resName + "</div>" +
					"<div class='t_pic'>" + (ele.resIntegral == 0 ? "免费" : ele.resIntegral) + "</div>" +
				"</div>" +
			"</div>";
		}
		option += "</div></div>";
		mainDiv.append(option);
    };
    
    //加载首页资源
    $.ajax({
    	type:"post",
        url: ctx + "/resource/getIndexResourceInfo",	//服务器路径
        dataType: 'JSON', 						//返回值类型，一般设置为json、application/json
        element:$(this),
        data:{},
        headers:{
        	"token" : window.localStorage.token
        },
        success: function(data, status){
        	if(data.success){
        		var jsonData = data.data;
        		loadres(jsonData.newResList, "最新", "其他", 1);
        		loadres(jsonData.freeResList, "免费", "精选 软件/网络 营销 游戏动漫 电商", 2);
        		$(".c_detail").click(resClick);
        	}
        },
        error: function(data, status, e){
        	alert("网络错误!!!");
        }
	});

});