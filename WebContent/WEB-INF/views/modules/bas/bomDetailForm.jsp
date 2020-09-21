<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>	Bom详情管理管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
<!-- tab -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/bomDetail/">Bom详情列表</a></li>
		<li class="active"><a href="${ctx}/bas/bomDetail/form">Bom详情${not empty bomDetail.id?'修改':'添加'}</a></li>
	</ul>
<!-- 表单 -->
	<form:form id="inputForm" action="${ctx}/bas/bomDetail/save" method="post" modelAttribute="bomDetail" class="breadcrumb form-search">
	  <form:hidden path="id"/>
	  	<div class="control-group">
			<label class="control-label">所属Bom:</label>
			<div class="controls">
				<form:select path="bom.id" id='bId' class="input-medium requied">
					<form:option value="" label=""/>
					<form:options items="${bomList}" itemLabel="bomName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料类型:</label>
			<div class="controls">
				<form:select path="mType" class="input-medium required">
					<form:options items="${fns:getDictList('material_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料的数量:</label>
			<div class="controls">
			<form:input path="mNum" maxlength="50" class="required number" htmlEscape="false"/>
			<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位:</label>
			<div class="controls">
				<form:select path="unit" class="input-medium required">
					<form:options items="${fns:getDictList('unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:area:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>