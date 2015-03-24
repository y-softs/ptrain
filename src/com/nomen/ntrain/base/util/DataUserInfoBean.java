package com.nomen.ntrain.base.util;
/**
 * 人员详细信息bean[随着表的追加，该bean的字段也要变动]
 * @author lianjinliang
 * @date   2012-4-18
 */
public class DataUserInfoBean {
	private String id;           //id
	private String unitid;       //现工作单位ID
	private String unitname;     //现工作单位 
	private String deptid;       //现工作部门ID
	private String deptname;     //现工作部门
	private String groupid;      //现工作班组ID
	private String groupname;    //现工作班组
	private String username;     //姓名
	private String py;   		 //姓名拼音
	private String userid;   	 //身份证号
	private String workid;   	 //工号
	private String countryid;    //国籍
	private String natalid;   	 //民族
	private String sex;   		 //性别
	private String birthday;     //出生年月
	private String place;   	 //籍贯
	private String politid;      //政治面貌
	private String politdate;    //入党时间
	private String kindid;		 //员工性质
	private String kinddate;     //员工性质确认时间
	private String stateid;   	 //人员状态
	private String statedate;    //人员状态确认时间
	private String oldunitid;    //原工作单位ID
	private String oldunitname;  //原工作单位
	private String olddeptid;    //原工作部门ID
	private String olddeptname;  //原工作部门
	private String oldgroupid;   //原工作班组ID
	private String oldgroupname; //原工作班组
	private String phone;     //办公电话
	private String mobile1;   //手机号码一
	private String mobile2;   //保密标志一（1是，0否）
	private String sesign1;   //手机号码二
	private String sesign2;   //保密标志二（1是，0否）
	private String micphone;  //微波号码
	private String oamail;    //OA邮箱
	private String email;     //E-mail地址
	private String qq;        //QQ号码
	private String bankname;  //银行名称
	private String bankid;    //银行卡号
	private String bankdetail;//开户银行
	private String address;   //家庭地址
	private String telephone; //家庭电话
	private String postcode;  //邮编号码
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBankdetail() {
		return bankdetail;
	}
	public void setBankdetail(String bankdetail) {
		this.bankdetail = bankdetail;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getKinddate() {
		return kinddate;
	}
	public void setKinddate(String kinddate) {
		this.kinddate = kinddate;
	}
	public String getKindid() {
		return kindid;
	}
	public void setKindid(String kindid) {
		this.kindid = kindid;
	}
	public String getMicphone() {
		return micphone;
	}
	public void setMicphone(String micphone) {
		this.micphone = micphone;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getNatalid() {
		return natalid;
	}
	public void setNatalid(String natalid) {
		this.natalid = natalid;
	}
	public String getOamail() {
		return oamail;
	}
	public void setOamail(String oamail) {
		this.oamail = oamail;
	}
	public String getOlddeptid() {
		return olddeptid;
	}
	public void setOlddeptid(String olddeptid) {
		this.olddeptid = olddeptid;
	}
	public String getOlddeptname() {
		return olddeptname;
	}
	public void setOlddeptname(String olddeptname) {
		this.olddeptname = olddeptname;
	}
	public String getOldgroupid() {
		return oldgroupid;
	}
	public void setOldgroupid(String oldgroupid) {
		this.oldgroupid = oldgroupid;
	}
	public String getOldgroupname() {
		return oldgroupname;
	}
	public void setOldgroupname(String oldgroupname) {
		this.oldgroupname = oldgroupname;
	}
	public String getOldunitid() {
		return oldunitid;
	}
	public void setOldunitid(String oldunitid) {
		this.oldunitid = oldunitid;
	}
	public String getOldunitname() {
		return oldunitname;
	}
	public void setOldunitname(String oldunitname) {
		this.oldunitname = oldunitname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPolitdate() {
		return politdate;
	}
	public void setPolitdate(String politdate) {
		this.politdate = politdate;
	}
	public String getPolitid() {
		return politid;
	}
	public void setPolitid(String politid) {
		this.politid = politid;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getSesign1() {
		return sesign1;
	}
	public void setSesign1(String sesign1) {
		this.sesign1 = sesign1;
	}
	public String getSesign2() {
		return sesign2;
	}
	public void setSesign2(String sesign2) {
		this.sesign2 = sesign2;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatedate() {
		return statedate;
	}
	public void setStatedate(String statedate) {
		this.statedate = statedate;
	}
	public String getStateid() {
		return stateid;
	}
	public void setStateid(String stateid) {
		this.stateid = stateid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
