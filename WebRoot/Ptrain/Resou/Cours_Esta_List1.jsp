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
        <div class="titleClass">课件编制[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
               课件类别： <select name="querymap.typeid" id="typeid">
             	<option value="">全部</option>
             </select>
             &nbsp;<input type="checkbox" name="querymap.showsign" value="1" ${'1'==querymap.showsign?'checked':''} onclick="querydata();"/>仅显示待申报
             &nbsp;关键字：
    		 <input type="hidden" name="fields" value="r.itemname"/>
    		 <input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
             <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
    		
    		<input type="hidden" name="sign" value="${sign}"/>
            <input type="hidden" name="ptrainCoursBean.id" id="id"/>
    		<input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
            <input type="hidden" name="querymap.chksign" id="chksign"/>
    		<input type="hidden" name="fatherid" value="${fatherid}" id="fatherid" />
            <input type="hidden" name="listpage" value="/Ptrain/Resou/Cours_Esta_List1.jsp" id="listpage" />
	        <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
	        <input type="hidden" name="record" value="${record}" id="record" />
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="insert">新增</button>
            <button class="btn btn-info" id="chkPl">批量申报</button>
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
    var stateList=${stateList};
    //列
    var cols = [
        { title:'', name:'id' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
        	var r=('${XQSB}'!=item.flowsta)?'disabled':'';
            return '<input type="checkbox" name="chk" '+r+' value="'+val+'"/>';
        }},
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'课件类别', name:'typeid',width:90, align:'center',sortName:'co.typeid',sortable: true, renderer: null},
        { title:'课件名称', name:'title' ,width:180, align:'left',sortName:'co.title,co.id',sortStatus:'asc',sortable: true, renderer: function(val,item,rowIndex){
        	return "<a href=\"JavaScript:dataDeta('"+item.id+"');\">"+val+"</a>";
        }},
        { title:'课件简介', name:'content' ,width:270, align:'left', renderer: function(val,item,rowIndex){
        	return val||'&nbsp;';
        }},
        { title:'*流程状态', name:'flowmark' ,width:100, align:'center', renderer: function(val,item,rowIndex){
        	var r='';
        	for(var i in stateList){
        		if(val==stateList[i].key){
        			r=stateList[i].desc;
        		}
        	}
        	if(''!=r)r='<a href="JavaScript:flowDeta(\''+item.id+'\');">'+r+'</a>';
        	return r||'&nbsp;';
        }},
        { title:'操作', name:'' ,width:300, align:'left', renderer: function(val,item,rowIndex){
        	var r='';
	        if('${XQSB}'==item.flowsta){
	        	r+='<button class="btn btn-info" onclick="chkData(\''+item.id+'\',\'1\')">申报</button>';
	        	r+=' <button class="btn btn-info" onclick="cataData(\''+item.id+'\')">目录</button>';
	        	r+=' <button class="btn btn-info" onclick="fileData(\''+item.id+'\')">文件</button>';
	        	r+=' <button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
	        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	}else if('${XQSH}'==item.flowsta){
	        	r='<button class="btn btn-info" onclick="chkData(\''+item.id+'\',\'2\')">撤回</button>';
	        }else{r="&nbsp;";}
        	return r;
        }}
    ];
    //分页
    var paramsV={
    	'fatherid':'${fatherid}',
    	'querymap.flowsta':'${querymap.flowsta}',
    	'querymap.flowstathis':'${not empty querymap.showsign?querymap.flowsta:''}',
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
        $("#chkPl").bind("click",function(){
        	var chk=$('input[name="chk"]:checked').size();
        	if(0==chk){
        		alert('请选择要操作的记录！');
        		return;
        	}
        	chkData('',1);
        });
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
    	var flowstathis='';
    	var chk=$('input[name="querymap.showsign"]:checked').size();
    	if(0!=chk)flowstathis='${querymap.flowsta}';
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.typeid':$("#typeid").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage,'querymap.flowstathis':flowstathis};
	    mmg.load({'querymap.typeid':$("#typeid").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage,'querymap.flowstathis':flowstathis});
    }
    //新增、修改数据
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&querymap.flowsta=${querymap.flowsta}&ptrainCoursBean.id="+id+"&fatherid=${fatherid}&ptrainCoursBean.fatherid=${fatherid}&querymap.typeid="+$("#typeid").val()+"&setpage=/Ptrain/Resou/Cours_Main_Set1.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Cours/setptraincours.shtml"/>?"+paramV, 
        (fun == 1?"课件编制-新增":"课件编制-修改"), 700, 520, { buttons: {}});
    } 
    //目录详细页面
    function cataData(id){
    	$("#fatherid").val(id);
    	$("#listpage").val("/Ptrain/Resou/Cours_Esta_List2.jsp");
		$("#form1").attr("action","<c:url value='/Ptrain/Cours/listptraincours_cata.shtml'/>").trigger("submit");
    }
    //附件列表信息
    function fileData(id){
    	$("#listpage").val("/Ptrain/Resou/Cours_EstaFile_List.jsp");
		$("#form1").attr("action","<c:url value='/Ptrain/Cours/listptraincoursfile.shtml'/>?ptrainCoursBean.fatherid="+id).trigger("submit");
    }
    //详细页面
    function dataDeta(id){
		var url="<c:url value='/Ptrain/Cours/setptraincours.shtml'/>?setpage=/Ptrain/Resou/Cours_Esta_Win.jsp&fatherid=0&ptrainCoursBean.id="+id;
        parent.$.jBox.open("iframe:"+url, "详细", 650, 270, { buttons: {'关闭':false},
            submit: function (v, h, f) {
                return true;
            }
        });
    }
    //流程详细
    function flowDeta(id){
		var url = "<c:url value='/Ptrain/Flow/toforwardptrainflow.shtml'/>?ptrainFlowBean.recid="+id;
		url+="&ptrainFlowBean.modsign=${flowModsign}";
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
	//申报/撤回
	function chkData(id,sign){
	    var newid = id;
		if('1'==sign){//申报
        	parent.$.jBox.confirm("确定申报记录？", "提示", function (v, h, f) {
	            	if (v == 'ok'){
	            		if(''==id){
							var idAttr=new Array();
							$.each($('input[name="chk"]'),function(i, n){
								if(n.checked)idAttr.push(n.value);
							});
							newid = idAttr.join(",");
				        }
						$("#chksign").val('${SHEN_BAO}');
					    $("#id").val(newid);
						$("#form1").attr("action","<c:url value='/Ptrain/Cours/updateptraincourschk.shtml'/>").trigger("submit");
	            	}
	            	else if (v == 'cancel')
	            	return true;
	            }
	        );
		}else if('2'==sign){//撤回
        	parent.$.jBox.confirm("确定撤回申报记录？", "提示", function (v, h, f) {
	            	if (v == 'ok'){
						$("#chksign").val('${CHE_HUI}');
					    $("#id").val(newid);
						$("#form1").attr("action","<c:url value='/Ptrain/Cours/updateptraincourschk.shtml'/>").trigger("submit");
	            	}
	            	else if (v == 'cancel')
	            	return true;
	            }
	        );
		}
	}
	//子页面重新刷新父页面
	function reloadPage(){
		querydata();
	}
</script>
</html>