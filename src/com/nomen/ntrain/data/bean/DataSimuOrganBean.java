package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ģ�⽨�Ʊ�POJO
 * @author ������
 * @date 2011-09-07
 */
public class DataSimuOrganBean implements Serializable{
	private String id;		 //id��������
	private String simuid;	 //���id
	private String fatherid; //����id
	private String organname;//��������
	private String amount;	 //���ƶ�Ա
	private String refcount; //������������[��ְ����]2011-10-31
	private String endsign;	 //ĩ�˱�־��1 �� ��0 ��
	private String sortnum;  //�����
	private String estauser; //������
	private String estatime; //����ʱ��
	private String mainuser; //ά����
	private String maintime; //ά��ʱ��
	private String strflag;  //Ԥ���ֶΣ��ַ�����
	private String intflag;  //Ԥ���ֶΣ����ͣ�
	
	
	//����Ϊset get����
	public String getEndsign() {
		return endsign;
	}
	public void setEndsign(String endsign) {
		this.endsign = endsign;
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
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
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
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public String getSimuid() {
		return simuid;
	}
	public void setSimuid(String simuid) {
		this.simuid = simuid;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRefcount() {
		return refcount;
	}
	public void setRefcount(String refcount) {
		this.refcount = refcount;
	}
	
}
