<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 配置文件信息 -->
<fmt:setBundle basename="globalMessages" var="bms"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/columns.css'/>"/>
	<link href="<c:url value='/Uploadify/uploadify.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<c:url value='/Web/Script/Valid.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/swfobject.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/jquery.uploadify.v2.1.4.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/FileDBUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery.jqtransform.js'/>"></script>
    <script type="text/javascript">
    	$(function(){
    		$('.rowElem').jqTransform();
    		FileContainer();
		});
		function subMit(){
			if(validateInfo()){				
				$("#form2").attr("action",'<c:url value="/pt/bbs/savetrainbbs.shtml"/>').trigger("submit");
			}
		}
		//验证
		function validateInfo(){			
			$("#specid").attr("dataType","Require").attr("msg","专业类别<fmt:message key="format.null" bundle="${bms}"/>");
			$("#title").attr("dataType","Require").attr("msg","帖子<fmt:message key="format.null" bundle="${bms}"/>");
			$("#content").attr("dataType","Require").attr("msg","内容<fmt:message key="format.null" bundle="${bms}"/>");
			return Validator.Validate(document.form2,2);
		}
		//加载附件上传控制器
		function FileContainer(){
			var fUtil = new FileDBUtil();
			fUtil.multiFileUpload = true;
			fUtil.fileUpLoadStyle = "2";
			fUtil.uploadFileId = "upfile";
			fUtil.modsign      = "${MODSIGN}";  
			fUtil.saveFolder   = "${SAVEPATH}";
			fUtil.relaDataId   = "${ptrainBbsBean.id}";			
			fUtil.uploadFileSizeLimit   = 1024*1024*500; //1024M
			fUtil.uploadFileDesc   = "支持格式:*";
			fUtil.uploadFileExt    = "*";
			fUtil.setConfigValues({
				'custom_file_delete_jqurl':'<c:url value="/Ptrain/File/deletePtrainFileByJq.shtml"/>',
				'custom_file_insert_jqurl':'<c:url value="/Ptrain/File/insertPtrainFileByJq.shtml"/>',
				'custom_file_upload_jqurl':'<c:url value="/uploadFile.shtml"/>'
			});
			//将后台数据转化为数据组
			var fileListArr = new Array();
	          	<c:forEach items="${fileList}" var="file" varStatus="st">
	          		fileListArr.push({"index":"${st.index}","id":"${file.id}","savepath":"${file.savepath}","savename":"${file.savename}","filename":"${file.filename}"});
	          	</c:forEach>
	        fUtil.makeFileList(fileListArr);
			fUtil.onLoadFileUploader();
		};
    </script>
<body>
<div class="set_tab">
<form action="" name="form2" id="form2" method="post">
	<table>
    	<tr>
        	<td>
            	<div class="rowElem" style="float:left;">
            	<select name="ptrainBbsBean.specid" id="specid">
                    <option value="">请选择专业</option>                                    
                    <c:forEach items="${codeList}" var="d">
           	 			<option value="${d.id}" ${d.id==ptrainBbsBean.specid?'selected':''}>${d.codename}</option>
           	 		</c:forEach>
                </select>
                </div>
                <input type="text" name="ptrainBbsBean.title" id="title"value="${ptrainBbsBean.title }" maxlength="50" class="text text_50" onpropertychange="checkMaxLen(this,50)" /><span class="fontcolor_red">（50字以内，当前<span id="count">0</span>字）</span>
           </td>
        </tr>
    	<tr>
        	<td>
            	<textarea name="ptrainBbsBean.content" id="content" class="textarea_50_8">${ptrainBbsBean.content }</textarea>
           </td>
        </tr>
    	<tr>
        	<td>
            	<span style="float:left;">附件：</span><div id="upfile_span"><input type="file" name="upfile" id="upfile" />（≤500MB，*）</div>
           </td>
        </tr>
        <tr>
        	<td>
                <a href="###" class="btn btn-primary" onclick="subMit();">${fun=='1'?'发布':'保存'}</a>
                <a href="###" class="btn btn-inverse" onclick="layerClose();">关闭</a>
                 <input type="hidden" name="fun" 	value="${fun}" />
                 <input type="hidden" name="tokenid" value="${tokenid}" />
                 <input type="hidden" name="ptrainBbsBean.id" value="${ptrainBbsBean.id}" />
                 <input type="hidden" name="ptrainBbsBean.kind" value="${KIND}" />
                 <input type="hidden" name="ptrainBbsBean.anssign" value="0" />
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>