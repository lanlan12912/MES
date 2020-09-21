<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="default">
	<title>工单管理</title>
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
    <!-- tab头部 -->
    <ul class="nav nav-tabs">
       <li><a href="${ctx}/bas/workOrder/">工单管理</a></li>
       <li class="active"><a href="">workOrder${not empty workOrder.id? '修改' : '添加 ' }</a></li><!-- id为空添加不为空修改 -->
   </ul>
   <!-- 表单 -->
   <form:form id="inputForm" method="post" action="${ctx}/bas/workOrder/save" modelAttribute="workOrder" class="form-horizontal">
        <form:hidden path="id"/>
        <div class="control-group">
           <label class="control-label">工单号：</label>
           <div class="control">
              <form:input path="orderCode" htmlEscape="false" maxlength="50" class="required"/>
              <span class="help-inline"><font color="red">*</font></span>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">订单号：</label>
           <div class="control">
              <form:input path="order.orderNo" value="${curOrder.orderNo}" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
              <form:hidden path="order.id" value="${curOrder.id}"/>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">工单类型：</label>
           <div class="control">
              <form:select path="orderType" id="orderType" class="input-medium required">
                 <form:option value="" label=""/>
               <form:options items="${fns:getDictList('workorder_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
              </form:select>
              <span class="help-inline"><font color="red">*</font></span>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">工单生产数量：</label>
           <div class="control">
              <form:input path="amount" value="${curOrder.quantity}" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">计量单位：</label>
           <div class="control">
              <form:input path="unitName" value="${fns:getDictLabel(curOrder.unit, 'unit', '')}" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">预计生产时间：</label>
           <div class="control">
              <form:input path="estStartTime" htmlEscape="false" maxlength="50" class="input-larg required Wdate"
              onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
              <span class="help-inline"><font color="red">*</font></span>
           </div>
        </div>
         <div class="control-group">
           <label class="control-label">预计结束时间：</label>
           <div class="control">
              <form:input path="estEndTime" htmlEscape="false" maxlength="50" class="input-larg required Wdate"
              onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
              <span class="help-inline"><font color="red">*</font></span>
           </div>
        </div>
         <div class="control-group">
           <label class="control-label">实际生产时间：</label>
           <div class="control">
              <form:input path="actStartTime" htmlEscape="false" maxlength="50" class="input-larg Wdate"
              onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">投入数量：</label>
           <div class="control">
              <form:input path="inAmount" htmlEscape="false" maxlength="50" class='digits'/>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">产出数量：</label>
           <div class="control">
              <form:input path="outAmount" htmlEscape="false" maxlength="50" class="digits"/>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">报废数量：</label>
           <div class="control">
              <form:input path="scrapAmount" htmlEscape="false" maxlength="50" class="digits"/>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">工单状态：</label>
           <div class="control">
              <form:select path="state" id="state" class="input-medium required">
                 <form:option value="" label=""/>
               <form:options items="${fns:getDictList('workorder_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
              </form:select>
              <span class="help-inline"><font color="red">*</font></span>
           </div>
        </div>
        <div class="control-group">
           <label class="control-label">所属产线：</label>
           <div class="control">
              <form:select path="line.id" id="lId" class="input-medium required">
                 <form:option value="" label=""/>
               <form:options items="${lineList}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
              </form:select>
              <span class="help-inline"><font color="red">*</font></span>
           </div>
        </div>
         <div class="control-group">
           <label class="control-label">产品：</label>
           <div class="control">
              <form:input path="product.productName" value="${curOrder.product.productName}" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
              <form:hidden path="product.id" value="${curOrder.product.id}"/>
           </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保存 " />&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返回 "  onclick="history.go(-1)"/>
        </div>
   </form:form>
</body>
</html>