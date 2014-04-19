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
<%@ include file="../common/include.jsp" %>
<script type="text/javascript" src="../js/selectLeftRight.js"></script>
<script type="text/javascript">
base_context="${base_context}";

$(function(){
	//$("#userDetail input").addClass("k-input ui-corner-all");
	//$("button").kendoButton();
	//$("#usrBtnSet").children().kendoButton();
	$("#groupTabs").kendoTabStrip();
	var tabStrip = $("#groupTabs").kendoTabStrip().data("kendoTabStrip");
	tabStrip.activateTab(tabStrip.items()[0]);
	$("#usrBtnSet > button").addClass("k-button leftrightBtn");
	$("#rolBtnSet > button").addClass("k-button leftrightBtn");
	
	initLeftRight('availableUsers','selectedUsers','usrMoveRightBtn','usrMoveLeftBtn','usrMoveAllRightBtn','usrMoveAllLeftBtn');
	initLeftRight('availableRoles','selectedRoles','rolMoveRightBtn','rolMoveLeftBtn','rolMoveAllRightBtn','rolMoveAllLeftBtn'); 
	$(".k-datepicker").kendoDatePicker({
	    format: "MM/dd/yyyy"
	});
	
	$("#saveBtn").bind("click", function(event){
		event.preventDefault();
		saveGroup();
	});
	
});    

function saveGroup()
{
	var selectedUsers="";
	var selectedRoles="";
	$("#selectedUsers option").each(function(){
		var uId = $(this).prop('value');
		selectedUsers += uId+",";
	});
	$("#userIds").val(selectedUsers);
	
	$("#selectedRoles option").each(function(){
		var rId = $(this).prop('value');
		selectedRoles += rId+",";
	});
	$("#roleIds").val(selectedRoles);
	
	
	$("#groupDetail").submit();
}
function resetForm()
{
	$("#groupDetail")[0].reset();
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

<form:form commandName="groupDetail" method="post" action="${base_context}/group/save.oa">
<form:hidden path="userIds"/>
<form:hidden path="roleIds"/>
<form:hidden path="groupId"/>
	<div id="baseInfo" class="k-block">
		<div class="k-header"><spring:message code="baseInfo"/></div>
		<div>
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td width="200px"><spring:message code="group.groupName"/></td>
					<td ><form:input path="groupName" size="30"/></td>
				</tr>
			</table>
		</div>
	</div>
		
		<div id="groupTabs" >
			<ul>
				<li><spring:message code="group.users"/></li>
				<li><spring:message code="group.roles"/></a></li>
			</ul>
			<!-- TAB 1 START -->
			<div id="tabs-1">
				<table cellspacing="0" cellpadding="0" width="100%" border="0">
					<tr>
						<td width="45%">
							<fieldset>
								<legend>
									<spring:message code="selectedUser"/>
								</legend>
								<select multiple="multiple" id="selectedUsers" name="selectedUsers" style="width: 98%; height: 300px;">
									<c:forEach var="suser" items="${selectedUsers }">
										<option value="${suser.userId}">${suser.userName }</option>
									</c:forEach>
								</select>
							</fieldset>
						</td>
						<td align="center">
							<div id="usrBtnSet" >
								<button id="usrMoveRightBtn" >></button><br>
								<button id="usrMoveAllRightBtn" >>></button><br>
								<button id="usrMoveLeftBtn" ><</button><br>
								<button id="usrMoveAllLeftBtn" ><<</button>
							</div>
						</td>
						<td width="45%">
							<fieldset>
								<legend>
									<spring:message code="availableUser"/>
								</legend>
								<select multiple="multiple" id="availableUsers" style="width: 98%; height: 300px;">
									<c:forEach var="auser" items="${availableUsers }">
										<option value="${auser.userId }">${auser.userName }</option>
									</c:forEach>
								</select>
							</fieldset>
						</td>
					</tr>
				</table>
			
			</div>
			<!-- TAB 1 END -->
						
			<!-- TAB 2 START -->
			<div id="tabs-2">
				<table cellspacing="0" cellpadding="0" width="100%" border="0">
					<tr>
						<td width="45%">
							<fieldset>
								<legend>
									<spring:message code="selectedRole"/>
								</legend>
								<select multiple="multiple" id="selectedRoles" name="selectedRoles" style="width: 98%; height: 300px;">
									<c:forEach var="srole" items="${selectedRoles }">
										<option value="${srole.roleId }">${srole.roleName }</option>
									</c:forEach>
								</select>
							</fieldset>
						</td>
						<td align="center">
							<div id="rolBtnSet" >
								<button id="rolMoveRightBtn" >></button><br>
								<button id="rolMoveAllRightBtn" >>></button><br>
								<button id="rolMoveLeftBtn" ><</button><br>
								<button id="rolMoveAllLeftBtn" ><<</button>
							</div>
						</td>
						<td width="45%">
							<fieldset>
								<legend>
									<spring:message code="availableRole" />
								</legend>
								<select multiple="multiple" id="availableRoles" name="availableRoles" style="width: 98%; height: 300px;">
									<c:forEach var="arole" items="${availableRoles }">
										<option value="${arole.roleId }">${arole.roleName }</option>
									</c:forEach>
								</select>
							</fieldset>
						</td>
					</tr>
				</table>
			
			</div>
			<!-- TAB 2 END -->
		</div>
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tr>
			<td align="center">
				<button id="saveBtn" class="k-button">Save</button>
				<button id="resetBtn" type="reset" class="k-button">Reset</button>
			</td>
		</tr>
	</table>
				


</form:form>

</body>
</html>