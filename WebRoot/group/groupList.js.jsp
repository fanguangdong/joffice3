<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
base_context="${base_context}";
var win;
$(function(){
    
	dataSource = new kendo.data.DataSource({
         transport: {
             read:  {
                 url: base_context + "/group/list.oa",
                 dataType: "json"
             }
         },
         batch: true,
         pageSize: 20,
         schema: {
             model: {
                 id: "groupId",
                 fields: {
                	 userId: { name: "groupId", editable: false, nullable: true },
                	 userName: { name:"groupName",validation: { required: true } }
                 }
             }
         }
     });
	
	 $("#groupGrid").kendoGrid({
		 toolbar: [{ template: kendo.template($("#toobarTemplate").html()) }],
         dataSource: dataSource,
         selectable: 'row',
         sortable: true,
         rowTemplate: kendo.template($("#rowTemplate").html()),
         pageable: true,
         reorderable: true,
         resizable: true,
         height: "500",
         columns: [
			 { headerTemplate: '<input type="checkbox" id="check-all" />', width:"40px" },
             { field: "groupName", title: "<spring:message code='group.groupName'/>" },
             { width:"200px" }
             ]
     });
	 
	//add/edit user window
	win = $("#window");
	addBtn = $("#addBtn")
			.bind("click", function(){
				win.data("kendoWindow").open();
				win.data("kendoWindow").toFront();
				win.data("kendoWindow").center();
				win.data("kendoWindow").refresh("${base_context}/group/add.oa");
			});
	
	win.kendoWindow({
        width: "600px",
        height:"500px",
        modal: true,
        title: "Add User",
        visible: false
    });
	
	$("#check-all").change(function(){
		var _checked = $(this).prop('checked');
		$("[id^='ck']").prop('checked',_checked);
	});
		
 });
function closeWin()
{
	$("#window").data("kendoWindow").close()
}

function editRow(rowId)
{
	win.data("kendoWindow").open();
	win.data("kendoWindow").toFront();
	win.data("kendoWindow").center();
	win.data("kendoWindow").refresh("${base_context}/group/add.oa?groupId="+rowId);
}
function delRow(e)
{
	alert("del"+e);
}

function isnull(a, b) {
    b = b || '';
     return a || b;
}

</script>
<script id="toobarTemplate" type="text/x-kendo-tmpl">
	<button class="k-button" id="addBtn" >Add new redcord</button>
</script>
<script id="rowTemplate" type="text/x-kendo-tmpl">
	<tr ondblclick="editRow(#: groupId #)" >
		<td> <input type="checkbox" id="ck_#: groupId #"/></td>
		<td> #: isnull(groupName,"") # </td>
		<td> 
			<button class="k-button" name="editBtn" onclick="editRow(#: groupId #)" ><spring:message code="btn.edit"/></button>
			<button class="k-button" name="delBtn" onclick="delRow(#: groupId #)" ><spring:message code="btn.del"/></button>
		</td>
	</tr>
</script>
