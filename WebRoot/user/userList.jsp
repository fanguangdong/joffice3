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
<%@ include file="../common/include_jui.jsp" %>
<%@ include file="../common/include_grid.jsp" %>
<%@ include file="js/userList.js.jsp" %>
<style type="text/css">


</style>
</head>
<body class="bodystyle">
<div id="window"></div>	
	<div id="userMgtBtn" class="ui-widget-header">
		<a href="#" id="addBtn" onclick="addRow()" icon="ui-icon-plus">添加</a>
		<a href="#" id="editBtn" onclick="editRow()" icon="ui-icon-pencil">编辑</a>
		<a href="#" id="delBtn" onclick="delRow()" icon="ui-icon-trash">删除</a>
		<a href="#" id="assignBtn" onclick="showAssign()" icon="ui-icon-person">角色</a>
	</div>
 	<table id="userGrid" cellspacing="0" cellpadding="0" border="0" width="100%">
 	</table>
 	<div id="pager"></div>  

	<ul id="userMgtMenu" style="width:80px">
	 	<li>
	 		<a href="#" id="editMenu" onclick="editRow()">
	 			<span class="ui-icon ui-icon-pencil">
	 			</span><span>编辑</span>
	 		</a>
	 	</li>
		<li>
	 		<a href="#" id="delMenu" onclick="delRow()">
	 			<span class="ui-icon ui-icon-trash">
	 			</span><span>删除</span>
	 		</a>
	 	</li>
	 	<li>
	 		<a href="#" id="assignMenu" onclick="showAssign()">
	 			<span class="ui-icon ui-icon-person">
	 			</span><span>角色</span>
	 		</a>
	 	</li>
	 	
	 </ul>
</body>
</html>