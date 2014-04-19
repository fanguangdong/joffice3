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
<script src="${base_context}/js/login.js"></script>
<style type="text/css">
.no-close .ui-dialog-titlebar-close 
{
	display: none;
}
.ui-dialog-buttonset { float: left; }
</style>
<script type="text/javascript">
$(function(){
	$("#loginWin").dialog({
		dialogClass			: "no-close",
		title				: '<spring:message code="login.userLogin"/>',
		resizable			: false,
		draggable			: false,
		width				: 300,
		height				: 160
		
	});
	
	$("#submitBtn").button()
					.click(function(event){
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
						 			alert(data.status);
						 			alert(1==data.status);
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
	$("#resetBtn").button();
	$("#msg").hide();
});



</script>
</head>
<body>
<table width="40%" cellspacing="0" cellpadding="0" border="0" align="center">
	<tr>
		<td align="center" id="msg">
		</td>
	</tr>
</table>
<div id="loginWin">
<form method="post" action="">
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td><spring:message code="login.userName"/></td>
		<td><input id="userName" /></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td><spring:message code="login.password"/></td>
		<td><input id="password" type="password" /></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
	<tr>
		<td colspan="2" align="center" valign="middle" >
			<button id="submitBtn"><spring:message code="login.login"/></button>
			<input id="resetBtn" type="reset">
		</td>
	</tr>
</table>

</form>
</div>
</body>
</html>