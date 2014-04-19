<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
base_context="${base_context}";
var posMgtMenu;
var positionTree;
var selectNode;
$(function(){
	$('#positionPanel').layout({
		//spacing_open : 3
	});

	posMgtMenu = $("#posMgtMenu").menu().hide();
	$(document).bind("contextmenu",function(e)
    {
        return false;
    }); 
	$(document).click(function(){
		posMgtMenu.hide();
	});
	
	var setting = {
			data: {
				key : {
					name: "posName",
					title: "posName"
				},
				simpleData: {
					enable: true,
					idKey: "posId",
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
				url:"${base_context}/position/getTreeData.oa",
				autoParam:["posId","parentId"],
				dataFilter: filter
			},
			callback: {
				onClick: zTreeOnLeftClick,
				onRightClick: zTreeOnRightClick
			}
		};
	
	positionTree = $.fn.zTree.init($("#positionTree"), setting);
	
	$("#toggleBtns").children()
	.first()
	.button({
		text:false,
		icons:{primary:"ui-icon-plus"}
	})
	.click(function(){positionTree.expandAll(true);})
	.next()
	.button({
		text:false,
		icons:{primary:"ui-icon-minus"}
	})
	.click(function(){positionTree.expandAll(false);})
	.parent()
	.buttonset();
 });

function zTreeOnRightClick(e, treeId, treeNode) {
	if (treeNode&&!treeNode.notRight) 
	{
		positionTree.selectNode(treeNode);
		posMgtMenu.show();
		posMgtMenu.css({"position":"absolute","top":e.clientY+"px", "left":e.clientX+"px"});
	}
};
function zTreeOnLeftClick(event, treeId, treeNode)
{
	selectNode = treeNode;
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

function addPos()
{
	var url = "${base_context}/position/add.oa?parentId="+selectNode.posId
	$("#posDetailPanel").load(url);
}
function editPos()
{
	var url = "${base_context}/position/edit.oa?posId="+selectNode.posId
	$("#posDetailPanel").load(url);
}

</script>

