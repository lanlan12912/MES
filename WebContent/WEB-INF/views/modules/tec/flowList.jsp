<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工艺流程管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tec/flow/">流程列表</a></li>
		<li><a href="${ctx}/tec/flow/form">流程添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="flow" action="${ctx}/tec/flow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>流程编码 ：</label><form:input path="flowCode" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>流程编码</th><th>流程版本</th><th>流程名称</th><th>流程描述</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="flow">
			<tr>
				<td>${flow.flowCode}</td>
				<td>${flow.version}</td>
				<td>${flow.flowName}</td>
				<td>${flow.flowDesc}</td>
				<td>
    				<a href="${ctx}/tec/flow/form?id=${flow.id}">修改</a>
					<a href="${ctx}/tec/flow/delete?id=${flow.id}" onclick="return confirmx('确认要删除该物料吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>