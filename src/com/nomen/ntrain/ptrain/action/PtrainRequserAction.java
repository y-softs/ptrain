package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.enums.PtrainReqFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqReqtypeEnum;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainReqService;
import com.nomen.ntrain.ptrain.service.PtrainRequserService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_培训需求报名action层
 * @author 林木山
 * @date 2014-3-21
 */
@SuppressWarnings("all")
public class PtrainRequserAction extends PtrainAction{

	private PtrainRequserService	ptrainRequserService;  	//莆田岗位培训_培训需求报名业务接口
	private PtrainReqService		ptrainReqService;  		//莆田岗位培训_培训点菜业务接口
	private PtrainCodeService		ptrainCodeService;		//代码列表业务接口
	private LoginService 			loginService;      		//登录信息业务处理类
	private PtrainRequserBean		ptrainRequserBean;     	//莆田岗位培训_培训需求报名信息表
	private Map<String,String>		querymap;				//传参map
	private List					dataList;				//数据列表
	//=====================辅助========================
	private String					sign;					//1 培训需求_菜单维护_点菜 2 查询统计_自助需求情况
	private String savePath; 					  			//保存文件的目录路径(通过依赖注入)
	
	public String toForwardQueryRequser(){
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
			/**
			 //专业类别
			Map map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("usesign", "1");
			req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
			 */
			
			//需求类别
			req.setAttribute("reqtypeList", PtrainReqReqtypeEnum.values());
			String state="",loginuserid="";
			if("1".equals(this.sign)){
				if(firstCome)this.querymap.put("showsign", "1");
				if(!func.IsEmpty(func.Trim(this.querymap.get("showsign"))))state="0";
				loginuserid=loginBean.getId();
				this.sortfield="r.estatime desc,r.id desc";
			}else if("2".equals(this.sign)){
				if(!func.IsEmpty(func.Trim(this.querymap.get("showsign"))))state="1";
				this.sortfield="intflag desc,r.id desc";
			}
			req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
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
			String state="",loginuserid="";
			if("1".equals(this.sign)){
				if(firstCome)this.querymap.put("showsign", "1");
				if(!func.IsEmpty(func.Trim(this.querymap.get("showsign"))))state="0";
				loginuserid=loginBean.getId();
				this.sortfield="r.estatime desc,r.id desc";
			}else if("2".equals(this.sign)){
				if(!func.IsEmpty(func.Trim(this.querymap.get("showsign"))))state="1";
				this.sortfield="intflag desc,r.id desc";
			}
			//数据列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
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
			this.dataList=this.ptrainRequserService.findPtrainRequserList(map);
			this.setTotal(String.valueOf(map.get("total")));
			this.print(this.creItemListPage(dataList, String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public String toForwardQueryReqSign(){
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			/**
			 List naturelist=null,deptlist=null;
			Map map = new HashMap();
			//部门列表
			map.put("unitid", this.querymap.get("unitid"));
			map.put("nature", func.Trim(this.querymap.get("nature")));
			deptlist = this.loginService.findDeptListByMap(map);
			req.setAttribute("naturelist", naturelist);
			req.setAttribute("deptlist", deptlist);
			 */
		} catch(Exception ex) {
			ex.printStackTrace();
		}
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
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
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
			this.dataList=this.ptrainRequserService.findPtrainRequserSignList(map);
			this.setTotal(String.valueOf(map.get("total")));
			this.print(this.creItemListPage(dataList, String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
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
				this.ptrainRequserService.savePtrainRequser(this.ptrainRequserBean);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			if(func.IsEmpty(func.Trim(this.querymap.get("fromwin")))){
				this.setActMessage("operate.error");
			}else{
				this.setActMessage("operate.success","window.close();");
				return Constant.NO_DATA;
			}
		}
		if(!func.IsEmpty(func.Trim(this.querymap.get("fromwin")))){
			this.setActMessage("operate.success","opener.querydata();\n window.close();");
			return Constant.NO_DATA;
		}
		return this.listPtrainRequser();
	}

	/**
	 * @description删除信息
	 */
	public String deletePtrainRequser() {
		try {
			this.ptrainRequserService.deletePtrainRequserById(this.ptrainRequserBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listPtrainRequser();
	}
	
	/**
	 * 查询统计_自助需求情况 Excel导出
	 */
	public String savePtrainRequserExpExcel() {
		try{
			String state="";
			if(!func.IsEmpty(func.Trim(this.querymap.get("showsign"))))state="1";
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("specid", func.Trim(this.querymap.get("specid")));
			map.put("flowstagath", PtrainReqFlowStaEnum.GD.getKey()+"");
			map.put("reqtypegath", PtrainReqReqtypeEnum.REQ_EXP.getKey()+","+PtrainReqReqtypeEnum.REQ_USER.getKey());
			map.put("state", state);
			map.put("sortfield","intflag desc,r.id desc");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			this.ptrainRequserService.savePtrainRequserExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	
	/**
	 * 查询统计_自助需求情况 已报名人员 Excel导出
	 */
	public String savePtrainRequserSignExpExcel() {
		try{
			String reqid=func.Trim(this.querymap.get("reqid"));
			if(func.IsEmpty(reqid))return null;
			//项目信息
			PtrainReqBean ptrainReqBean=this.ptrainReqService.findPtrainReqBeanById(reqid);
			Map map = new HashMap();
			//专业类别
			map.put("id", func.Trim(ptrainReqBean.getSpecid()));
			PtrainCodeBean ptrainCodeBean=this.ptrainCodeService.findPtrainCodeBeanByMap(map);
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("reqid", func.Trim(this.querymap.get("reqid")));
			if(func.IsEmpty(func.Trim(this.querymap.get("subgroupid")))){
				map.put("deptid", this.querymap.get("deptid"));
				map.put("groupid", this.querymap.get("groupid"));
			}
			map.put("subgroupid", this.querymap.get("subgroupid"));
			map.put("sortfield","u.deptid,u.groupid,u.sortnum,u.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("itemname",ptrainReqBean.getItemname());
			map.put("specname",ptrainCodeBean.getCodename());
			this.ptrainRequserService.savePtrainRequserSignExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}

	//Get和Set方法
	public PtrainRequserBean getPtrainRequserBean() {
		return ptrainRequserBean;
	}
	public void setPtrainRequserBean(PtrainRequserBean ptrainRequserBean) {
		this.ptrainRequserBean = ptrainRequserBean;
	}
	public PtrainRequserService getPtrainRequserService() {
		return ptrainRequserService;
	}
	public void setPtrainRequserService(PtrainRequserService ptrainRequserService) {
		this.ptrainRequserService = ptrainRequserService;
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

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}

	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public PtrainReqService getPtrainReqService() {
		return ptrainReqService;
	}

	public void setPtrainReqService(PtrainReqService ptrainReqService) {
		this.ptrainReqService = ptrainReqService;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
