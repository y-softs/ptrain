package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 模拟建制类别表POJO
 * @author 连金亮
 * @date 2011-09-07
 */
public class DataSimuBean implements Serializable{
	private String id;		//id（主键）
	private String unitid;  //单位id
	private String simuname;//类别名称
	private String calcdate;//计算日期
	private String usesign; //启用标志（1 启用， 0 禁用）
	private String sortnum; //排序号
	private String estauser;//创建人
	private String estatime;//创建时间
	private String mainuser;//维护人
	private String maintime;//维护时间
	private String strflag; //预留字段（字符串）
	private String intflag; //预留字段（整型）
	
	//以下为set get方法
	public String getCalcdate() {
		return calcdate;
	}
	public void setCalcdate(String calcdate) {
		this.calcdate = calcdate;
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
	public String getSimuname() {
		return simuname;
	}
	public void setSimuname(String simuname) {
		this.simuname = simuname;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	
}
