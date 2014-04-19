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
<title><spring:message code="module.moduleMgt.title"/></title>

<%@ include file="../common/save.js.jsp" %>
<script type="text/javascript">
base_context="${base_context}";

$(function(){
	$("#bottomBtns")
	.buttonset()
	.children()
	.each(function(){
		$(this).button()
	});
	
});    

function saveRole()
{
	save("roleDetail");
}
function afterSave()
{
	
}
function resetForm()
{
	$("#roleDetail")[0].reset();
}


</script>
<style type="text/css">
.leftrightBtn
{
	width:30px;
}

</style>
</head>
<body class="bodystyle">

<form:form commandName="roleDetail" method="post" action="${base_context}/role/save.oa">
<form:hidden path="roleId"/>
	<div class="ui-widget">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr>
				<td width="200px"><spring:message code="role.roleName"/></td>
				<td ><form:input path="roleName" size="30"/></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<div id="bottomBtns">
						<a id="saveBtn" href="#" onclick="saveRole()">Save</a>
						<a id="reset" href="#" onclick="resetForm()">Reset</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
		
	
				


</form:form>

</body>
</html>