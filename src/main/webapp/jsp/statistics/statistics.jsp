<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8" src="${ctx }/js/echarts.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="${ctx }/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<title>统计</title>
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
					<div style="float:center;width:100%">
						<div class="widget">
						<div class="widget-header ">
								<h5 class="widget-caption">统计</h5>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize"></a> <a href="#"
										data-toggle="collapse" onclick="tab('pro');"> <i
										class="fa fa-minus" id="pro-i"></i>
									</a> <a href="#" data-toggle="dispose"></a>
								</div>
							</div>
							
							<div class="widget-body" id="pro">
							<div class="table-toolbar" >
									<!-- <span>时间：</span>
									<input class="form-control" style="width:200px;" id="date_start" />
									~<input class="form-control" style="width:200px;" id="date_end" /> -->
									<div id="beginDate_queryDiv" class="col-lg-10 mtb10 input-group date form_time" data-date="" data-date-format="yyyy-mm-dd "
										data-link-field="dtp_input2" data-link-format="yyyy-mm-dd "
										style="width: 250px; margin: 0;">
										<input class="form-control" onfocus="this.blur();" id="beginDate" style="position: relative" name="beginDate" 
										placeholder="开始时间" size="10" type="text"	 readonly/> 
										<span class="input-group-addon">
												<span class="glyphicon glyphicon-remove"></span>
											</span> 
										<span class="input-group-addon"> 
										<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									<span>用户名：</span>
									<input  class="form-control" style="width:200px;" id="userName" />
									<a class="btn btn-default shiny" onclick="query();">查询</a>
									<div class="tools" style="float:right ">
                                       <a href="javascript:;"   onclick="change('bar')" class="fa fa-circle-o-notch" > </a>
                                       <a href="javascript:;"  onclick="change('pie')" class="fa fa-bar-chart-o"   > </a>
                                       <a href="javascript:;"   onclick="change('line')"  class="fa fa-refresh"   > </a>
                                       
                                    </div>
								</div>
								
							<div id="main" style="width: 500px;height: 400px;"></div>
								
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
	
	beginDate = $("#beginDate").val();
	endDate = $("#beginDate").val();
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
				alert(1);
				result = result.data;
				var myChart = echarts.init(document.getElementById("main"),"macarons");
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


$('.form_time').datetimepicker({
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
});


function change(type){
	if(type="bar"){
		var myChart = echarts.init(document.getElementById("main"),"macarons");
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
	}
	if(type="pie"){
		var myChart = echarts.init(document.getElementById("main"),"macarons");
		var option = {
                title:{
                    text:"饼状图"
                },
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        data:[
                            {value:235, name:'视频广告'},
                            {value:274, name:'联盟广告'},
                            {value:310, name:'邮件营销'},
                            {value:335, name:'直接访问'},
                            {value:400, name:'搜索引擎'}
                        ]
                    }
                ]
            };
		    myChart.setOption(option);
	}
}
</script>
</html>