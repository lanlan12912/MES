<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工艺路由与工序关系</title>
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
		<li class="active" ><a href=" ${ctx}/tec/flowProcess/">工艺路由与工序关系</a></li>
		<li><a href="${ctx}/tec/flowProcess/form">工艺路由与工序关系添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="flowProcess" action="${ctx}/tec/flowProcess/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>所属流程：</label><form:input path="flow.flowName" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>工序名称</th><th>所属工艺流程</th><th>排序号</th><th>操作</th></tr></thead>
		<tbody>
		<c:if test="${page.count <= 0}">
			<tr>
				<td colspan="4" text-align="center">暂无数据</td>
			</tr>
		</c:if>
		<c:if test="${page.count >0}">
			<c:forEach items="${page.list}" var="flowProcess">
				<tr>
					<td>${flowProcess.process.proName}</td>
					<td>${flowProcess.flow.flowName}</td>
					<td>${flowProcess.sort}</td>
					<td>
						<a href="${ctx}/tec/flowProcess/form?id=${flowProcess.id}">修改</a>
						<a href="${ctx}/tec/flowProcess/delete?id=${flowProcess.id}" onclick="return confirmx('确认要删除该关系吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>