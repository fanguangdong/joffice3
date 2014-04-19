<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/save.js.jsp" %>
<%@ include file="js/uploadFlowDef.js.jsp" %>
<title>aaa</title>


<style type="text/css">


</style>
</head>
<body class="bodystyle">
<div id="detailForm">
<form:form id="flowDef" method="post" enctype="multipart/form-data" action="${base_context}/flowDef/uploadDef.oa" target="hiddenFrame"> 
	<input type="hidden" id="flowDefId" name="flowDefId" value="${flowDefId}"/> 
	<table>  
		<tr>  
			<td>Upload File: </td>  
			<td><input type="file" name="file" /></td>  
		</tr>
		<tr>
			<td colspan="2" align="center" valign="middle" id="formBtns">
					<a id="saveBtn">保存</a>
					<a id="resetBtn">重置</a>
			</td>
		</tr>
	</table>  
  </form:form> 
<iframe id="hiddenFrame" name="hiddenFrame"  src="javascript:'<body></body>'" width="0" height="0" frameborder="0" target="_self"></iframe>
</div>
</body>
</html>