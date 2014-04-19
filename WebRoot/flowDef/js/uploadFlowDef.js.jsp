<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
base_context="${base_context}";
var win;
$(function(){
	/**/
	$("#saveBtn").button({
		icons:{
			primary : "ui-icon-disk"
		}
	})
	.click(function(){
		//save("flowDef");
		$( "#flowDef" ).submit();
	})
	.next()
	.button({
		icons : {
			primary : "ui-icon-cancel"
		}
	})
	.click(function(){
		$("#flowDef")[0].reset();
	})
	.parent()
	.buttonset();
	
 });

function afterSave()
{
	 top.$("#pubDialog").dialog( "close" );
}

</script>

