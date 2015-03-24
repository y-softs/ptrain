<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<script type="text/javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
<script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
<style type="text/css">
body{border:none;margin:0px;padding:0px;}
</style>
<title></title>	
<body>
	<!-- 显示错误信息 -->
	<s:property value="#request.htmlError" escape="false"/>
</body>
</html>
