package com.nomen.ntrain.res.bean;

import java.io.Serializable;

/**
 * @description  ��Դ�����
 * @author ���
 * @date 2011-5-18
 */
public class ResCodeBean implements Serializable{
	private String id;			//id��������
	private String nature;		//���ʣ�1���ԣ�2���ԣ�
	private String unitid;		//��λid
	private String fatherid;	//����id
	private String codename;	//��������
	private String path;		//������·��
	private String remark;		//˵��
	private String endsign;		//ĩ�˱�־��1�ǣ�0��
	private String specsign;	//��־��1�ǣ�0��
	private String usesign;		//���ñ�־��1���ã�0��ֹ��
	private String codevalue;	//����
	private String sortnum;		//�����
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	//==============���渨������============
	private String subjectnum;  //�������������ճ��Ǽ�->��Ŀ��ʱ
	private String subjecttitme;//��ʱ�������ճ��Ǽ�->��Ŀ��ʱ
	
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
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEndsign() {
		return endsign;
	}
	public void setEndsign(String endsign) {
		this.endsign = endsign;
	}
	public String getSpecsign() {
		return specsign;
	}
	public void setSpecsign(String specsign) {
		this.specsign = specsign;
	}
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
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
	public String getSubjectnum() {
		return subjectnum;
	}
	public void setSubjectnum(String subjectnum) {
		this.subjectnum = subjectnum;
	}
	public String getSubjecttitme() {
		return subjecttitme;
	}
	public void setSubjecttitme(String subjecttitme) {
		this.subjecttitme = subjecttitme;
	}
	
	
}