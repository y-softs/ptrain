package com.nomen.ntrain.res.bean;

import java.io.Serializable;

/**
 * @description  资源代码表
 * @author 李刚
 * @date 2011-5-18
 */
public class ResCodeBean implements Serializable{
	private String id;			//id（主键）
	private String nature;		//性质（1共性，2个性）
	private String unitid;		//单位id
	private String fatherid;	//父级id
	private String codename;	//代码名称
	private String path;		//服务器路径
	private String remark;		//说明
	private String endsign;		//末端标志（1是，0否）
	private String specsign;	//标志（1是，0否）
	private String usesign;		//启用标志（1启用，0禁止）
	private String codevalue;	//编码
	private String sortnum;		//排序号
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	//==============界面辅助属性============
	private String subjectnum;  //题型量：用在日常登记->项目课时
	private String subjecttitme;//课时：用在日常登记->项目课时
	
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
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEndsign() {
		return endsign;
	}
	public void setEndsign(String endsign) {
		this.endsign = endsign;
	}
	public String getSpecsign() {
		return specsign;
	}
	public void setSpecsign(String specsign) {
		this.specsign = specsign;
	}
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
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
	public String getSubjectnum() {
		return subjectnum;
	}
	public void setSubjectnum(String subjectnum) {
		this.subjectnum = subjectnum;
	}
	public String getSubjecttitme() {
		return subjecttitme;
	}
	public void setSubjecttitme(String subjecttitme) {
		this.subjecttitme = subjecttitme;
	}
	
	
}