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
        <div class="titleClass">菜单审批[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form1" id="form1" action="">
                    专业类别： <select id="specid">
                    <option value="">全部</option>
                </select>
                    &nbsp;<input type="checkbox" name="querymap.showsign" value="1" ${'1'==querymap.showsign?'checked':''} onclick="querydata();"/>仅显示待审核
                    关键字：
    		 <input type="hidden" name="fields" value="r.itemname"/>
             <input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
             <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
             <input type="hidden" name="tokenid" value="${tokenid}" />
             <input type="hidden" name="sign" value="${sign}" />
             <input type="hidden" name="ptrainReqBean.id" id="id" />
             <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
             <input type="hidden" name="record" value="${record}" id="record" />
             <input type="hidden" name="querymap.reqtype" value="${querymap.reqtype}" />
             <input type="hidden" name="querymap.requserid" value="${querymap.requserid}"/> 
             <input type="hidden" name="querymap.chksign" id="chksign" />
             <input type="hidden" name="savePath" value="${savePath}" />
             
             <input type="hidden" name="listpage" value="/Ptrain/Request/Conte_Uchk_List.jsp" />
    		
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="chkPl">批量审核</button>
            <button class="btn btn-info" id="fileDeta">展开附件</button>
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
    var stateList=${stateList};
    var cols = [
        { title:'', name:'id' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
        	var r=('${XQSH}'!=item.flowsta)?'disabled':'';
            return '<input type="checkbox" name="chk" '+r+' value="'+val+'"/>';
        }},
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'专业类别', name:'specid',width:90, align:'center',sortName:'r.specid',sortable: true, renderer: null},
        { title:'项目名称', name:'itemname' ,width:260, align:'left',sortName:'r.estatime desc,r.id desc',sortStatus:'desc',sortable: true, renderer: function(val,item,rowIndex){
        	return "<a href=\"JavaScript:dataDeta('"+item.id+"');\">"+val+"</a>";
        }},
        { title:'课程介绍', name:'itemdesc' ,width:260, align:'left', renderer: function(val,item,rowIndex){
        	return (val||"&nbsp;")+'<font style="display:none">'+item.id+'</font>&nbsp;';
        }},
        { title:'发起人', name:'requserid' ,width:60, align:'center', renderer: function(val,item,rowIndex){
        	return val+"&nbsp;";
        }},
        { title:'状态', name:'state' ,width:60, align:'center', renderer: function(val,item,rowIndex){
        	return ('0'!=val)?'已办班':'待办班';
        }},
        { title:'*流程状态', name:'flowmark' ,width:100, align:'center', renderer: function(val,item,rowIndex){
        	var r='';
        	for(var i in stateList){
        		if(val==stateList[i].key){
        			r=stateList[i].desc;
        		}
        	}
        	if(''!=r)r='<a href="JavaScript:flowDeta(\''+item.id+'\');">'+r+'</a>'
        	else r='&nbsp;';
        	return r;
        }},
        { title:'操作', name:'state' ,width:150, align:'left', renderer: function(val,item,rowIndex){
        	if('0'==val){
	        	var r='';
	        	if('${XQSH}'==item.flowsta){
	        		r='<button class="btn btn-info" onclick="chkData(\''+item.id+'\',\'1\')">审核</button>';
	        		r+=' <button class="btn btn-info" onclick="chkData(\''+item.id+'\',2)">退回</button>';
	        	}else{r="&nbsp;";}
	        	return r;
        	}
	        return "&nbsp;";
        }}
    ];
    //分页
    var paramsV={'querymap.unitid':'${querymap.unitid}','querymap.reqtype':'${querymap.reqtype}','querymap.reqtypestr':'${querymap.reqtypestr}','querymap.flowsta':'${querymap.flowsta}','querymap.specid':'${querymap.specid}','fields':'r.itemname','keyword':'${keyword}'}
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Req/listPtrainReqByJq.shtml"/>',
        params:paramsV,
        method: 'post',
        root: 'items',
		nowarp:true,
        remoteSort:true,
        plugins : [
            $('#dataPagi').mmPaginator()
        ]
    });
    $(function(){
        $("#chkPl").bind("click",function(){
        	var chk=$('input[name="chk"]:checked').size();
        	if(0==chk){
        		parent.$.jBox.info('请选择要操作的记录！','提示');
        		return;
        	}
        	chkData('',1);
        });
        specList();
        $("#specid").bind("change",querydata);
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
    function querydata(){
    	var keyword=($("#searchkey").val());
    	var flowstathis='';
    	var chk=$('input[name="querymap.showsign"]:checked').size();
    	if(0!=chk)flowstathis='${flowstathis}';
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.specid':$("#specid").val(),'keyword':keyword,'querymap.flowstathis':flowstathis,'tagpage':tagpage};
	    mmg.load({'querymap.specid':$("#specid").val(),'keyword':keyword,'querymap.flowstathis':flowstathis,'tagpage':tagpage});
    }
	//审核/退回
	function chkData(id,sign){
	    var newid = id,setpage='/Ptrain/Request/Conte_Uchk_Win1.jsp',querylist='';
		if('1'==sign){//审核
	        if(''==id){
				var idAttr=new Array();
				$.each($('input[name="chk"]'),function(i, n){
					if(n.checked)idAttr.push(n.value);
				});
				newid = idAttr.join(",");
				setpage='/Ptrain/Request/Conte_Uchk_Win2.jsp';
				querylist='true';
	        }
			var url = "<c:url value='/Ptrain/Req/setptrainreqchk.shtml'/>?ptrainReqBean.id="+newid;
			url+="&querymap.unitid=${querymap.unitid}";
			url+="&setpage="+setpage;
			url+="&querymap.querylist="+querylist;
        	parent.$.jBox.open("iframe:"+url, (''==id?"菜单审批-批量审核":"菜单审批-审核"), 900, (''==id?450:470), { buttons: {}});
		}else if('2'==sign){//退回
			parent.$.jBox.confirm("确定退回记录？", "提示", function (v, h, f) {
	            	if (v == 'ok'){
						$("#chksign").val('${TUI_HUI}');
					    $("#id").val(newid);
						document.form1.action="<c:url value='/Ptrain/Req/updateptrainreqchk.shtml'/>";
						document.form1.submit();
	            	}
	            	else if (v == 'cancel')
	            	return true;
	            }
	        );
		}
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
	//附件加载
    function fileDeta(){
		$.each($("#dataTable tbody tr"),function(){
			var obj=$("td",$(this)).eq(4),recid=$("font",obj).html();
			if(''!=recid){
				var url="<c:url value='/Ptrain/File/listPtrainFileByJq.shtml'/>";
				var res=jqueryOper(url,{'modsign':'${modsign}','recid':recid},obj);
			}
		});
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
		querydata();
	}
</script>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>