package com.nomen.ntrain.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.common.CommonAction;
import com.nomen.ntrain.util.Constant;
import com.opensymphony.xwork2.ActionContext;

public class WebAction extends CommonAction {
	private static final long serialVersionUID = 00000000000000000000002;
	protected String fun;                         //操作标志：新增（1），修改（2）
	protected String fields;                      //关键字字段名称
	protected String nature;        			  //性质（1 共性 2 个性）	
	protected String fatherid;   				  //父级类别ID
	protected String keyword;                     //关键字内容
	protected String sortfield;                   //排序的字段名

	/**
	 * 维护人、所属部门、维护时间
	 */
	protected void getOpraterInfoIntoRequest(){
		LoginBean login=(LoginBean)ActionContext.getContext().getSession().get(Constant.LOGIN_PARAM);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("opraName", login.getUsername());
		request.setAttribute("opraDeptName", login.getDeptname());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		request.setAttribute("opraTime", format.format(new Date()));
		request.setAttribute("opraId",login.getId());
		
		System.out.println(format.format(new Date()));
	}

	//以下为get和set方法
	public String getFatherid() {
		return fatherid;
	}

	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getFun() {
		return fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getSortfield() {
		return sortfield;
	}

	public void setSortfield(String sortfield) {
		this.sortfield = sortfield;
	}	
	
}