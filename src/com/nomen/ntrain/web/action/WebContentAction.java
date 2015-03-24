package com.nomen.ntrain.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.bean.PtrainContentBean;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.web.constatn.WebConstant;
import com.nomen.ntrain.web.service.WebContentService;


/**
 * @description 莆田岗位培训_资源表action层
 * @author 许东雄
 * @date 2014-11-16
 */
@SuppressWarnings("all")
public class WebContentAction extends WebAction{

	private 	WebContentService	webContentService;
	private 	PtrainFileService	ptrainFileService;
	private 	PtrainCodeService	ptrainCodeService;		//代码列表业务接口
	private 	PtrainContentBean	ptrainContentBean;
	private 	String				KIND;					//1.规程文件 2.影音影视库 3.经典书籍库 4.典型经验库
	private	String				pkId;					//内容ID
	private	String				typeid;					// 类别ID
	private 	List				dataList;				//结果集
	
	/**
	 * 资源列表
	 * @return
	 */
	public String listContent(){
		HttpServletRequest req = ServletActionContext.getRequest();
		Map codeM = new HashMap();
		String fatherid = this.getStaticValueByName(this.fatherid);
		codeM.put("fatherid", fatherid);
		List codeList = this.ptrainCodeService.findPtrainCodeList_Comm(codeM);
		req.setAttribute("codeList", codeList);
		if(!func.isEmpty(codeList) && func.IsEmpty(this.typeid)){
			this.typeid = ((PtrainCodeBean)codeList.get(0)).getId();
		}
		Map map = new HashMap();
		map.put("kind", this.KIND);
		if(func.IsEmpty(this.keyword)){
			map.put("typeid",this.typeid);
		}
//		map.put("fields", this.fields);
		map.put("keyword", this.keyword);
		this.dataList = this.webContentService.findPtrainContentList(map, func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
		this.setTotal(String.valueOf(map.get("total"))); //总记录数
		return 	SUCCESS;
	}
	/**
	 * 详细页
	 * @return
	 */
	public String setContentInfo(){
		HttpServletRequest req = ServletActionContext.getRequest();
		Map codeM = new HashMap();
		String fatherid = this.getStaticValueByName(this.fatherid);
		codeM.put("fatherid", fatherid);
		List codeList = this.ptrainCodeService.findPtrainCodeList_Comm(codeM);
		req.setAttribute("codeList", codeList);
		this.objFileInfo(this.pkId, this.KIND);
		this.ptrainContentBean = this.webContentService.findPtrainContentBean(this.pkId);
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
		fileMap.put("modsign", "3");
		List fileList = this.ptrainFileService.findPtrainFileList(fileMap);
		if("2".equals(modsign)){
			if(!func.isEmpty(fileList)){
				req.setAttribute("fileBean", ((PtrainFileBean)fileList.get(0)));
				
			}
		}
		req.setAttribute("fileList", fileList);
	}
	
	
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public String getKIND() {
		return KIND;
	}
	public void setKIND(String kind) {
		KIND = kind;
	}
	public PtrainContentBean getPtrainContentBean() {
		return ptrainContentBean;
	}
	public void setPtrainContentBean(PtrainContentBean ptrainContentBean) {
		this.ptrainContentBean = ptrainContentBean;
	}
	public WebContentService getWebContentService() {
		return webContentService;
	}
	public void setWebContentService(WebContentService webContentService) {
		this.webContentService = webContentService;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public PtrainFileService getPtrainFileService() {
		return ptrainFileService;
	}
	public void setPtrainFileService(PtrainFileService ptrainFileService) {
		this.ptrainFileService = ptrainFileService;
	}
	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}
	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	
	
}
