package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ģ�⽨������POJO
 * @author ������
 * @date 2011-09-07
 */
public class DataSimuBean implements Serializable{
	private String id;		//id��������
	private String unitid;  //��λid
	private String simuname;//�������
	private String calcdate;//��������
	private String usesign; //���ñ�־��1 ���ã� 0 ���ã�
	private String sortnum; //�����
	private String estauser;//������
	private String estatime;//����ʱ��
	private String mainuser;//ά����
	private String maintime;//ά��ʱ��
	private String strflag; //Ԥ���ֶΣ��ַ�����
	private String intflag; //Ԥ���ֶΣ����ͣ�
	
	//����Ϊset get����
	public String getCalcdate() {
		return calcdate;
	}
	public void setCalcdate(String calcdate) {
		this.calcdate = calcdate;
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
	public String getSimuname() {
		return simuname;
	}
	public void setSimuname(String simuname) {
		this.simuname = simuname;
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
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	
}
