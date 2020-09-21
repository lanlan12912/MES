<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>	Bom管理</title>
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
		<li class="active"><a href="${ctx}/bas/bom/">Bom列表</a></li>
		<li class="active"><a href="${ctx}/bas/bom/form">Bom${not empty bom.id?'修改':'添加'}</a></li>
	</ul>
<!-- 表单 -->
	<form:form id="inputForm" action="${ctx}/bas/bom/save" method="post" modelAttribute="bom" class="breadcrumb form-search">
	  <form:hidden path="id"/>
		<div class="controls-group">
			<label>Bom名称:</label>
			<form:input path="bomName" maxlength="50" class="required" htmlEscape="false"/>
			<span class="help-inline"><font color="red">*</font></span>
		</div>
		<div class="controls-group">
			<label>Bom版本:</label>
			<form:input path="bomVersion" maxlength="50" class="required" htmlEscape="false"/>
			<span class="help-inline"><font color="red">*</font></span>
		</div>
		<div class="controls-group">
			<label>Bom状态:</label>
			<form:input path="status" maxlength="50" class="required" htmlEscape="false"/>
			<span class="help-inline"><font color="red">*</font></span>
		</div>
		<div class="control-group">
			<label class="control-label">所属产品:</label>
			<div class="controls">
				<form:select path="product.id" class="input-medium requied">
				<form:options items="${productList}" itemLabel="productName" itemValue="id" htmlEscape="false"/>
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