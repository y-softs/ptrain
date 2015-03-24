<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Main.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/My97DatePicker/WdatePicker.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
	<script language="javascript">
		//提交查询数据列表
		function querydata(){
			document.form2.action="<c:url value='/pt/ask/listptrainaskquery.shtml'/>";
			document.form2.submit();
		}
		//提交查询数据列表
		function setDataWin(id){
			var param ="?querymap.unitid=${querymap.unitid}&querymap.askitemid="+id; 			
			$.layer({
					type: 2,
					title: '题目',
					maxmin: false,
					shadeClose: false, //开启点击遮罩关闭层
					offset: ['30px',''],
					area : ['650px' , '400px'],
					iframe: {src: "<c:url value='/pt/ask/setptrainaskitemwin.shtml'/>"+param}
			});
		}
	</script>
<body>
<form action="" name="form2" method="post">
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
                    <li><a href="<c:url value='/pt/ask/listptrainaskquery.shtml'/>"><label class="selected"></label>个人查询</a></li>
                    <li><a href="<c:url value='/pt/ask/listptrainaskskim.shtml'/>"><label></label>试题浏览</a></li>
                </ul>
            </div>
            <div class="list">            	
            	<div class="shop">
                		<div style=" float:left; margin-right:5px;">&nbsp;查询日期：<input type="text" name="querymap.datetime" value="${querymap.datetime }" class="Wdate" onClick="WdatePicker()"/>
                        </div>
                        <a href="###" class="btn btn-primary" style="float:left;" onclick="querydata();">查询</a>
                        <div style="float:right;">
                        	<span class="fontcolor_b">月份累计答题次数：<c:choose><c:when test="${empty ptrainAskBean.asktotal}">0</c:when><c:otherwise>${ptrainAskBean.asktotal}</c:otherwise></c:choose>次，月份累计答题得分：<c:choose><c:when test="${empty ptrainAskBean.score}">0</c:when><c:otherwise>${ptrainAskBean.score}</c:otherwise></c:choose>分</span>
                             <input type="hidden" name="querymap.postid" value="${querymap.postid}" />
                             <input type="hidden" name="querymap.chkDay" value="${querymap.chkDay}" id="chkDay" />
                        </div>
                </div>
                <div class="list-con">
                    <table class="list-tab" style=" border-left:none; border-right:none;">
                        <thead>
                            <tr>
                                <th width="5%">序号</th>
                                <th width="8%">题型</th>
                                <th>题目</th>
                                <th width="10%">答题答案</th>
                                <th width="10%">正确答案</th>
                                <th width="10%">答题得分</th>
                            </tr>
                        </thead>
                        <tbody>
                        	 <c:forEach items="${dataList}" var="d" varStatus="st">
                            <tr>
			                    <td align="center">${st.count}</td>
			                    <td align="center">${fn:replace(d.typeid,'题','')}</td>
                    			<td align="left">                    				
                    				<a href="JavaScript:setDataWin('${d.id}');" class="link" title="${d.topic }">
                    				<c:choose>
                                		<c:when test="${fn:length(d.topic)>35 }">
                                			${fn:substring(d.topic,0,35) }...
                                		</c:when>
                                		<c:otherwise>${d.topic}</c:otherwise>
                                	</c:choose>
                    				</a>                    				
                    			</td>
                                <td align="center">
			                    <c:choose>
			                    <c:when test="${'0'==d.ansright}">
			                    <span class="fontcolor_red">
			                        <c:choose>
			                        <c:when test="${'1'==d.ansdetail}">正确</c:when>
			                        <c:when test="${'0'==d.ansdetail}">错误</c:when>
			                        <c:otherwise>${d.ansdetail}</c:otherwise>
			                        </c:choose>
			                   </span>
			                    </c:when>
			                    <c:otherwise>
			                        <c:choose>
			                        <c:when test="${'1'==d.ansdetail}">正确</c:when>
			                        <c:when test="${'0'==d.ansdetail}">错误</c:when>
			                        <c:otherwise>${d.ansdetail}</c:otherwise>
			                        </c:choose>
			                    </c:otherwise>
			                    </c:choose>
			                    </td>
			                    <td align="center">
			                        <c:choose>
			                        <c:when test="${'1'==d.answer1}">正确</c:when>
			                        <c:when test="${'0'==d.answer1}">错误</c:when>
			                        <c:otherwise>${d.answer1}</c:otherwise>
			                        </c:choose>
			                    </td>
			                    <td align="center">${d.ansscore}</td>
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
            </div>  
        </div>              
    </div>
	<%@ include file="../Footer.jsp" %>
</div>
 </form>
</body>
</html>