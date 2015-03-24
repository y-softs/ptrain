<%@ page language="java"  import="java.util.*"  pageEncoding="UTF-8"%>
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
     		&nbsp;部门班组：
    		    <select name="querymap.deptid" id="deptid">
      				<option value="">全部</option>
          		</select>
               <select name="querymap.groupid" id="groupid">
           		<option value="">全部</option>
               </select>
             		<select name="querymap.subgroupid" id="subgroupid">
				<option value="">全部</option>
                  </select>
     				关键字：<select name="fields" id="fields">
                     <option value="u.username" ${fields=='u.username'?'selected':''}>姓名</option>
                     <option value="u.userid" ${fields=='u.userid'?'selected':''}>身份证号</option>
                 </select>
               <input type="text" name="keyword" id="keyword" class="text_10" value="${keyword}" maxlength="10" />
               <input type="button" name="btn_query" class="btn btn-primary" value="查询" onclick="toSearch();" />
               <input type="hidden" name="sign" value="${sign}" id="sign"/>
               <input type="hidden" name="tokenid" value="${tokenid}"/>
               <input type="hidden" name="ptrainPostuserBean.id" id="id" />
               <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
               <input type="hidden" name="querymap.postid" value="${querymap.postid}"/>
               <input type="hidden" name="querymap.specid" value="${querymap.specid}"/>
               <input type="hidden" name="querymap.selfields" value="${querymap.selfields}" id="selfields"/>
               <input type="hidden" name="querymap.selkey" value="${querymap.selkey}" id="selkey"/>
	    </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div id="btnGroup">
            <button class="btn btn-info" id="b_back1">返回</button>
        </div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <div class="gridDiv">
        <div class="btnGroup">
            <button class="btn btn-danger" id="b_back2">返回</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'部门名称', name:'deptname' ,width:280, align:'left'},
        { title:'班组名称', name:'groupname' ,width:150, align:'left'},
        { title:'姓名', name:'username' ,width:150, align:'left'},
        { title:'岗位名称', name:'postname' ,width:150, align:'left'},
        { title:'操作', name:'id' ,width:220, align:'left', lockWidth:true, lockDisplay: true, renderer: function(val,item,rowIndex){
        	if((val == "")||(val == null)){
        		return "<button class=\"btn btn-info\" onClick=setDataWin('"+val+"','"+item.userid+"','"+item.postid+"');>设置</button>"
        	}else{
        		return "<button class=\"btn btn-info\" onClick=setDataWin('"+val+"','"+item.userid+"','"+item.postid+"');>设置</button>&nbsp;<button class=\"btn btn-danger\" onClick=delData("+val+");>清除</button>"
        	}
        }}
    ];
    
	var url = "<c:url value='/Ptrain/Postuser/listptrainpostuser_reve.shtml'/>";
	    url += "?sign=${sign}";
	var paramsV={
    	'fields':'u.username',
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
        root: 'items',
        plugins : [
            $('#dataPagi').mmPaginator(),
        ]
    });
    
	$(function(){
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
		
    	//返回
    	$("#b_back1,#b_back2").bind("click",function(){
    		$("#sign").val('1');
			$("#deptid,#groupid,#subgroupid").remove();
			$("#fields").val($("#selfields").val());
			$("#keyword").val($("#selkey").val());
    		var url = "<c:url value='/Ptrain/Postuser/toforwardpuser.shtml'/>";
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
             	if(data.length==0){
					str += "<option value=''>全部</option>";
				}else{
					//通过jquery方法进行循环编译
					$.each(data,function(i,d){
						var sel = ""
						if('${querymap.deptid}'==d.id){
							sel = "selected";
						}
						str += "<option "+sel+" value="+d.id+" >"+d.organname+"</option>";
					});
				}
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
		var	tagpage = '1';
		var sign = '${sign}';
		var unitid = '${querymap.unitid}';   //单位
		var deptid = $("#deptid").val();     //部门
		var groupid = $("#groupid").val();    //班组
		var subgroupid = $("#subgroupid").val();  //子班组
	    var fields = $("#fields").val();      //关键字段
	    var keyword = $("#keyword").val();    //关键字
	    mmg.load({'tagpage':tagpage});
    }
    
    //设置[弹出]
	function setDataWin(id,userid,specid){
		var msg = "岗位设置";
		var url = "<c:url value='/Ptrain/Postuser/setptrainpostuserreve.shtml'/>";
			url+="?querymap.unitid=${querymap.unitid}";
			url+="&ptrainPostuserBean.id="+id;
			url+="&ptrainPostuserBean.userid="+userid;
			url+="&ptrainPostuserBean.postid="+specid;
		parent.$.jBox.open("iframe:"+url, msg, 500, 300, { buttons: {'确定':true,'关闭':false},
	        submit: function (v, h, f) {
	            var we = h.find("#jbox-iframe")[0].contentWindow;
	            if(v == 0){
	               return true;  //close the window
	            }else{
					var rtn = we.callBack();
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
    	if(id == ''){
        	parent.$.jBox.tip("参数错误！请刷新页面后重试！", 'info');
          	return ;
        }
        var submit = function (v, h, f) {
            if (v == 'ok')
            	$.ajax({
					type: "POST", 
					url: "<c:url value='/Ptrain/Postuser/deleteptrainpostuser_reve.shtml'/>", //代码数据
					async:false,
					data:{'ptrainPostuserBean.id':id},
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
        parent.$.jBox.confirm("确定清除该记录？", "提示", submit);
    }
</script>
</body>
</html>