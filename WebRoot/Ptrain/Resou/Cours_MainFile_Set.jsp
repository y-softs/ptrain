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
	<link rel="stylesheet" href="../../Style/valid.css"/>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<link href="<c:url value='/Uploadify/uploadify.css'/>" rel="stylesheet" type="text/css"/>
	<script language="javascript" src="<c:url value='/Uploadify/swfobject.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/jquery.uploadify.v2.1.4.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/FileDBUtil.js'/>"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
</head>
<body>
<div class="editContainer">
    <div class="errorBlock"></div>
    <!-- 查询条件 --->
    <form name="form1" id="form1" action="" method="post">
        <table class="editTable">
            <!--表格内容部分-->
            <tbody>
            <tr>
                <!--左侧表格项-->
                <td class="left">文件名称：</td>
                <!--右侧表格项-->
                <td class="right" id="fileobj">
                    <div id="upfile_span">
                       <input type="file" name="uploads" id="upfile"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="left">文件子目录：</td>
                <td class="right">
                    <input type="text" name="querymap.subfilepath" id="subfilepath" value="${querymap.subfilepath}" />
                    
                    <input type="hidden" name="ptrainCoursBean.fatherid" value="${ptrainCoursBean.fatherid}"/>
                    <input type="hidden" name="savePath" value="${savePath}"/>
                </td>
            </tr>
            </tbody>
            <!--表格合计或操作部分-->
            <tfoot>
            <tr>
                <td colspan="2">
					<span id="formerror"></span>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script type="text/javascript">
	$(function(){
		operFile();
		$("#subfilepath").bind("blur",function(){
			if(''!=$("#subfilepath").val()){
				$("#fileobj").html('');
				$("#fileobj").append('<div id="upfile_span"><input type="file" name="uploads" id="upfile"/>（≤500MB，*）</div>');
				operFile();
			}
		});
	});
  	//附件文件——多文件
	function operFile(){
		var filepath=$("#subfilepath").val();
		if(''!=filepath){
			if(filepath.indexOf('//')>=0||filepath.indexOf('\\')>=0){
				alert("文件子目录不规范！");
				return;
			}
			var benIndex=filepath.indexOf('/'),endIndex=filepath.lastIndexOf('/'),lengthV=filepath.length;
			if(filepath.indexOf('/')==0){
				filepath=filepath.substr(benIndex+1,lengthV);
			}
			if((endIndex+1)==lengthV){
				filepath=filepath.substr(0,filepath.length-1);
			}
			filepath=filepath+'/';
		}
		var fUtil2 = new FileDBUtil();	//引入文件上传类
		fUtil2.multiFileUpload = true;	//是否进行多文件上传[改动]
		fUtil2.uploadFileId = "upfile";	//文件控件id
		fUtil2.modsign   = "${modsign}";//模块标志
		fUtil2.fileUpLoadStyle = "2";	//非1：多文件上传1:替换新增
		fUtil2.saveFolder   = "${savePath}${ptrainCoursBean.fatherid}/"+filepath;	//保存路径
		fUtil2.relaDataId   = "${ptrainCoursBean.fatherid}"; //主记录id	
		fUtil2.uploadFileSizeLimit   = 1024*1024*50000; //900M
		fUtil2.updateName   = "1";//是否更新文件名称
		fUtil2.uploadFileDesc   = "支持格式:*";
		fUtil2.uploadFileExt    = "*";
		fUtil2.setConfigValues({
			'custom_file_delete_jqurl':'<c:url value="/Ptrain/File/deletePtrainFileByJq.shtml"/>',
			'custom_file_insert_jqurl':'<c:url value="/Ptrain/File/insertPtrainFileByJq.shtml"/>',
			'custom_file_upload_jqurl':'<c:url value="/Ptrain/Cours/uploadFile.shtml"/>'//文件上传后台处理程序url
		});
		//将后台数据转化为数据组 -- 文件列表
		var fileListArr = new Array();
          	<c:forEach items="${fileslist}" var="file" varStatus="st">
          		fileListArr.push({"index":"${st.index}","id":"${file.id}","savepath":"${file.savepath}","savename":"${file.savename}","filename":"${file.filename}"});
          	</c:forEach>
        fUtil2.makeFileList(fileListArr);
		fUtil2.onLoadFileUploader();
	}
   function callBack(){
		$("#form1").attr("action",'<c:url value='/Ptrain/Req/saveptrainreq.shtml'/>').trigger("submit");
		if($("#formerror").text() == "ok"){
	    	if($("#gosign").is(":checked")){
	    		return '1';
	    	}else{
				return 'true';
	    	}
		}
		return false;
   }
</script>
</body>
</html>