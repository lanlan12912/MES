<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="default">
	<title>工单列表</title>
	<script type="text/javascript">
	   function page(n,s){
		   $("#pageNo").val(n);
		   $("#pageSize").val(s);
		   $("#searchForm").submit;
	   }
	</script>
</head>
<body>
   <!--tab  -->
    <ul class="nav nav-tabs">
       <li class="active"><a href="${ctx}/bas/workOrder/">工单列表</a></li>
   </ul>
   <!-- 查询 -->
   <form:form id="searchForm" method="post" action="${ctx}/bas/workOrder" modelAttribute="workOrder" class="breadcrumb form-search">
       <input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
       <input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
       <div class="controls">
           <label>工单号：</label>
              <form:input path="orderCode" maxlength="50" class="input-medium" htmlEscape="false"/>
              <input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
       </div>
   </form:form>
   <sys:message content="${message}"/>
   <!--列表  -->
   <table id="contentTable" class="table table-striped table-bordered table-condensed">
       <thead>
          <th>工单号</th>
          <th>订单号</th>
          <th>工单类型</th>
          <th>工单生产数量</th>
          <th>计量单位</th>
          <th>预计生产时间</th>
          <th>预计结束时间</th>
          <th>实际生产时间</th>
          <th>投入数量</th>
          <th>产出数量</th>
          <th>报废数量</th>
          <th>工单状态</th>
          <th>所属产线</th>
          <th>产品</th>
          <th>操作</th>
       </thead>
       <tbody>
            <c:forEach items="${page.list}" var="workOrder">
            <tr>
               <td>${workOrder.orderCode}</td>
               <td>${workOrder.order.id}</td>
               <td>${workOrder.orderType}</td>
               <td>${workOrder.amount}</td>
               <td>${workOrder.unitName}</td>
               <td>${workOrder.estStartTime}</td>
               <td>${workOrder.estEndTime}</td>
               <td>${workOrder.actStartTime}</td>
               <td>${workOrder.inAmount}</td>
               <td>${workOrder.outAmount}</td>
               <td>${workOrder.scrapAmount}</td>
               <td>${fns:getDictLabel(workOrder.state,'workorder_state','')}</td>
               <td>${workOrder.line.lineName}</td>
               <td>${workOrder.product.productName}</td>
               <td>
                 <a href="${ctx}/bas/workOrder/form?id=${workOrder.id}">修改</a>
                 <a href="${ctx}/bas/workOrder/delete?id=${workOrder.id}" onclick="return confirmx('确认删除该工单信息吗? ',this.href)">删除</a>
                 <a href="${ctx}/bas/workOrder/bom?id=${workOrder.id}">查看BOM</a>
                 <a href="${ctx}/bas/workOrder/flow?id=${workOrder.id}">查看工艺路由</a>
               </td>
            </tr>
            </c:forEach>
          </tbody>
         </table>
   <!--分页 -->
   <div class="pagination">${page}</div>
</body>
</html>