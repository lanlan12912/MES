<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>绩效参数与工站关系</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n,s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		
	}
</script>
</head>
<body>
	<!-- tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/kpi/performStation/">绩效参数与工站关系列表</a></li>
		<li><a href="${ctx}/kpi/performStation/form">绩效参数与工站关系添加</a></li>
	</ul>
	<!-- 查询部分 -->
	<form:form id="searchForm" modelAttribute="performStation"
		action="${ctx}/kpi/performStation/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<div class="controls">
			<label>绩效名称 ：</label>
			<form:input path="performType.performTypeName" htmlEscape="false" maxlength="50" class="input-medium" />
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		</div>
		
	</form:form>
	<sys:message content="${message}" />
	
	<!-- 列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>绩效名称</th>
				<th>工站名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="performStation">
				<tr>
					<td>${performStation.performType.performTypeName}</td>
					<td>${performStation.workStationInfos.stationName}</td>

					<td><a href="${ctx}/kpi/performStation/form?id=${performStation.id}">修改</a>
						<a href="${ctx}/kpi/performStation/delete?id=${performStation.id}"
						onclick="return confirmx('确认要删除该关系信息吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<!-- 分页 -->
		<div class="pagination">${page}</div>
	</table>
</body>
</html>