package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * ��������--��Ա����--��ͥ��Աpojo
 * @author lianjinliang
 * @date   2011-9-23
 */
public class DataFamilyBean implements Serializable{
	
	private String id;			//id(����)
	private String userid;		//��Աid
	private String firsibtypeid;//һ����ν[����]
	private String sibtypeid;	//��ν
	private String sibname;		//��������
	private String sibbirthday;	//��������
	private String sibunit;		//������λ
	private String sibstateid;	//������״
	private String sortnum;		//�����
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	//������GET �� SET ����
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