/**
 * Created by zdl on 2016/8/16.
 */
function commitCom(){
	var comment = $("#comment").val().trim();
	if(comment == ""){
		alert("评论不能为空！");
		return;
	}
	var ctx = getContextPath();
	var resId = GetQueryString("resId");
	var userId = window.localStorage.userId;
	$.ajax({
    	type:"post",
    	async: false,
        url: ctx + "/comment/add",				
        dataType: 'JSON', 							
        data:{
        	"commContent" : comment,
        	"resId" : resId,
        	"userId" : userId
        },
        headers:{
        	"token":window.localStorage.token
        },success: function(data, status){
        	alert("发表成功！");
        	$("#comment").val("");
        	getCommentList();
        }, error: function(data, status, e){
        	alert("网络错误!!!");
        }
	});
}

function getCommentList(){
	var ctx = getContextPath();
	var resId = GetQueryString("resId");
	$.ajax({
    	type:"post",
    	async: false,
        url: ctx + "/comment/getCommentInfoList",				
        dataType: 'JSON', 							
        data:{
        	"resId" : resId
        },
        headers:{
        	"token":window.localStorage.token
        },success: function(data, status){
        	var lis = "";
        	if(data.success){
        		var dataList = data.data;
        		if(dataList.length == 0){
        			lis += "<li style='margin-top: 5px;'>亲！还没有评论哦！快来评论吧！</li>";
        		}
        		for(var i=0;i<dataList.length;i++){
            		var ele = dataList[i];
            		lis += "<li class='commentLi'><p>" + ele.userNick + "</p><p>" + ele.commContent + "</p></li>";
            	}
        		$("#commentsList").html(lis);
        	}
        	
        }, error: function(data, status, e){
        	alert("网络错误!!!");
        }
	});
}

$(function(){
	var ctx = getContextPath();
	var resId = GetQueryString("resId");
	//初始加载数据
	$.ajax({
    	type:"post",
    	async: false,
        url: ctx + "/resource/getResByResId",				//服务器路径
        dataType: 'JSON', 								//返回值类型，一般设置为json、application/json
        data:{"resId":resId},
        headers:{
        	"token":window.localStorage.token
        },success: function(data, status){
        	//console.log(data)
        	if(data.success){
        		$("#cateId").val(data.data.res.cateId);
        		$(".h_main").find("video").attr("src",data.data.res.resUrl);
        		$(".h_desc").find("p").text(data.data.res.resInfo);
        		var resList = data.data.resList;
        		var red_str = "";
        		for(var i=0;i<resList.length;i++){
        			var res =  resList[i];
        			/*if(resId == res.resId){
        				var red_div = "<div class='l_text'><div class='t_title'>"+res.resName +"</div><div class='t_pic'>"+res.resIntegral+"分</div></div>";
        				red_str += "<li class='box-shadow' resId="+res.resId+">"+red_div+"</li>";
        			}else{*/
        				var red_div = "<div class='l_text'><div class='t_title'>"+res.resName +"</div><div class='t_pic'>"+res.resIntegral+"分</div></div>";
        				red_str += "<li resId="+res.resId+">"+red_div+"</li>";
        				
        			/*}*/
        		}
        		$(".res-list").append(red_str);
        	}else{
        		alert(data.msg);
        		if(data.code == '004'){
        			
        			window.location.href="list.html?subId="+data.data;
        		}
        	}
        }, error: function(data, status, e){
        	alert("网络错误!!!");
        }
	})
	
	getCommentList();//首次加载评论
	
	$(".res-list > li").click(function(){
		var resId = $(this).attr("resId");
		window.location.href="detail.html?resId="+resId;
	});
	
	$("#commitCom").click(commitCom);
	
	$("#getMore").click(function(){
		window.open(ctx + "/list.html?subId=" + $("#cateId").val());
	});
	
})