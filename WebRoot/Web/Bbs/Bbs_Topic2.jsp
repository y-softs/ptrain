<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="nsoft" uri="/WEB-INF/nsoft.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/columns.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/bbs.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
	<script language="javascript" src="<c:url value='/Web/Script/page.js'/>"></script>
	<script language="javascript" src="<c:url value='/Web/Script/ajaxObj.js'/>"></script>
    <script type="text/javascript">
		$(function(){
			//加载分页
			$("#pageBar").page({pageSize:${ptrainBbsBean.intflag},limit:5,showPageNum:false});
			//发表评论
			$("#submit").ajaxObj({unitid:'${querymap.unitid}',
								  fatherid:'${ptrainBbsBean.id}',
								  content:$("#content"),
								  username:'${session.loginSession.username}',
								  url:'<c:url value="/pt/bbs/insertPtrainBbsByJq.shtml"/>'});
			//点赞
			$("#nice").ajaxJqNice({id:'${ptrainBbsBean.id}',url:'<c:url value="/pt/bbs/onClickNiceByJq.shtml"/>'});
		});
		function AjaxQjObj(page){
			$("#tbodyid").html("");
			var map = {'fatherid':'${ptrainBbsBean.id}',
					   'tagpage':page,
					   'timestmp':(new Date()).getTime()
					  };
			$.ajax({
				type: "post", 
				 url:'<c:url value="/pt/bbs/listTrainBbsChildByJq.shtml"/>',
				 data:map, 
				 async:true,
				 dataType:"json",
				 success:function(json){
				 	if(json==""){
						return;
					}
					for(i=0;i<json.length;i++){
						$("<tr><td valign=\"top\" class=\"left\" style=\"padding-right:5px;border-right:1px solid #c2d5e3;\">"+json[i].estauser+"<br/>"+json[i].estatime+"</td><td colspan=\"2\" style=\"padding-left:20px;\">"+json[i].content+"</td></tr>").appendTo("#tbodyid");
					}
				 }
			});
		}
		function DownCount(id){
			$.ajax({
				type: "post", 
				 url:'<c:url value="/pt/bbs/DownloadNumberByJq.shtml"/>',
				 data:{'querymap.fileid':id,'timestmp':(new Date()).getTime()}, 
				 async:true,
				 dataType:"json",
				 success:function(json){
				 	if(json=="-1"){
				 		alert("操作失败，与数据库交互是发生错误！");
					}else{
						$("#num_"+id).html(parseInt($("#num_"+id).text())+1);
					}
				 }
			});
		}		
		//模板下载 
		function downLoad(savename,savepath,id){
			DownCount(id)
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
		
    </script>    
    <style type="text/css">
    	body {
		width:100% !important;width:97%;
		}
    </style>
<body>
<div class="set_tab">
<form action="" name="from2" method="post">
	<table>
    	<tr>
        	<td  valign="top" class="left" style="padding-right:5px;border-right:1px solid #c2d5e3;">
            	${ptrainBbsBean.estauser}<br/>
                <span>${ptrainBbsBean.estatime }</span>
            </td>
        	<td style="padding-left:10px;">
            	<span class="fontcolor_b">${ptrainBbsBean.title }</span>
                <span class="fontcolor_gray" style="font-size:12px;">[&nbsp;浏览：${ptrainBbsBean.browse }&nbsp;|&nbsp;回复：${ptrainBbsBean.intflag }&nbsp;]</span><br/>
                ${ptrainBbsBean.content}<br/>
                <c:if test="${not empty fileList }">
                <div class="annex">
                	附件下载：<br/>
                 <c:forEach items="${fileList }" var="d">
                     <a href="JavaScript:;" onclick="JavaScript:downLoad('${d.savename}','${d.savepath }','${d.id }');">${d.filename }</a>&nbsp;(下载次数：<span id="num_${d.id }">
                     <c:choose>
                     	<c:when test="${not empty d.intflag}">${d.intflag}</c:when>
                     	<c:otherwise>0</c:otherwise>
                     </c:choose>
                     </span>)<br/>
				 </c:forEach>
                </div>
                </c:if>
                <div id="the-vote">
                    <div class="votecard">
                        <div>
                            <em><strong>${ptrainBbsBean.nicecount }</strong><span>Flips</span></em>
                        </div>
                    </div>
                    <a class="${ptrainBbsBean.nice=='0'?'voteaction':'disabled' }" id="nice" href="JavaScript:void(0);">Flippit</a>
                </div>	
                <c:if test="${ptrainBbsBean.anssign=='1' }">
                <div class="experts">
                	<div class="user">专家解答 <span class="user-time">[&nbsp;${ptrainBbsBean.mainuser}&nbsp;&nbsp;回答时间：${ptrainBbsBean.maintime}&nbsp;]</span>
                	<c:if test="${not empty ptrainBbsBean.evasign }"><span class="eva">评价：${ptrainBbsBean.evasign=='1'?'满意':'不满意' }</span></c:if>
                    </div>
					<div class="experts-content">${ptrainBbsBean.expans}</div>
                </div>
                </c:if>
            </td>
        </tr>
        <tbody id="tbodyid"></tbody>                       
        <tr>
             <td colspan="2" align="right" style="padding-right:20px;">                            
				<div id="pageBar"></div>
             </td>
        </tr>
    	<tr>
        	<td  valign="top" class="left" style="padding-right:5px;border-right:1px solid #c2d5e3;">
            	${opraName }<br/>
                <span>${opraTime }</span>
            </td>
        	<td style="padding-left:10px;">
            	<textarea name="explain" id="content" class="textarea_50_5"></textarea><br/>
                <a href="###" class="btn btn-primary" id="submit">回复</a>
                <a href="###" class="btn btn-inverse" id="colse" onclick="layerClose();">关闭</a>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>