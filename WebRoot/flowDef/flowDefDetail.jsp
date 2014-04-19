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
<%@ include file="../common/save.js.jsp" %>
<%@ include file="js/flowDefDetail.js.jsp" %>
<title>aaa</title>


<style type="text/css">


</style>
</head>
<body class="bodystyle">
<div id="detailForm">
<form:form commandName="flowDef" action="flowDef/save.oa">
	<form:hidden path="flowDefId"/>
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
				<td>标题</td>
				<td><form:input path="subject"/></td>
			</tr>
			<tr>
				<td>Key</td>
				<td ><form:input path="defKey"/></td>
			</tr>
			<tr>
				<td>分类</td>
				<td ><form:input path="defTypeId"/></td>
			</tr>
			<tr>
				<td>版本</td>
				<td><form:input path="versionNo"/></td>
			</tr>
			<!-- 
			 -->
			<tr>
				<td colspan="2" align="center" valign="middle" id="formBtns">
					<a id="saveBtn">保存</a>
					<a id="resetBtn">重置</a>
				</td>
			</tr>
	
	</table>

</form:form>
</div>
</body>
</html>