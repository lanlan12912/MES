<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产线管理</title>
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
		<li class="active"><a href="${ctx}/bas/line/">产线列表</a></li>
		<li><a href="${ctx}/bas/line/form">产线添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="line" action="${ctx}/bas/line/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>产线名称 ：</label><form:input path="lineName" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>产线名称</th><th>所属车间</th><th>产线编码</th><th>产线负责人</th><th>产线描述</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="line">
			<tr>
				<td>${line.lineName}</td>
				<td>${line.workshop.shopName}</td>
				<td>${line.lineNumber}</td>
				<td>${line.lineMaster}</td>
				<td>${line.lineDescription}</td>
				<td>
    				<a href="${ctx}/bas/line/form?id=${line.id}">修改</a>
					<a href="${ctx}/bas/line/delete?id=${line.id}" onclick="return confirmx('确认要删除该产线吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>