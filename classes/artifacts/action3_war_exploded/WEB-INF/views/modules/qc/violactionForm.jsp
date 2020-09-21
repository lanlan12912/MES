<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>违规记录管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#value").focus();
            $("#inputForm").validate({
                submitHandler:function (form) {
                    loading('正在提交，请稍等.....')
                    form.submit();
                },
                errorContainer:"#messageBox",
                errorPlacement:function (error,element) {
                    $("#messageBox").text("输入有误，请更正。");
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
<ul class="nav nav-tabs">
    <li><a href="${ctx}/qc/violaction/">违规记录列表</a></li>
    <li class="active"><a href="">违规记录${not empty violaction.id?'修改':'添加'}</a></li>
    <li><a href="${ctx}/qc/violaction/time">违规次数列表</a></li>
</ul>

<form:form id="inputForm" method="post" action="${ctx}/qc/violaction/save" modelAttribute="violaction" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">工站名称：</label>
        <div class="controls">
	        <form:select path="workStationInfos.id" id="wId" class="input-medium required">
	            <form:option value="" label=""/>
	            <form:options items="${stationList}" itemLabel="stationName" itemValue="id" htmlEscape="false"/>
	        </form:select>
            <span class="help-inline"><font color="red">*</font></span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">违规人员：</label>
        <div class="controls">
        	<form:select path="userName" id="eId" class="input-medium required">
	            <form:option value="" label=""/>
	            <form:options items="${employeeList}" itemLabel="employeeName" itemValue="id" htmlEscape="false"/>
	        </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">违规时间：</label>
        <div class="controls">
            <form:input path="violationTime" htmlEscape="false" maxlength="50" class="required wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">违规描述：</label>
        <div class="controls">
            <form:textarea path="violationDes" htmlEscape="false" maxlength="200" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
