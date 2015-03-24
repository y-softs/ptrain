<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript">
		$(function(){
		});
	    //下载文件 
		function downLoad(savename,savepath){
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
	</script>
</head>
<body>
<div class="editContainer">
    <div class="errorBlock"></div>
    <!-- 查询条件 --->
    <form name="form1" action="" method="post">
        <table class="editTable">
             <!--表格内容部分-->
             <tbody>
             <tr>
                 <!--左侧表格项-->
                 <td nowrap class="left">课件类别：</td>
                 <!--右侧表格项-->
                 <td class="right">${ptrainCoursBean.strflag}</td>
             </tr>
             <tr>
                 <td nowrap class="left">课件名称：</td>
                 <td class="right"><span class="fontcolor_b">${ptrainCoursBean.title}</span></td>
             </tr>
             <tr>
                 <td nowrap class="left">封面：</td>
                 <td class="right">
                 	<c:forEach items="${fileslist}" var="f" varStatus="st">
                 		<a href="JavaScript:downLoad('${f.savename}','${savePath}');">${f.filename}</a><br/>
                 	</c:forEach>
             	</td>
             </tr>
             <tr>
                 <td nowrap class="left">课件简介：</td>
                 <td class="right">
                 	${ptrainCoursBean.content}
             	</td>
             </tr>
             <c:choose>
             	<c:when test="${ptrainCoursBean.courstype==2}">
		             <tr>
		                 <td nowrap class="left">附件：</td>
		                 <td class="right">
		                 	<c:forEach items="${fileslist2}" var="f" varStatus="st">
		                 		附件${st.count}：<a href="JavaScript:downLoad('${f.savename}','${savePath}');">${f.filename}</a><br/>
		                 	</c:forEach>
		             	</td>
		             </tr>
             	</c:when>
             	<c:otherwise>
		             <tr>
		                 <td nowrap class="left">适用对象：</td>
		                 <td class="right">${ptrainCoursBean.object}</td>
		             </tr>
		             <tr>
		                 <td nowrap class="left">学时：</td>
		                 <td class="right">${ptrainCoursBean.hour}</td>
		             </tr>
		             <tr>
		                 <td nowrap class="left">时长：</td>
		                 <td class="right">${ptrainCoursBean.time}分钟 </td>
		             </tr>
             	</c:otherwise>
             </c:choose>
             <tr>
                 <td nowrap class="left">课件制作人：</td>
                 <td class="right">
                 	${ptrainCoursBean.producer}
             	</td>
             </tr>
             </tbody>
        </table>
    </form>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>