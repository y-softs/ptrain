package com.nomen.ntrain.web.constatn;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.web.action.LoginAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @description 登录过滤器 
 * @author 许东雄
 * @date 2014-11-17
 */

public class CheckInterceptor extends AbstractInterceptor
{
	public static final long serialVersionUID=20140504L;
	public static final String LOGIN_KEY  = com.nomen.ntrain.util.Constant.LOGIN_PARAM;
	public static final String LOGIN_PAGE = "global_login";
	
	public void destroy() {  
		System.out.println("==============================================================");
		System.out.println("=============    Web拦截器加载开始销毁   =======================");
		System.out.println("==============================================================");
	}
	
	public void init() { 
		System.out.println("==============================================================");
		System.out.println("=============    Web拦截器加载开始加载     =====================");
		System.out.println("==============================================================");
	}	
	
	public String intercept(ActionInvocation actionInvocation) throws Exception 
	{
		Object action = actionInvocation.getAction();
		if (action instanceof LoginAction) {
			return actionInvocation.invoke();
		}
		
		//确认Session中是否存在LOGIN
		Map session = actionInvocation.getInvocationContext().getSession();
		LoginBean login = (LoginBean) session.get(LOGIN_KEY);
		if (login != null) {
			return actionInvocation.invoke();
		} else {
			// 否则终止后续操作，返回LOGIN
			//获取request对象
			ActionContext actionContext = actionInvocation.getInvocationContext();
			HttpServletRequest request= (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
			String uri = ServletActionContext.getRequest().getRequestURI();  
			if(uri.startsWith("/")){
				uri = uri.substring(1);
			}
			String[] param = uri.split("/");
			uri = param[0]; 
			String htmlError = "<script language=\"javascript\">layer.msg('<div style=\"color:red;font-size:13px;height:28px;width:230px;\">您登录时间已经过期！请重新登录！</div>' ,2,3);setTimeout(function(){window.top.location=\"/"+uri+"/index.shtml\"},2000)</script>";
			request.setAttribute("htmlError", htmlError);
			return LOGIN_PAGE;
		}
	}
	
}