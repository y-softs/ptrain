// JavaScript Document
/** 
     config 配置选项 参数说明： 
	 pageSize：总记录  
	 limit：每页记录数 
	 pageNumLength：分页数字长度  
	 showPageNum：是否显示分页数字
 */  
(function($) {  
    $.fn.extend({  
        page : function(config) {  
                config = $.extend({  
                    //默认配置  
                    pageNumLength:4,  
                    showPageNum: true,  
                    limit: 10
                },config||{});  
                var temp = this;  
                temp.attr('paged','true');                    
				this.pageNumLength;  
				// 是否显示分页数字  
				this.showPageNum = true;  
				if (config.showPageNum != null  
						&& typeof (config.showPageNum) != "undefined") {  
					temp.showPageNum = config.showPageNum;  
				}  
				if (config.pageNumLength != null  
						&& typeof (config.pageNumLength) != "undefined") {
					temp.pageNumLength = config.pageNumLength * 1 - 1;
				}
				// 总记录数  
				this.pageSize = 0;  
				if (config.pageSize != null && typeof (config.pageSize) != "undefined") {  
					temp.pageSize = config.pageSize;  
				}  
				// 分页开始位置  
				this.start = 0;  
				if (config.start != null && typeof (config.start) != "undefined") {  
					temp.start = config.start;  
				}  
				// 每页记录数  
				this.limit;  
				if (config.limit != null && typeof (config.limit) != "undefined") {  
					temp.limit = config.limit;  
				}  
				// 当前页  
				this.currentPage = 1;  
				// 总页数  
				this.pageCount = parseInt((temp.pageSize*1 + temp.limit*1 - 1) / temp.limit*1);  
			  
				// 分页条对象数组  
				this.pageBar = [];  
                      
				this.render = function(){
					AjaxQjObj(temp.currentPage);  
					temp.pageBar = [];  
					var firstPage = "<input type='button' class='pageBtn'  name= 'firstPage' value='首&nbsp;&nbsp;页'/>";  
					var prePage = "<input type='button' class='pageBtn'  name='prePage' value='上一页'/>";  
					var turnPage = "<span id=\"currentPage\">"+temp.currentPage+"</span>/"+ temp.pageCount + "页 " + temp.pageSize + "条记录";  
					var lastPage = "<input type='button' class='pageBtn' name='lastPage'  value='尾&nbsp;&nbsp;页'/>";  
					var nextPage = "<input type='button' class='pageBtn' name='nextPage'  value='下一页'/>";  
					temp.pageBar.push(firstPage + prePage);  
					temp.pageBar.push("<b name='pageNums' ></b>");  
					temp.pageBar.push(nextPage + lastPage + turnPage);  
			  
					temp.html(  
							"<div  name='hasRecord' class='page'>"  
									+ temp.pageBar.join("") + "</div><div  name='noRecord' class='msg' id=\"manage\">无记录</div>");  
					temp.find("input[name='prePage']").click(function() {  
						if (temp.currentPage != 1) {  
							temp.queryData(temp.currentPage * 1 - 1);  
						}  
					});  
					temp.find("input[name='nextPage']").click(function() {  
						if (temp.currentPage != temp.pageCount) {
							temp.queryData(temp.currentPage * 1 + 1);  
						}  
					});  
					temp.find("input[name='firstPage']").click(function() {  
						if (temp.currentPage != 1) {
							temp.queryData(1);  
						}  
					});  
					temp.find("input[name='lastPage']").click(function() {  
						if (temp.currentPage != temp.pageCount) {
							temp.queryData(temp.pageCount);  
						}  
					});
					temp.find("input[name='prePage']").attr('class', 'disabled').attr("disabled",true);  
					temp.find("input[name='firstPage']").attr('class', 'disabled').attr("disabled",true); 
			  		if(temp.pageCount<=1){
						temp.find("input[name='nextPage']").attr('class', 'disabled').attr("disabled",true);   
						temp.find("input[name='lastPage']").attr('class', 'disabled').attr("disabled",true);  			  			
			  		}
					if (temp.pageSize == 0) {// 判断是否有记录  
						temp.find("div[name='hasRecord']").hide();  
						temp.find("div[name='noRecord']").show();  
					} else {  
						temp.find("div[name='hasRecord']").show();  
						temp.find("div[name='noRecord']").hide();  
						temp.genericPageNum();  
					}  
				}  
                          
				/** 
				 * 查询数据 startIndex：页数 
				 */  
				this.queryData = function(startIndex) {  
					temp.exeQuery(startIndex);
				}  
				this.exeQuery = function(startIndex){ 
					AjaxQjObj(startIndex);
					$("#currentPage").html(startIndex);
					temp.currentPage = startIndex;  
					// 当前页是第一页，禁用"首页"、"上一页"；  
					if (temp.currentPage == 1) {
						temp.find("input[name='prePage']").attr('class', 'disabled').attr("disabled",true);  
						temp.find("input[name='firstPage']").attr('class', 'disabled').attr("disabled",true);  
					} else {  
						temp.find("input[name='prePage']").attr('class', 'pageBtn').attr("disabled",false);  
						temp.find("input[name='firstPage']").attr('class', 'pageBtn').attr("disabled",false);  
					}  
					// 当前页是最后一页，禁用"尾页"、"下一页"  
					if (temp.currentPage == temp.pageCount) {  
						temp.find("input[name='nextPage']").attr('class', 'disabled').attr("disabled",true);   
						temp.find("input[name='lastPage']").attr('class', 'disabled').attr("disabled",true);  
					} else {
						temp.find("input[name='nextPage']").attr('class', 'pageBtn').attr("disabled",false);  
						temp.find("input[name='lastPage']").attr('class', 'pageBtn').attr("disabled",false);    
					}  
					temp.find("input[name='turnPage']").val(temp.currentPage); 
					temp.genericPageNum();  
				}  
				// 初始化分页数字栏  
				this.genericPageNum = function() {  
					if (!temp.showPageNum) {  
						return false;  
					}  
					var pageNums = [];  
					var indexUp = parseInt(temp.currentPage) + temp.pageNumLength * 1;  
					var startIndex;  
					// 判断分页数字循环起始点和结束点  
			  
					if (indexUp <= temp.pageCount) {// 分页数字最后一位小于总页数  
						if (temp.currentPage == 1) {  
							startIndex = 1;  
							indexUp = parseInt(temp.currentPage) + temp.pageNumLength * 1;  
						} else {// 分页数字排到页尾  
							startIndex = temp.currentPage * 1 - 1;  
							indexUp = parseInt(temp.currentPage)  
									+ (temp.pageNumLength * 1 - 1);  
						}  
					} else {  
						if (temp.currentPage == 1) {  
							startIndex = 1;  
							indexUp = temp.pageCount;  
						} else {  
							startIndex = temp.currentPage * 1 - 1;  
							indexUp = temp.pageCount;  
						}  
					}  
					for ( var i = startIndex; i <= indexUp; i++) {  
						var chooseClass = 'notChoosed';  
						if (i == temp.currentPage) {// 当前选中页  
							chooseClass = 'choosed';  
						}  
						if (i == indexUp) {  
							if (indexUp != temp.pageCount) {  
								pageNums.push("<a name='pageNum' class='"  
										+ chooseClass + "' value='" + i + "' index='end'>["  
										+ i + "]</a>");  
							} else {// 最后一页  
								pageNums.push("<a name='pageNum' class='"  
										+ chooseClass + "' value='" + i  
										+ "' index='last'>[" + i + "]</a>");  
							}  
						} else if (i == temp.currentPage) {  
							pageNums.push("<a name='pageNum' class='"  
									+ chooseClass + "' value='" + i + "' index='first'>["  
									+ i + "]</a>");  
						} else {  
							pageNums.push("<a name='pageNum' class='"  
									+ chooseClass + "' value='" + i + "' index=''>[" + i  
									+ "]</a>");  
						}  
					}  
			  
					temp.find("b[name='pageNums']").html(pageNums.join(""));  
			  
					// 分页数字点击事件  
					temp.find("a[name='pageNum']").unbind('click').bind(  
							'click',  
							function() {  
								$("a[name='pageNum']").attr('class',  
										'notChoosed');  
								$(this).attr('class', 'choosed');  
								temp.queryData($(this).attr('value'));  
							});  
				}  
			  
				// 变量get、set方法  
				temp.setPageSize = function(data) {  
					// 查询页数与之前不一致，重新初始化分页条（数据库记录更改）  
					if (temp.pageSize != data) {  
						this.pageSize = data;  
						this.pageCount = parseInt((this.pageSize*1 + this.limit*1 - 1)  
								/ this.limit*1);  
						this.render();  
						this.queryData(1);  
						return false;  
					}else{  
						return true;  
					}  
				}  
				  
				this.getPageSize = function() {  
					return this.pageSize;  
				}  
				this.setLimit = function(v) {  
					this.limit = v;  
				}  
				this.getLimit = function() {  
					return this.limit;  
				}  
				this.render();  
        }  
    })  
})(jQuery); 