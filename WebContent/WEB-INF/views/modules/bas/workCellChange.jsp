<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="default">
	<title>工位时长月变化趋势</title>
</head>
<body>
   <ul class="nav nav-tabs">
       <li class="active"><a href="${ctx}/bas/workCellChange">工位时长月变化趋势</a></li>
   </ul>
   <form:form id="searchForm" method="post" action="${ctx}/bas/workCellChange/form" modelAttribute="WorkCellChange" class="breadcrumb form-search">
       <div class="controls">
           <label>工位：</label>
           <form:select path="cellName" id="cellName" class="input-medium required">
               <form:option value="" label=""/>
               <form:options items="${WorkCellList}" itemLabel="cellName" itemValue="cellName" htmlEscape="false"/>
            </form:select>
           <label>年：</label>
           <form:select path="year" id="year" class="input-medium required">
               <form:option value="" label=""/>
               <form:options items="${fns:getDictList('year')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
             <c:forEach items="${pic}" var="workCellChange">
                xData.push("${workCellChange.month}");
                yData.push("${workCellChange.totalTime}");
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
                name:'月份 ',
                type:'category',
                data:xData
            },
            yAxis: {
            	name:'工作时长/小时',
            	type:'value'
            },
            series: [{
                name: '工作时长',
                type: 'line',
                data:yData
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
     })
    </script>
   </div>
</body>
</html>