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
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">英雄榜单[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
        	&nbsp;按<select name="querymap.sortsign" id="sortsign">
 						<option value="1" ${'1'==querymap.sortsign?'selected':''}>月份</option>
                        <option value="2" ${'2'==querymap.sortsign?'selected':''}>年度</option>
                    </select>排行
                 &nbsp;答题时间：<select name="querymap.year" id="year">
									<c:forEach items="${yearList}" var="y">
										<option value="${y.yearvalue}" ${y.yearvalue==querymap.year?'selected':''}>${y.yeartext}</option>						
									</c:forEach>
								</select>
								<select name="querymap.month" id="month">
									<c:forEach items="${monthList}" var="m">
										<option value="${m.monthvalue}" ${m.monthvalue==querymap.month?'selected':''}>${m.monthtext}</option>						
									</c:forEach>
								</select>
                             关键字：<select name="fields" id="fields">
                                    <option value="u.username" ${fields=='u.username'?'selected':''}>姓名</option>
                                    <option value="u.userid" ${fields=='u.userid'?'selected':''}>身份证号</option>
                                </select>
                    	<input type="text" name="keyword" id="keyword" value="${keyword}" maxlength="10" />
                    	<input type="button" class="btn btn-primary" name="btn_query" value="查询" onclick="toSearch();" />
						<input type="hidden" name="querymap.unitid" value="${querymap.unitid}" />
						<input type="hidden" name="querymap.reachnum" value="${querymap.reachnum}" />
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup"></div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <div class="gridDiv">
        <div class="btnGroup">
            <button class="btn btn-info" id="b_expexcel">导出Excel</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'部门名称', name:'DEPTNAME' ,width:360, align:'left'},
        { title:'班组名称', name:'GROUPNAME' ,width:180, align:'left',renderer: function(val,item,rowIndex){
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	return val;
             }
        }},
        { title:'姓名', name:'USERNAME' ,width:120, align:'center',renderer: function(val,item,rowIndex){
             var usernum = item.USERNUM;
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	return val;
             }
        }},
        { title:'答题次数', name:'ASKNUM' ,width:120, align:'center',renderer: function(val,item,rowIndex){
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	return val;
             }
        }},
        { title:'累计得分', name:'SCORE' ,width:120, align:'center',renderer: function(val,item,rowIndex){
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	return val;
             }
        }}
    ];
    
	var url = "<c:url value='/Ptrain/Ask/listptrainaskrank.shtml'/>";
	var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.reachnum':'${querymap.reachnum}',
		'querymap.year':'${querymap.year}',
		'querymap.month':'${querymap.month}',
    	'querymap.sortsign':'${querymap.sortsign}',
		'fields':'u.username',
		'keyword':'${keyword}'
    }
	
    //分页
    var mmg = $('#dataTable').mmGrid({
        height:'400',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post'
    });

	$(function(){
		opermonth();
		//年度,月份
		$("#sortsign").bind("change",function(){
			opermonth();
			toSearch();
		});
		
		//年,月
		$("#year,#month").bind("change",function(){
			toSearch();
		});
		
		//查询
		$("#b_search").bind("click",function(){
			toSearch();
		});
		
    	//导出Excel
    	$("#b_expexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Ask/saveptrainaskrankexpexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    });
    
    function opermonth(){
    	var sortsign = $("#sortsign").val();
		if(sortsign == '1'){
			$("#month").show();
		}else{
			$("#month").hide();
		}
    }
    
    //查询
    function toSearch(){
	    var unitid = $("#b_unitid").val();      //单位id
	    var sortsign = $("#sortsign").val();    //部门,专业标识
	    var year = $("#year").val();            //年度
	    var month = "";                         //月份
	    var reachnum = '${querymap.reachnum}';  //达标
	    var fields = $("#fields").val();      //关键字段
	    var keyword = $("#keyword").val();    //关键字
		var tagpage = '1';
	    if(sortsign == '1'){
	    	month = $("#month").val();          //月份
	    	mmg.load();
	    }else{
	    	//点击查询时页码置为1
		    mmg.load();
	    }
    }
</script>
</body>
</html>