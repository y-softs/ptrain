package com.nomen.ntrain.data.bean;

import java.io.Serializable;



/**
 * @description ������Ա_��λ�ȼ���������λ��λ�ڼ���ϵ��POJO
 * @author ���
 * @date 2010-12-10
 * @modifier ������
 * @date 2010-12-10
 */
public class DataPostgradeBean implements Serializable{
	private String id;			//id(����)
	private String nature;		//���ʣ�1���ԣ�2���ԣ�
	private String unitid;		//��λid
	private String gradeid;		//��λ�ȼ�
	private String postgrade;	//�ڼ�
	private String dutygrade;	//ְ��
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser	;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	//����GET �� SET ����
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getPostgrade() {
		return postgrade;
	}
	public void setPostgrade(String postgrade) {
		this.postgrade = postgrade;
	}
	public String getDutygrade() {
		return dutygrade;
	}
	public void setDutygrade(String dutygrade) {
		this.dutygrade = dutygrade;
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
