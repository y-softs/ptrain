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
	<script language="javascript">
		$(function(){			
			$("#demo li").mouseover(function(){$("span",$(this)).css("display","block");return false;})
		              .mouseout(function(){$("span",$(this)).css("display","none");return false;}) ;		    
		   var leftH = parseInt($(".list").height());
		   var rigthH = parseInt($(".ulsidebar").height());
		   if(rigthH>leftH){		   		
		   		$(".list").height(leftH+(${fn:length(codeList) }-15)*35);
		   }
		});
		
		 //下载文件 
		function downLoad(savename,savepath){
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
	</script>
<body>
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
                <span><img src="<c:url value='/Web/Image/menu-yyysk.png'/>" /></span>
                <ul class="ulsidebar">
                	<c:forEach items="${codeList}" var="d">                	
                    	<li><a href="<c:url value='/pt/con/listcontentmovies.shtml?typeid=${d.id }'/>"><label class="${d.id==typeid?'selected':'' }"></label>${d.codename }</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="list">
                <div class="list-con" style="clear:both;">
                	
                	<div class="view-title">${ptrainContentBean.title }</div>
                    <div class="view-content">
                    <c:if test="${not empty fileBean.savename}">
                    <div id="video" style="width:800px;height:600px;margin: 0 auto;">
					   	<div id="a1" style="display:block;"></div>
					</div>
                    <script type="text/javascript" src="<c:url value='/ckplayer/ckplayer.js'/>" charset="utf-8"></script>
					<script type="text/javascript">			
						var flashvars={
							f:'<c:url value='${fileBean.savepath }${fileBean.savename }'/>',
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
							//swfobject.getObjectById('ckplayer_a1').ckplayer_wandh(120,128);//屏幕比例
						}
					</script><br />
					</c:if>
                    ${ptrainContentBean.content }
                    <c:if test="${not empty fileList}">
                    <div id="demo">
                        <ul class="filelist">                        	
                           <c:forEach items="${fileList}" var="d">
				        	<li><a href="JavaScript:downLoad('${d.savename}','${d.savepath}');">${d.filename }<span class="download">点击下载</span></a></li>
				    		</c:forEach>
                        </ul>
                    </div>
                    </c:if>
                    </div>                    
                    <div style="width:30px; margin:10px auto 0 auto;">
                        <a href="###" class="btn btn-warning" onclick="JavaScript:window.close();">关闭</a>
			            <input type="hidden" name="fatherid" 		value="${fatherid}" />
			            <input type="hidden" name="KIND" 	value="${KIND}" />
			            <input type="hidden" name="pkId" 	value="${pkId}" />
			            <input type="hidden" name="typeid" 	value="${typeid}" />
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