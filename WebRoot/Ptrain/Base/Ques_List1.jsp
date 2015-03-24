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
        <div class="titleClass">题库编制[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
		<form method="post" name="form" id="form1" action="">
		专业类别：<select name="querymap.specid" id="specid">
          		<option value="">全部</option>
                  <c:forEach items="${specList}" var="p">
          			<option value="${p.id}" ${p.id==querymap.specid?'selected':''}>${p.codename}</option>
          		</c:forEach>
              </select>
              &nbsp;试题类型：<select name="querymap.typeid" id="typeid">
          		<option value="">全部</option>
                  <c:forEach items="${typeList}" var="p">
          			<option value="${p.id}" ${p.id==querymap.typeid?'selected':''}>${p.codename}</option>
          		</c:forEach>
              </select>
                                关键字：<select name="fields" id="b_fields">
                      <option value="q.topic" selected>题目</option>
                  </select>
		        <input type="text" name="keyword" id="b_keyword" value="${keyword}" />
		        <input type="button" name="search" class="btn btn-primary" id="b_search" value="搜索" />
	        
             <input type="hidden" name="tokenid" value="${tokenid}"/>
             <input type="hidden" name="ptrainQuestionsBean.id" id="id"/>
             <input type="hidden" name="ptrainQuestionsBean.typeid" value="${querymap.typeid}"/>
             <input type="hidden" name="querymap.postid" value="${querymap.postid}"/>
             <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
             <input type="hidden" name="record" value="${record}" id="record" />
 		</form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="btn_insert">新增</button>
            &nbsp;&nbsp;
        </div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
</div>
<script type="text/javascript">
	var specMap = {};
	$("#specid option").each(function(i,dom){
		if($(this).val() != "")
			specMap[""+$(this).val()] = $(this).text();
	});
    //列
    var cols = [
    	{ title:'序号', name:'id' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'专业类别', name:'kindid' ,width:40, align:'center',renderer:function(val,item,rowIndex){
        	return specMap[val];
        }},
        { title:'题型', name:'typeid' ,width:40, align:'center'},
        { title:'题目[↑]', name:'topic' ,width:500, align:'left',renderer: function(val,item,rowIndex){
        	if(item.usesign == 0){
               return '<a href="JavaScript:showItem('+item.id+')">[<span class="fontcolor_red">禁</span>]'+val+'</a>';
            }else{
            	return '<a href="JavaScript:showItem('+item.id+')">'+val+'</a>';
            }
        }},
        { title:'操作', name:'usesign' ,width:220, align:'left', lockWidth:true, lockDisplay: true, renderer: function(val,item,rowIndex){
        	if(val == 1){
        		return "<button class=\"btn btn-info\" onClick=setData("+item.id+",'2');>修改</button>&nbsp;<button class=\"btn btn-disabled\" onClick=updateUsesign("+item.id+",'0');>禁用</button>" 
        	}else if (val == 0){
        		return "<button class=\"btn btn-info\" onClick=setData("+item.id+",'2');>修改</button>&nbsp;<button class=\"btn btn-danger\" onClick=deleteOper("+item.id+");>删除</button>&nbsp;<button class=\"btn btn-info\" onClick=updateUsesign("+item.id+",'1');>启用</button>"
        	}
        	return "";
        }}
    ];
	var url = "<c:url value='/Ptrain/Questions/listptrainquestions.shtml'/>";
	var paramsV = {'querymap.unitid':'${querymap.unitid}','querymap.specid':'${querymap.specid}','querymap.typeid':'${querymap.typeid}'};
    //分页
    var mmg = $('#dataTable').mmGrid({
        indexColWidth: 35,
        height:'420',
        cols: cols,
        url: url,
        params:paramsV,
        method: 'post',
        root: 'items',
        fullWidthRows: true,
		nowarp:true,
        plugins : [
            $('#dataPagi').mmPaginator()
        ]
    });
    
	$(function(){
		$("#b_search").bind("click",toSearch);
		$("#specid,#typeid").bind("change",toSearch);
    	//新增
    	$("#btn_insert").bind("click",function(){
    		setData("","1");
    	});
    });
    
    //查询
    function toSearch(){
		//点击查询时页码置为1
	    var specid = $("#specid").val();
	    var typeid = $("#typeid").val();
	    var fields = $("#b_fields").val();      //关键字段
	    var keyword = $("#b_keyword").val();    //关键字
    	//默认重置为第一页
    	var	tagpage = '1'; 
    	_searparam = {'querymap.specid':specid,'querymap.typeid':typeid,'fields':fields,'keyword':keyword,'tagpage':tagpage};
	    mmg.load({
	    		'querymap.specid':specid,
	    		'querymap.typeid':typeid,
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
        var url = "<c:url value='/Ptrain/Questions/deleteptrainquestions.shtml'/>";
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url: url, //代码数据
					async:false,
					dataType:"json",
					data:{'ptrainQuestionsBean.id':id},
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
	
    //启用|禁止
    function updateUsesign(id,usesign){
    	if(id == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var submit = function (v, h, f) {
            if (v == 'ok')
           		$.ajax({
					type: "POST", 
					url: "<c:url value="/Ptrain/Questions/updatePtrainQuestionsUsesignByJq.shtml"/>", 
					async:false,
					data:{'ptrainQuestionsBean.id':id,'ptrainQuestionsBean.usesign':usesign},
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
        parent.$.jBox.confirm("确定启用|禁止操作？", "提示", submit);
    }
	
	
    //新增,修改代码设置
	function setData(id,fun){
		var url = "<c:url value='/Ptrain/Questions/setptrainquestions.shtml'/>";
		var msg = '';
		if(fun == '1'){
			msg = '题库编制-新增';
		}else if(fun == '2'){
			msg = '题库编制-修改';
		}
		url = url+"?ptrainQuestionsBean.id="+id;
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
	
	function showItem(id){
		var url = "<c:url value='/Ptrain/Questions/setptrainquestionswin.shtml'/>";
		var msg = '题库编制-详细';
		url = url+"?ptrainQuestionsBean.id="+id;
		url = url+"&querymap.unitid=${querymap.unitid}";
	    parent.$.jBox.open("iframe:"+url, msg, 720, 520, { buttons: {'关闭':false},
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