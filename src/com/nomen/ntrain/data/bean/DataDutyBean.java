package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * ����ְ����Ϣ Bean
 * @author ������
 * @date 2013-06-28
 */
public class DataDutyBean implements Serializable{
	private String id;          //ID��������
    private String userid;		//��Աid
    private String statusid;	//�������ID
    private String dutyname;	//ְ������
    private String cadreid;		//�ɲ�ְ�񼶱�id
    private String modeid;		//ְ�����÷�ʽid
    private String firstdate;   //������ְʱ��
    private String partyid;		//��Ⱥְ���ʶID��
    private String remark;      //��ע
    private String estauser;	//������
    private String estatime;	//����ʱ��
    private String mainuser;	//ά����
    private String maintime;	//ά��ʱ��
    private String strflag;	    //Ԥ���ֶΣ��ַ�����
    private String intflag;		//Ԥ���ֶΣ����ͣ�
    
    private String partynames;  //��Ⱥְ���ʶname��
    //������GET �� SET����
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	public String getCadreid() {
		return cadreid;
	}
	public void setCadreid(String cadreid) {
		this.cadreid = cadreid;
	}
	public String getModeid() {
		return modeid;
	}
	public void setModeid(String modeid) {
		this.modeid = modeid;
	}
	public String getPartyid() {
		return partyid;
	}
	public void setPartyid(String partyid) {
		this.partyid = partyid;
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
	public String getFirstdate() {
		return firstdate;
	}
	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDutyname() {
		return dutyname;
	}
	public void setDutyname(String dutyname) {
		this.dutyname = dutyname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPartynames() {
		return partynames;
	}
	public void setPartynames(String partynames) {
		this.partynames = partynames;
	}
}
