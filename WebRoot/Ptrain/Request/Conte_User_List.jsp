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
        <div class="titleClass">员工加菜[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
                    专业类别： <select id="specid">
                    <option value="">全部</option>
                </select>
                    关键字：
                    <input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
                    <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
                    <input type="hidden" name="tokenid" value="${tokenid}"/>
                    <input type="hidden" name="sign" value="${sign}"/>
                    <input type="hidden" name="ptrainReqBean.id" id="id"/>
                    <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
                    <input type="hidden" name="record" value="${record}" id="record" />
                    <input type="hidden" name="querymap.reqtype" value="${querymap.reqtype}" />
                    <input type="hidden" name="querymap.requserid" value="${querymap.requserid}"/>
                    <input type="hidden" name="querymap.chksign" id="chksign"/>
                    
                    <input type="hidden" name="listpage" value="/Ptrain/Request/Conte_User_List.jsp" />
                    <input type="hidden" name="setpage" value="/Ptrain/Request/Conte_User_Set.jsp" />
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
        { title:'专业类别', name:'specid',width:90, align:'center',sortName:'r.specid',sortable: true, renderer: null},
        { title:'项目名称[↓]', name:'itemname' ,width:160, align:'left',sortName:'r.estatime desc,r.id desc',sortStatus:'desc',sortable: true, renderer: function(val,item,rowIndex){
        	return "<a href=\"JavaScript:dataDeta('"+item.id+"');\">"+val+"</a>";
        }},
        { title:'课程介绍', name:'itemdesc' ,width:260, align:'left', renderer: function(val,item,rowIndex){
        	return (val||"&nbsp;")+'<font style="display:none">'+item.id+'</font>';
        }},
        { title:'报名人数', name:'intflag' ,width:60, align:'center', renderer: null},
        { title:'*流程状态', name:'flowmark' ,width:100, align:'center', renderer: function(val,item,rowIndex){
        	var r='';
        	for(var i in stateList){
        		if(val==stateList[i].key){
        			r=stateList[i].desc;
        		}
        	}
        	if(''!=r)r='<a href="JavaScript:flowDeta(\''+item.id+'\');">'+r+'</a>';
        	return (r||"&nbsp;");
        }},
        { title:'操作', name:'state' ,width:178, align:'left', renderer: function(val,item,rowIndex){
        	if('0'==val){
	        	var r='';
	        	if('${XQSB}'==item.flowsta){
	        		r+='<button class="btn btn-info" onclick="chkData(\''+item.id+'\',\'1\')">申报</button>';
	        		r+=' <button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
	        		r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
	        	}else if('${XQSH}'==item.flowsta){
	        		r='<button class="btn btn-info" onclick="chkData(\''+item.id+'\',\'2\')">撤回</button>';
	        	}
	        	return r||"&nbsp;";
        	}
	        return "&nbsp;";
        }}
    ];
    //分页
    var paramsV={'querymap.unitid':'${querymap.unitid}','querymap.reqtype':'${querymap.reqtype}','querymap.requserid':'${querymap.requserid}','querymap.flowsta':'${querymap.flowsta}','querymap.specid':'${querymap.specid}','fields':'r.itemname','keyword':'${keyword}'}
    var mmg = $('#dataTable').mmGrid({
        //checkCol:true,
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
        $("#specid").bind("change",querydata);
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
    	//var newParams=$.merge(paramsV,{'querymap.specid':$("#specid").val(),'keyword':$("#searchkey").val()});
	    //默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.specid':$("#specid").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage};
	    mmg.load({'querymap.specid':$("#specid").val(),'keyword':$("#searchkey").val(),'tagpage':tagpage});
    }
    //新增、修改数据
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&ptrainReqBean.id="+id+"&querymap.reqtype=${querymap.reqtype}&querymap.requserid=${querymap.requserid}&querymap.specid="+$("#specid").val()+"&setpage=/Ptrain/Request/Conte_User_Set.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Req/setptrainreq.shtml"/>?"+paramV, 
        (fun == 1?"员工加菜-新增":"员工加菜-修改"), 900, 350, { buttons: {}});
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
    //删除
    function delData(id){
    	if(id == ''){
        	alert('参数错误！请刷新页面后重试');
          	return ;
        }
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
            return true; //close
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
						$("#form1").attr("action","<c:url value='/Ptrain/Req/updateptrainreqchk.shtml'/>").trigger("submit");
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
						$("#form1").attr("action","<c:url value='/Ptrain/Req/updateptrainreqchk.shtml'/>").trigger("submit");
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
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>