package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ģ�⽨�ƹ�����POJO
 * @author ������
 * @date 2011-09-07
 */
public class DataSimuRefBean implements Serializable{
	private String id;			//id��������
	private String simuorganid; //����id
	private String organid;		//��������id
	private String organname;	//�����������ƣ������ϼ��������ƣ�
	private String amount;		//������������
	private String besign;		//���������Ƿ���ڣ�1�У�0�ޣ�
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	
	//����Ϊset get����
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBesign() {
		return besign;
	}
	public void setBesign(String besign) {
		this.besign = besign;
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
	public String getSimuorganid() {
		return simuorganid;
	}
	public void setSimuorganid(String simuorganid) {
		this.simuorganid = simuorganid;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	
	
}
