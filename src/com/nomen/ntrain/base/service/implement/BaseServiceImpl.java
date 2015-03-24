package com.nomen.ntrain.base.service.implement;
import com.nomen.ntrain.base.bean.BaseOptLogBean;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.dao.BaseDAO;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.PubFunc;
import com.opensymphony.xwork2.ActionContext;

public abstract class BaseServiceImpl{  
	private BaseDAO baseDAO; 
	protected PubFunc func = new PubFunc();
 
	public LoginBean getLoginSession(){
		return (LoginBean)ActionContext.getContext().getSession().get(Constant.LOGIN_PARAM);
		//以下方法可能会有问题，所以不敢启用[待确定可用时，直接替换成上面的方法]
		//return commonContext.getLoginSession();
	}
	
	public void consolePrint(String msg){
		System.err.println(msg);
	}
	//set get 
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
}
