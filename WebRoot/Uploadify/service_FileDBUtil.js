function makeRandomString(n){ 
	var baseStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
	for (var i = 0, r =""; i < n; i++) r += baseStr.charAt(Math.floor(Math.random() * 62));
	return r;
};
var PLUG_FINAL_RANDOMSTR = makeRandomString(32);
//���ñ�����̬���뵽form���У���������������ΪFINAL_RANDOMSTR
//��ȡȷ����
$(function(){
	var btnSureJqObj = $(document).find("input[name=btn_sure]");
	btnSureJqObj.before("<input name='FINAL_RANDOMSTR' type='hidden' value='"+PLUG_FINAL_RANDOMSTR+"' size='40'/>");
 }
);

function FileDBUtil(){
	this.uploadFileId =  "";    //�ļ��ؼ�ID
	this.saveFolder   =  "";    //����·��
	this.relaDataId   =  "";    //����¼ID
	this.fileUpLoadStyle = "1"; //1��ʾִ���滻/�����ϴ� ������1��ʾʵ�ֶ���ļ��ϴ�
	this.modsign         = "";  //ģ����
	this.multiFileUpload = false; //�Ƿ���ж��ļ��ϴ�
	this.uploadFileDesc =  ""; //������������µ�'fileExt'���ԣ���ô��������Ǳ���� [֧�ָ�ʽ:jpg/gif/jpeg/png/bmp.]
    this.uploadFileExt = "" ;//����ĸ�ʽ[*.jpg;*.gif;*.jpeg;*.png;*.bmp]
    this.uploadFileSizeLimit = 1024*1024*50; //Ĭ��50M
	this.FINAL_RANDOMSTR   = "";  //����--�޸��ļ��⽡ʱʹ�á����鶼���ɡ�
	this.IS_DEBUG          = false; //�Ƿ���е���
	this.insertFileMap          ={};    //�ò������ڸ���ʹ��
	this.isOnlyShowFile         = false;
	this.isAutoCommit           = true;
	this.fileSysUrl             = "http://192.168.1.168:13080/hr";
	//����ɾ�����������ϴ�JQUERY url
	this.configValues = {
		'custom_file_delete_jqurl':'',
		'custom_file_insert_jqurl':'',
		'custom_file_upload_jqurl':''
	};
	
	//�ⲿ�ӿ�
	this.setConfigValues = function(p){ 
		this.configValues = $.extend(this.configValues, p || {}); 
	};
	
	this.setInsertFileMap = function(p){ 
		this.insertFileMap = $.extend(this.insertFileMap, p || {}); 
	};
	
	//ɾ����������			
	this.deleteFileOper = function(index,fileid){
		 var $this = this;
		 if(confInfo(21)) {   
			 var parentFileSpan = $("#"+$this.uploadFileId+"FileList");
			 var spanJqObj = $("#"+$this.uploadFileId+"_efile_"+index);
			 $(spanJqObj,parentFileSpan).remove();
			 parentFileSpan.remove(spanJqObj);
			 var uploadJqObj = $("#"+$this.uploadFileId); 
			 var url = $this.configValues['custom_file_delete_jqurl'];
			 if(url == ""){
				alert('�����ظ�����ɾ����JQUERY������');return;
			 }
		 	 url = $this.tranToFileServicePath(url);
			 queryparam = {"fileid":fileid};
			 $.ajax({
				 type: "get", 
				 url:url,
				 async:true,
				 data:queryparam, 
				 dataType : "jsonp",
				 jsonp: 'jsoncallback', //Ĭ��callback
				 success:
				 function(data) {
					if(data=="-1"){
						alert("ɾ���ļ�ʧ�ܣ�");
					}
					else{
						//$this.onLoadFileUploader();
						//$("#"+$this.uploadFileId+"_span").show();
					}
				 }
			 });	
		  }
	}; 
	//������������
	this.insertBaseFileByJq = function(modsign,savename,filename){
		 var $this = this;
		 var url = $this.configValues['custom_file_insert_jqurl'];
		 if(url == ""){
			alert('�����ظ�����������JQUERY������');return;
		 }
		 url = $this.tranToFileServicePath(url);
		 queryparam = {
			"fileStyle":$this.fileUpLoadStyle,
			"savePath":$this.saveFolder,
			"modsign":modsign,
			"recid":$this.relaDataId,
			"savename":savename,
			"filename":encodeURIComponent(filename),
			"FINAL_RANDOMSTR" : $this.FINAL_RANDOMSTR
		};
		queryparam = $.extend(queryparam, $this.insertFileMap || {}); 
		 $.ajax({
			 type: "get", 
			 url:url,
			 async:false,   //һ��Ҫͬ��
			 dataType : "jsonp",
			 jsonp: 'jsoncallback', //Ĭ��callback
			 data:queryparam,
			 success:
			 function(json) {
				if(json=="-1"){
					alert('�������ݿ��ļ�����');
				}
				else{
					$this.reloadAfterInsert(json);
				}
			 }
		 });	
	}; 
	
	this.reloadAfterInsert = function(data){
		
	};
	
	//ע���¼�
	this.onLoadFileUploader=function(){
		var $this = this;
		if($this.uploadFileId == ""){
			alert("������Ҫ�ϴ��ļ��ؼ���ID��");return ;
		}
		if($this.isOnlyShowFile == false){
			if($this.saveFolder == ""){
				alert("������Ҫ�ϴ��ļ����ļ��У�");return ;
			}
			if($this.modsign == ""){
				alert("�������ϴ��ļ�ģ���ǣ�");return ;
			}
			//ʵ��һ�������
			if($this.FINAL_RANDOMSTR == "null" || $this.FINAL_RANDOMSTR==null || $this.FINAL_RANDOMSTR == ""){
				$this.FINAL_RANDOMSTR = PLUG_FINAL_RANDOMSTR;
			}
			if($this.FINAL_RANDOMSTR == ""){
				alert("������FINAL_RANDOMSTR��Ȼ�����ϴ���");return ;
			}
			
			//ʵ��flash�ﱾ�ļ��
			$this.checkFlashInfo();
			var uploadFileUrl = $this.configValues['custom_file_upload_jqurl'];
			if(uploadFileUrl==""){
				alert("�����ظ������ϴ���JQUERY������");return ;
			}
		    uploadFileUrl = $this.tranToFileServicePath(uploadFileUrl);
			var uploadJqObj  = $("#"+$this.uploadFileId);
			uploadJqObj.uploadify({
				'uploader'       : '../../Uploadify/uploadify.swf?v'+((new Date()).getTime()), // The path to the uploadify swf file
				'script'         : uploadFileUrl+'?savePath='+$this.saveFolder,
				'fileDataName'   : 'uploads',
				'auto'           : $this.isAutoCommit,
				'buttonText'     : 'BROWSE',//��ť�ϵ�����
				'buttonImg'      : '../../Uploadify/show.gif'+'?timestep1'+((new Date()).getTime()),
				'height'         : 20,
				'width'          : 62,
				'multi'          : $this.multiFileUpload,//�Ƿ�֧�ֶ��ļ��ϴ�
				'fileDesc'       : $this.uploadFileDesc, //������������µ�'fileExt'���ԣ���ô��������Ǳ���� [֧�ָ�ʽ:jpg/gif/jpeg/png/bmp.]
				'fileExt'        : $this.uploadFileExt,//����ĸ�ʽ[*.jpg;*.gif;*.jpeg;*.png;*.bmp]
				'sizeLimit'      : $this.uploadFileSizeLimit,
				'onComplete'     : function (event, queueID, fileObj, response, data){
					$this.insertBaseFileByJq($this.modsign,response,fileObj.name);
					
					if($this.IS_DEBUG){
						//������ϸ����
						alert("�Ƿ��滻�ļ�:==="+($this.fileUpLoadStyle=="1"?"��":"��")+"====\n �ļ�����·��:==="+$this.saveFolder+"====\n �ļ�modsign:==="+$this.modsign+"====\n ���id:==="+$this.relaDataId+"====\n �����:==="+$this.FINAL_RANDOMSTR+"====");
					}
				}
			});
		}
	};
	//�������ط���
	this.downLoad = function(savepath,savename){
		$this = this;
		if(($this.fileSysUrl || "") != ""){
		    downframe.document.form1.action = $this.fileSysUrl+"/downfile.shtml";
		}
		else{
		    //downframe.document.form1.action = "/downfile.shtml";
		}
	    downframe.document.form1.inputPath.value = savepath;
	    downframe.document.form1.fileName.value = savename;
	    downframe.document.form1.submit();
	}; 
	
	//js�����ļ��б�
	this.makeFileList = function(fileListArr){ 
		var $this = this;
		var fileParentSpan = $("#"+$this.uploadFileId+"_span");
		//��̬���������б�������ʽΪ fileId+FileList�磨uploads1FileList��
		var fileListSpan = $("<span id='"+$this.uploadFileId+"FileList'></span>");
		for(i=0;i<fileListArr.length;i++){
			(function(o){
					
				 var savepath = o.savepath;
				 var savename = o.savename;
				 var filename = o.filename;
				 var divTmp = null;
				 if($this.isOnlyShowFile == false){
					 divTmp   = $('<div id="'+$this.uploadFileId+'_efile_'+o.index+'">\
												<a href=\'JavaScript:void(0);\'>&gt;&gt;&nbsp;ɾ��</a>��\
												<a href=\'JavaScript:void(0);\'>'+filename+'</a>\
											</div>');
					$("a:eq(0)",divTmp).bind("click",function(){
						$this.deleteFileOper(o.index,o.id);
					});
					$("a:eq(1)",divTmp).bind("click",function(){
						$this.downLoad(o.savepath,o.savename);
					});
				 }
				 else{
					divTmp   = $('<div id="'+$this.uploadFileId+'_efile_'+o.index+'">\
												����'+(i+1)+'��<a href=\'JavaScript:void(0);\'>'+filename+'</a>\
											</div>');
					$("a:eq(0)",divTmp).bind("click",function(){
						$this.downLoad(o.savepath,o.savename);
					});
				 }
				fileListSpan.append(divTmp);
			})(fileListArr[i]);
		}
		fileParentSpan.after(fileListSpan);
	};
	
	//jquery������ȡ�ؼ�����
	this.getFormFieldByJq = function(fieldname){
		var ftypestr = "text,hidden,checkbox,radio,submit,button";
		var ftypeArr = ftypestr.split(",");
		for(i=0;i<ftypeArr.length;i++){
			if($("input[name='"+fieldname+"'][@type="+ftypeArr[i]+"]").size()>0){
				return $("input[name='"+fieldname+"'][@type="+ftypeArr[i]+"]");
			}
		}		
		if($("select[name='"+fieldname+"']").size()>0){
			return $("select[name='"+fieldname+"']");
		}
		
		if($("textarea[name='"+fieldname+"']").size()>0){
			return $("textarea[name='"+fieldname+"']");
		}
		alert("�Ҳ�������Ϊ��"+fieldname+"�Ŀؼ�");
		return ;
	};
	//ת�����ļ�����������·��
	this.tranToFileServicePath = function(url){
		$this = this;
		var fileSysUrl  = $this.fileSysUrl || "";
		if(fileSysUrl == ""){return url;}
		else{
			//��ʾ������ǿ����ļ�������
			//��Ϊ���ݵ�url���Դ�ϵͳ������/hr��Ҫ���hrȥ��
			uA = url.split("/");
			var rtn = "";
			alert(uA.length);
			for(var i=2;i<uA.length;i++){
				rtn +="/"+uA[i];
			}
			return $this.fileSysUrl+rtn;
		}
	}
	//���������Ƿ�װflash�Լ�flash�İ汾
	this.doCheckFlash = function(){
		$this = this;
		var hasFlash = 0; //�Ƿ�װ��flash���
		var flashVersion = 0; //flash�汾
		if(document.all){
			var isInstall = true;
			try {
				// version will be set for 7.X or greater players
				var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
				VSwf=swf.GetVariable("$version");
			} catch (e) {
				isInstall = false;
				return {f:0,v:0};
			}
			var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
			if(swf) {
				hasFlash=1;
				VSwf=swf.GetVariable("$version");
				flashVersion=parseInt(VSwf.split(" ")[1].split(",")[0]); 
			}
		}
		else{
			if(navigator.plugins && navigator.plugins.length > 0){
				var swf=navigator.plugins["Shockwave Flash"];
				if(swf){
					hasFlash = 1;
					var words = swf.description.split(" ");
					for(var i=0;i<words.length;i++){
						if(isNaN(parseInt(words[i]))) continue;
						flashVersion = parseInt(words[i]);
					}
				}
			}
		}
		return {f:hasFlash,v:flashVersion};
	};
	//ʵ�ּ�����
	this.checkFlashInfo = function(){
		$this = this;
		var fls = $this.doCheckFlash();
		if(fls.f){
			//�ж�flash�İ汾�Ƿ�С��10�����ǣ�����ʾ��������
			if(parseInt(fls.v)<10){
				$("#"+$this.uploadFileId).after("<div style=\"background-color: #FDE5DD !important;border: 2px solid #FBCBBC !important;width:90%;\"><a href=\"JavaScript:$this.downLoad('/Pelite/Download/','flash.rar');\">&nbsp;&nbsp;��ǰflash����汾Ϊ: "+fls.v+"���������ڱ�ϵͳ-�����ϴ����ܣ������������[��װ�������������]��</a></div>");
			}
		}
		else{
				$("#"+$this.uploadFileId).after("<div style=\"background-color: #FDE5DD !important;border: 2px solid #FBCBBC !important;width:90%;\"><a href=\"JavaScript:$this.downLoad('/Pelite/Download/','flash.rar');\">&nbsp;&nbsp;��������δ��װFlash���--����ʹ�ø����ϴ����ܣ�������Ӱ�װ[��װ�������������]��</a></div>");
		}
	};
};
