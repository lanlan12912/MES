<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default">
<title>工作时长</title>
</head>
<body>
<!--1.tab头部  -->
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bas/employeeTime/">员工工作时长（周）</a></li>
		<li><a href="${ctx}/bas/employeeTimeMonth/">员工工作时长（月）</a></li>
		<li class="active"><a href="${ctx}/bas/employeeTimeYear/">员工工作时长（年）</a></li>
	</ul>
<!--3.列表  -->
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<th>员工名稱</th>
			<th>开始时间</th>
			<th>年工作时长</th>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="EmployeeTime">
		<tr>
		            
					<td>${EmployeeTime.employeeName}</td>
					<td>${EmployeeTime.clockIn}</td>
					<td>${EmployeeTime.year}小时</td>

		</tr>
		</c:forEach>
		</tbody>
		</table>
	<!--4.分页  -->
		<div class="pagination">${page}</div>
</body>
</html>