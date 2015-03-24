<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../Include/TagLib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
<link rel="stylesheet" href="../../Style/main.css"/>
<link rel="stylesheet" href="../../Style/datagrid.css"/>
<link rel="stylesheet" href="../../Style/bootstrap.css"/>
<link rel="stylesheet" href="../../Ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../../Script/plugin/grid/grid.js"></script>
<script type="text/javascript" src="../../Ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../../Ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<style type="text/css">
         #ztree{
            width:20%; float:left; height:490px; border:1px solid red;margin:0px; padding:0px; border:none;
            background:#FFFFFF url(../../Images/organ-bg.jpg) right repeat-y;
         }
         .top-nav-bg{background:#f9f9f9 url(../../Images/organ-top-bg.jpg) top left repeat-x; height:40px; width:99%;}
         #treeDemo{ height:90%;overflow:auto; background:none;overflow-y:auto; width:93%;}
         .top-nav-bg span{ font: bold 12px/32px Arial, sans-serif; margin-left:30%; }
    </style>
</head>
<body>
<div id="ztree">
    <div class="top-nav-bg"><span>菜单列表</span></div>
    <ul id="treeDemo" class="ztree"></ul>
</div>
<div id="container" style="width:80%; float:right;">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">菜单设置</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi">
            <button class="btn btn-info" id="insert">新增 </button> 
            <button class="btn btn-info" id="update">修改 </button> 
            <button class="btn btn-danger" id="delete">删除 </button> 
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <button class="btn btn-info" id="usesign">启用 /禁用 </button> 
        </div>
        <div id="btnGroup">
            <button class="btn btn-info" id="insert_button">新增按钮权限</button> 
        </div>
        <div class="clear"/>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
</body>
	<script language="JavaScript">
		//列
	    var cols = [
	         { title:'序号', name:'' ,width:40,lockWidth:true, align:'center', rowspan:2, renderer: function(val,item,rowIndex){
	            return rowIndex+1;
	        }},
	        { title:'类别', name:'name' ,width:80, align:'left'},
	        { title:'按钮ID', name:'rel' ,width:140, align:'left',renderer: null},
	        { title:'操作', name:'usesign' ,width:90, align:'left', renderer: function(val,item,rowIndex){
	            var updateStr="<button class=\"btn btn-info\" onClick=\"setCode("+item.id+",'2');\">修改</button>";
	            updateStr+="&nbsp;<button class=\"btn btn-danger\" onClick=\"delOper("+item.id+");\">删除</button>";
	            return updateStr;
	        }}
	    ];
	    //列表数据加载
	    var url = '<c:url value='/Base/Menu/listBaseMenuByJq.shtml'/>';   
				
		$(function(){
			$("#insert_button").hide();
			$("#usesign").bind("click",operUsesign);
			$("#delete").bind("click",delOper);
			$("#insert").bind("click",function(){setCodeTwo('1');});
			$("#update").bind("click",function(){setCodeTwo('2');});
			onInitTree();
		});
		function onInitTree(){
			var url = "<c:url value='/Base/Menu/listBaseMenuTreeByJq.shtml'/>"
			var zNodes = new Array();
			$.ajax({
				type: "POST", 
				url: url,
				data:{},
				async : true,
				dataType:"json",
				success:
				function(data) {
					for(var i=0;i<data.length;i++){
						var usignV = "";
						var purV = "";
						var specsignV = "";
						var tmp = "";
						if(data[i].usesign=='0'){
						   usignV = "[<font color=red>禁用</font>]";
						}
						if(data[i].pur=='1'){
						   purV = "[<font color=blue>权</font>]";
						}
						if(data[i].specsign=='1'){
						   specsignV = "[<font color=green>特</font>]";
						}
						tmp = usignV+purV+specsignV+data[i].name;
						zNodes[i]=({id:data[i].id, pId:data[i].fatherid,name:tmp,rel:data[i].rel,src:data[i].url,usesign:data[i].usesign,lev:data[i].lev});
					}
					//初始化题库类别
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				}
			});
		}
		//设置checkbox属性
		var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType:'all'
			},
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId"
				}
			},
			callback: {
				onCheck: onCheck
			},
			view: {
				fontCss: getFont,
				nameIsHTML: true,
				dblClickExpand: true,
				showLine: true,
				selectedMulti: false
			}
			
		};
		function getFont(treeId, node) {
			return node.font ? node.font : {};
		}		
		
		function onCheck(e, treeId, treeNode) {
			//判断不存在子节点或子节点全部设置为 nocheck = true
			if(treeNode.check_Child_State == -1){
				//显示详细信息
				$("#insert_button").show();
				$("#dataTable").show();
				$(".mmg-headWrapper table").show();
				var mmg = $('#dataTable').mmGrid({
			        height:'420',
			        cols: cols,
			        url: url,
			        method: 'post',
			        fullWidthRows: true
			    });
			}else{
				//隐藏详细信息
				$("#insert_button").hide();
				$("#dataTable").hide();
				$(".mmg-headWrapper table").hide();
			}
		}
		
     //新增/修改
    function setCodeTwo(fun){
		//循环获取
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getCheckedNodes(true);
		 var id  = "";
		if(nodes.length>0){
	        id = (nodes[0].id);
	    }
        var url = '';
        var msg = '';
        if(fun == '1'){
        	msg = '新增二级及以下菜单';
        	if(id == ""){
        		id == "-1";msg="新增一级菜单";
				url = "<c:url value='/Base/Menu/setbasemenu1.shtml'/>?fun="+fun;
				url +="&querymap.fatherid="+id;
        	}
        	else{
				url = "<c:url value='/Base/Menu/setbasemenu2.shtml'/>?fun="+fun;
				url +="&baseMenuBean.fatherid="+id;
        	}
        }else if(fun == '2'){
            if(id == ""){
	        	parent.$.jBox.tip("请选择要操作的节点！", 'info');
	          	return ;
            }
        	msg = '修改';
			url = "<c:url value='/Base/Menu/setbasemenu2.shtml'/>?fun="+fun+"&baseMenuBean.id="+id;
        }
        parent.$.jBox.open("iframe:"+url, msg , 600, 420, { buttons: {}});
    }
    
    
    //删除
    function delOper(){
		//循环获取(specid)
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getCheckedNodes(true);
		if(nodes.length>0){
	        var id = (nodes[0].id);
	        var submit = function (v, h, f) {
	            if (v == 'ok'){
	            $.ajax({
						type: "POST", 
						url:"<c:url value='/Base/Menu/deleteBaseMenuByJq.shtml'/>", //代码数据
						async:false,
						data:{'baseMenuBean.id':id},
						error:function(data){
						    parent.$.jBox.tip("与数据库连接失败！", 'info');
						},
						success:function(data) {
							if(data == '1'){
		            			parent.$.jBox.tip("操作成功！", 'info');
								//刷新
								onInitTree();
							}else{
		            			parent.$.jBox.tip("操作失败！", 'info');
							}
						}
					});
				}
	            return true; 
	        };
	        parent.$.jBox.confirm("确定删除该记录？", "提示", submit);
	     }else{
	        parent.$.jBox.tip("请选择要操作的节点！", 'info');
		}
    }

   //启用|禁止
    function operUsesign(){
		//循环获取(specid)
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getCheckedNodes(true);
		if(nodes.length>0){
			var oldusesign = (nodes[0].usesign);
	        var id = (nodes[0].id);
	    	if(oldusesign == ''){
	        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
	          	return ;
	        }
	        var usesign = oldusesign == "1"?"0":"1";
	        var submit = function (v, h, f) {
	            if (v == 'ok')
	           		$.ajax({
						type: "POST", 
						url: "<c:url value='/Base/Menu/updateBaseMenuUsesignByJq.shtml'/>", //启用
						async:false,
						data:{'baseMenuBean.id':id,'baseMenuBean.usesign':usesign},
						error:function(data){
					    	parent.$.jBox.tip("与数据库连接失败！", 'info');
						},
						success:function(data) {
							if(data == '1'){
		            			parent.$.jBox.tip("操作成功！", 'info');
								//刷新
								onInitTree();
							}else{
	            				parent.$.jBox.tip("操作失败！", 'info');
							}
						}
					});
	            else if (v == 'cancel')
	            return true;
	        };
	        parent.$.jBox.confirm("确定启用|禁止操作？", "提示", submit);
		}else{
	        parent.$.jBox.tip("请选择要操作的节点！", 'info');
		}
    }
    
	//子页面重新刷新父页面
	function reloadPage(){
		onInitTree();
	}
	</script>
</html>