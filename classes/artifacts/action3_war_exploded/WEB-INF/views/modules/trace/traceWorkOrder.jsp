<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>工单管理</title>
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
	<!-- 1.tab -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/records/">工作记录列表</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/trace/traceWorkOrder/" modelAttribute="records" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			<label>批次：</label>
			<form:input path="lotNumber" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>批次</th>
			<th>产品名称</th>
			<th>工序号</th>
			<th>工序名称</th>
			<th>工位号</th>
			<th>工位名称</th>
			<th>员工号</th>
			<th>员工名称</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="records">
				<tr>
					<td>${records.lotNumber}</td>
					<td>${records.productName}</td>
					<td>${records.proCode }</td>
					<td>${records.proName }</td>
					<td>${records.cellNumber }</td>
					<td>${records.cellName }</td>
					<td>${records.employeeNo }</td>
					<td>${records.employeeName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>