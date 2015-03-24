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
    <script type="text/javascript">
		$(function(){
		});
		//查询数据
		function querydata(){
		    document.form2.action = "<c:url value='/pt/bbs/listtrainbbs_rules.shtml'/>";
		    document.form2.submit();
		}
		//新增/修改 [跳转]
		function setBbs(id,fun){			
			var param = "?fun="+fun;
				param +="&ptrainBbsBean.id="+id;
				param += "&setpage=Bbs_Set3";
				param += "&KIND=${KIND}";
			$.layer({
				type: 2,
				title: '发帖',
				maxmin: false,
				shadeClose: false, //开启点击遮罩关闭层
				offset: ['150px',''],
				area : ['650px' , '400px'],
				iframe: {src: "<c:url value='/pt/bbs/settrainbbs.shtml'/>"+param}
			});
		}
		function setTopic(id){
			$.layer({
					type: 2,
					title: '帖子',
					maxmin: false,
					shadeClose: false, //开启点击遮罩关闭层
					offset: ['30px',''],
					area : ['830px' , '560px'],
					iframe: {src: "<c:url value='/pt/bbs/setbbstopic.shtml'/>?ptrainBbsBean.id="+id}
			});
		}		
		function setChild(id){
			$.layer({
					type: 2,
					title: '回复列表',
					maxmin: false,
					shadeClose: false, //开启点击遮罩关闭层
					offset: ['30px',''],
					area : ['800px' , '500px'],
					iframe: {src: "<c:url value='/pt/bbs/listtrainbbschild.shtml'/>?ptrainBbsBean.id="+id}
			});
		}
		//转帖
		function reprint(id){
			var param = "?ptrainBbsBean.id="+id;
				param +="&setpage=Bbs_Win2&querymap.kind=2";
			openWin("转帖","300px","120px","<c:url value='/pt/bbs/settrainbbswin.shtml'/>"+param);
		}
		//删除
		function delOper(id){
			layer.confirm('确定删除吗？',function(index){
				var param = "?ptrainBbsBean.id="+id;
					param += "&listpage=Bbs_List3";
				document.form2.action = "<c:url value='/pt/bbs/deletetrainbbsbyid.shtml'/>"+param;
			    document.form2.submit();
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
                <span><img src="<c:url value='/Web/Image/menu-jslt.png'/>" /></span>
                <ul class="ulsidebar">
                    <li><a href="<c:url value='/pt/bbs/listtrainbbs.shtml'/>"><label></label>知识问答</a></li>
                    <li><a href="<c:url value='/pt/bbs/listtrainbbs_share.shtml'/>"><label></label>资源共享</a></li>
                    <li><a href="<c:url value='/pt/bbs/listtrainbbs_rules.shtml'/>"><label class="selected"></label>规章制度</a></li>
                </ul>
            </div>
            <div class="list">
            	<div class="shop">
            		<div style="float:left;">
            		<c:if test="${not empty codeList }">
                	<a href="JavaScript:setBbs('','1');" class="apost">&nbsp;</a>
                	</c:if>专业类别：
	                    <select name="querymap.specid" onchange="querydata();">
                       	<option value="">全部</option>
                    	<c:forEach items="${codeList}" var="d">
               	 			<option value="${d.id}" ${d.id==querymap.specid?'selected':''}>${d.codename}</option>
               	 		</c:forEach>
                    </select>
                    </div>
                    <div class="search">
                        <div class="search_keyword">关键字：</div>
                         <div class="search_text">
	                          <input type="text" name="keyword" class="keyword" value="${keyword }" /><a href="###" class="search-a" onclick="querydata();">&nbsp;</a>
	                          <input type="hidden" name="fields" 		value="title" />                            
			                  <input type="hidden" name="nature" 		value="${nature}" />
			                  <input type="hidden" name="KIND" 		value="${KIND}" />
			                  <input type="hidden" name="tagpage" 	value="1" />
			                  <input type="hidden" name="record" 		value="${record}" />
                         </div>
                    </div>                    
                 </div>
                <div class="list-con">
                	<table class="list-tab">
                    	<thead>
                        	<tr>
                            	<th nowrap align="left">
                            	<input type="checkbox" name="querymap.showsign" value="0" ${querymap.showsign=='0'?'checked':'' } onclick="querydata();" /><em>我的帖子</em>
                               	</th>
                            	<th width="6%">回复</th>
                            	<th width="6%">浏览</th>
                            	<th width="4%">点赞</th>
                            	<th width="20%" nowrap>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${dataList}" var="d" varStatus="order">
                       	<tr>
                       		<c:set var="userid" value="${session.loginSession.id }" />
                           	<td align="left">
                           		<label>[${d.strflag }]</label>
                           		<a href="JavaScript:setTopic('${d.id }');" class="title" title="${d.title }">
                           		<c:choose>
                              		<c:when test="${fn:length(d.title)>25 }">
                              			${fn:substring(d.title,0,25) }...
                              		</c:when>
                              		<c:otherwise>${d.title}</c:otherwise>
                              	</c:choose>
                           		</a>
                           	</td>
                           	<td align="center">
                           	<c:choose>
			            		<c:when test="${fn:indexOf(d.management,userid)>=0 }"><a href="JavaScript:setChild('${d.id }');">${d.intflag }</a></c:when>
			            		<c:otherwise>${d.intflag }</c:otherwise>
			            	</c:choose>
                           	</td>
				            <td align="center">${d.browse}</td>
				            <td align="center">${d.nice}</td>
				            <td align="center">
			            	<c:choose>
			            		<c:when test="${d.estauser==session.loginSession.id }">
			            			<a href="JavaScript:setBbs('${d.id}','2');" class="btn btn-primary">修改</a><a href="JavaScript:delOper('${d.id}');" class="btn btn-danger">删除</a>
			            			<c:if test="${fn:indexOf(d.management,userid)>=0 }"><a href="JavaScript:reprint('${d.id }');" class="btn btn-inverse">转帖</a></c:if>
			            		</c:when>
			            		<c:when test="${fn:indexOf(d.management,userid)>=0 }">
			            		<a href="JavaScript:delOper('${d.id}');" class="btn btn-danger">删除</a><a href="JavaScript:reprint('${d.id }');" class="btn btn-inverse">转帖</a>
			            		</c:when>
			            	</c:choose>
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
                    <div class="page_bar">
                    	<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listtrainbbs_rules.shtml'}"/></ul>
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