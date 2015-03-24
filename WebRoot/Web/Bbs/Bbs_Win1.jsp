<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		function delOper(id){
			var  index = parent.layer.confirm('确定删除吗？',function(i){
				parent.layer.close(index);
				document.form2.action = "<c:url value='/pt/bbs/deletetrainbbschildbyid.shtml'/>?querymap.pkId="+id;
			    document.form2.submit();
		    });
		}
	</script>
	<style type="text/css">
		.list-tab{font-size:13px;}
	</style>
<body>
<form action="" id="form2" name="form2" method="post">
	<table class="list-tab" style=" border-left:none; border-right:none;">
        <thead>
            <tr>
                <th width="4%" nowrap>序号</th>
	            <th nowrap>回复内容</th>
	            <th width="8%" nowrap>回复人</th>
	            <th width="18%" nowrap>回复时间[↑]</th>
	            <th width="8%" nowrap>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList }" var="d" varStatus="order">
        <tr class="${order.index%2==0?'list_tr1':'list_tr2'}">
            <td align="center">${order.index+1 }</td>
            <td align="left">${d.content }</td>
            <td align="center">${d.estauser }</td>
            <td align="center">${d.estatime }</td>
            <td align="center"><a href="JavaScript:delOper('${d.id }');" class="btn btn-danger" style="color:#fff;">删除</a></td>
        </tr>        	
        </c:forEach>
            <c:if test="${empty dataList}">
	        <tr align="center">
	        	<td style="color:red;" colspan="12">暂无数据</td>
	        </tr>
	        </c:if>
        </tbody>
    </table>
    <div class="page_bar">
    	<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listtrainbbschild.shtml'}"/></ul>
    </div>
	<div style="width:100px; margin:10px auto 0 auto;">
	    <a href="###" class="btn btn-inverse" id="colse"  onclick="layerClose();">关闭</a>
	    <input type="hidden" name="ptrainBbsBean.id" value="${ptrainBbsBean.id}" />
	    <input type="hidden" name="tagpage" 	value="1" />
	    <input type="hidden" name="record" 		value="${record}" />
	</div>
</form>
</body>
</html>