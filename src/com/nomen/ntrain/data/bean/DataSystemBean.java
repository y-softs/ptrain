package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员系统应用信息表POJO 
 * @author 钱新红
 * @date 2009-10-06
 * @modifier 陈高添
 * @date 2010-05-08
 */

public class DataSystemBean implements Serializable{
	private String postuser;    //人员ID
	private String postid;      //对应岗位ID
	private String appsign;     //应用类型
    private String estauser;	//创建人
    private String estatime;	//创建时间
    private String mainuser;	//维护人
    private String mainDeptName;//维护人所在部门
    private String maintime;	//维护时间
    private String strflag;     //预留字段（字符串）
    private String intflag;     //预留字段（整型）
    
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getMainDeptName() {
		return mainDeptName;
	}
	public void setMainDeptName(String mainDeptName) {
		this.mainDeptName = mainDeptName;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
	}
	public String getAppsign() {
		return appsign;
	}
	public void setAppsign(String appsign) {
		this.appsign = appsign;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getPostuser() {
		return postuser;
	}
	public void setPostuser(String postuser) {
		this.postuser = postuser;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getEstauser() {
		return estauser;
	}
	public void setEstauser(String estauser) {
		this.estauser = estauser;
	}
	public String getEstatime() {
		return estatime;
	}
	public void setEstatime(String estatime) {
		this.estatime = estatime;
	}
}