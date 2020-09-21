<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="default">
	<title>企业管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
	</head>
<body>
	<!-- tab头部 有企业列表和企业添加 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/enterprise/list">企业列表</a></li>
		<li><a href="${ctx}/bas/enterprise/form">企业添加</a></li>
	</ul>
	<!-- 查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/enterprise/list" modelAttribute="enterprise" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>企业名称：</label>
		<form:input path="enterName" maxlength="50" class="input-medium" htmlEscape="false"/>
		<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
	</form:form>
	
	<sys:message content="${message}"/>
	
	<!-- 查询列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业名称</th>
				<th>组织机构代码</th>
				<th>企业层次</th>
				<th>企业性质</th>
				<th>企业注册资本</th>
				<th>法人</th>
				<th>法人身份证</th>
				<th>地址</th>
				<th>邮政编码</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="enterprise">
				<tr>
					<td>${enterprise.enterName}</td>
					<td>${enterprise.enterCode}</td>
					<td>${enterprise.enterLevel}</td>
					<td>${enterprise.enterNature}</td>
					<td>${enterprise.enterCapital}</td>
					<td>${enterprise.enterLperson}</td>
					<td>${enterprise.enterLpCard}</td>
					<td>${enterprise.enterAddress}</td>
					<td>${enterprise.emailCode}</td>
					<td>${enterprise.enterRemarks}</td>
					<td>
						<a href="${ctx}/bas/enterprise/form?id=${enterprise.id}">修改</a>
						<a href="${ctx}/bas/enterprise/delete?id=${enterprise.id}" onclick="return confirm('确认要删除改企业吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<div class="pagination">${page}</div>
</body>
</html>