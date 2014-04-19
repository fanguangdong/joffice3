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
<%@ include file="js/moduleDetail.js.jsp" %>




<style type="text/css">
	

</style>
</head>
<body class="bodystyle">
	<div  class="ui-widget ui-widget-header">
		<div id="toolbar">
			<a href="#" id="saveBtn">保存</a>
			<a href="#" id="resetBtn">重置</a>
			<a href="#" id="delBtn">删除</a>
		</div>
	</div>
	<div id="detailform" class="ui-widget ui-widget-content">
		<form:form commandName="moduleDetail" method="post" action="${base_context}/module/save.oa">
		<form:hidden path="moduleId"/>
		<form:hidden path="parentId"/>
		<table cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td><spring:message code="module.moduleMgt.detail.moduleName"/></td>
				<td><form:input path="moduleName" size="30"/></td>
			</tr>
			<tr>
				<td><spring:message code="module.moduleMgt.detail.moduleTitle"/></td>
				<td><form:input path="title" size="30"/></td>
			</tr>
			<tr>
				<td><spring:message code="module.moduleMgt.detail.moduleIcon"/></td>
				<td><form:input path="icon" size="30"/></td>
			</tr>
			<tr>
				<td><spring:message code="module.moduleMgt.detail.moduleUrl"/></td>
				<td><form:input path="defaultUrl" size="30"/></td>
			</tr>
			<tr>
				<td><spring:message code="module.moduleMgt.detail.resSn"/></td>
				<td><form:input path="resSn" size="30"/></td>
			</tr>
			<tr>
				<td>在菜单中显示</td>
				<td>
					<form:select path="showInMenu">
						<form:option value="1">是</form:option>
						<form:option value="0">否</form:option>
					</form:select>
				</td>
			</tr>
			
		</table>
		
		</form:form>
	</div>

</body>
</html>