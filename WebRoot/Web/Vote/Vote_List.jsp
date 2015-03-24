<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../Include/TagLib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <link rel="stylesheet" href="<c:url value='/Ptrain/Vote/Css/rest.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Ptrain/Vote/Css/styles.css'/>"/>
    <style type="text/css">
		.operimg{cursor: hand;}
	</style>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.9.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
</head>
<body>
	<table align="center" class="maxtable">
	<tr>
		<td><img src="<c:url value='/Ptrain/Vote/'/>image/Banner.png" class="Banner" /></td>
	</tr>
	<tr style="display:none;">
		<td>
			<input name="appId" id="appId" value="${appId}" type="hidden"/>
			<input name="okIdStr" id="okIdStr" value="${okIdStr}" type="hidden"/>
			批次：${appName}
		</td>
	</tr>
  	<tr>
    	<td>
   		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      	<tr>
	        	<td width="25" align="left" valign="top"><img src="<c:url value='/Ptrain/Vote/'/>image/Left.png" width="25" height="50" /></td>
	        	<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	        			<tr>
	        			<c:choose>
	        				<c:when test="${not empty voteSrcList}">
	        					<c:forEach items="${voteSrcList}" var="d" varStatus="st">
	                        	<td>
	            					<table width="100" border="0" cellspacing="0" cellpadding="0">
										<tr>
	                  						<td><img src="<c:url value='/Ptrain/Vote/'/>image/List_Top.jpg" width="308" height="4" /></td>
	                					</tr>
						                <tr>
											<td align="center" background="<c:url value='/Ptrain/Vote/'/>image/List_Back.jpg">
												<img src="<c:url value='/Ptrain/Vote/'/>${d.srcimg}" width="300" height="225" border="0" style="cursor:hand;" onclick="setVote('${d.id}','${d.srcsign}','${d.srcurl}');" />
											</td>
						                </tr>
						                <tr>
											<td align="center" height="3" background="<c:url value='/Ptrain/Vote/'/>image/List_Back.jpg">&nbsp;</td>
						                </tr>
	                					<tr>
	                  						<td align="center" background="<c:url value='/Ptrain/Vote/'/>image/List_Back.jpg">
		                  						<table width="95%" border="0" cellspacing="0" cellpadding="0">
		                      						<tr height="25">
		                        						<td colspan="3" align="left"><strong>课件名称：</strong>${d.srctitle}</td>
		                      						</tr>
		                      						<tr height="25">
		                        						<td colspan="3" align="left"><strong>作者：</strong>${d.author}</td>
		                      						</tr>
		                      						<tr>
		                        						<td width="60%" height="34" align="center" valign="middle" class="voteButton">
															<input type="hidden" class="srcId" value="${d.id}"/>
		                        							<img src="<c:url value='/Ptrain/Vote/'/>image/Btn.jpg" width="154" height="26" border="0"/>
		                        						</td>
								                        <td width="25%" valign="middle">当前票数：</td>
								                        <td width="28%" valign="middle">${d.votecount}</td>
		                      						</tr>
		                  						</table>
	                  						</td>
	                					</tr>
						                <tr>
											<td><img src="<c:url value='/Ptrain/Vote/'/>image/List_Buttom.jpg" width="308" height="1" /></td>
						                </tr>
	            					</table>
	            				</td>
	            				
	            				<c:if test="${st.last}">
	                        		<c:if test="${st.index%3==0}">
	                        			<td></td>
			            				<td></td>
	                        		</c:if>
	                        		<c:if test="${st.index%3==1}">
	                        			<td></td>
	                        		</c:if>
	                        	</c:if>
	                        	<c:if test="${!(st.last)}">
	                        		<c:if test="${(st.index%3==2)}"></tr><tr><td colspan="3">&nbsp;</td></tr><tr></c:if>
	                        	</c:if>
	                        </c:forEach>
	        				</c:when>
	        				<c:otherwise>
	        					<td colspan="3" align="center" ><strong>暂无优秀课件评选记录</strong></td>
	        				</c:otherwise>
	        			</c:choose>
          				</tr>
        			</table>
				</td>
        		<td width="25" align="right" valign="top"><img src="<c:url value='/Ptrain/Vote/'/>image/Right.png" width="25" height="50" /></td>
      		</tr>
      		<tr><td>&nbsp;</td></tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td height="50" align="center" background="<c:url value='/Ptrain/Vote/'/>image/Buttom.jpg"><span class="STYLE1">CopyRight @ by 国网福建省电力有限公司莆田供电公司  软件开发：龙呈软件</span></td>
  </tr>
</table>

<script language="javascript">
	$(function(){
		bindVoteEvent();
	});
	
	function bindVoteEvent(){
		var okIdStrV= $("#okIdStr").val();
		okIdStrV = okIdStrV || "";
		var appIdV = $("#appId").val();
		$(".voteButton").each(function(){
			var trobj = $(this).parent();
			var s = trobj.find(".srcId:eq(0)").val();
			var imgobj = trobj.find("img:eq(0)");
			if(okIdStrV.indexOf(","+s+",")>=0){
				imgobj.attr("src","<c:url value='/Ptrain/Vote/'/>image/Btn_On.jpg");
			}else{
				imgobj.addClass("operimg");
				imgobj.bind("click",function(){voteEvent(imgobj,appIdV,s);});
			}
		});
	}
	
	function voteEvent(eventJqObj,appIdV,srcId){
		layer.confirm('您最多可对10个课件进行投票，是否确定投票！', function(){
		    $.ajax({
				type: "POST", 
				url:"<c:url value='/Ptrain/Vote/savePtrainVoteByJq.shtml'/>", //代码数据
				data:{'srcId':srcId,'appId':appIdV,'timestmp':((new Date()).getTime())},
				async:false,
				dataType:"json",
				error:function(data){
				    parent.layer.msg('<div style="font-size:13px;">与数据库连接失败！</div>' ,2,1);
				},
				success:function(data) {
					if(data[0].result=='1'){
						eventJqObj.attr("src","<c:url value='/Ptrain/Vote/'/>image/Btn_On.jpg").unbind('click').removeClass("operimg");
						$("td:eq(2)",eventJqObj.parent().parent()).html(data[0].other);
					}
					parent.layer.msg('<div style="font-size:13px;">'+data[0].message+'</div>' ,2,1);
				}
			});
		});
	}
	
	function setVote(srcId,srcSign,srcUrl){
		var url = "<c:url value='/setptrainvote.shtml'/>"+"?srcId="+srcId;
    	//var width = 1080;
    	//var height = 680;
		//var screenleft    = (screen.availWidth-width)/2;
    	//var screenheight  = (screen.availHeight-height)/2;
		//loca = 'width='+width+',height='+height+',top='+screenheight+',left='+screenleft+',scrollbars=yes,resizable=yes,status=yes';
    	//var url = 'http://192.168.1.65:8888/ptrain/Ptrain/Vote/files/10kV电力电缆故障探测（黄汉权）.files/fullscreen.htm';
    	if(srcSign =='0'){
    		url = "<c:url value='/Ptrain/Vote/"+srcUrl+"'/>";
 		}
 		window.open(url);
 		//window.lcoation.href=url;
    	//window.lcoation.href=(url,"newWindow",loca);
	}
	
</script>
</body>
</html>