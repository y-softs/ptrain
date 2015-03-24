<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head> 
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<link rel="stylesheet" href="../../Style/valid.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
	<script type="text/javascript" src="../../Script/Main.js"></script>
	<script type="text/javascript">
		$(function(){
			bindValid();
		});
		//保存
		function saveData(){
			//文件路径
			var temp="";
			$.each($('input[name=upfile]'),function(i,n){
				temp+=$(this).val();
			});
			if(temp==""){
				parent.$.jBox.tip("文件路径不能为空！", 'warning');
				return false;
			}
			$("input[name='btn_save'],input[name='btn_sure'],input[name='btn_reset'],input[name='btn_back']").attr("disabled","disabled");
			$("#tagpage").val("1")
			$("#typeid,#keyword").val("");
			openProcessDiv();
			document.form1.action="<c:url value='/Ptrain/Postuser/saveptrainpostuserexcel.shtml' />";
		    document.form1.submit();
		}
		//返回
		function goBack(){
		    document.form1.action="<c:url value='/Ptrain/Postuser/toforwardpuser.shtml' />";
		    document.form1.submit();
		}
	    //下载文件 
		function downLoad(savename,savepath){
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
		
		function bindValid(){
			//导入方式
			$("input[name='querymap.impsign']").attr("fv-empty","false").attr("fv-empty-msg","导入方式不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","paramvalue0");
			$("input[name='querymap.impsign']").parent().append('<span id="paramvalue0" class="validate-info"></span>');
			
			//关联主键
			$("input[name='querymap.impkey']").attr("fv-empty","false").attr("fv-empty-msg","关联主键不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","paramvalue1");
			$("input[name='querymap.impkey']").parent().append('<span id="paramvalue1" class="validate-info"></span>');
			
			//绑定form表单自定义验证
			$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		}
		
	</script>
<body>
<div class="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">人员岗位-导入</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
	<form name="form1" action="" method="post" id="form1" enctype="multipart/form-data">
        <table class="editTable">
            <tbody>
            	<tr>
                    <!--左侧表格项-->
                    <td nowrap class="left">专业类别：</td>
                    <!--右侧表格项-->
                    <td class="right">${postname}</td>
                </tr>
                <tr>
                    <td nowrap class="left">*&nbsp;文件路径：</td>
                    <td class="right">
                        <input type="hidden" name="filefat" value="xls">
                        <input type="hidden" name="filesize" value="50">
                        <input type="file" name="upfile" class="textfile_50" onChange="JavaScript:checkFile();">
                        （≤50MB，*.xls）
                        <a href="JavaScript:downLoad('Table02.xls','/Ptrain/Base/Download/')" class="fontcolor_red">&gt;&gt;&nbsp;格式下载</a>
                    </td>
                </tr>
                <tr>
                    <td nowrap class="left">*&nbsp;导入方式：</td>
                    <td class="right">
                       	<input type="radio" name="querymap.impsign" value="1">清空后导入
                       	<input type="radio" name="querymap.impsign" value="2" disabled>已有的更新
                       	<input type="radio" name="querymap.impsign" value="3" disabled>未有的追加
                       	<input type="radio" name="querymap.impsign" value="4">更新并追加
                       	<input type="radio" name="querymap.impsign" value="5" checked>全部都追加
                    </td>
                </tr>
                <tr>
                    <td nowrap class="left">关联主键：</td>
                    <td class="right">
                    	<input type="radio" name="querymap.impkey" value="1" checked>身份证号
                    </td>
                </tr>
                <!-- 以下为隐藏域 -->
	            <tr>
	                <td colspan="2">
	                	<input type="button" name="btn_sure" value="确定" onClick="JavaScript:saveData();">
                        <input type="reset" name="btn_reset" value="重置">
                        <input type="button" name="btn_back" value="返回" onClick="JavaScript:goBack();">
                        <input type="hidden" name="tokenid" value="${tokenid}">
                        <input type="hidden" name="querymap.unitid" value="${querymap.unitid}">
                        <input type="hidden" name="querymap.postid" value="${querymap.postid}">
                        <input type="hidden" name="querymap.specid" value="${querymap.specid}">
                        <!-- 回帖 -->
                        <input type="hidden" name="sign" value="${sign}">
                        <input type="hidden" name="querymap.deptid" value="${querymap.deptid}">
                        <input type="hidden" name="querymap.groupid" value="${querymap.groupid}">
                        <input type="hidden" name="querymap.subgroupid" value="${querymap.subgroupid}">
                        <input type="hidden" name="querymap.firstpostid" value="${querymap.firstpostid}">
                        <input type="hidden" name="querymap.secpostid" value="${querymap.secpostid}">
                        <input type="hidden" name="querymap.threepostid" value="${querymap.threepostid}">
                        <input type="hidden" name="fields" value="${fields}" id="fields">
                        <input type="hidden" name="keyword" value="${keyword}" id="keyword">
                        <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
                        <input type="hidden" name="record" value="${record}" id="record" />
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2" style="display:none;">
						<span id="formerror"></span>
	                </td>
	            </tr>
		    </tbody>
        </table>
   </form>
	<div class="errorBlock"></div>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>