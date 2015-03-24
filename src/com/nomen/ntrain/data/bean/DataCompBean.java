package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员职称资格信息表POJO 
 * @author 钱新红
 * @date 2009-12-17
 * @modifier 陈高添\丁新良
 * @date 2010-05-19、2011-06-18
 */

public class DataCompBean implements Serializable{
	private String userid;		//人员id
	private String specgrade;	//技术职务等级id
	private String specid;	//技术职务系列id
	private String specdate;	//职务等级获得时间
	private String workgrade;	//职业资格鉴定等级id
	private String workid;		//职业资格工种名称id
	private String workdate;	//职业资格获得时间
	private String langlevel;	//外语水平id
	private String langdate;	//外语水平获得时间
	private String complevel;	//计算机水平id
	private String compdate;	//计算机水平获得时间
	private String talid;		//专家人才类型id
	private String talsign;		//称号获得方式id
	private String taldate;		//专家人才获得时间
    private String estauser;	//创建人
    private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	//辅助字段
	private String specfatherid;      //技术职称系列fatherid
	private String langfatherid; 	  //外语水平fatherid
	private String compfatherid; 	  //计算机水平fatherid
	private String talfatherid;       //专家人才类型fatherid
	//以下是GET 和 SET 方法
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSpecgrade() {
		return specgrade;
	}
	public void setSpecgrade(String specgrade) {
		this.specgrade = specgrade;
	}
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public String getSpecdate() {
		return specdate;
	}
	public void setSpecdate(String specdate) {
		this.specdate = specdate;
	}
	public String getWorkgrade() {
		return workgrade;
	}
	public void setWorkgrade(String workgrade) {
		this.workgrade = workgrade;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getLanglevel() {
		return langlevel;
	}
	public void setLanglevel(String langlevel) {
		this.langlevel = langlevel;
	}
	public String getLangdate() {
		return langdate;
	}
	public void setLangdate(String langdate) {
		this.langdate = langdate;
	}
	public String getComplevel() {
		return complevel;
	}
	public void setComplevel(String complevel) {
		this.complevel = complevel;
	}
	public String getCompdate() {
		return compdate;
	}
	public void setCompdate(String compdate) {
		this.compdate = compdate;
	}
	public String getTalid() {
		return talid;
	}
	public void setTalid(String talid) {
		this.talid = talid;
	}
	public String getTalsign() {
		return talsign;
	}
	public void setTalsign(String talsign) {
		this.talsign = talsign;
	}
	public String getTaldate() {
		return taldate;
	}
	public void setTaldate(String taldate) {
		this.taldate = taldate;
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
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
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
	public String getSpecfatherid() {
		return specfatherid;
	}
	public void setSpecfatherid(String specfatherid) {
		this.specfatherid = specfatherid;
	}
	public String getLangfatherid() {
		return langfatherid;
	}
	public void setLangfatherid(String langfatherid) {
		this.langfatherid = langfatherid;
	}
	public String getCompfatherid() {
		return compfatherid;
	}
	public void setCompfatherid(String compfatherid) {
		this.compfatherid = compfatherid;
	}
	public String getTalfatherid() {
		return talfatherid;
	}
	public void setTalfatherid(String talfatherid) {
		this.talfatherid = talfatherid;
	}
}