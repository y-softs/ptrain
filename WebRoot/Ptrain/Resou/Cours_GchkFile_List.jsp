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
        <div class="titleClass">归口审核[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
                课件名称：<select id="typeid" disabled>
             	<option value="">${ptrainCoursBean.title}</option>
              </select>
             <%-- 关键字：
    		 <input type="hidden" name="fields" value="filelname"/>
    		 <input type="text" name="searchkey" id="searchkey" class="text_10" value=""/>
             <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>--%>
    		
    		 <input type="hidden" name="sign" value="${sign}"/>
    		 <input type="hidden" name="listpage" value="${listpage}"/>
    		 <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
    		 <input type="hidden" name="fatherid" value="${fatherid}" id="fatherid" />
    		 <input type="hidden" name="savePath" value="${savePath}" />
    		 <input type="hidden" name="querymap.showsign" value="${querymap.showsign}"/>
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="insert">新增文件</button>
            <button class="btn btn-info" id="goBack">返回</button>
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
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'文件名称', name:'filename' ,width:160, align:'left',sortName:'id',sortStatus:'asc',sortable: true, renderer: function(val,item,rowIndex){
        	return '<a href="JavaScript:downLoad(\''+item.savename+'\',\''+item.savepath+'\');">'+val+'</a>';
        }},
        { title:'操作', name:'' ,width:150,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
        	var r='<button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
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
        $("#insert").bind("click",function(){setData('',1);});
        $("#goBack").bind("click",goBack);
    });
    //数据搜索
    function querydata(){
	    mmg.load(paramsV);
    }
    //新增、修改数据
    function setData(id,fun){
    	var paramV="ptrainCoursBean.fatherid=${ptrainCoursBean.fatherid}&ptrainFileBean.id="+id+"&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Cours/setptraincoursfile.shtml"/>?"+paramV, 
        (fun == 1?"文件-新增":"文件-修改"), 700, 500, { buttons: {'关闭':false},
            submit: function (v, h, f) {
                h.find('.errorBlock').hide('fast', function () { $(this).remove(); });
                var we = h.find("#jbox-iframe")[0].contentWindow;
                if (v == 0) {
                	querydata();
                    return true;
                } else {
                    var rtn = we.callBack(),res=false;
                    if('true'==rtn){
                    	parent.$.jBox.tip("操作成功！", 'info');
				    	querydata();
                    	res=true;
                    }else if('1'==rtn){
				    	querydata();
                    	res=false;
                    }
                    return  res;
                }
                return false;
            }
        });
    } 
    //返回
    function goBack(){
    	$("#fatherid").val("0");
		$("#form1").attr("action","<c:url value='/Ptrain/Cours/listptraincours_gchk.shtml'/>").trigger("submit");
    }
    //删除
    function delData(id){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/File/deletePtrainFileByJq.shtml'/>",
					async:false,
					data:{'fileid':id},
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
    //下载文件 
	function downLoad(savename,savepath){
	   downframe.document.form1.inputPath.value = savepath;
	   downframe.document.form1.fileName.value = savename;
	   downframe.document.form1.submit();
	}
</script>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>