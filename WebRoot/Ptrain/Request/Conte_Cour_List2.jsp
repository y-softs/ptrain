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
	<script language="javascript" src="<c:url value='/Script/Main.js'/>"></script>
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
        <form name="form2" action="" method="post">
                  <c:if test="${empty querymap.specidchk}">&nbsp;专业类别：<select name="ptrainReqtempBean.specid" id="specid">
              		<option value="">全部</option>
                  </select>
                  </c:if>
                  <c:if test="${not empty querymap.specidchk}">&nbsp;专业类别：<select name="specid" id="specid" disabled>
              		<option value="">全部</option>
                  </select>
                  <input type="hidden" name="ptrainReqtempBean.specid" value="${ptrainReqtempBean.specid}"/>
                  </c:if>
                  &nbsp;记录状态：<input type="radio" name="querymap.showsign1" value="" ${empty querymap.showsign1||''==querymap.showsign1?'checked':''} onclick="querydata();"/>全部
                                <input type="radio" name="querymap.showsign1" value="-1" ${'-1'==querymap.showsign1?'checked':''} onclick="querydata();"/>异常
                   	 关键字：<input type="text" name="searchkey" id="searchkey" class="text_10" value=""/>
                    <input type="button" name="search" id="search" class="btn btn-primary" value="搜索" onclick="querydata();"/>
                    <input type="hidden" name="tokenid" value="${tokenid}"/>
                    <input type="hidden" name="sign" value="${sign}"/>
                    <input type="hidden" name="ptrainReqtempBean.id" id="id"/>
                    <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                    <input type="hidden" name="querymap.specid" value="${querymap.specid}"/>
                    <input type="hidden" name="querymap.reqtype" value="${querymap.reqtype}"/>
                    <input type="hidden" name="querymap.impsign" value="${querymap.impsign}"/>
                    <input type="hidden" name="querymap.specidchk" value="${querymap.specidchk}"/>
                    <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
                    <input type="hidden" name="record" value="${record}" id="record" />
                    <input type="hidden" name="listpage" value="${listpage}" />
                    <input type="hidden" name="listtemppage" value="${listtemppage}" />
                    <input type="hidden" name="settemppage" value="${settemppage}" />
                    <input type="hidden" name="savePath" value="${savePath}" />
        </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="impAll">全部导入</button>
            <button class="btn btn-info" id="impData">批量导入</button>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <button class="btn btn-danger" id="canData">取消</button>
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
        { title:'', name:'id' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
        	var r=('1'!=item.datasign)?'disabled':'checked';
            return '<input type="checkbox" name="chk" '+r+' value="'+val+'"/>';
        }},
        { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', renderer: function(val,item,rowIndex){
            return rowIndex+1;
        }},
        { title:'专业类别', name:'spectemp' ,width:180, align:'center',sortName:'r.specid',sortable: true, renderer: function(val,item,rowIndex){
        	var r=('-1'==item.datasign&&''==item.specid)?'<span class="fontcolor_red_b">！</span>':''
        	return r+val;
        }},
        { title:'项目名称', name:'itemname' ,width:260, align:'left',sortName:'r.estatime desc,r.id desc',sortStatus:'desc',sortable: true, renderer: function(val,item,rowIndex){
        	return (val||"&nbsp;");
        }},
        { title:'课程介绍', name:'itemdesc' ,width:180, align:'left', renderer: function(val,item,rowIndex){
        	return (val||"&nbsp;");
        }},
        { title:'操作', name:'' ,width:110, align:'center', renderer: function(val,item,rowIndex){
        	var r='<button class="btn btn-info" onclick="setData(\''+item.id+'\',2)">修改</button>';
        	r+=' <button class="btn btn-danger" onclick="delData(\''+item.id+'\')">删除</button>';
        	return r;
        }}
    ];
    //分页
    var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.reqtype':'${querymap.reqtype}',
    	'ptrainReqtempBean.specid':'${ptrainReqtempBean.specid}',
    	'fields':'r.itemname'
    }
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: '<c:url value="/Ptrain/Req/listPtrainReqtempByJq.shtml"/>',
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
        $("#impAll").bind("click",function(){impData(1);});
        $("#impData").bind("click",function(){impData(2);});
        $("#canData").bind("click",canData);
        specList();
        $("#specid").bind("change",querydata);
    });
    //数据搜索
    function querydata(){
    	var keyword=($("#searchkey").val());
    	var showsign=$("input[name='querymap.showsign1']:checked").val();
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'ptrainReqtempBean.specid':$("#specid").val(),'keyword':keyword,'querymap.showsign1':showsign,'tagpage':tagpage};
	    mmg.load({'ptrainReqtempBean.specid':$("#specid").val(),'keyword':keyword,'querymap.showsign1':showsign,'tagpage':tagpage});
    }
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
             		var chk=data[i].id=='${ptrainReqtempBean.specid}'?'selected':'';
             		$("#specid").append('<option value="'+data[i].id+'" '+chk+'>'+data[i].codename+'</option>');
             	}
             }
        });
    }
    //修改数据
    function setData(id,fun){
    	var paramV="querymap.unitid=${querymap.unitid}&ptrainReqtempBean.id="+id+"&querymap.reqtype=${querymap.reqtype}&querymap.specid="+$("#specid").val()+"&setpage=/Ptrain/Request/Conte_Cour_Set1.jsp&fun="+fun;
        parent.$.jBox.open("iframe:<c:url value="/Ptrain/Req/setptrainreqtempwin.shtml"/>?"+paramV, 
        (fun == 1?"新增":"课件培训点菜-修改"), 900, 380, { buttons: {}});
    }
    //删除
    function delData(id){
    	id = id || "-1";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Req/deletePtrainReqtempByJq.shtml'/>", 
					async:false,
					data:{'ptrainReqtempBean.id':id},
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
	//批量导入、全部导入
	function impData(sign){
		var url="<c:url value='/Ptrain/Req/saveptrainreqexcelall.shtml'/>";
        var submit = function (v, h, f) {
            if (v == 'ok'){
				if(2==sign){//批量导入				
					var idArr=new Array(),hasData='';
					$("input[name='chk']:checked").each(function(){
						hasData='1';
						idArr.push($(this).val());
					});
					$("#id").val(idArr.join(","));
					url="<c:url value='/Ptrain/Req/saveptrainreqexcel.shtml'/>";
			        if(hasData==''){
			            parent.$.jBox.tip("请选择要操作的记录！", 'warning');
			            return false;
			        }
				}else{//全部导入
					$("#tagpage").val("1");
					$("#keyword").val("");
				}
				openProcessDiv();
				document.form2.action=url;
				document.form2.submit();
            }
            return true;
        };
        parent.$.jBox.confirm("确定导入记录？", "提示", submit);
	}
	//取消
	function canData(){
        var submit = function (v, h, f) {
            if (v == 'ok'){
				$("#tagpage").val("1");
				$("#keyword").val("");
				document.form2.action="<c:url value='/Ptrain/Req/setptrainreqtempcan.shtml'/>";
				document.form2.submit();
            }
            return true;
        };
       	parent.$.jBox.confirm("确定取消导入？若是,系统自动跳转到导入前页面!", "提示", submit);
	}
	//子页面重新刷新父页面
	function reloadPage(){
		querydata();
	}
</script>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>