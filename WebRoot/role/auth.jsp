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
<link type="text/css" rel="stylesheet" href="${base_context}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" />
<script src="${base_context}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${base_context}/plugin/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
base_context="${base_context}";
var moduleTree;
var timer = null;
$(function(){
	top.showWaitDialog();
	var setting = {
			data: {
				key : {
					name: "moduleName",
					title: "title",
					resSn: "resSn"
				},
				simpleData: {
					enable: true,
					idKey: "moduleId",
					pIdKey: "parentId",
					rootPId: -1
				}
			},
			check: {
				enable: true,
				chkboxType: { "Y": "p"}

			},
			view: {
				showIcon: false,
				selectedMulti: false
			},
			
			async: {
				enable: true,
				url:"${base_context}/role/getAuth.oa",
				autoParam:["moduleId","parentId"],
				dataFilter: filter
			},
			
			callback: {
				onAsyncSuccess: zTreeOnAsyncSuccess

			}
		};
	
	
		moduleTree = $.fn.zTree.init($("#moduleTree"), setting);
		
	
});    

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		if(!childNodes[i].isParent){
			childNodes[i].isParent = true;
		}
	}
	return childNodes;
};
function zTreeOnAsyncSuccess()
{
	getPermission();
}
function getPermission()
{
	var _url = "${base_context}/role/getPermission.oa?_timeStamp="+(new Date()).getTime();
	var _param = {roleId : ${roleId}};
	$.getJSON(
		_url,
		_param,
		function(data){
			if(data)
			{
				$.each(data, function(key,value){
					var nodes = moduleTree.getNodesByParam("resSn", key, null);
					if(nodes[0])
					{
						moduleTree.checkNode(nodes[0], true, true);
					}
				});
				top.closeWaitDialog();
			}
		}
	);

}

function saveAuth()
{
	top.showWaitDialog();
	var params = new Array();
	var nodes = moduleTree.getCheckedNodes(true);
	var len = nodes.length;
	for(var i=0;i<len;i++)
	{
		var _resSn=nodes[i].resSn;
		params.push(_resSn);
	}

	var _url = "${base_context}/role/saveAuth.oa";
	var _param = {checks:params.join(","),roleId:${roleId}};
	$.post(
		_url,
		_param,
		function(data){
			if(data.status==1)
			{
			}
			top.closeWaitDialog();
		},
		"json"
	);
}
</script>

<style type="text/css">

</style>
</head>
<body class="bodystyle">

<table cellspacing="0" cellpadding="0" border="0" width="100%" height="100%" class="k-block">
	<tr>
		<td align="left" valign="top" >
			<div id="moduleTree"  class="ztree" style=" clear: left;"> </div>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" height="25px" class="k-header">
			<button id="saveBtn" onclick="saveAuth()">Save</button>
		</td>
	</tr>
</table>



</body>
</html>