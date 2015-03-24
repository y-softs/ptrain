<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="nsoft" uri="/WEB-INF/nsoft.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Main.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
	<script language="javascript">
		$(function(){
			$("#demo li").mouseover(function(){$("span",$(this)).css("display","block");return false;})
		              .mouseout(function(){$("span",$(this)).css("display","none");return false;}) ;
<%--			 var oFlashMovie = document.getElementById("index");  	--%>
<%--             $("#time").html(oFlashMovie.TotalFrames);--%>
<%--             var tooal = oFlashMovie.TotalFrames-2;--%>
<%--             var i=0;--%>
<%-- 			 setInterval(function(){--%>
<%-- 			 	i++;--%>
<%-- 			 	 $("#time2").html(oFlashMovie.CurrentFrame()-1+"===="+tooal);--%>
<%-- 			 },1000);--%>
<%----%>
		});
		 function startMovie() {
		 	<c:if test="${TYPE=='2'}">
             var oFlashMovie = document.getElementById("index");
             oFlashMovie.Play();
             </c:if>
             //oFlashMovie.Zoom(85);
             //oFlashMovie.GotoFrame(1111);
             
         }
          //下载文件 
		function downLoad(savename,savepath){
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
	</script>
	<style type="text/css">
		a.current{font-weight:bold;color:red;}
	</style>
<body onLoad="startMovie();">
<form name="form2" id="form2" action="" method="post">
<div id="container">
	<!--头部-->
	<div class="header">
    	<div class="logo"><img src="<c:url value='/Web/Image/logo.png'/>" /></div>
        <div class="logo-title"><img src="<c:url value='/Web/Image/log-title.png'/>" /></div>
    </div>
	<!--main内容-->
    <div class="main">
    	<%@ include file="../NavMenu.jsp" %>
        <div class="content">
        	<div class="leftsidebar">
                <span class="navtop-sidebar">
                	<a href="<c:url value='/pt/cour/listcours.shtml'/>?typeid=${typeid}" class="navtop-return">&nbsp;</a>
                  	<input type="hidden" name="pkId" 		value="${pkId}" />
                  	<input type="hidden" name="typeid" 		value="${typeid}" />
               	</span>
                <ul class="directory">
                	<c:set var="_title"	value=""/>
                	<c:forEach items="${dataList}" var="d">
                	<c:choose>
                   		<c:when test="${fn:length(d.title)>10 }">
                   			<c:set var="_title"	value="${fn:substring(d.title,0,10) }"/>                   			
                   		</c:when>
                   		<c:otherwise><c:set var="_title"	value="${d.title}"/>   </c:otherwise>
                   	</c:choose>
               		<c:choose>
               			<c:when test="${d.intflag=='1' && d.endsign=='1'}"><li><label class="icons-tree"></label><a href="<c:url value='/pt/cour/listchilcours.shtml'/>?mlId=${d.id}&pkId=${pkId }" class="${mlId==d.id?'current':'' }" title="${d.title }">${_title }</a></li></c:when>
               			<c:when test="${d.intflag=='1' && d.endsign=='0'}"><li><label class="icons-tree-father"></label>${d.title }</li></c:when>                			
               			<c:when test="${d.intflag=='2' && d.endsign=='1'}"><li style="padding-left:15px;"><label class="icons-tree"></label><a href="<c:url value='/pt/cour/listchilcours.shtml'/>?mlId=${d.id}&pkId=${pkId }" class="${mlId==d.id?'current':'' }" title="${d.title }">${_title }</a></li></c:when>
               			<c:when test="${d.intflag=='2' && d.endsign=='0'}"><li style="padding-left:15px;"><label class="icons-tree-father"></label>${d.title }</li></c:when>
               			<c:when test="${d.intflag=='3'}"><li style="padding-left:30px;"><label class="icons-tree"></label><a href="<c:url value='/pt/cour/listchilcours.shtml'/>?mlId=${d.id}&pkId=${pkId }" class="${mlId==d.id?'current':'' }" title="${d.title }">${_title }</a></li></c:when>
               		</c:choose>
                	</c:forEach>
                </ul>
            </div>
            <div class="list">
                <div class="list-con" style="clear:both;">                	
                		<div class="video-title">
                		<c:choose>
                			<c:when test="${not empty coursBean.strflag}"><span class="video-ml">${coursBean.title }</span><span class="video-kc">${title}</span></c:when>
                			<c:otherwise><div style="text-align:center;">${title}</div></c:otherwise>
                		</c:choose>                		
                		</div>
	                    <div class="video-content">
	                    	<c:set var="path" value="" />
		                    <c:choose>
		                    	<c:when test="${not empty coursBean.strflag}">
		                    		<c:set var="path" value="${coursBean.strflag}" />
		                    	</c:when>
		                    	<c:otherwise>
		                    		<c:set var="path" value="${coursBean.intflag}" />
		                    	</c:otherwise>
		                    </c:choose>
	                    	<c:choose>
	                    		<c:when test="${TYPE=='1'}">
	                    			<div id="video" style="width:800px;height:600px;margin: 0 auto;">
									   	<div id="a1" style="display:block;"></div>
									</div>
				                    <script type="text/javascript" src="<c:url value='/ckplayer/ckplayer.js'/>" charset="utf-8"></script>				                    
									<script type="text/javascript">			
										var flashvars={
											f:'<c:url value='${path }'/>',
											c:0, //不读取xml配置
											p:0, //加载为播放
											e:2
											//, //视频播放完之后暂停
											//k:'60',//提示点时间，如 30|60鼠标经过进度栏30秒，60秒会提示n指定的相应的文字
											//n:'第一个提示'//提示点文字，跟k配合使用，如 提示点1|提示点2
										};
										var params={bgcolor:'#000',allowFullScreen:true,allowScriptAccess:'always'};//这里定义播放器的其它参数如背景色（跟flashvars中的b不同），是否支持全屏，是否支持交互
										var attributes={id:'ckplayer_a1',name:'ckplayer_a1',menu:'false'};
										//下面一行是调用播放器了，括号里的参数含义：（播放器文件，要显示在的div容器，宽，高，需要flash的版本，当用户没有该版本的提示，加载初始化参数，加载设置参数如背景，加载attributes参数，主要用来设置播放器的id）
										swfobject.embedSWF('<c:url value="/ckplayer/ckplayer.swf"/>', 'a1', '800', '600', '10.0.0','<c:url value="/ckplayer/expressInstall.swf"/>', flashvars, params, attributes);
										function ckplayer_status(str){
											var ckObj=swfobject.getObjectById('ckplayer_a1').ckplayer_getstatus();
											//swfobject.getObjectById('ckplayer_a1').ckplayer_wandh(120,110);//屏幕比例
										}
									</script>
									<div id="demo">
	                    				<ul class="filelist">
	                    					<c:choose>
	                    						<c:when test="${not empty fileList}">
		                    						<c:forEach items="${fileList}" var="d">
										        	<li style="text-align: left;"><a href="JavaScript:downLoad('${d.savename}','${d.savepath}');">${d.filename }<span class="download">点击下载</span></a></li>
										    		</c:forEach>
	                    						</c:when>
	                    						<c:otherwise>
	                    							<c:if test="${not empty coursBean.filename}">
	                    							<li style="text-align: left;"><a href="JavaScript:downLoad('${coursBean.filename}','${coursBean.savepath}');">${coursBean.filename }<span class="download">点击下载</span></a></li>
	                    							</c:if>
	                    						</c:otherwise>
	                    					</c:choose>                        	
				                           
				                        </ul>
				                    </div>
	                    		</c:when>
	                    		<c:when test="${TYPE=='2'}">
	                    			<embed src='<c:url value='${path }'/>' width="780"  height="460" play="true" id='index' name='index'></embed>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div id="demo">
	                    				<ul class="filelist"> 
	                    				<c:choose>
                    						<c:when test="${not empty fileList}">			                    				                       	
					                           <c:forEach items="${fileList}" var="d">
									        	<li style="text-align: left;"><a href="JavaScript:downLoad('${d.savename}','${d.savepath}');">${d.filename }<span class="download">点击下载</span></a></li>
									    		</c:forEach>						                        
						                    </c:when>
						                    <c:otherwise>
						                    	<c:if test="${not empty coursBean.filename}">
                    							<li style="text-align: left;"><a href="JavaScript:downLoad('${coursBean.filename}','${coursBean.savepath}');">${coursBean.filename }<span class="download">点击下载</span></a></li>
                    							</c:if>
                    						</c:otherwise>
							            </c:choose>
							            </ul>
				                    </div>
	                    		</c:otherwise>
	                    	</c:choose>
	                    </div>
	                </div>
                </div>
            </div>  
       </div>  
	<%@ include file="../Footer.jsp" %>
</div>
</form>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>