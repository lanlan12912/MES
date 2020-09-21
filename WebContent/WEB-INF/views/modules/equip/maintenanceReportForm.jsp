<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default">
<script  src="/action/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>

<title>维修报告</title>

<script type="text/javascript">
$(document).ready(function(){
  /* 找到名叫avatar的input框 */
	$('#avatar').change(function () {
	   /* $(this)[0] 把jQuery对象变成dom对象，files[0]：取最近一次上传的文件 */
	   var choose_file =$(this)[0].files[0];
	    /* 实例化一个文件读取器 */
	    var read=new FileReader();
	   /* 把文件传入文件读取器readAsDataURL方法里 */
	   read.readAsDataURL(choose_file);
	    /* 由于readAsDataURL读取慢，而找类名为avatar的元素赋值快，所以需要添加onload事件，所有事件加载完之后再执行 */
	    read.onload=function () {
	       $('.avatar').attr('src',read.result);
	       var value="http://localhost:8080/action/static/imgs/"+$('#mid').val()+".jpg";
	       $('#faultImgs').val(value);
	    }
	
	});
}); 
</script>
<style>
.avatar{
	width:250px;
	height:150px;
}
</style>
</head>

<body>
	 <!-- 1.tab -->
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/equip/repair">${not empty equipRepair.id?'查看':'添加' }维修报告</a></li>
    </ul> 
    <!-- 2.表单 --> 
 	<form:form id="inputForm" modelAttribute="equipRepair" action="${ctx}/equip/repair/save" method="post" class="form-horizontal" enctype="multipart/form-data"> 
		<form:hidden path="id"/>
		<form:hidden path="mid"/>
		 <div class="control-group"> 
             <label class="control-label">故障类型:</label> 
             <div class="controls"> 
                 <form:input path="faultType" htmlEscape="false" maxlength="50" class="required" readonly="${not empty equipRepair.id?true:false }"/>
                 <span class="help-inline"><font color="red">*</font></span> 
             </div> 
         </div> 
		<div class="control-group"> 
             <label class="control-label">故障原因:</label> 
             <div class="controls"> 
                 <form:input path="faultReason" htmlEscape="false" maxlength="50" class="required" readonly="${not empty equipRepair.id?true:false }"/>
                 <span class="help-inline"><font color="red">*</font></span> 
             </div> 
         </div>
          <div class="control-group"> 
             <label class="control-label">故障描述:</label> 
             <div class="controls"> 
                 <form:textarea path="faultDesc" htmlEscape="false" maxlength="200" class="number" rows="5" readonly="${not empty equipRepair.id?true:false }"/>
                 <span class="help-inline"><font color="red">*</font></span> 
             </div> 
         </div>
         <div class="control-group"> 
             <label class="control-label">故障设备图片:</label> 
             <div class="controls"> 
             	 <form:hidden path="faultImgs"/>
             	 <label>
             	 	<img src="${not empty equipRepair.id?equipRepair.faultImgs:'' }" class="avatar" alt="">
                 	<input type="${not empty equipRepair.id?'hidden':'file' }" id="avatar" name="img">
                 </label>
             </div> 
         </div>  
         <div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type=${not empty equipRepair.id?'hidden':'submit' } value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>  
	</form:form>
</body>
</html>