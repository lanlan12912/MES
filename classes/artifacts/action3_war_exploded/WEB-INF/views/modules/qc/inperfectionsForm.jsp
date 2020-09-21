<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>残次品上报</title>
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
    <li class="active"><a href="${ctx}/qc/inperfections/">残次品列表</a></li>
    <li class="active"><a href="">残次品${not empty inperfections.id?'修改':'添加'}</a></li>
</ul>

<form:form id="inputForm" method="post" action="${ctx}/qc/inperfections/save" modelAttribute="inperfections" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">工单编号：</label>
        <div class="controls">
            <form:select path="workOrder.id" id="wId" class="input-medium required">
                <form:option value="" label=""/>
                <form:options items="${workOrderList}" itemLabel="orderCode" itemValue="id" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font></span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">残次品详情：</label>
        <div class="controls">
            <form:input path="descc" htmlEscape="false" maxlength="200" rows="3" class="required"/>
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
