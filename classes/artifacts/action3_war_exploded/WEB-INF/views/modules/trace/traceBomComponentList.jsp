<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <title>Bom管理</title>
    <script type="text/javascript">
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
        }
    </script>
</head>
<body>
<!--1.tab-->
<ul class="nav nav-tabs">
    <li class="active">
        <a href="${ctx}/trace/traceBomComponent">原材料追溯列表</a>
    </li>
</ul>
<!--2.查询-->
<form:form id="searchForm" method="post" action="${ctx}/trace/traceBomComponent/" modelAttribute="traceBomComponent" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
    <input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
    <div class="controls">
        <lable>产品批次：</lable>
        <form:input path="lotNumber" maxlength="50" class="input-mdeium" htmlEscape="false"/>
        <input id="btnSubmit" type="submit" value="查询" class="btn btn-primay"/>
    </div>
</form:form>
<!--3.列表-->
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <th>产品批次</th>
    <th>产品名称</th>
    <th>所用物料名称</th>
    <th>材料类型</th>
    <th>材料数量</th>
    <th>单位</th>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="tracebomcomponent">
        <tr>
            <td>${tracebomcomponent.product.lotNumber}</td>
            <td>${tracebomcomponent.product.productName}</td>
            <td>${tracebomcomponent.bomName}</td>
            <td>${fns:getDictLabel(tracebomcomponent.bomDetail.mType,'material_type' ,'')}</td>
            <td>${tracebomcomponent.bomDetail.mNum}</td>
            <td>${fns:getDictLabel(tracebomcomponent.bomDetail.unit,'unit' ,'')}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!--4.分页-->
<div class="pagination">${page}</div>
</body>
</html>
