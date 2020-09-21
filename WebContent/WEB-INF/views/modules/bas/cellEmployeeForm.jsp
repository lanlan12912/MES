<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>员工管理</title>
<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bas/cellEmployee/">工位与员工关系</a></li>
		<li class="active"><a href="">工位与员工关系${not empty cellEmployee.id?'修改':'添加'}</a></li>
	</ul>
	<!-- 2.表单 -->
	<form:form id="inputForm" modelAttribute="cellEmployee" action="${ctx}/bas/cellEmployee/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">员工:</label>
			<div class="controls">
					<form:select path="employee.id" id="employeeId" class="input-medium required" >
						<form:option value="" label=""/>
						<form:options items="${employeeList}" itemLabel="employeeName"  itemValue="id" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属工位:</label>
			<div class="controls">
					<form:select path="workCell.id" id="cellId" class="input-medium required" >
						<form:option value="" label=""/>
						<form:options items="${workCellList}" itemLabel="cellName"  itemValue="id" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>