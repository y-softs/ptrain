package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description �����ù���Ϣ��POJO 
 * @author ������
 * @date 2013-06-21
 */
public class DataWorkBean implements Serializable{
	private String userid;		//��Աid
	private String acckindid;	//��������id
	private String accplace;	//�������ڵ�
	private String accaddr;	    //������ַ
	private String workdate;	//�μӹ���ʱ��
	private String powerdate;	//����ϵͳʱ��
	private String unitdate;	//���뵥λʱ��
	private String formid;		//�ù���ʽid
	private String contdate;	//��ͬǩ��ʱ��
	private String contypeid;	//��ͬ����id
	private String limitid;		//��ͬ��������ID
	private String limitdate;	//��ͬ����
	private String contunit;	//��ͬ�ù���λ
	private String soldid;		//��ת����ID
	private String solddate;	//��תʱ��
	private String insertid;	//��Ա����ID
	private String remark;      //��ע
    private String estauser;	//������
    private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	//�����ֶ�
	private String insertdesc;      //��Ա��������[����]
	private String insertfatherid;	//��Ա���͸���id
	//������ GET �� SET ����
	public String getAccaddr() {
		return accaddr;
	}
	public void setAccaddr(String accaddr) {
		this.accaddr = accaddr;
	}
	public String getAcckindid() {
		return acckindid;
	}
	public void setAcckindid(String acckindid) {
		this.acckindid = acckindid;
	}
	public String getAccplace() {
		return accplace;
	}
	public void setAccplace(String accplace) {
		this.accplace = accplace;
	}
	public String getContdate() {
		return contdate;
	}
	public void setContdate(String contdate) {
		this.contdate = contdate;
	}
	public String getContunit() {
		return contunit;
	}
	public void setContunit(String contunit) {
		this.contunit = contunit;
	}
	public String getContypeid() {
		return contypeid;
	}
	public void setContypeid(String contypeid) {
		this.contypeid = contypeid;
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
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public String getInsertdesc() {
		return insertdesc;
	}
	public void setInsertdesc(String insertdesc) {
		this.insertdesc = insertdesc;
	}
	public String getInsertfatherid() {
		return insertfatherid;
	}
	public void setInsertfatherid(String insertfatherid) {
		this.insertfatherid = insertfatherid;
	}
	public String getInsertid() {
		return insertid;
	}
	public void setInsertid(String insertid) {
		this.insertid = insertid;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getLimitdate() {
		return limitdate;
	}
	public void setLimitdate(String limitdate) {
		this.limitdate = limitdate;
	}
	public String getLimitid() {
		return limitid;
	}
	public void setLimitid(String limitid) {
		this.limitid = limitid;
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
	public String getPowerdate() {
		return powerdate;
	}
	public void setPowerdate(String powerdate) {
		this.powerdate = powerdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSolddate() {
		return solddate;
	}
	public void setSolddate(String solddate) {
		this.solddate = solddate;
	}
	public String getSoldid() {
		return soldid;
	}
	public void setSoldid(String soldid) {
		this.soldid = soldid;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getUnitdate() {
		return unitdate;
	}
	public void setUnitdate(String unitdate) {
		this.unitdate = unitdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	
}