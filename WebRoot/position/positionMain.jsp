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
<%@ include file="js/positionMain.js.jsp" %>
<link type="text/css" rel="stylesheet" href="${base_context}/plugin/layout/layout-default-1.3.0.css" />
<script src="${base_context}/plugin/layout/jquery.layout-1.3.0.min.js"></script>

<link type="text/css" rel="stylesheet" href="${base_context}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" />
<script src="${base_context}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>


<style type="text/css">
	

	#positionPanel {
		position: fixed;
		left: 0;
		background:	#999;
		height:		99.9%;
		width:		99.9%;
		margin:		0 auto;
		max-width:	1900px;
		min-width:	700px;
		_width:		700px; /* min-width for IE6 */
	}
	.pane 
	{
		display:	none; /* will appear when layout inits */
	}
	.ui-layout-pane
	{
		 background: none repeat scroll 0 0 #FFFFFF;
   		 border: 1px solid #BBBBBB;
   		 overflow: auto;
   		 padding: 1px;
	}
</style>
</head>
<body class="bodystyle">
	<div id="positionPanel">
		
		
		<div class="pane ui-layout-west">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class='ui-widget ui-widget-header ui-corner-tl ui-corner-tr'>
					<tr>
						<td align="left"><span>岗位管理</span></td>
						<td align="right">
							<div id="toggleBtns">
								<a id="expendBtn">展开</a>
								<a id="collapseBtn" >收起</a>
							</div>
						</td>
					</tr>
			</table>
			<div id="positionTree" class="ztree" style=" clear: left;"></div>
		</div>
		<div class="pane ui-layout-center">
			<div id="posDetailPanel" ></div>
		</div>
	</div>
	
	<ul id="posMgtMenu" style="width:100px">
		<li><a id="addMenu" href="#" onclick="addPos()"><span class="ui-icon ui-icon-plus"></span>添加</a></li>
		<li><a id="editMenu" href="#" onclick="editPos()"><span class="ui-icon ui-icon-disk"></span>编辑</a></li>
		<li><a id="delMenu"><span class="ui-icon ui-icon-trash"></span>删除</a></li>
		<li><a id="userMenu"><span class="ui-icon ui-icon-person"></span>人员设置</a></li>
		<li><a id="refMenu"><span class="ui-icon ui-icon-refresh"></span>刷新</a></li>
	</ul>

</body>
</html>