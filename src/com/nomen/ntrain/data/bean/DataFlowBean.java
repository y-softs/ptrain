package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ������Ա_�������
 * @author ���
 * @date 2011-9-19
 */
public class DataFlowBean implements Serializable{
	private String id;				//id��������
	private String modsign;			//ģ���־��1������ˣ�
	private String recid;			//����¼id
	private String flowsta;			//����״̬��0������д��11������ˣ�41������ˣ�61��λ��ˣ�
	private String chksign;			//����������0����1ͨ����2��ͨ����3ȡ����4���ˣ�8��ʾ��9�鵵��
	private String chkmemo;			//������ʾ/��ע
	private String remark;          //��ע
	private String chkuser;			//������
	private String chktime;			//����ʱ��
	private String strflag;			//Ԥ���ֶΣ��ַ�����
	private String intflag;			//Ԥ���ֶΣ����ͣ�
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModsign() {
		return modsign;
	}
	public void setModsign(String modsign) {
		this.modsign = modsign;
	}
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	public String getFlowsta() {
		return flowsta;
	}
	public void setFlowsta(String flowsta) {
		this.flowsta = flowsta;
	}
	public String getChksign() {
		return chksign;
	}
	public void setChksign(String chksign) {
		this.chksign = chksign;
	}
	public String getChkmemo() {
		return chkmemo;
	}
	public void setChkmemo(String chkmemo) {
		this.chkmemo = chkmemo;
	}
	public String getChkuser() {
		return chkuser;
	}
	public void setChkuser(String chkuser) {
		this.chkuser = chkuser;
	}
	public String getChktime() {
		return chktime;
	}
	public void setChktime(String chktime) {
		this.chktime = chktime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}