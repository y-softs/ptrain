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
	<link rel="stylesheet" href="../../Style/valid.css"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
</head>
<body>
<div class="editContainer">
    <form name="form1" id="form1" action="" method="post">
        <table class="editTable">
            <!--表格内容部分-->
            <tbody>
            <tr>
                <!--左侧表格项-->
                <td nowrap class="left">课件名称：</td>
                <!--右侧表格项-->
                <td class="right"><select name="coursname" disabled><option>${querymap.coursname}</option></select></td>
            </tr>
            <c:if test="${not empty querymap.fathername}">
	            <tr>
	                <!--左侧表格项-->
	                <td nowrap class="left">上级目录：</td>
	                <!--右侧表格项-->
	                <td class="right"><select name="fathername" disabled><option>${querymap.fathername}</option></select></td>
	            </tr>
            </c:if>
            <tr>
                <td nowrap class="left">*&nbsp;目录名称：</td>
                <td class="right">
                	<input name="ptrainCoursBean.title" type="text" class="text_50" value="${ptrainCoursBean.title}"/>
                	<span id="valid_title" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td class="left">时长：</td>
                <td class="right">
                	<input name="ptrainCoursBean.time" type="text" class="text_04" maxlength="3" value="${ptrainCoursBean.time}"/>&nbsp;分钟
                	<span id="valid_time" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">&nbsp;末端标识：</td>
                <td class="right">
	            <c:choose>
	            	<c:when test="${empty ptrainCoursBean.id&&'2'==querymap.level||not empty ptrainCoursBean.id&&'3'==querymap.level}">
                		<input type="checkbox" name="endsign" id="endsign" value="1" checked disabled />
                		<input type="hidden" name="ptrainCoursBean.endsign" id="endsign" value="1"/>
	            	</c:when>
	            	<c:otherwise>
	            		<input type="checkbox" name="ptrainCoursBean.endsign" id="endsign" value="1" ${'1'==ptrainCoursBean.endsign?'checked':''}/>
	            	</c:otherwise>
	            </c:choose>
                </td>
            </tr>
            <c:if test="${empty ptrainCoursBean.id}">
            <tr>
                <td nowrap class="left">&nbsp;继续新增：</td>
                <td class="right">
                	<input type="checkbox" name="gosign" id="gosign" value="1" ${'1'==gosign?'checked':''}/>
                </td>
            </tr>
            </c:if>
            </tbody>
            <!--表格合计或操作部分-->
            <tfoot>
            <tr>
                <td colspan="2">
                	<input type="button" id="submitBtn" class="btn btn-info" value="确定"/>
                	<input type="button" id="closeBtn" class="btn btn-info" value="关闭"/>
                    <input type="hidden" name="tokenid" value="${tokenid}"/>
                    <input type="hidden" name="ptrainCoursBean.id" value="${ptrainCoursBean.id}"/>
                    <%--<input type="hidden" name="ptrainCoursBean.typeid" value="${querymap.typeid}"/>--%>
                    <input type="hidden" name="ptrainCoursBean.fatherid" value="${ptrainCoursBean.fatherid}"/>
                    <input type="hidden" name="ptrainCoursBean.flowsta" value="${querymap.flowsta}"/>
                    <!-- 回帖 -->
                    <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                    <input type="hidden" name="querymap.flowsta" value="${querymap.flowsta}"/>
                    <input type="hidden" name="querymap.typeid" value="${querymap.typeid}"/>
                    <input type="hidden" name="querymap.level" value="${querymap.level}"/>
                    <input type="hidden" name="fatherid" value="${fatherid}"/>
                    <input type="hidden" name="setpage" value="${setpage}"/>
					<span id="formerror"></span>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script type="text/javascript">
	$(function(){
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
</body>
</html>