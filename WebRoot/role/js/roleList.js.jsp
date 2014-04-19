<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
base_context="${base_context}";
var roleMgtMenu;
var selectId;
$(function(){
	
	roleMgtMenu = $("#roleMgtMenu").menu();
	roleMgtMenu.hide();
	$(document).bind("contextmenu",function(e)
    {
        return false;
    }); 
	$(document).click(function(){
		roleMgtMenu.hide();
	});
	
	
	
	$("#roleMgtBtn").buttonset()
    .children()
    .each(function(){
    	$(this).button({
    		icons:{primary: $(this).attr('icon')}
    	});
    });
    
	$("#roleGrid").jqGrid({
		url : "${base_context}/role/list.oa",  
        contentType : 'application/json',  
        mtype : "get",  
        datatype : 'json',  
        prmNames : {search : "search"},  
        jsonReader : {id : "0", repeatitems : false, userdata : "userdata"},
		height : $(window).height()-100,  
        colNames : ["ID","角色名称"],  
        colModel : [{name:"roleId", index:"roleId",hidden:true},  
                    {name:"roleName", index:"roleName"}
                    ],  
        pager : "#pager",  
        //autoWidth : true,  
        width:$(window).width(),
        rowNum : 10,  
        rowList:[10,20,30],
        rownumbers : true,  
        viewrecords: true,  
        gridview: true,
        onSelectRow: function(id){
        	selectRow(id); 
        },
        onRightClickRow : function(id,irow, icol, event){
        	selectRow(id); 
        	roleMgtMenu.show();
        //	roleMgtMenu.css({"position":"absolute","top":event.clientY+"px", "left":event.clientX+"px"});
        	roleMgtMenu.position({my:"left top", at:"left bottom", of:event});
        },
        ondblClickRow : function(id,irow, icol, event){
        	
        },
        
        gridComplete: function(){
        	var ids = $("#roleGrid").jqGrid('getDataIDs');
        	for(var i=0;i < ids.length;i++)
        	{
        		$("#roleGrid").setRowData( ids[i],false,{height: 30+i*2});
        	}
        	
        }
       // caption : "User List",
       // editurl : "${pageContext.request.contextPath}/user/saveUser.do"
	});
	$("#roleGrid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});	
	
	top.$("#pubDialog").dialog({
		autoOpen: false,
		pisition: { my: "center", at: "center", of: window },
		modal:true,
		resizable:true,
		title:"角色"
	});
	
	
 });

function selectRow(id)
{
	var data = $("#roleGrid").jqGrid('getRowData',id);
	selectId = data.roleId;
}

function editRow()
{
	if(selectId>0)
	{
		
		top.$("#pubDialog")
		.dialog("open")
		.dialog( "option", "width", 300 )
		.dialog( "option", "height", 150 )
		.load("${base_context}/role/edit.oa?roleId="+selectId);
	}
}
function addRow()
{
	top.$("#pubDialog")
	.dialog("open")
	.dialog( "option", "width", 300 )
	.dialog( "option", "height", 150 )
	.load("${base_context}/role/add.oa");
}

function showAssign()
{	
	top.$("#pubDialog")
	.dialog("open")
	.dialog( "option", "width", 500 )
	.dialog( "option", "height", 450 )
	.load("${base_context}/role/showAssignedUsers.oa?roleId="+selectId);
}

function closeAuth()
{
	
}
function showAuth()
{
	top.$("#pubDialog")
	.dialog("open")
	.dialog( "option", "width", 500 )
	.dialog( "option", "height", 450 )
	.load("${base_context}/role/showAuth.oa?roleId="+selectId);
}


</script>

