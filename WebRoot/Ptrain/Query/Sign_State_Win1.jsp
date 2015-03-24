<%@ page language="java"  pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<link rel="stylesheet" href="../../Style/valid.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
	<script type="text/javascript">
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
	<form name="form1" method="post" id="form1">
        <table class="editTable">
			<!--表格内容部分-->
            <tbody>
                <tr>
                    <!--左侧表格项-->
                    <td nowrap class="left">专业类别：</td>
                    <!--右侧表格项-->
                    <td class="right">${ptrainReqBean.specid}</td>
                </tr>
                <tr>
                    <td nowrap class="left">项目名称：</td>
                    <td class="right"><span class="fontcolor_b">${ptrainReqBean.itemname}</span></td>
                </tr>
                <tr>
                    <td nowrap class="left">课程介绍：</td>
                    <td class="right">
                    	${ptrainReqBean.itemdesc}<br/>
                    	<c:forEach items="${fileslist}" var="f" varStatus="st">
                    		附件${st.count}：<a href="JavaScript:downLoad('${f.savename}','${savePath}');">${f.filename}</a><br/>
                    	</c:forEach>
                	</td>
                </tr>
                <c:if test="${REQ_COUR!=ptrainReqBean.reqtype}">
                 <tr>
                     <td nowrap class="left">培训天数：</td>
                     <td class="right">${ptrainReqBean.daycount}&nbsp;天</td>
                 </tr>
                </c:if>
                 <tr>
                     <td nowrap class="left">报名人数：</td>
                     <td class="right">${ptrainReqBean.intflag}&nbsp;人</td>
                 </tr>
                <c:choose>
                 <c:when test="${REQ_COM==ptrainReqBean.reqtype||REQ_EXP==ptrainReqBean.reqtype}">
                  <tr>
                      <td nowrap class="left">&nbsp;项目来源：</td>
                      <td class="right">${ptrainReqBean.reqform}</td>
                  </tr>
                  <tr>
                      <td nowrap class="left">&nbsp;培训师：</td>
                      <td class="right">${ptrainReqBean.teacher}</td>
                  </tr>
                 </c:when>
                 <c:when test="${REQ_USER==ptrainReqBean.reqtype}">
                  <tr>
                      <td nowrap class="left">&nbsp;发起人：</td>
                      <td class="right">${ptrainReqBean.requserid}</td>
                  </tr>
                 </c:when>
                 <c:when test="${REQ_COUR==ptrainReqBean.reqtype}">
                  <tr>
                      <td nowrap class="left">&nbsp;课件制作人：</td>
                      <td class="right">${ptrainReqBean.teacher}</td>
                  </tr>
                 </c:when>
                </c:choose>
                <tr>
                    <td nowrap class="left">办班状态：</td>
                    <td class="right">${'0'==ptrainReqBean.state?'待办班':'已办班'}</td>
                </tr>
                <c:if test="${'1'==ptrainReqBean.state}">
                 <tr>
                     <td nowrap class="left">&nbsp;办班时间：</td>
                     <td class="right">${ptrainReqBean.maintime}</td>
                 </tr>
                </c:if>
		    </tbody>
        </table>
   </form>
	<div class="errorBlock"></div>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>