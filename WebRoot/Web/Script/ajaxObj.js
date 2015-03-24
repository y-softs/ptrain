/** 
* @author：许东雄
* @date:2014-3-28
* defaults：默认配置参数说明
* unitid：单位ID
* fatherid：父ID 
* content：内容控件  
* username：操作人
* url：操作地址  
 */  
(function($){
	$.fn.extend({
		//插件名称--点赞
		ajaxJqNice:function(options){
			var o = $.extend({id:"",
							  url:""},options);
			var temp = this;
			$(".votecard em").clone().appendTo(".votecard div");
			var node = $(".votecard em:last strong")
			node.text(parseInt(node.text())+1);	
			temp.bind({
			  click: function(event) {
			    event.preventDefault()
			  },
			  mouseup: function() {
			     if(temp.attr("class")=='voteaction'){			 
					temp.flip(temp);
					temp.removeClass().addClass("disabled");
					$.ajax({
						type: "post", 
						 url:o.url,
						 data:{'ptrainBbsBean.id':o.id,'timestmp':(new Date()).getTime()}, 
						 async:true,
						 dataType:"json",
						 success:function(json){
						 	if(json=="-1"){
						 		alert("操作失败，与数据库交互是发生错误！");
							}				
						 }
					});
				}
			  }
			});
			this.flip = function(obj) {
				obj.prev().find("em").animate({
					top: '-=45'
				}, 200);
			}

		},
		//插件名称--发表评论
		ajaxObj:function(options){
			//参数和默认值
			var defaults = {
					unitid : "",
					fatherid : "",
					content : "",
					username : "",
					url:""
			};
			var temp = this;
			var o = $.extend(defaults,options);
			temp.bind("click",function(){
				if(o.content.val() == '' || o.content.val() == null){					
					parent.layer.msg('评论内容不能为空！', 2, 3);
					return;
				} 
				temp.ajaxObjJqData();
			});
			//评论提交后台
			this.ajaxObjJqData = function(){
				temp.attr("disabled",true);
				//参数
				var map = {'fatherid':o.fatherid,
					   	   'unitid':o.unitid,
					   	   'content':encodeURIComponent(o.content.val()),
					   	   'timestmp':(new Date()).getTime()
					  	 };
				$.ajax({
					type:'post',
					url:o.url, 
					data:map, 
					async:true,
					dataType:"json",
					success:function(data){
						if(data=="-1"){
					 		alert("操作失败，与数据库交互是发生错误！");
					 		return;
						}else{
							//layer.close()
							parent.layer.msg('<div style="font-size:13px;">回复成功！</div>' ,2,1);
							$("#manage").hide();
							var html = '<tr>';
								html += '	<td valign="top" class="left" style="padding-right:5px;border-right:1px solid #c2d5e3;">'+o.username+'<br/>'+temp.currentTime();+'</td>';
								html += '	<td style="padding-left:10px;">'+o.content.val()+'</td>'; 
								html += '</tr>';
							$("#tbodyid").html(html+$("#tbodyid").html());
							o.content.val("");//清空值					
							temp.attr("disabled",false);
						}
					}
				});
			}
			//获取当前时间
			this.currentTime = function(){
				var d=new Date(),str='';
			    str +=d.getFullYear()+'-';   //获取当前年份
			    str +=d.getMonth()+1+'-';   //获取当前月份（0——11）		
			    str +=d.getDate()+' ';   
			    str +=d.getHours()+':';
			    str +=d.getMinutes()+':';
			    str +=d.getSeconds();
		     	return str; 
			}
		}
		
	});
})(jQuery);