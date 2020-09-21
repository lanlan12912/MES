<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备保养计划</title>
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
		<li><a href="${ctx}/equip/maintenance">设备保养计划列表</a></li>
		<li class="active"><a href="${ctx}/equip/maintenance/form?id=${maintenancePlan.id}">保养计划${not empty maintenancePlan.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="maintenancePlan" action="${ctx}/equip/maintenance/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">设备类型:</label>
			<div class="controls">
				<form:select path="equipType" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养周期:</label>
			<div class="controls">
				<form:select path="cycle"  class="input-medium required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('equip_cycle')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警时间:</label>
			<div class="controls">
				<form:input path="warnTime" htmlEscape="false" maxlength="50" class="number"/>天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养人:</label>
			<div class="controls">
				<sys:treeselect url="/sys/office/treeData?type=3" id="user" value="${maintenancePlan.user.id}" labelName="user.name" 
					labelValue="${maintenancePlan.user.name}" title="用户" name="user.id" cssClass="input-larg" allowClear="true" notAllowSelectParent="true">
				</sys:treeselect>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养内容:</label>
			<div class="controls">
				<form:textarea path="maintenance" htmlEscape="false" maxlength="200" row="5" class="input_xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>