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
	<script type="text/javascript" src="<c:url value='/Web/Script/Valid.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
	<script language="javascript">
		function subMit(){
			if(validateInfo()){				
				$("#form2").attr("action",'<c:url value="/pt/requser/saveptrainreq.shtml"/>').trigger("submit");
			}
		}
		//验证
		function validateInfo(){			
			$("#specid").attr("dataType","Require").attr("msg","专业类别<fmt:message key="format.null" bundle="${bms}"/>");
			$("#itemname").attr("dataType","Require").attr("msg","项目名称<fmt:message key="format.null" bundle="${bms}"/>");
			$("#itemdesc").attr("dataType","Require").attr("msg","课程介绍<fmt:message key="format.null" bundle="${bms}"/>");
			$("#daycount").attr("dataType","Number").attr("msg","培训天数<fmt:message key="format.int" bundle="${bms}"/>");
			return Validator.Validate(document.form2,2);
		}
	</script>
<body>
<div class="set_tab">
<form action="" name="form2" id="form2" method="post">
	<table>
         <tr>
             <!--左侧表格项-->
             <td class="left">*&nbsp;专业类别：</td>
             <!--右侧表格项-->
             <td class="right" style="padding-left:5px;"><select name="ptrainReqBean.specid" id="specid">
             		<option value="">请选择</option>
             		<c:forEach items="${specList}" var="s">
             			<option value="${s.id}" ${s.id==ptrainReqBean.specid||s.id==querymap.specid?'selected':''}>${s.codename}</option>
             		</c:forEach>
                 </select>
             </td>
         </tr>
         <tr>
             <td class="left">*&nbsp;项目名称：</td>
             <td class="right" style="padding-left:5px;">
              	<input name="ptrainReqBean.itemname" id="itemname" type="text" class="text text_50" value="${ptrainReqBean.itemname}"/>
             </td>
         </tr>
         <tr>
             <td nowrap class="left">*&nbsp;课程介绍：</td>
             <td class="right" style="padding-left:5px;">
                 <textarea name="ptrainReqBean.itemdesc" id="itemdesc" class="textarea_50_8">${ptrainReqBean.itemdesc}</textarea></textarea>
             </td>
         </tr>
         <tr>
             <td class="left">*&nbsp;培训天数：</td>
              <td class="right" style="padding-left:5px;">
              	<input name="ptrainReqBean.daycount" id="daycount" type="text" class="text text_04" maxlength="3" value="${ptrainReqBean.daycount}"/>&nbsp;天
              </td>
         </tr>
    </table>
    <div style="width:110px; margin-left:40%;">
    	<a href="###" class="btn btn-primary" onclick="subMit();">确定</a>
		<a href="###" class="btn btn-inverse" onclick="layerClose();">关闭</a>
        <input type="hidden" name="tokenid" value="${tokenid}"/>
        <input type="hidden" name="ptrainReqBean.id" value="${ptrainReqBean.id}"/>
	</div>
</form>
</div>
 
</body>
</html>
