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
               <td nowrap class="left">*&nbsp;专业类别：</td>
               <!--右侧表格项-->
               <td class="right"><select name="ptrainReqBean.specid">
               		<option value="">请选择</option>
               		<c:forEach items="${specList}" var="s">
               			<option value="${s.id}" ${s.id==ptrainReqBean.specid||s.id==querymap.specid?'selected':''}>${s.codename}</option>
               		</c:forEach>
                   </select>
                    <span id="valid_specid" class="validate-info"></span>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">*&nbsp;项目名称：</td>
               <td class="right">
                	<input name="ptrainReqBean.itemname" type="text" class="text_50" value="${ptrainReqBean.itemname}"/>
                	<span id="valid_itemname" class="validate-info"></span>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">*&nbsp;课程介绍：</td>
               <td class="right">
                   <textarea name="ptrainReqBean.itemdesc" class="textarea_50_8">${ptrainReqBean.itemdesc}</textarea></textarea><span id="valid_itemdesc" class="validate-info"></span>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">*&nbsp;培训天数：</td>
                <td class="right">
                	<input name="ptrainReqBean.daycount" type="text" class="text_04" maxlength="3" value="${ptrainReqBean.daycount}"/>&nbsp;天
                	<span id="valid_daycount" class="validate-info"></span>
                </td>
           </tr>
           <c:if test="${empty ptrainReqBean.id}">
           <tr>
               <td nowrap class="left">&nbsp;继续新增：</td>
               <td class="right"><input type="checkbox" name="gosign" id="gosign" value="1" ${'1'==gosign?'checked':''}/></td>
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
                   <input type="hidden" name="ptrainReqBean.id" value="${ptrainReqBean.id}"/>
                   <input type="hidden" name="ptrainReqBean.unitid" value="${querymap.unitid}"/>
                   <input type="hidden" name="ptrainReqBean.reqtype" value="${querymap.reqtype}"/>
                   <input type="hidden" name="ptrainReqBean.requserid" value="${querymap.requserid}"/>
                   <input type="hidden" name="querymap.postid" value="${querymap.postid}"/>
                   <!-- 回帖 -->
                   <input type="hidden" name="sign" value="${sign}"/>
                   <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                   <input type="hidden" name="querymap.specid" value="${querymap.specid}"/>
                   <input type="hidden" name="querymap.reqtype" value="${querymap.reqtype}"/>
                   <input type="hidden" name="querymap.requserid" value="${querymap.requserid}"/>
                   <input type="hidden" name="fields" value="${fields}" id="fields"/>
                   <input type="hidden" name="keyword" value="${keyword}" id="keyword"/>
                   <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
                   <input type="hidden" name="record" value="${record}" id="record" />
                   <input type="hidden" name="listpage" value="${listpage}" />
                   <input type="hidden" name="setpage" value="${setpage}" />
					<span id="formerror"></span>
               </td>
           </tr>
           </tfoot>
        </table>
    </form>
</div><script type="text/javascript">
	$(function(){
		$("#submitBtn").bind("click",callBack);
		$("#closeBtn").bind("click",function(){
			parent.$.jBox.close();
		});
		//绑定类别
		$("select[name='ptrainReqBean.specid']").attr("fv-empty","false").attr("fv-empty-msg","专业类别<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_specid");
		//项目名称
		$("input[name='ptrainReqBean.itemname']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
						.attr("fv-empty","false").attr("fv-empty-msg","项目名称<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_itemname");
		//课程介绍
		$("textarea[name='ptrainReqBean.itemdesc']").attr("fv-maxlength","1000").attr("fv-maxlength-msg","字数不能超过1000字")
						.attr("fv-empty","false").attr("fv-empty-msg","课程介绍<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_itemdesc");
		//绑定培训天数
							
		$("input[name='ptrainReqBean.daycount']").attr("fv-empty","false").attr("fv-empty-msg","培训天数<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-datatype","Float").attr("fv-datatype-msg","培训天数<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_daycount");
		
		//绑定form表单自定义验证
		$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
	});
   function callBack(){
		$("#form1").attr("action",'<c:url value='/Ptrain/Req/saveptrainreq.shtml'/>').trigger("submit");
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