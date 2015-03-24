package com.nomen.ntrain.web.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CommonAction;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.MD5;
import com.nomen.ntrain.web.constatn.WebConstant;
import com.opensymphony.xwork2.ActionContext;
/**
 * @description 登录Action  
 * @author 许东雄
 * @date 2014-11-13
 */
public class LoginAction extends CommonAction{
	private static final Log LOG = LogFactory.getLog(LoginAction.class);
	private static final long serialVersionUID=0000000000000000001L;
	private LoginService	loginService;
	private LoginBean 		loginbean;
	
	/**
	 * 登录跳转页面
	 * @return
	 */
	public String indexList(){
		
		return SUCCESS;
	}
	
	/****************************************************/
	/*******************	Jq方法	*********************/
	/****************************************************/
	/**
	 * 登录 Jq
	 * @return
	 */
	public String loginInByJq(){		
		try {
			String userid = func.Trim(this.loginbean.getUserid());
			String password = func.Trim(this.loginbean.getPassword());  //密码
			if("1".equals(func.Trim(this.getText("md5pass.sign")))){    //是否加密密码
				password = MD5.EncryptData(password);
			}
			Map map = new HashMap();
			map.put("userid", userid);
			map.put("password", password);
			Map loginSession=this.loginService.findLoginUser(map);
			if(null != loginSession){
				this.loginbean = (LoginBean)loginSession.get(Constant.LOGIN_PARAM);
				ActionContext ctx= ActionContext.getContext();
				Map session= ctx.getSession();
				session.put(Constant.LOGIN_PARAM, this.loginbean);
				this.printBean(this.loginbean);
			}else{			
				this.print("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 注销 Jq
	 * @return
	 */
	public String loginOutByJq(){
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.invalidate();  
			LOG.info("注销成功！");
			this.print("1");
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 判断是否已登录
	 * @return
	 */
	public String isLoginByJq(){
		LoginBean loginBean = CommonAction.getLoginSessionBean();
		if(null !=loginBean){
			this.print("1");
		}else{
			this.print("-1");
		}
		return null;
	}
	/**
	 * 空方法跳转页面
	 * @return
	 */
	public String setWin(){
		
		return SUCCESS;
	}

	/**
	 * 修改的密码 Jq
	 * @return
	 */
	public String updatePasswordByJq() { 
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Properties prop = new Properties();  
			String pass = req.getParameter("password");
			if("1".equals(func.Trim(this.getText("md5pass.sign")))){
				pass = MD5.EncryptData(pass);
			}
			this.loginbean = new LoginBean();
			this.loginbean.setId(this.getLoginSessionBean().getId());
			this.loginbean.setPassword(pass);
			this.loginService.updatePassword(this.loginbean);
			this.print("1");
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	
	public LoginBean getLoginbean() {
		return loginbean;
	}
	public void setLoginbean(LoginBean loginbean) {
		this.loginbean = loginbean;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	
}