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
		});
		
		function callBack(){
			$("#form1").attr("action",'<c:url value='/Ptrain/Postuser/saveptrainpostuser.shtml' />').trigger("submit");
			if($("#formerror").text() == "ok"){
				return true;
			}
			return false;
		}
		
		function bindValid(){
			//绑定类别
			$("#specid").attr("fv-empty","false").attr("fv-empty-msg","专业类别不能为空")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_specid");
			$("#specid").parent().append('<span id="valid_specid" class="validate-info"></span>');
			
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
                    <td nowrap class="left">*&nbsp;专业类别：</td>
                    <!--右侧表格项-->
                    <td colspan="3" class="right">
                    	<select name="ptrainPostuserBean.postid" id="specid">
                    		<option value="">请选择</option>
                            <c:forEach items="${specList}" var="p">
                    			<option value="${p.id}" <c:if test="${p.id==ptrainPostuserBean.postid}">selected</c:if>>${p.codename}</option>
                    		</c:forEach>
                        </select>
                    </td>
                </tr>
	            <!-- 以下为隐藏域 -->
	            <tr style="display:none;">
	                <td colspan="2">
					    <input type="hidden" name="querymap.unitid" value="${querymap.unitid}">
                        <input type="hidden" name="querymap.postid" value="${querymap.postid}">
                        <input type="hidden" name="querymap.firstpostid" value="${querymap.firstpostid}">
                        <input type="hidden" name="querymap.secpostid" value="${querymap.secpostid}">
                        <input type="hidden" name="querymap.threepostid" value="${querymap.threepostid}">
                        <input type="hidden" name="ptrainPostuserBean.id" value="${ptrainPostuserBean.id}">
                        <input type="hidden" name="ptrainPostuserBean.userid" value="${ptrainPostuserBean.userid}">
                        <input type="hidden" name="ptrainPostuserBean.unitid" value="${querymap.unitid}">	
						<span id="formerror"></span>
                    </td>
	            </tr>
		    </tbody>
        </table>
   </form>
</div>
</body>
</html>