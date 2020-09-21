<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备报修管理</title>
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
		<li class="active"><a href="${ctx}/equip/report">设备报修列表</a></li>
	</ul>
	<!-- 查询 -->
	<form:form id="searchForm" modelAttribute="equipFaultReport" action="${ctx}/equip/report" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<div class="controls">
			<label class="control-label">设备类型:</label>
			<form:select path="equipType" id="equipType" class="input-medium required" >
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label class="control-label">设备位置:</label>
			<form:select path="equipLoc" id="equipLoc" class="input-medium required">
				<form:option value="" label=""/>
				<form:options items="${lineList}" itemLabel="lineName" itemValue="lineNumber" htmlEscape="false"/>
			</form:select>
			<label class="control-label">维修状态:</label>
			<form:select path="status" id="status" class="input-medium required" >
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_maintenance')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>设备编号</th>
		<th>设备类型</th>
		<th>产线</th>
		<th>故障描述</th>
		<th>上报时间</th>
		<th>上报人</th>
		<th>处理状态</th>
		<th>操作</th></thead>
		<tbody>
		<c:forEach items="${page.list}" var="report">
			<tr>
				<td>${report.equipNo}</td>
				<td>${fns:getDictLabel(report.equipType, 'equip_type', '')}</td>
				<td>${report.equipLoc}</td>
				<td>${report.faultDesc}</td>
				<td>${fns:formatDateTime(report.createDate)}</td>
				<td>${report.reportPerson}</td>
				<td>${fns:getDictLabel(report.status, 'equip_maintenance', '')}</td>
				<td>
					<a href="${ctx}/equip/report/assign?id=${report.id}">派工</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>