package com.nomen.ntrain.ptrain.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.service.PtrainBbsService;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_知识学习 action层
 * @author 许东雄
 * @date 2014-3-24
 */
@SuppressWarnings("all")
public class PtrainBbsAction extends PtrainAction{

	private 	PtrainBbsService	ptrainBbsService;	//代码列表业务接口
	private 	PtrainCodeService	ptrainCodeService;
	private 	PtrainFileService	ptrainFileService;
	private 	LoginService 		loginService;      	//登录信息业务处理类
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
		codeM.put("unitid", this.querymap.get("unitid"));
		codeM.put("nature", this.nature);
		codeM.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
		codeM.put("usesign", "1");
		List codeList = this.ptrainCodeService.findPtrainCodeList_Comm(codeM);
		req.setAttribute("codeList", codeList);
		return SUCCESS;
	}
	
	/**
	 * 主帖 列表
	 * @return
	 */
	public String listTrainBbsByJq(){
		try{
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			if(null == this.querymap){
				this.querymap = new HashMap();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			Map param = new HashMap();
			param.put("fatherid", CodeFatherUtil.PTRAIN_BBS);
			param.put("unitid",   this.querymap.get("unitid"));
			param.put("specid",   this.querymap.get("specid"));
			param.put("kind",     this.KIND);
			param.put("anssign",   this.querymap.get("anssign"));
			param.put("showsign", this.querymap.get("showsign"));
			param.put("evasign", this.querymap.get("evasign"));
			param.put("userid", loginBean.getId());
			param.put("fields",   func.Trim(this.fields));
			param.put("keyword",  func.Trim(this.keyword));
			this.dataList = this.ptrainBbsService.findPtrainBbsList(param, func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
			this.print(this.creItemListPage(this.dataList, String.valueOf(param.get("total"))));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 帖子 信息
	 * @return
	 */
	public String setBbsTopic(){
		HttpServletRequest req = ServletActionContext.getRequest();
		if(!func.IsEmpty(this.ptrainBbsBean.getId())){
			this.ptrainBbsService.updatePtrainBbsByBrowse(this.ptrainBbsBean.getId());
			Map beanM = new HashMap();
			beanM.put("userid", this.getLoginSessionBean().getId());
			beanM.put("id", this.ptrainBbsBean.getId());
			this.ptrainBbsBean = this.ptrainBbsService.findPtrainBbsBeanByMap(beanM);
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
		this.dataList = this.ptrainBbsService.findPtrainBbsChildList(param,func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
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
				this.ptrainBbsBean = this.ptrainBbsService.findPtrainBbsBeanById(this.ptrainBbsBean.getId());
				objFileInfo(this.ptrainBbsBean.getId(),PtrainConstant.FILE_MODSIGN_BBS); //附件列表
			}
			Map codeM = new HashMap();
			codeM.put("unitid", this.querymap.get("unitid"));
//			String fId=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(codeM).getId()); //获取地市级单位ID
//			codeM.put("unitid", fId);
			codeM.put("nature", this.nature);
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
	 * 版主回答 跳转
	 * @return
	 */
	public String setTrainAns(){
		if(!func.IsEmpty(this.ptrainBbsBean.getId())){
			Map beanM = new HashMap();
			beanM.put("userid", this.getLoginSessionBean().getId());
			beanM.put("id", this.ptrainBbsBean.getId());
			this.ptrainBbsBean = this.ptrainBbsService.findPtrainBbsBeanByMap(beanM);
		}
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
				this.ptrainBbsBean.setFatherid(CodeFatherUtil.PTRAIN_BBS);
				String pkId = this.ptrainBbsService.savePtrainBbs(this.ptrainBbsBean);
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
//		//关闭当前页面，并刷新父页面标志
//		if("1".equals(this.querymap.get("sign"))){
//			String script = "opener.querydata();\n window.close();";
//			this.setActMessage("operate.success",script);
//			return Constant.NO_DATA;
//		}else{
//			return this.listTrainBbs();
//		}
		this.reloadParentPage();
		return Constant.NO_DATA;
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
				this.ptrainBbsService.savePtrainBbs(this.ptrainBbsBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		String script = "opener.querydata();\n window.close();";
		this.setActMessage("operate.success",script);
		return Constant.NO_DATA;
	}
	/**
	 * 更新 对专家解答进行评价
	 * @return
	 */
	public String saveTrainBbsByEvaSign(){
		this.ptrainBbsService.updatePtrainBbsByEvasign(this.ptrainBbsBean);
		String script = "opener.querydata();\n window.close();";
		this.setActMessage("operate.success",script);
		return Constant.NO_DATA;
	}
	/**
	 * 删除 主帖
	 * @return
	 */
	public String deleteTrainBbsById(){
		try {
			this.ptrainBbsService.deletePtrainBbsById(this.ptrainBbsBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listTrainBbs();
	}
	/**
	 * 删除 主帖
	 * @return
	 */
	public String deleteTrainBbsByJq(){
		try {
			this.ptrainBbsService.deletePtrainBbsById(this.ptrainBbsBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * 删除 子级记录
	 * @return
	 */
	public String deleteTrainBbsChildById(){
		try {
			this.ptrainBbsService.deletePtrainBbsById(this.querymap.get("pkId"));
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listTrainBbsChild();
	}

	/*************************************************************/
	/************************     查询统计     *********************/
	/*************************************************************/
	
	/**
	 * 查询统计 >> 知识学习情况 [跳转]
	 * @return
	 */
	public String toForwardQueryKnow(){
		return SUCCESS;
	}
	
	/**
	 * 查询统计 >> 知识学习情况 >> 应用统计[]
	 * @return
	 */
	public String listPtrainAppStati(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		if(null == this.querymap){
			this.querymap = new HashMap();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		Map map = new HashMap();
		req.setAttribute("unitlist", this.loginService.findOwnUnitListById(loginBean.getUnitid()));
		Map param = new HashMap();
		param.put("unitid", this.querymap.get("unitid"));
		String fId=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(param).getId()); //获取地市级单位ID
		param.put("unitid", fId);
		param.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
		this.dataList = this.ptrainBbsService.findPtrainAppStatiList(param);
		this.print(JSONArray.fromObject(dataList).toString());
		return null;
	}
	/**
	 * 查询统计 >> 知识学习情况 >> 应用统计 Excel导出
	 */
	public String savePtrainBbsExpExcel() {
		try{
			Map map = new HashMap();
			map.put("unitid", this.querymap.get("unitid"));
			String fId=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(map).getId()); //获取地市级单位ID
			map.put("unitid", fId);
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			this.ptrainBbsService.savePtrainBbsExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * 查询统计 >> 知识学习情况 >> 最佳知识
	 * @return
	 */
	public String listPtrainRank(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		if(null == this.querymap){
			this.querymap = new HashMap();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		Map map = new HashMap();
		req.setAttribute("unitlist", this.loginService.findOwnUnitListById(loginBean.getUnitid()));
//		map.put("unitid", this.querymap.get("unitid"));
//		String fId=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(map).getId()); //获取地市级单位ID
//		this.querymap.put("unitid", fId);
		this.dataList = this.ptrainBbsService.findPtrainRankList(null);
		this.print(JSONArray.fromObject(dataList).toString());
		return null;
	}
	
	/**
	 * 查询统计 >> 知识学习情况 >> 专业版主
	 * @return
	 */
	public String listPtrainMode(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		if(null == this.querymap){
			this.querymap = new HashMap();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		Map map = new HashMap();
		req.setAttribute("unitlist", this.loginService.findOwnUnitListById(loginBean.getUnitid()));
		Map param = new HashMap();
		param.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
		this.dataList = this.ptrainBbsService.findPtrainModeList(param);
		return SUCCESS;
	}
	/**
	 * 查询统计 >> 知识学习情况 >> 最佳知识 Excel导出
	 */
	public String savePtrainBbsRankExpExcel() {
		try{
//			Map map = new HashMap();
//			map.put("unitid",this.querymap.get("unitid"));
//			String fId=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(map).getId()); //获取地市级单位ID
//			this.querymap.put("unitid", fId);
			this.ptrainBbsService.savePtrainBbsRankExpExcel(null, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * 查询统计 >> 知识学习情况 >> 最佳知识 Excel导出
	 */
	public String savePtrainBbsModeExpExcel() {
		try{
			Map param = new HashMap();
			param.put("fatherid",CodeFatherUtil.PTRAIN_SPEC);
			this.ptrainBbsService.savePtrainBbsModeExpExcel(param, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
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
			this.dataList = this.ptrainBbsService.findPtrainBbsChildList(param, func.Cint(req.getParameter("tagpage")), 5);
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
			this.ptrainBbsService.insertPtrainBbsByJq(this.ptrainBbsBean);
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
			this.ptrainBbsService.updatePtrainBbsByDownnum(this.ptrainBbsBean.getId());
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
			this.ptrainBbsService.insertPtrainNicelog(this.ptrainBbsBean.getId(), this.getLoginSessionBean().getId());
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

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public PtrainBbsBean getPtrainBbsBean() {
		return ptrainBbsBean;
	}

	public void setPtrainBbsBean(PtrainBbsBean ptrainBbsBean) {
		this.ptrainBbsBean = ptrainBbsBean;
	}

	public PtrainBbsService getPtrainBbsService() {
		return ptrainBbsService;
	}

	public void setPtrainBbsService(PtrainBbsService ptrainBbsService) {
		this.ptrainBbsService = ptrainBbsService;
	}

	public Map<String, String> getQuerymap() {
		return querymap;
	}

	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}
	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}
	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}
	public String getSetpage() {
		return setpage;
	}
	public void setSetpage(String setpage) {
		this.setpage = setpage;
	}
	public String getSAVEPATH() {
		return SAVEPATH;
	}
	public void setSAVEPATH(String savepath) {
		SAVEPATH = savepath;
	}
	public PtrainFileService getPtrainFileService() {
		return ptrainFileService;
	}
	public void setPtrainFileService(PtrainFileService ptrainFileService) {
		this.ptrainFileService = ptrainFileService;
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
	
	
}
