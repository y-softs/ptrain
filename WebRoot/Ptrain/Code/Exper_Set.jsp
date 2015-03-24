<%@ page language="java" pageEncoding="UTF-8"%>
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
		$(function(){
			bindValid();
			$("#submitBtn").bind("click",callBack);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
		});
	
		function checkCodeValue(){
			var unitid = $("#b_unitid").val();
			var fatherid = '${ptrainCodeBean.fatherid}';
			var codevalue = $("#b_codevalue").val();
			var oldcodevalue = '${ptrainCodeBean.codevalue}';
			//当前编码为空
			if(codevalue == ''){
				$("#showmsg").text("");
				$("#showmsg").removeClass("fontcolor_red");
				return ;	
			}
			if(codevalue == oldcodevalue){
				return ;
			}
			var querymap = {'unitid':unitid,'fatherid':fatherid,'codevalue':codevalue};
			$.ajax({
					url:"<c:url value='/Ptrain/Code/checkCodeValueByJq.shtml'/>",   //接收页面
					type: 'post',      	//POST方式发送数据
					async: true,      		//ajax异步
					cache: false, 
					data:  querymap,
					error:function(data){
				    	alert( "与数据库操作失败！");
					},
					success:function(data){
						if(parseInt(data)>0){
							$("#showmsg").removeClass("validate-info")
										 .removeClass("validate-error").addClass("validate-error")
										 .html("编码重复");
						}else{
							$("#showmsg").removeClass("validate-error")
										 .removeClass("validate-info").addClass("validate-info")
										 .html("√");		
						}
					}
			});
		}
		
		function callBack(){
			$("#form1").attr("action",'<c:url value='/Ptrain/Code/saveptraincode.shtml'/>').trigger("submit");
			if($("#formerror").text() == "ok"){
				return true;
			}
			return false;
		}
		
		function bindValid(){
			//绑定类别
			$("#b_codename").attr("fv-maxlength","30").attr("fv-maxlength-msg","字数不能超过30字")
							.attr("fv-empty","false").attr("fv-empty-msg","类别不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_codename");
			$("#b_codename").parent().append('<span id="valid_codename" class="validate-info"></span>');
			//绑定编码
			$("#b_codevalue").attr("fv-empty","false").attr("fv-empty-msg","编码不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_codevalue");
			$("#b_codevalue").parent().append('<span id="valid_codevalue" class="validate-info"></span>');
			
			//绑定说明[备注]
			$("#b_remark").attr("fv-maxlength","1000").attr("fv-maxlength-msg","说明字数不能超过1000字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_remark");
			$("#b_remark").parent().append('<span id="valid_remark" class="validate-info"></span>');
			
			//绑定排序号
			$("#b_sortnum").attr("fv-empty","false").attr("fv-empty-msg","排序号不能为空")
							.attr("fv-datatype","Int16").attr("fv-datatype-msg","排序号只能为数字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_sortnum");
			$("#b_sortnum").parent().append('<span id="valid_sortnum" class="validate-info"></span>');
			
			//绑定form表单自定义验证
			$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		}
	</script>
</head>
<body>
<div class="editContainer">
	<form name="form1" method="post" id="form1">
        <table class="editTable">
            <tbody>
				<tr>
					<!--左侧表格项-->
					<td nowrap class="left">*类别：</td>
					<!--右侧表格项-->
					<td class="right">
					    <input type="text" name="ptrainCodeBean.codename" id="b_codename" class="text_50" value="${ptrainCodeBean.codename}">
					</td>
				</tr>
				<tr>
					<td class="left">说明：</td>
					<td class="right">
						<textarea name="ptrainCodeBean.remark" class="textarea_50_5" id="b_remark">${ptrainCodeBean.remark}</textarea>
					</td>
				</tr>
	            <tr>
	                <td class="left">编码：</td>
	                <td class="right">
	                    <input type="text" value="${ptrainCodeBean.codevalue}" id="b_codevalue" class="text_10" name="ptrainCodeBean.codevalue" maxlength="10" autocomplete="off" onkeyup="JavaScript:checkCodeValue();"/>
	                    <span id="showmsg"></span>
						<input type="hidden" name="codevalue" value="${code.codevalue}">
	                </td>
	            </tr>
	            <tr>
	                <td class="left" nowrap>排序号：</td>
	                <td class="right">
	                    <input type="numberbox" value="${ptrainCodeBean.sortnum}" id="b_sortnum"  class="text_10" name="ptrainCodeBean.sortnum"/>
	                </td>
	            </tr>
	            <!-- 以下为隐藏域 -->
	            <tr>
	                <td colspan="2">
	                	<input type="button" id="submitBtn" class="btn btn-info" value="确定"/>
	                	<input type="button" id="closeBtn" class="btn btn-info" value="关闭"/>
					    <input type="hidden" name="tokenid" value="${tokenid}" />
		                <input type="hidden" name="fatherid" value="${fatherid}" />
		                <input type="hidden" name="querymap.priorData" value="${querymap.priorData}" />
		                <input type="hidden" name="ptrainCodeBean.id" value="${ptrainCodeBean.id}" />
		                <input type="hidden" name="ptrainCodeBean.fatherid" value="${ptrainCodeBean.fatherid}" />
		                <input type="hidden" name="ptrainCodeBean.unitid" id="b_unitid" value="<c:choose><c:when test="${empty ptrainCodeBean.id}">${querymap.unitid}</c:when><c:otherwise>${ptrainCodeBean.unitid}</c:otherwise></c:choose>"/>
		                <input type="hidden" name="ptrainCodeBean.usesign" value="<c:choose><c:when test="${empty ptrainCodeBean.id}">1</c:when><c:otherwise>${ptrainCodeBean.usesign}</c:otherwise></c:choose>" />
	                </td>
	            </tr>
	            <tr style="display:none;">
	                <td colspan="2">
						<span id="formerror"></span>
	                </td>
	            </tr>
		    </tbody>
        </table>
   </form>
</div>
</body>
</html>