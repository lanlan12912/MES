<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工序管理</title>
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
		<li class="active"><a href="${ctx}/tec/process/">工序列表</a></li>
		<li><a href="${ctx}/tec/process/form">工序添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="process" action="${ctx}/tec/process/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>工序编码 ：</label><form:input path="proCode" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>工序编码</th><th>工序名称</th><th>工序描述</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="process">
			<tr>
				<td>${process.proCode}</td>
				<td>${process.proName}</td>
				<td>${process.proDesc}</td>
				
				<td>
    				<a href="${ctx}/tec/process/form?id=${process.id}">修改</a>
					<a href="${ctx}/tec/process/delete?id=${process.id}" onclick="return confirmx('确认要删除该物料吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>