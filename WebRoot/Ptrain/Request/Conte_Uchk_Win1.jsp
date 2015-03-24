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
	<script language="javascript" src="<c:url value='/Script/plugin/valid/FormValidator.1.0.min.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			if(${ptrainReqBean.reqtype==REQ_EXP}){operFile();}
			$("#submitBtn").bind("click",callBack);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
			$("#tr_0").hide();
			
			$("select[name='specid']").bind("change",function(){$("input[name='ptrainReqBean.specid']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='itemname']").bind("blur",function(){$("input[name='ptrainReqBean.itemname']").val($(this).val());$("#dataChange").val("true");});
			$("textarea[name='itemdesc']").bind("blur",function(){$("input[name='ptrainReqBean.itemdesc']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='daycount']").bind("blur",function(){$("input[name='ptrainReqBean.daycount']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='reqform']").bind("blur",function(){$("input[name='ptrainReqBean.reqform']").val($(this).val());$("#dataChange").val("true");});
			$("input[name='teacher']").bind("blur",function(){$("input[name='ptrainReqBean.teacher']").val($(this).val());$("#dataChange").val("true");});
			//绑定类别
			$("select[name='specid']").attr("fv-empty","false").attr("fv-empty-msg","专业类别<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_specid");
			//项目名称
			$("input[name='itemname']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
						.attr("fv-empty","false").attr("fv-empty-msg","项目名称<fmt:message key="format.null" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_itemname");
			//课程介绍
			$("textarea[name='itemdesc']").attr("fv-maxlength","1000").attr("fv-maxlength-msg","字数不能超过1000字")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_itemdesc");
			//绑定培训天数
			$("input[name='daycount']").attr("fv-empty","false").attr("fv-empty-msg","培训天数<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-datatype","Float").attr("fv-datatype-msg","培训天数<fmt:message key="format.int" bundle="${bms}"/>")
						.attr("fv-msg-success","").attr("fv-msgpanel","valid_daycount");	
			
			if(${ptrainReqBean.reqtype==REQ_EXP}){
				//绑定项目来源
				$("input[name='reqform']").attr("fv-empty","false").attr("fv-empty-msg","项目来源<fmt:message key="format.null" bundle="${bms}"/>")
								.attr("fv-msg-success","").attr("fv-msgpanel","valid_reqform");
				//绑定 培训师
				$("input[name='teacher']").attr("fv-empty","false").attr("fv-empty-msg"," 培训师<fmt:message key="format.null" bundle="${bms}"/>")
								.attr("fv-msg-success","").attr("fv-msgpanel","valid_teacher");
			}
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
		//保存
		function callBack(){
			operFiled();
			
			$("#form2").attr("action",'<c:url value='/Ptrain/Req/updateptrainreqchk.shtml'/>').trigger("submit");
	
			if($("#formerror").text() == "ok"){
		    	return true;
			}
			return false;
		}
	  	//附件文件——多文件
		function operFile(){
			var fUtil2 = new FileDBUtil();	//引入文件上传类
			fUtil2.multiFileUpload = true;	//是否进行多文件上传[改动]
			fUtil2.uploadFileId = "upfile";	//文件控件id
			fUtil2.modsign   = "${modsign}";//模块标志
			fUtil2.fileUpLoadStyle = "2";	//非1：多文件上传1:替换新增
			fUtil2.saveFolder   = "${savePath}";	//保存路径
			fUtil2.relaDataId   = "${ptrainReqBean.id}"; //主记录id	
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
               <td nowrap class="left">专业类别：</td>
               <td class="right"><select name="specid">
               		<option value="">请选择</option>
               		<c:forEach items="${specList}" var="s">
               			<option value="${s.id}" ${s.id==ptrainReqBean.specid||s.id==querymap.specid?'selected':''}>${s.codename}</option>
               		</c:forEach>
                   </select>
                    <span id="valid_specid" class="validate-info"></span>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">项目名称：</td>
               <td class="right">
               	<input name="itemname" type="text" class="text_50" value="${ptrainReqBean.itemname}"/>
               	<span id="valid_itemname" class="validate-info"></span>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">课程介绍：</td>
               <td class="right">
                   <textarea name="itemdesc" class="textarea_all_8">${ptrainReqBean.itemdesc}</textarea><span id="valid_itemdesc" class="validate-info"></span><br/>
                   <c:if test="${ptrainReqBean.reqtype==REQ_EXP}">
                   <div id="upfile_span"><input type="file" name="uploads" id="upfile"/></div>
                   </c:if>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">培训天数：</td>
               <td class="right">
               	<input name="daycount" type="text" class="text_04" maxlength="3" value="${ptrainReqBean.daycount}"/>&nbsp;天
                <span id="valid_daycount" class="validate-info"></span>
               </td>
           </tr>
           <c:choose>
           <c:when test="${ptrainReqBean.reqtype==REQ_USER}">
           <tr>
               <td nowrap class="left">发起人：</td>
               <td class="right">
               	<input type="text" name="mainuser" class="text_10line" value="${ptrainReqBean.requserid}" maxlength="10" readonly/>
               </td>
           </tr>
           </c:when>
           <c:when test="${ptrainReqBean.reqtype==REQ_EXP}">
           <tr>
               <td nowrap class="left">项目来源：</td>
               <td class="right">
                  <input name="reqform" type="text" class="text_50" value="${ptrainReqBean.reqform}" maxlength="50"/>
                  <span id="valid_reqform" class="validate-info"></span>
               </td>
           </tr>
           <tr>
               <td nowrap class="left">培训师：</td>
               <td class="right">
               	<input name="teacher" type="text" class="text_15" maxlength="10" value="${ptrainReqBean.teacher}"/>
                <span id="valid_teacher" class="validate-info"></span>	
               </td>
           </tr>
           </c:when>
           </c:choose>
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
                    <input type="hidden" name="ptrainReqBean.id" value="${ptrainReqBean.id}" id="id"/>
                    <input type="hidden" name="ptrainReqBean.specid" value="${ptrainReqBean.specid}"/>
                    <input type="hidden" name="ptrainReqBean.itemname" value="${ptrainReqBean.itemname}"/>
                    <input type="hidden" name="ptrainReqBean.itemdesc" value="${ptrainReqBean.itemdesc}"/>
                    <input type="hidden" name="ptrainReqBean.daycount" value="${ptrainReqBean.daycount}"/>
                    <input type="hidden" name="ptrainReqBean.reqform" value="${ptrainReqBean.reqform}"/>
                    <input type="hidden" name="ptrainReqBean.teacher" value="${ptrainReqBean.teacher}"/>
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
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>