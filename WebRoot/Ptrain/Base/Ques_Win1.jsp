<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head> 
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
</head>
<body>
<div class="editContainer">
	<form name="form1" method="post" id="form1">
        <table class="editTable">
           <tr>
               <td nowrap class="left">题目：</td>
               <td class="right">${ptrainQuestionsBean.topic}</td>
           </tr>
           <c:choose>
           <c:when test="${singleSel==ptrainQuestionsBean.typeid||multSel==ptrainQuestionsBean.typeid}">
            <tr>
                <td nowrap class="left">选项：</td>
                <td class="right">
                     <table class="em_tab">
           				<c:if test="${not empty ptrainQuestionsBean.option1}">
                         <tr><td>
                         <c:choose>
           					<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'A')}">
                         	<span class="fontcolor_b">A、${ptrainQuestionsBean.option1}</span>
                         	</c:when>
                         	<c:otherwise>A、${ptrainQuestionsBean.option1}</c:otherwise>
                         </c:choose>
                         </td></tr>
                        </c:if>
           				<c:if test="${not empty ptrainQuestionsBean.option2}">
                         <tr><td>
                         <c:choose>
           					<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'B')}">
                         	<span class="fontcolor_b">B、${ptrainQuestionsBean.option2}</span>
                         	</c:when>
                         	<c:otherwise>B、${ptrainQuestionsBean.option2}</c:otherwise>
                         </c:choose>
                         </td></tr>
                        </c:if>
           				<c:if test="${not empty ptrainQuestionsBean.option3}">
                         <tr><td>
                         <c:choose>
           					<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'C')}">
                         	<span class="fontcolor_b">C、${ptrainQuestionsBean.option3}</span>
                         	</c:when>
                         	<c:otherwise>C、${ptrainQuestionsBean.option3}</c:otherwise>
                         </c:choose>
                         </td></tr>
                        </c:if>
           				<c:if test="${not empty ptrainQuestionsBean.option4}">
                         <tr><td>
                         <c:choose>
           					<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'D')}">
                         	<span class="fontcolor_b">D、${ptrainQuestionsBean.option4}</span>
                         	</c:when>
                         	<c:otherwise>D、${ptrainQuestionsBean.option4}</c:otherwise>
                         </c:choose>
                         </td></tr>
                        </c:if>
           				<c:if test="${not empty ptrainQuestionsBean.option5}">
                         <tr><td>
                         <c:choose>
           					<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'E')}">
                         	<span class="fontcolor_b">E、${ptrainQuestionsBean.option5}</span>
                         	</c:when>
                         	<c:otherwise>E、${ptrainQuestionsBean.option5}</c:otherwise>
                         </c:choose>
                         </td></tr>
                        </c:if>
           				<c:if test="${not empty ptrainQuestionsBean.option6}">
                         <tr><td>
                         <c:choose>
           					<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'F')}">
                         	<span class="fontcolor_b">F、${ptrainQuestionsBean.option6}</span>
                         	</c:when>
                         	<c:otherwise>F、${ptrainQuestionsBean.option6}</c:otherwise>
                         </c:choose>
                         </td></tr>
                        </c:if>
           				<c:if test="${not empty ptrainQuestionsBean.option7}">
                         <tr><td>
                         <c:choose>
           					<c:when test="${fn:contains(ptrainQuestionsBean.answer1, 'G')}">
                         	<span class="fontcolor_b">G、${ptrainQuestionsBean.option7}</span>
                         	</c:when>
                         	<c:otherwise>G、${ptrainQuestionsBean.option7}</c:otherwise>
                         </c:choose>
                         </td></tr>
                        </c:if>
                     </table>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">答案：</td>
                <td class="right"><span class="fontcolor_b">${ptrainQuestionsBean.answer1}</span></td>
            </tr>
           </c:when>
           <c:when test="${judgeSel==ptrainQuestionsBean.typeid}">
            <tr>
                <td nowrap class="left">答案：</td>
                <td class="right"><span class="fontcolor_b">${'1'==ptrainQuestionsBean.answer1?'正确':''}${'0'==ptrainQuestionsBean.answer1?'错误':''}</span></td>
            </tr>
           </c:when>
           </c:choose>
           <tr>
               <td nowrap class="left">备注：</td>
               <td class="right">${ptrainQuestionsBean.remark}&nbsp;</td>
           </tr>
           </tbody>
        </table>
   </form>
</div>
</body>
</html>