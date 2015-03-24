<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/columns.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
	<script language="javascript">
	</script>	
    <style type="text/css">
    	body {
		width:100% !important;width:97%;
		}
    </style>
<body>
<div class="set_tab">
<form action="" name="from2" method="post">
	<table>
    	<tr>
        	<td class="left">题目：</td>
        	<td style="padding-left:5px;">${ptrainQuestionsBean.topic}</td>
        </tr>
        <c:choose>
             <c:when test="${singleSel==ptrainQuestionsBean.typeid||multSel==ptrainQuestionsBean.typeid}">
             	<tr>
		        	<td class="left">选项：</td>
		        	<td style="padding-left:5px;">
		            	 <c:if test="${not empty ptrainQuestionsBean.option1}">
		            	 	<c:choose>
                        		<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'A')}"><span class="fontcolor_b">A、${ptrainQuestionsBean.option1}</span></c:when>
	                            <c:otherwise>A、${ptrainQuestionsBean.option1}</c:otherwise>
	                      	</c:choose><br/>
	                      </c:if>
	                      <c:if test="${not empty ptrainQuestionsBean.option2}">
		            	 	<c:choose>
                        		<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'B')}"><span class="fontcolor_b">B、${ptrainQuestionsBean.option2}</span></c:when>
	                            <c:otherwise>B、${ptrainQuestionsBean.option2}</c:otherwise>
	                      	</c:choose><br/>
	                      </c:if>
	                      <c:if test="${not empty ptrainQuestionsBean.option3}">
		            	 	<c:choose>
                        		<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'C')}"><span class="fontcolor_b">C、${ptrainQuestionsBean.option3}</span></c:when>
	                            <c:otherwise>C、${ptrainQuestionsBean.option3}</c:otherwise>
	                      	</c:choose><br/>
	                      </c:if>
	                      <c:if test="${not empty ptrainQuestionsBean.option4}">
		            	 	<c:choose>
                        		<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'D')}"><span class="fontcolor_b">D、${ptrainQuestionsBean.option4}</span></c:when>
	                            <c:otherwise>D、${ptrainQuestionsBean.option4}</c:otherwise>
	                      	</c:choose><br/>
	                      </c:if>
	                      <c:if test="${not empty ptrainQuestionsBean.option5}">
		            	 	<c:choose>
                        		<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'E')}"><span class="fontcolor_b">E、${ptrainQuestionsBean.option5}</span></c:when>
	                            <c:otherwise>E、${ptrainQuestionsBean.option5}</c:otherwise>
	                      	</c:choose><br/>
	                      </c:if>
	                      <c:if test="${not empty ptrainQuestionsBean.option6}">
		            	 	<c:choose>
                        		<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'F')}"><span class="fontcolor_b">F、${ptrainQuestionsBean.option6}</span></c:when>
	                            <c:otherwise>F、${ptrainQuestionsBean.option6}</c:otherwise>
	                      	</c:choose><br/>
	                      </c:if>
	                      <c:if test="${not empty ptrainQuestionsBean.option7}">
		            	 	<c:choose>
                        		<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'G')}"><span class="fontcolor_b">G、${ptrainQuestionsBean.option7}</span></c:when>
	                            <c:otherwise>G、${ptrainQuestionsBean.option7}</c:otherwise>
	                      	</c:choose><br/>
	                      </c:if>
		            </td>
		        </tr>
		        <tr>
                   <td class="left">答案：</td>
                   <td style="padding-left:5px;">${ptrainQuestionsBean.answer1 }</td>
               </tr>
             </c:when>
              <c:when test="${judgeSel==ptrainQuestionsBean.typeid}">
               <tr>
                   <td class="left">答案：</td>
                   <td style="padding-left:5px;"><span class="fontcolor_b">${'1'==ptrainQuestionsBean.answer1?'正确':''}${'0'==ptrainQuestionsBean.answer1?'错误':''}</span></td>
               </tr>
              </c:when>
        </c:choose>
    	<tr>
        	<td class="left">备注：</td>
        	<td style="padding-left:5px;">${ptrainQuestionsBean.remark}</td>
        </tr>
    </table>
</form>
<div style="width:100px; margin:10px auto 0 auto;">
    <a href="###" class="btn btn-inverse" onclick="layerClose();">关闭</a>
	<input type="hidden" name="ptrainQuestionsBean.id" 		value="${ptrainQuestionsBean.id}" />
</div>
</div>
</body>
</html>