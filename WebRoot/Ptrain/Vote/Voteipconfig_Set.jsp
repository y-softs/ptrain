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
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.9.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/Script/plugin/valid/FormValidator.1.0.min.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			bindValid();
			$("#submitBtn").bind("click",callBack);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
		});
		
		function callBack(){
			$("#form1").attr("action",'<c:url value='/Ptrain/Voteipconfig/saveptrainvoteipconfig.shtml'/>').trigger("submit");
			if($("#formerror").text() == "ok"){
				return true;
			}
			return false;
		}
		
		function bindValid(){
			//批次
			$("#b_appid").attr("fv-empty","false").attr("fv-empty-msg","批次不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_appid");
			$("#b_appid").parent().append('<span id="valid_appid" class="validate-info"></span>');
			
			//开始IP段
			$("#b_startip").attr("fv-empty","false").attr("fv-empty-msg","开始IP段不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_startip");
			$("#b_startip").parent().append('<span id="valid_startip" class="validate-info"></span>');
			
			//结束IP段
			$("#b_endip").attr("fv-empty","false").attr("fv-empty-msg","结束IP段不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_endip");
			$("#b_endip").parent().append('<span id="valid_endip" class="validate-info"></span>');
			
			//绑定form表单自定义验证
			$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		}
	</script>
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
                <td class="left">批次：</td>
                <!--右侧表格项-->
                <td class="right">
                	<select name="ptrainVoteipconfigBean.appid" id="b_appid">
                		<option value="">请选择</option>
                		<c:forEach items="${appList}" var="d">
                			<option value="${d.id}" ${d.id==ptrainVoteipconfigBean.appid?'selected':''}>${d.appname}</option>
                		</c:forEach>
                	</select>
                </td>
            </tr>
            <tr>
                <!--左侧表格项-->
                <td class="left">开始IP段：</td>
                <!--右侧表格项-->
                <td class="right">
                	<input name="ptrainVoteipconfigBean.startip" type="text" id="b_startip" class="text_20" value="${ptrainVoteipconfigBean.startip}"/>
                </td>
            </tr>
            <tr>
                <td class="left">结束IP段：</td>
                <td class="right">
                	<input name="ptrainVoteipconfigBean.endip" type="text" id="b_endip" class="text_20" value="${ptrainVoteipconfigBean.endip}"/>
                </td>
            </tr>
            <c:if test="${empty ptrainVoteipconfigBean.id}">
            <tr>
                <td class="left">&nbsp;继续新增：</td>
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
                    <input type="hidden" name="querymap.appid" value="${querymap.appid}"/>
                    <input type="hidden" name="ptrainVoteipconfigBean.id" value="${ptrainVoteipconfigBean.id}"/>
                </td>
            </tr>
            <tr style="display:none;">
	                <td colspan="2">
						<span id="formerror"></span>
	                </td>
	            </tr>
            </tfoot>
        </table>
    </form>
</div>
</body>
</html>