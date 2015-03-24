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
    <script type="text/javascript" src="<c:url value='/Script/plugin/grid/grid.js'/>"></script>
</head>
<body>
<div id="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">合法IP段[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
     <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
	    <form method="post" name="form" id="form1" action="">
	    	<span id="b_spanappid">
	    		批次：<select name="querymap.appid" id="b_appid">
		        	<option value="" ${querymap.appid==""?'selected':''}>全部</option>
		        	<c:forEach items="${appList}" var="d">
		        		<option value="${d.id}" ${querymap.appid==d.id?'selected':''}>${d.appname}</option>
		        	</c:forEach>
		        </select>
	    	</span>
	    </form>
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
<script language="javascript">
	
	 $(function(){
		$("#b_appid").bind("change",function(){
	    	mmg.load({'querymap.appid':$("#b_appid").val()});
		});
		
		//新增
        $("#insert").bind("click",function(){setData('',1);});
    });
	
    //列
    var i=0,j=0,k=0,myIndex;
    var cols = [
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'开始IP段', name:'startip',width:160, align:'center', renderer: null},
        { title:'结束IP段', name:'endip' ,width:160, align:'center', renderer: null},
        { title:'操作', name:'' ,width:120, align:'center', renderer: function(val,item,rowIndex){
        	var r='<button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
        }}
    ];
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Voteipconfig/listPtrainVoteipconfigByJq.shtml"/>',
        params:{'querymap.appid':$("#b_appid").val()},
        method: 'post',
        root: 'items',
        fullWidthRows: true
    });
    
    //新增、修改数据
    function setData(id,fun){
    	var paramV="ptrainVoteipconfigBean.id="+id+"&fun="+fun+"&querymap.appid="+$("#b_appid").val();
    	
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Voteipconfig/setptrainvoteipconfig.shtml"/>?"+paramV, 
        (fun == 1?"批次设置-新增":"批次设置-设置"), 540, 230, { buttons: {}});
    }
    //删除
    function delData(id){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Voteipconfig/deletePtrainVoteipconfigByJq.shtml'/>",
					async:false,
					data:{'ptrainVoteipconfigBean.id':id},
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
</body>
</html>