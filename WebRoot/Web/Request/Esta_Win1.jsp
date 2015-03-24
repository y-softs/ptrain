<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 配置文件信息 -->
<fmt:setBundle basename="globalMessages" var="bms"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/columns.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript">
	$(function(){
		//报名
		$("#btn_sure").click(function(){
			var index = parent.layer.confirm('确定报名？',function(i){
				var url="<c:url value='/pt/requser/saveptrainrequser.shtml'/>?ptrainRequserBean.reqid=${ptrainReqBean.id}";
				url+="&querymap.fromwin=true";
				document.form2.action=url;
				document.form2.submit();
			 });
	 	});
	});
    //下载文件 
	function downLoad(savename,savepath){
	   downframe.document.form1.inputPath.value = savepath;
	   downframe.document.form1.fileName.value = savename;
	   downframe.document.form1.submit();
	}    	
    </script>
<body>
<div class="set_tab">
<form action="" name="form2" id="form2" method="post">
	<table>
    	<tr>
        	<td class="left">专业类别：</td>
        	<td style="padding-left:5px;">${ptrainReqBean.specid}</td>
        </tr>
    	<tr>
        	<td class="left">项目名称：</td>
        	<td style="padding-left:5px;"><span class="fontcolor_b">${ptrainReqBean.itemname}</span></td>
        </tr>
    	<tr>
        	<td class="left">课程介绍：</td>
        	<td style="padding-left:5px;">
        		${ptrainReqBean.itemdesc}<br/>
               	<c:forEach items="${fileslist}" var="f" varStatus="st">
               		附件${st.count}：<a href="JavaScript:downLoad('${f.savename}','${f.savepath}');">${f.filename}</a><br/>
               	</c:forEach>
            </td>
        </tr>
        <c:if test="${REQ_COUR!=ptrainReqBean.reqtype}">
    	<tr>
        	<td class="left">培训天数：</td>
        	<td style="padding-left:5px;">${ptrainReqBean.daycount}&nbsp;天</td>
        </tr>
        </c:if>
    	<tr>
        	<td class="left">报名人数：</td>
        	<td style="padding-left:5px;">${ptrainReqBean.intflag}&nbsp;人</td>
        </tr>
    	<c:choose>
        <c:when test="${REQ_COM==ptrainReqBean.reqtype||REQ_EXP==ptrainReqBean.reqtype}">
        <tr>
            <td class="left">&nbsp;项目来源：</td>
            <td style="padding-left:5px;">${ptrainReqBean.reqform}</td>
        </tr>
        <tr>
            <td class="left">&nbsp;培训师：</td>
            <td style="padding-left:5px;">${ptrainReqBean.teacher}</td>
        </tr>
        </c:when>
        <c:when test="${REQ_USER==ptrainReqBean.reqtype}">
        <tr>
            <td class="left">&nbsp;发起人：</td>
            <td style="padding-left:5px;">${ptrainReqBean.requserid}</td>
        </tr>
        </c:when>
        <c:when test="${REQ_COUR==ptrainReqBean.reqtype}">
        <tr>
            <td class="left">&nbsp;课件制作人：</td>
            <td style="padding-left:5px;">${ptrainReqBean.teacher}</td>
        </tr>
        </c:when>
        </c:choose>
        <tr>
            <td class="left">办班状态：</td>
            <td style="padding-left:5px;">${'1'==ptrainReqBean.state?'已办班':'待办班'}</td>
        </tr>
        <c:if test="${'1'==ptrainReqBean.state}">
        <tr>
            <td class="left">&nbsp;办班时间：</td>
            <td style="padding-left:5px;">${ptrainReqBean.maintime}</td>
        </tr>
        </c:if>    	
    </table>
    <div style="width:110px; margin-left:40%;">
		<c:if test="${'0'==ptrainReqBean.state&&empty querymap.requserid}">
		<a href="###" class="btn btn-primary" id="btn_sure">报名</a>
	     <input type="hidden" name="tokenid" 		value="${tokenid}" />
		</c:if>
		<a href="###" class="btn btn-inverse" onclick="layerClose();">关闭</a>
	</div>
</form>
</div>

</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>