<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>车间管理</title>
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
		<li><a href="${ctx }/bas/workShop/">车间列表</a></li>
		<li class="active"><a href="">车间${not empty workShop.id?'修改':'添加' }</a></li>
	</ul>
	<br>
	<!-- 2.表单 -->
	<!-- form的class可能不对！！！！！！！！！！ -->
	<form:form action="${ctx}/bas/workShop/save" id="inputForm" method="post" modelAttribute="workShop" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">所属工厂：</label>
			<div class="controls">
				<form:select path="factory.id" id="fId" class="input-medium required">
					<form:option value="" lable=""/>
                    <form:options items="${factoryList}" itemLabel="factoryName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车间名称：</label>
			<div class="controls">
				<form:input path="shopName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车间编码：</label>
			<div class="controls">
				<form:input path="shopNo" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车间负责人：</label>
			<div class="controls">
				<form:input path="master" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车间描述：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)"/>&nbsp;
		</div>
	</form:form>
</body>
</html>