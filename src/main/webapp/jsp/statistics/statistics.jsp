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
							
										</select> 
										
										<span>用户名：</span>
										<input  class="form-control" style="width:200px;" id="userName" />
										<a class="btn btn-default shiny" onclick="query();">查询</a>
								</div>
									<div class="tools" style="float:right ">
                                       <a href="javascript:;"   onclick="getBar()" class="fa fa-circle-o-notch" > </a>
                                       <a href="javascript:;"  onclick="getPie()" class="fa fa-bar-chart-o"   > </a>
                                       <a href="javascript:;"     class="fa fa-refresh"   > </a>
                                    </div>
                                    
                                    
                                    <div id="main" style="width: 500px;height: 400px;float：left;"></div>
                                    
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

function init(){
	
	beginDate = $("#qBeginTime").val();
	endDate = $("#qEndTime").val();
	userName = $("#userName").val();
	$.ajax({
		url : rootPath+"/statistics/statistics.do",
		type : "post",
		dataType : "json",
		data : {
			"beginDate" : beginDate,
			"endDate" : endDate,
			"userName" : userName
		},
		success : function(result) {
			result = JSON.parse(result);
			if(result.success){
				
				result = result.data;
				var myChart = echarts.init(document.getElementById("main"));
				var option = {
				        title:{//标题组件
				            text:""
				        },
				        tooltip:{//提示框组件
				            text:"this is tool tip"
				        },
				        legend:{//图例组件
				            data:['访问量']
				        },
				        grid: {       //直角坐标系内绘图网格
				            left: '3%',
				            right: '4%',
				            bottom: '3%',
				            containLabel: true
				        },
				        toolbox: {     //工具栏
				            feature: {
				                saveAsImage: {}
				            }
				        },
				        xAxis:{ //直角坐标系 grid 中的 x 轴
				            data:["文章","视频"]
				        },
				        yAxis:{ //直角坐标系 grid 中的 y 轴
				        	
				        },
				        series:[{//系列列表
				                    name:["访问量"],
				                    type:"bar",
				                    data:[5,20]
				                }]
				    };

				    myChart.setOption(option);
				    
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
	init();
	$("#pageSelect").change(function(){
		pageCount = 1;
		init();
	});
}); 


/* $('.form_time').datetimepicker({
	format : 'yyyy-mm-dd',
	language : 'zh-CN',
	minuteStep : 3,
	weekStart : 1,
	todayBtn : 1,
	autoclose : 1,
	todayHighlight : 1,
	startView : 2,
	minView : 0,
	maxView : 4,
	forceParse : 0
}); */
$('#qBeginTime').datetimepicker({  
	format : 'yyyy-mm-dd',
	language : 'zh-CN',
	minView: "month",
    todayBtn : "linked",  
    clearBtn:true,
    autoclose : true,  
    todayHighlight : true,  
    endDate : new Date()  
}).on('changeDate',function(e){  
    var startTime = e.date;  
    $('#qEndTime').datetimepicker('setStartDate',startTime);  
});  
//结束时间：  
$('#qEndTime').datetimepicker({  
	format : 'yyyy-mm-dd',
	language : 'zh-CN',
	minView: "month",
    todayBtn : "linked",
    clearBtn : true,
    autoclose : true,  
    todayHighlight : true,  
    endDate : new Date()  
}).on('changeDate',function(e){  
    var endTime = e.date;  
    $('#qBeginTime').datetimepicker('setEndDate',endTime);  
});
function getPie(){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'pie',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function getBar(){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function  btnCount_Click(){  
	var beginDate = $("#qBeginTime").val();
	var endDate = $("#qEndTime").val();
    
    if(beginDate==""||endDate==""){
    	alert('kong');
    	return;
    }
    var days = DateDiff(beginDate,endDate);
    if(days>1 && days<=30){
    	alert('1-30');
    	$("#dateSpace").find("option").remove();
    	$('#dateSpace').append("<option value='1'>天</option><option value='2'>月</option><option value='3'>年</option>");
    }else if(days>30&&days<=365){
    	alert('30-365');
    	$("#dateSpace").find("option").remove();
    	$('#dateSpace').append("<option value='2'>月</option><option value='3'>年</option>");
    }else if(days>365){
    	alert('365');
    	$("#dateSpace").find("option").remove();
    	$('#dateSpace').append("<option value='3'>年</option>");
    }else{
    	alert('1');
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