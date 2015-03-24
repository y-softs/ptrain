package com.nomen.ntrain.data.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.data.bean.DataOrganZBean;
import com.nomen.ntrain.data.bean.DataUserZBean;
import com.nomen.ntrain.data.service.DataUserZService;
import com.nomen.ntrain.ptrain.action.PtrainAction;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.MD5;
import com.nomen.ntrain.util.PubFunc;

/**
 * @description 人员基本信息  
 * @author 钱新红
 * @date 2009-09-30
 * @modifier tian_chen-丁新良 
 * @date 2010-05-21、2010-12-02
 */
@SuppressWarnings("all")
public class DataUserZAction extends DataAction {
	private static final long 		serialVersionUID = -1365767446985650627L;
	private DataUserZService 		dataUserZService;  				//人员基本信息业务逻辑处理类
	private LoginService 			loginService;        			//登录信息业务逻辑处理
	private DataUserZBean           dataUserZBean;                  //人员基本信息
	private Map<String,String>  	querymap;						//查询条件
	private List                    dataList;                       //结果集合
	private PubFunc func = new PubFunc();
	
	/************************************************************************/
	/*************************  更新维护相关  *********************************/
	/************************************************************************/
	
	/**
	 * 跳转到人员列表页面
	 */
	public String toForwardUserList(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
			//this.querymap.put("unitid", loginBean.getUnitid());
			this.querymap.put("unitid", loginBean.getUnitid());
			//this.querymap.put("fatherid", CodeFatherUtil.DATA_DEPTNATURE);
			this.querymap.put("fatherid", "0");
			this.sortfield="sortnum";
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到人员新增,修改列表页面
	 * @return
	 */
	public String setDataUserZBean(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//获取登录信息
		LoginBean loginBean = this.getLoginSessionBean();
		String id = this.dataUserZBean.getId();
		String unitid  =func.Trim(this.querymap.get("unitid"));
		if(func.IsEmpty(id)){
			this.dataUserZBean = new DataUserZBean();
			this.dataUserZBean.setUnitid(unitid);
			this.dataUserZBean.setStateid(CodeFatherUtil.DATA_USERSTATEFATHER);   //在岗
			this.dataUserZBean.setStatename("在岗");
		}else{
			this.dataUserZBean = this.dataUserZService.findDataUserZBeanById(id);
		}
		//设置代码查询参数
		Map<String,String> param = new HashMap<String,String>();
		param.put("unitid", this.querymap.get("unitid"));
		//设置获取性别代码标志
		param.put("fatherid", CodeFatherUtil.DATA_SEX);
		req.setAttribute("sexlist", this.dataUserZService.findDataCodeList(param));
		return SUCCESS;
	}
	
	/**
	 * 保存人员新增,修改列表页面
	 * @return
	 */
	public String saveDataUserZBean(){
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			if(this.isValidToken()) {
				//获取登录信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.dataUserZBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.dataUserZBean.getId()))){
					this.dataUserZBean.setEstauser(loginBean.getId());
					String password = func.Trim(this.dataUserZBean.getLoginpsd());
					if(func.IsEmpty(password)){
						password = "a_123456";
					}
					if("1".equals(func.Trim(this.getText("md5pass.sign")))){
						password = MD5.EncryptData(password);
					}
					this.dataUserZBean.setLoginpsd(password);
				}
				Map map = new HashMap();
				map.put("dataUserZBean", dataUserZBean);
				map.put("userid", loginBean.getId());
				this.dataUserZService.saveDataUserZBean(map);
			}
			this.reloadParentPage();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return Constant.NO_DATA;
	}
	
	/****************************************************************************************/
	/***********************************JQUERY 方法*******************************************/
	/****************************************************************************************/
	
	public String findDataUserZListByJq(){
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			String deptid = func.Trim(this.querymap.get("deptid"));
			String unitid = func.Trim(this.querymap.get("unitid"));
			Map map = new HashMap();
			//数据列表
			map.put("unitid", unitid);
			map.put("deptid", deptid);
			map.put("sortfield",func.Trim(this.sortfield));
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			List userList = this.dataUserZService.findDataUserZList(map, func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
			this.print(this.creItemListPage(userList,String.valueOf(map.get("total"))));
		} catch (RuntimeException e) {
			this.print("-1");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除人员信息
	 *
	 */
	public void delDataUserZBeanIdStrByJq(){
		try {
			String idstr = func.Trim(this.querymap.get("idstr"));
			this.dataUserZService.deleteDataUserZBeanByIdStr(idstr);
			this.print("1");
		} catch (RuntimeException e) {
			this.print("-1");
			e.printStackTrace();
		}
	}
	
	//以下为get和set方法
	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public DataUserZService getDataUserZService() {
		return dataUserZService;
	}

	public void setDataUserZService(DataUserZService dataUserZService) {
		this.dataUserZService = dataUserZService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public Map<String, String> getQuerymap() {
		return querymap;
	}

	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}

	public DataUserZBean getDataUserZBean() {
		return dataUserZBean;
	}

	public void setDataUserZBean(DataUserZBean dataUserZBean) {
		this.dataUserZBean = dataUserZBean;
	}
	
}
