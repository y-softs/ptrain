package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * 
 * ��������--��Ա����--����״��pojo
 * @author lianjinliang
 * @date   2011-9-23
 */
public class DataMarryBean implements Serializable{
	private String userid;		//��Աid
	private String marryid;		//����״��id
	private String dualsign; 	//˫ְ����־��1�ǣ�0��
	private String mateid;		//��żid
	private String matename;    //����
	private String remark;		//��ע
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	//����Ϊset get����
	public String getDualsign() {
		return dualsign;
	}
	public void setDualsign(String dualsign) {
		this.dualsign = dualsign;
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
	public String getMarryid() {
		return marryid;
	}
	public void setMarryid(String marryid) {
		this.marryid = marryid;
	}
	public String getMateid() {
		return mateid;
	}
	public void setMateid(String mateid) {
		this.mateid = mateid;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMatename() {
		return matename;
	}
	public void setMatename(String matename) {
		this.matename = matename;
	}
	
}
