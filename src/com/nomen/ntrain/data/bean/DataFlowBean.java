package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 机构人员_审核流程
 * @author 李刚
 * @date 2011-9-19
 */
public class DataFlowBean implements Serializable{
	private String id;				//id（主键）
	private String modsign;			//模块标志（1履历审核）
	private String recid;			//主记录id
	private String flowsta;			//流程状态（0个人填写，11部门审核，41人资审核，61单位审核）
	private String chksign;			//操作描述（0报审，1通过，2不通过，3取消，4回退，8公示，9归档）
	private String chkmemo;			//流程提示/备注
	private String remark;          //备注
	private String chkuser;			//操作人
	private String chktime;			//操作时间
	private String strflag;			//预留字段（字符串）
	private String intflag;			//预留字段（整型）
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModsign() {
		return modsign;
	}
	public void setModsign(String modsign) {
		this.modsign = modsign;
	}
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	public String getFlowsta() {
		return flowsta;
	}
	public void setFlowsta(String flowsta) {
		this.flowsta = flowsta;
	}
	public String getChksign() {
		return chksign;
	}
	public void setChksign(String chksign) {
		this.chksign = chksign;
	}
	public String getChkmemo() {
		return chkmemo;
	}
	public void setChkmemo(String chkmemo) {
		this.chkmemo = chkmemo;
	}
	public String getChkuser() {
		return chkuser;
	}
	public void setChkuser(String chkuser) {
		this.chkuser = chkuser;
	}
	public String getChktime() {
		return chktime;
	}
	public void setChktime(String chktime) {
		this.chktime = chktime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}