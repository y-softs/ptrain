package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Ա��ϵ��ʽ��Ϣ��POJO 
 * @author Ǯ�º�
 * @date 2009-12-18
 * @modifier tian_chen-������
 * @date 2010-05-24��2011-07-06
 */
public class DataMessBean implements Serializable{
 	private String userid; 		// ��Աid
 	private String ohouse;		//�칫¥
 	private String oroom;		//�칫¥�����
 	private String opostcode;	//�칫�ص��ʱ�
	private String phone; 		// �칫�绰
	private String mobile1; 	// �ֻ�����һ
	private String sesign1;		// ���ܱ�־һ��1�ǣ�0��
	private String mobile2; 	// �ֻ������
	private String sesign2;		// ���ܱ�־һ��1�ǣ�0��
	private String micphone; 	// ΢������
	private String oamail;      // OA
	private String email; 		// e-mail
	private String qq; 			// qq����
	private String bankname; 	// ��������
	private String bankid;		// ���п���
	private String bankdetail;	// ��������
	private String address; 	// ��ͥ��ַ
	private String telephone; 	// ��ͥ�绰
	private String postcode; 	// �ʱ����
	private String remark; 		// ��ע
	private String estauser; 	// ������	
	private String estatime; 	// ����ʱ��
	private String mainuser; 	// ά����
	private String maintime; 	// ά��ʱ��
	private String strflag; 	// Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	
	
	//������GET �� SET ����
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOhouse() {
		return ohouse;
	}
	public void setOhouse(String ohouse) {
		this.ohouse = ohouse;
	}
	public String getOroom() {
		return oroom;
	}
	public void setOroom(String oroom) {
		this.oroom = oroom;
	}
	public String getOpostcode() {
		return opostcode;
	}
	public void setOpostcode(String opostcode) {
		this.opostcode = opostcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getSesign1() {
		return sesign1;
	}
	public void setSesign1(String sesign1) {
		this.sesign1 = sesign1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getSesign2() {
		return sesign2;
	}
	public void setSesign2(String sesign2) {
		this.sesign2 = sesign2;
	}
	public String getMicphone() {
		return micphone;
	}
	public void setMicphone(String micphone) {
		this.micphone = micphone;
	}
	public String getOamail() {
		return oamail;
	}
	public void setOamail(String oamail) {
		this.oamail = oamail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getBankdetail() {
		return bankdetail;
	}
	public void setBankdetail(String bankdetail) {
		this.bankdetail = bankdetail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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