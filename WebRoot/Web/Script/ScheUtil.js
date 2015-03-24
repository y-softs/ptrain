$(function(){
	switchScript();
	$("#loginout").bind("click",loginOutMethod);
	
	$("#chkLog").bind("click",Sign_in);	
	$("#upass").bind("click",function(){
		$.layer({
		        type: 2,
		        title: false,
		        maxmin: false,
		        shadeClose: true, //开启点击遮罩关闭层
		        area : ['400px' , '200px'],
		        iframe: {src: 'setpass.shtml'}
		    });
	});
});
function Sign_in(){
	var username = $.trim($("#userid").val());
		var password = $.trim($("#password").val());
		if(username=="" || password==""){
			layer.msg('<div style="color:red;font-size:13px;">用户名或密码不能为空！</div>' ,2,3); 
			return false;
		}else{
			var loading = layer.load('加载中');
			setTimeout(function(){
				$.ajax({
					url:'loginInByJq.shtml',   //接收页面
					type: 'post',      				//POST方式发送数据
					async: false,      				//ajax异步
					data:{'loginbean.userid':username,'loginbean.password':password,'timestmp':(new Date()).getTime()},
					dataType:"json",
					success:function(data) {
						if(data=="-1"){
							layer.close(loading);
							layer.msg('<div style="color:red;font-size:13px;">操作失败，与数据库交互是发生错误！</div>' ,2,3); 
						}else if(data=="0"){
							layer.close(loading);
							layer.msg('<div style="color:red;font-size:13px;">您输入的用户名或密码错误！</div>' ,2,3); 
						}else{
							$("#user-login").hide();
							for(var i in data){	
								$(".user-info").show();
								$("#info").html(data[i].deptname+'&nbsp;&nbsp;'+data[i].username);
								if(data[i].roleids!=""){
									$("#admin").show();
								}
							}
							layer.msg('<div style="font-size:13px;">登录成功！</div>' ,2,1);
							setTimeout(function(){
								parent.layer.closeAll();
						    }, 2000);
						}						
					}
				});
			},500)
		}
}
/*
 *  （4）动态加载JS文件。
 *  函数名称：switchScript(doc)
 *  输入参数：doc，对象型，文档对象。
 *  输 出 值：
 *  应用实例：
 */
function switchScript() {
    var script = document.createElement("script"); 
    script.src = "/hr/Skin/ptrain_Main.js"; 
    script.type = "text/javascript";  
    document.getElementsByTagName("HEAD").item(0).appendChild(script);
}
function openWin(title,width,height,url){
	$.layer({
			type: 2,
			title:title,
			maxmin: false,
			shadeClose: false, //开启点击遮罩关闭层
			area : [width , height],
			iframe: {src: url}
		});
}
//判断是否已登录
function isLogin(){
	var resu = true;
	$.ajax({
		 type: "POST", 
		 url:'isLoginByJq.shtml',
         dataType: "json", 
         async: false,//同步 
		 success:function(rdata){
		 	if(rdata=='-1'){
		 		resu = false;
		 		logonMethod();
		 	}
		 }
	});
	return resu;
}
//登录页
function logonMethod(){
	$.layer({
        type: 2,
        title: false,
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['400px' , '200px'],
        iframe: {src: 'setlogin.shtml'}
    });
}
function loginOutMethod(){	
	loading = layer.load('注销中');
	$.ajax({
		url:'loginOutByJq.shtml',   //接收页面
		type: 'post',      				//POST方式发送数据
		async: true,      				//ajax异步
		dataType:"json",
		success:function(data) {
			if(data=="-1"){
				parent.layer.msg('<div style="color:red;font-size:13px;">操作失败，与数据库交互是发生错误！</div>' ,2,3); 
			}else{
				$(".user-info").hide();
				$("#user-login").show();
				layer.msg('<div style="font-size:13px;">注销成功！</div>' ,2,1);
				setTimeout(function(){
					layer.close(loading);
			    }, 2000);
				 
			}						
		}
	});
}
function layerClose(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
/*
 * （2）分页表单提交参数判断。
 *  函数名称：subFormPage(url,tobjname,page,record)
 *  输入参数：url，字符串型，目标路径；tobjname，字符串型，目标对象名称（表单）；
              page，整型，当前页码；record，整型，记录数/页。
 *  输出参数：布尔型。
 *  应用实例：
 */ 
function subFormPage(url,tobjname,page,record) {
    try {
        document.getElementsByName(tobjname)[0].action = url;
        document.getElementsByName("tagpage")[0].value = page;
        document.getElementsByName("record")[0].value = record;
        document.getElementsByName(tobjname)[0].submit();
    } catch(err) {  
        alert("提交表单参数有误！");
        return false;
    }
}
/*
 * （1）文本框字数限制。
 *  函数名称：checkMaxLen(sobj,maxlen,tobjname)
 *  输入参数：sobj，对象型，源对象（文本框）；maxlen，整型，限制字数；
              tobjname，字符串型，目标对象名称（标签）。
 *  输出参数：
 *  应用实例：
 */ 
function checkMaxLen(sobj,maxlen,tobjname) {
    tobjname = tobjname  || "count";
    var str = sobj.value;
    if(str.length > maxlen&&str.length>0) {
        sobj.value = str.substring(0,str.length-1);
    }
    document.getElementById(tobjname).innerHTML = sobj.value.length;
}