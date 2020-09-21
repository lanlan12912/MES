<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>残次品上报</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n,s) {
            ${"#pageNo"}.val(n);
            ${"#pageSize"}.val(s);
            ${"#searchForm"}.submit();
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/qc/inperfections/">残次品列表</a></li>
    <li><a href="${ctx}/qc/inperfections/form">残次品添加</a></li>
</ul>
<!--查询-->
<form:form id="searchForm" method="post" action="${ctx}/qc/inperfections" modelAttribute="inperfections" class="breadcrumb">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div class="controls">
        <label>产品名称：</label>
        <form:input path="product.productName" maxlength="50" class="input-medium" htmlEscape="false"/>
        <input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
    </div>
</form:form>
<sys:message content="${message}"/>
<!--列表-->
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <th>产品名称</th>
    <th>产品批次</th>
    <th>工单编号</th>
    <th>详情</th>
    <th>操作</th>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="inperfections">
        <tr>
            <td>${inperfections.product.productName}</td>
            <td>${inperfections.bn}</td>
            <td>${inperfections.workOrder.orderCode}</td>
            <td>${inperfections.descc}</td>
            <td>
                <a href="${ctx}/qc/inperfections/form?id=${inperfections.id}">修改</a>
                <a href="${ctx}/qc/inperfections/delete?id=${inperfections.id}" onclick="return confirmx('确认要删除该残次品信息吗？',this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!--分页-->
<div class="pagination">${page}</div>
</body>
</html>
