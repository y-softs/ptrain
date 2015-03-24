package com.nomen.ntrain.base.bean;

import java.io.Serializable;


/**
 * 莆田岗位培训_权限设置
 * @author 郑学仕
 * @date 
 */
public class BaseRoleUserBean implements Serializable{
	private String userid;		//身份证
	private String roleids;		//角色[A,B,C角色]
	private String mainuser;    //维护人
	
	//新增
	private String unitid;
	private String organname;
	private String username;
	private String mainusername;
	private String rolename;
	
	
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getRoleids() {
		return roleids;
	}
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public String getMainusername() {
		return mainusername;
	}
	public void setMainusername(String mainusername) {
		this.mainusername = mainusername;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
