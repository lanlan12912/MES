<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html>
	<head>
		<meta name="decorator" content="default">
		<title>订单信息</title>
		<script type="text/javascript">
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
			}
		</script>
	</head>
	<body>
		<!-- 1.tab列表 -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/bas/order/">订单列表</a></li>
		</ul>
		<!-- <sys:message content="${message}"/> -->
		<!-- 2.列表 -->
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<th>订单号</th>
				<th>订购商</th>
				<th>产品名称</th>
				<th>数量</th>
				<th>单位</th>
				<th>订购日期</th>
				<th>销售人</th>
				<th>状态</th>
				<th>操作</th>
				<tbody>
				<c:forEach items="${page.list}" var="order">
					<tr>
						<td>${order.orderNo}</td>
						<td>${order.indentor}</td>
						<td>${order.product.productName}</td>
						<td>${order.quantity}</td>
						<td>${fns:getDictLabels(order.unit, 'unit', '')}</td>
						<td>${order.orderDate}</td>
						<td>${order.salesman}</td>
						<td>${fns:getDictLabels(order.status, 'order_status', '')}</td>
						<td><a href="${ctx}/bas/order/doWork?id=${order.id}&status=${order.status}">下工单</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</thead>
		</table>
		<sys:message content="${message}"/>
		<!-- 3.分页 -->
		<div class="pagination">${page}</div>
	</body>
</html>