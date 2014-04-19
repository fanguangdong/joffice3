<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<spring:message code="login.title"/>
</title>
<%@ include file="common/include.jsp" %>
<%@ include file="common/from.js.jsp" %>

<style type="text/css">

</style>
<script src="${base_context}/js/login.js"></script>
<script type="text/javascript">

$(function(){
	$("#loginWin").kendoWindow({
		  actions		: [ ],
		  animation		: false,
		  title			: '<spring:message code="login.userLogin"/>',
		  width			: 300,
		  height		: 160,
		  resizable		: false,
		  draggable		: false

		});
	var loginWin = $("#loginWin").data("kendoWindow");
	loginWin.center();
	
	//$("#msgTable").hide();
	
	$("#submitBtn").click(function(event){
		 var uName = $("#userName").val();
		 var pwd = $("#password").val();
		 if(isNull(uName) || isNull(pwd) )
		 {
		  	showErrorMsg("<spring:message code='login.msg.accountInvalid'/>");
		 }
		 else
		 {
			 var url="${base_context}/login.oa";
			 var params = {userName:uName, password:pwd};
		 	$.post(
		 		url,	
		 		params,
		 		function(data){
		 			if(data && 1==data.status)
	 				{
		 				window.location.href="${base_context}/index.oa"
	 				}
		 		},
		 		"json"
		 	);
		 }
		  event.preventDefault();
	});

});


</script>
</head>
<body class="bodystyle">
<table id="msgTable" width="40%" cellspacing="0" cellpadding="0" border="0" align="center">
	<tr>
		<td align="center" valign="top" id="msg" height="500">
		</td>
	</tr>
</table>
<div id="loginWin">
<form method="post" action="#">
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	
	<tr>
		<td>申请人</td>
		<td>
			<input id="userName" class="dropDownList" refKey="users"/>
		</td>
		<td>请假日期</td>
		<td>
			From<input id="leave_from" style="width:150px;" class="datetimepicker"/> 
			To<input id="leave_through" style="width:150px;" class="datetimepicker"/> 
		</td>
	</tr>
	<tr>
		<td>请假类型</td>
		<td colspan="3">
			 <select id="leaveType" name="leaveType" class="dropDownList">
			 	<option value="1">事假</option>
			 	<option value="2">病假</option>
			 	<option value="3">婚假</option>
			 	<option value="4">丧假</option>
			 	<option value="5">产假</option>
			 	<option value="6">年假</option>
			 	<option value="8">其他</option>
			 </select>
		</td>
	</tr>
	<tr>
		<td>请假说明</td>
		<td colspan="3">
			<textarea id="summary" class="textbox" rows="6" cols="80" style="width:350px;">Textarea</textarea>
		</td>
	</tr>
	<tr>
		<td>Leader</td>
		<td colspan="3">
			 <input id="numeric" type="number" value="17" min="0" max="100" step="1" class="numeric" />
		</td>
	</tr>
	<tr>
		<td>manager</td>
		<td colspan="3">
			<input id="datepicker" value="10/10/2011" style="width:150px;" class="datepicker"/>
		</td>
	</tr>
</table>

</form>
</div>
</body>
</html>