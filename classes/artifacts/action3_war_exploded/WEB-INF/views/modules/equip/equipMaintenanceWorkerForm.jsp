<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备维修派工</title>
	<meta name="decorator" content="default"/>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="">设备报修派工</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="equipFaultReport" action="${ctx}/equip/report/maintenance" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="equipId"/>
		<form:hidden path="reportPerson"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">设备编号:</label>
			<div class="controls">
				<form:input path="equipNo" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备类型:</label>
			<div class="controls">
				<form:select path="equipType" id="equipType" class="input-medium" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备位置:</label>
			<div class="controls">
				<form:select path="equipLoc" id="equipLoc" class="input-medium" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${lineList}" itemLabel="lineName" itemValue="lineNumber" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">故障描述:</label>
			<div class="controls">
				<form:textarea path="faultDesc" htmlEscape="false" maxlength="200" row="5" class="input_xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修人:</label>
			<div class="controls">
				<sys:treeselect url="/sys/office/treeData?type=3" id="maintenanceWorker" value="${equipFalutReport.maintenanceWorker}" 
					labelName="equipFalutReport.maintenanceWorker" labelValue="${equipFalutReport.maintenanceWorker}" title="用户" 
					name="maintenanceWorker" cssClass="input-larg required" allowClear="true" 
					notAllowSelectParent="true">
					<span class="help-inline"><font color="red">*</font> </span>
				</sys:treeselect>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>