package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 模拟建制表POJO
 * @author 连金亮
 * @date 2011-09-07
 */
public class DataSimuOrganBean implements Serializable{
	private String id;		 //id（主键）
	private String simuid;	 //类别id
	private String fatherid; //父级id
	private String organname;//建制名称
	private String amount;	 //建制定员
	private String refcount; //关联机构人数[在职人数]2011-10-31
	private String endsign;	 //末端标志（1 是 ，0 否）
	private String sortnum;  //排序号
	private String estauser; //创建人
	private String estatime; //创建时间
	private String mainuser; //维护人
	private String maintime; //维护时间
	private String strflag;  //预留字段（字符串）
	private String intflag;  //预留字段（整型）
	
	
	//以下为set get方法
	public String getEndsign() {
		return endsign;
	}
	public void setEndsign(String endsign) {
		this.endsign = endsign;
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
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public String getSimuid() {
		return simuid;
	}
	public void setSimuid(String simuid) {
		this.simuid = simuid;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRefcount() {
		return refcount;
	}
	public void setRefcount(String refcount) {
		this.refcount = refcount;
	}
	
}
