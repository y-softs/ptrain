package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Ա��Ϣ��������POJO 
 * @author ������
 * @date 2010-12-15
 */
public class DataReqBean implements Serializable{
	private String id; 			// id��������
	private String userid; 		// ������id
	private String subtiime; 	// ����ʱ��
	private String staflag; 	// ����״̬��0 δ���� 1 �ѱ��� 2 ������� 3 ��λ��� 4 ������
	private String estauser; 	// ������
	private String estatime; 	// ����ʱ��
	private String mainuser; 	// ά����
	private String maintime; 	// ά��ʱ��
	private String strflag; 	// Ԥ���ֶΣ��ַ�����
	private String intflag; 	// Ԥ���ֶΣ����ͣ�
	
	//������GET �� SET ����
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubtiime() {
		return subtiime;
	}
	public void setSubtiime(String subtiime) {
		this.subtiime = subtiime;
	}
	public String getStaflag() {
		return staflag;
	}
	public void setStaflag(String staflag) {
		this.staflag = staflag;
	}
	public String getEstauser() {
		return estauser;
	}
	public void setEstauser(String estauser) {
		this.estauser = estauser;
	}
	public String getEstatime() {
		return estatime;
	}
	public void setEstatime(String estatime) {
		this.estatime = estatime;
	}
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
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
