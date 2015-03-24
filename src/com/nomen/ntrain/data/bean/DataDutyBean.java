package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * 个人职务信息 Bean
 * @author 连金亮
 * @date 2013-06-28
 */
public class DataDutyBean implements Serializable{
	private String id;          //ID（主键）
    private String userid;		//人员id
    private String statusid;	//档案身份ID
    private String dutyname;	//职务名称
    private String cadreid;		//干部职务级别id
    private String modeid;		//职务配置方式id
    private String firstdate;   //初次任职时间
    private String partyid;		//党群职务标识ID串
    private String remark;      //备注
    private String estauser;	//创建人
    private String estatime;	//创建时间
    private String mainuser;	//维护人
    private String maintime;	//维护时间
    private String strflag;	    //预留字段（字符串）
    private String intflag;		//预留字段（整型）
    
    private String partynames;  //党群职务标识name串
    //以下是GET 和 SET方法
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	public String getCadreid() {
		return cadreid;
	}
	public void setCadreid(String cadreid) {
		this.cadreid = cadreid;
	}
	public String getModeid() {
		return modeid;
	}
	public void setModeid(String modeid) {
		this.modeid = modeid;
	}
	public String getPartyid() {
		return partyid;
	}
	public void setPartyid(String partyid) {
		this.partyid = partyid;
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
	public String getFirstdate() {
		return firstdate;
	}
	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDutyname() {
		return dutyname;
	}
	public void setDutyname(String dutyname) {
		this.dutyname = dutyname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPartynames() {
		return partynames;
	}
	public void setPartynames(String partynames) {
		this.partynames = partynames;
	}
}
