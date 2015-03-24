<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title></title>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/datagrid.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/grid.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/plugins.js"></script>
</head>
<body>
<!-- 后面新增的样式 -->
<div id="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">最佳知识[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain" style="display:none">
	    <!-- 查询条件 --->
	     <form method="post" name="form" id="form1" action="">
	     	<input type="hidden" name="querymap.unitid" value="${loginSession.unitid}" />
		 </form>
	</div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div class="btnGroup">
            <button class="btn btn-info" id="b_expexcel">导出Excel</button>
        </div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'专业类别', name:'strflag' ,width:180, align:'left'},
        { title:'帖子', name:'title' ,width:130, align:'left'},
        { title:'部门名称', name:'deptname' ,width:220, align:'left'},
        { title:'班组名称', name:'groupname' ,width:120, align:'left'},
        { title:'发帖人', name:'username' ,width:80, align:'center'},
        { title:'浏览数', name:'browse' ,width:80, align:'center'},
        { title:'点赞数', name:'nice' ,width:80, align:'center'}
    ];
    
	var url = "<c:url value='/Ptrain/Bbs/listptrainrank.shtml'/>";
    //结果集
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: url,
        method: 'post'
    });

	$(function(){
    	//导出Excel
    	$("#b_expexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Bbs/saveptrainbbsrankexpexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    });
</script>
</body>
</html>