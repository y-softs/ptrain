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
	<script type="text/javascript" src="../../Script/plugin/grid/grid-pagi.js"></script>
	<script type="text/javascript" src="../../Script/plugin/grid/plugins.js"></script>
</head>
<body>
<!-- 后面新增的样式 -->
<div id="container">
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">试题库-题库导入-预览[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
		<form method="post" name="form1" id="form1" action="">
		专业类别：<select name="querymap.specidimp" id="specidimp">
          		<option value="">全部</option>
                  <c:forEach items="${specList}" var="p">
          			<option value="${p.id}" ${p.id==querymap.specidimp?'selected':''}>${p.codename}</option>
          		</c:forEach>
              </select>
              &nbsp;试题类型：<select name="querymap.typeid" id="typeid">
          		<option value="">全部</option>
                  <c:forEach items="${typeList}" var="p">
          			<option value="${p.id}" ${p.id==querymap.typeid?'selected':''}>${p.codename}</option>
          		</c:forEach>
              </select>
              <input type="checkbox" name="querymap.showsign1" id="showsign1" value="1"/>仅显示待导入记录
              <input type="checkbox" name="querymap.showsign2" id="showsign2" value="2"/>仅显示异常记录
                                关键字：<select name="fields" id="b_fields">
                      <option value="q.topic" selected>题目</option>
                  </select>
		        <input type="text" name="keyword" id="b_keyword" value="${keyword}" />
		        <input type="button" name="search" class="btn btn-primary" id="b_search" value="搜索" />
		        
                <input type="hidden" name="ptrainQuestionstempBean.id" id="id"/>
                <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
                <input type="hidden" name="record" value="${record}" id="record" />
                <input type="hidden" name="querymap.impsign" value="${querymap.impsign}"/>
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="btn_all">全部导入</button>
            <button class="btn btn-info" id="btn_batch">批量导入</button>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <button class="btn btn-danger" id="btn_cancel">取消</button>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <button class="btn btn-info" id="btn_back">返回</button>
        </div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
</div>
<script type="text/javascript">
	var specMap = {};
	$("#specidimp option").each(function(i,dom){
		if($(this).val() != "")
			specMap[""+$(this).val()] = $(this).text();
	});
    //列
    var cols = [
    	{ title:'', name:'id' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
    		if(item.datasign !='1'){
             	return '<input type="checkbox" name="chk" value="'+val+'" disabled/>';
    		}else{
             	return '<input type="checkbox" name="chk" value="'+val+'"/>';
    		}
        }},
    	{ title:'序号', name:'id' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'专业类别', name:'kindid' ,width:150, align:'center',renderer:function(val,item,rowIndex){
        	return specMap[val];
        }},
        { title:'题型', name:'typetemp' ,width:80, align:'center'},
        { title:'题目[↑]', name:'topic' ,width:550, align:'left',renderer: function(val,item,rowIndex){
        	if(item.datasign == "-1"){
               return '[<span class="fontcolor_red">待修改</span>]'+val;
            }else{
            	return val;
            }
        }},
        { title:'操作', name:'usesign' ,width:220, align:'left', lockWidth:true, lockDisplay: true, renderer: function(val,item,rowIndex){
        	return "<button class=\"btn btn-info\" onClick=setData("+item.id+");>修改</button>&nbsp;<button class=\"btn btn-danger\" onClick=deleteOper("+item.id+");>删除</button>"
        }}
    ];
	var url = "<c:url value='/Ptrain/Questions/listptrainquestionstemp.shtml'/>";
	var paramsV = {
		'querymap.unitid':'${querymap.unitid}',
		'querymap.specidimp':'${querymap.specidimp}',
		'querymap.typeid':'${querymap.typeid}'};
    //分页
    var mmg = $('#dataTable').mmGrid({
        indexColWidth: 35,
        height:'420',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post',
        root: 'items',
		nowarp:true,
        plugins : [
            $('#dataPagi').mmPaginator()
        ]
    });
    
	$(function(){
		$("#b_search").bind("click",toSearch);
		$("#specidimp,#typeid").bind("change",toSearch);
		$("#showsign1,#showsign2").bind("click",toSearch);
    	//新增
    	$("#btn_back").bind("click",function(){
    		$("#form1").attr("action","<c:url value='/Ptrain/Questions/setptrainquestionstempexcel.shtml'/>").trigger("submit");
    	});
    	//全部导入
    	$("#btn_all").bind("click",function(){
    		impData(1);
    	});
    	//批量导入
    	$("#btn_batch").bind("click",function(){
    		impData(2);
    	});
    	//取消
    	$("#btn_cancel").bind("click",function(){
	        var submit = function (v, h, f) {
	            if (v == 'ok'){
					$("#tagpage").val("1");
					$("#typeid,#keyword").val("");
					document.form1.action="<c:url value='/Ptrain/Questions/setptrainquestionstempcan.shtml'/>";
					document.form1.submit();
	            }
	            return true;
	        };
        	parent.$.jBox.confirm("确定取消导入？若是,系统自动跳转到导入前页面!", "提示", submit);
    	});
    });
    
    //查询
    function toSearch(){
		//点击查询时页码置为1
	    var specidimp = $("#specidimp").val();
	    var typeid = $("#typeid").val();
	    var showsign1 = $("#showsign1").is(":checked")?$("#showsign1").val():"";
	    var showsign2 = $("#showsign2").is(":checked")?$("#showsign2").val():"";
	    var fields = $("#b_fields").val();      //关键字段
	    var keyword = $("#b_keyword").val();    //关键字
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.specidimp':specidimp,'querymap.typeid':typeid,'querymap.showsign1':showsign1,'querymap.showsign2':showsign2,'fields':fields,'keyword':keyword,'tagpage':tagpage};
	    mmg.load({
	    		'querymap.specidimp':specidimp,
	    		'querymap.typeid':typeid,
	    		'querymap.showsign1':showsign1,
	    		'querymap.showsign2':showsign2,
	    		'fields':fields,
	    		'keyword':keyword,
	    		'tagpage':tagpage
	    });
    }
    
	//清除
	function deleteOper(id){
    	if(id == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var url = "<c:url value='/Ptrain/Questions/deleteptrainquestionstemp.shtml'/>";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url: url, //代码数据
					async:false,
					dataType:"json",
					data:{'ptrainQuestionstempBean.id':id},
					error:function(data){
					    parent.$.jBox.tip("与数据库连接失败！", 'info');
					},
					success:function(data) {
						if(data == '1'){
	            			parent.$.jBox.tip("操作成功！", 'info');
		                   toSearch();
						}else{
	            			parent.$.jBox.tip("操作失败！", 'info');
						}
					}
				});
            else if (v == 'cancel')
                parent.$.jBox.tip("操作失败！", 'info');
            return true;
        };
        parent.$.jBox.confirm("确定删除该记录？", "提示", submit);
	}
	
    //修改代码设置
	function setData(id){
		var url = "<c:url value='/Ptrain/Questions/setptrainquestionstempwin.shtml'/>";
		var msg =  '试题库-题库导入-修改';
		url = url+"?ptrainQuestionstempBean.id="+id;
		url = url+"&querymap.unitid=${querymap.unitid}";
	    parent.$.jBox.open("iframe:"+url, msg, 720, 520, { buttons: {'确定':true,'关闭':false},
	        submit: function (v, h, f) {
	            var we = h.find("#jbox-iframe")[0].contentWindow;
	            if(v == 0){
	               return true; 
	            }else{
	               var rtn = we.callBack();
	               //是否验证通过
	               if(rtn){
	               		parent.$.jBox.tip("操作成功！", 'info');
		                toSearch();
	               }
	               return rtn;
	            }
	            return false;
	        }
	    });
	}
	//批量导入、全部导入
	function impData(sign){
		var url="<c:url value='/Ptrain/Questions/saveprrainquesexcelall.shtml'/>";
		var submit = function (v, h, f) {
            if (v == 'ok'){
				if(2==sign){//批量导入[获取选中的记录]
					var idArr=new Array();
					$("input[name='chk']:checked").each(function(){
						idArr.push($(this).val());
					});
					var param = {
								'querymap.specidimp':'${querymap.specidimp}',
								'querymap.impsign':'${querymap.impsign}',
								'ptrainQuestionstempBean.id':idArr.join(",")
								};
					url="<c:url value='/Ptrain/Questions/saveprrainquesexcel.shtml'/>";
	            	$.ajax({
						type: "POST", 
						url: url, //代码数据
						async:false,
						dataType:"json",
						data:param,
						error:function(data){
						    parent.$.jBox.tip("与数据库连接失败！", 'info');
						},
						success:function(data) {
							if(data == '1'){
		            			parent.$.jBox.tip("操作成功！", 'info');
		                   		toSearch();
							}else{
		            			parent.$.jBox.tip("操作失败！", 'info');
							}
						}
					});
				}else{
					var param = {
								'querymap.specidimp':'${querymap.specidimp}',
								'querymap.impsign':'${querymap.impsign}'
								};
					$.ajax({
						type: "POST", 
						url: url, //代码数据
						async:false,
						dataType:"json",
						data:param,
						error:function(data){
						    parent.$.jBox.tip("与数据库连接失败！", 'info');
						},
						success:function(data) {
							if(data == '1'){
		            			parent.$.jBox.tip("操作成功！", 'info');
		                   		toSearch();
							}else{
		            			parent.$.jBox.tip("操作失败！", 'info');
							}
						}
					});
				}
            }
            return true;
        };
        parent.$.jBox.confirm("确定导入记录？", "提示", submit);
	}
</script>
</body>
</html>