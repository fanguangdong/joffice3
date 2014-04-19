<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
var moduleTree;
var selectNode;
var modMgtMenu;
$(function(){
	$('#modulePanel').layout();
	//menu
	modMgtMenu = $("#modMgtMenu").menu().hide();
	$(document).bind("contextmenu",function(e)
    {
        return false;
    }); 
	$(document).click(function(){
		modMgtMenu.hide();
	});
	
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
			async: {
				enable: true,
				url:"${base_context}/module/getTreeData.oa",
				autoParam:["moduleId","parentId"],
				dataFilter: filter
			},
			callback: {
				onClick: zTreeOnLeftClick,
				onRightClick: zTreeOnRightClick
			}
		};
	
	moduleTree = $.fn.zTree.init($("#moduleTree"), setting);
	
	$("#toggleBtns").children()
	.first()
	.button({
		text:false,
		icons:{primary:"ui-icon-plus"}
	})
	.click(function(){moduleTree.expandAll(true);})
	.next()
	.button({
		text:false,
		icons:{primary:"ui-icon-minus"}
	})
	.click(function(){moduleTree.expandAll(false);})
	.parent()
	.buttonset();
});

function zTreeOnRightClick(e, treeId, treeNode) {
	if (treeNode&&!treeNode.notRight) 
	{
		moduleTree.selectNode(treeNode);
		modMgtMenu.show();
		modMgtMenu.css({"position":"absolute","top":e.clientY+"px", "left":e.clientX+"px"});
	}
};
function zTreeOnLeftClick(event, treeId, treeNode)
{
	selectNode = treeNode;
	editMod();
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
function refreshTree(node)
{
	if(node)
		moduleTree.reAsyncChildNodes(node, "refresh");
	else
		moduleTree.reAsyncChildNodes(null, "refresh");
}
function addMod()
{
	var url = "${base_context}/module/add.oa?parentId="+selectNode.moduleId;
	$("#modDetailPanel").load(url);
}
function editMod()
{
	if(selectNode.moduleId >0)
	{
		var url = "${base_context}/module/edit.oa?moduleId="+selectNode.moduleId;
		$("#modDetailPanel").load(url);
	}
}
</script>
