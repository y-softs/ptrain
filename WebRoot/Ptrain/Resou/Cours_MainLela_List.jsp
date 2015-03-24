<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../Include/TagLib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/tabs.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/datagrid.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.9.1.min.js'/>"></script>
    <script src="<c:url value='/Script/plugin/grid/grid.js'/>"></script>
    <script src="<c:url value='/Script/plugin/grid/grid-pagi.js'/>"></script>
    <script src="<c:url value='/Script/plugin/grid/plugins.js'/>"></script>
</head>
<body>
<div id="container">
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
                课件名称：<select id="typeid" disabled>
             	<option value="">${ptrainCoursBean.title}</option>
              </select>
              &nbsp;目录名称：<select id="typeid" disabled>
             	<option value="">${ptrainCoursBean.strflag}</option>
              </select>
              
             <input type="hidden" name="tokenid" value="${tokenid}"/>
    		 <input type="hidden" name="ptrainCoursBean.fileid" value="${ptrainCoursBean.fileid}" id="fileid" />
    		 <input type="hidden" name="ptrainCoursBean.endsign" value="${ptrainCoursBean.endsign}"/>
    		 <input type="hidden" name="fatherid" value="${fatherid}" id="fatherid" />
    		 <input type="hidden" name="savePath" value="${savePath}" />
 		</form>
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
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
</body>
<script language="javascript">
    //列
    var cols = [
        { title:'', name:'id' ,width:37,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
        	var r=val=='${ptrainCoursBean.fileid}'?'checked':'';
            return "<input type='radio' name='fileid' value='"+val+"' "+r+" />";
        }},
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'文件名称', name:'filename' ,width:160, align:'left',sortName:'id',sortStatus:'asc',sortable: true, renderer: null},
        { title:'路径', name:'savepath' ,width:190,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
        	return "/"+val.replace('${savePath}','');
        }}
    ];
    //无分页
    var paramsV={
    	'modsign':'${modsign}',
    	'recid':'${ptrainCoursBean.fatherid}'
    }
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/File/listPtrainFileByJq.shtml"/>',
        params:paramsV,
        method: 'post',
        fullWidthRows: true,
		nowarp:true,
        remoteSort:true
    });
    $(function(){
    });
    //保存
    function callBack(){
    	if($("input[name='fileid']:checked").size()==0){
    		alert("请选择关联文件！");
    		return -1;
    	}
    	$("#fileid").val($("input[name='fileid']:checked").val());
    	var paramV="?ptrainCoursBean.id=${ptrainCoursBean.id}";
    	$("#form1").attr("action",'<c:url value='/Ptrain/Cours/saveptraincours.shtml'/>'+paramV).trigger("submit");
    	return $("input[name='fileid']:checked").val();
    }
</script>
</html>