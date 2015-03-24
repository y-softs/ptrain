package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 机构沿革表：用于记录机构变更历史POJO
 * @author 连金亮
 * @date 2011-09-05
 */
public class DataOrganhisBean implements Serializable{
	private String id;			//id（主键）
	private String organid;		//机构id
	private String organname;	//机构名称
	private String typesign;	//变更类型（1设立，2更名，3升级，9撤销）
	private String checkunit;	//批准单位
	private String checkdate;	//批准时间
	private String filename;	//文件名称
	private String fileids;		//文件引入索引id串
	private String remark;		//备注
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	
	//以下为set get方法
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getCheckunit() {
		return checkunit;
	}
	public void setCheckunit(String checkunit) {
		this.checkunit = checkunit;
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
	public String getFileids() {
		return fileids;
	}
	public void setFileids(String fileids) {
		this.fileids = fileids;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getTypesign() {
		return typesign;
	}
	public void setTypesign(String typesign) {
		this.typesign = typesign;
	}	
}
