package com.nomen.ntrain.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.web.constatn.WebConstant;
import com.nomen.ntrain.web.service.WebCoursService;



/**
 * @description 莆田岗位培训_课件资源表 action层
 * @author 许东雄
 * @date 2014-11-16
 */
@SuppressWarnings("all")
public class WebCoursAction extends WebAction{

	private	WebCoursService		webCoursService;
	private 	PtrainCodeService	ptrainCodeService;		//代码列表业务接口
	private 	PtrainFileService	ptrainFileService;
	private	String				pkId;					//课件ID
	private 	String 				mlId;					//目录ID
	private	String				typeid;					// 类别ID
	private 	List				dataList;				//结果集
	
	/**
	 * 课件列表
	 * @return
	 */
	public String listCours(){
		HttpServletRequest req = ServletActionContext.getRequest();
		Map codeM = new HashMap();
		codeM.put("fatherid", CodeFatherUtil.PTRAIN_COURS);
		List codeList = this.ptrainCodeService.findPtrainCodeList_Comm(codeM);
		req.setAttribute("codeList", codeList);
		if(!func.isEmpty(codeList) && func.IsEmpty(this.typeid)){
			this.typeid = ((PtrainCodeBean)codeList.get(0)).getId();
		}
		Map map = new HashMap();
		map.put("typeid",this.typeid);
		map.put("fatherid", WebConstant._FATHERID);
		map.put("fields", this.fields);
		map.put("keyword", this.keyword);
		this.dataList = this.webCoursService.findPtrainCoursList(map, func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
		this.setTotal(String.valueOf(map.get("total"))); //总记录数
		return SUCCESS;
	}	
	/**
	 * 目录
	 * @return
	 */
	public String listChilCours(){
		HttpServletRequest req = ServletActionContext.getRequest();
		if(!func.IsEmpty(this.pkId)){
			Map map = new HashMap();
			map.put("fatherid", this.pkId);
			this.dataList = this.webCoursService.findPtrainCoursChilList(map);
			if(!func.isEmpty(this.dataList) && func.IsEmpty(this.mlId)){
				this.mlId = ((PtrainCoursBean)this.dataList.get(0)).getId();
			}
			req.setAttribute("title", this.webCoursService.findPtrainCoursBeanById(this.pkId).getTitle());
			PtrainCoursBean cBean = this.webCoursService.findPtrainCoursBeanById(this.mlId);
			if(null == cBean){
				cBean = this.webCoursService.findPtrainCoursBeanById(this.pkId);
				if(func.IsEmpty(cBean.getIntflag())){
					return SUCCESS;
				}
				if(cBean.getIntflag().toLowerCase().indexOf(".mp4")>=0 || cBean.getIntflag().toLowerCase().indexOf(".flv")>=0){
					req.setAttribute("TYPE", "1");
				}else if(cBean.getIntflag().toLowerCase().indexOf(".swf")>=0){
					req.setAttribute("TYPE", "2");
				}else{
					req.setAttribute("TYPE", "3");
				}
				this.objFileInfo(this.pkId,"2");
				
			}else{
				if(cBean.getStrflag().toLowerCase().indexOf(".mp4")>=0 || cBean.getStrflag().toLowerCase().indexOf(".flv")>=0){
					req.setAttribute("TYPE", "1");
				}else if(cBean.getStrflag().toLowerCase().indexOf(".swf")>=0){
					req.setAttribute("TYPE", "2");
				}else{
					req.setAttribute("TYPE", "3");
				}
			}
			req.setAttribute("coursBean", cBean);
			
		}
		return SUCCESS;
	}
	/*******************************************************/
	/******************   辅助查询   *************************/
	/*******************************************************/
	/**
	 *查询文件信息[辅助]
	 * @return
	 */
	private void objFileInfo(String id,String modsign){
		//实例请求类
		HttpServletRequest req=ServletActionContext.getRequest();
		Map fileMap=new HashMap();
		fileMap.put("recid",id);			
		fileMap.put("modsign", modsign);
		List fileList = this.ptrainFileService.findPtrainFileList(fileMap);
		req.setAttribute("fileList", fileList);
	}
	
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public WebCoursService getWebCoursService() {
		return webCoursService;
	}
	public void setWebCoursService(WebCoursService webCoursService) {
		this.webCoursService = webCoursService;
	}

	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}

	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}
	public String getMlId() {
		return mlId;
	}
	public void setMlId(String mlId) {
		this.mlId = mlId;
	}
	public PtrainFileService getPtrainFileService() {
		return ptrainFileService;
	}
	public void setPtrainFileService(PtrainFileService ptrainFileService) {
		this.ptrainFileService = ptrainFileService;
	}
	
	
	
}
