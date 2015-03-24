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
    <link rel="stylesheet" href="<c:url value='/Web/Style/Main.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
	<script language="javascript">
		$(function(){
		});
		//提交查询数据列表
		function querydata(){
			document.form2.action="<c:url value='/pt/ask/listptrainaskskim.shtml'/>";
			document.form2.submit();
		}
		
		//导出excel
		function expExcel(){
			document.form2.action="<c:url value='/pt/ask/saveptrainaskskimexpexcel.shtml'/>";
			document.form2.submit();
		}
		//提交查询数据列表
		function setDataWin(id){		
			$.layer({
					type: 2,
					title: '题目',
					maxmin: false,
					shadeClose: false, //开启点击遮罩关闭层
					offset: ['30px',''],
					area : ['650px' , '400px'],
					iframe: {src: "<c:url value='/pt/ask/setptrainquestionswin.shtml'/>?ptrainQuestionsBean.id="+id}
			});
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
                <span><img src="<c:url value='/Web/Image/menu-mrsw.png'/>" /></span>
                <ul class="ulsidebar">
                    <li><a href="<c:url value='/pt/ask/listptrainask.shtml'/>"><label></label>三问答题</a></li>
                    <li><a href="<c:url value='/pt/ask/listptrainaskquery.shtml'/>"><label></label>个人查询</a></li>
                    <li><a href="<c:url value='/pt/ask/listptrainaskskim.shtml'/>"><label class="selected"></label>试题浏览</a></li>
                </ul>
            </div>
            <div class="list">           	         	
            	<div class="shop">
                		<div style=" float:left; margin-right:5px;">&nbsp;试题类型：<select name="querymap.typeid" onchange="querydata();">
                            		<option value="">全部</option>
                                    <c:forEach items="${typeListOperMap}" var="k">
                            			<option value="${k.key}" ${k.key==querymap.typeid?'selected':''}>${k.value}</option>
                            		</c:forEach>
                                </select>
                        </div>
                        <div class="search">
                            <div class="search_keyword">关键字：</div>
                             <div class="search_text">
	                          <input type="text" name="keyword" class="keyword" value="${keyword }" /><a href="###" class="search-a" onclick="querydata();">&nbsp;</a>
	                          <input type="hidden" name="fields" 		value="topic" />                                                          
			                  <input type="hidden" name="tagpage" 		value="1" />
			                  <input type="hidden" name="record" 		value="${record}" />
			                  <input type="hidden" name="querymap.unitid" 		value="${querymap.unitid}" />
                             </div>
                        </div>
                </div>
                <div class="list-con">
                    <table class="list-tab" style=" border-left:none; border-right:none;">
                        <thead>
                            <tr>
                                <th width="5%">序号</th>
                                <th width="8%">题型</th>
                                <th>题目</th>
                            </tr>
                        </thead>
                        <tbody>                        	
                       	<c:forEach items="${dataList}" var="d" varStatus="st">
                         <tr>
                             <td align="center">${st.count}</td>
                             <td align="center">${fn:replace(d.typeid,'题','')}</td>
                             <td align="left">
                             <a href="JavaScript:setDataWin('${d.id }');" class="link" title="${d.topic }">
                             	<c:choose>
                              		<c:when test="${fn:length(d.topic)>50 }">
                              			${fn:substring(d.topic,0,50) }...
                              		</c:when>
                              		<c:otherwise>${d.topic}</c:otherwise>
                              	</c:choose>
                             </a>
                             </td>
                         </tr>
                        </c:forEach>                                                      
                       	<c:if test="${empty dataList}">
				        <tr class="list_tr1" align="center">
				        	<td style="color:red;" colspan="12">暂无数据</td>
				        </tr>
				        </c:if> 
                        </tbody>
                    </table>
               </div>               
               <div class="page_bar">               		
               		<div>
                    	<a href="###" class="btn btn-success" style=" font-size:14px;" onclick="expExcel();">导出</a>
                    </div>	
               		<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listptrainaskskim.shtml'}"/></ul>
               </div>
            </div>  
        </div>              
    </div>
	<%@ include file="../Footer.jsp" %>
</div>
</form>
</body>
</html>