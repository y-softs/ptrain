<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="nsoft" uri="/WEB-INF/nsoft.tld"%>
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
			$("#deptid,#groupid,#subgroupid").bind("change",querydata);
		});
		//提交查询数据列表
		function querydata(){
			if($(this).is("#deptid")){
		 		$("#groupid").val('');
		 		$("#subgroupid").val('');
		 	}else if($(this).is("#groupid")){
		 		$("#subgroupid").val('');
		 	}
		 	$("input[name=tagpage]").val('1');
			$("input[name=record]").val('${record}');
			document.form2.action="<c:url value='/pt/requser/listptrainrequsersign.shtml'/>";
			document.form2.submit();
		}
	</script>
	<style type="text/css">
		body{background:#fff;font-size:13px;}
		.function{height:35px;line-height: 35px; padding-top:5px;}
		.list-tab th{background:#f2f2f2;line-height:35px; font-weight:normal;}
		.list-tab td{ border-top:1px solid #c2d5e3; border-buttom:1px solid #c2d5e3; line-height:30px; vertical-align:middle;}
		.keyword{width:80px;border:1px solid #ccc;}
	</style>
<body>
<form action="" id="form2" name="form2" method="post">
	<div class="function">
		<div style=" float:left; margin-right:5px;">
			部门班组：<select name="querymap.deptid" id="deptid">
             		<option value="">全部</option>
             		<c:forEach items="${deptlist}" var="d">
                     	<option value="${d.id}" ${querymap.deptid==d.id?'selected':''}>${d.organname}</option>
                     </c:forEach>
                 </select>
                 <c:if test="${not empty grouplist}">
                 <select name="querymap.groupid" id="groupid">
             		<option value="">全部</option>
             		<c:forEach items="${grouplist}" var="g">
                     	<option value="${g.id}" ${querymap.groupid==g.id?'selected':''}>${g.organname}</option>
                     </c:forEach>
                 </select>
                 </c:if>
                 <c:if test="${not empty supgrouplist}">
                 <select name="querymap.subgroupid" id="subgroupid">
             		<option value="">全部</option>
             		<c:forEach items="${supgrouplist}" var="s">
                     	<option value="${s.id}" ${querymap.subgroupid==s.id?'selected':''}>${s.organname}</option>
                     </c:forEach>
                 </select>
                 </c:if>
          </div> 
          <div style="float:right;width:270px;">
          		<label style="float:left;">
          		关键字：<select name="fields">
                        <option value="u.username" ${'u.username'==fields?'selected':''}>姓名</option>
                        <option value="u.userid" ${'u.userid'==fields?'selected':''}>身份证号</option>
                        </select><input type="text" name="keyword" class="keyword" value="${keyword }"/>
          		</label>
          		<a href="###" class="btn btn-primary" style="float:left;" onclick="querydata();">查询</a>
          </div>
    </div>
	<table class="list-tab" style="border-left:none; border-right:none;">
        <thead>
            <tr>
                <th width="4%">序号</th>
	            <th nowrap>部门名称</th>
	            <th width="25%">班组名称</th>
	            <th width="10%">姓名</th>
	            <th width="14%">报名时间</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList }" var="d" varStatus="order">
        <tr>
            <td align="center">${order.index+1 }</td>
            <td align="left">${d.deptname }</td>
            <td align="center">${d.groupname }</td>
            <td align="center">${d.userid }</td>
            <td align="center">${regdate }</td>
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
    	<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listptrainrequsersign.shtml'}"/></ul>
    </div>
	<div style="width:100px; margin:10px auto 0 auto;">
	    <a href="###" class="btn btn-inverse" id="colse"  onclick="layerClose();">关闭</a>
        <input type="hidden" name="querymap.reqid" value="${querymap.reqid}" />
        <input type="hidden" name="querymap.unitid" value="${querymap.unitid}" />
	    <input type="hidden" name="tagpage" 	value="1" />
	    <input type="hidden" name="record" 		value="${record}" />
	</div>
</form>
</body>
</html>