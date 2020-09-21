<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>员工管理</title>
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
		<li class="active"><a href="${ctx}/bas/employee/">员工信息列表</a></li>
		<li><a href="${ctx}/bas/employee/form">员工添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" modelAttribute="employee" action="${ctx}/bas/employee/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
			<label>员工工号 ：</label><form:input path="employeeNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			&nbsp;<label>员工姓名 ：</label><form:input path="employeeName" htmlEscape="false" maxlength="50" class="input-medium"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>员工号</th>
			<th>员工姓名</th>
			<th>入职日期</th>
			<th>身份证号</th>
			<th>所属部门</th>
			<th>性别</th>
			<th>年龄</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>地址</th>
			<th>操作</th></tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="employee">
			<tr>
				<td>${employee.employeeNo}</td>
				<td>${employee.employeeName}</td>
				<td>${employee.workDate}</td>
				<td>${employee.idCard}</td>
				<td>${employee.office.name}</td>
				<td>${employee.sex}</td>
				<td>${employee.age}</td>
				<td>${employee.telNum}</td>
				<td>${employee.address}</td>
				<td>${employee.email}</td>
				<td>
    				<a href="${ctx}/bas/employee/form?id=${employee.id}">修改</a>
					<a href="${ctx}/bas/employee/delete?id=${employee.id}" onclick="return confirmx('确认要删除该员工吗？', this.href)">删除</a>
    			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>