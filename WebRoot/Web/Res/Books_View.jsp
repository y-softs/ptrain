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
                <span><img src="<c:url value='/Web/Image/menu-jdsjk.png'/>" /></span>
                <ul class="ulsidebar">
                	<c:forEach items="${codeList}" var="d">                	
                    	<li><a href="<c:url value='/pt/con/listcontentbooks.shtml?typeid=${d.id }'/>"><label class="${d.id==typeid?'selected':'' }"></label>${d.codename }</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="list">
                <div class="list-con" style="clear:both;">
                	
                	<div class="view-title">${ptrainContentBean.title }</div>
                    <div class="view-content">
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