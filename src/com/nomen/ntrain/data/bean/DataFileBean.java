package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 机构人员_附件表POJO 
 * @author 丁新良
 * @date 2011-06-18
 */
public class DataFileBean implements Serializable{
	private String id;			//id（主键）
	private String recid;		//关联记录id
	private String modsign;		//模块标志（1身份证，2工作证，4机构沿革）
	private String filename;	//上传文件名
	private String savename;	//保存文件名
	private String savepath;	//保存路径
	private String content;		//文件内容（预留）
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	//以下是GET 和 SET 方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	public String getModsign() {
		return modsign;
	}
	public void setModsign(String modsign) {
		this.modsign = modsign;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
