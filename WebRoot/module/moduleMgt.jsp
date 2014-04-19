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
<%@ include file="js/moduleMgt.js.jsp" %>
<link type="text/css" rel="stylesheet" href="${base_context}/plugin/layout/layout-default-1.3.0.css" />
<script src="${base_context}/plugin/layout/jquery.layout-1.3.0.min.js"></script>

<link type="text/css" rel="stylesheet" href="${base_context}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" />
<script src="${base_context}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>


<style type="text/css">
	

	
</style>
</head>
<body class="bodystyle">
	<div id="modulePanel" class="paneContainer">
		<div class="pane ui-layout-west">
		
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class='ui-widget ui-widget-header ui-corner-tl ui-corner-tr'>
					<tr>
						<td align="left"><span>资源管理</span></td>
						<td align="right">
							<div id="toggleBtns">
								<a id="expendBtn">展开</a>
								<a id="collapseBtn" >收起</a>
							</div>
						</td>
					</tr>
			</table>
			
			<div id="moduleTree" class="ztree" style=" clear: left;"></div>
		</div>
		<div class="pane ui-layout-center">
			<div id="modDetailPanel" >
			</div>
		</div>
	</div>
	
	<ul id=modMgtMenu style="width:150px">
		<li id="mAddMenu" ><a href="#" onclick="addMod()"><span class="ui-icon ui-icon-plus"></span>添加</a></li>
		<li id="mDelMenu" ><a href="#" onclick="delMod()"><span class="ui-icon ui-icon-trash"></span>删除</a></li>
	</ul>
</body>
</html>