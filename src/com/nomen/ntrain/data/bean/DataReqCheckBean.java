package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Ա��Ϣ������
 * @author ������
 * @date 2010-12-15
 */
public class DataReqCheckBean implements Serializable{
	private String id;			//id��������
	private String reqid;		//�������id
	private String staflag;		//����״̬��2 ������� 3 ��λ��ˣ�
	private String chksign;		//��˱�־��1 ͨ�� 0 ��ͨ����
	private String chkmemo;		//����������ͨ��ԭ��
	private String chkuser;		//�����
	private String chktime;		//���ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	//������GET �� SET ����
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
	public String getStaflag() {
		return staflag;
	}
	public void setStaflag(String staflag) {
		this.staflag = staflag;
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
}
