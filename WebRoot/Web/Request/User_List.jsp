<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="nsoft" uri="/WEB-INF/nsoft.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Main.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
	<script language="javascript">
		$(function(){
			$("#specid").bind("change",querydata);
		});
		//新增/修改 [跳转]
		function setData(id,fun){
			var param = "?ptrainReqBean.id="+id;
				param +="&querymap.specid=${querymap.specid}";
			$.layer({
				type: 2,
				title: fun=='1'?'新增':'修改',
				maxmin: false,
				shadeClose: false, //开启点击遮罩关闭层
				offset: ['150px',''],
				area : ['750px' , '400px'],
				iframe: {src: "<c:url value='/pt/requser/setptrainreq.shtml'/>"+param}
			});
		}
		//提交查询数据列表
		function querydata(){
			document.form2.action="<c:url value='/pt/requser/listptrainreq.shtml'/>";
			document.form2.submit();
		}
		//详细页面
		function setDataWin(id,requserid){
			var param="?ptrainReqBean.id="+id;
				param+="&querymap.requserid="+requserid;
			$.layer({
					type: 2,
					title: '详细',
					maxmin: false,
					shadeClose: false, //开启点击遮罩关闭层
					offset: ['30px',''],
					area : ['700px' , '500px'],
					iframe: {src: "<c:url value='/pt/requser/setptrainreqwin.shtml'/>"+param}
			});
		}
		//已报名人员 详细页面
		function setUserWin(reqid){		
			$.layer({
					type: 2,
					title: '报名人数',
					maxmin: false,
					shadeClose: false, //开启点击遮罩关闭层
					offset: ['30px',''],
					area : ['830px' , '560px'],
					iframe: {src: "<c:url value='/pt/requser/listptrainrequsersign.shtml'/>?querymap.unitid=${querymap.unitid}&querymap.reqid="+reqid}
			});
		}
		//流程详细
	    function flowDeta(id){
	    	$.layer({
					type: 2,
					title: '流程状态',
					maxmin: false,
					shadeClose: false, //开启点击遮罩关闭层
					offset: ['30px',''],
					area : ['700px' , '400px'],
					iframe: {src: "<c:url value='/Ptrain/Flow/toforwardptrainflow.shtml'/>?ptrainFlowBean.modsign=${flowModsign}&ptrainFlowBean.recid="+id}
			});			
	    }
		//删除
	    function delData(id){
	    	var index = parent.layer.confirm('确定删除记录？',function(i){
				parent.layer.close(index);
			    $("#id").val(id);
				$("#form2").attr("action","<c:url value='/pt/requser/deleteptrainreq.shtml'/>").trigger("submit");
		    });	 
	    }
		//申报/撤回
		function chkData(id,sign){
		    var newid = id;
			if('1'==sign){//申报
				var index = parent.layer.confirm('确定申报记录？',function(i){
					parent.layer.close(index);
					$("#chksign").val('${SHEN_BAO}');
				    $("#id").val(id);
					$("#form2").attr("action","<c:url value='/pt/requser/updateptrainreqchk.shtml'/>").trigger("submit");
			    });	        	
			}else if('2'==sign){//撤回
				var index = parent.layer.confirm('确定撤回申报记录？',function(i){
					parent.layer.close(index);
					$("#chksign").val('${CHE_HUI}');
				    $("#id").val(id);
					$("#form2").attr("action","<c:url value='/pt/requser/updateptrainreqchk.shtml'/>").trigger("submit");
			    });	        
			}
		}
	    //下载文件 
		function downLoad(savename,savepath){
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
	</script>
<body>
<form name="form2" id="form2" action="listptrainreq.shtml" method="post">
<div id="container">
	<!--头部-->
	<div class="header">
    	<div class="logo"><img src="<c:url value='/Web/Image/logo.png'/>" /></div>
        <div class="logo-title"><img src="<c:url value='/Web/Image/log-title.png'/>" /></div>
    </div>
	<!--main内容-->
    <div class="main">
    	<%@ include file="../NavMenu.jsp" %>
        <div class="content">
        	<div class="leftsidebar">
                <span><img src="<c:url value='/Web/Image/menu-pxdc.png'/>" /></span>
                <ul class="ulsidebar">
                    <li><a href="<c:url value='/pt/requser/listptrainrequser.shtml'/>"><label></label>点菜</a></li>
                    <li><a href="<c:url value='/pt/requser/listptrainreq.shtml'/>"><label class="selected"></label>员工加菜</a></li>
                </ul>
            </div>
            <div class="list">           	         	
            	<div class="shop">
                		<div style=" float:left; margin-right:5px;"><a  href="JavaScript:setData('','1');" class="btn btn-info">新增</a>专业类别：<select name="querymap.specid" id="specid">
                            		<option value="">全部</option>
                            		<c:forEach items="${specList}" var="s">
                            			<option value="${s.id}" ${s.id==querymap.specid?'selected':''}>${s.codename}</option>
                            		</c:forEach>
                                </select>
                        </div>
                        <div class="search">
	                        <div class="search_keyword">关键字：</div>
                             <div class="search_text">
	                          <input type="text" name="keyword" class="keyword" value="${keyword }" /><a href="###" class="search-a" onclick="querydata();">&nbsp;</a>
			                  <input type="hidden" name="tagpage" 		value="1" />
			                  <input type="hidden" name="fields" 		value="${r.itemname }"/>
                              <input type="hidden" name="tokenid" 		value="${tokenid}" />
			                  <input type="hidden" name="record" 		value="${record}" />
                    		  <input type="hidden" name="ptrainReqBean.id" id="id"/>
			                  <input type="hidden" name="querymap.unitid" 		value="${querymap.unitid}" />
                    		  <input type="hidden" name="querymap.chksign" id="chksign"/>
                             </div>
                        </div>
                </div>
                <div class="list-con">                  
                	<table class="list-tab" style="border-left:none; border-right:none;">
                    	<thead>
                        <tr>
                        	<th width="30%">项目名称</th>
                        	<th>课程介绍</th>
				            <th width="6%" nowrap>报名人数</th>
				            <th width="8%" nowrap>*流程状态</th>
                        	<th width="20%" style="position:relative">操作<a href="JavaScript:setData('','1');" class="add" title="新增">&nbsp;</a></th>
                        </tr>                        
                        </thead>
                        <tbody>                        
        				<c:forEach items="${dataList}" var="d" varStatus="st">
                        <tr>
                        	<td align="left" style="padding-left:5px;">
                        	<a href="Javascript:setDataWin('${d.id}','${d.strflag}');" class="link" title="${d.itemname }">
                        	<c:choose>
                           		<c:when test="${fn:length(d.itemname)>15 }">
                           			${fn:substring(d.itemname,0,15) }...
                           		</c:when>
                           		<c:otherwise>${d.itemname}</c:otherwise>
                           	</c:choose>
                        	</a>
                        	</td>
                            <td align="left" recid='<c:if test="${not empty d.filenum}">${d.id}</c:if>'>
                            <c:choose>
                           		<c:when test="${fn:length(d.itemdesc)>13 }">
                           			${fn:substring(d.itemdesc,0,13) }...
                           		</c:when>
                           		<c:otherwise>${d.itemdesc}</c:otherwise>
                           	</c:choose>
                            </td>
                            <td align="center"><a href="JavaScript:setUserWin('${d.id}');">${d.intflag}</a></td>
                            <td align="center">
                            <c:forEach items="${stateList}" var="state">
				               <c:if test="${state.key==d.flowmark}">
				                  <a href="JavaScript:flowDeta('${d.id}');">${state.desc}</a>
				               </c:if>
				            </c:forEach>
                            </td>
                            <td align="center">
                            <c:choose>
					            <c:when test="${XQSB==d.flowsta}">
					            	<a href="JavaScript:chkData('${d.id}','1');" class="btn btn-warning">申报</a><a href="JavaScript:setData('${d.id}','2');" class="btn btn-primary">修改</a><a href="###" onClick="javascript:delData('${d.id}');" class="btn btn-danger">删除</a>
					            </c:when>
					            <c:when test="${XQSH==d.flowsta}">
					            	<a href="JavaScript:chkData('${d.id}','2');" class="btn btn-inverse">撤回</a>
					            </c:when>
					            <c:otherwise>&nbsp;</c:otherwise>
				            </c:choose>
                            </td>
                        </tr>
                        </c:forEach>
                       	<c:if test="${empty dataList}">
				        <tr align="center">
				        	<td style="color:red;" colspan="12">暂无数据</td>
				        </tr>
				        </c:if> 
                        </tbody>
                    </table>
               </div>               
               <div class="page_bar">
               		<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listptrainreq.shtml'}"/></ul>
               </div>
            </div>  
        </div>              
    </div>
	<%@ include file="../Footer.jsp" %>
</div>
</form>
</body>
</html>
<iframe id="downframe" src="<c:url value='/Include/NoData.jsp'/>" style="display:none"/>