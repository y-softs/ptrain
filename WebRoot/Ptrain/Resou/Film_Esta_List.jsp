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
        <div class="titleClass">影音影视上传[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
               资源类别： <select name="querymap.typeid" id="typeid">
             	<option value="">全部</option>
             </select>
	         &nbsp;启用状态：<select name="querymap.usesign" id="usesign">
	        	<option value="" ${querymap.usesign==""?'selected':''}>全部</option>
	        	<option value="1" ${querymap.usesign=="1"?'selected':''}>启用</option>
	        	<option value="0" ${querymap.usesign=="0"?'selected':''}>禁用</option>
	         </select>
             &nbsp;<input type="checkbox" name="querymap.showsign" value="1" ${'1'==querymap.showsign?'checked':''} onclick="querydata();"/>仅显示待申报
             &nbsp;关键字：
    		 <input type="hidden" name="fields" value="r.itemname"/>
    		 <input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
             <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
    		
    		 <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
             <input type="hidden" name="ptrainContentBean.id" id="id"/>
             <input type="hidden" name="querymap.chksign" id="chksign"/>
    		 <input type="hidden" name="sign" value="${sign}" />
    		 <input type="hidden" name="opersign" value="${opersign}" />
    		 <input type="hidden" name="querymap.kind" value="${querymap.kind}" />
    		 <input type="hidden" name="fatherid" value="${fatherid}"/>
             <input type="hidden" name="listpage" value="/Ptrain/Resou/Film_Esta_List.jsp" id="listpage" />
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
<script type="text/javascript">
    //列
    var stateList=${stateList};
    var cols = [
        { title:'', name:'id' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
        	var r=('${XQSB}'!=item.flowsta)?'disabled':'';
            return '<input type="checkbox" name="chk" '+r+' value="'+val+'"/>';
        }},
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'资源类别', name:'typeid',width:90, align:'center',sortName:'co.typeid',sortable: true, renderer: null},
        { title:'标题', name:'title' ,width:160, align:'left',sortName:'co.sortnum desc,co.id desc',sortStatus:'desc',sortable: true, renderer: function(val,item,rowIndex){
        	if(item.usesign == 0){val='[<span class="fontcolor_red">禁</span>]'+val;}
        	return "<a href=\"JavaScript:dataDeta('"+item.id+"');\">"+val+"</a>";
        }},
        { title:'文本内容', name:'content' ,width:210, align:'left', renderer: function(val,item,rowIndex){
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
        { title:'操作', name:'usesign' ,width:170, align:'left', renderer: function(val,item,rowIndex){
	        var r='';
	        if('${XQSB}'==item.flowsta){
	        	r+='<button class="btn btn-info" onclick="chkData(\''+item.id+'\',\'1\')">申报</button>';
	        	r+=' <button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
	       		if(val == 1){
	       			r+=" <button class=\"btn btn-disabled\" onClick=operUsesign("+item.id+",'0');>禁用</button>";
	        	}else if (val == 0){
	        		r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
	       			r+=" <button class=\"btn btn-info\" onClick=operUsesign("+item.id+",'1');>启用</button>";
	        	}
	        }else if('${XQSH}'==item.flowsta){
	        	r='<button class="btn btn-info" onclick="chkData(\''+item.id+'\',\'2\')">撤回</button>';
	        }else{r="&nbsp;";}
        	return r;
        }}
    ];
    //分页
    var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.kind':'${querymap.kind}',
    	'querymap.typeid':'${querymap.typeid}',
    	'querymap.flowsta':'${querymap.flowsta}',
    	'querymap.flowstathis':'${not empty querymap.showsign?querymap.flowsta:''}',
    	'querymap.usesign':'${querymap.usesign}',
    	'fatherid':'${fatherid}',
    	'fields':'co.title',
    	'keyword':'${keyword}'
    }
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Content/listPtrainContentByJq.shtml"/>',
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
        $("#typeid,#usesign").bind("change",querydata);
    });
    //资源类别数据
    function specList(){
    	$.ajax({
             type: 'post',
             async: false,
             url: '<c:url value="/Ptrain/Content/findSpecListByJq.shtml"/>',
             data: {'querymap.unitid':'${querymap.unitid}','fatherid':'${fatherid}'},
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
    	_searparam = {'querymap.typeid':$("#typeid").val(),'querymap.usesign':$("#usesign").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage,'querymap.flowstathis':flowstathis};
	    mmg.load({'querymap.typeid':$("#typeid").val(),'querymap.usesign':$("#usesign").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage,'querymap.flowstathis':flowstathis});
    }
    //新增、修改数据
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&fatherid=${fatherid}&querymap.kind=${querymap.kind}&querymap.flowsta=${querymap.flowsta}&querymap.typeid="+$("#typeid").val()+"&ptrainContentBean.id="+id+"&setpage=/Ptrain/Resou/Film_Main_Set.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Content/setptraincontent.shtml"/>?"+paramV, 
        (fun == 1?"影音影视上传-新增":"影音影视上传-修改"), 700, 390, { buttons: {}});
    } 
    //详细页面
    function dataDeta(id){
		var url="<c:url value='/Ptrain/Content/setptraincontentwin.shtml'/>?ptrainContentBean.id="+id;
        parent.$.jBox.open("iframe:"+url, "详细", 650, 322, { buttons: {'关闭':false},
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
    //启用|禁止
    function operUsesign(id,usesign){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Content/updatePtrainContentUsesignByJq.shtml'/>",
					async:false,
					data:{'ptrainContentBean.id':id,'ptrainContentBean.usesign':usesign},
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
    //删除
    function delData(id){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Content/deletePtrainContentByJq.shtml'/>",
					async:false,
					data:{'ptrainContentBean.id':id},
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
						$("#form1").attr("action","<c:url value='/Ptrain/Content/updateptraincontentchk.shtml'/>").trigger("submit");
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
						$("#form1").attr("action","<c:url value='/Ptrain/Content/updateptraincontentchk.shtml'/>").trigger("submit");
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