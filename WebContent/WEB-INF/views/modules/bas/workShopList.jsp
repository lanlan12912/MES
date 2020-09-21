<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <script type="text/javascript">
        function page(n,s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("searchForm").submit();
        }
    </script>
    <title>车间管理</title>
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/workShop/">车间列表</a></li>
		<li><a href="${ctx}/bas/workShop/form">车间添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form action="${ctx}/bas/workShop/" id="searchForm" method="post" modelAttribute="workShop" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>车间名称：</label>
		<form:input path="shopName" class="input-medium" maxlength="50" htmlEscape="false"/>
		<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
	</form:form>
	<sys:message content="${message }"/>
	<!-- 3.列表部分 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>车间名称</th>
			<th>所属工厂</th>
			<th>车间编码</th>
			<th>车间负责人</th>
			<th>车间描述</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="workShop">
				<tr>
					<td>${workShop.shopName }</td>
					<td>${workShop.factory.factoryName }</td><!-- 注意：外键对象 -->
					<td>${workShop.shopNo }</td>
					<td>${workShop.master }</td>
					<td>${workShop.description }</td>
					<td>
						<a href="${ctx }/bas/workShop/form?id=${workShop.id}">修改</a>
						<a href="${ctx }/bas/workShop/delete?id=${workShop.id}" onclick="return confirmx('确认要删除该车间吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page }</div>
</body>
</html>