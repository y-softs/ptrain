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
        <div class="titleClass">专业版主设置[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
	        关键字：<select name="fields" id="b_fields">
	        	<option value="codename">类别</option>
	        </select>
	        <input type="text" name="keyword" id="b_keyword" value="${keyword}" />
	        <input type="button" name="search" id="b_search" class="btn btn-primary" value="搜索" />
	        <input type="hidden" name="querymap.unitid" id="b_unitid" value="${loginSession.unitid}" />
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
</div>
<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'id' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'类别', name:'codename' ,width:480, align:'left'},
        { title:'版主', name:'strflag' ,width:280, align:'left'},
        { title:'操作', name:'' ,width:220, align:'left', lockWidth:true, lockDisplay: true, renderer: function(val,item,rowIndex){
        	return "<button class=\"btn btn-info\" onClick=setManager('"+item.id+"','"+item.kpid+"','"+item.managers+"');>设置</button>&nbsp;<button class=\"btn btn-danger\" onClick=delData("+item.id+");>清除</button>"
        }}
    ];
    
	var url = "<c:url value='/Ptrain/Manager/listptrainmanager.shtml'/>";
	var paramsV={
    	'fields':'codename',
    	'keyword':'${keyword}'
    }
	
    //分页
    var mmg = $('#dataTable').mmGrid({
        indexColWidth: 35,
        height:'420',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post',
		nowarp:true
    });
    
	$(function(){
		$("#b_search").bind("click",function(){
			toSearch();
		});
    });
    
    //查询
    function toSearch(){
		//点击查询时页码置为1
		var tagpage = '1';
	    var fields = $("#b_fields").val();      //关键字段
	    var keyword = $("#b_keyword").val();    //关键字
	    mmg.load();
    }
    
    //设置
	function setManager(id,kpid,userid){
		var url = '';
		var msg = '';
		msg = '专业版主设置';
		url = "<c:url value='/Ptrain/Manager/setptrainmanagerwin.shtml'/>";
		url +="?ptrainManagerBean.unitid=${loginSession.unitid}";
		url +="&ptrainManagerBean.id="+id;
		url +="&ptrainManagerBean.kpid="+kpid;
		url +="&useridstr="+userid;
		
	    parent.$.jBox.open("iframe:"+url, msg, 750, 550, { buttons: {'确定':true,'关闭':false},
	        submit: function (v, h, f) {
	            var we = h.find("#jbox-iframe")[0].contentWindow;
	            if(v == 0){
	               return true;
	            }else{
	               var rtn = we.checkForm();
	               if(rtn){
	               		parent.$.jBox.tip("操作成功！", 'info');
	               		toSearch()
	               }
	               return rtn;
	            }
	            return false;
	        }
	    });
	}
	
	//清除
    function delData(id){
    	if(id == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var url = "<c:url value='/Ptrain/Manager/deletePtrainManagerByJq.shtml'/>";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url: url, //代码数据
					async:false,
					data:{'ptrainManagerBean.id':id},
					error:function(data){
					    parent.$.jBox.tip("与数据库连接失败！", 'info');
					},
					success:function(data) {
						if(data == '1'){
	            			parent.$.jBox.tip("操作成功！", 'info');
	               			toSearch();
						}else{
	            			parent.$.jBox.tip("操作失败！", 'info');
						}
					}
				});
            else if (v == 'cancel')
                parent.$.jBox.tip("操作失败！", 'info');
            return true;
        };
        parent.$.jBox.confirm("确定删除该记录？", "提示", submit);
    }
	
</script>
</body>
</html>