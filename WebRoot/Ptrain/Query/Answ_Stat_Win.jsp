<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title></title>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<link rel="stylesheet" href="../../Style/datagrid.css"/>
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
    <div class="titleClass">应用统计详细[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
    	<form method="post" name="form" id="form1" action="">
           	<c:choose>
				<c:when test="${'1'==querymap.sortsign}">
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
				</c:when>
				<c:when	test="${'2'==querymap.sortsign}">   
	                &nbsp;专业类别：<select name="querymap.specid" id="specid">
	                           		<option value="">全部</option>
	                              </select>
				</c:when>
			</c:choose>
			&nbsp;<input type="checkbox" name="querymap.showsign1" id="showsign1" value="1" ${'1'==querymap.showsign1?'checked':''} />仅显示达标
			&nbsp;<input type="checkbox" name="querymap.showsign2" id="showsign2" value="1" ${'1'==querymap.showsign2?'checked':''} />仅显示未达标
     				关键字：<select name="fields" id="fields">
                     <option value="u.username" ${fields=='u.username'?'selected':''}>姓名</option>
                     <option value="u.userid" ${fields=='u.userid'?'selected':''}>身份证号</option>
                 </select>
               <input type="text" name="keyword" id="keyword" class="text_10" value="${keyword}" maxlength="10" />
               <input type="button" name="btn_query" value="查询" onclick="toSearch();" />
               
	           <input type="hidden" name="querymap.unitid" value="${querymap.unitid}" />
	           <input type="hidden" name="querymap.year" value="${querymap.year}" />
	           <input type="hidden" name="querymap.month" value="${querymap.month}" />
	           <input type="hidden" name="querymap.reachnum" value="${querymap.reachnum}" />
	           <input type="hidden" name="querymap.sortsign" value="${querymap.sortsign}" />
	    </form>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 结果集合 --->
    <div class="gridDiv">
        <div id="dataPagi"></div>
        <div class="btnGroup">
            <button class="btn btn-info" id="b_expexcel">导出Excel</button>
        </div>
        <div class="clear"></div>
        <table id="dataTable"></table>
    </div>
</div>
</body>
<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'部门名称', name:'DEPTNAME' ,width:180, align:'left'},
        { title:'班组名称', name:'GROUPNAME' ,width:80, align:'left'},
        { title:'姓名', name:'USERNAME' ,width:80, align:'center', renderer: function(val,item,rowIndex){
        	var userid = item.USERID;
        	return "<span title=\"身份证号："+userid+" \"style=\"cursor:pointer\" >"+val+"</span>";
        }},
        { title:'答题次数', name:'ASKNUM' ,width:80, align:'center',renderer: function(val,item,rowIndex){
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	return val;
             }
        }},
        { title:'累计得分', name:'SCORE' ,width:80, align:'center',renderer: function(val,item,rowIndex){
             if((val == "")||(val == null)){
             	return '&nbsp;';
             }else{
             	return val;
             }
        }},
        { title:'达标状态', name:'REACHSTAT' ,width:80, align:'center'}
    ];
    
	var url = "<c:url value='/Ptrain/Ask/setptrainaskuserwin.shtml'/>";
	var paramsV={
    	'querymap.unitid':'${querymap.unitid}',
    	'querymap.reachnum':'${querymap.reachnum}',
		'querymap.year':'${querymap.year}',
		'querymap.month':'${querymap.month}',
		'querymap.deptid':'${querymap.deptid}',
		'querymap.groupid':'${querymap.groupid}',
		'querymap.subgroupid':'${querymap.subgroupid}',
		'querymap.showsign1':'${querymap.showsign1}',
		'querymap.showsign2':'${querymap.showsign2}',
    	'querymap.specid':'${querymap.specid}',
		'fields':'u.username',
		'keyword':'${keyword}'
    }
	//分页
    var mmg = $('#dataTable').mmGrid({
        cols: cols,
        width:'100%',
        height:'330',
        url: url,
        params:paramsV,
        method: 'post',
        root: 'items',
        plugins : [
            $('#dataPagi').mmPaginator(),
        ]
    });
    
	$(function(){
		var sortsign = '${querymap.sortsign}';
		if(sortsign == '1'){
			mkDeptList();
		}else{
			mkSpecList();
		}
		
		$("#groupid,#subgroupid").hide();
		
		//仅显示达标,仅显示未达标
		$("#showsign1,#showsign2").bind("change",function(){
			toSearch();
		});
			
		//专业类别
		$("#specid").bind("change",function(){
			toSearch();
		});
		
		//部门change事件触发班组下拉列表框
		$("#deptid").bind("change",function(){
			operDept();
		});
		
		//班组change事件触发班组下拉列表框
		$("#groupid").bind("change",function(){
			operGroupid();
		});
		
		//子班组change事件
		$("#subgroupid").bind("change",toSearch);
		
    	//导出Excel
    	$("#b_expexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Ask/saveptrainaskuserexpexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    });
    
    //构造专业列表
    function mkSpecList(){
    	$.ajax({
             type: 'post',
             async: false,
             url: '<c:url value='/Ptrain/Postuser/findThreeSpecListByJq.shtml'/>',
             data: {'querymap.unitid':'${querymap.unitid}'},
             dataType: 'json',
             cache: false,
             success:function(data){
             	var str = "";
				//通过jquery方法进行循环编译
				$.each(data,function(i,d){
					var sel = ""
					if('${querymap.specid}'==d.id){
						sel = "selected";
					}
					str += "<option "+sel+" value="+d.id+" >"+d.codename+"</option>";
				});
				$("#specid").append(str);
             },
			error:function(msg){
			    parent.$.jBox.tip("与数据库连接失败！", 'info');
			}
        });
    }
    
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
    
    //操作部门
    function operDept(){
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
		loaddept(deptid);
		toSearch();
	}
	//加载部门数据
	function loaddept(deptid){
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
	}
	function operGroupid(){
		var groupid = $("#groupid").val();
		if(groupid == ''){
			$("#subgroupid").val('');
			$("#subgroupid").hide();
			toSearch();
			return;
		}
		loadgroupid(groupid);
		toSearch();
	}
    //加载班组数据
    function loadgroupid(groupid){
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
    }
    
    //查询
    function toSearch(){
		//点击查询时页码置为1
		var tagpage = '1';
		var sortsign = '${querymap.sortsign}';
		var unitid = '${querymap.unitid}';   //单位
	    var fields = $("#fields").val();      //关键字段
	    var keyword = $("#keyword").val();    //关键字
	    var showsign1 = $("#showsign1").is(":checked")?$("#showsign1").val():"";
	    var showsign2 = $("#showsign2").is(":checked")?$("#showsign2").val():"";
		if(sortsign=='1'){
			var deptid = $("#deptid").val();     //部门
			var groupid = $("#groupid").val();    //班组
			var subgroupid = $("#subgroupid").val();  //子班组
			mmg.load({'tagpage':tagpage});
		}else if(sortsign=='2'){
			var specid = $("#specid").val();     //专业类别
			mmg.load({'tagpage':tagpage});
		}
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
					alert(rtn);
					if(rtn){
						parent.$.jBox.tip("操作成功！", 'info');
		                //toSearch();
						mmg.load();
					}
	               return rtn;
	            }
	            return false;
	        }
	    });
	}
</script>
</html>