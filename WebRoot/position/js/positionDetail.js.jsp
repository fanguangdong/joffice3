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
					save("posDetail");
				})
				.next()
				.button({
					icons : {primary: "ui-icon-cancel"}
				})
				.click(function(){
					$("#posDetail")[0].reset();
				});
});

function afterSave()
{
	var node = null;
	var sNodes = positionTree.getSelectedNodes();
	if (sNodes.length > 0) {
		node = sNodes[0].getParentNode();
	}
	positionTree.reAsyncChildNodes(node, "refresh");
}

</script>

