<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>产品管理</title>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler:function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer:"#messageBox",
				errorPlacement:function(error,element){
					$("#messageBox").text("输入有误，请先更正");
					if(element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					}else{
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
		<li><a href="${ctx }/bas/product/">产品列表</a></li>
		<li class="active"><a href="">车间${not empty product.id?'修改':'添加' }</a></li>
	</ul>
	<br>
	<!-- 2.表单 -->
	<!-- form的class可能不对！！！！！！！！！！ -->
	<form:form action="${ctx}/bas/product/save" id="inputForm" method="post" modelAttribute="product" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input path="productName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品简称：</label>
			<div class="controls">
				<form:input path="productAbbr" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品计量单位：</label>
			<div class="controls">
				<form:input path="productUnit" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否需要首件检测：</label>
			<div class="controls">
				<form:select path="firstCheck" id="firstCheck" class="input-medium required">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条码：</label>
			<div class="controls">
				<form:input path="qrCode" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">管理方式：</label>
			<div class="controls">
				<form:input path="manageWay" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批次：</label>
			<div class="controls">
				<form:input path="lotNumber" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="state" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品简述：</label>
			<div class="controls">
				<form:input path="productDesc" htmlEscape="false" maxlength="200"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">属性：</label>
			<div class="controls">
				<form:input path="productProp" htmlEscape="false" maxlength="200"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产流程：</label>
			<div class="controls">
				<form:select path="flow.id" id="fId" class="input-medium required">
                    <form:options items="${flowList}" itemLabel="flowName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)"/>&nbsp;
		</div>
	</form:form>
</body>
</html>