<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/datagrid.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.9.1.min.js'/>"></script>
    <script src="<c:url value='/Script/plugin/grid/grid.js'/>"></script>
</head>
<body>
<div id="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">审核流程-详细</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
        </div>
        <div class="clear"/>
        <table id="dataTable"></table>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
	var nodeFlowM = {};
	<c:forEach items="${flowStaEnums}" var="fl">
		nodeFlowM["${fl.key}"] = "${fl.desc}";
	</c:forEach>
	var chksignMap = {};
	<c:forEach items="${chksignMap}" var="c">
		chksignMap["${c.key}"] = "${c.value}";
	</c:forEach>
    //列
    var cols = [
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'流程节点', name:'flowsta',width:90, align:'center',renderer: function(val,item,rowIndex){
        	return nodeFlowM[val]||"&nbsp;";
        }},
        { title:'操作描述', name:'chksign' ,width:80, align:'center',renderer: function(val,item,rowIndex){
        	return chksignMap[val];
        }},
        { title:'操作人', name:'strflag' ,width:60, align:'center', renderer: null},
        { title:'操作时间', name:'chktime' ,width:130, align:'center', renderer: null},
        { title:'备注', name:'' ,width:100, align:'center', renderer: function(val,item,rowIndex){
        	return (item.remark + item.chkmemo)||"&nbsp;";
        }}
    ];
    //分页
    var paramsV={'ptrainFlowBean.recid':'${ptrainFlowBean.recid}','ptrainFlowBean.modsign':'${ptrainFlowBean.modsign}'}
    var mmg = $('#dataTable').mmGrid({
        height:'190',
        width:'100%',
        cols: cols,
        url: '<c:url value="/Ptrain/Flow/listptrainflow.shtml"/>',
        params:paramsV,
        method: 'post',
        root: 'items',
		nowarp:true
    });
</script>