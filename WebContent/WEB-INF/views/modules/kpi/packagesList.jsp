<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>装袋绩效记录</title>
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
	<form:form id="searchForm" modelAttribute="packages" action="${ctx}/kpi/packages/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>开始时间：</label><form:input path="startTime" htmlEscape="false" maxlength="50" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
		&nbsp;<label>结束时间：</label><form:input path="endTime" htmlEscape="false" maxlength="50" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>日期</th><th>总数</th><th>薪资</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="packages">
			<tr>
				<td>${packages.time}</td>
				<td>${packages.weight}</td>
				<td>${packages.money}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>