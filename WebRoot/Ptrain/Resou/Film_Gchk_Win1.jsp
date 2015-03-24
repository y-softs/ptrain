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
	<link rel="stylesheet" href="<c:url value='/Style/valid.css'/>"/>
	<script language="javascript" src="<c:url value='/Script/Main.js'/>"></script>
	<script language="javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<link href="<c:url value='/Uploadify/uploadify.css'/>" rel="stylesheet" type="text/css"/>
	<script language="javascript" src="<c:url value='/Uploadify/swfobject.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/jquery.uploadify.v2.1.4.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/FileDBUtil.js'/>"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			operFile();
			$("#submitBtn").bind("click",callBack);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
			$("#tr_0").hide();
			
			$("select[name='typeid']").bind("change",function(){$("input[name='ptrainContentBean.typeid']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='title']").bind("blur",function(){$("input[name='ptrainContentBean.title']").val($(this).val());$("#dataChange").val("true");});
			$("textarea[name='content']").bind("blur",function(){$("input[name='ptrainContentBean.content']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='sortnum']").bind("blur",function(){$("input[name='ptrainContentBean.object']").val($(this).val());$("#dataChange").val("true");});
						
			//绑定类别
			$("select[name='typeid']").attr("fv-empty","false").attr("fv-empty-msg","资源类别<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_typeid");
			//标题
			$("input[name='title']").attr("fv-maxlength","150").attr("fv-maxlength-msg","字数不能超过150字")
							.attr("fv-empty","false").attr("fv-empty-msg","标题<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_title");
			//文本内容
			$("textarea[name='content']").attr("fv-maxlength","50000").attr("fv-maxlength-msg","字数不能超过50000字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_content");
			//绑定排序号
			$("input[name='sortnum']").attr("fv-empty","false").attr("fv-empty-msg","排序号<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-datatype","Float").attr("fv-datatype-msg","排序号<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_sortnum");
			//绑定form表单自定义验证
			$("#form2").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		});
	    //验证绑定
	    function operFiled(){
	    	if($("input[name=querymap.chksign]:checked").val()=='${BU_TONG_YI}'){
				$("#chkmemo").attr("fv-empty","false").attr("fv-empty-msg"," 意见<fmt:message key="format.null" bundle="${bms}"/>")
								.attr("fv-msg-success","").attr("fv-msgpanel","valid_chkmemo");
				$("#tr_0").show();
			}else{
				$("#chkmemo").attr("fv-empty","true").attr("fv-empty-msg","")
								.attr("fv-msg-success","").attr("fv-msgpanel","valid_chkmemo");
				$("#tr_0").hide();
			}
	    }
	  	//附件文件——多文件
		function operFile(){
			var fUtil2 = new FileDBUtil();	//引入文件上传类
			fUtil2.multiFileUpload = true;	//是否进行多文件上传[改动]
			fUtil2.uploadFileId = "upfile";	//文件控件id
			fUtil2.modsign   = "${modsign}";//模块标志
			fUtil2.fileUpLoadStyle = "2";	//非1：多文件上传1:替换新增
			fUtil2.saveFolder   = "${savePath}";	//保存路径
			fUtil2.relaDataId   = "${ptrainContentBean.id}"; //主记录id			
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
	          	<c:forEach items="${fileslist}" var="file" varStatus="st">
	          		fileListArr.push({"index":"${st.index}","id":"${file.id}","savepath":"${file.savepath}","savename":"${file.savename}","filename":"${file.filename}"});
	          	</c:forEach>
	        fUtil2.makeFileList(fileListArr);
			fUtil2.onLoadFileUploader();
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
	</script>
</head>
<style>
	.titleClass {clear:both;display:block;float:none;height:30px;width:100%;text-align:center;}
	.splarLine  {float:left;width:3px;height:400px;background:#321;}
	.floatClass  {padding-left:10px;float:left;width:48%;height:400px;background:#FFF;}
</style>
<body>
<div class="container">
    <!-- 查询条件 --->
    <form name="form2" id="form2" action="" method="post">
    	<div class="floatClass">
    	<span class="titleClass">申报内容</span>
        <table class="editTable">
           <tbody>
            <tr>
                <!--左侧表格项-->
                <td nowrap class="left">*&nbsp;资源类别：</td>
                <!--右侧表格项-->
                <td class="right"><select name="typeid">
                		<option value="">请选择</option>
                		<c:forEach items="${specList}" var="s">
                			<option value="${s.id}" ${s.id==ptrainContentBean.typeid||s.id==querymap.typeid?'selected':''}>${s.codename}</option>
                		</c:forEach>
                    </select>
                    <span id="valid_typeid" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">*&nbsp;标题：</td>
                <td class="right">
                	<input name="title" type="text" class="text_50" value="${ptrainContentBean.title}"/>
                	<span id="valid_title" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">文本内容：</td>
                <td class="right">
                    <textarea name="content" class="textarea_50_8">${ptrainContentBean.content}</textarea><br/>
                    <span id="valid_content" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">文件信息：</td>
                <td class="right">
                    <div id="upfile_span">
                       <input type="file" name="uploads" id="upfile"/>（≤500MB，*）
                    </div>
                </td>
            </tr>
            <tr>
                <td class="left">*&nbsp;排序号：</td>
                <td class="right">
                	<input name="sortnum" type="text" class="text_04" value="${ptrainContentBean.sortnum}"/>
                	<span id="valid_sortnum" class="validate-info"></span>
                </td>
            </tr>
           </tbody>
        </table>
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
                    <input type="radio" name="querymap.chksign" value="${TONG_YI}" checked onClick="operFiled();"/>同意
                    <input type="radio" name="querymap.chksign" value="${BU_TONG_YI}" onClick="operFiled();"/>不同意
                </td>
            </tr>
            <tr id="tr_0">
                <td nowrap class="left">*&nbsp;</td>
                <td class="right">
                	<textarea name="querymap.chkmemo" id="chkmemo" class="textarea_all_3"></textarea>
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
                    <input type="hidden" name="ptrainContentBean.typeid" value="${ptrainContentBean.typeid}"/>
                    <input type="hidden" name="ptrainContentBean.title" value="${ptrainContentBean.title}"/>
                    <input type="hidden" name="ptrainContentBean.content" value="${ptrainContentBean.content}"/>
                    <input type="hidden" name="ptrainContentBean.sortnum" value="${ptrainContentBean.sortnum}"/>
                    <input type="hidden" name="querymap.dataChange" id="dataChange"/>
					<span id="formerror"></span>
                </td>
            </tr>
            </tbody>
        </table>
       </div>
    </form>
</div>
</body>
</html>