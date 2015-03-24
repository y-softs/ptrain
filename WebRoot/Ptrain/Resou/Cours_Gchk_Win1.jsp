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
	<link rel="stylesheet" href="<c:url value='/Style/valid.css'/>"/>
	<script language="javascript" src="<c:url value='/Script/Main.js'/>"></script>
	<script language="javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#submitBtn").bind("click",callBack);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
			$("#tr_0").hide();
			
			$("select[name='typeid']").bind("change",function(){$("input[name='ptrainCoursBean.typeid']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='title']").bind("blur",function(){$("input[name='ptrainCoursBean.title']").val($(this).val());$("#dataChange").val("true");});
			$("textarea[name='content']").bind("blur",function(){$("input[name='ptrainCoursBean.content']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='object']").bind("blur",function(){$("input[name='ptrainCoursBean.object']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='hour']").bind("blur",function(){$("input[name='ptrainCoursBean.hour']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='time']").bind("blur",function(){$("input[name='ptrainCoursBean.time']").val($(this).val());$("#dataChange").val("true");});
			
			//绑定类别
			$("select[name='typeid']").attr("fv-empty","false").attr("fv-empty-msg","课件类别<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_typeid");
			//课件名称
			$("input[name='title']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
							.attr("fv-empty","false").attr("fv-empty-msg","课件名称<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_title");
			//课件简介
			$("textarea[name='content']").attr("fv-maxlength","500").attr("fv-maxlength-msg","字数不能超过500字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_content");
			//适用对象
			$("textarea[name='object']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_object");
			//学时
			$("input[name='hour']").attr("fv-datatype","Float").attr("fv-datatype-msg","学时<fmt:message key="format.int" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_hour");
			//时长
			$("input[name='time']").attr("fv-datatype","Float").attr("fv-datatype-msg","时长<fmt:message key="format.int" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_time");
			//绑定form表单自定义验证
			$("#form2").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		});
	    //验证绑定
	    function operFiled(){
	    	if($("input[name=querymap.chksign]:checked").val()=='${BU_TONG_YI}'){
				$("#chkmemo").attr("fv-empty","false").attr("fv-empty-msg"," 意见<fmt:message key="format.null" bundle="${bms}"/>")
								.attr("fv-msg-success","").attr("fv-msgpanel","valid_chkmemo");
				$("#tr_0").show();
			}else{
				$("#chkmemo").attr("fv-empty","true").attr("fv-empty-msg","")
								.attr("fv-msg-success","").attr("fv-msgpanel","valid_chkmemo");
				$("#tr_0").hide();
			}
	    }
		//保存
		function callBack(){
			operFiled();
			
			$("#form2").attr("action",'<c:url value='/Ptrain/Cours/updateptraincourschk.shtml'/>').trigger("submit");
	
			if($("#formerror").text() == "ok"){
		    	return true;
			}
			return false;
		}
	</script>
</head>
<style>
	.titleClass {clear:both;display:block;float:none;height:30px;width:100%;text-align:center;}
	.splarLine  {float:left;width:3px;height:400px;background:#321;}
	.floatClass  {padding-left:10px;float:left;width:48%;height:400px;background:#FFF;}
</style>
<body>
<div class="container">
    <!-- 查询条件 --->
    <form name="form2" id="form2" action="" method="post">
    	<div class="floatClass">
    	<span class="titleClass">申报内容</span>
        <table class="editTable">
           <tbody>
            <tr>
                <!--左侧表格项-->
                <td nowrap class="left">*&nbsp;课件类别：</td>
                <!--右侧表格项-->
                <td class="right"><select name="typeid">
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
                	<input name="title" type="text" class="text_50" value="${ptrainCoursBean.title}"/>
                	<span id="valid_title" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">课件简介：</td>
                <td class="right">
                    <textarea name="content" class="textarea_50_8">${ptrainCoursBean.content}</textarea><br/>
                    <span id="valid_content" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">适用对象：</td>
                <td class="right">
                    <textarea name="object" class="textarea_50_8">${ptrainCoursBean.object}</textarea><br/>
                	<span id="valid_object" class="validate-info"></span>	
                </td>
            </tr>
            <tr>
                <td class="left">学时：</td>
                <td class="right">
                	<input name="hour" type="text" class="text_04" maxlength="3" value="${ptrainCoursBean.hour}"/>
                	<span id="valid_hour" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td class="left">时长：</td>
                <td class="right">
                	<input name="time" type="text" class="text_04" maxlength="3" value="${ptrainCoursBean.time}"/>&nbsp;分钟
                	<span id="valid_time" class="validate-info"></span>
                </td>
            </tr>
           </tbody>
        </table>
       </div>
    	<div class="splarLine"></div>
    	<div class="floatClass">
    	<span class="titleClass">审核意见</span>
        <table class="editTable">
            <!--表格内容部分-->
            <tbody>
            <tr>
                <!--左侧表格项-->
                <td nowrap class="left">审核意见：</td>
                <!--右侧表格项-->
                <td class="right">
                    <input type="radio" name="querymap.chksign" value="${TONG_YI}" checked onClick="operFiled();"/>同意
                    <input type="radio" name="querymap.chksign" value="${BU_TONG_YI}" onClick="operFiled();"/>不同意
                </td>
            </tr>
            <tr id="tr_0">
                <td nowrap class="left">*&nbsp;</td>
                <td class="right">
                	<textarea name="querymap.chkmemo" id="chkmemo" class="textarea_all_3"></textarea>
                	<span id="valid_chkmemo" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">审核人：</td>
                <td class="right"><input name="chkuser" type="text" class="text_10line" value="${opraName}" maxlength="10" readonly/></td>
            </tr>
            <tr>
                <td nowrap class="left">审核时间：</td>
                <td class="right">
                	<input name="chktime" type="text" class="text_20line" value="${opraTime}" maxlength="20" readonly/>
                </td>
            </tr>
            <tr>
                <td nowrap colspan="2">
                	<input type="button" id="submitBtn" class="btn btn-info" value="确定"/>
                	<input type="button" id="closeBtn" class="btn btn-info" value="关闭"/>
                    <input type="hidden" name="ptrainCoursBean.id" value="${ptrainCoursBean.id}" id="id"/>
                    <input type="hidden" name="ptrainCoursBean.typeid" value="${ptrainCoursBean.typeid}"/>
                    <input type="hidden" name="ptrainCoursBean.title" value="${ptrainCoursBean.title}"/>
                    <input type="hidden" name="ptrainCoursBean.content" value="${ptrainCoursBean.content}"/>
                    <input type="hidden" name="ptrainCoursBean.object" value="${ptrainCoursBean.object}"/>
                    <input type="hidden" name="ptrainCoursBean.hour" value="${ptrainCoursBean.hour}"/>
                    <input type="hidden" name="ptrainCoursBean.time" value="${ptrainCoursBean.time}"/>
                    <input type="hidden" name="querymap.dataChange" id="dataChange"/>
					<span id="formerror"></span>
                </td>
            </tr>
            </tbody>
        </table>
       </div>
    </form>
</div>
</body>
</html>