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
<style type="text/css">

</style>
<script type="text/javascript">

$(function(){
	$("#tabs").kendoTabStrip();
	 var tabStrip = $("#tabs").kendoTabStrip().data("kendoTabStrip");
	 tabStrip.activateTab(tabStrip.items()[0]);
	
});
</script>
</head>
<body class="bodystyle">
	<div id="tabs">
		<ul>
			<c:forEach var="client" items="${clients }">
				<li id="clientTab_${client.clientId}">${client.clientName}</li>
			</c:forEach>
		</ul>
		<c:forEach var="client" items="${clients }">
			<div id="content_${client.clientId}">
					<iframe id="clientFrame_${client.clientId}" name="clientFrame_${client.clientId}" src="${base_context}/showContent.oa?clientId=${client.clientId}" width="100%" height="100%" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" ></iframe>
			</div>
		</c:forEach>
	</div>
</body>
</html>