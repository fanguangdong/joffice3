<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
base_context="${base_context}";

$(function(){
    
	dataSource = new kendo.data.DataSource({
         transport: {
             read:  {
                 url: base_context + "/ref/getRefs.oa",
                 dataType: "json"
             },
	         create: {
	         	url: base_context + "/ref/saveRef.oa",
	         	dataType: "json",
	         	type: "POST"
	         },
	         update: {
	         	url: base_context + "/ref/saveRef.oa",
	         	dataType: "json",
	         	type: "POST"
	
	         },
	         parameterMap: function(data,type)
	         {
	         	console.log(type);
	         	if('read' != type)
	       		{
	         		console.log((data.models)[0]);
	        		return $.param((data.models)[0]);
	       		}
	         }
         },
         batch: true,
         pageSize: 20,
         schema: {
             model: {
                 id: "refId",
                 fields: {
                	 refId			: 		{  editable: false, nullable: true },
                	 refKey			: 		{  editable: true, nullable: true },
                	 refTable		: 		{  editable: true, nullable: true },
                	 textField		: 		{  editable: true, nullable: true },
                	 valueField		: 		{  editable: true, nullable: true }
                 }
             }
         }
     });
	
	
	
	 $("#refGrid").kendoGrid({
		 toolbar: [{name:'create'}],
         dataSource: dataSource,
         selectable: 'row',
         sortable: true,
         pageable: true,
         reorderable: true,
         resizable: true,
         height: "500",
         editable:"popup",
         columns: [
      			 { headerTemplate: '<input type="checkbox" id="check-all" />', width:"40px" },
      			{ field: "refId", hidden:true },
                 { field: "refKey", title: "<spring:message code='ref.key'/>" },
                 { field: "refTable", title: "<spring:message code='ref.table'/>" },
                 { field: "textField", title: "<spring:message code='ref.textField'/>" },
                 { field: "valueField", title: "<spring:message code='ref.valueField'/>" },
                 { command: ["edit", "destroy"] }
                 ]
     });
	 

	
	$("#check-all").change(function(){
		var _checked = $(this).prop('checked');
		$("[id^='ck']").prop('checked',_checked);
	});
		
 });



</script>

