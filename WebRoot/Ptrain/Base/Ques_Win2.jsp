<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head> 
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<link rel="stylesheet" href="../../Style/valid.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
	<script language="javascript">
		$(function(){
			typeChangBind();
			$("#typeid").trigger("change");
			bindValid();
		});
		
		function bindValid(){
			//绑定类别
			$("#specid").attr("fv-empty","false").attr("fv-empty-msg","专业类别不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_specid");
			$("#specid").parent().append('<span id="valid_specid" class="validate-info"></span>');
			//绑定题目
			$("#b_topic").attr("fv-empty","false").attr("fv-empty-msg","题目不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_topic");
			$("#b_topic").parent().append('<span id="valid_topic" class="validate-info"></span>');
			
			$("#b_remark").attr("fv-maxlength","1000").attr("fv-maxlength-msg","备注不能超过1000字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_remark");
			$("#b_remark").parent().append('<span id="valid_remark" class="validate-info"></span>');
			
			
			//绑定form表单自定义验证
			$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		}
		//保存
		function callBack(){
			var selOpt=$("#typeid option:selected").val();
			var selOutId = "";
			//clear valid
			$("input[id='single'],input[id='mult'],input[id='judge']").attr("fv-minlength","0").attr("fv-minlength-msg","");
			if('${singleSel}'==selOpt){
				selOutId ="single_selout";
				$("input[id='single']:eq(0)").attr("fv-minlength","1").attr("fv-minlength-msg","答案不能为空").attr("fv-msgpanel","valid_single");
				$("#valid_single").remove();
				$("input[id='single']:eq(0)").parent().append('<span id="valid_single" class="validate-info"></span>');
			}else if('${multSel}'==selOpt){
				selOutId ="mult_selout";
				$("input[id='mult']:eq(0)").attr("fv-minlength","1").attr("fv-minlength-msg","答案不能为空").attr("fv-msgpanel","valid_mult");
				$("input[id='mult']:eq(0)").parent().append('<span id="valid_mult" class="validate-info"></span>');
			}else if('${judgeSel}'==selOpt){
				$("input[id='judge']:eq(0)").attr("fv-minlength","1").attr("fv-minlength-msg","答案不能为空").attr("fv-msgpanel","valid_jugde");
				$("input[id='judge']:eq(0)").parent().append('<span id="valid_jugde" class="validate-info"></span>');
			}
			//选项、答案验证
			if('${singleSel}'==selOpt||'${multSel}'==selOpt){
				var answerObj=$("input[id='single']");
				if('${singleSel}'==selOpt){
					$("input[id='mult']").attr("name","mult");
					$("input[id='judge']").attr("name","judge");
					$("input[id='single']").attr("name","ptrainQuestionstempBean.answer1");
				}else{
					$("input[id='single']").attr("name","single");
					$("input[id='judge']").attr("name","judge");
					answerObj=$("input[id='mult']");
					$("input[id='mult']").attr("name","ptrainQuestionstempBean.answer1");
				}
				var seloptionStr='',notEmptyIndex=0,emptyIndex=999,emptyBool='';
				$("input[name='seloption']").each(function(i,n){
					seloptionStr+=$(this).val();
					if(''!=$(this).val()){
						notEmptyIndex=i;
					}else if(''==emptyBool){
						emptyIndex=i;
						emptyBool='true';
					}
				});
				if(''==seloptionStr){
					//alert('选项至少填写一项！');
					$("input[name='seloption']:eq(0)").attr("fv-empty","false").attr("fv-empty-msg","选项至少填写一项").attr("fv-msgpanel","valid_seloption0");
					$("#valid_seloption0").remove();
					$("input[name='seloption']:eq(0)").parent().append('<span id="valid_seloption0" class="validate-info"></span>');
					//$("input[name='seloption']:eq(0)").css("color","red").focus();
				}else if(notEmptyIndex>emptyIndex){
					//alert('选项空缺！');
					$("input[name='seloption']:eq("+emptyIndex+")").attr("fv-empty","false").attr("fv-empty-msg","选项空缺").attr("fv-msgpanel","valid_seloption"+emptyIndex);
					$("#valid_seloption"+emptyIndex).remove();
					$("input[name='seloption']:eq("+emptyIndex+")").parent().append('<span id="valid_seloption'+emptyIndex+'" class="validate-info"></span>');
					//$("input[name='seloption']:eq("+emptyIndex+")").css("color","red").focus();
				}else{
					var chkIndex=0;
					answerObj.each(function(i,n){
						if(n.checked)chkIndex=i;
					});
					$("#valid_seloptionx").remove();
					if(chkIndex>notEmptyIndex){
						//alert('答案超出选项范围！');
						//answerObj.eq(chkIndex).attr("fv-empty","false").attr("fv-empty-msg","答案超出选项范围").attr("fv-msgpanel","valid_seloptionx");
						//$("#valid_seloptionx").remove();
						//answerObj.eq(chkIndex).parent().append('<span id="valid_seloptionx" class="validate-info"></span>');
						//answerObj.eq(chkIndex).focus();
						$("#"+selOutId).attr("fv-empty","false").attr("fv-empty-msg","答案超出选项范围").attr("fv-msgpanel","valid_seloptionx");
						$("#"+selOutId).parent().append('<span id="valid_seloptionx" class="validate-info"></span>');
						
					}
				}
			}else if('${judgeSel}'==selOpt){
				$("input[id='single']").attr("name","single");
				$("input[id='mult']").attr("name","mult");
				$("input[id='judge']").attr("name","ptrainQuestionstempBean.answer1");
			}
			$("#typetemp").val($("select[name='ptrainQuestionstempBean.typeid'] option:selected").text().replace('题',''));
		   	document.form1.action="<c:url value='/Ptrain/Questions/saveptrainquestionstemp.shtml' />";
	    	document.form1.submit();
			if($("#formerror").text() == "ok"){
				return true;
			}
			return false;
		}
		
	    function typeChangBind() {
	    	$("#typeid").bind("change",function(){
	    		$("#tr_option,#tr_answer1,#tr_answer2,#tr_answer3").hide();
	    		if($(this).val() == "${singleSel}"){
	    			$("#tr_option,#tr_answer1").show();
	    		}else if($(this).val() == "${multSel}"){
	    			$("#tr_option,#tr_answer2").show();
	    		}else if($(this).val() == "${judgeSel}"){
	    			$("#tr_answer3").show();
	    			$("input[name='seloption']").val("");
	    		}
	    	});
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
                       <td nowrap class="left">*专业类别：</td>
                       <!--右侧表格项-->
                       <td class="right">
                       	<select name="ptrainQuestionstempBean.kindid" id="specid">
                       		<option value="">请选择</option>
                               <c:forEach items="${specList}" var="p">
                       			<option value="${p.id}" <c:if test="${p.id==ptrainQuestionstempBean.kindid}">selected</c:if>>${p.codename}</option>
                       		</c:forEach>
                           </select>
                       </td>
                   </tr>
                   <tr>
                       <td nowrap class="left">*题型：</td>
                       <td class="right">
                       	<c:if test="${not empty typeList}">
                           <select name="ptrainQuestionstempBean.typeid" id="typeid">
                               <c:forEach items="${typeList}" var="p">
                       			<option value="${p.id}" ${p.id==ptrainQuestionstempBean.typeid?'selected':''}>${p.codename}</option>
                       		</c:forEach>
                           </select>
                           </c:if>
                       </td>
                   </tr>
                   <tr>
                       <td nowrap class="left">*题目：</td>
                       <td class="right"><textarea name="ptrainQuestionstempBean.topic" id="b_topic" class="textarea_50_3">${ptrainQuestionstempBean.topic}</textarea></td>
                   </tr>
                   <tr id="tr_option">
                       <td nowrap class="left">*选项：</td>
                       <td colspan="2" align="left">
                               <div>A、<input type="text" name="seloption" class="text_50" maxlength="50" value="${ptrainQuestionstempBean.option1}"></div>
                               <div>B、<input type="text" name="seloption" class="text_50" maxlength="50" value="${ptrainQuestionstempBean.option2}"></div>
                               <div>C、<input type="text" name="seloption" class="text_50" maxlength="50" value="${ptrainQuestionstempBean.option3}"></div>
                               <div>D、<input type="text" name="seloption" class="text_50" maxlength="50" value="${ptrainQuestionstempBean.option4}"></div>
                               <div>E、<input type="text" name="seloption" class="text_50" maxlength="50" value="${ptrainQuestionstempBean.option5}"></div>
                               <div>F、<input type="text" name="seloption" class="text_50" maxlength="50" value="${ptrainQuestionstempBean.option6}"></div>
                               <div>G、<input type="text" name="seloption" class="text_50" maxlength="50" value="${ptrainQuestionstempBean.option7}"></div>
                       </td>
                   </tr>
                   <tr id="tr_answer1">
                       <td nowrap class="left">*答案：</td>
                       <td class="right">
                           <input type="radio" name="single" id="single" value="A" ${fn:contains(ptrainQuestionstempBean.answer1, 'A')&&ptrainQuestionstempBean.typeid==singleSel?'checked':''}>A 
                           <input type="radio" name="single" id="single" value="B" ${fn:contains(ptrainQuestionstempBean.answer1, 'B')&&ptrainQuestionstempBean.typeid==singleSel?'checked':''}>B
                           <input type="radio" name="single" id="single" value="C" ${fn:contains(ptrainQuestionstempBean.answer1, 'C')&&ptrainQuestionstempBean.typeid==singleSel?'checked':''}>C
                           <input type="radio" name="single" id="single" value="D" ${fn:contains(ptrainQuestionstempBean.answer1, 'D')&&ptrainQuestionstempBean.typeid==singleSel?'checked':''}>D
                           <input type="radio" name="single" id="single" value="E" ${fn:contains(ptrainQuestionstempBean.answer1, 'E')&&ptrainQuestionstempBean.typeid==singleSel?'checked':''}>E
                           <input type="radio" name="single" id="single" value="F" ${fn:contains(ptrainQuestionstempBean.answer1, 'F')&&ptrainQuestionstempBean.typeid==singleSel?'checked':''}>F
                           <input type="radio" name="single" id="single" value="G" ${fn:contains(ptrainQuestionstempBean.answer1, 'G')&&ptrainQuestionstempBean.typeid==singleSel?'checked':''}>G
                       		<!-- 选项越界辅助字段 -->
                           <input type="hidden" id="single_selout" value="">
                       </td>
                   </tr>
                   <tr id="tr_answer2">
                       <td nowrap class="left">*答案：</td>
                       <td class="right">
                           <input type="checkbox" name="mult" id="mult" value="A" ${fn:contains(ptrainQuestionstempBean.answer1, 'A')&&ptrainQuestionstempBean.typeid==multSel?'checked':''}>A 
                           <input type="checkbox" name="mult" id="mult" value="B" ${fn:contains(ptrainQuestionstempBean.answer1, 'B')&&ptrainQuestionstempBean.typeid==multSel?'checked':''}>B
                           <input type="checkbox" name="mult" id="mult" value="C" ${fn:contains(ptrainQuestionstempBean.answer1, 'C')&&ptrainQuestionstempBean.typeid==multSel?'checked':''}>C
                           <input type="checkbox" name="mult" id="mult" value="D" ${fn:contains(ptrainQuestionstempBean.answer1, 'D')&&ptrainQuestionstempBean.typeid==multSel?'checked':''}>D
                           <input type="checkbox" name="mult" id="mult" value="E" ${fn:contains(ptrainQuestionstempBean.answer1, 'E')&&ptrainQuestionstempBean.typeid==multSel?'checked':''}>E
                           <input type="checkbox" name="mult" id="mult" value="F" ${fn:contains(ptrainQuestionstempBean.answer1, 'F')&&ptrainQuestionstempBean.typeid==multSel?'checked':''}>F
                           <input type="checkbox" name="mult" id="mult" value="G" ${fn:contains(ptrainQuestionstempBean.answer1, 'G')&&ptrainQuestionstempBean.typeid==multSel?'checked':''}>G
                       		<!-- 选项越界辅助字段 -->
                           <input type="hidden" id="mult_selout" value="">
                       </td>
                   </tr>
                   <tr id="tr_answer3">
                       <td nowrap class="left">*答案：</td>
                       <td class="right">
                           <input type="radio" name="judge" id="judge" value="1" ${'1'==ptrainQuestionstempBean.answer1&&ptrainQuestionstempBean.typeid==judgeSel?'checked':''}>正确 
                           <input type="radio" name="judge" id="judge" value="0" ${'0'==ptrainQuestionstempBean.answer1&&ptrainQuestionstempBean.typeid==judgeSel?'checked':''}>错误
                       </td>
                   </tr>
                   <tr>
                       <td nowrap class="left">备注：</td>
                       <td class="right"><textarea name="ptrainQuestionstempBean.remark" class="textarea_50_3" id="b_remark">${ptrainQuestionstempBean.remark}</textarea></td>
                   </tr>
                   </tbody>
                   <!--表格合计或操作部分-->
                   <tfoot style="display:none;">
                   <tr>
                       <td colspan="2">
						   <span id="formerror"></span>
                           <input type="hidden" name="tokenid" value="${tokenid}">
                           <input type="hidden" name="ptrainQuestionstempBean.id" value="${ptrainQuestionstempBean.id}">
                           <input type="hidden" name="ptrainQuestionstempBean.datasign" value="${ptrainQuestionstempBean.datasign}">
                           <input type="hidden" name="ptrainQuestionstempBean.typetemp" value="${ptrainQuestionstempBean.typetemp}" id="typetemp">
                           <input type="hidden" name="ptrainQuestionstempBean.unitid" value="${querymap.unitid}">
                       </td>
                   </tr>  	
                   </tfoot>
        </table>
   </form>
</div>
</body>
</html>