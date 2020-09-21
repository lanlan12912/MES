<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>违规记录管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li><a href="${ctx}/qc/violaction">违规记录列表</a></li>
	    <li><a href="${ctx}/qc/violaction/form">违规记录添加</a></li>
	    <li class="active"><a href="${ctx}/qc/violaction/time">违规次数列表</a></li>
	</ul>
	<!--查询-->
	<form:form action="${ctx}/qc/violaction/time" id="searchForm" method="post" modelAttribute="violaction" class="breadcrumb form-search">
	    <div class="controls">
	        <label>违规日期：</label>
	        <form:input path="violationTime" htmlEscape="false" maxlength="50" class="required wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/>
	        <input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
	    </div>
	</form:form>
	<!--列表-->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	    <thead>
	         <th>违规人员</th>
	         <th>违规次数</th>
	    </thead>
	    <tbody>
		    <c:choose>
		    	<c:when test="${empty list}">
		    		<tr><td colspan="2">无违规人员！</td></tr>
		    	</c:when>
		    	<c:otherwise>
		    		<c:forEach items="${list}" var="violaction">
				        <tr>
				            <td>${violaction.userName}</td>
				            <td>${violaction.count}</td>
				        </tr>
		    		</c:forEach>
		    	</c:otherwise>
		    </c:choose>
	    </tbody>
	</table>
</body>
</html>
