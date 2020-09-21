<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>违规记录管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n,s) {
            $("pageNo").val(n);
            $("pageSize").val(s);
            $("#searchForm").submit();
        }
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li class="active"><a href="${ctx}/qc/violaction">违规记录列表</a></li>
	    <li><a href="${ctx}/qc/violaction/form">违规记录添加</a></li>
	    <li><a href="${ctx}/qc/violaction/time">违规次数列表</a></li>
	</ul>
	<!--查询-->
	<form:form action="${ctx}/qc/violaction" id="searchForm" method="post" modelAttribute="violaction" class="breadcrumb form-search">
	    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	    <div class="controls">
	        <label>工站名称：</label>
	        <form:input path="workStationInfos.stationName" class="input-medium" maxlength="50" htmlEscape="false"/>
	        <input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
	    </div>
	</form:form>
	<sys:message content="${message}"/>
	<!--列表-->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	    <thead>
	         <th>工序编码</th>
	         <th>工站名称</th>
	         <th>违规人员</th>
	         <th>违规描述</th>
	         <th>违规时间</th>
	         <th>操作</th>
	    </thead>
	    <tbody>
	    <c:forEach items="${page.list}" var="violaction">
	        <tr>
	            <td>${violaction.process.proCode}</td>
	            <td>${violaction.workStationInfos.stationName}</td>
	            <td>${violaction.userName}</td>
	            <td>${violaction.violationDes}</td>
	            <td>${violaction.violationTime}</td>
	            <td>
	                <a href="${ctx}/qc/violaction/form?id=${violaction.id}">修改</a>
	                <a href="${ctx}/qc/violaction/delete?id=${violaction.id}" onclick="return confirmx('确认要删除该违规记录信息吗？',this.href)">删除</a>
	            </td>
	        </tr>
	    </c:forEach>
	    </tbody>
	</table>
	<!--分页-->
	<div class="pagination">${page}</div>
</body>
</html>
