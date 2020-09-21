<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="default">
	<title>工位时长统计</title>
</head>
<body>
   <ul class="nav nav-tabs">
       <li class="active"><a href="${ctx}/bas/workCellTime">工位工作时长</a></li>
   </ul>
   <form:form id="searchForm" method="post" action="${ctx}/bas/workCellTime/form" modelAttribute="WorkCellTime" class="breadcrumb form-search">
       <div class="controls">
           <label>年：</label>
           <form:select path="year" id="year" class="input-medium required">
               <form:option value="2018" label=""/>
               <form:options items="${fns:getDictList('year')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
           <label>月：</label>
           <form:select path="month" id="month" class="input-medium required">
               <form:option value="1" label=""/>
               <form:options items="${fns:getDictList('month')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
           <label>周：</label>
            <form:select path="week" id="week" class="input-medium required">
               <form:option value="1" label=""/>
               <form:options items="${fns:getDictList('week')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
           <input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
       </div>
   </form:form>
   <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/echarts/4.3.0-rc.2/echarts-en.min.js"></script>
   <div>
      <div id="chartmain" style="width: 100%;height:500px;"></div>
      
      <script type="text/javascript">
         $(function(){
        	 var xData=[];
   	         var yData=[];
             <c:forEach items="${pic}" var="workCellTime">
                xData.push("${workCellTime.cellName}");
                yData.push("${workCellTime.totalTime}");
             </c:forEach>
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chartmain'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '工位工作时长',
                top:'bottom',
                left:'center'
            },
            tooltip: {
            	trigger:'axis'
            },
            legend: {
                
            },
            xAxis: {
                name:'工位',
                type:'category',
                data:xData
            },
            yAxis: {
            	name:'工作时长/小时'
            },
            series: [{
                name: '工作时长',
                type: 'bar',
                data:yData
            }]
        };
        console.log(1);
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
     })
    </script>
   </div>
</body>
</html>