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
<%@ include file="common/include_jui.jsp" %>

<style type="text/css">

</style>
<script src="${base_context}/js/login.js"></script>
<script type="text/javascript">

$(function(){
	
	top.$("#loginWin").dialog({
		autoOpen: true,
		width: 300,
		height: 200,
		pisition: { my: "center", at: "center", of: window },
		modal:false,
		resizable:false,
		title:"<spring:message code="login.userLogin"/>"
	});
	/*
	$("#loginWin").kendoWindow({
		  actions		: [ ],
		  animation		: false,
		  title			: '<spring:message code="login.userLogin"/>',
		  width			: 300,
		  height		: 140,
		  resizable		: false,
		  draggable		: false

		});
	*/
	//var loginWin = $("#loginWin").data("kendoWindow");
	//loginWin.center();
	
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
	})
	.button()
	.next()
	.button()
	;

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
			<a id="submitBtn" ><spring:message code="login.login"/></a>
			<input id="resetBtn" type="reset" class="k-button">
		</td>
	</tr>
</table>

</form>
</div>
</body>
</html>