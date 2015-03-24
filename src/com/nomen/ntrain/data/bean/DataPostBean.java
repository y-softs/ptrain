package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Ա��λ��Ϣ��POJO 
 * @author Ǯ�º�
 * @date 2009-10-20
 * @modifier �¸���-������
 * @date 2010-05-07��2011-06-15
 */

public class DataPostBean implements Serializable{
	private String userid;			//��Աid
	private String mgrade;			//����λ�㼶id
	private String mpostid;			//����λ����id
	private String mpost;			//����λ����
	private String mdate;	        //����λ��ְʱ�� 
	private String mstate;			//����λ״̬id
	private String mtype;			//����λ���Ź���id
	private String pgrade1;			//��ְ��λ1�㼶id
	private String ppostid1;		//��ְ��λ1����id
	private String ppost1;			//��ְ��λ1����
	private String pdate1;	 		//��ְ��λ1��ְʱ��
	private String pstate1;			//��ְ��λ1״̬id
	private String ptype1;			//��ְ��λ1���Ź���id
	private String pgrade2;			//��ְ��λ2�㼶id
	private String ppostid2;		//��ְ��λ2����id
	private String ppost2;			//��ְ��λ2����
	private String pdate2;			//��ְ��λ2��ְʱ��
	private String pstate2;			//��ְ��λ2״̬id
	private String ptype2;			//��ְ��λ2���Ź���id
	private String pgrade3;			//��ְ��λ3�㼶id
	private String ppostid3;		//��ְ��λ3����id
	private String ppost3;			//��ְ��λ3����
	private String pdate3;			//��ְ��λ3��ְʱ��
	private String pstate3;			//��ְ��λ3״̬id
	private String ptype3;			//��ְ��λ3���Ź���id
	private String typeid;			//��Ա���id
	private String posttypeid;		//��λ����id
	private String specid;			//רҵ���ID
	private String specname;		//רҵ����
	private String workyear;		//������רҵ���
	private String postgrade;		//�ڼ�
	private String dutygrade;		//ְ��
	private String dutydate;		//ְ��Ƹ��ʱ��
	private String natureid;		//��������id
	private String partid;			//���⹤������id
	private String partdate;	    //���⹤��Ƹ��ʱ��
	private String specpost;		//�ض���λID��ID����,�����
	private String remark;			//��ע
	private String estauser;		//������
	private String estatime;		//����ʱ��
	private String mainuser;		//ά����
	private String maintime;		//ά��ʱ��
	private String strflag;			//Ԥ���ֶΣ��ַ�����
	private String intflag;			//Ԥ���ֶΣ����ͣ�
	
	//�����ֶ�
	private String posttypefatherid; //��λ���ุ��id
	private String posttypeendsign;  //��λ����ĩ�˱�־
	private String specfatherid; 	 //רҵ��𸸼�id
	private String specidendsign; 	 //רҵ���ĩ�˱�־
	private String partfatherid; 	 //���⹤�ָ���id
	
	private String posttypename;     //��λ����[����]
	private String specpoststr;		//�ض���λ[����]
	private String specnamestr;		//רҵ/�������[����]
	private String workmonth; 		//������רҵ�·�

	//������GET �� SET ����
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMgrade() {
		return mgrade;
	}
	public void setMgrade(String mgrade) {
		this.mgrade = mgrade;
	}
	public String getMpostid() {
		return mpostid;
	}
	public void setMpostid(String mpostid) {
		this.mpostid = mpostid;
	}
	public String getMpost() {
		return mpost;
	}
	public void setMpost(String mpost) {
		this.mpost = mpost;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMstate() {
		return mstate;
	}
	public void setMstate(String mstate) {
		this.mstate = mstate;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getPgrade1() {
		return pgrade1;
	}
	public void setPgrade1(String pgrade1) {
		this.pgrade1 = pgrade1;
	}
	public String getPpostid1() {
		return ppostid1;
	}
	public void setPpostid1(String ppostid1) {
		this.ppostid1 = ppostid1;
	}
	public String getPpost1() {
		return ppost1;
	}
	public void setPpost1(String ppost1) {
		this.ppost1 = ppost1;
	}
	public String getPdate1() {
		return pdate1;
	}
	public void setPdate1(String pdate1) {
		this.pdate1 = pdate1;
	}
	public String getPstate1() {
		return pstate1;
	}
	public void setPstate1(String pstate1) {
		this.pstate1 = pstate1;
	}
	public String getPtype1() {
		return ptype1;
	}
	public void setPtype1(String ptype1) {
		this.ptype1 = ptype1;
	}
	public String getPgrade2() {
		return pgrade2;
	}
	public void setPgrade2(String pgrade2) {
		this.pgrade2 = pgrade2;
	}
	public String getPpostid2() {
		return ppostid2;
	}
	public void setPpostid2(String ppostid2) {
		this.ppostid2 = ppostid2;
	}
	public String getPpost2() {
		return ppost2;
	}
	public void setPpost2(String ppost2) {
		this.ppost2 = ppost2;
	}
	public String getPdate2() {
		return pdate2;
	}
	public void setPdate2(String pdate2) {
		this.pdate2 = pdate2;
	}
	public String getPstate2() {
		return pstate2;
	}
	public void setPstate2(String pstate2) {
		this.pstate2 = pstate2;
	}
	public String getPtype2() {
		return ptype2;
	}
	public void setPtype2(String ptype2) {
		this.ptype2 = ptype2;
	}
	public String getPgrade3() {
		return pgrade3;
	}
	public void setPgrade3(String pgrade3) {
		this.pgrade3 = pgrade3;
	}
	public String getPpostid3() {
		return ppostid3;
	}
	public void setPpostid3(String ppostid3) {
		this.ppostid3 = ppostid3;
	}
	public String getPpost3() {
		return ppost3;
	}
	public void setPpost3(String ppost3) {
		this.ppost3 = ppost3;
	}
	public String getPdate3() {
		return pdate3;
	}
	public void setPdate3(String pdate3) {
		this.pdate3 = pdate3;
	}
	public String getPstate3() {
		return pstate3;
	}
	public void setPstate3(String pstate3) {
		this.pstate3 = pstate3;
	}
	public String getPtype3() {
		return ptype3;
	}
	public void setPtype3(String ptype3) {
		this.ptype3 = ptype3;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getPosttypeid() {
		return posttypeid;
	}
	public void setPosttypeid(String posttypeid) {
		this.posttypeid = posttypeid;
	}
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public String getSpecname() {
		return specname;
	}
	public void setSpecname(String specname) {
		this.specname = specname;
	}
	public String getWorkyear() {
		return workyear;
	}
	public void setWorkyear(String workyear) {
		this.workyear = workyear;
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
	public String getDutydate() {
		return dutydate;
	}
	public void setDutydate(String dutydate) {
		this.dutydate = dutydate;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getPartid() {
		return partid;
	}
	public void setPartid(String partid) {
		this.partid = partid;
	}
	public String getPartdate() {
		return partdate;
	}
	public void setPartdate(String partdate) {
		this.partdate = partdate;
	}
	public String getSpecpost() {
		return specpost;
	}
	public void setSpecpost(String specpost) {
		this.specpost = specpost;
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
	public String getPosttypefatherid() {
		return posttypefatherid;
	}
	public void setPosttypefatherid(String posttypefatherid) {
		this.posttypefatherid = posttypefatherid;
	}
	public String getPosttypeendsign() {
		return posttypeendsign;
	}
	public void setPosttypeendsign(String posttypeendsign) {
		this.posttypeendsign = posttypeendsign;
	}
	public String getPosttypename() {
		return posttypename;
	}
	public void setPosttypename(String posttypename) {
		this.posttypename = posttypename;
	}
	public String getSpecfatherid() {
		return specfatherid;
	}
	public void setSpecfatherid(String specfatherid) {
		this.specfatherid = specfatherid;
	}
	public String getSpecidendsign() {
		return specidendsign;
	}
	public void setSpecidendsign(String specidendsign) {
		this.specidendsign = specidendsign;
	}
	public String getPartfatherid() {
		return partfatherid;
	}
	public void setPartfatherid(String partfatherid) {
		this.partfatherid = partfatherid;
	}
	public String getWorkmonth() {
		return workmonth;
	}
	public void setWorkmonth(String workmonth) {
		this.workmonth = workmonth;
	}
	public String getSpecpoststr() {
		return specpoststr;
	}
	public void setSpecpoststr(String specpoststr) {
		this.specpoststr = specpoststr;
	}
	public String getSpecnamestr() {
		return specnamestr;
	}
	public void setSpecnamestr(String specnamestr) {
		this.specnamestr = specnamestr;
	}
	
	
}