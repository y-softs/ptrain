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
			// var useridstr=$("#useridstr",window.opener.document.body).val();
			var useridstr='${useridstr}';
			var url = "<c:url value='/Ptrain/Manager/findDataUserListHasByJq.shtml'/>";
			var dataMap = {'querymap.unitid':'${ptrainManagerBean.unitid}','querymap.userid':useridstr};
			if(''!=useridstr){
				var res=operAjax(url,dataMap);
				if("-1"==res){alert("操作失败！");return;}
				else if(''!=res){
					//添加已有人员
					for(var i in res){
						$("#selview").append("<option value='"+res[i].id+"' >"+res[i].username+"</option>");
					}
				}
			}
		
			var commSelectUsers = new CommSelectUsers(); 
			//覆盖默认选择值
			//commSelectUsers.setDefaultVals({
			//	d_nature : '${sessionScope.loginSession.nature}',
			//	d_deptid : '${sessionScope.loginSession.deptid}',
			//	d_nature_addall:false,
			//	d_dept_addall:false
			//});
			commSelectUsers.setQueryUserUrl({
	<%--			action_query_userurl:'<c:url value="/Wage/Purview/findUserListByJq.shtml"/>'--%>
			});
			commSelectUsers.initBody(); 
			//重载回车事件
			commSelectUsers.reloadKeyDown();
			//重载提交方法
			commSelectUsers.reloadCheckForm = checkForm;
			
			if($("#deptid option").size()==0)$("#deptid").hide();
		}); 
		
		//提交验证
		function checkForm(){
			var str="",text="",subText="";
			$("#selview option").each(function(index,dom){
				str=(str==""?$(this).val():str+","+$(this).val());
				if(($(this).text()).indexOf('（')>0){
					subText = ($(this).text()).substring(0,($(this).text()).indexOf('（'));
				}else{
					subText = $(this).text();
				}
				text=(text==""?subText:text+"；"+subText);
			}); 
			if(str==""||str==","){
				alert("人员选择不能为空！");
				return;
			}else{
				$("#managers").val(str);
				document.form1.action="<c:url value='/Ptrain/Manager/saveptrainmanager.shtml'/>";
				document.form1.submit();
				return true;
			}
		}
		//数据保存
		function savaData(){
			document.form1.action="<c:url value='/Ptrain/Manager/saveptrainmanager.shtml'/>";
			document.form1.submit();
		}
		// 数据操作接口
		function operAjax(url,dataMap){
			var res='';
			$.ajax({
					type: "POST", 
					url:url,
					async:false,
					dataType:"json",
					data:dataMap,
					success:function(json){
						res=json;
					}
			});
			return res;
		}
	</script>
</head>
<body>
<div class="editContainer">
	<form name="form1" action="" method="post" id="form1">
        <table class="editTable">
			<!--表格内容部分-->
			<tbody>
			<tr>
				<!--左侧表格项-->
				<td nowrap class="left">部门班组：</td>
				<!--右侧表格项-->
				<td colspan="3" class="right">
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
                    <td colspan="3" class="right">
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
                    <td nowrap class="left">*&nbsp;人员选择：</td>
                    <td class="right">
                        <select name="preview" id="preview" multiple="MULTIPLE" class="select_40_24" onDblClick="JavaScript:setSelectValue3(11);"></select>
                    </td>
                    <td class="right">
                        <input name="add" type="button" value=" > " title="选择" onClick="JavaScript:setSelectValue3(11);">
                        <input name="addall" type="button" value=">>>" title="全选" onClick="JavaScript:setSelectValue3(19);">
                        <input name="sub" type="button" value=" < " title="移除" onClick="JavaScript:setSelectValue3(91);">
                        <input name="suball" type="button" value="<<<" title="全移" onClick="JavaScript:setSelectValue3(99);">
                    </td>
                    <td class="right">
                    	<select name="selview" id="selview" multiple="multiple" class="select_40_24" onDblClick="JavaScript:setSelectValue3(91);">
                    	</select>
                    </td>
                </tr>
			</tbody>
			<!--表格操作部分-->
			<tfoot>
			<!--表格行-->
			<tr class="btnlist" style="display:none;">
				<td colspan="2">
			<%--	<input type="hidden" name="loginBean.unitid" id="unitid" value="${sessionScope.loginSession.unitid}"/> --%>
	                <input type="hidden" name="loginBean.unitid" id="unitid" value="${ptrainManagerBean.unitid}"/>
	                <input type="hidden" name="tokenid" value="${tokenid}"/> 
					
					<input type="hidden" name="ptrainManagerBean.id" value="${ptrainManagerBean.id}"/>
	                <input type="hidden" name="ptrainManagerBean.unitid" value="${ptrainManagerBean.unitid}"/>
	                <input type="hidden" name="ptrainManagerBean.kpid" value="${ptrainManagerBean.kpid}"/>
	                <input type="hidden" name="ptrainManagerBean.managers" id="managers"/>			
				</td>
			</tr>
			</tfoot>
			</table>
		</form>
</div>
</body>
</html>