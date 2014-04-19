<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Content</title>
<%@ include file="common/include.jsp" %>
<script type="text/javascript" src="${base_context}/js/content.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
var clientId = "${clientId}";
base_context="${base_context}";
$(function(){
	pageInit();
	
});
</script>
</head>
<body class="bodystyle">
<div id="horizontal"  style="height:99%">
 	<div id="lPanel">
 		<ul id="menus">
 			<c:forEach var="module" items="${modules }">
 				<li id="mod_${module.id }">
 					${module.name}
 					<c:if test="${module.hasChildren }">
 					<ul>
	 					<c:forEach var="child" items="${module.items }">
	 						<li id="mod_${child.id }">${child.name }</li>
	 					</c:forEach>
 					</ul>
 					</c:if>
 				</li>
 			</c:forEach>
 	  	
 		</ul>
 	
 	</div>
	<div id="rPanel">
		<iframe id="rightFrame" name="rightFrame" src="" width="100%" height="99%" marginwidth="0" marginheight="0" frameborder="0" scrolling="yes" ></iframe>
	</div>

</div>
</body>
</html>