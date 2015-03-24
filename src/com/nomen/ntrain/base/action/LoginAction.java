package com.nomen.ntrain.base.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.annotation.LoginEnums;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.BaseMenuService;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.common.CommonAction;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.MD5;
import com.opensymphony.xwork2.ActionContext;

/**
 * @description 登录模块  
 * @author lianjinliang
 * @date 2010-11-23
 */

public class LoginAction extends CommonAction{
	private static final Log  LOG = LogFactory.getLog(LoginAction.class);
	private static final long serialVersionUID=200905183423L;
	private LoginService loginService;
	private BaseMenuService baseMenuService;
	private LoginBean loginbean;
	
	/**
	 * 进入登录页面
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String listLogin() { 
		return SUCCESS;
	} 
	
	/**
	 * 验证登录信息
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveLoginIn() {
		Map map=new HashMap();
		if(null == this.loginbean){
			this.loginbean = new LoginBean();
		}
		//将登录条件写入参数集合
		map.put("userid", func.Trim(this.loginbean.getUserid()));   //身份证/工号
		String password = func.Trim(this.loginbean.getPassword());  //密码
		if("1".equals(func.Trim(this.getText("md5pass.sign")))){    //是否加密密码
			password = MD5.EncryptData(password);
		}
		map.put("password", password);
		String userstate = CodeFatherUtil.DATA_USERSTATEFATHER;
		map.put("userstatefather",userstate);                       //过滤出在职在岗的人员（只有该状态的人员才可以登录）
		//判断登录信息
		Map loginSession=this.loginService.findLoginUser(map);
		HttpServletRequest req=ServletActionContext.getRequest();
		if(loginSession!=null) {
			this.loginbean = (LoginBean)loginSession.get(Constant.LOGIN_PARAM);
			ActionContext ctx= ActionContext.getContext();
			Map session= ctx.getSession();
			session.put(Constant.LOGIN_PARAM, this.loginbean);
			//记录操作日志
			this.loginService.insertLoginLog(loginbean, LoginEnums.LOGIN);
			//查询正在启用的大类菜单
			List menuList = this.baseMenuService.findUserBigMenuList(this.loginbean.getId());
			req.setAttribute("menuList",menuList);
			return SUCCESS;
		} else {
			String htmlError = "<script language=\"javascript\">jBox.tip(\""+this.getText("login.error")
			                +"\");setTimeout(function(){window.top.location=\"saveloginout.shtml\"},3000);</script>";
			req.setAttribute("htmlError", htmlError);
			return "login_error";
		}
	}
	public String saveLoginInWeb(){
		HttpServletRequest req=ServletActionContext.getRequest();
		if(null == this.getLoginSessionBean()){
			String htmlError = "<script language=\"javascript\">jBox.tip(\""+this.getText("login.fail")+"\");setTimeout(function(){window.top.location=\"saveloginout.shtml\"},2000);</script>";
			req.setAttribute("htmlError", htmlError);
			return "login_error"; 
		}else{
			//查询正在启用的大类菜单
			List menuList = this.baseMenuService.findUserBigMenuList(this.getLoginSessionBean().getId());
			req.setAttribute("menuList",menuList);
			return SUCCESS;
		}
		
	}
	/**
	 * 注销登录
	 * @return
	 */
	public String saveLoginOut() {
		LoginBean login = this.getLoginSessionBean();
		if(null!=login){
			//记录操作日志
			this.loginService.insertLoginLog(login, LoginEnums.LOGOUT);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();  
		LOG.info("注销成功！");
		return SUCCESS;
	}
	
	
	public String setPassWordByMD5(){
		try
		{
			//将明码转成MD5
			HttpServletRequest req = ServletActionContext.getRequest();
			String password = func.Trim(req.getParameter("password"));
			if("1".equals(func.Trim(this.getText("md5pass.sign")))){
				password = MD5.EncryptData(password);
			}  
			this.print(password);
			return null;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	} 
	

	/**
	 *  查询部门性质列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listDeptNature(){
		try{
			//实例响应类 
			HttpServletRequest req = ServletActionContext.getRequest();
			String unitid = func.Trim(req.getParameter("unitid"));
			String keyword = func.Trim(req.getParameter("keyword"));
			keyword = URLDecoder.decode(keyword,"utf-8");
			Map map = new HashMap();
			map.put("fatherid", CodeFatherUtil.DATA_DEPTNATURE);
			map.put("unitid",   unitid);
			map.put("fields",func.Trim(req.getParameter("fields")));
			map.put("keyword",keyword);
			List naturelist = this.loginService.findDeptNatureList(map);
			JSONArray json = JSONArray.fromObject(naturelist);
			this.print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取部门列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listDept(){
		try{
			//实例响应类 
			HttpServletRequest req = ServletActionContext.getRequest();
			String unitid = func.Trim(req.getParameter("unitid"));
			String keyword = func.Trim(req.getParameter("keyword"));
			keyword = URLDecoder.decode(keyword,"utf-8");
			Map qmap = new HashMap();
			qmap.put("unitid", unitid);
			qmap.put("fields",func.Trim(req.getParameter("fields")));  //当该功能用做查询人员是该关键字不须传递
			qmap.put("keyword",keyword); //当该功能用做查询人员是该关键字不须传递
			JSONArray json = JSONArray.fromObject(this.loginService.findDeptListByMap(qmap));
			this.print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询班组列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listGroup(){
		try{
			//实例响应类
			HttpServletRequest req = ServletActionContext.getRequest();
			String deptid = func.Trim(req.getParameter("deptid")); 
			Map map = new HashMap();
			map.put("fatherid",deptid);
			JSONArray json = JSONArray.fromObject(this.loginService.findGroupListByMap(map));
			this.print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询子班组列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listGroupSub(){
		try{
			//实例响应类 
			HttpServletRequest req = ServletActionContext.getRequest();
			String groupid = func.Trim(req.getParameter("groupid")); 
			Map map = new HashMap();
			map.put("fatherid",groupid);
			JSONArray json = JSONArray.fromObject(this.loginService.findGroupListByMap(map));
			this.print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查询人员信息
	 * @param map：unitid，单位ID；nature，部门性质；deptid，部门ID；
	 * 			  groupid，班组ID；groupid2，自班组ID;fields，关键字段；keyword，关键字内容
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listUnitUsers(){
		try{
			//实例响应类 
			HttpServletRequest req = ServletActionContext.getRequest();
			Map qmap = new HashMap(); 
			qmap.put("unitid",func.Trim(req.getParameter("unitid"))); 
			qmap.put("nature",func.Trim(req.getParameter("nature"))); 
			qmap.put("deptid",func.Trim(req.getParameter("deptid"))); 
			qmap.put("groupid",func.Trim(req.getParameter("groupid"))); 
			qmap.put("groupid2",func.Trim(req.getParameter("groupid2"))); 
			qmap.put("kindid", func.Trim(req.getParameter("kindid")));
			qmap.put("fields",func.Trim(req.getParameter("fields")));  
			String keyword = func.Trim(req.getParameter("keyword"));
			keyword = URLDecoder.decode(keyword,"utf-8");
			qmap.put("keyword",keyword); 

			//人员状态必须为在职的
			String userstate = CodeFatherUtil.DATA_USERSTATEFATHER;
			qmap.put("userstatefather",userstate); 
			List rtnlist = this.loginService.findAllUseUser(qmap);
			JSONArray json = JSONArray.fromObject(rtnlist);
			this.print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取部门详细信息(JQUERY)
	 * @return
	 */
	public String findDataOrganBeanByJq(){
		try{
			//实例响应类 
			HttpServletRequest req = ServletActionContext.getRequest();
			String pkId = func.Trim(req.getParameter("id"));
			JSONArray json = JSONArray.fromObject(this.loginService.findDataOrganById(pkId));
			this.print(json.toString());
		}catch(Exception e){
			this.print(null);
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 左侧菜单
	 * @return
	 */
	public void leftSubMenu(){
		String userId = this.getLoginSessionBean().getId();
		HttpServletRequest req = ServletActionContext.getRequest();
		String fatherid = func.Trim(req.getParameter("fatherid"));
		this.printList(this.baseMenuService.findUserChildMenuTreeList(userId,fatherid));
	}
	
	
	
	/**
	 * 录播管理平台登录验证
	 */
	public String loginRecord(){
		System.out.println("____________________");
		this.print("-1");
		return SUCCESS;
	}
	
	//以下为get和set方法
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public BaseMenuService getBaseMenuService() {
		return baseMenuService;
	}

	public void setBaseMenuService(BaseMenuService baseMenuService) {
		this.baseMenuService = baseMenuService;
	}

	public LoginBean getLoginbean() {
		return loginbean;
	}
	public void setLoginbean(LoginBean loginbean) {
		this.loginbean = loginbean;
	}
}