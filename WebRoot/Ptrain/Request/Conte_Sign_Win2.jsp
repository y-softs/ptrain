<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title></title>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/datagrid.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/grid.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/grid-pagi.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/plugins.js"></script>
</head>
<body>
<!-- 后面新增的样式 -->
<div id="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
    	<div class="titleClass">报名人员详细[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
    	<form method="post" name="form" id="form1" action="">
     		&nbsp;&nbsp;部门班组：
    		    <select name="querymap.deptid" id="deptid">
      				<option value="">全部</option>
          		</select>
                <select name="querymap.groupid" id="groupid">
           			<option value="">全部</option>
                </select>
           		<select name="querymap.subgroupid" id="subgroupid">
					<option value="">全部</option>
                </select>
				&nbsp;关键字：<select name="fields" id="fields">
			                    <option value="u.username" ${'u.username'==fields?'selected':''}>姓名</option>
			                    <option value="u.userid" ${'u.userid'==fields?'selected':''}>身份证号</option>
		                    </select>
                    	<input type="text" name="keyword" id="keyword" class="text_10" value="${keyword}" maxlength="10" />
						<input type="button" name="btn_query" value="查询" onclick="toSearch();" />
						<input type="hidden" name="querymap.unitid" value="${querymap.unitid}" />
						<input type="hidden" name="querymap.reqid" value="${querymap.reqid}" />
	    </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div class="btnGroup">
        </div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
</div>

<script type="text/javascript">
	var _searparam;
    //列
    var cols = [
    	{ title:'序号', name:'' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'部门名称', name:'deptname' ,width:180, align:'left'},
        { title:'班组名称', name:'groupname' ,width:180, align:'left'},
        { title:'姓名[↑]', name:'userid' ,width:80, align:'center', renderer: function(val,item,rowIndex){
        	return "<span title=\"工号："+item.workid+"\" style=\"cursor:pointer\">"+val+"</span>"
        }},
        { title:'报名时间', name:'regdate' ,width:180, align:'center'}
    ];
    
	var url = "<c:url value='/Ptrain/Requser/listptrainrequsersign_que.shtml'/>";
	var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.reqid':'${querymap.reqid}'
    }
	
	//分页
    var mmg = $('#dataTable').mmGrid({
    	height:'350',
    	width:'100%',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post',
        root: 'items',
        fullWidthRows: true,
        plugins : [
            $('#dataPagi').mmPaginator(),
        ]
    });
    
	$(function(){
		//构造部门列表
		mkDeptList();
		$("#groupid,#subgroupid").hide();
		
		//部门change事件触发班组下拉列表框
		$("#deptid").bind("change",function(){
			var deptid = $("#deptid").val();
			if(deptid == ''){
				$("#groupid,#subgroupid").val('');
				$("#groupid,#subgroupid").hide();
				toSearch();
				return;
			}else{
				$("#groupid").val('');
				$("#subgroupid").hide();
			}
			$.ajax({
				type: "POST", 
				url: "<c:url value='/Ptrain/Postuser/findPtrainDeptGroupByJq.shtml'/>", //代码数据
				async:false,
				data:{'querymap.deptid':deptid},
				dataType:"json",
				error:function(data){
				    parent.$.jBox.tip("与数据库连接失败！", 'info');
				},
				success:function(data) {
					if(data.length==0){
						$("#groupid").hide();
					}else{
						var str = "";
						//通过jquery方法进行循环编译
						$.each(data,function(i,d){ 
							str += "<option value="+d.id+" >"+d.organname+"</option>";
						});
						//先清空在添加
						$("#groupid option").not(":first").remove();
						$("#groupid").append(str);
						$("#groupid").show();
					}
				}
			});
			toSearch();
		});
		
		//班组change事件触发班组下拉列表框
		$("#groupid").bind("change",function(){
			var groupid = $("#groupid").val();
			if(groupid == ''){
				$("#subgroupid").val('');
				$("#subgroupid").hide();
				toSearch();
				return;
			}
			$.ajax({
				type: "POST", 
				url: "<c:url value='/Ptrain/Postuser/findPtrainDeptGroupByJq.shtml'/>", //代码数据
				async:false,
				data:{'querymap.groupid':groupid},
				dataType:"json",
				error:function(data){
				    parent.$.jBox.tip("与数据库连接失败！", 'info');
				},
				success:function(data) {
					if(data.length==0){
						$("#subgroupid").hide();
					}else{
						var str = "";
						//通过jquery方法进行循环编译
						$.each(data,function(i,d){ 
							str += "<option value="+d.id+" >"+d.organname+"</option>";
						});
						//先清空在添加
						$("#subgroupid option").not(":first").remove();
						$("#subgroupid").append(str);
						$("#subgroupid").show();
					}
				}
			});
			toSearch();
		});
		//子班组change事件
		$("#subgroupid").bind("change",toSearch);
		
    	//导出Excel
    	$("#b_expexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Requser/saveptrainrequsersignexpexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    });
    
    //构造部门列表
    function mkDeptList(){
    	$.ajax({
             type: 'post',
             async: false,
             url: '<c:url value='/Ptrain/Postuser/findPtrainDeptByJq.shtml'/>',
             data: {'querymap.unitid':'${querymap.unitid}'},
             dataType: 'json',
             cache: false,
             success:function(data){
             	var str = "";
				//通过jquery方法进行循环编译
				$.each(data,function(i,d){
					var sel = ""
					if('${querymap.deptid}'==d.id){
						sel = "selected";
					}
					str += "<option "+sel+" value="+d.id+" >"+d.organname+"</option>";
				});
				$("#deptid").append(str);
             },
			error:function(msg){
			    parent.$.jBox.tip("与数据库连接失败！", 'info');
			}
        });
    }
    
    //查询
    function toSearch(){
		//点击查询时页码置为1
		var tagpage = '1';
	    var fields = $("#fields").val();      //关键字段
	    var keyword = $("#keyword").val();    //关键字
		var deptid = $("#deptid").val();     //部门
		var groupid = $("#groupid").val();    //班组
		var subgroupid = $("#subgroupid").val();  //子班组
		//防止滚动上一下,下一页无法传递传参
		_searparam = {'querymap.deptid':deptid,
	                  'querymap.groupid':groupid,
	                  'querymap.subgroupid':subgroupid,
	                  'fields':fields,
	                  'keyword':keyword};
		mmg.load({'querymap.deptid':deptid,
	              'querymap.groupid':groupid,
	              'querymap.subgroupid':subgroupid,
	              'fields':fields,
	              'keyword':keyword,
	              'tagpage':tagpage});
    }
</script>
</body>
</html>