<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
base_context="${base_context}";
var _editDdialog;

$(function(){
    
	dataSource = new kendo.data.DataSource({
         transport: {
             read:  {
                 url: base_context + "/form/getForms.oa",
                 dataType: "json"
             }
         },
         batch: true,
         pageSize: 20,
         schema: {
             model: {
                 id: "formId",
                 fields: {
                	 formId: {  editable: false, nullable: true },
                	 formName: {  editable: false, nullable: true }
                 }
             }
         }
     });
	
	
	
	 $("#formGrid").kendoGrid({
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
                 { field: "formName", title: "<spring:message code='form.name'/>" },
                 { width:"150px" }
                 ]
     });
	 
	//add/edit user window	
	top.$("#pubDialog").kendoWindow({
        width: "800px",
        height:"500px",
        modal: true,
        title: "Edit",
        visible: false,
        close: function(e){
        	dataSource.read();
        }
    });
	_editDialog = top.$("#pubDialog").data("kendoWindow");
	
	
	
	$("#check-all").change(function(){
		var _checked = $(this).prop('checked');
		$("[id^='ck']").prop('checked',_checked);
	});
		
 });

function getPubDialog(_defDialog)
{
	var rtnDialog=null;
	var _pwin = window.parent;
	var flag = false;
	var i =0
	while(_pwin)
	{
		i++;
		if(i>10 ) break;
		
		var _parentWin = _pwin;
		
		if(_parentWin.$("#pubDialog").length>0)
		{
			rtnDialog =  _parentWin.$("#pubDialog");
			flag = true;
			break;
		}
		else
		{
			_pwin = _parentWin.parent;
		}
		
	}
	 
	if(flag==false)
	{
		rtnDialog = $("#"+_defDialog);
	}
	return rtnDialog;
}
function addRow()
{
	_editDialog.refresh({
		url : "${base_context}/form/add.oa"
	});
	
	_editDialog.center();
	_editDialog.open();
	_editDialog.setOptions({
		  width: 800,
		  height: 500
		});
}
function editRow(rowId)
{
	
	_editDialog.refresh({
		url : "${base_context}/form/edit.oa",
		data : {formId : rowId}
	});
	
	_editDialog.center();
	_editDialog.open();
	_editDialog.setOptions({
		  width: 800,
		  height: 500
		});

}
function delUser(e)
{
	alert("del"+e);
}

function isnull(a, b) {
    b = b || '';
     return a || b;
}

</script>
<script id="toobarTemplate" type="text/x-kendo-tmpl">
	<button class="k-button" id="addBtn" onclick="addRow()">Add new redcord</button>
</script>
<script id="rowTemplate" type="text/x-kendo-tmpl">
	<tr ondblclick="editRow(#: formId #)" >
		<td> <input type="checkbox" id="ck_#: formId #"/></td>
		<td> #: isnull(formName,"") # </td>
		<td> 
<li class="k-tool-group k-button-group">
			<button class="k-tool k-button k-button-icon " onclick="editRow(#: formId #)" title="<spring:message code='btn.edit'/>">
				<span class="k-sprite ui-icon ui-icon-pencil" ></span>
			</button>
			<button class="k-tool k-button k-button-icon " onclick="delRow(#: formId #)" title="<spring:message code='btn.del'/>">
				<span class="k-sprite ui-icon ui-icon-trash" ></span>
			</button>
</li>
		</td>
	</tr>
</script>
