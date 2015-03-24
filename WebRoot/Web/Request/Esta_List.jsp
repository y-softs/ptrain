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
			<c:if test="${not empty dataList}">
			//附件加载
			$.each($(".list-tab tbody tr"),function(){
				var obj=$("td",$(this)).eq(1),recid=obj.attr("recid");
				if(''!=recid){
					var url="<c:url value='/Ptrain/File/listPtrainFileByJq.shtml'/>";
					var res=jqueryOper(url,{'modsign':'${modsign}','recid':recid},obj);
				}
			});
			</c:if>
		});
		//提交查询数据列表
		function querydata(){
			document.form2.action="<c:url value='/pt/requser/listptrainrequser.shtml'/>";
			document.form2.submit();
		}
		//报名
		function saveData(reqid){
			var index = parent.layer.confirm('确定报名？',function(i){
				parent.layer.close(index);
				$("#reqid").val(reqid);
				document.form2.action = "<c:url value='/pt/requser/saveptrainrequser.shtml'/>";
			    document.form2.submit();
		    });
		}
		//详细页面
		function setDataWin(id,requserid){
			var param="?ptrainReqBean.id="+id;
				param+="&querymap.requserid="+requserid;
			$.layer({
					type: 2,
					title: '报名人数',
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
		//取消
		function canOper(id){
			var index = parent.layer.confirm('确定取消？',function(i){
				parent.layer.close(index);
				var newid = id;
		        if(''==id){
					var idAttr=new Array();
					$.each($('input[name="chk"]'),function(i, n){
						if(n.checked)idAttr.push(n.value);
					});
					newid = idAttr.join(",");
		        }
		        $("#id").val(newid);
				document.form2.action='<c:url value="/pt/requser/deleteptrainrequser.shtml"/>';
				document.form2.submit();
		    });
		}
		
		//jquery接口
		function jqueryOper(urlV,dataV,obj){
			var res='',filename='';
			$.ajax({
				type:"POST",
				url:urlV,
				data:dataV,
				//async:false,
				dataType:"json",
				success:function(jsonV){
					res=jsonV;
					if(''!=res){
						for(var i in res){
							$(".list-con").height($(".list-con").height()+30)
							if(res[i].filename.length>10){
								filename = res[i].filename.substr(0,10)+'...';
							}else{
								filename = res[i].filename;
							}
							obj.append('<br/>课件'+(parseInt(i)+1)+'：<a href="JavaScript:downLoad(\''+res[i].savename+'\',\''+res[i].savepath+'\');" title="'+res[i].filename+'">'+filename+'</a>');
							$(".main").height($(".list-con").height()+150);
						}
					}
				},
	            error: function (msg) {
	                res=msg;
	                obj.append("<br/><font color='red'>附件加载失败！</font>");
	            }
			});
			return res;
		}
	    //下载文件 
		function downLoad(savename,savepath){
		   downframe.document.form1.inputPath.value = savepath;
		   downframe.document.form1.fileName.value = savename;
		   downframe.document.form1.submit();
		}
	</script>
<body>
<form name="form2" id="form2" action="listptrainrequser.shtml" method="post">
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
                    <li><a href="<c:url value='/pt/requser/listptrainrequser.shtml'/>"><label class="selected"></label>点菜</a></li>
                    <li><a href="<c:url value='/pt/requser/listptrainreq.shtml'/>"><label></label>员工加菜</a></li>
                </ul>
            </div>
            <div class="list">           	         	
            	<div class="shop">
                		<div style=" float:left; margin-right:5px;">专业类别：<select name="querymap.specid" id="specid">
                            		<option value="">全部</option>
                            		<c:forEach items="${specList}" var="s">
                            			<option value="${s.id}" ${s.id==querymap.specid?'selected':''}>${s.codename}</option>
                            		</c:forEach>
                                </select>
                                &nbsp;<input type="checkbox" name="querymap.showsign" value="1" ${'1'==querymap.showsign?'checked':''} onclick="querydata();" />仅显示待办班
                        </div>
                        <div class="search">
	                        <div class="search_keyword">关键字：</div>
                             <div class="search_text">
	                          <input type="text" name="keyword" class="keyword" value="${keyword }" /><a href="###" class="search-a" onclick="querydata();">&nbsp;</a>
			                  <input type="hidden" name="tagpage" 		value="1" />
			                  <input type="hidden" name="fields" 		value="${r.itemname }"/>
                              <input type="hidden" name="tokenid" 		value="${tokenid}" />
			                  <input type="hidden" name="record" 		value="${record}" />
			                  <input type="hidden" name="querymap.unitid" 		value="${querymap.unitid}" />
                              <input type="hidden" name="ptrainRequserBean.reqid" id="reqid" />
                              <input type="hidden" name="ptrainRequserBean.id" id="id" />
                             </div>
                        </div>
                </div>
                <div class="list-con">                  
                	<table class="list-tab" style="border-left:none; border-right:none;">
                    	<thead>
                        <tr>
                        	<th width="30%">项目名称</th>
                        	<th>课程介绍</th>
                        	<th width="10%">培训类别</th>
                        	<th width="10%">办班时间</th>
                        	<th width="8%">报名人数</th>
                        	<th width="8%">状态</th>
                        	<th width="8%">操作</th>
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
                            <td align="center">
                           	<c:forEach items="${reqtypeList}" var="r">
				               <c:if test="${r.key==d.reqtype}">${r.desc}</c:if>
				            </c:forEach>
                            </td>
                            <td align="center"><c:if test="${'1'==d.state}">${d.maintime}</c:if></td>
                            <td align="center"><a href="JavaScript:setUserWin('${d.id}');">${d.intflag}</a></td>
                            <td align="center">${'0'==d.state?'待办班':'已办班'}</td>
                            <td align="center">
                            <c:choose>
					            <c:when test="${'0'==d.state}">
						            <c:choose>
						            <c:when test="${empty d.strflag}">
						            	<a href="JavaScript:saveData('${d.id}');" class="btn btn-primary">报名</a>
						            </c:when>
						            <c:otherwise>
						            	<a href="JavaScript:canOper('${d.strflag}');" class="btn btn-inverse">取消</a>
						            </c:otherwise>
						            </c:choose>
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
               		<ul class="page_record"><nsoft:pages record="%{record}" tagpage="%{tagpage}" total="%{total}" formname="%{'form2'}" url="%{'listptrainrequser.shtml'}"/></ul>
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