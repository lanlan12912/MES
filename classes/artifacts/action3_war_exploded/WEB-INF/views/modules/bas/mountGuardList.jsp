<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>上岗记录管理</title>
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
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/mountGuard/">员工工作信息列表</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" modelAttribute="mountGuard" action="${ctx}/bas/mountGuard/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<div class="controls">
			<label>员工号:</label>
			<form:input path="employee.employeeNo" maxlength="50" class="input-medium" htmlEscape="false"/>
			<label>工位：</label>
			<form:select path="workCell.id" id="wId" class="input-medium required" >
				<form:option value="" label=""/>
				<form:options items="${workCellList}" itemLabel="cellName" itemValue="id" htmlEscape="false"/>
			</form:select>
			<label>工作状态:</label>
			<form:select path="workStatus" id="workStatus" class="input-medium required">
				<form:option value="" label=""/>
				<form:option value="上班" label="上班"/>
				<form:option value="下班" label="下班"/>
			</form:select>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>员工编号</th>
			<th>员工姓名</th>
			<th>所属部门</th>
			<th>所属产线</th>
			<th>所属工站</th>
			<th>工位</th>
			<th>上班时间</th>
			<th>下班时间</th>
			<th>工作状态</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="m">
				<tr>
					<td>${m.employee.employeeNo}</td>
					<td>${m.employee.employeeName}</td>
					<td>${m.office.name}</td>
					<td>${m.line.lineName}</td>
					<td>${m.workStationInfos.stationName}</td>
					<td>${m.workCell.cellName}</td>
					<td>${m.clockIn}</td>
					<td>${m.clockOff}</td>
					<td>${m.workStatus}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>