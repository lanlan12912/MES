<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备台账</title>
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
<!-- tab -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equip/equip">设备台账列表</a></li>
	</ul>
	<!-- 查询 -->
	<form:form id="searchForm" modelAttribute="equipment" action="${ctx}/equip/equip" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<div class="controls">
			<label class="control-label">设备类型:</label>
			<form:select path="type" id="type" class="input-medium" >
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label class="control-label">设备规格:</label>
			<form:select path="spec" id="spec" class="input-medium">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_spec')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		
		<label>编码 ：</label><form:input path="qrCode" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>编码</th>
		<th>设备类型</th>
		<th>设备规格</th>
		<th>供应商</th>
		<th>生产商</th>
		<th>出厂编号</th>
		<th>用途</th>
		<th>采购日期</th>
		<th>资产负责人</th></thead>
		<tbody>
		<c:forEach items="${page.list}" var="equipment">
			<tr>
				<td>${equipment.qrCode}</td>
				<td>${fns:getDictLabel(equipment.type, 'equip_type', '')}</td>
				<td>${fns:getDictLabel(equipment.spec, 'equip_spec', '')}</td>
				<td>${equipment.supplier}</td>
				<td>${equipment.manufacturer}</td>
				<td>${equipment.factoryNumber}</td>
				<td>${equipment.purpose}</td>
				<td>${equipment.buyDate}</td>
				<td>${equipment.person}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>