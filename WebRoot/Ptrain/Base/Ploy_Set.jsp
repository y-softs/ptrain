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
			bindValid();
			$("input[name='btn_sure']").bind("click",saveData);
			
			if(''==$("#typeid").val()&&$("#typeid option").length!=0&&${empty querymap.alltypeid}){
				$("#typeid").val($("#typeid option:eq(1)").attr("value"));
			}
			$("#typeid").bind("change",typeOper);
			$("#typeid").trigger("change");
		});
		
		function typeOper(){
			var typeid=$("#typeid").val(),typeidStr=typeid,alltypeSign='true';
			$("#alltypeid").val('');
			if(''==typeid&&$("#typeid option").length!=0){
				typeid=$("#typeid option:eq(1)").attr("value");
				typeidStr=$("#typeidStr").val();
				$("#alltypeid").val('true');
			}
			$("#b_typeid").val(typeidStr);
			var urlV="<c:url value='/Ptrain/Policy/findPtrainPolicyByJq.shtml' />";
			var dataV={'querymap.unitid':'${querymap.unitid}','querymap.typeidstr':typeidStr,'querymap.typeid':typeid,'querymap.alltypeid':alltypeSign};
			$.ajax({
				type:"post",
				url:urlV,
				data:dataV,
				async:false,
				dataType:"json",
				success:function(jsonV){
					if(''!=jsonV){
						$("input[name='ptrainPolicyBean.amount']").val(jsonV[0].amount);
						$("input[name='ptrainPolicyBean.score']").val(jsonV[0].score);
						$("#typenum").html(jsonV[0].intflag);
						$("#id").val(jsonV[0].id);
					}else{
						$("input[name='ptrainPolicyBean.amount']").val('');
						$("input[name='ptrainPolicyBean.score']").val('');
						$("#typenum").html('0');
						$("#id").val('');
					}
				},
				error:function(msg){
					alert('题量题分获取失败！');
				}
			});
		}
		//保存
		function saveData(){
		   	document.form1.action="<c:url value='/Ptrain/Policy/saveptrainpolicy.shtml' />";
	    	document.form1.submit();
		}
		
		function bindValid(){
			//每日出题
			$("input[name='paramvalue']:eq(0)").attr("fv-empty","false").attr("fv-empty-msg","每日出题不能为空")
							.attr("fv-datatype","Int16").attr("fv-datatype-msg","每日出题只能为数字")
							.attr("fv-msg-success","").attr("fv-msgpanel","paramvalue0");
			$("input[name='paramvalue']:eq(0)").parent().append('<span id="paramvalue0" class="validate-info"></span>');
			
			//每月达标
			$("input[name='paramvalue']:eq(1)").attr("fv-empty","false").attr("fv-empty-msg","每月达标不能为空")
							.attr("fv-datatype","Int16").attr("fv-datatype-msg","每月达标只能为数字")
							.attr("fv-msg-success","").attr("fv-msgpanel","paramvalue1");
			$("input[name='paramvalue']:eq(1)").parent().append('<span id="paramvalue1" class="validate-info"></span>');
			
			//题量
			$("input[name='ptrainPolicyBean.amount']").attr("fv-empty","false").attr("fv-empty-msg","题量不能为空")
							.attr("fv-datatype","Int16").attr("fv-datatype-msg","题量只能为数字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_amount");
			$("input[name='ptrainPolicyBean.amount']").parent().append('<span id="valid_amount" class="validate-info"></span>');
			
			//题分
			$("input[name='ptrainPolicyBean.score']").attr("fv-empty","false").attr("fv-empty-msg","题分不能为空")
							.attr("fv-datatype","Double").attr("fv-datatype-msg","题分只能为数字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_score");
			$("input[name='ptrainPolicyBean.score']").parent().append('<span id="valid_score" class="validate-info"></span>');
			
			//绑定form表单自定义验证
			$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		}
		
	</script>
</head>
<body>
<div class="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">抽题策略设置</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
	<form name="form1" action="" method="post" id="form1" enctype="multipart/form-data">
        <table class="editTable">
            <tbody>
                <tr>
					<td nowrap class="left">专业类别：</td>
			        <td class="right">
				        <c:set value="" var="typeidStr"></c:set>
				      	<select name="typeid" id="typeid">
				        	<option value="">全部</option>
					      	<c:forEach items="${specList}" var="s">
				              <option value="${s.id}" ${ptrainPolicyBean.typeid==s.id?'selected':''}>${s.codename}</option>
				              <c:set value="${typeidStr}${empty typeidStr?'':','}${s.id}" var="typeidStr"></c:set>
				            </c:forEach>
	                    </select>
					</td>
                 </tr>
                 <tr>
                     <td class="left">*&nbsp;每日出题：</td>
                     <td class="right">
                     	<c:set var="paramid" value="${PARAM_MRCT}_PARAMID"></c:set>
				      	<c:set var="paramvalue" value="${PARAM_MRCT}_PARAMVALUE"></c:set>
				      	<c:set var="remark" value="${PARAM_MRCT}_REMARK"></c:set>
				      	<input type="text" name="paramvalue" class="text_04" value="${paramMap[paramvalue]}"  maxlength="3">&nbsp;次
      	
				      	<input type="hidden" name="paramid" value="${paramMap[paramid]}">
				      	<input type="hidden" name="paramname" value="${PARAM_MRCT}">
				      	<input type="hidden" name="remark" value="${paramMap[remark]}${empty paramMap[remark]?'每日出题数量':''}">
      				</td>
  				</tr>
<%--                        <tr>--%>
<%--                            <td nowrap class="left">*&nbsp;每月达标：</td>--%>
<%--                            <td class="right">--%>
<%--                            	<c:set var="paramid" value="${PARAM_MYDB}_PARAMID"></c:set>--%>
<%--                            	<c:set var="paramvalue" value="${PARAM_MYDB}_PARAMVALUE"></c:set>--%>
<%--                            	<c:set var="remark" value="${PARAM_MYDB}_REMARK"></c:set>--%>
<%--                            	<input type="text" name="paramvalue" class="text_04" value="${paramMap[paramvalue]}"  maxlength="3" dataType="Number" msg="每月达标<fmt:message key="format.int" bundle="${bms}"/>">&nbsp;次--%>
<%--                            	--%>
<%--                            	<input type="hidden" name="paramid" value="${paramMap[paramid]}">--%>
<%--                            	<input type="hidden" name="paramname" value="${PARAM_MYDB}">--%>
<%--                            	<input type="hidden" name="remark" value="${paramMap[remark]}${empty paramMap[remark]?'每月达标次数':''}">--%>
<%--                            </td>--%>
<%--                        </tr>--%>
  				<tr>
					<td nowrap class="left">*&nbsp;题量题分：</td>
			      	<td class="right"><span><input type="text" name="ptrainPolicyBean.amount" class="text_03" value="${ptrainPolicyBean.amount}" maxlength="3">&nbsp;题，</span><span><input type="text" name="ptrainPolicyBean.score" class="text_06" value="${ptrainPolicyBean.score}" maxlength="6">分/题，</span>共<span id="typenum">0</span>题
          <%--<table class="em_tab">
          	<c:forEach items="${typeList}" var="t" varStatus="st">
          		<c:if test="${st.index%2==0}">
          		<c:if test="${st.index!=0}"></tr></c:if><tr>
          		</c:if>
                  <td align="right">${t.codename}：</td>
                  <td>
         	<c:set var="policyid" value="${t.id}_POLICYID"></c:set>
         	<c:set var="amount" value="${t.id}_AMOUNT"></c:set>
         	<c:set var="score" value="${t.id}_SCORE"></c:set>
         	<c:set var="total" value="${t.id}_TOTAL"></c:set>
                      <input type="text" name="amount" class="text_03" value="${policyMap[amount]}" maxlength="3" dataType="Number" msg="题数<fmt:message key="format.int" bundle="${bms}"/>">题，
                      <input type="text" name="score" class="text_06" value="${policyMap[score]}" maxlength="6" dataType="Double" msg="题分<fmt:message key="format.int" bundle="${bms}"/>">分/题，
                      共<span id="typenum1">${policyMap[total]}${empty policyMap[total]?'0':''}</span>题
                      
      				<input type="hidden" name="policyid" value="${policyMap[policyid]}">
      				<input type="hidden" name="typeid" value="${t.id}">
                  </td>
          	</c:forEach>
          	<c:if test="${fn:length(typeList)%2==0}"></tr></c:if> 刚好 
          	<c:if test="${fn:length(typeList)%2==1}"><td align="right"></td><td></td></tr></c:if> 多一列 
          </table>--%>
			      </td>
			  </tr>
			</tbody>
		    <!--表格合计或操作部分-->
            <tfoot>
            	<tr style="display:none;">
	                <td colspan="2">
						<span id="formerror"></span>
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2" align="center">
                        <input type="button" name="btn_sure" class="btn btn-info" value="确定" ${empty specList?'disabled':'' }>
                        <input type="reset" name="btn_reset" class="btn btn-disabled" value="重置">
                        <input type="hidden" name="tokenid" value="${tokenid}">
                        <input type="hidden" name="querymap.unitid" value="${querymap.unitid}">
                        <input type="hidden" name="ptrainPolicyBean.unitid" value="${querymap.unitid}">
                        <input type="hidden" name="ptrainPolicyBean.id" id="id" value="${ptrainPolicyBean.id}">
                        <input type="hidden" name="ptrainPolicyBean.typeid" id="b_typeid" value="${ptrainPolicyBean.typeid}">
                        <input type="hidden" name="typeidStr" id="typeidStr" value="${typeidStr}">
                        <input type="hidden" name="querymap.alltypeid" id="alltypeid">
                    </td>
	            </tr>
            </tfoot>
        </table>
   </form>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
</body>
</html>