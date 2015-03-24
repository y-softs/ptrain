<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../Include/TagLib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="<c:url value='/Style/main.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/Style/bootstrap.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/Style/valid.css'/>"/>
	<script type="text/javascript" src="<c:url value='/Script/jquery-1.9.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/Script/plugin/valid/FormValidator.1.0.min.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			bindValid();
			$("#submitBtn").bind("click",callBack);
			$("#closeBtn").bind("click",function(){
				parent.$.jBox.close();
			});
		});
		
		function callBack(){
			$("#form1").attr("action",'<c:url value='/Ptrain/Votesrc/saveptrainvotesrc.shtml'/>').trigger("submit");
			if($("#formerror").text() == "ok"){
				return true;
			}
			return false;
		}
		
		function bindValid(){
			//批次
			$("#b_appid").attr("fv-empty","false").attr("fv-empty-msg","批次不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_appid");
			$("#b_appid").parent().append('<span id="valid_appid" class="validate-info"></span>');
			
			//资源封面
			$("#b_srcimg").attr("fv-empty","false").attr("fv-empty-msg","资源封面不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_srcimg");
			$("#b_srcimg").parent().append('<span id="valid_srcimg" class="validate-info"></span>');
			
			//投票对象标题
			$("#b_srctitle").attr("fv-empty","false").attr("fv-empty-msg","投票对象标题不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_srctitle");
			$("#b_srctitle").parent().append('<span id="valid_codevalue" class="validate-info"></span>');
			
			//对象url
			$("#b_srcurl").attr("fv-empty","false").attr("fv-empty-msg","对象url不能为空")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_srcurl");
			$("#b_srcurl").parent().append('<span id="valid_srcurl" class="validate-info"></span>');
			
			//绑定排序号
			$("#b_sortnum").attr("fv-empty","false").attr("fv-empty-msg","排序号不能为空")
							.attr("fv-datatype","Int16").attr("fv-datatype-msg","排序号只能为数字")
							.attr("fv-msg-success","").attr("fv-msgpanel","valid_sortnum");
			$("#b_sortnum").parent().append('<span id="valid_sortnum" class="validate-info"></span>');
			
			//绑定form表单自定义验证
			$("#form1").attr("fv-validate","true").attr("fv-msg-success","ok").attr("fv-msgpanel","formerror");
		}
	</script>
</head>
<body>
<div class="editContainer">
	<form name="form1" method="post" id="form1">
        <table class="editTable">
            <tbody>
	            <tr>
	                <!--左侧表格项-->
	                <td class="left">批次：</td>
	                <!--右侧表格项-->
	                <td class="right">
	                	<select name="ptrainVotesrcBean.appid" id="b_appid">
	                		<option value="">请选择</option>
	                		<c:forEach items="${appList}" var="d">
	                			<option value="${d.id}" ${d.id==ptrainVotesrcBean.appid?'selected':''}>${d.appname}</option>
	                		</c:forEach>
	                	</select>
	                </td>
	            </tr>
				<tr>
					<!--左侧表格项-->
					<td nowrap class="left">资源封面：</td>
					<!--右侧表格项-->
					<td class="right">
					    <input type="text" name="ptrainVotesrcBean.srcimg" id="b_srcimg" class="text_50" value="${ptrainVotesrcBean.srcimg}" />
					</td>
				</tr>
				<tr>
					<!--左侧表格项-->
					<td nowrap class="left">投票对象标题：</td>
					<!--右侧表格项-->
					<td class="right">
					    <input type="text" name="ptrainVotesrcBean.srctitle" id="b_srctitle" class="text_50" value="${ptrainVotesrcBean.srctitle}" />
					</td>
				</tr>
				<tr>
					<!--左侧表格项-->
					<td nowrap class="left">对象类别：</td>
					<!--右侧表格项-->
					<td class="right">
					    <input type="radio" name="ptrainVotesrcBean.srcsign" value="0" ${ptrainVotesrcBean.srcsign=='0'?'checked':''} />PPT
					    <input type="radio" name="ptrainVotesrcBean.srcsign" value="1" ${ptrainVotesrcBean.srcsign=='1'?'checked':''} />视频
					</td>
				</tr>
				<tr>
					<!--左侧表格项-->
					<td nowrap class="left">对象url：</td>
					<!--右侧表格项-->
					<td class="right">
					    <input type="text" name="ptrainVotesrcBean.srcurl" id="b_srcurl" class="text_50" value="${ptrainVotesrcBean.srcurl}" />
					</td>
				</tr>
				<tr>
					<!--左侧表格项-->
					<td nowrap class="left">作者：</td>
					<!--右侧表格项-->
					<td class="right">
					    <input type="text" name="ptrainVotesrcBean.author" id="b_author" class="text_50" value="${ptrainVotesrcBean.author}" />
					</td>
				</tr>
				<tr>
					<!--左侧表格项-->
					<td nowrap class="left">工作单位：</td>
					<!--右侧表格项-->
					<td class="right">
					    <input type="text" name="ptrainVotesrcBean.unitname" id="b_unitname" class="text_50" value="${ptrainVotesrcBean.unitname}" />
					</td>
				</tr>
				<tr>
					<td class="left">作品简介：</td>
					<td class="right">
						<textarea name="ptrainVotesrcBean.remark" class="textarea_50_5">${ptrainVotesrcBean.remark}</textarea>
					</td>
				</tr>
	            <tr>
	                <td class="left" nowrap>排序号：</td>
	                <td class="right">
	                    <input type="numberbox" value="${ptrainVotesrcBean.sortnum}" id="b_sortnum"  class="text_10" name="ptrainVotesrcBean.sortnum"/>
	                </td>
	            </tr>
	            <c:if test="${empty ptrainVotesrcBean.id}">
	            <tr>
	                <td class="left">&nbsp;继续新增：</td>
	                <td class="right"><input type="checkbox" name="gosign" id="gosign" value="1" ${'1'==gosign?'checked':''}/></td>
	            </tr>
	            </c:if>
	            <!-- 以下为隐藏域 -->
	            <tr>
	                <td colspan="2">
	                	<input type="button" id="submitBtn" class="btn btn-info" value="确定"/>
	                	<input type="button" id="closeBtn" class="btn btn-info" value="关闭"/>
					    <input type="hidden" name="tokenid" value="${tokenid}" />
		                <input type="hidden" name="querymap.appid" value="${querymap.appid}" />
		                <input type="hidden" name="ptrainVotesrcBean.id" value="${ptrainVotesrcBean.id}" />
	                </td>
	            </tr>
	            <tr style="display:none;">
	                <td colspan="2">
						<span id="formerror"></span>
	                </td>
	            </tr>
		    </tbody>
        </table>
   </form>
</div>
</body>
</html>