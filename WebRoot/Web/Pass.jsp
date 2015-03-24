<%@ page language="java"  pageEncoding="utf-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			$("#btn_sure").bind("click",function(){
				var pass = $.trim($("#pass").val());
				var pass2 = $.trim($("#pass2").val());
				if(pass==""){
					parent.layer.msg('<div style="color:red;font-size:13px;">新密码不能为空！</div>' ,2,3); 
					return false;
				}
				if(pass2==""){
					parent.layer.msg('<div style="color:red;font-size:13px;">确认新密码不能为空！</div>' ,2,3); 
					return false;
				}
				if(pass!=pass2){
					parent.layer.msg('<div style="color:red;font-size:13px;">新密码输入不一致！</div>' ,2,3); 
					return false;
				}
				if(isLetterAndNum(pass)){
					$.ajax({
				            type: 'post',      //POST方式发送数据
				            url: 'updatePasswordByJq.shtml',   //接收页面
				            async: false,      //ajax同步
				            data: {'password':pass,'timestmp':(new Date()).getTime()},
				            success: function(msg) { 
				              	if(msg=='-1'){				              		
									parent.layer.msg('<div style="color:red;font-size:13px;">操作失败，与数据库交互是发生错误！</div>' ,2,3); 
				              	}else{
				              		parent.layer.msg('<div style="font-size:13px;">修改成功！</div>' ,2,1);
				              		setTimeout(function(){							
										parent.layer.closeAll();
								    }, 2000);
				              	}
				            }
				        });
				}
			});	
		});
		function isLetterAndNum(pasValue){	 
			if(pasValue.length<8){
				parent.layer.msg('<div style="color:red;font-size:13px;">密码位数不能小于8位！</div>' ,2,3);
				return false;
			}
			//var reg = /^(([A-Za-z]+[0-9]+)|([0-9]+[A-Za-z]+))[A-Za-z0-9]*$/; 
			var reg = /^[a-zA-Z][a-zA-Z0-9_]{7,17}$/;
			//if(!reg.exec(pasValue)) 
			if(!reg.test(pasValue)) 
			{
				parent.layer.msg('<div style="color:red;font-size:13px;">密码只能以字母开头，只能包含字符、数字或下划线！</div>' ,2,3);
				return false ;
			} 
			return true   
		}
	</script>
	<style type="text/css">
	body,ul,li{ margin:0; padding:0;}
	ul,li{margin-top: 20px;}
	li{ list-style:none; clear:both; line-height:30px; margin-bottom:10px;width:400px;}
	label{ line-height:30px; height:30px; float:left; text-align:right; width:100px; font-size:14px;
	font-family:"黑体";}
	span{ line-height:30px; height:30px;font-size:11px;
	font-family:"黑体";}
	input{ line-height:30px; height:30px; border:1px solid #ccc; width:200px;}
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
		_line-height: 36px;text-indent:60px;
		color:#fff;text-decoration:none;
	}
	.log-button a.checkout:hover{background-position:0 -36px;box-shadow:0 1px 1px rgba(0,1,1,0.3);background-color:#ED4749;text-decoration: none;}
	</style>
</head>
<body>
<ul>
	<li><label>新密码：</label><input type="password" id="pass" value=""/></li>
	<li><label>确认新密码：</label><input type="password" id="pass2" value=""/></li>
	<li class="log-button"><a href="JavaScript:void(0);" class="checkout" id="btn_sure">确定修改</a></li>
	
</ul>
</body>
</html>
