package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainManagerBean;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainManagerService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_知识版主action层
 * @author 林木山
 * @date 2014-3-10
 */
@SuppressWarnings("all")
public class PtrainManagerAction extends PtrainAction{

	private PtrainManagerService	ptrainManagerService;  	//莆田岗位培训_知识版主业务接口
	private LoginService 			loginService;      		//登录信息业务处理类
	private PtrainCodeService		ptrainCodeService;	//代码列表业务接口
	private PtrainManagerBean		ptrainManagerBean;     	//莆田岗位培训_知识版主信息表
	private Map<String,String>		querymap;				//传参map
	private List					dataList;				//数据列表
	
	public String toForwardManager(){
		return SUCCESS;
	}
	
	/**
	 * 莆田岗位培训_知识版主 列表信息
	 */
	public String listPtrainManager() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
//			Map map = new HashMap();
//			req.setAttribute("unitlist", this.loginService.findOwnUnitListById(loginBean.getUnitid()));
//			map.put("unitid", func.Trim(this.querymap.get("unitid")));
//			String certerUnitid=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(map).getId());
			//数据列表
			Map map = new HashMap();
			map.put("nature", "2");
			map.put("centerunitid", loginBean.getUnitid());
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("usesign", "1");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("sortfield", "c.sortnum,c.id");
			this.dataList=this.ptrainManagerService.findPtrainManagerList(map);
			this.print(JSONArray.fromObject(dataList).toString());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}

	/**
	 * 莆田岗位培训_知识版主 设置 [弹出]
	 */
	public String setPtrainManagerWin() {
		//登录人员信息
		HttpServletRequest req = ServletActionContext.getRequest();
		String useridstr = req.getParameter("useridstr");
		req.setAttribute("useridstr", useridstr);
		return SUCCESS;
	}

	/**
	 * 莆田岗位培训_知识版主 设置[保存]
	 */
	public String savePtrainManager() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainManagerBean.setEstauser(loginBean.getId());
			this.ptrainManagerBean.setMainuser(loginBean.getId());
			this.ptrainManagerService.savePtrainManager(ptrainManagerBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error","window.close();");		
			return Constant.NO_DATA;
		}
		// 关闭窗口时，刷父窗体页面
//		String script = "opener.querydata();\n window.close();";
//		this.setActMessage("operate.success",script);		
		return Constant.NO_DATA;
	}
	
	/**
	 * *****************************JQuery相关******************************
	 */
	/**
	 * 查找莆田岗位培训_知识版主 已有人员 
	 */
	public void findDataUserListHasByJq() {
		try{
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("id", func.Trim(this.querymap.get("userid")));
			map.put("sortfield", "u.sortnum,u.id");
			JSONArray json = JSONArray.fromObject(this.ptrainManagerService.findDataUserListHas(map));
			this.print(json.toString());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}

	/**
	 * 莆田岗位培训_知识版主 删除信息
	 */
	public String deletePtrainManagerByJq() {
		try {
			if(!func.IsEmpty(this.ptrainManagerBean.getId())){
				this.ptrainManagerService.deletePtrainManagerById(this.ptrainManagerBean.getId());
			}
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}

	//Get和Set方法
	public PtrainManagerBean getPtrainManagerBean() {
		return ptrainManagerBean;
	}
	public void setPtrainManagerBean(PtrainManagerBean ptrainManagerBean) {
		this.ptrainManagerBean = ptrainManagerBean;
	}
	public PtrainManagerService getPtrainManagerService() {
		return ptrainManagerService;
	}
	public void setPtrainManagerService(PtrainManagerService ptrainManagerService) {
		this.ptrainManagerService = ptrainManagerService;
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
}
