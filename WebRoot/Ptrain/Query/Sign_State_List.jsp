<%@ page language="java"  pageEncoding="UTF-8"%>
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
    	<div class="titleClass">自助需求情况[<span class="switchSearch">数据搜索</span>]</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
    <div class="mySearchMain" id="mySearchMain">
    	<form method="post" name="form" id="form1" action="">
            &nbsp;专业类别：<select name="querymap.specid" id="specid">
                       		<option value="">全部</option>
                          </select>
			&nbsp;<input type="checkbox" name="querymap.showsign" value="1" ${'1'==querymap.showsign?'checked':''} id="showsign" />仅显示办班
     				关键字：<select name="fields" id="fields">
                               <option value="r.itemname" selected>项目名称</option>
                           </select>
                           <input type="text" name="keyword" id="keyword" value="${keyword}" maxlength="10" />
                           <input type="button" class="btn btn-primary" name="b_search" id="b_search" value="查询" />
                           <input type="hidden" name="tokenid" value="${tokenid}" />
                           <input type="hidden" name="sign" value="${sign}" />
		        		   <input type="hidden" name="querymap.unitid" id="b_unitid" value="${querymap.unitid}" />
                           <input type="hidden" name="ptrainRequserBean.id" id="id" />
                           <input type="hidden" name="ptrainRequserBean.reqid" id="reqid" />
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
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>

<script type="text/javascript">
    //列
    var cols = [
    	{ title:'序号', name:'' ,width:40,lockWidth:true, align:'center',renderer: function(val,item,rowIndex){
             return rowIndex+1;
        }},
        { title:'专业类别', name:'specid' ,width:180, align:'left'},
        { title:'项目名称', name:'itemname' ,width:320, align:'left',renderer: function(val,item,rowIndex){
        	return "<a href='###' onClick=setDataWin('"+item.id+"','"+item.strflag+"');>"+val+"</a>";
        }},
        { title:'培训类别', name:'reqtype' ,width:180, align:'left', renderer: function(val,item,rowIndex){
        	<c:forEach items="${reqtypeList}" var="r">
               <c:if test="${r.key==d.reqtype}">${r.desc}</c:if>
               if('${r.key}'==val){
               		return '${r.desc}';
               }
            </c:forEach>
        }},
        { title:'状态', name:'state' ,width:80, align:'left',renderer: function(val,item,rowIndex){
             if(val == "0"){
             	return '待办班';
             }else{
             	return "已办班";
             }
        }},
        { title:'办班时间', name:'maintime' ,width:120, align:'left',renderer: function(val,item,rowIndex){
             if(item.state == "1"){
             	return item.maintime;
             }else{
             	return "&nbsp;";
             }
        }},
        { title:'报名人数', name:'intflag' ,width:80, align:'center',renderer: function(val,item,rowIndex){
        	return "<a href='###' onClick=setUserWin('"+item.id+"');>"+val+"</a>";
        }}
    ];
  
	var url = "<c:url value='/Ptrain/Requser/listptrainrequser_que.shtml'/>";
	url += "?querymap.unitid=${querymap.unitid}";
	url += "&sign=${sign}";
	
	var paramsV={
		'fields':'r.itemname',
		'keyword':'${keyword}'
    }
	
	//分页
    var mmg = $('#dataTable').mmGrid({
    	height:'380',
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
		mkSpecList();
		//查询
		$("#b_search").bind("click",function(){
			toSearch();
		});
		
		//专业
		$("#specid").bind("change",function(){
			toSearch();
		});
		
		//仅显示待办班
		$("#showsign").bind("click",function(){
			toSearch();
		});
		
    	//导出Excel
    	$("#b_expexcel").bind("click",function(){
    		var url = "<c:url value='/Ptrain/Requser/saveptrainrequserexpexcel.shtml'/>";
    		$("#form1").attr("action",url).trigger("submit");
    	});
    });
    
    //专业类别数据
    function mkSpecList(){
    	$.ajax({
             type: 'post',
             async: false,
             url: '/ptrain/Ptrain/Req/findSpecListByJq.shtml',
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
    
    //查询
    function toSearch(){
		//点击查询时页码置为1
		var tagpage = '1';
	    var fields = $("#fields").val();      //关键字段
	    var keyword = $("#keyword").val();    //关键字
	    var showsign = $("#showsign").is(":checked")?$("#showsign").val():"";
		var specid = $("#specid").val();     //专业类别
		//防止滚动上一下,下一页是进行传参
		mmg.load({'tagpage':tagpage});
    }
    
    //项目详细页面
	function setDataWin(id,requserid){
		var msg = "项目详细";
		var url = "<c:url value='/Ptrain/Req/setptrainreqwin_que.shtml'/>";
			url+="?ptrainReqBean.id="+id;
			url+="&querymap.requserid="+requserid;
		parent.$.jBox.open("iframe:"+url, msg, 500, 320, { buttons: {'关闭':false},
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
	
	//人员详细页面
	function setUserWin(reqid){
		var msg = "人员详细";
		var url = "<c:url value='/Ptrain/Requser/toforwardqueryreqsign.shtml'/>";
			url+="?querymap.unitid=${querymap.unitid}";
			url+="&querymap.reqid="+reqid;
		parent.$.jBox.open("iframe:"+url, msg, 800, 550, { buttons: {'关闭':false},
	        submit: function (v, h, f) {
	            var we = h.find("#jbox-iframe")[0].contentWindow;
	            if(v == 0){
	               return true;  //close the window
	            }
	            return false;
	        }
	    });
	}
</script>
</body>
</html>