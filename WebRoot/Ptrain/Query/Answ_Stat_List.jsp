<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title></title>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/datagrid.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/grid.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/plugins.js"></script>
</head>
<body>
<!-- 后面新增的样式 -->
<div id="container">
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">应用统计[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
        	<span style="float:left;width=50%">
        	&nbsp;按<select name="querymap.sortsign" id="sortsign">
						<option value="1" ${'1'==querymap.sortsign?'selected':''}>部门</option>
						<option value="2" ${'2'==querymap.sortsign?'selected':''}>专业</option>
					</select>统计
			&nbsp;答题年月：<select name="querymap.year" id="year">
							<c:forEach items="${yearList}" var="y">
								<option value="${y.yearvalue}" ${y.yearvalue==querymap.year?'selected':''}>${y.yeartext}</option>						
							</c:forEach>
						  </select>
						<select name="querymap.month" id="month">
							<c:forEach items="${monthList}" var="m">
								<option value="${m.monthvalue}" ${m.monthvalue==querymap.month?'selected':''}>${m.monthtext}</option>						
							</c:forEach>
						</select>
						<input type="hidden" name="querymap.reachnum" value="${querymap.reachnum}" />
						<input type="hidden" name="sortfield" value="${sortfield}" id="sortfield" />
						<input type="hidden" name="querymap.centerunitid" value="${querymap.unitid}" />
<%--				        <input type="button" class="btn btn-primary" name="search" id="b_search" value="查询" />--%>
				        <input type="hidden" name="querymap.unitid" id="b_unitid" value="${querymap.unitid}" />
        	</span>			
        	<span style="float:right;width=50%"><span id="mkstr"></span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				        
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup"></div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <div class="gridDiv">
        <div class="btnGroup">
            <button class="btn btn-info" id="b_expexcel">导出Excel</button>
        </div>
    </div>
</div>
<script type="text/javascript">
	var colname = "专业";
	var sortsign = '${querymap.sortsign}';
	var t_usernum = 0;
	var t_reachnum = 0;
	var t_scorenum = 0;
	
    //列
    var i=0,j=0,k=0,myIndex
    var cols = [
    	{ title:'序号', name:'ID' ,width:60,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
    		sortsign = $("#sortsign").val();
             if(sortsign == '1'){
             	if(0==rowIndex){
	                i=0;
	                j=0;
	                k=0;
            	}
	            if(parseInt(item.DATALEVEL)==1){
	                i+=1;
	                j=0;
	                myIndex=i;
	            }else if(parseInt(item.DATALEVEL)==2){
	                j+=1;
	                k=0;
	                myIndex=i+'.'+j;
	            }else if(parseInt(item.DATALEVEL)==3){
	                k+=1;
	                myIndex=i+'.'+j+'.'+k;
	            }
            	return myIndex;
             }else{
             	return rowIndex+1;
             }
        }},
        { title:'名称', name:'APPNAME' ,width:280, align:'left'},
        { title:'参加人数', name:'USERNUM' ,width:130, align:'center',renderer: function(val,item,rowIndex){
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	t_usernum += parseInt(val);
             	return "<a href=\"JavaScript:setDataWin('"+item.ID+"','"+item.DATALEVEL+"','');\">"+val+"</a>";
             }
        }},
        { title:'达标人数', name:'REACHNUM' ,width:120, align:'center',sortName:' app.sortnum',sortable: true,renderer: function(val,item,rowIndex){
             var usernum = item.USERNUM;
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	t_reachnum += parseInt(val);
             	return "<a href=\"JavaScript:setDataWin('"+item.ID+"','"+item.DATALEVEL+"','1');\">"+val+"</a>";
             }
        }},
        { title:'未达标人数', name:'' ,width:120, align:'center',sortName:'app.sortnum',sortable: true,renderer: function(val,item,rowIndex){
             var usernum = item.USERNUM;
             if((usernum == "")||(usernum == null)){
             	return '&nbsp;';
             }else{
             	return "<a href=\"JavaScript:setDataWin('"+item.ID+"','"+item.DATALEVEL+"','2');\">"+(item.USERNUM-item.REACHNUM)+"</a>";
             }
        }},
        { title:'达标比例', name:'REACHPER' ,width:120, align:'center',sortName:'app.sortnum',sortable: true,renderer: function(val,item,rowIndex){
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	return val+"%";
             }
        }},
        { title:'人均得分', name:'AVESCORE' ,width:120, align:'center',sortName:'app.sortnum',sortable: true,renderer: function(val,item,rowIndex){
             var usernum = item.USERNUM;
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	t_scorenum += parseFloat(val*usernum);
             	return val;
             }
        }}
    ];
    
	var url = "<c:url value='/Ptrain/Ask/listptrainaskappstat.shtml'/>";
	var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.sortsign':'${querymap.sortsign}',
		'querymap.year':'${querymap.year}',
		'querymap.month':'${querymap.month}',
    	'querymap.reachnum':'${querymap.reachnum}'
    }
    
    //分页
    var mmg = $('#dataTable').mmGrid({
        height:'400',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post'
    });
	
	//加载完成后
	mmg.on('loadSuccess', function(e, data){
		//这个事件需要在数据加载之前注册才能触发
		var usernum = t_usernum;           //参加总人数
		var reachnum = t_reachnum;         //达标人数
		var scorenum = t_scorenum;         //达标人数
		if(sortsign == '1'){
        	usernum = parseInt(usernum/2);
        	reachnum = parseInt(reachnum/2);
        	scorenum = parseFloat(scorenum/2);
       	}
		var noreachnum = (usernum-reachnum);   //未达标人数
		var reachrate = ((parseInt(reachnum)/usernum)*100)+'%';    //达标
		var avescore = parseFloat(scorenum/usernum).toFixed(2);   //平均得分
		var mkstr = "<font color='red'>参加总人数：</font>"+usernum+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		    mkstr += "<font color='red'>达标人数：</font>"+reachnum+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		    mkstr += "<font color='red'>未达标人数：</font>"+noreachnum+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		    mkstr += "<font color='red'>达标比例：</font>"+reachrate+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		    mkstr += "<font color='red'>平均得分：</font>"+avescore;
		$("#mkstr").html(mkstr);
	});
	
	$(function(){
		//专业,部门/年/月
		$("#sortsign,#year,#month").bind("change",function(){
			toSearch();
		});
		
		//查询
		$("#b_search").bind("click",function(){
			toSearch();
		});
		
    	//导出Excel
    	$("#b_expexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Ask/saveptrainaskappstatexpexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    	
    });
    
    //查询
    function toSearch(){
    	
        t_usernum = 0;
		t_reachnum = 0;
		t_scorenum = 0;
		
	    var unitid = $("#b_unitid").val();      //单位id
	    var sortsign = $("#sortsign").val();    //部门,专业标识
	    var year = $("#year").val();            //年度
	    var month = $("#month").val();          //月份
	    var reachnum = '${querymap.reachnum}';  //达标
		//点击查询时页码置为1
		var tagpage = '1';
	    mmg.load();
    }
    
    //设置
	function setDataWin(id,datalevel,showsign){
		var sortsign = $("#sortsign").val();    //部门,专业标识
		var operStr = '';
			if(sortsign == '2'){
				operStr='&querymap.specid='+id;
			}else{
				operStr='&querymap.deptid='+id;
			}
			if(datalevel=='2')operStr='&querymap.groupid='+id;
			if(datalevel=='3')operStr='&querymap.subgroupid='+id;
			
			var url="<c:url value='/Ptrain/Ask/toforwardqueryaskuserwin.shtml'/>?querymap.unitid=${querymap.unitid}";
				url+="&querymap.reachnum=${querymap.reachnum}";
				url+="&querymap.year=${querymap.year}";
				url+="&querymap.month=${querymap.month}";
				url+=operStr;
				url+="&querymap.sortsign="+$("#sortsign").val();
			if(showsign=='1')url+="&querymap.showsign1=1";
			if(showsign=='2')url+="&querymap.showsign2=1";
		var msg = '详细';
	    parent.$.jBox.open("iframe:"+url, msg, 900, 520, { buttons: {'关闭':false},
	        submit: function (v, h, f) {
	            var we = h.find("#jbox-iframe")[0].contentWindow;
	            if(v == 0){
	               return true;
	            }
	        }
	    });
	}
</script>
</body>
</html>