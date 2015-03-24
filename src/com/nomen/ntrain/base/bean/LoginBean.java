package com.nomen.ntrain.base.bean;

import java.io.Serializable;

/**
 * @description 登录信息表
 * @author 连金亮
 * @date 2010-12-26
 */

public class LoginBean implements Serializable{
	private String id;          //人员主键ID
    private String unitid;      //单位主键ID
    private String unitname;    //单位名称
    private String nature;      //部门性质ID
    private String deptid;      //部门主键ID
    private String deptname;    //部门名称
    private String groupid;     //班组主键ID
    private String groupname;   //班组主键名称
    private String username;    //姓名
    private String postname;    //岗位名称
    private String userid;      //人员身份证号
    private String workid;      //工号
    private String sex;         //性别
    private String kindid;      //人员性质
    private String password;    //密码
    private String strflag;     //预留字段（字符串）
    private String intflag;     //预留字段（整型）
    
    private String roleids;         //人员对应的角色
    private String loginpsd;
    public String getRoleids() {
		return roleids;
	}
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	private String userlogonid;   //记录登录人员登录日志在日志表中的id[辅助]
    private String enterunitid;   //登录系统后要跳转到的单位对应的页面
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getKindid() {
		return kindid;
	}
	public void setKindid(String kindid) {
		this.kindid = kindid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getUserlogonid() {
		return userlogonid;
	}
	public void setUserlogonid(String userlogonid) {
		this.userlogonid = userlogonid;
	}
	public String getEnterunitid() {
		return enterunitid;
	}
	public void setEnterunitid(String enterunitid) {
		this.enterunitid = enterunitid;
	}
	public String getLoginpsd() {
		return loginpsd;
	}
	public void setLoginpsd(String loginpsd) {
		this.loginpsd = loginpsd;
	}
    
    
	
}