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
<%@ include file="js/positionDetail.js.jsp" %>




<style type="text/css">
	

</style>
</head>
<body class="bodystyle">
	<div  class="ui-widget ui-widget-header">
		<div id="toolbar">
			<a href="#" id="saveBtn">保存</a>
			<a href="#" id="resetBtn">重置</a>
		</div>
	</div>
	<div id="detailform" class="ui-widget ui-widget-content">
		<form:form commandName="posDetail" method="post" action="${base_context}/position/save.oa">
		<form:hidden path="posId"/>
		<form:hidden path="parentId"/>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td width="200px">岗位名称</td>
					<td ><form:input path="posName" size="30"/></td>
				</tr>
				<tr>
					<td width="200px">岗位描述</td>
					<td ><form:textarea path="posDesc" rows="5" cols="80"></form:textarea></td>
				</tr>
				
			</table>
		
		</form:form>
	</div>

</body>
</html>