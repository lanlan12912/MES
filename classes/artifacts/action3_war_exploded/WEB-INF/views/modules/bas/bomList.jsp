<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>	Bom管理</title>
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
		<li class="active"><a href="${ctx}/bas/bom/">Bom列表</a></li>
		<li><a href="${ctx}/bas/bom/form">Bom添加</a></li>
	</ul>
<!-- 查询 -->
	<form:form id="searchForm" action="${ctx}/bas/bom/" method="post" modelAttribute="bom" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
		<label>Bom名称:</label>
		<form:input path="bomName" maxlength="50" class="input-medium" htmlEscape="false"/>
		<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
<!-- 列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>Bom名称</th>
			<th>Bom版本</th>
			<th>Bom状态</th>
			<th>所属产品</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bom">
				<tr>
				<td>${bom.bomName}</td>
				<td>${bom.bomVersion}</td>
				<td>${bom.status}</td>
				<td>${bom.product.productName}</td>
				<td>
    				<a href="${ctx}/bas/bom/form?id=${bom.id}">修改</a>
					<a href="${ctx}/bas/bom/delete?id=${bom.id}" onclick="return confirmx('确认要删除该Bom信息吗？', this.href)">删除</a>
				</td>
			  </tr>
			</c:forEach>
		</tbody>
	</table>
<!-- 分页 -->
	<div class="pagination">${page}</div>	
</body>
</html>