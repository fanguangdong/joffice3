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
<%@ include file="../common/save.js.jsp" %>
<script type="text/javascript">
base_context="${base_context}";

$(function(){
	$("#saveBtn").bind("click", function(event){
		event.preventDefault();
		save("formDetail");
	});
	$("#formTemplate").kendoEditor({
		encoded: false,
		tools: [
                "bold",
                "italic",
                "underline",
                "strikethrough",
                "justifyLeft",
                "justifyCenter",
                "justifyRight",
                "justifyFull",
                "insertUnorderedList",
                "insertOrderedList",
                "indent",
                "outdent",
                "createLink",
                "unlink",
                "insertImage",
                "subscript",
                "superscript",
                "createTable",
                "addRowAbove",
                "addRowBelow",
                "addColumnLeft",
                "addColumnRight",
                "deleteRow",
                "deleteColumn",
                "viewHtml",
                "formatting",
                "fontName",
                "fontSize",
                "foreColor",
                "backColor",
                {
                	name: "formTools",
                    template: (	  "<a title='Insert Form' id='istForm' class='k-tool  k-group-start ' onclick='addFormEle(this)'><span class='k-tool-icon ui-form-icon'></span></a>"
		                   	     +"<a title='Insert Text' id='istText' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-input-icon'></span></a>"
				            	 +"<a title='Insert Textarea' id='istTextarea' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-textarea-icon'></span></a>"
				            	 +"<a title='Insert Checkbox' id='istCheckbox' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-checkbox-icon'></span></a>"
				            	 +"<a title='Insert Select' id='istSelect' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-select-icon'></span></a>"
				            	 +"<a title='Insert Hidden' id='istHidden' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-hidden-icon'></span></a>"
				            	 +"<a title='Insert Radio' id='istTadio' class='k-tool k-group-end ' onclick='addFormEle(this)'><span class='k-tool-icon ui-radio-icon'></span></a>")

                }
                

            ],
	
		select: function(e){
			$("a[id^='ist']").each(function(){
				if($(this).hasClass("k-state-selected"))
				{
					$(this).removeClass("k-state-selected");
				}
			});
			var editor = $("#formTemplate").data("kendoEditor");
			var html = editor.selectedHtml();

		}
		});
	//form tool style
	$("a[id^='ist']")
	.click(function(){
		$("a[id^='ist']").removeClass("k-state-hover");
		$("a[id^='ist']").removeClass("k-state-selected");
		$(this).addClass("k-state-selected");
	})
	.hover(
		function(){
			$(this).addClass("k-state-hover");
		},
		function(){
			$(this).removeClass("k-state-hover");
		}
	)
	;
	
	
	$("#formTabs").kendoTabStrip();
	var tabStrip = $("#formTabs").kendoTabStrip().data("kendoTabStrip");
	tabStrip.activateTab(tabStrip.items()[0]);
	
	 
	dataSource = new kendo.data.DataSource({
        transport: {
            read:  {
                url: base_context + "/form/getFields.oa?formId=${formDetail.formId}",
                dataType: "json"
            },
            
            create: {
            	url: base_context + "/form/saveFiled.oa",
            	dataType: "json",
            	//contentType: "application/json",
            	type: "POST"
            },
            
            update: {
            	url: base_context + "/form/saveFiled.oa",
            	dataType: "json",
            	type: "POST"

            },
            parameterMap: function(data,type)
            {
            	if('read' != type)
           		{
            		return $.param((data.models)[0])+"&formId=${formDetail.formId}";
           		}
            }
        },
        batch: true,
        pageSize: 20,
        schema: {
            model: {
                id: "fieldId",
                fields: {
               	// formId: {  editable: false, nullable: true },
               	 fieldId	: {  editable: false, nullable: true },
               	 fieldName	: {  editable: true, nullable: true },
               	 fieldLabel	: {  editable: true, nullable: true },
               	 fieldType	: {  editable: true, nullable: true },
               	 fieldSize	: {  editable: true, nullable: true },
               	 maxSize	: {  editable: true, nullable: true },
                 isRequired	: {  editable: true, nullable: true }
                }
            }
        }
    });
	
	$("#fieldGrid").kendoGrid({
		 toolbar: [{name:'create'}],
        dataSource: dataSource,
        selectable: 'row',
        sortable: true,
       // rowTemplate: kendo.template($("#fieldRowTemplate").html()),
        pageable: true,
        reorderable: true,
        resizable: true,
        height: "500",
        editable: "popup",
        columns: [
     			 { headerTemplate: '<input type="checkbox" id="check-all" />', width:"40px" },
     			{ field: "fieldId",hidden: true, title: "<spring:message code='field.name'/>" },
                { field: "fieldName", title: "<spring:message code='field.name'/>" },
                { field: "fieldLabel", title: "<spring:message code='field.label'/>" },
                { field: "fieldType", title: "<spring:message code='field.type'/>" },
                { field: "fieldSize", title: "<spring:message code='field.size'/>" },
                { field: "maxSize", title: "<spring:message code='field.maxSize'/>" },
                { field: "isRequired", title: "<spring:message code='field.required'/>" },
                { command: ["edit", "destroy"],width:"200px" }
                ]
    });
	

});    


function afterSave()
{
	top.$("#pubDialog").data("kendoWindow").close();
}
function resetForm()
{
	$("#formDetail")[0].reset();
}

function isnull(a, b) {
    b = b || '';
     return a || b;
}

function addFormEle(ele)
{
	
}
</script>

<script id="formToolsTmpl" type="text/x-kendo-tmpl">
<a title='Insert Form' id='istForm' class='k-tool  k-group-start ' onclick='addFormEle(this)'><span class='k-tool-icon ui-form-icon'></span></a>
<a title='Insert Text' id='istText' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-input-icon'></span></a>
<a title='Insert Textarea' id='istTextarea' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-textarea-icon'></span></a>
<a title='Insert Checkbox' id='istCheckbox' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-checkbox-icon'></span></a>
<a title='Insert Select' id='istSelect' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-select-icon'></span></a>
<a title='Insert Hidden' id='istHidden' class='k-tool ' onclick='addFormEle(this)'><span class='k-tool-icon ui-hidden-icon'></span></a>
<a title='Insert Radio' id='istTadio' class='k-tool k-group-end ' onclick='addFormEle(this)'><span class='k-tool-icon ui-radio-icon'></span></a>
</script>
<style type="text/css">


</style>
</head>
<body class="bodystyle">
<div id="formTabs">
	<ul>
		<li>Form Detail</li>
		<li>Form Fields</li>
	</ul>
	<div>
		<form:form commandName="formDetail" method="post" action="${base_context}/form/save.oa">
		<form:hidden path="formId"/>
		<!-- 
			<div id="baseInfo" class="k-block" >
				<div class="k-header"><spring:message code="baseInfo"/></div>
		 -->
				<div>
					<table cellspacing="0" cellpadding="0" width="100%" border="0">
						<tr valign="top">
							<td width="100px"><spring:message code="form.name"/></td>
							<td ><form:input path="formName" size="80"/></td>
						</tr>
						<tr valign="top">
							<td width="100px"><spring:message code="form.template"/></td>
							<td >
								<form:textarea path="formTemplate" style="width: 99%; height:400px"/>
							</td>
						</tr>
						
						
					</table>
				</div>
				<!-- 
			</div>
				 -->
					
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<tr>
					<td align="center">
						<button id="saveBtn" class="k-button">Save</button>
						<button id="reset" type="reset" class="k-button">Reset</button>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	<div>
		<table id="fieldGrid" cellspacing="0" cellpadding="0" border="0" width="100%">
 		
 	
 	</table>
	</div>
</div>

<div id="fieldDialog">
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tr>
			<td>Field Name</td>
			<td><input id="fieldName" name="fieldName" size="20"></td>
		</tr>
		<tr>
			<td>Field Id</td>
			<td><input id="fieldId" name="fieldId" size="20"></td>
		</tr>
	</table>

</div>


</body>
</html>