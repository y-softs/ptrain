package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员联系方式信息表POJO 
 * @author 钱新红
 * @date 2009-12-18
 * @modifier tian_chen-丁新良
 * @date 2010-05-24、2011-07-06
 */
public class DataMessBean implements Serializable{
 	private String userid; 		// 人员id
 	private String ohouse;		//办公楼
 	private String oroom;		//办公楼房间号
 	private String opostcode;	//办公地点邮编
	private String phone; 		// 办公电话
	private String mobile1; 	// 手机号码一
	private String sesign1;		// 保密标志一（1是，0否）
	private String mobile2; 	// 手机号码二
	private String sesign2;		// 保密标志一（1是，0否）
	private String micphone; 	// 微波号码
	private String oamail;      // OA
	private String email; 		// e-mail
	private String qq; 			// qq号码
	private String bankname; 	// 银行名称
	private String bankid;		// 银行卡号
	private String bankdetail;	// 开户银行
	private String address; 	// 家庭地址
	private String telephone; 	// 家庭电话
	private String postcode; 	// 邮编号码
	private String remark; 		// 备注
	private String estauser; 	// 创建人	
	private String estatime; 	// 创建时间
	private String mainuser; 	// 维护人
	private String maintime; 	// 维护时间
	private String strflag; 	// 预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	
	
	//以下是GET 和 SET 方法
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