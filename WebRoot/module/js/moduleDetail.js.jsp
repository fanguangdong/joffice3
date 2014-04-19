<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
base_context="${base_context}";

$(function(){
	$("#toolbar").buttonset()
				.children()
				.first()
				.button({
					icons : {primary: "ui-icon-disk"}
				})
				.click(function(){
					save("moduleDetail");
				})
				.next()
				.button({
					icons : {primary: "ui-icon-cancel"}
				})
				.click(function(){
					$("#moduleDetail")[0].reset();
				})
				.next()
				.button({
					icons : {primary: "ui-icon-trash"}
				});
});

function afterSave(data)
{
	if(data && data.moduleDetail)
	{
		$("#moduleId").attr("value",data.moduleDetail.moduleId);
	}
	var node = null;
	var sNodes = moduleTree.getSelectedNodes();
	if (sNodes.length > 0) {
		node = sNodes[0].getParentNode();
	}
	refreshTree(node);
}

</script>

