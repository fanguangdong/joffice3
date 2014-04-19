<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="jof" uri="/WEB-INF/tlds/jof.tld" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Content</title>
<%@ include file="common/include_jui.jsp" %>
<script type="text/javascript" src="${base_context}/js/content.js"></script>

<link type="text/css" rel="stylesheet" href="${base_context}/plugin/layout/layout-default-1.3.0.css" />
<script src="${base_context}/plugin/layout/jquery.layout-1.3.0.min.js"></script>

<link type="text/css" rel="stylesheet" href="${base_context}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" />
<script src="${base_context}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<style type="text/css">


.toggleTreeBtn
{
	width:20px;
	height:20px;
	
}
</style>
<script type="text/javascript">

base_context="${base_context}";
var navTree;
$(function(){
	$('#contentPanel').layout({
		spacing_open:2,
		spacing_closed:10,
		 togglerContent_closed:"<div>></div>"
	});
	//pageInit();
	var setting = {
			data: {
				key : {
					name: "moduleName",
					title: "title"
				},
				simpleData: {
					enable: true,
					idKey: "moduleId",
					pIdKey: "parentId",
					rootPId: -1
				}
			},
			view: {
				showIcon: false,
				selectedMulti: false
			},
			/*
			async: {
				enable: true,
				url:"${base_context}/module/getTreeData.oa?moduleId=${moduleId}",
				autoParam:["moduleId","parentId"],
				dataFilter: filter
			},
			*/
			callback: {
				onClick: zTreeOnLeftClick
			}
		};
	
	$.getJSON("${base_context}/module/getNavTreeData.oa?moduleId=${moduleId}",
			function(data){
				navTree = $.fn.zTree.init($("#navTree"), setting,data);
			}
	);
 	
 	
 	$("#expendBtn").button({
		text:false,
		icons:{primary: "ui-icon-plus"}
	})
	.addClass("toggleTreeBtn")
	.click(function(){expandTree();});
	
	$("#collapseBtn").button({
		text:false,
		icons:{primary: "ui-icon-minus"}
	})
	.addClass("toggleTreeBtn")
	.click(function(){collapseTree();});
	
	
});

function zTreeOnLeftClick(event, treeId, treeNode)
{
	var _url = treeNode.defaultUrl;
	if(_url.length>0)
	{
		$("#rightFrame").attr('src',_url);
	}
};

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		if(!childNodes[i].isParent){
			childNodes[i].isParent = true;
		}
	}
	return childNodes;
};

function expandTree()
{
	navTree.expandAll(true);

}
function collapseTree()
{
	navTree.expandAll(false);
}
</script>
</head>
<body class="bodystyle">
<div id="contentPanel"  style="height:99%">

 	<div id="lPanel" class="pane ui-layout-west" style="height:99%">	
 		<div id="navHeader" class="ui-widget ui-widget-header ui-corner-tl ui-corner-tr" style="height:25px">${module.moduleName }</div>
 		<div id="navTree" class="ztree ui-widget ui-widget-content " style=" clear: left;height:85%"></div>
 		<div id="navBottom" class="ui-widget ui-widget-header ui-corner-bl ui-corner-br" style="height:25px">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" height="20px">
				<tr>
					<td align="left"><a id="expendBtn">展开</a></td>
					<td align="right"><a id="collapseBtn" >收起</a></td>
				</tr>
			</table>
		</div>
 	</div>
	<div id="rPanel" class="pane ui-layout-center">
		<iframe id="rightFrame" name="rightFrame" src="" width="100%" height="99%" marginwidth="0" marginheight="0" frameborder="0" scrolling="yes" ></iframe>
	</div>

</div>
</body>
</html>