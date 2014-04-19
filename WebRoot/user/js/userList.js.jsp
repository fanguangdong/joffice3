<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
base_context="${base_context}";
var userMgtMenu;
var selectId;
$(function(){
    
	userMgtMenu = $("#userMgtMenu").menu();
	userMgtMenu.hide();
	$(document).bind("contextmenu",function(e)
    {
        return false;
    }); 
	$(document).click(function(){
		userMgtMenu.hide();
	});
	
	
	
	$("#userMgtBtn").buttonset()
    .children()
    .each(function(){
    	$(this).button({
    		icons:{primary: $(this).attr('icon')}
    	});
    });
    
	$("#userGrid").jqGrid({
		url : "${base_context}/user/list.oa",  
        contentType : 'application/json',  
        mtype : "get",  
        datatype : 'json',  
        prmNames : {search : "search"},  
        jsonReader : {id : "0", repeatitems : false, userdata : "userdata"},
		height : $(window).height()-100,  
        colNames : ["ID","用户名","姓名","邮箱","地址"],  
        colModel : [{name:"userId", index:"userId",hidden:true},  
                    {name:"userName", index:"userName"},
                    {name:"fullName", index:"fullName"},
                    {name:"email", index:"email"},
                    {name:"address", index:"address"}
                    ],  
        pager : "#pager",  
        //autoWidth : true,  
        width:$(window).width(),
        rowNum : 20,  
        rowList:[10,20,30],
        rownumbers : true,  
        viewrecords: true,  
        gridview: true,
        onSelectRow: function(id){
        	selectRow(id); 
        },
        onRightClickRow : function(id,irow, icol, event){
        	selectRow(id); 
        	userMgtMenu.show();
        //	userMgtMenu.css({"position":"absolute","top":event.clientY+"px", "left":event.clientX+"px"});
        	userMgtMenu.position({my:"left top", at:"left bottom", of:event});
        },
        ondblClickRow : function(id,irow, icol, event){
        	
        },
        
        gridComplete: function(){
        	var ids = $("#userGrid").jqGrid('getDataIDs');
        	for(var i=0;i < ids.length;i++)
        	{
        		$("#userGrid").setRowData( ids[i],false,{height: 30+i*2});
        	}
        	
        }
       // caption : "User List",
       // editurl : "${pageContext.request.contextPath}/user/saveUser.do"
	});
	$("#userGrid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});	
	
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
	var data = $("#userGrid").jqGrid('getRowData',id);
	selectId = data.userId;
}
function closeWin()
{
	
}

function addRow()
{
	
	top.$("#pubDialog")
	.dialog("open")
	.dialog( "option", "width", 500 )
	.dialog( "option", "height",450 )
	.load("${base_context}/user/add.oa");
	
}
function editRow()
{
	if(selectId>0)
	{
		
		top.$("#pubDialog")
		.dialog("open")
		.dialog( "option", "width", 500 )
		.dialog( "option", "height",450 )
		.load("${base_context}/user/add.oa?userId="+selectId);
	}
}
function showAssign()
{
	if(selectId>0)
	{
		
		top.$("#pubDialog")
		.dialog("open")
		.dialog( "option", "width", 500 )
		.dialog( "option", "height", 450 )
		.load("${base_context}/user/showAssignRoles.oa?userId="+selectId);
	}
}
function delRow(e)
{

}

function isnull(a, b) {
    b = b || '';
     return a || b;
}

</script>

