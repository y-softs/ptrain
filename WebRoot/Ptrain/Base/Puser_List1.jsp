<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
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
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">人员岗位关联[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
        <form method="post" name="form" id="form1" action="">
			<c:if test="${not empty specList}">
				专业类别：<select name="querymap.specid" id="specid">
					<c:forEach items="${specList}" var="p">
						<option value="${p.id}" ${p.id==querymap.specid?'selected':''}>${p.codename}</option>
					</c:forEach>
				</select>
			</c:if>
		        关键字：<select name="fields" id="fields">
			       <option value="u.username" ${fields=='u.username'?'selected':''}>姓名</option>
			       <option value="u.userid" ${fields=='u.userid'?'selected':''}>身份证号</option>
			    </select>
	        <input type="text" name="keyword" id="keyword" value="${keyword}" />
	        <input type="button" name="search" id="b_search" class="btn btn-primary" onclick="toSearch();" value="搜索" />
	        
	        <input type="hidden" name="querymap.unitid" id="b_unitid" value="${querymap.unitid}" />
	        <input type="hidden" name="sign" value="${sign}" id="sign" />
            <input type="hidden" name="tokenid" value="${tokenid}" />
            <input type="hidden" name="ptrainPostuserBean.id" id="id" />
            <input type="hidden" name="querymap.postid" value="${querymap.postid}" />
            <input type="hidden" name="querymap.selfields" id="selfields" />
            <input type="hidden" name="querymap.selkey" id="selkey" />
            <input type="hidden" name="tagpage" value="${tagpage}" />
            
	    </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
        	<button class="btn btn-info" id="b_datereve">反向设置</button>
            <button class="btn btn-info" id="b_impexcel">导入Excel</button>
            <button class="btn btn-info" id="b_datauser">人员引入</button>&nbsp;&nbsp;|&nbsp;&nbsp;
        	<button class="btn btn-danger" id="b_batchdel">批量删除</button>
            <button class="btn btn-info" id="b_expexcel">导出Excel</button>
        </div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <div class="gridDiv">
    </div>
</div>
<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'id' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1+'<input type="hidden" name="pkid" value='+val+' />';
        }},
        { title:'部门名称', name:'deptname' ,width:280, align:'left'},
        { title:'班组名称', name:'groupname' ,width:150, align:'left'},
        { title:'姓名', name:'username' ,width:150, align:'left'},
        { title:'岗位名称', name:'postname' ,width:150, align:'left'},
        { title:'操作', name:'' ,width:220, align:'left', lockWidth:true, lockDisplay: true, renderer: function(val,item,rowIndex){
        	return "<button class=\"btn btn-danger\" onClick=delData("+item.id+");>删除</button>&nbsp;"
        }}
    ];
    
	var url = "<c:url value='/Ptrain/Postuser/listptrainpostuser.shtml'/>";
	var paramsV={
		'sign':'${sign}',
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.specid':$("#specid").val(),
    	'fields':'u.username',
    	'keyword':'${keyword}'
    }
	//分页
    var mmg = $('#dataTable').mmGrid({
        height:'420',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post',
		checkCol:true,
        fullWidthRows: true,
        multiSelect: true,
        root: 'items',
        plugins : [
            $('#dataPagi').mmPaginator(),
        ]
    });
    
	$(function(){
		$("#specid").bind("change",function(){
			toSearch();
		});
	
    	//反向设置
    	$("#b_datereve").bind("click",function(){
    		$("#sign").val('2');
			$("#selfields").val($("#fields").val());
			$("#selkey").val($("#keyword").val());
			$("#fields").val('');
			$("#keyword").val('');
    		var url = "<c:url value='/Ptrain/Postuser/toforwardreve.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    	
    	//导入Excel 跳转
    	$("#b_impexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Postuser/setptrainpostuserexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    	
    	//人员引入
    	$("#b_datauser").bind("click",function(){
    		setDataUser();
    	});
    	
    	//批量删除
    	$("#b_batchdel").bind("click",function(){
    		delData('');
    	});
    	
    	//导出Excel
    	$("#b_expexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Postuser/saveptrainpostuserexpexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    	
    });
    
    //查询
    function toSearch(){
		//点击查询时页码置为1
		var tagpage = '1'; 
		var sign = '${sign}';
		var unitid = '${querymap.unitid}';      //单位Id
		var specid = $("#specid").val();        //专业
	    var fields = $("#fields").val();      //关键字段
	    var keyword = $("#b_keyword").val();    //关键字
	    mmg.load({'tagpage':tagpage});
    }
    
    //人员引入[弹出]
	function setDataUser(){
		var msg = "人员引入";
		var url = "<c:url value='/Ptrain/Postuser/setptrainpostuserwin.shtml'/>";
		url+="?querymap.unitid=${querymap.unitid}";
		url+="&querymap.postid="+$("#specid").val();
		parent.$.jBox.open("iframe:"+url, msg, 750, 500, { buttons: {'确定':true,'关闭':false},
	        submit: function (v, h, f) {
	            var we = h.find("#jbox-iframe")[0].contentWindow;
	            if(v == 0){
	               return true;  //close the window
	            }else{
	               var rtn = we.checkForm();
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
    
    //删除
    function delData(id){
    	var newid = id;
        if(''==id){
			var idAttr=new Array();
			$.each($("#dataTable input[type='checkbox']"),function(i,n){
				if(n.checked){
					var obj = $(this).parent().parent().parent();
					$("td:eq(1) input[name='pkid']",obj).val();
					idAttr.push($("td:eq(1) input[name='pkid']",obj).val());
				}
			});
			newid = idAttr.join(",");
			if(newid == ''){
	        	parent.$.jBox.tip("请选中您所要操作的数据！", 'info');
	          	return ;
	        }
        }
    	if(newid == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url: "<c:url value='/Ptrain/Postuser/deleteptrainpostuser.shtml'/>", //代码数据
					async:false,
					data:{'ptrainPostuserBean.id':newid},
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
            return true;
        };
        parent.$.jBox.confirm("确定删除该记录？", "提示", submit);
    }
    
</script>
</body>
</html>