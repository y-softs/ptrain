package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description  岗位标准_部门归口表
 * @author 李刚
 * @date 2011-5-31
 */
public class DataDeptascrBean implements Serializable{
	private String id;			//id（主键）
	private String unitid;		//单位id
	private String deptid;		//部门id
	private String ascrdeptid;	//归口部门id
	private String estauser;	//创建人
	private String estatime;    //创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getAscrdeptid() {
		return ascrdeptid;
	}
	public void setAscrdeptid(String ascrdeptid) {
		this.ascrdeptid = ascrdeptid;
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