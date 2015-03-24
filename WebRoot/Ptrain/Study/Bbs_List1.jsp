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
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">知识问答[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
                    专业类别： <select name="querymap.specid" id="specid">
                      	<option value="">全部</option>
                   		<c:forEach items="${codeList}" var="d">
              	 			<option value="${d.id}" ${d.id==querymap.specid?'selected':''}>${d.codename}</option>
              	 		</c:forEach>
                </select>
                    关键字：
    		 <input type="hidden" name="fields" value="title"/>
    		 <input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
             <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
    		
    		 <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
             <input type="hidden" name="nature" 		value="${nature}" />
             <input type="hidden" name="KIND" 		value="${KIND}" />
    		
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
	var _searparam;
    //列
    var i=0,j=0,k=0,myIndex;
    var cols = [
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'专业类别', name:'strflag' ,width:80, align:'center', renderer: null},
        { title:'帖子', name:'title' ,width:260, align:'left', renderer: function(val,item,rowIndex){
        	return "<a href=\"JavaScript:setTopic('"+item.id+"');\">"+val+"</a>";
        }},
        { title:'回复', name:'intflag' ,width:40, align:'center', renderer: function(val,item,rowIndex){
        	return "<a href=\"JavaScript:setChild('"+item.id+"');\">"+val+"</a>";
        }},
        { title:'浏览', name:'browse' ,width:40, align:'center', renderer: null},
        { title:'点赞', name:'nice' ,width:40, align:'center', renderer: null},
        { title:'状态', name:'anssign' ,width:40, align:'center', renderer: function(val,item,rowIndex){
        	var r='&nbsp;';
        	if(val=='1'){
        		r='已答';
        	}else if(val=='0'){
        		r='未答';
        	}
        	return r;
        }},
        { title:'操作', name:'state' ,width:100, align:'left', renderer: function(val,item,rowIndex){
        	var r='<button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
        }}
    ];
    //分页
    var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'KIND':'${KIND}',
    	'querymap.specid':'${querymap.specid}',
    	'fields':'title',
    	'keyword':'${keyword}'
    }
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Bbs/listTrainBbsByJq.shtml"/>',
        params:paramsV,
        method: 'post',
        root: 'items',
        fullWidthRows: true,
		nowarp:true,
        remoteSort:true,
        plugins : [
            $('#dataPagi').mmPaginator()
        ]
    });
    $(function(){
        $("#specid").bind("change",querydata);
    });
    //数据搜索
    function querydata(){
    	var keyword=$("#searchkey").val();
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.specid':$("#specid").val(),'keyword':keyword,'tagpage':tagpage};
	    mmg.load({'querymap.specid':$("#specid").val(),'keyword':keyword,'tagpage':tagpage});
    }
    //新增、修改数据
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&ptrainBbsBean.id="+id+"&querymap.specid="+$("#specid").val()+"&setpage=Bbs_Set1&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Bbs/settrainbbs.shtml"/>?"+paramV, 
        (fun == 1?"知识问答-新增":"知识问答-修改"), 700, 420, { buttons: {}});
    }
    //删除
    function delData(id){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Bbs/deleteTrainBbsByJq.shtml'/>",
					async:false,
					data:{'ptrainBbsBean.id':id},
					success:function(data) {
						if('-1'!=data){
	            			parent.$.jBox.tip("操作成功！", 'info');
	                    	querydata();
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
		querydata();
	}
</script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
<script language="javascript">
	//帖子详细
	function setTopic(id){
		$.layer({
				type: 2,
				title: '帖子',
				maxmin: false,
				shadeClose: false, //开启点击遮罩关闭层
				offset: ['5px',''],
				area : ['830px' , '522px'],
				iframe: {src: "<c:url value='/Ptrain/Bbs/setbbstopic.shtml'/>?ptrainBbsBean.id="+id}
		});
	}
	//回复列表
	function setChild(id){
		$.layer({
				type: 2,
				title: '回复列表',
				maxmin: false,
				shadeClose: false, //开启点击遮罩关闭层
				offset: ['30px',''],
				area : ['800px' , '500px'],
				iframe: {src: "<c:url value='/Ptrain/Bbs/listtrainbbschild.shtml'/>?ptrainBbsBean.id="+id}
		});
	}
</script>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>