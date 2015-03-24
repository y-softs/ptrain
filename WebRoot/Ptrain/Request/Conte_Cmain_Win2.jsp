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
	<script language="javascript" src="<c:url value='/Script/jquery-1.4.2.min.js'/>"></script>
	<link href="<c:url value='/Uploadify/uploadify.css'/>" rel="stylesheet" type="text/css"/>
	<script language="javascript" src="<c:url value='/Uploadify/swfobject.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/jquery.uploadify.v2.1.4.js'/>"></script>
	<script language="javascript" src="<c:url value='/Uploadify/FileDBUtil.js'/>"></script>
	<script type="text/javascript" src="../../Script/plugin/valid/FormValidator.1.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			operFile();
			$("#submitBtn").bind("click",saveData);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
			$("input[name='btn_sure']").bind("click",saveData);
			
			//绑定类别
			$("#specid").attr("fv-empty","false").attr("fv-empty-msg","专业类别<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_specid");
			//项目名称
			$("input[name='ptrainReqtempBean.itemname']").attr("fv-maxlength","50").attr("fv-maxlength-msg","字数不能超过50字")
							.attr("fv-empty","false").attr("fv-empty-msg","项目名称<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_itemname");
			//课程介绍
			$("textarea[name='ptrainReqtempBean.itemdesc']").attr("fv-maxlength","1000").attr("fv-maxlength-msg","字数不能超过1000字")
							.attr("fv-empty","false").attr("fv-empty-msg","课程介绍<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_itemdesc");
			//绑定项目来源
			$("input[name='ptrainReqtempBean.reqform']").attr("fv-empty","false").attr("fv-empty-msg","项目来源<fmt:message key="format.null" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_reqform");
			//绑定 培训师
			var tmpTip = "培训师<fmt:message key="format.null" bundle="${bms}"/>";
	        if(${REQ_COUR==ptrainReqtempBean.reqtype}){
	        	tmpTip = "课件制作人<fmt:message key="format.null" bundle="${bms}"/>";
	        }  
			$("input[name='ptrainReqtempBean.teacher']").attr("fv-empty","false").attr("fv-empty-msg",tmpTip)
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_teacher");
			//绑定培训天数
								
			$("input[name='ptrainReqtempBean.daycount']").attr("fv-empty","false").attr("fv-empty-msg","培训天数<fmt:message key="format.int" bundle="${bms}"/>")
							.attr("fv-datatype","Float").attr("fv-datatype-msg","培训天数<fmt:message key="format.int" bundle="${bms}"/>")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_daycount");
			//绑定form表单自定义验证
			$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		});
		//保存
		function saveData(){
			$("#form1").attr("action",'<c:url value='/Ptrain/Req/saveptrainreqtemp.shtml'/>').trigger("submit");
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
			fUtil2.relaDataId   = "${ptrainReqtempBean.id}"; //主记录id		
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
                <td nowrap class="left">*&nbsp;专业类别：</td>
                <!--右侧表格项-->
                <td class="right">
                <c:choose>
                <c:when test="${empty querymap.specidchk}">
                	<select name="ptrainReqtempBean.specid" id="specid">
                		<option value="">请选择</option>
                		<c:forEach items="${specList}" var="s">
                			<option value="${s.id}" ${s.id==ptrainReqtempBean.specid?'selected':''}>${s.codename}</option>
                		</c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                	<select name="specid" id="specid" disabled id="specid">
                		<option value="">全部</option>
                		<c:forEach items="${specList}" var="s">
                			<option value="${s.id}" ${s.id==querymap.specidchk?'selected':''}>${s.codename}</option>
                		</c:forEach>
                    </select>
                    <input type="hidden" name="ptrainReqtempBean.specid" value="${querymap.specidchk}"/>
                </c:otherwise>
                </c:choose>
                    <span id="valid_specid" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">*&nbsp;项目名称：</td>
                <td class="right">
                	<input name="ptrainReqtempBean.itemname" type="text" class="text_50" value="${ptrainReqtempBean.itemname}"/>
                	<span id="valid_itemname" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">*&nbsp;课程介绍：</td>
                <td class="right">
                    <textarea name="ptrainReqtempBean.itemdesc" class="textarea_50_8">${ptrainReqtempBean.itemdesc}</textarea><span id="valid_itemdesc" class="validate-info"></span><br/>
                    <div id="upfile_span">
                       <input type="file" name="uploads" id="upfile"/>
                    </div>
                </td>
            </tr>
            <c:if test="${REQ_COUR==ptrainReqtempBean.reqtype}">                        
            <tr>
                <td nowrap class="left">课件制作人：</td>
                <td class="right">
                	<input name="ptrainReqtempBean.teacher" type="text" class="text_15" maxlength="10" value="${ptrainReqtempBean.teacher}">
                	<span id="valid_teacher" class="validate-info"></span>	
                </td>
            </tr>
            </c:if>
            <c:if test="${REQ_COUR!=ptrainReqtempBean.reqtype}">
            <tr>
                <td nowrap class="left">项目来源：</td>
                <td class="right">
                   <input name="ptrainReqtempBean.reqform" type="text" class="text_50" value="${ptrainReqtempBean.reqform}" maxlength="50"/>
                	<span id="valid_reqform" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">*&nbsp;培训天数：</td>
                <td class="right">
                	<input name="ptrainReqtempBean.daycount" type="text" class="text_04" maxlength="3" value="${ptrainReqtempBean.daycount}"/>&nbsp;天
                	<span id="valid_daycount" class="validate-info"></span>
                </td>
            </tr>
            <tr>
                <td nowrap class="left">培训师：</td>
                <td class="right">
                	<input name="ptrainReqtempBean.teacher" type="text" class="text_15" maxlength="10" value="${ptrainReqtempBean.teacher}">
                	<span id="valid_teacher" class="validate-info"></span>	
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
                    <input type="hidden" name="ptrainReqtempBean.id" value="${ptrainReqtempBean.id}"/>
                    <input type="hidden" name="ptrainReqtempBean.unitid" value="${querymap.unitid}"/>
                    <input type="hidden" name="ptrainReqtempBean.reqtype" value="${querymap.reqtype}"/>
                    <input type="hidden" name="ptrainReqtempBean.spectemp" value="${ptrainReqtempBean.spectemp}" id="spectemp"/>
                    <input type="hidden" name="ptrainReqtempBean.daycounttemp" value="${ptrainReqtempBean.daycounttemp}" id="daycounttemp"/>
                    <input type="hidden" name="ptrainReqtempBean.datasign" value="${'-1'!=ptrainReqtempBean.datasign?ptrainReqtempBean.datasign:'1'}"/>
                    <!-- 回帖 -->
                    <input type="hidden" name="sign" value="${sign}"/>
                    <input type="hidden" name="querymap.unitid" value="${querymap.unitid}"/>
                    <input type="hidden" name="querymap.specid" value="${querymap.specid}"/>
                    <input type="hidden" name="querymap.specidchk" value="${querymap.specidchk}"/>
                    <input type="hidden" name="querymap.reqtype" value="${querymap.reqtype}"/>
                    <input type="hidden" name="listtemppage" value="${listtemppage}" />
					<span id="formerror"></span>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>