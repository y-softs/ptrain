package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * 机构管理--人员管理--家庭成员pojo
 * @author lianjinliang
 * @date   2011-9-23
 */
public class DataFamilyBean implements Serializable{
	
	private String id;			//id(主键)
	private String userid;		//人员id
	private String firsibtypeid;//一级称谓[辅助]
	private String sibtypeid;	//称谓
	private String sibname;		//家属姓名
	private String sibbirthday;	//出生年月
	private String sibunit;		//工作单位
	private String sibstateid;	//家属现状
	private String sortnum;		//排序号
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	//以下是GET 和 SET 方法
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
	public String getSibbirthday() {
		return sibbirthday;
	}
	public void setSibbirthday(String sibbirthday) {
		this.sibbirthday = sibbirthday;
	}
	public String getSibname() {
		return sibname;
	}
	public void setSibname(String sibname) {
		this.sibname = sibname;
	}
	public String getSibstateid() {
		return sibstateid;
	}
	public void setSibstateid(String sibstateid) {
		this.sibstateid = sibstateid;
	}
	public String getSibtypeid() {
		return sibtypeid;
	}
	public void setSibtypeid(String sibtypeid) {
		this.sibtypeid = sibtypeid;
	}
	public String getSibunit() {
		return sibunit;
	}
	public void setSibunit(String sibunit) {
		this.sibunit = sibunit;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFirsibtypeid() {
		return firsibtypeid;
	}
	public void setFirsibtypeid(String firsibtypeid) {
		this.firsibtypeid = firsibtypeid;
	}
	
}