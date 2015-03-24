<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head> 
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/Main.js"></script>
<script type="text/javascript">
	$(function(){
		$("#specid").change(function(){
			$("#downexcel a").remove();
			var downhref='<a href="JavaScript:downLoad(\'Table01.xls\',\'/Ptrain/Base/Download/\')">&gt;&gt;&nbsp;格式下载</a>';
			if(''==$(this).val()){
				downhref='<a href="JavaScript:downLoad(\'Table04.xls\',\'/Ptrain/Base/Download/\')">&gt;&gt;&nbsp;格式下载</a>';
			}
			$("#downexcel").append(downhref);
		});
		$("#specid").trigger("change");
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
		$("#specname").val($("#specid option:selected").text());
		$("input[name='btn_save'],input[name='btn_sure'],input[name='btn_reset']").attr("disabled","disabled");
		openProcessDiv();
		document.form1.action="<c:url value='/Ptrain/Questions/saveptrainquestionstempexcel.shtml' />";
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
        <div class="titleClass">试题库-题库导入</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <form name="form1" action="" method="post" enctype="multipart/form-data">
       <table class="editTable">
           <!--表格内容部分-->
           <tbody>
           <tr>
               <!--左侧表格项-->
               <td nowrap class="left">专业类别：</td>
               <!--右侧表格项-->
               <td class="right">
               	<select name="querymap.specidimp" id="specid">
               		<option value="">全部</option>
                       <c:forEach items="${specList}" var="p">
               			<option value="${p.id}" ${p.id==querymap.specid?'selected':''}>${p.codename}</option>
               		</c:forEach>
                   </select>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">*&nbsp;文件路径：</td>
               <td class="right" id="downexcel">
                   <input type="hidden" name="filefat" value="xls">
                   <input type="hidden" name="filesize" value="50">
                   <input type="file" name="upfile" class="textfile_50" onChange="JavaScript:checkFile();">
                   （≤50MB，*.xls）
                   <a href="JavaScript:downLoad('Table01.xls','/Ptrain/Base/Download/')">&gt;&gt;&nbsp;格式下载</a>
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
                   <input type="button" class="btn btn-danger"  name="btn_sure" value="确定" onClick="JavaScript:saveData();">
                   <input type="reset" class="btn btn-disabled" name="btn_reset" value="重置">
                   <input type="hidden" name="tokenid" value="${tokenid}">
                   <input type="hidden" name="querymap.unitid" value="${querymap.unitid}">
                   <input type="hidden" name="querymap.specname" id="specname">
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