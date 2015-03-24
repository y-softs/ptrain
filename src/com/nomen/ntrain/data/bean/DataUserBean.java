package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Ա������Ϣ��POJO 
 * @author Ǯ�º�
 * @date 2009-09-30
 * @modifier �¸���-������
 * @date 2010-04-29��2011-07-21
 */

public class DataUserBean implements Serializable{
	private String id;		    //id��������
	private String unitid;		//�ֹ�����λid
	private String deptid;		//�ֹ�������id
	private String groupid;		//�ֹ�������id
	private String username;	//����
	private String oldname;     //������
	private String py;		    //����ƴ��
	private String photo;		//��Ƭ
	private String userid;		//���֤��
	private String workid;		//����
	private String countryid;	//����
	private String natalid;		//����
	private String sex;		    //�Ա�
	private String birthday;	//��������
	private String birthplace;  //������
	private String religion;    //�ڽ�����ID�������ID����,�����
	private String healthid;    //����״��ID
	private String marryid;     //����״��ID
	private String place;		//����
	private String politid;		//������ò
	private String politdate;	//����ʱ��
	private String kindid;		//��Ա����
	private String kinddate;	//��Ա���ʱ䶯ʱ��
	private String stateid;		//��Ա״̬
	private String statedate;	//��Ա״̬�䶯ʱ��
	private String interest;    //��Ȥ�س�
	private String loginpsd;	//��½����
	private String changesign;	//���֤��15λ��18λ��־��1�ǣ�0��
	private String oldunitid;	//ԭ������λid
	private String olddeptid;	//ԭ��������id
	private String oldgroupid;	//ԭ��������id
	private String remark;		//��ע
	private String sortnum;		//�����
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
    private byte[] image;       //ͼƬ�ֽ�����
    
    //��ע �������ֶΣ�
    private String natureid;		    //��������id
    private String oldgroupfatherid;    //���鸸id
    private String groupfatherid;		//���鸸id
    private String statefatherid;		//��Ա״̬��id
    private String marryfatherid;		//����״����id
    private String religionnames;       //�ڽ�����[����]
    private String mpost;               //����λ����
    
    //������GET �� SET ����
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarryfatherid() {
		return marryfatherid;
	}
	public void setMarryfatherid(String marryfatherid) {
		this.marryfatherid = marryfatherid;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getNatalid() {
		return natalid;
	}
	public void setNatalid(String natalid) {
		this.natalid = natalid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPolitid() {
		return politid;
	}
	public void setPolitid(String politid) {
		this.politid = politid;
	}
	public String getPolitdate() {
		return politdate;
	}
	public void setPolitdate(String politdate) {
		this.politdate = politdate;
	}
	public String getKindid() {
		return kindid;
	}
	public void setKindid(String kindid) {
		this.kindid = kindid;
	}
	public String getKinddate() {
		return kinddate;
	}
	public void setKinddate(String kinddate) {
		this.kinddate = kinddate;
	}
	public String getStateid() {
		return stateid;
	}
	public void setStateid(String stateid) {
		this.stateid = stateid;
	}
	public String getStatedate() {
		return statedate;
	}
	public void setStatedate(String statedate) {
		this.statedate = statedate;
	}
	public String getLoginpsd() {
		return loginpsd;
	}
	public void setLoginpsd(String loginpsd) {
		this.loginpsd = loginpsd;
	}
	public String getChangesign() {
		return changesign;
	}
	public void setChangesign(String changesign) {
		this.changesign = changesign;
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
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getGroupfatherid() {
		return groupfatherid;
	}
	public void setGroupfatherid(String groupfatherid) {
		this.groupfatherid = groupfatherid;
	}
	public String getStatefatherid() {
		return statefatherid;
	}
	public void setStatefatherid(String statefatherid) {
		this.statefatherid = statefatherid;
	}
	public String getOlddeptid() {
		return olddeptid;
	}
	public void setOlddeptid(String olddeptid) {
		this.olddeptid = olddeptid;
	}
	public String getOldgroupid() {
		return oldgroupid;
	}
	public void setOldgroupid(String oldgroupid) {
		this.oldgroupid = oldgroupid;
	}
	public String getOldunitid() {
		return oldunitid;
	}
	public void setOldunitid(String oldunitid) {
		this.oldunitid = oldunitid;
	}
	public String getOldgroupfatherid() {
		return oldgroupfatherid;
	}
	public void setOldgroupfatherid(String oldgroupfatherid) {
		this.oldgroupfatherid = oldgroupfatherid;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getHealthid() {
		return healthid;
	}
	public void setHealthid(String healthid) {
		this.healthid = healthid;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getMarryid() {
		return marryid;
	}
	public void setMarryid(String marryid) {
		this.marryid = marryid;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getReligionnames() {
		return religionnames;
	}
	public void setReligionnames(String religionnames) {
		this.religionnames = religionnames;
	}
	public String getMpost() {
		return mpost;
	}
	public void setMpost(String mpost) {
		this.mpost = mpost;
	}
}