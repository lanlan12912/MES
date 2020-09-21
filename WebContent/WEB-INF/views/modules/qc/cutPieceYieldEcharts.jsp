<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="decorator" content="default">
	<title>切片质量检测</title>
	<!-- 引入 echarts.js 所有要用的引用都已通过decorator引用了,直接用就可以 -->
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qc/cutPieceYield">切片质量检测</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form action="${ctx}/qc/cutPieceYield/" id="searchForm" method="post" modelAttribute="cutPieceYield" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
			<label>开始时间：</label>
			<form:input path="startTime" class="input-medium wdate" maxlength="50" htmlEscape="false" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			&nbsp;&nbsp;
			<label>结束时间：</label>
			<form:input path="endTime" class="input-medium wdate" maxlength="50" htmlEscape="false" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<sys:message content="${message }"/>
	<!-- 3.图表 -->
	<!-- 具体教程看Echarts官网 -->
	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="chartmain" style="width:100%;height: 500px"></div>
	<script type="text/javascript">
	//当页面进行加载时，直接显示柱状图
		$(function(){
			var xData=[];
			var yData=[];
			<c:forEach items="${yieldList}" var="cutPieceYield">
				xData.push("${cutPieceYield.cardId}${cutPieceYield.employeeName}");
				yData.push(("${cutPieceYield.yield}"*100).toFixed(2));
				//fix->固定小数位为2位
			</c:forEach>
	        // 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('chartmain'));

	        // 指定图表的配置项和数据
	        var option = {
	            title: {
	                text: '切片工位成品率',
	                top:'bottom',
	                left:'center'
	            },
	            tooltip: {//提示信息
	            	trigger:'axis'
	            },
	            legend: {},
	            xAxis: {
	            	name:'员工工号',
	            	type:'category',
	                data: xData
	            },
	            yAxis: {
	            	name:'成品率%'
	            },
	            series: [{
	                name: '成品率',
	                type: 'bar',
	                data: yData
	            }]
	        };
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
		})
    </script>
	<!-- 4.分页 -->
	<div class="pagination">${page }</div>
</body>
</html>