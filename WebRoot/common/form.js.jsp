<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
function applyFormStype()
{
	//class is datepicker
	$(".datepicker").kendoDatePicker();
	
	$(".textbox").addClass("k-textbox");;
	
	//class is datetimepicker
	$(".datetimepicker").kendoDateTimePicker({
        value:new Date()
    });
	//class is numeric
	$(".numeric").kendoNumericTextBox();
	
	//class is select 
	$(".dropDownList").each(function(){
		var refKey = $(this).attr("refKey")
		if(refKey ==null || refKey == "")
		{
			$(this).kendoDropDownList();
		}
		else
		{
		
			$(this).kendoDropDownList({
		        dataTextField: "description",
		        dataValueField: "codeKey",
		        dataSource: {
		            transport: {
		                read: {
		                    dataType: "json",
		                    url: "${base_context}/ref/getFefList.oa?refKey="+refKey,
		                }
		            }
		        }
		    });
		}
	})




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

