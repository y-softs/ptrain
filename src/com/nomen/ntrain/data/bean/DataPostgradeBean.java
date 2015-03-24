package com.nomen.ntrain.data.bean;

import java.io.Serializable;



/**
 * @description 机构人员_岗位等级关联（单位岗位岗级关系表）POJO
 * @author 李刚
 * @date 2010-12-10
 * @modifier 丁新良
 * @date 2010-12-10
 */
public class DataPostgradeBean implements Serializable{
	private String id;			//id(主键)
	private String nature;		//性质（1共性，2个性）
	private String unitid;		//单位id
	private String gradeid;		//岗位等级
	private String postgrade;	//岗级
	private String dutygrade;	//职级
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser	;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	//以下GET 和 SET 方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getPostgrade() {
		return postgrade;
	}
	public void setPostgrade(String postgrade) {
		this.postgrade = postgrade;
	}
	public String getDutygrade() {
		return dutygrade;
	}
	public void setDutygrade(String dutygrade) {
		this.dutygrade = dutygrade;
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
