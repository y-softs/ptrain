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
</head>
<body>
<div id="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">批次设置[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="insert">新增</button>
        </div>
        <div class="clear"/>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
</body>
<script language="javascript">
	var _searparam;
    //列
    var i=0,j=0,k=0,myIndex;
    var cols = [
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'批次名称', name:'appname',width:160, align:'center', renderer: null},
        { title:'默认批次', name:'defvote' ,width:80, align:'center', renderer: function(val,item,rowIndex){
        	var r=(val==1)?'√':'&nbsp;';
        	return r;
        }},
        { title:'操作', name:'' ,width:120, align:'center', renderer: function(val,item,rowIndex){
        	var r='<button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">设置</button>';
        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
        }}
    ];
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Voteapp/listPtrainVoteappByJq.shtml"/>',
        params:{},
        method: 'post',
        root: 'items',
        fullWidthRows: true
    });
    $(function(){
        $("#insert").bind("click",function(){setData('',1);});
    });
    //新增、修改数据
    function setData(id,fun){
    	var paramV="ptrainVoteappBean.id="+id+"&fun="+fun;
    	
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Voteapp/setptrainvoteapp.shtml"/>?"+paramV, 
        (fun == 1?"批次设置-新增":"批次设置-设置"), 730, 190, { buttons: {}});
    }
    //删除
    function delData(id){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Voteapp/deletePtrainVoteappByJq.shtml'/>",
					async:false,
					data:{'ptrainVoteappBean.id':id},
					success:function(data) {
						if('-1'!=data){
	            			parent.$.jBox.tip("操作成功！", 'info');
							mmg.load();
						}else{
	            			parent.$.jBox.tip("操作失败！", 'info');
						}
					},
					error:function(msg){
					    parent.$.jBox.tip("与数据库连接失败！", 'info');
					}
				});
            else if (v == 'cancel')
            return true;
        };
        parent.$.jBox.confirm("确定删除该记录？", "提示", submit);
    }
	//子页面重新刷新父页面
	function reloadPage(){
		mmg.load();
	}
</script>
</html>