var base_context;
/**
 * Initial
 */
var moduleMenu;
var actMenu;
var currNode;
var dataSource;
var actDialog;
function oncCustomLoad()
{
	moduleMenu = $("#moduleMenu").hide();
	actMenu = $("#actMenu").hide();
	$(document).bind("contextmenu",function(e)
    {
        return false;
    }); 
	$(document).click(function(){
		moduleMenu.hide();
		actMenu.hide();
	});
	$("#module").kendoSplitter(
	{
		orientation : "horizontal",
		panes : [
		{
			collapsible : false,
			resizable : true,
			size : "300px"
		}
		,
		{
			collapsible : false,
			resizable : true
		} 
		]
	});


	// Moudel tree start

	var _url = base_context+"/module/getModules.oa";
	dataSource = new kendo.data.HierarchicalDataSource({
	    transport: {
	        read: {
	            url: _url,
	            dataType: "json"
	        }
	    },
	    schema: {
	        model: {
	            id: "id",
	            hasChildren: "hasChildren"
	        }
	    }
	});
	$("#moduleTree").kendoTreeView(
	{
		//loadOnDemand: false,
		template: kendo.template($("#treeTemplate").html()),
		dataSource : dataSource,
		dataTextField : "name",
		select : function(e)
		{
			var treeview = $("#moduleTree").data("kendoTreeView");
			var dataItem = treeview.dataItem(e.node);
			selectModule(dataItem);
		}
	})
	.on('mousedown', '.k-in', function(event)
    {
        // Handle right click events...
        if (event.which === 3)
        {
        	var _uid = event.currentTarget.childNodes[1].id;
        	var treeview = $("#moduleTree").data("kendoTreeView");
        	var _currNode = treeview.findByUid(_uid);
        	treeview.select(_currNode);
        	var dataItem = treeview.dataItem(_currNode);
			selectModule(dataItem);
            showMenu(event,dataItem.nodeType,dataItem.id);
        }
    })
   
    ;
	
	// module tree end
	
	
	$("#saveBtn").kendoButton({
		click : function(e){
			saveModule();
		}
	});
	
	$("#saveAct").click(function(event){
		event.preventDefault();
		saveAction();
	});
	
	
	//bind dirty
	$("#moduleForm table tr td input").change(function(){
		disableBtn("saveBtn",false);
	});
	
	$("#actWin").kendoWindow({
        width: "500px",
        modal: true,
        title: "Action Detail",
        visible: false
    });
	actDialog = $("#actWin").data("kendoWindow");
}


/**
 * Disable or enable save button
 * @param disable
 */

function disableBtn(_id,disable)
{
	var button = $("#"+_id).data("kendoButton");

	/**/
	if(disable)
	{
		button.enable(false);
	}
	else
	{
		button.enable(true);

	}
}
function resetFrom()
{
	$("#moduleForm")[0].reset();
}


/*
 * Click save button
 */
function saveModule()
{
	var _parentId 		= $("#parentId").val();
	var _moduleId		= $("#moduleId").val();
	var _moduleName	= $("#moduleName").val();
	var _title 			= $("#title").val();
	var _icon 			= $("#icon").val();
	var _murl 			= $("#url").val();
	var _resSn 			= $("#resSn").val();

	
	var _url=base_context+"/module/save.oa";
	var _params = {	
					parentId		:_parentId,
					moduleId		:_moduleId,
					moduleName 		:_moduleName,
					title			:_title,
					icon			:_icon,
					url				:_murl,
					resSn			:_resSn
				 };
	$.post(
			_url, _params, 
			function(data)
			{
				if(data.status && data.status==1)
				{
					$("#moduleId").val(data.moduleId);
					//var treeview = $("#moduleTree").data("kendoTreeView");
					refreshTree();
				}
			},
			"json"
		);
}
function validateAction()
{
	var validator = $("#operForm").kendoValidator().data("kendoValidator");
	return validator.validate();

}
function saveAction()
{
	if(!validateAction())
	{
		return;
	}
	var _moduleId				= $("#mid").val();
	var _actionId 				= $("#actid").val();
	var _actionTypeCode 		= $("#actionTypeCode").val();
	var _actionTypeDesc 		= $("#actionTypeDesc").val();
	
	var _url	= base_context+"/module/saveAction.oa";
	var _params = {
			moduleId 		: _moduleId,
			actionId 		: _actionId,
			actionTypeCode	: _actionTypeCode,
			actionTypeDesc	: _actionTypeDesc
	};
	$.post(
			_url,
			_params,
			function(data)
			{
				if(data)
				{
					if(data.status == 1)
					{
						actDialog.close();
						refreshTree();
					}
					else
					{
						alert("Error");
					}
				}
			},
			"json"
	);
	
}
/**
 * Add new module or select moudel
 * @param _moduleId
 * @param _clientId
 * @param _parentId
 */
function getModuleDetail(_moduleId,_parentId)
{
	var _url = base_context+"/module/getModuleDetail.oa"
	var params = {moduleId : _moduleId, parentId : _parentId};
	$.post(
			_url, 
			params, 
			function(data)
			{
				if(data)
				{
					if(data)
					{
						$("#parentId").val(_parentId);
						
						$("#moduleId").val(data.moduleDetail.moduleId);
						$("#parent").val(data.parent);
						$("#moduleName").val(data.moduleDetail.moduleName);
						$("#title").val(data.moduleDetail.title);
						$("#icon").val(data.moduleDetail.icon);
						$("#url").val(data.moduleDetail.url);
						$("#resSn").val(data.moduleDetail.resSn);
					}
				}
			},
			"json"
			
	);
	
}



function selectModule(_dataItem)
{
	currNode = _dataItem;
	if(_dataItem.id>0 && _dataItem.nodeType=="module")
	{
		getModuleDetail(_dataItem.id,_dataItem.parentId);
		disableBtn("saveBtn",true);
	}
}

/**
 * Show module tree panel
 * @param clientId
 */
function refreshTree()
{
	var moduleTree =  $("#moduleTree").data("kendoTreeView"); 
	moduleTree.dataSource.read();
}

function showMenu(_event,_type,_id)
{
	if("module" == _type)
	{
		moduleMenu.show().kendoMenu({
			 orientation: "vertical"
		});
		var _menu = moduleMenu.data("kendoMenu");
		
		moduleMenu.on("click","#mAddMenu",addModule);
		if(_id==0)
		{
			moduleMenu.off("click","#mDelMenu",delModule);
			moduleMenu.off("click","#aAdd",addAct);
			_menu.enable("#mDelMenu",false);
			_menu.enable("#aAdd",false);
		}
		else
		{
			moduleMenu.on("click","#mDelMenu",delModule);
			moduleMenu.on("click","#aAdd",addAct);
			_menu.enable("#mDelMenu",true);
			_menu.enable("#aAdd",true);
		}
		moduleMenu.css({"position":"absolute","top":_event.clientY+"px", "left":_event.clientX+"px"});
	}
	
	if("action" == _type)
	{
		actMenu.show().kendoMenu({
			 orientation: "vertical"
		});
		actMenu.on("click","#actionEdit",editAct);
		actMenu.css({"position":"absolute","top":_event.clientY+"px", "left":_event.clientX+"px"});
	}
	
}
//add/edit/del act
function addAct()
{
	var mid = currNode.id;
	$("#mid").val(mid);
	$("#actid").val("");
	$("#actionTypeCode").val("");
	$("#actionTypeDesc").val("");
	actDialog.center();
	actDialog.open();
}
function editAct()
{
	$("#mid").val(currNode.mid);
	$("#actid").val(currNode.id);
	$("#actionTypeCode").val(currNode.actionTypeCode);
	$("#actionTypeDesc").val(currNode.actionTypeDesc);
	actDialog.center();
	actDialog.open();
}

//add/edit/del module
function addModule()
{
	var pid = currNode.id;
	if(pid<=0)
	{
		pid = 0;
	}
	getModuleDetail("",pid);
	disableBtn("saveBtn",false);
}


function delModule()
{
	if(currNode.hasChildren )
	{
		alert("There are chidlren node. Please remove children first.")
	}
	else
	{
		var mid = currNode.id;
		var _url = base_context+"/module/del.oa?moduleId="+mid;
		$.post(
				_url,
				function(data)
				{
					if(data && data.status && data.status ==1)
					{
						refreshTree();
					}
				},
				"json"
				
		);
	}
	
}

function findNodeById(_id)
{
	var treeview = $("#moduleTree").data("kendoTreeView");
	var dsItem = treeview.dataSource.get(_id);
	var _node = treeview.findByUid(dsItem.uid);
	return _node;
}