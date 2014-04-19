function initLeftRight(_available,_selected,_btnR,_btnL,_btnAR,_btnAL)
{
	$('#'+_available).dblclick(function(){
	       $("option:selected",this).appendTo('#'+_selected);
	    });
	$('#'+_selected).dblclick(function(){
	       $("option:selected",this).appendTo('#'+_available);
	    });
	
	
	$('#'+_btnR).click(function(event){
		event.preventDefault();
		$('#'+_selected+' option:selected').appendTo('#'+_available);
	});
	$('#'+_btnAR).click(function(event){
		event.preventDefault();
		$('#'+_selected+' option').appendTo('#'+_available);
	});
	
	$('#'+_btnL).click(function(event){
		event.preventDefault();
		$('#'+_available+' option:selected').appendTo('#'+_selected);
	});
	$('#'+_btnAL).click(function(event){
		event.preventDefault();
		$('#'+_available+' option').appendTo('#'+_selected);
	});
}