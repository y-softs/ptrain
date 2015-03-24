package com.nomen.ntrain.web.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.web.service.WebBbsService;

/**
 * @description 莆田岗位培训_知识学习 action层
 * @author 许东雄
 * @date 2014-3-24
 */
@SuppressWarnings("all")
public class WebBbsAction extends WebAction{
	
	private 	WebBbsService		webBbsService;	//代码列表业务接口
	private 	PtrainCodeService	ptrainCodeService;
	private 	PtrainFileService	ptrainFileService;
	private 	PtrainBbsBean		ptrainBbsBean;		//代码信息bean
	private 	Map<String,String>	querymap;			//传参map
	private 	List				dataList;			//结果集
	private 	String				SAVEPATH;			//上传目录 依赖注入
	private 	String				listpage;			//列表页
	private 	String				setpage;			// 跳转页
	private 	String				KIND;				//帖子类别（1常规帖，0资源共享帖，2规章制度） 依赖注入
	
	/**
	 * 主帖 列表
	 * @return
	 */
	public String listTrainBbs(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		if(null == this.querymap){
			this.querymap = new HashMap();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		Map codeM = new HashMap();
		codeM.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
		req.setAttribute("codeList", this.ptrainCodeService.findPtrainCodeList_Comm(codeM));
		Map param = new HashMap();
		param.put("fatherid", CodeFatherUtil.PTRAIN_BBS);
		param.put("specid",   this.querymap.get("specid"));
		param.put("kind",     this.KIND);
		param.put("anssign",   this.querymap.get("anssign"));
		param.put("showsign", this.querymap.get("showsign"));
		param.put("evasign", this.querymap.get("evasign"));
		param.put("userid", loginBean.getId());
		param.put("fields",   func.Trim(this.fields));
		param.put("keyword",  func.Trim(this.keyword));
		this.dataList = this.webBbsService.findPtrainBbsList(param, func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
		this.setTotal(String.valueOf(param.get("total"))); //总记录数
		return SUCCESS;
	}
	/**
	 *  新增/修改 跳转
	 * @return
	 */
	public String setTrainBbs(){
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			if("2".equals(this.fun)){
				this.ptrainBbsBean = this.webBbsService.findPtrainBbsBeanById(this.ptrainBbsBean.getId());
				objFileInfo(this.ptrainBbsBean.getId(),PtrainConstant.FILE_MODSIGN_BBS); //附件列表
			}
			Map codeM = new HashMap();
			codeM.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			List codeList = this.ptrainCodeService.findPtrainCodeList_Comm(codeM);
			req.setAttribute("codeList", codeList);			
			req.setAttribute("MODSIGN", PtrainConstant.FILE_MODSIGN_BBS);
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");			
		}
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String saveTrainBbs(){
		try {
			if(this.isValidToken()){
				this.ptrainBbsBean.setMainuser(this.getLoginSessionBean().getId());
				this.ptrainBbsBean.setUnitid(this.getLoginSessionBean().getUnitid());
				this.ptrainBbsBean.setFatherid(CodeFatherUtil.PTRAIN_BBS);
				if(!func.IsEmpty(this.ptrainBbsBean.getContent())){
					this.ptrainBbsBean.setContent(Constant.formatClob(this.ptrainBbsBean.getContent()));
				}
				String pkId = this.webBbsService.savePtrainBbs(this.ptrainBbsBean);
				if("1".equals(this.fun)){
					//修改附件表中的recid[根据tokenid修改]
					HttpServletRequest req = ServletActionContext.getRequest();
					String FINAL_RANDOMSTR = func.Trim(req.getParameter("FINAL_RANDOMSTR"));
					this.ptrainFileService.updatePtrainFileByRandomStr(FINAL_RANDOMSTR, pkId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		//关闭窗口，刷新父页面
		String script = "window.parent.querydata(); \n parent.layer.closeAll;";
		this.setActMessage("",script);	
		return Constant.NO_DATA;
	}
	/**
	 * 帖子 信息
	 * @return
	 */
	public String setBbsTopic(){
		HttpServletRequest req = ServletActionContext.getRequest();
		if(!func.IsEmpty(this.ptrainBbsBean.getId())){
			this.webBbsService.updatePtrainBbsByBrowse(this.ptrainBbsBean.getId());
			Map beanM = new HashMap();
			beanM.put("userid", this.getLoginSessionBean().getId());
			beanM.put("id", this.ptrainBbsBean.getId());
			this.ptrainBbsBean = this.webBbsService.findPtrainBbsBeanByMap(beanM);
			objFileInfo(this.ptrainBbsBean.getId(),PtrainConstant.FILE_MODSIGN_BBS); //附件列表
			this.getOpraterInfoIntoRequest();
			if("1".equals(this.ptrainBbsBean.getKind())){
				return "Topic1";
			}else{
				return "Topic2";
			}
		}
		return null;
	}
	/**
	 * 版主人员管理_帖子互动 列表
	 * @return
	 */
	public String listTrainBbsChild(){
		HttpServletRequest req = ServletActionContext.getRequest();
		Map param = new HashMap();
		param.put("fatherid", this.ptrainBbsBean.getId());
		this.dataList = this.webBbsService.findPtrainBbsChildList(param,func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
		this.setTotal(String.valueOf(param.get("total"))); //总记录数
		return SUCCESS;
	}
	/**
	 * 版主回答 跳转
	 * @return
	 */
	public String setTrainAns(){
		if(!func.IsEmpty(this.ptrainBbsBean.getId())){
			Map beanM = new HashMap();
			beanM.put("userid", this.getLoginSessionBean().getId());
			beanM.put("id", this.ptrainBbsBean.getId());
			this.ptrainBbsBean = this.webBbsService.findPtrainBbsBeanByMap(beanM);
		}
		return SUCCESS;
	}
	/**
	 * 弹出调整公共方法Win
	 * @return
	 */
	public String setTrainBbsWin(){
		return SUCCESS;
	}
	/**
	 * 转帖 保存
	 * @return
	 */
	public String saveTrainBbsReprint(){
		try {
			if(this.isValidToken()){
				this.ptrainBbsBean.setMainuser(this.getLoginSessionBean().getId());
				this.webBbsService.savePtrainBbs(this.ptrainBbsBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		//关闭窗口，刷新父页面
		String script = "window.parent.querydata(); \n parent.layer.closeAll;";
		this.setActMessage("",script);	
		return Constant.NO_DATA;
	}
	/**
	 * 更新 对专家解答进行评价
	 * @return
	 */
	public String saveTrainBbsByEvaSign(){
		this.webBbsService.updatePtrainBbsByEvasign(this.ptrainBbsBean);
		//关闭窗口，刷新父页面
		String script = "window.parent.querydata(); \n parent.layer.closeAll;";
		this.setActMessage("",script);	
		return Constant.NO_DATA;
	}
	/**
	 * 删除 主帖
	 * @return
	 */
	public String deleteTrainBbsById(){
		try {
			this.webBbsService.deletePtrainBbsById(this.ptrainBbsBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listTrainBbs();
	}
	/**
	 * 删除 子级记录
	 * @return
	 */
	public String deleteTrainBbsChildById(){
		try {
			this.webBbsService.deletePtrainBbsById(this.querymap.get("pkId"));
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listTrainBbsChild();
	}
	/*******************************************************/
	/******************   Jq方法   *************************/
	/*******************************************************/
	/**
	 * 互动 列表Jq
	 * @return 
	 */
	public String listTrainBbsChildByJq(){
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Map param = new HashMap();
			param.put("fatherid",this.fatherid);
			this.dataList = this.webBbsService.findPtrainBbsChildList(param, func.Cint(req.getParameter("tagpage")), 5);
			this.printList(this.dataList);
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 回复 Jq
	 * @return
	 */
	public String insertPtrainBbsByJq(){
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			this.ptrainBbsBean = new PtrainBbsBean();
			this.ptrainBbsBean.setContent(URLDecoder.decode(req.getParameter("content"),"utf-8"));
			this.ptrainBbsBean.setUnitid(this.getLoginSessionBean().getUnitid());
			this.ptrainBbsBean.setFatherid(req.getParameter("fatherid"));
			this.ptrainBbsBean.setEstauser(this.getLoginSessionBean().getId());
			this.ptrainBbsBean.setMainuser(this.getLoginSessionBean().getId());
			this.webBbsService.insertPtrainBbsByJq(this.ptrainBbsBean);
			this.print("1");			
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 累加下载次数 Jq
	 * @return
	 */
	public String DownloadNumberByJq(){
		try {
			this.webBbsService.updatePtrainBbsByDownnum(this.querymap.get("fileid"));
			this.print("1");
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 点赞 Jq
	 * @return
	 */
	public String onClickNiceByJq(){
		try {
			this.webBbsService.insertPtrainNicelog(this.ptrainBbsBean.getId(), this.getLoginSessionBean().getId());
			this.print("1");
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
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
		req.setAttribute("fileList", this.ptrainFileService.findPtrainFileList(fileMap));
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
	public String getListpage() {
		return listpage;
	}
	public void setListpage(String listpage) {
		this.listpage = listpage;
	}
	public PtrainBbsBean getPtrainBbsBean() {
		return ptrainBbsBean;
	}
	public void setPtrainBbsBean(PtrainBbsBean ptrainBbsBean) {
		this.ptrainBbsBean = ptrainBbsBean;
	}
	
	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}
	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}
	public PtrainFileService getPtrainFileService() {
		return ptrainFileService;
	}
	public void setPtrainFileService(PtrainFileService ptrainFileService) {
		this.ptrainFileService = ptrainFileService;
	}
	public Map<String, String> getQuerymap() {
		return querymap;
	}
	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}
	public String getSAVEPATH() {
		return SAVEPATH;
	}
	public void setSAVEPATH(String savepath) {
		SAVEPATH = savepath;
	}
	public String getSetpage() {
		return setpage;
	}
	public void setSetpage(String setpage) {
		this.setpage = setpage;
	}
	public WebBbsService getWebBbsService() {
		return webBbsService;
	}
	public void setWebBbsService(WebBbsService webBbsService) {
		this.webBbsService = webBbsService;
	}
	
	
	
}
