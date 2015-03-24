<%@ page language="java"  pageEncoding="UTF-8"%> 
<html>
<head> 
	<%@ include file="../../Include/TagLib.jsp"%>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="../../Style/main.css"/>
	<link rel="stylesheet" href="../../Style/bootstrap.css"/>
	<link rel="stylesheet" href="../../Style/valid.css"/>
	<script type="text/javascript" src="../../Script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../../Script/Main.js"></script>
	<script type="text/javascript" src="../../Script/SelectUsers.js"></script>
	<script type="text/javascript">
		$(function($){
			var commSelectUsers = new CommSelectUsers();
			$("#selview2").hide();
			//覆盖默认选择值
			commSelectUsers.setDefaultVals({
				d_deptid : '${sessionScope.loginSession.deptid}'
			});
			//commSelectUsers.setFormControlNames({
			//	f_selview_help:'selview2'
			//});
			commSelectUsers.setQueryUserUrl({
				action_query_userurl:'<c:url value='/Ptrain/Postuser/listUnitUsersByJq.shtml' />?postid=${querymap.postid}'
			});
			commSelectUsers.initBody(); 
			//重载回车事件
			commSelectUsers.reloadKeyDown();
			//重载提交方法
			commSelectUsers.reloadCheckForm = checkForm;
		}); 
		
		//提交验证
		function checkForm(){
			var str="",strHtm="";
			$("#selview option").each(function(index,dom){
				str=(str==""?$(this).val():str+","+$(this).val()); 
			});
			if(str==""||str==",") {
				parent.$.jBox.tip("人员选择不能为空！", 'info');
			} else {
				$("#userid").val(str);
				document.form1.submit();
				return true;
			}
		} 
	</script>
</head>
<body>
<div class="editContainer">
	<form name="form1" action="<c:url value='/Ptrain/Postuser/saveptrainpostuser.shtml' />" method="post" id="form1">
        <table class="editTable">
			<!--表格内容部分-->
			<tbody>
			<tr>
			<!--左侧表格项-->
			    <td nowrap class="left">部门班组：</td>
			    <!--右侧表格项-->
			    <td class="right">
			    	<!-- 部门名称 -->
			    	<select name="loginBean.deptid" id="deptid"></select>
			    	<!-- 班组名称 -->
			    	<select name="loginBean.groupid" id="groupid"></select>
			        <!--子班组名称 -->
			        <select name="loginBean.groupid2" id="subgroupid"></select>
			    </td>
			</tr> 
            <tr>
				<td nowrap class="left">关键字：</td>
                <td class="right">
                	<select name="fields" id="fields">
                		<option value="username">姓名</option>
                		<option value="userid">身份证号</option>
                		<option value="workid">工号</option>
                		<option value="postname">岗位名称</option>
                	</select>  
                	<input type="text" name="keyword" id="keyword" class="text_10"/>
            		<input name="btn_query" id="btn_query" type="button" value="查询">
                </td>
            </tr>
            <tr>
            	<td nowrap class="left">(*)&nbsp;人员选择：</td>
                <td class="right">
                	<table>
	                    <tr>
	                        <td align="left" nowrap>
	                            <select name="preview" id="preview" size="20" multiple="MULTIPLE" class="select_40_24" onDblClick="JavaScript:setSelectValue3(11);"></select>
	                        </td>
	                        <td align="center" nowrap>
	                            <input name="add" type="button" value=" > " title="选择" onClick="JavaScript:setSelectValue3(11);"><br>
	                            <input name="addall" type="button" value=">>>" title="全部选择" onClick="JavaScript:setSelectValue3(19);"><br>
	                            <input name="sub" type="button" value=" < " title="移除" onClick="JavaScript:setSelectValue3(91);"><br>
	                            <input name="suball" type="button" value="<<<" title="全部移除" onClick="JavaScript:setSelectValue3(99);">
	                        </td>
	                        <td>
	                        	<select name="selview" id="selview" size="20" multiple="multiple" class="select_40_24" onDblClick="JavaScript:setSelectValue3(91);">
	                        	</select>
	                        </td>
	                    </tr>
					</table>
				</td>
			</tr>
			</tbody>
			<!--表格操作部分-->
			<tfoot>
			<!--表格行-->
			<tr class="btnlist" style="display:none;">
				<td colspan="2">
				<input type="hidden" name="loginBean.unitid" id="unitid" value="${querymap.unitid}"/>
<%--            <input type="hidden" name="loginBean.unitid" id="unitid" value="${session.loginSession.unitid}"/>  --%>
				<input type="hidden" name="tokenid" value="${tokenid}"/>
				<input name="ptrainPostuserBean.unitid" type="hidden" value="${querymap.unitid}" />
				<input name="ptrainPostuserBean.postid" type="hidden" value="${querymap.postid}" />
				<input name="ptrainPostuserBean.userid" type="hidden" id="userid" />
				
				<input name="querymap.unitid" type="hidden" value="${querymap.unitid}" />
				<input name="querymap.postid" type="hidden" value="${querymap.postid}" />			
				</td>
			</tr>
			</tfoot>
			</table>
		</form>
</div>
</body>
</html>