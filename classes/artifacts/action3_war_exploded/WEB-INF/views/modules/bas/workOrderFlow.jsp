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
		<li><a href="${ctx}/bas/workOrder/">工单列表</a></li>
		<li class="active"><a href="#">工艺流程</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" modelAttribute="workOrder" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>工单号：</label>
		<form:input path="orderCode" maxlength="50" class="input-medium" htmlEscape="false" readonly="true"/>
		<label>产品：</label>
		<form:input path="product.productName" maxlength="50" class="input-medium" htmlEscape="false" readonly="true"/>
		<label>工艺流程：</label>
		<form:input path="" value="${product.flow.flowName}" maxlength="50" class="input-medium" htmlEscape="false" readonly="true"/>
	</form:form>
	<!-- 3.列表 -->
	<label style="padding-left:10px;padding-bottom:5px;">工艺流程详情：</label>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>工序编码</th>
			<th>工序名称</th>
			<th>工序描述</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="flowProcess">
				<tr>
					<td>${flowProcess.process.proCode}</td>
					<td>${flowProcess.process.proName}</td>
					<td>${flowProcess.process.proDesc}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>