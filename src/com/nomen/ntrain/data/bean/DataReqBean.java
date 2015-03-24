package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员信息变更申请表POJO 
 * @author 丁新良
 * @date 2010-12-15
 */
public class DataReqBean implements Serializable{
	private String id; 			// id（主键）
	private String userid; 		// 报审人id
	private String subtiime; 	// 报审时间
	private String staflag; 	// 流程状态（0 未报审 1 已报审 2 部门审核 3 单位审核 4 结束）
	private String estauser; 	// 创建人
	private String estatime; 	// 创建时间
	private String mainuser; 	// 维护人
	private String maintime; 	// 维护时间
	private String strflag; 	// 预留字段（字符串）
	private String intflag; 	// 预留字段（整型）
	
	//以下是GET 和 SET 方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubtiime() {
		return subtiime;
	}
	public void setSubtiime(String subtiime) {
		this.subtiime = subtiime;
	}
	public String getStaflag() {
		return staflag;
	}
	public void setStaflag(String staflag) {
		this.staflag = staflag;
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
}
