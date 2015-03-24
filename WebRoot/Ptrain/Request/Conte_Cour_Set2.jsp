<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<script language="javascript" src="<c:url value='/Script/Main.js'/>"></script>
</head>
<body>
<div class="container">
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 标题区域 --->
    <div class="titleArea">
        <div class="titleClass">课件培训点菜-导入</div>
    </div>
    <!-- 分隔符 --->
    <div class="lineSpac"></div>
    <!-- 查询条件 --->
      	<form name="form1" action="" method="post" enctype="multipart/form-data">
        <table class="editTable">
             <!--表格内容部分-->
             <tbody>
             <tr>
                 <!--左侧表格项-->
                 <td nowrap class="left">专业类别：</td>
                 <!--右侧表格项-->
                 <td class="right"><select name="ptrainReqtempBean.specid" id="specid">
                 		<option value="">全部</option>
                 		<c:forEach items="${specList}" var="s">
                 			<option value="${s.id}" ${s.id==querymap.specid?'selected':''}>${s.codename}</option>
                 		</c:forEach>
                     </select>
                 </td>
             </tr>
             <tr>
                 <td nowrap class="left">*&nbsp;文件路径：</td>
                 <td class="right">
                     <input type="hidden" name="filefat" value="xls"/>
                     <input type="hidden" name="filesize" value="50"/>
                     <input type="file" name="upfile" class="textfile_50" onChange="JavaScript:checkFile();"/> （≤50MB，*.xls）
                     <a href="JavaScript:downLoad('Table03.xls','/Ptrain/Request/Download/')"/>&gt;&gt;&nbsp;格式下载</a>
                 </td>            
             </tr>
             <tr>
                 <td nowrap class="left">导入方式：</td>
                 <td class="right">
                 	<input type="radio" name="querymap.impsign" value="1"/>清空后导入
                 	<input type="radio" name="querymap.impsign" value="5" checked/>全部都追加
                 </td>
             </tr>
             </tbody> 
             <!--表格合计或操作部分-->
             <tfoot> 
             <tr>
                 <td colspan="2" align="center">
                     <input type="button" name="btn_sure" class="btn btn-danger" value="预览" onClick="JavaScript:saveData();"/>
                     <input type="reset" name="btn_reset" class="btn btn-disabled" value="重置"/>
                     <input type="button" name="btn_back" class="btn btn-info" value="返回" onClick="JavaScript:goBack();"/>
                     <input type="hidden" name="tokenid" value="${tokenid}"/>
                     <input type="hidden" name="sign" value="${sign}"/>
                     <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                     <input type="hidden" name="querymap.specid" value="${querymap.specid}"/>
                     <input type="hidden" name="querymap.reqtype" value="${querymap.reqtype}"/>
                     <input type="hidden" name="fields" value="${fields}" id="fields"/>
                     <input type="hidden" name="keyword" value="${keyword}" id="keyword"/>
                     <input type="hidden" name="tagpage" value="${tagpage}" id="tagpage" />
                     <input type="hidden" name="record" value="${record}" id="record" />
                     <input type="hidden" name="listpage" value="${listpage}" />
                     <input type="hidden" name="listtemppage" value="${listtemppage}" />
                     <input type="hidden" name="settemppage" value="${settemppage}" />
                     <input type="hidden" name="querymap.specidchk" id="specidchk" />
                     <input type="hidden" name="savePath" value="${savePath}" />
                 </td>
             </tr>  	                
             </tfoot>
        </table>
    </form>
</div>
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
		$("#tagpage").val("1")
		$("#typeid,#keyword").val("");
		$("#specidchk").val($("#specid").val());
		openProcessDiv();
		document.form1.action="<c:url value='/Ptrain/Req/saveptrainreqtempexcel.shtml' />";
	    document.form1.submit();
	}
	//返回
	function goBack(){
	    document.form1.action="<c:url value='/Ptrain/Req/listptrainreq_cour.shtml' />";
	    document.form1.submit();
	}
    //下载文件 
	function downLoad(savename,savepath){
	   downframe.document.form1.inputPath.value = savepath;
	   downframe.document.form1.fileName.value = savename;
	   downframe.document.form1.submit();
	}
</script>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>