<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
function save(formId)
{
	if(!confirm('<spring:message code="saveConfirm"/>'))
	{
		return;
	}
	if(validate())
	{
		top.showWaitDialog();
		var _form = $("#"+formId);
		$.ajax({
			type 	: 	"POST",
			url		:	 _form[0].action,
			data	:	 _form.serialize(),
			dataType:	 "json"
		})
		.done(function(data){
			afterSave(data);
			top.closeWaitDialog();
			//alert('<spring:message code="saveSuccess"/>');
		})
		.fail(function(){
			top.closeWaitDialog();
			alert('<spring:message code="saveFailed"/>');
		})
		;
	}
}
function validate()
{
	return true;
}
function beforeSave()
{
	
}
function afterSave()
{
	
}
</script>

