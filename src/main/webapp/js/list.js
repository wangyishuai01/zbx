/**
 * Created by zdl on 2016/8/16.
 */
$(function(){
	var ctx = getContextPath();
	
	var parId = GetQueryString("parId");
	var subId = GetQueryString("subId");
	if(parId == null && subId != null){
	    $.ajax({
	    	type:"post",
	    	async: false,
	        url: ctx + "/category/getByCateId",				//服务器路径
	        dataType: 'JSON', 								//返回值类型，一般设置为json、application/json
	        data:{"cateId":subId},
	        headers:{
	        },success: function(data, status){
	        	if(data.success){
	        		parId = data.data.catePid;
	        	}else{
	        		alert(data.msg);
	        	}
	        }, error: function(data, status, e){
	        	alert("网络错误!!!");
	        }
		});
	}
	//加载分类信息
    $.ajax({
    	type:"post",
    	async: false,
        url: ctx + "/category/get",				//服务器路径
        dataType: 'JSON', 						//返回值类型，一般设置为json、application/json
        data:{"catePid":0},
        headers:{
        }, success: function(data, status){
        	if(data.success){
        		var list = "";
        		var type = true;
        		for(var i=0;i<data.data.length;i++){
        			if(data.data[i].cateId == parId){
        				type = false;
        				list += "<li class='cursor main' cateId="+data.data[i].cateId+">"+data.data[i].cateName+"</li>";
        			}else{
        				if(parId == null && type){
        					type = false;
        					parId = data.data[i].cateId;
        					list += "<li class='cursor main' cateId="+data.data[i].cateId+">"+data.data[i].cateName+"</li>";
        				}else{
        					list += "<li class='cursor' cateId="+data.data[i].cateId+">"+data.data[i].cateName+"</li>";
        				}
        			}
        		}
        		$(".l-par").append(list);
        	}else{
        		alert(data.msg)
        	}
        }, error: function(data, status, e){
        	alert("网络错误!!!");
        }
	});
    
    $.ajax({
    	type:"post",
    	async: false,
        url: ctx + "/category/get",				//服务器路径
        dataType: 'JSON', 						//返回值类型，一般设置为json、application/json
        data:{"catePid":$(".l-par .main").attr("cateId")},
        headers:{
        }, success: function(data, status){
        	if(data.success){
        		var list = "";
        		var type = true;
        		for(var i=0;i<data.data.length;i++){
        			if(data.data[i].cateId == subId && parId != null){
        				type = false;
        				list += "<li class='cursor main' cateId="+data.data[i].cateId+">"+data.data[i].cateName+"</li>";
        			}else{
        				if(subId == null && type){
        					type = false;
        					subId = data.data[i].cateId;
        					list += "<li class='cursor main' cateId="+data.data[i].cateId+">"+data.data[i].cateName+"</li>";
        				}else{
        					list += "<li class='cursor' cateId="+data.data[i].cateId+">"+data.data[i].cateName+"</li>";
        				}
        			}
        		}
        		$(".l-sub").append(list);
        	}else{
        		alert(data.msg)
        	}
        }, error: function(data, status, e){
        	alert("网络错误!!!");
        }
	});
    $("#cateId").val(subId);
    
    $(".l-par li").on("click",function(){
    	parId = $(this).attr("cateId");
    	window.location.href="list.html?parId="+parId;
    });
    $(".l-sub li").on("click",function(){
    	subId = $(this).attr("cateId");
    	window.location.href="list.html?parId="+parId+"&subId="+subId;
    });
    
	
	//加载页面资源
	var loadshowResList = function(data, currentPage){
		var listDiv = $(".c_body .b_main .m_list");
		var pageDiv = $(".c_body .b_main .m_page .p_list ul");
		var option = "<ul>";
		var pageCountSum = data.pageCountSum;
		if(pageCountSum != 0){
			var resList = data.list;
			for(var i=0; i<resList.length; i++){
				var ele = resList[i];
				option  +=  "<li class='cursor hover-transform-rotate-scale' resid='" + ele.resId + "'>" +
					            "<div class='l_img'><img src='" + ele.resCoverImg + "'></div>" +
					            "<div class='l_text'>" +
					                "<div class='t_title'>" + ele.resName + "</div>" +
					                "<div class='t_pic'>" + (ele.resIntegral == 0 ? "免费" : ele.resIntegral) + "</div>" +
					            "</div>" +
					        "</li>";
			}
			option += "</ul>";
			listDiv.html(option);
			listDiv.find("li").click(resClick);
		}
		var currentP = parseInt(currentPage);
		var pageOption = "", selectOption = "";
		if(currentP != 1){
			pageOption += "<li value='" + ((currentP-1)<=1?1:(currentP-1)) + "'>上一页</li>";
		}
		for(var i=1; i<=pageCountSum; i++){
			if(currentPage == i){
				pageOption += "<li class='main' value='" + i + "'>" + i + "</li>";
				selectOption += "<option selected='selected' value='" + i + "'>" + i + "</option>";
			} else {
				pageOption += "<li value='" + i + "'>" + i + "</li>";
				selectOption += "<option value='" + i + "'>" + i + "</option>";
			}
			
		}
		if(currentP != pageCountSum){
			pageOption += "<li value='" + ((currentP+1)>=pageCountSum?pageCountSum:(currentP+1)) + "'>下一页</li>";
		}
		pageOption += "<li value='0'><select>" + selectOption + "</select></li>"
		pageDiv.html(pageOption);
		pageDiv.find("li").click(function(){
			var pageCount = $(this).attr("value");
			if(pageCount != 0 && $("#pageCount").val() != pageCount){
				$("#pageCount").val(pageCount);
				getPageResList();
			}
		});
		pageDiv.find("li select").change(function(){
			var pageCount = $(this).val();
			if($("#pageCount").val() != pageCount){
				$("#pageCount").val(pageCount);
				getPageResList();
			}
		});
	};
	//获取页面资源
	var getPageResList = function(){
		var pageCount = $("#pageCount").val();
		var pageSize = $("#pageSize").val();
		var cateId = $("#cateId").val();
		if(cateId == null || cateId == ""){
			//没有数据
		}else{
			$.ajax({
				type:"post",
				url: ctx + "/resource/getResInfoByParamPage",
				dataType: 'JSON', 
				element:$(this),
				data:{
					"pageCount": pageCount,
					"pageSize": pageSize,
					"cateId" : cateId
				},
				headers:{
				},
				success: function(data, status){
					loadshowResList(data.data, pageCount);
				},
				error: function(data, status, e){
					alert("网络错误!!!");
				}
			});
		}
	};
	
	getPageResList();//首次加载页面
	
	
})