package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员信息变更内容
 * @author 丁新良
 * @date 2010-12-15
 */
public class DataReqItemBean implements Serializable{
	private String id;			//（主键）
	private String reqid;		//变更申请id
	private String itemname;	//变更内容名称
	private String tablename;	//变更表名
	private String fieldname;	//变更字段名
	private String oldid;		//变更前对应id
	private String oldname;		//变更前内容
	private String newid;		//变更后对应id
	private String newname;		//变更后内容
	private String oldphoto;	//变更前照片
	private String newphoto;	//变更后照片
	private String recid;		//变更记录id
	private String fieldtype;	//变更字段类型（varchar2、date、number、blob）
	private String usesign;		//确认标志（1 是 0 否）
	private String sortnum;     //排序号
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	private String username;    //姓名（辅助字段）
	
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
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getOldid() {
		return oldid;
	}
	public void setOldid(String oldid) {
		this.oldid = oldid;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getNewid() {
		return newid;
	}
	public void setNewid(String newid) {
		this.newid = newid;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getOldphoto() {
		return oldphoto;
	}
	public void setOldphoto(String oldphoto) {
		this.oldphoto = oldphoto;
	}
	public String getNewphoto() {
		return newphoto;
	}
	public void setNewphoto(String newphoto) {
		this.newphoto = newphoto;
	}
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
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
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
