package com.nomen.ntrain.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqReqtypeEnum;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.util.CheckSignConstant;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.web.service.WebRequserService;
import com.nomen.ntrain.util.CheckRemarkEnum;


/**
 * @description 莆田岗位培训_培训需求报名action层
 * @author 林木山
 * @date 2014-3-21
 */
@SuppressWarnings("all")
public class WebRequserAction extends WebAction{
	private WebRequserService		webRequserService;  	//莆田岗位培训_培训需求报名业务接口
	private PtrainCodeService		ptrainCodeService;		//代码列表业务接口
	private PtrainFileService		ptrainFileService;
	private LoginService 			loginService;      		//登录信息业务处理类
	private PtrainRequserBean		ptrainRequserBean;     	//莆田岗位培训_培训需求报名信息表
	private PtrainReqBean			ptrainReqBean;     	//莆田岗位培训_培训需求报名信息表
	private Map<String,String>		querymap;				//传参map
	private List					dataList;				//数据列表
	
	/**
	 * 员工加菜
	 * @return
	 */
	public String listPtrainReq(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		//初始化
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
		}
		Map map = new HashMap();
		map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
		req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList_Comm(map));
		map.put("specid", this.querymap.get("specid"));
		map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
		map.put("fields", this.fields);
		map.put("keyword", this.keyword);
		map.put("reqtype", PtrainReqReqtypeEnum.REQ_USER.getKey());
		map.put("requserid",this.getLoginSessionBean().getId());
		this.dataList=this.webRequserService.findPtrainReqList(map, func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
		this.setTotal(String.valueOf(map.get("total")));			
		req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
		req.setAttribute("stateList", CheckRemarkEnum.values());
		req.setAttribute("XQSB", PtrainReqFlowStaEnum.XQSB.getKey()+"");
		req.setAttribute("XQSH", PtrainReqFlowStaEnum.XQSH.getKey()+"");
		req.setAttribute("SHEN_BAO", CheckSignConstant.SHEN_BAO);
		req.setAttribute("CHE_HUI", CheckSignConstant.CHE_HUI);
		req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINREQ.getKey()+"");
		return SUCCESS;
	}
	public String setPtrainReq(){

		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Map map = new HashMap();
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList_Comm(map));
			if(func.IsEmpty(this.ptrainReqBean.getId())){
				
			}else{
				this.ptrainReqBean = this.webRequserService.findPtrainReqBeanById(this.ptrainReqBean.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 	SUCCESS;
	}
	public String savePtrainReq(){
		try {
			if(this.isValidToken()){
				if(func.IsEmpty(this.ptrainReqBean.getId())){
					this.ptrainReqBean.setFlowsta(PtrainReqFlowStaEnum.XQSB.getKey()+"");
					this.ptrainReqBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
					this.ptrainReqBean.setReqtype(PtrainReqReqtypeEnum.REQ_USER.getKey()+"");
					this.ptrainReqBean.setRequserid(this.getLoginSessionBean().getId());
				}
				this.webRequserService.savePtrainReq(this.ptrainReqBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		//关闭窗口，刷新父页面
		String script = "window.parent.querydata(); \n parent.layer.closeAll;";
		this.setActMessage("",script);	
		return Constant.NO_DATA;
	}
	/**
	 * 员工加菜 申报/撤回
	 * 菜单审批 同意(不同意)/退回
	 */
	public String updatePtrainReqChk() {
		String chksign=func.Trim(this.querymap.get("chksign"));
		try {
			if(func.IsEmpty(chksign))return SUCCESS;
			
			Map map=new HashMap();
			this.ptrainReqBean.setMainuser(this.getLoginSessionBean().getId());
			map.put("ptrainReqBean", this.ptrainReqBean);
			map.put("chksign",chksign);
			map.put("chkmemo",func.Trim(this.querymap.get("chkmemo")));
			this.webRequserService.updatePtrainReqChk(map);			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return this.listPtrainReq();
	}
	/**
	 * @description列表信息
	 * 查询统计_自助需求情况 列表信息
	 */
	public String listPtrainRequser() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			boolean firstCome=false;
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
				firstCome=true;
			}
			Map map = new HashMap();
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList_Comm(map));
			//需求类别
			req.setAttribute("reqtypeList", PtrainReqReqtypeEnum.values());
			String state="",loginuserid="";
			if(firstCome)this.querymap.put("showsign", "1");
			if(!func.IsEmpty(func.Trim(this.querymap.get("showsign"))))state="0";
			loginuserid=loginBean.getId();
			this.sortfield="r.estatime desc,r.id desc";
			//数据列表
			map = new HashMap();
			map.put("specid", func.Trim(this.querymap.get("specid")));
			map.put("flowstagath", PtrainReqFlowStaEnum.GD.getKey()+"");
			map.put("reqtypegath", PtrainReqReqtypeEnum.REQ_EXP.getKey()+","+PtrainReqReqtypeEnum.REQ_USER.getKey());
			map.put("state", state);
			map.put("loginuserid", loginuserid);
			map.put("sortfield",this.sortfield);
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.webRequserService.findPtrainRequserList(map);
			this.setTotal(String.valueOf(map.get("total")));			
			req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}
	/**
	 * @description详细页面 弹出
	 */
	public String setPtrainReqWin() {
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Map map = new HashMap();
			//修改
			map.put("id", this.ptrainReqBean.getId());
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			this.ptrainReqBean = this.webRequserService.findPtrainReqBeanByMap(map);
			//附件列表
			map = new HashMap();
			map.put("recid", this.ptrainReqBean.getId());
			map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN );
			req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
			//培训类别
			req.setAttribute("REQ_COM", PtrainReqReqtypeEnum.REQ_COM.getKey()+"");
			req.setAttribute("REQ_EXP", PtrainReqReqtypeEnum.REQ_EXP.getKey()+"");
			req.setAttribute("REQ_COUR", PtrainReqReqtypeEnum.REQ_COUR.getKey()+"");
			req.setAttribute("REQ_USER", PtrainReqReqtypeEnum.REQ_USER.getKey()+"");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	/**
	 * @description列表信息 已报名人员列表
	 * 查询统计_自助需求情况 已报名人员列表
	 */
	public String listPtrainRequserSign() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			List naturelist=null,deptlist=null,grouplist=null,supgrouplist=null;
			Map map = new HashMap();
			//部门列表
			map.put("nature", func.Trim(this.querymap.get("nature")));
			deptlist = this.loginService.findDeptListByMap(map);
			if(!func.IsEmpty(func.Trim(this.querymap.get("deptid")))){
				//班组列表
				map = new HashMap();
				map.put("fatherid", func.Trim(this.querymap.get("deptid")));
				grouplist = this.loginService.findGroupListByMap(map);
			}
			if(!func.IsEmpty(func.Trim(this.querymap.get("groupid")))){
				//子班组列表
				map = new HashMap();
				map.put("fatherid", func.Trim(this.querymap.get("groupid")));
				supgrouplist = this.loginService.findGroupListByMap(map);
			}
			req.setAttribute("naturelist", naturelist);
			req.setAttribute("deptlist", deptlist);
			req.setAttribute("grouplist", grouplist);
			req.setAttribute("supgrouplist", supgrouplist);
			//数据列表
			map = new HashMap();
			map.put("reqid", func.Trim(this.querymap.get("reqid")));
			if(func.IsEmpty(func.Trim(this.querymap.get("subgroupid")))){
				map.put("deptid", this.querymap.get("deptid"));
				map.put("groupid", this.querymap.get("groupid"));
			}
			map.put("subgroupid", this.querymap.get("subgroupid"));
			map.put("sortfield","u.deptid,u.groupid,u.sortnum,u.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.webRequserService.findPtrainRequserSignList(map);
			this.setTotal(String.valueOf(map.get("total")));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}
	/**
	 * @description报名[保存]
	 */
	public String savePtrainRequser() {
		try {
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainRequserBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainRequserBean.getId()))){
					this.ptrainRequserBean.setUserid(loginBean.getId());
					this.ptrainRequserBean.setEstauser(loginBean.getId());
				}
				this.webRequserService.savePtrainRequser(this.ptrainRequserBean);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		if(!func.IsEmpty(func.Trim(this.querymap.get("fromwin")))){
			String script = "window.parent.querydata(); \n parent.layer.closeAll;";
			this.setActMessage("",script);	
			return Constant.NO_DATA;
		}
		return this.listPtrainRequser();
	}
	/**
	 * @description删除信息
	 */
	public String deletePtrainRequser() {
		try {
			this.webRequserService.deletePtrainRequserById(this.ptrainRequserBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listPtrainRequser();
	}
	/**
	 * @description删除信息
	 */
	public String deletePtrainReq() {
		try {
			this.webRequserService.deletePtrainReqById(this.ptrainReqBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listPtrainReq();
	}
	
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public Map<String, String> getQuerymap() {
		return querymap;
	}
	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}
	public WebRequserService getWebRequserService() {
		return webRequserService;
	}
	public void setWebRequserService(WebRequserService webRequserService) {
		this.webRequserService = webRequserService;
	}



	
	

	public PtrainRequserBean getPtrainRequserBean() {
		return ptrainRequserBean;
	}
	public void setPtrainRequserBean(PtrainRequserBean ptrainRequserBean) {
		this.ptrainRequserBean = ptrainRequserBean;
	}
	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}

	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public PtrainReqBean getPtrainReqBean() {
		return ptrainReqBean;
	}
	public void setPtrainReqBean(PtrainReqBean ptrainReqBean) {
		this.ptrainReqBean = ptrainReqBean;
	}
	public PtrainFileService getPtrainFileService() {
		return ptrainFileService;
	}
	public void setPtrainFileService(PtrainFileService ptrainFileService) {
		this.ptrainFileService = ptrainFileService;
	}
	
	
}
