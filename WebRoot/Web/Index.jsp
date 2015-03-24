<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Home.css'/>"/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
    <script type="text/javascript">
    	$(function(){
    		$(".mrsw,.jslt,.pxdc,.gcwj,.yyysk,.jdsjk,.kjk,.dxjyk").bind("click",isLogin);
    		document.onkeydown = function(e) {
				e = e ? e : window.event;
			    var keyCode = e.which ? e.which : e.keyCode;
			   if(keyCode==13){
			      Sign_in();
			   }
			}
    	});
    </script>
<body>
<!--内容导航-->
<div id="main">
	<div class="head">
    	<div class="logo"></div>
        <div class="logo-title"></div>
    </div>
    <div id="navmain">
    <a href="<c:url value='/pt/ask/listptrainask.shtml'/>" class="mrsw">&nbsp;</a>
    <a href="<c:url value='/pt/bbs/listtrainbbs.shtml'/>" class="jslt">&nbsp;</a>
    <a href="<c:url value='/pt/requser/listptrainrequser.shtml'/>" class="pxdc">&nbsp;</a>
    <a href="<c:url value='/pt/con/listcontentrules.shtml'/>" class="gcwj">&nbsp;</a>
    <a href="<c:url value='/pt/con/listcontentmovies.shtml'/>" class="yyysk">&nbsp;</a>
    <a href="<c:url value='/pt/con/listcontentbooks.shtml'/>" class="jdsjk">&nbsp;</a>
    <a href="<c:url value='/pt/cour/listcours.shtml'/>" class="kjk">&nbsp;</a>
    <a href="<c:url value='/pt/con/listcontentexper.shtml'/>" class="dxjyk">&nbsp;</a>
    </div>
</div>
<!-- 登录 -->
<div id="login-bg">
	<div class="login">
		<div id="user-login" style="display:${empty sessionScope.loginSession?'':'none'}" >
	    	<label>用户登录</label>
	        <div class="user">
	        	<ul>
	            	<li class="info">用户名：</li>
	            	<li><input type="text"name="loginbean.userid" id="userid" value=""  class="input"/></li>
	            	<li class="info">密&nbsp;&nbsp;码：</li>
	            	<li><input type="password" name="loginbean.password" id="password" value="" class="input" /></li>
	            </ul>
	        </div>
	        <div class="btn-bg">
	        	<input type="button" class="sub" id="chkLog" value="" />
	        </div>
        </div>
        <div style="display:${empty sessionScope.loginSession?'none':''}"class="user-info">
        	<span id="info">${sessionScope.loginSession.deptname }&nbsp;&nbsp;${sessionScope.loginSession.username }</span>&nbsp;&nbsp;<span id="admin" style="display:${empty sessionScope.loginSession.roleids?'none':''};"><a href="savelogininweb.shtml">【管理】</a>&nbsp;&nbsp;</span><a href="###" id="upass">【修改密码】</a>&nbsp;&nbsp;<a href="###" id="loginout">【注销】</a>
        </div>
    </div>
</div>
<!-- 页脚 -->
<div id="footer">
	<span>
	CopyRight&nbsp;@&nbsp;by 国网福建省电力有限公司莆田供电公司  软件开发：龙呈软件
    </span>
</div>
</body>
</html>