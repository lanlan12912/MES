<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备保养计划</title>
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
<!-- tab -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equip/maintenance">设备保养计划列表</a></li>
		<li><a href="${ctx}/equip/maintenance/form">设备保养计划添加</a></li>
	</ul>
	<!-- 查询 -->
	<form:form id="searchForm" modelAttribute="maintenancePlan" action="${ctx}/equip/maintenance" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
			<label class="control-label">设备类型:</label>
			<form:select path="equipType" id="equipType" class="input-medium required" >
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>设备类型</th>
		<th>维护周期</th>
		<th>预警时间</th>
		<th>保养内容</th>
		<th>保养人</th>
		<th>操作</th></thead>
		<tbody>
		<c:forEach items="${page.list}" var="maintenancePlan">
			<tr>
				<td>${fns:getDictLabel(maintenancePlan.equipType, 'equip_type', '')}</td>
				<td>${fns:getDictLabel(maintenancePlan.cycle, 'equip_cycle', '')}</td>
				<td>${maintenancePlan.warnTime}</td>
				<td>${maintenancePlan.maintenance}</td>
				<td>${maintenancePlan.user.name}</td>
				<td>
    				<a href="${ctx}/equip/maintenance/form?id=${maintenancePlan.id}">修改</a>
					<a href="${ctx}/equip/maintenance/delete?id=${maintenancePlan.id}" onclick="return confirmx('确认要删除该保养计划吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>