//动态图表缓存池
var myChartMap = {};var myOptionMap = {};var myCountMap = {};

//图表水印信息
var waterMarkText = '';

//div.echarts图表注册
$(document).ready(function() {
	$("div.echarts").dblclick(echartsConfig);
	$("div.echarts").dblclick();
});

//执行主函数入口
function echartsConfig(options){
	//div上的属性值封装
	var data = new Object();
	var _defaults = {
        srcEle: this,
        
        id: $(this).attr('id'),
        echarts_type: $(this).attr('echarts-type'),
        echarts_title: $(this).attr('echarts-title'),
        echarts_subtitle: $(this).attr('echarts-subtitle'),
        echarts_link: $(this).attr('echarts-link'),
        echarts_sublink: $(this).attr('echarts-sublink'),
        echarts_theme: $(this).attr('echarts-theme'),
        
        echarts_x_axis_name: $(this).attr('echarts-x-axis-name'),
        echarts_y_axis_name: $(this).attr('echarts-y-axis-name'),
        echarts_split_number: $(this).attr('echarts-split-number'),
        echarts_min_interval: $(this).attr('echarts-min-interval'),
        
        echarts_toolbox: $(this).attr('echarts-toolbox'),
        echarts_datazoom: $(this).attr('echarts-datazoom'),
        echarts_stack: $(this).attr('echarts-stack'),
        echarts_markpoint: $(this).attr('echarts-markpoint'),
        echarts_markline: $(this).attr('echarts-markline'),
        
        echarts_regression: $(this).attr('echarts-regression'),
        echarts_degree: $(this).attr('echarts-degree'),
        
        echarts_url: $(this).attr('echarts-url'),
        echarts_socket: $(this).attr('echarts-socket'),
        
        echarts_measure_range: $(this).attr('echarts-measure-range'),
        echarts_axis_label_show: $(this).attr('echarts-axis-label-show'),
        
        echarts_onclick_id: $(this).attr('echarts-onclick-id')
    };
    var opts = $.extend(true, _defaults, options);
    
    data.id = opts.id;
    data.type = opts.echarts_type;
    data.title = opts.echarts_title;
    data.subtitle = opts.echarts_subtitle;
    data.link = opts.echarts_link;
    data.sublink = opts.echarts_sublink;
    data.theme = opts.echarts_theme;
    
    data.xaxisName = opts.echarts_x_axis_name;
    data.yaxisName = opts.echarts_y_axis_name;
    data.splitNumber = opts.echarts_split_number;
    data.minInterval = opts.echarts_min_interval;
    
    data.isNeedToolBox = opts.echarts_toolbox;
    data.isNeedDataZoom = opts.echarts_datazoom;
    data.isNeedStack = opts.echarts_stack;
    data.isNeedMarkPoint = opts.echarts_markpoint;
    data.isNeedMarkLine = opts.echarts_markline;
    
    data.regression = opts.echarts_regression;
    data.degree = opts.echarts_degree;
    
    data.url = opts.echarts_url;
    data.socket = opts.echarts_socket;
    
    data.measureRange = opts.echarts_measure_range;
    data.axisLabelShow = opts.echarts_axis_label_show;
    
    data.onClickId = opts.echarts_onclick_id;
    
    //传入form参数
    data.serializeParameters=$("form").serializeArray();
    var paraMap = {};
    var paraStr = "";
    for (var i = 0; i < data.serializeParameters.length; i++) {
    	if(paraStr.indexOf(data.serializeParameters[i].name)>-1){
    		paraMap[data.serializeParameters[i].name]+=","+data.serializeParameters[i].value
    	}else{
    		paraMap[data.serializeParameters[i].name]=data.serializeParameters[i].value;
    	}
    	paraStr+="||"+data.serializeParameters[i].name;
    }
    data.parameters=paraMap;
    
    if(data.type=='gauge'){
    	echartsGauge(data);
    }else if(data.type=='liquidFill'){
    	echartsLiquidFill(data);
    }else if(data.type=='calendar'){
    	echartsCalendar(data);
    }else if(data.type=='bar3d'||data.type=='pie3d'){
    	highcharts(data);
    }else if(data.type=='multidata-scatter'){
    	echartsMultidataScatter(data);
    }else if(data.type=='multidata-heatmap'){
    	echartsMultidataHeatmap(data);
    }else{
	    jQuery.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			type : "POST",
			url : data.url,
			data : JSON.stringify(data),
			dataType : 'text',
			success : function(message, tst, jqXHR) {
				//散点图通过前台会更加方便
			    if(data.type=='scatter'){
			    	echartsScatter(data,message);
			    }else{
					// 基于准备好的dom，初始化echarts实例
				    var myChart = echarts.init(document.getElementById(data.id),data.theme);
				    myOption = eval('(' + message + ')');
				    // 使用刚指定的配置项和数据显示图表。
				    myChart.setOption(myOption);
				    // 图表背景水印
				    myChart.setOption({backgroundColor: {
				        type: 'pattern',
				        image: canvas,
				        repeat: 'repeat'
				    }});
				    myChartMap[data.id] = myChart;
				    myOptionMap[data.id] = myOption;
                    myCountMap[data.id] = 0;
				    //给图表相关区域添加click事件
				    echartsOnclick(data,myChart);
				    
				    if(data.socket!=''){
				    	var stompClient = null;
				    	var socket = new SockJS('/echarts-websocket');
					    stompClient = Stomp.over(socket);
					    stompClient.debug = null;
					    stompClient.connect({}, function (frame) {
					        //setConnected(true);
					        stompClient.subscribe(data.socket, function (socketData) {
					            var jsonObj = JSON.parse(socketData.body);
					            // debugger;
					        	for (var i=0;i<myOptionMap[data.id].series.length;i++){
									myOptionMap[data.id].series[i].data.shift(); //zzz
									myOptionMap[data.id].series[i].data.push(jsonObj[myOptionMap[data.id].series[i].name]);
					        	}
                                //myOptionMap[data.id].xAxis[0].data.shift();
                                myOptionMap[data.id].xAxis[0].data.push(jsonObj['xxx']);
                                myChartMap[data.id].setOption(myOptionMap[data.id]);
					         	//console.log('Received: ' + event.data); 
					        });
					    }); 
				    }
			    }
			}
	    });
    }
    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');
    canvas.width = canvas.height = 100;
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.globalAlpha = 0.08;
    ctx.font = '20px Microsoft Yahei';
    ctx.translate(50, 50);
    ctx.rotate(-Math.PI / 4);
    ctx.fillText(waterMarkText, 0, 0);
}

function echartsOnclick(data,myChart){
	myChart.on('click', function (params) {
		//获取URL以及参数
		var seriesName = encodeURIComponent(params.seriesName);
		var name = encodeURIComponent(params.name);
		var aHref = $('#'+data.onClickId).attr("href");
		var url = aHref+"?id="+data.id+"&seriesName="+seriesName+"&name="+name+"&value="+params.value
		//获取target为"_jbox"时的参数信息
		var target = $('#'+data.onClickId).attr("target");
		var jbox_width = $('#'+data.onClickId).attr("jbox-width");
		var jbox_height = $('#'+data.onClickId).attr("jbox-height");
		var jbox_title = $('#'+data.onClickId).attr("jbox-title");
		//给宽和高附上默认值
		if(jbox_width==''||jbox_width==undefined){
			jbox_width = $(top.document).height()+350;
		}
		if(jbox_height==''||jbox_height==undefined){
			jbox_height = $(top.document).height()-180;
		}
		//判断target类型
		if(target=='_jbox'){
			$.jBox.open("iframe:"+url,
    		 		jbox_title.replace("{seriesName}",params.seriesName).replace('{name}',params.name),
    		 		Number(jbox_width),Number(jbox_height),
    		 		{buttons:{'关闭':true}, opacity:0.5, top:'3%'},
    		 		{loaded:function(h){
    	                $(".jbox-content", top.document).css("overflow-y","hidden");
    	            }}
			);
		}else if(target=='_echarts'){
			var echartsIds = $('#'+data.onClickId).attr("echarts-ids");
			debugger;
			if(echartsIds!=null&&echartsIds!=undefined&&echartsIds!=''){
				$("input[name='id']").val(data.id);
				$("input[name='seriesName']").val(seriesName);
				$("input[name='name']").val(name);
				$("input[name='value']").val(params.value);
				var ids = echartsIds.split(',');
				for (var i=0;i<ids.length;i++){
					$('#'+ids[i]).dblclick();
				}
			}else{
				console.log("当选择_echarts为跳转类型，echarts-ids为必选项，例如：demo-bar、demo-pie");
			}
		}else{		    			
			$('#'+data.onClickId).attr("href",url);
			$('#'+data.onClickId)[0].click();
		}
	});
}
function echartsGauge(data){
	var option = {
	    tooltip : {
	        formatter: "{a} <br/>{b}: {c}"+data.xaxisName
	    },
	    toolbox: {
	    	show: data.isNeedToolBox,
	        feature : {
	            mark : {show: false},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    series : [
	        {
	            name:data.title,
	            type:'gauge',
	            z: 3,
	            min:0,
	            max: data.measureRange,
	            splitNumber:data.splitNumber,
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    width: 10
	                }
	            },
	            axisTick: {            // 坐标轴小标记
	                length :15,        // 属性length控制线长
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: 'auto'
	                }
	            },
	             axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
	                show:data.axisLabelShow,
	              	textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    color: 'auto'
	                }
	            },
	            splitLine: {           // 分隔线
	                length :20,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    color: 'auto'
	                }
	            },
	            title : {
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontWeight: 'bolder',
	                    fontSize: 20,
	                    fontStyle: 'italic'
	                }
	            },
	            detail : {
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontWeight: 'bolder'
	                },
	                formatter:'{value}'+data.xaxisName
	            },
	            data:[{value: 0, name: data.subtitle}]
	        }
	    ]
	 };
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(data.id),data.theme);
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
    var stompClient = null;
	var socket = new SockJS('/echarts-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        stompClient.subscribe(data.socket, function (socketData) {
            var jsonObj = JSON.parse(socketData.body);
            option.series[0].data[0].value = jsonObj;
        	myChart.setOption(option, true);
         	console.log('Received: ' + jsonObj); 
        });
    }); 
}

function echartsLiquidFill(data){
	var option = {
	    series : [
	        {
	        	name: data.title,
	        	type:'liquidFill',
	            data:[0]
	        },
	    ],
	    tooltip: {
	    	formatter: "{a} <br/>{b}: {c}"
	    },
	    toolbox: {
	    	show: data.isNeedToolBox,
	        feature : {
	            mark : {show: false},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	 };
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(data.id),data.theme);
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
    var stompClient = null;
	var socket = new SockJS('/echarts-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        stompClient.subscribe(data.socket, function (socketData) {
            var jsonObj = JSON.parse(socketData.body);
            myChart.setOption({
        	    series : [
        	  	        {
        	  	           data:[(jsonObj*0.01).toFixed(2)]
        	  	        }
        	  	    ]
        	  	 });
         	console.log('Received: ' + (jsonObj*0.01).toFixed(2)); 
        });
    }); 
}

function echartsCalendar(data){
	jQuery.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "POST",
		url : data.url,
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(message, tst, jqXHR) {
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById(data.id),data.theme);
			var cellSize = [80, 80];
			var pieRadius = 30;
			var range;
			var echartsDate;
			var legend = [];
	    	$.each(message[0],function(key,values){
    			 if(key!='echartsDate'){				    				 	
    				 legend.push(key);
    			 }
			});
			function getVirtulData() {
			    var data = [];
			    jQuery.each(message, function(i, val) {   
			    	 data.push([val.echartsDate,0]);
			    	 range=val.echartsDate.substring(0,7);
			    });  
			    return data;
			}
			
			function getPieSeries(scatterData, chart) {
			    return echarts.util.map(scatterData, function (item, index) {
			        var center = chart.convertToPixel('calendar', item);
			        var data=[];
			        jQuery.each(message, function(i, val) {   
				    	 if(item[0]==val.echartsDate){
				    		 $.each(val,function(key,values){
				    			 if(key!='echartsDate'){	
				    				 echartsDate=val.echartsDate.substring(0,10);
				    				 data.push({name:key,value:values});	
				    				 legend.push(key);
				    			 }
						     });
				    	 }
				    }); 
			        return {
			            id: index + 'pie',
			            name: echartsDate,
			            type: 'pie',
			            center: center,
			            label: {
			                normal: {
			                    formatter: '{c}',
			                    position: 'inside'
			                }
			            },
			            radius: pieRadius,
			            data: data
			        };
			    });
			}
			var scatterData = getVirtulData();
			var option = {
				title: {
			        text: data.title,
			        subtext: data.subtitle,
			        left: 'center'
			    },
			    tooltip : {
			        formatter: "{a} <br/>{b}: {c}"+data.xaxisName
			    },
			    toolbox: {
			    	show: data.isNeedToolBox,
			        feature : {
			            mark : {show: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    legend: {
			        data: legend,
			        bottom: 20
			    },
			    calendar: {
			        top: 'middle',
			        left: 'center',
			        orient: 'vertical',
			        cellSize: cellSize,
			        yearLabel: {
			            show: false,
			            textStyle: {
			                fontSize: 30
			            }
			        },
			        dayLabel: {
			            margin: 20,
			            firstDay: 1,
			            nameMap: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
			        },
			        monthLabel: {
			            show: false
			        },
			        range: [range]
			    },
			    series: [{
			        id: 'label',
			        type: 'scatter',
			        coordinateSystem: 'calendar',
			        symbolSize: 1,
			        label: {
			            normal: {
			                show: true,
			                formatter: function (params) {
			                    return echarts.format.formatTime('dd', params.value[0]);
			                },
			                offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
			                textStyle: {
			                    color: '#000',
			                    fontSize: 14
			                }
			            }
			        },
			        data: scatterData
			    }]
			};
		    var pieInitialized;
		    setTimeout(function () {
		        pieInitialized = true;
		        myChart.setOption({
		            series: getPieSeries(scatterData, myChart)
		        });
		    }, 10);
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		    //给图表相关区域添加click事件
		    echartsOnclick(data,myChart);
		}
    });
}

//散点图以及回归方程
function echartsScatter(data,message){
	// See https://github.com/ecomfe/echarts-stat
	var option =  myOption = eval('(' + message + ')');
	if(data.regression=='polynomial'){
		var myRegression = ecStat.regression(data.regression,option.series[0].data,data.degree);
	}else{
		var myRegression = ecStat.regression(data.regression,option.series[0].data);
	}
	myRegression.points.sort(function(a, b) {
	    return a[0] - b[0];
	});
//jm.z	option.series[1] = {
//	        name: 'line',
//	        type: 'line',
//	        showSymbol: false,
//	        data: myRegression.points,
//	        markPoint: {
//	            itemStyle: {
//	                normal: {
//	                    color: 'transparent'
//	                }
//	            },
//	            label: {
//	                normal: {
//	                    show: true,
//	                    position: 'left',
//	                    formatter: myRegression.expression,
//	                    textStyle: {
//	                        color: '#333',
//	                        fontSize: 14
//	                    }
//	                }
//	            },
//	            data: [{
//	                coord: myRegression.points[myRegression.points.length - 1]
//	            }]
//	        }
//	};
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(data.id),data.theme);
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//Highcharts 3D柱状图和饼状图
function highcharts(data){
	jQuery.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "POST",
		url : data.url,
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(message, tst, jqXHR) {
			$(function () {
				$('#'+data.id).highcharts(
					 message
				);
			});
		}
	});
}

function echartsMultidataScatter(data){
	jQuery.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "POST",
		url : data.url,
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(message, tst, jqXHR) {
			var dataMap = message.data;
			option = {
				title: {
			        text: data.title,
			        subtext: data.subtitle,
			        left: 'center'
			    },
			    tooltip: {
			        position: 'top',
			        formatter: function (params) {
			            return  message.x[params.value[0]] + '<br/>' + message.y[params.value[1]] + ':' + params.value[2];
			        }
			    },
			    toolbox: {
			    	show: data.isNeedToolBox,
			        feature : {
			            mark : {show: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    dataZoom: [
	               {
	                   show: data.isNeedDataZoom,
	                   realtime: data.isNeedDataZoom,
	                   start: 0,
	                   end: 100,
	                   xAxisIndex: [0]
	               },{
	                   type: 'inside'
	               }
	           ],
			    grid: {
			        left: 2,
			        bottom: 10,
			        right: 10,
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        data: message.x,
			        boundaryGap: false,
			        splitLine: {
			            show: true,
			            lineStyle: {
			                color: '#999',
			                type: 'dashed'
			            }
			        },
			        axisLine: {
			            show: false
			        }
			    },
			    yAxis: {
			        type: 'category',
			        data: message.y,
			        axisLine: {
			            show: false
			        }
			    },
			    series: [{
			        name: 'Punch Card',
			        type: 'scatter',
			        symbolSize: function (val) {
			            return val[2] * 2;
			        },
			        data: dataMap,
			        animationDelay: function (idx) {
			            return idx * 5;
			        }
			    }]
			};
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById(data.id),data.theme);
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		    
		    myChart.on('click', function (params) {
	    		var aHref = $('#'+data.onClickId).attr("href");
	    		$('#'+data.onClickId).attr("href",aHref+"?id="+data.id+"&x="+message.x[params.value[0]]+"&y="+message.y[params.value[1]]);
	    		$('#'+data.onClickId)[0].click();
	    	});
		}
    });
}

function echartsMultidataHeatmap(data){
	jQuery.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "POST",
		url : data.url,
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(message, tst, jqXHR) {
			option = {
				title: {
			        text: data.title,
			        subtext: data.subtitle,
			        left: 'center'
			    },
			    tooltip: {
			        position: 'top',
			        formatter: function (params) {
			            return  message.x[params.value[0]] + '<br/>' + message.y[params.value[1]] + ':' + params.value[2];
			        }
			    },
			    toolbox: {
			    	show: data.isNeedToolBox,
			        feature : {
			            mark : {show: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    dataZoom: [
	               {
	                   show: data.isNeedDataZoom,
	                   realtime: data.isNeedDataZoom,
	                   start: 0,
	                   end: 100,
	                   xAxisIndex: [0]
	               },{
	                   type: 'inside'
	               }
	            ],
			    animation: false,
			    grid: {
			        height: '50%',
			        y: '10%'
			    },
			    xAxis: {
			        type: 'category',
			        data: message.x,
			        splitArea: {
			            show: true
			        }
			    },
			    yAxis: {
			        type: 'category',
			        data: message.y,
			        splitArea: {
			            show: true
			        }
			    },
			    visualMap: {
			        min: message.min,
			        max: message.max,
			        calculable: true,
			        orient: 'horizontal',
			        left: 'center',
			        bottom: '15%'
			    },
			    series: [{
			        type: 'heatmap',
			        data: message.data,
			        label: {
			            normal: {
			                show: true
			            }
			        },
			        itemStyle: {
			            emphasis: {
			                shadowBlur: 10,
			                shadowColor: 'rgba(0, 0, 0, 0.5)'
			            }
			        }
			    }]
			};
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById(data.id),data.theme);
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		    
		    myChart.on('click', function (params) {
	    		var aHref = $('#'+data.onClickId).attr("href");
	    		$('#'+data.onClickId).attr("href",aHref+"?id="+data.id+"&x="+message.x[params.value[0]]+"&y="+message.y[params.value[1]]);
	    		$('#'+data.onClickId)[0].click();
	    	});
		}
    });
}
