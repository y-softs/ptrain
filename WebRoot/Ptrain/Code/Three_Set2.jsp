<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/Main.js"></script>
	<script type="text/javascript">
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
			openProcessDiv();
			document.form1.action="<c:url value='/Ptrain/Code/saveptraincodeexcel.shtml' />";
		    document.form1.submit();
		}
		//返回
		function goBack(){
		    document.form1.action="<c:url value='/Ptrain/Code/toforwardthree.shtml' />";
		    document.form1.submit();
		}
	    //下载文件 
		function downLoad(savename,savepath){
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
	</script>
<body>
<div class="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">三问类别-导入</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
	<form name="form1" action="" method="post" id="form1" enctype="multipart/form-data">
        <table class="editTable">
            <tbody>
                <tr>
                    <td nowrap class="left">*&nbsp;文件路径：</td>
                    <td class="right">
                        <input type="hidden" name="filefat" value="xls">
                        <input type="hidden" name="filesize" value="50">
                        <input type="file" name="upfile" class="textfile_50" onChange="JavaScript:checkFile();">
                        （≤50MB，*.xls）
                        <a href="JavaScript:downLoad('Table01.xls','/Ptrain/Code/Download/')" class="fontcolor_red">&gt;&gt;&nbsp;格式下载</a>
                    </td>
                </tr>
                <tr>
                    <td nowrap class="left">*&nbsp;导入方式：</td>
                    <td class="right">
                    	<input type="radio" name="querymap.impsign" value="1">清空后导入
                    	<input type="radio" name="querymap.impsign" value="5" checked>全部都追加
                    </td>
                </tr>
		    </tbody>
		    <!--表格合计或操作部分-->
            <tfoot>
	            <tr>
	                <td colspan="2" align="center">
	                    <input type="button" class="btn btn-danger" name="btn_sure" value="确定" onClick="JavaScript:saveData();">
	                    <input type="reset" class="btn btn-disabled" name="btn_reset" value="重置">
	                    <input type="button" class="btn btn-info" name="btn_back" value="返回" onClick="JavaScript:goBack();">
	                    <input type="hidden" name="tokenid" value="${tokenid}" />
	                    <input type="hidden" name="ptrainCodeBean.sortnum" value="${ptrainCodeBean.sortnum}">
	                    
		                <input type="hidden" name="fatherid" value="${fatherid}" />
		                <input type="hidden" name="listpage" value="Three_List.jsp" />
                        <input type="hidden" name="querymap.unitid" value="${querymap.unitid}">
                    </td>
	            </tr>
            </tfoot>
        </table>
   </form>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>