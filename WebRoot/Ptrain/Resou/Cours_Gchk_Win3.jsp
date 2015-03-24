<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
	<link rel="stylesheet" href="../../Style/valid.css"/>
	<script language="javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<link href="<c:url value='/Uploadify/uploadify.css'/>" rel="stylesheet" type="text/css"/>
	<script language="javascript" src="<c:url value='/Uploadify/swfobject.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/jquery.uploadify.v2.1.4.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/FileDBUtil.js'/>"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
</head>
<body>
<div class="editContainer">
    <div class="errorBlock"></div>
    <!-- 查询条件 --->
    <form name="form1" id="form1" action="" method="post">
        <table class="editTable">
           <!--表格内容部分-->
           <tbody>
            <tr>
                <!--左侧表格项-->
                <td nowrap class="left">*&nbsp;课件类别：</td>
                <!--右侧表格项-->
                <td class="right"><select name="ptrainCoursBean.typeid">
                		<option value="">请选择</option>
                		<c:forEach items="${specList}" var="s">
                			<option value="${s.id}" ${s.id==ptrainCoursBean.typeid||s.id==querymap.typeid?'selected':''}>${s.codename}</option>
                		</c:forEach>
                    </select>
                    <span id="valid_typeid" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">*&nbsp;课件名称：</td>
                <td class="right">
                	<input name="ptrainCoursBean.title" type="text" class="text_50" value="${ptrainCoursBean.title}"/>
                	<span id="valid_title" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">课件简介：</td>
                <td class="right">
                    <textarea name="ptrainCoursBean.content" class="textarea_50_8">${ptrainCoursBean.content}</textarea><br/>
                    <span id="valid_content" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">适用对象：</td>
                <td class="right">
                    <textarea name="ptrainCoursBean.object" class="textarea_50_8">${ptrainCoursBean.object}</textarea><br/>
                	<span id="valid_object" class="validate-info"></span>	
                </td>
            </tr>
            <tr>
                <td class="left">学时：</td>
                <td class="right">
                	<input name="ptrainCoursBean.hour" type="text" class="text_04" maxlength="3" value="${ptrainCoursBean.hour}"/>
                	<span id="valid_hour" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td class="left">时长：</td>
                <td class="right">
                	<input name="ptrainCoursBean.time" type="text" class="text_04" maxlength="3" value="${ptrainCoursBean.time}"/>&nbsp;分钟
                	<span id="valid_time" class="validate-info"></span>
                </td>
            </tr>
           </tbody>
           <!--表格合计或操作部分-->
           <tfoot>
           <tr>
               <td colspan="2">
                	<input type="button" id="submitBtn" class="btn btn-info" value="确定"/>
                	<input type="button" id="closeBtn" class="btn btn-info" value="关闭"/>
                    <input type="hidden" name="tokenid" value="${tokenid}"/>
                    <input type="hidden" name="ptrainCoursBean.id" value="${ptrainCoursBean.id}"/>
                    <input type="hidden" name="ptrainCoursBean.fatherid" value="${fatherid}"/>
                    <input type="hidden" name="ptrainCoursBean.flowsta" value="${querymap.flowsta}"/>
                    <!-- 回帖 -->
                    <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                    <input type="hidden" name="querymap.flowsta" value="${querymap.flowsta}"/>
                    <input type="hidden" name="fatherid" value="${fatherid}"/>
                    <input type="hidden" name="setpage" value="${setpage}"/>
					<span id="formerror"></span>
               </td>
           </tr>
           </tfoot>
        </table>
    </form>
</div>
</body>
	<script type="text/javascript">	$(function(){
		$("#submitBtn").bind("click",callBack);
		$("#closeBtn").bind("click",function(){
			parent.$.jBox.close();
		});
		//绑定类别
		$("select[name='ptrainCoursBean.typeid']").attr("fv-empty","false").attr("fv-empty-msg","课件类别<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_typeid");
		//课件名称
		$("input[name='ptrainCoursBean.title']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
						.attr("fv-empty","false").attr("fv-empty-msg","课件名称<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_title");
		//课件简介
		$("textarea[name='ptrainCoursBean.content']").attr("fv-maxlength","500").attr("fv-maxlength-msg","字数不能超过500字")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_content");
		//适用对象
		$("textarea[name='ptrainCoursBean.object']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_object");
		//学时
		$("input[name='ptrainCoursBean.hour']").attr("fv-datatype","Float").attr("fv-datatype-msg","学时<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_hour");
		//时长
		$("input[name='ptrainCoursBean.time']").attr("fv-datatype","Float").attr("fv-datatype-msg","时长<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_time");
		//绑定form表单自定义验证
		$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
	});
   function callBack(){
		$("#form1").attr("action",'<c:url value='/Ptrain/Cours/saveptraincours.shtml'/>').trigger("submit");
		if($("#formerror").text() == "ok"){
	    	if($("#gosign").is(":checked")){
	    		return '1';
	    	}else{
				return 'true';
	    	}
		}
		return false;
   }
	</script>
</html>