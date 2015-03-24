<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../Include/TagLib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/Web/Style/Main.css'/>"/>
<script language="javascript" src="<c:url value='/Web/Script/Valid.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/Script/ScheUtil.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/lib.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/Web/layer-v1.8.3/layer.min.js'/>"></script>
	<script language="javascript">
		$(function(){
			$(".tab tfoot").hide();
			if(${empty querymap.postid})return;
			//出题轮次 每日出题次数
			var url="<c:url value='/pt/ask/findPtrainAskPushnumByJq.shtml'/>";
			var queryMap = {'querymap.unitid':'${querymap.unitid}'};
			var dataInfo=operDataByJq(url,queryMap);
			var pushnum=dataInfo[0].pushnum,intflag=dataInfo[0].intflag,subtime=dataInfo[0].subtime;
			if('0'==intflag){
				layer.msg('<div style="color:red;font-size:13px;">请设置抽题策略！</div>' ,2,3); 
				return;
			}else if(''==pushnum){//今天未答题
				queryData('true',1);
			}else if(''==subtime){//答题中断或答题错误
				queryData('','');
				$("#btn_submit").after('<input type="hidden" name="ptrainAskBean.pushnum" value="'+pushnum+'">');
			}else if(parseInt(pushnum)>=parseInt(intflag)){
			    $(".tab tr:eq(0)").after('<tr class="list_tr1" align="center"><td style="color:red;" colspan="2">今日已完成答题'+pushnum+'次，达到最大出题次数限制！</td></tr>');
				return;
			}else if(parseInt(pushnum)<parseInt(intflag)){
				var  index = layer.confirm('今日已完成答题'+pushnum+'次，是否继续答题？',function(i){
					layer.close(index);
					queryData('',(parseInt(pushnum)+1));			
			    },function(){
			    	document.form1.action="<c:url value='/pt/ask/listptrainaskquery.shtml' />";
					document.form1.submit();
			    });
			    
			}
			//提交
			$("#btn_submit").click(function(){
					$(".em_tab").each(function(){
						$($("input[name='"+$("input[type=radio]",$(this)).attr("name")+"']")[0]).attr("dataType","Group").attr("msg","答案不能为空！");
					});
				if(Validator.Validate(form1,2)){
					layer.confirm('确定提交吗？',function(index){
						layer.load('加载中');
						document.form1.action="<c:url value='/pt/ask/saveptrainaskitem.shtml' />";
						document.form1.submit();					
				    });
				}				
			});
		});
		//查找每日三问 题目列表
		function queryData(todaynoask,pushnum){
			var url="<c:url value='/pt/ask/findPtrainAskitemListByJq.shtml'/>";
			var queryMap = {'querymap.unitid':'${querymap.unitid}','querymap.postid':'${querymap.postid}','querymap.todaynoask':todaynoask,'querymap.pushnum':pushnum};
			var resMap=operDataByJq(url,queryMap);
			//var typeMap=resMap[0].typeMap;
			var policyMap=resMap[0].policyMap;
			var askid=resMap[0].askid;
			var dataList=resMap[0].ptrainAskitemList;
			
			var singleSel=resMap[0].TYPE_SINGLE,multSel=resMap[0].TYPE_MULT,judgeSel=resMap[0].TYPE_JUDGE;
			if(''==dataList){
				if(''!=askid&&''!=pushnum){					
					layer.msg('<div style="color:red;font-size:13px;">没有符合您专业的题目或您本月已答完所有题目！</div>' ,2,3); 
					//今天未答题或继续答题 没有题目, 删除本次每日三问主表记录
					operDataByJq("<c:url value='/pt/ask/deletePtrainAskByJq.shtml'/>",{'ptrainAskBean.id':askid});
					setTimeout(function(){							
							document.form1.action="<c:url value='/pt/ask/listptrainaskquery.shtml' />";
				    		document.form1.submit();
					}, 2000);					
				}
				return;
			}
			var trObj=$(".tab tr:eq(0)"),dataStr='';
			for(var i=0;i<dataList.length;i++){
				dataStr+='<tr><td colspan="2" class="right"><table class="em_tab">';
				dataStr+='<tr><td><span class="fontcolor_b">['+dataList[i].strflag.replace('题','')+']</span></td><td colspan="2">'+(i+1)+'、'+dataList[i].topic+'</td></tr>';
				var inpType='radio',inpName='querymap.ansdetail'+dataList[i].id;				
				var chkValid='  ';
				if(dataList[i].strflag.indexOf(singleSel)>-1||dataList[i].strflag.indexOf(multSel)>-1){
					//单选、多选
					if(dataList[i].strflag.indexOf(multSel)>-1)inpType='checkbox';
					if(''!=dataList[i].option1){
						dataStr+='<tr><td width="6%">&nbsp;</td><td width="47%"><input type="'+inpType+'" name="'+inpName+'" value="A"'+chkValid+'>A、'+dataList[i].option1+'</td>';
						if(''!=dataList[i].option2){
							dataStr+='<td width="47%"><input type="'+inpType+'" name="'+inpName+'" value="B"'+chkValid+'>B、'+dataList[i].option2+'</td></tr>';
						}else{
							dataStr+='<td width="47%">&nbsp;</td></tr>';
						}
					}
					if(''!=dataList[i].option3){
						dataStr+='<tr><td>&nbsp;</td><td><input type="'+inpType+'" name="'+inpName+'" value="C"'+chkValid+'>C、'+dataList[i].option3+'</td>';
						if(''!=dataList[i].option4){
							dataStr+='<td><input type="'+inpType+'" name="'+inpName+'" value="D"'+chkValid+'>D、'+dataList[i].option4+'</td></tr>';
						}else{
							dataStr+='<td>&nbsp;</td></tr>';
						}
					}
					if(''!=dataList[i].option5){
						dataStr+='<tr><td>&nbsp;</td><td><input type="'+inpType+'" name="'+inpName+'" value="E"'+chkValid+'>E、'+dataList[i].option5+'</td>';
						if(''!=dataList[i].option6){
							dataStr+='<td><input type="'+inpType+'" name="'+inpName+'" value="F"'+chkValid+'>F、'+dataList[i].option6+'</td></tr>';
						}else{
							dataStr+='<td>&nbsp;</td></tr>';
						}
					}
					if(''!=dataList[i].option7){
						dataStr+='<tr><td>&nbsp;</td><td><input type="'+inpType+'" name="'+inpName+'" value="G"'+chkValid+'>G、'+dataList[i].option7+'</td>';
						dataStr+='<td>&nbsp;</td></tr>';
					}
				}else if(dataList[i].strflag.indexOf(judgeSel)>-1){
					//判断
					dataStr+='<tr><td width="6%">&nbsp;</td><td width="47%"><input type="'+inpType+'" name="'+inpName+'" value="1"'+chkValid+'>正确</td>';
					dataStr+='<td width="47%"><input type="'+inpType+'" name="'+inpName+'" value="0"'+chkValid+'>错误</td></tr>';
				}
				dataStr+='</table>';
				dataStr+='<input type="hidden" name="askitemid" value="'+dataList[i].id+'">';
				dataStr+='<input type="hidden" name="rightans" value="'+dataList[i].answer1+'">';
				dataStr+='<input type="hidden" name="quescore" value="'+policyMap['${querymap.postid}_SCORE']+'">';
				dataStr+='</td></tr>';
			}
			trObj.after(dataStr);
			$(".tab tfoot").show();
			$("#btn_submit").after('<input type="hidden" name="ptrainAskBean.id" value="'+askid+'">');
		}
	    //数据操作接口
		function operDataByJq(url,queryMap){
			var res='';
			$.ajax({
				type: "POST", 
				url:url,
				async:false,
				data:queryMap,
				dataType:"json",
				success:function(json){
					if("-1"==json){						
						layer.msg('<div style="color:red;font-size:13px;">操作失败!</div>' ,2,3); 
					}else{
						res=json;
					}
				}
			});
			return res;
		}
	</script>
<body>
<form name="form1" id="form1" action="" method="post">
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
                <span><img src="<c:url value='/Web/Image/menu-mrsw.png'/>" /></span>
                <ul class="ulsidebar">
                    <li><a href="<c:url value='/pt/ask/listptrainask.shtml'/>"><label class="selected"></label>三问答题</a></li>
                    <li><a href="<c:url value='/pt/ask/listptrainaskquery.shtml'/>"><label></label>个人查询</a></li>
                    <li><a href="<c:url value='/pt/ask/listptrainaskskim.shtml'/>"><label></label>试题浏览</a></li>
                </ul>
            </div>
            <div class="list">
                <div class="list-con">
                    <table class="tab">
                    	<!--表格内容部分-->
                        <tbody id="tbody">
                        <tr>
                            <td bgcolor="#EFEFEF"><span class="fontcolor_b">本月累计答题次数：<c:choose><c:when test="${empty ptrainAskBean.asktotal}">0</c:when><c:otherwise>${ptrainAskBean.asktotal}</c:otherwise></c:choose>次</span></td>
                            <td bgcolor="#EFEFEF" align="right"><span class="fontcolor_b">本月累计答题得分：<c:choose><c:when test="${empty ptrainAskBean.score}">0</c:when><c:otherwise>${ptrainAskBean.score}</c:otherwise></c:choose>分</span></td>
                        </tr>
                        
                        </tbody>
                        <!--表格合计或操作部分-->

                        <tfoot>
                        <tr>
                            <td colspan="2">
                            	<div style="width:30px; margin:10px auto 0 auto;">
                               	<a href="###" class="btn btn-primary" id="btn_submit">提交</a>
                                <input type="hidden" name="tokenid" value="${tokenid}" />
                                <input type="hidden" name="querymap.unitid" value="${querymap.unitid}" />
                                <input type="hidden" name="querymap.postid" value="${querymap.postid}" />
                                <input type="hidden" name="querymap.fromask" value="true" />
                                </div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>  
        </div>              
    </div>
	<%@ include file="../Footer.jsp" %>
</div>
</form>
</body>
</html>