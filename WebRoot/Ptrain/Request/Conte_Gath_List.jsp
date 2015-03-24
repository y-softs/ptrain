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
        <div class="titleClass">汇总维护[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form1" id="form1" action="">
                    专业类别： <select name="querymap.specid" id="specid">
                    <option value="">全部</option>
                </select>
                &nbsp;<input type="checkbox" name="querymap.showsign" value="1" ${'1'==querymap.showsign?'checked':''} onclick="querydata();"/>仅显示待办班
                    关键字<input type="text" name="keyword" id="searchkey" class="text_10" value="${keyword}"/>
           <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
           <input type="hidden" name="tokenid" value="${tokenid}"/>
           <input type="hidden" name="sign" value="${sign}"/>
           <input type="hidden" name="ptrainReqBean.id" id="id"/>
           <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
           <input type="hidden" name="record" value="${record}" id="record" />
           <input type="hidden" name="querymap.opersign" id="opersign"/>
           <input type="hidden" name="savePath" value="${savePath}" />
                    
           <input type="hidden" name="querymap.flowstagath" value="${querymap.flowstagath}" />
           <input type="hidden" name="querymap.reqtypegath" value="${querymap.reqtypegath}" />
           <input type="hidden" name="querymap.state" value="${querymap.state}" />
           <input type="hidden" name="querymap.reqtype" id="reqtype" />
                    
           <input type="hidden" name="listpage" value="/Ptrain/Request/Conte_Gath_List.jsp" />
           <input type="hidden" name="setpage" value="/Ptrain/Request/Conte_Gath_Set.jsp" />
        </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
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
    var reqtypeList=${reqtypeList};
    var cols = [
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
        { title:'培训类别', name:'reqtype' ,width:60, align:'center', renderer: function(val,item,rowIndex){
        	var r='';
        	for(var i in reqtypeList){
        		if(val==reqtypeList[i].key){
        			r=reqtypeList[i].desc;
        		}
        	}
        	return r;
        }},
        { title:'办班时间', name:'maintime' ,width:100, align:'center', renderer: function(val,item,rowIndex){
        	return ('1'==item.state?val:'&nbsp;');
        }},
        { title:'报名人数', name:'intflag' ,width:60, align:'center', renderer: function(val,item,rowIndex){
        	return '<a href="JavaScript:setUserWin(\''+item.id+'\');">'+val+'</a>';
        }},
        { title:'办班状态', name:'state' ,width:60, align:'center', renderer: function(val,item,rowIndex){
        	return ('0'!=val)?'已办班':'待办班';
        }},
        { title:'操作', name:'state' ,width:198, align:'center', renderer: function(val,item,rowIndex){
	        var r='';
        	if('0'==val){
	        	if(item.intflag>=10){
	        		r+='<button class="btn btn-info" onclick="operClas(\''+item.id+'\',\'1\',\'${d.reqtype}\')">拟办班</button>';
	        		r+=' <button class="btn btn-info" onclick="setData(\''+item.id+'\')">修改</button>';
	        	}else{
	        		r+='<button class="btn btn-info" onclick="operClas(\''+item.id+'\',\'2\',\'${d.reqtype}\')">拟办班</button>';
	        		r+=' <button class="btn btn-info" onclick="setData(\''+item.id+'\')">修改</button>';
	        	}
        	}else{
        		r+='<button class="btn btn-info" onclick="operClas(\''+item.id+'\',\'3\',\'${d.reqtype}\')">再办班</button>';
        		r+=' <button class="btn btn-info" onclick="operClas(\''+item.id+'\',\'4\',\'${d.reqtype}\')">撤回</button>';
	        }
        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
        }}
    ];
    //分页
    var paramsV={'querymap.unitid':'${querymap.unitid}','querymap.reqtype':'${querymap.reqtype}','querymap.flowstagath':'${querymap.flowstagath}','querymap.reqtypegath':'${querymap.reqtypegath}','querymap.flowsta':'${querymap.flowsta}','querymap.specid':'${querymap.specid}','fields':'r.itemname','keyword':'${keyword}'}
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
        $("#expdata").bind("click",expExcel);
        $("#insert").bind("click",function(){setData('',1);});
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
    	var state='';
    	var chk=$('input[name="querymap.showsign"]:checked').size();
    	if(0!=chk)state='${querymap.state}';
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.specid':$("#specid").val(),'querymap.state':state,'keyword':$("#searchkey").val(),'tagpage':tagpage};
	    mmg.load({'querymap.specid':$("#specid").val(),'querymap.state':state,'keyword':$("#searchkey").val(),'tagpage':tagpage});
    }
    //修改数据
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&ptrainReqBean.id="+id+"&querymap.reqtype=${querymap.reqtype}&querymap.flowstagath=${querymap.flowstagath}&querymap.reqtypegath=${querymap.reqtypegath}&querymap.specid="+$("#specid").val()+"&setpage=/Ptrain/Request/Conte_Gath_Set.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Req/setptrainreq.shtml"/>?"+paramV,("汇总维护-修改"), 900, 450, { buttons: {}});
    }
    //详细页面
    function dataDeta(id){
		var url="<c:url value='/Ptrain/Req/setptrainreqwin.shtml'/>?ptrainReqBean.id="+id;
        parent.$.jBox.open("iframe:"+url, "详细", 650, 350, { buttons: {'关闭':false},
            submit: function (v, h, f) {
                return true;
            }
        });
    }
	//报名人员人员详细页面
	function setUserWin(reqid){
		var msg = "详细";
		var url = "<c:url value='/Ptrain/Requser/toforwardgathreqsign.shtml'/>";
			url+="?querymap.unitid=${querymap.unitid}";
			url+="&querymap.reqid="+reqid;
		parent.$.jBox.open("iframe:"+url, msg, 800, 550, { buttons: {'关闭':false},
	        submit: function (v, h, f) {
	            var we = h.find("#jbox-iframe")[0].contentWindow;
	            if(v == 0){
	               return true;  //close the window
	            }
	            return false;
	        }
	    });
	}
    //删除
    function delData(id){
    	id = id||"-1";
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
	//导出excel
	function expExcel(){
		document.form1.action="<c:url value='/Ptrain/Req/saveptrainreqexpexcel.shtml'/>";
		document.form1.submit();
	}
	//拟办班、再办班、撤回办班
	function operClas(id,opersign,reqtype){
		var msg="确定办班？";
	    if('2'==opersign)msg="点菜人数少于10人！确定办班？";
	    if('3'==opersign)msg="确定再办班？";
	    if('4'==opersign)msg="确定撤销？";
	    $("#id").val(id);
	    $("#opersign").val(opersign);
	    $("#reqtype").val(reqtype);
		parent.$.jBox.confirm(msg, "提示", function (v, h, f) {
            	if (v == 'ok'){
					document.form1.action="<c:url value='/Ptrain/Req/saveptrainreqclas.shtml'/>";
					document.form1.submit();
            	}
            	else if (v == 'cancel')
            	return true;
            }
        );
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