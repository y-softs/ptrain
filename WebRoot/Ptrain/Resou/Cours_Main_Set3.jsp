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
    <form name="form1" id="form1" action="" method="post">
        <table class="editTable">
            <!--表格内容部分-->
            <tbody>
            <tr>
                <!--左侧表格项-->
                <td nowrap class="left">*&nbsp;课件类别：</td>
                <!--右侧表格项-->
                <td class="right"><select name="ptrainCoursBean.typeid">
                		<option value="">请选择</option>
                		<c:forEach items="${specList}" var="s">
                			<option value="${s.id}" ${s.id==ptrainCoursBean.typeid||s.id==querymap.typeid?'selected':''}>${s.codename}</option>
                		</c:forEach>
                    </select>
                    <span id="valid_typeid" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">*&nbsp;课件名称：</td>
                <td class="right">
                	<input name="ptrainCoursBean.title" type="text" class="text_50" value="${ptrainCoursBean.title}"/>
                	<span id="valid_title" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td class="left">封面：</td>
                <td class="right">
                    <div id="upfile_span">
                       <input type="file" name="uploads" id="upfile"/>（≤500MB，*）
                    </div>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">课件简介：</td>
                <td class="right">
                    <textarea name="ptrainCoursBean.content" class="textarea_50_8">${ptrainCoursBean.content}</textarea><br/>
                    <span id="valid_content" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td class="left">附件：</td>
                <td class="right">
                    <div id="upfile2_span">
                       <input type="file" name="uploads" id="upfile2"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">课件制作人：</td>
                <td class="right">
                	<input name="ptrainCoursBean.producer" type="text" class="text_15" maxlength="10" value="${ptrainCoursBean.producer}"/>
                	<span id="valid_producer" class="validate-info"></span>	
                </td>
            </tr>
            <c:if test="${empty ptrainCoursBean.id}">
            <tr>
                <td nowrap class="left">&nbsp;继续新增：</td>
                <td class="right">
                	<input type="checkbox" name="gosign" id="gosign" value="1" ${'1'==gosign?'checked':''}/>
                </td>
            </tr>
            </c:if>
            </tbody>
            <!--表格合计或操作部分-->
            <tfoot>
            <tr>
                <td colspan="2">
                	<input type="button" id="submitBtn" class="btn btn-info" value="确定"/>
                	<input type="button" id="closeBtn" class="btn btn-info" value="关闭"/>
                    <input type="hidden" name="tokenid" value="${tokenid}"/>
                    <input type="hidden" name="ptrainCoursBean.id" value="${ptrainCoursBean.id}"/>
                    <input type="hidden" name="ptrainCoursBean.fatherid" value="${fatherid}"/>
                    <input type="hidden" name="ptrainCoursBean.flowsta" value="${querymap.flowsta}"/>
                    <input type="hidden" name="ptrainCoursBean.courstype" value="2"/>
                    <!-- 回帖 -->
                    <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                    <input type="hidden" name="querymap.flowsta" value="${querymap.flowsta}"/>
                    <input type="hidden" name="fatherid" value="${fatherid}"/>
                    <input type="hidden" name="setpage" value="${setpage}"/>
                    <input type="hidden" name="savePath" value="${savePath}" />
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
		operFile2();
		$("#submitBtn").bind("click",callBack);
		$("#closeBtn").bind("click",function(){
			parent.$.jBox.close();
		});
		//绑定类别
		$("select[name='ptrainCoursBean.typeid']").attr("fv-empty","false").attr("fv-empty-msg","课件类别<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_typeid");
		//课件名称
		$("input[name='ptrainCoursBean.title']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
						.attr("fv-empty","false").attr("fv-empty-msg","课件名称<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_title");
		//课件简介
		$("textarea[name='ptrainCoursBean.content']").attr("fv-maxlength","500").attr("fv-maxlength-msg","字数不能超过500字")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_content");
		//绑定form表单自定义验证
		$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
	});
   function callBack(){
		$("#form1").attr("action",'<c:url value='/Ptrain/Cours/saveptraincours.shtml'/>').trigger("submit");
		if($("#formerror").text() == "ok"){
	    	if($("#gosign").is(":checked")){
	    		return '1';
	    	}else{
				return 'true';
	    	}
		}
		return false;
   }
  	//附件文件——多文件（封面）
	function operFile(){
		var fUtil2 = new FileDBUtil();	//引入文件上传类
		fUtil2.multiFileUpload = false;	//是否进行多文件上传[改动]
		fUtil2.uploadFileId = "upfile";	//文件控件id
		fUtil2.modsign   = "${modsign}";//模块标志
		fUtil2.fileUpLoadStyle = "1";	//非1：多文件上传1:替换新增
		fUtil2.saveFolder   = "${savePath}";	//保存路径
		fUtil2.relaDataId   = "${ptrainCoursBean.id}"; //主记录id	
		fUtil2.uploadFileSizeLimit   = 1024*1024*50000; //500M
		fUtil2.uploadFileDesc   = "支持格式:*";
		fUtil2.uploadFileExt    = "*";
		fUtil2.setConfigValues({
			'custom_file_delete_jqurl':'<c:url value="/Ptrain/File/deletePtrainFileByJq.shtml"/>',
			'custom_file_insert_jqurl':'<c:url value="/Ptrain/File/insertPtrainFileByJq.shtml"/>',
			'custom_file_upload_jqurl':'<c:url value="/uploadFile.shtml"/>'//文件上传后台处理程序url
		});
		//将后台数据转化为数据组 -- 文件列表
		var fileListArr = new Array();
          	<c:forEach items="${fileslist}" var="file" varStatus="st">
          		fileListArr.push({"index":"${st.index}","id":"${file.id}","savepath":"${file.savepath}","savename":"${file.savename}","filename":"${file.filename}"});
          	</c:forEach>
        fUtil2.makeFileList(fileListArr);
		fUtil2.onLoadFileUploader();
	}
  	//附件文件——多文件（文件）
	function operFile2(){
		var fUtil2 = new FileDBUtil();	//引入文件上传类
		fUtil2.multiFileUpload = false;	//是否进行多文件上传[改动]
		fUtil2.uploadFileId = "upfile2";	//文件控件id
		fUtil2.modsign   = "${modsign2}";//模块标志
		fUtil2.fileUpLoadStyle = "2";	//非1：多文件上传1:替换新增
		fUtil2.saveFolder   = "${savePath}";	//保存路径
		fUtil2.relaDataId   = "${ptrainCoursBean.id}"; //主记录id	
		fUtil2.uploadFileSizeLimit   = 1024*1024*500; //500M
		fUtil2.uploadFileDesc   = "支持格式:*";
		fUtil2.uploadFileExt    = "*";
		fUtil2.setConfigValues({
			'custom_file_delete_jqurl':'<c:url value="/Ptrain/File/deletePtrainFileByJq.shtml"/>',
			'custom_file_insert_jqurl':'<c:url value="/Ptrain/File/insertPtrainFileByJq.shtml"/>',
			'custom_file_upload_jqurl':'<c:url value="/uploadFile.shtml"/>'//文件上传后台处理程序url
		});
		//将后台数据转化为数据组 -- 文件列表
		var fileListArr = new Array();
          	<c:forEach items="${fileslist2}" var="file" varStatus="st">
          		fileListArr.push({"index":"${st.index}","id":"${file.id}","savepath":"${file.savepath}","savename":"${file.savename}","filename":"${file.filename}"});
          	</c:forEach>
        fUtil2.makeFileList(fileListArr);
		fUtil2.onLoadFileUploader();
	}
</script>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>