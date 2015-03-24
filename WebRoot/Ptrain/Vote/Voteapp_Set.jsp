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
                <td class="left">*&nbsp;批次名称：</td>
                <!--右侧表格项-->
                <td class="right">
                	<input name="ptrainVoteappBean.appname" type="text" class="text_50" value="${ptrainVoteappBean.appname}"/>
                	<span id="valid_appname" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td class="left">默认当前投票批次：</td>
                <td class="right">
                	<input name="ptrainVoteappBean.defvote" type="checkbox" value="1" ${ptrainVoteappBean.defvote=='1'?'checked':''} />
                </td>
            </tr>
            <c:if test="${empty ptrainVoteappBean.id}">
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
                    <input type="hidden" name="ptrainVoteappBean.id" value="${ptrainVoteappBean.id}"/>
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
		//批次名称
		$("select[name='ptrainVoteappBean.appname']").attr("fv-empty","false").attr("fv-empty-msg","批次名称<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_appname");
		
		//绑定form表单自定义验证
		$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
	});
   function callBack(){
		$("#form1").attr("action",'<c:url value='/Ptrain/Voteapp/saveptrainvoteapp.shtml'/>').trigger("submit");
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