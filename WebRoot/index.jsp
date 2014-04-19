<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="jof" uri="/WEB-INF/tlds/jof.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<%@ include file="common/include_jui.jsp" %>
<style type="text/css">
#vertical 
{
	position: fixed;
	top: 0;
	left: 0;
    height: 750px;
    width: 99.9%;
}
#topPanel
{
	position: absolute;
	top: 0;
	left: 0;
	height: 100px;
	width: 100%;
	overflow: hidden;
	vertical-align: middle;
}
#toolbar
{
	position: fixed;
	top: 100px;
	left: 0;
	width: 100%;
	height: 30px;
	
	vertical-align: top;
	border-top: solid 2px #999;
}
#content
{
	position: fixed;
	left: 0px;
	top: 133px;
	right: 0;
	bottom: 0;
	overflow: auto;
	
	border-top: solid 2px #999;
	border-left: solid 2px #999;
}
.ui-menu { width: 150px; }

.ui-widget-header
{
	height:30px;
}
#tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
</style>
<script type="text/javascript">
var base_context="${base_context}";

$(function(){


	$("#toolbar")
	.buttonset()
	.children()
	.each(function(){
		var id = $(this).attr("id");
		$(this).button({
			icons :{secondary : "ui-icon-triangle-1-s"}
		})
		.click(function(){
			$(".menu").hide();
			var id = $(this).attr("id");
			$("#"+id+"Menu")
			.show()
			.position({my: "left top", at:"left bottom", of:this})
			.mouseleave(function(){
				$(this).hide();
			});
		});
	});
	
	$(".menu")
	.menu()
	.hide()
	.children()
	.click(function(){
		$(this).parent().hide();
	});
	
	tabs = $("#tabs").tabs();
	

	$("#contentFrame").attr("height",$(top.window).height()-140);
	
	$("#loadingDialog").dialog({
		autoOpen: false,
		width:100,
		height:100,
		pisition: { my: "center", at: "center", of: window },
		modal:true,
		resizable:true,
		title:"Loading..."
	});
	
});

function showWaitDialog()
{
	$("#loadingDialog")
	.dialog("open")
	.dialog( "moveToTop" );
}

function closeWaitDialog()
{
	$("#loadingDialog")
	.dialog("close");
}


function clickMenuItem(mid)
{
	$("#contentFrame").attr('src',"${base_context}/content.oa?moduleId="+mid);
}


</script>
</head>
<body class="bodystyle">
<div id="vertical" >
	<div id="topPanel" class="ui-widget ui-widget-header">top</div>
	 <div id="toolbar"> 
	 	
	 	<a id="file"><spring:message code="menu.file"/></a>
	 	
	 	<a id="admin">&nbsp;&nbsp;系&nbsp;&nbsp;统&nbsp;&nbsp;</a>
	 </div>
	<div id="content">
		<iframe id="contentFrame" name="contentFrame"  width="99%" height="99%" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" ></iframe>
	</div>
</div>




<ul id="fileMenu" class="menu" style="width:50px">
	<li><spring:message code="menu.file.exist"/></li>
</ul>

 <ul id="adminMenu" class="menu">
 
 	<c:forEach var="module" items="${modules }">
 		<c:if test="${jof:hasPermission(_user_login,module.resSn,1) }">
			<li id="${ module.moduleId}"><a href="#" onclick="clickMenuItem(${module.moduleId})">${ module.moduleName}</a></li>
 		</c:if>
 	</c:forEach>
 
</ul>


 
<div id="pubDialog"></div>
<div id="loadingDialog">Please wait...</div>
</body>

</html>