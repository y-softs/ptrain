package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Աְ���ʸ���Ϣ��POJO 
 * @author Ǯ�º�
 * @date 2009-12-17
 * @modifier �¸���\������
 * @date 2010-05-19��2011-06-18
 */

public class DataCompBean implements Serializable{
	private String userid;		//��Աid
	private String specgrade;	//����ְ��ȼ�id
	private String specid;	//����ְ��ϵ��id
	private String specdate;	//ְ��ȼ����ʱ��
	private String workgrade;	//ְҵ�ʸ�����ȼ�id
	private String workid;		//ְҵ�ʸ�������id
	private String workdate;	//ְҵ�ʸ���ʱ��
	private String langlevel;	//����ˮƽid
	private String langdate;	//����ˮƽ���ʱ��
	private String complevel;	//�����ˮƽid
	private String compdate;	//�����ˮƽ���ʱ��
	private String talid;		//ר���˲�����id
	private String talsign;		//�ƺŻ�÷�ʽid
	private String taldate;		//ר���˲Ż��ʱ��
    private String estauser;	//������
    private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	//�����ֶ�
	private String specfatherid;      //����ְ��ϵ��fatherid
	private String langfatherid; 	  //����ˮƽfatherid
	private String compfatherid; 	  //�����ˮƽfatherid
	private String talfatherid;       //ר���˲�����fatherid
	//������GET �� SET ����
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSpecgrade() {
		return specgrade;
	}
	public void setSpecgrade(String specgrade) {
		this.specgrade = specgrade;
	}
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public String getSpecdate() {
		return specdate;
	}
	public void setSpecdate(String specdate) {
		this.specdate = specdate;
	}
	public String getWorkgrade() {
		return workgrade;
	}
	public void setWorkgrade(String workgrade) {
		this.workgrade = workgrade;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getLanglevel() {
		return langlevel;
	}
	public void setLanglevel(String langlevel) {
		this.langlevel = langlevel;
	}
	public String getLangdate() {
		return langdate;
	}
	public void setLangdate(String langdate) {
		this.langdate = langdate;
	}
	public String getComplevel() {
		return complevel;
	}
	public void setComplevel(String complevel) {
		this.complevel = complevel;
	}
	public String getCompdate() {
		return compdate;
	}
	public void setCompdate(String compdate) {
		this.compdate = compdate;
	}
	public String getTalid() {
		return talid;
	}
	public void setTalid(String talid) {
		this.talid = talid;
	}
	public String getTalsign() {
		return talsign;
	}
	public void setTalsign(String talsign) {
		this.talsign = talsign;
	}
	public String getTaldate() {
		return taldate;
	}
	public void setTaldate(String taldate) {
		this.taldate = taldate;
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
	public String getSpecfatherid() {
		return specfatherid;
	}
	public void setSpecfatherid(String specfatherid) {
		this.specfatherid = specfatherid;
	}
	public String getLangfatherid() {
		return langfatherid;
	}
	public void setLangfatherid(String langfatherid) {
		this.langfatherid = langfatherid;
	}
	public String getCompfatherid() {
		return compfatherid;
	}
	public void setCompfatherid(String compfatherid) {
		this.compfatherid = compfatherid;
	}
	public String getTalfatherid() {
		return talfatherid;
	}
	public void setTalfatherid(String talfatherid) {
		this.talfatherid = talfatherid;
	}
}