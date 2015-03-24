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
        <div class="titleClass">课件维护[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
               课件类别： <select name="querymap.typeid" id="typeid">
             	<option value="">全部</option>
             </select>
                 关键字：
    		 <input type="hidden" name="fields" value="r.itemname"/>
    		 <input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
             <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
    		
    		 <input type="hidden" name="sign" value="${sign}"/>
    		 <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
    		 <input type="hidden" name="fatherid" value="${fatherid}" id="fatherid" />
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="insert">目录式课件新增</button>
            <button class="btn btn-info" id="insert2">单个课件新增</button>
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
    var cols = [
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'课件类别', name:'typeid',width:90, align:'center',sortName:'co.typeid',sortable: true, renderer: null},
        { title:'课件名称', name:'title' ,width:160, align:'left',sortName:'co.title,co.id',sortStatus:'asc',sortable: true, renderer: function(val,item,rowIndex){
        	return "<a href=\"JavaScript:dataDeta('"+item.id+"','"+item.courstype+"');\">"+val+"</a>";
        }},
        { title:'类型', name:'courstype' ,width:40, align:'center', renderer: function(val,item,rowIndex){
        	return (val=='2'?'单个课件':'目录式课件');
        }},
        { title:'课件简介', name:'content' ,width:200, align:'left', renderer: function(val,item,rowIndex){
        	return val||'&nbsp;';
        }},
        { title:'操作', name:'courstype' ,width:240, align:'center', renderer: function(val,item,rowIndex){
	        var r='';
        	if('2'==val){
	        	r+='<button class="btn btn-info" onclick="setData2(\''+item.id+'\',2)">修改</button>';
        	}else{
	        	r+='<button class="btn btn-info" onclick="cataData(\''+item.id+'\')">目录</button>';
	        	r+=' <button class="btn btn-info" onclick="fileData(\''+item.id+'\')">文件</button>';
	        	r+=' <button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
        	}
	        r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
        }}
    ];
    //分页
    var paramsV={
    	'fatherid':'${fatherid}',
    	'querymap.flowsta':'${querymap.flowsta}',
    	'querymap.typeid':'${querymap.typeid}',
    	'fields':'co.title',
    	'keyword':'${keyword}'
    }
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Cours/listPtrainCoursByJq.shtml"/>',
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
        $("#insert").bind("click",function(){setData('',1);});
        $("#insert2").bind("click",function(){setData2('',1);});
        specList();
        $("#typeid").bind("change",querydata);
    });
    //课件类别数据
    function specList(){
    	$.ajax({
             type: 'post',
             async: false,
             url: '<c:url value="/Ptrain/Cours/findSpecListByJq.shtml"/>',
             data: {'querymap.unitid':'${querymap.unitid}'},
             dataType: 'json',
             cache: false,
             success:function(data){
             	for(var i in data){
             		var chk=data[i].id=='${querymap.typeid}'?'selected':'';
             		$("#typeid").append('<option value="'+data[i].id+'" '+chk+'>'+data[i].codename+'</option>');
             	}
             },
			error:function(msg){
			    parent.$.jBox.tip("与数据库连接失败！", 'info');
			}
        });
    }
    //数据搜索
    function querydata(){
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.typeid':$("#typeid").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage};
	    mmg.load({'querymap.typeid':$("#typeid").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage});
    }
    //新增、修改数据（目录式课件）
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&querymap.flowsta=${querymap.flowsta}&ptrainCoursBean.id="+id+"&fatherid=${fatherid}&ptrainCoursBean.fatherid=${fatherid}&querymap.typeid="+$("#typeid").val()+"&setpage=/Ptrain/Resou/Cours_Main_Set1.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Cours/setptraincours.shtml"/>?"+paramV, 
        (fun == 1?"课件维护-新增":"课件维护-修改"), 700, 520, { buttons: {}});
    }
    //新增、修改数据（单个课件）
    function setData2(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&querymap.flowsta=${querymap.flowsta}&ptrainCoursBean.id="+id+"&fatherid=${fatherid}&ptrainCoursBean.fatherid=${fatherid}&querymap.typeid="+$("#typeid").val()+"&setpage=/Ptrain/Resou/Cours_Main_Set3.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Cours/setptraincours2.shtml"/>?"+paramV, 
        (fun == 1?"课件维护-新增":"课件维护-修改"), 700, 490, { buttons: {}});
    }
    //目录详细页面
    function cataData(id){
    	$("#fatherid").val(id);
		$("#form1").attr("action","<c:url value='/Ptrain/Cours/listptraincours_cata.shtml'/>?listpage=/Ptrain/Resou/Cours_Main_List2.jsp").trigger("submit");
    }
    //附件列表信息
    function fileData(id){
		$("#form1").attr("action","<c:url value='/Ptrain/Cours/listptraincoursfile.shtml'/>?listpage=/Ptrain/Resou/Cours_MainFile_List.jsp&ptrainCoursBean.fatherid="+id).trigger("submit");
    }
    //课件详细页面
    function dataDeta(id,courstype){
    	var hrefV=(courstype=='2'?'setptraincours2.shtml':'setptraincours.shtml');
		var url="<c:url value='/Ptrain/Cours/'/>"+hrefV+"?setpage=/Ptrain/Resou/Cours_Esta_Win.jsp&fatherid=0&ptrainCoursBean.id="+id;
        parent.$.jBox.open("iframe:"+url, "详细", 650, 320, { buttons: {'关闭':false},
            submit: function (v, h, f) {
                return true;
            }
        });
    }
    //删除
    function delData(id){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Cours/deletePtrainCoursByJq.shtml'/>",
					async:false,
					data:{'ptrainCoursBean.id':id,'querymap.delsign':1},
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
</html>