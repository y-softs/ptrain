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
		   var leftH = parseInt($(".list").height());
		   var rigthH = parseInt($(".ulsidebar").height());
		   if(rigthH>leftH){		   		
		   		$(".list").height(leftH+(${fn:length(codeList) }-15)*35);
		   }
			
		});
		//提交查询数据列表
		function querydata(){
			document.form2.action="<c:url value='/pt/con/listcontentrules.shtml'/>";
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
                <span><img src="<c:url value='/Web/Image/menu-gcwj.png'/>" /></span>
                <ul class="ulsidebar">
                	<c:choose>
                		<c:when test="${not empty keyword}"><li><a href="JavaScript:;"><label class="selected"></label>查询结果</a></li></c:when>
                		<c:otherwise>
	                	<c:forEach items="${codeList}" var="d">                	
	                    	<li><a href="<c:url value='/pt/con/listcontentrules.shtml?typeid=${d.id }'/>"><label class="${d.id==typeid?'selected':'' }"></label>${d.codename }</a></li>
	                	</c:forEach>                		
                		</c:otherwise>
                	</c:choose>
                </ul>
            </div>
            <div class="list">
            	<div class="shop">            		
                    <div class="search">
                        <div class="search_keyword">关键字：</div>
                         <div class="search_text">
	                          <input type="text" name="keyword" class="keyword" value="${keyword }" /><a href="###" class="search-a" onclick="querydata();">&nbsp;</a>
			                  <input type="hidden" name="KIND" 			value="${KIND}" />
			                  <input type="hidden" name="tagpage" 		value="1" />
			                  <input type="hidden" name="record" 		value="${record}" />
			                  <input type="hidden" name="fatherid" 		value="${fatherid}" />
                         </div>
                    </div>                    
                </div>
                <div class="list-con" style="clear:both;">
                	<table class="list-tab" style="border-left:none; border-right:none;">
                    	<thead>
                        	<tr>
                                <th>&nbsp;</th>
                                <th width="20%">&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${dataList}" var="d" varStatus="order">
                       	<tr>                       		
                             <td align="left" style="padding-left:10px;">
                             	<a href="<c:url value='/pt/con/rulesview.shtml'/>?pkId=${d.id }&fatherid=${fatherid }&typeid=${typeid }&KIND=${KIND }" class="title" target="_blank" title="${d.title }">
	                             	<c:choose>
	                           		<c:when test="${fn:length(d.title)>40 }">
	                           			${fn:substring(d.title,0,40) }...
	                           		</c:when>
	                           		<c:otherwise>${d.title}</c:otherwise>
	                           	</c:choose>
                             	</a>
                             	</td>
                             <td align="center">${d.estatime }</td>
                        </tr>
                        </c:forEach>
                       	<c:if test="${empty dataList}">
				        <tr class="list_tr1" align="center">
				        	<td style="color:red;" colspan="12">暂无数据</td>
				        </tr>
				        </c:if> 
                        </tbody>
                    </table>
                    <div class="page_bar">
                    	<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listcontentrules.shtml'}"/></ul>
                    </div>
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