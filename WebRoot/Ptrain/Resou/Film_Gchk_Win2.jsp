<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title></title>
    <link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/datagrid.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/Style/valid.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.9.1.min.js'/>"></script>
    <script src="<c:url value='/Script/plugin/grid/grid.js'/>"></script>
    <script src="<c:url value='/Script/plugin/grid/plugins.js'/>"></script>
	<script language="javascript" src="<c:url value='/Script/plugin/valid/FormValidator.1.0.min.js'/>"></script>
	<style>
		.titleClass {clear:both;display:block;float:none;height:30px;width:100%;text-align:center;}
		.splarLine  {float:left;width:3px;height:400px;background:#321;}
		.floatClass  {padding-left:10px;float:left;width:48%;height:400px;background:#FFF;}
	</style>
</head>
<body>
<div class="container">
    <!-- 查询条件 --->
    <form name="form2" id="form2" action="" method="post">
    	<div class="floatClass">
    		<span class="titleClass">申报内容</span>
	        <table id="dataTable"></table>
	    </div>
    	<div class="splarLine"></div>
    	<div class="floatClass">
    	<span class="titleClass">审核意见</span>
        <table class="editTable">
             <!--表格内容部分-->
             <tbody>
             <tr>
                 <!--左侧表格项-->
                 <td nowrap class="left">审核意见：</td>
                 <!--右侧表格项-->
                 <td class="right">
                     <input type="radio" name="querymap.chksign" class="q_chksign" value="${TONG_YI}" checked onClick="operFiled();"/>同意
                     <input type="radio" name="querymap.chksign" class="q_chksign" value="${BU_TONG_YI}" onClick="operFiled();"/>不同意
                 </td>
             </tr>
             <tr id="tr_0">
                 <td nowrap class="left">*&nbsp;</td>
                 <td class="right">
                 	<textarea name="querymap.chkmemo" id="chkmemo" class="textarea_50_3"></textarea><br/>
                	<span id="valid_chkmemo" class="validate-info"></span>
                 </td>
             </tr>
             <tr>
                 <td nowrap class="left">审核人：</td>
                 <td class="right"><input name="chkuser" type="text" class="text_10line" value="${opraName}" maxlength="10" readonly/></td>
             </tr>
             <tr>
                 <td nowrap class="left">审核时间：</td>
                 <td class="right">
                 	<input name="chktime" type="text" class="text_20line" value="${opraTime}" maxlength="20" readonly/>
                 </td>
             </tr>
            <tr>
                <td nowrap colspan="2">
                	<input type="button" id="submitBtn" class="btn btn-info" value="确定"/>
                	<input type="button" id="closeBtn" class="btn btn-info" value="关闭"/>
                    
                     <input type="hidden" name="ptrainContentBean.id" value="${ptrainContentBean.id}" id="id"/>
                     <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                     <input type="hidden" name="fatherid" value="${fatherid}"/>
                     <input type="hidden" name="setpage" value="${setpage}"/>
                     <input type="hidden" name="querymap.querylist" value="${querymap.querylist}"/>
					 <span id="formerror"></span>    
                </td>
            </tr>
             </tbody>
        </table>
        </div>
    </form>
</div>
</body>
	<script type="text/javascript">
		$(function(){
			$("#submitBtn").bind("click",callBack);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
			$("#tr_0").hide();
			//绑定form表单自定义验证
			$("#form2").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		});
		
	    //验证绑定
	    function operFiled(){
	    	$(".q_chksign").each(function(i,dom){
	    		if($(this).prop("checked")){
	    			if($(this).val()=='${BU_TONG_YI}'){
						$("#chkmemo").attr("fv-empty","false").attr("fv-empty-msg","意见<fmt:message key="format.null" bundle="${bms}"/>")
										.attr("fv-msg-success","").attr("fv-msgpanel","valid_chkmemo");
						$("#tr_0").show();
	    			}else{
						$("#chkmemo").attr("fv-empty","true").attr("fv-empty-msg","")
										.attr("fv-msg-success","").attr("fv-msgpanel","valid_chkmemo");
						$("#tr_0").hide();
	    			}
	    		}
	    	});
	    }
		//保存
		function callBack(){
			operFiled();
			$("#form2").attr("action",'<c:url value='/Ptrain/Content/updateptraincontentchk.shtml'/>').trigger("submit");
	
			if($("#formerror").text() == "ok"){
		    	return true;
			}
			return false;
		}
		//修改弹出
		function setDataWin(id){
			var url = "<c:url value='/Ptrain/Content/setptraincontentchk.shtml'/>?ptrainContentBean.id="+id;
			url+="&querymap.unitid=${querymap.unitid}";
			url+="&fatherid=${fatherid}";
			url+="&setpage=/Ptrain/Resou/Film_Gchk_Win3.jsp";
        	parent.$.jBox.open("iframe:"+url, ("归口审核-修改"), 700, 390, { buttons: {}});
		}
		
		
	    //列
	    var cols = [
	        { title:'资源类别', name:'typeid',width:140, align:'center',sortName:'co.typeid',sortable: true, renderer: null},
	        { title:'标题', name:'title' ,width:240, align:'left',sortName:'co.title,co.id',sortStatus:'asc',sortable: true, renderer: function(val,item,rowIndex){
	        	return "<a href=\"JavaScript:setDataWin('"+item.id+"');\">"+val+"</a>";
	        }}
	    ];
	    //分页
	    var paramsV={
	    	'querymap.unitid':'${querymap.unitid}',
	    	'ptrainContentBean.id':'${ptrainContentBean.id}'
	    }
	    var mmg = $('#dataTable').mmGrid({
        	indexColWidth: 35,
	        height:'360',
	        width:'98%',
	        cols: cols,
	        url: '<c:url value="/Ptrain/Content/listptraincontentbyids.shtml"/>',
	        params:paramsV,
	        method: 'post',
			nowarp:true
	    });
		//子页面重新刷新父页面
		function reloadPage(){
			mmg.load(paramsV);
		}
	</script>
</html>