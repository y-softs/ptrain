<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/columns.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
	<script language="javascript">
	</script>
    <style type="text/css">
    	body {
		width:100% !important;width:97%;
		}
    </style>
<body>
<div class="set_tab">
<form action="" name="from2" method="post">
	<table>
    	<tr>
        	<td class="left">题型：</td>
        	<td style="padding-left:5px;"><span class="fontcolor_b">${ptrainAskitemBean.strflag}</span></td>
        </tr>
    	<tr>
        	<td class="left">题目：</td>
        	<td style="padding-left:5px;">${ptrainAskitemBean.topic}</td>
        </tr>
        <c:if test="${not empty ptrainAskitemBean.option1||not empty ptrainAskitemBean.option2||not empty ptrainAskitemBean.option3||not empty ptrainAskitemBean.option4||not empty ptrainAskitemBean.option5||not empty ptrainAskitemBean.option6||not empty ptrainAskitemBean.option7}">
    	<tr>
        	<td class="left">选项：</td>
        	<td style="padding-left:5px;">
            	 <c:if test="${not empty ptrainAskitemBean.option1}">A、${ptrainAskitemBean.option1}<br/></c:if>
            	 <c:if test="${not empty ptrainAskitemBean.option2}">B、${ptrainAskitemBean.option2}<br/></c:if>
            	 <c:if test="${not empty ptrainAskitemBean.option3}">C、${ptrainAskitemBean.option3}<br/></c:if>
            	 <c:if test="${not empty ptrainAskitemBean.option4}">D、${ptrainAskitemBean.option4}<br/></c:if>
            	 <c:if test="${not empty ptrainAskitemBean.option5}">E、${ptrainAskitemBean.option5}<br/></c:if>
            	 <c:if test="${not empty ptrainAskitemBean.option6}">F、${ptrainAskitemBean.option6}<br/></c:if>
            	 <c:if test="${not empty ptrainAskitemBean.option7}">G、${ptrainAskitemBean.option7}</c:if>
            </td>
        </tr>
        </c:if>
    	<tr>
        	<td class="left">答题答案：</td>
        	<td style="padding-left:5px;"> 
        		<span class="fontcolor_red">
                <c:choose>
                <c:when test="${'1'==ptrainAskitemBean.answer1}">正确</c:when>
                <c:when test="${'0'==ptrainAskitemBean.answer1}">错误</c:when>
                <c:otherwise>${ptrainAskitemBean.answer1}</c:otherwise>
                </c:choose>
                </span>
	       </td>
        </tr>
    	<tr>
        	<td class="left">正确答案：</td>
        	<td style="padding-left:5px;">
               <span class="fontcolor_blue">
               <c:choose>
               <c:when test="${'1'==ptrainAskitemBean.ansdetail}">正确</c:when>
               <c:when test="${'0'==ptrainAskitemBean.ansdetail}">错误</c:when>
               <c:otherwise>${ptrainAskitemBean.ansdetail}</c:otherwise>
               </c:choose>
               </span>
            </td>
        </tr>
    	<tr>
        	<td class="left">备注：</td>
        	<td style="padding-left:5px;">${ptrainAskitemBean.remark}${empty ptrainAskitemBean.remark?'&nbsp;':''}</td>
        </tr>
    </table>
</form>
<div style="width:100px; margin:10px auto 0 auto;">
    <a href="###" class="btn btn-inverse" onclick="layerClose();">关闭</a>
	<input type="hidden" name="querymap.unitid" 		value="${querymap.unitid}" />
	<input type="hidden" name="querymap.askitemid" 		value="${querymap.askitemid}" />
</div>
</div>
</body>
</html>