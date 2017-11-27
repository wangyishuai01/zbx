<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8" src="${ctx }/js/echarts.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="${ctx }/bootstrap-datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet" />
<title>统计管理</title>
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
					<div class="col-xs-12 col-md-12">
						<div class="widget">
						<div class="widget-header ">
								<h5 class="widget-caption">统计管理</h5>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize"></a> <a href="#"
										data-toggle="collapse" onclick="tab('pro');"> <i
										class="fa fa-minus" id="pro-i"></i>
									</a> <a href="#" data-toggle="dispose"></a>
								</div>
							</div>
							
							<div class="widget-body" id="pro">
								<div class="table-toolbar" >
									<span style="font-size: 14px;">
										<div class="col-md-5 cy-text-right-md">
											<div class="form-inline">
												<div class="form-group cy-mar-ver-s">
													<span class="cy-pad-hor-s">时间：</span>
												</div>
												<div class="input-daterange input-group " id="datepicker">
													<input type="text" class="form-control" name="start"
														id="qBeginTime" readonly onchange="btnCount_Click()"/> <span class="input-group-addon">至</span>
													<input type="text" class="form-control" name="end"
														id="qEndTime" readonly onchange="btnCount_Click()"/>
												</div>
											</div>
										</div></span> 
										<span>时间间隔：</span>
										<select class="form-control"style="width:100px;" id="dateSpace"  >  
											<option value="0" selected = "selected">时</option>
											<option value="1">天</option>
											<option value="2">月</option>
											<option value="3">年</option>
										</select> 
										
										<span>用户名：</span>
										<input  class="form-control" style="width:200px;" id="userName" />
										<a class="btn btn-default shiny" onclick="init();">查询</a>
								</div>
									<div class="tools" id="iconIsShow"style="float:right ;display: none">
                                       <!--饼图 <a href="javascript:;"   onclick="getPie()" class="fa fa-circle-o-notch" > </a> -->
                                       <a href="javascript:;"  onclick="getBar()" class="fa fa-bar-chart-o"   > </a>
                                       <a href="javascript:;"  onclick="getLine()" class="fa fa-bars"   > </a>
                                       <a href="javascript:;"  onclick="getBar()" class="fa fa-refresh"   > </a>
                                    </div>
                                    
                                    
                                    <div id="main1" style="width: 1200px;height: 500px;display: none"></div>
                                    <div id="main2" style="width: 1000px;height: 500px;display: none"></div>
                                    <div id="main3" style="width: 1000px;height: 500px;display: none"></div>
								</div>
								
							</div>
						</div>
					</div>
				</div>	
			</div>
			<!-- /Page Body -->
		</div>
		<!-- /Page Content -->
	</div>
</body>
<script type="text/javascript">

var rootPath = "${pageContext.request.contextPath}";

var xAxisArticle=[];
var seriesDataArticle=[];
var xAxisVideo=[];
var seriesDataVideo=[];

function init(){
	
	var beginDate = $("#qBeginTime").val();
	var endDate = $("#qEndTime").val();
	var userName = $("#userName").val();
	var dateSpace = $("#dateSpace").val();
	if(beginDate==""){
		alert("请选择时间");
		return;
	}
	if(endDate==""){
		alert("请选择时间");
		return;
	}
	beginDate +=" 00:00:00";
	endDate +=" 23:59:59";
	$.ajax({
		url : rootPath+"/statistics/statistics.do",
		type : "post",
		dataType : "json",
		data : {
			"beginDate" : beginDate,
			"endDate" : endDate,
			"userName" : userName,
			"dateSpace" : dateSpace
		},
		success : function(result) {
			result = JSON.parse(result);
			console.log(result);
			if(result.success){
				
				result = result.data;
				xAxisArticle = result[0];
				seriesDataArticle = result[1];
				xAxisVideo = result[0];
				seriesDataVideo = result[1];
				var main1 = document.getElementById("main1");
				main1.style.display="block";
				var main2 = document.getElementById("main2");
				main2.style.display="none";
				var main3 = document.getElementById("main3");
				main3.style.display="none";
				var myChart = echarts.init(document.getElementById("main1"));
				var option = {
				        title: {
				            text: '文章/视频统计'
				        },
				        tooltip: {},
				        legend: {
				            data:['文章','视频']
				        },
				        xAxis: {
				            data: xAxisArticle
				        },
				        yAxis: {},
				        series: [{
				            name: '文章',
				            type: 'bar',
				            barWidth : 10,
				            center: ['50%','50%'],
				            data: seriesDataArticle
				        },{
				            name: '视频',
				            type: 'bar',
				            barWidth : 10,
				            center: ['50%','50%'],
				            data: seriesDataVideo
				        }]
				    };
				    myChart.setOption(option);
				    //显示图报
				    var iconIsShow = document.getElementById("iconIsShow");
					iconIsShow.style.display="block";
			} else {
				alert("查询数据为空！");
			}
			
		}
	});
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
	
	$("#pageSelect").change(function(){
		pageCount = 1;
		init();
	});
}); 

function Appendzero(obj)  
{  
    if(obj<10) return "0" +""+ obj;  
    else return obj;  
}

$('#qBeginTime').datetimepicker({  
	format : 'yyyy-mm-dd',
	language : 'zh-CN',
	minView: "month",
    todayBtn : "linked",  
    clearBtn:true,
    autoclose : true,  
    todayHighlight : true
   
}).on('changeDate',function(e){  
    $('#qEndTime').datetimepicker('setStartDate',$('#qBeginTime').val());  
});  
//结束时间：  
$('#qEndTime').datetimepicker({  
	format : 'yyyy-mm-dd',
	language : 'zh-CN',
	minView: "month",
    todayBtn : "linked",
    clearBtn : true,
    autoclose : true,  
    todayHighlight : true 
}).on('changeDate',function(e){  
    $('#qBeginTime').datetimepicker('setEndDate',$('#qEndTime').val());  
});
//饼图
function getPie(){
	var main1 = document.getElementById("main1");
	main1.style.display="none";
	var main2 = document.getElementById("main2");
	main2.style.display="block";
	var main3 = document.getElementById("main3");
	main3.style.display="none";
	var myChart = echarts.init(document.getElementById('main2'));
	/* var pieArticleData=[];
	for(var i=0;i<seriesDataArticle.length;i++){
		var obj ={value:seriesDataArticle[i],name:"文章"};
		pieArticleData[i]=obj;
	}
	var pieVideoData=[];
	for(var i=0;i<seriesDataVideo.length;i++){
		var obj ={value:seriesDataVideo[i],name:"视频"};
		pieVideoData[i]=obj;
	} */
    var option = {
      title: {
        text: '文章/视频统计',
        x:'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)>"
        //饼图中{a}表示系列名称，{b}表示数据项名称，{c}表示数值，{d}表示百分比
      },
      legend: {//图例
        orient: 'vertical',
        left: 'left',
        data: ['文章','视频']
      },
      series: [{
        name: '文章',
        type: 'pie',
        radius: '55%',
        data: [{value:235,name:'AAAA'},
               {value:275,name:'BBBB'}],
        itemStyle: {     //itemStyle有正常显示：normal，有鼠标hover的高亮显示：emphasis
          emphasis:{//normal显示阴影,与shadow有关的都是阴影的设置
            shadowBlur:10,//阴影大小
            shadowOffsetX:0,//阴影水平方向上的偏移
            shadowColor:'rgba(0,0,0,0.5)'//阴影颜色
          }
        }
      },{
          name: '视频',
          type: 'pie',
          radius: '55%',
          data: [{value:235,name:'AAAA'},
                 {value:275,name:'BBBB'}],
          itemStyle: {     //itemStyle有正常显示：normal，有鼠标hover的高亮显示：emphasis
            emphasis:{//normal显示阴影,与shadow有关的都是阴影的设置
              shadowBlur:10,//阴影大小
              shadowOffsetX:0,//阴影水平方向上的偏移
              shadowColor:'rgba(0,0,0,0.5)'//阴影颜色
            }
          }
        }]
    };
    myChart.setOption(option);
}
//折线图
function getLine(){
	var main1 = document.getElementById("main1");
	main1.style.display="none";
	var main2 = document.getElementById("main2");
	main2.style.display="none";
	var main3 = document.getElementById("main3");
	main3.style.display="block";
	
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main3'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '文章/视频统计'
        },
        tooltip: {},
        legend: {
            data:['文章','视频']
        },
        xAxis: {
            data: xAxisArticle
        },
        yAxis: {},
        series: [{
            name: '文章',
            type: 'line',
            data: seriesDataArticle
        },{
            name: '视频',
            type: 'line',
            data: seriesDataVideo
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
//柱状图
function getBar(){
	var main1 = document.getElementById("main1");
	main1.style.display="block";
	var main2 = document.getElementById("main2");
	main2.style.display="none";
	var main3 = document.getElementById("main3");
	main3.style.display="none";
	var myChart = echarts.init(document.getElementById("main1"));
	var option = {
	        title: {
	            text: '文章/视频统计'
	        },
	        tooltip: {},
	        legend: {
	            data:['文章','视频']
	        },
	        xAxis: {
	            data: xAxisArticle
	        },
	        yAxis: {},
	        series: [{
	            name: '文章',
	            type: 'bar',
	            barWidth : 10,
	            center: ['50%','50%'],
	            data: seriesDataArticle
	        },{
	            name: '视频',
	            type: 'bar',
	            barWidth : 10,
	            center: ['50%','50%'],
	            data: seriesDataVideo
	        }]
	    };
	    myChart.setOption(option);
}

function  btnCount_Click(){  
	var beginDate = $("#qBeginTime").val();
	var endDate = $("#qEndTime").val();
    
    if(beginDate==""||endDate==""){
    	//alert('kong');
    	return;
    }
    var days = DateDiff(beginDate,endDate);
    if(days>1 && days<=30){
    	//alert('1-30');
    	$("#dateSpace").find("option").remove();
    	$('#dateSpace').append("<option value='1'>天</option><option value='2'>月</option><option value='3'>年</option>");
    }else if(days>30&&days<=365){
    	//alert('30-365');
    	$("#dateSpace").find("option").remove();
    	$('#dateSpace').append("<option value='2'>月</option><option value='3'>年</option>");
    }else if(days>365){
    	//alert('365');
    	$("#dateSpace").find("option").remove();
    	$('#dateSpace').append("<option value='3'>年</option>");
    }else{
    	//alert('1');
    	$("#dateSpace").find("option").remove();
    	$('#dateSpace').append("<option value='0'>时</option><option value='1'>天</option><option value='2'>月</option><option value='3'>年</option>");
    }
} 
//计算天数
function  DateDiff(startDate,  endDate){  
	var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();     
    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();     
    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
    return  dates;    
}
</script>
</html>