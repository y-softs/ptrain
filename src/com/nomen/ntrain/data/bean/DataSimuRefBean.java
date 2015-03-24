package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 模拟建制关联表POJO
 * @author 连金亮
 * @date 2011-09-07
 */
public class DataSimuRefBean implements Serializable{
	private String id;			//id（主键）
	private String simuorganid; //建制id
	private String organid;		//关联机构id
	private String organname;	//关联机构名称（包含上级机构名称）
	private String amount;		//关联机构人数
	private String besign;		//关联机构是否存在（1有，0无）
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	
	//以下为set get方法
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBesign() {
		return besign;
	}
	public void setBesign(String besign) {
		this.besign = besign;
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
	public String getOrganid() {
		return organid;
	}
	public void setOrganid(String organid) {
		this.organid = organid;
	}
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public String getSimuorganid() {
		return simuorganid;
	}
	public void setSimuorganid(String simuorganid) {
		this.simuorganid = simuorganid;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	
	
}
