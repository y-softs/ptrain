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
                 <td nowrap class="left">资源类别：</td>
                 <!--右侧表格项-->
                 <td class="right">${ptrainContentBean.strflag}</td>
             </tr>
             <tr>
                 <td nowrap class="left">标题：</td>
                 <td class="right"><span class="fontcolor_b">${ptrainContentBean.title}</span></td>
             </tr>
             <tr>
                 <td nowrap class="left">文本内容：</td>
                 <td class="right">
                 	${ptrainContentBean.content}
             	</td>
             </tr>
             <tr>
                 <td nowrap class="left">文件信息：</td>
                 <td class="right">
                 	<c:forEach items="${fileslist}" var="f" varStatus="st">
                 		附件${st.count}：<a href="JavaScript:downLoad('${f.savename}','${savePath}');">${f.filename}</a><br/>
                 	</c:forEach>
                 </td>
             </tr>
             </tbody>
        </table>
    </form>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>