package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ������Ա_������POJO 
 * @author ������
 * @date 2011-06-18
 */
public class DataFileBean implements Serializable{
	private String id;			//id��������
	private String recid;		//������¼id
	private String modsign;		//ģ���־��1���֤��2����֤��4�����ظ
	private String filename;	//�ϴ��ļ���
	private String savename;	//�����ļ���
	private String savepath;	//����·��
	private String content;		//�ļ����ݣ�Ԥ����
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	//������GET �� SET ����
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
