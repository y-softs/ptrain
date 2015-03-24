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
			$(".ul-list li").hover(function(){
				$("label",$(this)).css("background","url(<c:url value='/Web/Image/list-ul-line-hover.png'/>) repeat-x bottom");
			},function(){			
				$("label",$(this)).css("background","url(<c:url value='/Web/Image/list-ul-line.png'/>) repeat-x bottom");
			});			
		});
		//提交查询数据列表
		function querydata(){
			document.form2.action="<c:url value='/pt/cour/listcours.shtml'/>";
			document.form2.submit();
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
                <span><img src="<c:url value='/Web/Image/menu-kjk.png'/>" /></span>
                <ul class="ulsidebar">
                	<c:forEach items="${codeList}" var="d">                	
                    	<li><a href="<c:url value='/pt/cour/listcours.shtml?typeid=${d.id }'/>"><label class="${d.id==typeid?'selected':'' }"></label>${d.codename }</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="list">
            	<div class="shop">            		
                    <div class="search">
                        <div class="search_keyword">关键字：</div>
                         <div class="search_text">
	                          <input type="text" name="keyword" class="keyword" value="${keyword }" /><a href="###" class="search-a" onclick="querydata();">&nbsp;</a>
	                          <input type="hidden" name="fields" 		value="title" />    
			                  <input type="hidden" name="tagpage" 		value="1" />
			                  <input type="hidden" name="record" 		value="${record}" />
			                  <input type="hidden" name="fatherid" 		value="${fatherid}" />
                         </div>
                    </div>                    
                </div>
                <div class="list-con" style="clear:both;">                	
                	<div class="ul-list">
                    	<ul>
                    		<c:forEach items="${dataList}" var="d">
                        	<li>
                            	<a href="<c:url value='/pt/cour/listchilcours.shtml?pkId=${d.id }&typeid=${typeid }'/>" title="${d.title }">
                            	<c:choose>
                            		<c:when test="${not empty d.strflag}"><img src="<c:url value='${d.strflag }'/>" width="230" height="150" /></c:when>
                            		<c:otherwise><img src="<c:url value='/Web/Image/001.jpg'/>" width="230" height="150" /></c:otherwise>
                            	</c:choose>
                            	
                                <label>
                                	<c:choose>
                                		<c:when test="${fn:length(d.title)>=18 }">
                                			${fn:substring(d.title,0,18) }
                                		</c:when>
                                		<c:otherwise>${d.title}</c:otherwise>
                                	</c:choose><br/>
                                	<span></span><span class="hours"></span>
                                </label>
                                </a>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <c:if test="${not empty dataList}">
                    <div class="page_bar" style="clear:both;">
                    	<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listcours.shtml'}"/></ul>
                    </div>
                    </c:if>
                </div>
            </div>  
        </div>              
    </div>
	<%@ include file="../Footer.jsp" %>
</div>
</form>
<input type="hidden" name="typeid" 		value="${typeid}" />
</body>
</html>