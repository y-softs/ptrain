package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * 
 * 机构管理--人员管理--婚姻状况pojo
 * @author lianjinliang
 * @date   2011-9-23
 */
public class DataMarryBean implements Serializable{
	private String userid;		//人员id
	private String marryid;		//婚姻状况id
	private String dualsign; 	//双职工标志（1是，0否）
	private String mateid;		//配偶id
	private String matename;    //辅助
	private String remark;		//备注
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	//以下为set get方法
	public String getDualsign() {
		return dualsign;
	}
	public void setDualsign(String dualsign) {
		this.dualsign = dualsign;
	}
	public String getEstatime() {
		return estatime;
	}
	public void setEstatime(String estatime) {
		this.estatime = estatime;
	}
	public String getEstauser() {
		return estauser;
	}
	public void setEstauser(String estauser) {
		this.estauser = estauser;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
	}
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getMarryid() {
		return marryid;
	}
	public void setMarryid(String marryid) {
		this.marryid = marryid;
	}
	public String getMateid() {
		return mateid;
	}
	public void setMateid(String mateid) {
		this.mateid = mateid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMatename() {
		return matename;
	}
	public void setMatename(String matename) {
		this.matename = matename;
	}
	
}
