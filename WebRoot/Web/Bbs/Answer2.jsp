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
    			var evasign = $("input[name='review']:checked").val();
				if(evasign==undefined){
					parent.layer.msg('<div style="color:red;font-size:13px;">请为专家的解答进行评价！</div>' ,2,3); 
					return;
				}
				$("#evasign").val($("input[name='review']:checked").val());
				$("#from2").attr("action","<c:url value='/pt/bbs/savetrainbbsbyevasign.shtml'/>").trigger("submit");
			});
		});
    </script>
    <style type="text/css"> 
		.user-time{ color:#666; font-size:12px; display:block;}
	</style>	
<body>
<div class="set_tab">
<form action="" id="from2" name="from2" method="post">
	<table>
    	<tr>
        	<td class="left">专业类别：</td>
        	<td class="right" style="padding-left:10px;"><span class="fontcolor_b">${ptrainBbsBean.strflag }</span></td>
        </tr>
    	<tr>
        	<td class="left">主帖：</td>
        	<td class="right" style="padding-left:10px;">${ptrainBbsBean.title }</td>
        </tr>
    	<tr>
        	<td class="left">内容：</td>
        	<td class="right" style="padding-left:10px;">${ptrainBbsBean.content }</td>
        </tr>
    	<tr>
            <td class="left">专家回答：</td>
            <td class="right" style="padding-left:10px;">
            	<span class="user-time">[&nbsp;${ptrainBbsBean.mainuser }&nbsp;&nbsp;${ptrainBbsBean.maintime }&nbsp;]</span>
               	${ptrainBbsBean.expans }
            </td>
        </tr>
        <tr>
           <td nowrap class="left">评价：</td>
           <td class="right" style="padding-left:10px;">
                <input type="radio" name="review" value="1"/>满意&nbsp;
                <input type="radio" name="review" value="0" />不满意
           </td>
       </tr>
    	<tr>
        	<td>&nbsp;</td>
        	<td class="right">
            	<a href="###" class="btn btn-primary" id="btn_sure">确定</a>
                <a href="###" class="btn btn-inverse" onclick="layerClose();">关闭</a>                
                 <input type="hidden" name="tokenid" value="${tokenid}" />
                 <input type="hidden" name="ptrainBbsBean.evasign" 	id="evasign" />
                 <input type="hidden" name="ptrainBbsBean.id" 		value="${ptrainBbsBean.id }" />
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>