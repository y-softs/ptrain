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
                 关键字：
    		 <input type="hidden" name="fields" value="r.itemname"/>
    		 <input type="text" name="searchkey" id="searchkey" class="text_10" value=""/>
             <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
    		 
    		 <input type="hidden" name="fatherid" value="${fatherid}" id="fatherid"/>
             <!-- 回帖 -->
    		 <input type="hidden" name="sign" value="${sign}"/>
    		 <input type="hidden" name="listpage" value="${listpage}"/>
    		 <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
             <input type="hidden" name="keywprd" value="${keywprd}"/>
    		 <input type="hidden" name="querymap.typeid" value="${querymap.typeid}"/>
    		 <input type="hidden" name="querymap.showsign" value="${querymap.showsign}"/>
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="insert">新增</button>
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
    var i=0,j=0,k=0,myIndex;
    var cols = [
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            if(0==rowIndex){
                i=0;
                j=0;
                k=0;
            }
            if(parseInt(item.intflag)==1){
                i+=1;
                j=0;
                myIndex=i;
            }else if(parseInt(item.intflag)==2){
                j+=1;
                k=0;
                myIndex=i+'.'+j;
            }else if(parseInt(item.intflag)==3){
                k+=1;
                myIndex=i+'.'+j+'.'+k;
            }
            return myIndex;
        }},
        { title:'目录名称', name:'title' ,width:260, align:'left',sortName:'co.title,co.id',sortStatus:'asc',sortable: true, renderer: function(val,item,rowIndex){
            var r = "";
            for(var i=1;i<parseInt(item.intflag);i++){
                r += "&nbsp;&nbsp;";
            }
            r += val;
            return r;
        }},
        { title:'时长（分钟）', name:'time' ,width:50, align:'center', renderer: function(val,item,rowIndex){
        	return val||'&nbsp;';
        }},
        { title:'关联文件', name:'fileid' ,width:50, align:'center', renderer: function(val,item,rowIndex){
        	if('1'==item.endsign){return (''!=val)?'已关联':'待关联';}
        	else{return "&nbsp;"}
        }},
        { title:'操作', name:'intflag' ,width:100, align:'right', renderer: function(val,item,rowIndex){
        	var r='';
        	if('0'==item.endsign){
        		if('1'==val||'2'==val)
        			r+='<button class="btn btn-info" onclick="setData(\'\',\''+item.id+'\',\''+val+'\',1)">新增子项</button> ';
        	}else{
        		r+='<button class="btn btn-info" onclick="fileRela(\''+item.id+'\')">文件关联</button> ';
        	}
        	r+='<button class="btn btn-info" onclick="setData(\''+item.id+'\',\''+item.fatherid+'\',\''+item.intflag+'\',2)">修改</button>';
        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
        }}
    ];
    //无分页
    var paramsV={
    	'fatherid':'${fatherid}',
    	'fields':'co.title'
    }
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Cours/listPtrainCoursByJq.shtml"/>',
        params:paramsV,
        method: 'post',
        fullWidthRows: true,
		nowarp:true,
        remoteSort:true
    });
    $(function(){
        $("#insert").bind("click",function(){setData('','${fatherid}',1,1);});
        $("#goBack").bind("click",goBack);
    });
    //数据搜索
    function querydata(){
	    mmg.load({'querymap.typeid':$("#typeid").val(),'keyword':$("#searchkey").val()});
    }
    //新增、修改数据[level级别]
    function setData(id,fatherid,level,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&querymap.flowsta=${querymap.flowsta}&fatherid=${fatherid}&ptrainCoursBean.id="+id+"&querymap.typeid=${ptrainCoursBean.typeid}&ptrainCoursBean.fatherid="+fatherid+"&querymap.level="+level+"&setpage=/Ptrain/Resou/Cours_Main_Set2.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Cours/setptraincours.shtml"/>?"+paramV, 
        (fun == 1?"归口审核-新增":"归口审核-修改"), 700, 230, { buttons: {}});
    } 
    //附件关联
    function fileRela(id){
    	var paramV="ptrainCoursBean.fatherid=${fatherid}&ptrainCoursBean.id="+id;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Cours/listptraincoursfile_rela.shtml"/>?"+paramV, 
        ("文件关联"), 700, 450, { buttons: {'确定':true,'关闭':false},
            submit: function (v, h, f) {
                h.find('.errorBlock').hide('fast', function () { $(this).remove(); });
                var we = h.find("#jbox-iframe")[0].contentWindow;
                if (v == 0) {
                    return true;
                } else {
                    var rtn = we.callBack(),res=false;
                    if(-1!=rtn){
                    	parent.$.jBox.tip("操作成功！", 'info');
				    	querydata();
                    	res=true;
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
		$("#form1").attr("action","<c:url value='/Ptrain/Cours/listptraincours_esta.shtml'/>").trigger("submit");
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