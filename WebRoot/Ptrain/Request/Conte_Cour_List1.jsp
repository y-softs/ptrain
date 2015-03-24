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
        <div class="titleClass">课件培训点菜[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
                    专业类别： <select  name="querymap.specid" id="specid">
                    <option value="">全部</option>
                </select>
                    关键字：
    		 <input type="hidden" name="fields" value="r.itemname"/>
            <input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
            <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="queryData();"/>

    		<input type="hidden" name="sign" value="${sign}"/>
    		<input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
    		<input type="hidden" name="querymap.reqtype" value="${querymap.reqtype}"/>
    		<input type="hidden" name="listpage" value="/Ptrain/Request/Conte_Cour_List1.jsp"/>
    		<input type="hidden" name="listtemppage" value="/Ptrain/Request/Conte_Cour_List2.jsp"/>
    		<input type="hidden" name="settemppage" value="/Ptrain/Request/Conte_Cour_Set2.jsp"/>
    		<input type="hidden" name="savePath" value="${savePath}"/>
 		</form>
 	</div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="impdata">导入Excel</button>
            <button class="btn btn-info" id="insert">新增</button>
            <button class="btn btn-info" id="fileDeta">展开附件</button>
            <button class="btn btn-info" id="expdata">导出Excel</button>
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
        { title:'专业类别', name:'specid',width:90, align:'center',sortName:'r.specid',sortable: true, renderer: null},
        { title:'项目名称', name:'itemname' ,width:260, align:'left',sortName:'r.estatime desc,r.id desc',sortStatus:'desc',sortable: true, renderer: function(val,item,rowIndex){
        	return "<a href=\"JavaScript:dataDeta('"+item.id+"');\">"+val+"</a>";
        }},
        { title:'课程介绍', name:'itemdesc' ,width:360, align:'left', renderer: function(val,item,rowIndex){
        	return (val||"&nbsp;")+'<font style="display:none">'+item.id+'</font>';
        }},
        { title:'报名人数', name:'intflag' ,width:60, align:'center', renderer: null},
        { title:'状态', name:'state' ,width:60, align:'center', renderer: function(val,item,rowIndex){
        	var r='待办班';
        	if('0'!=val){r='已办班';}
        	return r;
        }},
        { title:'操作', name:'state' ,width:120, align:'center', renderer: function(val,item,rowIndex){
        	if('0'==val){
	        	var r='<button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
	        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
	        	return r;
        	}
        	return "&nbsp;";
        }}
    ];
    //分页
    var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.reqtype':'${querymap.reqtype}',
    	'querymap.specid':'${querymap.specid}',
    	'fields':'r.itemname',
    	'keyword':'${keyword}'
    }
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Req/listPtrainReqByJq.shtml"/>',
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
        $("#impdata").bind("click",impExcel);
        $("#expdata").bind("click",expExcel);
        $("#insert").bind("click",function(){setData('',1);});
        specList();
        $("#specid").bind("change",queryData);
        $("#fileDeta").bind("click",fileDeta);
    });
    //专业类别数据
    function specList(){
    	$.ajax({
             type: 'post',
             async: false,
             url: '<c:url value="/Ptrain/Req/findSpecListByJq.shtml"/>',
             data: {'querymap.unitid':'${querymap.unitid}'},
             dataType: 'json',
             cache: false,
             success:function(data){
             	for(var i in data){
             		var chk=data[i].id=='${querymap.specid}'?'selected':'';
             		$("#specid").append('<option value="'+data[i].id+'" '+chk+'>'+data[i].codename+'</option>');
             	}
             },
			error:function(msg){
			    parent.$.jBox.tip("与数据库连接失败！", 'info');
			}
        });
    }
    //数据搜索
    function queryData(){
    	var keyword=($("#searchkey").val());
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.specid':$("#specid").val(),'keyword':keyword,'tagpage':tagpage};
	    mmg.load({'querymap.specid':$("#specid").val(),'keyword':keyword,'tagpage':tagpage});
    }
    //新增、修改数据
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&ptrainReqBean.id="+id+"&querymap.reqtype=${querymap.reqtype}&querymap.specid="+$("#specid").val()+"&setpage=/Ptrain/Request/Conte_Cour_Set1.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Req/setptrainreq.shtml"/>?"+paramV, 
        (fun == 1?"课件培训点菜-新增":"课件培训点菜-修改"), 900, 380, { buttons: {}});
    }
    //详细页面
    function dataDeta(id){
		var url="<c:url value='/Ptrain/Req/setptrainreqwin.shtml'/>?ptrainReqBean.id="+id;
        parent.$.jBox.open("iframe:"+url, "详细", 650, 322, { buttons: {'关闭':false},
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
					url:"<c:url value='/Ptrain/Req/deletePtrainReqByJq.shtml'/>", //代码数据
					async:false,
					data:{'ptrainReqBean.id':id},
					success:function(data) {
						if('-1'!=data){
	            			parent.$.jBox.tip("操作成功！", 'info');
	                    	queryData();
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
	//附件加载
    function fileDeta(){
		$.each($("#dataTable tbody tr"),function(){
			var obj=$("td",$(this)).eq(3),recid=$("font",obj).html();
			if(''!=recid){
				var url="<c:url value='/Ptrain/File/listPtrainFileByJq.shtml'/>";
				var res=jqueryOper(url,{'modsign':'${modsign}','recid':recid},obj);
			}
		});
    }
	//导入excel 跳转
	function impExcel(){
		$("#form1").attr("action","<c:url value='/Ptrain/Req/setptrainreqtempexcel.shtml'/>").trigger("submit");
	}
	//导出excel
	function expExcel(){
		$("#form1").attr("action","<c:url value='/Ptrain/Req/saveptrainreqexpexcel.shtml'/>").trigger("submit");
	}
	//jquery接口
	function jqueryOper(urlV,dataV,obj){
		var res='',fileData='<span id="fileData"></span>';
		$.ajax({
			type:"POST",
			url:urlV,
			data:dataV,
			//async:false,
			dataType:"json",
			beforeSend:function(){
				$("#fileData",obj).remove();
				obj.append('<br/><font id="fileLoad" color="red">附件搜索中...</font>');
			},
			success:function(jsonV){
				$("br",obj).remove();
				$("#fileLoad",obj).remove();
				res=jsonV;
				if(''!=res){
					obj.append(fileData);
					for(var i in res){
						$("#fileData",obj).append('<br/>课件'+(parseInt(i)+1)+'：<a href="JavaScript:downLoad(\''+res[i].savename+'\',\'${savePath}\');">'+res[i].filename+'</a>');
					}
				}
			},
            error: function (msg) {
                res=msg;
                $("#fileData",obj).append("<br/><font color='red'>附件加载失败！</font>");
            }
		});
		return res;
	}
    //下载文件 
	function downLoad(savename,savepath){
	   downframe.document.form1.inputPath.value = savepath;
	   downframe.document.form1.fileName.value = savename;
	   downframe.document.form1.submit();
	}
	
	//子页面重新刷新父页面
	function reloadPage(){
		queryData();
	}
</script>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>