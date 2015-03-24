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
	<table width="1004" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td><img src="<c:url value='/Ptrain/Vote/'/>image/Banner.png" width="1004" height="289" /></td>
	  	</tr>
	  	<tr>
	    	<td align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      			<tr>
	        			<td width="25" align="left" valign="top"><img src="<c:url value='/Ptrain/Vote/'/>image/Left.png" width="25" height="50" /></td>
	        			<td>
	        				<table width="100%" border="0" cellspacing="0" cellpadding="0">
	        					<tr style="display:none;">
						        	<td><img src="<c:url value='/Ptrain/Vote/'/>image/Page_Tag01.png" width="954" height="28" border="0" /></td>
						        </tr>
								<tr>
	            					<td height="500" align="right" valign="top" >
	            						<table width="930" border="0" cellspacing="0" cellpadding="0">
	            							<tr>
							                	<td>&nbsp;</td>
							                </tr>
	                						<tr>
	                  							<td height="36" align="center" bgcolor="dcf5a4"><strong>${srcBean.srctitle}（${srcBean.author}）</strong></td>
	               							</tr>
	               							<tr>
							                	<td>&nbsp;</td>
							                </tr>
							                <tr>
							                	<td align="center">
							                		<c:choose>
							                			<c:when test="${fn:indexOf(srcBean.srcurl,'.flv')>=0}">
								                			<script type="text/javascript">
																var swf_width=720
																var swf_height=540
																var texts='${srcBean.srctitle}'
																var files="<c:url value='/Ptrain/Vote/${srcBean.srcurl}'/>"
																document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
																document.write('<param name="movie" value="<c:url value='/Web/Vote/Play.swf'/>"><param name="quality" value="high">');
																document.write('<param name="menu" value="false"><param name=wmode value="opaque"><param name="salign" value="lt" /> <param name="scale" value="noscale" />');
																document.write('<param name="FlashVars" value="vcastr_file='+files+'&vcastr_title='+texts+'">');
																document.write('<embed src="<c:url value='/Web/Vote/Play.swf'/>" wmode="opaque" FlashVars="vcastr_file='+files+'&vcastr_title='+texts+'& menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />'); document.write('</object>'); 
															</script>
							                			</c:when>
							                			<c:when test="${fn:indexOf(srcBean.srcurl,'.swf')>=0}">
							                				<embed src="<c:url value='/Ptrain/Vote/${srcBean.srcurl}'/>" width="720"  height="480" play="true"></embed>
							                			</c:when>
							                		</c:choose>
							                	</td>
							                </tr>
	            						</table>
	           						</td>
	          					</tr>
				                <tr>
				                  <td align="center">&nbsp;</td>
				                </tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
	        				</table>
						</td>
	        			<td width="25" align="right" valign="top"><img src="<c:url value='/Ptrain/Vote/'/>image/Right.png" width="25" height="50" /></td>
	      			</tr>
	    		</table>
			</td>
		</tr>
	  	<tr>
			<td height="50" align="center" background="<c:url value='/Ptrain/Vote/'/>image/Buttom.jpg"><span class="STYLE2">CopyRight @ by 国网福建省电力有限公司莆田供电公司  软件开发：龙呈软件</span></td>
	  	</tr>
	</table>
</body>
</html>