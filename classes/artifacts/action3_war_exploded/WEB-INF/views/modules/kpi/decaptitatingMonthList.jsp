<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>去头工站绩效</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<!-- tab头部 -->
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/kpi/decaptitating/">去头工站日度绩效</a></li>
		<li class="active"><a href="${ctx}/kpi/decaptitating/month">去头工站月度绩效</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="decaptitating" action="${ctx}/kpi/decaptitating/month" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>开始时间：</label><form:input path="startTime" htmlEscape="false" maxlength="50" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
		&nbsp;<label>结束时间：</label><form:input path="endTime" htmlEscape="false" maxlength="50" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
		&nbsp;<label>人员名称：</label><form:input path="employeeName" htmlEscape="false" maxlength="50" class="input-medium"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		<a href="${ctx}/kpi/decaptitating/export?isMonth=1">
			<input id="btnExport" class="btn btn-primary" type="button" value="报表"/>
		</a>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>日期</th><th>人员</th><th>总重</th><th>薪资</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="decaptitating">
			<tr>
				<td>${decaptitating.time}</td>
				<td>${decaptitating.employeeName}</td>
				<td>${decaptitating.weight}</td>
				<td>${decaptitating.money}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>