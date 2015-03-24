<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title></title>
	<link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/Style/datagrid.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
	<script type="text/javascript" src="<c:url value='/Script/jquery-1.9.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/Script/plugin/grid/grid.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/Script/plugin/grid/grid-pagi.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/Script/plugin/grid/plugins.js'/>"></script>
</head>
<body>
<!-- 后面新增的样式 -->
<div id="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">投票资源[<span class="switchSearch">数据搜索</span>]</div>
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
	        关键字：<select name="fields" id="b_fields">
	        	<option value="srctitle">投票对象标题</option>
	        </select>
	        <input type="text" name="keyword" id="b_keyword" value="${keyword}" />
	        <input type="button" name="search" class="btn btn-primary" id="b_search" onclick="toSearch();" value="搜索" />
	    </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="btn_insert">新增</button>
        </div>
        <div class="clear"/>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
<script type="text/javascript">
	var _searparam ;
    
    //列
    var cols = [
    	{ title:'序号', name:'id' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1+'<input type="hidden" name="pkid" value='+val+' />';
        }},
        { title:'资源封面', name:'srcimg' ,width:220, align:'left'},
        { title:'投票对象标题', name:'srctitle' ,width:220, align:'left'},
        { title:'对象类别', name:'srcsign' ,width:80, align:'left',renderer: function(val,item,rowIndex){
        	if(val =='1'){
        		return "视频";
        	}else{
        		return "课件";
        	}
        }},
        { title:'对象url', name:'srcurl' ,width:250, align:'center'},
        { title:'总票数', name:'votecount' ,width:50, align:'center'},
        { title:'操作', name:'' ,width:220, align:'left', lockWidth:true, lockDisplay: true, renderer: function(val,item,rowIndex){
       		return "<button class=\"btn btn-info\" onClick=setCode("+item.id+",'2');>修改</button>&nbsp;<button class=\"btn btn-danger\" onClick=delData("+item.id+");>删除</button>"
        }}
    ];
    
	var url = '<c:url value='/Ptrain/Votesrc/listPtrainVotesrcByJq.shtml'/>';
    //分页
    var mmg = $('#dataTable').mmGrid({
        indexColWidth: 35,
        height:'420',
        cols: cols,
        url: url,
        method: 'post',
        multiSelect: false,
		checkCol:true,
		nowarp:true,
        root: 'items',
        plugins : [
            $('#dataPagi').mmPaginator(),
        ]
    });
    
    $(function(){
    	//新增
    	$("#btn_insert").bind("click",function(){
    		setCode("","1");
    	});
    	
    	//批次
    	$("#b_appid").bind("change",function(){
	    	mmg.load({'querymap.appid':$("#b_appid").val(),'keyword':$("#b_keyword").val(),fields:$("#fields").val()});
    	});
    });
    
    //数据搜索
    function toSearch(){
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.appid':$("#b_appid").val(),'keyword':$("#b_keyword").val(),fields:$("#b_fields").val(),'tagpage':tagpage};
	    mmg.load({'querymap.appid':$("#b_appid").val(),'keyword':$("#b_keyword").val(),fields:$("#b_fields").val(),'tagpage':tagpage});
    }
    
    //删除
    function delData(id){
    	if(id == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Votesrc/deletePtrainVotesrcByJq.shtml'/>", //代码数据
					async:false,
					data:{'ptrainVotesrcBean.id':id},
					error:function(data){
					    parent.$.jBox.tip("与数据库连接失败！", 'info');
					},
					success:function(data) {
						if(data == '1'){
	            			parent.$.jBox.tip("操作成功！", 'info');
		                    mmg.load();
						}else{
	            			parent.$.jBox.tip("操作失败！", 'info');
						}
					}
				});
            return true; //close
        };
        parent.$.jBox.confirm("确定删除该记录？", "提示", submit);
    }

    function toSearch(){
	    mmg.load();
    }
    
    //新增,修改代码设置
	function setCode(id,fun){
		var url = '';
		var msg = '';
		if(fun == '1'){
			msg = '投票资源-新增';
			url = "<c:url value='/Ptrain/Votesrc/setptrainvotesrc.shtml'/>?ptrainVotesrcBean.id=&querymap.appid="+$("#b_appid").val();
			url += "&setpage=Votesrc_Set.jsp";
		}else if(fun == '2'){
			msg = '投票资源-修改';
			url = "<c:url value='/Ptrain/Votesrc/setptrainvotesrc.shtml'/>?ptrainVotesrcBean.id="+id+"&querymap.appid="+$("#b_appid").val();
			url += "&setpage=Votesrc_Set.jsp";
		}
	    parent.$.jBox.open("iframe:"+url, msg, 620, 470, {buttons: {}});
	}
	
	//子页面重新刷新父页面
	function reloadPage(){
		toSearch();
	}
	
</script>
</body>
</html>