<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../Include/TagLib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title></title>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/datagrid.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script src="../../Script/plugin/grid/grid.js"></script>
	<script src="../../Script/plugin/grid/plugins.js"></script>
</head>
<body>
<!-- 后面新增的样式 -->
<div id="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">规程文件[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
	    <form method="post" name="form" id="form1" action="">
	        启用状态：<select name="querymap.usesign" id="b_usesign">
	        	<option value="" ${querymap.usesign==""?'selected':''}>全部</option>
	        	<option value="1" ${querymap.usesign=="1"?'selected':''}>启用</option>
	        	<option value="0" ${querymap.usesign=="0"?'selected':''}>禁用</option>
	        </select>
	        
	        关键字：<select name="fields" id="b_fields">
	        	<option value="codename">类别</option>
	        </select>
	        <input type="text" name="keyword" id="b_keyword" value="${keyword}" />
	        <input type="button" name="search" class="btn btn-primary" id="b_search" onclick="toSearch();" value="查询" />
	        <input type="hidden" name="fatherid" id="b_fatherid" value="${fatherid}" />
	        <input type="hidden" name="querymap.unitid" id="b_unitid" value="${loginSession.unitid}" />
	    </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="btn_insert">新增</button>
            &nbsp;&nbsp;|&nbsp;&nbsp;
        	<button class="btn btn-info" id="top">置顶</button>
            <button class="btn btn-info" id="up">上移</button>
            <button class="btn btn-info" id="down">下移</button>
            <button class="btn btn-info" id="buttom">置底</button>
            <button class="btn btn-danger" id="save">保存</button>
        </div>
        <div class="clear"/>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'id' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1+'<input type="hidden" name="pkid" value='+val+' />';
        }},
        { title:'类别', name:'codename' ,width:480, align:'left', renderer: function(val,item,rowIndex){
            if(item.usesign == 1){
            	if(item.specsign=='1'){
                	return val+'＊';
            	}else{
                	return val;
            	} 
            }else if(item.usesign == 0){
            	if(item.specsign=='1'){
                	return '[<span class="fontcolor_red">禁</span>]'+val+'＊';
            	}else{
                	return '[<span class="fontcolor_red">禁</span>]'+val;
            	}
            }
        }},
        { title:'编码', name:'codevalue' ,width:150, align:'left'},
        { title:'说明', name:'remark' ,width:40, align:'center', renderer: function(val){
            if((val == "")||(val == null)){
                return '&nbsp;';
            }else {
                return '<span title="'+val+'" style="cursor:pointer">√</span>';
            }
        }},
        { title:'操作', name:'usesign' ,width:220, align:'left', lockWidth:true, lockDisplay: true, renderer: function(val,item,rowIndex){
       		if(val == 1){
        		return "<button class=\"btn btn-info\" onClick=setCode("+item.id+",'2');>修改</button>&nbsp;<button class=\"btn btn-disabled\" onClick=operUsesign("+item.id+",'0');>禁用</button>" 
        	}else if (val == 0){
        		return "<button class=\"btn btn-info\" onClick=setCode("+item.id+",'2');>修改</button>&nbsp;<button class=\"btn btn-danger\" onClick=delData("+item.id+");>删除</button>&nbsp;<button class=\"btn btn-info\" onClick=operUsesign("+item.id+",'1');>启用</button>"
        	}
        }}
    ];
    
	var url = '<c:url value='/Ptrain/Code/listptraincode_law.shtml'/>';
	
	var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'fields':'codename',
    	'keyword':'${keyword}'
    }
	
    //分页
    var mmg = $('#dataTable').mmGrid({
        indexColWidth: 35,
        height:'420',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post',
        multiSelect: false,
		checkCol:true,
		nowarp:true
    });
    //置顶
    $('#top').on('click', function(){
        var selectedIndexes = mmg.selectedRowsIndex();
        if(selectedIndexes=="") return;
        mmg.select(function(item, index,colIndex){
            if(index==selectedIndexes){
                if(index>0){
                    mmg.addRow(item, 0);
                    mmg.removeRow(index+1);
                    mmg.select(0);
                }
            }
        });
    });
    //上移
    $('#up').on('click', function(){
        var selectedIndexes = mmg.selectedRowsIndex();
        if(selectedIndexes=="") return;
        mmg.select(function(item, index,colIndex){
            if(index==selectedIndexes){
                if(index>0){
                    mmg.addRow(mmg.row(index), index-1);
                    mmg.removeRow(index+1);
                    mmg.select(index-1);
                }
            }
        });
    });
    //下移
    $('#down').on('click', function(){
        var selectedIndexes = mmg.selectedRowsIndex();
        if(selectedIndexes=="") return;
        var maxIndex = (mmg.rowsLength()-1);//最大数字下标
        mmg.select(function(item, index,colIndex){
            if(index==selectedIndexes){
                if(maxIndex>selectedIndexes){
                    mmg.addRow(mmg.row(index),index+2);
                    mmg.removeRow(index);
                    mmg.select(index+1);
                }
            }
        });
    });
    //置底
    $('#buttom').on('click', function(){
        var maxIndex = (mmg.rowsLength()-1);//最大数字下标
        var selectedIndexes = mmg.selectedRowsIndex();
        if(selectedIndexes=="") return;
        mmg.select(function(item, index,colIndex){
            if(index==selectedIndexes){
                if(maxIndex>selectedIndexes){
                    mmg.addRow(item, mmg.rowsLength());
                    mmg.removeRow(index);
                    mmg.select(maxIndex);
                }
            }
        });
    });
    //保存
    $('#save').on('click', function(){
		var dataArr=new Array();
		$.each($("#dataTable input[name='pkid']"),function(i,m){
			dataArr.push($(this).val());
		});
		$.ajax({
			type:"post",
			url:"<c:url value='/Ptrain/Code/updatePtrainCodeSortnumByJq.shtml'/>",
			data:{"dataidstr":dataArr.join(",")},
			error:function(data){
			    parent.$.jBox.tip("与数据库连接失败！", 'info');
			},
			success:function(){
				//toSearch();
				parent.$.jBox.tip("操作成功！", 'info');
			},
			error:function(){
				toSearch();
				parent.$.jBox.tip("操作失败！", 'info');
			}
		});
    });
    
    //启用|禁止
    function operUsesign(id,usesign){
    	if(id == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var submit = function (v, h, f) {
            if (v == 'ok')
           		$.ajax({
					type: "POST", 
					url: "<c:url value='/Ptrain/Code/updatePtrainCodeUsesignByJq.shtml'/>", //启用
					async:false,
					data:{'ptrainCodeBean.id':id,'ptrainCodeBean.usesign':usesign},
					error:function(data){
				    parent.$.jBox.tip("与数据库连接失败！", 'info');
					},
					success:function(data) {
						if(data == '1'){
            				parent.$.jBox.tip("操作成功！", 'info');
		                    mmg.load();
						}else{
            				parent.$.jBox.tip("操作失败！", 'info');
						}
					}
				});
            return true; //close
        };
        parent.$.jBox.confirm("确定启用|禁止操作？", "提示", submit);
    }
    
    //删除
    function delData(id){
    	if(id == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url:"<c:url value='/Ptrain/Code/deletePtrainCodeByJq.shtml'/>", //代码数据
					async:false,
					data:{'ptrainCodeBean.id':id},
					error:function(data){
					    parent.$.jBox.tip("与数据库连接失败！", 'info');
					},
					success:function(data) {
						if(data == '1'){
	            			parent.$.jBox.tip("操作成功！", 'info');
		                    mmg.load();
						}else{
	            			parent.$.jBox.tip("操作失败！", 'info');
						}
					}
				});
            return true; //close
        };
        parent.$.jBox.confirm("确定删除该记录？", "提示", submit);
    }

	$(function(){
    	//新增
    	$("#btn_insert").bind("click",function(){
    		setCode("","1");
    	});
    	$("#b_usesign").bind("change",toSearch);
    });
    
    function toSearch(){
		//点击查询时页码置为1
		var usesign = $("#b_usesign option:selected").val();    //启用,禁用
	    var fields = $("#b_fields").val();      //关键字段
	    var keyword = $("#b_keyword").val();    //关键字
	    mmg.load();
	    //mmg.load({'querymap.usesign':usesign,'fields':fields,'keyword':keyword});
    }
    
    //新增,修改代码设置
	function setCode(id,fun){
		var url = '';
		var msg = '';
		var fatherid = $("#b_fatherid").val();
		if(fun == '1'){
			msg = '规程文件-新增';
			url = "<c:url value='/Ptrain/Code/setptraincode.shtml'/>?ptrainCodeBean.fatherid="+fatherid+"&querymap.unitid=${loginSession.unitid}";
			url += "&fatherid="+fatherid;
			url += "&setpage=Law_Set.jsp";
		}else if(fun == '2'){
			msg = '规程文件-修改';
			url = "<c:url value='/Ptrain/Code/setptraincode.shtml'/>?ptrainCodeBean.fatherid="+fatherid+"&ptrainCodeBean.id="+id+"&querymap.unitid=${loginSession.unitid}";
			url += "&fatherid="+fatherid;
			url += "&setpage=Law_Set.jsp";
		}
	    parent.$.jBox.open("iframe:"+url, msg, 620, 270, {buttons: {}});
	}
	
	//子页面重新刷新父页面
	function reloadPage(){
		toSearch();
	}

</script>
</body>
</html>