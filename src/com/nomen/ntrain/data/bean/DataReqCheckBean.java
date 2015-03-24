package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员信息变更审核
 * @author 丁新良
 * @date 2010-12-15
 */
public class DataReqCheckBean implements Serializable{
	private String id;			//id（主键）
	private String reqid;		//变更申请id
	private String staflag;		//流程状态（2 部门审核 3 单位审核）
	private String chksign;		//审核标志（1 通过 0 不通过）
	private String chkmemo;		//审核意见（不通过原因）
	private String chkuser;		//审核人
	private String chktime;		//审核时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	//以下是GET 和 SET 方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getStaflag() {
		return staflag;
	}
	public void setStaflag(String staflag) {
		this.staflag = staflag;
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
}
