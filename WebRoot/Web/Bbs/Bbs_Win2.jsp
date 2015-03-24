<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/columns.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#btn_sure").bind("click",function(){
    			var kind = $("input[name='kind']:checked").val();
				if(kind==undefined){
					parent.layer.msg('<div style="color:red;font-size:13px;">请选择转帖类别！</div>' ,2,3); 
					return;
				}
				$("#kind").val($("input[name='kind']:checked").val());
				$("#from2").attr("action",'<c:url value='/pt/bbs/savetrainbbsreprint.shtml'/>').trigger("submit");
			});
		});
    </script>
<body>
<form action="" id="from2" name="from2" method="post">
<div style="margin:10px 0 0 10px; font-size:13px;">
	<input type="radio" name="kind" value="1" ${querymap.kind=='1'?'disabled':'' }/>知识问答 
   	<input type="radio" name="kind" value="0" ${querymap.kind=='0'?'disabled':'' }/>资源共享
   	<input type="radio" name="kind" value="2" ${querymap.kind=='2'?'disabled':'' }/>规章制度
</div>
<div style="width:100px; margin:10px auto 0 auto;">
    <a href="###" class="btn btn-primary" id="btn_sure">确定</a>
    <a href="###" class="btn btn-inverse" id="colse"  onclick="layerClose();">关闭</a>
    <input type="hidden" name="tokenid" value="${tokenid}" />
    <input type="hidden" name="ptrainBbsBean.id" 		value="${ptrainBbsBean.id }" />
    <input type="hidden" name="ptrainBbsBean.kind" 		id="kind" />
    <input type="hidden" name="setpage" 				value="${setpage}" />
</div>
</form>
</body>
</html>