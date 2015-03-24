<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
    <script type="text/JavaScript">	
		$(function(){
			$("#chkSub").bind("click",function(){
			var username = $.trim($("#userid").val());
			var password = $.trim($("#password").val());
			if(username=="" || password==""){
				parent.layer.msg('<div style="color:red;font-size:13px;">用户名或密码不能为空！</div>' ,2,3); 
				return false;
			}else{
				var loading = parent.layer.load('加载中');
				setTimeout(function(){
					$.ajax({
						url:'loginInByJq.shtml',   //接收页面
						type: 'post',      				//POST方式发送数据
						async: false,      				//ajax异步
						data:{'loginbean.userid':username,'loginbean.password':password,'timestmp':(new Date()).getTime()},
						dataType:"json",
						success:function(data) {
							if(data=="-1"){
								parent.layer.close(loading);
								parent.layer.msg('<div style="color:red;font-size:13px;">操作失败，与数据库交互是发生错误！</div>' ,2,3); 
							}else if(data=="0"){
								parent.layer.close(loading);
								parent.layer.msg('<div style="color:red;font-size:13px;">您输入的用户名或密码错误！</div>' ,2,3); 
							}else{
								parent.$("#user-login").hide();
								for(var i in data){	
									parent.$(".user-info").show();
									parent.$("#info").html(data[i].deptname+'&nbsp;&nbsp;'+data[i].username);
									if(data[i].roleids!=""){
										parent.$("#admin").show();
									}
								}
								parent.layer.msg('<div style="font-size:13px;">登录成功！</div>' ,2,1);
								setTimeout(function(){
									parent.layer.closeAll();
							    }, 2000);
							}						
						}
					});
				},500);
			}
		});
		});
	</script>
	<style type="text/css">
	body,ul,li{ margin:0; padding:0;}
	ul,li{margin-top: 20px;}
	li{ list-style:none; clear:both; line-height:30px; margin-bottom:10px;width:400px;}
	label{ line-height:30px; height:30px; float:left; text-align:right; width:100px; font-size:14px;
	font-family:"黑体";}
	input{ line-height:30px; height:30px; border:1px solid #ccc; width:180px;}
	.log-button{clear:both;position:relative;height:45px;width: 300px;}
	.log-button a.checkout {
		position:absolute;top:0px;right:0px;
		width: 200px;
		height: 36px;
		display: block;
		overflow: hidden;
		line-height: 100px;
		background: url(<c:url value='/Web/Image/'/>but-01.jpg) no-repeat #4ab5a1;
		font-family: "\5FAE\8F6F\96C5\9ED1";
		font-size: 18px;
		font-weight: bold;
		line-height: 32px;
		_line-height: 36px;text-indent:80px;
		color:#fff;text-decoration:none;
	}
	.log-button a.checkout:hover{background-position:0 -36px;box-shadow:0 1px 1px rgba(0,1,1,0.3);background-color:#ED4749;text-decoration: none;}
	</style>
</head>
<body>
<ul>
	<li><label>用户名：</label><input type="text" name="loginbean.userid" id="userid" value="" maxlength="18"/></li>
	<li><label>密码：</label><input type="password" name="loginbean.password" id="password"  maxlength="18"/></li>
	<li class="log-button"><a href="###" class="checkout" id="chkSub">登录</a></li>	
</ul>
</body>
</html>
