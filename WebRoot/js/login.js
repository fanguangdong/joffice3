function showErrorMsg(msg)
{
	var _div = "<div class='k-state-error'>";
	_div += msg;
	_div +="</div>"
	$("#msg").html(_div);
}
function isNull(_str)
{
	return _str==null || _str=='';
}